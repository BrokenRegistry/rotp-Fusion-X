package br.profileManager.src.main.java;

/**
 * Single element for validation list
 * @param <ValueClass> the Value's Code View Class
 */
public class ValidationElement<ValueClass> extends ToPrint {

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
	private ValueClass codeView; // Computer Format
	private String userView; // Human Format
	
    // ------------------------------------------------
    // Constructors
    //
	ValidationElement(ValueClass codeView) {
		setCategory("");
		setDescription("");
		setCodeView(codeView);
		setUserView(PMutil.suggestedUserViewFromCodeView(codeView));
	}

	ValidationElement(ValueClass codeView, String userView) {
		setCategory("");
		setDescription("");
		setCodeView(codeView);
		setUserView(userView);
	}

	ValidationElement(ValueClass codeView, String description, String category) {
		setCategory(category);
		setDescription(description);
		setCodeView(codeView);
		setUserView(PMutil.suggestedUserViewFromCodeView(codeView));
	}

	ValidationElement(ValueClass codeView, String userView, String description, String category) {
		setCategory(category);
		setDescription(description);
		setCodeView(codeView);
		setUserView(userView);
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
	boolean isValidCodeView(ValueClass codeViewToTest,
							ValidationCriteria criteria) {
		return PMutil.genericTest(codeView.toString(),
								  codeViewToTest.toString(),
								  criteria.codeViewIsCaseSensitive,
								  true);
	}

	/**
	 * Test if codeView is part of the category validation list
	 * @param codeViewToTest the {@code ValueClass} to test
	 * @param category the {@code String} category filter
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return  {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidCodeView(ValueClass codeViewToTest,
							String category,
							ValidationCriteria criteria) {
		return isValidCodeView(codeViewToTest, criteria)
				&& isValidCategory(category, criteria);
	}
	
	/**
	 * Test if user entry is recognized as known element
	 * @param userEntry the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidUserEntry(String userEntry,
							 ValidationCriteria criteria) {
		return PMutil.genericTest(userEntry,
								  userView,
								  criteria.userViewIsCaseSensitive,
								  criteria.userViewEquals);
	}

	/**
	 * Test if user entry is part of the category validation list
	 * @param userEntry the {@code String} to test
	 * @param category the {@code String} category filter
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return  {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidUserEntry(String userEntry,
							 String category,
							 ValidationCriteria criteria) {
		return isValidUserEntry(userEntry, criteria) 
				&& isValidCategory(category, criteria);
	}
	
	/**
	 * Test if category is recognized as known element
	 * @param categoryToTest the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isValidCategory(String categoryToTest,
							ValidationCriteria criteria) {
		return PMutil.genericTest(category,
								  categoryToTest,
								  criteria.categoryIsCaseSensitive,
								  criteria.categoryEquals);
	}

	/**
	 * Test if category is recognized as a known member 
	 * @param categoryToTest the {@code String} to test
	 * @param criteria {@code ValidationCriteria} test criteria
	 * @return {@code boolean} <b>true</b> if recognized
	 */
	boolean isMember(String categoryToTest, 
					 ValidationCriteria criteria) {
		return PMutil.genericTest(category,
								  categoryToTest,
								  criteria.categoryIsCaseSensitive,
								  false);
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

	ValueClass getCodeView() {
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
	private void setCodeView(ValueClass newCodeView) {
		codeView = newCodeView;
	}
	private void setUserView(String newUserView) {
		userView = newUserView;
	}
}
