
/*
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

package br.config.old;

import java.util.concurrent.ThreadLocalRandom;

public class Cfg_EntryValidation{
//
//	protected static final String RANDOM_ID = "RANDOM";
//	protected static final String PARAMETERS_SEPARATOR  = ",";
//	
//	private Cfg_ValidationData validationData;
//	private Cfg_Entry cfgEntry;
//	private String   randomParameterString;
//	private String[] randomParameterList;
//	private Numeric[] numericList;
//	private Numeric min; // Entry Value
//	private Numeric max; // Entry Value
//	private Boolean isRandom;
//	private Boolean hasRandomParameters;
//	private Integer NbRandomParameters;
//	private Boolean isNumeric;
//	private Boolean isMinMax;
//	private Boolean hasRandomList;
//    // ==================================================
//    // Constructors
//    //
//	protected Cfg_EntryValidation(Cfg_ValidationData validationData) {
//		setValidationData(validationData);
//	}
//	// ==================================================
//    // protected Methods
//    //
//	/**
//	 * Test if a valid non blank value may be returned from user choice
//	 */
//	protected Boolean isValid() {
//		if (isBlank()) {
//			return false;
//		}
//		if (cfgEntry != null) {
//			String value = getStringOption();
//			if(!value.isBlank()) {
//				if(validationData.isBooleanDataType() && Cfg_Util.isBoolean(value)) {
//					return true;
//				}
//				if(validationData.isNumericDataType() && Numeric.isNumeric(value)) {
//					return true;
//				}				
//			}
//		}
//		return false;
//	}
//	/**
//	 * Test if user choice is blank
//	 */
//	protected Boolean isBlank() {
//		return cfgEntry == null || cfgEntry.isBlank();
//	}
//	protected void setEntry(Cfg_Entry entry) {
//		cfgEntry = entry;
//		resetParameters();
//		isRandom = testRandom();
//		if (isRandom) {
//			extractRandomParameters();
//			testParameters();
//		}
//	}
//
//	protected String getCategory() {
//		return validationData.getCategory(getRawEntry());
//	}
//	
//	protected String getStringOption() {
//		if (isRandom) {
//			if (validationData.hasList()) {
//				return validationData.getOption(getRandomString());
//			}
//			return getRandomNumeric().toLong().toString();
//		}
//		if (validationData.hasList()) {
//			return validationData.getOption(cfgEntry);
//		}
//		// whole by default
//		return getNumericOption().toLong().toString();
//		
//	};
//	protected Numeric getNumericOption() {
//		if (isRandom) {
//			return getRandomNumeric();
//		}
//		Numeric value = new Numeric(cfgEntry.get());
//		return Numeric.max(validationData.limitMin(),
//				           Numeric.min(validationData.limitMax(), value));
//	};
//	protected Cfg_Entry getRawEntry() {
//		return cfgEntry;
//	}
//	/**
//	 * Return a random Boolean
//	 */
//	public static Boolean getBooleanRandom() {
//		return ThreadLocalRandom.current().nextBoolean();
//	}	
//    // ==================================================
//    // Setters
//    //
//	private void setValidationData(Cfg_ValidationData options) {
//		validationData = options;
//	}
//    // ==================================================
//    //  Getters
//    //
//	private Numeric getIndexMin() {
//		// Try with entry parameters
//		if (min != null) {
//			return min;
//		}
//		// Try with default random
//		if (validationData.randomMin() != null) {
//			return validationData.randomMin();
//		}
//		// Then it's 0
//		return new Numeric(0);
//	}
//	private Numeric getRandomMin() {
//		// Try with entry parameters
//		Numeric result = min;
//		if (result == null) {
//		// Try with default random
//			result = validationData.randomMin();
//		}
//		if (result == null) {
//			result = validationData.limitMin();
//		}
//		// Check Validity
//		if (validationData.limitMin() != null) {
//			result.validateMin(validationData.limitMin());
//			return result;
//		}
//		// This should not happen... Let choose 0 as default Min
//		return new Numeric(0);
//	}
//	private Numeric getIndexMax() {
//		// Try with entry parameters
//		if (max != null) {
//			return max;
//		}
//		// Try with default random
//		if (validationData.randomMax() != null) {
//			return validationData.randomMax();
//		}
//		// Then it's Option list lastIndex
//		return new Numeric(validationData.lastIndex());
//	}
//	private Numeric getRandomMax() {
//		// Try with entry parameters
//		Numeric result = max;
//		if (result == null) {
//		// Try with default random
//			result = validationData.randomMax();
//		}
//		if (result == null) {
//			result = validationData.limitMax();
//		}
//		// Check Validity
//		if (validationData.limitMax() != null) {
//			result.validateMax(validationData.limitMax());
//			return result;
//		}		// This should not happen... Let choose 0 as default Max
//		return new Numeric(0);
//	}
//	private String getRandomStringFromParameterList() {
//		Numeric randomIndex = Numeric.nextRandomNumeric(
//										new Numeric(0),
//										new Numeric(randomParameterList.length-1));
//		return randomParameterList[randomIndex.toInteger()];
//	}
//	private Numeric getRandomNumericFromParameterList() {
//		Numeric raw = new Numeric(getRandomStringFromParameterList());
//		return raw.validateMinMax(getRandomMin(), getRandomMax());
//	}
//	private String getRandomStringFromValidationData() {
//		Numeric listMin = new Numeric(0);
//		Numeric listMax = new Numeric(validationData.lastIndex());
//		Numeric maxi = getIndexMax().validateMinMax(listMin, listMax);
//		Numeric mini = getIndexMin().validateMinMax(listMin, listMax);
//		Numeric randomIndex = Numeric.nextRandomNumeric(mini, maxi);
//		return validationData.getOption(randomIndex.toInteger());
//	}
//	private String getRandomString() {
//		if (hasRandomList) {
//			return getRandomStringFromParameterList();
//		}
//		if (validationData.hasList()) {
//			return getRandomStringFromValidationData();
//		}
//		// Whole Number by default should not use to string for Numeric!
//		return getRandomNumeric().toLong().toString();
//	}
//	private Numeric getRandomNumeric() {
//		if (hasRandomList) {
//			return getRandomNumericFromParameterList();
//		}
//		return Numeric.nextRandomNumeric(getRandomMin(), getRandomMax());
//	}
//	// ==================================================
//    // Parameters management Methods
//    //
//	private boolean testRandom() {
//		if (cfgEntry.toString().length() >= RANDOM_ID.length()) {
//			return cfgEntry.toTest().substring(0, RANDOM_ID.length()).equals(RANDOM_ID); 
//		}
//		return false;
//	}
//	private void testParameters() {
//		if (!hasRandomParameters) {
//			return;
//		}
//		// Test Min Max
//		isMinMax = isNumeric && NbRandomParameters <= 2;
//		if (isMinMax) {
//			min = numericList[0];
//			if (NbRandomParameters <= 1) {
//				max = numericList[1];
//			}
//			return;
//		}
//		hasRandomList = true;
//	}
//	private void resetParameters() {
//		min = null;
//		max = null;
//		randomParameterList   = null;
//		randomParameterString = null;
//		NbRandomParameters    = 0;
//		hasRandomParameters   = false;
//		numericList   = null;
//		hasRandomList = false;
//		isRandom  = false;
//		isNumeric = false;
//		isMinMax  = false;
//	}
//	private void extractRandomParameters() {
//		// Remove RANDOM_ID
//		randomParameterString = cfgEntry.toString().substring(RANDOM_ID.length()).strip();
//		if (!randomParameterString.isBlank()) {
//			// Check for misplaced PARAMETERS_SEPARATOR
//			if (randomParameterString.charAt(0) == PARAMETERS_SEPARATOR.charAt(0)) {
//				randomParameterString = randomParameterString.substring(1).strip();
//			}
//			if (!randomParameterString.isBlank()) {
//				randomParameterList = randomParameterString.split(PARAMETERS_SEPARATOR);
//				hasRandomParameters = true;
//				NbRandomParameters  = randomParameterList.length;
//				// Convert to Numeric
//				numericList = new Numeric[NbRandomParameters];
//				isNumeric = true; // all parameters must be numeric
//				for ( int i = 0; i < NbRandomParameters; i++) {
//					numericList[i] = new Numeric(randomParameterList[i]);
//					isNumeric &= numericList[i].isNumeric();
//				}
//			}
//		}
//	}
}
