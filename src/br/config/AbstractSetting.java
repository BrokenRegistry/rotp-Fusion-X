package br.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import br.config.comment.Comment;
import br.config.comment.CommentLine;

public abstract class AbstractSetting <T> {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
	static final String HEAD_OF_INFO    = new Comment("FIRST / LAST").toPrint();
	static final String HEAD_OF_OPTIONS = new Comment("OPTIONS").toPrint();
	static final String DEFAULT_LOCAL_ENABLE = "Both";
	static final String LOCAL_ENABLE_LABELS  = 
		new CommentLine(CfgField.capitalize(CfgField.ENABLE_VALID_LIST.toString())).toPrint();
    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private Comment headComments;
	private Comment settingComments;
	private Comment optionsComments;
	private Comment bottomComments;

	private CfgField settingKey = new CfgField();
	private CfgField lastValue  = new CfgField(); // Last saved value (User friendly)
	private String   lastOption = ""; // Last saved option (Computer friendly)
	private CfgField firstValue   = new CfgField(); // Value when firstly loaded (User friendly)
	private String   firstOption  = ""; // Option when firstly loaded (Computer friendly)
	private CfgField userOptionList = new CfgField();
	private CfgField localEnable = new CfgField(DEFAULT_LOCAL_ENABLE);
	
	private List<String> settingOptions; // (Computer friendly)
	private LinkedHashMap<String, CfgLine> settingMap;
	private CfgBlock userSettings;
	private LinkedHashMap<String, String> labelToOptionMap; // (User friendly) to (Computer friendly)
	private Integer minValue;
	private Integer maxValue;
	private Integer minRandom;
	private Integer maxRandom;
	private boolean settingIsInteger = false;
	private boolean settingIsBoolean = false;
	private CfgLine currentSetting;

	// ------------------------------------------------------------------------
	// Constructors and Helpers
	//
	/**
	 * Constructor for the setting Key
	 */
	private AbstractSetting(String key) {
		resetUserSettings();
		settingKey.set(key);
		initComments();
	}
	/**
	 * Constructor for String parameters without first Option
	 */
	public AbstractSetting(String settingKey, List<String> settingOptions) {
		this(settingKey);
		setSettingOptions(settingOptions);
		initComments();
	}
	/**
	 * Constructor for String parameters with first Option (Computer Friendly)
	 */
	public AbstractSetting(String settingKey, List<String> settingOptions, String initialOption) {
		this(settingKey, settingOptions);
		setFirstOption(initialOption);
		initComments();
	}
	/**
	 * Constructor for boolean parameters without first Option
	 */
	public AbstractSetting(String settingKey, boolean[] settingOptions) {
		this(settingKey);
		setSettingOptions(CfgField.BOOLEAN_LIST);
		initComments();
		settingIsBoolean = true;
	}
	/**
	 * Constructor for boolean parameters with first Option
	 */
	public AbstractSetting(String settingKey, boolean initialOption) {
		this(settingKey);
		setSettingOptions(CfgField.BOOLEAN_LIST);
		setFirstOption(CfgField.toYesNoString(initialOption));
		initComments();
		settingIsBoolean = true;
	}
	/**
	 * Constructor for boolean parameters with first Option
	 */
	public AbstractSetting(String settingKey, boolean[] settingOptions, boolean initialOption) {
		this(settingKey, initialOption);
	}
	/**
	 * Constructor for integer parameters without first Option
	 */
	public AbstractSetting(String settingKey, int min, int max, int minR, int maxR) {
		this(settingKey);
		setSettingOptions(min, max, minR, maxR);
		initComments();
		settingIsInteger = true;
	}
	/**
	 * Constructor for integer parameters without first Option
	 */
	public AbstractSetting(String settingKey, int min, int max, int minR, int maxR, int initialOption) {
		this(settingKey, min, max, minR, maxR);
		setSettingOptions(min, max, minR, maxR);
		setFirstOption(String.valueOf(initialOption));
		initComments();
	}
    public void resetUserSettings() {
    	settingMap = new LinkedHashMap<String, CfgLine>();
    	userSettings = new CfgBlock();
    }
	// ========================================================================
	// Abstract Methods
	//
	
	public abstract String getSelectedOption (T gameObject);
	public abstract void   setSelectedOption (T gameObject, String userOption);
	public abstract void   setSelectedOptionToInitial (T gameObject);
	public abstract void   initComments ();
	// ------------------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * Override the Game parameter with winning option 
	 */
    public void overrideGameParameters (T gameObject, LinkedHashSet<String> settingKeys) {
    	String userOption = "";
   		// Loop thru user Keys, last valid win
    	for (String userKey : settingKeys) {       
            if (isSectionReadable(userKey)) {
            	userOption = getOrDefaultOption(userKey, userOption);
            }
        }
    	// if one valid option is found: set it
    	if (!userOption.isBlank()) {
    		setSelectedOption(gameObject, userOption);
    	}
    }
	/**
	 * Return Valid option as String or Default if none 
	 */
    private String getOrDefaultOption(String key, String OnWrong) {
        if (key != null) {
        	CfgField cfgValue = getCfgValue(key);
        	if (cfgValue.isRandom()) { 
        		int[] rndParam = cfgValue.extractOrDefaultMinMaxRandomParameters(0, settingOptions.size()-1); // (defaultMin, defaultMax)
        		int rnd = CfgField.getIntegerRandom(rndParam);
        		return settingOptions.get(rnd);
        	}
        	return labelToOptionMap.getOrDefault(cfgValue.toKey(), OnWrong);
        }
        return OnWrong;
    }
	/**
	 * Set Initial Option (Computer friendly) 
	 */
	private void setFirstOption(String option) {
		firstOption = option;
		firstValue.set(settingNameToLabel(option).toUpperCase());
		setLastOption(option);
	}
	/**
	 * Set Last Option (Computer friendly) 
	 */
	public void setLastOption(T AllOptions) {
		setLastOption(getSelectedOption(AllOptions));
	}
	private void setLastOption(String option) {
		lastOption = option;
		lastValue.set(settingNameToLabel(lastOption).toUpperCase());
	}
	/**
	 * Return Setting's Key as CfgFields 
	 */
	public CfgField settingKey() {
		return settingKey;
	}
	/**
	 * Return first Value as CfgFields 
	 */
	protected CfgField firstValue() {
		return firstValue;
	}
	/**
	 * Return first Option as CfgFields 
	 */
	protected String firstOption() {
		return firstOption;
	}
	/**
	 * Return last Value as CfgFields 
	 */
	private CfgField lastValue() {
		return lastValue;
	}
	/**
	 * Return last Option as CfgFields 
	 */
	private String lastOption() {
		return lastOption;
	}
	/**
	 * Return userOptionList as CfgFields 
	 */
	private CfgField userOptionList() {
		return userOptionList;
	}
	/**
	 * Return localEnable as CfgFields 
	 */
	private CfgField localEnable() {
		return localEnable;
	}
	/**
	 * Return Valid option as String : Initial if none 
	 */
    private String getValidOption(String key) {
        if (key != null) {
            String value = getValidValue(key);
            if (!value.isBlank()) {
                return labelToOptionMap.get(value.toUpperCase());
            }
        }
        return firstOption();
    }
	/**
	 * Return Setting's Default Choice as String 
	 */
//	private String getDefaultValueAsString() {
//		return Presets.defaultValuesMap().get(settingKey().toKey());
//	}
	/**
	 * Return Setting's Default Choice as CfgField 
	 */
//	private CfgField getDefaultValue() {
//		return new CfgField(getDefaultValueAsString()) ;
//	}
	/**
	 * Return Iterator of settingMap
	 */
	public Iterable<CfgLine> getSettingMapIterable() {
		return settingMap.values();
	}
	/**
	 * Return Set of User Setting Keys
	 */
	public LinkedHashSet<String> getUserSettingKeySet () {
		LinkedHashSet<String> keyLHS = new LinkedHashSet<String>(settingMap.keySet());
		return keyLHS;
	}
	/**
	 * Return  Setting's selected User Choice as CfgLine 
	 */
	public CfgLine getCfgLine(String key) {
		if (key == null) { 
			key = "Initial";
		}
		if (settingMap.containsKey(key)) {
			return settingMap.get(key);
		}
		return new CfgLine(key, firstValue());
//		return userSettings.getOrDefaultLine(key, new CfgLine(key, initialValue()));
	}
	/**
	 * Return  Setting's selected User Choice as CfgField 
	 */
	private CfgField getCfgValue(String key) {
		if (key != null && settingMap.containsKey(key)) {
			return settingMap.get(key).value();
		}
		return firstValue();
//		return userSettings.getOrDefaultValue(key, initialValue());
	}
	/**
	 * Return Setting's selected User Choice as boolean 
	 */
	protected boolean getBooleanOption(String key) {
		boolean preset = firstValue().getOrDefault(false);
		CfgField setting = getCfgValue(key);
		if (setting.isRandom()) {
			return CfgField.getBooleanRandom();
		}
		return setting.getOrDefault(preset);
	}
	/**
	 * Return Setting's selected User Choice as integer 
	 */
	protected Integer getIntegerOption(String key) {
		Integer preset = firstValue().getOrDefault(0);
		CfgField setting = getCfgValue(key);
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
		String preset = firstValue().toString();
		CfgField cfgLine = getCfgValue(key);
		if (cfgLine.isRandom()) { 
			int[] rndParam = cfgLine.extractOrDefaultMinMaxRandomParameters(0, settingOptions.size()-1); // (defaultMin, defaultMax)
			int rnd = CfgField.getIntegerRandom(rndParam);
			String result = settingOptions.get(rnd);
			return settingNameToLabel(result);
		}
		return cfgLine.getOrDefault(preset);
	}
	private String getValidNonBlankValue(String key) {
		if (key != null) {
			String Key = key.toUpperCase();
			if (settingMap.containsKey(Key)) {
				String value = settingMap.get(Key).value().toString();
				if (labelToOptionMap.keySet().contains(value.toUpperCase())) {
					return value;
				}
			}
		}
		return firstValue().toString();
	}
	public void putCfgLine (CfgLine cfgLine) { 
		if (cfgLine == null || !cfgLine.hasKey()) {
			return; // No idea what to do with that
		}
		cfgLine.value().clean();
		// Test for local section special parameters
		if (cfgLine.key().isLocalEnableKey()) {
			setLocalEnable(cfgLine.value()); // To force a valid Value
			return;
		}
		settingMap.put(cfgLine.key().toKey(), cfgLine);
	}
	private void setLocalEnable(CfgField value) {
		value.removeComments();
		if (value.isValidEnable()) {
			localEnable.set(value);
			return;
		}
		localEnable.set(DEFAULT_LOCAL_ENABLE);
	}
	private void putCfgLine (KeyField key, CfgField value) {
		putCfgLine(new CfgLine(key, value));
	}
	private void setLastValue(String value) {
		lastValue.set(settingNameToLabel(value));
	}
	public void removeLocalEnable() {
		localEnable.set("");
	}
	private void setLastValue(boolean value) {
		lastValue.set(value);
	}
	private void setLastValue(Integer value) { 
		lastValue.set(value.toString());
	}
	protected void setSettingOptions(int min, int max, int minR, int maxR) {
		minValue  = min;
		maxValue  = max;
		minRandom = minR;
		maxRandom = maxR;
		setSettingOptions( List.of(
			"Rmin = " + String.valueOf(minR),
			"Rmax = " + String.valueOf(maxR),
			"Lmin = " + String.valueOf(min),
			"Lmax = " + String.valueOf(max)
			) );
		settingMap = new LinkedHashMap<String, CfgLine>();
	}
	/**
	 * Store the option list (Computer friendly)
	 * Create the Label List (User friendly)
	 * Build the labelToOptionMap (User friendly UpperCase) to (Computer friendly) 
	 */
	private void setSettingOptions(List<String> optionList) {
		settingOptions   = new ArrayList<String>();
		labelToOptionMap = new LinkedHashMap<String, String>();
		for (String option : optionList) {
			settingOptions.add(option);
			labelToOptionMap.put(settingNameToLabel(option).toUpperCase(), option);
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
		// HEAD COMMENTS
    	if (headComments != null && !headComments.isEmpty()) {
    		out += (headComments.toPrint() + System.lineSeparator());
    	}
    	// SETTING NAME
   		out += new CfgLine(KeyField.LABEL_OF_SECTION_KEY, settingKey)
				.toPrint() + System.lineSeparator();
   		// SETTING COMMENTS
    	if (settingComments != null && !settingComments.isEmpty()) {
    		out += (settingComments.toPrint() + System.lineSeparator());
    	}
    	// OPTIONS LIST
   		out += new CfgLine(HEAD_OF_OPTIONS, userOptionList)
				.toPrint() + System.lineSeparator();
   		// FIRST / LAST INFO
   		out += new CfgLine(HEAD_OF_INFO
   						, (settingNameToLabel(firstValue.toString())
   							+ " / "
   							+settingNameToLabel(lastValue.toString())
   						)
   					).toPrint() + System.lineSeparator();
   		// OPTIONS COMMENTS
		if (optionsComments != null && !optionsComments.isEmpty()) {
			out += (optionsComments.toPrint() + System.lineSeparator());
		}
   		// LOCAL ENABLE
   		if (!localEnable.isBlank()) {
	   		out += new CfgLine(KeyField.LABEL_OF_ENABLE_SECTION_KEY, localEnable).toPrint();
			out += "    " + LOCAL_ENABLE_LABELS + System.lineSeparator();
   		}
		// USER SETTINGS BLOCK
    	for (String option : groupOptions) {
    		if (!settingMap.containsKey(option.toUpperCase())) {
    			settingMap.put(option.toUpperCase(), 
    					new CfgLine(option, firstValue()));
    		}
    		out += (settingMap.get(option.toUpperCase()).toPrint() + System.lineSeparator());
    	}
    	// BOTTOM COMMENTS
    	if (bottomComments != null && !bottomComments.isEmpty()) { 
    		out += (bottomComments.toPrint() + System.lineSeparator()); 
    		}
    	out += System.lineSeparator();
    	return out;
	}
    /**
	 * Get the last in game option (Computer friendly)
	 */
//    void actionGetLastValue(Object allOptions) {
//    	String Option = getSelectedOption(allOptions);
//    	setLastOption(Option);
//    }
    /**
	 * Conditions to set user choice to last value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 */
	public void actionSave(String key) {
		if (isSectionWritable() || !settingMap.containsKey(key.toUpperCase())) {
			putCfgLine(new KeyField(key), lastValue());
		}
	}
	/**
	 * Conditions to set user choice to last value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 */
	public void actionUpdate(String key) {
		if ((isSectionWritable() 
				&& !settingMap.get(key.toUpperCase()).value().isBlank()
				) 
				|| !settingMap.containsKey(key.toUpperCase())) {
			putCfgLine(new KeyField(key), lastValue());
		}
	}
	/**
	 * Conditions to set user choice to first value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 */
	public void actionFirst(String key) {
		if ((isSectionWritable() 
				&& !settingMap.get(key.toUpperCase()).value().isBlank()
				) 
				|| !settingMap.containsKey(key.toUpperCase())) {
			putCfgLine(new KeyField(key), firstValue());
		}
	}
	protected void headComments(Comment comments) {
		headComments = comments;
	}
	protected void settingComments(Comment comments) { 
		settingComments = comments;
	}
	protected void optionsComments(Comment comments) { 
		optionsComments = comments;
	}
	protected void bottomComments(Comment comments) {
		bottomComments = comments; 
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
	public boolean isSectionReadable(String key) { 
			return (isSectionEnabled() & hasValidSetting (key));
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
				if (settingIsBoolean) {
					return currentSetting.value().isBoolean();
				}
				if (settingIsInteger) {
					return currentSetting.value().isValid(minValue, maxValue);
				}
				return currentSetting.value().isMemberOf(labelToOptionMap.keySet());
			}
		}
		return false;
	}
}

