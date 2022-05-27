
/*
 * Licensed under the GNU General License, Version 3 (the "License");
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

import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * For the validation of the configurations Action
 */
class Valid_Verbose extends Valid_String {
	
	private static final String OPTION_ENABLED  = "OPTION";
	private static final String PARAMETER_ENABLED = "PARAMETER";
	private static final String COMMENT_ENABLED = "COMMENT";

	public  static final String PARAMETER_NAME = "Verbose";
	private static final String DEFAULT_VALUE  = "FULL";

	Valid_Verbose() {
		setDefaultName(PARAMETER_NAME);
		setHistoryUserView(Default, DEFAULT_VALUE);
		setHistoryUserView(Current, DEFAULT_VALUE);
		
		setValidationCriteria(new ValidationCriteria()
				.isRandomAllowed(false)
				.userViewEquals(false)
				.codeViewEquals(false));

		addElement("NO",
				"No option comments, No parameter comments, no option" ,
				"");
		addElement("OPTION",
				"No option comments, No parameter comments, Show Options" ,
				OPTION_ENABLED);
		addElement("PARAMETER", 
				"No option comments, Parameter comments, Show Options" ,
				OPTION_ENABLED + " " + PARAMETER_ENABLED);
		addElement("FULL", 
				"Option comments, Parameter Comments, Show Options",
				OPTION_ENABLED + " " + PARAMETER_ENABLED + " " + COMMENT_ENABLED);
	}

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Verbose extends Generic_Line<String, Valid_Verbose>{

	 	// ==========================================================
	    // Constructors
	    //
		/**
		 * Create a new standard default valued LocalEnable
		 */
		Line_Verbose() {
			super(new Valid_Verbose());
			setValue(DEFAULT_VALUE);
		}

		/**
		 * Create a new standard LocalEnable with a custom value
		 */
		Line_Verbose(String value) {
			super(new Valid_Verbose());
			setValue(value);
		}

		// ==========================================================
		// Getters and Setters
		//
		boolean isOptionEnabled() {
			return isValueFromCategory(OPTION_ENABLED);
		}
		
		boolean isSettingEnabled() {
			return isValueFromCategory(PARAMETER_ENABLED);
		}
		
		boolean isCommentEnabled() {
			return isValueFromCategory(COMMENT_ENABLED);
		}
	}
}
