
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

package br.profileManager.src.main.java;

/**
 * Base for every User Entry Lines
 * @param <ValueClass> the Value's Code View Class
 * @param <ValidClass>  The Value's validation class
 */
class Generic_Line<
		ValueClass, 
		ValidClass extends Abstract_ValidData<ValueClass>> extends WriteUtil {
	
    // ==================================================
	// Constant Properties
    //
    private static final String BASE_KEY_FORMAT = 
    		"%-" + Integer.toString(lineSplitPosition()) + "s";
    public  static final String KEY_VALUE_SEPARATOR = ":";
    private static final String VALUE_SPACER        = " ";
    private static final String	KEY_VALUE_SEPARATOR_PRT =
    		KEY_VALUE_SEPARATOR + VALUE_SPACER;
    private static final String KEY_FORMAT = 
    		BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_PRT;
    private static final String KEY_VALUE_FORMAT =
    		"%-" + Integer.toString(commentEndPosition()) + "s";
 
    // ==================================================
	// Variables Properties
    //
	private String entryName = "none";
	private Generic_Entry<ValueClass, ValidClass> entryValue;
	private String comment = null;

	// ==================================================
    // Constructors
    //
	/**
	 * create and initialize a new {@code LineValid} with default value
	 * @param validationData the {@code Abstract_ValidData<ValueClass>} validationData
	 */
	Generic_Line(Abstract_ValidData<ValueClass> validationData) {
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData);
		setName(validationData.getDefaultName());
	}
	
	Generic_Line(Abstract_ValidData<ValueClass> validationData,
			 String line) {	
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData);
		newLine(line);
	}

	Generic_Line(Abstract_ValidData<ValueClass> validationData,
			 String name,
			 String value) {
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData, value);
		setName(name);
	}

	Generic_Line(Abstract_ValidData<ValueClass> validationData,
			 String name,
			 ValueClass value) {
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData, value);
		setName(name);
	}

	Generic_Line(Abstract_ValidData<ValueClass> validationData,
			 String name,
			 String value,
			 String comment) {
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData, value);
		setName(name);
		setComment(comment);
	}

	Generic_Line(Abstract_ValidData<ValueClass> validationData,
			 String name,
			 ValueClass value, 
			 String comment) {
		entryValue = new Generic_Entry<ValueClass, ValidClass>(validationData, value);
		setName(name);
		setComment(comment);
	}

	@SuppressWarnings("unused")
	private Generic_Line() {} // Forbidden constructor
	
	// ==================================================
	// Setters
	//
	private void initValue(String newValue) {
		entryValue.set(newValue);
	}

	Generic_Line<ValueClass, ValidClass> newLine(String line) {
 		if (PMutil.isBlank(line)) {
 			initValue("");
 			return this;
 			}
 		// Get the comment if one
 		setComment(WriteUtil.extractComment(line));
 		// Split the Key and the value
		String[] list = WriteUtil.removeComment(line).split(KEY_VALUE_SEPARATOR, 2);
		setName(list[0]);
		if (list.length == 2) {
			initValue(list[1]);
		}
		return this;
	}

	/**
	 * set a new {@code String} key
	 * @param newKey the new {@code String} key
	 * @return current object to allow chaining
	 */
	Generic_Line<ValueClass, ValidClass> setName(String newKey) {
		entryName = PMutil.clean(newKey);
		return this;
	}

	/**
	 * set a new {@code ValueClass} value
	 * @param newValue the new {@code ValueClass} Value
	 * @return current object to allow chaining
	 */
	Generic_Line<ValueClass, ValidClass> setValue(ValueClass newValue) {
		entryValue.setValue(newValue);
		return this;
	}

	/**
	 * set a new {@code String} comment
	 * @param newComment the new {@code String} Comment
	 * @return current object to allow chaining
	 */
	Generic_Line<ValueClass, ValidClass> setComment(String newComment) {
		comment = newComment;
		return this;
	}
	
	// ==================================================
	// Getters
	//
	/**
	 * @return the entry Name as {@code String}
	 */
	String getName() {
		return entryName;
	}

	/**
	 * @return the entryValue value as {@code ValueClass}
	 */
	ValueClass getValue() {
		return entryValue.getValue();
	}

	/**
	 * @param defaultValue The value to return on Blank
	 * @return the entryValue value as {@code ValueClass}
	 */
	ValueClass getValue(ValueClass defaultValue) {
		return entryValue.getValue(defaultValue);
	}

	/**
	 * @param defaultValue The value to return on Blank
	 * @return the entryValue value as {@code ValueClass}
	 */
	ValueClass getOrDefault(ValueClass defaultValue) {
		return entryValue.getOrDefault(defaultValue);
	}

	/**
	 * @return the comment value as {@code String}
	 */
	String getComment() {
		return comment;
	}

	/**
	 * Return the entryValue as {@code EntryValid}
	 */
	Generic_Entry<ValueClass, ValidClass> getValueAsEntry() {
		return entryValue;
	}

	/**
	 * Return the Value Validation Data
	 */
	Abstract_ValidData<ValueClass> getValidationData() {
		return getValueAsEntry().getValidationData();
	}

	// ==================================================
	// Testers
	//
	/**
	 * Check if this entry line is dedicated for this Validation,
	 * if true, set new parameters
	 * @param line the {@code String to analyze}
	 * @return <b>true</b> if line was used
	 */
	boolean isLineForMe(String line) {
		if (getName().equalsIgnoreCase(getKey(line))) {
			newLine(line);
			return true;
		}
		return false;
	}
	
	/**
	 * Ask if something has been set by user entry
	 * @return {@code boolean}
	 */
	boolean isBlankUserEntry() { 
		return entryValue.isBlankUserEntry();
	}

	/**
	 * Ask if about the value state (code view)
	 * @return {@code boolean}
	 */
	boolean isBlankValue() { 
		return entryValue.isBlankValue();
	}

	/**
	 * Ask if about the value state (code view)
	 * @return {@code boolean}
	 */
	boolean isValidValue() { 
		return entryValue.isValidValue();
	}

	/**
	 * Test if the value is part of the category
	 * @param category the {@code String} category to filter with
	 * @return as {@code boolean} never null
	 */
	boolean isValueFromCategory(String category) {
		if (getValueAsEntry() == null) {
			return false;
		}
		return getValidationData().isValidCodeView(getValue(), category);
	}

	/**
	 * Test if the Filter contains the value
	 * @param filter the {@code String} containing filter
	 * @return as {@code boolean} never null
	 */
	boolean isValueInFilter(String filter) {
		if (getValue() == null) {
			return false;
		}
		String value = getValue().toString();
		if (value != null) {
			return PMutil.containsIgnoreCase(filter, value);
		}
		return false;
	}

	/**
	 * Test if the value contains the filter
	 * @param filter the {@code String} filter to be contained
	 * @return as {@code boolean} never null
	 */
	boolean isFilterInValue(String filter) {
		if (getValue() == null) {
			return false;
		}
		String value = getValue().toString();
		if (value != null) {
			return PMutil.containsIgnoreCase(value, filter);
		}
		return false;
	}

	/**
	 * Test if the value equals the filter
	 * @param filter the {@code String} filter to test
	 * @return as {@code boolean} never null
	 */
	boolean isValueAsFilter(String filter) {
		if (getValue() != null
				&& filter != null) {
			String value = getValue().toString();
			if (value != null) {
				return value.equalsIgnoreCase(filter);
			}
		}
		return false;
	}

	// ==================================================
    // Overriders and overridden
    //
	@Override public String toString() {
		String out = "";
		out += String.format(KEY_FORMAT, entryName.toString());
		out += PMutil.neverNull(entryValue);
		if (comment != null && !comment.isBlank()) {
			out = String.format(KEY_VALUE_FORMAT, out);
			if (!" ".equals(PMutil.getLastChar(out))) {
				out += " ";
			}
			out += toComment(comment);		
		}
		return out;
	}

	// ==================================================
    // Static Methods
    //
	/**
	 * Test if the {@code String} has a key and extract it
	 * @param line the {@code String} to analyze
	 * @return the {@code String} key if one, never null
	 */
	static String getKey(String line) {
		line = PMutil.clean(line);
		if (line.isBlank()) {
			return "";
		}
		return line.split(KEY_VALUE_SEPARATOR, 2)[0].strip();
	}
	/**
	 * Test if the {@code String} has a value and extract it
	 * @param line the {@code String} to analyze
	 * @return the {@code String} value if one, never null
	 */
	static String getValueAsString(String line) {
		line = PMutil.clean(line);
		if (!line.isBlank()) {
			String[] list = WriteUtil.removeComment(line).split(KEY_VALUE_SEPARATOR, 2);
			if (list.length == 2) {
				return list[1].strip();
			}
		}
		return "";
	}
}

