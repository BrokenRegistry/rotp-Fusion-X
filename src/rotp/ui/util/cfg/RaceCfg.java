package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class RaceCfg {

    static final List<String> EMPIRE_COLORS  =
	List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
			"brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {
        p.initDV(u, "PLAYER RACE",  p.gameOptions.selectedPlayerRace(), p.gameOptions.startingRaceOptions());
		p.initDV(u, "PLAYER COLOR", EMPIRE_COLORS.get(p.gameOptions.selectedPlayerColor()), EMPIRE_COLORS);
    }
    static void initComments(Presets p) {
        p.settingsMap.get("MAXIMIZE EMPIRES SPACING").headComments(p
            .new Comments(List.of("--------- BrokenRegistry's Options ---------", " ")));
    }
    static void overrideGameOptions (Presets p, String userOption) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        IGameOptions gameOptions = p.gameOptions;

        setting = "PLAYER RACE";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedPlayerRace(section.getValidSetting(userOption));
        }
        setting = "PLAYER COLOR";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(section.getValidSetting(userOption)));
        }
    }
}
