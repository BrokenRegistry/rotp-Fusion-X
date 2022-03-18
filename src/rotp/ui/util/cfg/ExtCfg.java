package rotp.ui.util.cfg;

import java.util.LinkedHashMap;
import java.util.List;

import rotp.ui.util.cfg.Configs.Sections;

public class ExtCfg extends BaseCfg {
    // Parameters are outside their class for an easier acces to their default value
    // Spacing
    private static boolean selectedMaximizeEmpiresSpacing = true;
    private static Integer selectedPrefStarsPerEmpire = 16;
    private static Integer selectedMinStarsPerEmpire  = 8;
    // Planet type
    private static Integer selectedNoPlanetPctMult = 100;

    // Getters and Setters
    static int selectedNoPlanetPctMult() {
        return selectedNoPlanetPctMult;
    }
    static void selectedNoPlanetPctMult(int val) {
        selectedNoPlanetPctMult =  val;
    }
    // Other Methods
    void loadGameOptions(Presets p, boolean u) {
		p.setSetting("MAXIMIZE EMPIRES SPACING", Spacing.selectedMaximizeEmpiresSpacing());
		p.setSetting("PREF STARS PER EMPIRE",    Spacing.selectedPrefStarsPerEmpire(), 0, 1000000, 16, 24);
		p.setSetting("MIN STARS PER EMPIRE",     Spacing.selectedMinStarsPerEmpire(), 0, 1000000, 4, 16);
		p.setSetting("NO PLANET PCTS MULT",      selectedNoPlanetPctMult(), 0, 1000000, 0, 200);
    }
    void initComments(Presets p) {
        p.settingsMap().get("MAXIMIZE EMPIRES SPACING").optionsComments(p
			.new Comments("Empires may want space to breath"));
        p.settingsMap().get("PREF STARS PER EMPIRE").optionsComments(p
			.new Comments("Determine default opponents number"));
        p.settingsMap().get("MAXIMIZE EMPIRES SPACING").headComments(p
            .new Comments(List.of("--------- BrokenRegistry's Options ---------", " ")));
    }
    void overrideGameOptions (Presets p) {
        String setting;
        Sections section;
        LinkedHashMap<String, Sections> settingsMap = p.settingsMap();

        for (String userOption : p.selectedUserOptionsSet) {
            if (p.resetToDefault() || settingsMap.get(Configs.ACTION_KEY).getPairValue(userOption).toUpperCase().contains("LOAD")) {
                setting = "MAXIMIZE EMPIRES SPACING";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        Spacing.selectedMaximizeEmpiresSpacing(section.getBooleanSetting(userOption));
                }
                setting = "MIN STARS PER EMPIRE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        Spacing.selectedMinStarsPerEmpire(section.getIntegerSetting(userOption));
                }
                setting = "PREF STARS PER EMPIRE";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        Spacing.selectedPrefStarsPerEmpire(section.getIntegerSetting(userOption));
                }
                setting = "NO PLANET PCTS MULT";
                if (settingsMap.containsKey(setting)) {
                    section = settingsMap.get(setting);
                    if (section.isSectionReadable(userOption))
                        ExtCfg.selectedNoPlanetPctMult(section.getIntegerSetting(userOption));
                }
            } // \ if ACTION LOAD
        }
    }
    @Override
    void setGameOptionsToDefault(Presets p) {
        String setting;
        LinkedHashMap<String, String> defaultValues = p.defaultValuesMap();
        setting = "MAXIMIZE EMPIRES SPACING";
        Spacing.selectedMaximizeEmpiresSpacing(Configs.toBoolean(defaultValues.get(setting)));
        setting = "MIN STARS PER EMPIRE";
        Spacing.selectedMinStarsPerEmpire(Configs.getInteger(defaultValues.get(setting), 16));
        setting = "PREF STARS PER EMPIRE";
        Spacing.selectedPrefStarsPerEmpire(Configs.getInteger(defaultValues.get(setting), 16));
        setting = "NO PLANET PCTS MULT";
        ExtCfg.selectedNoPlanetPctMult(Configs.getInteger(defaultValues.get(setting), 100));
    }
    // ------------------------------------------------------------------------
	// Nested Classes
	//
	// ------------------------------------------------------------------------
	// Spacing
	//
	public static class Spacing {
        //
		// Parameters
        //
        private static float minEmpireBuffer;
		private static float maxMinEmpireBuffer;
		private static float minOrionBuffer;
        //
		// Getters and Setters
        //
        static boolean selectedMaximizeEmpiresSpacing() {
            return selectedMaximizeEmpiresSpacing;
        }
        static void selectedMaximizeEmpiresSpacing(boolean val) {
            selectedMaximizeEmpiresSpacing = val;
        }
	    static int selectedMinStarsPerEmpire() {
            return selectedMinStarsPerEmpire;
        }
        static void selectedMinStarsPerEmpire(int val) {
            selectedMinStarsPerEmpire = val;
        }
    	static int selectedPrefStarsPerEmpire() {
            return selectedPrefStarsPerEmpire;
        }
        static void selectedPrefStarsPerEmpire(int val) {
            selectedPrefStarsPerEmpire = val;
        }
        //
    	// Public Methods
        //
		public static boolean isEnabled() {
            return true;
        }
		public static void init(int maxStars, int numOpps, float sysBuffer) {
			int minStarsPerEmpire = selectedMinStarsPerEmpire();
			if (selectedMaximizeEmpiresSpacing())
                minStarsPerEmpire = maxStars/numOpps;
			float maxMinEmpireFactor = 15f; // To avoid problems with strange galaxy shapes
			                                // Maybe To-Do Make this a new setting
			float minEmpireFactor = (minStarsPerEmpire + 1) / 3; // 8 spe -> 3; 12 spe -> 4;
			if (minEmpireFactor >= (maxMinEmpireFactor - 2))
				minEmpireFactor = maxMinEmpireFactor - 2;
			minEmpireBuffer    = sysBuffer * minEmpireFactor;
			maxMinEmpireBuffer = sysBuffer * maxMinEmpireFactor;
			minOrionBuffer     = sysBuffer * minEmpireFactor + 1;
		}
        public static float getMinEmpireBuffer() {
            return minEmpireBuffer;
        }
		public static float getMaxMinEmpireBuffer() {
            return maxMinEmpireBuffer;
        }
		public static float getMinOrionBuffer() {
            return minOrionBuffer;
        }
    } // \Spacing
}
