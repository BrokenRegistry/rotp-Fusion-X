package rotp.ui.util.cfg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import rotp.model.game.IGameOptions;

public abstract class Group_Base {
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    LinkedHashMap<String, BaseSetting> keySettingMap = new LinkedHashMap<String, BaseSetting>();
    // ------------------------------------------------------------------------
    // Constructor
    //
    Group_Base(IGameOptions gameOptions) {
        initSettingList(gameOptions);
    }
    // ========================================================================
	// Abstract Methods
	//
    abstract void initSettingList(IGameOptions gameOptions);

    // ========================================================================
	// Public Methods
	//
    public void reloadLocalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        overrideGameOptions(gameOptions, presets.selectedUserOptionsSet);
    }
    public void reloadGlobalUserPresets(IGameOptions gameOptions) {
        Presets presets = new Presets().readUserConfig(gameOptions);
        overrideGameOptions(gameOptions, presets.selectedUserOptionsSet);
    }
    public void reloadDefaultConfig(IGameOptions gameOptions) {
		setGameOptionsToDefault(gameOptions);
	}
    public LinkedHashSet<String> keyList() {
        return new LinkedHashSet<String>(keySettingMap.keySet());
     }
    public BaseSetting getSetting(String key) {
        return keySettingMap.get(key);
    }
    String toString(LinkedHashSet<String> userSettingKeys) {
        String out = "";
        for (BaseSetting setting : keySettingMap.values() ) {
            out += setting.toString(userSettingKeys);
        }
        return out;
    }
    // ========================================================================
	// Initialisation Methods
	//
    void addSetting(BaseSetting setting) {
        keySettingMap.put(setting.getKey(), setting);
    }
    void resetAllUserSettings() {
        for (BaseSetting setting : keySettingMap.values() ) {
            setting.resetUserSettings();
        }
    }
    private void overrideGameOptions (IGameOptions gameOptions, LinkedHashSet<String> selectedUserOptionsSet) {
        for (String userOption : selectedUserOptionsSet) {
            for (BaseSetting setting : keySettingMap.values() ) {
                setting.overrideGameOptions(gameOptions, userOption);
            }
        }
    }
    private void setGameOptionsToDefault(IGameOptions gameOptions) {
        for (BaseSetting setting : keySettingMap.values() ) {
            setting.setGameOptionsToDefault(gameOptions);
        }
    }
}
