
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

import static br.profileManager.src.main.java.WriteUtil.History.*;

import java.util.List;

import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Parameter;
import br.profileManager.src.main.java.Valid_Boolean;
import br.profileManager.src.main.java.Valid_Float;
import br.profileManager.src.main.java.Valid_Integer;
import rotp.model.empires.Empire;
import rotp.ui.UserPreferences;

/**
 * @author BrokenRegistry
 * For Parameters in Modnar GUI
 */
class Group_Modnar extends  Abstract_Group <ClientClasses> {

	Group_Modnar(ClientClasses go) {
	   super(go);
	}
	@Override
	protected void initSettingList(ClientClasses go) {
		addParameter(new AlwaysStarGates(go));
		addParameter(new AlwaysThorium(go));
		addParameter(new ChallengeMode(go));
		addParameter(new BattleScouts(go));
		addParameter(new CompanionWorlds(go));
		addParameter(new RandomTechStart(go));
		addParameter(new CustomDifficulty(go));
		addParameter(new DynamicDifficulty(go));
		addParameter(new MissileSizeModifier(go));
	}

	// ==============================================================
	// ALWAYS STAR GATES
	//
	static class AlwaysStarGates extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		private String StarGateId = "Stargate:0";
		private int StarGateCategory = 4;

		AlwaysStarGates(ClientClasses go) {
			super( "ALWAYS STAR GATES", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.alwaysStarGates());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			for (Empire empire : go.getSessionObject().galaxy().empires()) {
				List<String> techList = empire.tech()
						.category(StarGateCategory).possibleTechs();			
				if (!techList.contains(StarGateId)) {
					return false;
				}
			}
			return true;
		}

		@Override public void putToGame(ClientClasses go, Boolean codeView) {
			for (Empire empire : go.getSessionObject().galaxy().empires()) {
				empire.tech().category(StarGateCategory).addPossibleTech(StarGateId);
			}
		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.alwaysStarGates();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setAlwaysStarGates(codeView);;
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"------------- Modnar's Options -------------" + NL +
				" ");
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// ALWAYS THORIUM
	//
	static class AlwaysThorium extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		private String ThoriumCellId = "FuelRange:8";
		private int ThoriumCellCategory = 4;

		AlwaysThorium(ClientClasses go) { 
			super("ALWAYS THORIUM", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.alwaysThorium());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			for (Empire empire : go.getSessionObject().galaxy().empires()) {
				List<String> techList = empire.tech()
						.category(ThoriumCellCategory).possibleTechs();
				if (!techList.contains(ThoriumCellId)) {
					return false;
				}
			}
			return true;
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {
			for (Empire empire : go.getSessionObject().galaxy().empires()) {
				empire.tech().category(ThoriumCellCategory).addPossibleTech(ThoriumCellId);
			}
		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.alwaysThorium();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setAlwaysThorium(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// CHALLENGE MODE
	//
	static class ChallengeMode extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		ChallengeMode(ClientClasses go) {
			super("CHALLENGE MODE", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.challengeMode());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return go.getSessionObject().galaxy().empire(0).isChallengeMode();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.challengeMode();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setChallengeMode(codeView);
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// BATTLE SCOUT
	//
	static class BattleScouts extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		BattleScouts(ClientClasses go) {
			super("BATTLE SCOUT", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.battleScout());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return null; // There is no way to know!
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {}		
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.battleScout();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setBattleScout(codeView);
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// COMPANION WORLDS
	//
	static class CompanionWorlds extends 
			Abstract_Parameter <Integer, Valid_Integer, ClientClasses> {

		CompanionWorlds(ClientClasses go) {
			super("COMPANION WORLDS", new Valid_Integer());
			setHistoryCodeView(Initial, UserPreferences.companionWorlds());
			setHistoryCodeView(Default, 0); // MODNAR DEFAULT
			setLimits(0 , 4);
			setDefaultRandomLimits(0 , 4);
		}
		
		@Override public Integer getFromGame (ClientClasses go) {
			return go.getSessionObject().galaxy().empire(0).getCompanionWorldsNumber();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return UserPreferences.companionWorlds();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			UserPreferences.setCompanionWorlds(codeView);
		}

		@Override public void initComments() {}
	}

	// ==============================================================
	// RANDOM TECH START
	//
	static class RandomTechStart extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		RandomTechStart(ClientClasses go) { 
			super("RANDOM TECH START", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.randomTechStart());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return null; // There is no way to know!
//			return UserPreferences.randomTechStart();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.randomTechStart();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setRandomTechStart(codeView);
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// CUSTOM DIFFICULTY
	//
	static class CustomDifficulty extends
			Abstract_Parameter <Integer, Valid_Integer, ClientClasses> {

		CustomDifficulty(ClientClasses go) { 
			super("CUSTOM DIFFICULTY", new Valid_Integer());
			setHistoryCodeView(Initial, UserPreferences.customDifficulty());
			setHistoryCodeView(Default, 100); // MODNAR DEFAULT
			setLimits(20 , 500);
			setDefaultRandomLimits(20 , 500);
		}

		@Override public Integer getFromGame (ClientClasses go) {
			return UserPreferences.customDifficulty(); // Dynamic: Same as UserPreferences
		}

		@Override public void putToGame(ClientClasses go, Integer codeView) {
			UserPreferences.setCustomDifficulty(codeView); // Dynamic: Same as UserPreferences
		}		

		@Override public Integer getFromUI (ClientClasses go) {
			return UserPreferences.customDifficulty();
		}

		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			UserPreferences.setCustomDifficulty(codeView);
		}

		@Override public void initComments() {
			setBottomComments(DYNAMIC_PARAMETER);
		}
	}

	// ==============================================================
	// DYNAMIC DIFFICULTY
	//
   static class DynamicDifficulty extends
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		DynamicDifficulty(ClientClasses go) {
			super("DYNAMIC DIFFICULTY", new Valid_Boolean());
			setHistoryCodeView(Initial, UserPreferences.dynamicDifficulty());
			setHistoryCodeView(Default, false); // MODNAR DEFAULT
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.dynamicDifficulty(); // Dynamic: Same as UserPreferences
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {
			UserPreferences.setDynamicDifficulty(codeView); // Dynamic: Same as UserPreferences
		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.dynamicDifficulty();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			UserPreferences.setDynamicDifficulty(codeView);
		}

		@Override public void initComments() {
			setBottomComments(DYNAMIC_PARAMETER);
		}
	}

	// ==============================================================
	// MISSILE SIZE MODIFIER
	//
	static class MissileSizeModifier extends
			Abstract_Parameter <Float, Valid_Float, ClientClasses> {

		MissileSizeModifier(ClientClasses go) { 
			super("MISSILE SIZE MODIFIER", new Valid_Float());
			setHistoryCodeView(Initial, UserPreferences.missileSizeModifier());
			setHistoryCodeView(Default, 0.66f); // XILMI DEFAULT
			setLimits(0.1f , 1.0f);
			setDefaultRandomLimits(0.1f , 1.0f);
		}

		@Override public Float getFromGame (ClientClasses go) {
			return UserPreferences.missileSizeModifier(); // Dynamic: Same as UserPreferences
		}

		@Override public void putToGame(ClientClasses go, Float codeView) {
			UserPreferences.setMissileSizeModifier(codeView); // Dynamic: Same as UserPreferences
		}		

		@Override public Float getFromUI (ClientClasses go) {
			return UserPreferences.missileSizeModifier();
		}

		@Override public void putToGUI(ClientClasses go, Float codeView) {
			UserPreferences.setMissileSizeModifier(codeView);
		}

		@Override public void initComments() {
			setBottomComments(DYNAMIC_PARAMETER);
		}
	}
}
