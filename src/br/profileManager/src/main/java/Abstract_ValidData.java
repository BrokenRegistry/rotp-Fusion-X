
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

/**
 * Common methods for data validation
 * @param <ValueClass> the Value's Code View Class
 */
public abstract class Abstract_ValidData<ValueClass> extends ToPrint{
	
	private List<ValidationElement<ValueClass>> 
			validationList = new ArrayList<ValidationElement<ValueClass>>();
	private ValidationCriteria criteria = new ValidationCriteria();

	private ValueClass[] defaultRandomLimits = null;
	private ValueClass[] limits     = null;
	private ValueClass defaultValue = null;
	private ValueClass initialValue = null;
	private ValueClass lastValue    = null;
	private ValueClass gameValue    = null;
	private ValueClass currentValue = null;
	private ValueClass blankValue   = null;
	private String     defaultName  = "none";

    // ==================================================
    // Constructors and initializers
    //
	Abstract_ValidData() {}

	Abstract_ValidData(List<ValueClass> options) {
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
	 * Process non Random user entry
	 * @return {@code Code View} Validated Value
	 */
	abstract ValueClass entryValidation(String userEntry);

	/**
	 * Process Random without parameters
	 * @return {@code Code View} OutputString
	 */
	abstract ValueClass randomWithoutParameters();

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Code View} OutputString
	 */
	abstract ValueClass randomWithParameters(String[] parameters);

	/**
	 * Conversion from code view to user view
	 * <br> For already validated Data
	 * @param codeView {@code Code View} the value to convert
	 * @return {@code String} the user view
	 */
	abstract String toUserView(ValueClass codeView);
	
	/**
	 * Conversion from user view to code view.
	 * <br> For already validated Data
	 * @param codeView {@code String} the value to convert
	 * @return {@code Code View} the code view
	 */
	abstract ValueClass toCodeView(String userView);
	
	// ==================================================
    // Setters
    //
	/**
	 * Set the {@code Code View} limits
	 * @param newLimits
	 */
	protected void setLimits (ValueClass[] newLimits) {
		limits = newLimits;
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param newLimits
	 */
	protected void setDefaultRandomLimits (ValueClass[] newLimits) {
		defaultRandomLimits = newLimits;
	}

	/**
	 * Set the {@code ValidationCriteria} criteria
	 * @param newCriteria
	 */
	protected void setValidationCriteria (ValidationCriteria newCriteria) {
		criteria = newCriteria;
	}

	/**
	 * Set the default {@code Code View} value to be assigned
	 * @param newValue the new default Value
	 */
	protected void setDefaultValue (ValueClass newValue) {
		defaultValue = newValue;
	}

	/**
	 * Set the initial {@code Code View} value at the loading
	 * @param newValue the new initial Value
	 */
	protected void setInitialValue (ValueClass newValue) {
		initialValue = newValue;
	}

	/**
	 * Set the last known {@code Code View} value
	 * @param newValue the new last Value
	 */
	protected void setLastValue (ValueClass newValue) {
		lastValue = newValue;
		currentValue = newValue;
	}

	/**
	 * Set the last game {@code Code View} value (at game load)
	 * @param newValue the new game Value
	 */
	protected void setGameValue (ValueClass newValue) {
		gameValue = newValue;
	}

	/**
	 * Set the current {@code Code View} value 
	 * @param newValue the new current Value
	 */
	protected void setCurrentValue (ValueClass newValue) {
		currentValue = newValue;
	}

	/**
	 * Set the blank {@code Code View} value 
	 * @param newValue the new blank Value
	 */
	protected void setBlankValue (ValueClass newValue) {
		blankValue = newValue;
	}

	/**
	 * Set the default parameter Name 
	 * @param newValue the new default Parameter
	 */
	protected void setDefaultName (String newName) {
		defaultName = newName;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected ValueClass[] getLimits () {
		return limits;
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * @return the limits
	 */
	protected ValueClass[] getDefaultRandomLimits () {
		return defaultRandomLimits;
	}

	/**
	 * Get the {@code ValidationCriteria} limits
	 * @return the limits
	 */
	protected ValidationCriteria getValidationCriteria () {
		return criteria;
	}

	/**
	 * Get the default {@code Code View} value
	 * @return the default value
	 */
	protected ValueClass getDefaultValue () {
		return defaultValue;
	}

	/**
	 * Get the initial {@code Code View} value
	 * @return the initial value
	 */
	protected ValueClass getInitialValue () {
		return initialValue;
	}

	/**
	 * Get the last {@code Code View} value
	 * @return the last value
	 */
	protected ValueClass getLastValue () {
		return lastValue;
	}

	/**
	 * Get the last game loaded {@code Code View} value
	 * @return the game value
	 */
	protected ValueClass getGameValue () {
		return gameValue;
	}

	/**
	 * Get the current {@code Code View} value
	 * @return the current value
	 */
	protected ValueClass getCurrentValue () {
		return currentValue;
	}

	/**
	 * Get the blank {@code Code View} value
	 * @return the current value
	 */
	protected ValueClass getBlankValue () {
		return blankValue;
	}

	/**
	 * Get the default parameter name
	 * @return the current value
	 */
	protected String getDefaultName () {
		return defaultName;
	}

	// ==================================================
    // Test Methods
    //
	/**
	 * @return <b>true</b> if the Validation List is not empty
	 */
	protected boolean hasList() {
		return this.validationList.size() > 0;
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Test if the user entry is part of the validation list
	 * and return the category
	 * @param userEntry the {@code User View} value to test
	 * @return the category, "" if none
	 */
 	protected String getCategory(String userEntry) {
 		if (userEntry != null) {
 			for (ValidationElement<ValueClass> element : validationList) {
 				if (element.isValidUserEntry(userEntry, criteria)) {
 					return element.getCategory();
 				}
 			}
 		}
		return "";
	}

 	/**
	 * Test if the user entry is part of the validation list
	 * and return the {@code Code View} value as {@code String}
	 * @param userEntry the value to test
	 * @return the code view, "" if none
	 */
 	protected ValueClass getCodeView(String userEntry) {
 		if (userEntry != null) {
			for (ValidationElement<ValueClass> element : validationList) {
					if (element.isValidUserEntry(userEntry, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return blankValue;
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return the user view
	 * @param codeView the code view to test
	 * @return the user view, "" if none
	 */
	protected String getUserView(ValueClass codeView) {
		if (codeView == null) {
			return "";
		}
		for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, criteria)) {
				return element.getUserView();
			}
		}
		return codeView.toString(); // Not in the list
	}
	
	/**
	 * Test if the user view is part of the validation list
	 * and return its index, or the default one if none
	 * @param userView the {@code String} to search
	 * @param defaultIndex the {@code int} default returned value
	 * @return the {@code int} index
	 */
	protected int getUserViewIndex(String userView, int defaultIndex) {
 		if (userView != null) {
 			int index = 0;
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userView, criteria)) {
					return index;
				}
				index++;
			}
 		}
		return defaultIndex;
	}
	
	/**
	 * @return The list size
	 */
	protected int listSize() {
		return validationList.size();
	}
	
	/**
	 *  @return The CodeView from its index
	 */
	protected ValueClass getCodeView(int index) {
		return validationList.get(index).getCodeView();
	}
	
	/**
	 *  @return The UserView from its index
	 */
	protected String getUserView(int index) {
		return validationList.get(index).getUserView();
	}
	
	/**
	 * Test if the user view is part of the category list
	 * and return the code view
	 * @param userEntry the user view to check
	 * @param category the filter to apply
	 *  @return The CodeView, "" if none
	 */
	protected ValueClass getCodeView(String userEntry, String category) {
 		if (userEntry != null && userEntry != category) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return blankValue;
	}
	
	/**
	 * Test if the user view is part of the category list
	 * @param userEntry the user view to check
	 */
	protected boolean isValidUserEntry(String userEntry) {
		if (userEntry != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is part of the category validation list
	 * @param userEntry the user view to check
	 * @param category the filter to apply
	 */
	protected boolean isValidUserEntry(String userEntry, String category) {
		if (userEntry != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is part of the category validation list
	 * @param codeView the user view to check
	 * @param category the filter to apply
	 */
	protected boolean isValidCodeView(ValueClass codeView, String category) {
		if (codeView != null) {
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the user view is valid
	 * @param codeView the user view to check
	 */
	protected boolean isValidCodeView(ValueClass codeView) {
		if (codeView != null) {
			if (criteria.isBlankAllowed && codeView.toString().isBlank()) {
				return true;
			}
			for (ValidationElement<ValueClass> element : validationList) {
				if (element.isValidCodeView(codeView, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return UserViewList to String List
	 */
	protected List<String> getUserViewList() {
		List<String> result = new ArrayList<String>();
		for (ValidationElement<ValueClass> element : validationList) {
			result.add(element.getUserView());
		}
		return result;
	}
	
	/**
	 * Generate UserViewList and convert it to capitalized String
	 * @return UserView List in capitalized String
	 */
	@Override public String toString() {
		return PMutil.capitalize(getUserViewList().toString());
	}

	/**
	 * @return String with formated list of key = description as comment
	 */
	@Override protected String toPrint() {
		String result = "";
		for (ValidationElement<ValueClass> line : validationList) {
			result += line.toPrint() + System.lineSeparator();
		}
		return result;
	}
	
	/**
	 * to String for selected Category:
	 * @param category the filtering Category
	 * @return string with formated list of key = description
	 */
	protected String toString(String category) {
		String result = "";
		for (ValidationElement<ValueClass> element : validationList) {
			if (element.isMember(category, criteria)) {
				result += element.toString() + System.lineSeparator();
			}
		}
		return result;
	}
	
	/**
	 * For selected Category:
	 * @return string with formated list of key = description as comment
	 */
	protected String toPrint(String category) {
		String result = "";
		for (ValidationElement<ValueClass> element : validationList) {
			if (element.isMember(category, criteria)) {
				result += element.toPrint() + System.lineSeparator();
			}
		}
		return result;
	}
	
	// ------------------------------------------------
    // Validation List Setters & Getters
    //	
	protected void addElement(ValueClass codeView) {
		validationList.add(new ValidationElement<ValueClass>(codeView));
	}
	
	protected void addElement(ValueClass codeView, String userView) {
		validationList.add(new ValidationElement<ValueClass>(
									codeView, userView));
	}
	
	protected void addElement(ValueClass codeView, String userView, 
					String description, String category) {
		validationList.add(new ValidationElement<ValueClass>(
								codeView, userView, description, category));
	}
	
	protected void addElement(ValueClass codeView, String description, String category) {
		validationList.add(new ValidationElement<ValueClass>(
									codeView, description, category));
	}
	
	protected void setValidationList(List<ValidationElement<ValueClass>> list) {
		validationList = list;
	}    
}
