package rotp.ui.util.cfg;

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
import java.util.LinkedHashSet;
import java.util.List;
import rotp.Rotp;
import rotp.model.game.IGameOptions;

public class UserSettings extends BaseSetting {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    private String filePath = Rotp.jarPath();
    private String fileName = "PresetsTest.cfg";
    // private String selectedEnableGlobal = "SAVE";
    // private String selectedConfigAction = "SAVE";
    private LinkedHashSet<String> defaultUserSettingKeys =
        new LinkedHashSet<String>(List.of("User", "Last", "Cryslonoid"));
    private IGameOptions gameOptions;
    private String       currentSettingKey;
    private BaseSetting  currentSetting;
    private Group_Base   currentGroup;

    private LinkedHashMap<String, Group_Base> keyGroupMap;
    private List<Group_Base> groupList;

    // ------------------------------------------------------------------------
    // Constructors
    //
	public UserSettings(IGameOptions options) {
        super(
            "PRESET ACTION",
            "SAVE",
            List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE", "LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT")
            );
        init(options);
    }
	// ========================================================================
	//  @Override Methods
	//
    @Override
    void initComments() {
		headComments(new Comments(List.of(
            "            EXTENDED PLAYER'S SETTINGS",
            "-------------------------------------------------- ",
            " ",
            "---- This is where you add your configuration list ",
            "---- Multiple LOAD will follow this sequence")));
		bottomComments(new Comments(
            "(---- The last loaded Win)"));
	}
    @Override
    void selectedGameOptions(IGameOptions gameOptions, String userOption) {
        // TODO Auto-generated method stub

    }
    @Override
    void setGameOptionsToDefault(IGameOptions gameOptions) {
        // TODO Auto-generated method stub

    }
    	// ========================================================================
	// Initializations Methods
	//
    private void initGroupList(IGameOptions options) {
        groupList = new ArrayList<Group_Base>();
        groupList.add(new Group_Race(options));
        // groupList.add(new Group_Galaxy(options)); // TODO
        // groupList.add(new Group_Advanced(options)); // TODO
        // groupList.add(new Group_Modnar(options)); // TODO
        // groupList.add(new Group_BrokenRegistry(options));  // TODO
        initkeyGroupMap();
    }
    private void initkeyGroupMap() {
        keyGroupMap = new LinkedHashMap<String, Group_Base>();
        for (Group_Base group : groupList) {
            for (String key : group.keyList()) {
                keyGroupMap.put(key, group);
            }
        }
    }
    private void init(IGameOptions options) {
		gameOptions = options;
        initGroupList(options);
		initComments();
        readUserConfig(options);
	}
    private void readUserConfig(IGameOptions options) {
		loadConfigurationFile();
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
        // Test for Emptyness and ignore
		if (line.isEmpty()) return;
		// test for comment and ignore
		if (Comments.isComment(line)) return;
		// Test for new setting
		KeyValuePair configLine = new KeyValuePair(line);
		if (configLine.getKey().isBlank()) return;
        // Test for New Setting Section
		if (configLine.isSectionKey() ) {
			currentSettingKey = configLine.getValue();
            currentGroup = keyGroupMap.get(currentSettingKey);
            currentSetting = null;
            if (currentGroup != null) {
                currentSetting = currentGroup.getSetting(currentSettingKey);
            }
            return;
		}
        // it's a setting Line
		if (currentSetting != null) {
			currentSetting.addUserSetting(configLine);
        }
	}
    private int saveConfigFile() {
        LinkedHashSet<String> settingKeys = getUserSettingKeys();
        if (settingKeys == null || settingKeys.isEmpty()) {
            settingKeys = defaultUserSettingKeys;
        }
		try (FileOutputStream fout = new FileOutputStream(new File(filePath, fileName));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
	        for (Group_Base group : groupList) {
	    		out.println(group.toString(settingKeys));
			}
            return 0;
	    }
	    catch (IOException e) {
	        System.err.println("ConfigMap.save -- IOException: "+ e.toString());
	        return -1;
	    }
	}
    private void resetAllUserSettings() {
        for (Group_Base group : groupList) {
            group.resetAllUserSettings();
        }
    }

}
