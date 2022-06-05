
/*
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.profileManager.src.main.java;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * Common methods for data validation
 * @param <ValueClass>  the Base Type for Code View
 * @param <ValueClass> Either ValueClass or List<ValueClass>
 */
public abstract class Abstract_Valid<ValueClass> 
						extends Abstract_Valid_Base<ValueClass>{

	private static final String LIST_SEPARATOR = "/";
	private boolean isList;
	private Map<History, String> historyMap = new EnumMap<>(History.class);
	
	private boolean isClassCollection(Class value) {
		  return Collection.class.isAssignableFrom(value) || Map.class.isAssignableFrom(value);
		}
    // ==================================================
    // Constructors and initializers
    //
	Abstract_Valid(ValueClass defaultValue) {
		isList = defaultValue instanceof Collection;
	}

	Abstract_Valid(boolean isList) {
		this.isList = isList;}

	Abstract_Valid(List<ValueClass> options) {
		this.isList = false;
		initList(options);
	}
	
	Abstract_Valid(List<ValueClass> options, boolean isList) {
		this.isList = isList;
		initList(options);
	}
	
	private void initList(List<ValueClass> options) {
		if (options == null) {
			return;
		}
		for (ValueClass option : options) {
			this.addElement(option,
					PMutil.suggestedUserViewFromCodeView(option));
		}
	}
	
	// ==================================================
    // Abstract Methods Request
    //
	/**
	 * Validate the limits for the Value class type
	 * @param value the {@code Code View} Value to validate
	 * @param lim1  the {@code Code View} first limit
	 * @param lim2  the {@code Code View} second limit
	 * @return {@code Code View} Validated Value
	 */
	abstract ValueClass validateLimits(ValueClass value,
										ValueClass lim1, ValueClass lim2);

	/**
	 * Process Random without parameters
	 * @return {@code Integer} OutputString
	 */
	abstract String getRandom(ValueClass lim1, ValueClass lim2);

	/**
	 * Process non Random user entry
	 * @return {@code Code View} Validated Value
	 */
	String entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryUserView(Default);
		}
		// Then Check if value is valid
		ValueClass value = toCodeView(userEntry);  // Raw conversion
		if (value == null) {
			// not Valid Check if part of the list 
			if (hasList() && isValidUserEntry(userEntry)) {
				return userEntry;
			} // Nothing Valid, then either blank or default
			else if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			else {
				return getHistoryUserView(Default);
			}
		} else {
			// Check for limit before returning the value
			return toUserView(validateLimits(value, getLimits(0), getLimits(1)));
		}
	}

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Code View} OutputString
	 */
//	abstract ValueClass randomWithLimit(String[] parameters);
	String randomWithLimit(String[] parameters) {
		ValueClass lim1 = getLimits(0);
		ValueClass lim2 = getLimits(1);
		if (parameters.length >= 1) {
			ValueClass codeView = toCodeView(parameters[0]);
			if (codeView != null) {
				lim1 = validateLimits(codeView, lim1, lim2);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			ValueClass codeView = toCodeView(parameters[1]);
			if (codeView != null) {
				lim2 = validateLimits(codeView, getLimits(0), lim2);
			} 
		}
		// get Random
		return getRandom(lim1, lim2);
	}

	/**
	 * Process Random without parameters
	 * @return {@code Code View} Output Value
	 */
//	abstract ValueClass randomWithoutParameters();
	String randomWithoutParameters() {
		return getRandom(getDefaultRandomLimits(0), getDefaultRandomLimits(1));
	}

	/**
	 * Conversion from code view to user view
	 * <br> For already validated Data
	 * @param codeView {@code Code View} the value to convert
	 * @return {@code String} the user view
	 */
	abstract String toUserView(ValueClass codeView);
	
	/**
	 * Conversion from user view to code view.
	 * <br> For already validated Data
	 * @param codeView {@code String} the value to convert
	 * @return {@code Code View} the code view
	 */
	abstract ValueClass toCodeView(String userView);

	// ------------------------------------------------
    // Overriders
    //	
	/**
	 * Get the default limit value
	 * @return the default limit Code View
	 */
	@Override ValueClass getDefaultLimit() {
		return getHistoryCodeView(Default);
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set the "history" Code View
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */

	protected void setHistoryUserView(History history, String newValue) {
		if (history == Last) { // if in two step to allow breakpoint
			if (!PMutil.neverNull(historyMap.get(Last)).isBlank()) {
				return; // Last was already assigned	
			}
		}
		historyMap.put(history, newValue);
		if (history == Initial) {
			historyMap.put(Current, newValue);
		}
	}

	/**
	 * Set the "history" User View
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */
	protected void setHistoryCodeView(History history, ValueClass newValue) {
		setHistoryUserView(history, toUserView(newValue));
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the "history" Code View
	 * @param history  Field to be retrieved
	 * @return the "history" Code View
	 */
	protected ValueClass getHistoryCodeView(History history) {
		return toCodeView(historyMap.get(history));
	}

	/**
	 * Get the "history" User View
	 * @param history  Field to be retrieved
	 * @return the "history" User View
	 */
	protected String getHistoryUserView(History history) {
		return historyMap.get(history);
	}
	// ==================================================
    // Test Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	protected String randomWithInListLimit(String[] parameters) {
		int min = 0;
		int max = listSize();
		// First Limit
		if (parameters.length >= 1) {
			min = getUserViewIndex(parameters[0], min);
		}
		// Second Limit
		if (parameters.length >= 2) {
			max = getUserViewIndex(parameters[1], max);
		}
		// get Random
		int id = PMutil.getRandom(min, max);
		return getUserView(id);
	}

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Output Value
	 */
	String randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		if (hasList() && isValidUserEntry(parameters[0])) {
			return randomWithInListLimit(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	String randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}
	// ==================================================
    // Other Methods
    //
	/**
	 * Analyze user Entry content
	 * @return 
	 */
	protected String entryAnalysis(String userEntry) {
		if ( getValidationCriteria().isRandomAllowed()
				&& isRandom(userEntry)) {
			if (hasExtraParameters(userEntry)) {
				return randomWithParameters(
						splitParameters(removeRandomId(userEntry)));
			}
			return randomWithoutParameters();
		}
		return entryValidation(userEntry);
	}
}
