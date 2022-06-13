
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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static br.profileManager.src.main.java.WriteUtil.History.*;
import static br.profileManager.src.main.java.PMconfig.randomId;
import static br.profileManager.src.main.java.PMconfig.parametersSeparator;
import static br.profileManager.src.main.java.PMconfig.listSeparator;

/**
 * Common methods for data validation
 * @param <T>  the Base Type for Code View
 */
public class Validation<T> extends WriteUtil{

	private final boolean isEntryList;
	private final AbstractT<T> factory;
	private final boolean isString;
	private final boolean isBoolean;

	private Map<History, AbstractT<T>> historyMap = new EnumMap<>(History.class);
	private T blankCodeView = null;

	private List<Options<T>> validationList =
			new ArrayList<Options<T>>();
	private ValidationCriteria criteria = new ValidationCriteria();
	private List<T> defaultRandomLimits = new ArrayList<T>();
	private List<T> limits = new ArrayList<T>();

	private String  defaultName     = "none";
	private boolean showWithOptions = false;
	private boolean showHistory     = true;
	private boolean showLocalEnable = true;

    // ==================================================
    // Constructors and initializers
    //
	/**
	 * Base Constructor for Abstract_U<T> Validation,
	 * is not an entry list
	 * @param initialValue 
	 */
	public Validation(AbstractT<T> initialValue) {
		this(initialValue, null, false);
	}
	/**
	 * Constructor for Abstract_U<T> Validation, that set isEntryList
	 * @param initialValue Initial setting
	 * @param isEntryList true if list of entry expected
	 */
	public Validation(AbstractT<T> initialValue, boolean isEntryList) {
		this(initialValue, null, isEntryList);
	}
	/**
	 * Constructor for Abstract_U<T> Validation with list initialization,
	 * is not an entry list
	 * @param initialValue  Initial setting
	 * @param options the list to initialize
	 */
	public Validation(AbstractT<T> initialValue, List<T> options) {
		this(initialValue, options, false);
	}
	/**
	 * Constructor for Abstract_U<T> Validation with list initialization
	 * @param initialValue  Initial setting
	 * @param options the list to initialize
	 * @param isEntryList true if list of entry expected
	 */
	public Validation(AbstractT<T> initialValue, List<T> options, boolean isEntryList) {
		factory   = initialValue.New();
		isString  = initialValue.codeView() instanceof String;
		isBoolean = initialValue.codeView() instanceof Boolean;
		setBlankCodeView(initialValue.blankCodeView());
		setHistory(Initial, initialValue.clone());
		setHistory(Default, initialValue.clone()); // better not be null!
		this.isEntryList = isEntryList;
		initOptions(options);
	}
	
	private void initOptions(List<T> options) {
		initEntryList();
		if (options == null) {
			return;
		}
		for (T option : options) {
			this.addElement(option,
					PMutil.suggestedUserViewFromCodeView(option));
		}
	}
	
	protected void initEntryList() {
		if (isEntryList) {
			this.getValidationCriteria().isBlankAllowed(false);
		}
	}
	
	private AbstractT<T> clone(AbstractT<T> t) {
		if (t == null) {
			return t;
			}
		return t.clone();
	}
	// ------------------------------------------------
    // Former Overriders
    //	
	/**
	 * Get the default limit value
	 * @return the default limit Code View, never <b>null</b>
	 */
	T getDefaultLimit() {
		return getHistory(Default).codeView();
	}

	/**
	 * @return the blankCodeView
	 */
	protected T getBlankCodeView() {
		return blankCodeView;
	}

	/**
	 * @param blankCodeView the new Code View to set
	 */
	protected void setBlankCodeView(T blankCodeView) {
		this.blankCodeView = blankCodeView;
	}    

	// ==================================================
    // Setters
    //
	/**
	 * Set the "history" Value
	 * @param history  Field to be filled
	 * @param newValue the new "history" Value
	 */
	protected void setHistory(History history, AbstractT<T> newValue) {
		if (history == Last) { // if in two step to allow breakpoint
			if (historyMap.get(Last) != null) {
				return; // Last was already assigned	
			}
		}
		historyMap.put(history, clone(newValue));
		if (history == Initial) {
			historyMap.put(Current, clone(newValue));
		}
	}

	/**
	 * Copy one History to another
	 * @param history   The History case to fill
	 * @param source    The History to copy
	 */
	protected void setHistory(History history, History source) {
		setHistory(history, getHistory(source));
	}

	/**
	 * Set the "history" User View
	 * @param history  Field to be filled
	 * @param userView the new "history" Value
	 */
	protected void setHistory(History history, String userView) {
		AbstractT<T> value = newValue(getCodeView(userView));
		setHistory(history, value);
	}

	/**
	 * Set the "history" Code View
	 * @param history  Field to be filled
	 * @param codeView the new "history" codeView
	 */
	protected void setHistoryCodeView(History history, T codeView) {
		AbstractT<T> value = newValue(codeView);
		setHistory(history, value);
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the "history" Value
	 * @param history  Field to be retrieved
	 * @return the "history" Value
	 */
	protected AbstractT<T> getHistory(History history) {
		AbstractT<T> out = historyMap.get(history);
		if (out == null) {
			return newValue();
		}
		return out.clone();
	}

	/**
	 * Check if the "history" Exist
	 * @param history  Field to be retrieved
	 * @return true if null
	 */
	protected boolean historyIsNull(History history) {
		return historyMap.get(history) == null;
	}

	/**
	 * Get the code view from User View then set the value
	 * @param userView  the user entry
	 * @return the validated Value
	 */
	protected AbstractT<T> getValue(String userView) {
		AbstractT<T> value = newValue();
		if (userView == null) {
			return value;
		}
		value.userView(userView);
		value.codeView(getCodeView(userView));
		return value;
	}

	/**
	 * Factory create a new empty Abstract_T<T>
	 * @return the new Abstract_T<T>
	 */
	protected AbstractT<T> newValue() {
		return factory.New(getBlankCodeView());
	}

	/**
	 * Factory create a new Abstract_T<T> and set code View
	 * @param codeView  the value
	 * @return the new Abstract_T<T>
	 */
	public AbstractT<T> newValue(T codeView) {
		return factory.New(codeView).userView(getUserView(codeView));
	}

	// ==================================================
    // Random Management Methods
    //
	/**
	 * Process Random without parameters
	 * @return {@code Integer} OutputString
	 */
	protected AbstractT<T> getRandom(T lim1, T lim2) {
		T codeView = factory.random(lim1, lim2);
		return factory.New(codeView);
	}

	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return Random Value
	 */
	protected AbstractT<T> randomWithInListLimit(String[] parameters) {
		int min = 0;
		int max = listSize();
		// First Limit
		if (parameters.length >= 1) {
			min = getUserViewIndex(parameters[0], min);
		}
		// Second Limit
		if (parameters.length >= 2) {
			max = getUserViewIndex(parameters[1], max);
		}
		// get Random
		int id = PMutil.getRandom(min, max);
		return factory.New(getCodeView(id));
	}

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return Random Value
	 */
	AbstractT<T> randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		if (hasList() && isValidUserEntry(parameters[0])) {
			return randomWithInListLimit(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return Random Value
	 */
	AbstractT<T> randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

	/**
	 * Test if user Entry is asking for a random parameter
	 * @param userEntry the {@code String} to analyze
	 * @return <b>true</b> if is random
	 */
	protected static boolean isRandom(String userEntry) {
		userEntry = PMutil.clean(userEntry).toUpperCase();
		if (userEntry.length() >= randomId().length()) {
			return userEntry.substring(0, randomId().length()).equals(randomId()); 
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
		userEntry = userEntry.substring(randomId().length()).strip();
		// Check for misplaced parametersSeparator()
		if (!userEntry.isEmpty() &&
				userEntry.charAt(0) == parametersSeparator().charAt(0)) {
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
		return parameters.split(parametersSeparator());
	}

	/**
	 * Process non Random user entry
	 * @return {@code Code View} Validated Value
	 */
	@SuppressWarnings("unchecked")
	AbstractT<T> entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed()) {
				return newValue(getBlankCodeView());
			}
			return getHistory(Default);
		}
		// Then Check check if part of the list 
		if (hasList()) {
			if (isValidUserEntry(userEntry)) {
				return getValue(userEntry);
			} 
			// Bad entry, then either blank or default
			else if (getValidationCriteria().isBlankAllowed()) {
				return newValue(getBlankCodeView());
			}
			else {
				return getHistory(Default);
			}
			// end of hasList
		}
		// Not on the list! check for String entry
		if (isString) {
			return newValue((T)userEntry);
		}
		// Then Check if value is valid
		T codeView = factory.toCodeView(userEntry); // Raw conversion
		if (codeView != null) {
			AbstractT<T> value = newValue(codeView);
			// Check for limit before returning the value
			value.validBound(getLimits(0), getLimits(1));
			return value;
		}
		// No list! No codeView, Not a String, then either blank or default
		if (getValidationCriteria().isBlankAllowed()) {
			return newValue();
		}
		else {
			return getHistory(Default);
		}
	}

	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Code View} OutputString
	 */
	AbstractT<T> randomWithLimit(String[] parameters) {
		T lim1 = getLimits(0);
		T lim2 = getLimits(1);
		if (parameters.length >= 1) {
			T codeView = factory.toCodeView(parameters[0]);
			AbstractT<T> value = factory.New(codeView);
			if (codeView != null) {
				lim1 = value.validBound(codeView, lim1, lim2);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			T codeView = factory.toCodeView(parameters[1]);
			AbstractT<T> value = newValue(codeView);
			if (codeView != null) {
				lim2 = value.validBound(codeView, getLimits(0), lim2);
			} 
		}
		// get Random
		return getRandom(lim1, lim2);
	}

	/**
	 * Process Random without parameters
	 * @return {@code Code View} Output Value
	 */
	AbstractT<T> randomWithoutParameters() {
		if (hasList()) {
			int id = PMutil.getRandom(
					getCodeViewIndex(getDefaultRandomLimits(0), 0),
					getCodeViewIndex(getDefaultRandomLimits(1), listSize()));
			return newValue(getCodeView(id));

		}
		return getRandom(getDefaultRandomLimits(0), getDefaultRandomLimits(1));
	}

	// ==================================================
    // Other Methods
    //
	/**
	 * Analyze user Entry content
	 * @return 
	 */
	protected AbstractT<T> baseAnalysis(String userEntry) {
		AbstractT<T> out = newValue();
		// Random Management
		if ( getValidationCriteria().isRandomAllowed()
				&& isRandom(userEntry)) {
			if (hasExtraParameters(userEntry)) {
				out = randomWithParameters(
						splitParameters(removeRandomId(userEntry)));
				out.userView(userEntry);
				return out;
			}
			out = randomWithoutParameters();
			out.userView(userEntry);
			return out;
		}
		// Not Random
		out = entryValidation(userEntry);
		return out;
	}
	/**
	 * Analyze user Entry content
	 * @return value
	 */
	protected AbstractT<T> entryAnalysis(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// Not a list
		if (!isEntryList) {
			return baseAnalysis(userEntry);
		}
		// Is a list: Then split it to elements and manage them
		AbstractT<T> element;
		List<AbstractT<T>> valueList = new ArrayList<AbstractT<T>>();
		for (String baseEntry : userEntry.split(listSeparator())) {
			element = baseAnalysis(baseEntry);
			if (element.codeView() != null
					&& !element.isBlank()) {
				valueList.add(element);
			}
		}
		AbstractT<T> out = newValue();
		out.valueList(valueList); ;
		return out;
	}

	/**
	 * Convert the user entry in a more useful format
	 * @param userEntry  The {@code String} to convert
	 * @return {@code String[]} from user entry list
	 */
	public String[] getArrayUserViewArray (String userEntry) {
		if (userEntry == null) {
			return null;
		}
		return userEntry.split(listSeparator());
	}
	
	// ==================================================
    // FORMER ABSTRACT_OPTION --- INTEGRATED
    //
	// ==================================================
    // Setters
    //
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
	protected void setLimits(List<T> newLimits) {
		limits = newLimits;
	}

	/**
	 * Set the {@code Code View} limits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setLimits(T Limit1, T Limit2) {
		limits = new ArrayList<T>();
		limits.add(Limit1);
		limits.add(Limit2);
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param newLimits
	 */
	protected void setDefaultRandomLimits(List<T> newLimits) {
		defaultRandomLimits = newLimits;
	}

	/**
	 * Set the {@code Code View} defaultRandomLimits
	 * @param Limit1
	 * @param Limit2
	 */
	protected void setDefaultRandomLimits(T Limit1, T Limit2) {
		defaultRandomLimits = new ArrayList<T>();
		defaultRandomLimits.add(Limit1);
		defaultRandomLimits.add(Limit2);
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the {@code Code View} validationList
	 * @return the validationList
	 */
	protected List<Options<T>> getValidationList() {
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
	protected List<T> getLimits() {
		return limits;
	}

	/**
	 * Get the {@code Code View} limits
	 * @return the limits
	 */
	protected T getLimits(int index) {
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
	protected List<T> getDefaultRandomLimits() {
		return defaultRandomLimits;
	}

	/**
	 * Get the {@code Code View} defaultRandomLimits
	 * index the limit index
	 * @return the limit
	 */
	protected T getDefaultRandomLimits(int index) {
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
 			for (Options<T> element : validationList) {
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
 	protected T getCodeView(String userEntry) {
		return getCodeViewOrDefault(userEntry, getBlankCodeView());
	}
	
 	/**
	 * Test if the user entry is part of the validation list
	 * and return the {@code Code View} value as {@code String} or default Value
	 * @param userEntry the value to test
	 * @param onWrong   the value to return if not on the list
	 * @return the code view, onWrong if none
	 */
 	protected T getCodeViewOrDefault(String userEntry, T onWrong) {
 		if (userEntry != null) {
 			for (Options<T> element : validationList) {
				if (element.isValidUserEntry(userEntry, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return onWrong;
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return the user view
	 * @param codeView the code view to test
	 * @return the user view, "" if none
	 */
	protected String getUserView(T codeView) {
		if (codeView == null) {
			return getUserViewOrDefault(codeView, "");
		}
		return getUserViewOrDefault(codeView, codeView.toString()); // Not in the list
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return the user view or default Value
	 * @param codeView the code view to test
	 * @param onWrong  the value to return if not on the list
	 * @return the user view, "" if none
	 */
	protected String getUserViewOrDefault(T codeView, String onWrong) {
		if (codeView == null) {
			return onWrong;
		}
		// Try the options list
		for (Options<T> element : validationList) {
				if (element.isValidCodeView(codeView, criteria)) {
				return element.getUserView();
			}
		}
		// Try the direct conversion
		String userView = factory.toUserView(codeView);
		if (userView == null) {
			return onWrong; // Not in the list
		}	
		return userView;
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
		for (Options<T> element : validationList) {
			if (element.isValidUserEntry(userView, criteria)) {
				return index;
			}
			index++;
		}
		// Not in the List, then it's either a given index or default
		return PMutil.toIntegerOrDefault(userView, defaultIndex);
	}
	
	/**
	 * Test if the code view is part of the validation list
	 * and return its index, or the default one if none
	 * @param userView the {@code T Class} to search
	 * @param defaultIndex the {@code int} default returned value
	 * @return the {@code int} index
	 */
	protected int getCodeViewIndex(T codeView, int defaultIndex) {
		if (codeView == null) {
			return defaultIndex;
		}
		int index = 0;
		for (Options<T> element : validationList) {
			if (element.isValidCodeView(codeView, criteria)) {
				return index;
			}
			index++;
		}
		// Not in the List, then it's either a given index or default
		return PMutil.toIntegerOrDefault(codeView.toString(), defaultIndex);
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
	protected T getCodeView(int index) {
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
	protected T getCodeView(String userEntry, String category) {
 		if (userEntry != null && userEntry != category) {
			for (Options<T> element : validationList) {
				if (element.isValidUserEntry(userEntry, category, criteria)) {
					return element.getCodeView();
				}
			}
 		}
		return getBlankCodeView();
	}
	
	/**
	 * Test if the user view is part of the category list
	 * @param userEntry the user view to check
	 */
	protected boolean isValidUserEntry(String userEntry) {
		if (userEntry != null) {
			for (Options<T> element : validationList) {
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
			for (Options<T> element : validationList) {
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
	protected boolean isValidCodeView(T codeView, String category) {
		if (codeView != null) {
			for (Options<T> element : validationList) {
				if (element.isValidCodeView(codeView, category, criteria)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the value is part of the category validation list
	 * @param value the value to check
	 * @param category the filter to apply
	 */
	protected boolean isValid(AbstractT<T> value, String category) {
		if (value != null) {
			T codeView = value.codeView();
			if (codeView != null) {
				for (Options<T> element : validationList) {
					if (element.isValidCodeView(codeView, category, criteria)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Test if the value is valid
	 * @param value the value to check
	 */
	protected boolean isValid(AbstractT<T> value) {
		if (value != null) {
			if (criteria.isBlankAllowed() && value.isBlank()) {
				return true;
			}	
			T codeView = value.codeView();
			if (codeView != null) {
				for (Options<T> element : validationList) {
					if (element.isValidCodeView(codeView, criteria)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @return UserViewList to String List
	 */
	protected List<String> getOptionsStringList() {
		List<String> result = new ArrayList<String>();
		for (Options<T> element : validationList) {
			result.add(element.getUserView());
		}
		return result;
	}

	/**
	 * Generate UserViewList as String
	 * @return UserView List in capitalized String
	 */
	public String getOptionsRange() {
		if (hasList()) {
			return PMutil.capitalize(getOptionsStringList().toString());
		}
		if (isBoolean) {
			return PMutil.BOOLEAN_LIST.toString();
		}
		// Then it's Numeric
		return ("[Min=" + this.getLimits(0).toString()
				+ ", Max=" + this.getLimits(1).toString()
				+ ", Rnd Low=" + this.getDefaultRandomLimits(0).toString()
				+ ", Rnd Up=" + this.getDefaultRandomLimits(1).toString()
				+ "]");
	}

	/**
	 * Generate String with all Options and their = description
	 * @return the String, never null
	 */
	public String getOptionsDescription() {
		String result = "";
		String line;
		for (Options<T> element : validationList) {
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

	protected void addElement(Options<T> element) {
		validationList.add(element);
		autoUpdateLimits();
	}
	
	protected void addElement(T codeView) {
		addElement(new Options<T>(codeView));
	}
	
	protected void addElement(T codeView, String userView) {
		addElement(new Options<T>(
									codeView, userView));
	}
	
	protected void addElement(T codeView, String userView, 
					String description, String category) {
		addElement(new Options<T>(
								codeView, userView, description, category));
	}
	
	protected void addElement(T codeView, String description, String category) {
		addElement(new Options<T>(
									codeView, description, category));
	}
	
	protected void setValidationList(List<Options<T>> list) {
		validationList = list;
		autoUpdateLimits();
	}
}
