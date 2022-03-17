package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class RaceCfg extends BaseCfg {
    // Assocated GUI: SetupRaceUI.java

    static final List<String> EMPIRE_COLORS  =
	List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
			"brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

    // ========================================================================
	// Public Methods
	//
    // public static void reloadLocalUserPresets(IGameOptions gameOptions) {
    //     Presets presets = new Presets().readUserConfig(gameOptions);
    //     // User asked for this then it overload GLOBAL ENABLE
    //     overrideGameOptions(presets, false); // resetToDefault = false
    // }
    // public static void reloadGlobalUserPresets(IGameOptions gameOptions) {
    //     Presets presets = new Presets().readUserConfig(gameOptions);
    //     // User asked for this then it overload GLOBAL ENABLE
    //     presets.overrideGameOptions(false); // resetToDefault = false
    // }
    // public static void reloadDefaultConfig(IGameOptions gameOptions) {
    //     Presets presets = new Presets().readUserConfig(gameOptions);
	// 	overrideGameOptions(presets, true); // resetToDefault = true
	// }
    // ========================================================================
	// Initialization Methods
	//
    @Override
    void loadGameOptions(Presets p, boolean u) {
        p.initDV(u, "PLAYER RACE",  p.gameOptions.selectedPlayerRace(), p.gameOptions.startingRaceOptions());
		p.initDV(u, "PLAYER COLOR", EMPIRE_COLORS.get(p.gameOptions.selectedPlayerColor()), EMPIRE_COLORS);
    }
    @Override
    void initComments(Presets p) {
        p.settingsMap.get("PLAYER RACE").headComments(p
            .new Comments(List.of("", "--------- Races Game Options ---------", " ")));
    }
    @Override
    void overrideGameOptions (Presets p, boolean resetToDefault) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        IGameOptions gameOptions = p.gameOptions;
        for (String userOption : p.selectedUserOptionsSet) {
            if (resetToDefault || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
                setting = "PLAYER RACE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedPlayerRace(section.getDefaultValue());
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedPlayerRace(section.getValidSetting(userOption));
                }
                setting = "PLAYER COLOR";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (resetToDefault)
                        gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(section.getDefaultValue()));
                    else if (section.isSectionReadable(userOption))
                        gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(section.getValidSetting(userOption)));
                }
            } // \ if ACTION LOAD
        }
    }
}
