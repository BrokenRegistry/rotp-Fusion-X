
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

package mod.br.settings;

import java.util.LinkedHashMap;
import java.util.List;

import br.config.AbstractGroup;
import br.config.AbstractCfgFile;
import rotp.Rotp;
import rotp.model.game.IGameOptions;
import rotp.model.game.GameSession;

public class UserSettings extends AbstractCfgFile<IGameOptions, GameSession> {
    
    // ------------------------------------------------------------------------
    // Constructors
    //
	public UserSettings() {}
	
	// ========================================================================
	//  Abstract Methods
	//
	@Override protected String getFilePath () { return Rotp.jarPath(); }
	@Override protected String getFileName () { return "Presets.cfg"; }
	@Override protected String getSettingUserActionName () { return "PRESET ACTION"; }
	@Override protected List<String> getSettingUserActionOptions () { 
				return List.of("File To UI", "File To Game", 
						"UI To File", "Game To File", "Initial To File",
						"UI Update File", "Game Update File", "Initial Update File");
    			}
	@Override protected String getSettingUserActionFirst () { return "UI To File"; }
    /*
	 * Add all the groups to the Map with an easy key
	 */
	@Override
	protected void initGroupMap(IGameOptions options) {
      groupMap = new LinkedHashMap<String, AbstractGroup<IGameOptions, GameSession>>();
      groupMap.put("RACE",     new Group_Race(options));
      groupMap.put("GALAXY",   new Group_Galaxy(options));
      groupMap.put("ADVANCED", new Group_Advanced(options));
      groupMap.put("MODNAR",   new Group_Modnar(options));
      groupMap.put("BR",       new Group_BrokenRegistry(options));
	}
}
