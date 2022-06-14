
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

package br.profileManager.src.main.java;

import java.util.ArrayList;
import java.util.List;

import static br.profileManager.src.main.java.PMconfig.listSeparator;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * Common methods for validation of List of Parameters
 * @param <T>  the Base Type for Code View
 */
public class ValidationList<T> extends	Validation<T>{

    // ==================================================
    // Constructors and initializers
    //

    /**
	 * Base Generic Constructor for Validation of List of Parameters
	 * @param initialValue  Initial setting
     */
	public ValidationList(AbstractT<T> initialValue) {
		this(initialValue, null);
	}

	/**
	 * Generic Constructor for Validation, that set isEntryList
	 * @param initialValue  Initial setting
	 * @param options the list to initialize
	 */
	public ValidationList(AbstractT<T> initialValue, List<T> options) {
		super(initialValue, options, true);
		// TODO Auto-generated constructor stub
	}

	// ==================================================
    // Overriders
    //	
	/**
	 * Analyze user Entry content
	 * @return value
	 */
	@Override protected AbstractT<T> entryAnalysis(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		AbstractT<T> value;
		List<String> userViewList = new ArrayList<String>();
		List<T> codeViewList      = new ArrayList<T>();
		for (String element : userEntry.split(listSeparator())) {
			value = super.entryAnalysis(element); // Never null
			userViewList.add(value.userView());
			codeViewList.add(value.codeView());
			}
		value = newValue();
		value.userViewList(userViewList);
		value.codeViewList(codeViewList);
		return value;
	}

//	/**
//	 * Process non Random user entry
//	 * @return {@code Code View List<String>} Validated Value
//	 */
//	@Override AbstractT<T> entryValidation(String userEntry) {
//		userEntry = PMutil.clean(userEntry);
//		// First Check for blank values
//		if (userEntry.isBlank()) {
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		// Then Check if value is valid
//		for (String element : splitUserView(userEntry)) {
//			
//		}
//		Integer value = PMutil.toInteger(userEntry);
//		if (value == null) {
//			if (hasOptions() && isValidUserEntry(userEntry)) {
//				return getCodeView(userEntry);
//			}
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		return PMutil.validateLimits(value, getLimits(0), getLimits(1));

//		
//		// Then Check for a validation list
//		if (!hasOptions()) {
//			// No list = entry accepted
//			return userEntry;
//		}
//		// Then check validity
//		if (isValidUserEntry(userEntry)) {
//			return toCodeView(userEntry);
//		}
//		return getHistoryUserView(Default);
//
//		// TODO Auto-generated method stub
//		return null;
//	}

    //
	// ===== No Random Allowed Yet =====
	//
//	/**
//	 * Process Random with parameters
//	 * @param parameters {@code String[]} the extra parameters
//	 * @return {@code List<String>} OutputString
//	 */
//	@Override String randomWithLimit(String[] parameters) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * Process Random without parameters
//	 * @return {@code String} OutputString
//	 */
//	@Override String randomWithoutParameters() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	/**
//	 * Conversion from code view to user view
//	 * <br> For already validated Data
//	 * @param codeView {@code Code View} the value to convert
//	 * @return {@code String} the user view
//	 */
//	@Override String toUserView(List<String> codeView) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * Conversion from user view to code view.
//	 * <br> For already validated Data
//	 * @param codeView {@code String} the value to convert
//	 * @return {@code List<String>} the code view
//	 */
//	@Override List<String> toCodeView(String userView) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
