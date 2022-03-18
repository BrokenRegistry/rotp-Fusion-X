package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class AdvancedCfg extends BaseCfg {
    // Assocated GUI: StartOptionsUI.java

    // Other Methods
    void loadGameOptions(Presets p, boolean u) {
        IGameOptions gameOptions = p.gameOptions;
        p.setSetting("GALAXY AGE",     gameOptions.selectedGalaxyAge(),           gameOptions.galaxyAgeOptions());
        p.setSetting("STAR DENSITY",   gameOptions.selectedStarDensityOption(),   gameOptions.starDensityOptions());
        p.setSetting("NEBULAE",        gameOptions.selectedNebulaeOption(),       gameOptions.nebulaeOptions());
		p.setSetting("PLANET QUALITY", gameOptions.selectedPlanetQualityOption(), gameOptions.planetQualityOptions());
		p.setSetting("TERRAFORMING",   gameOptions.selectedTerraformingOption(),  gameOptions.terraformingOptions());
		p.setSetting("RANDOM EVENTS",  gameOptions.selectedRandomEventOption(),   gameOptions.randomEventOptions());
		p.setSetting("AI HOSTILITY",   gameOptions.selectedAIHostilityOption(),   gameOptions.aiHostilityOptions());
        p.setSetting("COUNCIL",        gameOptions.selectedCouncilWinOption(),    gameOptions.councilWinOptions());
		p.setSetting("RANDOMIZE AI",   gameOptions.selectedRandomizeAIOption(),   gameOptions.randomizeAIOptions());
        p.setSetting("AUTOPLAY",       gameOptions.selectedAutoplayOption(),      gameOptions.autoplayOptions());
		p.setSetting("RESEARCH",       gameOptions.selectedResearchRate(),        gameOptions.researchRateOptions());
		p.setSetting("WARP SPEED",     gameOptions.selectedWarpSpeedOption(),     gameOptions.warpSpeedOptions());
		p.setSetting("FUEL RANGE",     gameOptions.selectedFuelRangeOption(),     gameOptions.fuelRangeOptions());
		p.setSetting("TECH TRADING",   gameOptions.selectedTechTradeOption(),     gameOptions.techTradingOptions());
		p.setSetting("COLONIZING",     gameOptions.selectedColonizingOption(),    gameOptions.colonizingOptions());

    }
    void initComments(Presets p) {
        p.settingsMap().get("GALAXY AGE").headComments(p
            .new Comments(List.of(" ", "----------- Advanced Game Options -----------", " ")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap();
        IGameOptions gameOptions = p.gameOptions;
        for (String userOption : p.selectedUserOptionsSet) {
            if (p.resetToDefault() || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
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
                    if (p.resetToDefault())
                        gameOptions.selectedAutoplayOption(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
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
            } // \ if ACTION LOAD
        }
    }
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        IGameOptions gameOptions = p.gameOptions;
        setting = "GALAXY AGE";
                gameOptions.selectedGalaxyAge(defaultValues.get(setting));
        setting = "STAR DENSITY";
                gameOptions.selectedStarDensityOption(defaultValues.get(setting));
        setting = "NEBULAE";
                gameOptions.selectedNebulaeOption(defaultValues.get(setting));
        setting = "PLANET QUALITY";
                gameOptions.selectedPlanetQualityOption(defaultValues.get(setting));
        setting = "TERRAFORMING";
                gameOptions.selectedTerraformingOption(defaultValues.get(setting));
        setting = "RANDOM EVENTS";
                gameOptions.selectedRandomEventOption(defaultValues.get(setting));
        setting = "AI HOSTILITY";
                gameOptions.selectedAIHostilityOption(defaultValues.get(setting));
        setting = "COUNCIL";
                gameOptions.selectedCouncilWinOption(defaultValues.get(setting));
        setting = "RANDOMIZE AI";
                gameOptions.selectedRandomizeAIOption(defaultValues.get(setting));
        setting = "AUTOPLAY";
                gameOptions.selectedAutoplayOption(defaultValues.get(setting));
        setting = "RESEARCH";
                gameOptions.selectedResearchRate(defaultValues.get(setting));
        setting = "WARP SPEED";
                gameOptions.selectedWarpSpeedOption(defaultValues.get(setting));
        setting = "FUEL RANGE";
                gameOptions.selectedFuelRangeOption(defaultValues.get(setting));
        setting = "TECH TRADING";
                gameOptions.selectedTechTradeOption(defaultValues.get(setting));
        setting = "COLONIZING";
                gameOptions.selectedColonizingOption(defaultValues.get(setting));
    }
}
