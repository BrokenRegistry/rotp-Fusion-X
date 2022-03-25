
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
import java.util.Set;

public class UserChoice {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    static final String BASE_KEY_FORMAT     = "%-20s";
	static final String KEY_VALUE_SEPARATOR = ":";
	static final String VALUE_SPACER        = " ";
//	static final String RANDOM_ID           = "RANDOM";
	static final String KEY_VALUE_SEPARATOR_KEY_SPACER = KEY_VALUE_SEPARATOR + VALUE_SPACER;
	static final String KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_KEY_SPACER;
    static final String LABEL_OF_SECTION_KEY = "¦ SETTING";
    static final List<String> ENABLE_LOAD    = List.of("LOAD", "BOTH");
	static final List<String> ENABLE_WRITE   = List.of("SAVE", "BOTH");

    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private StrField key   = new StrField();
	private StrField value = new StrField();
   	// --------------------------------------------------------------
    // Constructors
    //
	public UserChoice(String key, String value) {
		setKey(key);
		setValue(value);
	}
	public UserChoice(StrField key, StrField value) {
		setKey(key);
		setValue(value);
	}
	public UserChoice(StrField key, String value) {
		setKey(key);
		setValue(value);
	}
	public UserChoice(String key, StrField value) {
		setKey(key);
		setValue(value);
	}
	public UserChoice(String line) {
 		if (StrField.isBlank(line)) {
 			return;
 			}
		List<String> list = Arrays.asList(line.split(KEY_VALUE_SEPARATOR));
		setKey(list.get(0));
		if (list.size() > 1) { // in the case the value contains KEY_VALUE_SEPARATOR
			setValue(String.join(KEY_VALUE_SEPARATOR, list.subList(1, list.size())));
		}
	}
	// --------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * set a new String value for the field "key"
	 * Return current object to allow chaining
	 */
	private UserChoice setKey(String newKey) {
		key.cleanSet(newKey);
		return this;
	}
	/**
	 * set a new StrField value for the field "key"
	 * Return current object to allow chaining
	 */
	private UserChoice setKey(StrField newKey) {
		key = newKey.clean();
		return this;
	}
	/**
	 * set a new String value for the field "value"
	 * Return current object to allow chaining
	 */
	public UserChoice setValue(String newValue) { 
		value.cleanSet(newValue);
		return this;
	}
	/**
	 * set a new StrField value for the field "value"
	 * Return current object to allow chaining
	 */
	public UserChoice setValue(StrField newValue) { 
		value = newValue.clean();
		return this;
	}
	/**
	 * set a new boolean value for the field "value"
	 * Return current object to allow chaining
	 */
	public UserChoice setValue(boolean newValue) { 
		value.cleanSet(newValue);
		return this;
	}
	/**
	 * set a new integer value for the field "value"
	 * Return current object to allow chaining
	 */
	public UserChoice setValue(int newValue) { 
		value.cleanSet(newValue);
		return this;
	}
	/**
	 * Return the value as integer  on Wrong if Blank
	 */
	public int getOrDefaultValue(int onWrong) { 
		return value.getOrDefault(onWrong);
	}
	/**
	 * Return the value as boolean  on Wrong if Blank
	 */
	public boolean getOrDefaultValue(boolean onWrong) {
		return value.getOrDefault(onWrong);
	}
	/**
	 * Return the value as String  on Wrong if Blank
	 */
	public String getOrDefaultValue(String onWrong) {
		return value.getOrDefault(onWrong);
	}
	/**
	 * Return the value as StrField
	 */
	public StrField getValue() {
		return value;
	}
	/**
	 * Return the raw value as String but never Null
	 */
	public String getValueAsString() {
		return value.toString();
	}
	/**
	 * Return the value as KEY comparison compatible
	 */
	public String getValueAsKey() {
		return value.getAsKey();
	}
	/**
	 * Return the key as StrField
	 */
	public StrField getKey() {
		return key;
	}
	/**
	 * Return the raw key as String but never Null
	 */
	public String getKeyAsString() {
		return key.toString();
	}
	/**
	 * true if not null nor blank
	 */
	public boolean hasKey() {
		return !key.isBlank();
	}
	// ------------------------------------------------------------------------
	// Other Methods
	//
	public int[] getRandomParameters(int min, int max) {
		return value.getOrDefaultMinMaxRandomParameters(min, max);
	}
	public boolean isValid(Integer min, Integer max) {
		Integer val = value.getOrDefault(min - 1);
		return (val >= min && val <= max);
	}
	public boolean isValid(List<String> list) {
		return list.contains(value.getUpperCase());
		}
	public boolean isValid(Set<String> set) {
		return set.contains(value.getUpperCase());
		}
	public boolean isBlank() { 
		return value.isBlank();
	}
	public boolean isSectionKey() {
		return key.keyTest(LABEL_OF_SECTION_KEY);
	}
	/**
	 * check if is member of RANDOM_LIST
	 */
//	public boolean isRandom() {
//		return value.isRandom();
//	}
	public boolean isWritable() {
		return value.isMemberOf(ENABLE_WRITE);
	}
	public boolean isReadable() { 
		return value.isMemberOf(ENABLE_LOAD);
	}
	public String toString() {
		return String.format(KEY_FORMAT, key.toString()) + value.getCapitalized(true);
	}
//	public static boolean isRandomValue(StrField value) {
//		return value.keyTest(RANDOM_ID);
//	}
//	public static boolean isRandomValue(String value) {
//		return new StrField(value).isRandom();
//	}
}

