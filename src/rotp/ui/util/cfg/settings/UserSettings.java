package rotp.ui.util.cfg.settings;

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
import br.config.AbstractGroup;
import br.config.AbstractSetting;
import br.config.CfgLine;
import br.config.comment.Comment;
import rotp.Rotp;
import rotp.model.game.IGameOptions;

public class UserSettings {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    private static final String filePath = Rotp.jarPath();
    private static final String fileName = "PresetsTest.cfg";
    private static final LinkedHashSet<String> defaultUserSettingKeys =
    				new LinkedHashSet<String>(List.of("User", "Last", "Cryslonoid"));
    private static boolean firstInit = true;

    private static LinkedHashMap<String, AbstractGroup<IGameOptions>> keyGroupMap;
    private static LinkedHashMap<String, AbstractGroup<IGameOptions>> groupMap;
    private static Iterable<AbstractGroup<IGameOptions>> groupList;
    private static Setting_PRESET_ACTION settingPresetAction;
    private static boolean cleanUserKeys = false;

    private AbstractSetting<IGameOptions> currentSetting;
    private String currentSettingKey;
    private AbstractGroup<IGameOptions> currentGroup;
    
    // ------------------------------------------------------------------------
    // Constructors
    //
	public UserSettings() {
    }
	// ========================================================================
	//  Public Methods
	//
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public void saveToUserConfig(IGameOptions options) {
    	loadConfigurationFile(); // in case the user changed load or save actions
        // Remove the Local Enable parameter where possibly wrongly added 
        	settingPresetAction.removeLocalEnable();
    	updateLastValue(options);
    	doUserUpdateActions();
        saveConfigFile();
	}
    /*
   	 * Load the configuration file and memorize first options
   	 */
    public void init(IGameOptions options) {
    	if (firstInit) {
	        settingPresetAction = new Setting_PRESET_ACTION(options);
//	        initGroupList(options);
	        initGroupMap(options);
	        firstInit = false;
    	}
        loadConfigurationFile();
	}
    /*
   	 * Load and execute the configuration file
   	 * only for the selected UI
   	 */
    public void loadLocalGroupSettings(String group, IGameOptions options) {
    	loadConfigurationFile();
    	LinkedHashSet<String> settingKeys = getReadableUserKeySet();
    	currentGroup = groupMap.get(group.toUpperCase());
    	currentGroup.overrideGameParameters(options, settingKeys);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public void loadGlobalGroupSettings(IGameOptions options) {
    	loadConfigurationFile();
    	LinkedHashSet<String> settingKeys = getReadableUserKeySet();
    	for (AbstractGroup<IGameOptions> group : groupList) {
    		group.overrideGameParameters(options, settingKeys);
		}
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public void resetFirstOptions(IGameOptions options) {
    	for (AbstractGroup<IGameOptions> group : groupList) {
    		group.setGameParametersToFirst(options);
		}
    }
    // ========================================================================
	// Initializations Methods
	//
    /*
	 * Add all the groups to the Map with an easy key
	 */
    private void initGroupMap(IGameOptions options) {
        groupMap = new LinkedHashMap<String, AbstractGroup<IGameOptions>>();
        groupMap.put("RACE",     new Group_Race(options));
        groupMap.put("GALAXY",   new Group_Galaxy(options));
        groupMap.put("ADVANCED", new Group_Advanced(options));
        groupMap.put("MODNAR",   new Group_Modnar(options));
        groupMap.put("BR",       new Group_BrokenRegistry(options));
        groupList = groupMap.values();
        initKeyGroupMap();
    }
	/*
	 * Key Map the group list 
	 */
    private void initKeyGroupMap() {
        keyGroupMap = new LinkedHashMap<String, AbstractGroup<IGameOptions>>();
        for (AbstractGroup<IGameOptions> group : groupList) {
            for (String key : group.keyList()) {
                keyGroupMap.put(key, group);
            }
        }
    }
    // ========================================================================
	// Other Methods
	//
    private LinkedHashSet<String> getUserKeySet() {
    	return settingPresetAction.getUserSettingKeySet();
    }
    private LinkedHashSet<String> getReadableUserKeySet() {
    	LinkedHashSet<String> keySet = new LinkedHashSet<String>();
    	for (CfgLine userCfg : settingPresetAction.getSettingMapIterable()) {
    		if (userCfg.value().isLoadEnabled()) {
    			keySet.add(userCfg.key().toKey());
    		}
    	}
    	return keySet;
    }
    private void loadConfigurationFile() {
        resetAllUserSettings();
        File configFile = new File(filePath, fileName);
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
        		System.err.println(filePath + fileName + " not found.");
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
			if (settingPresetAction.settingKey().keyTest(currentSettingKey)) {
				currentSetting = settingPresetAction;
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
    	settingPresetAction.removeLocalEnable();
    	
        LinkedHashSet<String> settingKeys = getUserKeySet();
        if (settingKeys == null || settingKeys.isEmpty()) {
            settingKeys = defaultUserSettingKeys;
        }
		try (FileOutputStream fout = new FileOutputStream(new File(filePath, fileName));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
			// SETTING :PRESET ACTIONS
            out.print(settingPresetAction.toPrint(settingKeys));
            // Loop thru settings
	        for (AbstractGroup<IGameOptions> group : groupList) {
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
        for (AbstractGroup<IGameOptions> group : groupList) {
            group.resetAllUserSettings();
        }
    }
    void doUserUpdateActions() {
    	// Loop Thru User's Keys and perform requested action
    	LinkedHashSet<String> keySet = settingPresetAction.getUserSettingKeySet();
		for (String userKey : keySet) {
			userKey = userKey.toUpperCase();
			String action = settingPresetAction.getCfgLine(userKey).value().toKey();
			if (action.contains("SAVE")) {
				for (AbstractGroup<IGameOptions> group : groupList) {
		    		group.actionSave(userKey);
				}
			}
			if (action.contains("UPDATE")) {
				for (AbstractGroup<IGameOptions> group : groupList) {
		    		group.actionUpdate(userKey);
				}
			}
			if (action.contains("DEFAULT")) {
				for (AbstractGroup<IGameOptions> group : groupList) {
		    		group.actionFirst(userKey);
				}
			}
		}
		saveConfigFile();
	}
    void updateLastValue(IGameOptions options) {
    	for (AbstractGroup<IGameOptions> group : groupList) {
    		group.actionGetLastValue(options);
		}
    }
}
