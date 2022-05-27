
/*
 * Licensed under the GNU General License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	 https://www.gnu.org/licenses/gpl-3.0.html
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
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @param <ValueClass>  the Value's Code View Class
 * @param <ValidClass>  The Value's validation class
 * @param <ClientClass> The class that have to go thru the profile manager
 */
public abstract class Abstract_Parameter<
		ValueClass,
		ValidClass extends Abstract_ValidData<ValueClass>,
		ClientClass> extends WriteUtil {

	// ------------------------------------------------------------------------
	// Constant Properties
	//
	private static final String HEAD_OF_PARAMETER = "¦ Parameter";
	private static final String HEAD_OF_HISTORY	  = "¦ History";
	private static final String HDEF = " : ";
	private static final String HSEP = " / ";
	private static final String HEAD_OF_OPTIONS = new Line_String(
								toComment("Options"), "", null).toString();
	private static final String OPTIONS_NEWLINE   = new Line_String(
								toComment("  \" \" "), "", null).toString();
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
	//	private Abstract_ValidData<?>	  valueValidation;
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
	public void changeGameFileParameters(
 		ClientClass clientObject, List<String> profileNames) {
	 	ValueClass value = getWinningCodeView (profileNames);
	 	// if one valid code View is found: set it
	 	if (value != null) {
	 		putToGame(clientObject, value);
	 	}
	}

	private void overrideGuiParameters(
	 		ClientClass clientObject, ValueClass value) {
	 	// if one valid value is found: set it
	 	if (!PMutil.neverNull(value).isBlank()) {
	 		putToGUI(clientObject, value);
	 	}
	}

	/**
	 * Search for the winning Value and
	 * Override the GUI parameter with it 
	 * @param clientObject   the {@code ClientClass Object}
	 * @param profileNames  List of names to check
	 */
	public void overrideGuiParameters(
	 		ClientClass clientObject, List<String> profileNames) {
		overrideGuiParameters(clientObject, getWinningCodeView (profileNames));
	}

	protected void putHistoryToGUI(History history, ClientClass clientObject) {
		overrideGuiParameters (clientObject, getHistoryCodeView(history));
	}

	/**
	 * Set "history" Code View
	 * @param value the new value
	 */
	protected void setHistoryCodeView(History history, ValueClass newValue) {
		dataValidation.setHistoryCodeView(history, newValue);
	}

	/**
	 * Set "history" User View
	 * @param value the new value
	 */
	protected void setHistoryUserView(History history, String newValue) {
		dataValidation.setHistoryUserView(history, newValue);
	}
	
	/**
	 * Get "history" Code View
	 * @return The "history" Code View
	 */
	protected ValueClass getHistoryCodeView(History history) {
		return dataValidation.getHistoryCodeView(history);
	}

	/**
	 * Get "history" User View
	 * @return The "history" User View
	 */
	protected String getHistoryUserView(History history) {
		return dataValidation.getHistoryUserView(history);
	}

	/**
	 * Conditions to set user choice to "history" value:
	 *	- if the key is absent: 
	 *	- if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param history the target Field
	 * @param profile the profile to set
	 */
	public void actionToFile(History history, String profile) {
			actionToFile(profile, dataValidation.getHistoryUserView(history));
	}

	/**
	 * Conditions to set user choice to "history" value:
	 *	- if the key is absent: 
	 *	- if Writing is allowed and value not blank:
	 * Add profile if none
	 * @param history the target Field
	 * @param profile the profile to set
	 */
	public void actionUpdateFile(History history, String profile) {
			actionUpdateFile(profile, dataValidation.getHistoryUserView(history));
	}

	/**
	 * Set Limits Value
	 * @param value the new values
	 */
	protected void setLimits(ValueClass Limit1, ValueClass Limit2) {
		dataValidation.setLimits(Limit1, Limit2);
	}

	/**
	 * Set Default Random Limits Value
	 * @param value the new values
	 */
	protected void setDefaultRandomLimits(ValueClass Limit1, ValueClass Limit2) {
		dataValidation.setDefaultRandomLimits(Limit1, Limit2);
	}

	/**
	 * Update Last GUI code View 
	 * @param clientObject   the {@code ClientClass Object}
 	 */
	public void setGuiCodeView(ClientClass clientObject) {
		setGuiValue(getFromUI(clientObject));
	}
	
	private void setGuiValue(ValueClass value) {
		dataValidation.setHistoryCodeView(Current, value);
	}
	
	/**
	 * Update Last Game CodeView (Computer friendly) 
	 * @param clientObject   the {@code ClientClass Object}
 	 */
	public void setGameCodeView(ClientClass clientObject) {
		setGameValue(getFromGame(clientObject));
	}

	private void setGameValue(ValueClass value) {
		dataValidation.setHistoryCodeView(Game, value);
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
				.setValue(getHistoryCodeView(Initial));
	}

	// for default parameters and internal use
	void addLine (String name, String value) {
		userProfiles.add(name, value);
	}

	// for default parameters and internal use
	void addLine (String name, String value, String comment) {
		userProfiles.add(name, value, comment);
	}

	void addLine (String newLine) { // from config files
		if (localEnable.isLineForMe(newLine)) {
			return; // the line has been taken
		}
		if (isHistory(newLine)) {
			return; // the line has been taken
		}
		userProfiles.add(newLine);
	}

	/**
	 * Find "Current" value and assign to "Last" 
	 * @param line the {@code String to process}
	 * @return isHistory?
	 */
	private boolean isHistory(String line) {
		if (HEAD_OF_HISTORY.equalsIgnoreCase(Generic_Line.getKey(line))) {
			for (String element : 
					Generic_Line.getValueAsString(line).split(HSEP)) {
				String[] param = element.split(HDEF);
				if (param[0].strip().equalsIgnoreCase(Current.toString())) {
					if (param.length >= 2) {
						setHistoryUserView(Last, param[1].strip());
						return true;
					}
					return true;
				}
				// loop
			}
			return true;
		}
		return false;
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
		out += multiLines(dataValidation.getOptionsRange(),
				HEAD_OF_OPTIONS, OPTIONS_NEWLINE) + NL;

		// OPTIONS DESCRIPTION
		out += toCommentLine(dataValidation.getOptionsDescription(), 1, 1);
		
		// OPTIONS COMMENTS
		out += toCommentLine(optionsComments) ;

		// FIRST / LAST INFO
		if (!localEnable.isBlankValue()) {
			out += new Line_String(HEAD_OF_HISTORY,
					Current.toString() + HDEF
					+ getHistoryUserView(Current)
					+ HSEP
					+ Last.toString() + HDEF
					+ getHistoryUserView(Last)
					+ HSEP
					+ Initial.toString() + HDEF
					+ getHistoryUserView(Initial)
					+ HSEP
					+ Default.toString() + HDEF
					+ getHistoryUserView(Default)
					+ HSEP
					+ Game.toString() + HDEF
					+ getHistoryUserView(Game),
					(String)null)
					.toString() + NL;
		}

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
	 *	 - if Writing is allowed 
	 *	 - if the user choice is absent
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
	 *	- if the key is absent: 
	 *	- if Writing is allowed and value not blank:
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
