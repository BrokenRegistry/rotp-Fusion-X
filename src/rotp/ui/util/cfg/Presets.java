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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import rotp.model.game.MOO1GameOptions;



public class Presets extends Cfg {

	// protected static LinkedHashMap<String, Sections> settingsMap;
	// protected static LinkedHashSet<String> multipleUserOptionsSet;
	// protected static LinkedHashSet<String> singleUserOptionsSet  = 
	// 		new LinkedHashSet<String>(List.of(ENABLE_KEY));
	// protected static LinkedHashSet<String> selectedUserOptionsSet = 
	// 		new LinkedHashSet<String>(List.of("User", "Last", "Default", "Cryslonoid"));
	private static boolean selectedMaximizeEmpiresSpacing = true;
	private static Integer selectedNoPlanetPctMult        = 100;
	private static Integer selectedMinStarsPerEmpire      = 8;
	private static Integer selectedPrefStarsPerEmpire     = 16;
	private static MOO1GameOptions gameOptions;
	private static Presets presets;

	private void init() {
		fileName = "Presets.cfg";
		HEADER_COMMENT = new Comments(List.of(
			"        EXTENDED PLAYER'S PRESETS",
			"----------------------------------------- ",
			" "));
		ACTION_OPTIONS = 
			List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE",
			"LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT");
		selectedEnable       = "Both";
		selectedConfigAction = "LOAD AND SAVE";
		settingsMap = new LinkedHashMap<String, Sections>();
		loadSettingsMap();
		// Override with config file values
		setGameOptions();
	}
	// ========================================================================
	// Public Methods
	//
	public Presets() {}
	
	public static void load(MOO1GameOptions moo1GameOptions) {
		presets = new Presets();
		gameOptions = moo1GameOptions;
		presets.init();
	}
	public static void updateAndSavePresets()   {presets.updateAndSave();}
	public static int  minStarsPerEmpire()      {return selectedMinStarsPerEmpire;}
	public static int  preferedStarsPerEmpire() {return selectedPrefStarsPerEmpire;}
	public static int  noPlanetPctMult()        {return selectedNoPlanetPctMult;}
	public static boolean maximiseEmpireSpacing() {return selectedMaximizeEmpiresSpacing;}

	// ========================================================================
	// protected Methods
	//
	@Override
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
}