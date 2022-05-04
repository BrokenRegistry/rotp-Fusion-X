package br.configurationManager.src.main.java;

/**
 * For the validation of the {@code Double}
 */
class Valid_Double extends Abstract_ValidData<Double> {
	
	Valid_Double() {
		setValidationCriteria(new ValidationCriteria()
				.isNullAllowed(true)
				.isBlankAllowed(true)
				.isRandomAllowed(true)
				.userViewEquals(false)
				.categoryEquals(false)
				.userViewIsCaseSensitive(false)
				.codeViewIsCaseSensitive(false)
				.categoryIsCaseSensitive(false)
				.printFormat(PrintFormat.CAPITALIZE));

		// Some arbitrary values to simplify the code
		setLimits(new Double[] {0.0, 1000000.0});
		setDefaultRandomLimits(new Double[] {0.0, 1.0});
	}
	/**
	 * Process non Random user entry
	 * @return {@code Double} Validated Value
	 */
	@Override Double entryValidation(String userEntry) {
		userEntry = CMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		// Then Check if value is valid
		Double value = CMutil.toDouble(userEntry);
		if (value == null) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		return CMutil.validateLimits(value, getLimits()[0], getLimits()[1]);
	}
	
	/**
	 * Process Random without parameters
	 * @return {@code Double} OutputString
	 */
	@Override Double randomWithoutParameters() {
		return CMutil.getRandom(getDefaultRandomLimits()[0], getDefaultRandomLimits()[1]);
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Double} OutputString
	 */
	@Override Double randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override String toUserView(Double codeView) {
		return codeView.toString();
	}
	
	@Override Double toCodeView(String userView) {
		return CMutil.toDouble(userView);
	}
	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Double} Random Value
	 */
	Double randomWithLimit(String[] parameters) {
		Double lim1 = getLimits()[0];
		Double lim2 = getLimits()[1];
		Double min = Math.min(lim1, lim2);
		Double max = Math.max(lim1, lim2);
		// First Limit
		if (parameters.length >= 1) {
			if (CMutil.testForNumeric(parameters[0])) {
				lim1 = CMutil.validateLimits(CMutil.toDouble(parameters[0]), min, max);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (CMutil.testForNumeric(parameters[1])) {
				lim2 = CMutil.validateLimits(CMutil.toDouble(parameters[1]), min, max);
			} 
		}
		// get Random
		return CMutil.getRandom(lim1, lim2);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Double} Random Value
	 */
	Double randomWithList(String[] parameters) {
		int id = CMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}
}
