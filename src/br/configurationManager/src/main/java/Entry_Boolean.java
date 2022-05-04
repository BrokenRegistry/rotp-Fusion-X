
/*
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

/**
 * Boolean management and conversion.
 * No exceptions are thrown, if a problem happen: null will be returned.
 * not the type then null.
 */	
class Entry_Boolean extends Abstract_Entry<Boolean>{
	
	// From Entry_Base:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// extended with:
	private Boolean value = null;

	// ==================================================
    // Constructors
    //
	/**
	 * Create new empty {@code Entry_Boolean}
	 */
	Entry_Boolean() {}

	/**
	 * Create new {@code Entry_Boolean} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 */
	Entry_Boolean(String entry) {
		init(entry);
	}

	/**
	 * Create new {@code Entry_Boolean}
	 * and set a new {@code boolean} value
	 * @param  value  original {@code boolean} value
	 */
	Entry_Boolean(Boolean value) {
		init(value);
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set new {@code Boolean} value
	 * @param  value  the new {@code Boolean} value
	 * @return this for chaining purpose
	 */
	Entry_Boolean set(Boolean value) {
		reset();
		init(value);
		return this;
	}

	// ==================================================
    // Overriders and Mandatory methods
    //
	/**
	 * Set new {@code String} value for {@code Entry_Boolean}
	 * @param  entry  the new {@code String} value
	 * @return this for chaining purpose	 */
	@Override Entry_Boolean set(String entry) {
		reset();
		init(entry);
		return this;
	}

	/**
	 * return value as {@code String}
	 * @return the original value
	 */
	@Override Boolean getValue() { 
		return value;
	}

	@Override Entry_Boolean reset() {
		super.reset();
		value = null;
		return this;
	}
	
	/**
	 * Ask for a cloned {@code Entry_Boolean}
	 * @return the clone {@code Entry_Boolean}
	 */
	@Override protected Entry_Boolean clone() {
		if (getUserEntry().isEmpty()) {
			return new Entry_Boolean(value);
		}
		return new Entry_Boolean(getUserEntry());
	}
	
	/**
	 * Ask for a copy of superclass
	 * @return value as  {@code Entry_Boolean} (used by subclasses)
	 */
	@Override Entry_Boolean copy() { 
		return this;
	}
	
	@Override public String toString() { 
		return getOutputStr();
	}
	
	/**
	 * Ask if there is something usable in the element
	 * @return {@code boolean}
	 */
	@Override boolean isBlank() { 
		return value == null;
	}

	// ========== overriders for chaining purpose ==========
	@Override Entry_Boolean removeComment() {
		super.removeComment();
		return this;
	}

	// ==================================================
    // Testers
    //
	/**
	 * Check if the value may be returned as true if {@code long} compatible
	 * @return  true if {@code boolean} compatible
	 */
	boolean isBoolean() { 
		return getValue() != null;
	}

	// ==================================================
    // Getters
    //
	// Kept for more readability in the calling code 
	/**
	 * Convert the value to {@code boolean}
	 * @return  value or null if none or not compatible
	 */
	Boolean toBoolean() {
		return getValue();
	}

	// Kept for more readability in the calling code 	
	/**
	 * Convert the value to {@code Boolean}
	 * @param onEmpty default value to return
	 * @return  value or onEmpty if none or not compatible
	 */
	Boolean toBoolean(Boolean onEmpty) {
		return getValue(onEmpty);
	}

	// ==================================================
    // Initialization Methods
    //
	private void init(String source) {
		super.set(source);
		value = CMutil.toBoolean(source);
		setOutputStr(CMutil.toYesNoString(value));
	}
	
	private void init(Boolean value) {
		super.set(CMutil.toYesNoString(value));
		this.value = value;
	}
}