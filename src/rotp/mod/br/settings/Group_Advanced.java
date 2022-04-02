package rotp.mod.br.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.comment.Comment;
import rotp.model.game.IGameOptions;

public class Group_Advanced extends AbstractGroup <IGameOptions> {

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
    public static class GalaxyAge extends AbstractSetting <IGameOptions> {

        GalaxyAge(IGameOptions gO) { super(
            "GALAXY AGE",
            gO.galaxyAgeOptions(),
            gO.selectedGalaxyAge());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedGalaxyAge();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedGalaxyAge(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedGalaxyAge(firstOption());
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
    public static class StarDensity extends AbstractSetting <IGameOptions> {

    	StarDensity(IGameOptions gO) { super(
            "STAR DENSITY",
            gO.starDensityOptions(),
            gO.selectedStarDensityOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedStarDensityOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedStarDensityOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedStarDensityOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // NEBULAE
    //
    public static class Nebulae extends AbstractSetting <IGameOptions> {

    	Nebulae(IGameOptions gO) { super(
            "NEBULAE",
            gO.nebulaeOptions(),
            gO.selectedNebulaeOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedNebulaeOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedNebulaeOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedNebulaeOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // PLANET QUALITY
    //
    public static class PlanetQuality extends AbstractSetting <IGameOptions> {

    	PlanetQuality(IGameOptions gO) { super(
            "PLANET QUALITY",
            gO.planetQualityOptions(),
            gO.selectedPlanetQualityOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlanetQualityOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedPlanetQualityOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedPlanetQualityOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // TERRAFORMING
    //
    public static class Terraforming extends AbstractSetting <IGameOptions> {

    	Terraforming(IGameOptions gO) { super(
            "TERRAFORMING",
            gO.terraformingOptions(),
            gO.selectedTerraformingOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedTerraformingOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedTerraformingOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedTerraformingOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // RANDOM EVENTS
    //
    public static class RandomEvents extends AbstractSetting <IGameOptions> {

    	RandomEvents(IGameOptions gO) { super(
            "RANDOM EVENTS",
            gO.randomEventOptions(),
            gO.selectedRandomEventOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedRandomEventOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedRandomEventOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedRandomEventOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // AI HOSTILITY
    //
    public static class AIHostility extends AbstractSetting <IGameOptions> {

    	AIHostility(IGameOptions gO) { super(
            "AI HOSTILITY",
            gO.aiHostilityOptions(),
            gO.selectedAIHostilityOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedAIHostilityOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedAIHostilityOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedAIHostilityOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // COUNCIL
    //
    public static class Council extends AbstractSetting <IGameOptions> {

    	Council(IGameOptions gO) { super(
            "COUNCIL",
            gO.councilWinOptions(),
            gO.selectedCouncilWinOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedCouncilWinOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedCouncilWinOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedCouncilWinOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // RANDOMIZE AI
    //
    public static class RandomizeAI extends AbstractSetting <IGameOptions> {

    	RandomizeAI(IGameOptions gO) { super(
            "RANDOMIZE AI",
            gO.randomizeAIOptions(),
            gO.selectedRandomizeAIOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedRandomizeAIOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedRandomizeAIOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedRandomizeAIOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // AUTOPLAY
    //
    public static class AutoPlay extends AbstractSetting <IGameOptions> {

    	AutoPlay(IGameOptions gO) { super(
            "AUTOPLAY",
            gO.autoplayOptions(),
            gO.selectedAutoplayOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedAutoplayOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedAutoplayOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedAutoplayOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // RESEARCH
    //
    public static class Research extends AbstractSetting <IGameOptions> {

    	Research(IGameOptions gO) { super(
            "RESEARCH",
            gO.researchRateOptions(),
            gO.selectedResearchRate());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedResearchRate();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedResearchRate(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedResearchRate(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // WARP SPEED
    //
    public static class WarpSpeed extends AbstractSetting <IGameOptions> {

    	WarpSpeed(IGameOptions gO) { super(
            "WARP SPEED",
            gO.warpSpeedOptions(),
            gO.selectedWarpSpeedOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedWarpSpeedOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedWarpSpeedOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedWarpSpeedOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // FUEL RANGE
    //
    public static class FuelRange extends AbstractSetting <IGameOptions> {

    	FuelRange(IGameOptions gO) { super(
            "FUEL RANGE",
            gO.fuelRangeOptions(),
            gO.selectedFuelRangeOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedFuelRangeOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedFuelRangeOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedFuelRangeOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // TECH TRADING
    //
    public static class TechTrading extends AbstractSetting <IGameOptions> {

    	TechTrading(IGameOptions gO) { super(
            "TECH TRADING",
            gO.techTradingOptions(),
            gO.selectedTechTradeOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedTechTradeOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedTechTradeOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedTechTradeOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // COLONIZING
    //
    public static class Colonizing extends AbstractSetting <IGameOptions> {

    	Colonizing(IGameOptions gO) { super(
            "COLONIZING",
            gO.colonizingOptions(),
            gO.selectedColonizingOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedColonizingOption();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedColonizingOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedColonizingOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
}
