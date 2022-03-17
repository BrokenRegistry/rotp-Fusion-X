package rotp.ui.util.cfg;

import rotp.model.game.IGameOptions;

public abstract class BaseCfg {
    // ========================================================================
	// Public Methods
	//
    public void reloadLocalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        overrideGameOptions(presets, false); // resetToDefault = false
    }
    public void reloadGlobalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        presets.overrideGameOptions(false); // resetToDefault = false
    }
    public void reloadDefaultConfig(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
		overrideGameOptions(presets, true); // resetToDefault = true
	}
    // ========================================================================
	// Initialization Methods
	//
    abstract void loadGameOptions(Presets p, boolean u);
    abstract void initComments(Presets p);
    abstract void overrideGameOptions (Presets p, boolean resetToDefault);

}
