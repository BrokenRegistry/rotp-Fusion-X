package br.configurationManager.src.main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <ValueClass> The class of Values
 */
class Generic_Block<ValueClass> extends ToPrint {

	private List<Generic_Line<ValueClass>> lineList;
	private Abstract_ValidData<ValueClass> valueValidation;
	private Abstract_ValidData<String>     configValidation;

    // ==================================================
    // Constructors
    //
	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 * @param valueValidationData  the values validation parameters
	 * @param configValidationData the configuration validation parameters
	 */	
	@SuppressWarnings("unused")
	private Generic_Block() {} // no empty block allowed

	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 * @param valueValidationData  the values validation parameters
	 * @param configValidationData the configuration validation parameters
	 */	
	Generic_Block(
			Abstract_ValidData<ValueClass> valueValidationData) {
		
		valueValidation  = valueValidationData;
		configValidation = new Valid_String();
		lineList = new ArrayList<Generic_Line<ValueClass>>();
	}

	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 * @param valueValidationData  the values validation parameters
	 * @param configValidationData the configuration validation parameters
	 */	
	Generic_Block(
			Abstract_ValidData<ValueClass> valueValidationData,
			Abstract_ValidData<String>     configValidationData) {
		
		valueValidation  = valueValidationData;
		configValidation = configValidationData;
		lineList = new ArrayList<Generic_Line<ValueClass>>();
	}
	
	// ==================================================
    // Setters
    //
	/**
	 * add a new user configuration
	 * @param    newLine the new {@code String} configuration line
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass> add(String newLine) {
		lineList.add(new Generic_Line<ValueClass>(
				valueValidation, configValidation, newLine));
		return this;
	}

	/**
	 * add a new user configuration to build the demo config file
	 * @param    config    the {@code String} configuration name
	 * @param    userEntry the {@code String} entry value
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass> add(String config, String userEntry) {
		lineList.add(new Generic_Line<ValueClass>(
				valueValidation,
				configValidation,
				config + " " + Generic_Line.KEY_VALUE_SEPARATOR + " " + userEntry));
		return this;
	}

	/**
	 * add a new user configuration
	 * @param    newLine the new {@code Line<ValueClass>} configuration line
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass> add(Generic_Line<ValueClass> newLine) {
		lineList.add(newLine);
		return this;
	}

	// ==================================================
    // Testers
	
	/**
	 * Test if a valid non blank value may be returned from user configuration
	 * @param config configuration name
	 * @return {@code Boolean} <b>true</b> if user configuration is valid
	 */
	protected Boolean isValid(String config) {
		Generic_Line<ValueClass> line = this.get(config);
		if (line != null
				&& line.getValueAsEntry() != null) {
			return !line.getValueAsEntry().isBlank();
		}
		return false;
	}
	
	/**
	 * Test for Blank value
	 * @param config configuration name
	 * @return {@code Boolean} <b>true</b> if user configuration is blank, 
	 *                         <b>null</b> if none 
	 */
	protected Boolean isBlankValue(String config) {
		Generic_Line<ValueClass> line = this.get(config);
		if (line != null
				&& line.getValueAsEntry() != null) {
			return line.getValueAsEntry().isBlank();
		}
		return null;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the configuration value
	 * @param config configuration name
	 * @return the value as {@code ValueClass} or null if none
	 */	
	 ValueClass getValue(String config) {
		Generic_Line<ValueClass> line = get(config);
		if (line == null) { // none exist... so empty
			return null;
		}
		return line.getValue();
	}
	
	/**
	 * @return print ready String with all the block content
	 */	
	@Override public String toString() {
		String out = "";
		for (Generic_Line<ValueClass> line : lineList) {
			if (out.isBlank()) {
				out = line.toPrint();
			}
			out += System.lineSeparator() + line.toPrint();
		}
		return out;
	}
	
	/**
	 * add missing configs as given ones
	 * @param configList configurations list that should be
	 * @param newLine Missing configurations template
	 */
	void addMissing(List<String> configList) {
		Generic_Line<ValueClass> line;
		for (String config : configList) {
			line = get(config);
			if (CMutil.getForceCreationMissingConfig() 
					&& line == null) { // none exist...
				add(config);           // add the missing one
				line = get(config);
			}
		}
	}

	/**
	 * toString filtered by configuration list
	 * @return print ready String with configList block content
	 */	
	String toString(List<String> configList) {
		String out = "";
		Generic_Line<ValueClass> line;
		for (String config : configList) {
			line = get(config);
			if (CMutil.getForceCreationMissingConfig() 
					&& line == null) { // none exist...
				add(config);           // add the missing one
				line = get(config);
			}
			if (out.isBlank()) {
				out = line.toPrint();
			}
			out += System.lineSeparator() + line.toPrint();
		}
		return out;
	}

	/**
	 * Get configuration list from the block
	 * @return List of all configurations in the block
	 */	
	List<String> getLabelList() {
		List<String> configs = new ArrayList<String>();
		for (Generic_Line<ValueClass> line : lineList) {
			if (line.getKey() != null) {
				configs.add(line.getKey());
			}
		}
		return configs;
	}

	/**
	 * Get configuration list from the block filtered by value
	 * @param filter the {@code String} filter to be equal
	 * @return List of configurations following the request
	 */	
	List<String> getLabelListIfValueEqualsFilter(String filter) {
		List<String> configs = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass> line : lineList) {
				if (line.isValueAsFilter(filter)) {
					configs.add(line.getKey());
				}
			}
		}
		return configs;
	}

	/**
	 * Get configuration list from the block filtered by value
	 * @param filter the {@code String} filter to be contained
	 * @return List of configurations following the request
	 */	
	List<String> getLabelListIfValueContainsFilter(String filter) {
		List<String> configs = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass> line : lineList) {
				if (line.isFilterInValue(filter)) {
					configs.add(line.getKey());
				}
			}
		}
		return configs;
	}
	
	/**
	 * Get configuration list from the block filtered by value
	 * @param filter the {@code String} containing filter
	 * @return List of configurations following the request
	 */	
	List<String> getLabelListForValueInFilter(String filter) {
		List<String> configs = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass> line : lineList) {
				if (line.isValueInFilter(filter)) {
					configs.add(line.getKey());
				}
			}
		}
		return configs;
	}
	
	/**
	 * Get configuration list from the block for the given category
	 * @param category the {@code String} category to filter with
	 * @return List of configurations following the request
	 */	
	List<String> getLabelListForCategory(String category) {
		List<String> configs = new ArrayList<String>();
		if (category != null) {
			for (Generic_Line<ValueClass> line : lineList) {
				if (line.isValueFromCategory(category)) {
					configs.add(line.getKey());
				}
			}
		}
		return configs;
	}
	
	// --------------------------------------------------------------
	// private Methods
	//
//	/**
//	 * @return validationData
//	 */	
//	private Abstract_ValidData<ValueClass> getValueValidationData() {
//		return valueValidation;
//	}
//
//	/**
//	 * Search for selected configuration, if none, add it
//	 * @param config {@code String} configuration name
//	 */
//	private void addConfigIfNone(String config) {
//		if (get(config) == null) {
//			add(config);
//		}
//	}
	
	/**
	 * Search for selected configuration
	 * @param config {@code String} configuration name
	 * @return the configuration line, or null if none
	 */
	private Generic_Line<ValueClass> get(String config) {
		if (config != null) {
			for (Generic_Line<ValueClass> line : lineList) {
				if (line.getKey().equalsIgnoreCase(config)) {
					return(line);
				}
			}			
		}
		return null;
	}

}
