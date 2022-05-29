
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

import java.awt.event.KeyEvent;
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

import rotp.ui.UserPreferences;

/**
 * @param <ClientClass> The class that have to go thru the profile manager
 */
public abstract class Abstract_Profiles<ClientClass> extends WriteUtil {
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
   	 * Load the configuration file to update the Action
   	 * Update with last Loaded Game options values
   	 * Save the new configuration file
	 * @param key the key to process
	 * @param global Global or Local ?
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
	 * @return key to local remaining processing
   	 */
	public boolean processKey(int key, boolean global,
			String group, ClientClass clientObject) {
		switch (key) {
		case KeyEvent.VK_D: // BR: "D" = Reload Default Presets
			if(global) {
				resetGlobalDefaultOptions(clientObject);
				return true;
			} else {
				resetLocalDefaultOptions(group, clientObject);
				return true;            		
			}
		case KeyEvent.VK_G: // BR: "G" = Reload User Presets
			loadGlobalGroupSettings(clientObject);
			return true;
		case KeyEvent.VK_I: // BR: "I" = Reload Initial Presets
			if(global) {
				resetGlobalInitialOptions(clientObject);
				return true;
			} else {
				resetLocalInitialOptions(group, clientObject);
				return true;            		
			}
		case KeyEvent.VK_L: // BR: "L" = Load GUI User Presets
			if(global) {
				loadGlobalGroupSettings(clientObject);
				return true;
			} else {
				loadLocalGroupSettings(group, clientObject);
				return true;
			}
		case KeyEvent.VK_R: // BR: "R" = Load GUI Surprise Presets
			if(global) {
				loadSurpriseGlobalGroupSettings(clientObject);
				return true;
			} else {
				loadSurpriseLocalGroupSettings(group, clientObject);
				return true;
			}
		case KeyEvent.VK_S: // BR: "S = Save Remnant.cfg
			UserPreferences.save();
			return false;
		case KeyEvent.VK_U: // BR: "U" = Update User Presets
			saveGuiToFile(clientObject);
			return false;
		}
		return false;
	}

	/**
	 * Load the profile file to update the Action
   	 * Update with last options values
   	 * Save the new profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void saveGuiToFile(ClientClass clientObject) {
		loadProfilesCfg(); // in case the user changed load or save actions
		// Remove the Local Enable parameter where possibly wrongly added 
			parameterProfileAction.removeLocalEnable();
		updateGuiValue(clientObject);
		doUserUpdateActions();
		saveProfilesCfg();
	}

	/**
   	 * Load and execute the profile file
   	 * only for the selected UI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void loadLocalGroupSettings(String group, ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.overrideGuiParameters(clientObject, settingKeys);
	}
	
	/**
   	 * Load and execute the profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void loadGlobalGroupSettings(ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getReadableProfiles();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.overrideGuiParameters(clientObject, settingKeys);
		}
	}
	
	/**
   	 * Load and execute "Surprise" profile file
   	 * only for the selected UI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void loadSurpriseLocalGroupSettings(String group, ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getSurpriseProfiles();
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.overrideGuiParameters(clientObject, settingKeys);
	}
	
	/**
   	 * Load and execute "Surprise" profile file
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void loadSurpriseGlobalGroupSettings(ClientClass clientObject) {
		loadProfilesCfg();
		List<String> settingKeys = getSurpriseProfiles();
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.overrideGuiParameters(clientObject, settingKeys);
		}
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for all GUI
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void resetGlobalInitialOptions(ClientClass clientObject) {
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.setGuiParameters(Initial, clientObject);
		}
	}

	/**
   	 * Reset the game options to their very default values,
   	 * for all GUI
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void resetGlobalDefaultOptions(ClientClass clientObject) {
		for (Abstract_Group<ClientClass> group : groupMap.values()) {
			group.setGuiParameters(Default, clientObject);
		}
	}

	/**
   	 * Reset the game options as they where at the beginning,
   	 * for the selected GUI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void resetLocalInitialOptions(String group, ClientClass clientObject) {
		currentGroup = groupMap.get(group.toUpperCase());
		currentGroup.setGuiParameters(Initial, clientObject);
	}

	/**
   	 * Reset the game options to their very default values,
   	 * for the selected GUI
	 * @param group group name
	 * @param clientObject The class that manage GUI parameters
   	 */
	private void resetLocalDefaultOptions(String group, ClientClass clientObject) {
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
		parameterProfileAction.addLine("User", 
				ACTION_GUI_TO_FILE + " " + ACTION_FILE_TO_GUI,
				"This profile could be Loaded by pressing \"L\"");
		parameterProfileAction.addLine("LastGui",
				ACTION_GUI_TO_FILE,
				"This profile will keep the last GUI configuration");
		parameterProfileAction.addLine("LastGame",
				ACTION_GAME_TO_FILE,
				"This profile will keep the loaded Game configuration");
		parameterProfileAction.addLine("Random", 
				ACTION_RANDOM,
				"Loaded by pressing \"R\", add or replace by "
						+ ACTION_FILE_TO_GUI + " to allow it to be loaded");
		// Fill the Random
		for (Abstract_Parameter<?, ?, ClientClass> parameter : parameterNameMap.values()) {
			parameter.addLine("Random", "Random");
		}
		getParameter("AUTOPLAY").addLine("Random", "Off", "Not Random!");
		getParameter("MAXIMIZE EMPIRES SPACING").addLine("Random", "NO",
				"Not Random, not yet");
		
		// Special random with comments
		getParameter("PLAYER RACE").addLine("Random", "Random",
				"Full random");
		getParameter("PLAYER COLOR").addLine("Random", "Green, Lime",
				"2 values = a range from option list");
		getParameter("GALAXY SHAPE").addLine("Random",
				"Random Rectangle, Ellipse, Spiral, Spiralarms",
				"a limited choice");
		getParameter("GALAXY SIZE").addLine("Random", "",
				"Nothing changed by this profile");
		getParameter("DIFFICULTY").addLine("Random", "Random 1, 4",
				"a range from option list");
		getParameter("OPPONENT AI").addLine("Random", 
				"Random Base, Xilmi, Xilmi",
				"2 chances to have Xilmi vs Base");
		getParameter("NB OPPONENTS").addLine("Random", "Random 3, 6",
				"a custom range");
		getParameter("GALAXY AGE").addLine("Random",
				"Random Young, Young, Old, Old",
				"Only 2 choices... Not a range");
		getParameter("NEBULAE").addLine("Random","Random 1, 4",
				"Range = Rare .. Common (first option = 0)");
//		getParameter("AI HOSTILITY").addLine("Random", "Random 0, 3");
//		getParameter("COUNCIL").addLine("Random", "Random");
//		getParameter("RANDOMIZE AI").addLine("Random", "Random");
//		getParameter("RESEARCH").addLine("Random", "Random");
//		getParameter("TECH TRADING").addLine("Random", "Random");
//		getParameter("ALWAYS STAR GATES").addLine("Random", "Yes", "Not Random!");
	}

	private List<String> getAllProfiles() {
		return parameterProfileAction.getProfileList();
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
	
	private void forceCreationMissingProfile(List<String> profileList) {
		if (PMutil.getForceCreationMissingProfile()) {
			for (Abstract_Group<ClientClass> group : groupMap.values()) {
				group.forceCreationMissingProfile(profileList);
			}
		}
	}
	
	void doUserUpdateActions() {
		// Loop Thru User's Keys and perform requested action
		for (String profile : getAllProfiles()) {
			String action = parameterProfileAction.getProfileValue(profile.toUpperCase());
			if (action.contains(ACTION_GUI_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionToFile(Current, profile);
				}
			}
			if (action.contains(ACTION_GAME_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionToFile(Game, profile);
				}
			}
			if (action.contains(ACTION_INITIAL_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionToFile(Initial, profile);
				}
			}
			if (action.contains(ACTION_DEFAULT_TO_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionToFile(Default, profile);
				}
			}
			if (action.contains(ACTION_GUI_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionUpdateFile(Current, profile);
				}
			}
			if (action.contains(ACTION_GAME_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionUpdateFile(Game, profile);
				}
			}
			if (action.contains(ACTION_INITIAL_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionUpdateFile(Initial, profile);
				}
			}
			if (action.contains(ACTION_DEFAULT_UPDATE_FILE)) {
				for (Abstract_Group<ClientClass> group : groupMap.values()) {
					group.actionUpdateFile(Default, profile);
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
				" " + NL
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
	}
}
