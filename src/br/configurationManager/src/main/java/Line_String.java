
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

	// ==================================================
    // Abstract Methods Declaration
    //
	/**
	 * ask for value as {@code ValueClass}
	 * @return the value
	 */
	@Override Entry_String stringToEntry(String entryValue) {
		return new Entry_String(entryValue);
	}

	@Override Entry_String valueToEntry(String entryValue) {
		return new Entry_String(entryValue);
	}

   	// --------------------------------------------------------------
    // Constructors
    //
	Line_String() {}

	/**
	 * @param line {@code String} new Setting Line from config file
	 */
	Line_String(String line) {
		super(line);
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

