
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

package rotp.mod.br.Galaxy;

import mod.br.Galaxy.GalaxySpacing;
import mod.br.Galaxy.StarsOptions;

//import mod.br.alteration.GalaxySpacing;

/**
 * @author BrokenRegistry
 * Some tools to optimize empires spacing
 */
public class GalaxyOptions {
	
	/**
	 * @param maxStars the {@code Integer} maximum number of stars per empire
	 * @param numOpps the {@code Integer} number of opponents
	 * @param sysBuffer the {@code float} some space reserve factor
	 */
	public static void initSpacing(int maxStars, int numOpps, float sysBuffer) {
		GalaxySpacing.initSpacing(maxStars, numOpps, sysBuffer);
	}

	/**
	 * @return minEmpireBuffer {@code float} value
	 */
    public static float getMinEmpireBuffer() {
         return GalaxySpacing.getMinEmpireBuffer();
    }

    /**
	 * @return maxMinEmpireBuffer {@code float} value
	 */
	public static float getMaxMinEmpireBuffer() {
         return GalaxySpacing.getMaxMinEmpireBuffer();
    }

	/**
	 * @return minOrionBuffer {@code float} value
	 */
	public static float getMinOrionBuffer() {
		return GalaxySpacing.getMinOrionBuffer();
    }
	
	/**
	 * @param cumSum the Cumulative Probability
	 * @return the new Cumulative Probability
	 */
	public static float[] changeCumulativeProbability(float[] cumSum) {
		return StarsOptions.changeCumulativeProbability(cumSum);
	}
		
}
