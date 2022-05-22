package br.profileManager.src.main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <ValueClass> The class of Values
 */
class Generic_Block<
		ValueClass, 
		ValidClass extends Abstract_ValidData<ValueClass>> extends ToPrint {

	private List<Generic_Line<ValueClass, Abstract_ValidData<ValueClass>>> lineList;
	private Abstract_ValidData<ValueClass> valueValidation;

	// ==================================================
    // Constructors
    //
	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 */	
	@SuppressWarnings("unused")
	private Generic_Block() {} // no empty block allowed

	/**
	 * New Abstract_Block<ValueClass> with the validation parameter Type
	 * @param valueValidationData  the values validation parameters
	 */	
	Generic_Block(Abstract_ValidData<ValueClass> valueValidationData) {	
		valueValidation = valueValidationData;
		lineList = new ArrayList<Generic_Line<ValueClass, Abstract_ValidData<ValueClass>>>();
	}

	// ==================================================
    // Setters
    //
	/**
	 * remove a profile from the list
	 * @param profile profile name
	 * @return this for chaining purpose
	 */
	protected Generic_Block<ValueClass, ValidClass> remove(String profile) {
		if (exist(profile)) {
			lineList.remove(get(profile));
		}
		return this;
	}

	/**
	 * add a new user profile (overwrite)
	 * @param    newLine the new {@code String} profile line
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass, ValidClass> add(String newLine) {
		add(new Generic_Line<ValueClass,
				Abstract_ValidData<ValueClass>>(
						valueValidation, newLine));
		return this;
	}

	/**
	 * add a new user profile  (overwrite)
	 * @param    profile   the {@code String} profile name
	 * @param    userEntry the {@code String} entry value
	 * @param    comment   the {@code String} the comment
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass, ValidClass> add(
			String profile, String userEntry, String comment) {
		remove(profile);
		lineList.add(new Generic_Line<ValueClass, Abstract_ValidData<ValueClass>>(
				valueValidation,
				profile,
				userEntry,
				comment));
		return this;
	}

	/**
	 * add a new user profile  (overwrite)
	 * @param    profile   the {@code String} profile name
	 * @param    userEntry the {@code String} entry value
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass, ValidClass> add(String profile, String userEntry) {
		remove(profile);
		lineList.add(new Generic_Line<ValueClass, Abstract_ValidData<ValueClass>>(
				valueValidation,
				profile,
				userEntry));
		return this;
	}

	/**
	 * add a new user profile  (overwrite)
	 * @param    newLine the new {@code Line<ValueClass>} profile line
	 * @return   This for chaining purpose
	 */
	Generic_Block<ValueClass, ValidClass> 
			add(Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> newLine) {
		remove(newLine.getName());
		lineList.add(newLine);
		return this;
	}

	/**
	 * add missing profiles as given ones
	 * @param profileList profiles list that should be
	 * @param newLine Missing profiles template
	 */
	void addMissing(String profile) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line = get(profile);
		if (PMutil.getForceCreationMissingProfile() 
				&& line == null) { // none exist...
			add(profile);           // add the missing one
		}
	}

	/**
	 * add missing profiles as given ones
	 * @param profileList profiles list that should be
	 * @param newLine Missing profiles template
	 */
	void addMissing(List<String> profileList) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line;
		for (String profile : profileList) {
			line = get(profile);
			if (PMutil.getForceCreationMissingProfile() 
					&& line == null) { // none exist...
				add(profile);           // add the missing one
			}
		}
	}
	// ==================================================
    // Testers
	//
	/**
	 * Test if a valid value may be returned from user profile
	 * @param profile profile name
	 * @return {@code Boolean} <b>true</b> if user profile is valid
	 */
	protected Boolean isValid(String profile) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line = get(profile);
		if (line != null) {
			return line.isValidValue();
		}
		return false;
	}
	
	/**
	 * Test for Blank value
	 * @param profile profile name
	 * @return {@code Boolean} <b>true</b> if user profile is blank, 
	 *                         <b>null</b> if none 
	 */
	protected Boolean isBlankValue(String profile) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line = get(profile);
		if (line != null) {
			return line.isBlankValue();
		}
		return null;
	}

	/**
	 * Test if a profile is already present
	 * @param profile profile name
	 * @return {@code Boolean} the answer
	 */
	protected Boolean exist(String profile) {
		return get(profile) != null;
	}

	// ==================================================
    // Getters
    //
	/**
	 * Get the profile value
	 * @param profile profile name
	 * @return the value as {@code ValueClass} or null if none
	 */	
	 ValueClass getValue(String profile) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line = get(profile);
		if (line == null) { // none exist... so empty
			return null;
		}
		return line.getValue();
	}

	/**
	 * Get the profile value or return the default one
	 * @param profile profile name
	 * @param defaultValue The value to return if none
	 * @return the value as {@code ValueClass}
	 */	
	 ValueClass getValue(String profile, ValueClass defaultValue) {
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line = get(profile);
		if (line == null) { // none exist... so empty
			return defaultValue;
		}
		return line.getValue();
	}
		
	/**
	 * Get the profile Line or return the default one
	 * @param profile profile name
	 * @return the profile Line as {@code Gen_Line} or null if none
	 */	
	 Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> getLine(String profile) {
		 return get(profile);
	}
			
	/**
	 * @return print ready String with all the block content
	 */	
	@Override public String toString() {
		String out = "";
		for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
			if (out.isBlank()) {
				out = line.toPrint();
			} else {
				out += System.lineSeparator() + line.toPrint();
			}
		}
		return out;
	}

	/**
	 * toString filtered by profile list
	 * @return print ready String with profileList block content
	 */	
	String toString(List<String> profileList) {
		String out = "";
		Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line;
		for (String profile : profileList) {
			line = get(profile);
			if (PMutil.getForceCreationMissingProfile() 
					&& line == null) { // none exist...
				add(profile);           // add the missing one
				line = get(profile);
			}
			if (out.isBlank()) {
				out = line.toString();
			} else {
				out += System.lineSeparator() + line.toString();
			}
		}
		return out;
	}

	/**
	 * Get profile list from the block
	 * @return List of all profiles in the block
	 */	
	List<String> getProfileList() {
		List<String> profiles = new ArrayList<String>();
		for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
			if (line.getName() != null) {
				profiles.add(line.getName());
			}
		}
		return profiles;
	}

	/**
	 * Get profile list from the block filtered by value
	 * @param filter the {@code String} filter to be equal
	 * @return List of profiles following the request
	 */	
	List<String> getProfileListForValueEqualsFilter(String filter) {
		List<String> profiles = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
				if (line.isValueAsFilter(filter)) {
					profiles.add(line.getName());
				}
			}
		}
		return profiles;
	}

	/**
	 * Get profile list from the block filtered by value
	 * @param filter the {@code String} filter to be contained
	 * @return List of profiles following the request
	 */	
	List<String> getProfileListForValueContainsFilter(String filter) {
		List<String> profiles = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
				if (line.isFilterInValue(filter)) {
					profiles.add(line.getName());
				}
			}
		}
		return profiles;
	}
	
	/**
	 * Get profile list from the block filtered by value
	 * @param filter the {@code String} containing filter
	 * @return List of profiles following the request
	 */	
	List<String> getProfileListForValueInFilter(String filter) {
		List<String> profiles = new ArrayList<String>();
		if (filter != null) {
			for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
				if (line.isValueInFilter(filter)) {
					profiles.add(line.getName());
				}
			}
		}
		return profiles;
	}
	
	/**
	 * Get profile list from the block for the given category
	 * @param category the {@code String} category to filter with
	 * @return List of profiles following the request
	 */	
	List<String> getProfileListForCategory(String category) {
		List<String> profiles = new ArrayList<String>();
		if (category != null) {
			for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
				if (line.isValueFromCategory(category)) {
					profiles.add(line.getName());
				}
			}
		}
		return profiles;
	}
	
	// --------------------------------------------------------------
	// private Methods
	//
	/**
	 * Search for selected profile
	 * @param profile {@code String} profile name
	 * @return the profile line, or null if none
	 */
	private Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> get(String profile) {
		if (profile != null) {
			for (Generic_Line<ValueClass, Abstract_ValidData<ValueClass>> line : lineList) {
				if (line.getName().equalsIgnoreCase(profile)) {
					return(line);
				}
			}			
		}
		return null;
	}
}
