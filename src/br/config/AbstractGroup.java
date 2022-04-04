package br.config;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public abstract class AbstractGroup <T> {
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    LinkedHashMap<String, AbstractSetting<T>> keySettingMap 
    			= new LinkedHashMap<String, AbstractSetting<T>>();
    // ------------------------------------------------------------------------
    // Constructor
    //
    protected AbstractGroup(T gameObject) {
        initSettingList(gameObject);
    }
    // ========================================================================
	// Abstract Methods
	//
    protected abstract void initSettingList(T gameObject);

    // ========================================================================
	// Public and packages Methods
	//
    public void actionGetGuiValue(T gameObject) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.setGuiOption(gameObject);
        }
    }
    public void actionGetGameValue(T gameObject) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.setGameOption(gameObject);
        }
    }
    public void actionGuiToFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionUiToFile(userSettingKey);
        }
    }
    public void actionGameToFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionGameToFile(userSettingKey);
        }
    }
    public void actionInitialToFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionInitialToFile(userSettingKey);
        }
    }
    public void actionGuiUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionUiUpdateFile(userSettingKey);
        }
    }
    public void actionGameUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionGameUpdateFile(userSettingKey);
        }
    }
    public void actionInitialUpdateFile(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionInitialUpdateFile(userSettingKey);
        }
    }
    public LinkedHashSet<String> keyList() {
        return new LinkedHashSet<String>(keySettingMap.keySet());
     }
    public AbstractSetting<T> getSetting(String key) {
        return keySettingMap.get(key);
    }
    public String toPrint(LinkedHashSet<String> userSettingKeys, boolean cleanUserKeys) {
        String out = "";
        for (AbstractSetting<T> setting : keySettingMap.values() ) {
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
    protected void addSetting(AbstractSetting<T> setting) {
        keySettingMap.put(setting.settingKey().toKey(), setting);
    }
    public void resetAllUserSettings() {
        for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.resetUserSettings();
        }
    }
    public void overrideGameParameters (T gameObject, LinkedHashSet<String> settingKeys) {
    	// Loop thru settings
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
    		setting.overrideGameParameters(gameObject, settingKeys);
        }
    }
    public void setGameParametersToFirst(T gameObject) {
        for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.putInitialToGUI(gameObject);
        }
    }
}
