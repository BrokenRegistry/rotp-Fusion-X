
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
import br.profileManager.src.main.java.PMconfig;
import br.profileManager.src.main.java.T_String;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @author BrokenRegistry
 * For Parameters in Advanced GUI
 */
public class Group_Advanced extends  AbstractGroup <ClientClasses> {

	Group_Advanced(ClientClasses go) {
	   super(go);
	}
	@Override
	protected void initSettingList(ClientClasses go) {
		addParameter(new GalaxyAge(go));
		addParameter(new StarDensity(go));
		addParameter(new Nebulae(go));
		addParameter(new PlanetQuality(go));
		addParameter(new Terraforming(go));
		addParameter(new RandomEvents(go));
		addParameter(new AIHostility(go));
		addParameter(new Council(go));
		addParameter(new RandomizeAI(go));
		addParameter(new AutoPlay(go));
		addParameter(new Research(go));
		addParameter(new WarpSpeed(go));
		addParameter(new FuelRange(go));
		addParameter(new TechTrading(go));
		addParameter(new Colonizing(go));
	}

	// ==============================================================
	// GALAXY AGE
	//
	static class GalaxyAge extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		GalaxyAge(ClientClasses go) { 
			super("GALAXY AGE", 
					new Validation<String>(
							new T_String(go.options().selectedGalaxyAge()),
							go.options().galaxyAgeOptions()));

			setHistory(Default, "Normal"); // Ray
			}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedGalaxyAge());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedGalaxyAge());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedGalaxyAge(value.codeView());
			go.option2().selectedGalaxyAge(value.codeView());
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"----------- Advanced Game Options -----------" + NL +
				" ");
		}
	}
 	// ==============================================================
	// STAR DENSITY
	//
	static class StarDensity extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		StarDensity(ClientClasses go) {
			super("STAR DENSITY",
					new Validation<String>(
							new T_String(go.options().selectedStarDensityOption()),
							go.options().starDensityOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedStarDensityOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedStarDensityOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedStarDensityOption(value.codeView());
			go.option2().selectedStarDensityOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// NEBULAE
	//
	static class Nebulae extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		Nebulae(ClientClasses go) {
			super("NEBULAE",
					new Validation<String>(
							new T_String(go.options().selectedNebulaeOption()),
							go.options().nebulaeOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedNebulaeOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedNebulaeOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedNebulaeOption(value.codeView());
			go.option2().selectedNebulaeOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// PLANET QUALITY
	//
   static class PlanetQuality extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		PlanetQuality(ClientClasses go) {
			super("PLANET QUALITY",
					new Validation<String>(
							new T_String(go.options().selectedPlanetQualityOption()),
							go.options().planetQualityOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedPlanetQualityOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedPlanetQualityOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedPlanetQualityOption(value.codeView());
			go.option2().selectedPlanetQualityOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// TERRAFORMING
	//
	static class Terraforming extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		Terraforming(ClientClasses go) {
			super("TERRAFORMING", 
					new Validation<String>(
							new T_String(go.options().selectedTerraformingOption()),
							go.options().terraformingOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedTerraformingOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
//			gs.getGuiObject().selectedTerraformingOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedTerraformingOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedTerraformingOption(value.codeView());
			go.option2().selectedTerraformingOption(value.codeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(PMconfig.availableForChange());
	   	}
	}
	// ==============================================================
	// RANDOM EVENTS
	//
   static class RandomEvents extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		RandomEvents(ClientClasses go) {
			super("RANDOM EVENTS", 
					new Validation<String>(
							new T_String(go.options().selectedRandomEventOption()),
							go.options().randomEventOptions()));

			setHistory(Default, "On"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedRandomEventOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
			gs.options().selectedRandomEventOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedRandomEventOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedRandomEventOption(value.codeView());
			go.option2().selectedRandomEventOption(value.codeView());
		}
		
		@Override public void initComments() {
			setBottomComments(PMconfig.availableForChange());
	   	}
	}
	// ==============================================================
	// AI HOSTILITY
	//
	static class AIHostility extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		AIHostility(ClientClasses go) {
			super("AI HOSTILITY", 
					new Validation<String>(
							new T_String(go.options().selectedAIHostilityOption()),
							go.options().aiHostilityOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedAIHostilityOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
//			gs.getGuiObject().selectedAIHostilityOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedAIHostilityOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedAIHostilityOption(value.codeView());
			go.option2().selectedAIHostilityOption(value.codeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(PMconfig.availableForChange());
	   	}
	}
	// ==============================================================
	// COUNCIL
	//
	static class Council extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		Council(ClientClasses go) {
			super("COUNCIL", 
					new Validation<String>(
							new T_String(go.options().selectedCouncilWinOption()),
							go.options().councilWinOptions()));

			setHistory(Default, "Rebels"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedCouncilWinOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
			gs.options().selectedCouncilWinOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedCouncilWinOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedCouncilWinOption(value.codeView());
			go.option2().selectedCouncilWinOption(value.codeView());
		}
		
		@Override public void initComments() {
			setBottomComments(PMconfig.availableForChange());
	   	}
	}
	// ==============================================================
	// RANDOMIZE AI
	//
	static class RandomizeAI extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		RandomizeAI(ClientClasses go) {
			super("RANDOMIZE AI",
					new Validation<String>(
							new T_String(go.options().selectedRandomizeAIOption()),
							go.options().randomizeAIOptions()));

			setHistory(Default, "None"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedRandomizeAIOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedRandomizeAIOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedRandomizeAIOption(value.codeView());
			go.option2().selectedRandomizeAIOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// AUTOPLAY
	//
	static class AutoPlay extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		AutoPlay(ClientClasses go) {
			super("AUTOPLAY", 
					new Validation<String>(
							new T_String(go.options().selectedAutoplayOption()),
							go.options().autoplayOptions()));

			setHistory(Default, "Off"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedAutoplayOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {

		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedAutoplayOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedAutoplayOption(value.codeView());
			go.option2().selectedAutoplayOption(value.codeView());
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// RESEARCH
	//
	static class Research extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		Research(ClientClasses go) { 
			super("RESEARCH", 
					new Validation<String>(
							new T_String(go.options().selectedResearchRate()),
							go.options().researchRateOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedResearchRate());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
//			gs.getGuiObject().selectedResearchRate(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedResearchRate());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedResearchRate(value.codeView());
			go.option2().selectedResearchRate(value.codeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(PMconfig.availableForChange());
		}
	}
	// ==============================================================
	// WARP SPEED
	//
	static class WarpSpeed extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		WarpSpeed(ClientClasses go) { 
			super("WARP SPEED", 
					new Validation<String>(
							new T_String(go.options().selectedWarpSpeedOption()),
							go.options().warpSpeedOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedWarpSpeedOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
//			gs.getGuiObject().selectedWarpSpeedOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedWarpSpeedOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedWarpSpeedOption(value.codeView());
			go.option2().selectedWarpSpeedOption(value.codeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(PMconfig.availableForChange());
		}
	}
	// ==============================================================
	// FUEL RANGE
	//
	static class FuelRange extends
			AbstractParameter <String, Validation<String>, ClientClasses> {

		FuelRange(ClientClasses go) { 
			super("FUEL RANGE", 
					new Validation<String>(
							new T_String(go.options().selectedFuelRangeOption()),
							go.options().fuelRangeOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedFuelRangeOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
//			gs.getGuiObject().selectedFuelRangeOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedFuelRangeOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedFuelRangeOption(value.codeView());
			go.option2().selectedFuelRangeOption(value.codeView());
		}
		
		@Override public void initComments() {
//			setBottomComments(PMconfig.availableForChange());
		}
	}
	// ==============================================================
	// TECH TRADING
	//
	static class TechTrading extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		TechTrading(ClientClasses go) { 
			super("TECH TRADING", 
					new Validation<String>(
							new T_String(go.options().selectedTechTradeOption()),
							go.options().techTradingOptions()));

			setHistory(Default, "Yes"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedTechTradeOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
			gs.options().selectedTechTradeOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedTechTradeOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedTechTradeOption(value.codeView());
			go.option2().selectedTechTradeOption(value.codeView());
		}
		
		@Override public void initComments() {
			setBottomComments(PMconfig.availableForChange());
		}
	}
	// ==============================================================
	// COLONIZING
	//
	static class Colonizing extends 
			AbstractParameter <String, Validation<String>, ClientClasses> {

		Colonizing(ClientClasses go) { 
			super("COLONIZING", 
					new Validation<String>(
							new T_String(go.options().selectedColonizingOption()),			
							go.options().colonizingOptions()));

			setHistory(Default, "Normal"); // Ray
		}
		
		@Override public AbstractT<String> getFromGame (ClientClasses go) {
			return new T_String(go.options().selectedColonizingOption());
		}
		
		@Override public void putToGame(ClientClasses gs, AbstractT<String> value) {
			gs.options().selectedColonizingOption(value.codeView());
		}
		
		@Override public AbstractT<String> getFromUI (ClientClasses go) {
			return new T_String(go.options().selectedColonizingOption());
		}
		
		@Override public void putToGUI(ClientClasses go, AbstractT<String> value) {
			go.options().selectedColonizingOption(value.codeView());
			go.option2().selectedColonizingOption(value.codeView());
		}
		
		@Override public void initComments() {
			setBottomComments(PMconfig.availableForChange());
			}
	}
}
