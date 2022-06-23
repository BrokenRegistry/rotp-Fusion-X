
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

import static br.profileManager.src.main.java.Validation.History.Default;
import static br.profileManager.src.main.java.Validation.History.Initial;

import java.util.ArrayList;
import java.util.List;

import br.profileManager.src.main.java.AbstractGroup;
import br.profileManager.src.main.java.AbstractParameter;
import br.profileManager.src.main.java.AbstractT;
import br.profileManager.src.main.java.T_Integer;
import br.profileManager.src.main.java.T_String;
import br.profileManager.src.main.java.Validation;
import mod.br.AddOns.RaceFilter;
import rotp.model.empires.Empire;
import rotp.model.game.IGameOptions;

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
		addParameter(new GuiRaceFilter(go));
		addParameter(new GameRaceFilter(go));
		addParameter(new GuiPresetOpponent(go));
		addParameter(new StartPresetOpponent(go));
	}

	// ========== Common Methods ==========
	//
	private static List<String> getOptionList(ClientClasses go) {
		List<String> list = go.newOptions().startingRaceOptions();
		list.add("null");
		list.add("gui");
		list.add("game");
		return list;
	}

	private static List<String> getFromGUI(IGameOptions options) {
		List<String> list = new ArrayList<String>() ;
		String race;
		int lim = options.selectedNumberOpponents();
		for (int i=0; i<lim; i++) {
			race = options.selectedOpponentRace(i);
			if (race == null) {
				race = "null";
			}
			list .add(race);
		}
		return list;
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
							new T_String(go.newOptions().selectedGalaxyShape()),
							go.newOptions().galaxyShapeOptions()));			

			setHistory(Default, "Rectangle"); // Ray Choice
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.newOptions().selectedGalaxyShape());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.newOptions().selectedGalaxyShape());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.newOptions().selectedGalaxyShape(value.getCodeView());
			go.options().selectedGalaxyShape(value.getCodeView());
//			go.newOptions().galaxyShape().quickGenerate();
//			go.options().galaxyShape().quickGenerate();
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
							new T_String(go.newOptions().selectedGalaxySize()),
							go.newOptions().galaxySizeOptions()));			

		setHistory(Default, "Small"); // Ray Choice
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.newOptions().selectedGalaxySize());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.newOptions().selectedGalaxySize());
		}
		@Override
		public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.newOptions().selectedGalaxySize(value.getCodeView());
			go.options().selectedGalaxySize(value.getCodeView());
//			go.newOptions().galaxyShape().quickGenerate();
//			go.options().galaxyShape().quickGenerate();
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
							new T_String(go.newOptions().selectedGameDifficulty()),
							go.newOptions().gameDifficultyOptions()));			

		setHistory(Default, "Easy"); // Ray Choice
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.newOptions().selectedGameDifficulty());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {
//			go.getGuiObject().selectedGameDifficulty(codeView);
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.newOptions().selectedGameDifficulty());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.newOptions().selectedGameDifficulty(value.getCodeView());
			go.options().selectedGameDifficulty(value.getCodeView());
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
							new T_String(go.newOptions().selectedOpponentAIOption()),
							go.newOptions().opponentAIOptions()));			

			setHistory(Default, "Base"); // Ray Choice
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.newOptions().selectedOpponentAIOption());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.newOptions().selectedOpponentAIOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.newOptions().selectedOpponentAIOption(value.getCodeView());
			go.options().selectedOpponentAIOption(value.getCodeView());
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
							new T_Integer(go.newOptions().selectedNumberOpponents())));			

			setHistoryCodeView(Default, 3); // Ray Choice
			Integer min = 0;
			Integer max = go.newOptions().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
		}

		// ========== Overriders ==========
		//
		@Override public AbstractT<Integer> getFromGame (ClientClasses go) {
			return new T_Integer(go.newOptions().selectedNumberOpponents());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Integer> value) {}
		
		@Override public AbstractT<Integer> getFromUI (ClientClasses go) {
			return new T_Integer(go.newOptions().selectedNumberOpponents());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Integer> value) {
			// the limits may have changed from previous settings
			Integer min = 0;
			Integer max = go.newOptions().maximumOpponentsOptions();
			setLimits(min, max);
			setDefaultRandomLimits(1, max);
			go.newOptions().selectedNumberOpponents(Math.min(max, value.getCodeView()));
			go.options().selectedNumberOpponents(Math.min(max, value.getCodeView()));
//			go.newOptions().galaxyShape().quickGenerate();
//			go.options().galaxyShape().quickGenerate();
		}

		@Override public void initComments() {}
	}
   
	// ==============================================================
	// GUI RACE FILTER
	//
	static class GuiRaceFilter extends
			AbstractParameter <String, Validation<String>, ClientClasses> {

		// ==================================================
		// Constructors and initializers
		//
		GuiRaceFilter(ClientClasses go) { 
			super("GUI RACE FILTER",
					new Validation<String>(
							new T_String(go.newOptions().selectedPlayerRace()), 
							go.newOptions().startingRaceOptions()));
			
			List<String> defaultValue = go.newOptions().startingRaceOptions();
			setHistoryCodeView(Initial, defaultValue); // set Current too
			setHistoryCodeView(Default, defaultValue);
			RaceFilter.defaultRaceList(defaultValue);
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(); // Not really possible
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String().setFromCodeView(RaceFilter.selectedGuiRaceFilter());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			RaceFilter.selectedGuiRaceFilter(value.getCodeList());
		}
		
		@Override public void initComments() {}
	}

	// ==============================================================
	// GAME RACE FILTER
	//
	static class GameRaceFilter extends
			AbstractParameter <String, Validation<String>, ClientClasses> {

	    // ==================================================
	    // Constructors and initializers
	    //
		GameRaceFilter(ClientClasses go) { 
			super("GAME RACE FILTER",
					new Validation<String>(
							new T_String(go.newOptions().selectedPlayerRace()), 
							go.newOptions().startingRaceOptions()));
			
			List<String> defaultValue = go.newOptions().startingRaceOptions();
			setHistoryCodeView(Initial, defaultValue); // set Current too
			setHistoryCodeView(Default, defaultValue);
			RaceFilter.defaultRaceList(defaultValue);
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(); // Not really possible
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String().setFromCodeView(RaceFilter.selectedGameRaceFilter());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			RaceFilter.selectedGameRaceFilter(value.getCodeList());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// GUI PRESET OPPONENT
	//
	// GuiRaceFilter is required
	// 
	static class GuiPresetOpponent extends
			AbstractParameter <String, Valid_RaceList, ClientClasses> {

		// ==================================================
		// Constructors and initializers
		//
		GuiPresetOpponent(ClientClasses go) { 
			super("GUI PRESET OPPONENT",
					new Valid_RaceList(
							new T_String(go.newOptions().selectedPlayerRace())
							, getOptionList(go)
					)
			);
			
			List<String> defaultValue = go.newOptions().startingRaceOptions();
			setHistoryCodeView(Initial, defaultValue); // set Current too
			setHistoryCodeView(Default, defaultValue);
			// remove the "null" from randomize
			setDefaultRandomLimits(defaultValue.get(0)
					, defaultValue.get(defaultValue.size()-1));
			setLimits(defaultValue.get(0)
					, defaultValue.get(defaultValue.size()-1));
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			List<String> list = new ArrayList<String>();
			for (Empire empire : go.session().galaxy().empires()) {
				list.add(empire.raceName());
			}
			list.remove(0); // remove player
			return new T_String().setFromCodeView(list);
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String().setFromCodeView(getFromGUI(go.newOptions()));
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			String[] selectedOpponents = ((Valid_RaceList) getValidation())
										.analyze(go, value.getUserList()
												, IGameOptions.MAX_OPPONENT_TYPE
												, false);
			int i=0;
			for (String race : selectedOpponents) {
				go.newOptions().selectedOpponentRace(i, race);
				go.options().selectedOpponentRace(i, race);
				i++;
			}
		}
		
		@Override public void initComments() {}		
	}
	// ==============================================================
	// START PRESET OPPONENT
	//
	// GuiRaceFilter is required
	// 
	/**
	 * Management of random opponent filling at start
	 */
	public static class StartPresetOpponent extends
			AbstractParameter <String, Valid_RaceList, ClientClasses> {

		// ==================================================
		// Constructors and initializers
		//
		StartPresetOpponent(ClientClasses go) { 
			super("START PRESET OPPONENT",
					new Valid_RaceList(
							new T_String(go.newOptions().selectedPlayerRace())
							, getOptionList(go)
					)
			);
			
			List<String> defaultValue = go.newOptions().startingRaceOptions();
			setHistoryCodeView(Initial, defaultValue); // set Current too
			setHistoryCodeView(Default, defaultValue);
			// remove the "null" from randomize
			setDefaultRandomLimits(defaultValue.get(0)
					, defaultValue.get(defaultValue.size()-1));
			setLimits(defaultValue.get(0)
					, defaultValue.get(defaultValue.size()-1));
		}
		
		// ========== Overriders ==========
		//
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			List<String> list = new ArrayList<String>();
			for (Empire empire : go.session().galaxy().empires()) {
				list.add(empire.raceName());
			}
			list.remove(0); // remove player
			return new T_String().setFromCodeView(list);
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String().setFromCodeView(getFromGUI(go.newOptions()));
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			String[] selectedOpponents = ((Valid_RaceList) getValidation())
										.analyze(go, value.getUserList()
												, IGameOptions.MAX_OPPONENT_TYPE
												, true);
			RaceFilter.startOpponentRace(selectedOpponents);
		}
		
		@Override public void initComments() {}
		// ========== Other Methods ==========
		//
		/**
		 * @param go the ClientClass
		 */
		public void loadOpponents(ClientClasses go) {
			String[] selectedOpponents = RaceFilter.startOpponentRace();
			if (selectedOpponents != null) {
				int i=0;
				for (String race : selectedOpponents) {
					go.newOptions().selectedOpponentRace(i, race);
					go.options().selectedOpponentRace(i, race);
					i++;
				}
			}
		}
	}
}
