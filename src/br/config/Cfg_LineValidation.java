
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

import java.util.Arrays;
import java.util.List;

public class Cfg_LineValidation {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    static final String BASE_LABEL_FORMAT     
	= "%-" + Cfg_Util.LINE_SPLIT_POSITION.toString() + "s";
    static final String COMMENT_FORMAT     
	= "%-" + Cfg_Util.END_COMMENT_POSITION.toString() + "s";
	static final String LABEL_VALUE_SEPARATOR = ":";
	static final String VALUE_SPACER        = " ";
	static final String LABEL_VALUE_SEPARATOR_SPACER = LABEL_VALUE_SEPARATOR + VALUE_SPACER;
	static final String LABEL_FORMAT = BASE_LABEL_FORMAT + LABEL_VALUE_SEPARATOR_SPACER;
    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private Cfg_Entry label;
	private Cfg_ValueValidation value;
	private CommentLine comment;
    // ==================================================
    // Constructors
    //
	/**
	 * New CfgLineValidation with validationData
	 * @param validationData
	 */
	protected Cfg_LineValidation(Cfg_ValidationData validationData) {
		setValidationData(validationData);
	}
	/**
	 * New CfgLineValidation with validationData, label
	 */
	protected Cfg_LineValidation(Cfg_ValidationData validationData
								, String label) {
		setValidationData(validationData);
		setLabel(label);
	}
	/**
	 * New CfgLineValidation with validationData, label and value
	 */
	protected Cfg_LineValidation(Cfg_ValidationData validationData
								, String label, String value) {
		setValidationData(validationData);
		setLabel(label);
		setValue(value);
	}
	/**
	 * New CfgLineValidation without validationData
	 */
	protected Cfg_LineValidation(String line) {
		newLine(line);
	}
	/**
	 * New CfgLineValidation without validationData
	 */
	protected Cfg_LineValidation(String label, String value) {
		setLabel(label);
		setValue(value);
	}
	/**
	 * New CfgLineValidation without validationData
	 */
	protected Cfg_LineValidation(String label, String value, String comment) {
		setLabel(label);
		setValue(value);
		setComment(comment);
	}
	/**
	 * New CfgLineValidation without validationData
	 */
	protected Cfg_LineValidation(String label, String value, CommentLine comment) {
		setLabel(label);
		setValue(value);
		setComment(comment);
	}
	// --------------------------------------------------------------
	// Protected Methods
	//
	/**
	 * Test if a valid non blank value may be returned from user choice
	 */
	protected Boolean isValid() {
		return value.isValid();
	}
	/**
	 * @return label as it was in file line as String
	 */
	protected Cfg_Entry label() {
		return label;
	}
	/**
	 * @return value as it was in file line as String
	 */
	protected String getRawValue() {
		return value.toString();
	}
	/**
	 * @return value as it was in file line as CfgEntry
	 */
	protected Cfg_Entry rawValue() {
		return value.getRawEntry();
	}
	/**
	 * @return Current category as String
	 */
	protected String getCategory() {
		return value.getCategory();
	}
	/**
	 * @return Current option as String
	 */
	protected String getStringOption() {
		return value.getStringOption();
	}
	/**
	 * @return Current option as Numeric
	 */
	protected Cfg_Value getNumericOption() {
		return value.getNumericOption();
	}
	/**
	 * Assign label, value and comment from file line
	 * @param newLine new String contents to be assigned
	 * @return this for chaining purpose
	 */
	protected Cfg_LineValidation newLine (String newLine) {
		if (Cfg_Util.isBlank(newLine)) { // should not happen!!! but let's continue...
 			return this;
 			}
		// Get the comment
		Cfg_Entry cfgEntryLine = new Cfg_Entry(newLine); 
		comment = CommentLine.splitComment(cfgEntryLine);
		// Split Label and Value
		List<String> list = Arrays.asList(cfgEntryLine.toString().split(LABEL_VALUE_SEPARATOR));
		setLabel(list.get(0));
		if (list.size() > 1) { // in the case the value contains KEY_VALUE_SEPARATOR
			setValue(String.join(LABEL_VALUE_SEPARATOR, list.subList(1, list.size())));
		}
		return this;
	}
	// --------------------------------------------------------------
	// Setters
	//
	private Cfg_LineValidation setValidationData(Cfg_ValidationData options) {
		value = new Cfg_ValueValidation(options);
		return this;
	}
	private void setLabel(Cfg_Entry label) {
		this.label = label;
	}
	private void setLabel(String label) {
		setLabel(new Cfg_Entry(label));
	}
	protected void setValue(Cfg_Entry value) {
		this.value.setEntry(value);
	}
	protected void setValue(String value) {
		setValue(new Cfg_Entry(value));
	}
	private CommentLine getComment() {
		return comment;
	}
	// --------------------------------------------------------------
	// Getters
	//
	private Cfg_ValueValidation getValue() {
		return value;
	}
	private void setComment(CommentLine comment) {
		this.comment = comment;
	}
	private void setComment(String comment) {
		this.comment = new CommentLine(comment);
	}
	// ------------------------------------------------------------------------
	// Other Public Methods
	//
	/**
	 * @return line as String, ready to be printed
	 */
	public String toPrint() {
		String left = String.format(LABEL_FORMAT, label().toCapitalized());
		String mid  = rawValue().toCapitalized();
		if (getComment() == null || getComment().isEmpty()) {
			return left + mid;
		}
		return String.format(COMMENT_FORMAT, left + mid) + getComment().toPrint();
	}
}
