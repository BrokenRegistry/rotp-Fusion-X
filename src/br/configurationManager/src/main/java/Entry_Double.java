
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
 * Numeric Double management and conversion.
 * No exceptions are thrown, if a problem happen: null will be returned.
 * not the type then null.
 */	
class Entry_Double extends Abstract_Entry<Double>{

	// From Entry_Base:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// extended with:
	private Double value = null;
	// ==================================================
    // Constructors
    //
	/**
	 * Create new empty {@code Entry_Double}
	 */
	Entry_Double() {}

	/**
	 * Create new {@code Entry_Double} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 */
	Entry_Double(String entry) {
		init(entry);
	}

	/**
	 * Create new {@code Entry_Double} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 */
	Entry_Double(Double value) {
		init(value);
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set new {@code Double} value for {@code Double}
	 * @param  newValue  the new {@code Double} value
	 * @return this for chaining purpose
	 */
	Entry_Double set(Double newValue) {
		reset();
		init(newValue);
		return this;
	}

	/**
	 * Set new {@code Double} value for {@code Entry_Double}
	 * @param  newValue  the new {@code Double} value
	 * @return this for chaining purpose
	 */
	Entry_Double set(Entry_Double newValue) {
		reset();
		init(newValue.getValue());
		return this;
	}

	// ==================================================
    // Overriders
    //
	/**
	 * Set new {@code String} value for {@code Entry_Double}
	 * @param  entry  the new {@code String} value
	 * @return this for chaining purpose	 */
	@Override Entry_Double set(String entry) {
		reset();
		init(entry);
		return this;
	}

	/**
	 * return value as {@code Double}
	 * @return the original value
	 */
	@Override Double getValue() {
		return value;
	}
	
	@Override Entry_Double reset() {
		super.reset();
		value = null;
		return this;
	}

	/**
	 * Ask for a cloned {@code Entry_Double}
	 * @return the clone {@code Entry_Double}
	 */
	@Override protected Entry_Double clone() { 
		if (getUserEntry().isEmpty()) {
			return new Entry_Double(value);
		}
		return new Entry_Double(getUserEntry());
	}

	/**
	 * Ask for a copy of superclass
	 * @return value as  {@code Entry_Double} (used by subclasses)
	 */
	@Override Entry_Double copy() { 
		return this;
	}

	/**
	 * Convert the value to {@code String}
	 * @return  {@code String}
	 */
	@Override public String toString() { 
		return getOutputStr();
	}

	/**
	 * Check if any value may be returned
	 * @return  true if no values have been set,
	 *  only empty {@code String} may be returned
	 */	
	@Override boolean isBlank() {
		return value == null;
	}

	// ========== overriders for chaining purpose ==========
	@Override Entry_Double removeComment() {
		super.removeComment();
		return this;
	}

	// ==================================================
    // Testers
    //
	/**
	 * Check if the value may be returned as {@code int}
	 * @return  true if {@code int} compatible
	 */
	boolean isDouble() { 
		return getValue() != null;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Convert the value to {@code int}
	 * @return  value or null if none or not compatible
	 */
	Double toDouble() { 
		return  getValue();
	}

	/**
	 * get the value as {@code Double}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	Double toDouble(Double onEmpty) { 
		return  getValue(onEmpty);
	}

	// ==================================================
    // Tools
    //
	/**
	 * Compare the two numerical value and keep the smallest one
	 * @param  min the value to compare
	 * @return the smallest value or this if equal
	 */
	Entry_Double validateMin(Double min) {
		return init(CMutil.max(getValue(), min));
	}

	/**
	 * Compare the two numerical value and keep the biggest one
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	Entry_Double validateMax(Double max) {
		return init(CMutil.min(getValue(), max));
	}

	/**
	 * Compare this value with the two other and keep it inside the range
	 * @param  min the value to compare
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	 Entry_Double validateMinMax(Double min, Double max) {
		return init(CMutil.validateLimits(getValue(), min, max));
	}

//	/**
//	 * Compare the two numerical value and return the biggest one.
//	 * If value are null the other one win. If both are null return null
//	 * @param  value1 the first value to compare, the favorite in equality
//	 * @param  value2 the second value to compare
//	 * @return the biggest value or value 1 if equal
//	 */
//	static Entry_Double max(Entry_Double value1, Entry_Double value2) {
//		// If one is null, return the other, return null if both are null
//		if (value1 == null) {
//			return value2.clone();
//		}
//		if (value2 == null) {
//			return value1.clone();
//		}
//		if (value1.getValue() == null) {
//			return value2.clone();
//		}
//		if (value2.getValue() == null) {
//			return value1.clone();
//		}
//		if (value1.getValue() >= value2.getValue()) {
//			return value1.clone();
//		}
//		return value2.clone();
//	}
//	/**
//	 * Compare the two numerical value and return the smallest one.
//	 * If value are null the other one win. If both are null return null
//	 * @param  value1 the first value to compare, the favorite in equality
//	 * @param  value2 the second value to compare
//	 * @return the smallest value or value 1 if equal
//	 */
//	static Entry_Double min(Entry_Double value1, Entry_Double value2) {
//		// If one is null, return the other, return null if both are null
//		if (value1 == null) {
//			return value2.clone();
//		}
//		if (value2 == null) {
//			return value1.clone();
//		}
//		if (value1.getValue() == null) {
//			return value2.clone();
//		}
//		if (value2.getValue() == null) {
//			return value1.clone();
//		}
//		if (value1.getValue() <= value2.getValue()) {
//			return value1.clone();
//		}
//		return value2.clone();
//	}
	// ==================================================
    // Other Methods
    //
//	/**
//	 * Remove the comments and clean
//	 * @return this for chaining purpose
//	 */
//	@Override
//	Entry_Double removeComment() {
//		set(ToComment.removeComment(toString()));
//		return this;
//	}
	// ==================================================
    // Initialization Methods
    //
	private Entry_Double init(String source) {
		super.set(source);
		value = CMutil.toDouble(source);
		setOutputStr(CMutil.neverNull(value));
		return this;
	}

	private Entry_Double init(Double value) {
		super.set(CMutil.neverNull(value));
		this.value = value;
		return this;
	}
}