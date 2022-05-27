
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

import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Parameter;
import mod.br.Galaxy.GalaxySpacing;
import mod.br.Galaxy.StarsOptions;
import br.profileManager.src.main.java.Valid_Boolean;
import br.profileManager.src.main.java.Valid_Double;
import br.profileManager.src.main.java.Valid_Integer;
import static br.profileManager.src.main.java.WriteUtil.History.*;


/**
 * @author BrokenRegistry
 * For Parameters without GUI from BrokenRegistry Mods
 */
public class Group_BrokenRegistry extends  Abstract_Group <ClientClasses> {
	
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
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {
	 
		MaximizeEmpiresSpacing(ClientClasses go) {
			super( "MAXIMIZE EMPIRES SPACING", new Valid_Boolean());
			setHistoryCodeView(Initial, GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING);
			setHistoryCodeView(Default, false); // BR DEFAULT
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public Boolean getFromGame (ClientClasses go) {
			return GalaxySpacing.isMaximizeEmpiresSpacing();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean codeView) {}
		
		@Override public Boolean getFromUI (ClientClasses go) {
			return GalaxySpacing.isMaximizeEmpiresSpacing();
		}
		
		@Override public void putToGUI(ClientClasses go, Boolean codeView) {
			GalaxySpacing.setMaximizeEmpiresSpacing(codeView);
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
			Abstract_Parameter <Integer, Valid_Integer, ClientClasses> {

		PreferedStarsPerEmpire(ClientClasses go) { 
			super( "PREF STARS PER EMPIRE", new Valid_Integer());
			setHistoryCodeView(Initial, GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE);
			setHistoryCodeView(Default, 16); // BR DEFAULT
			setLimits(0 , 1000000);
			setDefaultRandomLimits(16 , 24);
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public Integer getFromGame (ClientClasses go) {
			return GalaxySpacing.getPreferedStarsPerEmpire();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return GalaxySpacing.getPreferedStarsPerEmpire();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			GalaxySpacing.setPreferedStarsPerEmpire(codeView);
		}
		
		@Override public void initComments() {}
	}
	
	// ========================================================================
	// MIN STARS PER EMPIRE
	//
	static class MinStarsPerEmpire extends 
			Abstract_Parameter <Integer, Valid_Integer, ClientClasses> {

		MinStarsPerEmpire(ClientClasses go) {
			super( "MIN STARS PER EMPIRE", new Valid_Integer());
			setHistoryCodeView(Initial, GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE);
			setHistoryCodeView(Default, 8); // BR DEFAULT
			setLimits(0 , 1000000);
			setDefaultRandomLimits(4 , 16);
		}
		// ------------------------------------------------
		// Overrider
		//
		@Override public Integer getFromGame (ClientClasses go) {
			return GalaxySpacing.getMinStarsPerEmpire();
		}
		
		@Override public void putToGame(ClientClasses go, Integer codeView) {}
		
		@Override public Integer getFromUI (ClientClasses go) {
			return GalaxySpacing.getMinStarsPerEmpire();
		}
		
		@Override public void putToGUI(ClientClasses go, Integer codeView) {
			GalaxySpacing.setMinStarsPerEmpire(codeView);
		}
		
		@Override public void initComments() {}

	}
	// ========================================================================
	// NO PLANET MULTIPLIER
	//
	static class NoPlanetMultiplier extends 
			Abstract_Parameter <Double, Valid_Double, ClientClasses> {

		NoPlanetMultiplier(ClientClasses go) { 
			super( "NO PLANET PCT MULT", new Valid_Double());
			setHistoryCodeView(Initial, StarsOptions.NO_PLANET_MULTIPLIER);
			setHistoryCodeView(Default, 1.0); // BR DEFAULT
			setLimits(0.0 , 1000000.0);
			setDefaultRandomLimits(0.0 , 2.0);
		}
		
		// ------------------------------------------------
		// Overrider
		//
		@Override public Double getFromGame (ClientClasses go) {
			return StarsOptions.getNoPlanetMultiplier();
		}
		
		@Override public void putToGame(ClientClasses go, Double codeView) {}
		
		@Override public Double getFromUI (ClientClasses go) {
			return StarsOptions.getNoPlanetMultiplier();
		}
		
		@Override public void putToGUI(ClientClasses go, Double codeView) {
			StarsOptions.setNoPlanetMultiplier(codeView);
		}
		
		@Override public void initComments() {}
	}
}
