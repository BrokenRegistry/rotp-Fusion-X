
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

package br.config;

import java.util.concurrent.ThreadLocalRandom;

public class Cfg_ValueValidation extends Cfg_Value{

	protected static final String RANDOM_ID = "RANDOM";
	protected static final String PARAMETERS_SEPARATOR  = ",";
	
	private Cfg_ValidationData validationData;
	// Randomness related parameters
	private String   randomParameterString;
	private String[] randomParameterList;
	private Cfg_Value min; // Entry Value
	private Cfg_Value max; // Entry Value
	private Boolean isRandom;
	private Boolean hasRandomParameters;
	private Integer NbRandomParameters;
	private Boolean isNumeric;
	private Boolean isMinMax;
	private Boolean hasRandomList;
	private Cfg_Value[] numericList; // Entry Value
    // ==================================================
    // Constructors
    //
	/**
	 * Create new {@code CfgValueValidation} 
     * associated with specified {@code Cfg_ValidationData}
	 * @param  validationData  the related {@code Cfg_ValidationData}
	 */
	protected Cfg_ValueValidation(Cfg_ValidationData validationData) {
		setValidationData(validationData);
	}
	// ==================================================
    // protected Methods
    //
	/**
	 * Test if a valid non blank value may be returned from user choice
	 */
	protected Boolean isValid() {
		if (super.isBlank()) {
			return false;
		}
		String value = getStringOption();
		if(!value.isBlank()) {
			if(validationData.isBooleanDataType() 
					&& Cfg_Util.testForBoolean(value)) {
				return true;
			}
			if(validationData.isNumericDataType() 
					&& Cfg_Util.testForNumeric(value)) {
				return true;
			}				
		}
		return false;
	}
	/**
	 * Set new {@code Cfg_Entry} value
	 * @param  entry  the new {@code String} value
	 */	
	protected void setEntry(Cfg_Entry entry) {
		super.set(entry);
		resetParameters();
		isRandom = testRandom();
		if (isRandom) {
			extractRandomParameters();
			testParameters();
		}
	}
	/**
	 * Get ValidationData category according to the current entry
	 */	
	protected String getCategory() {
		return validationData.getCategory(getRawEntry());
	}
	/**
	 * Get Option according to the current entry
	 */	
	protected String getStringOption() {
		if (isRandom) {
			if (validationData.hasList()) {
				return validationData.getOption(getRandomString());
			}
			return getRandomNumeric().toLong().toString();
		}
		if (validationData.hasList()) {
			return validationData.getOption(super.get());
		}
		// whole by default
		return getNumericOption().toLong().toString();
	};
	/**
	 * Get Cfg_Value Option according to the current entry
	 */	
	protected Cfg_Value getNumericOption() {
		if (isRandom) {
			return getRandomNumeric();
		}
		Cfg_Value value = new Cfg_Value(super.get());
		return Cfg_Value.max(validationData.limitMin(),
				Cfg_Value.min(validationData.limitMax(), value));
	};
	protected Cfg_Entry getRawEntry() {
		return super.getCfg_Entry();
	}
	/**
	 * Return a random Boolean
	 */
	public static Boolean getBooleanRandom() {
		return ThreadLocalRandom.current().nextBoolean();
	}	
    // ==================================================
    // Setters
    //
	private void setValidationData(Cfg_ValidationData options) {
		validationData = options;
		setDataType(options.getDataType());
	}
    // ==================================================
    //  Getters
    //
	private Cfg_Value getIndexMin() {
		// Try with entry parameters
		if (min != null) {
			return min;
		}
		// Try with default random
		if (validationData.randomMin() != null) {
			return validationData.randomMin();
		}
		// Then it's 0
		return new Cfg_Value(0);
	}
	private Cfg_Value getRandomMin() {
		// Try with entry parameters
		Cfg_Value result = min;
		if (result == null) {
		// Try with default random
			result = validationData.randomMin();
		}
		if (result == null) {
			result = validationData.limitMin();
		}
		// Check Validity
		if (validationData.limitMin() != null) {
			result.validateMin(validationData.limitMin());
			return result;
		}
		// This should not happen... Let choose 0 as default Min
		return new Cfg_Value(0);
	}
	private Cfg_Value getIndexMax() {
		// Try with entry parameters
		if (max != null) {
			return max;
		}
		// Try with default random
		if (validationData.randomMax() != null) {
			return validationData.randomMax();
		}
		// Then it's Option list lastIndex
		return new Cfg_Value(validationData.lastIndex());
	}
	private Cfg_Value getRandomMax() {
		// Try with entry parameters
		Cfg_Value result = max;
		if (result == null) {
		// Try with default random
			result = validationData.randomMax();
		}
		if (result == null) {
			result = validationData.limitMax();
		}
		// Check Validity
		if (validationData.limitMax() != null) {
			result.validateMax(validationData.limitMax());
			return result;
		}		// This should not happen... Let choose 0 as default Max
		return new Cfg_Value(0);
	}
	private String getRandomStringFromParameterList() {
		Cfg_Value randomIndex = Cfg_Value.nextRandom(
										new Cfg_Value(0),
										new Cfg_Value(randomParameterList.length-1));
		return randomParameterList[randomIndex.toInteger()];
	}
	private Cfg_Value getRandomNumericFromParameterList() {
		Cfg_Value raw = new Cfg_Value(getRandomStringFromParameterList());
		return raw.validateMinMax(getRandomMin(), getRandomMax());
	}
	private String getRandomStringFromValidationData() {
		Cfg_Value listMin = new Cfg_Value(0);
		Cfg_Value listMax = new Cfg_Value(validationData.lastIndex());
		Cfg_Value maxi = getIndexMax().validateMinMax(listMin, listMax);
		Cfg_Value mini = getIndexMin().validateMinMax(listMin, listMax);
		Cfg_Value randomIndex = Cfg_Value.nextRandom(mini, maxi);
		return validationData.getOption(randomIndex.toInteger());
	}
	private String getRandomString() {
		if (hasRandomList) {
			return getRandomStringFromParameterList();
		}
		if (validationData.hasList()) {
			return getRandomStringFromValidationData();
		}
		// Whole Number by default should not use to string for Cfg_Value!
		return getRandomNumeric().toLong().toString();
	}
	private Cfg_Value getRandomNumeric() {
		if (hasRandomList) {
			return getRandomNumericFromParameterList();
		}
		return Cfg_Value.nextRandom(getRandomMin(), getRandomMax());
	}
	// ==================================================
    // Parameters management Methods
    //
	private boolean testRandom() {
		String entry = super.get();
		if (entry.length() >= RANDOM_ID.length()) {
			return entry.substring(0, RANDOM_ID.length()).equalsIgnoreCase(RANDOM_ID); 
		}
		return false;
	}
	private void testParameters() {
		if (!hasRandomParameters) {
			return;
		}
		// Test Min Max
		isMinMax = isNumeric && NbRandomParameters <= 2;
		if (isMinMax) {
			min = numericList[0];
			if (NbRandomParameters <= 1) {
				max = numericList[1];
			}
			return;
		}
		hasRandomList = true;
	}
	private void resetParameters() {
		min = null;
		max = null;
		randomParameterList   = null;
		randomParameterString = null;
		NbRandomParameters    = 0;
		hasRandomParameters   = false;
		numericList   = null;
		hasRandomList = false;
		isRandom  = false;
		isNumeric = false;
		isMinMax  = false;
	}
	private void extractRandomParameters() {
		// Remove RANDOM_ID
		randomParameterString = super.get().substring(RANDOM_ID.length()).strip();
		if (!randomParameterString.isBlank()) {
			// Check for misplaced PARAMETERS_SEPARATOR
			if (randomParameterString.charAt(0) == PARAMETERS_SEPARATOR.charAt(0)) {
				randomParameterString = randomParameterString.substring(1).strip();
			}
			if (!randomParameterString.isBlank()) {
				randomParameterList = randomParameterString.split(PARAMETERS_SEPARATOR);
				hasRandomParameters = true;
				NbRandomParameters  = randomParameterList.length;
				// Convert to Cfg_Value
				numericList = new Cfg_Value[NbRandomParameters];
				isNumeric = true; // all parameters must be numeric
				for ( int i = 0; i < NbRandomParameters; i++) {
					numericList[i] = new Cfg_Value(randomParameterList[i]);
					isNumeric &= numericList[i].testForNumeric();
				}
			}
		}
	}
}
