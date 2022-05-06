
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

package br.configurationManager.src.main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Common methods for data validation
 */
abstract class Abstract_ValidData<ValueClass> extends ToPrint{
	
	private List<ValidationElement> validationList = new ArrayList<ValidationElement>();
	private ValidationCriteria criteria = new ValidationCriteria();

	private ValueClass[] defaultRandomLimits = null;
	private ValueClass[] limits = null;
	private ValueClass defaultValue = null;
	private ValueClass initialValue = null;
	private ValueClass lastValue    = null;
	private ValueClass currentValue = null;

	// ==================================================
    // Abstract Methods Request
    //
	/**
	 * Process non Random user entry
	 * @return {@code ValueClass} Validated Value
	 */
	abstract ValueClass entryValidation(String userEntry);

	/**
	 * Process Random without parameters
	 * @return {@code ValueClass} OutputString
	 */
	abstract ValueClass randomWithoutParameters();

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code ValueClass} OutputString
	 */
	abstract ValueClass randomWithParameters(String[] parameters);

	/**
	 * Conversion from code view to user view
	 * <br> For already validated Data
	 * @param codeView {@code ValueClass} the value to convert
	 * @return {@code String} the user view
	 */
	abstract String toUserView(ValueClass codeView);
	
	/**
	 * Conversion from user view to code view.
	 * <br> For already validated Data
	 * @param codeView {@code String} the value to convert
	 * @return {@code ValueClass} the code view
	 */
	abstract ValueClass toCodeView(String userView);
	
	// ==================================================
    // Setters
    //
	/**
	 * Set the {@code ValueClass} limits
	 * @param newLimits
	 */
	void setLimits (ValueClass[] newLimits) {
		limits = newLimits;
	}

	/**
	 * Set the {@code ValueClass} defaultRandomLimits
	 * @param newLimits
	 */
	void setDefaultRandomLimits (ValueClass[] newLimits) {
		defaultRandomLimits = newLimits;
	}

	/**
	 * Set the {@code ValidationCriteria} criteria
	 * @param newCriteria
	 */
	void setValidationCriteria (ValidationCriteria newCriteria) {
		criteria = newCriteria;
	}

	/**
	 * Set the default value to be assigned
	 * @param newValue the new default Value
	 */
	void setDefaultValue (ValueClass newValue) {
		defaultValue = newValue;
	}

	/**
	 * Set the initial value at the loading
	 * @param newValue the new initial Value
	 */
	void setInitialValue (ValueClass newValue) {
		initialValue = newValue;
	}

	/**
	 * Set the last known value
	 * @param newValue the new last Value
	 */
	void setLastValue (ValueClass newValue) {
		lastValue = newValue;
	}

	/**
	 * Set the current value 
	 * @param newValue the new current Value
	 */
	void setCurrentValue (ValueClass newValue) {
		currentValue = newValue;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the {@code ValueClass} limits
	 * @return the limits
	 */
	ValueClass[] getLimits () {
		return limits;
	}

	/**
	 * Get the {@code ValueClass} defaultRandomLimits
	 * @return the limits
	 */
	ValueClass[] getDefaultRandomLimits () {
		return defaultRandomLimits;
	}

	/**
	 * Get the {@code ValidationCriteria} limits
	 * @return the limits
	 */
	ValidationCriteria getValidationCriteria () {
		return criteria;
	}

	/**
	 * Get the default value
	 * @return the default value
	 */
	ValueClass getDefaultValue () {
		return defaultValue;
	}

	/**
	 * Get the initial value
	 * @return the initial value
	 */
	ValueClass getInitialValue () {
		return initialValue;
	}

	/**
	 * Get the last value
	 * @return the last value
	 */
	ValueClass getLastValue () {
		return lastValue;
	}

	/**
	 * Get the current value
	 * @return the current value
	 */
	ValueClass getCurrentValue () {
		return currentValue;
	}

	// ==================================================
    // Test Methods
    //
	/**
	 * @return <b>true</b> if the Validation List is not empty
	 */
	boolean hasList() {
		return this.validationList.size() > 0;
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Test if the user entry is part of the validation list
	 * and return the category
	 * @param userEntry the value to test
	 * @return the category, "" if none
	 */
 	String getCategory(String userEntry) {
 		if (userEntry != null) {
 			for (ValidationElement element : validationList) {
 				if (element.isValidUserEntry(userEntry, criteria)) {
 					return element.getCategory();
 				}
 			}
 		}
		return "";
	}

 	/**
	 * Test if the user entry is part of the validation list
	 * and return the value
	 * @param userEntry the value to test
	 * @return the code view, "" if none
	 */
	String getCodeView(String userEntry) {
 		if (userEntry != null) {
			for (ValidationElement element : validationList) {
					if (element.isValidUserEntry(userEntry, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return "";
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return the user view
	 * @param codeView the code view to test
	 * @return the user view, "" if none
	 */
	String getUserView(String codeView) {
 		if (codeView != null) {
			for (ValidationElement element : validationList) {
					if (element.isValidCodeView(codeView, criteria)) {
					return element.getUserView();
				}
			}
 		}
		return "";
	}
	
	/**
	 * Test if the user view is part of the validation list
	 * and return its index, or the default one if none
	 * @param userView the {@code String} to search
	 * @param defaultIndex the {@code int} default returned value
	 * @return the {@code int} index
	 */
	int getUserViewIndex(String userView, int defaultIndex) {
 		if (userView != null) {
 			int index = 0;
			for (ValidationElement element : validationList) {
				if (element.isValidUserEntry(userView, criteria)) {
					return index;
				}
				index++;
			}
 		}
		return defaultIndex;
	}
	
	/**
	 * @return The last valid index
	 */
	int lastIndex() {
		return validationList.size()-1;
	}
	
	/**
	 * @return The list size
	 */
	int listSize() {
		return validationList.size();
	}
	
	/**
	 *  @return The CodeView from its index
	 */
	String getCodeView(int index) {
		return validationList.get(index).getCodeView();
	}
	
	/**
	 *  @return The UserView from its index
	 */
	String getUserView(int index) {
		return validationList.get(index).getUserView();
	}
	
	/**
	 * Test if the user view is part of the category list
	 * and return the code view
	 * @param userEntry the user view to check
	 * @param category the filter to apply
	 *  @return The CodeView, "" if none
	 */
	String getCodeView(String userEntry, String category) {
 		if (userEntry != null && userEntry != category) {
			for (ValidationElement element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return "";
	}
	
	/**
	 * Test if the user view is part of the category list
	 * @param userEntry the user view to check
	 */
	boolean isValidUserEntry(String userEntry) {
		if (userEntry != null) {
			for (ValidationElement element : validationList) {
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
	boolean isValidUserEntry(String userEntry, String category) {
		if (userEntry != null) {
			for (ValidationElement element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return UserViewList to String List
	 */
	List<String> getUserViewList() {
		List<String> result = new ArrayList<String>();
		for (ValidationElement element : validationList) {
			result.add(element.getUserView());
		}
		return result;
	}
	
	/**
	 * Generate UserViewList and convert it to capitalized String
	 * @return UserView List in capitalized String
	 */
	@Override
	public String toString() {
		return CMutil.capitalize(getUserViewList().toString());
	}

	/**
	 * @return String with formated list of key = description as comment
	 */
	String toPrint() {
		String result = "";
		for (ValidationElement line : validationList) {
			result += line.toPrint() + System.lineSeparator();
		}
		return result;
	}
	
	/**
	 * to String for selected Category:
	 * @param category the filtering Category
	 * @return string with formated list of key = description
	 */
	String toString(String category) {
		String result = "";
		for (ValidationElement element : validationList) {
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
	String toPrint(String category) {
		String result = "";
		for (ValidationElement element : validationList) {
			if (element.isMember(category, criteria)) {
				result += element.toPrint() + System.lineSeparator();
			}
		}
		return result;
	}
	
	// ------------------------------------------------
    // Validation List Setters & Getters
    //	
	void addElement(String userView, String codeView, 
					String description, String category) {
		validationList.add(new ValidationElement(userView, codeView, 
												 description, category));
	}
	
	void addElement(String codeView, String description, String category) {
		validationList.add(new ValidationElement(codeView, description, category));
	}
	
	void addElement(String codeView, String description) {
		validationList.add(new ValidationElement(codeView, description));
	}
	
	void addElement(String codeView) {
		validationList.add(new ValidationElement(codeView));
	}
	
	void setValidationList(List<ValidationElement> list) {
		validationList = list;
	}    
}
