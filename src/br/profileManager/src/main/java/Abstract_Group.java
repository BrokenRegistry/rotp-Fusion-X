
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @param <ClientClass> The class that have to go thru the profile manager
 */
public abstract class Abstract_Group<ClientClass> extends ToPrint {

	//  ========================================================================
	// Variables Properties
	//
	LinkedHashMap<String, Abstract_Parameter<?, ?, ClientClass>>
		parameterNameMap = new LinkedHashMap<String, 
								Abstract_Parameter<?, ?, ClientClass>>();

	//  ========================================================================
	// Constructor
	//
	protected Abstract_Group(ClientClass clientObject) {
		initSettingList(clientObject);
	}
	// ========================================================================
	// Abstract Methods
	//
	protected abstract void initSettingList(ClientClass clientObject);

	// ========================================================================
	// Public and packages Methods
	//
	/**
	 * @param clientObject The class that manage GUI parameters
	 */
	public void actionGetGuiCodeView(ClientClass clientObject) {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.setGuiCodeView(clientObject); 
		}
	}

	/**
	 * @param clientObject The class that manage GUI parameters
	 */
	public void actionGetGameCodeView(ClientClass clientObject) {
		for (Abstract_Parameter<?, ?, ClientClass> 
					parameter : parameterNameMap.values() ) {
			parameter.setGameCodeView(clientObject);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionGuiToFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass> 
					parameter : parameterNameMap.values() ) {
			parameter.actionUiToFile(profileName);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionGameToFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass> 
					parameter : parameterNameMap.values() ) {
			parameter.actionGameToFile(profileName);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionInitialToFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.actionInitialToFile(profileName);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionGuiUpdateFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.actionUiUpdateFile(profileName);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionGameUpdateFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.actionGameUpdateFile(profileName);
		}
	}

	/**
	 * @param profileName the profile name
	 */
	public void actionInitialUpdateFile(String profileName) {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.actionInitialUpdateFile(profileName);
		}
	}

	/**
	 * @return the profile name list
	 */
	public List<String> profileList() {
		return new ArrayList<String>(parameterNameMap.keySet());
	 }

	/**
	 * @param profileName the profile name
	 * @return Selected parameter
	 */
	public Abstract_Parameter<?, ?, ClientClass> getParameter(String profileName) {
		return parameterNameMap.get(profileName);
	}

	/**
	 * @param profileList the profile name
	 * @param cleanProfiles to remove the unlisted profiles
	 * @return parameters group as String, ready to be printed
	 */
	public String toString(List<String> profileList, boolean cleanProfiles) {
		String out = "";
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			if (cleanProfiles) {
				out += parameter.toString(profileList);
			}
			else {
				List<String> keySet = new ArrayList<String>(profileList);
				keySet.addAll(parameter.getProfileList());
				out += parameter.toString(keySet);
			}
		}
		return out;
	}
	
	/**
	 * @param profileList the profile name
	 * @return parameters group as String, ready to be printed
	 */
	public String toString(List<String> profileList) {
		return toString(profileList, false);
	}

//	/**
//	 * @return parameters group as String, ready to be printed
//	 */
//	@Override public String toString() {
//		return toString(profileList(), false);
//	}

	// ========================================================================
	// Initialization Methods
	//
	/**
	 * @param parameter the parameter block to process
	 */
	protected void addParameter(
			Abstract_Parameter<?, ?, ClientClass> parameter) {
		parameterNameMap.put(parameter.getParameterName(), parameter);
	}

	/**
	 * Partial reset, values only
	 */
	void resetAllUserSettings() {
		for (Abstract_Parameter<?, ?, ClientClass>
					parameter : parameterNameMap.values() ) {
			parameter.resetUserProfiles();
		}
	}

	/**
	 * @param clientObject The class that manage GUI parameters
	 * @param profileList the profile name
	 */
	public void overrideGuiParameters(
				ClientClass clientObject, List<String> profileList) {
		// Loop thru settings
		for (Abstract_Parameter<?, ?, ClientClass>
						parameter : parameterNameMap.values() ) {
			parameter.overrideGuiParameters(clientObject, profileList);
		}
	}

	/**
	 * @param runObject The class that manage Game parameters
	 * @param profileList the profile name
	 */
	public void changeGameFileParameters(
				ClientClass runObject, List<String> profileList) {
		// Loop thru settings
		for (Abstract_Parameter<?, ?, ClientClass> 
					parameter : parameterNameMap.values() ) {
			parameter.changeGameFileParameters(runObject, profileList);
		}
	}

	/**
	 * @param clientObject The class that manage GUI parameters
	 */
	public void setGuiParametersToInitial(ClientClass clientObject) {
		for (Abstract_Parameter<?, ?, ClientClass> 
					parameter : parameterNameMap.values() ) {
			parameter.putInitialToGUI(clientObject);
		}
	}
}
