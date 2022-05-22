
/*
 * Licensed under the GNU General License, Version 3 (the "License");
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

package br.profileManager.src.main.java;

import java.util.List;

import br.profileManager.src.main.java.Valid_LocalEnable.Line_LocalEnable;
import br.profileManager.src.main.java.Valid_String.Line_String;

/**
 * @param <ValueClass>  the Value's Code View Class
 * @param <ValidClass>  The Value's validation class
 * @param <ClientClass> The class that have to go thru the profile manager
 */
public abstract class Abstract_Parameter<
		ValueClass,
		ValidClass extends Abstract_ValidData<ValueClass>,
		ClientClass> extends ToPrint {

    // ------------------------------------------------------------------------
	// Constant Properties
    //
	static final String HEAD_OF_PARAMETER = "¦ PARAMETER";
	static final String HEAD_OF_INFO      = toComment("Initial/GUI/Game");
	static final String HEAD_OF_OPTIONS   = toComment("OPTIONS");
	static final String DEFAULT_LOCAL_ENABLE = "Both";
	protected static final String AVAILABLE_FOR_CHANGE = "---- Available for changes in game saves";

	// ------------------------------------------------------------------------
	// Variables Properties
    //
	private String headComments;
	private String settingComments;
	private String optionsComments;
	private String bottomComments;
	
	private String parameterName;
	private Line_LocalEnable localEnable = new Line_LocalEnable();
	private Abstract_ValidData<ValueClass> dataValidation;
	private Generic_Block<ValueClass, ValidClass> userProfiles;
	// from Generic_Block
	//	private List<Gen_Line<ValidClass>> lineList;
	//	private Abstract_ValidData<?>      valueValidation;
	//	private Abstract_ValidData<String> profileValidation;
	
	// ==================================================
    // Constructors and helpers
    //
	protected Abstract_Parameter(String parameterName,
				  Abstract_ValidData<ValueClass> valueValidationData) {
		setParameterName(parameterName);
		dataValidation = valueValidationData;
		resetUserProfiles();
		initComments ();
	}
	
    void resetUserProfiles() {
    	userProfiles = new Generic_Block<ValueClass, ValidClass>(dataValidation);
    }

	// ========================================================================
	// Abstract Methods
	//
	
    protected abstract ValueClass getFromUI (ClientClass clientObject);

    protected abstract void putToGUI (ClientClass clientObject, ValueClass value);

    protected abstract ValueClass getFromGame (ClientClass clientObject);

    protected abstract void putToGame (ClientClass clientObject, ValueClass value);

    protected abstract void putInitialToGUI (ClientClass clientObject);

    protected abstract void initComments ();

	// ------------------------------------------------------------------------
	// Getters and Setters
	//
    /**
     * @return the value validation class
     */
    public Abstract_ValidData<ValueClass> getDataValidation() {
    	return dataValidation;
    }
    
	/**
     * Search for the winning code View
     * @param profileNames  List of names to check
     * @return The Value, if one.
	 */
    private ValueClass getWinningCodeView (List<String> profileNames) {
    	ValueClass value = null;
    	if (localEnable.isLoadEnabled()) {
       		// Loop thru profiles, last valid win
        	for (String profile : profileNames) {       
        		value = userProfiles.getValue(profile, value);
            }
     	}
		return value;
    }

    /**
     * Search for the winning code View and
	 * Override the Game File parameter with it
     * @param clientObject   the {@code ClientClass Object}
     * @param profileNames  List of names to check
	 */
    public void changeGameFileParameters (
    		ClientClass clientObject, List<String> profileNames) {
    	ValueClass value = getWinningCodeView (profileNames);
    	// if one valid code View is found: set it
    	if (value != null) {
    		putToGame(clientObject, value);
    	}
    }

    /**
     * Search for the winning Value and
	 * Override the GUI parameter with it 
     * @param clientObject   the {@code ClientClass Object}
     * @param profileNames  List of names to check
	 */
    public void overrideGuiParameters (
    		ClientClass clientObject, List<String> profileNames) {
    	ValueClass value = getWinningCodeView (profileNames);
    	// if one valid value is found: set it
    	if (value != null) {
    		putToGUI(clientObject, value);
    	}
    }
    
    /**
	 * Set Initial Value
	 * @param value the new value
	 */
	protected void setInitialValue(ValueClass value) {
		dataValidation.setInitialValue(value);
		setGuiValue(value);
	}

	/**
	 * Set Limits Value
	 * @param value the new values
	 */
	protected void setLimits(ValueClass[] value) {
		dataValidation.setLimits(value);
	}

	/**
	 * Set Default Random Limits Value
	 * @param value the new values
	 */
	protected void setDefaultRandomLimits(ValueClass[] value) {
		dataValidation.setDefaultRandomLimits(value);
	}

	/**
	 * Update Last GUI code View 
     * @param clientObject   the {@code ClientClass Object}
 	 */
	public void setGuiCodeView(ClientClass clientObject) {
		setGuiValue(getFromUI(clientObject));
	}
	
	private void setGuiValue(ValueClass value) {
		dataValidation.setLastValue(value);
	}
	
	/**
	 * Update Last Game CodeView (Computer friendly) 
     * @param clientObject   the {@code ClientClass Object}
 	 */
	public void setGameCodeView(ClientClass clientObject) {
		setGameValue(getFromGame(clientObject));
	}

	private void setGameValue(ValueClass value) {
		dataValidation.setGameValue(value);
	}

	/**
	 * @return the Parameter's Name 
	 */
	public String getParameterName() {
		return parameterName;
	}
	
	/**
	 * @param name  the Parameter's Name 
	 */
	private void setParameterName(String name) {
		parameterName = name;
	}
	
	/**
	 * @return first User View 
	 */
	protected String getInitialUserView() {
		return dataValidation.toUserView(getInitialCodeView());
	}
	
	/**
	 * @return first Code View 
	 */
	protected ValueClass getInitialCodeView() {
		return dataValidation.getInitialValue();
	}

	/**
	 * @return GUI User View 
	 */
	protected String getGuiUserView() {
		return dataValidation.toUserView(getGuiCodeView());
	}
	
	/**
	 * @return last GUI Code View 
	 */
	protected ValueClass getGuiCodeView() {
		return dataValidation.getLastValue();
	}

	/**
	 * @return first User View 
	 */
	protected String getGameUserView() {
		return dataValidation.toUserView(getGameValue());
	}
	
	/**
	 * @return last Game Code View 
	 */
	protected ValueClass getGameValue() {
		return dataValidation.getGameValue();
	}
	
	/**
	 * @return Full Profiles list
	 */
	public List<String> getProfileList() {
		return userProfiles.getProfileList();
	}

	/**
	 * Get profile list from for the given category
	 * @param category the {@code String} category to filter with
	 * @return Filtered Profile list
	 */
	public List<String> getProfileListForCategory(String category) {
		return userProfiles.getProfileListForCategory(category);
	}

	/**
	 * Ask for profile line, or initial
	 * @return  selected Profile as Gen_Line
	 * @param   profile the profile name 
	 */
	public ValueClass getProfileValue(String profile) {
		return getProfileLine(profile).getValue();
	}

	/**
	 * Ask for profile line, or initial
	 * @return  selected Profile as Gen_Line
	 * @param   profile the profile name 
	 */
	private Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> getProfileLine(String profile) {
		if (profile != null) { 
			if (userProfiles.isValid(profile)) {
				return userProfiles.getLine(profile);
			}
		}
		profile = "Initial";
		return new Generic_Line<ValueClass, Abstract_ValidData<ValueClass>>(
				dataValidation)
				.setName(profile)
				.setValue(getInitialCodeView());
	}

	private void addLine (String name, String value) {
		userProfiles.add(name, value);
	}

	void addLine (String newline) {
		userProfiles.add(newline);
	}

	/**
	 * Remove the Local Enable parameter where possibly wrongly added 
	 */
	public void removeLocalEnable() {
		localEnable.setValue("");
	}

	/**
	 * Get the Local Enable loading state 
	 * @return loading status
	 */
	public Boolean isLoadEnabled() {
		return localEnable.isLoadEnabled();
	}

	/**
	 * Get the Local Enable writing state 
	 * @return writing status
	 */
	public Boolean isWriteEnabled() {
		return localEnable.isWriteEnabled();
	}

	// ==========================================================
	// Other Methods
	//
	/**
	 * @param groupCodeViews 
	 * @return parameter as String, ready to be printed
	 */
	public String toString(List<String> groupCodeViews) {
		String out = "";

		// HEAD COMMENTS
		out += toCommentLine(headComments) ;

		// SETTING NAME
   		out += new Line_String(HEAD_OF_PARAMETER, parameterName, (String)null)
				.toString() + NL;

		// SETTING COMMENTS
		out += toCommentLine(settingComments) ;

		// OPTIONS LIST
   		out += new Line_String(HEAD_OF_OPTIONS, 
   								dataValidation.toString(),
   								(String)null)
				.toString() + NL;

		// FIRST / LAST INFO
   		out += new Line_String(HEAD_OF_INFO,
   				getInitialUserView()
   					+ " / "
   					+ getGuiUserView()
   					+ " / "
   					+ getGameUserView(),
   				(String)null)
				.toString() + NL;

		// OPTIONS COMMENTS
		out += toCommentLine(optionsComments) ;

		// LOCAL ENABLE
   		if (!localEnable.isBlankValue()) {
	   		out += localEnable.toString() + NL;
   		}

   		// USER SETTINGS BLOCK
   		out += userProfiles.toString(groupCodeViews) + NL;

		// BOTTOM COMMENTS
		out += toCommentLine(bottomComments) ;

		out += NL;
    	return out;
	}
	
	private void addProfileIfNone(String profile) {
		userProfiles.addMissing(profile);
	}

  /**
	 * Conditions to set user choice to value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add profile if none
	 * @param profile the profile to set
	 * @param value  the value to set
	 */
	private void actionToFile(String profile, String value) {
		if (localEnable.isWriteEnabled()) {
			addLine(profile, value);
		}
	}

	/**
	 * Conditions to set user choice to value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param profile the profile to set
	 * @param value  the value to set
	 */	
	private void actionUpdateFile(String profile, String value) {
		if (localEnable.isWriteEnabled()) {
			addProfileIfNone(profile);
			if (!userProfiles.isBlankValue(profile)) {
				addLine(profile, value);
			}
		}
	}

	/**
	 * Conditions to set user choice to last value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionUiToFile(String profile) {
		actionToFile(profile, getGuiUserView());
	}

	/**
	 * Conditions to set user choice to game value:
	 *     - if Writing is allowed 
	 *     - if the user choice is absent
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionGameToFile(String profile) {
		actionToFile(profile, getGameUserView());
	}

	/**
	 * Conditions to set user choice to first value:
	 *    - if Writing is allowed 
	 *    - if the user choice is absent
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionInitialToFile(String profile) {
		actionToFile(profile, getInitialUserView());
	}

	/**
	 * Conditions to set user choice to last value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionUiUpdateFile(String profile) {
		actionUpdateFile(profile,  getGuiUserView());
	}
	
	/**
	 * Conditions to set user choice to first value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionGameUpdateFile(String profile) {
		actionUpdateFile(profile, getGameUserView());
	}

	/**
	 * Conditions to set user choice to first value:
	 *    - if the key is absent: 
	 *    - if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param profile the profile to set
	 */
	public void actionInitialUpdateFile(String profile) {
			actionUpdateFile(profile, getInitialUserView());
	}
	
	protected void setHeadComments(String comments) {
		headComments = comments;
	}

	protected void setSettingComments(String comments) { 
		settingComments = comments;
	}

	protected void setOptionsComments(String comments) { 
		optionsComments = comments;
	}

	protected void setBottomComments(String comments) {
		bottomComments = comments; 
	}

	// ==================================================
    // Static Methods
    //
	/**
	 * Test if the {@code String} announce a new parameter section
	 * @param key the {@code String} to analyze
	 * @return {@code Boolean} <b>true</b> if new parameter section, never null
	 */
	static boolean isHeadOfParameter(String key) {
		key = PMutil.clean(key);
		return key.equalsIgnoreCase(HEAD_OF_PARAMETER);
	}
}
