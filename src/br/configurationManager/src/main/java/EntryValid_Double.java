
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
class EntryValid_Double extends Generic_Valid<Double> {

	// From Abstract_Entry:
	// private String userEntry  // what we get from the file
	// private String outputStr  // what will be the outputStr
	//                           // To make the common code easier to manage
	// From Abstract_EntryValid:
	//	private Double value =  null;
	//	private Valid_Double validationData;
	
	// outputStr := User View
	// value     := Code View
	
    // ==================================================
    // Constructors
    //
	/**
	 * create and initialize {@code EntryValid_Double}
	 * @param validationData the {@code Valid_Double} validationData
	 */
	EntryValid_Double(
			Abstract_ValidData<Double> validationData) {
		super(validationData);
	}
	
	/**
	 * create and initialize {@code EntryValid_Double}
	 * @param validationData the {@code Valid_Double} validationData
	 * @param value the {@code Double} value
	 */
	EntryValid_Double(
			Abstract_ValidData<Double> validationData,
			String value) {
		super(validationData, value);
	}
	
	/**
	 * create and initialize {@code EntryValid_Double}
	 * @param validationData the {@code Valid_Double} validationData
	 * @param value the {@code String} value
	 * @param printFormat the default {@code PrintFormat}
	 */
	EntryValid_Double(
			Abstract_ValidData<Double> validationData,
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
	
	@Override protected EntryValid_Double reset() {
		super.reset();
		return this;
	}
	
	/**
	 * Set a new {@code String} value
	 * @param newValue the new {@code String} value
	 * @return this for chaining purpose 
	 */
	@Override EntryValid_Double set(String newValue) {
		super.set(newValue);
		return this;
	}
	
	/**
	 * Ask for a clone of {@code EntryValid_Double}
	 * @return the cloned {@code EntryValid_Double}
	 */
	@Override protected EntryValid_Double clone() { 
		return new EntryValid_Double(
				getValidationData(), getUserEntry(), getPrintFormat());
	}
	
	/**
	 * may be used by child class, returns the instance of this object.
	 * @return a copy of this object.
	 */
	@Override EntryValid_Double copy() { 
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
