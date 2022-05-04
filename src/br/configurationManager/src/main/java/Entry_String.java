
/**
 * Licensed under the GNU General License, Version 3 (the "License");
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
 */
class Entry_String extends Abstract_Entry<String> {

	// From Abstract_Entry:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// extended with:
	private Boolean caseSensitive = null;
		// false = tested as UpperCase
		// null = default Value
	
    // ==================================================
    // Constructors
    //
	/**
	 * create a new empty {@code Entry_String}
	 */
	Entry_String() {}

	/**
	 * create and initialize a new empty {@code Entry_String}
	 * @param value the {@code Entry_String} value and case sensitivity
	 */
	Entry_String(Entry_String value) {
		super(value.getUserEntry());
		this.setCaseSensitive(value.getCaseSensitive());
	}

	/**
	 * create and initialize a new {@code Entry_String}
	 * @param value the {@code String} value
	 */
	Entry_String(String value) {
		super(value);
	}

	/**
	 * create and initialize a new {@code Entry_String}
	 * @param value the {@code Entry_String} value
	 * @param caseSensitive the {@code Boolean} case sensitivity, this value
	 *        override the one in {@code Entry_String} value
	 */
	Entry_String(Entry_String value, Boolean caseSensitive) {
		super(value.getUserEntry());
		this.setCaseSensitive(caseSensitive);
	}

	/**
	 * create and initialize a new {@code Entry_String}
	 * @param value the {@code String} value
	 * @param caseSensitive the {@code Boolean} case sensitivity
	 */
	Entry_String(String value, Boolean caseSensitive) {
		super(value);
		this.setCaseSensitive(caseSensitive);
	}

	// ==================================================
    // Abstract Methods overriders + extended
    //
	/**
	 * return value as {@code String}
	 * @return the original value
	 */
	@Override
	String getValue() { 
		return getUserEntry();
	}

	@Override protected Entry_String reset() {
		super.reset();
		caseSensitive = null;
		return this;
	}
	
	/**
	 * Set a new {@code String} value
	 * @param newValue the new {@code String} value
	 * @return this for chaining purpose 
	 */
	@Override Entry_String set(String newValue) {
		super.set(newValue);
		return this;
	}
	
	/**
	 * Ask for a clone of {@code Entry_String}
	 * @return the cloned {@code Entry_String}
	 */
	@Override protected Entry_String clone() { 
		return new Entry_String(getUserEntry(), caseSensitive);
	}
	
	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	@Override Entry_String copy() { 
		return this;
	}
	
	// ========== overriders for chaining purpose ==========
	@Override Entry_String removeComment() {
		super.removeComment();
		return this;
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set a new {@code String} value and new case Sensitivity
	 * @param newValue  the new {@code String} value
	 * @param caseSensitive  the new {@code Boolean} sensitivity
	 * @return this for chaining purpose 
	 */
	Entry_String set(String newValue, Boolean caseSensitive) {
		super.set(newValue);
		setCaseSensitive(caseSensitive);
		return this;
	}

	/**
	 * Set new value {@code Entry_String} (clone)
	 * @param newValue the {@code Entry_String}
	 * @return this for chaining purpose 
	 */
	Entry_String set(Entry_String newValue) {
		set(newValue.getUserEntry(), newValue.getCaseSensitive());
		return this;
	}

	/**
	 * Set new {@code Entry_String} value and new {@code Boolean} case sensitivity
	 * @param newValue the new {@code Entry_String} value
	 * @param caseSensitive the new {@code Boolean} sensitivity this value
	 *        override the one in {@code Entry_String} newValue
	 * @return this for chaining purpose 
	 */
	Entry_String set(Entry_String newValue, Boolean caseSensitive) {
		set(newValue.getUserEntry(), caseSensitive);
		return this;
	}

	/**
	 * Set new case sensitivity
	 * @param newValue the new {@code Boolean} sensitivity
	 * @return this for chaining purpose 
	 */
	Entry_String setCaseSensitive(Boolean newValue) {
		caseSensitive = newValue;
		return this;
	}

	// ==================================================
    // Getters simple
    //
	/**
	 * {@code Boolean} getter for case sensitivity
	 * @return {@code Boolean} the sensitivity
	 */
	Boolean getCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * {@code boolean} getter for case sensitivity
	 * null value is replaced by Cfg_Util.defaultCaseSensitivity
	 * @return {@code boolean} the sensitivity
	 */
	boolean isCaseSensitive() {
		if (caseSensitive == null) {
			return getDefaultCaseSensitivity();
		}
		return caseSensitive;
	}

	/**
	 * Ask for value ready to be tested depending
	 * on case sensitivity (upper case if not case sensitive)
	 * @return toUpperCase if not case sensitive
	 */
	String toTest() {
		if (isCaseSensitive()) {
			return toString();
		}
		return toString().toUpperCase();
	}
   
	// ==================================================
    // Tests Methods
    //
	/**
	 * check if value == string taking account of case sensitivity
	 * @param string the {@code String} to be tested
	 * @return  the {@code Boolean} test result
	 */
	Boolean equals(String string) {
		if (string == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return toString().equals(string);
		}
		return toString().equalsIgnoreCase(string);
	}

	/**
	 * check if value == entryString taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * @param entryString the {@code Entry_String} to be tested
	 * @return the {@code Boolean} test result
	 */
	Boolean equals(Entry_String entryString) {
		if (entryString == null) {
			return false;
		}
		if (isCaseSensitive() && entryString.isCaseSensitive()) {
			return toString().equals(entryString.toString());
		}
		return toString().equalsIgnoreCase(entryString.toString());
	}

	/**
	 * check if value contains the {@code String} subString 
	 * taking account of case sensitivity
	 * <br> if subString is null, its contained
	 * @param subString the contained {@code String}
	 * @return the {@code Boolean} test result
	 */
	Boolean contains(String subString) {
		if (subString == null) {
			return true;
		}
		if (isCaseSensitive()) {
			return toString().contains(subString);
		}
		return toString().toUpperCase().contains(subString.toUpperCase());
	}

	/**
	 * check if value contains the {@code Entry_String} subString 
	 * taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * <br> if subString is null, its contained
	 * @param subString the contained {@code Entry_String}
	 * @return the {@code Boolean} test result
	 */
	Boolean contains(Entry_String subString) {
		if (subString == null) {
			return true;
		}
		if (isCaseSensitive() && subString.isCaseSensitive()) {
			return toString().contains(subString.toString());
		}
		// at least one is not case sensitive
		return toString().toUpperCase().contains(subString.toString().toUpperCase());
	}

	/**
	 * Check if {@code String} container contains this.value 
	 * taking account of case sensitivity
	 * @param container the containing {@code String}
	 * @return the {@code Boolean} test result
	 */
	Boolean isContained(String container) {
		if (container == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return container.contains(toString());
		}
		return container.toUpperCase().contains(toString().toUpperCase());
	}

	/**
	 * Check if {@code Entry_String} container contains this.value 
	 * taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * @param container the containing {@code Entry_String}
	 * @return the {@code Boolean} test result
	 */
	Boolean isContained(Entry_String container) {
		if (container == null) {
			return false;
		}
		if (isCaseSensitive() && container.isCaseSensitive()) {
			return container.toString().contains(toString());
		}
		// at least one is not case sensitive
		return container.toString().toUpperCase().contains(toString().toUpperCase());
	}
}
