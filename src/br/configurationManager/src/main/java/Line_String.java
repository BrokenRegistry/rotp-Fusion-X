
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
class Line_String extends Abstract_Line<String>{

   	// --------------------------------------------------------------
    // Constructors
    //
	Line_String() {
		super(new Valid_String());
		setValue("");
	}

	/**
	 * @param validationData {@code Valid_String} validation parameters
	 */
	Line_String(Valid_String validationData) {
		super(validationData);
		setValue("");
	}

	/**
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_String(String line) {
		super(new Valid_String(), line);
	}
	
	/**
	 * @param validationData {@code Valid_String} validation parameters
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_String(Valid_String validationData, String line) {
		super(validationData, line);
	}

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * Value Initialization with Validation Parameters 
	 * @return the value
	 */
	@Override EntryValid_String InitValidationData(
			Abstract_ValidData<String> validationData) {
		return new EntryValid_String(validationData);
	}

	// ==================================================
    // Overriders for chaining purpose
    //

	@Override Line_String newLine(String line) {
		super.newLine(line);
		return this;
	}

	@Override Line_String setKey(String newKey) {
		super.setKey(newKey);
		return this;
	}

	@Override Line_String setValue(String newValue) {
		super.setValue(newValue);
		return this;
	}

	@Override Line_String setComment(String newComment) {
		super.setComment(newComment);
		return this;
	}
	
	// --------------------------------------------------------------
	// Setters
	//



	// --------------------------------------------------------------
	// Getters
	//
}

