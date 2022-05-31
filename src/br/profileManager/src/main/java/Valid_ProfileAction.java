
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
 * For the validation of the profiles Action
 */

class Valid_ProfileAction extends Valid_String {
	static final String LOAD_ENABLED    = "ENABLE_LOAD_LIST";
	static final String RANDOM_ENABLED  = "ENABLE_RANDOM_LIST";
	static final String WRITE_ENABLED   = "ENABLE_WRITE_LIST";
	static final String GAME_ENABLED    = "ENABLE_GAME_LIST";
	static final String SPECIAL_ENABLED = "SPECIAL_LIST";
	static final String ACTION_RANDOM   = "Surprise";
	static final String ACTION_FILE_TO_GUI  = "Load";
	static final String ACTION_FILE_TO_GAME = "Change";
	static final String ACTION_GUI_TO_FILE  = "SaveGui";
	static final String ACTION_GAME_TO_FILE = "SaveGame";
	static final String ACTION_INITIAL_TO_FILE  = "SaveInitial";
	static final String ACTION_DEFAULT_TO_FILE  = "SaveDefault";
	static final String ACTION_GUI_UPDATE_FILE  = "GetGUI";
	static final String ACTION_GAME_UPDATE_FILE = "GetGame";
	static final String ACTION_INITIAL_UPDATE_FILE = "GetInitial";
	static final String ACTION_DEFAULT_UPDATE_FILE = "GetDefault";

	public  static final String PARAMETER_NAME = "PROFILES ACTIONS";
	private static final String DEFAULT_VALUE  = "";

	Valid_ProfileAction() {
		setDefaultName("None!");
		setHistoryUserView(Default, DEFAULT_VALUE);
		setShowHistory(false);
		setShowLocalEnable(false);
		
		setValidationCriteria(new ValidationCriteria()
				.isRandomAllowed(false)
				.userViewEquals(false)
				.codeViewEquals(false));
		
		addElement(ACTION_FILE_TO_GUI,
				"If the key \"L\" is pressed, this profile will change the GUI" ,
				LOAD_ENABLED);
		addElement(ACTION_RANDOM,
				"If the key \"R\" is pressed, this profile will change the GUI..." + NL
				+ "I use it to Randomize, but could be alternate load!" ,
				RANDOM_ENABLED);
		addElement(ACTION_FILE_TO_GAME, 
				"If the key \"X\" is pressed in Load Menu, the loaded Game will be changed" ,
				GAME_ENABLED + " " + LOAD_ENABLED);
		addElement(ACTION_GUI_TO_FILE, 
				"When a Game is started or if the key \"U\" is pressed, this profile will get the GUI settings" ,
				WRITE_ENABLED);
		addElement(ACTION_GAME_TO_FILE,
				"When a Game is started, this profile will get the Game settings" ,
				WRITE_ENABLED);
		addElement(ACTION_INITIAL_TO_FILE,
				"When a Game is started or if the key \"U\" is pressed, this profile will get the initial settings" ,
				WRITE_ENABLED);
		addElement(ACTION_DEFAULT_TO_FILE,
				"When a Game is started or if the key \"U\" is pressed, this profile will get the default settings" ,
				WRITE_ENABLED);
		addElement(ACTION_GUI_UPDATE_FILE,
				"When a Game is started or if the key \"U\" is pressed, non empty parameters of this profile will get the GUI settings" ,
				WRITE_ENABLED);
		addElement(ACTION_GAME_UPDATE_FILE, 
				"When a Game is started, non empty parameters of this profile will get the Game settings" ,
				WRITE_ENABLED);
		addElement(ACTION_INITIAL_UPDATE_FILE,
				"When a Game is started or if the key \"U\" is pressed, non empty parameters of this profile will get the initial settings" ,
				WRITE_ENABLED);
		addElement(ACTION_DEFAULT_UPDATE_FILE,
				"When a Game is started or if the key \"U\" is pressed, non empty parameters of this profile will get the default settings" ,
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
