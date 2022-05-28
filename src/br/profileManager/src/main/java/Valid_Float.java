
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
 * For the validation of the {@code Float}
 */
public class Valid_Float extends Abstract_ValidData<Float> {
	
	// ==================================================
    // Constructors
    //
	
	/**
	 * Base Constructor for Float Validation Class
	 */
	public Valid_Float() {
		super();
		init();
	}

	protected Valid_Float(List<Float> options) { 
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
		setLimits(0.0f, 1000000.0f);
		setDefaultRandomLimits(0.0f, 1.0f);
	}
	
	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code Float} Validated Value
	 */
	@Override Float entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryCodeView(Default);
		}
		// Then Check if value is valid
		Float value = PMutil.toFloat(userEntry);
		if (value == null) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryCodeView(Default);
		}
		return PMutil.validateLimits(value, getLimits(0), getLimits(1));
	}
	
	/**
	 * Process Random without parameters
	 * @return {@code Float} OutputString
	 */
	@Override Float randomWithoutParameters() {
		return PMutil.getRandom(getDefaultRandomLimits(0), getDefaultRandomLimits(1));
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Float} OutputString
	 */
	@Override Float randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override String toUserView(Float codeView) {
		return PMutil.neverNull(codeView);
	}
	
	@Override Float toCodeView(String userView) {
		return PMutil.toFloat(userView);
	}

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

	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Float} Random Value
	 */
	Float randomWithLimit(String[] parameters) {
		Float lim1 = getLimits(0);
		Float lim2 = getLimits(1);
		Float min = Math.min(lim1, lim2);
		Float max = Math.max(lim1, lim2);
		// First Limit
		if (parameters.length >= 1) {
			if (PMutil.testForNumeric(parameters[0])) {
				lim1 = PMutil.validateLimits(PMutil.toFloat(parameters[0]), min, max);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (PMutil.testForNumeric(parameters[1])) {
				lim2 = PMutil.validateLimits(PMutil.toFloat(parameters[1]), min, max);
			} 
		}
		// get Random
		return PMutil.getRandom(lim1, lim2);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Float} Random Value
	 */
	Float randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Float extends Generic_Line<Float, Valid_Float>{

	   	// --------------------------------------------------------------
	    // Constructors
	    //
		Line_Float() {
			super(new Valid_Float());
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_Float(String line) {
			super(new Valid_Float(), line);
		}
	}
}
