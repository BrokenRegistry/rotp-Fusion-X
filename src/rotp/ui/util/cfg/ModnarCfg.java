package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.UserPreferences;
import rotp.ui.util.cfg.Configs.Sections;

public class ModnarCfg extends BaseCfg {
    // Assocated GUI: StartModOptionsUI.java

    // Other Methods
    void loadGameOptions(Presets p, boolean u) {
        p.setSetting("ALWAYS STAR GATES",  UserPreferences.alwaysStarGates());
		p.setSetting("ALWAYS THORIUM",     UserPreferences.alwaysThorium());
		p.setSetting("CHALLENGE MODE",     UserPreferences.challengeMode());
		p.setSetting("BATTLE SCOUT",       UserPreferences.battleScout());
		p.setSetting("COMPANION WORLDS",   UserPreferences.companionWorlds(), 0, 4, 0, 4);
        p.setSetting("RANDOM TECH START",  UserPreferences.randomTechStart());
		p.setSetting("CUSTOM DIFFICULTY",  UserPreferences.customDifficulty(), 20, 500, 20, 500);
		p.setSetting("DYNAMIC DIFFICULTY", UserPreferences.dynamicDifficulty());
    }
    void initComments(Presets p) {
        p.settingsMap().get("ALWAYS STAR GATES").headComments(p
			.new Comments(List.of("------------- Modnar's Options -------------", " ")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap();

        for (String userOption : p.selectedUserOptionsSet) {
            if (p.resetToDefault() || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {

            } // \ if ACTION LOAD
        }
    }
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        // IGameOptions gameOptions = p.gameOptions;

    }
}
