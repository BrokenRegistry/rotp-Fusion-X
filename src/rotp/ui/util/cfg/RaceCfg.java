package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.model.game.IGameOptions;
import br.config.Section;
//import rotp.ui.util.cfg.Configs.Section;
import br.config.comment.Comment;

public class RaceCfg extends BaseCfg {
    // Assocated GUI: SetupRaceUI.java

    static final List<String> EMPIRE_COLORS  =
	List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
			"brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

    // ========================================================================
	// Initialization Methods
	//
    @Override
    void loadGameOptions(Presets p, boolean u) {
        p.setSetting("PLAYER RACE",  p.gameOptions.selectedPlayerRace(), p.gameOptions.startingRaceOptions());
		p.setSetting("PLAYER COLOR", EMPIRE_COLORS.get(p.gameOptions.selectedPlayerColor()), EMPIRE_COLORS);
    }
    @Override
    void initComments(Presets p) {
        p.settingsMap().get("PLAYER RACE").headComments(
            new Comment(List.of("", "--------- Races Game Options ---------", " ")));
    }
    @Override
    void overrideGameOptions (Presets p) {
        String setting;
        Section section;
        LinkedHashMap<String, Section> settingsMap = p.settingsMap();
        IGameOptions gameOptions = p.gameOptions;
        for (String userOption : p.selectedUserOptionsSet) {
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
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = Presets.defaultValuesMap();
//        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        IGameOptions gameOptions = p.gameOptions;
        setting = "PLAYER RACE";
        gameOptions.selectedPlayerRace(defaultValues.get(setting));
        // gameOptions.selectedPlayerRace(gameOptions.startingRaceOptions().get(0));
        setting = "PLAYER COLOR";
        gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(defaultValues.get(setting)));
    }
}
