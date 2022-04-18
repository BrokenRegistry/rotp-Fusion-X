
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

package br.config.old;

public class CfgIntegerValidation {
//
//	private Integer max; 
//	private Integer min; 
//	private Cfg_Entry entry;
//	private boolean isInteger = false;
//	private boolean isValid   = false;
//	private Integer value;
//	private Integer closestValidValue;
//
//	// ==================================================
//    // Constructors
//    //
//	protected CfgIntegerValidation() {}
//	protected CfgIntegerValidation(int min, int max) {
//		setMinMax(min, max);
//	}
//    // ==================================================
//    // Setters
//    //
//	public CfgIntegerValidation setMax(int max) {
//		this.max = max;
//		testLimits();
//		return this;
//	}
//	public CfgIntegerValidation setMin(int min) {
//		this.min = min;
//		testLimits();
//		return this;
//	}
//	public CfgIntegerValidation setMinMax(int min, int max) {
//		this.min = min;
//		this.max = max;
//		testLimits();
//		return this;
//	}
//	public CfgIntegerValidation setEntry(Cfg_Entry entry) {
//		this.entry = entry;
//		testFullValidation();
//		return this;
//	}
//    // ==================================================
//    //  Getters
//    //
//	public Integer getMax() {
//		return max;
//	}
//	public Integer getMin() {
//		return min;
//	}
//	public Cfg_Entry getEntry() {
//		return entry;
//	}
//	public boolean isInteger() {
//		return isInteger;
//	}
//	/**
//	 * true if value is Integer between Max and Min 
//	 */	
//	public boolean isValid() {
//		return isValid;
//	}
//	/**
//	 * return the Integer value or null if not an Integer 
//	 */
//	public Integer getValue() {
//		return value;
//	}
//	/**
//	 * return the validated value or defaultValue if not valid 
//	 */
//	public Integer getOrDefaultValue(Integer defaultValue) {
//		if (isValid) {
//			return value;
//		}
//		return defaultValue;
//	}
//	/**
//	 * return the Integer getClosestValidValue or null if not an Integer 
//	 */
//	public Integer getClosestValidValue() {
//		return closestValidValue;
//	}
//	
//	// ==================================================
//    // Tests Methods
//    //
//	/**
//	 * Do all the tests 
//	 */
//	private void testFullValidation() {
//		isInteger = false;
//		value     = null;
//		if (entry != null && !entry.isBlank()) {
//			double d = Double.valueOf(entry.toString());
//			isInteger = d <= Integer.MAX_VALUE && d >= Integer.MIN_VALUE;
//			if (isInteger) {
//				value = Integer.valueOf(entry.toString());
//				testLimits();
//			}
//		}
//	}
//	private void testLimits() {
//		isValid = false;
//		closestValidValue = null;		
//		if (isInteger) {
//			closestValidValue = value;
//			if (max != null) {
//				closestValidValue = Math.min(max, closestValidValue);
//			}
//			if (min != null) {
//				closestValidValue = Math.max(min, closestValidValue);
//			}
//			isValid = closestValidValue == value;
//		}
//	}



}
