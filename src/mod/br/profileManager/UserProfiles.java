
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

import java.util.LinkedHashMap;
import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Profiles;
import rotp.Rotp;

/**
 * <ClientClass> The class that have to go thru the profile manager
 */
public class UserProfiles extends Abstract_Profiles<ClientClasses> {
    
	static enum BaseMod {
		Original, Governor, Modnar, Xilmi, BrokenRegistry
	}
	
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
      groupMap.put("RACE",     new Group_Race(options));
      groupMap.put("GALAXY",   new Group_Galaxy(options));
      groupMap.put("ADVANCED", new Group_Advanced(options));
      groupMap.put("MODNAR",   new Group_Modnar(options));
      groupMap.put("BR",       new Group_BrokenRegistry(options));
	}

}
