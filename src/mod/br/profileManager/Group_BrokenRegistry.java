
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

import static br.profileManager.src.main.java.Validation.History.Current;
import static br.profileManager.src.main.java.Validation.History.Default;
import static br.profileManager.src.main.java.Validation.History.Initial;

import br.profileManager.src.main.java.AbstractGroup;
import br.profileManager.src.main.java.AbstractParameter;
import br.profileManager.src.main.java.AbstractT;
import br.profileManager.src.main.java.PMconfig;
import br.profileManager.src.main.java.T_Boolean;
import br.profileManager.src.main.java.T_Float;
import br.profileManager.src.main.java.T_Integer;
import br.profileManager.src.main.java.Validation;
import mod.br.Galaxy.GalaxySpacing;
import mod.br.Galaxy.StarsOptions;


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
			GalaxySpacing.setMaximizeEmpiresSpacing(value.getCodeView());
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
			GalaxySpacing.setPreferedStarsPerEmpire(value.getCodeView());
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
			GalaxySpacing.setMinStarsPerEmpire(value.getCodeView());
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
							new T_Float(StarsOptions.defaultNoPlanetMultiplier)));

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
			StarsOptions.setNoPlanetMultiplier(value.getCodeView());
		}
		
		@Override public void initComments() {}
	}

	// ==============================================================
	// STAR PROBABILITY
	//
	static class StarProbability extends 
			AbstractParameter <Float, Valid_ProbabilityDensity, ClientClasses> {

		private static final Float DEFAULT = StarsOptions.DefaultProbabilityMultiplier;
	    // ========== Constructors and initializer ==========
	    //
		StarProbability(ClientClasses go) {
			super("STAR PROBABILITY"
					, new Valid_ProbabilityDensity(DEFAULT
							, StarsOptions.starColorList));

			setHistoryCodeView(Initial, StarsOptions.defaultStarProbability);
			setHistoryCodeView(Default, StarsOptions.defaultStarProbability);
			setHistoryCodeView(Current, StarsOptions.defaultStarProbability);
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<Float> getFromGame (ClientClasses go) {
			return null; // Too complicated to only guess!
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Float> value) {}
		
		@Override public AbstractT<Float> getFromUI (ClientClasses go) {
			return new T_Float().setFromCodeView(
					StarsOptions.selectedStarProbability());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Float> value) {
			StarsOptions.selectedStarProbability(value.getCodeList());
		}
		
		@Override public void initComments() {
			setBottomComments(PMconfig.availableForChange());
		}
		// ========== Other Methods ==========
		//
		/**
		 * @param go the ClientClass
		 */
		public void loadStarProbability(ClientClasses go) {
			// TODO
		}
	}
}
