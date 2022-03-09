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
// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
// import java.util.Set;

import rotp.Rotp;
import rotp.model.game.MOO1GameOptions;



public class Postsets extends Cfg {

	protected static final String ENABLE_KEY = "ENABLE";
	protected static final String ACTION_KEY = "CONFIG ACTION";
	// protected static final List<String> BOOLEAN_LIST   = List.of("YES", "NO", "TRUE", "FALSE");
	// protected static final List<String> YES_LIST       = List.of("YES", "TRUE");
	// protected static final List<String> NO_LIST        = List.of("NO", "FALSE");
	protected static final List<String> ENABLE_OPTIONS = List.of("NO", "SAVE", "LOAD", "BOTH");
	protected static final List<String> ACTION_OPTIONS = 
			List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE", "LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT");
	protected static final List<String> ENABLE_LOAD    = List.of("LOAD", "BOTH");
	protected static final List<String> ENABLE_SAVE    = List.of("SAVE", "BOTH");
	protected static final String NEW_START_FILE_PATH = Rotp.jarPath();
	protected static final Comments HEADER_COMMENT = new Comments(List.of(
			"        EXTENDED PLAYER'S PRESETS",
			"----------------------------------------- ",
			" ")
			);
	protected static final Comments FOOTER_COMMENT = null;
	protected static final List<String> EMPIRE_COLORS = 
			List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
					"brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");
	protected static String newStartFileName = "Presets.cfg";
	// protected static String currentSetting = "";

	protected static LinkedHashMap<String, Sections> settingsMap;
	protected static LinkedHashSet<String> multipleUserOptionsSet;
	protected static LinkedHashSet<String> singleUserOptionsSet  = 
			new LinkedHashSet<String>(List.of(ENABLE_KEY));
	protected static LinkedHashSet<String> selectedUserOptionsSet = 
			new LinkedHashSet<String>(List.of("User", "Last", "Default", "Cryslonoid"));
	protected static String  selectedEnable                 = "Both";
	protected static String  selectedConfigAction           = "LOAD AND SAVE";
	protected static boolean selectedMaximizeEmpiresSpacing = true;
	protected static Integer selectedNoPlanetPctMult        = 100;
	protected static Integer selectedMinStarsPerEmpire      = 8;
	protected static Integer selectedPrefStarsPerEmpire     = 16;
	protected static MOO1GameOptions gameOptions;
	protected static Postsets presets;

	// ========================================================================
	// Public Methods
	//
	public Postsets() {}

	public static void load(MOO1GameOptions moo1GameOptions) {
		presets = new Postsets();
		settingsMap = new LinkedHashMap<String, Sections>();
		gameOptions = moo1GameOptions;
		presets.loadMap(); 
		// Override with config file values
		presets.setGameOptions();
	}
	public static void updateAndSavePresets()   {presets.updateAndSave();}
	public static int  minStarsPerEmpire()      {return selectedMinStarsPerEmpire;}
	public static int  preferedStarsPerEmpire() {return selectedPrefStarsPerEmpire;}
	public static int  noPlanetPctMult()        {return selectedNoPlanetPctMult;}
	public static boolean maximiseEmpireSpacing() {return selectedMaximizeEmpiresSpacing;}

	// ========================================================================
	// protected Methods
	//
	protected void loadGameOptions(boolean u) {
		initDV(u, ENABLE_KEY, selectedEnable, ENABLE_OPTIONS);
//		settingsMap.get(ENABLE_KEY).cfgOptions(List.of(ENABLE_KEY));
		initDV(u, ACTION_KEY, selectedConfigAction, ACTION_OPTIONS);
//		initDV(u, "TRANSPORT POPULATION",     "10",   List.of("1", "100"));
//		initDV(u, "TRANSPORT MAX PERCENT",    "10",   List.of("1", "50"));
//		initDV(u, "TRANSPORT MAX TURNS",      "5",    List.of("1", "1000000"));
//		initDV(u, "TRANSPORT RICH DISABLED",  "YES",  BOOLEAN_LIST);
//		initDV(u, "TRANSPORT POOR DOUBLE",    "YES",  BOOLEAN_LIST);
//		initDV(u, "MINIMUM MISSILE BASES",    "0",    List.of("0", "1000000"));
//		initDV(u, "AUTOSPEND",                "YES",  BOOLEAN_LIST);
//		initDV(u, "RESERVE",                  "1000", List.of("0", "1000000"));
//		initDV(u, "SHIP BUILDING",            "YES",  BOOLEAN_LIST);
//		initDV(u, "AUTO SHIPS BY DEFAULT",    "YES",  BOOLEAN_LIST);
//		initDV(u, "AUTO SCOUT",               "YES",  BOOLEAN_LIST);
//		initDV(u, "AUTO ATTACK",              "NO",   BOOLEAN_LIST);
//		initDV(u, "AUTO SCOUT SHIP COUNT",    "1",    List.of("0", "1000000"));
//		initDV(u, "AUTO COLONY SHIP COUNT",   "1",    List.of("0", "1000000"));
//		initDV(u, "AUTO ATTACK SHIP COUNT",   "1",    List.of("0", "1000000"));
		initDV(u, "GALAXY SIZE",    gameOptions.selectedGalaxySize(),          gameOptions.galaxySizeOptions());
		initDV(u, "GALAXY SHAPE",   gameOptions.selectedGalaxyShape(),	       gameOptions.galaxyShapeOptions());
		initDV(u, "GALAXY AGE",     gameOptions.selectedGalaxyAge(),           gameOptions.galaxyAgeOptions());
		initDV(u, "DIFFICULTY",     gameOptions.selectedGameDifficulty(),      gameOptions.gameDifficultyOptions());
		initDV(u, "RESEARCH",       gameOptions.selectedResearchRate(),        gameOptions.researchRateOptions());
		initDV(u, "TECH TRADING",   gameOptions.selectedTechTradeOption(),     gameOptions.techTradingOptions());
		initDV(u, "RANDOM EVENTS",  gameOptions.selectedRandomEventOption(),   gameOptions.randomEventOptions());
		initDV(u, "WARP SPEED",     gameOptions.selectedWarpSpeedOption(),     gameOptions.warpSpeedOptions());
		initDV(u, "NEBULAE",        gameOptions.selectedNebulaeOption(),       gameOptions.nebulaeOptions());
		initDV(u, "COUNCIL",        gameOptions.selectedCouncilWinOption(),    gameOptions.councilWinOptions());
		initDV(u, "STAR DENSITY",   gameOptions.selectedStarDensityOption(),   gameOptions.starDensityOptions());
		initDV(u, "PLANET QUALITY", gameOptions.selectedPlanetQualityOption(), gameOptions.planetQualityOptions());
		initDV(u, "TERRAFORMING",   gameOptions.selectedTerraformingOption(),  gameOptions.terraformingOptions());
		initDV(u, "COLONIZING",     gameOptions.selectedColonizingOption(),    gameOptions.colonizingOptions());
		initDV(u, "FUEL RANGE",     gameOptions.selectedFuelRangeOption(),     gameOptions.fuelRangeOptions());
		initDV(u, "RANDOMIZE AI",   gameOptions.selectedRandomizeAIOption(),   gameOptions.randomizeAIOptions());
		initDV(u, "AI HOSTILITY",   gameOptions.selectedAIHostilityOption(),   gameOptions.aiHostilityOptions());
		initDV(u, "OPPONENT AI",    gameOptions.selectedOpponentAIOption(),    gameOptions.opponentAIOptions());
		initDV(u, "AUTOPLAY",       gameOptions.selectedAutoplayOption(),      gameOptions.autoplayOptions());
		initDV(u, "NB OPPONENTS",   gameOptions.selectedNumberOpponents(),  0, gameOptions.maximumOpponentsOptions());
		initDV(u, "PLAYER RACE",    gameOptions.selectedPlayerRace(),          gameOptions.startingRaceOptions());
		initDV(u, "PLAYER COLOR",   EMPIRE_COLORS.get(gameOptions.selectedPlayerColor()), EMPIRE_COLORS);
		initDV(u, "MAXIMIZE EMPIRES SPACING", selectedMaximizeEmpiresSpacing);
		initDV(u, "MIN STARS PER EMPIRE",     selectedMinStarsPerEmpire,    0, 1000000);
		initDV(u, "PREF STARS PER EMPIRE",    selectedPrefStarsPerEmpire,   0, 1000000);
		initDV(u, "NO PLANET PCTS MULT",      selectedNoPlanetPctMult,      0, 1000000);
		// Build setting list excluding single config list
		multipleUserOptionsSet = new LinkedHashSet<String>();
		for (String setting : settingsMap.keySet()) {
			if ( !singleUserOptionsSet.contains(setting) ) {
				multipleUserOptionsSet.add(setting);
			}
		}
	}

	protected void initComments() {	
		settingsMap.get(ENABLE_KEY).headComments(new Comments("---- MOD activation"));
		settingsMap.get(ACTION_KEY).headComments(new Comments(
				List.of("---- This is where you add your configuration list ",
						"---- Multiple LOAD will follow this sequence")));
		settingsMap.get(ACTION_KEY).bottomComments(new Comments("(---- The last loaded Win)"));
//		settingsMap.get("TRANSPORT POPULATION").headComments(new Comments("------------- Governor Options"));
		settingsMap.get("MAXIMIZE EMPIRES SPACING").headComments(new Comments("------------ Galaxy Options"));
		settingsMap.get("MAXIMIZE EMPIRES SPACING").optionsComments(new Comments("Empires may want space to breath"));
		settingsMap.get("PREF STARS PER EMPIRE").optionsComments(new Comments("Determine default opponents number"));
		settingsMap.get("GALAXY SIZE").headComments(new Comments(List.of("------------ Standard Options", " ")));
	}

	protected void setGameOptions () {
		// Update user config key list
		if (settingsMap.containsKey(ACTION_KEY)) {
			selectedUserOptionsSet = settingsMap.get(ACTION_KEY).getGroupKeySet();
		}
		// Update Enable Setting
		String setting;
		Sections section;
		selectedEnable = settingsMap.get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		if (ENABLE_LOAD.contains(selectedEnable)) {
			for (String userOption : selectedUserOptionsSet) {
				if (settingsMap.get(ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
					setting = "PLAYER RACE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedPlayerRace(section.getValidSetting(userOption)); 
					}
					setting = "PLAYER COLOR";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(section.getValidSetting(userOption)));
					}
					setting = "GALAXY SIZE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedGalaxySize(section.getValidSetting(userOption));
					}
					setting = "GALAXY SHAPE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedGalaxyShape(section.getValidSetting(userOption));
					}
					setting = "GALAXY AGE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedGalaxyAge(section.getValidSetting(userOption));
					}
					setting = "DIFFICULTY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedGameDifficulty(section.getValidSetting(userOption));
					}
					setting = "RESEARCH";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedResearchRate(section.getValidSetting(userOption));
					}
					setting = "TECH TRADING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedTechTradeOption(section.getValidSetting(userOption));
					}
					setting = "RANDOM EVENTS";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedRandomEventOption(section.getValidSetting(userOption));
					}
					setting = "WARP SPEED";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedWarpSpeedOption(section.getValidSetting(userOption));
					}
					setting = "NEBULAE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedNebulaeOption(section.getValidSetting(userOption));
					}
					setting = "COUNCIL";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedCouncilWinOption(section.getValidSetting(userOption));
					}
					setting = "STAR DENSITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedStarDensityOption(section.getValidSetting(userOption));
					}
					setting = "PLANET QUALITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedPlanetQualityOption(section.getValidSetting(userOption));
					}
					setting = "TERRAFORMING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedTerraformingOption(section.getValidSetting(userOption));
					}
					setting = "COLONIZING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedColonizingOption(section.getValidSetting(userOption));
					}
					if (settingsMap.containsKey(setting)) {
						String value = settingsMap.get(setting).getValidSetting(userOption);
						if (!value.isBlank()) { gameOptions.selectedColonizingOption(value); }
					}
					setting = "FUEL RANGE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedFuelRangeOption(section.getValidSetting(userOption));
					}
					setting = "RANDOMIZE AI";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedRandomizeAIOption(section.getValidSetting(userOption));
					}
					setting = "AI HOSTILITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedAIHostilityOption(section.getValidSetting(userOption));
					}
					setting = "OPPONENT AI";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedOpponentAIOption(section.getValidSetting(userOption));
					}
					setting = "AUTOPLAY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedAutoplayOption(section.getValidSetting(userOption));
					}
					setting = "NB OPPONENTS";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							gameOptions.selectedNumberOpponents(section.getIntegerSetting(userOption));
					}
					setting = "MAXIMIZE EMPIRES SPACING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							selectedMaximizeEmpiresSpacing = section.getBooleanSetting(userOption);
					}
					setting = "NO PLANET PCTS MULT";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							selectedNoPlanetPctMult = section.getIntegerSetting(userOption);
					}
					setting = "MIN STARS PER EMPIRE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							selectedMinStarsPerEmpire = section.getIntegerSetting(userOption);
					}
					setting = "PREF STARS PER EMPIRE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.hasValidSetting(userOption))
							selectedPrefStarsPerEmpire = section.getIntegerSetting(userOption);
					}
				} // \ if ACTION LOAD
			} // \options loop
		} // \if ENABLE_LOAD
	} // \setGameOptions
	
	protected void updateAndSave() {
		selectedEnable = settingsMap.get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		if (ENABLE_SAVE.contains(selectedEnable)) {
			loadGameOptions(true); // To update config Last value
			for (String userOption : selectedUserOptionsSet) {
				userOption = userOption.toUpperCase();
				String action = settingsMap.get(ACTION_KEY).getPairValue(userOption).toUpperCase();
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
	}
	protected int saveConfigFile() {
	       try (FileOutputStream fout = new FileOutputStream(new File(NEW_START_FILE_PATH, newStartFileName));
	        		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {

	    	   if ( HEADER_COMMENT != null && !HEADER_COMMENT.isEmpty() ) { out.println(HEADER_COMMENT.toString()); }
	    	   for ( String setting : singleUserOptionsSet ) {
	    			out.println(settingsMap.get(setting).toString(new LinkedHashSet<String>(List.of(setting))));
				}
				settingsMap.get(ACTION_KEY).setLastValue("-");
				for ( String setting : multipleUserOptionsSet ) {
					out.println(settingsMap.get(setting).toString(selectedUserOptionsSet));
				}
	        	if ( FOOTER_COMMENT != null && !FOOTER_COMMENT.isEmpty() ) { out.println(FOOTER_COMMENT.toString()); }
				return 0;
	        }
	        catch (IOException e) {
	            System.err.println("ConfigMap.save -- IOException: "+ e.toString());
	            return -1;
	        }
	}
	protected void loadMap() {
		// Init local default value
		loadGameOptions(false); // To set default value
		// Load the config file
		loadConfigFile();
		initComments();
	}
	protected void loadConfigFile () {
        File configFile = new File(NEW_START_FILE_PATH, newStartFileName);
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
        		System.err.println(NEW_START_FILE_PATH + newStartFileName + " not found.");
        	}
        	catch (IOException e) {
        		System.err.println("UserPreferences.load -- IOException: "+ e.toString());
        	}
        }
        else {
        	saveConfigFile();
        }
    }
// 	protected void loadLine(String line) {
// 		// test for emptiness
// 		if ( line.isEmpty() ) return;
// 		// test for comment
// 		if ( Comments.isComment(line) ) return;
// 		// test for setting
// 		KeyValuePair configLine = new KeyValuePair(line);
// 		if ( configLine.getKey().isBlank() ) return;
// 		add(configLine);	        
// 	  }
// 	protected void add(KeyValuePair configLine) {
// 		if ( configLine.isSectionKey() ) {
// 			currentSetting = configLine.getValue();
// 			return;
// 		}
// 		if ( settingsMap.containsKey(currentSetting) ) {
// 			settingsMap.get(currentSetting).setKeyValuePair(configLine);
// 		}
// 	}
// 	protected void initDV(boolean update, String key, String value, List<String> options) {
// 		if ( settingsMap.containsKey(key) ) {
// 			if (update) {
// 				settingsMap.get(key).setLastValue(value);
// 				return;
// 			}
// 			settingsMap.get(key).initSection(key, value, options);
// 			return;
// 		}
// 		settingsMap.put(key, new Sections(key, value, options));
// 	}
// 	protected void initDV(boolean update, String key, boolean value) {
// 		if ( settingsMap.containsKey(key) ) {
// 			if (update) {
// 				settingsMap.get(key).setLastValue(value);
// 				return;
// 			}
// 			settingsMap.get(key).initSection(key, value);
// 			return;
// 		}
// 		settingsMap.put(key, new Sections(key, value));
// 	}
// 	protected void initDV(boolean update, String key, Integer value, Integer min, Integer max) {
// 		if ( settingsMap.containsKey(key) ) {
// 			if (update) {
// 				settingsMap.get(key).setLastValue(value);
// 				return;
// 			}
// 			settingsMap.get(key).initSection(key, value, min, max);
// 			return;
// 		}
// 		settingsMap.put(key, new Sections(key, value, min, max));
// 	}
	
// 	protected static String yesOrNo(boolean b) { return b ? "YES" : "NO"; }
//     protected static boolean yesOrNo(String s) { return YES_LIST.contains(s.toUpperCase()); }
//     protected static boolean yesOrNo(String s, boolean onWrong) {
//     	if (s != null) {
//     		String S = s.toUpperCase();
//         	if ( YES_LIST.contains(S) ) return true;
//         	if ( NO_LIST.contains(S) )  return false;   		
//     	}
//     	return onWrong;
//     }
// 	protected static boolean isNumeric(String str){
//         return str != null && str.matches("[0-9.]+");
//     }
// 	protected static Integer getInteger(String str, Integer onWrong){
// 		if (isNumeric(str)) return Integer.valueOf(str);
//         return onWrong;
//     }
//  // ============================================================================
//  // Nested Classes
//  // 
//  // ============================================================================
//  // Sections
//  //
//     static class Sections {
    	
//     	protected static final String LABEL_OF_SECTION_KEY = KeyValuePair.LABEL_OF_SECTION_KEY;
//     	protected static final String HEAD_OF_OPTIONS      = "# OPTIONS";
//     	protected static final String HEAD_OF_DEFAULT      = "# DEFAULT";
//     	protected static final String HEAD_OF_LAST         = "# LAST";

//     	protected Comments     headComments;
//     	protected KeyValuePair settingKey   = new KeyValuePair(LABEL_OF_SECTION_KEY, null);
//     	protected KeyValuePair optionsList  = new KeyValuePair(HEAD_OF_OPTIONS, null);
//     	protected KeyValuePair defaultValue = new KeyValuePair(HEAD_OF_DEFAULT, null);
//     	protected KeyValuePair lastValue    = new KeyValuePair(HEAD_OF_LAST, null);
//     	protected Comments     settingComments;
//     	protected List<String> settingOptions;
//     	protected Comments     optionsComments;
//     	protected LinkedHashMap<String, KeyValuePair> settingMap;
//     	protected Comments     bottomComments;
//     	protected LinkedHashMap<String, String> labelOptionsMap;
//     	protected Integer      minValue;
//     	protected Integer      maxValue;
//     	protected boolean      isInteger = false;
//     	protected boolean      isBoolean = false;
//     	protected KeyValuePair currentSetting;
    	
//     	// ------------------------------------------------------------------------
//     	// Constructors
//     	//
//     	protected Sections(String key, String defaultValue, List<String> settingOptions) { // String Value
//     		initSection(key, defaultValue, settingOptions);
//     		settingMap = new LinkedHashMap<String, KeyValuePair>();
//     	}
//     	protected Sections(String key, boolean defaultValue) { // Boolean Value
//     		initSection(key, defaultValue);
//     		settingMap = new LinkedHashMap<String, KeyValuePair>();
//     		isBoolean  = true;
//     	}
//     	protected Sections(String key, Integer defaultValue, Integer min, Integer max) { // Integer Value
//     		initSection(key, defaultValue, min, max);
//     		settingMap = new LinkedHashMap<String, KeyValuePair>();
//     		isInteger  = true;
//     	}
    	
//     	// ------------------------------------------------------------------------
//     	// Initializers
//     	//
//     	protected void initSection(String key, String value, List<String> settingOptions) { // String Value
//     		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
//     		defaultValue.setValue(settingNameToLabel(value));
//     		setSettingOptions(settingOptions);
//     	}
//     	protected void initSection(String key, boolean value) { // Boolean Value
//     		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
//     		defaultValue.setValue(yesOrNo(value));
//     		setSettingOptions(BOOLEAN_LIST);
//     	}
//     	protected void initSection(String key, Integer value, Integer min, Integer max) { // Integer Value
//     		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
//     		defaultValue.setValue(value.toString());
//     		setSettingOptions(min, max);
//     	}
    	
//     	protected boolean hasValidSetting (String key) {
//     		if (key != null) {
//     			String Key = key.toUpperCase();
//     			if (settingMap.containsKey(Key)) {
//     				currentSetting = settingMap.get(Key);
//     				if (currentSetting.isBlank()) return false;
//     				if (isBoolean) return currentSetting.isValid(BOOLEAN_LIST);
//     				if (isInteger) return currentSetting.isValid(minValue, maxValue);
//     				return currentSetting.isValid(labelOptionsMap.keySet());

//     			}
//     		}
//     		return false; 
//     	}
//     	// ------------------------------------------------------------------------
//     	// Getters and Setters
//     	//
//     	protected String getDefaultValue() {
//     		return defaultValue.getValue();
//     	}
//     	protected String getLastValue() {
//     		return lastValue.getValue();
//     	}
//     	protected LinkedHashSet<String> getGroupKeySet () {
//     		return new LinkedHashSet<String>(settingMap.keySet());
//     	}
//     	protected String getPairValue(String key) {
//     		if (key != null && settingMap.containsKey(key)) {
//     			return settingMap.get(key).getValue();
//     		}
//     		return defaultValue.getValue();
//     	}
//     	protected boolean getBooleanSetting(String key) {
//     		boolean preset = defaultValue.getBooleanValue();
//     		if (key != null && isBoolean) {
//     			String Key = key.toUpperCase();
//     			if (settingMap.containsKey(Key)) return settingMap.get(Key).getValue(preset);
//     		}
//     		return preset;
//     	}
//     	protected Integer getIntegerSetting(String key) {
//     		Integer preset = defaultValue.getIntegerValue();
//     		if (key != null && isInteger) {
//     			String Key = key.toUpperCase();
//     			if (settingMap.containsKey(Key)) return settingMap.get(Key).getValue(preset);
//     		}
//     		return preset;
//     	}
//     	protected String getValidSetting(String key) {
//     		if (key != null) {
//     			String value = getValidValue(key);
//     			if (!value.isBlank()) {
//     				return labelOptionsMap.get(value.toUpperCase());
//     			}
//     		}
//     		return ""; 
//     	}
//     	protected String getValidNonBlankSetting(String key) {
//     		String value = defaultValue.getValue();
//     		if (key != null) {
//     			value = getValidNonBlankValue(key);
//     		}
//     		return labelOptionsMap.get(value.toUpperCase()); 
//     	}
//     	protected String getValidNonBlankValue(String key) {
//     		if (key != null) {
//     			String Key = key.toUpperCase();
//     			if (settingMap.containsKey(Key)) {
//     				String value = settingMap.get(Key).getValue();
//     				if (labelOptionsMap.keySet().contains(value.toUpperCase())) {
//     					return value;
//     				}
//     			}
//     		}
//     		return defaultValue.getValue(); 
//     	}
//     	protected String getValidValue(String key) {
//     		if (key != null) {
//     			String Key = key.toUpperCase();
//     			if (settingMap.containsKey(Key)) {
//     				String value = settingMap.get(Key).getValue();
//     				if (value.isBlank() || labelOptionsMap.keySet().contains(value.toUpperCase())) {
//     					return value;
//     				}
//     			}
//     		}
//     		return defaultValue.getValue(); 
//     	}
//     	protected void setKeyValuePair (KeyValuePair pair) { setKeyValuePair (pair.getKey(), pair.getValue()); }
//     	protected void setKeyValuePair (String key, String value) {
//     		if (key != null && value != null) {
//     			if (value.isBlank() || labelOptionsMap.keySet().contains(value.toUpperCase())) {
//     				settingMap.put(key.toUpperCase(), new KeyValuePair(key, settingNameToLabel(value)) );
//     				}
//     				else {
//     					settingMap.put(key.toUpperCase(), new KeyValuePair(key, getDefaultValue()) );			
//     				}			
//     		}
//     	}
//     	protected void setLastValue(String value) { lastValue.setValue(settingNameToLabel(value)); }
//     	protected void setLastValue(boolean value) { lastValue.setValue(yesOrNo(value)); }
//     	protected void setLastValue(Integer value) { lastValue.setValue(value.toString()); }
//     	protected void setSettingOptions(Integer min, Integer max) {
//     		minValue = min;
//     		maxValue = max;
//     		setSettingOptions(List.of(min.toString(), max.toString()));
//     	}
//     	protected void setSettingOptions(List<String> options) {
//     		settingOptions  = new ArrayList<String>();
//     		labelOptionsMap = new LinkedHashMap<String, String>();
//     		for (String option : options) {
//     			settingOptions.add(option);
//     			labelOptionsMap.put(settingNameToLabel(option).toUpperCase(), option);
//     		}
//     		optionsList.setValue(settingNameToLabel(settingOptions).toString());
//     	}

//     	// ------------------------------------------------------------------------
//     	// Other Methods
//     	//
//     	protected String toString(LinkedHashSet<String> groupOptions) {
//     		String out = "";
//         	if (headComments != null    && !headComments.isEmpty())          { out += (headComments.toString()    + System.lineSeparator()); }
//         	if (settingKey   != null    && !settingKey.getKey().isEmpty())   { out += (settingKey.toString()      + System.lineSeparator()); }
//         	if (settingComments != null && !settingComments.isEmpty())       { out += (settingComments.toString() + System.lineSeparator()); }
//         	if (optionsList  != null    && !optionsList.getKey().isEmpty())  { out += (optionsList.toString()     + System.lineSeparator()); }    	
//         	if (defaultValue != null    && !defaultValue.getKey().isEmpty()) { out += (defaultValue.toString()    + System.lineSeparator()); }
//         	if (lastValue    != null    && !lastValue.getKey().isEmpty())    { out += (lastValue.toString()       + System.lineSeparator()); }
//         	if (optionsComments != null && !optionsComments.isEmpty())       { out += (optionsComments.toString() + System.lineSeparator()); }
//         	for (String option : groupOptions) {
//         		if (!settingMap.containsKey(option.toUpperCase())) {
//         			settingMap.put(option.toUpperCase(), new KeyValuePair(option, getDefaultValue()));
//         		}
//         		 out += (settingMap.get(option.toUpperCase()).toString() + System.lineSeparator());
//         	}
//         	if (bottomComments != null && !bottomComments.isEmpty()) { out += (bottomComments.toString() + System.lineSeparator()); }
//         	return out;
//     	}
//     	protected void actionSave(String key) { setKeyValuePair(key, getLastValue()); }
//     	protected void actionUpdate(String key) {
//     		if (!settingMap.containsKey(key)) {
//     			setKeyValuePair(key, getLastValue());
//     			return;
//     		}
//     		if (!settingMap.get(key).getValue().isBlank()) {
//     			settingMap.get(key).setValue(getLastValue());
//     		}
//     	}
//     	protected void actionDefault(String key) {
//     		if (!settingMap.containsKey(key)) {
//     			setKeyValuePair(key, getDefaultValue());
//     			return;
//     		}
//     		if (!settingMap.get(key).getValue().isBlank()) {
//     			settingMap.get(key).setValue(getDefaultValue());
//     		}
//     	}
//     	protected void headComments(Comments comments)    { headComments = comments; }
//     	protected void settingComments(Comments comments) { settingComments = comments; }
//     	protected void optionsComments(Comments comments) { optionsComments = comments; }
//     	protected void bottomComments(Comments comments)  { bottomComments = comments; }
//     	protected static String settingNameToLabel (String option) {
//     		return capitalize(option.substring(option.lastIndexOf("_") + 1));
//     	}
//     	protected static List<String> settingNameToLabel (List<String> options) {
//     		List<String> labels = new ArrayList<>();
//     		for (String option : options) {
//     			labels.add(settingNameToLabel (option));
//     		}
//     		return labels;
//     	}
//     	protected static String capitalize(String s) {
//         	if ( s.isEmpty() ) { return s; }
//         	if ( s.length() == 1 ) { return s.toUpperCase(); }
//     		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
//         }
//     }
//  // ============================================================================
//  // KeyValuePair
//  //
//     static class KeyValuePair {

//     	protected static final String KEY_VALUE_SEPARATOR = ":";	
//     	protected static final String VALUE_SPACER = " ";
//     	protected static final String KEY_VALUE_SEPARATOR_KEY_SPACER = KEY_VALUE_SEPARATOR + VALUE_SPACER;
//     	protected static final String BASE_KEY_FORMAT = "%-20s";
//     	protected static final String KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_KEY_SPACER;
//     	protected static final String LABEL_OF_SECTION_KEY = "¦ SETTING";
//     	protected static final Integer DEFAULT_VALUE = 0;
//     	protected String key = "";
//     	protected String value = "";
    	
//     	protected KeyValuePair() {}
//     	protected KeyValuePair(String key, String value) {
//     		if (key == null) { key = "Error! key is null"; } 
//     		if (value == null) { value = ""; } 
//     		this.key = key;
//     		this.value = value;
//     	}
//     	// Constructor for text line entry
//     	protected KeyValuePair(String line) {
//     		key = "";
//     		value = "";
//     		if (line == null || line.isBlank()) {return;}
//     		List<String> list = Arrays.asList(line.split(KEY_VALUE_SEPARATOR));
//     		key = list.get(0).trim();
//     		if (list.size() > 1) {
//     			value = String.join(KEY_VALUE_SEPARATOR, list.subList(1, list.size())).trim();              
//     		}
//     	}
//     	protected void setValue(String value) { this.value = value; }

//     	protected boolean isBlank()         { return value.isBlank();}
//     	protected boolean isValid(Integer min, Integer max) {
//     		Integer val = getInteger(value, min - 1);
//     		return (val >= min && val <= max);
//     	}
//     	protected boolean isValid(List<String> list) { return list.contains(value.toUpperCase()); }
//     	protected boolean isValid(Set<String> set)   { return set.contains(value.toUpperCase()); }
//     	protected boolean isSectionKey()             { return key.equals(LABEL_OF_SECTION_KEY); }
//     	protected boolean getBooleanValue()          { return yesOrNo(value); }
//     	protected Integer getIntegerValue()          { return getInteger(value, DEFAULT_VALUE); }
//     	protected Integer getValue(Integer onWrong)  { return getInteger(value, onWrong); }
//     	protected boolean getValue(boolean onWrong)  { return yesOrNo(value, onWrong); }
//     	protected String  getValue() { return value; }
//     	protected String  getKey()   { return key; }
//     	public  String  toString() { return String.format(KEY_FORMAT, key) + value; }
//     }

//  // ============================================================================
//  // Comments
//  //
//     static class Comments {
    	
//     	public  static final String COMMENT_KEY = "#";
//     	protected static final String COMMENT_SPACER = " ";
//     	protected static final String COMMENT_KEY_SPACER = COMMENT_KEY + COMMENT_SPACER;

//     	protected List<String> comments;

//     	// ------------------------------------------------------------------------
//     	// Constructors
//     	//	
//     	public Comments(String comment) {
//     		comments = new ArrayList<String>();
//     		addLine(comment);
//     	}
//     	public Comments(List<String> comments) {
//     		this.comments = comments;
//     	}
    	
//     	// ------------------------------------------------------------------------
//     	// Getters and Setters
//     	//
//     	public void set(String comment) {
//     		comments = new ArrayList<String>();
//     		addLine(comment);
//     	}
//     	public void set(List<String> comments) {
//     		this.comments = comments;
//     	}

//     	// ------------------------------------------------------------------------
//     	// Other Public Methods
//     	//
//     	public boolean isEmpty() {
//     		return comments.isEmpty();
//     	}
    	
//     	public String toString() {
//     		if (comments == null || comments.isEmpty()) { return COMMENT_KEY_SPACER; } 
//     		List<String> list = new ArrayList<String>();
//     		for (String comment : comments) {
//     			list.add(COMMENT_KEY_SPACER + comment);
//     		}
//     		return String.join(System.lineSeparator(), list);
//     	}

//     	public static boolean isComment(String s) {
//     		if (s == null || s.isEmpty()) { return false; }
//     		boolean result = s.trim().startsWith(COMMENT_KEY);
//     		return result;
//     	}
    	
//     	// ------------------------------------------------------------------------
//     	// Other protected Methods
//     	//
    	
//     	protected void addLine(String comments) {
//     		this.comments.add(comments);
//     	}

//     }

}