package mod.br.postSetup;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.CfgField;
import br.config.Comment;
import rotp.model.game.IGameOptions;
import rotp.ui.UserPreferences;

public class Group_Modnar extends AbstractGroup <IGameOptions> {

    Group_Modnar(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new AlwaysStarGates(gO));
        addSetting(new AlwaysThorium(gO));
        addSetting(new ChallengeMode(gO));
        addSetting(new BattleScouts(gO));
        addSetting(new CompanionWorlds(gO));
        addSetting(new RandomTechStart(gO));
        addSetting(new CustomDifficulty(gO));
        addSetting(new DynamicDifficulty(gO));
    }

    // ------------------------------------------------------------------------
    // ALWAYS STAR GATES
    //
    public static class AlwaysStarGates extends AbstractSetting <IGameOptions> {

        AlwaysStarGates(IGameOptions gO) { super(
            "ALWAYS STAR GATES",
            UserPreferences.alwaysStarGates());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.alwaysStarGates());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Modnar's Options -------------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // ALWAYS THORIUM
    //
    public static class AlwaysThorium extends AbstractSetting <IGameOptions> {

        AlwaysThorium(IGameOptions gO) { super(
            "ALWAYS THORIUM",
            UserPreferences.alwaysThorium());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.alwaysThorium());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // CHALLENGE MODE
    //
    public static class ChallengeMode extends AbstractSetting <IGameOptions> {

    	ChallengeMode(IGameOptions gO) { super(
            "CHALLENGE MODE",
            UserPreferences.challengeMode());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.challengeMode());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // BATTLE SCOUT
    //
    public static class BattleScouts extends AbstractSetting <IGameOptions> {

    	BattleScouts(IGameOptions gO) { super(
            "BATTLE SCOUT",
            UserPreferences.battleScout());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.battleScout());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // COMPANION WORLDS
    //
    public static class CompanionWorlds extends AbstractSetting <IGameOptions> {

        private static final int MIN   = 0;
        private static final int MAX   = 4;
        private static final int MIN_R = 0;
        private static final int MAX_R = 4;

    	CompanionWorlds(IGameOptions gO) { super(
            "COMPANION WORLDS",
            MIN, MAX, MIN_R, MAX_R,
            UserPreferences.companionWorlds());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(UserPreferences.companionWorlds());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // RANDOM TECH START
    //
    public static class RandomTechStart extends AbstractSetting <IGameOptions> {

    	RandomTechStart(IGameOptions gO) { super(
            "RANDOM TECH START",
            UserPreferences.randomTechStart());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.randomTechStart());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // CUSTOM DIFFICULTY
    //
    public static class CustomDifficulty extends AbstractSetting <IGameOptions> {

        private static final int MIN   = 20;
        private static final int MAX   = 500;
        private static final int MIN_R = 20;
        private static final int MAX_R = 500;

    	CustomDifficulty(IGameOptions gO) { super(
            "CUSTOM DIFFICULTY",
            MIN, MAX, MIN_R, MAX_R,
            UserPreferences.customDifficulty());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(UserPreferences.customDifficulty());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // DYNAMIC DIFFICULTY
    //
    public static class DynamicDifficulty extends AbstractSetting <IGameOptions> {

    	DynamicDifficulty(IGameOptions gO) { super(
            "DYNAMIC DIFFICULTY",
            UserPreferences.dynamicDifficulty());
        }
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(UserPreferences.dynamicDifficulty());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
//            gO.selectedPlayerRace(userOption);
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
//            gO.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {}
    }
}
