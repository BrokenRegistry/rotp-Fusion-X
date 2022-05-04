
/*
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.config;

import java.util.ArrayList;
import java.util.List;

import br.config.Cfg_Value.DataType;

/**
 * @author BrokenRegistry
 *
 */
public class Cfg_ValidationData {
	
	private List<ValidationElement> validationList = new ArrayList<ValidationElement>();
	private Boolean labelContained   = false; // Check for equal instead
	private Boolean categoryContains = true; // Check for equal instead
	// for String test only, CfgEntry parameter is prevalent
	private Boolean isLabelCaseSensitive = false;
	private Boolean isCategoryCaseSensitive = false;
	private Boolean isOptionCaseSensitive = false;
	private Cfg_Value randomMin = null; // Default value
	private Cfg_Value randomMax = null; // Default value
	private Cfg_Value limitMin = null;
	private Cfg_Value limitMax = null;
	private DataType dataType = DataType.STRING; // the value by default

	// ------------------------------------------------
    // Constructors
    //
	public Cfg_ValidationData() {}
	// ------------------------------------------------
    // public and public Methods
    //
	/**
	 * Set the DataType to {@code UNKNOWN} (default value)
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setUnknownDataType() {
		dataType = DataType.UNKNOWN;	
		return this;
	}
	/**
	 * Set the DataType to {@code STRING} (default value)
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setStringDataType() {
		dataType = DataType.STRING;	
		return this;
	}
	/**
	 * Set the DataType to {@code BOOLEAN}
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setBooleanDataType() {
		dataType = DataType.BOOLEAN;	
		return this;
	}
	/**
	 * Set the DataType to {@code WHOLE}
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setWholeDataType () {
		dataType = DataType.WHOLE;
		return this;
	}
	/**
	 * Set the DataType to {@code FLOATING}
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setFloatingDataType (boolean isFloating) {
		dataType = DataType.FLOATING;
		return this;
	}
	/**
	 * Set the Numerical Limits if needed
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setLimits (
			Cfg_Value limitMin, Cfg_Value limitMax) {
		this.limitMin = limitMin;
		this.limitMax = limitMax;
		return this;
	}
	/**
	 * Set the default Random Limits if needed
	 * @return this for chaining purpose
	 */
	public Cfg_ValidationData setDefaultRandomLimits (
			Cfg_Value randomMin, Cfg_Value randomMax) {
		this.randomMin = randomMin;
		this.randomMax = randomMax;
		return this;
	}
	/**
	 * @return the islabelCaseSensitive
	 */
	public Boolean isLabelCaseSensitive() {
		return isLabelCaseSensitive;
	}
	/**
	 * @return the isOptionCaseSensitive
	 */
	public Boolean isOptionCaseSensitive() {
		return isOptionCaseSensitive;
	}
	/**
	 * @return the isCategoryCaseSensitive
	 */
	public Boolean isCategoryCaseSensitive() {
		return isCategoryCaseSensitive;
	}
	/**
	 * @return isUnknown
	 */
	public Boolean isUnknownDataType() {
		return dataType == DataType.UNKNOWN;
	}
	/**
	 * @return isString
	 */
	public Boolean isStringDataType() {
		return dataType == DataType.STRING;
	}
	/**
	 * @return isBoolean
	 */
	public Boolean isBooleanDataType() {
		return dataType == DataType.BOOLEAN;
	}
	/**
	 * @return isWhole
	 */
	public Boolean isWholeDataType() {
		return dataType == DataType.WHOLE;
	}
	/**
	 * @return isFloating
	 */
	public Boolean isFloatingDataType() {
		return dataType == DataType.FLOATING;
	}
	/**
	 * @return isNumeric
	 */
	public Boolean isNumericDataType() {
		return isWholeDataType() || isFloatingDataType();
	}
	/**
	 * @return dataType
	 */
	public DataType getDataType() {
		return dataType;
	}
	/**
	 * @return the randomMin
	 */
	public Cfg_Value randomMin() {
		return randomMin;
	}
	/**
	 * @return the randomMax
	 */
	public Cfg_Value randomMax() {
		return randomMax;
	}
	/**
	 * @return the limitMin
	 */
	public Cfg_Value limitMin() {
		return limitMin;
	}
	/**
	 * @return the limitMax
	 */
	public Cfg_Value limitMax() {
		return limitMax;
	}
	/**
	 * @return true if the Validation List is not empty
	 */
	public Boolean hasList() {
		return this.validationList.size() > 0;
	}
	/**
	 * Test if Entry is part of the validation list
	 * @return the category, "" if none
	 */
 	public String getCategory(Cfg_Entry entry) {
 		if (entry != null) {
 			for (ValidationElement validationLine : validationList) {
 				if (validationLine.isValid(entry)) {
 					return validationLine.getCategory();
 				}
 			}
 		}
		return "";
	}
	/**
	 * Test if label is part of the validation list
	 * @return the Option, "" if none
	 */
	public String getOption(String label) {
		return getOption(new Cfg_Entry(label, isLabelCaseSensitive));
	}
	/**
	 * Test if label is part of the validation list
	 * @return the Option, "" if none
	 */
	public String getOption(Cfg_Entry label) {
		for (ValidationElement validationLine : validationList) {
			if (validationLine.isValid(label)) {
				return validationLine.getOption();
			}
		}
		return "";
	}
	/**
	 * Test if option is part of the validation list
	 * @return the Label, "" if none
	 */
	public String getLabel(String option) {
		return getLabel(new Cfg_Entry(option, isOptionCaseSensitive));
	}
	/**
	 * Test if option is part of the validation list
	 * @return the Label, "" if none
	 */
	public String getLabel(Cfg_Entry option) {
		for (ValidationElement validationLine : validationList) {
			if (validationLine.isOption(option)) {
				return validationLine.getLabel();
			}
		}
		return "";
	}
	/**
	 * @return The list size
	 */
	public Integer lastIndex() {
		return validationList.size()-1;
	}
	/**
	 *  @return The Option from its index
	 */
	public String getOption(Integer index) {
		return validationList.get(index).getOption();
	}
	/**
	 * Test if Entry is part of the category list
	 *  @return The Option, "" if none
	 */
	public String getOption(Cfg_Entry entry, String category) {
		for (ValidationElement validationLine : validationList) {
			if (validationLine.isValid(entry, category)) {
				return validationLine.getOption();
			}
		}
		return "";
	}
	/**
	 * Test if Entry is part of the category list
	 */
	public boolean isValid(Cfg_Entry entry) {
		for (ValidationElement validationLine : validationList) {
			if (validationLine.isValid(entry)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Test if Entry is part of the category validation list
	 */
	public boolean isValid(Cfg_Entry entry, String category) {
		for (ValidationElement validationLine : validationList) {
			if (validationLine.isValid(entry, category)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @return LabelList to String
	 */
	public List<String> getLabelList() {
		List<String> result = new ArrayList<String>();
		for (ValidationElement line : validationList) {
			result.add(line.getLabel());
		}
		return result;
	}
	/**
	 * Generate LabelList and convert it to capitalized String
	 * @return Label List in capitalized String
	 */
	public String toString() {
		return Cfg_Util.capitalize(getLabelList().toString());
	}
	/**
	 * @return String with formated list of key = description as comment
	 */
	String toPrint() {
		String result = "";
		for (ValidationElement line : validationList) {
			result += line.toPrint() + System.lineSeparator();
		}
		return result;
	}
	/**
	 * For selected Category:
	 * @return string with formated list of key = description
	 */
	public String toString(String category) {
		String result = "";
		for (ValidationElement line : validationList) {
			if (line.isMember(category)) {
				result += line.toString() + System.lineSeparator();
			}
		}
		return result;
	}
	/**
	 * For selected Category:
	 * @return string with formated list of key = description as comment
	 */
	String toPrint(String category) {
		String result = "";
		for (ValidationElement line : validationList) {
			if (line.isMember(category)) {
				result += line.toPrint() + System.lineSeparator();
			}
		}
		return result;
	}
	// ------------------------------------------------
    // Setters & Getters
    //	
	public void addElement(String label, String option, String description, String category) {
		validationList.add(new ValidationElement(label, option, description, category));
	}
	public void addElement(String option, String description, String category) {
		validationList.add(new ValidationElement(option, description, category));
	}
	public void addElement(String option, String description) {
		validationList.add(new ValidationElement(option, description));
	}
	public void setValidationList(List<ValidationElement> list) {
		validationList = list;
	}
	/**
	 * set Option contains value or Option equals value
	 * @param labelContained true => contains; false => equals
	 */
	public void setOptionContains(boolean labelContained) {
		this.labelContained = labelContained;
	}
	/**
	 * set isLabelCaseSensitive (for String, CfgEntry set are prevalent)
	 * @param isLabelCaseSensitive New case sensitivity
	 * @param forceUpdate if true: will reset all existing labels
	 */
	public void setIsLabelCaseSensitive(
			boolean isLabelCaseSensitive, boolean forceUpdate) {
		this.isLabelCaseSensitive = isLabelCaseSensitive;
		if (forceUpdate) {
			for (ValidationElement validationLine : validationList) {
				validationLine.setLabelCaseSensitivity(isOptionCaseSensitive);
			}
		}
	}
	/**
	 * set isOptionCaseSensitive (for String, CfgEntry set are prevalent)
	 * @param isOptionCaseSensitive New case sensitivity
	 * @param forceUpdate if true: will reset all existing options
	 */
	public void setIsOptionCaseSensitive(
			boolean isOptionCaseSensitive, boolean forceUpdate) {
		this.isOptionCaseSensitive = isOptionCaseSensitive;
		if (forceUpdate) {
			for (ValidationElement validationLine : validationList) {
				validationLine.setOptionCaseSensitivity(isOptionCaseSensitive);
			}
		}
	}
	/**
	 * set isCategoryCaseSensitive (for String, CfgEntry set are prevalent)
	 * @param isCategoryCaseSensitive New case sensitivity
	 * @param forceUpdate if true: will reset all existing categories
	 */
	public void setIsCategoryCaseSensitive(
			boolean isCategoryCaseSensitive, boolean forceUpdate) {
		this.isCategoryCaseSensitive = isCategoryCaseSensitive;
		if (forceUpdate) {
			for (ValidationElement validationLine : validationList) {
				validationLine.setCategoryCaseSensitivity(isOptionCaseSensitive);
			}
		}
	}
	/**
	 * set Category contains or Category equals
	 * @param labelContained true => contains; false => equals
	 */
	public void setCategoryContains(boolean categoryContains) {
		this.categoryContains = categoryContains;
	}
	// ==================================================
    // Nested Class ValidationElement
    //
	class ValidationElement {
		
	    // ------------------------------------------------------------------------
		// Constant Properties
	    //
		private final Integer SEPARATOR_POSITION = Cfg_Util.LINE_SPLIT_POSITION - 2;
		private final String  SEPARATOR_SYMBOL   = "=";
		private final String  SEPARATOR_SPACER   = " ";
		private final String  KEY_FORMAT         = "%-" 
		                                        + SEPARATOR_POSITION.toString()
		                                        + "s"
		                                        + SEPARATOR_SYMBOL 
		                                        + SEPARATOR_SPACER;
		
		private String description;
		private Cfg_Entry category ;
		private Cfg_Entry option; // Computer Format
		private Cfg_Entry label; // Human Format
		
	    // ------------------------------------------------
	    // Constructors
	    //
		ValidationElement(String label, String option, String description, String category) {
			setCategory(category);
			setDescription(description);
			setOption(option);
			setLabel(label);
		}
		ValidationElement(Cfg_Entry label, String option, String description, String category) {
			setCategory(category);
			setDescription(description);
			setOption(option);
			setLabel(label);
		}
		ValidationElement(Cfg_Entry label, Cfg_Entry option, String description, String category) {
			setCategory(category);
			setDescription(description);
			setOption(option);
			setLabel(label);
		}
		ValidationElement(String option, String description, String category) {
			setCategory(category);
			setDescription(description);
			setOption(option);
			setLabel(Cfg_Util.suggestedOptionToLabel(option));
		}
		ValidationElement(Cfg_Entry option, String description, String category) {
			setCategory(category);
			setDescription(description);
			setOption(option);
			setLabel(Cfg_Util.suggestedOptionToLabel(option));
		}
		ValidationElement(String option, String description) {
			setCategory("");
			setDescription(description);
			setOption(option);
			setLabel(Cfg_Util.suggestedOptionToLabel(option));
		}
		ValidationElement(Cfg_Entry option, String description) {
			setCategory("");
			setDescription(description);
			setOption(option);
			setLabel(Cfg_Util.suggestedOptionToLabel(option));
		}
		// ------------------------------------------------
	    // Other Methods
	    //
		/**
		 * Test if Entry is part of one of the validation line
		 * If one element is not case sensitive, the test is not
		 */
		public boolean isOption(Cfg_Entry option) {
			// if one element is not case sensitive, the test is not
			if (this.option.isCaseSensitive() && option.isCaseSensitive()) {
				return this.option.toString().equals(option.toString());
			}
			return this.option.toString().equalsIgnoreCase(option.toString());
			
		}
		/**
		 * Test if Entry is part of one of the validation line
		 * If one element is not case sensitive, the test is not
		 */
		public boolean isValid(String label) {
			return isValidEntry(label);
		}
		/**
		 * Test if Entry is part of one of the validation line
		 * If one element is not case sensitive, the test is not
		 */
		public boolean isValid(Cfg_Entry label) {
			return isValidEntry(label);
		}
		/**
		 * Test if is a member of category
		 */
		public boolean isMember(String category) {
			return this.category.contains(category);
		}
		/**
		 * Test if Entry is part of the category validation list
		 */
		public boolean isValid(String entry, String lineCategory) {
			return isValidEntry(entry) && isValidCategory(lineCategory);
		}		
		/**
		 * Test if Entry is part of the category validation list
		 */
		public boolean isValid(Cfg_Entry entry, String lineCategory) {
			return isValidEntry(entry) && isValidCategory(lineCategory);
		}		
		public void setOptionCaseSensitivity(boolean newCaseSensitivity) {
			option.setCaseSensitive(newCaseSensitivity);;
		}
		public void setLabelCaseSensitivity(boolean newCaseSensitivity) {
			label.setCaseSensitive(newCaseSensitivity);;
		}
		public void setCategoryCaseSensitivity(boolean newCaseSensitivity) {
			category.setCaseSensitive(newCaseSensitivity);;
		}
		/**
		 * @return string with formated category = description
		 */
		public String toString() {
			return String.format(KEY_FORMAT, category) + description;
		}
		/**
		 * Return string with formated category = description as comment
		 */
		String toPrint() {
			return new Comment(toString()).toPrint();
		}
		// ------------------------------------------------
	    // Setters
	    //
		private void setCategory(String newCategory) {
			category = new Cfg_Entry(newCategory, isCategoryCaseSensitive) ;
		}
		private void setDescription(String newDescription) {
			description = newDescription;
		}
		private void setOption(String newOption) {
			option = new Cfg_Entry(newOption, isOptionCaseSensitive);
		}
		private void setOption(Cfg_Entry newOption) {
			option = newOption;
		}
		private void setLabel(String newLabel) {
			label = new Cfg_Entry(newLabel, isLabelCaseSensitive);
		}
		private void setLabel(Cfg_Entry newLabel) {
			label = newLabel;
		}

		// ------------------------------------------------
	    // Getters
	    //
		private String getCategory() {
			return category.toString();
		}
		private String getOption() {
			return option.toString();
		}
		private String getLabel() {
			return label.toString();
		}
		private Boolean isValidCategory(String in) {
			if (categoryContains) {
				return category.contains(in);
			}
			return category.equals(in);
		}
		private boolean isValidEntry(Cfg_Entry in) {
			if (labelContained) {
				return label.isContained(in);
			}
			return label.equals(in);
		}
		private boolean isValidEntry(String in) {
			if (labelContained) {
				return label.isContained(in);
			}
			return label.equals(in);
		}
	}
}
