
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
	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file (without User Action)
	 * @param options The class that manage GUI parameters
   	 */
	public static void saveLastGuiToFile(IGameOptions options) {
//		userProfiles.saveLastGuiToFile(new ClientClasses(options));
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
   	 * Load the configuration file to update the Action
   	 * Update with last Loaded Game options values
   	 * Save the new configuration file
	 * @param instance 
   	 */
	public static void saveGameOptionsToFile(GameSession instance) {
		userProfiles.saveGameToFile(new ClientClasses(instance));
	}

	/**
   	 * Load and execute the configuration file to Change the game file
	 * @param instance 
   	 */
	public static void changeGameSettings(GameSession instance) {
		userProfiles.changeGameSettings(new ClientClasses(instance));
	}

	/**
   	 * Load the configuration file to update the Action
   	 * Update with last Loaded Game options values
   	 * Save the new configuration file
	 * @param key the key to process
	 * @param global Global or Local ?
	 * @param group group name
	 * @param options class containing info
	 * @return <b>true</b> if something has been changed
   	 */
	public static boolean processKey(int key, boolean global,
			String group, IGameOptions options) {
		return userProfiles.processKey(key, global, group, new ClientClasses(options));
	}

	/**
  	 * Check if it is OK to use Spacing
	 * @return status
	 */
	public static boolean isSpacingEnabled() {
		// Here because this is the Mod that knows the other Mods
		return userProfiles.isParameterEnabled("MAXIMIZE EMPIRES SPACING");
//		return userProfiles.isInitialized() &&
//				userProfiles.getParameter("MAXIMIZE EMPIRES SPACING").isLoadEnabled();
	}

	/**
  	 * Check if it is OK to use NoPlanet
	 * @return status
	 */
	public static boolean isNoPlanetMultiplierEnabled() {
		// Here because this is the Mod that knows the other Mods
		return userProfiles.isParameterEnabled("NO PLANET MULTIPLIER");
	}
}
