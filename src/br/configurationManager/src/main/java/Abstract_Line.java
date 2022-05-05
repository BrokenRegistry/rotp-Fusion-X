
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
 * Base for every User Entry Lines
 * @param <ValueClass> The class of Values
 */
abstract class Abstract_Line <ValueClass> extends Abstract_ToPrint {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    private static final String 
    		BASE_KEY_FORMAT = "%-" + LINE_SPLIT_POSITION.toString() + "s";
    private static final String KEY_VALUE_SEPARATOR = ":";
    private static final String VALUE_SPACER        = " ";
    private static final String
    		KEY_VALUE_SEPARATOR_PRT = KEY_VALUE_SEPARATOR + VALUE_SPACER;
    private static final String 
    		KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_PRT;
    private static final String 
    		KEY_VALUE_FORMAT = "%-" + END_COMMENT_POSITION.toString() + "s";
 
    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private EntryValid_String entryKey = new EntryValid_String(new Valid_String());
	private Abstract_EntryValid<ValueClass> entryValue;
	private String comment = null;

	// --------------------------------------------------------------
    // Constructors
    //
	Abstract_Line(Abstract_ValidData<ValueClass> validationData) {
		entryValue = InitValidationData(validationData);
		entryKey   = new EntryValid_String(new Valid_String());
	}
	
	Abstract_Line(Abstract_ValidData<ValueClass> validationData,
			Valid_String keyValidation) {
		entryValue = InitValidationData(validationData);
		entryKey   = new EntryValid_String(keyValidation);
	}

	Abstract_Line(Abstract_ValidData<ValueClass> validationData, String line) {
		entryValue = InitValidationData(validationData);
		entryKey   = new EntryValid_String(new Valid_String());
		newLine(line);
	}

	Abstract_Line(Abstract_ValidData<ValueClass> validationData,
			Valid_String keyValidation,
			String line) {
		entryValue = InitValidationData(validationData);
		entryKey   = new EntryValid_String(keyValidation);
		newLine(line);
	}

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * Initialize value as {@code EntryValid<ValueClass>}
	 * @return the value
	 */
	abstract Abstract_EntryValid<ValueClass> InitValidationData(
			Abstract_ValidData<ValueClass> validationData);

	// --------------------------------------------------------------
	// Setters
	//
	private void initValue(String newValue) {
		entryValue.set(newValue);
	}

	Abstract_Line<ValueClass> newLine(String line) {
 		if (CMutil.isBlank(line)) {
 			initValue("");
 			return this;
 			}
 		// Get the comment if one
 		setComment(Abstract_ToComment.extractComment(line));
 		// Split the Key and the value
		String[] list = Abstract_ToComment.removeComment(line).split(KEY_VALUE_SEPARATOR, 2);
		setKey(list[0]);
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
	Abstract_Line<ValueClass> setKey(String newKey) {
		entryKey.set(newKey);
		return this;
	}

	/**
	 * set a new {@code ValueClass} value
	 * @param newValue the new {@code ValueClass} Value
	 * @return current object to allow chaining
	 */
	Abstract_Line<ValueClass> setValue(ValueClass newValue) {
		entryValue.setValue(newValue);
		return this;
	}

	/**
	 * set a new {@code String} comment
	 * @param newComment the new {@code String} Comment
	 * @return current object to allow chaining
	 */
	Abstract_Line<ValueClass> setComment(String newComment) {
		comment = newComment;
		return this;
	}
	
	// --------------------------------------------------------------
	// Getters
	//
	/**
	 * @return the entryKey value as {@code String}
	 */
	String getKey() {
		return entryKey.getValue();
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
	 * @return the entryKey value as {@code Entry_String}
	 */
	EntryValid_String getKeyAsEntry() {
		return entryKey;
	}

	/**
	 * Return the entryValue as {@code EntryValid}
	 */
	Abstract_EntryValid<ValueClass> getValueAsEntry() {
		return entryValue;
	}

	// ==================================================
    // Overriders
    //
	@Override
	public
	String toString() {
		String out = "";
		out += String.format(KEY_FORMAT, entryKey.toString());
		out += entryValue.toString();
		out = String.format(KEY_VALUE_FORMAT, out);
		out += toComment(comment);
		return out;
	}
}

