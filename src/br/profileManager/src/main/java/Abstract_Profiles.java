
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
 * @param <ClientClass> The class that have to go thru the profile manager
 */
public abstract class Abstract_Profiles<ClientClass> extends ToPrint {
	// ------------------------------------------------------------------------
	// Variables Properties
	//
	private List<String> defaultUserSettingKeys = new ArrayList<String>(List.of("User", "Last", "Cryslonoid"));
	private boolean firstInit = true;

	private LinkedHashMap<String, Abstract_Parameter<?, ?, ClientClass>> parameterNameMap;
	private LinkedHashMap<String, Abstract_Group<ClientClass>> groupNameMap;
	protected LinkedHashMap<String, Abstract_Group<ClientClass>> groupMap;
	private Parameter_ProfileAction parameterProfileAction;
	private boolean cleanUserKeys = true;

	private Abstract_Parameter<?, ?, ClientClass> currentParameter;
	private String currentParameterName;
	private Abstract_Group<ClientClass> currentGroup;
	
	// ========================================================================
	//  Abstract Methods
	//
	/**
	 * Add all the groups to the Map with an easy key
	 */
	protected abstract void initGroupMap(ClientClass clientObject);

	protected abstract String getFilePath();

	protected abstract String getFileName();

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
	 * @param name ID of the group
	 * @return the group instance
	 */
	public Abstract_Group<ClientClass> getGroup (String name) {
		return groupNameMap.get(name.toUpperCase());
	}

	/**
	 * @param name ID of the parameter
	 * @return the parameter instance
	 */
	public Abstract_Parameter<?, ?, ClientClass> getParameter (String name) {
		return parameterNameMap.get(name.toUpperCase());
	}

	/**
	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void saveGuiToFile(ClientClass clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		// Remove the Local Enable parameter where possibly wrongly added 
			parameterProfileAction.removeLocalEnable();
		updateGuiValue(clientObject);
		doUserUpdateActions();
		saveProfilesCfg();
	}

	/**
   	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void saveGameToFile(ClientClass clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		// Remove the Local Enable parameter where possibly wrongly added 
			parameterProfileAction.removeLocalEnable();
		updateGameValue(clientObject);
		doUserUpdateActions();
		saveProfilesCfg();
	}

	/**
   	 * Load the profile file and memorize first options
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void initAndLoadProfiles(ClientClass clientObject) {
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
   	 * Load and execute the profile file
   	 * only for the selected UI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void loadLocalGroupSettings(String group, ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.overrideGuiParameters(clientObject, settingKeys);
	}
	
	/**
   	 * Load and execute the profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void loadGlobalGroupSettings(ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.overrideGuiParameters(clientObject, settingKeys);
		}
	}
	
	/**
	 * @param runObject The class that manage Game parameters
	 */
	public void changeGameSettings(ClientClass runObject) {
		loadProfilesCfg();
		List<String> settingKeys = getGameChangingProfiles();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.changeGameFileParameters(runObject, settingKeys);
		}
		//Settings.ChangeGameFile = false;
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for all GUI
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void resetGlobalInitialOptions(ClientClass clientObject) {
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.setGuiParametersToInitial(clientObject);
		}
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for the selected GUI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	public void resetLocalInitialOptions(String group, ClientClass clientObject) {
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.setGuiParametersToInitial(clientObject);
	}

	// ========================================================================
	// Initializations Methods
	//
	/**
	 * Key Map the group list 
	 */
	private void initGroupNameMap() {
		groupNameMap = new LinkedHashMap<String, Abstract_Group<ClientClass>>();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			for (String profile : group.profileList()) {
				groupNameMap.put(profile, group);
			}
		}
	}
	
	/**
	 * Add all the Settings to the Map with an easy key
	 */
	private void initParameterNameMap() {
		parameterNameMap = new LinkedHashMap<String, Abstract_Parameter<?, ?, ClientClass>>();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			for (String profile : group.profileList()) {
				parameterNameMap.put(profile, group.getParameter(profile));
			}
		}
	}
	
	// ========================================================================
	// Other Methods
	//
	private void createDefaultUserProfiles() {
		List<String> settingKeys = new ArrayList<String>(List.of("User", "Last", "Cryslonoid"));
		
	}

	private List<String> getAllProfiles() {
		return parameterProfileAction.getProfileList();
	}
	
	private List<String> getReadableProfiles() {
		return parameterProfileAction.getProfileListForCategory(LOAD_ENABLED);
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
		} else {
			// the file does not exist: create a default one 
			saveProfilesCfg();
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
		String key = Generic_Line.getKey(line);
		if (key.isBlank()) {
			return;
		}
		// Test for New Setting Section
		if (Abstract_Parameter.isHeadOfParameter(key) ) {
			currentParameterName = Generic_Line.getValueAsString(line).toUpperCase();
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
		// Remove the Local Enable parameter where possibly wrongly added 
		parameterProfileAction.removeLocalEnable();
		
		List<String> settingKeys = getAllProfiles();
		if (settingKeys == null || settingKeys.isEmpty()) {
			settingKeys = defaultUserSettingKeys;
		}
		try (FileOutputStream fout = new FileOutputStream(new File(getFilePath(), getFileName()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(fout, "UTF-8")); ) {
			// SETTING :PRESET ACTIONS
			out.print(parameterProfileAction.toString(settingKeys));
			// Loop thru settings
			for (Abstract_Group<ClientClass> group : groupMap.values()) {
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
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.resetAllUserSettings();
		}
	}
	
	void doUserUpdateActions() {
		// Loop Thru User's Keys and perform requested action
		List<String> profileList = parameterProfileAction.getProfileList();
		for (String profile : profileList) {
			profile = profile.toUpperCase();
			String action = parameterProfileAction.getProfileValue(profile);
			if (action.contains(ACTION_GUI_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionGuiToFile(profile);
				}
			}
			if (action.contains(ACTION_GAME_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionGameToFile(profile);
				}
			}
			if (action.contains(ACTION_INITIAL_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionInitialToFile(profile);
				}
			}
			if (action.contains(ACTION_GUI_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionGuiUpdateFile(profile);
				}
			}
			if (action.contains(ACTION_GAME_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionGameUpdateFile(profile);
				}
			}
			if (action.contains(ACTION_INITIAL_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionInitialUpdateFile(profile);
				}
			}
		}
		saveProfilesCfg();
	}
	
	void updateGuiValue(ClientClass clientObject) {
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.actionGetGuiCodeView(clientObject);
		}
	}
	
	void updateGameValue(ClientClass clientObject) {
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.actionGetGameCodeView(clientObject);
		}
	}
	
	// ========================================================================
	// Nested Classes
	//
	protected class Parameter_ProfileAction extends 
			Abstract_Parameter<String, Valid_ProfileAction, ClientClass> {
		// The Class ClientClass is not used, but needed for compatibility

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
			" " + NL +
			"---- This is where you add your profile list " + NL +
			"- File To UI		  = Load from file and update GUI" + NL +
			"- File To Game		= Load from file and update Game at specific LOAD" + NL +
			"- UI To File		  = Get from GUI and Save to file" + NL +
			"- Game To File		= Get from Game and Save to file" + NL +
			"- Initial To File	 = Get initial value of GUI and Save to file" + NL +
			"- UI Update File	  = Get from GUI and Save to file, if field is not Empty" + NL +
			"- Game Update File	= Get from GUI and Save to file, if field is not Empty" + NL +
			"- Initial Update File = Get initial value of GUI and Save to file, if field is not Empty" + NL +
			"---- Multiple LOAD will follow their sequence" + NL +
			"---- Multiple choices are allowed. ex Load save" + NL +
			" " 
			);
		setBottomComments(
			"(---- The last loaded Win)" );
	  }
	
	@Override public String getFromGame(ClientClass clientObject) { 
		return "-";
	}

	@Override public void putToGame(ClientClass runObject, String userOption) {}

	@Override public String getFromUI(ClientClass gO) { 
		return "-";
	}

	@Override public void putToGUI(ClientClass gO, String userOption) {}

	@Override public void putInitialToGUI(ClientClass gO) {}
 }
}
