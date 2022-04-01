package rotp.ui.util.cfg.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.CfgField;
import br.config.comment.Comment;
import rotp.model.game.IGameOptions;

public class Group_BrokenRegistry extends AbstractGroup <IGameOptions> {
	
// TODO ALL
	
    Group_BrokenRegistry(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new MaximizeEmpiresSpacing(gO));
        addSetting(new PrefStarsPerEmpire(gO));
        addSetting(new MinStarsPerEmpire(gO));
        addSetting(new NoPlanetPctMult(gO));
    }

    // ========================================================================
    // MAXIMIZE EMPIRES SPACING
    //
    public static class MaximizeEmpiresSpacing extends AbstractSetting <IGameOptions> {

        private static final boolean DEFAULT = false;

        private static boolean selectedMaximizeEmpiresSpacing = DEFAULT;
        
    	MaximizeEmpiresSpacing(IGameOptions gO) { super(
            "MAXIMIZE EMPIRES SPACING",
            DEFAULT);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(selectedMaximizeEmpiresSpacing());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	selectedMaximizeEmpiresSpacing(getBooleanOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	selectedMaximizeEmpiresSpacing(firstValue().getOrDefault(DEFAULT));
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Extended Options -------------",
                " ")));
        }
        // ------------------------------------------------
        // Getters and Setters
        //
        private boolean selectedMaximizeEmpiresSpacing() {
            return selectedMaximizeEmpiresSpacing;
        }
        private void selectedMaximizeEmpiresSpacing(boolean val) {
        	selectedMaximizeEmpiresSpacing = val;
        }
    }
    // ========================================================================
    // PREF STARS PER EMPIRE
    //
    public static class PrefStarsPerEmpire extends AbstractSetting <IGameOptions> {

        private static final int DEFAULT = 16;
        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 16;
        private static final int MAX_R = 24;
        private static Integer selectedPrefStarsPerEmpire = DEFAULT;
    	
    	PrefStarsPerEmpire(IGameOptions gO) { super(
            "PREF STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            DEFAULT);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(selectedPrefStarsPerEmpire());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	selectedPrefStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	selectedPrefStarsPerEmpire(firstValue().getOrDefault(DEFAULT));
        }
        @Override
        public void initComments() {}
        // ------------------------------------------------
        // Getters and Setters
        //
        private int selectedPrefStarsPerEmpire() {
            return selectedPrefStarsPerEmpire;
        }
        private void selectedPrefStarsPerEmpire(int val) {
        	selectedPrefStarsPerEmpire = val;
        }
    }
    // ========================================================================
    // MIN STARS PER EMPIRE
    //
    public static class MinStarsPerEmpire extends AbstractSetting <IGameOptions> {

        private static final int DEFAULT = 8;
        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 4;
        private static final int MAX_R = 16;
        private static Integer selectedMinStarsPerEmpire  = DEFAULT;
        
    	MinStarsPerEmpire(IGameOptions gO) { super(
            "MIN STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            DEFAULT);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(selectedMinStarsPerEmpire());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	selectedMinStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	selectedMinStarsPerEmpire(firstValue().getOrDefault(DEFAULT));
        }
        @Override
        public void initComments() {}
        // ------------------------------------------------
        // Getters and Setters
        //
        private int selectedMinStarsPerEmpire() {
            return selectedMinStarsPerEmpire;
        }
        private void selectedMinStarsPerEmpire(int val) {
        	selectedMinStarsPerEmpire = val;
        }
    }
    // ========================================================================
    // NO PLANET PCT MULT
    //
    public static class NoPlanetPctMult extends AbstractSetting <IGameOptions> {

        private static final int DEFAULT = 100;
        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 0;
        private static final int MAX_R = 200;
        private static int selectedNoPlanetPctMult = DEFAULT;
        
    	NoPlanetPctMult(IGameOptions gO) { super(
            "NO PLANET PCT MULT",
            MIN, MAX, MIN_R, MAX_R,
            DEFAULT);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(selectedNoPlanetPctMult());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	selectedNoPlanetPctMult(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	selectedNoPlanetPctMult(firstValue().getOrDefault(DEFAULT));
        }
        @Override
        public void initComments() {}
        // ------------------------------------------------
        // Getters and Setters
        //
        private int selectedNoPlanetPctMult() {
            return selectedNoPlanetPctMult;
        }
        private void selectedNoPlanetPctMult(int val) {
            selectedNoPlanetPctMult = val;
        }
    }
}
