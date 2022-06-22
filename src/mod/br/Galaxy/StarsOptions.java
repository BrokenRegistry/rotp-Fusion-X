
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *	 https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mod.br.Galaxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.profileManager.src.main.java.PMutil;
import rotp.model.game.MOO1GameOptions;
import rotp.model.planet.PlanetType;

/**
 * @author BrokenRegistry
 * Customize the number of star without planets
 */
public class StarsOptions {

	/**
	 * Default value for every PROBABILITY_MULTIPLIER
	 */
	private static final List<String> PLANET_TYPES = 
			Arrays.asList(MOO1GameOptions.planetTypes());

	// ========================================================================
	// COMMON TOOLS
	//
	// ===== FOR DEBUG =====
	static String asString(List<Float> value) {
		if (value == null) {
			return "Null";
		}
		Float[] array = value.toArray(new Float[0]);
		return asString(array);
	}
	static String asString(float[] probabilityDensity) {
		if (probabilityDensity == null) {
			return "Null";
		}
		return Arrays.toString(probabilityDensity);
	}
	static String asString(Float[] probabilityDensity) {
		if (probabilityDensity == null) {
			return "Null";
		}
		return Arrays.toString(probabilityDensity);
	}
	// ========================================================================
	// NO PLANET MULTIPLIER
	//
	/**
	 * Default value for NO_PLANET_MULTIPLIER
	 */
	public static final Float defaultNoPlanetMultiplier = 1.0f;
	private static final int  NONE_INDEX = PLANET_TYPES.indexOf(PlanetType.NONE);
	
	private static Float noPlanetMultiplier = defaultNoPlanetMultiplier;
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
	// ========================================================================
	// PROBABILITY MODIFIER PARAMETERS
	//
	/**
	 * STAR TYPE MODIFIER
	 */
	public static final ProbabilityModifier starTypeModifier =
			new ProbabilityModifier(MOO1GameOptions.starTypeColors);
	/**
	 * PLANET TYPE MODIFIER GLOBAL
	 */
	public static final ProbabilityModifier planetTypeModifierGlobal =
			new ProbabilityModifier(Arrays.asList(MOO1GameOptions.planetTypes()));
	
	// ==============================================================
	// NESTED CLASS PROBABILITY MODIFIER
	//
	/**
	 * Base class for star and planets probability modifier
	 */
	public static class ProbabilityModifier {
		/**
		 * Default Probability Density Modifier
		 */
		public static final Float DefaultProbabilityModifier = 1.0f;

		private List<String> optionList;
		private List<Float> defaultModifierList;
		private List<Float> selectedModifierList;
		
	    // ========== Constructors and initializer ==========
	    //
		/**
		 * @param options parameters description
		 */
		public ProbabilityModifier(List<String> options) {
			optionList = options;
			defaultModifierList = new ArrayList<Float>(Collections
					.nCopies(optionList.size(), DefaultProbabilityModifier));
			selectedModifierList = defaultModifierList;
		}
	    // ========== Public Getters ==========
	    //
		/**
		 * @return the optionList
		 */
		public List<String> optionList() {
			return optionList;
		}
		/**
		 * @return the default List of probability modifier
		 */
		public List<Float> defaultModifierList() {
			return defaultModifierList;
		}
		/**
		 * @return the selected probability modifier
		 */
		public List<Float> selectedModifierList() {
			if (selectedModifierList == null) {
				selectedModifierList = defaultModifierList;
			}
			return selectedModifierList;
		}
		/**
		 * @param list newValue for the selected probability modifier
		 */
		public void selectedModifierList(List<Float> list) {
			selectedModifierList = list;
		}
		/**
		 * adjust Star Probability to user taste
		 * @param cumulativeProbability the original cumulative distribution
		 * @return the modified cumulative distribution
		 */
		public float[] modifyProbability(float[] cumulativeProbability) {
			return processModification(cumulativeProbability, selectedModifierList());
		}
	    // ========== Private Methods ==========
	    //
		/**
		 * Convert cumulative probability to probability density
		 * @param cumulativeProbability the cumulative probability
		 * @return the probability density
		 */
		private static float[] cumulativeToDensity(float[] cumulativeProbability) {
			int length = cumulativeProbability.length;
			float[] probabilityDensity = new float[length];
		   	probabilityDensity[0] = cumulativeProbability[0];
		   	float total = cumulativeProbability[length-1];
		   	if (total == 0f) { // means nothing ... but no /0 !
		   		total = 1.0f;
		   	}
		   	// Convert and Normalize to 1.0
			for (int i = 1; i < length; i++) {
			   	probabilityDensity[i] = 
			   			(cumulativeProbability[i] - cumulativeProbability[i-1])
			   			/ total;
			 }		
			return probabilityDensity;
		}
		/**
		 * Convert probability density to cumulative probability
		 * @param probabilityDensity the probability density
		 * @return the cumulative probability
		 */
		private static float[] densityToCumulative(float[] probabilityDensity) {
			int length = probabilityDensity.length;
			float[] cumulativeProbability = new float[length];
			// Sum the density
			float total = 0;
			for (int i = 0; i < length; i++) {
				total += probabilityDensity[i];
				cumulativeProbability[i] = total;
			}
		   	if (total == 0f) { // means nothing ... but no /0 !
		   		float uniformValue = 1.0f / length;
				for (int i = 0; i < length; i++) {
					cumulativeProbability[i] = uniformValue;
				}
				return cumulativeProbability;
		   	}
			// Normalize to 1.0
			for (int i = 0; i < length; i++) {
				cumulativeProbability[i] /= total;
			}
			return cumulativeProbability;
		}
		/**
		 * adjust Star Probability to user taste
		 * @param cumulativeProbability the original cumulative distribution
		 * @param modifierList probability density modifier List
		 * @return the modified cumulative distribution
		 */
		public static float[] processModification(float[] cumulativeProbability
				, List<Float> modifierList) {
			if (modifierList != null
					&& !modifierList.isEmpty())
			{
				int length = Math.min(cumulativeProbability.length
									, modifierList.size());
				float[] probabilityDensity = cumulativeToDensity(cumulativeProbability);
				for (int i = 0; i < length; i++) {
					Float modifier = modifierList.get(i);
					if (modifier == null) { // should not happen! ... but!
						modifier = 1.0f;
					}
					if (modifier > 0f) {
						probabilityDensity[i] *= modifier;
					} else {
						probabilityDensity[i] = -modifier;
					}
				}
				return densityToCumulative(probabilityDensity);
			}
			return cumulativeProbability;
		}
	}
}