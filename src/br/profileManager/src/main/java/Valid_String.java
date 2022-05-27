
/*
 * Licensed under the GNU General License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	 https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.profileManager.src.main.java;

import java.util.List;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * For the validation of the {@code String}
 */
public class Valid_String extends Abstract_ValidData<String> {
		
    // ==================================================
    // Constructors and initializers
    //
	protected Valid_String() {
		super();
		init();
	}
	protected Valid_String(List<String> options) {
		super(options);
		init();
	}
	
	private void initCriteria() {
		setValidationCriteria(new ValidationCriteria());
	}
	
	private void init() {
		initCriteria();
		setHistoryCodeView(Default, "");
		setHistoryCodeView(Last, "");
		setHistoryCodeView(Initial, "");
		setHistoryCodeView(Current, "");
		setHistoryCodeView(Game, "");
		setHistoryCodeView(Blank, "");
	}

	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code String} Validated Code View
	 */
	@Override String entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return userEntry;
			}
			return getHistoryUserView(Default);
		}
		// Then Check for a validation list
		if (!hasList()) {
			// No list = entry accepted
			return userEntry;
		}
		// Then check validity
		if (isValidUserEntry(userEntry)) {
			return toCodeView(userEntry);
		}
		return getHistoryUserView(Default);
	}

	/**
	 * Process Random without parameters
	 * @return {@code String} Random Value
	 */
	@Override String randomWithoutParameters() {
		if (hasList()) {
			// without default Random Limits
			if (getDefaultRandomLimits() == null 
					|| getDefaultRandomLimits().size() == 0) {
				int id = PMutil.getRandom(0, listSize());
				return getCodeView(id);
			}
			// with String default Random Limits
			int min = 0;
			if (getDefaultRandomLimits().size() >= 1) {
				min = getUserViewIndex(getDefaultRandomLimits(0), min);
			}
			int max = listSize();
			if (getDefaultRandomLimits().size() >= 2) {
				max = getUserViewIndex(getDefaultRandomLimits(1), max);
			}
			int id = PMutil.getRandom(min, max);
			return getCodeView(id);
		}
		return getHistoryUserView(Default); // What else?
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	@Override String randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override String toCodeView(String userView) {
		return getCodeView(userView);
	}
	
	@Override String toUserView(String codeView) {
		return getUserView(codeView);
	}
	
	/**
	 * Test if the user entry is part of the validation list
	 * for both unique and multiple values allowed,
	 * and return the {@code Code View} value as {@code String}
	 * @param userEntry the value to test
	 * @return the code view, "" if none
	 */
	@Override protected String getCodeView(String userEntry) {
 		if (userEntry != null) {
 			if (getValidationCriteria().userViewEquals()) {
 				// get only one
 				for (ValidationElement<String> element : getValidationList()) {
					if (element.isValidUserEntry(userEntry, getValidationCriteria())) {
						return element.getCodeView();
					}
				}
 			}
 			// get all Valid
 			String out = "";
			for (ValidationElement<String> element : getValidationList()) {
				if (element.isValidUserEntry(userEntry, getValidationCriteria())) {
					if (!out.isBlank()) {
						out += ", ";
					}
					out += element.getCodeView();
				}
			}
			return out;
 		}
		return getHistoryUserView(Blank);
//		return getBlankValue();
	}

	/**
	 * Test if the code view is part of the validation list
	 * for both unique and multiple values allowed,
	 * and return the user view
	 * @param codeView the code view to test
	 * @return the user view, "" if none
	 */
	@Override protected String getUserView(String codeView) {
		if (codeView != null) {
 			if (getValidationCriteria().userViewEquals()) {
 				// get only one
 				for (ValidationElement<String> element : getValidationList()) {
					if (element.isValidCodeView(codeView, getValidationCriteria())) {
						return element.getUserView();
					}
				}
 			}
 			// get all Valid
 			String out = "";
			for (ValidationElement<String> element : getValidationList()) {
				if (element.isValidCodeView(codeView, getValidationCriteria())) {
					if (!out.isBlank()) {
						out += ", ";
					}
					out += element.getUserView();
				}
			}
			if (out.isBlank()) {
				return codeView; // Not in the list
			}
			return out;
 		}
		return "";
	}

	// ==================================================
    // Other Methods
    //
	private Integer getBestLimit(String[] parameters, int index, int[] lim) {
		int out = lim[index];
		// get given parameter and test it
		String param = parameters[index];
		// test for integer
		if (PMutil.testForInteger(param)) {
			return PMutil.validateLimits(PMutil.toInteger(param), lim[0], lim[1]);
		} 
		// test if in list
		if (isValidUserEntry(param)) {
			return this.getUserViewIndex(param, out);
		}
		// get default value and repeat the test
		param = getDefaultRandomLimits(index);
		if (PMutil.testForInteger(param)) {
			return PMutil.validateLimits(PMutil.toInteger(param), lim[0], lim[1]);
		} 
		if (isValidUserEntry(param)) {
			return this.getUserViewIndex(param, out);
		}
		// Nothing good... return the limit
		return out;
	}
	
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	String randomWithLimit(String[] parameters) {
		int min = 0;
		int max = listSize();
		int[] lim = new int[] {min, max};
		// First Limit
		if (parameters.length >= 1) {
			min = getBestLimit(parameters, 0, lim);
		}
		// Second Limit
		if (parameters.length >= 2) {
			max = getBestLimit(parameters, 1, lim);
		}
		// get Random
		int id = PMutil.getRandom(min, max);
		return getCodeView(id);
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

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_String extends Generic_Line<String, Valid_String>{

	   	// ==========================================================
	    // Constructors
	    //
		Line_String() {
			super(new Valid_String());
			setValue("");
		}

		/**
		 * @param name    {@code String} key / config name
		 * @param value   {@code String} value
		 * @param comment {@code String} comment, null if none
		 */
		Line_String(String name, String value, String comment) {
			super(new Valid_String());
			if (comment != null) {
				setComment(comment);
			}
			setName(name);
			setValue(value);
		}
		/**
		 * @param validationData {@code Valid_String} validation parameters
		 */
		Line_String(Abstract_ValidData<String> validationData) {
			super(validationData);
			setValue("");
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_String(String line) {
			super(new Valid_String(), line);
		}
		
		/**
		 * @param validationData {@code Valid_String} validation parameters
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_String(Abstract_ValidData<String> validationData, String line) {
			super(validationData, line);
		}
	}
}