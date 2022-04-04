package br.config;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public abstract class AbstractGroup <T, U> {
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    LinkedHashMap<String, AbstractSetting<T, U>> keySettingMap 
    			= new LinkedHashMap<String, AbstractSetting<T, U>>();
    // ------------------------------------------------------------------------
    // Constructor
    //
    protected AbstractGroup(T tObject) {
        initSettingList(tObject);
    }
    // ========================================================================
	// Abstract Methods
	//
    protected abstract void initSettingList(T tObject);

    // ========================================================================
	// Public and packages Methods
	//
    public void actionGetGuiValue(T tObject) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.setGuiOption(tObject);
        }
    }
    public void actionGetGameValue(T tObject) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.setGameOption(tObject);
        }
    }
    public void actionGuiToFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionUiToFile(userSettingKey);
        }
    }
    public void actionGameToFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionGameToFile(userSettingKey);
        }
    }
    public void actionInitialToFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionInitialToFile(userSettingKey);
        }
    }
    public void actionGuiUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionUiUpdateFile(userSettingKey);
        }
    }
    public void actionGameUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionGameUpdateFile(userSettingKey);
        }
    }
    public void actionInitialUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.actionInitialUpdateFile(userSettingKey);
        }
    }
    public LinkedHashSet<String> keyList() {
        return new LinkedHashSet<String>(keySettingMap.keySet());
     }
    public AbstractSetting<T, U> getSetting(String key) {
        return keySettingMap.get(key);
    }
    public String toPrint(LinkedHashSet<String> userSettingKeys, boolean cleanUserKeys) {
        String out = "";
        for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
        	if (cleanUserKeys) {
        		out += setting.toPrint(userSettingKeys);
        	}
        	else {
        		LinkedHashSet<String> keySet = new LinkedHashSet<String>(userSettingKeys);
        		keySet.addAll(setting.getUserSettingKeySet());
        		out += setting.toPrint(keySet);
        	}
        }
        return out;
    }
    // ========================================================================
	// Initialization Methods
	//
    protected void addSetting(AbstractSetting<T, U> setting) {
        keySettingMap.put(setting.settingKey().toKey(), setting);
    }
    public void resetAllUserSettings() {
        for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.resetUserSettings();
        }
    }
    public void overrideGuiParameters (T tObject, LinkedHashSet<String> settingKeys) {
    	// Loop thru settings
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
    		setting.overrideGuiParameters(tObject, settingKeys);
        }
    }
    public void changeGameFileParameters (U uObject, LinkedHashSet<String> settingKeys) {
    	// Loop thru settings
    	for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
    		setting.changeGameFileParameters(uObject, settingKeys);
        }
    }
    public void setGuiParametersToInitial(T tObject) {
        for (AbstractSetting<T, U> setting : keySettingMap.values() ) {
            setting.putInitialToGUI(tObject);
        }
    }
}
