
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
import static mod.br.AddOns.StarsOptions.ALL_PLANETS_KEY;
import static mod.br.AddOns.StarsOptions.PLANET_TYPES;
import static mod.br.AddOns.StarsOptions.STARS_KEY;
import static mod.br.AddOns.StarsOptions.STAR_TYPES;
import static mod.br.AddOns.StarsOptions.probabilityModifier;

import java.util.List;

import br.profileManager.src.main.java.AbstractGroup;
import br.profileManager.src.main.java.AbstractParameter;
import br.profileManager.src.main.java.AbstractT;
import br.profileManager.src.main.java.PMutil;
import br.profileManager.src.main.java.T_Boolean;
import br.profileManager.src.main.java.T_Float;
import br.profileManager.src.main.java.T_Integer;
import br.profileManager.src.main.java.Validation;
import mod.br.AddOns.GalaxySpacing;
import mod.br.AddOns.Miscellaneous;
import mod.br.AddOns.StarsOptions.ProbabilityModifier;


/**
 * @author BrokenRegistry
 * For Parameters without GUI from BrokenRegistry Mods
 */
public class Group_BrokenRegistry extends  AbstractGroup <ClientClasses> {
	
//	private static List<String> planetTypeList =
//			PMutil.suggestedUserViewFromCodeView(PLANET_TYPES);
	
	Group_BrokenRegistry(ClientClasses go) {
	   super(go);
	}
	
	@Override protected void initSettingList(ClientClasses go) {
		addParameter(new FlagColorOrder(go));
		addParameter(new MaximizeEmpiresSpacing(go));
		addParameter(new PreferedStarsPerEmpire(go));
		addParameter(new MinStarsPerEmpire(go));
		addParameter(new BaseProbabilityModifier(go, "STAR TYPE PROBABILITY"
								, probabilityModifier(STARS_KEY), STAR_TYPES));
		addParameter(new BaseProbabilityModifier(go
				, "PLANET TYPE PROBABILITY " + ALL_PLANETS_KEY
				, probabilityModifier(ALL_PLANETS_KEY), PLANET_TYPES));
		for (String color : STAR_TYPES) {
			addParameter(new BaseProbabilityModifier(go
					, "PLANET TYPE PROBABILITY " + color
					, probabilityModifier(color), PLANET_TYPES));
		}

	}

	// ========================================================================
	// FLAG COLOR ORDER
	//
	static class Valid_FlagColorOrder extends Validation<Integer> {

		static final T_Integer flagColors = Miscellaneous.defaultFlagColorOrder();

		Valid_FlagColorOrder(int initial) {
			super(new T_Integer(initial));
			init();
		}

		private void initCriteria() {
			getCriteria().isNullAllowed(false);
		}
		
		private void init() {
			List<String> colors = flagColors.getUserList();
			initCriteria();
			for (String color : colors) {
				addOption(colors.indexOf(color), color);
			}
			setLimits(0 , colors.size());
			setDefaultRandomLimits(0 , colors.size());
			setHistory(Default, flagColors);
		}
				
		/**
		 * Generate UserViewList and convert it to capitalized String
		 * @return UserView List in capitalized String
		 */
		@Override public String getOptionsRange() {
			return PMutil.capitalize(getOptionsStringList().toString());
		}
	}

	// ========== Parameter Section ==========
	//
	static class FlagColorOrder extends 
			AbstractParameter <Integer, Valid_FlagColorOrder, ClientClasses> {

	    // ========== Constructors and initializer ==========
	    //
		FlagColorOrder(ClientClasses go) {
			super("FLAG COLOR ORDER", new Valid_FlagColorOrder(0));
			
			getValidation().setHistory(Initial, Miscellaneous.defaultFlagColorOrder());
			getValidation().setHistory(Default, Miscellaneous.defaultFlagColorOrder());
			getValidation().setHistory(Current, Miscellaneous.defaultFlagColorOrder());
		}
		
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<Integer> getFromGame (ClientClasses go) {
			return Miscellaneous.selectedFlagColorOrder();
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Integer> value) {
			Miscellaneous.selectedFlagColorOrder(value);
		}
		
		@Override public AbstractT<Integer> getFromUI (ClientClasses go) {
			return Miscellaneous.selectedFlagColorOrder();
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Integer> value) {
			Miscellaneous.selectedFlagColorOrder(value);

			go.newOptions().selectedPlayerColor(value.getCodeView());
			go.options().selectedPlayerColor(value.getCodeView());
		}
		
		@Override public void initComments() {
			setBottomComments(dynamicParameter());
			setHeadComments(
					" " + NL +
					"------------- Broken Registry Options -------------" + NL +
					" ");
		}	
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
	    // ========== Overriders ==========
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
		
		@Override public void initComments() {}
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
	    // ========== Overriders ==========
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
	    // ========== Overriders ==========
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

	// ==============================================================
	// BASE PROBABILITY MODIFIER CLASS
	//
	/**
	 *  Base class for star and planets probability modifier
	 */
	public static class BaseProbabilityModifier extends 
			AbstractParameter <Float, Valid_ProbabilityDensity, ClientClasses> {

		private final ProbabilityModifier pMod;
	    // ========== Constructors and initializer ==========
	    //
		BaseProbabilityModifier(ClientClasses go, String Name
				, ProbabilityModifier modifier, List<String> options)
		{
			super(Name, new Valid_ProbabilityDensity(
					ProbabilityModifier.DefaultProbabilityModifier, options));

			pMod = modifier;
			setHistoryCodeView(Initial, pMod.defaultModifierList());
			setHistoryCodeView(Default, pMod.defaultModifierList());
			setHistoryCodeView(Current, pMod.defaultModifierList());
		}
				
	    // ========== Overriders ==========
	    //
		@Override public AbstractT<Float> getFromGame (ClientClasses go) {
			return null; // Too complicated to only guess!
		}
		
		@Override public void putToGame(ClientClasses go, AbstractT<Float> value) {}
		
		@Override public AbstractT<Float> getFromUI (ClientClasses go) {
			return new T_Float().setFromCodeView(
					pMod.selectedModifierList());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<Float> value) {
			pMod.selectedModifierList(value.getCodeList());
		}
		
		@Override public void initComments() {}
	}
}
