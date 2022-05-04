package br.configurationManager.src.main.java;

import br.configurationManager.src.main.java.Abstract_ToPrint.PrintFormat;

/**
 * All boolean conditions for parameters validations
 * with default values already set
 * used as "struct"
 * so setters and getters are not mandatory
 */
class ValidationCriteria {

	boolean isNullAllowed   = false;
	boolean isBlankAllowed  = true;
	boolean isRandomAllowed = true;
	/**
	 * <b>true</b>: user View equals 
	 * <b>false</b>: user View isContained
	 */
	boolean userViewEquals = true ;
	/**
	 * <b>true</b>: category equals 
	 * <b>false</b>: category Contains
	 */
	boolean categoryEquals = false;
	boolean userViewIsCaseSensitive  = false;
	boolean categoryIsCaseSensitive  = false;
	boolean codeViewIsCaseSensitive  = true;
	PrintFormat printFormat = PrintFormat.CAPITALIZE;
	
	// ==================================================
    // getters
    //
	boolean isNullAllowed()  { 
		return isNullAllowed; 
	}
	
	boolean isBlankAllowed() { 
		return isBlankAllowed; 
	}
	boolean isRandomAllowed() { 
		return isRandomAllowed; 
	}
	
	/**
	 * <b>true</b>: user View equals 
	 * <b>false</b>: user View isContained
	 */
	boolean userViewEquals() { 
		return userViewEquals; 
	}
	
	/**
	 * <b>true</b>: category equals 
	 * <b>false</b>: category Contains
	 */
	boolean categoryEquals() { 
		return categoryEquals; 
	}
	
	boolean userViewIsCaseSensitive() { 
		return userViewIsCaseSensitive; 
	}
	
	boolean categoryIsCaseSensitive() { 
		return categoryIsCaseSensitive; 
	}
	
	boolean codeViewIsCaseSensitive() { 
		return codeViewIsCaseSensitive; 
	}
	
	PrintFormat printFormat() { 
		return printFormat; 
	}
	
	// ==================================================
    // Setters for chaining purpose
    //

	ValidationCriteria isNullAllowed(boolean allowed) {
		isNullAllowed = allowed;
		return this; 
	}
	
	ValidationCriteria isBlankAllowed(boolean allowed) { 
		isBlankAllowed = allowed;
		return this; 
	}
	
	ValidationCriteria isRandomAllowed(boolean allowed) { 
		isRandomAllowed = allowed;
		return this; 
	}
	
	/**
	 * <b>true</b>: user View equals 
	 * <b>false</b>: user View isContained
	 */
	ValidationCriteria userViewEquals(boolean equals) { 
		userViewEquals = equals;
		return this; 
	}
	
	/**
	 * <b>true</b>: category equals 
	 * <b>false</b>: category Contains
	 */
	ValidationCriteria categoryEquals(boolean equals) { 
		categoryEquals = equals;
		return this; 
	}
	
	ValidationCriteria userViewIsCaseSensitive(boolean caseSensitive) { 
		userViewIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	ValidationCriteria categoryIsCaseSensitive(boolean caseSensitive) { 
		categoryIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	ValidationCriteria codeViewIsCaseSensitive(boolean caseSensitive) { 
		codeViewIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	ValidationCriteria printFormat(PrintFormat format) { 
		printFormat = format;
		return this; 
	}
	
	// ==================================================
    // Other methods
    //
	ValidationCriteria copy() { 
		return this; 
	}

	@Override
	protected ValidationCriteria clone() { 
		return new ValidationCriteria()
				.isNullAllowed(isNullAllowed)
				.isBlankAllowed(isBlankAllowed)
				.isRandomAllowed(isRandomAllowed)
				.userViewEquals(userViewEquals)
				.categoryEquals(categoryEquals)
				.userViewIsCaseSensitive(userViewIsCaseSensitive)
				.categoryIsCaseSensitive(categoryIsCaseSensitive)
				.codeViewIsCaseSensitive(codeViewIsCaseSensitive)
				.printFormat(printFormat);
	}
	
}
