
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
import br.profileManager.src.main.java.Valid_Integer;
import br.profileManager.src.main.java.Valid_String;
import br.profileManager.src.main.java.ValidationCriteria;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @author BrokenRegistry
 * For Parameters in Galaxy GUI
 */
public class Group_Galaxy extends  Abstract_Group <ClientClasses> {

	Group_Galaxy(ClientClasses go) {
	   super(go);
	}
	@Override
	protected void initSettingList(ClientClasses go) {
		addParameter(new GalaxyShape(go));
		addParameter(new GalaxySize(go));
		addParameter(new Difficulty(go));
		addParameter(new OpponentAI(go));
		addParameter(new NbOpponent(go));	
	}

	// ==============================================================
	// GALAXY SHAPE
	//
	static class Valid_GalaxyShape extends Valid_String {

		Valid_GalaxyShape() {
			super();
			init();
		}
		
		Valid_GalaxyShape(List<String> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria());
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	// ========== Parameter Section ==========
	//
	static class GalaxyShape extends 
			Abstract_Parameter <String, Valid_GalaxyShape, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		GalaxyShape(ClientClasses go) { 
			super("GALAXY SHAPE",
					new Valid_GalaxyShape(go.getGuiObject().galaxyShapeOptions()));
			setHistoryCodeView(Initial, go.getGuiObject().selectedGalaxyShape());
			setHistoryUserView(Default, "Rectangle"); // Ray
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getGuiObject().selectedGalaxyShape();
		}
		
		@Override public void putToGame(ClientClasses go, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedGalaxyShape();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedGalaxyShape(codeView);
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"------------- Galaxy Options -------------" + NL +
				" ");
		}
	}
	// ==============================================================
	// GALAXY SIZE
	//
	static class Valid_GalaxySize extends Valid_String {

		Valid_GalaxySize() {
			super();
			init();
		}
		
		Valid_GalaxySize(List<String> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria());
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	// ========== Parameter Section ==========
	//
	static class GalaxySize extends 
			Abstract_Parameter <String, Valid_GalaxySize, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		GalaxySize(ClientClasses go) {
			super("GALAXY SIZE",
					new Valid_GalaxySize(go.getGuiObject().galaxySizeOptions()));
		setHistoryCodeView(Initial, go.getGuiObject().selectedGalaxySize());
		setHistoryUserView(Default, "Small"); // Ray
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getGuiObject().selectedGalaxySize();
		}
		
		@Override public void putToGame(ClientClasses go, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedGalaxySize();
		}
		@Override
		public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedGalaxySize(codeView);
		}
		
		@Override public void initComments() {}
	}
	 
	// ==============================================================
	// DIFFICULTY
	//
	static class Valid_Difficulty extends Valid_String {

		Valid_Difficulty() {
			super();
			init();
		}
		
		Valid_Difficulty(List<String> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria());
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	// ========== Parameter Section ==========
	//
	static class Difficulty extends 
			Abstract_Parameter <String, Valid_Difficulty, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		Difficulty(ClientClasses go) {
			super("DIFFICULTY",
				  new Valid_Difficulty(go.getGuiObject().gameDifficultyOptions()));
		setHistoryCodeView(Initial, go.getGuiObject().selectedGameDifficulty());
		setHistoryUserView(Default, "Easy"); // Ray
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getGuiObject().selectedGameDifficulty();
		}
		
		@Override public void putToGame(ClientClasses go, String codeView) {
//			go.getGuiObject().selectedGameDifficulty(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedGameDifficulty();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedGameDifficulty(codeView);
		}
		
		@Override public void initComments() {
//					setBottomComments(AVAILABLE_FOR_CHANGE);
			}
	}
	// ==============================================================
	// OPPONENT AI
	//
	static class Valid_OpponentAI extends Valid_String {

		Valid_OpponentAI() {
			super();
			init();
		}
		
		Valid_OpponentAI(List<String> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria());
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	// ========== Parameter Section ==========
	//
	static class OpponentAI extends 
			Abstract_Parameter <String, Valid_OpponentAI, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		OpponentAI(ClientClasses go) {
			super("OPPONENT AI", 
					new Valid_OpponentAI(go.getGuiObject().opponentAIOptions()));
			setHistoryCodeView(Initial, go.getGuiObject().selectedOpponentAIOption());
			setHistoryUserView(Default, "Base"); // Ray
		}
		
	    // ========== Overriders ==========
	    //
		@Override public String getFromGame (ClientClasses go) {
			return go.getGuiObject().selectedOpponentAIOption();
		}
		
		@Override public void putToGame(ClientClasses go, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedOpponentAIOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedOpponentAIOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// NB OPPONENTS
	//
	static class Valid_NbOpponent extends Valid_Integer {

		Valid_NbOpponent() {
			super();
			initCriteria();
			initNbOpponent();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false));
		}
		
		private void initNbOpponent() {
		}
	}
	
	// ========== Parameter Section ==========
	//
   static class NbOpponent extends
			Abstract_Parameter <Integer, Valid_NbOpponent, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		NbOpponent(ClientClasses go) {
			super("NB OPPONENTS", new Valid_NbOpponent());
			setHistoryCodeView(Initial, go.getGuiObject().selectedNumberOpponents());
			setHistoryCodeView(Default, 3); // Ray
			Integer min = 0;
			Integer max = go.getGuiObject().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
		}

	    // ========== Overriders ==========
	    //
		@Override public Integer getFromGame (ClientClasses go) {
			return go.getGuiObject().selectedNumberOpponents();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {

		}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedNumberOpponents();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			// the limits may have changed from previous settings
			Integer min = 0;
			Integer max = go.getGuiObject().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
			go.getGuiObject().selectedNumberOpponents(Math.min(max, codeView));
		}

		@Override public void initComments() {}
	}
}
