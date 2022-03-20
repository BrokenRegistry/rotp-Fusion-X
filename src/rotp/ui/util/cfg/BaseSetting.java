package rotp.ui.util.cfg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import rotp.model.game.IGameOptions;

public abstract class BaseSetting {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
	static final String HEAD_OF_OPTIONS = "# OPTIONS";
	static final String HEAD_OF_DEFAULT = "# DEFAULT";
	static final String HEAD_OF_LAST    = "# LAST";
	static final String HEAD_OF_INFO    = "# DEFAULT / LAST";
	static final String LABEL_OF_ENABLE_SECTION_KEY = "¦ LOCAL ENABLE";
	static final List<String> BOOLEAN_LIST = List.of("YES", "NO", "TRUE", "FALSE");
    // ------------------------------------------------------------------------
	// Variables Properties
    //
 	private Comments     headComments;
    private KeyValuePair settingKey   = new KeyValuePair(KeyValuePair.LABEL_OF_SECTION_KEY, null);
    private KeyValuePair localEnable  = new KeyValuePair(LABEL_OF_ENABLE_SECTION_KEY, "Both");
    private KeyValuePair optionsList  = new KeyValuePair(HEAD_OF_OPTIONS, null);
    private KeyValuePair defaultValue = new KeyValuePair(HEAD_OF_DEFAULT, null);
    private KeyValuePair lastValue    = new KeyValuePair(HEAD_OF_LAST, null);
    private Comments     settingComments;
    private List<String> settingOptions;
    private Comments     optionsComments;
    private LinkedHashMap<String, KeyValuePair> userSettingMap;
    private Comments     bottomComments;
    private LinkedHashMap<String, String> labelOptionsMap;
    private Integer      minValue;
    private Integer      maxValue;
    private Integer      minRandom;
    private Integer      maxRandom;
    private boolean      isInteger = false;
    private boolean      isBoolean = false;
    // ------------------------------------------------------------------------
    // Constructors And Helpers
    //
    BaseSetting(String key, String defaultVal, List<String> settingOptions) { // String Value
        init0(key, defaultVal);
        setSettingOptions(settingOptions);
    }
    BaseSetting(String key, String defaultVal) { // Boolean Value
        init0(key, defaultVal);
        setSettingOptions(BOOLEAN_LIST);
        isBoolean  = true;
    }
    BaseSetting(String key, String defaultVal, int min, int max, int minR, int maxR) { // Integer Value
        init0(key, defaultVal);
        setSettingOptions(min, max, minR, maxR);
        isInteger  = true;
    }
    private void init0(String key, String defaultVal) {
        reset();
        settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
        userSettingMap = new LinkedHashMap<String, KeyValuePair>();
        defaultValue.setValue(defaultVal);
        initComments();
    }
    private void reset() {
        localEnable  = new KeyValuePair(LABEL_OF_ENABLE_SECTION_KEY, "Both");
        optionsList  = new KeyValuePair(HEAD_OF_OPTIONS, null);
        lastValue    = new KeyValuePair(HEAD_OF_LAST, null);
    }
    // ========================================================================
	// Abstract Methods
	//
    abstract void initComments();
    abstract void selectedGameOptions (IGameOptions gameOptions, String userOption);
    abstract void setGameOptionsToDefault (IGameOptions gameOptions);
    // ------------------------------------------------------------------------
    // Game options related methods
    //
    void overrideGameOptions (IGameOptions gameOptions, String userOption){
        if (userSettingMap.containsKey(userOption)) {
            if (isSectionReadable(userOption))
                gameOptions.selectedPlayerRace(getValidSetting(userOption));
        }
    }
    // ------------------------------------------------------------------------
    // User Settings related Methods
    //
    void resetUserSettings() { // but keep default values
        userSettingMap = new LinkedHashMap<String, KeyValuePair>();
    }
    void addUserSetting(KeyValuePair configLine) {
        setKeyValuePair (configLine.getKey(), configLine.getValue());
    }
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
   String getKey() {
       return settingKey.value;
    }
    String getDefaultValue() {
        return defaultValue.getValue();
    }
    String getLastValue() {
        return lastValue.getValue();
    }
    LinkedHashSet<String> getUserSettingKeys () {
        return new LinkedHashSet<String>(userSettingMap.keySet());
    }
    String  getPairValue(String key) {
        if (key != null && userSettingMap.containsKey(key)) {
            return userSettingMap.get(key).getValue();
        }
        return getDefaultValue();
    }
    boolean getBooleanSetting(String key) {
        boolean preset = defaultValue.getValue(false); // onWrong = false
        if (key != null && isBoolean) {
            String Key = key.toUpperCase();
            if (userSettingMap.containsKey(Key)) {
                KeyValuePair setting = userSettingMap.get(Key);
                if (setting.isRandom()) return getBooleanRandom();
                return setting.getValue(preset);
            }
        }
        return preset;
    }
    Integer getIntegerSetting(String key) {
        Integer preset = defaultValue.getValue(0); // onWrong = 0
        if (key != null && isInteger) {
            String Key = key.toUpperCase();
            if (userSettingMap.containsKey(Key)) {
                KeyValuePair setting = userSettingMap.get(Key);
                if (setting.isRandom())
                    return getIntegerRandom(setting.getRandomParameters(minRandom, maxRandom));
                return setting.getValue(preset);
            }
        }
        return preset;
    }
    String  getValidSetting(String key) {
        if (key != null) {
            String value = getValidValue(key);
            if (!value.isBlank()) {
                return labelOptionsMap.get(value.toUpperCase());
            }
        }
        return "";
    }
    String  getValidNonBlankSetting(String key) {
        String value = getDefaultValue();
        if (key != null) {
            value = getValidNonBlankValue(key);
        }
        return labelOptionsMap.get(value.toUpperCase());
    }
    void setLastValue(String value) {
        lastValue.setValue(settingNameToLabel(value));
    }
    void removeLocalEnable() {
        localEnable = null;
    }
    void setSettingOptions(Integer min, Integer max, Integer minR, Integer maxR) {
        minValue  = min;
        maxValue  = max;
        minRandom = minR;
        maxRandom = maxR;
        setSettingOptions( List.of(
            "Rmin = " + minR.toString(),
            "Rmax = " + maxR.toString(),
            "Lmin = " + min.toString(),
            "Lmax = " + max.toString()
            ) );
    }
    // ------------------------------------------------------------------------
    // Other Methods
    //
    boolean defaultValueNotInitialized() {
        return defaultValue.isBlank();
    }
    String toString(LinkedHashSet<String> groupOptions) {
        String out = "";
        if (headComments != null    && !headComments.isEmpty())    out += (headComments.toString()    + System.lineSeparator());
        if (settingKey   != null    && settingKey.hasKey())        out += (settingKey.toString()      + System.lineSeparator());
        if (localEnable  != null    && localEnable.hasKey())       out += (localEnable.toString()     + System.lineSeparator());
        if (settingComments != null && !settingComments.isEmpty()) out += (settingComments.toString() + System.lineSeparator());
        if (optionsList  != null    && optionsList.hasKey())       out += (optionsList.toString()     + System.lineSeparator());
        if (lastValue != null && lastValue.hasKey())
            out += (String.format(KeyValuePair.KEY_FORMAT, HEAD_OF_INFO) +
                    getDefaultValue() + " / " +
                    lastValue.getValue().toString() +
                    System.lineSeparator());
        if (optionsComments != null && !optionsComments.isEmpty()) out += (optionsComments.toString() + System.lineSeparator());
        for (String option : groupOptions) {
            if (!userSettingMap.containsKey(option.toUpperCase())) {
                userSettingMap.put(option.toUpperCase(), new KeyValuePair(option, getDefaultValue()));
            }
                out += (userSettingMap.get(option.toUpperCase()).toString() + System.lineSeparator());
        }
        if (bottomComments != null && !bottomComments.isEmpty()) { out += (bottomComments.toString() + System.lineSeparator()); }
        return out;
    }
    void actionSave(String key) {
        // Section should be writable to overwrite with last value
        if (isSectionWritable()) setKeyValuePair(key, getLastValue());
    }
    void actionUpdate(String key) {
        // if the key is absent, add it with last value
        if (!userSettingMap.containsKey(key)) {
            setKeyValuePair(key, getLastValue());
            return;
        }
        // Section should be writable and value not blank
        if (isSectionWritable() && !userSettingMap.get(key).isBlank())
            userSettingMap.get(key).setValue(getLastValue());
    }
    void actionDefault(String key) {
        // if the key is absent, add it with default value
        if (!userSettingMap.containsKey(key)) {
            setKeyValuePair(key, getDefaultValue());
            return;
        }
        // Section should be writable and value not blank
        if (isSectionWritable() && !userSettingMap.get(key).isBlank())
                userSettingMap.get(key).setValue(getDefaultValue());
    }
    void headComments(Comments comments)    { headComments = comments; }
    void settingComments(Comments comments) { settingComments = comments; }
    void optionsComments(Comments comments) { optionsComments = comments; }
    void bottomComments(Comments comments)  { bottomComments = comments; }
    boolean isSectionReadable(String key) { return (isSectionEnabled() & hasValidSetting (key)); }
    // ------------------------------------------------------------------------
    // Private Methods
    //
    private String getValidValue(String key) {
        if (key != null) {
            String Key = key.toUpperCase();
            if (userSettingMap.containsKey(Key)) {
                KeyValuePair setting = userSettingMap.get(Key);
                if (setting.isRandom()){
                    int[] rndParam = setting.getRandomParameters(0, settingOptions.size()-1); // (defaultMin, defaultMax)
                    int rnd = getIntegerRandom(rndParam);
                    String result = settingOptions.get(rnd);
                    return settingNameToLabel(result);
                }
                String value = setting.getValue();
                if (value.isBlank() || labelOptionsMap.keySet().contains(value.toUpperCase())) {
                    return value;
                }
            }
        }
        return getDefaultValue();
    }
    private String getValidNonBlankValue(String key) {
        if (key != null) {
            String Key = key.toUpperCase();
            if (userSettingMap.containsKey(Key)) {
                String value = userSettingMap.get(Key).getValue();
                if (labelOptionsMap.keySet().contains(value.toUpperCase())) {
                    return value;
                }
            }
        }
        return getDefaultValue();
    }
    private void setKeyValuePair (String key, String value) {
        if (key != null && value != null) {
            KeyValuePair pair = new KeyValuePair(key, value);
            if (pair.isBlank() || pair.isRandom() || labelOptionsMap.keySet().contains(value.toUpperCase())) {
                userSettingMap.put(key.toUpperCase(), new KeyValuePair(key, settingNameToLabel(value)) );
                }
            else {
                userSettingMap.put(key.toUpperCase(), new KeyValuePair(key, getDefaultValue()) );
            }
        }
    }
    private void setSettingOptions(List<String> options) {
        settingOptions  = new ArrayList<String>();
        labelOptionsMap = new LinkedHashMap<String, String>();
        for (String option : options) {
            settingOptions.add(option);
            labelOptionsMap.put(settingNameToLabel(option).toUpperCase(), option);
        }
        optionsList.setValue(settingNameToLabel(settingOptions).toString());
    }
    private String settingNameToLabel (String option) {
        return capitalize(option.substring(option.lastIndexOf("_") + 1));
    }
    private List<String> settingNameToLabel (List<String> options) {
        List<String> labels = new ArrayList<>();
        for (String option : options) {
            labels.add(settingNameToLabel (option));
        }
        return labels;
    }
    private boolean isSectionEnabled()  { return localEnable.isReadable(); }
    private boolean isSectionWritable() { return localEnable.isWritable();}
    private boolean hasValidSetting (String key) {
        if (key != null) {
            String Key = key.toUpperCase();
            if (userSettingMap.containsKey(Key)) {
                KeyValuePair currentSetting = userSettingMap.get(Key);
                if (currentSetting.isBlank())  return false;
                if (currentSetting.isRandom()) return true;
                if (isBoolean) return currentSetting.isValid(BOOLEAN_LIST);
                if (isInteger) return currentSetting.isValid(minValue, maxValue);
                return currentSetting.isValid(labelOptionsMap.keySet());
            }
        }
        return false;
    }
    // -----------------------------------------------------------------------
    // Static Methods
    //
	private static String capitalize(String s) {
		if ( s.isEmpty() ) {
            return s;
        }
		if ( s.length() == 1 ) {
            return s.toUpperCase();
        }
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	private static boolean getBooleanRandom() {
        return ThreadLocalRandom.current().nextBoolean();
    }
	private static Integer getIntegerRandom(int min, int max) {
		int diff = max - min;
		if (diff == 0) {
			return min;
		}
		if (diff < 0) {
			return getIntegerRandom(max, min);
		}
		return ThreadLocalRandom.current().nextInt(diff + 1) + min; // +1 because last value is exclusive!!!
	}
	private static Integer getIntegerRandom(int[] lim) {
		return getIntegerRandom(lim[0], lim[1]);
	}
}
