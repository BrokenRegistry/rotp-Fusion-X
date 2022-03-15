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
		selectedEnableGlobal = settingsMap.get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		if (ENABLE_LOAD.contains(selectedEnableGlobal)) {
			for (String userOption : selectedUserOptionsSet) {
				if (settingsMap.get(ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
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
