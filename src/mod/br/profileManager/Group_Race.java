
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
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

package mod.br.profileManager;

import java.util.List;

import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Parameter;
import br.profileManager.src.main.java.PMutil;
import br.profileManager.src.main.java.Valid_Integer;
import br.profileManager.src.main.java.Valid_String;
import br.profileManager.src.main.java.ValidationCriteria;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @author BrokenRegistry
 * For Parameters in Race GUI
 */
public class Group_Race extends Abstract_Group <ClientClasses> {

	Group_Race(ClientClasses go) {
		super(go);
	}
	
	@Override protected void initSettingList(ClientClasses go) {
			addParameter(new PlayerRace(go));
			addParameter(new PlayerColor(go));
//			addParameter(new PlayerHomeWorld(go));
//			addParameter(new PlayerName(go));
	}

	// ==============================================================
	// PLAYER RACE
	//
	static class PlayerRace extends
			Abstract_Parameter <String, Valid_String, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    // startingRaceOptions
		PlayerRace(ClientClasses go) { 
			super("PLAYER RACE",
				  new Valid_String(go.getOptionsObject().startingRaceOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedPlayerRace());
			// No default (Random) ... So Initial!
			setHistoryCodeView(Default, go.getOptionsObject().selectedPlayerRace());
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedPlayerRace();
		}
		
		@Override public void putToGame(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedPlayer().race = userOption; // Direct to avoid reseting opponents
//			go.getSessionObject().player().setRace(userOption);
			go.getSessionObject().galaxy().empire(0).setRace(userOption);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedPlayerRace();
		}
		
		@Override public void putToGUI(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedPlayerRace(userOption);
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"--------- Races Game Options ---------" + NL +
				" ");
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// PLAYER COLOR
	//
	static class Valid_Color extends Valid_Integer {

		static final List<String> EMPIRECOLORS = getEmpireColors();

		private static List<String> getEmpireColors() {
			switch(UserProfiles.baseMod) {
			case BrokenRegistry:
			case Modnar:
			case Xilmi:
				return List.of ("Red", "Green", "Yellow", "Blue", "Orange", "Purple",
								"Aqua", "Fuchsia", "Brown", "White", "Lime", "Grey",
								"Plum", "Light Blue", "Mint", "Olive");
			default:
				return List.of ("Blue", "Brown", "Green",  "Orange", "Pink",
								"Purple", "Red", "Teal", "Yellow", "White");
			}
		}

		Valid_Color() {
			super();
			init();
		}

		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false));
		}
		
		private void init() {
			initCriteria();
			for (String color : EMPIRECOLORS) {
				addElement(EMPIRECOLORS.indexOf(color), color);
			}
			setLimits(0 , EMPIRECOLORS.size());
			setDefaultRandomLimits(0 , EMPIRECOLORS.size());
			setHistoryUserView(Default, getDefaultColor());
		}

		private String getDefaultColor() {
			switch(UserProfiles.baseMod) {
			case Modnar:
			case Xilmi:
				return "Red";
			default:
				return "Blue";
			}
		}
				
		@Override protected String toUserView(Integer codeView) {
			if (codeView == null) {
				return "";
			}
			return EMPIRECOLORS.get(codeView);
		}

		@Override protected Integer toCodeView(String userView) {
			if (userView == null) {
				userView = getDefaultColor();
			}
			return EMPIRECOLORS.indexOf(userView);
		}

		/**
		 * Generate UserViewList and convert it to capitalized String
		 * @return UserView List in capitalized String
		 */
		@Override public String getOptionsRange() {
			return PMutil.capitalize(getUserViewList().toString());
		}
	}

	// ========== Parameter Section ==========
	//
	static class PlayerColor extends 
			Abstract_Parameter <Integer, Valid_Color, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		PlayerColor(ClientClasses go) {
			super("PLAYER COLOR", new Valid_Color());
			setHistoryCodeView(Initial, go.getOptionsObject().selectedPlayerColor());
		}
		
	    // ========== Overriders ==========
	    //
		@Override public Integer getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedPlayerColor();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {
			go.getOptionsObject().selectedPlayerColor(codeView);
			go.getSessionObject().galaxy().empire(0).changeColorId(codeView);
		}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedPlayerColor();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			go.getOptionsObject().selectedPlayerColor(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}	
	}

	// ==============================================================
	// PLAYER HOMEWORLD
	//
	static class PlayerHomeWorld extends
			Abstract_Parameter <String, Valid_String, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    // startingRaceOptions
		PlayerHomeWorld(ClientClasses go) { 
			super("PLAYER HOMEWORLD", new Valid_String());
			setHistoryCodeView(Initial, go.getOptionsObject().selectedHomeWorldName());
			// No default (Random) ... So Initial!
			setHistoryCodeView(Default, go.getOptionsObject().selectedHomeWorldName());
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedHomeWorldName();
		}
		
		@Override public void putToGame(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedHomeWorldName(userOption);
			go.getSessionObject().galaxy().empire(0).setHomeWorldName(userOption);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedHomeWorldName();
		}
		
		@Override public void putToGUI(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedHomeWorldName(userOption);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}

	// ==============================================================
	// PLAYER NAME
	//
	static class PlayerName extends
			Abstract_Parameter <String, Valid_String, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    // startingRaceOptions
		PlayerName(ClientClasses go) { 
			super("PLAYER NAME", new Valid_String());
			setHistoryCodeView(Initial, go.getOptionsObject().selectedLeaderName());
			// No default (Random) ... So Initial!
			setHistoryCodeView(Default, go.getOptionsObject().selectedLeaderName());
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getSessionObject().galaxy().empire(0).leader().name();
		}
		
		@Override public void putToGame(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedLeaderName(userOption);
			go.getSessionObject().galaxy().empire(0).leader().setName(userOption);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedLeaderName();
		}
		
		@Override public void putToGUI(ClientClasses go, String userOption) {
			go.getOptionsObject().selectedLeaderName(userOption);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
}