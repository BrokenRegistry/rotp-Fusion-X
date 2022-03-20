package rotp.ui.util.cfg;

import java.util.List;

import rotp.model.game.IGameOptions;

public class Group_Race extends Group_Base {

    Group_Race(IGameOptions gameOptions) {
       super(gameOptions);
    }
    @Override
    void initSettingList(IGameOptions gameOptions) {
        addSetting(new Player_Race(gameOptions));
        addSetting(new Player_Color(gameOptions));
    }

    // ------------------------------------------------------------------------
    // PLAYER RACE
    //
    public static class Player_Race extends BaseSetting {

        Player_Race(IGameOptions gameOptions) {
            super(
                "PLAYER RACE",
                gameOptions.selectedPlayerRace(),
                gameOptions.startingRaceOptions()
                );
        }
        @Override
        void selectedGameOptions(IGameOptions gameOptions, String userOption) {
            gameOptions.selectedPlayerRace(getValidSetting(userOption));
        }
        @Override
        void setGameOptionsToDefault(IGameOptions gameOptions) {
            gameOptions.selectedPlayerRace(getDefaultValue());
        }
        @Override
        void initComments() {
            headComments(new Comments(List.of(
                " ",
                "--------- Races Game Options ---------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // PLAYER COLOR
    //
    public static class Player_Color extends BaseSetting {
        static final List<String> EMPIRE_COLORS  =
        List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
                "brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

        Player_Color(IGameOptions gameOptions) {
            super(
                "PLAYER COLOR",
                EMPIRE_COLORS.get(gameOptions.selectedPlayerColor()),
                EMPIRE_COLORS
                );
        }
        @Override
        void selectedGameOptions(IGameOptions gameOptions, String userOption) {
            gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(getValidSetting(userOption)));
        }
        @Override
        void setGameOptionsToDefault(IGameOptions gameOptions) {
            gameOptions.selectedPlayerColor(EMPIRE_COLORS.indexOf(getDefaultValue()));
        }
        @Override
        void initComments() {}
    }
}
