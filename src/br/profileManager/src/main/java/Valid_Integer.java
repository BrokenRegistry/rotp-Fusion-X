
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
 * For the validation of the {@code Integer}
 */
public class Valid_Integer extends Abstract_ValidData<Integer> {
	
	// ==================================================
    // Constructors
    //
	/**
	 * Base Constructor for Integer Validation Class
	 */
	public Valid_Integer() {
		super();
		init();
	}

	protected Valid_Integer(List<Integer> options) { 
		super(options);
		init();
	}
	
	private void initCriteria() {
		setValidationCriteria(new ValidationCriteria());
	}

	private void init() {
		initCriteria();
		// Some arbitrary values to simplify the code
		setLimits(0, 1000000);
		setDefaultRandomLimits(0, 101);
	}
	
	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code Integer} Validated Value
	 */
	@Override Integer entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return null;
			}
			return getHistoryCodeView(Default);
		}
		// Then Check if value is valid
		Integer value = PMutil.toInteger(userEntry);
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
	 * @return {@code Integer} OutputString
	 */
	@Override Integer randomWithoutParameters() {
		return PMutil.getRandom(getDefaultRandomLimits(0), getDefaultRandomLimits(1));
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Integer} OutputString
	 */
	@Override Integer randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override protected String toUserView(Integer codeView) {
		return PMutil.neverNull(codeView);
	}
	
	@Override protected Integer toCodeView(String userView) {
		return PMutil.toInteger(userView);
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
	 * @return {@code Integer} Random Value
	 */
	Integer randomWithLimit(String[] parameters) {
		int lim1 = getLimits(0);
		int lim2 = getLimits(1);
		int min = Math.min(lim1, lim2);
		int max = Math.max(lim1, lim2);
		// First Limit
		if (parameters.length >= 1) {
			if (PMutil.testForInteger(parameters[0])) {
				lim1 = PMutil.validateLimits(PMutil.toInteger(parameters[0]), min, max);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (PMutil.testForInteger(parameters[1])) {
				lim2 = PMutil.validateLimits(PMutil.toInteger(parameters[1]), min, max);
			} 
		}
		// get Random
		return PMutil.getRandom(lim1, lim2);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Integer} Random Value
	 */
	Integer randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Integer extends Generic_Line<Integer, Valid_Integer>{

	   	// --------------------------------------------------------------
	    // Constructors
	    //
		Line_Integer() {
			super(new Valid_Integer());
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_Integer(String line) {
			super(new Valid_Integer(), line);
		}
	}
}
