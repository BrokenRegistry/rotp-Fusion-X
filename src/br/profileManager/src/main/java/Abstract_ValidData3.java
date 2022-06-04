
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

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * Common methods for data validation
 * @param <ValueClass> the Value's Code View Class
 */
public abstract class Abstract_ValidData3<ValueClass> extends WriteUtil{
	
	private List<ValidationElement<ValueClass>> validationList =
			new ArrayList<ValidationElement<ValueClass>>();
	private ValidationCriteria criteria = new ValidationCriteria();

	private List<ValueClass> defaultRandomLimits = new ArrayList<ValueClass>();
	private List<ValueClass> limits = new ArrayList<ValueClass>();
	private Map<History, ValueClass> historyMap = new EnumMap<>(History.class);
	
	private String  defaultName     = "none";
	private boolean showWithOptions = false;
	private boolean showHistory     = true;
	private boolean showLocalEnable = true;
	
	/**
	 * Set the "history" Code View
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */

	protected void setHistoryCodeView(History history, ValueClass newValue) {
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
	protected void setHistoryUserView(History history, String newValue) {
		setHistoryCodeView(history, toCodeView(newValue));
	}

	/**
	 * Get the "history" Code View
	 * @param history  Field to be retrieved
	 * @return the "history" Code View
	 */
	protected ValueClass getHistoryCodeView(History history) {
		return historyMap.get(history);
	}

	/**
	 * Get the "history" User View
	 * @param history  Field to be retrieved
	 * @return the "history" Code View
	 */
	protected String getHistoryUserView(History history) {
		return toUserView(historyMap.get(history));
	}

    // ==================================================
    // Constructors and initializers
    //
	Abstract_ValidData3() {}

	Abstract_ValidData3(List<ValueClass> options) {
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
	 * Process non Random user entry
	 * @return {@code Code View} Validated Value
	 */
	abstract ValueClass entryValidation(String userEntry);

	/**
	 * Process Random without parameters
	 * @return {@code Code View} OutputString
	 */
	abstract ValueClass randomWithoutParameters();

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Code View} OutputString
	 */
	abstract ValueClass randomWithLimit(String[] parameters);

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
	
	// ==================================================
    // Setters
    //
	/**
	 * Set the showWithOptions parameter value
	 * @param newValue
	 */
	protected void setShowWithOptions(boolean newValue) {
		showWithOptions = newValue;
	}

	/**
	 * Set the showHistory parameter value
	 * @param newValue
	 */
	protected void setShowHistory(boolean newValue) {
		showHistory = newValue;
	}

	/**
	 * Set the showLocalEnable parameter value
	 * @param newValue
	 */
	protected void setShowLocalEnable(boolean newValue) {
		showLocalEnable = newValue;
	}

	/**
	 * Set the {@code Code View} limits
	 * @param newLimits
	 */
	protected void setLimits(List<ValueClass> newLimits) {
		limits = newLimits;
	}

	/**
	 * Set the {@code Code View} limits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setLimits(ValueClass Limit1, ValueClass Limit2) {
		limits = new ArrayList<ValueClass>();
		limits.add(Limit1);
		limits.add(Limit2);
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param newLimits
	 */
	protected void setDefaultRandomLimits(List<ValueClass> newLimits) {
		defaultRandomLimits = newLimits;
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setDefaultRandomLimits(ValueClass Limit1, ValueClass Limit2) {
		defaultRandomLimits = new ArrayList<ValueClass>();
		defaultRandomLimits.add(Limit1);
		defaultRandomLimits.add(Limit2);
	}

	/**
	 * Set the {@code ValidationCriteria} criteria
	 * @param newCriteria
	 */
	protected void setValidationCriteria(ValidationCriteria newCriteria) {
		criteria = newCriteria;
	}

	/**
	 * Set the default parameter Name 
	 * @param newValue the new default Parameter
	 */
	protected void setDefaultName(String newName) {
		defaultName = newName;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the showWithOptions parameter value
	 * @return newValue
	 */
	protected boolean isShowWithOptions() {
		return showWithOptions;
	}

	/**
	 * Get the showHistory parameter value
	 * @return newValue
	 */
	protected boolean isShowHistory() {
		return showHistory;
	}

	/**
	 * Get the showLocalEnable parameter value
	 * @return newValue
	 */
	protected boolean isShowLocalEnabled() {
		return showLocalEnable;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected List<ValidationElement<ValueClass>> getValidationList() {
		return validationList;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected List<ValueClass> getLimits() {
		return limits;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected ValueClass getLimits(int index) {
		if (limits.isEmpty()) {
			return getHistoryCodeView(Default);
		}
		if (index < 0) {
			return limits.get(0);
		}
		if (index > limits.size()-1) {
			return limits.get(limits.size()-1);
		}
		return limits.get(index);
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * @return the limits
	 */
	protected List<ValueClass> getDefaultRandomLimits() {
		return defaultRandomLimits;
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * index the limit index
	 * @return the limit
	 */
	protected ValueClass getDefaultRandomLimits(int index) {
		if (defaultRandomLimits.isEmpty()) {
			return getHistoryCodeView(Default);
		}
		if (index < 0) {
			return defaultRandomLimits.get(0);
		}
		if (index > defaultRandomLimits.size()-1) {
			return defaultRandomLimits.get(defaultRandomLimits.size()-1);
		}
		return defaultRandomLimits.get(index);
	}

	/**
	 * Get the {@code ValidationCriteria} limits
	 * @return the limits
	 */
	public ValidationCriteria getValidationCriteria() {
		return criteria;
	}

	/**
	 * Get the default parameter name
	 * @return the current value
	 */
	protected String getDefaultName() {
		return defaultName;
	}

	// ==================================================
    // Test Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	protected ValueClass randomWithInListLimit(String[] parameters) {
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
		return getCodeView(id);
	}

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code ValueClass} Output Value
	 */
	ValueClass randomWithParameters(String[] parameters) {
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
	 * @return {@code ValueClass} Random Value
	 */
	ValueClass randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

	/**
	 * @return <b>true</b> if the Validation List is not empty
	 */
	protected boolean hasList() {
		return this.validationList.size() > 0;
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Test if the user entry is part of the validation list
	 * and return the category
	 * @param userEntry the {@code User View} value to test
	 * @return the category, "" if none
	 */
 	protected String getCategory(String userEntry) {
 		if (userEntry != null) {
 			for (ValidationElement<ValueClass> element : validationList) {
 				if (element.isValidUserEntry(userEntry, criteria)) {
 					return element.getCategory();
 				}
 			}
 		}
		return "";
	}

 	/**
	 * Test if the user entry is part of the validation list
	 * and return the {@code Code View} value as {@code String}
	 * @param userEntry the value to test
	 * @return the code view, "" if none
	 */
 	protected ValueClass getCodeView(String userEntry) {
 		if (userEntry != null) {
 			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, criteria)) {
					return element.getCodeView();
				}
			}
 		}
return null; // TODO
//		return getHistoryCodeView(Blank);
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return the user view
	 * @param codeView the code view to test
	 * @return the user view, "" if none
	 */
	protected String getUserView(ValueClass codeView) {
		if (codeView == null) {
			return "";
		}
		for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, criteria)) {
				return element.getUserView();
			}
		}
		return codeView.toString(); // Not in the list
	}
	
	/**
	 * Test if the user view is part of the validation list
	 * and return its index, or the default one if none
	 * @param userView the {@code String} to search
	 * @param defaultIndex the {@code int} default returned value
	 * @return the {@code int} index
	 */
	protected int getUserViewIndex(String userView, int defaultIndex) {
		userView = PMutil.clean(userView);
		int index = 0;
		for (ValidationElement<ValueClass> element : validationList) {
			if (element.isValidUserEntry(userView, criteria)) {
				return index;
			}
			index++;
		}
		return defaultIndex;
	}
	
	/**
	 * @return The list size
	 */
	protected int listSize() {
		return validationList.size();
	}
	
	/**
	 *  @return The CodeView from its index
	 */
	protected ValueClass getCodeView(int index) {
		return validationList.get(index).getCodeView();
	}
	
	/**
	 *  @return The UserView from its index
	 */
	protected String getUserView(int index) {
		return validationList.get(index).getUserView();
	}
	
	/**
	 * Test if the user view is part of the category list
	 * and return the code view
	 * @param userEntry the user view to check
	 * @param category the filter to apply
	 *  @return The CodeView, "" if none
	 */
	protected ValueClass getCodeView(String userEntry, String category) {
 		if (userEntry != null && userEntry != category) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return element.getCodeView();
				}
			}
 		}
 		return null; // TODO
 		//		return getHistoryCodeView(Blank);
	}
	
	/**
	 * Test if the user view is part of the category list
	 * @param userEntry the user view to check
	 */
	protected boolean isValidUserEntry(String userEntry) {
		if (userEntry != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is part of the category validation list
	 * @param userEntry the user view to check
	 * @param category the filter to apply
	 */
	protected boolean isValidUserEntry(String userEntry, String category) {
		if (userEntry != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is part of the category validation list
	 * @param codeView the user view to check
	 * @param category the filter to apply
	 */
	protected boolean isValidCodeView(ValueClass codeView, String category) {
		if (codeView != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is valid
	 * @param codeView the user view to check
	 */
	protected boolean isValidCodeView(ValueClass codeView) {
		if (codeView != null) {
			if (criteria.isBlankAllowed() && codeView.toString().isBlank()) {
				return true;
			}
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return UserViewList to String List
	 */
	protected List<String> getUserViewList() {
		List<String> result = new ArrayList<String>();
		for (ValidationElement<ValueClass> element : validationList) {
			result.add(element.getUserView());
		}
		return result;
	}
	
	/**
	 * Generate UserViewList as String
	 * @return UserView List in capitalized String
	 */
	public String getOptionsRange() {
		return PMutil.capitalize(getUserViewList().toString());
	}

	/**
	 * Generate String with all Options and their = description
	 * @return the String, never null
	 */
	public String getOptionsDescription() {
		String result = "";
		String line;
		for (ValidationElement<ValueClass> element : validationList) {
			line = element.toString();
			if (!line.isBlank()) {
				result += line + NL;
			}
		}
		return result;
	}
	
	// ------------------------------------------------
    // Validation List Setters & Getters
    //	
	protected void autoUpdateLimits() {
		setLimits(getCodeView(0), getCodeView(validationList.size()-1));
		setDefaultRandomLimits(getCodeView(0), getCodeView(validationList.size()-1));
	}
	
	protected void addElement(ValidationElement<ValueClass> element) {
		validationList.add(element);
		autoUpdateLimits();
	}
	
	protected void addElement(ValueClass codeView) {
		addElement(new ValidationElement<ValueClass>(codeView));
	}
	
	protected void addElement(ValueClass codeView, String userView) {
		addElement(new ValidationElement<ValueClass>(
									codeView, userView));
	}
	
	protected void addElement(ValueClass codeView, String userView, 
					String description, String category) {
		addElement(new ValidationElement<ValueClass>(
								codeView, userView, description, category));
	}
	
	protected void addElement(ValueClass codeView, String description, String category) {
		addElement(new ValidationElement<ValueClass>(
									codeView, description, category));
	}
	
	protected void setValidationList(List<ValidationElement<ValueClass>> list) {
		validationList = list;
		autoUpdateLimits();
	}    
}
