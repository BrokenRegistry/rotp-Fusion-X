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

public class Presets extends Configs {

	IGameOptions gameOptions;
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
		//
		// Other Parameters
		//
		RaceCfg.loadGameOptions(this, u);
		GalaxyCfg.loadGameOptions(this, u);
		AdvancedCfg.loadGameOptions(this, u);
		GovernorCfg.loadGameOptions(this, u);
		ModnarCfg.loadGameOptions(this, u);
		ExtCfg.loadGameOptions(this, u);

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

		RaceCfg.initComments(this);
		GalaxyCfg.initComments(this);
		AdvancedCfg.initComments(this);
		GovernorCfg.initComments(this);
		ModnarCfg.initComments(this);
		ExtCfg.initComments(this);
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


					setting = "GALAXY SIZE";
					if (settingsMap.containsKey(setting)) {
						section = settingsMap.get(setting);
						if (section.isSectionReadable(userOption))
							gameOptions.selectedGalaxySize(section.getValidSetting(userOption));
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

					RaceCfg.overrideGameOptions(this, userOption);
					AdvancedCfg.overrideGameOptions(this, userOption);
					GovernorCfg.overrideGameOptions(this, userOption);
					ModnarCfg.overrideGameOptions(this, userOption);
					ExtCfg.overrideGameOptions(this, userOption);
					// to the end: NB Empire is depedent of MIN STARS PER EMPIRE
					GalaxyCfg.overrideGameOptions(this, userOption);

				} // \ if ACTION LOAD
			} // \options loop
		} // \if ENABLE_LOAD
	} // \overrideGameOptions
}
