
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
import br.profileManager.src.main.java.T_Boolean;
import br.profileManager.src.main.java.T_Float;
import br.profileManager.src.main.java.T_Integer;
import br.profileManager.src.main.java.T_String;
import mod.br.Galaxy.GalaxySpacing;
import mod.br.Galaxy.StarsOptions;
import static br.profileManager.src.main.java.WriteUtil.History.*;


/**
 * @author BrokenRegistry
 * For Parameters without GUI from BrokenRegistry Mods
 */
public class Group_BrokenRegistry extends  AbstractGroup <ClientClasses> {
	
	Group_BrokenRegistry(ClientClasses go) {
	   super(go);
	}
	@Override
	protected void initSettingList(ClientClasses go) {
		addParameter(new MaximizeEmpiresSpacing(go));
		addParameter(new PreferedStarsPerEmpire(go));
		addParameter(new MinStarsPerEmpire(go));
		addParameter(new NoPlanetMultiplier(go));
		addParameter(new OpponentRaceList(go));
	}

	// ========================================================================
	// MAXIMIZE EMPIRES SPACING
	//
	static class MaximizeEmpiresSpacing extends 
			AbstractParameter <Boolean, Validation<Boolean>, ClientClasses> {
	 
		MaximizeEmpiresSpacing(ClientClasses go) {
			super( "MAXIMIZE EMPIRES SPACING",
					new Validation<Boolean>(
							new T_Boolean(GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING)));

			setHistoryCodeView(Default, false); // BR DEFAULT
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public AbstractT<Boolean> getFromGame (ClientClasses go) {
			return new T_Boolean(GalaxySpacing.isMaximizeEmpiresSpacing());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Boolean> value) {}
		
		@Override public AbstractT<Boolean> getFromUI (ClientClasses go) {
			return new T_Boolean(GalaxySpacing.isMaximizeEmpiresSpacing());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Boolean> value) {
			GalaxySpacing.setMaximizeEmpiresSpacing(value.codeView());
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"------------- Broken Registry Options -------------" + NL +
				" ");
		}
	}
	// ========================================================================
	// PREF STARS PER EMPIRE
	//
	static class PreferedStarsPerEmpire extends 
			AbstractParameter <Integer, Validation<Integer>, ClientClasses> {

		PreferedStarsPerEmpire(ClientClasses go) { 
			super( "PREF STARS PER EMPIRE",
					new Validation<Integer>(
							new T_Integer(GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE)));

			setHistoryCodeView(Default, 16); // BR DEFAULT
			setLimits(0 , 1000000);
			setDefaultRandomLimits(16 , 24);
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public AbstractT<Integer> getFromGame (ClientClasses go) {
			return new T_Integer(GalaxySpacing.getPreferedStarsPerEmpire());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Integer> value) {}
		
		@Override public AbstractT<Integer> getFromUI (ClientClasses go) {
			return new T_Integer(GalaxySpacing.getPreferedStarsPerEmpire());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Integer> value) {
			GalaxySpacing.setPreferedStarsPerEmpire(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	
	// ========================================================================
	// MIN STARS PER EMPIRE
	//
	static class MinStarsPerEmpire extends 
			AbstractParameter <Integer, Validation<Integer>, ClientClasses> {

		MinStarsPerEmpire(ClientClasses go) {
			super( "MIN STARS PER EMPIRE",
					new Validation<Integer>(
							new T_Integer(GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE)));

			setHistoryCodeView(Default, 8); // BR DEFAULT
			setLimits(0 , 1000000);
			setDefaultRandomLimits(4 , 16);
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public AbstractT<Integer> getFromGame (ClientClasses go) {
			return new T_Integer(GalaxySpacing.getMinStarsPerEmpire());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Integer> value) {}
		
		@Override public AbstractT<Integer> getFromUI (ClientClasses go) {
			return new T_Integer(GalaxySpacing.getMinStarsPerEmpire());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Integer> value) {
			GalaxySpacing.setMinStarsPerEmpire(value.codeView());
		}
		
		@Override public void initComments() {}

	}
	// ========================================================================
	// NO PLANET MULTIPLIER
	//
	static class NoPlanetMultiplier extends 
			AbstractParameter <Float, Validation<Float>, ClientClasses> {

		NoPlanetMultiplier(ClientClasses go) { 
			super( "NO PLANET MULTIPLIER", 
					new Validation<Float>(
							new T_Float(StarsOptions.DEFAULT_NO_PLANET_MULTIPLIER)));

			setHistoryCodeView(Default, 1f); // BR DEFAULT
			setLimits(0f, 1000000f);
			setDefaultRandomLimits(0f, 2f);
		}
		
		// ------------------------------------------------
		// Overrider
		//
		@Override public AbstractT<Float> getFromGame (ClientClasses go) {
			return new T_Float(StarsOptions.getNoPlanetMultiplier());
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Float> value) {}
		
		@Override public AbstractT<Float> getFromUI (ClientClasses go) {
			return new T_Float(StarsOptions.getNoPlanetMultiplier());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Float> value) {
			StarsOptions.setNoPlanetMultiplier(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	
	// ==============================================================
	// OPPONENTS RACE LIST
		
	static class OpponentRaceList extends
			AbstractParameter <String, Validation<String>, ClientClasses> {

		private static final boolean isEntryList = true;
	    // ==================================================
	    // Constructors and initializers
	    //
		OpponentRaceList(ClientClasses go) { 
			super("OPPONENTS RACE LIST",
					new Validation<String>(
							new T_String(), 
							go.options().startingRaceOptions(),
							isEntryList));
			
			T_String defaultValue = new T_String(go.options().startingRaceOptions());
			setHistory(Initial, defaultValue);
			setHistory(Default, defaultValue);
			//getDataValidation().getValidationCriteria().isRandomAllowed(false);
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(); // No really possible
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<String> value) {}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			// List<String> raceList = RaceFilter.getRaceList();
			return new T_String(); // TODO
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			// TODO
		}
		
		@Override public void initComments() {}
	}
}
