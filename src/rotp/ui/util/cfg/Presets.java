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
import rotp.model.game.MOO1GameOptions;

public class Presets extends Configs {

	IGameOptions gameOptions;

	// ------------------------------------------------------------------------
    // Constructors
    //
	public Presets() {}
	// ========================================================================
	// Initializations Methods
	//
	Presets readUserConfig(IGameOptions options) {
		fileName = "Presets.cfg";
		HEADER_COMMENT = new Comments(List.of(
			"        EXTENDED PLAYER'S POSTSETS",
			"----------------------------------------- ",
			" "));
		ACTION_OPTIONS =
			List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE",
			"LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT");
		selectedEnableGlobal = "SAVE";
		selectedConfigAction = "SAVE";

		gameOptions = options;
		settingsMap = new LinkedHashMap<String, Sections>();
		// Load configuration file
		loadSettingsMap();
		// Update user presets key list
		if (settingsMap.containsKey(ACTION_KEY))
			selectedUserOptionsSet = settingsMap.get(ACTION_KEY).getGroupKeySet();
		// Update Enable Setting
		selectedEnableGlobal = settingsMap.get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		return this;
	}
	// ========================================================================
	// Public Methods
	//
	public void loadUserConfig(IGameOptions gameOptions) {
		readUserConfig(gameOptions);
		if (ENABLE_LOAD.contains(selectedEnableGlobal)) overrideGameOptions(false);
	}
	public void reloadDefaultConfig(IGameOptions gameOptions) {
		readUserConfig(gameOptions);
		overrideGameOptions(true); // resetToDefault = true
	}
	public void saveToUserConfig(IGameOptions options) {
		readUserConfig(gameOptions);
		updateAndSave();
	}
	// ========================================================================
	// Overrrided abstract Methods
	//
	@Override
	void loadGameOptions(boolean u) {
		initDV(u, ENABLE_KEY, selectedEnableGlobal, ENABLE_OPTIONS);
		settingsMap.get(ENABLE_KEY).removeLocalEnable();
		initDV(u, ACTION_KEY, selectedConfigAction, ACTION_OPTIONS);
		settingsMap.get(ACTION_KEY).removeLocalEnable();

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
	@Override
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
	@Override
	void overrideGameOptions (boolean resetToDefault) {
		RaceCfg.overrideGameOptions(this, resetToDefault);     // Associated GUI: SetupRaceUI.java
		AdvancedCfg.overrideGameOptions(this, resetToDefault); // Associated GUI: StartOptionsUI.java
		GovernorCfg.overrideGameOptions(this, resetToDefault); // Associated GUI: .java
		ModnarCfg.overrideGameOptions(this, resetToDefault);   // Associated GUI: StartModOptionsUI.java
		ExtCfg.overrideGameOptions(this, resetToDefault);      // Associated GUI: NONE
		// to the end: NB Empire is depedent of MIN STARS PER EMPIRE
		GalaxyCfg.overrideGameOptions(this, resetToDefault);   // Associated GUI: SetupGalaxyUI.java
		// RemnantCfg                                          // Associated GUI: GameSettingsUI.java
		// LaunchCfg                                           // Associated GUI: GameUI.java
		// LoadFileCfg                                         // Associated GUI: LoadGameUI.java
	}

}
