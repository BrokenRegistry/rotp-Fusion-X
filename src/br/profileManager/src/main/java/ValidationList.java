
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

}
