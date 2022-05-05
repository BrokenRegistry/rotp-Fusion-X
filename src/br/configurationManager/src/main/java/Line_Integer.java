
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
 */
class Line_Integer extends Abstract_Line<Integer>{

   	// --------------------------------------------------------------
    // Constructors
    //
	Line_Integer() {
		super(new Valid_Integer());
	}

	/**
	 * @param validationData {@code Valid_Integer} validation parameters
	 */
	Line_Integer(Valid_Integer validationData) {
		super(validationData);
	}

	/**
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Integer(String line) {
		super(new Valid_Integer(), line);
	}
	/**
	 * @param validationData {@code Valid_Integer} validation parameters
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Integer(Valid_Integer validationData, String line) {
		super(validationData, line);
	}

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * Value Initialization with Validation Parameters 
	 * @return the value
	 */
	@Override EntryValid_Integer InitValidationData(
			Abstract_ValidData<Integer> validationData) {
		return new EntryValid_Integer(validationData);
	}

	// ==================================================
    // Overriders for chaining purpose
    //

	@Override Line_Integer newLine(String line) {
		super.newLine(line);
		return this;
	}

	@Override Line_Integer setKey(String newKey) {
		super.setKey(newKey);
		return this;
	}

	@Override Line_Integer setValue(Integer newValue) {
		super.setValue(newValue);
		return this;
	}

	@Override Line_Integer setComment(String newComment) {
		super.setComment(newComment);
		return this;
	}
	
	// --------------------------------------------------------------
	// Setters
	//

	// --------------------------------------------------------------
	// Getters
	//
	/**
	 * @return the value as {@code Boolean}
	 */
	Integer toInteger() {
		return getValue();
	}

}

