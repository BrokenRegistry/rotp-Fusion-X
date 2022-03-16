package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.UserPreferences;
import rotp.ui.util.cfg.Configs.Sections;

public class GovernorCfg {
    // Assocated GUI: .java

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {
        p.initDV(u, "GOVERNOR ON BY DEFAULT",    UserPreferences.governorOnByDefault());
		p.initDV(u, "AUTOSPEND ON BY DEFAULT",   UserPreferences.governorAutoSpendByDefault());
		p.initDV(u, "DEFAULT MAX BASES",         UserPreferences.defaultMaxBases(), 0, 100000, 0, 100);
		p.initDV(u, "DIVERT EXCESS TO RESEARCH", UserPreferences.divertColonyExcessToResearch());
    }
    static void initComments(Presets p) {
        p.settingsMap.get("GOVERNOR ON BY DEFAULT").headComments(p
			.new Comments(List.of("------------ Governor's Options ------------", "")));
    }
    static void overrideGameOptions (Presets p, boolean resetToDefault) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        // IGameOptions gameOptions = p.gameOptions;
        for (String userOption : p.selectedUserOptionsSet) {
            if (resetToDefault || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {

            } // \ if ACTION LOAD
        }

    }
}
