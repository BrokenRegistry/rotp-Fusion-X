package br.configurationManager.src.main.java;

/**
 *Single element for validation list
 */
public class ValidationElement extends ToPrint {

	// ------------------------------------------------------------------------
	// Constant Properties
    //
	private final Integer SEPARATOR_POSITION = LINE_SPLIT_POSITION - COMMENT_PRT.length();
	private final String  SEPARATOR_SYMBOL   = "=";
	private final String  SEPARATOR_SPACER   = " ";
	private final String  KEY_FORMAT         = "%-" 
	                                        + SEPARATOR_POSITION.toString()
	                                        + "s"
	                                        + SEPARATOR_SYMBOL 
	                                        + SEPARATOR_SPACER;
	
	private String description;
	private String category ;
	private String codeView; // Computer Format
	private String userView; // Human Format
	
    // ------------------------------------------------
    // Constructors
    //
	ValidationElement(String userView, String codeView, String description, String category) {
		setCategory(category);
		setDescription(description);
		setCodeView(codeView);
		setUserView(userView);
	}

	ValidationElement(String codeView, String description, String category) {
		setCategory(category);
		setDescription(description);
		setCodeView(codeView);
		setUserView(CMutil.suggestedUserViewFromCodeView(codeView));
	}

	ValidationElement(String codeView, String description) {
		setCategory("");
		setDescription(description);
		setCodeView(codeView);
		setUserView(CMutil.suggestedUserViewFromCodeView(codeView));
	}

	ValidationElement(String codeView) {
		setCategory("");
		setDescription("");
		setCodeView(codeView);
		setUserView(CMutil.suggestedUserViewFromCodeView(codeView));
	}

	// ------------------------------------------------
    // Other Methods
    //
	/**
	 * Test if codeView is is recognized as known codeView
	 * @param codeViewToTest the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidCodeView(String codeViewToTest, ValidationCriteria criteria) {
		return CMutil.genericTest(codeView, codeViewToTest,
									criteria.codeViewIsCaseSensitive, true);
	}

	/**
	 * Test if user entry is recognized as known element
	 * @param userEntry the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidUserEntry(String userEntry, ValidationCriteria criteria) {
		return CMutil.genericTest(userView, userEntry, 
				criteria.userViewIsCaseSensitive, criteria.userViewEquals);
	}

	/**
	 * Test if user entry is part of the category validation list
	 * @param userEntry the {@code String} to test
	 * @param category the {@code String} category filter
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return  {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidUserEntry(String userEntry, String category, ValidationCriteria criteria) {
		return isValidUserEntry(userEntry, criteria) && isValidCategory(category, criteria);
	}
	
	/**
	 * Test if category is recognized as known element
	 * @param categoryToTest the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidCategory(String categoryToTest, ValidationCriteria criteria) {
		return CMutil.genericTest(category, categoryToTest,
				criteria.categoryIsCaseSensitive, criteria.categoryEquals);
	}

	/**
	 * Test if category is recognized as a known member 
	 * @param categoryToTest the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isMember(String categoryToTest, ValidationCriteria criteria) {
		return CMutil.genericTest(category, categoryToTest,
				criteria.categoryIsCaseSensitive, false);
	}
	
	/**
	 * @return string with formated userView = description
	 */
	@Override
	public String toString() {
		return String.format(KEY_FORMAT, userView) + description;
	}
	/**
	 * Return string with formated userView = description as comment
	 */
	@Override
	String toPrint() {
		return toComment();
	}
	// ------------------------------------------------
    // Getters
    //
	String getCategory() {
		return category;
	}

	String getDescription() {
		return description;
	}

	String getCodeView() {
		return codeView;
	}

	String getUserView() {
		return userView;
	}

	// ------------------------------------------------
    // Setters
    //
	private void setCategory(String newCategory) {
		category = newCategory ;
	}
	private void setDescription(String newDescription) {
		description = newDescription;
	}
	private void setCodeView(String newCodeView) {
		codeView = newCodeView;
	}
	private void setUserView(String newUserView) {
		userView = newUserView;
	}
}
