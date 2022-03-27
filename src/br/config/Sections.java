package br.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import br.config.comment.Comment;
import rotp.ui.util.cfg.Presets;

public class Sections {
	static final String HEAD_OF_INFO    = "# DEFAULT / LAST";
	static final String HEAD_OF_OPTIONS = "# OPTIONS";
	static final String HEAD_OF_LAST    = "# LAST";
	private Comment headComments;
	private CfgLine settingKey  = new CfgLine(CfgField.LABEL_OF_SECTION_KEY, "");
	private CfgLine localEnable = new CfgLine(CfgField.LABEL_OF_ENABLE_SECTION_KEY, "Both");
	private CfgLine optionsList = new CfgLine(HEAD_OF_OPTIONS, "");
	private CfgLine lastValue   = new CfgLine(HEAD_OF_LAST, "");
	private Comment settingComments;
	private List<String> settingOptions;
	private Comment optionsComments;
	private LinkedHashMap<String, CfgLine> settingMap;
	private Comment bottomComments;
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
	public Sections(String key, String defaultValue, List<String> settingOptions) { // String Value
		settingKey.setRightSide(key);
		setSettingOptions(settingOptions);
		settingMap = new LinkedHashMap<String, CfgLine>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, defaultValue);
		}
	}
	public Sections(String key, boolean defaultValue) { // Boolean Value
		settingKey.setRightSide(key);
		setSettingOptions(CfgField.BOOLEAN_LIST);
		settingMap = new LinkedHashMap<String, CfgLine>();
		if (Presets.firstLoad()) {
			Presets.defaultValuesMap().put(key, CfgField.toYesNoString(defaultValue));
		}
		isBoolean  = true;
	}
	public Sections(String key, Integer defaultValue, int min, int max, int minR, int maxR) { // Integer Value
		settingKey.setRightSide(key);
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
	 * Return Setting's Key as String 
	 */
	String getKeyAsString() {
		return settingKey.rightSide().toString();
	}
	/**
	 * Return Setting's Key as CfgFields 
	 */
	CfgField getKey() {
		return settingKey.rightSide();
	}
	/**
	 * Return Setting's Default Choice as String 
	 */
	public String getDefaultValueAsString() {
		return Presets.defaultValuesMap().get(getKey().toKey());
	}
	/**
	 * Return Setting's Default Choice as CfgField 
	 */
	public CfgField getDefaultValue() {
		return new CfgField(getDefaultValueAsString()) ;
	}
	/**
	 * Return Setting's Last Choice as String 
	 */
	String getLastValueAsString() {
		return lastValue.rightSide().toString();
	}
	/**
	 * Return Setting's Last Choice as CfgField 
	 */
	CfgField getLastValue() {
		return lastValue.rightSide();
	}
	public LinkedHashSet<String> getGroupKeySet () {
		return new LinkedHashSet<String>(settingMap.keySet());
	}
	/**
	 * Return Setting's raw selected User Choice as String 
	 */
	public String getCfgLineAsString(String key) {
		return getCfgLine(key).toString();
	}
	/**
	 * Return  Setting's selected User Choice as CfgField 
	 */
	public CfgField getCfgLine(String key) {
		if (key != null && settingMap.containsKey(key)) {
			return settingMap.get(key).rightSide();
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
				String value = settingMap.get(Key).rightSide().toString();
				if (labelOptionsMap.keySet().contains(value.toUpperCase())) {
					return value;
				}
			}
		}
		return getDefaultValueAsString();
	}
	public void setCfgLine (CfgLine pair) { 
		setCfgLine(pair.leftSide(), pair.rightSide());
	}
	private void setCfgLine (CfgField key, CfgField value) {
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
	public void    setLastValue(String value) {
		lastValue.setRightSide(settingNameToLabel(value));
	}
	public void    removeLocalEnable() { localEnable = null; }
	public void setLastValue(boolean value) {
		lastValue.setRightSide(value);
	}
	public void setLastValue(Integer value) { 
		lastValue.setRightSide(value.toString());
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
		optionsList.setRightSide(settingNameToLabel(settingOptions).toString());
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
    	if (settingKey != null && settingKey.hasLeftSide()) {
    		out += (settingKey.toPrint() + System.lineSeparator());
    	}
    	if (localEnable != null && localEnable.hasLeftSide()) {
    		out += (localEnable.toPrint() + System.lineSeparator());
    	}
    	if (settingComments != null && !settingComments.isEmpty()) {
    		out += (settingComments.toPrint() + System.lineSeparator());
    	}
    	if (optionsList != null && optionsList.hasLeftSide()) {
    		out += (optionsList.toPrint() + System.lineSeparator());
    	}
		if (lastValue != null && lastValue.hasLeftSide()) {
			out += (String.format(CfgLine.KEY_FORMAT, HEAD_OF_INFO) +
					settingNameToLabel(getDefaultValueAsString()) + " / " +
					settingNameToLabel(lastValue.rightSide().toString()) +
					System.lineSeparator());
		}
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
	public void actionSave(String key) {
		// Section should be writable to overwrite with last value
		if (isSectionWritable()) setCfgLine(new CfgField(key), getLastValue());
	}
	public void actionUpdate(String key) {
		// if the key is absent, add it with last value
		if (!settingMap.containsKey(key.toUpperCase())) {
			setCfgLine(new CfgField(key), getLastValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() 
			&& !settingMap.get(key.toUpperCase()).rightSide().isBlank())
				settingMap.get(key).setRightSide(getLastValue());
	}
	public void actionDefault(String key) {
		// if the key is absent, add it with default value
		if (!settingMap.containsKey(key.toUpperCase())) {
			setCfgLine(new CfgField(key), getDefaultValue());
			return;
		}
		// Section should be writable and value not blank
		if (isSectionWritable() 
			&& !settingMap.get(key).rightSide().isBlank())
				settingMap.get(key).setRightSide(getDefaultValue());
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
		return localEnable.rightSide().isLoadEnabled();
	}
	private boolean isSectionWritable() {
		return localEnable.rightSide().isWriteEnabled();
	}
	private boolean hasValidSetting (String key) {
		if (key != null) {
			String Key = key.toUpperCase();
			if (settingMap.containsKey(Key)) {
				currentSetting = settingMap.get(Key);
				if (currentSetting.rightSide().isBlank()) {
					return false;
				}
				if (currentSetting.rightSide().isRandom()) {
					return true;
				}
				if (isBoolean) {
					return currentSetting.rightSide().isBoolean();
				}
				if (isInteger) {
					return currentSetting.rightSide().isValid(minValue, maxValue);
				}
				return currentSetting.rightSide().isMemberOf(labelOptionsMap.keySet());
			}
		}
		return false;
	}
}

