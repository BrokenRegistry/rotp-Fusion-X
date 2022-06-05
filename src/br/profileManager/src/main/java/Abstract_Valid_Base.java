
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
 * List of Validation Element Management ... Base for data validation
 * @param <ValueClass> the Value's Code View Class
 */
public abstract class Abstract_Valid_Base<ValueClass> extends WriteUtil{
	
	private static final String RANDOM_ID = "RANDOM";
	private static final String PARAMETERS_SEPARATOR  = ",";
	private List<ValidationElement<ValueClass>> validationList =
			new ArrayList<ValidationElement<ValueClass>>();
	private ValidationCriteria criteria = new ValidationCriteria();
	private List<ValueClass> defaultRandomLimits = new ArrayList<ValueClass>();
	private List<ValueClass> limits = new ArrayList<ValueClass>();

	private String  defaultName     = "none";
	private boolean showWithOptions = false;
	private boolean showHistory     = true;
	private boolean showLocalEnable = true;

	private ValueClass blankValue = null;

	// ==================================================
    // Constructors and initializers
    //
	Abstract_Valid_Base() {}

	Abstract_Valid_Base(List<ValueClass> options) {
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
	 * Get the default limit value
	 * @return the default limit Code View
	 */
	abstract ValueClass getDefaultLimit();

	// ==================================================
    // Setters
    //
	/**
	 * @param blankValue the blankValue to set
	 */
	protected void setBlankValue(ValueClass blankValue) {
		this.blankValue = blankValue;
	}    

	/**
	 * Set the {@code ValidationCriteria} criteria
	 * @param newCriteria
	 */
	protected void setValidationCriteria(ValidationCriteria newCriteria) {
		criteria = newCriteria;
	}

	/**
	 * Set the showWithOptions parameter value
	 * @param newValue
	 */
	protected void setShowWithOptions(boolean newValue) {
		showWithOptions = newValue;
	}

	/**
	 * Set the showHistory parameter value
	 * @param newValue
	 */
	protected void setShowHistory(boolean newValue) {
		showHistory = newValue;
	}

	/**
	 * Set the showLocalEnable parameter value
	 * @param newValue
	 */
	protected void setShowLocalEnable(boolean newValue) {
		showLocalEnable = newValue;
	}

	/**
	 * Set the default parameter Name 
	 * @param newValue the new default Parameter
	 */
	protected void setDefaultName(String newName) {
		defaultName = newName;
	}

	/**
	 * Set the {@code Code View} limits
	 * @param newLimits
	 */
	protected void setLimits(List<ValueClass> newLimits) {
		limits = newLimits;
	}

	/**
	 * Set the {@code Code View} limits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setLimits(ValueClass Limit1, ValueClass Limit2) {
		limits = new ArrayList<ValueClass>();
		limits.add(Limit1);
		limits.add(Limit2);
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param newLimits
	 */
	protected void setDefaultRandomLimits(List<ValueClass> newLimits) {
		defaultRandomLimits = newLimits;
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setDefaultRandomLimits(ValueClass Limit1, ValueClass Limit2) {
		defaultRandomLimits = new ArrayList<ValueClass>();
		defaultRandomLimits.add(Limit1);
		defaultRandomLimits.add(Limit2);
	}

	// ==================================================
    // Getters
    //
	/**
	 * @return the blankValue
	 */
	protected ValueClass getBlankValue() {
		return blankValue;
	}

	/**
	 * Get the {@code Code View} validationList
	 * @return the validationList
	 */
	protected List<ValidationElement<ValueClass>> getValidationList() {
		return validationList;
	}

	/**
	 * Get the {@code ValidationCriteria}
	 * @return the criteria
	 */
	public ValidationCriteria getValidationCriteria() {
		return criteria;
	}

	/**
	 * Get the showWithOptions parameter value
	 * @return newValue
	 */
	protected boolean isShowWithOptions() {
		return showWithOptions;
	}

	/**
	 * Get the showHistory parameter value
	 * @return newValue
	 */
	protected boolean isShowHistory() {
		return showHistory;
	}

	/**
	 * Get the showLocalEnable parameter value
	 * @return newValue
	 */
	protected boolean isShowLocalEnabled() {
		return showLocalEnable;
	}

	/**
	 * Get the default parameter name
	 * @return the current value
	 */
	protected String getDefaultName() {
		return defaultName;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected List<ValueClass> getLimits() {
		return limits;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected ValueClass getLimits(int index) {
		if (limits.isEmpty()) {
			return getDefaultLimit();
		}
		if (index < 0) {
			return limits.get(0);
		}
		if (index > limits.size()-1) {
			return limits.get(limits.size()-1);
		}
		return limits.get(index);
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * @return the limits
	 */
	protected List<ValueClass> getDefaultRandomLimits() {
		return defaultRandomLimits;
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * index the limit index
	 * @return the limit
	 */
	protected ValueClass getDefaultRandomLimits(int index) {
		if (defaultRandomLimits.isEmpty()) {
			return getDefaultLimit();
		}
		if (index < 0) {
			return defaultRandomLimits.get(0);
		}
		if (index > defaultRandomLimits.size()-1) {
			return defaultRandomLimits.get(defaultRandomLimits.size()-1);
		}
		return defaultRandomLimits.get(index);
	}

	// ==================================================
    // Test Methods
    //
	/**
	 * @return <b>true</b> if the Validation List is not empty
	 */
	protected boolean hasList() {
		return validationList.size() > 0;
	}

	// ==================================================
    // Random Management Methods
    //
	// TODO Move up to Valid
	/**
	 * Test if user Entry is asking for a random parameter
	 * @param userEntry the {@code String} to analyze
	 * @return <b>true</b> if is random
	 */
	protected static boolean isRandom(String userEntry) {
		userEntry = PMutil.clean(userEntry).toUpperCase();
		if (userEntry.length() >= RANDOM_ID.length()) {
			return userEntry.substring(0, RANDOM_ID.length()).equals(RANDOM_ID); 
		}
		return false;
	}

	/**
	 * Check for extra parameter in Random request
	 * @param userEntry the {@code String} to analyze
	 * @return <b>true</b> if has extra parameters
	 */
	protected static boolean hasExtraParameters(String userEntry) {
		return !removeRandomId(userEntry).isBlank();
	}

	/**
	 * Remove the Random word and return the extra parameters
	 * @param userEntry the {@code String} to analyze
	 * @return the extra parameters
	 */
	protected static String removeRandomId(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		userEntry = userEntry.substring(RANDOM_ID.length()).strip();
		// Check for misplaced PARAMETERS_SEPARATOR
		if (!userEntry.isEmpty() &&
				userEntry.charAt(0) == PARAMETERS_SEPARATOR.charAt(0)) {
			userEntry = userEntry.substring(1).strip();
		}
		return userEntry;
	}

	/**
	 * Remove the Random word and return the extra parameters
	 * @param userEntry the {@code String} to analyze
	 * @return the extra parameters
	 */
	protected static String[] splitParameters(String parameters) {
		// parameters should already be tested
		return parameters.split(PARAMETERS_SEPARATOR);
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
		return getBlankValue();
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
		userView = PMutil.clean(userView);
		int index = 0;
		for (ValidationElement<ValueClass> element : validationList) {
			if (element.isValidUserEntry(userView, criteria)) {
				return index;
			}
			index++;
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
		index = Math.max(0, Math.min(validationList.size()-1, index));
		return validationList.get(index).getCodeView();
	}
	
	/**
	 *  @return The UserView from its index
	 */
	protected String getUserView(int index) {
		index = Math.max(0, Math.min(validationList.size()-1, index));
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
		return getBlankValue();
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
			if (criteria.isBlankAllowed() && codeView.toString().isBlank()) {
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
	 * Generate UserViewList as String
	 * @return UserView List in capitalized String
	 */
	public String getOptionsRange() {
		return PMutil.capitalize(getUserViewList().toString());
	}

	/**
	 * Generate String with all Options and their = description
	 * @return the String, never null
	 */
	public String getOptionsDescription() {
		String result = "";
		String line;
		for (ValidationElement<ValueClass> element : validationList) {
			line = element.toString();
			if (!line.isBlank()) {
				result += line + NL;
			}
		}
		return result;
	}
	
	// ------------------------------------------------
    // Validation List Setters & Getters
    //	
	protected void autoUpdateLimits() {
		setLimits(getCodeView(0), getCodeView(getValidationList().size()-1));
		setDefaultRandomLimits(getCodeView(0), getCodeView(getValidationList().size()-1));
	}

	protected void addElement(ValidationElement<ValueClass> element) {
		validationList.add(element);
		autoUpdateLimits();
	}
	
	protected void addElement(ValueClass codeView) {
		addElement(new ValidationElement<ValueClass>(codeView));
	}
	
	protected void addElement(ValueClass codeView, String userView) {
		addElement(new ValidationElement<ValueClass>(
									codeView, userView));
	}
	
	protected void addElement(ValueClass codeView, String userView, 
					String description, String category) {
		addElement(new ValidationElement<ValueClass>(
								codeView, userView, description, category));
	}
	
	protected void addElement(ValueClass codeView, String description, String category) {
		addElement(new ValidationElement<ValueClass>(
									codeView, description, category));
	}
	
	protected void setValidationList(List<ValidationElement<ValueClass>> list) {
		validationList = list;
		autoUpdateLimits();
	}
}
