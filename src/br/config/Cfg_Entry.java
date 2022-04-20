
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

/**
 * The internal parameter will never be null
 * and will be striped
 */
public class Cfg_Entry extends Cfg_Util {

	
	private String entry = "";
	private Boolean caseSensitive;
		// false = tested as UpperCase
		// null = default Value
	
    // ==================================================
    // Constructors
    //
	public Cfg_Entry() {}
	public Cfg_Entry(Cfg_Entry entry) {
		set(entry);
	}
	public Cfg_Entry(String entry) {
		set(entry);
	}
	public Cfg_Entry(Cfg_Entry entry, boolean caseSensitive) {
		set(entry, caseSensitive);
	}
	public Cfg_Entry(String entry, boolean caseSensitive) {
		set(entry, caseSensitive);
	}
    // ==================================================
    // Setters
    //
	/**
	 * Set a new String entry
	 */
	public void set(String newValue) {
		entry = clean(newValue);
	}
	/**
	 * Set a new String entry and new case Sensitivity
	 */
	public void set(String newValue, boolean caseSensitive) {
		entry = clean(newValue);
		setCaseSensitive(caseSensitive);
	}
	public void set(Cfg_Entry newValue) {
		set(newValue.get(), newValue.isCaseSensitive());
	}
	public void set(Cfg_Entry newValue, boolean caseSensitive) {
		set(newValue.get(), isCaseSensitive());
	}
	/**
	 * Set case sensitivity
	 */
	public void setCaseSensitive(boolean newValue) {
		caseSensitive = newValue;
	}
    // ==================================================
    // Getters simple
    //
	public boolean isCaseSensitive() {
		if (caseSensitive == null) {
			return getDefaultCaseSensitivity();
		}
		return caseSensitive;
	}
	/**
	 * return entry as String
	 */
	public String get() { 
		return entry;
	}
	/**
	 * return entry as Cfg_Entry
	 */
	public Cfg_Entry getCfg_Entry() { 
		return this;
	}
	/**
	 * return entry as String
	 */
	public String toString() { 
		return entry;
	}
	/**
	 * return entry as String, ready to be printed
	 */
	public String toPrint() { 
		return entry;
	}
	/**
	 * Test case sensitivity and return value
	 * @return toUpperCase if not case sensitive
	 */
	public String toTest() {
		if (isCaseSensitive()) {
			return entry;
		}
		return toKey();
	}
	/**
	 * return value ready to be tested as not case sensitive
	 * @return value toUpperCase
	 */
	public String toKey() {
		return entry.toUpperCase();
	}
	/**
	 * return entry in lower case, with first char to upper case,
	 * with every word capitalized if eachWord is true
	 */
	public String toCapitalized() { 
		return capitalize(entry);
	}
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 */
	public String toSentence() { 
		return toSentence(entry);
	}
    // ==================================================
    // Getters with default values
    //
	/**
	 * return the entry, defaultValue if blank
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
	 * true if Empty or null
	 */
	public boolean isEmpty() {
	    return (entry.isEmpty());
    }
	/**
	 * true if Blank, Empty or null
	 */
	public boolean isBlank() {
	    return (entry.isBlank());
    }
	/**
	 * check if entry == value taking account of case sensitivity
	 */
	public boolean equals(String value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return entry.equals(value);
		}
		return entry.equalsIgnoreCase(value);
	}
	/**
	 * check if entry == value taking account of both case sensitivity
	 */
	public boolean equals(Cfg_Entry value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive() && value.isCaseSensitive()) {
			return entry.equals(value.toString());
		}
		return entry.equalsIgnoreCase(value.toString());
	}
	/**
	 * check if entry contains value taking account of case sensitivity
	 */
	public boolean contains(String value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return entry.contains(value);
		}
		return entry.toUpperCase().contains(value.toUpperCase());
	}
	/**
	 * check if entry contains value taking account of both case sensitivity
	 */
	public boolean contains(Cfg_Entry value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive() && value.isCaseSensitive()) {
			return entry.contains(value.toString());
		}
		// at least one is not case sensitive
		return entry.toUpperCase().contains(value.toString().toUpperCase());
	}
	/**
	 * check if value contains entry taking account of case sensitivity
	 */
	public boolean isContained(String value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive()) {
			return value.contains(entry);
		}
		return value.toUpperCase().contains(entry.toUpperCase());
	}
	/**
	 * check if value contains entry taking account of both case sensitivity
	 */
	public boolean isContained(Cfg_Entry value) {
		if (value == null) {
			return false;
		}
		if (isCaseSensitive() && value.isCaseSensitive()) {
			return value.toString().contains(entry);
		}
		// at least one is not case sensitive
		return value.toString().toUpperCase().contains(entry.toUpperCase());
	}
	/**
	 * check for the presence of "[0-9.]+"
	 */
	public boolean isNumeric() {
		return testForNumeric(entry);
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Remove the comments and clean
	 */
	public void removeComment() {
		set(CommentLine.removeComment(entry));
	}
}
