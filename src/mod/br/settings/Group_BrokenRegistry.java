package mod.br.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.AbstractGroup;
import br.config.CfgField;
import br.config.Comment;
import mod.br.alteration.GalaxySpacing;
import mod.br.alteration.StarsOptions;
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
            GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return CfgField.toYesNoString(GalaxySpacing.isMaximizeEmpiresSpacing());
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return CfgField.toYesNoString(GalaxySpacing.isMaximizeEmpiresSpacing());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setMaximizeEmpiresSpacing(getBooleanOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setMaximizeEmpiresSpacing(initialValue().getOrDefault(GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING));
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
            GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getPreferedStarsPerEmpire());
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getPreferedStarsPerEmpire());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setPreferedStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setPreferedStarsPerEmpire(initialValue().getOrDefault(GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE));
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
            GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getMinStarsPerEmpire());
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getMinStarsPerEmpire());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setMinStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setMinStarsPerEmpire(initialValue().getOrDefault(GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE));
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
            StarsOptions.NO_PLANET_PCT_MULTIPLIER);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(StarsOptions.getNoPlanetPctMultiplier());
        }
        @Override
        public void putToGame(IGameOptions gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(StarsOptions.getNoPlanetPctMultiplier());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	StarsOptions.setNoPlanetPctMultiplier(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	StarsOptions.setNoPlanetPctMultiplier(initialValue().getOrDefault(StarsOptions.NO_PLANET_PCT_MULTIPLIER));
        }
        @Override
        public void initComments() {}
    }
}
