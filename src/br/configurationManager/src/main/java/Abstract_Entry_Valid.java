
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

package br.configurationManager.src.main.java;

/**
 * The internal parameter will never be null
 * and will be stripped
 * @param <ValueClass> The class of Values
 */
abstract class Abstract_Entry_Valid <ValueClass> extends Abstract_Entry <ValueClass> {

	private static final String RANDOM_ID = "RANDOM";
	private static final String PARAMETERS_SEPARATOR  = ",";

	// From Abstract_Entry:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// extended with:
	private ValueClass value =  null;
	private Abstract_ValidData<ValueClass> validationData;
	
    // ==================================================
    // Constructors
    //
	/**
	 * No empty class creation allowed!
	 */
	@SuppressWarnings("unused")
	private  Abstract_Entry_Valid() {}

	/**
	 * create and initialize a new {@code Entry_Valid}
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 */
	Abstract_Entry_Valid(Abstract_ValidData<ValueClass> validationData) {
		setValidationData(validationData);
	}

	/**
	 * create and initialize a new {@code Entry_Valid}
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 * @param userEntry the {@code String} userEntry
	 */
	Abstract_Entry_Valid(
			Abstract_ValidData<ValueClass> validationData,
			String userEntry) {
		setValidationData(validationData);
		set(userEntry);
	}

	/**
	 * create and initialize a new {@code Entry_Valid}
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 * @param userEntry the {@code String} userEntry
	 * @param printFormat the default {@code PrintFormat}
	 */
	Abstract_Entry_Valid(
			Abstract_ValidData<ValueClass> validationData,
			String userEntry,
			PrintFormat printFormat) {
		setValidationData(validationData);
		setPrintFormat(printFormat);
		set(userEntry);
}
	// ==================================================
    // Abstract Methods Request
    //
	
	@Override public abstract String toString();

	// ==================================================
    // Abstract Methods Overriders
    //
	/**
	 * ask for value as {@code ValueClass}
	 * @return the value
	 */
	@Override ValueClass getValue() {
		return value;
	}

	// ==================================================
    // Other Overriders
    //

	/**
	 * Set a new {@code String} userEntry and analyze it
	 * @param newValue the new {@code String} userEntry
	 * @return this for chaining purpose 
	 */
	@Override Abstract_Entry_Valid<ValueClass> set(String newValue) {
		super.set(newValue);
		entryAnalysis();
		return this;
	}

	/**
	 * Clear the content of the element,
	 * But not the {@code PrintFormat} neither the {@code validationData}
	 * @return this for chaining purpose
	 */
	@Override Abstract_Entry_Valid<ValueClass> reset() {
		super.reset();
		return this;
	}

	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	@Override Abstract_Entry_Valid<ValueClass> copy() { 
		return this;
	}

    // ========== Overriders for chaining purpose ==========
	@Override Abstract_Entry<ValueClass> removeComment() {
		super.removeComment();
		return this;
	}

	// ==================================================
    // Setters simple
    //
	void setValidationData(Abstract_ValidData<ValueClass> newValidationData) {
		validationData = newValidationData;
	}

	void setValue(ValueClass newValue) {
		value = newValue;
		setOutputStr(value.toString());
	}

	// ==================================================
    // Getters simple
    //
	Abstract_ValidData<ValueClass> getValidationData() {
		return validationData;
	}

	// ==================================================
    // User Entry analysis Methods
    //
	/**
	 * Analyze user Entry content
	 */
	private void entryAnalysis() {
		if ( validationData.getValidationCriteria().isRandomAllowed
				&& isRandom(getUserEntry())) {
			if (hasExtraParameters(getUserEntry())) {
				setValue(validationData.randomWithParameters(
						splitParameters(removeRandomId(getUserEntry()))));
			}
			setValue(validationData.randomWithoutParameters());
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
		userEntry = CMutil.clean(userEntry).toUpperCase();
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
		userEntry = CMutil.clean(userEntry);
		userEntry = userEntry.substring(RANDOM_ID.length()).strip();
		// Check for misplaced PARAMETERS_SEPARATOR
		if (userEntry.charAt(0) == PARAMETERS_SEPARATOR.charAt(0)) {
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
}
