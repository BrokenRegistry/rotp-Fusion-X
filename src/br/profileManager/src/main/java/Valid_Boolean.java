package br.profileManager.src.main.java;

/**
 * For the validation of the {@code Boolean}
 */
public class Valid_Boolean extends Abstract_ValidData<Boolean> {
	
	// ==================================================
    // Constructors
    //
	public Valid_Boolean() {
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

	// ==================================================
    // Overriders
    //
	/**
	 * Process non Random user entry
	 * @return {@code Boolean} Validated Value
	 */
	@Override Boolean entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			if (getValidationCriteria().isBlankAllowed) {
				return null;
			}
			return getDefaultValue();
		}
		// Then Check if value is valid
		Boolean value = PMutil.toBoolean(userEntry);
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
		return PMutil.getBooleanRandom();
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
		return PMutil.toYesNoString(codeView);
	}
	
	@Override Boolean toCodeView(String userView) {
		return PMutil.toBoolean(userView);
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
		int id = PMutil.getRandom(0, parameters.length);
		return entryValidation(parameters[id]);
	}

 	// ==========================================================
    // Nested Classes
    //
	/**
	 * Base for every User Entry Lines
	 */
	static class Line_Boolean extends Generic_Line<Boolean, Valid_Boolean>{

	   	// --------------------------------------------------------------
	    // Constructors
	    //
		Line_Boolean() {
			super(new Valid_Boolean());
		}

		/**
		 * @param line {@code String} new Setting Line from config file
		 */
		Line_Boolean(String line) {
			super(new Valid_Boolean(), line);
		}
	}
}
