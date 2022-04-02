package mod.br.alteration;

public class StarsOptions {

    // ========================================================================
    // NO PLANET PCT MULTIPLIER
    //
	public static final int NO_PLANET_PCT_MULTIPLIER = 100;
	private static Integer noPlanetPctMultiplier = NO_PLANET_PCT_MULTIPLIER;
    
	public static void setNoPlanetPctMultiplier(Integer i) {
		noPlanetPctMultiplier = i;
	}
	public static Integer getNoPlanetPctMultiplier() {
		return noPlanetPctMultiplier;
	}
}
