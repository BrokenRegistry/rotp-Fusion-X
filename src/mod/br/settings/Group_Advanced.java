
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mod.br.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.Comment;
import br.config.AbstractGroup;
import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

public class Group_Advanced extends AbstractGroup <IGameOptions, GameSession> {

    Group_Advanced(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new GalaxyAge(gO));
        addSetting(new StarDensity(gO));
        addSetting(new Nebulae(gO));
        addSetting(new PlanetQuality(gO));
        addSetting(new Terraforming(gO));
        addSetting(new RandomEvents(gO));
        addSetting(new AIHostility(gO));
        addSetting(new Council(gO));
        addSetting(new RandomizeAI(gO));
        addSetting(new AutoPlay(gO));
        addSetting(new Research(gO));
        addSetting(new WarpSpeed(gO));
        addSetting(new FuelRange(gO));
        addSetting(new TechTrading(gO));
        addSetting(new Colonizing(gO));
    }

    // ------------------------------------------------------------------------
    // GALAXY AGE
    //
    public static class GalaxyAge extends AbstractSetting <IGameOptions, GameSession> {

        GalaxyAge(IGameOptions gO) { super(
            "GALAXY AGE",
            gO.galaxyAgeOptions(),
            gO.selectedGalaxyAge());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedGalaxyAge();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedGalaxyAge();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedGalaxyAge(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedGalaxyAge(initialOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "----------- Advanced Game Options -----------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // STAR DENSITY
    //
    public static class StarDensity extends AbstractSetting <IGameOptions, GameSession> {

    	StarDensity(IGameOptions gO) { super(
            "STAR DENSITY",
            gO.starDensityOptions(),
            gO.selectedStarDensityOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedStarDensityOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedStarDensityOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedStarDensityOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedStarDensityOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // NEBULAE
    //
    public static class Nebulae extends AbstractSetting <IGameOptions, GameSession> {

    	Nebulae(IGameOptions gO) { super(
            "NEBULAE",
            gO.nebulaeOptions(),
            gO.selectedNebulaeOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedNebulaeOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedNebulaeOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedNebulaeOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedNebulaeOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // PLANET QUALITY
    //
    public static class PlanetQuality extends AbstractSetting <IGameOptions, GameSession> {

    	PlanetQuality(IGameOptions gO) { super(
            "PLANET QUALITY",
            gO.planetQualityOptions(),
            gO.selectedPlanetQualityOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedPlanetQualityOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedPlanetQualityOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedPlanetQualityOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedPlanetQualityOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // TERRAFORMING
    //
    public static class Terraforming extends AbstractSetting <IGameOptions, GameSession> {

    	Terraforming(IGameOptions gO) { super(
            "TERRAFORMING",
            gO.terraformingOptions(),
            gO.selectedTerraformingOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedTerraformingOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//        	gs.options().selectedTerraformingOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedTerraformingOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedTerraformingOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedTerraformingOption(initialOption());
        }
        @Override
        public void initComments() {
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
       	}
    }
    // ------------------------------------------------------------------------
    // RANDOM EVENTS
    //
    public static class RandomEvents extends AbstractSetting <IGameOptions, GameSession> {

    	RandomEvents(IGameOptions gO) { super(
            "RANDOM EVENTS",
            gO.randomEventOptions(),
            gO.selectedRandomEventOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedRandomEventOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
        	gs.options().selectedRandomEventOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedRandomEventOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedRandomEventOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedRandomEventOption(initialOption());
        }
        @Override
        public void initComments() {
        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
       	}
    }
    // ------------------------------------------------------------------------
    // AI HOSTILITY
    //
    public static class AIHostility extends AbstractSetting <IGameOptions, GameSession> {

    	AIHostility(IGameOptions gO) { super(
            "AI HOSTILITY",
            gO.aiHostilityOptions(),
            gO.selectedAIHostilityOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedAIHostilityOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//            gs.options().selectedAIHostilityOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedAIHostilityOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedAIHostilityOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedAIHostilityOption(initialOption());
        }
        @Override
        public void initComments() {
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
       	}
    }
    // ------------------------------------------------------------------------
    // COUNCIL
    //
    public static class Council extends AbstractSetting <IGameOptions, GameSession> {

    	Council(IGameOptions gO) { super(
            "COUNCIL",
            gO.councilWinOptions(),
            gO.selectedCouncilWinOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedCouncilWinOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
            gs.options().selectedCouncilWinOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedCouncilWinOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedCouncilWinOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedCouncilWinOption(initialOption());
        }
        @Override
        public void initComments() {
        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
       	}
    }
    // ------------------------------------------------------------------------
    // RANDOMIZE AI
    //
    public static class RandomizeAI extends AbstractSetting <IGameOptions, GameSession> {

    	RandomizeAI(IGameOptions gO) { super(
            "RANDOMIZE AI",
            gO.randomizeAIOptions(),
            gO.selectedRandomizeAIOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedRandomizeAIOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedRandomizeAIOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedRandomizeAIOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedRandomizeAIOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // AUTOPLAY
    //
    public static class AutoPlay extends AbstractSetting <IGameOptions, GameSession> {

    	AutoPlay(IGameOptions gO) { super(
            "AUTOPLAY",
            gO.autoplayOptions(),
            gO.selectedAutoplayOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedAutoplayOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedAutoplayOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedAutoplayOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedAutoplayOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // RESEARCH
    //
    public static class Research extends AbstractSetting <IGameOptions, GameSession> {

    	Research(IGameOptions gO) { super(
            "RESEARCH",
            gO.researchRateOptions(),
            gO.selectedResearchRate());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedResearchRate();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//            gs.options().selectedResearchRate(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedResearchRate();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedResearchRate(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedResearchRate(initialOption());
        }
        @Override
        public void initComments() {
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        	}
    }
    // ------------------------------------------------------------------------
    // WARP SPEED
    //
    public static class WarpSpeed extends AbstractSetting <IGameOptions, GameSession> {

    	WarpSpeed(IGameOptions gO) { super(
            "WARP SPEED",
            gO.warpSpeedOptions(),
            gO.selectedWarpSpeedOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedWarpSpeedOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//            gs.options().selectedWarpSpeedOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedWarpSpeedOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedWarpSpeedOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedWarpSpeedOption(initialOption());
        }
        @Override
        public void initComments() {
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        	}
    }
    // ------------------------------------------------------------------------
    // FUEL RANGE
    //
    public static class FuelRange extends AbstractSetting <IGameOptions, GameSession> {

    	FuelRange(IGameOptions gO) { super(
            "FUEL RANGE",
            gO.fuelRangeOptions(),
            gO.selectedFuelRangeOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedFuelRangeOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//            gs.options().selectedFuelRangeOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedFuelRangeOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedFuelRangeOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedFuelRangeOption(initialOption());
        }
        @Override
        public void initComments() {
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        }
    }
    // ------------------------------------------------------------------------
    // TECH TRADING
    //
    public static class TechTrading extends AbstractSetting <IGameOptions, GameSession> {

    	TechTrading(IGameOptions gO) { super(
            "TECH TRADING",
            gO.techTradingOptions(),
            gO.selectedTechTradeOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedTechTradeOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
            gs.options().selectedTechTradeOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedTechTradeOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedTechTradeOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedTechTradeOption(initialOption());
        }
        @Override
        public void initComments() {
        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        }
    }
    // ------------------------------------------------------------------------
    // COLONIZING
    //
    public static class Colonizing extends AbstractSetting <IGameOptions, GameSession> {

    	Colonizing(IGameOptions gO) { super(
            "COLONIZING",
            gO.colonizingOptions(),
            gO.selectedColonizingOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedColonizingOption();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
        	gs.options().selectedColonizingOption(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedColonizingOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedColonizingOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedColonizingOption(initialOption());
        }
        @Override
        public void initComments() {
        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        	}
    }
}
