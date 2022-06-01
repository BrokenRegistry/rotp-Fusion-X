
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

package mod.br.profileManager;
import static br.profileManager.src.main.java.Valid_ProfileAction.*;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Parameter;
import br.profileManager.src.main.java.Abstract_Profiles;
import rotp.Rotp;
import rotp.ui.UserPreferences;

/**
 * <ClientClass> The class that have to go thru the profile manager
 */
public class UserProfiles extends Abstract_Profiles<ClientClasses> {
    
	static enum BaseMod {
		Original, Governor, Modnar, Xilmi, BrokenRegistry
	}
	
	private static final String BR_GROUP_NAME = "BR";
	static BaseMod baseMod = BaseMod.Xilmi;
	
	// ========================================================================
	//  Abstract Methods
	//
	@Override protected String getFilePath () {
		return Rotp.jarPath(); 
	}

	@Override protected String getFileName () {
		return "Profiles.cfg";
	}

	/*
	 * Add all the groups to the Map with an easy key
	 */
	@Override protected void initGroupMap(ClientClasses options) {
      groupMap = new LinkedHashMap<String, Abstract_Group<ClientClasses>>();
      groupMap.put("RACE",        new Group_Race(options));
      groupMap.put("GALAXY",      new Group_Galaxy(options));
      groupMap.put("ADVANCED",    new Group_Advanced(options));
      groupMap.put("MODNAR",      new Group_Modnar(options));
      groupMap.put(BR_GROUP_NAME, new Group_BrokenRegistry(options));
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
			String group, ClientClasses clientObject) {
		switch (key) {
		case KeyEvent.VK_B: // "B" = Load Broken Registry User Presets
			loadLocalGroupSettings(BR_GROUP_NAME, clientObject);
			return false;
		case KeyEvent.VK_D: // "D" = Reload Default Presets
			if(global) {
				resetGlobalDefaultOptions(clientObject);
				return true;
			} else {
				resetLocalDefaultOptions(group, clientObject);
				return true;            		
			}
		case KeyEvent.VK_G: // "G" = Reload User Presets
			loadGlobalGroupSettings(clientObject);
			return true;
		case KeyEvent.VK_I: // "I" = Reload Initial Presets
			if(global) {
				resetGlobalInitialOptions(clientObject);
				return true;
			} else {
				resetLocalInitialOptions(group, clientObject);
				return true;            		
			}
		case KeyEvent.VK_L: // "L" = Load GUI User Presets
			if(global) {
				loadGlobalGroupSettings(clientObject);
				return true;
			} else {
				loadLocalGroupSettings(group, clientObject);
				return true;
			}
		case KeyEvent.VK_R: // "R" = Load GUI Surprise Presets
			if(global) {
				loadSurpriseGlobalGroupSettings(clientObject);
				return true;
			} else {
				loadSurpriseLocalGroupSettings(group, clientObject);
				return true;
			}
		case KeyEvent.VK_S: // "S = Save Remnant.cfg
			UserPreferences.save();
			return false;
		case KeyEvent.VK_U: // "U" = Update User Presets
			saveGuiToFile(clientObject);
			return false;
		}
		return false;
	}
	
	@Override protected void createDefaultUserProfiles() {
		parameterProfileAction().addLine("User", 
				ACTION_GUI_TO_FILE + " " + ACTION_FILE_TO_GUI,
				"This profile could be Loaded by pressing \"L\"");
		parameterProfileAction().addLine("LastGui",
				ACTION_GUI_TO_FILE,
				"This profile will keep the last GUI configuration");
		parameterProfileAction().addLine("LastGame",
				ACTION_GAME_TO_FILE,
				"This profile will keep the loaded Game configuration");
		parameterProfileAction().addLine("Random", 
				ACTION_RANDOM,
				"Loaded by pressing \"R\", add or replace by "
						+ ACTION_FILE_TO_GUI + " to allow it to be loaded");
		// Fill the Random
		for (Abstract_Parameter<?, ?, ClientClasses> parameter : parameterNameMap().values()) {
			parameter.addLine("Random", "Random");
		}
		getParameter("AUTOPLAY").addLine("Random", "Off", "Not Random!");
		getParameter("MAXIMIZE EMPIRES SPACING").addLine("Random", "NO",
				"Not Random, not yet");
		
		// Special random with comments
		getParameter("PLAYER RACE").addLine("Random", "Random",
				"Full random");
		getParameter("PLAYER COLOR").addLine("Random", "Random Green, Lime",
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
}
