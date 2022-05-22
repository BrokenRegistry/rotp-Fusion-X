
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
import br.profileManager.src.main.java.ValidationCriteria;
import br.profileManager.src.main.java.Valid_Boolean;
import br.profileManager.src.main.java.Valid_Integer;
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
	}

	// ==============================================================
	// ALWAYS STAR GATES
	//
	static class Valid_AlwaysStarGates extends Valid_Boolean {

		Valid_AlwaysStarGates() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	static class AlwaysStarGates extends 
			Abstract_Parameter <Boolean, Valid_AlwaysStarGates, ClientClasses> {

		AlwaysStarGates(ClientClasses go) {
			super( "ALWAYS STAR GATES", new Valid_AlwaysStarGates());
			setInitialValue(UserPreferences.alwaysStarGates());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.alwaysStarGates();
		}

		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.alwaysStarGates();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"------------- Modnar's Options -------------" + NL +
				" ");
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// ALWAYS THORIUM
	//
	static class Valid_AlwaysThorium extends Valid_Boolean {

		Valid_AlwaysThorium() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	static class AlwaysThorium extends 
			Abstract_Parameter <Boolean, Valid_AlwaysThorium, ClientClasses> {

		AlwaysThorium(ClientClasses go) { 
			super("ALWAYS THORIUM", new Valid_AlwaysThorium());
			setInitialValue(UserPreferences.alwaysThorium());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.alwaysThorium();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.alwaysThorium();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// CHALLENGE MODE
	//
	static class Valid_ChallengeMode extends Valid_Boolean {

		Valid_ChallengeMode() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	static class ChallengeMode extends 
			Abstract_Parameter <Boolean, Valid_ChallengeMode, ClientClasses> {

		ChallengeMode(ClientClasses go) {
			super("CHALLENGE MODE", new Valid_ChallengeMode());
			setInitialValue(UserPreferences.challengeMode());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.challengeMode();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.challengeMode();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	
	// ==============================================================
	// BATTLE SCOUT
	//
	static class Valid_BattleScouts extends Valid_Boolean {

		Valid_BattleScouts() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	static class BattleScouts extends 
			Abstract_Parameter <Boolean, Valid_BattleScouts, ClientClasses> {

		BattleScouts(ClientClasses go) {
			super("BATTLE SCOUT", new Valid_BattleScouts());
			setInitialValue(UserPreferences.battleScout());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.battleScout();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}		
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.battleScout();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// COMPANION WORLDS
	//
	static class Valid_CompanionWorlds extends Valid_Integer {

		Valid_CompanionWorlds() {
			super();
			init();
		}
		
		Valid_CompanionWorlds(List<Integer> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
			setLimits(new Integer[] {0 , 4});
			setDefaultRandomLimits(new Integer[] {0 , 4});
		}
	}
	
	static class CompanionWorlds extends 
			Abstract_Parameter <Integer, Valid_CompanionWorlds, ClientClasses> {

		CompanionWorlds(ClientClasses go) {
			super("COMPANION WORLDS", new Valid_CompanionWorlds());
			setInitialValue(UserPreferences.companionWorlds());
		}
		
		@Override public Integer getFromGame (ClientClasses go) {
			return UserPreferences.companionWorlds();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {

		}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return UserPreferences.companionWorlds();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// RANDOM TECH START
	//
	static class Valid_RandomTechStart extends Valid_Boolean {

		Valid_RandomTechStart() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	static class RandomTechStart extends 
			Abstract_Parameter <Boolean, Valid_RandomTechStart, ClientClasses> {

		RandomTechStart(ClientClasses go) { 
			super("RANDOM TECH START", new Valid_RandomTechStart());
			setInitialValue(UserPreferences.randomTechStart());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.randomTechStart();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.randomTechStart();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// CUSTOM DIFFICULTY
	//
	static class Valid_CustomDifficulty extends Valid_Integer {

		Valid_CustomDifficulty() {
			super();
			init();
		}
		
		Valid_CustomDifficulty(List<Integer> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
			setLimits(new Integer[] {20 , 500});
			setDefaultRandomLimits(new Integer[] {20 , 500});
		}
	}
	
	static class CustomDifficulty extends
			Abstract_Parameter <Integer, Valid_CustomDifficulty, ClientClasses> {

		CustomDifficulty(ClientClasses go) { 
			super("CUSTOM DIFFICULTY", new Valid_CustomDifficulty());
			setInitialValue(UserPreferences.customDifficulty());
		}
		@Override
		public Integer getFromGame (ClientClasses go) {
			return UserPreferences.customDifficulty();
		}
		@Override
		public void putToGame(ClientClasses go, Integer codeView) {

		}		
		@Override
		public Integer getFromUI (ClientClasses go) {
			return UserPreferences.customDifficulty();
		}
		@Override
		public void putToGUI(ClientClasses go, Integer codeView) {
//			go.(codeView);
		}
		@Override
		public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		@Override
		public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	// ==============================================================
	// DYNAMIC DIFFICULTY
	//
	static class Valid_DynamicDifficulty extends Valid_Boolean {

		Valid_DynamicDifficulty() {
			super();
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(true)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
   static class DynamicDifficulty extends
			Abstract_Parameter <Boolean, Valid_DynamicDifficulty, ClientClasses> {

		DynamicDifficulty(ClientClasses go) {
			super("DYNAMIC DIFFICULTY", new Valid_DynamicDifficulty());
			setInitialValue(UserPreferences.dynamicDifficulty());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return UserPreferences.dynamicDifficulty();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {

		}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return UserPreferences.dynamicDifficulty();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
//			go.(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
//			go.(getInitialCodeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
}
