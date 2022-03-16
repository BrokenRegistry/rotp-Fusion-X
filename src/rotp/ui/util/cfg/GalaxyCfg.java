package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.game.SetupGalaxyUI;
import rotp.ui.util.cfg.Configs.Sections;
import rotp.util.Base;

public class GalaxyCfg implements Base {
    // Assocated GUI: SetupGalaxyUI.java

    // ========================================================================
	// Public Methods
	//
    private static void refreshUserPresets(SetupGalaxyUI p) {
        p.newGameOptions().selectedGameDifficulty(p.options().selectedGameDifficulty());
        p.repaint();
        p.newGameOptions().selectedOpponentAIOption(p.options().selectedOpponentAIOption());
        p.repaint();
        p.newGameOptions().selectedOpponentAIOption(p.options().selectedOpponentAIOption());
        p.repaint();
        p.newGameOptions().selectedGalaxySize(p.options().selectedGalaxySize());
        p.repaint();
        p.newGameOptions().selectedGalaxyShape(p.options().selectedGalaxyShape());
        p.repaint();
        p.newGameOptions().galaxyShape().quickGenerate();
        //p.backImg = null;
        p.repaint();
        p.newGameOptions().selectedNumberOpponents(
            Math.min(p.newGameOptions().maximumOpponentsOptions(), p.options().selectedNumberOpponents()));
        p.repaint();
    }
    public static void reloadLocalUserPresets(SetupGalaxyUI setupGalaxyUI) {
        Presets presets = new Presets().readUserConfig(setupGalaxyUI.options());
        // User asked for this then it overload GLOBAL ENABLE
        overrideGameOptions(presets, false); // resetToDefault = false
        // refreshUserPresets(setupGalaxyUI);
    }
    public static void reloadGlobalUserPresets(SetupGalaxyUI setupGalaxyUI) {
        Presets presets = new Presets().readUserConfig(setupGalaxyUI.options());
        // User asked for this then it overload GLOBAL ENABLE
        presets.overrideGameOptions(false); // resetToDefault = false
        // refreshUserPresets(setupGalaxyUI);
    }
    public static void reloadDefaultConfig(SetupGalaxyUI setupGalaxyUI) {
        Presets presets = new Presets().readUserConfig(setupGalaxyUI.options());
		overrideGameOptions(presets, true); // resetToDefault = true
        // refreshUserPresets(setupGalaxyUI);
	}
    // ========================================================================
	// Initialization Methods
	//
    static void loadGameOptions(Presets p, boolean u) { // u for Update
        IGameOptions gameOptions = p.gameOptions;
        //            "KEY"                    "Value"                                 "Options"
        p.initDV(u, "GALAXY SHAPE", gameOptions.selectedGalaxyShape(),      gameOptions.galaxyShapeOptions());
        p.initDV(u, "GALAXY SIZE",  gameOptions.selectedGalaxySize(),       gameOptions.galaxySizeOptions());
        p.initDV(u, "DIFFICULTY",   gameOptions.selectedGameDifficulty(),   gameOptions.gameDifficultyOptions());
        p.initDV(u, "OPPONENT AI",  gameOptions.selectedOpponentAIOption(), gameOptions.opponentAIOptions());
		p.initDV(u, "NB OPPONENTS", gameOptions.selectedNumberOpponents(), 0, gameOptions.maximumOpponentsOptions(),
                                                                           1, gameOptions.maximumOpponentsOptions());
    }
    static void initComments(Presets p) {
        p.settingsMap.get("GALAXY SHAPE").headComments(p
			.new Comments(List.of(" ", "------------- Galaxy Options -------------", " ")));
    }
    static void overrideGameOptions (Presets p, boolean resetToDefault) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        IGameOptions gameOptions = p.gameOptions;

        for (String userOption : p.selectedUserOptionsSet) {
            if (resetToDefault || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
                setting = "GALAXY SHAPE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedGalaxyShape(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedGalaxyShape(section.getValidSetting(userOption));
                }
                setting = "GALAXY SIZE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedGalaxySize(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedGalaxySize(section.getValidSetting(userOption));
                }
                setting = "DIFFICULTY";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedGameDifficulty(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedGameDifficulty(section.getValidSetting(userOption));
                }
                setting = "OPPONENT AI";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedOpponentAIOption(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedOpponentAIOption(section.getValidSetting(userOption));
                }
                setting = "NB OPPONENTS";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.defaultOpponentsOptions();
                    else if (section.isSectionReadable(userOption)) {
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
}
