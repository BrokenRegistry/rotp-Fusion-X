package br.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import br.config.comment.Comment;
import rotp.ui.util.cfg.Presets;

public class Section {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
	static final String HEAD_OF_INFO    = new Comment("DEFAULT / LAST").toPrint();
	static final String HEAD_OF_OPTIONS = new Comment("OPTIONS").toPrint();
    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private Comment headComments;
	private Comment settingComments;
	private Comment optionsComments;
	private Comment bottomComments;

	private CfgField settingKey = new CfgField();
	private CfgField lastValue  = new CfgField(); // Last saved value
	private CfgField initValue  = new CfgField(); // Value when initially loaded
	private CfgField userOptionList = new CfgField();
	private CfgField localEnable = new CfgField("Both");
	
	private List<String> settingOptions;
	private LinkedHashMap<String, CfgLine> settingMap;
	private LinkedHashMap<String, String> labelOptionsMap;
	private Integer minValue;
	private Integer maxValue;
	private Integer minRandom;
	private Integer maxRandom;
	private boolean isInteger = false;
	private boolean isBoolean = false;
	private CfgLine currentSetting;

	// ------------------------------------------------------------------------
	// Constructors
	//
	public Section(String key, String defaultValue, List<String> settingOptions) { // String Value
		settingKey.set(key);
		setSettingOptions(settingOptions);
		settingMap = new LinkedHashMap<String, CfgLine>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, defaultValue);
		}
	}
	public Section(String key, boolean defaultValue) { // Boolean Value
		settingKey.set(key);
		setSettingOptions(CfgField.BOOLEAN_LIST);
		settingMap = new LinkedHashMap<String, CfgLine>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, CfgField.toYesNoString(defaultValue));
		}
		isBoolean  = true;
	}
	public Section(String key, Integer defaultValue, int min, int max, int minR, int maxR) { // Integer Value
		settingKey.set(key);
		setSettingOptions(min, max, minR, maxR);
		settingMap = new LinkedHashMap<String, CfgLine>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, defaultValue.toString());
		}
		isInteger  = true;
	}
	// ------------------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * Return Setting's Key as CfgFields 
	 */
	CfgField settingKey() {
		return settingKey;
	}
	/**
	 * Return initValue as CfgFields 
	 */
	CfgField initValue() {
		return initValue;
	}
	/**
	 * Return lastValue as CfgFields 
	 */
	CfgField lastValue() {
		return lastValue;
	}
	/**
	 * Return userOptionList as CfgFields 
	 */
	CfgField userOptionList() {
		return userOptionList;
	}
	/**
	 * Return localEnable as CfgFields 
	 */
	CfgField localEnable() {
		return localEnable;
	}
	
	/**
	 * Return Setting's Default Choice as String 
	 */
	public String getDefaultValueAsString() {
		return Presets.defaultValuesMap().get(settingKey().toKey());
	}
	/**
	 * Return Setting's Default Choice as CfgField 
	 */
	public CfgField getDefaultValue() {
		return new CfgField(getDefaultValueAsString()) ;
	}
	/**
	 * Return Set of User Setting Keys
	 */
	public LinkedHashSet<String> getUserSettingKeySet () {
		return new LinkedHashSet<String>(settingMap.keySet());
	}
	/**
	 * Return  Setting's selected User Choice as CfgField 
	 */
	public CfgField getCfgLine(String key) {
		if (key != null && settingMap.containsKey(key)) {
			return settingMap.get(key).value();
		}
		return getDefaultValue();
	}
	/**
	 * Return Setting's selected User Choice as boolean 
	 */
	public boolean getBooleanSetting(String key) {
		boolean preset = getDefaultValue().getOrDefault(false);
		CfgField setting = getCfgLine(key);
		if (setting.isRandom()) {
			return CfgField.getBooleanRandom();
		}
		return setting.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as integer 
	 */
	public Integer getIntegerSetting(String key) {
		Integer preset = getDefaultValue().getOrDefault(0);
		CfgField setting = getCfgLine(key);
		if (setting.isRandom()) {
			return CfgField.getIntegerRandom(setting
					.extractOrDefaultMinMaxRandomParameters(minRandom, maxRandom));
		}
		return setting.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as String 
	 */
	private String getValidValue(String key) {
		String preset = getDefaultValueAsString();
		CfgField cfgLine = getCfgLine(key);
		if (cfgLine.isRandom()) { 
			int[] rndParam = cfgLine.extractOrDefaultMinMaxRandomParameters(0, settingOptions.size()-1); // (defaultMin, defaultMax)
			int rnd = CfgField.getIntegerRandom(rndParam);
			String result = settingOptions.get(rnd);
			return settingNameToLabel(result);
		}
		return cfgLine.getOrDefault(preset);
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
				String value = settingMap.get(Key).value().toString();
				if (labelOptionsMap.keySet().contains(value.toUpperCase())) {
					return value;
				}
			}
		}
		return getDefaultValueAsString();
	}
	public void putCfgLine (CfgLine pair) { 
		putCfgLine(pair.key(), pair.value());
	}
	private void putCfgLine (KeyField key, CfgField value) {
		if (key != null && value != null) {
			if (value.isBlank() 
					|| value.isRandom() 
					|| value.isMemberOf(labelOptionsMap.keySet())) {
				settingMap.put(key.toKey(), new CfgLine(key, value));
				}
				else {
					settingMap.put(key.toKey(), new CfgLine(key, getDefaultValue()) );
				}
		}
	}
	public void setLastValue(String value) {
		lastValue.set(settingNameToLabel(value));
	}
	public void removeLocalEnable() {
		localEnable.set("");
	}
	public void setLastValue(boolean value) {
		lastValue.set(value);
	}
	public void setLastValue(Integer value) { 
		lastValue.set(value.toString());
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
		userOptionList.set(settingNameToLabel(settingOptions).toString());
	}
	// ------------------------------------------------------------------------
	// Other Methods
	//
	/**
	 * return sections as String, ready to be printed
	 */
	public String toPrint(LinkedHashSet<String> groupOptions) {
		String out = "";
    	if (headComments != null && !headComments.isEmpty()) {
    		out += (headComments.toPrint() + System.lineSeparator());
    	}
   		out += new CfgLine(KeyField.LABEL_OF_SECTION_KEY, settingKey)
				.toPrint() + System.lineSeparator();
   		if (!localEnable.isBlank()) {
	   		out += new CfgLine(KeyField.LABEL_OF_ENABLE_SECTION_KEY, localEnable)
					.toPrint() + System.lineSeparator();
   		}
    	if (settingComments != null && !settingComments.isEmpty()) {
    		out += (settingComments.toPrint() + System.lineSeparator());
    	}
   		out += new CfgLine(HEAD_OF_OPTIONS, userOptionList)
				.toPrint() + System.lineSeparator();
   		out += new CfgLine(HEAD_OF_INFO
   						, (settingNameToLabel(getDefaultValueAsString())
   							+ " / "
   							+settingNameToLabel(lastValue.toString())
   						)
   					).toPrint() + System.lineSeparator();
		if (optionsComments != null && !optionsComments.isEmpty()) {
			out += (optionsComments.toPrint() + System.lineSeparator());
		}
    	for (String option : groupOptions) {
    		if (!settingMap.containsKey(option.toUpperCase())) {
    			settingMap.put(option.toUpperCase(), 
    					new CfgLine(option, (getDefaultValue())));
    		}
    		out += (settingMap.get(option.toUpperCase()).toPrint() + System.lineSeparator());
    	}
    	if (bottomComments != null && !bottomComments.isEmpty()) { 
    		out += (bottomComments.toPrint() + System.lineSeparator()); 
    		}
    	return out;
	}
	/**
	 * if Writing is allowed:
	 *     Set user choice to Last Value
	 */
	public void actionSave(String key) {
		if (isSectionWritable()) {
			putCfgLine(new KeyField(key), lastValue());
		}
	}
	public void actionUpdate(String key) {
		// if the key is absent, add it with last value
		if (!settingMap.containsKey(key.toUpperCase())) {
			putCfgLine(new KeyField(key), lastValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() 
			&& !settingMap.get(key.toUpperCase()).value().isBlank())
				settingMap.get(key).setValue(lastValue());
	}
	public void actionDefault(String key) {
		// if the key is absent, add it with default value
		if (!settingMap.containsKey(key.toUpperCase())) {
			putCfgLine(new KeyField(key), getDefaultValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() 
			&& !settingMap.get(key).value().isBlank())
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
		return CfgField.capitalize(option.substring(option.lastIndexOf("_") + 1));
	}
	private List<String> settingNameToLabel (List<String> options) {
		List<String> labels = new ArrayList<>();
		for (String option : options) {
			labels.add(settingNameToLabel (option));
		}
		return labels;
	}
	private boolean isSectionEnabled() {
		return localEnable.isLoadEnabled();
	}
	private boolean isSectionWritable() {
		return localEnable.isWriteEnabled();
	}
	private boolean hasValidSetting (String key) {
		if (key != null) {
			String Key = key.toUpperCase();
			if (settingMap.containsKey(Key)) {
				currentSetting = settingMap.get(Key);
				if (currentSetting.value().isBlank()) {
					return false;
				}
				if (currentSetting.value().isRandom()) {
					return true;
				}
				if (isBoolean) {
					return currentSetting.value().isBoolean();
				}
				if (isInteger) {
					return currentSetting.value().isValid(minValue, maxValue);
				}
				return currentSetting.value().isMemberOf(labelOptionsMap.keySet());
			}
		}
		return false;
	}
}

