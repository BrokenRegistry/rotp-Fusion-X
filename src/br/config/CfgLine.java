
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
	private CfgField leftSide  = new CfgField();
	private CfgField rightSide = new CfgField();
   	// --------------------------------------------------------------
    // Constructors
    //
	public CfgLine(String leftSide, String rightSide) {
		setLeftSide(leftSide);
		setRightSide(rightSide);
	}
	public CfgLine(CfgField leftSide, CfgField rightSide) {
		setLeftSide(leftSide);
		setRightSide(rightSide);
	}
	public CfgLine(CfgField leftSide, String rightSide) {
		setLeftSide(leftSide);
		setRightSide(rightSide);
	}
	public CfgLine(String leftSide, CfgField rightSide) {
		setLeftSide(leftSide);
		setRightSide(rightSide);
	}
	public CfgLine(String line) {
 		if (CfgField.isBlank(line)) {
 			return;
 			}
		List<String> list = Arrays.asList(line.split(KEY_VALUE_SEPARATOR));
		setLeftSide(list.get(0));
		if (list.size() > 1) { // in the case the rightSide contains KEY_VALUE_SEPARATOR
			setRightSide(String.join(KEY_VALUE_SEPARATOR, list.subList(1, list.size())));
		}
	}
	// --------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * set a new String value for the left side field
	 * Return current object to allow chaining
	 */
	private CfgLine setLeftSide(String newValue) {
		leftSide.set(newValue);
		return this;
	}
	/**
	 * set a new CfgField value for the left side field
	 * Return current object to allow chaining
	 */
	private CfgLine setLeftSide(CfgField newValue) {
		leftSide = newValue;
		return this;
	}
	/**
	 * set a new String value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setRightSide(String newValue) { 
		rightSide.set(newValue);
		return this;
	}
	/**
	 * set a new CfgField value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setRightSide(CfgField newValue) { 
		rightSide = newValue;
		return this;
	}
	/**
	 * set a new boolean value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setRightSide(boolean newValue) { 
		rightSide.set(newValue);
		return this;
	}
	/**
	 * set a new integer value for the right Side field
	 * Return current object to allow chaining
	 */
	public CfgLine setRightSide(int newValue) { 
		rightSide.set(newValue);
		return this;
	}
	/**
	 * Return the Right Side value as CfgField
	 */
	public CfgField rightSide() {
		return rightSide;
	}
	/**
	 * Return the left Side value as CfgField
	 */
	public CfgField leftSide() {
		return leftSide;
	}
	/**
	 * true if not null nor blank
	 */
	public boolean hasLeftSide() {
		return !leftSide.isBlank();
	}
	// ------------------------------------------------------------------------
	// Other Methods
	//
	/**
	 * return line as String, ready to be printed
	 */
	public String toPrint() {
		return String.format(KEY_FORMAT, leftSide.toString()) + rightSide.toCapitalized();
	}
}

