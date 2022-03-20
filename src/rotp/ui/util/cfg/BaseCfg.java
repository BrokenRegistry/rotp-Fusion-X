package rotp.ui.util.cfg;

import rotp.model.game.IGameOptions;

public abstract class BaseCfg {
    // ========================================================================
	// Public Methods
	//
    public void reloadLocalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        presets.setResetToDefault(false);
        overrideGameOptions(presets);
    }
    public void reloadGlobalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        presets.setResetToDefault(false);
        presets.overrideGameOptions();
        overrideGameOptions(presets);
    }
    public void reloadDefaultConfig(IGameOptions gameOptions) {
        Presets presets = new Presets().initPresets(gameOptions);
        // presets.setResetToDefault(true);
		setGameOptionsToDefault(presets);
        // presets.setResetToDefault(false);
	}

    // ========================================================================
	// Initialization Methods
	//
    abstract void loadGameOptions(Presets p, boolean u); // u for Update
    abstract void initComments(Presets p);
    abstract void overrideGameOptions (Presets p);
    abstract void setGameOptionsToDefault (Presets p);
}
