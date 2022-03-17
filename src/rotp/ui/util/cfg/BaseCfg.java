package rotp.ui.util.cfg;

import rotp.model.game.IGameOptions;

public abstract class BaseCfg {
      // ========================================================================
	// Public Methods
	//
    public void reloadLocalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        presets.resetToDefault = false;
        overrideGameOptions(presets);
    }
    public void reloadGlobalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        // User asked for this then it overload GLOBAL ENABLE
        presets.resetToDefault = false;
        presets.overrideGameOptions();
   }
    public void reloadDefaultConfig(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        presets.resetToDefault = true;
		overrideGameOptions(presets);
        presets.resetToDefault = false;
	}
    // ========================================================================
	// Initialization Methods
	//
    abstract void loadGameOptions(Presets p, boolean u); // u for Update
    abstract void initComments(Presets p);
    abstract void overrideGameOptions (Presets p);

}
