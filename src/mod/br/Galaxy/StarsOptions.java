package mod.br.Galaxy;

/**
 * @author BrokenRegistry
 * Customize the number of star without planets
 */
public class StarsOptions {

    // ========================================================================
    // NO PLANET MULTIPLIER
    //
	/**
	 * Default value for NO_PLANET_MULTIPLIER
	 */
	public static final Double NO_PLANET_MULTIPLIER = 1.0;
	private static Double noPlanetMultiplier = NO_PLANET_MULTIPLIER;

	/**
	 * @return the noPlanetMultiplier
	 */
	public static Double getNoPlanetMultiplier() {
		return noPlanetMultiplier;
	}

	/**
	 * @param noPlanetMultiplier the noPlanetMultiplier to set
	 */
	public static void setNoPlanetMultiplier(Double noPlanetMultiplier) {
		StarsOptions.noPlanetMultiplier = noPlanetMultiplier;
	}
}