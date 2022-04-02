package rotp.mod.br.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.CfgField;
import br.config.comment.Comment;
import rotp.mod.br.settings.BR_Options;
import rotp.model.game.IGameOptions;

public class Group_BrokenRegistry extends AbstractGroup <IGameOptions> {
	
    Group_BrokenRegistry(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new MaximizeEmpiresSpacing(gO));
        addSetting(new PreferedStarsPerEmpire(gO));
        addSetting(new MinStarsPerEmpire(gO));
        addSetting(new NoPlanetPctMultiplier(gO));
    }

    // ========================================================================
    // MAXIMIZE EMPIRES SPACING
    //
    public static class MaximizeEmpiresSpacing extends AbstractSetting <IGameOptions> {
     
    	MaximizeEmpiresSpacing(IGameOptions gO) { super(
            "MAXIMIZE EMPIRES SPACING",
            BR_Options.DEFAULT_MAXIMIZE_EMPIRES_SPACING);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return CfgField.toYesNoString(BR_Options.isMaximizeEmpiresSpacing());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	BR_Options.setMaximizeEmpiresSpacing(getBooleanOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	BR_Options.setMaximizeEmpiresSpacing(firstValue().getOrDefault(BR_Options.DEFAULT_MAXIMIZE_EMPIRES_SPACING));
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Extended Options -------------",
                " ")));
        }
    }
    // ========================================================================
    // PREF STARS PER EMPIRE
    //
    public static class PreferedStarsPerEmpire extends AbstractSetting <IGameOptions> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 16;
        private static final int MAX_R = 24;
    	
    	PreferedStarsPerEmpire(IGameOptions gO) { super(
            "PREF STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            BR_Options.DEFAULT_PREFERED_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(BR_Options.getPreferedStarsPerEmpire());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	BR_Options.setPreferedStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	BR_Options.setPreferedStarsPerEmpire(firstValue().getOrDefault(BR_Options.DEFAULT_PREFERED_STARS_PER_EMPIRE));
        }
        @Override
        public void initComments() {}
    }
    // ========================================================================
    // MIN STARS PER EMPIRE
    //
    public static class MinStarsPerEmpire extends AbstractSetting <IGameOptions> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 4;
        private static final int MAX_R = 16;
        
    	MinStarsPerEmpire(IGameOptions gO) { super(
            "MIN STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            BR_Options.DEFAULT_MIN_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(BR_Options.getMinStarsPerEmpire());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	BR_Options.setMinStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	BR_Options.setMinStarsPerEmpire(firstValue().getOrDefault(BR_Options.DEFAULT_MIN_STARS_PER_EMPIRE));
        }
        @Override
        public void initComments() {}

    }
    // ========================================================================
    // NO PLANET PCT MULT
    //
    public static class NoPlanetPctMultiplier extends AbstractSetting <IGameOptions> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 0;
        private static final int MAX_R = 200;
        
    	NoPlanetPctMultiplier(IGameOptions gO) { super(
            "NO PLANET PCT MULT",
            MIN, MAX, MIN_R, MAX_R,
            BR_Options.NO_PLANET_PCT_MULTIPLIER);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getSelectedOption (IGameOptions gO) {
        	return String.valueOf(BR_Options.getNoPlanetPctMultiplier());
        }
        @Override
        public void setSelectedOption(IGameOptions gO, String userOption) {
        	BR_Options.setNoPlanetPctMultiplier(getIntegerOption(userOption));
        }
        @Override
        public void setSelectedOptionToInitial(IGameOptions gO) {
        	BR_Options.setNoPlanetPctMultiplier(firstValue().getOrDefault(BR_Options.NO_PLANET_PCT_MULTIPLIER));
        }
        @Override
        public void initComments() {}
    }
}
