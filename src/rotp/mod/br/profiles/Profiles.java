
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
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

package rotp.mod.br.profiles;

import mod.br.profileManager.ClientClasses;
import mod.br.profileManager.UserProfiles;
//import mod.br.settings.UserSettings;
import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

/**
 * @author BrokenRegistry
 * Access point for BrokenRegistry Mods
 *   - Profile Manager
 *   - Galaxy Options
 */

public class Profiles {
	
	/**
	 * Global Parameter to allow or block the edition of game files
	 * Pressing "X" to load the file make it <b>true</b> 
	 */
	public static boolean ChangeGameFile = false;
	
	private static UserProfiles userProfiles = new UserProfiles();
	
	/**
   	 * Load the configuration file and memorize first options
	 * @param options the {@code IGameOptions} containing the parameters
	 */
	public static void initUserSettings(IGameOptions options) {
		userProfiles.initAndLoadProfiles(new ClientClasses(options));
	}
	/**
   	 * Check if User Settings are already initialized with gameOptions
	 * @return <b>true</b> if already initialized
   	 */
	public static boolean isInitialized () {
		return userProfiles.isInitialized();
	}

	/**
  	 * Check if' OK to use Spacing
	 * @return status
	 */
	public static boolean isSpacingEnabled() {
		// Here because this is the Mod that knows the other Mods
		return userProfiles.isInitialized() &&
				userProfiles.getParameter("MAXIMIZE EMPIRES SPACING").isLoadEnabled();
	}

	/**
   	 * Load and execute the configuration file,
   	 * only for the selected GUI
	 * @param group 
	 * @param options 
   	 */
	public static void loadLocalGroupSettings(String group, IGameOptions options) {
		userProfiles.loadLocalGroupSettings(group, new ClientClasses(options));
	}

	/**
   	 * Load and execute the configuration file,
   	 * for All GUI
	 * @param options 
   	 */
	public static void loadGlobalGroupSettings(IGameOptions options) {
		userProfiles.loadGlobalGroupSettings(new ClientClasses(options));
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for all GUI
	 * @param options 
   	 */
	public static void resetGlobalInitialOptions(IGameOptions options) {
		userProfiles.resetGlobalInitialOptions(new ClientClasses(options));
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for the selected GUI
	 * @param group group name
	 * @param options 
   	 */
	public static void resetLocalInitialOptions(String group, IGameOptions options) {
		userProfiles.resetLocalInitialOptions(group, new ClientClasses(options));
	}

	/**
   	 * Load the configuration file to update the Action
   	 * Update with last GUI options values
   	 * Save the new configuration file
	 * @param options 
   	 */
	public static void saveGuiToFile(IGameOptions options) {
		userProfiles.saveGuiToFile(new ClientClasses(options));
	}

	/**
   	 * Load the configuration file to update the Action
   	 * Update with last Loaded Game options values
   	 * Save the new configuration file
	 * @param options 
   	 */
	public static void saveGameOptionsToFile(IGameOptions options) {
		userProfiles.saveGameToFile(new ClientClasses(options));
	}

	/**
   	 * Load and execute the configuration file to Change the game file
	 * @param instance 
   	 */
	public static void changeGameSettings(GameSession instance) {
		userProfiles.changeGameSettings(new ClientClasses(instance));
	}
}
