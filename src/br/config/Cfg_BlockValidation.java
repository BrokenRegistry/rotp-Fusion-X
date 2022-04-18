package br.config;

import java.util.ArrayList;
import java.util.List;

public class Cfg_BlockValidation {
	// ------------------------------------------------------------------------
	// Variables Properties
    //
	private List<Cfg_LineValidation> lineList;
	private Cfg_ValidationData validationData;
	
	// ------------------------------------------------------------------------
	// Constructors and Helpers
	//
	/**
	 * New CfgBlockValidation with the Option Type
	 */	
	protected Cfg_BlockValidation(Cfg_ValidationData validationData) {
		resetUserSettings();
		setValidationData(validationData);
	}
	/**
	 * Remove all user Settings, but keep Validation Data
	 */	
	protected void resetUserSettings() {
		lineList = new ArrayList<Cfg_LineValidation>();
	}
	// --------------------------------------------------------------
	// Protected Methods
	//
	/**
	 * Add or change a new CfgLineValidation from label and value
	 */	
	protected void put(String label, String value) {
		put(label, new Cfg_Entry(value));
	}
	/**
	 * Add or change a new CfgLineValidation from label and value
	 */	
	protected void put(String label, Cfg_Entry value) {
		// Get or Create CfgLineValidation
		Cfg_LineValidation cfgLine = get(label);
		if (cfgLine == null) {
			cfgLine = new Cfg_LineValidation(validationData, label);
		}
		cfgLine.setValue(value);
	}
	/**
	 * Add a new CfgLineValidation from File line
	 */	
	protected void put(String newLine) {
		// Create new CfgLineValidation
		Cfg_LineValidation newCfgLine = 
				new Cfg_LineValidation(validationData).newLine(newLine);
		if (newCfgLine == null) {
			return;
		}
		Cfg_Entry newLabel = newCfgLine.label();
		// Check if label already exist
		Cfg_LineValidation line = get(newLabel.toString());
		if (line == null) { // none exist... so add it
			lineList.add(newCfgLine);
			return;
		}
		// replace with the new one
		line.newLine(newLine);
	}
	/**
	 * Test if a valid non blank value may be returned from user choice
	 */
	protected Boolean isValid(String label) {
		Cfg_LineValidation cfgLine = this.get(label);
		if (cfgLine != null) {
			return cfgLine.isValid();
		}
		return null;
	}
	/**
	 * @param label user Label of choice
	 * @return true if user choice is blank
	 */
	protected Boolean isBlankValue(String label) {
		Cfg_LineValidation cfgLine = this.get(label);
		if (cfgLine != null) {
			return cfgLine.rawValue().isBlank();
		}
		return null;
	}
	/**
	 * @param label user Label of choice
	 * @return validated option as Boolean or null if none
	 */	
	protected Boolean getBooleanOption(String label) {
		return Cfg_Util.toBoolean(getStringOption(label));
	}
	/**
	 * @param label user Label of choice
	 * @return validated option as String or null if none
	 */	
	protected String getStringOption(String label) {
		Cfg_LineValidation cfgLine = get(label);
		if (cfgLine == null) { // none exist...
			return null;
		}
		return cfgLine.getStringOption();
	}
	/**
	 * @param label user Label of choice
	 * @return validated option as Numeric or null if none
	 */	
	protected Cfg_Value getNumericOption(String label) {
		Cfg_LineValidation cfgLine = get(label);
		if (cfgLine == null) { // none exist... so empty
			return null;
		}
		return cfgLine.getNumericOption();
	}
	/**
	 * @return print ready String with all the block content
	 */	
	protected String toPrint() {
		String out = "";
		for (Cfg_LineValidation cfgLine : lineList) {
			out += cfgLine.toPrint();
		}
		return out;
	}
	/**
	 * @return print ready String with labelList block content
	 * add missing labels as blank ones
	 */	
	protected String toPrint(List<String> labelList) {
		String out = "";
		Cfg_LineValidation cfgLine;
		for (String label : labelList) {
			cfgLine = get(label);
			if (cfgLine == null) { // none exist...
				put(label); // create the missing one
				cfgLine = get(label);
			}
			if (out.isBlank()) {
				out = cfgLine.toPrint();
			}
			out += System.lineSeparator() + cfgLine.toPrint();
		}
		return out;
	}
	/**
	 * Get labels present in the block
	 * @return List of all labels in the block
	 */	
	protected List<String> getLabelList() {
		List<String> labels = new ArrayList<String>();
		for (Cfg_LineValidation line : lineList) {
			if (line.label() != null) {
				labels.add(line.label().toString());
			}
		}
		return labels;
	}
	/**
	 * Get labels present in the block filtered by value
	 * @param filter value needed for label selection
	 * @return List of filtered labels in the block
	 */	
	protected List<String> getLabelListIfValueEqualsFilter(Cfg_Entry filter) {
		List<String> labels = new ArrayList<String>();
		if (filter != null) {
			for (Cfg_LineValidation line : lineList) {
				if (line.rawValue() != null 
						|| filter.equals(line.rawValue()) ) {
					labels.add(line.label().toString());
				}
			}			
		}
		return labels;
	}
	/**
	 * Get labels present in the block filtered by value
	 * @param filter value needed for label selection
	 * @return List of filtered labels in the block
	 */	
	protected List<String> getLabelListIfValueContainsFilter(Cfg_Entry filter) {
		List<String> labels = new ArrayList<String>();
		if (filter != null) {
			for (Cfg_LineValidation line : lineList) {
				if (line.rawValue() != null 
						|| line.rawValue().contains(filter)) {
					labels.add(line.label().toString());
				}
			}
		}
		return labels;
	}
	/**
	 * Get labels present in the block filtered by value
	 * @param filter value to be contained
	 * @return List of filtered labels in the block
	 */	
	protected List<String> getLabelListIfValueIsContainedByFilter(Cfg_Entry filter) {
		List<String> labels = new ArrayList<String>();
		if (filter != null) {
			for (Cfg_LineValidation line : lineList) {
				if (line.rawValue() != null 
						|| line.rawValue().isContained(filter)) {
					labels.add(line.label().toString());
				}
			}
		}
		return labels;
	}
	/**
	 * Get labels present in the block filtered by category
	 * @param filter value to be contained
	 * @return List of filtered labels in the block
	 */	
	protected List<String> getLabelListIfCategoryContainsFilter(Cfg_Entry filter) {
		List<String> labels = new ArrayList<String>();
		if (filter != null) {
			for (Cfg_LineValidation line : lineList) {
				if (line.rawValue() != null 
						|| filter.isContained(line.getCategory()) ) {
					labels.add(line.label().toString());
				}
			}
		}
		return labels;
	}
	/**
	 * @return validationData
	 */	
	protected Cfg_ValidationData validationData() {
		return validationData;
	}
	protected void addLabelIfNone(String label) {
		if (get(label) == null) {
			put(label);
		}
	}
	// --------------------------------------------------------------
	// Setters
	//
	private void setValidationData(Cfg_ValidationData validationData) {
		this.validationData = validationData;
	}
	// --------------------------------------------------------------
	// Getters
	//
	private Cfg_LineValidation get(String label) {
		if (label != null) {
			for (Cfg_LineValidation line : lineList) {
				if (line.label().equals(label)) {
					return(line);
				}
			}			
		}
		return null;
	}
}
