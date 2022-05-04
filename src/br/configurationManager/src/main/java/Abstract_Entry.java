
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

import static br.configurationManager.src.main.java.CMutil.*;

/**
 * The internal parameter will never be null
 * and will be stripped
 * @param <ValueClass> The class of Values
 */
abstract class Abstract_Entry <ValueClass> extends Abstract_ToPrint {

	private String userEntry = ""; // what we get from the file
	private String outputStr = ""; // what will be the outputStr
                                   // To make the common code easier to manage
	private PrintFormat printFormat = PrintFormat.HOLD; // The default format for outputStr
    // To make the common code easier to manage
	
    // ==================================================
    // Constructors
    //
	/**
	 * create a new empty {@code Entry_String}
	 */
	Abstract_Entry() {}

	/**
	 * create and initialize a new {@code Entry_String}
	 * @param userEntry the {@code String} userEntry
	 */
	Abstract_Entry(String userEntry) {
		set(userEntry);
	}

	/**
	 * create and initialize a new {@code Entry_String}
	 * @param userEntry the {@code String} userEntry
	 * @param printFormat the default {@code PrintFormat}
	 */
	Abstract_Entry(String userEntry, PrintFormat printFormat) {
		set(userEntry);
		setPrintFormat(printFormat);
	}

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * ask for value as {@code ValueClass}
	 * @return the value
	 */
	abstract ValueClass getValue();

	// ==================================================
    // Overriders
    //
	/**
	 * Ask for value as {@code String}
	 * @return the {@code String}
	 */
	@Override
	public String toString() { 
		return getOutputStr();
	}

	/**
	 * Ask if there is something usable in the element
	 * @return {@code boolean}
	 */
	boolean isBlank() { 
		return userEntry == null || userEntry.isBlank();
	}

	/**
	 * Set a new {@code String} userEntry
	 * @param newValue the new {@code String} userEntry
	 * @return this for chaining purpose 
	 */
	Abstract_Entry<ValueClass> set(String newValue) {
		userEntry = clean(newValue);
		outputStr = clean(newValue);
		return this;
	}

	/**
	 * Clear the content of the element,
	 * But not the {@code PrintFormat}
	 * @return this for chaining purpose
	 */
	Abstract_Entry<ValueClass> reset() { 
		userEntry = "";
		outputStr = "";
		return this;
	}

	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	Abstract_Entry<ValueClass> copy() { 
		return this;
	}

	// ==================================================
    // Setters simple
    //
	/**
	 * Set the new preformatted output {@code String}
	 * @param newOutputStr the new value
	 */
	void setOutputStr(String newOutputStr) {
		outputStr = newOutputStr;
	}

	/**
	 * Set the new preformatted output {@code String}
	 * @param newFormat the new {@code PrintFormat}
	 */
	void setPrintFormat(PrintFormat newFormat) {
		printFormat = newFormat;
	}

	// ==================================================
    // Getters simple
    //
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
	 * Ask for Printing Format as {@code PrintFormat}
	 * @return the  {@code PrintFormat}
	 */
	PrintFormat getPrintFormat() { 
		return printFormat;
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
		return CMutil.toSentence(getOutputStr());
	}

	// ==================================================
    // Tests Methods
    //
	/**
	 * check if a valid numeric value may be extracted
	 * @return true if is Numeric
	 */
	Boolean testForNumeric() {
		return CMutil.testForNumeric(getUserEntry());
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Remove the comments and clean
	 * @return this for chaining purpose
	 */
	Abstract_Entry<ValueClass> removeComment() {
		set(Abstract_ToComment.removeComment(getUserEntry()));
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
		if (isBlank()) {
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
	ValueClass getOrDefault(Abstract_Entry <ValueClass> defaultValue) {
		return getValue(defaultValue.getValue());
	}
}
