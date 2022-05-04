
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
 * Numeric Integer management and conversion.
 * No exceptions are thrown, if a problem happen: null will be returned.
 * not the type then null.
 */	
class Entry_Integer extends Abstract_Entry<Integer>{

	// From Entry_Base:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// extended with:
	private Integer value = null;
	// ==================================================
    // Constructors
    //
	/**
	 * Create new empty {@code Entry_Integer}
	 */
	Entry_Integer() {}

	/**
	 * Create new {@code Entry_Integer} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 */
	Entry_Integer(String entry) {
		init(entry);
	}

	/**
	 * Create new {@code Entry_Integer} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 */
	Entry_Integer(Integer value) {
		init(value);
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set new {@code Integer} value for {@code Integer}
	 * @param  newValue  the new {@code Integer} value
	 * @return this for chaining purpose
	 */
	Entry_Integer set(Integer newValue) {
		reset();
		init(newValue);
		return this;
	}

	/**
	 * Set new {@code Integer} value for {@code Entry_Integer}
	 * @param  newValue  the new {@code Integer} value
	 * @return this for chaining purpose
	 */
	Entry_Integer set(Entry_Integer newValue) {
		reset();
		init(newValue.getValue());
		return this;
	}

	// ==================================================
    // Overriders
    //
	/**
	 * Set new {@code String} value for {@code Entry_Integer}
	 * @param  entry  the new {@code String} value
	 * @return this for chaining purpose	 */
	@Override Entry_Integer set(String entry) {
		reset();
		init(entry);
		return this;
	}

	/**
	 * return value as {@code Integer}
	 * @return the original value
	 */
	@Override
	Integer getValue() {
		return value;
	}

	@Override Entry_Integer reset() {
		super.reset();
		value = null;
		return this;
	}

	/**
	 * Ask for a cloned {@code Entry_Integer}
	 * @return the clone {@code Entry_Integer}
	 */
	@Override protected Entry_Integer clone() { 
		if (getUserEntry().isEmpty()) {
			return new Entry_Integer(value);
		}
		return new Entry_Integer(getUserEntry());
	}

	/**
	 * Ask for a copy of superclass
	 * @return value as  {@code Entry_Integer} (used by subclasses)
	 */
	@Override Entry_Integer copy() { 
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
	@Override Entry_Integer removeComment() {
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
	boolean isInteger() { 
		return getValue() != null;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Convert the value to {@code int}
	 * @return  value or null if none or not compatible
	 */
	Integer toInteger() { 
		return  getValue();
	}

	/**
	 * get the value as {@code Integer}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	Integer toInteger(Integer onEmpty) { 
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
	Entry_Integer validateMin(Integer min) {
		return init(CMutil.max(getValue(), min));
	}

	/**
	 * Compare the two numerical value and keep the biggest one
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	Entry_Integer validateMax(Integer max) {
		return init(CMutil.min(getValue(), max));
	}

	/**
	 * Compare this value with the two other and keep it inside the range
	 * @param  min the value to compare
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	 Entry_Integer validateMinMax(Integer min, Integer max) {
		return init(CMutil.validateLimits(getValue(), min, max));
	}
//	/**
//	 * Compare the two numerical value and return the biggest one.
//	 * If value are null the other one win. If both are null return null
//	 * @param  value1 the first value to compare, the favorite in equality
//	 * @param  value2 the second value to compare
//	 * @return the biggest value or value 1 if equal
//	 */
//	static Entry_Integer max(Entry_Integer value1, Entry_Integer value2) {
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
//	static Entry_Integer min(Entry_Integer value1, Entry_Integer value2) {
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
//	Entry_Integer removeComment() {
//		set(ToComment.removeComment(toString()));
//		return this;
//	}
	// ==================================================
    // Initialization Methods
    //
	private Entry_Integer init(String source) {
		super.set(source);
		value = CMutil.toInteger(source);
		setOutputStr(CMutil.neverNull(value));
		return this;
	}

	private Entry_Integer init(Integer value) {
		super.set(CMutil.neverNull(value));
		this.value = value;
		return this;
	}
}