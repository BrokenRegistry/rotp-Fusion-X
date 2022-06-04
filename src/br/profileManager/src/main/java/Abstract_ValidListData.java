
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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import static br.profileManager.src.main.java.WriteUtil.History.*;

/**
 * Common methods for data validation of List of Values
 * @param <ValueClass> the Value's Base Code View Class
 * @param <ListClass>  the Value's List Code View Class
 */
public abstract class Abstract_ValidListData<ValueClass, ListClass extends List<ValueClass>> 
						extends Abstract_Valid_Base<ValueClass>{
	
	private Map<History, ListClass> historyMap = new EnumMap<>(History.class);
	
    // ==================================================
    // Constructors and initializers
    //
	Abstract_ValidListData() {}

	Abstract_ValidListData(List<ValueClass> options) {
		initList(options);
	}
	
	private void initList(List<ValueClass> options) {
		if (options == null) {
			return;
		}
		for (ValueClass option : options) {
			this.addElement(option,
					PMutil.suggestedUserViewFromCodeView(option));
		}
	}
	
	// ==================================================
    // Abstract Methods Request
    //
	/**
	 * Process Random without parameters
	 * @return {@code Code View} OutputString
	 */
	abstract ValueClass randomWithoutParameters();

	/**
	 * Conversion from code view to user view
	 * <br> For already validated Data
	 * @param codeView {@code Code View} the value to convert
	 * @return {@code String} the user view
	 */
	abstract String toUserView(ListClass codeView);
	
	/**
	 * Conversion from user view to code view.
	 * <br> For already validated Data
	 * @param codeView {@code String} the value to convert
	 * @return {@code Code View} the code view
	 */
	abstract ListClass toCodeView(String userView);

	// ------------------------------------------------
    // Overriders
    //	
	/**
	 * Get the default limit value
	 * @return the default limit Code View
	 */
	@Override ValueClass getDefaultLimit() {
		return getHistoryCodeView(Default).get(0);
	}

	// ==================================================
    // Setters
    //
	/**
	 * Set the "history" Code View
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */

	protected void setHistoryCodeView(History history, ListClass newValue) {
		if (history == Last) { // if in two step to allow breakpoint
			if (!PMutil.neverNull(historyMap.get(Last)).isBlank()) {
				return; // Last was already assigned	
			}
		}
		historyMap.put(history, newValue);
		if (history == Initial) {
			historyMap.put(Current, newValue);
		}
	}

	/**
	 * Set the "history" User View
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */
	protected void setHistoryUserView(History history, String newValue) {
		setHistoryCodeView(history, toCodeView(newValue));
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the "history" Code View
	 * @param history  Field to be retrieved
	 * @return the "history" Code View
	 */
	protected ListClass getHistoryCodeView(History history) {
		return historyMap.get(history);
	}

	/**
	 * Get the "history" User View
	 * @param history  Field to be retrieved
	 * @return the "history" Code View
	 */
	protected String getHistoryUserView(History history) {
		return toUserView(historyMap.get(history));
	}
}
