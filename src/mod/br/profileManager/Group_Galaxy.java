
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

import br.profileManager.src.main.java.AbstractGroup;
import br.profileManager.src.main.java.AbstractParameter;
import br.profileManager.src.main.java.AbstractT;
import br.profileManager.src.main.java.Validation;
import br.profileManager.src.main.java.T_Integer;
import br.profileManager.src.main.java.T_String;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @author BrokenRegistry
 * For Parameters in Galaxy GUI
 */
public class Group_Galaxy extends  AbstractGroup <ClientClasses> {

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
	static class GalaxyShape extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		GalaxyShape(ClientClasses go) { 
			super("GALAXY SHAPE",
					new Validation<String>(
							new T_String(go.options().selectedGalaxyShape()),
							go.options().galaxyShapeOptions()));			

			setHistory(Default, "Rectangle"); // Ray Choice
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedGalaxyShape());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedGalaxyShape());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedGalaxyShape(value.codeView());
			go.option2().selectedGalaxyShape(value.codeView());
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
	static class GalaxySize extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		GalaxySize(ClientClasses go) {
			super("GALAXY SIZE",
					new Validation<String>(
							new T_String(go.options().selectedGalaxySize()),
							go.options().galaxySizeOptions()));			

		setHistory(Default, "Small"); // Ray Choice
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedGalaxySize());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedGalaxySize());
		}
		@Override
		public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedGalaxySize(value.codeView());
			go.option2().selectedGalaxySize(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	 
	// ==============================================================
	// DIFFICULTY
	//
	static class Difficulty extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		Difficulty(ClientClasses go) {
			super("DIFFICULTY",
					new Validation<String>(
							new T_String(go.options().selectedGameDifficulty()),
							go.options().gameDifficultyOptions()));			

		setHistory(Default, "Easy"); // Ray Choice
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedGameDifficulty());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {
//			go.getGuiObject().selectedGameDifficulty(codeView);
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedGameDifficulty());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedGameDifficulty(value.codeView());
			go.option2().selectedGameDifficulty(value.codeView());
		}
		
		@Override public void initComments() {
//					setBottomComments(AVAILABLE_FOR_CHANGE);
			}
	}
	// ==============================================================
	// OPPONENT AI
	//
	static class OpponentAI extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		OpponentAI(ClientClasses go) {
			super("OPPONENT AI", 
					new Validation<String>(
							new T_String(go.options().selectedOpponentAIOption()),
							go.options().opponentAIOptions()));			

			setHistory(Default, "Base"); // Ray Choice
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedOpponentAIOption());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedOpponentAIOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedOpponentAIOption(value.codeView());
			go.option2().selectedOpponentAIOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// NB OPPONENTS
	//
   static class NbOpponent extends
			AbstractParameter <Integer, Validation<Integer>, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		NbOpponent(ClientClasses go) {
			super("NB OPPONENTS", 
					new Validation<Integer>(
							new T_Integer(go.options().selectedNumberOpponents())));			

			setHistoryCodeView(Default, 3); // Ray Choice
			Integer min = 0;
			Integer max = go.options().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
		}

	    // ========== Overriders ==========
	    //
		@Override public AbstractT<Integer> getFromGame (ClientClasses go) {
			return new T_Integer(go.options().selectedNumberOpponents());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Integer> value) {}
		
		@Override public AbstractT<Integer> getFromUI (ClientClasses go) {
			return new T_Integer(go.options().selectedNumberOpponents());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Integer> value) {
			// the limits may have changed from previous settings
			Integer min = 0;
			Integer max = go.options().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
			go.options().selectedNumberOpponents(Math.min(max, value.codeView()));
			go.option2().selectedNumberOpponents(Math.min(max, value.codeView()));
		}

		@Override public void initComments() {}
	}
}
