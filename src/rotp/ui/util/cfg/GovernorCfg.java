package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.UserPreferences;
import br.config.Section;
//import rotp.ui.util.cfg.Configs.Section;
import br.config.comment.Comment;

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
        p.settingsMap().get("GOVERNOR ON BY DEFAULT").headComments(
			new Comment(List.of("------------ Governor's Options ------------", "")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Section section;
        LinkedHashMap<String, Section> settingsMap = p.settingsMap();
        // IGameOptions gameOptions = p.gameOptions;
        for (String userOption : p.selectedUserOptionsSet) {
            if (p.resetToDefault() 
            		|| settingsMap.get(Configs.ACTION_KEY)
            			.getCfgLine(userOption).toKey().contains("LOAD")) {

            } // \ if ACTION LOAD
        }
    }
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = Presets.defaultValuesMap();
//        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        // IGameOptions gameOptions = p.gameOptions;

    }
}
