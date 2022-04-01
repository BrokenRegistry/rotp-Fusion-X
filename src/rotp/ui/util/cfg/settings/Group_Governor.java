package rotp.ui.util.cfg.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.comment.Comment;
import rotp.model.game.IGameOptions;

public class Group_Governor extends AbstractGroup <IGameOptions> {
	
	// TODO ALL

    Group_Governor(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new GovernorOnByDefault(gO));
        addSetting(new AutospendOnByDefault(gO));
        addSetting(new DefaultMaxBases(gO));
        addSetting(new DivertExcessToResearch(gO));
    }

    // ------------------------------------------------------------------------
    // GOVERNOR ON BY DEFAULT
    //
    public static class GovernorOnByDefault extends AbstractSetting <IGameOptions> {

    	GovernorOnByDefault(IGameOptions gO) { super(
            "GOVERNOR ON BY DEFAULT",
            gO.galaxySizeOptions(),
            gO.selectedGalaxySize());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return gO.selectedPlayerRace();
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
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
    // AUTOSPEND ON BY DEFAULT
    //
    public static class AutospendOnByDefault extends AbstractSetting <IGameOptions> {

    	AutospendOnByDefault(IGameOptions gO) { super(
            "AUTOSPEND ON BY DEFAULT",
            gO.galaxySizeOptions(),
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
    // DEFAULT MAX BASES
    //
    public static class DefaultMaxBases extends AbstractSetting <IGameOptions> {

    	DefaultMaxBases(IGameOptions gO) { super(
            "DEFAULT MAX BASES",
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
    // DIVERT EXCESS TO RESEARCH
    //
    public static class DivertExcessToResearch extends AbstractSetting <IGameOptions> {

    	DivertExcessToResearch(IGameOptions gO) { super(
            "DIVERT EXCESS TO RESEARCH",
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
}
