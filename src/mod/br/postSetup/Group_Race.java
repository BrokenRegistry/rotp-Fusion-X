package mod.br.postSetup;

import java.util.List;

import br.config.AbstractSetting;
import br.config.Comment;
import br.config.AbstractGroup;
import rotp.model.game.IGameOptions;

public class Group_Race extends AbstractGroup <IGameOptions> {

    Group_Race(IGameOptions gO) {
       super(gO);
    }
    @Override
	protected void initSettingList(IGameOptions gO) {
        addSetting(new PlayerRace(gO));
        addSetting(new PlayerColor(gO));
    }

    // ------------------------------------------------------------------------
    // PLAYER RACE
    //
    public static class PlayerRace extends AbstractSetting <IGameOptions> {

        PlayerRace(IGameOptions gO) { super(
            "PLAYER RACE",
            gO.startingRaceOptions(),
            gO.selectedPlayerRace());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedPlayerRace(firstOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "--------- Races Game Options ---------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // PLAYER COLOR
    //
    public static class PlayerColor extends AbstractSetting <IGameOptions> {
        static final List<String> EMPIRECOLORS  =
        List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
                "brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

        PlayerColor(IGameOptions gO) { super(
            "PLAYER COLOR",
            EMPIRECOLORS,
            EMPIRECOLORS.get(gO.selectedPlayerColor()));
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return EMPIRECOLORS.get(gO.selectedPlayerColor());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedPlayerColor(EMPIRECOLORS.indexOf(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedPlayerColor(EMPIRECOLORS.indexOf(firstOption()));
        }
        @Override
        public void initComments() {}
    }
}
