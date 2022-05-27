
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

import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * For the validation of the {@code Boolean}
 */
public class Valid_Boolean extends Abstract_ValidData<Boolean> {
	
	// ==================================================
    // Constructors
    //
	/**
	 * Base validation for Boolean parameters
	 */
	public Valid_Boolean() {
		setValidationCriteria(new ValidationCriteria());
	}

	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code Boolean} Validated Value
	 */
	@Override Boolean entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryCodeView(Default);
		}
		// Then Check if value is valid
		Boolean value = PMutil.toBoolean(userEntry);
		if (value == null) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryCodeView(Default);
		}
		return value;
	}
	
	/**
	 * Process Random without parameters
	 * @return {@code Boolean} OutputString
	 */
	@Override Boolean randomWithoutParameters() {
		return PMutil.getBooleanRandom();
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Boolean} OutputString
	 */
	@Override Boolean randomWithParameters(String[] parameters) {
		// Could be used to have a weighting on the Yes or No
		// ex: Random yes, yes, yes, NO
		return randomWithList(parameters);
	}
	
	@Override String toUserView(Boolean codeView) {
		return PMutil.toYesNoString(codeView);
	}
	
	@Override Boolean toCodeView(String userView) {
		return PMutil.toBoolean(userView);
	}
	
	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Boolean} Random Value
	 */
	Boolean randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Boolean extends Generic_Line<Boolean, Valid_Boolean>{

	   	// --------------------------------------------------------------
	    // Constructors
	    //
		Line_Boolean() {
			super(new Valid_Boolean());
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_Boolean(String line) {
			super(new Valid_Boolean(), line);
		}
	}
}
