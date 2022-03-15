package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import rotp.ui.util.cfg.Configs.Sections;

public class GalaxyCfg {

    // Other Methods
    static void loadGameOptions(Presets p, boolean u) {
        p.initDV(u, "GALAXY SHAPE", p.gameOptions.selectedGalaxyShape(),
                                    p.gameOptions.galaxyShapeOptions());
        p.initDV(u, "NB OPPONENTS", p.gameOptions.selectedNumberOpponents(),
                                    0, p.gameOptions.maximumOpponentsOptions(),
                                    1, p.gameOptions.maximumOpponentsOptions());

    }
    static void initComments(Presets p) {
        p.settingsMap.get("GALAXY SIZE").headComments(p
			.new Comments(List.of("------------- Galaxy Options -------------", " ")));

    }
    static void overrideGameOptions (Presets p, String userOption) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap;
        IGameOptions gameOptions = p.gameOptions;

        setting = "GALAXY SHAPE";
        if (settingsMap.containsKey(setting)) {
            section = settingsMap.get(setting);
            if (section.isSectionReadable(userOption))
                gameOptions.selectedGalaxyShape(section.getValidSetting(userOption));
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
    }
}
