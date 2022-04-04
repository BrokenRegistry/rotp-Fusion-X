package br.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import rotp.mod.br.settings.Settings;
import rotp.model.game.GameSession;

public abstract class AbstractCfgFile <T, U> {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    private LinkedHashSet<String> defaultUserSettingKeys = new LinkedHashSet<String>(List.of("User", "Last", "Cryslonoid"));
    private boolean firstInit = true;

    private LinkedHashMap<String, AbstractSetting<T, U>> keySettingMap;
    private LinkedHashMap<String, AbstractGroup<T, U>> keyGroupMap;
    protected LinkedHashMap<String, AbstractGroup<T, U>> groupMap;
    private Setting_USER_ACTION settingUserAction;
    private boolean cleanUserKeys = true;

    private AbstractSetting<T, U> currentSetting;
    private String currentSettingKey;
    private AbstractGroup<T, U> currentGroup;
    
    // ------------------------------------------------------------------------
    // Constructors
    //
	public AbstractCfgFile() {
    }
	// ========================================================================
	//  Abstract Methods
	//
    /*
	 * Add all the groups to the Map with an easy key
	 */
	protected abstract void initGroupMap(T tObject);
	protected abstract String getFilePath();
	protected abstract String getFileName();
	protected abstract String getSettingUserActionName();
	protected abstract List<String> getSettingUserActionOptions();
	protected abstract String getSettingUserActionFirst();
	// ========================================================================
	//  Public Methods
	//
	/*
   	 * Check if User Settings are already initialized with gameOptions
   	 */
	public boolean isInitialized () {
		return !firstInit;
	}
	public AbstractGroup<T, U> getGroup (String group) {
		return keyGroupMap.get(group.toUpperCase());
	}
	public AbstractSetting<T, U> getSetting (String setting) {
		return keySettingMap.get(setting.toUpperCase());
	}
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public void saveGuiToFile(T tObject) {
    	loadConfigurationFile(); // in case the user changed load or save actions
        // Remove the Local Enable parameter where possibly wrongly added 
        	settingUserAction.removeLocalEnable();
    	updateGuiValue(tObject);
    	doUserUpdateActions();
        saveConfigFile();
	}
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public void saveGameToFile(T tObject) {
    	loadConfigurationFile(); // in case the user changed load or save actions
        // Remove the Local Enable parameter where possibly wrongly added 
        	settingUserAction.removeLocalEnable();
    	updateGameValue(tObject);
    	doUserUpdateActions();
        saveConfigFile();
	}
    /*
   	 * Load the configuration file and memorize first options
   	 */
    public void initUserSettings(T tObject) {
    	if (firstInit) {
	        settingUserAction = new Setting_USER_ACTION(getSettingUserActionName()
	        		, getSettingUserActionOptions(), getSettingUserActionFirst());
	        initGroupMap(tObject);
	        initKeyGroupMap();
	        initKeySettingMap();
	        firstInit = false;
    	}
        loadConfigurationFile();
	}
    /*
   	 * Load and execute the configuration file
   	 * only for the selected UI
   	 */
    public void loadLocalGroupSettings(String group, T tObject) {
    	loadConfigurationFile();
    	LinkedHashSet<String> settingKeys = getReadableUserKeySet();
    	currentGroup = groupMap.get(group.toUpperCase());
    	currentGroup.overrideGuiParameters(tObject, settingKeys);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public void loadGlobalGroupSettings(T tObject) {
    	loadConfigurationFile();
    	LinkedHashSet<String> settingKeys = getReadableUserKeySet();
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		group.overrideGuiParameters(tObject, settingKeys);
		}
    }
    public void changeGameSettings(U uObject) {
    	loadConfigurationFile();
    	LinkedHashSet<String> settingKeys = getGameChangingUserKeySet();
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		group.changeGameFileParameters(uObject, settingKeys);
		}
    	Settings.ChangeGameFile = false;
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public void resetFirstOptions(T tObject) {
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		group.setGuiParametersToInitial(tObject);
		}
    }
    // ========================================================================
	// Initializations Methods
	//
	/*
	 * Key Map the group list 
	 */
    private void initKeyGroupMap() {
        keyGroupMap = new LinkedHashMap<String, AbstractGroup<T, U>>();
        for (AbstractGroup<T, U> group : groupMap.values()) {
            for (String key : group.keyList()) {
                keyGroupMap.put(key, group);
            }
        }
    }
    /*
	 * Add all the Settings to the Map with an easy key
	 */
    private void initKeySettingMap() {
    	keySettingMap = new LinkedHashMap<String, AbstractSetting<T, U>>();
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		for (String settingKey : group.keyList()) {
    			keySettingMap.put(settingKey, group.getSetting(settingKey));
    		}
    	}
    }
    // ========================================================================
	// Other Methods
	//
    private LinkedHashSet<String> getUserKeySet() {
    	return settingUserAction.getUserSettingKeySet();
    }
    private LinkedHashSet<String> getReadableUserKeySet() {
    	LinkedHashSet<String> keySet = new LinkedHashSet<String>();
    	for (CfgLine userCfg : settingUserAction.getSettingMapIterable()) {
    		if (userCfg.value().isLoadEnabled()) {
    			keySet.add(userCfg.key().toKey());
    		}
    	}
    	return keySet;
    }
    private LinkedHashSet<String> getGameChangingUserKeySet() {
    	LinkedHashSet<String> keySet = new LinkedHashSet<String>();
    	for (CfgLine userCfg : settingUserAction.getSettingMapIterable()) {
    		if (userCfg.value().isGameChangingEnabled()) {
    			keySet.add(userCfg.key().toKey());
    		}
    	}
    	return keySet;
    }
    
    private void loadConfigurationFile() {
        resetAllUserSettings();
        File configFile = new File(getFilePath(), getFileName());
        if ( configFile.exists() ) {
        	try ( BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(configFile), "UTF-8"));) {
        		String line;
        		if (in != null) {
        			while ((line = in.readLine()) != null) loadLine(line.trim());
        		}
        	}
        	catch (FileNotFoundException e) {
        		System.err.println(getFilePath() + getFileName() + " not found.");
        	}
        	catch (IOException e) {
        		System.err.println("UserPreferences.load -- IOException: "+ e.toString());
        	}
        }
        else {
        	saveConfigFile();
        }
    }
    private void loadLine(String line) {
        // Test for Emptiness and ignore
		if (line.isEmpty()) return;
		// test for comment and ignore
		if (Comment.isComment(line)) return;
		// Test for new setting
		CfgLine configLine = new CfgLine(line);
		if (configLine.key().isBlank()) return;
        // Test for New Setting Section
		if (configLine.key().isSectionKey() ) {
			currentSettingKey = configLine.value().toKey();
			if (settingUserAction.settingKey().keyTest(currentSettingKey)) {
				currentSetting = settingUserAction;
				currentGroup = null;
				return;
			}
            currentGroup = keyGroupMap.get(currentSettingKey);
            currentSetting = null;
            if (currentGroup != null) {
                currentSetting = currentGroup.getSetting(currentSettingKey);
                return;
            }
            return;
		}
        // it's a setting Line
		if (currentSetting != null) {
			currentSetting.putCfgLine(configLine);
        }
	}
    private int saveConfigFile() {
    	// Remove the Local Enable parameter where possibly wrongly added 
    	settingUserAction.removeLocalEnable();
    	
        LinkedHashSet<String> settingKeys = getUserKeySet();
        if (settingKeys == null || settingKeys.isEmpty()) {
            settingKeys = defaultUserSettingKeys;
        }
		try (FileOutputStream fout = new FileOutputStream(new File(getFilePath(), getFileName()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
			// SETTING :PRESET ACTIONS
            out.print(settingUserAction.toPrint(settingKeys));
            // Loop thru settings
	        for (AbstractGroup<T, U> group : groupMap.values()) {
	    		out.print(group.toPrint(settingKeys, cleanUserKeys));
			}
            return 0;
	    }
	    catch (IOException e) {
	        System.err.println("ConfigMap.save -- IOException: "+ e.toString());
	        return -1;
	    }
	}
    private void resetAllUserSettings() {
        for (AbstractGroup<T, U> group : groupMap.values()) {
            group.resetAllUserSettings();
        }
    }
    void doUserUpdateActions() {
    	// Loop Thru User's Keys and perform requested action
    	LinkedHashSet<String> keySet = settingUserAction.getUserSettingKeySet();
		for (String userKey : keySet) {
			userKey = userKey.toUpperCase();
			String action = settingUserAction.getCfgLine(userKey).value().toKey();
			if (action.contains("UI TO FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionGuiToFile(userKey);
				}
			}
			if (action.contains("GAME TO FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionGameToFile(userKey);
				}
			}
			if (action.contains("INITIAL TO FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionInitialToFile(userKey);
				}
			}
			if (action.contains("UI UPDATE FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionGuiUpdateFile(userKey);
				}
			}
			if (action.contains("GAME UPDATE FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionGameUpdateFile(userKey);
				}
			}
			if (action.contains("INITIAL UPDATE FILE")) {
				for (AbstractGroup<T, U> group : groupMap.values()) {
		    		group.actionInitialUpdateFile(userKey);
				}
			}
		}
		saveConfigFile();
	}
    void updateGuiValue(T tObject) {
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		group.actionGetGuiValue(tObject);
		}
    }
    void updateGameValue(T tObject) {
    	for (AbstractGroup<T, U> group : groupMap.values()) {
    		group.actionGetGameValue(tObject);
		}
    }
    // ========================================================================
    // Nested Classes
    //
    protected class Setting_USER_ACTION extends AbstractSetting<T, U> {
    	// The Class T is not used, but needed for compatibility

    	// ------------------------------------------------------------------------
        // Constructors
        //
    	protected Setting_USER_ACTION() { super(
			"USER CONFIG ACTION",
			List.of("File To UI", "File To Game", 
					"UI To File", "Game To File", "Initial To File",
					"UI Update File", "Game Update File", "Initial Update File"),
			"UI To File"
			);
    	}
    	protected Setting_USER_ACTION(String Name, List<String> options, String first) {
    		super(Name, options, first);
        }
    @Override public void initComments() {
    	headComments(new Comment(List.of(
            "            EXTENDED PLAYER'S SETTINGS",
            "-------------------------------------------------- ",
            " ",
            "---- This is where you add your configuration list ",
            "- File To UI          = Load from file and update GUI",
            "- File To Game        = Load from file and update Game at specific LOAD",
            "- UI To File          = Get from GUI and Save to file",
            "- Game To File        = Get from Game and Save to file",
            "- Initial To File     = Get initial value of GUI and Save to file",
            "- UI Update File      = Get from GUI and Save to file, if field is not Empty",
            "- Game Update File    = Get from GUI and Save to file, if field is not Empty",
            "- Initial Update File = Get initial value of GUI and Save to file, if field is not Empty",
            "---- Multiple LOAD will follow their sequence",
            "---- Multiple choices are allowed. ex Load save",
            " " 
            )));
    	bottomComments(new Comment(
    		"(---- The last loaded Win)" ));
      }
	@Override public String getFromGame(T gameObject) { return "-"; }
	@Override public void   putToGame(U uObject, String userOption) {}
   	@Override public String getFromUI(T gO) { return "-"; }
  	@Override public void   putToGUI(T gO, String userOption) {}
  	@Override public void   putInitialToGUI(T gO) {}
 }

}
