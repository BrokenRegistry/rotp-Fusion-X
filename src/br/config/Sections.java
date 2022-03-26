package br.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.config.comment.Comment;
import rotp.ui.util.cfg.Presets;

public class Sections {
	static final List<String> BOOLEAN_LIST   = List.of("YES", "NO", "TRUE", "FALSE");
	static final String HEAD_OF_INFO    = "# DEFAULT / LAST";
	static final String LABEL_OF_SECTION_KEY = "¦ SETTING";
	static final String LABEL_OF_ENABLE_SECTION_KEY = "¦ LOCAL ENABLE";
	static final String HEAD_OF_OPTIONS = "# OPTIONS";
	static final String HEAD_OF_LAST    = "# LAST";
//	static final String HEAD_OF_DEFAULT = "# DEFAULT";
	private Comment      headComments;
	private UserChoice settingKey   = new UserChoice(LABEL_OF_SECTION_KEY, "");
	private UserChoice localEnable  = new UserChoice(LABEL_OF_ENABLE_SECTION_KEY, "Both");
	private UserChoice optionsList  = new UserChoice(HEAD_OF_OPTIONS, "");
	// private UserChoice defaultValue = new UserChoice(HEAD_OF_DEFAULT, null);
	private UserChoice lastValue    = new UserChoice(HEAD_OF_LAST, "");
	private Comment      settingComments;
	private List<String> settingOptions;
	private Comment      optionsComments;
	private LinkedHashMap<String, UserChoice> settingMap;
	private Comment      bottomComments;
	private LinkedHashMap<String, String> labelOptionsMap;
	private Integer      minValue;
	private Integer      maxValue;
	private Integer      minRandom;
	private Integer      maxRandom;
	private boolean      isInteger = false;
	private boolean      isBoolean = false;
	private UserChoice currentSetting;

	// ------------------------------------------------------------------------
	// Constructors
	//
	public Sections(String key, String defaultValue, List<String> settingOptions) { // String Value
		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
		setSettingOptions(settingOptions);
		settingMap = new LinkedHashMap<String, UserChoice>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, defaultValue);
		}
	}
	public Sections(String key, boolean defaultValue) { // Boolean Value
		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
		setSettingOptions(BOOLEAN_LIST);
		settingMap = new LinkedHashMap<String, UserChoice>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, StrField.toYesNoString(defaultValue));
		}
		isBoolean  = true;
	}
	public Sections(String key, Integer defaultValue,
			Integer min, Integer max, Integer minR, Integer maxR) { // Integer Value
		settingKey.setValue(key); // YES key! because the key is LABEL_OF_MAIN_KEY!
		setSettingOptions(min, max, minR, maxR);
		settingMap = new LinkedHashMap<String, UserChoice>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, defaultValue.toString());
		}
		isInteger  = true;
	}
	// ------------------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * Return Setting's Key as String 
	 */
	String getKeyAsString() {
		return settingKey.getValueAsString();
	}
	/**
	 * Return Setting's Key as StrFields 
	 */
	StrField getKey() {
		return settingKey.getValue();
	}
	/**
	 * Return Setting's Default Choice as String 
	 */
	public String getDefaultValueAsString() {
		return Presets.defaultValuesMap().get(getKey().getAsKey());
	}
	/**
	 * Return Setting's Default Choice as StrField 
	 */
	public StrField getDefaultValue() {
		return new StrField(getDefaultValueAsString()).clean() ;
	}
	/**
	 * Return Setting's Last Choice as String 
	 */
	String getLastValueAsString() {
		return lastValue.getValueAsString();
	}
	/**
	 * Return Setting's Last Choice as StrField 
	 */
	StrField getLastValue() {
		return lastValue.getValue();
	}
	public LinkedHashSet<String> getGroupKeySet () {
		return new LinkedHashSet<String>(settingMap.keySet());
	}
	/**
	 * Return Setting's raw selected User Choice as String 
	 */
	public String getUserChoiceAsString(String key) {
		return getUserChoice(key).toString();
	}
	/**
	 * Return  Setting's selected User Choice as StrField 
	 */
	public StrField getUserChoice(String key) {
		if (key != null && settingMap.containsKey(key)) {
			return settingMap.get(key).getValue();
		}
		return getDefaultValue();
	}
	/**
	 * Return Setting's selected User Choice as boolean 
	 */
	public boolean getBooleanSetting(String key) {
		boolean preset = getDefaultValue().getOrDefault(false);
		StrField setting = getUserChoice(key);
		if (setting.isRandom()) {
			return getBooleanRandom();
		}
		return setting.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as integer 
	 */
	public Integer getIntegerSetting(String key) {
		Integer preset = getDefaultValue().getOrDefault(0);
		StrField setting = getUserChoice(key);
		if (setting.isRandom()) {
			return getIntegerRandom(setting.extractOrDefaultMinMaxRandomParameters(minRandom, maxRandom));
		}
		return setting.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as String 
	 */
	private String getValidValue(String key) {
		String preset = getDefaultValueAsString();
		StrField userChoice = getUserChoice(key);
		if (userChoice.isRandom()) { 
			int[] rndParam = userChoice.extractOrDefaultMinMaxRandomParameters(0, settingOptions.size()-1); // (defaultMin, defaultMax)
			int rnd = getIntegerRandom(rndParam);
			String result = settingOptions.get(rnd);
			return settingNameToLabel(result);
		}
		return userChoice.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as ROTP Understand
	 * Or blank Value for No change
	 */
	public String getValidSetting(String key) {
		if (key != null) {
			String value = getValidValue(key);
			if (!value.isBlank()) {
				return labelOptionsMap.get(value.toUpperCase());
			}
		}
		return "";
	}
	/**
	 * Return Setting's selected User Choice as ROTP Understand 
	 */
	public String getValidNonBlankSetting(String key) {
		String value = getDefaultValueAsString();
		if (key != null) {
			value = getValidNonBlankValue(key);
		}
		return labelOptionsMap.get(value.toUpperCase());
	}
	private String getValidNonBlankValue(String key) {
		if (key != null) {
			String Key = key.toUpperCase();
			if (settingMap.containsKey(Key)) {
				String value = settingMap.get(Key).getValue().toString();
				if (labelOptionsMap.keySet().contains(value.toUpperCase())) {
					return value;
				}
			}
		}
		return getDefaultValueAsString();
	}
	public void setUserChoice (UserChoice pair) { 
		setUserChoice(pair.getKey(), pair.getValue());
	}
	private void setUserChoice (StrField key, StrField value) {
		if (key != null && value != null) {
			if (value.isBlank() 
					|| value.isRandom() 
					|| value.isMemberOf(labelOptionsMap.keySet())) {
				settingMap.put(key.getAsKey(), new UserChoice(key, value));
				}
				else {
					settingMap.put(key.getAsKey(), new UserChoice(key, getDefaultValue()) );
				}
		}
	}
	public void    setLastValue(String value) {
		lastValue.setValue(settingNameToLabel(value));
	}
	public void    removeLocalEnable() { localEnable = null; }
	public void setLastValue(boolean value) {
		lastValue.setValue(value);
	}
	public void setLastValue(Integer value) { 
		lastValue.setValue(value.toString());
	}
	public void setSettingOptions(Integer min, Integer max, Integer minR, Integer maxR) {
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
	private void setSettingOptions(List<String> options) {
		settingOptions  = new ArrayList<String>();
		labelOptionsMap = new LinkedHashMap<String, String>();
		for (String option : options) {
			settingOptions.add(option);
			labelOptionsMap.put(settingNameToLabel(option).toUpperCase(), option);
		}
		optionsList.setValue(settingNameToLabel(settingOptions).toString());
	}
	// ------------------------------------------------------------------------
	// Other Methods
	//
	public String toPrint(LinkedHashSet<String> groupOptions) {
		String out = "";
    	if (headComments != null && !headComments.isEmpty()) {
    		out += (headComments.toPrint() + System.lineSeparator());
    	}
    	if (settingKey != null && settingKey.hasKey()) {
    		out += (settingKey.toPrint() + System.lineSeparator());
    	}
    	if (localEnable != null && localEnable.hasKey()) {
    		out += (localEnable.toPrint() + System.lineSeparator());
    	}
    	if (settingComments != null && !settingComments.isEmpty()) {
    		out += (settingComments.toPrint() + System.lineSeparator());
    	}
    	if (optionsList != null && optionsList.hasKey()) {
    		out += (optionsList.toPrint() + System.lineSeparator());
    	}
		if (lastValue != null && lastValue.hasKey()) {
			out += (String.format(UserChoice.KEY_FORMAT, HEAD_OF_INFO) +
					settingNameToLabel(getDefaultValueAsString()) + " / " +
					settingNameToLabel(lastValue.getValue().toString()) +
					System.lineSeparator());
		}
		if (optionsComments != null && !optionsComments.isEmpty()) {
			out += (optionsComments.toPrint() + System.lineSeparator());
		}
    	for (String option : groupOptions) {
    		if (!settingMap.containsKey(option.toUpperCase())) {
    			settingMap.put(option.toUpperCase(), 
    					new UserChoice(option, (getDefaultValue())));
    		}
    		out += (settingMap.get(option.toUpperCase()).toPrint() + System.lineSeparator());
    	}
    	if (bottomComments != null && !bottomComments.isEmpty()) { 
    		out += (bottomComments.toPrint() + System.lineSeparator()); 
    		}
    	return out;
	}
	public void actionSave(String key) {
		// Section should be writable to overwrite with last value
		if (isSectionWritable()) setUserChoice(new StrField(key).clean(), getLastValue());
	}
	public void actionUpdate(String key) {
		// if the key is absent, add it with last value
		if (!settingMap.containsKey(key.toUpperCase())) {
			setUserChoice(new StrField(key).clean(), getLastValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() && !settingMap.get(key.toUpperCase()).isBlank())
			settingMap.get(key).setValue(getLastValue());
	}
	public void actionDefault(String key) {
		// if the key is absent, add it with default value
		if (!settingMap.containsKey(key.toUpperCase())) {
			setUserChoice(new StrField(key).clean(), getDefaultValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() && !settingMap.get(key).isBlank())
				settingMap.get(key).setValue(getDefaultValue());
	}
	public void headComments(Comment comments) {
		headComments = comments;
	}
	void settingComments(Comment comments) { 
		settingComments = comments;
	}
	public void optionsComments(Comment comments) { 
		optionsComments = comments;
	}
	public void bottomComments(Comment comments) {
		bottomComments = comments; 
	}
	public boolean isSectionReadable(String key) { 
			return (isSectionEnabled() & hasValidSetting (key));
		}
		private String settingNameToLabel (String option) {
		return StrField.capitalize(option.substring(option.lastIndexOf("_") + 1));
	}
	private List<String> settingNameToLabel (List<String> options) {
		List<String> labels = new ArrayList<>();
		for (String option : options) {
			labels.add(settingNameToLabel (option));
		}
		return labels;
	}
	private boolean isSectionEnabled() {
		return localEnable.isReadable();
	}
	private boolean isSectionWritable() {
		return localEnable.isWritable();
	}
	private boolean hasValidSetting (String key) {
		if (key != null) {
			String Key = key.toUpperCase();
			if (settingMap.containsKey(Key)) {
				currentSetting = settingMap.get(Key);
				if (currentSetting.isBlank())  return false;
				if (currentSetting.getValue().isRandom()) return true;
				if (isBoolean) return currentSetting.isValid(BOOLEAN_LIST);
				if (isInteger) return currentSetting.isValid(minValue, maxValue);
				return currentSetting.isValid(labelOptionsMap.keySet());
			}
		}
		return false;
	}
	// ==============================================================
	// From Configs

	
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
	private static boolean getBooleanRandom() {
		return ThreadLocalRandom.current().nextBoolean();
	}


}

