package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.UserPreferences;
import rotp.ui.util.cfg.Configs.Sections;

public class GovernorCfg extends BaseCfg {
    // Assocated GUI: .java

    // Other Methods
    void loadGameOptions(Presets p, boolean u) {
        p.setSetting("GOVERNOR ON BY DEFAULT",    UserPreferences.governorOnByDefault());
		p.setSetting("AUTOSPEND ON BY DEFAULT",   UserPreferences.governorAutoSpendByDefault());
		p.setSetting("DEFAULT MAX BASES",         UserPreferences.defaultMaxBases(), 0, 100000, 0, 100);
		p.setSetting("DIVERT EXCESS TO RESEARCH", UserPreferences.divertColonyExcessToResearch());
    }
    void initComments(Presets p) {
        p.settingsMap().get("GOVERNOR ON BY DEFAULT").headComments(p
			.new Comments(List.of("------------ Governor's Options ------------", "")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap();
        // IGameOptions gameOptions = p.gameOptions;
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
