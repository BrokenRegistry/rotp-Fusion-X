package br.configurationManager.src.main.java;

import java.util.ArrayList;
import java.util.List;

import br.config.Cfg_ValidationData;

/**
 * @param <ValueClass> The class of Values
 */
abstract class Abstract_Block<ValueClass> extends Abstract_ToPrint {

	private List<Abstract_Line<ValueClass>> lineList;
	private Abstract_ValidData<ValueClass>  valueValidation;
	private Valid_String keyValidation;

    // ==================================================
    // Constructors
    //
	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 * @param validationData the values validation parameters
	 */	
	Abstract_Block(Abstract_ValidData<ValueClass> validationData) {
		valueValidation = validationData;
		keyValidation   = new Valid_String();
		lineList = new ArrayList<Abstract_Line<ValueClass>>();
	}
	
	// ==================================================
    // Abstract Methods Request
    //
	
	// ==================================================
    // Setters
    //
	Abstract_Block<ValueClass> put(Abstract_Line <ValueClass> newLine) {
		// TODO
		return this;
	}

	/**
	 * add a new user configuration
	 * @param newLine the new configuration line
	 * @return This for chaining purpose
	 */
	Abstract_Block<ValueClass> put(String newLine) {
		// TODO
		return this;
	}
	
	
	// ==================================================
    // Getters
    //

}
