package br.configurationManager.src.main.java;

/**
 * For the validation of the {@code String}
 */
class Valid_String extends Abstract_ValidData<String> {
		
    // ==================================================
    // Constructors
    //
	Valid_String() {
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

	// ==================================================
    // Abstract Methods Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code String} Validated Code View
	 */
	@Override String entryValidation(String userEntry) {
		userEntry = CMutil.clean(userEntry);
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
				int id = CMutil.getRandom(0, listSize());
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
			int id = CMutil.getRandom(min, max);
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
	
	@Override String toUserView(String codeView) {
		return getUserView(codeView);
	}
	
	@Override String toCodeView(String userView) {
		return getCodeView(userView);
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
			if (CMutil.testForInteger(parameters[0])) {
				lim1 = CMutil.validateLimits(CMutil.toInteger(parameters[0]), min, max);
			} else {
				lim1 = getUserViewIndex(getDefaultRandomLimits()[0], min);
			}
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (CMutil.testForInteger(parameters[1])) {
				lim2 = CMutil.validateLimits(CMutil.toInteger(parameters[1]), min, max);
			} else {
				lim2 = getUserViewIndex(getDefaultRandomLimits()[1], max);
			}
		}
		// get Random
		int id = CMutil.getRandom(lim1, lim2);
		return getCodeView(id);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code String} Random Value
	 */
	String randomWithList(String[] parameters) {
		int id = CMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}
}
