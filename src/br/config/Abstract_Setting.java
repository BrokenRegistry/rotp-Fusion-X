
/*
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.config;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class Abstract_Setting <GuiObject, SaveObject> extends Cfg_BlockValidation {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
	static final String 
	HEAD_OF_INFO = new Comment("Initial/GUI/Game").toPrint();
	static final String 
	HEAD_OF_OPTIONS = new Comment("OPTIONS").toPrint();
	static final String 
	DEFAULT_LOCAL_ENABLE = "ALL";
	static final String 
	LOCAL_ENABLE_LABELS = new CommentLine(Cfg_Util.capitalize(
			Cfg_Util.ENABLE_VALID_LIST.toString())).toPrint();
	protected static final String 
	AVAILABLE_FOR_CHANGE = "---- Available for changes in game saves";
    public static final String 
    LABEL_OF_SECTION_KEY   = "¦ SETTING";
	public static final String
	LABEL_OF_ENABLE_SECTION_KEY = "¦ LOCAL ENABLE";
	// ------------------------------------------------------------------------
	// Variables Properties
    //
	private Comment headComments;
	private Comment settingComments;
	private Comment optionsComments;
	private Comment bottomComments;

	private CfgLine_LocalEnable localEnable = new CfgLine_LocalEnable();
	private Cfg_Entry settingID;
	private String   guiValueAsLabel;
	private String   gameValueAsLabel;
	private String   initialValueAsLabel;
	// ------------------------------------------------------------------------
	// Constructors and Helpers
	//
	/**
	 * Constructor for the setting settingID
	 */
	protected Abstract_Setting(String settingID
				, Cfg_ValidationData settingOptions) {
		super(settingOptions);
		this.settingID.set(settingID);
		initComments();
		// TODO
	}
	protected Abstract_Setting(String settingID
				, Cfg_ValidationData settingOptions
				, String initialOption) {
		super(settingOptions);
		this.settingID.set(settingID);
		setInitialOption(initialOption);
		initComments();
		// TODO
	}
	protected void resetUserSettings() {
    	super.resetUserSettings();
    }
	// ========================================================================
	// Abstract Methods
	//
	public abstract String getFromUI (GuiObject guiObject);
	public abstract void   putToGUI (GuiObject guiObject, String userOption);
	public abstract String getFromGame (GuiObject guiObject);
	public abstract void   putToGame (SaveObject saveObject, String userOption);
	public abstract void   putInitialToGUI (GuiObject guiObject);
	public abstract void   initComments ();
	// ------------------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * Override the Game File parameter with winning option 
	 * @param labelList List of user labels to search for
	 */
    public void changeGameFileParameters (SaveObject saveObject, List<String> labelList) {
    	String userOption = "";
   		// Loop thru user labels, last valid win
    	for (String userLabel : labelList) {       
            if (isSectionReadable(userLabel)) {
            	userOption = getOrDefaultOption(userLabel, userOption);
            }
        }
    	// if one valid option is found: set it
    	if (!userOption.isBlank()) {
    		putToGame(saveObject, userOption);
    	}
    }
	/**
	 * Override the GUI parameter with winning option 
	 * @param labelList List of user labels to search for
	 */
    public void overrideGuiParameters (GuiObject guiObject, List<String> labelList) {
    	String userOption = "";
   		// Loop thru user labels, last valid win
    	for (String userLabel : labelList) {       
            if (isSectionReadable(userLabel)) {
            	userOption = getOrDefaultOption(userLabel, userOption);
            }
        }
    	// if one valid option is found: set it
    	if (!userOption.isBlank()) {
    		putToGUI(guiObject, userOption);
    	}
    }
	/**
	 * @param userLabel user setting ID
	 * @param OnWrong Default String to return if option not valid
	 * @return Valid option as String or OnWrong if none 
	 */
    private String getOrDefaultOption(String userLabel, String OnWrong) {
        if (userLabel != null) {
        	String option = getStringOption(userLabel);
        	if (option != null && !option.isBlank()) {
        		return option;
        	}
        }
        return OnWrong;
    }
	/**
	 * Set Initial Option
	 * @param option Computer friendly String
	 */
	private void setInitialOption(String option) {
		initialValueAsLabel = validationData().getLabel(option);
		setGuiOption(option);
	}
	/**
	 * Update Last GUI Option (Computer friendly) 
	 */
	public void setGuiOption(GuiObject guiObject) {
		setGuiOption(getFromUI(guiObject));
	}
	private void setGuiOption(String option) {
		guiValueAsLabel = validationData().getLabel(option);
	}
	/**
	 * Update Last Game Option (Computer friendly) 
	 */
	public void setGameOption(GuiObject guiObject) {
		setGameOption(getFromGame(guiObject));
	}
	private void setGameOption(String option) {
		gameValueAsLabel = validationData().getLabel(option);
	}
	/**
	 * Return Setting's Key as CfgEntrys 
	 */
	public Cfg_Entry settingID() {
		return settingID;
	}
	/**
	 * @return Initial Value as Label (User friendly)
	 */
	protected Cfg_Entry initialValue() {
		return new Cfg_Entry(initialValueAsLabel);
	}
	/**
	 * @return Initial value as Option (Computer friendly) 
	 */
	protected String initialOption() {
		return validationData().getOption(initialValueAsLabel);
	}
	/**
	 * @return last GUI Value as Label 
	 */
	private Cfg_Entry guiValue() {
		return new Cfg_Entry(guiValueAsLabel);
	}
	/**
	 * @return last Game Value as Label
	 */
	private Cfg_Entry gameValue() {
		return new Cfg_Entry(gameValueAsLabel);
	}
	/**
	 * @param label user Label of choice
	 * @return valid Setting as Boolean or null if not initialized 
	 */
	protected Boolean getValidBooleanOption(String label) {
		Boolean setting = getBooleanOption(label);
		if (setting == null) {
			return Cfg_Util.toBoolean(initialOption());
		}
		return setting;
	}
	/**
	 * @param label user Label of choice
	 * @return valid Setting as String  or null if not initialized
	 */
	protected String getValidStringOption(String label) {
		String setting = getStringOption(label);
		if (setting == null) {
			return initialOption();
		}
		return setting;
	}
	/**
	 * @param label user Label of choice
	 * @return valid Setting as Numeric or null if not initialized
	 */
	protected Cfg_Value getValidNumericOption(String label) {
		Cfg_Value setting = getNumericOption(label);
		if (setting == null) {
			return new Cfg_Value(initialOption());
		}
		return setting;
	}
	/**
	 * @param label user Label of choice
	 * @return true if user choice is blank
	 */
	protected Boolean isBlankValue(String label) {
		return super.isBlankValue(label);
	}
	private void setLocalEnable(String value) {
		localEnable.newLine(value);
	}
	public void removeLocalEnable() {
		localEnable.setValue("");;
	}
	// ------------------------------------------------------------------------
	// Other Methods
	//
	/**
	 * @return sections as String, ready to be printed
	 */
	public String toPrint(List<String> userLabels) {
		// TODO Manage Verbose
		String out = "";
		// HEAD COMMENTS
    	if (headComments != null && !headComments.isEmpty()) {
    		out += (headComments.toPrint() + System.lineSeparator());
    	}
    	// SETTING NAME
   		out += new Cfg_LineValidation(LABEL_OF_SECTION_KEY, settingID.toCapitalized())
				.toPrint() + System.lineSeparator();
   		// SETTING COMMENTS
    	if (settingComments != null && !settingComments.isEmpty()) {
    		out += (settingComments.toPrint() + System.lineSeparator());
    	}
    	// OPTIONS LIST
   		out += new Cfg_LineValidation(HEAD_OF_OPTIONS, validationData().toString())
				.toPrint() + System.lineSeparator();
   		// FIRST / LAST INFO
   		out += new Cfg_LineValidation(HEAD_OF_INFO
   					, initialValue() + " / " + guiValue() + " / " + gameValue()
   					).toPrint() + System.lineSeparator();
   		// OPTIONS COMMENTS
		if (optionsComments != null && !optionsComments.isEmpty()) {
			out += (optionsComments.toPrint() + System.lineSeparator());
		}
   		// LOCAL ENABLE
   		if (localEnable != null) {
	   		out += localEnable.toPrint();
   		}
		// USER SETTINGS BLOCK
   		out += super.toPrint(userLabels);
    	// BOTTOM COMMENTS
    	if (bottomComments != null && !bottomComments.isEmpty()) { 
    		out += (bottomComments.toPrint() + System.lineSeparator()); 
    		}
    	out += System.lineSeparator();
    	return out;
	}
    /**
	 * Conditions to set user choice to value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add key if none
	 */
	private void actionToFile(String label, Cfg_Entry value) {
		if (isSectionWritable()) {
			put(label, value);
		}
		addLabelIfNone(label);
	}
	/**
	 * Conditions to set user choice to value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add key if none
	 */	private void actionUpdateFile(String label, Cfg_Entry value) {
		 addLabelIfNone(label);
			if (isSectionWritable() && !isBlankValue(label)) {
				put(label, value);
			}
	}
    /**
	 * Conditions to set user choice to last value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add key if none
	 */
	public void actionUiToFile(String key) {
		actionToFile(key, guiValue());
	}
    /**
	 * Conditions to set user choice to game value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add key if none
	 */
	public void actionGameToFile(String key) {
		actionToFile(key, gameValue());
	}
	/**
	 * Conditions to set user choice to first value:
	 *    - if Writing is allowed 
	 *    - if the user choice is absent
	 * Add key if none
	 */
	public void actionInitialToFile(String key) {
		actionToFile(key, initialValue());
	}
	/**
	 * Conditions to set user choice to last value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add key if none
	 */
	public void actionUiUpdateFile(String key) {
		actionUpdateFile(key, guiValue());
	}
	/**
	 * Conditions to set user choice to first value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add key if none
	 */
	public void actionGameUpdateFile(String key) {
		actionUpdateFile(key, gameValue());
	}
	/**
	 * Conditions to set user choice to first value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add key if none
	 */
	public void actionInitialUpdateFile(String key) {
			actionUpdateFile(key, initialValue());
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

	public boolean isSectionEnabled() {
		return localEnable.isLoadEnabled();
	}
	public boolean isSectionReadable(String label) { 
			return (isSectionEnabled() & hasValidSetting (label));
	}
	private boolean isSectionWritable() {
		return localEnable.isWriteEnabled();
	}
	private boolean hasValidSetting (String label) {
		return isValid(label);
	}
}

