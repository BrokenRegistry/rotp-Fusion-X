package rotp.mod.br.settings;

import java.util.LinkedHashSet;

import br.config.AbstractGroup;
import mod.br.settings.UserSettings;
import rotp.model.game.IGameOptions;

public class Settings {
	
	/*
   	 * Load the configuration file and memorize first options
   	 */
	public static void initUserSettings(IGameOptions options) {
		UserSettings.initUserSettings(options);
	}
	/*
   	 * Check if User Settings are already initialized with gameOptions
   	 */
	public static boolean isInitialized () {
		return UserSettings.isInitialized();
	}
	/*
   	 * Check if' OK to use Spacing
   	 */
	public static boolean isSpacingEnabled() {
		// Here because this is the Mod that knows the other Mods
		return UserSettings.isInitialized() &&
				UserSettings.getSetting("MAXIMIZE EMPIRES SPACING").isSectionEnabled();
	}
	 /*
   	 * Load and execute the configuration file
   	 * only for the selected UI
   	 */
    public static void loadLocalGroupSettings(String group, IGameOptions options) {
    	UserSettings.loadLocalGroupSettings(group, options);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public static void loadGlobalGroupSettings(IGameOptions options) {
    	UserSettings.loadGlobalGroupSettings(options);
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public static void resetFirstOptions(IGameOptions options) {
    	UserSettings.resetFirstOptions(options);
    }
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public static void saveToUserConfig(IGameOptions options) {
    	UserSettings.saveToUserConfig(options);
	}
 
}
