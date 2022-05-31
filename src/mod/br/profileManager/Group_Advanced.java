
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
import br.profileManager.src.main.java.Valid_String;
import br.profileManager.src.main.java.ValidationCriteria;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * @author BrokenRegistry
 * For Parameters in Advanced GUI
 */
public class Group_Advanced extends  Abstract_Group <ClientClasses> {

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
	static class Valid_GalaxyAge extends Valid_String {

		Valid_GalaxyAge() {
			super();
			init();
		}
		
		Valid_GalaxyAge(List<String> options) {
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
	
	static class GalaxyAge extends 
			Abstract_Parameter <String, Valid_GalaxyAge, ClientClasses> {

		GalaxyAge(ClientClasses go) { 
			super("GALAXY AGE", 
					new Valid_GalaxyAge(go.getOptionsObject().galaxyAgeOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedGalaxyAge());
			setHistoryUserView(Default, "Normal"); // Ray
			}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedGalaxyAge();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedGalaxyAge();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedGalaxyAge(codeView);
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
	static class Valid_StarDensity extends Valid_String {

		Valid_StarDensity() {
			super();
			init();
		}
		
		Valid_StarDensity(List<String> options) {
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
	
	static class StarDensity extends 
			Abstract_Parameter <String, Valid_StarDensity, ClientClasses> {

		StarDensity(ClientClasses go) {
			super("STAR DENSITY",
					new Valid_StarDensity(go.getOptionsObject().starDensityOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedStarDensityOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedStarDensityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedStarDensityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedStarDensityOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// NEBULAE
	//
	static class Valid_Nebulae extends Valid_String {

		Valid_Nebulae() {
			super();
			init();
		}
		
		Valid_Nebulae(List<String> options) {
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
	
	static class Nebulae extends 
			Abstract_Parameter <String, Valid_Nebulae, ClientClasses> {

		Nebulae(ClientClasses go) {
			super("NEBULAE",
					new Valid_Nebulae(go.getOptionsObject().nebulaeOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedNebulaeOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedNebulaeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedNebulaeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedNebulaeOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// PLANET QUALITY
	//
	static class Valid_PlanetQuality extends Valid_String {

		Valid_PlanetQuality() {
			super();
			init();
		}
		
		Valid_PlanetQuality(List<String> options) {
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
	
   static class PlanetQuality extends 
			Abstract_Parameter <String, Valid_PlanetQuality, ClientClasses> {

		PlanetQuality(ClientClasses go) {
			super("PLANET QUALITY",
					new Valid_PlanetQuality(go.getOptionsObject().planetQualityOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedPlanetQualityOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedPlanetQualityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedPlanetQualityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedPlanetQualityOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// TERRAFORMING
	//
	static class Valid_Terraforming extends Valid_String {

		Valid_Terraforming() {
			super();
			init();
		}
		
		Valid_Terraforming(List<String> options) {
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
	
	static class Terraforming extends 
			Abstract_Parameter <String, Valid_Terraforming, ClientClasses> {

		Terraforming(ClientClasses go) {
			super("TERRAFORMING", 
					new Valid_Terraforming(go.getOptionsObject().terraformingOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedTerraformingOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedTerraformingOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGuiObject().selectedTerraformingOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedTerraformingOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedTerraformingOption(codeView);
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
	   	}
	}
	// ==============================================================
	// RANDOM EVENTS
	//
	static class Valid_RandomEvents extends Valid_String {

		Valid_RandomEvents() {
			super();
			init();
		}
		
		Valid_RandomEvents(List<String> options) {
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
	
   static class RandomEvents extends 
			Abstract_Parameter <String, Valid_RandomEvents, ClientClasses> {

		RandomEvents(ClientClasses go) {
			super("RANDOM EVENTS", 
					new Valid_RandomEvents(go.getOptionsObject().randomEventOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedRandomEventOption());
			setHistoryUserView(Default, "On"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedRandomEventOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getOptionsObject().selectedRandomEventOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedRandomEventOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedRandomEventOption(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
	   	}
	}
	// ==============================================================
	// AI HOSTILITY
	//
	static class Valid_AIHostility extends Valid_String {

		Valid_AIHostility() {
			super();
			init();
		}
		
		Valid_AIHostility(List<String> options) {
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
	
	static class AIHostility extends 
			Abstract_Parameter <String, Valid_AIHostility, ClientClasses> {

		AIHostility(ClientClasses go) {
			super("AI HOSTILITY", 
					new Valid_AIHostility(go.getOptionsObject().aiHostilityOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedAIHostilityOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedAIHostilityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGuiObject().selectedAIHostilityOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedAIHostilityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedAIHostilityOption(codeView);
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
	   	}
	}
	// ==============================================================
	// COUNCIL
	//
	static class Valid_Council extends Valid_String {

		Valid_Council() {
			super();
			init();
		}
		
		Valid_Council(List<String> options) {
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
	
	static class Council extends 
			Abstract_Parameter <String, Valid_Council, ClientClasses> {

		Council(ClientClasses go) {
			super("COUNCIL", 
					new Valid_Council(go.getOptionsObject().councilWinOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedCouncilWinOption());
			setHistoryUserView(Default, "Rebels"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedCouncilWinOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getOptionsObject().selectedCouncilWinOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedCouncilWinOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedCouncilWinOption(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
	   	}
	}
	// ==============================================================
	// RANDOMIZE AI
	//
	static class Valid_RandomizeAI extends Valid_String {

		Valid_RandomizeAI() {
			super();
			init();
		}
		
		Valid_RandomizeAI(List<String> options) {
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
	
	static class RandomizeAI extends 
			Abstract_Parameter <String, Valid_RandomizeAI, ClientClasses> {

		RandomizeAI(ClientClasses go) {
			super("RANDOMIZE AI",
					new Valid_RandomizeAI(go.getOptionsObject().randomizeAIOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedRandomizeAIOption());
			setHistoryUserView(Default, "None"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedRandomizeAIOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedRandomizeAIOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedRandomizeAIOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// AUTOPLAY
	//
	static class Valid_AutoPlay extends Valid_String {

		Valid_AutoPlay() {
			super();
			init();
		}
		
		Valid_AutoPlay(List<String> options) {
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
	
	static class AutoPlay extends 
			Abstract_Parameter <String, Valid_AutoPlay, ClientClasses> {

		AutoPlay(ClientClasses go) {
			super("AUTOPLAY", 
					new Valid_AutoPlay(go.getOptionsObject().autoplayOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedAutoplayOption());
			setHistoryUserView(Default, "Off"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedAutoplayOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedAutoplayOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedAutoplayOption(codeView);
		}
		
		@Override public void initComments() {}
	}
	// ==============================================================
	// RESEARCH
	//
	static class Valid_Research extends Valid_String {

		Valid_Research() {
			super();
			init();
		}
		
		Valid_Research(List<String> options) {
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
	
	static class Research extends 
			Abstract_Parameter <String, Valid_Research, ClientClasses> {

		Research(ClientClasses go) { 
			super("RESEARCH", 
					new Valid_Research(go.getOptionsObject().researchRateOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedResearchRate());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedResearchRate();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGuiObject().selectedResearchRate(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedResearchRate();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedResearchRate(codeView);
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	// ==============================================================
	// WARP SPEED
	//
	static class Valid_WarpSpeed extends Valid_String {

		Valid_WarpSpeed() {
			super();
			init();
		}
		
		Valid_WarpSpeed(List<String> options) {
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
	
	static class WarpSpeed extends 
			Abstract_Parameter <String, Valid_WarpSpeed, ClientClasses> {

		WarpSpeed(ClientClasses go) { 
			super("WARP SPEED", 
					new Valid_WarpSpeed(go.getOptionsObject().warpSpeedOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedWarpSpeedOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedWarpSpeedOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGuiObject().selectedWarpSpeedOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedWarpSpeedOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedWarpSpeedOption(codeView);
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	// ==============================================================
	// FUEL RANGE
	//
	static class Valid_FuelRange extends Valid_String {

		Valid_FuelRange() {
			super();
			init();
		}
		
		Valid_FuelRange(List<String> options) {
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
	
	static class FuelRange extends
			Abstract_Parameter <String, Valid_FuelRange, ClientClasses> {

		FuelRange(ClientClasses go) { 
			super("FUEL RANGE", 
					new Valid_FuelRange(go.getOptionsObject().fuelRangeOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedFuelRangeOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedFuelRangeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGuiObject().selectedFuelRangeOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedFuelRangeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedFuelRangeOption(codeView);
		}
		
		@Override public void initComments() {
//			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	// ==============================================================
	// TECH TRADING
	//
	static class Valid_TechTrading extends Valid_String {

		Valid_TechTrading() {
			super();
			init();
		}
		
		Valid_TechTrading(List<String> options) {
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
	
	static class TechTrading extends 
			Abstract_Parameter <String, Valid_TechTrading, ClientClasses> {

		TechTrading(ClientClasses go) { 
			super("TECH TRADING", 
					new Valid_TechTrading(go.getOptionsObject().techTradingOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedTechTradeOption());
			setHistoryUserView(Default, "Yes"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedTechTradeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getOptionsObject().selectedTechTradeOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedTechTradeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedTechTradeOption(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
		}
	}
	// ==============================================================
	// COLONIZING
	//
	static class Valid_Colonizing extends Valid_String {

		Valid_Colonizing() {
			super();
			init();
		}
		
		Valid_Colonizing(List<String> options) {
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
	
	static class Colonizing extends 
			Abstract_Parameter <String, Valid_Colonizing, ClientClasses> {

		Colonizing(ClientClasses go) { 
			super("COLONIZING", 
					new Valid_Colonizing(go.getOptionsObject().colonizingOptions()));
			setHistoryCodeView(Initial, go.getOptionsObject().selectedColonizingOption());
			setHistoryUserView(Default, "Normal"); // Ray
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getOptionsObject().selectedColonizingOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getOptionsObject().selectedColonizingOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getOptionsObject().selectedColonizingOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getOptionsObject().selectedColonizingOption(codeView);
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
			}
	}
}
