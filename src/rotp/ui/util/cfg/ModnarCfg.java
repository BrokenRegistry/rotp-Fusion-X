package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.UserPreferences;
import rotp.ui.util.cfg.Configs.Sections;

public class ModnarCfg {
    // Assocated GUI: StartModOptionsUI.java

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {
        p.initDV(u, "ALWAYS STAR GATES",  UserPreferences.alwaysStarGates());
		p.initDV(u, "ALWAYS THORIUM",     UserPreferences.alwaysThorium());
		p.initDV(u, "CHALLENGE MODE",     UserPreferences.challengeMode());
		p.initDV(u, "BATTLE SCOUT",       UserPreferences.battleScout());
		p.initDV(u, "COMPANION WORLDS",   UserPreferences.companionWorlds(), 0, 4, 0, 4);
        p.initDV(u, "RANDOM TECH START",  UserPreferences.randomTechStart());
		p.initDV(u, "CUSTOM DIFFICULTY",  UserPreferences.customDifficulty(), 20, 500, 20, 500);
		p.initDV(u, "DYNAMIC DIFFICULTY", UserPreferences.dynamicDifficulty());
    }
    static void initComments(Presets p) {
        p.settingsMap.get("ALWAYS STAR GATES").headComments(p
			.new Comments(List.of("------------- Modnar's Options -------------", " ")));
    }
    static void overrideGameOptions (Presets p, boolean resetToDefault) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;

        for (String userOption : p.selectedUserOptionsSet) {
            if (resetToDefault || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {

            } // \ if ACTION LOAD
        }
    }
}
