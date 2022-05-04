
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

package br.cfg.old;
//
//import static br.config.src.main.java.CfgUtil.*;
//
//import br.config.src.main.java.CfgUtil;
//import br.config.src.main.java.Abstract_ToComment;
//import br.config.src.main.java.Abstract_ToPrint;
//
///**
// * The internal parameter will never be null
// * and will be stripped
// * @param <ValueClass> The class of Values
// */
//public abstract class Entry_Base <ValueClass> implements Abstract_ToPrint, Abstract_ToComment{
//
//	
//	private String entry = "";
//	
//    // ==================================================
//    // Constructors
//    //
//	/**
//	 * create a new empty {@code Entry_String}
//	 */
//	public Entry_Base() {}
//	/**
//	 * create and initialize a new {@code Entry_String}
//	 * @param entry the {@code String} entry
//	 */
//	public Entry_Base(String entry) {
//		set(entry);
//	}
//	// ==================================================
//    // Abstract User
//    //
//    //
//	/**
//	 * return value as {@code ValueClass}
//	 * @return the value
//	 */
//	public ValueClass get() {
//		return getOrDefault((ValueClass)null);
//	}
// 	// ==================================================
//    // Abstract Methods Declaration
//    //
//
//	/**
//	 * Ask for a non <i>empty</i> value
//	 * @param defaultValue returned value if <i>blank</i> or  <i>empty</i> 
//	 * @return the value, defaultValue if <i>blank</i> or  <i>empty</i> 
//	 */
//	public abstract ValueClass getOrDefault(ValueClass defaultValue);
//	// ==================================================
//    // Abstract Mandatory Overriders
//    //
//	/**
//     * Format the value as Comment
//	 * @return the {@code String} formated value
//	 */
//	@Override
//	public String toComment() { 
//		return Abstract_ToComment.toComment(entry);
//	}
//	/**
//     * Format the value as Comment
//	 * @return the {@code String} formated value
//	 */
//	@Override
//	public String toComment(Boolean onEmpty) { 
//		return Abstract_ToComment.toComment(entry, onEmpty);
//	}
//	/**
//	 * Ask for String, ready to be printed
//	 * @return value as {@code String}, ready to be printed
//	 */
//	@Override
//	public String toPrint() { 
//		return toString();
//	}
//	// ==================================================
//    // Overriders
//    //
//	/**
//	 * Ask for value as {@code String}
//	 * @return the {@code String}
//	 */
//	@Override
//	public String toString() { 
//		return entry;
//	}
//	// ==================================================
//    // Setters
//    //
//	protected void reset() {
//		entry = null;
//	}
//	/**
//	 * Set a new {@code String} entry
//	 * @param newValue the new {@code String} entry
//	 * @return this for chaining purpose 
//	 */
//	public Entry_Base<ValueClass> set(String newValue) {
//		entry = clean(newValue);
//		return this;
//	}
//    // ==================================================
//    // Getters simple
//    //
//	/**
//	 * ask for value in lower case, with first char to upper case,
//	 * with every word capitalized if eachWord is true
//	 * @param onlyFirstWord if true only the first word is capitalized
//	 * @return a {@code String} as requested
//	 */
//	public String toCapitalized(Boolean onlyFirstWord) { 
//		return capitalize(entry, onlyFirstWord);
//	}
//	/**
//	 * ask for value in lower case, with first char to upper case,
//	 * with every word capitalized
//	 * @return a {@code String} as requested
//	 */
//	public String toCapitalized() { 
//		return capitalize(entry);
//	}
//	/**
//	 * ask for a stripped in lower case with first char to upper case, never null
//	 * @return a {@code String} as requested
//	 */
//	public String toSentence() {
//		return CfgUtil.toSentence(entry);
//	}
//    // ==================================================
//    // Tests Methods
//    //
//	/**
//	 * Test if Empty or null
//	 * @return true if Empty or null
//	 */
//	public boolean isEmpty() {
//	    return (entry.isEmpty());
//    }
//	/**
//	 * Test if Blank, Empty or null
//	 * @return true if Blank, Empty or null
//	 */
//	public Boolean isBlank() {
//	    return (entry.isBlank());
//    }
//	/**
//	 * check if a valid numeric value may be extracted
//	 * @return true if is Numeric
//	 */
//	public Boolean testForNumeric() {
//		return CfgUtil.testForNumeric(entry);
//	}
//	// ==================================================
//    // Other Methods
//    //
//	/**
//	 * Remove the comments and clean
//	 * @return this for chaining purpose
//	 */
//	public Entry_Base<ValueClass> removeComment() {
//		set(Abstract_ToComment.removeComment(entry));
//		return this;
//	}
//}
