
/*
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import rotp.mod.br.settings.Settings;

public abstract class Abstract_CfgFile <GuiObject, SaveObject> {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
	// TODO move to interface with local Enabled 
	protected static final String LOAD_ENABLED  = "ENABLE_LOAD_LIST";
	protected static final String WRITE_ENABLED = "ENABLE_WRITE_LIST";
	protected static final String GAME_ENABLED  = "ENABLE_GAME_LIST";
	protected static final String SPECIAL_ENABLED = "SPECIAL_LIST";
	private static final Cfg_Entry LOADABLE = new Cfg_Entry(LOAD_ENABLED,  false);
	private static final Cfg_Entry WRITABLE = new Cfg_Entry(WRITE_ENABLED, false);
	private static final Cfg_Entry GAMEABLE = new Cfg_Entry(GAME_ENABLED,  false);
    private static final String FILE_TO_GUI  = "GUI";
    private static final String FILE_TO_GAME = "GAME";
    private static final String GUI_TO_FILE  = "SAVE";
    private static final String GAME_TO_FILE = "PULL";
    private static final String INITIAL_TO_FILE  = "INITIAL";
    private static final String GUI_UPDATE_FILE  = "UPDATE";
    private static final String GAME_UPDATE_FILE = "PICK";
    private static final String INITIAL_UPDATE_FILE = "INIT";

	private static final
	Cfg_ValidationData LOCAL_LIST = initLocalList();

	protected static Cfg_ValidationData initLocalList() {
		Cfg_ValidationData list = new Cfg_ValidationData();	
		list.setCategoryContains(true);
		list.setOptionContains(true);
		list.setIsLabelCaseSensitive(false, false);
		list.setIsOptionCaseSensitive(false, false);
		list.setIsCategoryCaseSensitive(false, false);
		
		list.addElement(FILE_TO_GUI,
				"Load from file and change GUI" ,
				LOAD_ENABLED);
		list.addElement(FILE_TO_GAME, 
				"Load from file and change Game at \"X LOAD\"" ,
				GAME_ENABLED + " " + LOAD_ENABLED);
		list.addElement(GUI_TO_FILE, 
				"Get from GUI and Save to file" ,
				WRITE_ENABLED);
		list.addElement(GAME_TO_FILE,
				"Get from Game and Save to file" ,
				WRITE_ENABLED);
		list.addElement(INITIAL_TO_FILE,
				"Get initial value of GUI and Save to file" ,
				WRITE_ENABLED);
		list.addElement(GUI_UPDATE_FILE,
				"If actif Field: Get from GUI and Save to file" ,
				WRITE_ENABLED);
		list.addElement(GAME_UPDATE_FILE, 
				"If actif Field: Get from Game and Save to file" ,
				WRITE_ENABLED);
		list.addElement(INITIAL_UPDATE_FILE,
				"If actif Field: Get initial value of GUI and Save to file" ,
				WRITE_ENABLED);
//		list.addElement("CLEAR",
//				"Remove every occurrence of this setting" ,
//				SPECIAL_ENABLED);
		return list;
	}
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    private List<String> defaultUserSettingKeys = new ArrayList<String>(List.of("User", "Last", "Cryslonoid"));
    private boolean firstInit = true;

    private LinkedHashMap<String, Abstract_Setting<GuiObject, SaveObject>> keySettingMap;
    private LinkedHashMap<String, Abstract_Group<GuiObject, SaveObject>> keyGroupMap;
    protected LinkedHashMap<String, Abstract_Group<GuiObject, SaveObject>> groupMap;
    private Setting_USER_ACTION settingUserAction;
    private boolean cleanUserKeys = true;

    private Abstract_Setting<GuiObject, SaveObject> currentSetting;
    private String currentSettingKey;
    private Abstract_Group<GuiObject, SaveObject> currentGroup;
    
    // ------------------------------------------------------------------------
    // Constructors
    //
	public Abstract_CfgFile() {
    }
	// ========================================================================
	//  Abstract Methods
	//
    /*
	 * Add all the groups to the Map with an easy key
	 */
	protected abstract void initGroupMap(GuiObject guiObject);
	protected abstract String getFilePath();
	protected abstract String getFileName();
	protected abstract String getSettingUserActionName();
	protected abstract Cfg_ValidationData getSettingUserActionOptions();
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
	public Abstract_Group<GuiObject, SaveObject> getGroup (String group) {
		return keyGroupMap.get(group.toUpperCase());
	}
	public Abstract_Setting<GuiObject, SaveObject> getSetting (String setting) {
		return keySettingMap.get(setting.toUpperCase());
	}
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public void saveGuiToFile(GuiObject guiObject) {
    	loadConfigurationFile(); // in case the user changed load or save actions
        // Remove the Local Enable parameter where possibly wrongly added 
        	settingUserAction.removeLocalEnable();
    	updateGuiValue(guiObject);
    	doUserUpdateActions();
        saveConfigFile();
	}
    /*
   	 * Load the configuration file to update the Action
   	 * Update with last options values
   	 * Save the new configuration file
   	 */
    public void saveGameToFile(GuiObject guiObject) {
    	loadConfigurationFile(); // in case the user changed load or save actions
        // Remove the Local Enable parameter where possibly wrongly added 
        	settingUserAction.removeLocalEnable();
    	updateGameValue(guiObject);
    	doUserUpdateActions();
        saveConfigFile();
	}
    /*
   	 * Load the configuration file and memorize first options
   	 */
    public void initUserSettings(GuiObject guiObject) {
    	if (firstInit) {
	        settingUserAction = new Setting_USER_ACTION(getSettingUserActionName()
	        		, getSettingUserActionOptions(), getSettingUserActionFirst());
	        initGroupMap(guiObject);
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
    public void loadLocalGroupSettings(String group, GuiObject guiObject) {
    	loadConfigurationFile();
    	List<String> settingKeys = getReadableUserLabelList();
    	currentGroup = groupMap.get(group.toUpperCase());
    	currentGroup.overrideGuiParameters(guiObject, settingKeys);
    }
    /*
   	 * Load and execute the configuration file
   	 */
    public void loadGlobalGroupSettings(GuiObject guiObject) {
    	loadConfigurationFile();
    	List<String> settingKeys = getReadableUserLabelList();
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		group.overrideGuiParameters(guiObject, settingKeys);
		}
    }
    public void changeGameSettings(SaveObject saveObject) {
    	loadConfigurationFile();
    	List<String> settingKeys = getGameableUserLabelList();
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		group.changeGameFileParameters(saveObject, settingKeys);
		}
    	Settings.ChangeGameFile = false;
    }
    /*
   	 * Reset the game options as they where at the beginning
   	 */
    public void resetFirstOptions(GuiObject guiObject) {
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		group.setGuiParametersToInitial(guiObject);
		}
    }
    // ========================================================================
	// Initializations Methods
	//
	/*
	 * Key Map the group list 
	 */
    private void initKeyGroupMap() {
        keyGroupMap = new LinkedHashMap<String, Abstract_Group<GuiObject, SaveObject>>();
        for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
            for (String key : group.keyList()) {
                keyGroupMap.put(key, group);
            }
        }
    }
    /*
	 * Add all the Settings to the Map with an easy key
	 */
    private void initKeySettingMap() {
    	keySettingMap = new LinkedHashMap<String, Abstract_Setting<GuiObject, SaveObject>>();
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		for (String settingKey : group.keyList()) {
    			keySettingMap.put(settingKey, group.getSetting(settingKey));
    		}
    	}
    }
    // ========================================================================
	// Other Methods
	//
    private List<String> getUserLabelList() {
		return settingUserAction.getLabelList();
	}
    private List<String> getReadableUserLabelList() {
		return settingUserAction.getLabelListIfCategoryContainsFilter(LOADABLE);
	}
    private List<String> getWritableUserLabelList() {
		return settingUserAction.getLabelListIfCategoryContainsFilter(WRITABLE);
	}
    private List<String> getGameableUserLabelList() {
		return settingUserAction.getLabelListIfCategoryContainsFilter(GAMEABLE);
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
		Cfg_LineValidation configLine = new Cfg_LineValidation(line);
		if (configLine.label().isBlank()) return;
        // Test for New Setting Section
		if (configLine.label().equals(Abstract_Setting.LABEL_OF_SECTION_KEY) ) {
			currentSettingKey = configLine.rawValue().toTest();
			if (settingUserAction.settingID().equals(currentSettingKey)) {
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
			currentSetting.put(line);
        }
	}
    private int saveConfigFile() {
    	// Remove the Local Enable parameter where possibly wrongly added 
    	settingUserAction.removeLocalEnable();
    	
        List<String> settingKeys = getUserLabelList();
        if (settingKeys == null || settingKeys.isEmpty()) {
            settingKeys = defaultUserSettingKeys;
        }
		try (FileOutputStream fout = new FileOutputStream(new File(getFilePath(), getFileName()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
			// SETTING :PRESET ACTIONS
            out.print(settingUserAction.toPrint(settingKeys));
            // Loop thru settings
	        for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
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
        for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
            group.resetAllUserSettings();
        }
    }
    void doUserUpdateActions() {
    	// Loop Thru User's Keys and perform requested action
    	List<String> keySet = getUserLabelList();
		for (String userKey : keySet) {
			userKey = userKey.toUpperCase();
			String action = settingUserAction.getStringOption(userKey).toUpperCase();
			if (action.contains(GUI_TO_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionGuiToFile(userKey);
				}
			}
			if (action.contains(GAME_TO_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionGameToFile(userKey);
				}
			}
			if (action.contains(INITIAL_TO_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionInitialToFile(userKey);
				}
			}
			if (action.contains(GUI_UPDATE_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionGuiUpdateFile(userKey);
				}
			}
			if (action.contains(GAME_UPDATE_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionGameUpdateFile(userKey);
				}
			}
			if (action.contains(INITIAL_UPDATE_FILE)) {
				for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
		    		group.actionInitialUpdateFile(userKey);
				}
			}
		}
		saveConfigFile();
	}
    void updateGuiValue(GuiObject guiObject) {
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		group.actionGetGuiValue(guiObject);
		}
    }
    void updateGameValue(GuiObject guiObject) {
    	for (Abstract_Group<GuiObject, SaveObject> group : groupMap.values()) {
    		group.actionGetGameValue(guiObject);
		}
    }
    // ========================================================================
    // Nested Classes
    //
    protected class Setting_USER_ACTION extends Abstract_Setting<GuiObject, SaveObject> {
    	// The Class GuiObject is not used, but needed for compatibility

    	// ------------------------------------------------------------------------
        // Constructors
        //
    	protected Setting_USER_ACTION() { super(
			"USER CONFIG ACTION",
			LOCAL_LIST
			);
    	}
    	protected Setting_USER_ACTION(String settingID
    				, Cfg_ValidationData settingOptions
    				, String initialOption) {
    		super(settingID, settingOptions, initialOption);
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
	@Override public String getFromGame(GuiObject gameObject) { return "-"; }
	@Override public void   putToGame(SaveObject saveObject, String userOption) {}
   	@Override public String getFromUI(GuiObject gO) { return "-"; }
  	@Override public void   putToGUI(GuiObject gO, String userOption) {}
  	@Override public void   putInitialToGUI(GuiObject gO) {}
 }

}
