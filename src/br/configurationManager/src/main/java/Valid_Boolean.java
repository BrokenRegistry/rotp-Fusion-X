package br.configurationManager.src.main.java;

/**
 * For the validation of the {@code Boolean}
 */
class Valid_Boolean extends Abstract_ValidData<Boolean> {
	
	Valid_Boolean() {
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
	}
	/**
	 * Process non Random user entry
	 * @return {@code Boolean} Validated Value
	 */
	@Override Boolean entryValidation(String userEntry) {
		userEntry = CMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		// Then Check if value is valid
		Boolean value = CMutil.toBoolean(userEntry);
		if (value == null) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		return value;
	}
	
	/**
	 * Process Random without parameters
	 * @return {@code Boolean} OutputString
	 */
	@Override Boolean randomWithoutParameters() {
		return CMutil.getBooleanRandom();
	}
	
	/**
	 * Process Random with parameters
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Boolean} OutputString
	 */
	@Override Boolean randomWithParameters(String[] parameters) {
		// Could be used to have a weighting on the Yes or No
		// ex: Random yes, yes, yes, NO
		return randomWithList(parameters);
	}
	
	@Override String toUserView(Boolean codeView) {
		return CMutil.toYesNoString(codeView);
	}
	
	@Override Boolean toCodeView(String userView) {
		return CMutil.toBoolean(userView);
	}
	
	// ==================================================
    // Other Methods
    //
	/**
	 * Process Random among the given list
	 * @param parameters {@code String[]} the extra parameters
	 * @return {@code Boolean} Random Value
	 */
	Boolean randomWithList(String[] parameters) {
		int id = CMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}
}
