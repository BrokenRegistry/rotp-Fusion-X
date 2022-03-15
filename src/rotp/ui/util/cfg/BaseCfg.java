package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;
import rotp.ui.util.cfg.Configs.Sections;

public class BaseCfg {

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {

    }
    static void initComments(Presets p) {
        p.settingsMap.get("MAXIMIZE EMPIRES SPACING").headComments(p
            .new Comments(List.of("--------- BrokenRegistry's Options ---------", " ")));
    }
    static void overrideGameOptions (Presets p, String userOption) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        // IGameOptions gameOptions = p.gameOptions;

    }
}
