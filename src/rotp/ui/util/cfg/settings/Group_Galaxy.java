package rotp.ui.util.cfg.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.comment.Comment;
import rotp.model.game.IGameOptions;

public class Group_Galaxy extends AbstractGroup <IGameOptions> {

    Group_Galaxy(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new GalaxyShape(gO));
        addSetting(new GalaxySize(gO));
        addSetting(new Difficulty(gO));
        addSetting(new OpponentAI(gO));
        addSetting(new NbOpponent(gO));    
    }

    // ------------------------------------------------------------------------
    // GALAXY SHAPE
    //
    public static class GalaxyShape extends AbstractSetting <IGameOptions> {

        GalaxyShape(IGameOptions gO) { super(
            "GALAXY SHAPE",
            gO.galaxyShapeOptions(),
            gO.selectedGalaxySize());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedGalaxyShape(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedGalaxyShape(firstOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Galaxy Options -------------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // GALAXY SIZE
    //
    public static class GalaxySize extends AbstractSetting <IGameOptions> {

    	GalaxySize(IGameOptions gO) { super(
            "GALAXY SIZE",
            gO.galaxySizeOptions(),
            gO.selectedGalaxySize());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedGalaxySize(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedGalaxySize(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // DIFFICULTY
    //
    public static class Difficulty extends AbstractSetting <IGameOptions> {

    	Difficulty(IGameOptions gO) { super(
            "DIFFICULTY",
            gO.gameDifficultyOptions(),
            gO.selectedGameDifficulty());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedGameDifficulty(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedGameDifficulty(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // OPPONENT AI
    //
    public static class OpponentAI extends AbstractSetting <IGameOptions> {

    	OpponentAI(IGameOptions gO) { super(
            "OPPONENT AI",
            gO.opponentAIOptions(),
            gO.selectedOpponentAIOption());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
            gO.selectedOpponentAIOption(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedOpponentAIOption(firstOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // NB OPPONENTS
    //
    public static class NbOpponent extends AbstractSetting <IGameOptions> {

    	NbOpponent(IGameOptions gO) { super(
            "NB OPPONENTS",
            0, gO.maximumOpponentsOptions(), 1, gO.maximumOpponentsOptions(),
            gO.selectedNumberOpponents());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	// the limits may have changed from previous settings
            int min = 0;
            int max = gO.maximumOpponentsOptions();
            setSettingOptions(min, max, 1, max);
            gO.selectedNumberOpponents(
                Math.min(max, getIntegerOption(userOption)));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
            gO.selectedNumberOpponents(firstValue().toInteger());
        }
        @Override
        public void initComments() {}
    }

}
