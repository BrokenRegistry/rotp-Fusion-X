
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
	
	static class GalaxyAge extends 
			Abstract_Parameter <String, Valid_GalaxyAge, ClientClasses> {

		GalaxyAge(ClientClasses go) { 
			super("GALAXY AGE", 
					new Valid_GalaxyAge(go.getGuiObject().galaxyAgeOptions()));
			setInitialValue(go.getGuiObject().selectedGalaxyAge());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedGalaxyAge();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedGalaxyAge();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedGalaxyAge(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedGalaxyAge(getInitialCodeView());
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
	
	static class StarDensity extends 
			Abstract_Parameter <String, Valid_StarDensity, ClientClasses> {

		StarDensity(ClientClasses go) {
			super("STAR DENSITY",
					new Valid_StarDensity(go.getGuiObject().starDensityOptions()));
			setInitialValue(go.getGuiObject().selectedStarDensityOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedStarDensityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedStarDensityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedStarDensityOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedStarDensityOption(getInitialCodeView());
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
	
	static class Nebulae extends 
			Abstract_Parameter <String, Valid_Nebulae, ClientClasses> {

		Nebulae(ClientClasses go) {
			super("NEBULAE",
					new Valid_Nebulae(go.getGuiObject().nebulaeOptions()));
			setInitialValue(go.getGuiObject().selectedNebulaeOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedNebulaeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedNebulaeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedNebulaeOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedNebulaeOption(getInitialCodeView());
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
	
   static class PlanetQuality extends 
			Abstract_Parameter <String, Valid_PlanetQuality, ClientClasses> {

		PlanetQuality(ClientClasses go) {
			super("PLANET QUALITY",
					new Valid_PlanetQuality(go.getGuiObject().planetQualityOptions()));
			setInitialValue(go.getGuiObject().selectedPlanetQualityOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedPlanetQualityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedPlanetQualityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedPlanetQualityOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedPlanetQualityOption(getInitialCodeView());
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
	
	static class Terraforming extends 
			Abstract_Parameter <String, Valid_Terraforming, ClientClasses> {

		Terraforming(ClientClasses go) {
			super("TERRAFORMING", 
					new Valid_Terraforming(go.getGuiObject().terraformingOptions()));
			setInitialValue(go.getGuiObject().selectedTerraformingOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedTerraformingOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGameObject().options().selectedTerraformingOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedTerraformingOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedTerraformingOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedTerraformingOption(getInitialCodeView());
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
	
   static class RandomEvents extends 
			Abstract_Parameter <String, Valid_RandomEvents, ClientClasses> {

		RandomEvents(ClientClasses go) {
			super("RANDOM EVENTS", 
					new Valid_RandomEvents(go.getGuiObject().randomEventOptions()));
			setInitialValue(go.getGuiObject().selectedRandomEventOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedRandomEventOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getGameObject().options().selectedRandomEventOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedRandomEventOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedRandomEventOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedRandomEventOption(getInitialCodeView());
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
	
	static class AIHostility extends 
			Abstract_Parameter <String, Valid_AIHostility, ClientClasses> {

		AIHostility(ClientClasses go) {
			super("AI HOSTILITY", 
					new Valid_AIHostility(go.getGuiObject().aiHostilityOptions()));
			setInitialValue(go.getGuiObject().selectedAIHostilityOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedAIHostilityOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGameObject().options().selectedAIHostilityOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedAIHostilityOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedAIHostilityOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedAIHostilityOption(getInitialCodeView());
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
	
	static class Council extends 
			Abstract_Parameter <String, Valid_Council, ClientClasses> {

		Council(ClientClasses go) {
			super("COUNCIL", 
					new Valid_Council(go.getGuiObject().councilWinOptions()));
			setInitialValue(go.getGuiObject().selectedCouncilWinOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedCouncilWinOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getGameObject().options().selectedCouncilWinOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedCouncilWinOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedCouncilWinOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedCouncilWinOption(getInitialCodeView());
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
	
	static class RandomizeAI extends 
			Abstract_Parameter <String, Valid_RandomizeAI, ClientClasses> {

		RandomizeAI(ClientClasses go) {
			super("RANDOMIZE AI",
					new Valid_RandomizeAI(go.getGuiObject().randomizeAIOptions()));
			setInitialValue(go.getGuiObject().selectedRandomizeAIOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedRandomizeAIOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedRandomizeAIOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedRandomizeAIOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedRandomizeAIOption(getInitialCodeView());
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
	
	static class AutoPlay extends 
			Abstract_Parameter <String, Valid_AutoPlay, ClientClasses> {

		AutoPlay(ClientClasses go) {
			super("AUTOPLAY", 
					new Valid_AutoPlay(go.getGuiObject().autoplayOptions()));
			setInitialValue(go.getGuiObject().selectedAutoplayOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedAutoplayOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {

		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedAutoplayOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedAutoplayOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedAutoplayOption(getInitialCodeView());
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
	
	static class Research extends 
			Abstract_Parameter <String, Valid_Research, ClientClasses> {

		Research(ClientClasses go) { 
			super("RESEARCH", 
					new Valid_Research(go.getGuiObject().researchRateOptions()));
			setInitialValue(go.getGuiObject().selectedResearchRate());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedResearchRate();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGameObject().options().selectedResearchRate(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedResearchRate();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedResearchRate(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedResearchRate(getInitialCodeView());
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
	
	static class WarpSpeed extends 
			Abstract_Parameter <String, Valid_WarpSpeed, ClientClasses> {

		WarpSpeed(ClientClasses go) { 
			super("WARP SPEED", 
					new Valid_WarpSpeed(go.getGuiObject().warpSpeedOptions()));
			setInitialValue(go.getGuiObject().selectedWarpSpeedOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedWarpSpeedOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGameObject().options().selectedWarpSpeedOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedWarpSpeedOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedWarpSpeedOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedWarpSpeedOption(getInitialCodeView());
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
	
	static class FuelRange extends
			Abstract_Parameter <String, Valid_FuelRange, ClientClasses> {

		FuelRange(ClientClasses go) { 
			super("FUEL RANGE", 
					new Valid_FuelRange(go.getGuiObject().fuelRangeOptions()));
			setInitialValue(go.getGuiObject().selectedFuelRangeOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedFuelRangeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
//			gs.getGameObject().options().selectedFuelRangeOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedFuelRangeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedFuelRangeOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedFuelRangeOption(getInitialCodeView());
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
	
	static class TechTrading extends 
			Abstract_Parameter <String, Valid_TechTrading, ClientClasses> {

		TechTrading(ClientClasses go) { 
			super("TECH TRADING", 
					new Valid_TechTrading(go.getGuiObject().techTradingOptions()));
			setInitialValue(go.getGuiObject().selectedTechTradeOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedTechTradeOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getGameObject().options().selectedTechTradeOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedTechTradeOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedTechTradeOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedTechTradeOption(getInitialCodeView());
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
	
	static class Colonizing extends 
			Abstract_Parameter <String, Valid_Colonizing, ClientClasses> {

		Colonizing(ClientClasses go) { 
			super("COLONIZING", 
					new Valid_Colonizing(go.getGuiObject().colonizingOptions()));
			setInitialValue(go.getGuiObject().selectedColonizingOption());
		}
		
		@Override public String getFromGame (ClientClasses go) {
			return go.getGameObject().options().selectedColonizingOption();
		}
		
		@Override public void putToGame(ClientClasses gs, String codeView) {
			gs.getGameObject().options().selectedColonizingOption(codeView);
		}
		
		@Override public String getFromUI (ClientClasses go) {
			return go.getGuiObject().selectedColonizingOption();
		}
		
		@Override public void putToGUI(ClientClasses go, String codeView) {
			go.getGuiObject().selectedColonizingOption(codeView);
		}
		
		@Override public void putInitialToGUI(ClientClasses go) {
			go.getGuiObject().selectedColonizingOption(getInitialCodeView());
		}
		
		@Override public void initComments() {
			setBottomComments(AVAILABLE_FOR_CHANGE);
			}
	}
}
