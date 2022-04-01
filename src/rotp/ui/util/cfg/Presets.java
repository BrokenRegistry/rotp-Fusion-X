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

import java.util.LinkedHashSet;
import java.util.List;
import rotp.model.game.IGameOptions;
import br.config.comment.Comment;

public class Presets extends Configs {

	IGameOptions gameOptions;

	// ------------------------------------------------------------------------
    // Constructors
    //
	public Presets() {}
	// ========================================================================
	// Initializations Methods
	//
	public void initDefaultValues(IGameOptions options) {
		if (firstLoad()) readUserConfig(options);
	}
	Presets initPresets(IGameOptions options) {
		fileName = "Presets.cfg";
		HEADER_COMMENT = new Comment(List.of(
			"        EXTENDED PLAYER'S POSTSETS",
			"----------------------------------------- ",
			" "));
		ACTION_OPTIONS =
			List.of("-", "LOAD", "SAVE", "UPDATE", "LOAD AND SAVE",
			"LOAD AND UPDATE", "SAVE DEFAULT", "UPDATE TO DEFAULT");
		selectedEnableGlobal = "SAVE";
		selectedConfigAction = "SAVE";
		gameOptions = options;
		return this;
	}
	public Presets readUserConfig(IGameOptions options) {
		initPresets(options);
		// Load configuration file
		loadSettingsMap();
		// Update user presets key list
		if (settingsMap().containsKey(ACTION_KEY))
			selectedUserOptionsSet = settingsMap().get(ACTION_KEY).getUserSettingKeySet();
		// Update Enable Setting
		selectedEnableGlobal = settingsMap().get(ENABLE_KEY).getValidNonBlankSetting(ENABLE_KEY);
		setFirstLoad(false);
		return this;
	}
	// ========================================================================
	// Public Methods
	//
	public void reloadDefaultConfig(IGameOptions gameOptions) {
		readUserConfig(gameOptions);
		setResetToDefault(true);
		overrideGameOptions();
		setResetToDefault(false);
	}
	public void saveToUserConfig(IGameOptions options) {
		readUserConfig(options);
		updateAndSave();
	}
	// ========================================================================
	// Overridden Abstract Methods
	//
	@Override
	void loadGameOptions(boolean u) {
		setSetting(ENABLE_KEY, selectedEnableGlobal, ENABLE_OPTIONS);
		settingsMap().get(ENABLE_KEY).removeLocalEnable();
		setSetting(ACTION_KEY, selectedConfigAction, ACTION_OPTIONS);
		settingsMap().get(ACTION_KEY).removeLocalEnable();

		new RaceCfg().loadGameOptions(this, u);
		new GalaxyCfg().loadGameOptions(this, u);
		new AdvancedCfg().loadGameOptions(this, u);
		new GovernorCfg().loadGameOptions(this, u);
		new ModnarCfg().loadGameOptions(this, u);
		new ExtCfg().loadGameOptions(this, u);

		// Build setting list excluding single config list
		multipleUserOptionsSet = new LinkedHashSet<String>();
		for (String setting : settingsMap().keySet()) {
			if ( !singleUserOptionsSet.contains(setting) ) {
				multipleUserOptionsSet.add(setting);
			}
		}
	}
	@Override
	void initComments() {
		settingsMap().get(ENABLE_KEY).headComments(new Comment("---- MOD activation"));
		settingsMap().get(ACTION_KEY).headComments(new Comment(
				List.of("---- This is where you add your configuration list ",
						"---- Multiple LOAD will follow this sequence")));
		settingsMap().get(ACTION_KEY).bottomComments(
			new Comment("(---- The last loaded Win)"));

		new RaceCfg().initComments(this);
		new GalaxyCfg().initComments(this);
		new AdvancedCfg().initComments(this);
		new GovernorCfg().initComments(this);
		new ModnarCfg().initComments(this);
		new ExtCfg().initComments(this);
	}
	@Override
	void overrideGameOptions () {
		new RaceCfg().overrideGameOptions(this);     // Associated GUI: SetupRaceUI.java
		new AdvancedCfg().overrideGameOptions(this); // Associated GUI: StartOptionsUI.java
		new GovernorCfg().overrideGameOptions(this); // Associated GUI: .java
		new ModnarCfg().overrideGameOptions(this);   // Associated GUI: StartModOptionsUI.java
		new ExtCfg().overrideGameOptions(this);      // Associated GUI: NONE
		// to the end: NB Empire is dependent of MIN STARS PER EMPIRE
		new GalaxyCfg().overrideGameOptions(this);   // Associated GUI: SetupGalaxyUI.java
		// RemnantCfg                                          // Associated GUI: GameSettingsUI.java
		// LaunchCfg                                           // Associated GUI: GameUI.java
		// LoadFileCfg                                         // Associated GUI: LoadGameUI.java
	}

}
