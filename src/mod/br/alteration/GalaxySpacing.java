
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

package mod.br.alteration;

public class GalaxySpacing {

    // ========================================================================
    // MAXIMIZE EMPIRES SPACING
    //
	public static final boolean DEFAULT_MAXIMIZE_EMPIRES_SPACING = false;
    private static boolean maximizeEmpiresSpacing = DEFAULT_MAXIMIZE_EMPIRES_SPACING;
    
	public static boolean isMaximizeEmpiresSpacing() {
		return maximizeEmpiresSpacing;
	}
	public static void setMaximizeEmpiresSpacing(boolean b) {
		maximizeEmpiresSpacing = b;
	}
    private static float minEmpireBuffer;
	private static float maxMinEmpireBuffer;
	private static float minOrionBuffer;

	public static void initSpacing(int maxStars, int numOpps, float sysBuffer) {
		int minStarsPerEmpire = getMinStarsPerEmpire();
		if (isMaximizeEmpiresSpacing())
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
	// ========================================================================
    // PREFERED STARS PER EMPIRE
    //
	public static final int DEFAULT_PREFERED_STARS_PER_EMPIRE = 16;
    private static Integer preferedStarsPerEmpire = DEFAULT_PREFERED_STARS_PER_EMPIRE;
    
    public static Integer getPreferedStarsPerEmpire() {
		return preferedStarsPerEmpire;
	}
	public static void setPreferedStarsPerEmpire(Integer i) {
		preferedStarsPerEmpire = i;
	}
    // ========================================================================
    // MIN STARS PER EMPIRE
    //
	public static final int DEFAULT_MIN_STARS_PER_EMPIRE = 8;
    private static Integer minStarsPerEmpire  = DEFAULT_MIN_STARS_PER_EMPIRE;

    public static Integer getMinStarsPerEmpire() {
		return minStarsPerEmpire;
	}
	public static void setMinStarsPerEmpire(Integer i) {
		minStarsPerEmpire = i;
	}
}
