
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

/**
 * For the validation of the profiles Action
 */

class Valid_ProfileAction extends Valid_String {
	static final String LOAD_ENABLED    = "ENABLE_LOAD_LIST";
	static final String WRITE_ENABLED   = "ENABLE_WRITE_LIST";
	static final String GAME_ENABLED    = "ENABLE_GAME_LIST";
	static final String SPECIAL_ENABLED = "SPECIAL_LIST";
	static final String ACTION_FILE_TO_GUI  = "GUI";
	static final String ACTION_FILE_TO_GAME = "GAME";
	static final String ACTION_GUI_TO_FILE  = "SAVE";
	static final String ACTION_GAME_TO_FILE = "PULL";
	static final String ACTION_INITIAL_TO_FILE  = "INITIAL";
	static final String ACTION_GUI_UPDATE_FILE  = "UPDATE";
	static final String ACTION_GAME_UPDATE_FILE = "PICK";
	static final String ACTION_INITIAL_UPDATE_FILE = "DEFAULT";

	public  static final String PARAMETER_NAME = "PROFILES ACTIONS";
	private static final String DEFAULT_VALUE  = "SAVE";

	Valid_ProfileAction() {
		setDefaultName("None!");
		setDefaultValue(DEFAULT_VALUE);

		setValidationCriteria(new ValidationCriteria()
				.isNullAllowed(false)
				.isBlankAllowed(true)
				.isRandomAllowed(false)
				.userViewEquals(false)
				.categoryEquals(false)
				.userViewIsCaseSensitive(false)
				.codeViewIsCaseSensitive(false)
				.categoryIsCaseSensitive(false)
				.printFormat(PrintFormat.CAPITALIZE));
		
		addElement(ACTION_FILE_TO_GUI,
				"Load from file and change GUI" ,
				LOAD_ENABLED);
		addElement(ACTION_FILE_TO_GAME, 
				"Load from file and change Game at \"X LOAD\"" ,
				GAME_ENABLED + " " + LOAD_ENABLED);
		addElement(ACTION_GUI_TO_FILE, 
				"Get from GUI and Save to file" ,
				WRITE_ENABLED);
		addElement(ACTION_GAME_TO_FILE,
				"Get from Game and Save to file" ,
				WRITE_ENABLED);
		addElement(ACTION_INITIAL_TO_FILE,
				"Get initial value of GUI and Save to file" ,
				WRITE_ENABLED);
		addElement(ACTION_GUI_UPDATE_FILE,
				"If actif Field: Get from GUI and Save to file" ,
				WRITE_ENABLED);
		addElement(ACTION_GAME_UPDATE_FILE, 
				"If actif Field: Get from Game and Save to file" ,
				WRITE_ENABLED);
		addElement(ACTION_INITIAL_UPDATE_FILE,
				"If actif Field: Get initial value of GUI and Save to file" ,
				WRITE_ENABLED);
//		list.addElement("CLEAR",
//				"Remove every occurrence of this setting" ,
//				"SPECIAL_ENABLED");
	}

 	// --------------------------------------------------------------
    // Nested Class
    //
	/**
	 * Base for every profile line Action declaration
	 */
	static class Line_ProfileAction extends Generic_Line<String, Valid_ProfileAction>{

	 	// --------------------------------------------------------------
	    // Constructors
	    //
		/**
		 * Create a new standard default valued ProfileAction
		 */
		Line_ProfileAction() {
			super(new Valid_ProfileAction());
		}

		/**
		 * Create a new standard ProfileAction with a custom value
		 */
		Line_ProfileAction(String value) {
			super(new Valid_ProfileAction());
			setValue(value);
		}

		// --------------------------------------------------------------
		// Getters and Setters
		//
		boolean isLoadEnabled() {
			return isValueFromCategory(LOAD_ENABLED);
		}
		
		boolean isWriteEnabled() {
			return isValueFromCategory(WRITE_ENABLED);
		}
	}
}
