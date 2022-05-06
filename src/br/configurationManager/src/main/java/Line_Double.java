
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
class Line_Double extends Generic_Line<Double>{

   	// --------------------------------------------------------------
    // Constructors
    //
	Line_Double() {
		super(new Valid_Double());
	}
	
	/**
	 * @param validationData {@code Valid_Double} validation parameters
	 */
	Line_Double(Abstract_ValidData<Double> validationData) {
		super(validationData);
	}

	/**
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Double(String line) {
		super(new Valid_Double(), line);
	}

	/**
	 * @param validationData {@code Valid_Double} validation parameters
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Double(Abstract_ValidData<Double> validationData, String line) {
		super(validationData, line);
	}

	/**
	 * @param validationData {@code Valid_Double} value validation parameters
	 * @param validationKey {@code Valid_String} key validation parameters
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Double(
			Abstract_ValidData<Double> validationData,
			Abstract_ValidData<String> validationKey,
			String line) {
		super(validationData, validationKey, line);
	}

	// ==================================================
    // Overriders for chaining purpose
    //	
	@Override protected Generic_Line<Double> clone() {
		return new Line_Double(
				getValueAsEntry().getValidationData(),
				getKeyAsEntry().getValidationData(),
				this.toString());
	}

	@Override Generic_Line<Double> clone(String newKey) {
		return new Line_Double(
				getValueAsEntry().getValidationData(),
				getKeyAsEntry().getValidationData(),
				newKey);		
	}

	@Override Line_Double newLine(String line) {
		super.newLine(line);
		return this;
	}

	@Override Line_Double setKey(String newKey) {
		super.setKey(newKey);
		return this;
	}

	@Override Line_Double setValue(Double newValue) {
		super.setValue(newValue);
		return this;
	}

	@Override Line_Double setComment(String newComment) {
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
	Double toDouble() {
		return getValue();
	}
}

