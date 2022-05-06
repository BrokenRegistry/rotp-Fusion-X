
/**
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
 * The internal parameter will never be null
 * and will be stripped
 */
class EntryValid_String extends Generic_Valid<String> {

	// From Abstract_Entry:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// From Abstract_EntryValid:
	//	private String value =  null;
	//	private Valid_String validationData;
	
	// outputStr := User View
	// value     := Code View
	
    // ==================================================
    // Constructors
    //
	/**
	 * create and initialize {@code EntryValid_String}
	 * @param validationData the {@code Valid_String} validationData
	 */
	EntryValid_String(
			Abstract_ValidData<String> validationData) {
		super(validationData);
	}
	
	/**
	 * create and initialize {@code EntryValid_String}
	 * @param validationData the {@code Valid_String} validationData
	 * @param value the {@code String} value
	 */
	EntryValid_String(
			Abstract_ValidData<String> validationData,
			String value) {
		super(validationData, value);
	}
	
	/**
	 * create and initialize {@code EntryValid_String}
	 * @param validationData the {@code Valid_String} validationData
	 * @param value the {@code String} value
	 * @param printFormat the default {@code PrintFormat}
	 */
	EntryValid_String(
			Abstract_ValidData<String> validationData,
			String value,
			PrintFormat printFormat) {
		super(validationData, value, printFormat);
	}
	
	// ==================================================
    // Abstract Methods overriders + extended
    //
	/**
	 * return value as {@code String}
	 * @return the original value
	 */
	@Override public String toString() {
		return getValue();
	}
	
	@Override protected EntryValid_String reset() {
		super.reset();
		return this;
	}
	
	/**
	 * Set a new {@code String} value
	 * @param newValue the new {@code String} value
	 * @return this for chaining purpose 
	 */
	@Override EntryValid_String set(String newValue) {
		super.set(newValue);
		return this;
	}
	
	/**
	 * Ask for a clone of {@code EntryValid_String}
	 * @return the cloned {@code EntryValid_String}
	 */
	@Override protected EntryValid_String clone() { 
		return new EntryValid_String(
				getValidationData(), getUserEntry(), getPrintFormat());
	}
	
	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	@Override EntryValid_String copy() { 
		return this;
	}

    // ==================================================
    // Setters
    //
	
    // ==================================================
    // Getters simple
    //
	
    // ==================================================
    // Tests Methods
    //
}
