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

import rotp.model.game.IGameOptions;
import rotp.ui.UserPreferences;

public class Presets extends Configs {

	private static boolean selectedMaximizeEmpiresSpacing = true;
	private static Integer selectedMinStarsPerEmpire  = 8;
	private Integer selectedPrefStarsPerEmpire = 16;
	private Integer selectedNoPlanetPctMult    = 100;
	private IGameOptions gameOptions;
	private Presets presets;

	// ------------------------------------------------------------------------
    // Constructors
    //
	public Presets() {}
	private void init() {
		fileName = "Presets.cfg";
		HEADER_COMMENT = new Comments(List.of(
			"        EXTENDED PLAYER'S POSTSETS",
			"----------------------------------------- ",
			" "));
		ACTION_OPTIONS =
			List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE",
			"LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT");
		selectedEnableGlobal = "Both";
		selectedConfigAction = "SAVE";
		settingsMap = new LinkedHashMap<String, Sections>();
	}
	// ========================================================================
	// Public static Methods
	//
	public void loadUserConfig(IGameOptions moo1GameOptions) {
			presets = new Presets();
		presets.gameOptions = moo1GameOptions;
		presets.init();
		// Load configuration file
		presets.loadSettingsMap();
		// Override with current config values
		presets.overrideGameOptions();
	}
	public void saveToUserConfig(IGameOptions options) {
		presets = new Presets();
		presets.gameOptions = options;
		presets.init();
		// Load configuration file
		presets.loadSettingsMap();
		// Update and save to User config file
		presets.updateAndSave();
	}
	// ========================================================================
	// Overrrided abstract Methods
	//
	// @Override
	void loadGameOptions(boolean u) {
		initDV(u, ENABLE_KEY, selectedEnableGlobal, ENABLE_OPTIONS);
		settingsMap.get(ENABLE_KEY).removeLocalEnable();
		initDV(u, ACTION_KEY, selectedConfigAction, ACTION_OPTIONS);
		settingsMap.get(ACTION_KEY).removeLocalEnable();
		// --------------------------------------------------------------------
		// Base Parameters
		//
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
		initDV(u, "NB OPPONENTS",   gameOptions.selectedNumberOpponents(),  0, gameOptions.maximumOpponentsOptions(),
		                                                                    1, gameOptions.maximumOpponentsOptions());
		initDV(u, "PLAYER RACE",    gameOptions.selectedPlayerRace(),          gameOptions.startingRaceOptions());
		initDV(u, "PLAYER COLOR",   EMPIRE_COLORS.get(gameOptions.selectedPlayerColor()), EMPIRE_COLORS);
		// --------------------------------------------------------------------
		// Governor Parameters
		//
		initDV(u, "GOVERNOR ON BY DEFAULT",    UserPreferences.governorOnByDefault());
		initDV(u, "AUTOSPEND ON BY DEFAULT",   UserPreferences.governorAutoSpendByDefault());
		initDV(u, "DEFAULT MAX BASES",         UserPreferences.defaultMaxBases(), 0, 100000, 0, 100);
		initDV(u, "DIVERT EXCESS TO RESEARCH", UserPreferences.divertColonyExcessToResearch());
		// --------------------------------------------------------------------
		// Modnar Parameters
		//
		initDV(u, "CUSTOM DIFFICULTY",  UserPreferences.customDifficulty(), 20, 500, 20, 500);
		initDV(u, "DYNAMIC DIFFICULTY", UserPreferences.dynamicDifficulty());
		initDV(u, "ALWAYS STAR GATES",  UserPreferences.alwaysStarGates());
		initDV(u, "ALWAYS THORIUM",     UserPreferences.alwaysThorium());
		initDV(u, "CHALLENGE MODE",     UserPreferences.challengeMode());
		initDV(u, "RANDOM TECH START",  UserPreferences.randomTechStart());
		initDV(u, "BATTLE SCOUT",       UserPreferences.battleScout());
		initDV(u, "COMPANION WORLDS",   UserPreferences.companionWorlds(), 0, 4, 0, 4);
		// --------------------------------------------------------------------
		// BrokenRegistry Parameters
		//
		initDV(u, "MAXIMIZE EMPIRES SPACING", selectedMaximizeEmpiresSpacing);
		initDV(u, "MIN STARS PER EMPIRE",     selectedMinStarsPerEmpire,    0, 1000000, 4, 16);
		initDV(u, "PREF STARS PER EMPIRE",    selectedPrefStarsPerEmpire,   0, 1000000, 16, 24);
		initDV(u, "NO PLANET PCTS MULT",      selectedNoPlanetPctMult,      0, 1000000, 0, 200);
		// Build setting list excluding single config list
		multipleUserOptionsSet = new LinkedHashSet<String>();
		for (String setting : settingsMap.keySet()) {
			if ( !singleUserOptionsSet.contains(setting) ) {
				multipleUserOptionsSet.add(setting);
			}
		}
	}
	void initComments() {
		settingsMap.get(ENABLE_KEY).headComments(new Comments("---- MOD activation"));
		settingsMap.get(ACTION_KEY).headComments(new Comments(
				List.of("---- This is where you add your configuration list ",
						"---- Multiple LOAD will follow this sequence")));
		settingsMap.get(ACTION_KEY).bottomComments(
			new Comments("(---- The last loaded Win)"));
		settingsMap.get("MAXIMIZE EMPIRES SPACING").optionsComments(
			new Comments("Empires may want space to breath"));
		settingsMap.get("PREF STARS PER EMPIRE").optionsComments(
			new Comments("Determine default opponents number"));
		settingsMap.get("GALAXY SIZE").headComments(
			new Comments(List.of("------------- Standard Options -------------", " ")));
		settingsMap.get("GOVERNOR ON BY DEFAULT").headComments(
			new Comments(List.of("------------ Governor's Options ------------", "")));
		settingsMap.get("CUSTOM DIFFICULTY").headComments(
			new Comments(List.of("------------- Modnar's Options -------------", " ")));
		settingsMap.get("MAXIMIZE EMPIRES SPACING").headComments(
			new Comments(List.of("--------- BrokenRegistry's Options ---------", " ")));
	}
	void overrideGameOptions () {
		// Update user presets key list
		if (settingsMap.containsKey(ACTION_KEY)) {
			selectedUserOptionsSet = settingsMap.get(ACTION_KEY).getGroupKeySet();
		}
		// Update Enable Setting
		String setting;
		Sections section;
		selectedEnableGlobal = settingsMap.get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		if (ENABLE_LOAD.contains(selectedEnableGlobal)) {
			for (String userOption : selectedUserOptionsSet) {
				if (settingsMap.get(ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
					setting = "PLAYER RACE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedPlayerRace(section.getValidSetting(userOption));
					}
					setting = "PLAYER COLOR";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(section.getValidSetting(userOption)));
					}
					setting = "GALAXY SIZE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedGalaxySize(section.getValidSetting(userOption));
					}
					setting = "GALAXY SHAPE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedGalaxyShape(section.getValidSetting(userOption));
					}
					setting = "GALAXY AGE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedGalaxyAge(section.getValidSetting(userOption));
					}
					setting = "DIFFICULTY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedGameDifficulty(section.getValidSetting(userOption));
					}
					setting = "RESEARCH";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedResearchRate(section.getValidSetting(userOption));
					}
					setting = "TECH TRADING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedTechTradeOption(section.getValidSetting(userOption));
					}
					setting = "RANDOM EVENTS";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedRandomEventOption(section.getValidSetting(userOption));
					}
					setting = "WARP SPEED";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedWarpSpeedOption(section.getValidSetting(userOption));
					}
					setting = "NEBULAE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedNebulaeOption(section.getValidSetting(userOption));
					}
					setting = "COUNCIL";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedCouncilWinOption(section.getValidSetting(userOption));
					}
					setting = "STAR DENSITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedStarDensityOption(section.getValidSetting(userOption));
					}
					setting = "PLANET QUALITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedPlanetQualityOption(section.getValidSetting(userOption));
					}
					setting = "TERRAFORMING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedTerraformingOption(section.getValidSetting(userOption));
					}
					setting = "COLONIZING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedColonizingOption(section.getValidSetting(userOption));
					}
					if (settingsMap.containsKey(setting)) {
						String value = settingsMap.get(setting).getValidSetting(userOption);
						if (!value.isBlank()) { gameOptions.selectedColonizingOption(value); }
					}
					setting = "FUEL RANGE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedFuelRangeOption(section.getValidSetting(userOption));
					}
					setting = "RANDOMIZE AI";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedRandomizeAIOption(section.getValidSetting(userOption));
					}
					setting = "AI HOSTILITY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedAIHostilityOption(section.getValidSetting(userOption));
					}
					setting = "OPPONENT AI";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedOpponentAIOption(section.getValidSetting(userOption));
					}
					setting = "AUTOPLAY";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedAutoplayOption(section.getValidSetting(userOption));
					}
					// setting = "GOVERNOR ON BY DEFAULT";
					// if (settingsMap.containsKey(setting)) {
					// 	section = settingsMap.get(setting);
					// 	if (section.isSectionReadable(userOption))
					// 	UserPreferences.setGovernorOn(section.getBooleanSetting(userOption));
					// }

					setting = "MAXIMIZE EMPIRES SPACING";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							selectedMaximizeEmpiresSpacing = section.getBooleanSetting(userOption);
					}
					setting = "MIN STARS PER EMPIRE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							selectedMinStarsPerEmpire = section.getIntegerSetting(userOption);
					}
					setting = "PREF STARS PER EMPIRE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							selectedPrefStarsPerEmpire = section.getIntegerSetting(userOption);
					}
					setting = "NB OPPONENTS";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption)) {
							// the limits may have changed from previous settings
							int min = 0;
							int max = gameOptions.maximumOpponentsOptions();
							section.setSettingOptions(min, max, 1, max);
							gameOptions.selectedNumberOpponents(
								Math.min(max, section.getIntegerSetting(userOption)));
						}
					}
					setting = "NO PLANET PCTS MULT";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							selectedNoPlanetPctMult = section.getIntegerSetting(userOption);
					}

				} // \ if ACTION LOAD
			} // \options loop
		} // \if ENABLE_LOAD
	} // \overrideGameOptions
	// ------------------------------------------------------------------------
	// Other Methods
	//
	static boolean maximiseEmpireSpacing() {return selectedMaximizeEmpiresSpacing;}
	static int     minStarsPerEmpire()     {return selectedMinStarsPerEmpire;}
	int preferedStarsPerEmpire() {return selectedPrefStarsPerEmpire;}
	int noPlanetPctMult()        {return selectedNoPlanetPctMult;}

	// ------------------------------------------------------------------------
	// Nested Classes
	//
	// ------------------------------------------------------------------------
	// Spacing
	//
	public class Spacing {
		// Parameters
		private float minEmpireBuffer;
		private float maxMinEmpireBuffer;
		private float minOrionBuffer;
		// Getters and Setters
		public float getMinEmpireBuffer()    {return minEmpireBuffer;}
		public float getMaxMinEmpireBuffer() {return maxMinEmpireBuffer;}
		public float getMinOrionBuffer()     {return minOrionBuffer;}
		public boolean isEnabled()           {return true;}
		// Other Methods
		public void init(int maxStars, int numOpps, float sysBuffer) {
			int minStarsPerEmpire = Presets.minStarsPerEmpire();
			if (Presets.maximiseEmpireSpacing()) minStarsPerEmpire = maxStars/numOpps;
			float maxMinEmpireFactor = 15f; // To avoid problems with strange galaxy shapes
			                                // Maybe To-Do Make this a new setting
			float minEmpireFactor = (minStarsPerEmpire + 1) / 3; // 8 spe -> 3; 12 spe -> 4;
			if (minEmpireFactor >= (maxMinEmpireFactor - 2))
				minEmpireFactor = maxMinEmpireFactor - 2;
			minEmpireBuffer    = sysBuffer * minEmpireFactor;
			maxMinEmpireBuffer = sysBuffer * maxMinEmpireFactor;
			minOrionBuffer     = sysBuffer * minEmpireFactor + 1;
		}
	}

}
