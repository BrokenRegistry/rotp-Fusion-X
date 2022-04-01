
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

public class CfgLine {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    static final String BASE_KEY_FORMAT     = "%-20s";
	static final String KEY_VALUE_SEPARATOR = ":";
	static final String VALUE_SPACER        = " ";
	static final String KEY_VALUE_SEPARATOR_KEY_SPACER = KEY_VALUE_SEPARATOR + VALUE_SPACER;
	static final String KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_KEY_SPACER;

    // ------------------------------------------------------------------------
	// Variables Properties
    //
	private KeyField key   = new KeyField();
	private CfgField value = new CfgField();
   	// --------------------------------------------------------------
    // Constructors
    //
	public CfgLine(String key, String value) {
		setKey(key);
		setValue(value);
	}
	public CfgLine(KeyField key, CfgField value) {
		setKey(key);
		setValue(value);
	}
	public CfgLine(KeyField key, String value) {
		setKey(key);
		setValue(value);
	}
	public CfgLine(String key, CfgField value) {
		setKey(key);
		setValue(value);
	}
	public CfgLine(String line) {
 		if (CfgField.isBlank(line)) {
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
	 * set a new String value for the left side field
	 * Return current object to allow chaining
	 */
	private CfgLine setKey(String newValue) {
		key.set(newValue);
		return this;
	}
	/**
	 * set a new CfgField value for the left side field
	 * Return current object to allow chaining
	 */
	private CfgLine setKey(KeyField newValue) {
		key = newValue;
		return this;
	}
	/**
	 * set a new String value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setValue(String newValue) { 
		value.set(newValue);
		return this;
	}
	/**
	 * set a new CfgField value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setValue(CfgField newValue) { 
		value = newValue;
		return this;
	}
	/**
	 * set a new boolean value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setValue(boolean newValue) { 
		value.set(newValue);
		return this;
	}
	/**
	 * set a new integer value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setValue(int newValue) { 
		value.set(newValue);
		return this;
	}
	/**
	 * Return the Right Side value as CfgField
	 */
	public CfgField value() {
		return value;
	}
	/**
	 * Return the left Side value as CfgField
	 */
	public KeyField key() {
		return key;
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
	/**
	 * return line as String, ready to be printed
	 */
	public String toPrint() {
		return String.format(KEY_FORMAT, key.toString()) + value.toCapitalized();
	}
}

