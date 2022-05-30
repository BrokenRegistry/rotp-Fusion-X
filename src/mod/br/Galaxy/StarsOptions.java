
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mod.br.Galaxy;

import java.util.Arrays;
import java.util.List;

import br.profileManager.src.main.java.PMutil;
import rotp.model.game.MOO1GameOptions;
import rotp.model.planet.PlanetType;

/**
 * @author BrokenRegistry
 * Customize the number of star without planets
 */
public class StarsOptions {

	private static final List<String> PLANET_TYPES = 
			Arrays.asList(MOO1GameOptions.planetTypes());

	// ========================================================================
    // NO PLANET MULTIPLIER
    //
	/**
	 * Default value for NO_PLANET_MULTIPLIER
	 */
	public static final Float DEFAULT_NO_PLANET_MULTIPLIER = 1.0f;
	private static final int   NONE_INDEX = PLANET_TYPES.indexOf(PlanetType.NONE);
	
	private static Float noPlanetMultiplier = DEFAULT_NO_PLANET_MULTIPLIER;

	/**
	 * @return the current noPlanetMultiplier
	 */
	public static Float getNoPlanetMultiplier() {
		return noPlanetMultiplier;
	}

	/**
	 * @param noPlanetMultiplier the new value
	 */
	public static void setNoPlanetMultiplier(Float noPlanetMultiplier) {
		StarsOptions.noPlanetMultiplier = noPlanetMultiplier;
	}
	
	/**
	 * @param cumSum the Cumulative Probability
	 * @return the new Cumulative Probability
	 */
	public static float[] changeCumulativeProbability(float[] cumSum) {
		float[] density = PMutil.probCumulToDensity(cumSum);
		density[NONE_INDEX] *= noPlanetMultiplier;
		return PMutil.probDensityToCumul(density);
	}
	

}