package br.profileManager.src.main.java;

import java.util.List;

/**
 * For the validation of the {@code String}
 */
public class Valid_String extends Abstract_ValidData<String> {
		
    // ==================================================
    // Constructors and initializers
    //
	protected Valid_String() {
		super();
		init();
	}
	protected Valid_String(List<String> options) {
		super(options);
		init();
	}
	
	private void initCriteria() {
		setValidationCriteria(new ValidationCriteria()
				.isNullAllowed(false)
				.isBlankAllowed(true)
				.isRandomAllowed(true)
				.userViewEquals(false)
				.categoryEquals(false)
				.userViewIsCaseSensitive(false)
				.codeViewIsCaseSensitive(false)
				.categoryIsCaseSensitive(false)
				.printFormat(PrintFormat.CAPITALIZE));
	}
	
	private void init() {
		initCriteria();
		setBlankValue("");
		setCurrentValue("");
		setInitialValue("");
		setLastValue("");
		setGameValue("");
		setDefaultValue("");
	}

	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code String} Validated Code View
	 */
	@Override String entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed) {
				return userEntry;
			}
			return getDefaultValue();
		}
		// Then Check for a validation list
		if (!hasList()) {
			// No list = entry accepted
			return userEntry;
		}
		// Then check validity
		if (isValidUserEntry(userEntry)) {
			return toCodeView(userEntry);
		}
		return getDefaultValue();
	}

	/**
	 * Process Random without parameters
	 * @return {@code String} Random Value
	 */
	@Override String randomWithoutParameters() {
		if (hasList()) {
			// without default Random Limits
			if (getDefaultRandomLimits() == null 
					|| getDefaultRandomLimits().length == 0) {
				int id = PMutil.getRandom(0, listSize());
				return getCodeView(id);
			}
			// with String default Random Limits
			int min = 0;
			if (getDefaultRandomLimits().length >= 1) {
				min = getUserViewIndex(getDefaultRandomLimits()[0], min);
			}
			int max = listSize();
			if (getDefaultRandomLimits().length >= 2) {
				max = getUserViewIndex(getDefaultRandomLimits()[1], max);
			}
			int id = PMutil.getRandom(min, max);
			return getCodeView(id);
		}
		return getDefaultValue(); // What else?
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	@Override String randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override String toCodeView(String userView) {
		return getCodeView(userView);
	}
	
	@Override String toUserView(String codeView) {
		return getUserView(codeView);
	}
	
	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	String randomWithLimit(String[] parameters) {
		int min = 0;
		int max = listSize();
		int lim1 = min;
		int lim2 = max;
		// First Limit
		if (parameters.length >= 1) {
			if (PMutil.testForInteger(parameters[0])) {
				lim1 = PMutil.validateLimits(PMutil.toInteger(parameters[0]), min, max);
			} else {
				lim1 = getUserViewIndex(getDefaultRandomLimits()[0], min);
			}
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (PMutil.testForInteger(parameters[1])) {
				lim2 = PMutil.validateLimits(PMutil.toInteger(parameters[1]), min, max);
			} else {
				lim2 = getUserViewIndex(getDefaultRandomLimits()[1], max);
			}
		}
		// get Random
		int id = PMutil.getRandom(lim1, lim2);
		return getCodeView(id);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	String randomWithList(String[] parameters) {
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_String extends Generic_Line<String, Valid_String>{

	   	// ==========================================================
	    // Constructors
	    //
		Line_String() {
			super(new Valid_String());
			setValue("");
		}

		/**
		 * @param name    {@code String} key / config name
		 * @param value   {@code String} value
		 * @param comment {@code String} comment, null if none
		 */
		Line_String(String name, String value, String comment) {
			super(new Valid_String());
			if (comment != null) {
				setComment(comment);
			}
			setName(name);
			setValue(value);
		}
		/**
		 * @param validationData {@code Valid_String} validation parameters
		 */
		Line_String(Abstract_ValidData<String> validationData) {
			super(validationData);
			setValue("");
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_String(String line) {
			super(new Valid_String(), line);
		}
		
		/**
		 * @param validationData {@code Valid_String} validation parameters
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_String(Abstract_ValidData<String> validationData, String line) {
			super(validationData, line);
		}
	}
}
