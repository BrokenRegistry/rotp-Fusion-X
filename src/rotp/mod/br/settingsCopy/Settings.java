
/*
 * Copyright 2015-2020 Ray Fowler
 * 
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

package rotp.mod.br.settingsCopy;

import mod.br.settings.UserSettings;
import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

public class Settings {
	
	public static boolean ChangeGameFile = false;
	
	private static UserSettings userSettings = new UserSettings();
	
	/*
   	 * Load the configuration file and memorize first options
   	 */
	public static void initUserSettings(IGameOptions options) {
		userSettings.initUserSettings(options);
	}
	/*
   	 * Check if User Settings are already initialized with gameOptions
   	 */
	public static boolean isInitialized () {
		return userSettings.isInitialized();
	}
	/*
   	 * Check if' OK to use Spacing
   	 */
	public static boolean isSpacingEnabled() {
		// Here because this is the Mod that knows the other Mods
		return userSettings.isInitialized() &&
				userSettings.getSetting("MAXIMIZE EMPIRES SPACING").isSectionEnabled();
	}
	 /*
   	 * Load and execute the configuration file
   	 * only for the selected UI
   	 */
    public static void loadLocalGroupSettings(String group, IGameOptions options) {
    	userSettings.loadLocalGroupSettings(group, options);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public static void loadGlobalGroupSettings(IGameOptions options) {
    	userSettings.loadGlobalGroupSettings(options);
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public static void resetInitialOptions(IGameOptions options) {
    	userSettings.resetFirstOptions(options);
    }
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last GUI options values
   	 * Save the new configuration file
   	 */
    public static void saveGuiToFile(IGameOptions options) {
    	userSettings.saveGuiToFile(options);
	}
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last Loaded Game options values
   	 * Save the new configuration file
   	 */
    public static void saveGameOptionsToFile(IGameOptions options) {
    	userSettings.saveGameToFile(options);
	}
    /*
   	 * Load and execute the configuration file to Change the game file
   	 */
    public static void changeGameSettings(GameSession instance) {
    	userSettings.changeGameSettings(instance);
    }

}
