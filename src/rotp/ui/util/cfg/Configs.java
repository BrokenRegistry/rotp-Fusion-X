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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import rotp.Rotp;
import rotp.ui.UserPreferences;
import br.config.comment.Comment;
import br.config.UserChoice;
import br.config.Sections;

abstract class Configs {

	// Key-Values Constants
	static final String BASE_KEY_FORMAT     = "%-20s";
	static final String KEY_VALUE_SEPARATOR = ":";
	static final String VALUE_SPACER        = " ";
	static final String RANDOM_ID           = "RANDOM";
	static final String KEY_VALUE_SEPARATOR_KEY_SPACER = KEY_VALUE_SEPARATOR + VALUE_SPACER;
	static final String KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_KEY_SPACER;
	// Other Constants
	static final String ENABLE_KEY = "GLOBAL ENABLE";
	static final String ACTION_KEY = "CONFIG ACTION";
//	static final List<String> BOOLEAN_LIST   = List.of("YES", "NO", "TRUE", "FALSE");
	static final List<String> ENABLE_OPTIONS = List.of("NO", "SAVE", "LOAD", "BOTH");
	static final List<String> ENABLE_LOAD    = List.of("LOAD", "BOTH");
	static final List<String> ENABLE_WRITE   = List.of("SAVE", "BOTH");
	private static final LinkedHashMap<String, String> defaultValuesMap = new LinkedHashMap<String, String>();
	// Static Variables List<String> settingOptions
	private static boolean resetToDefault = false;
	private static boolean forceRandom   = false;
	// Other Variables
	List<String> ACTION_OPTIONS;
	LinkedHashMap<String, Sections> settingsMap;
	LinkedHashSet<String> multipleUserOptionsSet;
	LinkedHashSet<String> singleUserOptionsSet = new LinkedHashSet<String>(List.of(ENABLE_KEY));
	LinkedHashSet<String> selectedUserOptionsSet = new LinkedHashSet<String>(List.of("User", "Last", "Cryslonoid"));
	String filePath = Rotp.jarPath();
	String fileName;
	String currentSetting = "";
	String selectedEnableGlobal = "SAVE";
	String selectedConfigAction;
	Comment HEADER_COMMENT;
	Comment FOOTER_COMMENT;
	// ========================================================================
	// Abstract Methods
	//
	abstract void initComments();
	abstract void overrideGameOptions();
	abstract void loadGameOptions(boolean u);
	// ========================================================================
	// Other Methods
	//
	public static LinkedHashMap<String, String> defaultValuesMap() { return defaultValuesMap;}
	void    setResetToDefault (boolean b) { resetToDefault = b; }
	boolean resetToDefault () { return resetToDefault; }
	void    setFirstLoad (boolean b) { UserPreferences.firstLoad(b); }
	public static boolean firstLoad() { 
		return UserPreferences.firstLoad();
	}
	void    setForceRandom (boolean b) { forceRandom = b; }
	boolean forceRandom () { return forceRandom; }
	LinkedHashMap<String, Sections> settingsMap () { return settingsMap; }
	void updateAndSave() {
		loadGameOptions(true); // To update config Last value
		for (String userOption : selectedUserOptionsSet) {
			userOption = userOption.toUpperCase();
			String action = settingsMap.get(ACTION_KEY).getUserChoice(userOption).getAsKey();
			if (action.contains("SAVE")) {
				for(String setting : multipleUserOptionsSet) {
				if (setting == ACTION_KEY) continue;
					settingsMap.get(setting).actionSave(userOption);
				}
			}
			if (action.contains("UPDATE")) {
				for(String setting : multipleUserOptionsSet) {
					if (setting == ACTION_KEY) continue;
					settingsMap.get(setting).actionUpdate(userOption);
				}
			}
			if (action.contains("DEFAULT")) {
				for(String setting : multipleUserOptionsSet) {
					if (setting == ACTION_KEY) continue;
					settingsMap.get(setting).actionDefault(userOption);
				}
			}
		}
		saveConfigFile();
	}
	int saveConfigFile() {
		try (FileOutputStream fout = new FileOutputStream(new File(filePath, fileName));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); )
		{
	    	if (HEADER_COMMENT != null && !HEADER_COMMENT.isEmpty()) { out.println(HEADER_COMMENT.toString()); }
	        for (String setting : singleUserOptionsSet) {
	    		out.println(settingsMap.get(setting).toString(new LinkedHashSet<String>(List.of(setting))));
			}
			settingsMap.get(ACTION_KEY).setLastValue("-");
			for (String setting : multipleUserOptionsSet) {
				out.println(settingsMap.get(setting).toString(selectedUserOptionsSet));
			}
	       	if (FOOTER_COMMENT != null && !FOOTER_COMMENT.isEmpty()) { out.println(FOOTER_COMMENT.toString()); }
			return 0;
	    }
	    catch (IOException e) {
	        System.err.println("ConfigMap.save -- IOException: "+ e.toString());
	        return -1;
	    }
	}
	void loadSettingsMap() {
		settingsMap = new LinkedHashMap<String, Sections>();
		// Init local default value
		loadGameOptions(false); // To set default value
		// Load the config file
		loadConfigFile();
		initComments();
	}
	/**
	 * Load the configuration file and process its contents
	 */
	void loadConfigFile () {
        File configFile = new File(filePath, fileName);
        if ( configFile.exists() ) {
        	try ( BufferedReader in = new BufferedReader(
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
	/**
	 * Process the content of the current Line
	 */
	void loadLine(String line) {
		// test for emptiness
		if ( line.isEmpty() ) return;
		// test for comment
		if ( Comment.isComment(line) ) return;
		// test for setting
		UserChoice configLine = new UserChoice(line);
		if ( configLine.getKey().isBlank() ) return;
		add(configLine);
	}
	/**
	 * Add User Choice from configuration line
	 */
	void add(UserChoice configLine) {
		if ( configLine.isSectionKey() ) {
			currentSetting = configLine.getValueAsKey();
			return;
		}
		if ( settingsMap.containsKey(currentSetting) ) {
			settingsMap.get(currentSetting).setUserChoice(configLine);
		}
	}
	/**
	 * Set String Setting
	 */
	void setSetting(String key, String value, List<String> options) {
		if(settingsMap.containsKey(key)) {
			// update setting
			settingsMap.get(key).setLastValue(value);
			return;
		}
		settingsMap.put(key, new Sections(key, value, options));
	}
	/**
	 * Set boolean Setting
	 */
	void setSetting(String key, boolean value) {
		if(settingsMap.containsKey(key)) {
			// update setting
			settingsMap.get(key).setLastValue(value);
			return;
		}
		settingsMap.put(key, new Sections(key, value));
	}
	/**
	 * Set integer Setting
	 */
	void setSetting(String key, int value, int min, int max, int minR, int maxR) {
		if(settingsMap.containsKey(key)) {
			// update setting
			settingsMap.get(key).setLastValue(value);
			return;
		}
		settingsMap.put(key, new Sections(key, value, min, max, minR, maxR));
	}
}
