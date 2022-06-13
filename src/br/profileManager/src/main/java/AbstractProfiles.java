
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

import static br.profileManager.src.main.java.Valid_ProfileAction.*;
import static br.profileManager.src.main.java.WriteUtil.History.*;
import static br.profileManager.src.main.java.PMutil.containsIgnoreCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @param <C> The class that have to go thru the profile manager
 */
public abstract class AbstractProfiles<C> extends WriteUtil {
	// ------------------------------------------------------------------------
	// Variables Properties
	//
	private List<String> defaultUserSettingKeys = new ArrayList<String>(List.of("User", "Last", "Cryslonoid"));
	private boolean firstInit = true;

	private LinkedHashMap<String, AbstractParameter<?, ?, C>> parameterNameMap;
	private LinkedHashMap<String, AbstractGroup<C>> groupNameMap;
	protected LinkedHashMap<String, AbstractGroup<C>> groupMap;
	private Parameter_ProfileAction parameterProfileAction;
	private boolean cleanUserKeys = true;

	private AbstractParameter<?, ?, C> currentParameter;
	private String currentParameterName;
	private AbstractGroup<C> currentGroup;
		
	// ========================================================================
	//  Abstract Methods
	//
	/**
	 * Add all the groups to the Map with an easy key
	 */
	protected abstract void initGroupMap(C clientObject);

	protected abstract String getFilePath();

	protected abstract String getFileName();
	
	protected abstract void createDefaultUserProfiles();

	// ========================================================================
	//  Protected Getters and Setters
	//
	protected Parameter_ProfileAction parameterProfileAction() {
		return parameterProfileAction;
	}

	protected LinkedHashMap<String, AbstractParameter<?, ?, C>> parameterNameMap() {
		return parameterNameMap;
	}
	// ========================================================================
	//  Public Methods
	//
	/**
   	 * Check if User Settings are already initialized with gameOptions
   	 * @return the initialization status
   	 */
	public boolean isInitialized () {
		return !firstInit;
	}

	/**
   	 * Check if' OK to use a parameter
	 * @param parameter the Parameter to check
   	 * @return the Parameter status
   	 */
	public boolean isParameterEnabled (String parameter) {
		if (isInitialized()) {
			AbstractParameter<?, ?, C> param = getParameter(parameter);
			if (param != null) {
				return param.isLoadEnabled();
			}
		}
		return false;
	}

	/**
	 * @param name ID of the group
	 * @return the group instance
	 */
	public AbstractGroup<C> getGroup (String name) {
		return groupNameMap.get(name.toUpperCase());
	}

	/**
	 * @param name ID of the parameter
	 * @return the parameter instance
	 */
	public AbstractParameter<?, ?, C> getParameter (String name) {
		return parameterNameMap.get(PMutil.neverNull(name).toUpperCase());
	}
	
	/**
   	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void saveGameToFile(C clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		updateFromGameValue(clientObject);
		for (String profile : getGameToFileProfiles()) {
			String action = parameterProfileAction.getProfileCodeView(profile.toUpperCase());
			if (containsIgnoreCase(action, ACTION_GAME_TO_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionToFile(Game, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_GAME_UPDATE_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionUpdateFile(Game, profile);
				}
			}
		}
		saveProfilesCfg();
	}

	/**
   	 * Load the profile file and memorize first options
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void initAndLoadProfiles(C clientObject) {
		if (firstInit) {
			parameterProfileAction = new Parameter_ProfileAction();
			initGroupMap(clientObject);
			initGroupNameMap();
			initParameterNameMap();
			firstInit = false;
		}
		loadProfilesCfg();
	}

	/**
	 * @param runObject The class that manage Game parameters
	 */
	public void changeGameSettings(C runObject) {
		loadProfilesCfg();
		List<String> settingKeys = getGameChangingProfiles();
		for (AbstractGroup<C> group : groupMap.values()) {
			group.changeGameFileParameters(runObject, settingKeys);
		}
		//Settings.ChangeGameFile = false;
	}

	/**
	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Execute User Actions
   	 * Save the new profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void saveGuiToFile(C clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		updateFromGuiValue(clientObject);
		doUserUpdateActions();
		saveProfilesCfg();
	}

	/**
	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file (without User Action)
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void saveLastGuiToFile(C clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		updateFromGuiValue(clientObject);
		saveProfilesCfg();
	}

	/**
   	 * Load and execute the profile file
   	 * only for the selected UI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void loadLocalGroupSettings(String group, C clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.overrideGuiParameters(clientObject, settingKeys);
	}
	
	/**
   	 * Load and execute the profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void loadGlobalGroupSettings(C clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		for (AbstractGroup<C> group : groupMap.values()) {
			group.overrideGuiParameters(clientObject, settingKeys);
		}
	}
	
	/**
   	 * Load and execute "Surprise" profile file
   	 * only for the selected UI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void loadSurpriseLocalGroupSettings(String group, C clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getSurpriseProfiles();
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.overrideGuiParameters(clientObject, settingKeys);
	}
	
	/**
   	 * Load and execute "Surprise" profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void loadSurpriseGlobalGroupSettings(C clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getSurpriseProfiles();
		for (AbstractGroup<C> group : groupMap.values()) {
			group.overrideGuiParameters(clientObject, settingKeys);
		}
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for all GUI
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void resetGlobalInitialOptions(C clientObject) {
		for (AbstractGroup<C> group : groupMap.values()) {
			group.setGuiParameters(Initial, clientObject);
		}
	}

	/**
   	 * Reset the game options to their very default values,
   	 * for all GUI
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void resetGlobalDefaultOptions(C clientObject) {
		for (AbstractGroup<C> group : groupMap.values()) {
			group.setGuiParameters(Default, clientObject);
		}
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for the selected GUI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void resetLocalInitialOptions(String group, C clientObject) {
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.setGuiParameters(Initial, clientObject);
	}

	/**
   	 * Reset the game options to their very default values,
   	 * for the selected GUI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	protected void resetLocalDefaultOptions(String group, C clientObject) {
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.setGuiParameters(Default, clientObject);
	}

	// ========================================================================
	// Initializations Methods
	//
	/**
	 * Key Map the group list 
	 */
	private void initGroupNameMap() {
		groupNameMap = new LinkedHashMap<String, AbstractGroup<C>>();
		for (AbstractGroup<C> group : groupMap.values()) {
			for (String profile : group.profileList()) {
				groupNameMap.put(profile, group);
			}
		}
	}
	
	/**
	 * Add all the Settings to the Map with an easy key
	 */
	private void initParameterNameMap() {
		parameterNameMap = new LinkedHashMap<String, AbstractParameter<?, ?, C>>();
		for (AbstractGroup<C> group : groupMap.values()) {
			for (String profile : group.profileList()) {
				parameterNameMap.put(profile, group.getParameter(profile));
			}
		}
	}
	
	// ========================================================================
	// Other Methods
	//
	private List<String> getAllProfiles() {
		return parameterProfileAction.getProfileList();
	}
	
	private List<String> getGameToFileProfiles() {
		return parameterProfileAction.getProfileListForCategory(LAST_ENABLED);
	}
	
	private List<String> getReadableProfiles() {
		return parameterProfileAction.getProfileListForCategory(LOAD_ENABLED);
	}
	
	private List<String> getSurpriseProfiles() {
		return parameterProfileAction.getProfileListForCategory(RANDOM_ENABLED);
	}
	
	private List<String> getGameChangingProfiles() {
		return parameterProfileAction.getProfileListForCategory(GAME_ENABLED);
	}
	
	private void loadProfilesCfg() {
		resetAllUserSettings();
		File profilesCfg = new File(getFilePath(), getFileName());
		if ( profilesCfg.exists() ) {
			try ( BufferedReader in =
				new BufferedReader(
					new InputStreamReader(
						new FileInputStream(profilesCfg), "UTF-8"));) {
				String line;
				while ((line = in.readLine()) != null) {
					loadLine(line.trim());
				}
			}
			catch (FileNotFoundException e) {
				System.err.println(getFilePath() + getFileName() + " not found.");
			}
			catch (IOException e) {
				System.err.println("UserPreferences.load -- IOException: "+ e.toString());
			}
			forceCreationMissingProfile(getAllProfiles());
		}
		else {
			// the file does not exist: create a default one
			createDefaultUserProfiles();
			forceCreationMissingProfile(getAllProfiles());
			doUserUpdateActions();
		}
	}
	
	private void loadLine(String line) {
		// Test for Emptiness and ignore
		if (line.isEmpty()) {
			return;
		}
		// test for comment and ignore
		if (isComment(line)) {
			return;
		}
		// Test for new setting
		String key = Lines.getKey(line);
		if (key.isBlank()) {
			return;
		}
		// Test for New Setting Section
		if (AbstractParameter.isHeadOfParameter(key) ) {
			currentParameterName = Lines.getValueAsString(line).toUpperCase();
			// Test if initial profile list declaration
			if (parameterProfileAction.getParameterName()
					.equalsIgnoreCase(currentParameterName)) {
				currentParameter = parameterProfileAction;
				currentGroup = null;
				return;
			}
			currentGroup = groupNameMap.get(currentParameterName);
			currentParameter = null;
			if (currentGroup != null) {
				currentParameter = currentGroup.getParameter(currentParameterName);
				return;
			}
			return;
		}
		// it's a setting Line
		if (currentParameter != null) {
			currentParameter.addLine(line);
		}
	}
	
	private int saveProfilesCfg() {
		List<String> settingKeys = getAllProfiles();
		if (settingKeys == null || settingKeys.isEmpty()) {
			settingKeys = defaultUserSettingKeys;
		}
		try (FileOutputStream fout = new FileOutputStream(new File(getFilePath(), getFileName()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
			// SETTING :PRESET ACTIONS
			out.print(parameterProfileAction.toString(settingKeys));
			// Loop thru settings
			for (AbstractGroup<C> group : groupMap.values()) {
				out.print(group.toString(settingKeys, cleanUserKeys));
			}
			return 0;
		}
		catch (IOException e) {
			System.err.println("ProfileMap.save -- IOException: "+ e.toString());
			return -1;
		}
	}
	
	private void resetAllUserSettings() {
		for (AbstractGroup<C> group : groupMap.values()) {
			group.resetAllUserSettings();
		}
	}
	
	private void forceCreationMissingProfile(List<String> profileList) {
		if (PMutil.getForceCreationMissingProfile()) {
			for (AbstractGroup<C> group : groupMap.values()) {
				group.forceCreationMissingProfile(profileList);
			}
		}
	}
	
	void doUserUpdateActions() {
		// Loop Thru User's Keys and perform requested action
		for (String profile : getAllProfiles()) {
			String action = parameterProfileAction.getProfileUserView(profile.toUpperCase());
			if (containsIgnoreCase(action, ACTION_GUI_TO_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionToFile(Current, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_GAME_TO_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionToFile(Game, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_INITIAL_TO_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionToFile(Initial, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_DEFAULT_TO_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionToFile(Default, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_GUI_UPDATE_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionUpdateFile(Current, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_GAME_UPDATE_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionUpdateFile(Game, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_INITIAL_UPDATE_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionUpdateFile(Initial, profile);
				}
			}
			if (containsIgnoreCase(action, ACTION_DEFAULT_UPDATE_FILE)) {
				for (AbstractGroup<C> group : groupMap.values()) {
					group.actionUpdateFile(Default, profile);
				}
			}
		}
		saveProfilesCfg();
	}
	
	void updateFromGuiValue(C clientObject) {
		for (AbstractGroup<C> group : groupMap.values()) {
			group.actionTakeGuiCodeView(clientObject);
		}
	}
	
	void updateFromGameValue(C clientObject) {
		for (AbstractGroup<C> group : groupMap.values()) {
			group.actionTakeGameCodeView(clientObject);
		}
	}
	
	// ========================================================================
	// Nested Classes
	//
	protected class Parameter_ProfileAction extends 
			AbstractParameter<String, Valid_ProfileAction, C> {
		// The Class C is not used, but needed for compatibility

		// ------------------------------------------------------------------------
		// Constructors
		//
		protected Parameter_ProfileAction() { 
			super(PARAMETER_NAME, new Valid_ProfileAction());
		}
		
		//	protected Parameter_ProfileAction(String Name) {
		//		super(Name, new Valid_ProfileAction());
		//	}
			
		@Override public void initComments() {
			setHeadComments (
				"			EXTENDED PLAYER'S SETTINGS" + NL +
				"-------------------------------------------------- " + NL +
				" " + NL
				);
			setBottomComments(
				"(---- The last loaded Win)" );
		  }
		
		@Override public AbstractT<String> getFromGame(C clientObject) { 
			return null; // Should never happen
		}
	
		@Override public void putToGame(C runObject, AbstractT<String> userOption) {}
	
		@Override public AbstractT<String> getFromUI(C gO) { 
			return null; // Should never happen
		}
	
		@Override public void putToGUI(C gO, AbstractT<String> userOption) {}
	}
}
