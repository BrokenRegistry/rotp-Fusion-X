package br.profileManager.src.main.java;

import br.profileManager.src.main.java.ToPrint.PrintFormat;

/**
 * All boolean conditions for parameters validations
 * with default values already set
 * used as "struct"
 * so setters and getters are not mandatory
 */
public class ValidationCriteria {

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

	/**
	 * @param allowed the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria isNullAllowed(boolean allowed) {
		isNullAllowed = allowed;
		return this; 
	}
	
	/**
	 * @param allowed the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria isBlankAllowed(boolean allowed) { 
		isBlankAllowed = allowed;
		return this; 
	}
	
	/**
	 * @param allowed the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria isRandomAllowed(boolean allowed) { 
		isRandomAllowed = allowed;
		return this; 
	}
	
	/**
	 * <b>true</b>: user View equals 
	 * <b>false</b>: user View isContained
	 * @param equals the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria userViewEquals(boolean equals) { 
		userViewEquals = equals;
		return this; 
	}
	
	/**
	 * <b>true</b>: category equals 
	 * <b>false</b>: category Contains
	 * @param equals the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria categoryEquals(boolean equals) { 
		categoryEquals = equals;
		return this; 
	}
	
	/**
	 * @param caseSensitive the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria userViewIsCaseSensitive(boolean caseSensitive) { 
		userViewIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	/**
	 * @param caseSensitive the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria categoryIsCaseSensitive(boolean caseSensitive) { 
		categoryIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	/**
	 * @param caseSensitive the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria codeViewIsCaseSensitive(boolean caseSensitive) { 
		codeViewIsCaseSensitive = caseSensitive;
		return this; 
	}
	
	/**
	 * @param format the new value
	 * @return this for chaining purpose
	 */
	public ValidationCriteria printFormat(PrintFormat format) { 
		printFormat = format;
		return this; 
	}
}
