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
    public void actionGetLastValue(T gameObject) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.setLastOption(gameObject);
        }
    }
    public void actionSave(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionSave(userSettingKey);
        }
    }
    public void actionUpdate(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionUpdate(userSettingKey);
        }
    }
    public void actionFirst(String userSettingKey) {
    	for (AbstractSetting<T> setting : keySettingMap.values() ) {
            setting.actionFirst(userSettingKey);
        }
    }
    public LinkedHashSet<String> keyList() {
        return new LinkedHashSet<String>(keySettingMap.keySet());
     }
    public AbstractSetting<T> getSetting(String key) {
        return keySettingMap.get(key);
    }
    public String toPrint(LinkedHashSet<String> userSettingKeys) {
        String out = "";
        for (AbstractSetting<T> setting : keySettingMap.values() ) {
            out += setting.toPrint(userSettingKeys);
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
            setting.setSelectedOptionToInitial(gameObject);
        }
    }
}
