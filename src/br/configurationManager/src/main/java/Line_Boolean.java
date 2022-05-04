
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
class Line_Boolean extends Abstract_Line<Boolean>{

   	// --------------------------------------------------------------
    // Constructors
    //
	Line_Boolean() {}

	/**
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_Boolean(String line) {
		super(line);
	}

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * ask for value as {@code ValueClass}
	 * @return the value
	 */
	@Override Entry_Boolean stringToEntry(String entryValue) {
		return new Entry_Boolean(entryValue);
	}

	@Override Entry_Boolean valueToEntry(Boolean entryValue) {
		return new Entry_Boolean(entryValue);
	}

	// ==================================================
    // Overriders for chaining purpose
    //

	@Override Line_Boolean newLine(String line) {
		super.newLine(line);
		return this;
	}

	@Override Line_Boolean setKey(String newKey) {
		super.setKey(newKey);
		return this;
	}

	@Override Line_Boolean setValue(Boolean newValue) {
		super.setValue(newValue);
		return this;
	}

	@Override Line_Boolean setComment(String newComment) {
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
	Boolean toBoolean() {
		return getValue();
	}

}

