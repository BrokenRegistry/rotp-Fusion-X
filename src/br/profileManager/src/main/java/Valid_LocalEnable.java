
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
//class Valid_LocalEnable extends Valid_ProfileAction {
class Valid_LocalEnable extends Valid_String {

	// From Valid_ConfigAction
		static final String LOAD_ENABLED    = "ENABLE_LOAD_LIST";
		static final String WRITE_ENABLED   = "ENABLE_WRITE_LIST";
		static final String GAME_ENABLED    = "ENABLE_GAME_LIST";
	//	static final String SPECIAL_ENABLED = "SPECIAL_LIST";
	//  static final String LOCAL_ENABLED = "LOCAL_LIST";

	public  static final String PARAMETER_NAME = "¦ LOCAL ENABLE";
	private static final String DEFAULT_VALUE  = "ALL";

	Valid_LocalEnable() {
		setDefaultName(PARAMETER_NAME);
		setHistoryUserView(Default, DEFAULT_VALUE);
		setHistoryUserView(Current, DEFAULT_VALUE);
		setShowWithOptions(true);
		
		setValidationCriteria(new ValidationCriteria()
				.isRandomAllowed(false)
				.userViewEquals(false)
				.codeViewEquals(false));

		addElement("NO",
				"No actions are allowed in this Setting" ,
//				LOCAL_ENABLED
				"");
		addElement("ALL",
				"All actions are allowed in this Setting" ,
//				LOCAL_ENABLED + " " + 
				LOAD_ENABLED + " " + WRITE_ENABLED);
		addElement("WRITE", 
				"Allows actions that change the file" ,
//				LOCAL_ENABLED + " " + 
				WRITE_ENABLED);
		addElement("LOAD", 
				"Allows actions Get from GUI and Save to file" ,
//				LOCAL_ENABLED + " " + 
				LOAD_ENABLED);
	}

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_LocalEnable extends Generic_Line<String, Valid_LocalEnable>{

	 	// ==========================================================
	    // Constructors
	    //
		/**
		 * Create a new standard default valued LocalEnable
		 */
		Line_LocalEnable() {
			super(new Valid_LocalEnable());
			setValue(DEFAULT_VALUE);
		}

		/**
		 * Create a new standard LocalEnable with a custom value
		 */
		Line_LocalEnable(String value) {
			super(new Valid_LocalEnable());
			setValue(value);
		}

		// ==========================================================
		// Getters and Setters
		//
		boolean isLoadEnabled() {
			return isValueFromCategory(LOAD_ENABLED);
		}
		
		boolean isWriteEnabled() {
			return isValueFromCategory(WRITE_ENABLED);
		}
		
//		boolean isLocal() {
//			return isValueFromCategory(LOCAL_ENABLED);
//		}
	}
}
