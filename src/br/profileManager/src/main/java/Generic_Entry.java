
/**
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

package br.profileManager.src.main.java;

import static br.profileManager.src.main.java.PMutil.capitalize;
import static br.profileManager.src.main.java.PMutil.clean;
import static br.profileManager.src.main.java.WriteUtil.History.Default;

/**
 * @param <ValueClass> The class of Values
 */
class Generic_Entry<
		ValueClass,
		ValidClass extends Abstract_ValidData<ValueClass>> extends WriteUtil {

	private static final String RANDOM_ID = "RANDOM";
	private static final String PARAMETERS_SEPARATOR  = ",";

	private String userEntry = "";  // what we get from the file
	private String outputStr = "";  // what will be the outputStr
	                                // To make the common code easier to manage

//	private PrintFormat printFormat = PrintFormat.HOLD; // The default format for outputStr
	private ValueClass value = null;
	private Abstract_ValidData<ValueClass> validationData;
	
    // ==================================================
    // Constructors
    //
	/**
	 * No empty class creation allowed!
	 */
	@SuppressWarnings("unused")
	private  Generic_Entry() {}

	/**
	 * create and initialize a new {@code EntryValid} with default value
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 */
	Generic_Entry(Abstract_ValidData<ValueClass> validationData) {
		setValidationData(validationData);
		setValue(validationData.getHistoryCodeView(Default));
	}

	/**
	 * create and initialize a new {@code EntryValid}
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 * @param userEntry the {@code String} userEntry
	 */
	Generic_Entry(Abstract_ValidData<ValueClass> validationData,
			  String userEntry) {
		setValidationData(validationData);
		set(userEntry);
	}

	/**
	 * create and initialize a new {@code EntryValid}
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 * @param value the {@code ValueClass} userEntry
	 */
	Generic_Entry(Abstract_ValidData<ValueClass> validationData,
			ValueClass value) {
		setValidationData(validationData);
		setValue(value);
	}

	// ==================================================
    // Overriders
    //
	@Override public String toString() { 
		return getOutputStr();
	}

	// ==================================================
    // Methods to be Overridden
    //
	/**
	 * Set a new {@code String} userEntry and analyze it
	 * @param newValue the new {@code String} userEntry
	 * @return this for chaining purpose 
	 */
	Generic_Entry<ValueClass, ValidClass> set(String newValue) {
		userEntry = clean(newValue);
		outputStr = userEntry;
		entryAnalysis();
		return this;
	}

	/**
	 * Clear the content of the element,
	 * But not the {@code PrintFormat} neither the {@code validationData}
	 * @return this for chaining purpose
	 */
	Generic_Entry<ValueClass, ValidClass> reset() {
		userEntry = "";
		outputStr = "";
		value = null;
		return this;
	}

	/**
	 * Ask if something has been set by user entry
	 * @return {@code boolean}
	 */
	boolean isBlankUserEntry() { 
		return userEntry == null || userEntry.isBlank();
	}

	/**
	 * Ask if about the value state (code view)
	 * @return {@code boolean}
	 */
	boolean isBlankValue() { 
		return value == null || value.toString().isBlank();
	}

	/**
	 * Ask if about the value state (code view)
	 * @return {@code boolean}
	 */
	boolean isValidValue() {
		return validationData.isValidCodeView(value);
	}

	// ==================================================
    // Setters simple
    //
	void setValidationData(Abstract_ValidData<ValueClass> newValidationData) {
		validationData = newValidationData;
	}

	void setValue(ValueClass newValue) {
		value = newValue;
		if (value != null) {
			setOutputStr(validationData.getUserView(newValue));
		} else {
			setOutputStr(getUserEntry());
		}
	}

	/**
	 * Set the new preformatted output {@code String}
	 * @param newOutputStr the new value
	 */
	void setOutputStr(String newOutputStr) {
		outputStr = PMutil.neverNull(newOutputStr);
	}

	// ==================================================
    // Getters simple
    //
	/**
	 * ask for value as {@code ValueClass}
	 * @return the value
	 */
	ValueClass getValue() {
		return value;
	}

	Abstract_ValidData<ValueClass> getValidationData() {
		return validationData;
	}

	/**
	 * Ask for userEntry as {@code String}
	 * @return the {@code String}
	 */
	String getUserEntry() { 
		return userEntry;
	}	

	/**
	 * Ask for preformatted outputStr as {@code String}
	 * @return the {@code String}
	 */
	String getOutputStr() { 
		return outputStr;
	}	

	/**
	 * ask for value in lower case, with first char to upper case,
	 * with every word capitalized if eachWord is true
	 * @param onlyFirstWord if true only the first word is capitalized
	 * @return a {@code String} as requested
	 */
	String toCapitalized(Boolean onlyFirstWord) { 
		return capitalize(getOutputStr(), onlyFirstWord);
	}

	/**
	 * ask for value in lower case, with first char to upper case,
	 * with every word capitalized
	 * @return a {@code String} as requested
	 */
	String toCapitalized() { 
		return capitalize(getOutputStr());
	}

	/**
	 * ask for a stripped in lower case with first char to upper case, never null
	 * @return a {@code String} as requested
	 */
	String toSentence() {
		return PMutil.toSentence(getOutputStr());
	}

	// ==================================================
    // User Entry analysis Methods
    //
	/**
	 * Analyze user Entry content
	 */
	private void entryAnalysis() {
		if ( validationData.getValidationCriteria().isRandomAllowed()
				&& isRandom(getUserEntry())) {
			if (hasExtraParameters(getUserEntry())) {
				setValue(validationData.randomWithParameters(
						splitParameters(removeRandomId(getUserEntry()))));
				setOutputStr(getUserEntry());
				return;
			}
			setValue(validationData.randomWithoutParameters());
			setOutputStr(getUserEntry());
			return;
		}
		setValue(validationData.entryValidation(getUserEntry()));
	}
	
	// ==================================================
    // Random Management Methods
    //
	/**
	 * Test if user Entry is asking for a random parameter
	 * @param userEntry the {@code String} to analyze
	 * @return <b>true</b> if is random
	 */
	private static boolean isRandom(String userEntry) {
		userEntry = PMutil.clean(userEntry).toUpperCase();
		if (userEntry.length() >= RANDOM_ID.length()) {
			return userEntry.substring(0, RANDOM_ID.length()).equals(RANDOM_ID); 
		}
		return false;
	}
	
	/**
	 * Check for extra parameter in Random request
	 * @param userEntry the {@code String} to analyze
	 * @return <b>true</b> if has extra parameters
	 */
	private static boolean hasExtraParameters(String userEntry) {
		return !removeRandomId(userEntry).isBlank();
	}

	/**
	 * Remove the Random word and return the extra parameters
	 * @param userEntry the {@code String} to analyze
	 * @return the extra parameters
	 */
	private static String removeRandomId(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		userEntry = userEntry.substring(RANDOM_ID.length()).strip();
		// Check for misplaced PARAMETERS_SEPARATOR
		if (!userEntry.isEmpty() &&
				userEntry.charAt(0) == PARAMETERS_SEPARATOR.charAt(0)) {
			userEntry = userEntry.substring(1).strip();
		}
		return userEntry;
	}

	/**
	 * Remove the Random word and return the extra parameters
	 * @param userEntry the {@code String} to analyze
	 * @return the extra parameters
	 */
	private static String[] splitParameters(String parameters) {
		// parameters should already be tested
		return parameters.split(PARAMETERS_SEPARATOR);
	}

	// ==================================================
    // Tests Methods
    //
	/**
	 * check if a valid numeric value may be extracted
	 * @return true if is Numeric
	 */
	Boolean testForNumeric() {
		return PMutil.testForNumeric(getUserEntry());
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Remove the comments and clean
	 * @return this for chaining purpose
	 */
	Generic_Entry<ValueClass, ValidClass> removeComment() {
		set(WriteUtil.removeComment(getUserEntry()));
		return this;
	}
	
	// ==================================================
	// Methods using the Abstract methods
	// Just copy and paste in child classes
	// They should work even if not overridden
    //
	/**
	 * Ask for a non <b>null</b> nor <i>empty</i> nor <i>blank</i> value
	 * @param defaultValue value to <b>return</b if <b>null</b>, <i>empty</i> or <i>blank</i> 
	 * @return the value, following the conditions
	 */
	ValueClass getValue(ValueClass defaultValue) {
		if (isBlankValue()) {
			return defaultValue;
		}
		return getValue();
	}

	// Other name for compatibility
	/**
	 * Ask for a non <b>null</b> nor <i>empty</i> nor <i>blank</i> value
	 * @param defaultValue value to <b>return</b if <b>null</b>, <i>empty</i> or <i>blank</i> 
	 * @return the value, following the conditions
	 */
	ValueClass getOrDefault(ValueClass defaultValue) {
			return getValue(defaultValue);
	}

	/**
	 * Ask for a non <b>null</b> nor <i>empty</i> nor <i>blank</i> value
	 * @param defaultValue class object to <b>return</b if <b>null</b>, <i>empty</i> or <i>blank</i> 
	 * @return the class object, following the conditions
	 */
	ValueClass getOrDefault(Generic_Entry<ValueClass, ValidClass> defaultValue) {
		return getValue(defaultValue.getValue());
	}
}
