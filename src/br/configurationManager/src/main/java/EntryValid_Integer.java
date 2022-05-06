
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
class EntryValid_Integer extends Generic_Valid<Integer> {

	// From Abstract_Entry:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// From Abstract_EntryValid:
	//	private Integer value =  null;
	//	private Valid_Integer validationData;
	
	// outputStr := User View
	// value     := Code View
	
    // ==================================================
    // Constructors
    //
	/**
	 * create and initialize {@code EntryValid_Integer}
	 * @param validationData the {@code Valid_Integer} validationData
	 */
	EntryValid_Integer(
			Abstract_ValidData<Integer> validationData) {
		super(validationData);
	}
	
	/**
	 * create and initialize {@code EntryValid_Integer}
	 * @param validationData the {@code Valid_Integer} validationData
	 * @param value the {@code Integer} value
	 */
	EntryValid_Integer(
			Abstract_ValidData<Integer> validationData,
			String value) {
		super(validationData, value);
	}
	
	/**
	 * create and initialize {@code EntryValid_Integer}
	 * @param validationData the {@code Valid_Integer} validationData
	 * @param value the {@code String} value
	 * @param printFormat the default {@code PrintFormat}
	 */
	EntryValid_Integer(
			Abstract_ValidData<Integer> validationData,
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
		return getValue().toString();
	}
	
	@Override protected EntryValid_Integer reset() {
		super.reset();
		return this;
	}
	
	/**
	 * Set a new {@code String} value
	 * @param newValue the new {@code String} value
	 * @return this for chaining purpose 
	 */
	@Override EntryValid_Integer set(String newValue) {
		super.set(newValue);
		return this;
	}
	
	/**
	 * Ask for a clone of {@code EntryValid_Integer}
	 * @return the cloned {@code EntryValid_Integer}
	 */
	@Override protected EntryValid_Integer clone() { 
		return new EntryValid_Integer(
				getValidationData(), getUserEntry(), getPrintFormat());
	}
	
	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	@Override EntryValid_Integer copy() { 
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
