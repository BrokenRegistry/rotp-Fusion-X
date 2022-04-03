package rotp.mod.br.settings;

import mod.br.postSetup.UserPostSetup;
import rotp.model.game.IGameOptions;

public class PostSetup {
	private static UserPostSetup userPostSettings = new UserPostSetup();
	
	/*
   	 * Load the configuration file and memorize first options
   	 */
	public static void initUserSettings(IGameOptions options) {
		userPostSettings.initUserSettings(options);
	}
	/*
   	 * Check if User Settings are already initialized with gameOptions
   	 */
	public static boolean isInitialized () {
		return userPostSettings.isInitialized();
	}
	 /*
   	 * Load and execute the configuration file
   	 * only for the selected UI
   	 */
    public static void loadLocalGroupSettings(String group, IGameOptions options) {
    	userPostSettings.loadLocalGroupSettings(group, options);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public static void loadGlobalGroupSettings(IGameOptions options) {
    	userPostSettings.loadGlobalGroupSettings(options);
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public static void resetFirstOptions(IGameOptions options) {
    	userPostSettings.resetFirstOptions(options);
    }
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public static void saveToUserConfig(IGameOptions options) {
    	userPostSettings.saveToUserConfig(options);
	}
 
}
