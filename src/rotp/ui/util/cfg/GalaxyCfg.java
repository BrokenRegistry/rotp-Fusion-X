package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class GalaxyCfg extends BaseCfg {
    // Assocated GUI: SetupGalaxyUI.java


    // ========================================================================
	// Initialization Methods
	//
    void loadGameOptions(Presets p, boolean u) { // u for Update
        IGameOptions gameOptions = p.gameOptions;
        //            "KEY"                    "Value"                                 "Options"
        p.setSetting("GALAXY SHAPE", gameOptions.selectedGalaxyShape(),      gameOptions.galaxyShapeOptions());
        p.setSetting("GALAXY SIZE",  gameOptions.selectedGalaxySize(),       gameOptions.galaxySizeOptions());
        p.setSetting("DIFFICULTY",   gameOptions.selectedGameDifficulty(),   gameOptions.gameDifficultyOptions());
        p.setSetting("OPPONENT AI",  gameOptions.selectedOpponentAIOption(), gameOptions.opponentAIOptions());
		p.setSetting("NB OPPONENTS", gameOptions.selectedNumberOpponents(), 0, gameOptions.maximumOpponentsOptions(),
                                                                           1, gameOptions.maximumOpponentsOptions());
    }
    void initComments(Presets p) {
        p.settingsMap().get("GALAXY SHAPE").headComments(p
			.new Comments(List.of(" ", "------------- Galaxy Options -------------", " ")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap();
        IGameOptions gameOptions = p.gameOptions;

        for (String userOption : p.selectedUserOptionsSet) {
            if (p.resetToDefault() || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
                setting = "GALAXY SHAPE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        gameOptions.selectedGalaxyShape(section.getValidSetting(userOption));
                }
                setting = "GALAXY SIZE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        gameOptions.selectedGalaxySize(section.getValidSetting(userOption));
                }
                setting = "DIFFICULTY";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        gameOptions.selectedGameDifficulty(section.getValidSetting(userOption));
                }
                setting = "OPPONENT AI";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        gameOptions.selectedOpponentAIOption(section.getValidSetting(userOption));
                }
                setting = "NB OPPONENTS";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption)) {
                        // the limits may have changed from previous settings
                        int min = 0;
                        int max = gameOptions.maximumOpponentsOptions();
                        section.setSettingOptions(min, max, 1, max);
                        gameOptions.selectedNumberOpponents(
                            Math.min(max, section.getIntegerSetting(userOption)));
                    }
                }
            } // \ if ACTION LOAD
        }
    }
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        IGameOptions gameOptions = p.gameOptions;
        setting = "GALAXY SHAPE";
        gameOptions.selectedGalaxyShape(defaultValues.get(setting));
        setting = "GALAXY SIZE";
        gameOptions.selectedGalaxySize(defaultValues.get(setting));
        setting = "DIFFICULTY";
        gameOptions.selectedGameDifficulty(defaultValues.get(setting));
        setting = "OPPONENT AI";
        gameOptions.selectedOpponentAIOption(defaultValues.get(setting));
        setting = "NB OPPONENTS";
        gameOptions.defaultOpponentsOptions();
    }
}
