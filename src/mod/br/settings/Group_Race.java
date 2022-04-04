package mod.br.settings;

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
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }        
        @Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedPlayerRace(initialOption());
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
            intToColor(gO));
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return intToColor(gO);
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }    
        @Override
        public String getFromUI (IGameOptions gO) {
        	return intToColor(gO);
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedPlayerColor(colorToInt(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedPlayerColor(colorToInt(initialOption()));
        }
        @Override
        public void initComments() {}
        // -----------------------------------------------------------------
        // Private Converter Methods
        //
        private static String intToColor (IGameOptions gO) {
        	return EMPIRECOLORS.get(gO.selectedPlayerColor());
        }        
        private static int colorToInt (String color) {
        	return EMPIRECOLORS.indexOf(color);
        }
    }
}