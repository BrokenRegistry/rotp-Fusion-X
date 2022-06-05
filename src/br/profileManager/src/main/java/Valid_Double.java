
/*
 * Licensed under the GNU General License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	 https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.profileManager.src.main.java;

import static br.profileManager.src.main.java.WriteUtil.History.Default;

import java.util.List;

/**
 * For the validation of the {@code Double}
 */
public class Valid_Double extends Abstract_ValidData<Double> {
	
	// ==================================================
    // Constructors
    //
	/**
	 * Base Constructor for Double Validation Class
	 */
	public Valid_Double() {
		super();
		init();
	}

	protected Valid_Double(List<Double> options) { 
		super(options);
		init();
	}
	
	private void initCriteria() {
		setValidationCriteria(new ValidationCriteria()
				.isNullAllowed(true));
	}

	private void init() {
		initCriteria();
		// Some arbitrary values to simplify the code
		setLimits(0.0, 1000000.0);
		setDefaultRandomLimits(0.0, 1.0);
	}
	
	// ==================================================
    // Overriders
    //
	
	@Override protected String toUserView(Double codeView) {
		return PMutil.neverNull(codeView);
	}
	
	@Override protected Double toCodeView(String userView) {
		return PMutil.toDouble(userView);
	}

	/**
	 * Process Random without user parameters
	 * @return {@code Double} OutputString
	 */
	@Override Double getRandom(Double lim1, Double lim2) {
		return PMutil.getRandom(lim1, lim2);
	}
	
	/**
	 * Validate the limits for the Value class type
	 * @param value the {@code Code View} Value to validate
	 * @param lim1  the {@code Code View} first limit
	 * @param lim2  the {@code Code View} second limit
	 * @return {@code Code View} Validated Value
	 */
	@Override Double validateLimits(Double value,
									Double lim1, Double lim2) {
		return PMutil.validateLimits(value, getLimits(0), getLimits(1));
	}

//	/**
//	 * Process non Random user entry
//	 * @return {@code Double} Validated Value
//	 */
//	@Override Double entryValidation(String userEntry) {
//		userEntry = PMutil.clean(userEntry);
//		// First Check for blank values
//		if (userEntry.isBlank()) {
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		// Then Check if value is valid
//		Double value = PMutil.toDouble(userEntry);
//		if (value == null) {
//			if (hasList() && isValidUserEntry(userEntry)) {
//				return getCodeView(userEntry);
//			}
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		return PMutil.validateLimits(value, getLimits(0), getLimits(1));
//	}
//	
//	/**
//	 * Process Random without parameters
//	 * @return {@code Double} OutputString
//	 */
//	@Override Double randomWithoutParameters() {
//		return PMutil.getRandom(getDefaultRandomLimits(0), getDefaultRandomLimits(1));
//	}
//	
//	/**
//	 * Process Random within Given Limits
//	 * @param parameters {@code String[]} the extra parameters
//	 * @return {@code Double} Random Value
//	 */
//	@Override Double randomWithLimit(String[] parameters) {
//		Double lim1 = getLimits(0);
//		Double lim2 = getLimits(1);
//		Double min = Math.min(lim1, lim2);
//		Double max = Math.max(lim1, lim2);
//		// First Limit
//		if (parameters.length >= 1) {
//			if (PMutil.testForNumeric(parameters[0])) {
//				lim1 = PMutil.validateLimits(PMutil.toDouble(parameters[0]), min, max);
//			} 
//		}
//		// Second Limit
//		if (parameters.length >= 2) {
//			if (PMutil.testForNumeric(parameters[1])) {
//				lim2 = PMutil.validateLimits(PMutil.toDouble(parameters[1]), min, max);
//			} 
//		}
//		// get Random
//		return PMutil.getRandom(lim1, lim2);
//	}
//	
//	@Override String toUserView(Double codeView) {
//		return PMutil.neverNull(codeView);
//	}
//	
//	@Override Double toCodeView(String userView) {
//		return PMutil.toDouble(userView);
//	}

	/**
	 * Generate option Range for limit and random
	 * @return the range
	 */
	@Override public String getOptionsRange() {
		return ("[Min=" + this.getLimits(0).toString()
				+ ", Max=" + this.getLimits(1).toString()
				+ ", Rnd Low=" + this.getDefaultRandomLimits(0).toString()
				+ ", Rnd Up=" + this.getDefaultRandomLimits(1).toString()
				+ "]");
	}

	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Double extends Generic_Line<Double, Valid_Double>{

	   	// --------------------------------------------------------------
	    // Constructors
	    //
		Line_Double() {
			super(new Valid_Double());
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_Double(String line) {
			super(new Valid_Double(), line);
		}
	}
}
