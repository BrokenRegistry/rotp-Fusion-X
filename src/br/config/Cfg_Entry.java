
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

package br.config;

import static br.config.Cfg_Util.*;

/**
 * The internal parameter will never be null
 * and will be stripped
 */
public class Cfg_Entry implements I_Prt, I_Comment{

	
	private String entry = "";
	private Boolean caseSensitive = null;
		// false = tested as UpperCase
		// null = default Value
	
    // ==================================================
    // Constructors
    //
	/**
	 * create a new empty {@code CfgEntry}
	 */
	public Cfg_Entry() {}
	/**
	 * create and initialize a new empty {@code CfgEntry}
	 * @param entry the {@code CfgEntry} value and case sensitivity
	 */
	public Cfg_Entry(Cfg_Entry entry) {
		set(entry);
	}
	/**
	 * create and initialize a new empty {@code CfgEntry}
	 * @param entry the {@code String} value
	 */
	public Cfg_Entry(String entry) {
		set(entry);
	}
	/**
	 * create and initialize a new empty {@code CfgEntry}
	 * @param entry the {@code CfgEntry} value
	 * @param caseSensitive the {@code Boolean} case sensitivity, this value
	 *        override the one in {@code CfgEntry} value
	 */
	public Cfg_Entry(Cfg_Entry entry, Boolean caseSensitive) {
		set(entry, caseSensitive);
	}
	/**
	 * @param entry the {@code String} value
	 * @param caseSensitive the {@code Boolean} case sensitivity
	 */
	public Cfg_Entry(String entry, Boolean caseSensitive) {
		set(entry, caseSensitive);
	}
    // ==================================================
    // Setters
    //
	/**
	 * Set a new {@code String} entry
	 * @param newValue the new {@code String} entry
	 * @return this for chaining purpose 
	 */
	public Cfg_Entry set(String newValue) {
		entry = clean(newValue);
		return this;
	}
	/**
	 * Set a new {@code String} entry and new case Sensitivity
	 * @param newValue  the new {@code String} entry
	 * @param caseSensitive  the new {@code Boolean} sensitivity
	 * @return this for chaining purpose 
	 */
	public Cfg_Entry set(String newValue, Boolean caseSensitive) {
		entry = clean(newValue);
		setCaseSensitive(caseSensitive);
		return this;
	}
	/**
	 * Set new value {@code CfgEntry} (clone)
	 * @param newValue the {@code CfgEntry}
	 * @return this for chaining purpose 
	 */
	public Cfg_Entry set(Cfg_Entry newValue) {
		set(newValue.get(), newValue.getCaseSensitive());
		return this;
	}
	/**
	 * Set new {@code CfgEntry} value and new {@code Boolean} case sensitivity
	 * @param newValue the new {@code CfgEntry} value
	 * @param caseSensitive the new {@code Boolean} sensitivity this value
	 *        override the one in {@code CfgEntry} newValue
	 * @return this for chaining purpose 
	 */
	public Cfg_Entry set(Cfg_Entry newValue, Boolean caseSensitive) {
		set(newValue.get(), caseSensitive);
		return this;
	}
	/**
	 * Set new case sensitivity
	 * @param newValue the new {@code Boolean} sensitivity
	 * @return this for chaining purpose 
	 */
	public Cfg_Entry setCaseSensitive(Boolean newValue) {
		caseSensitive = newValue;
		return this;
	}
    // ==================================================
    // Getters simple
    //
	/**
	 * Ask for a clone of {@code CfgEntry}
	 * @return the cloned {@code CfgEntry}
	 */
	@Override
	public Cfg_Entry clone() { 
		return new Cfg_Entry(entry, caseSensitive);
	}
	/**
	 * {@code Boolean} getter for case sensitivity
	 * @return {@code Boolean} the sensitivity
	 */
	public Boolean getCaseSensitive() {
		return caseSensitive;
	}
	/**
	 * {@code boolean} getter for case sensitivity
	 * null value is replaced by Cfg_Util.defaultCaseSensitivity
	 * @return {@code boolean} the sensitivity
	 */
	public boolean isCaseSensitive() {
		if (caseSensitive == null) {
			return getDefaultCaseSensitivity();
		}
		return caseSensitive;
	}
	/**
	 * return entry as {@code String}
	 * @return the original value
	 */
	public String get() { 
		return entry;
	}
	/**
	 * Ask for this as {@code CfgEntry} (used by sub classes)
	 * @return this
	 */
	protected Cfg_Entry getCfg_Entry() { 
		return this;
	}
	/**
	 * Ask for entry as {@code Comment String}
	 * @return the {@code String}
	 */
	@Override
	public String toComment() { 
		return I_Comment.toComment(this);
	}
	/**
	 * Ask for entry as {@code String}
	 * @return the {@code String}
	 */
	@Override
	public String toString() { 
		return entry;
	}
	/**
	 * Ask for String, ready to be printed
	 * @return entry as {@code String}, ready to be printed
	 */
	@Override
	public String toPrint() { 
		return entry;
	}
	/**
	 * Ask for value ready to be tested depending
	 * on case sensitivity (upper case if not case sensitive)
	 * @return toUpperCase if not case sensitive
	 */
	public String toTest() {
		if (isCaseSensitive()) {
			return entry;
		}
		return toKey();
	}
	/**
	 * Ask for value ready to be tested as not case sensitive
	 * @return value toUpperCase
	 */
	public String toKey() {
		return entry.toUpperCase();
	}
	/**
	 * ask for entry in lower case, with first char to upper case,
	 * with every word capitalized if eachWord is true
	 * @param onlyFirstWord if true only the first word is capitalized
	 * @return a {@code String} as requested
	 */
	public String toCapitalized(Boolean onlyFirstWord) { 
		return capitalize(entry, onlyFirstWord);
	}
	/**
	 * ask for entry in lower case, with first char to upper case,
	 * with every word capitalized
	 * @return a {@code String} as requested
	 */
	public String toCapitalized() { 
		return capitalize(entry);
	}
	/**
	 * ask for a stripped in lower case with first char to upper case, never null
	 * @return a {@code String} as requested
	 */
	public String toSentence() {
		return Cfg_Util.toSentence(entry);
	}
    // ==================================================
    // Getters with default values
    //
	/**
	 * Ask for a non blank value
	 * @param defaultValue Value if Blank
	 * @return the entry, defaultValue if blank
	 */
	public String getOrDefault(String defaultValue) {
	   	if (entry.isBlank()) {
    		return defaultValue;
    	}
        return entry;
	}
    // ==================================================
    // Tests Methods
    //
	/**
	 * Test if Empty or null
	 * @return true if Empty or null
	 */
	public boolean isEmpty() {
	    return (entry.isEmpty());
    }
	/**
	 * Test if Blank, Empty or null
	 * @return true if Blank, Empty or null
	 */
	public Boolean isBlank() {
	    return (entry.isBlank());
    }
	/**
	 * check if entry == value taking account of case sensitivity
	 * @param value the {@code String} to be tested
	 * @return  the {@code Boolean} test result
	 */
	public Boolean equals(String value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return entry.equals(value);
		}
		return entry.equalsIgnoreCase(value);
	}
	/**
	 * check if entry == value taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * @param value the {@code CfgEntry} to be tested
	 * @return the {@code Boolean} test result
	 */
	public Boolean equals(Cfg_Entry value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive() && value.isCaseSensitive()) {
			return entry.equals(value.toString());
		}
		return entry.equalsIgnoreCase(value.toString());
	}
	/**
	 * check if entry contains the {@code String} subString 
	 * taking account of case sensitivity
	 * <br> if subString is null, its contained
	 * @param subString the contained {@code String}
	 * @return the {@code Boolean} test result
	 */
	public Boolean contains(String subString) {
		if (subString == null) {
			return true;
		}
		if (isCaseSensitive()) {
			return entry.contains(subString);
		}
		return entry.toUpperCase().contains(subString.toUpperCase());
	}
	/**
	 * check if entry contains the {@code CfgEntry} subString 
	 * taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * <br> if subString is null, its contained
	 * @param subString the contained {@code CfgEntry}
	 * @return the {@code Boolean} test result
	 */
	public Boolean contains(Cfg_Entry subString) {
		if (subString == null) {
			return true;
		}
		if (isCaseSensitive() && subString.isCaseSensitive()) {
			return entry.contains(subString.toString());
		}
		// at least one is not case sensitive
		return entry.toUpperCase().contains(subString.toString().toUpperCase());
	}
	/**
	 * Check if {@code String} container contains this.entry 
	 * taking account of case sensitivity
	 * @param container the containing {@code String}
	 * @return the {@code Boolean} test result
	 */
	public Boolean isContained(String container) {
		if (container == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return container.contains(entry);
		}
		return container.toUpperCase().contains(entry.toUpperCase());
	}
	/**
	 * Check if {@code CfgEntry} container contains this.entry 
	 * taking account of case sensitivity.
	 * Both case sensitivity should be true for the test 
	 * to be case sensitive
	 * @param container the containing {@code CfgEntry}
	 * @return the {@code Boolean} test result
	 */
	public Boolean isContained(Cfg_Entry container) {
		if (container == null) {
			return false;
		}
		if (isCaseSensitive() && container.isCaseSensitive()) {
			return container.toString().contains(entry);
		}
		// at least one is not case sensitive
		return container.toString().toUpperCase().contains(entry.toUpperCase());
	}
	/**
	 * check if a valid numeric value may be extracted
	 * @return true if is Numeric
	 */
	public Boolean testForNumeric() {
		return Cfg_Util.testForNumeric(entry);
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Remove the comments and clean
	 * @return this for chaining purpose
	 */
	public Cfg_Entry removeComment() {
		set(CommentLine.removeComment(entry));
		return this;
	}
}
