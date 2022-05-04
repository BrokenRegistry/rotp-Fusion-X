package br.configurationManager.src.main.java;

/**
 * For the validation of the {@code Integer}
 */
class Valid_Integer extends Abstract_ValidData<Integer> {
	
	Valid_Integer() {
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
		setLimits(new Integer[] {0, 1000000});
		setDefaultRandomLimits(new Integer[] {0, 101});
	}
	/**
	 * Process non Random user entry
	 * @return {@code Integer} Validated Value
	 */
	@Override Integer entryValidation(String userEntry) {
		userEntry = CMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		// Then Check if value is valid
		Integer value = CMutil.toInteger(userEntry);
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
	 * @return {@code Integer} OutputString
	 */
	@Override Integer randomWithoutParameters() {
		return CMutil.getRandom(getDefaultRandomLimits()[0], getDefaultRandomLimits()[1]);
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Integer} OutputString
	 */
	@Override Integer randomWithParameters(String[] parameters) {
		if (parameters.length > 2) {
			return randomWithList(parameters);
		}
		return randomWithLimit(parameters);
	}
	
	@Override String toUserView(Integer codeView) {
		return codeView.toString();
	}
	
	@Override Integer toCodeView(String userView) {
		return CMutil.toInteger(userView);
	}
	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random within Given Limits
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Integer} Random Value
	 */
	Integer randomWithLimit(String[] parameters) {
		int lim1 = getLimits()[0];
		int lim2 = getLimits()[1];
		int min = Math.min(lim1, lim2);
		int max = Math.max(lim1, lim2);
		// First Limit
		if (parameters.length >= 1) {
			if (CMutil.testForInteger(parameters[0])) {
				lim1 = CMutil.validateLimits(CMutil.toInteger(parameters[0]), min, max);
			} 
		}
		// Second Limit
		if (parameters.length >= 2) {
			if (CMutil.testForInteger(parameters[1])) {
				lim2 = CMutil.validateLimits(CMutil.toInteger(parameters[1]), min, max);
			} 
		}
		// get Random
		return CMutil.getRandom(lim1, lim2);
	}
	
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Integer} Random Value
	 */
	Integer randomWithList(String[] parameters) {
		int id = CMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}
}
