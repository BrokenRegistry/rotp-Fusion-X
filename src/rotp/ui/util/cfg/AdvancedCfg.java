package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class AdvancedCfg {

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {
        IGameOptions gameOptions = p.gameOptions;
        p.initDV(u, "GALAXY AGE",     gameOptions.selectedGalaxyAge(),           gameOptions.galaxyAgeOptions());
        p.initDV(u, "STAR DENSITY",   gameOptions.selectedStarDensityOption(),   gameOptions.starDensityOptions());
        p.initDV(u, "NEBULAE",        gameOptions.selectedNebulaeOption(),       gameOptions.nebulaeOptions());
		p.initDV(u, "PLANET QUALITY", gameOptions.selectedPlanetQualityOption(), gameOptions.planetQualityOptions());
		p.initDV(u, "TERRAFORMING",   gameOptions.selectedTerraformingOption(),  gameOptions.terraformingOptions());
		p.initDV(u, "RANDOM EVENTS",  gameOptions.selectedRandomEventOption(),   gameOptions.randomEventOptions());
		p.initDV(u, "AI HOSTILITY",   gameOptions.selectedAIHostilityOption(),   gameOptions.aiHostilityOptions());
        p.initDV(u, "COUNCIL",        gameOptions.selectedCouncilWinOption(),    gameOptions.councilWinOptions());
		p.initDV(u, "RANDOMIZE AI",   gameOptions.selectedRandomizeAIOption(),   gameOptions.randomizeAIOptions());
        p.initDV(u, "AUTOPLAY",       gameOptions.selectedAutoplayOption(),      gameOptions.autoplayOptions());
		p.initDV(u, "RESEARCH",       gameOptions.selectedResearchRate(),        gameOptions.researchRateOptions());
		p.initDV(u, "WARP SPEED",     gameOptions.selectedWarpSpeedOption(),     gameOptions.warpSpeedOptions());
		p.initDV(u, "FUEL RANGE",     gameOptions.selectedFuelRangeOption(),     gameOptions.fuelRangeOptions());
		p.initDV(u, "TECH TRADING",   gameOptions.selectedTechTradeOption(),     gameOptions.techTradingOptions());
		p.initDV(u, "COLONIZING",     gameOptions.selectedColonizingOption(),    gameOptions.colonizingOptions());

    }
    static void initComments(Presets p) {
        p.settingsMap.get("GALAXY AGE").headComments(p
            .new Comments(List.of(" ", "----------- Advanced Game Options -----------", " ")));
    }
    static void overrideGameOptions (Presets p, String userOption) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        IGameOptions gameOptions = p.gameOptions;
        setting = "GALAXY AGE";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedGalaxyAge(section.getValidSetting(userOption));
        }
        setting = "STAR DENSITY";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedStarDensityOption(section.getValidSetting(userOption));
        }
        setting = "NEBULAE";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedNebulaeOption(section.getValidSetting(userOption));
        }
        setting = "PLANET QUALITY";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedPlanetQualityOption(section.getValidSetting(userOption));
        }
        setting = "TERRAFORMING";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedTerraformingOption(section.getValidSetting(userOption));
        }
        setting = "RANDOM EVENTS";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedRandomEventOption(section.getValidSetting(userOption));
        }
        setting = "AI HOSTILITY";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedAIHostilityOption(section.getValidSetting(userOption));
        }
        setting = "COUNCIL";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedCouncilWinOption(section.getValidSetting(userOption));
        }
        setting = "RANDOMIZE AI";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedRandomizeAIOption(section.getValidSetting(userOption));
        }
        setting = "AUTOPLAY";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedAutoplayOption(section.getValidSetting(userOption));
        }
        setting = "RESEARCH";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedResearchRate(section.getValidSetting(userOption));
        }
        setting = "WARP SPEED";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedWarpSpeedOption(section.getValidSetting(userOption));
        }
        setting = "FUEL RANGE";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedFuelRangeOption(section.getValidSetting(userOption));
        }
        setting = "TECH TRADING";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedTechTradeOption(section.getValidSetting(userOption));
        }
        setting = "COLONIZING";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedColonizingOption(section.getValidSetting(userOption));
        }
    }
}
