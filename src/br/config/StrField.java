
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

import java.util.List;
import java.util.Set;

public class StrField {
	
	public static final List<String> YES_LIST = List.of("YES", "TRUE");
	public static final List<String> NO_LIST  = List.of("NO", "FALSE");
	public static final String RANDOM_PARAMETERS_SEPARATOR = ",";
	public static final String RANDOM_ID = "RANDOM";
	
	private String strField = "";
	
    // ==================================================
    // Constructors
    //
	public StrField() {}
	public StrField(String newStrField) {
        set(newStrField);
    }
    // ==================================================
    // Setters and Getters
    //
	/**
	 * set a new String value
	 * Return current object to allow chaining
	 */
	public StrField set (String newValue) {
		strField = newValue;
		return this;
	}
	/**
	 * Null values will be converted to Empty
	 * Return current object to allow chaining
	 */
	public StrField neverNullSet (String newValue) {
		strField = neverNull(newValue); 
		return this;
	}
	/**
	 * Never null and stripped / converted
	 * Return current object to allow chaining
	 */
	public StrField cleanSet (String newValue) {
		strField = clean(newValue); 
		return this;
	}
	/**
	 * Never null and stripped / converted
	 * Return current object to allow chaining
	 */
	public StrField cleanSet (boolean newValue) {
		strField = toYesNoString(newValue); 
		return this;
	}
	/**
	 * Never null and stripped / converted
	 * Return current object to allow chaining
	 */
	public StrField cleanSet (int newValue) {
		strField = Integer.toString(newValue); 
		return this;
	}
	/**
	 * return raw value as String
	 */
	public String get() { 
		return strField;
	}
	/**
	 * return raw value, but never null
	 */
	public String toString() { 
		return strField;
	}
	/**
	 * return Upper Case of stripped strField, never null
	 */
	public String getAsKey() { 
		return clean(strField).toUpperCase();
	}
	/**
	 * return Upper Case of strField, never null
	 */
	public String getUpperCase() { 
		return strField.toUpperCase();
	}
	/**
	 * return Lower Case of strField, never null
	 */
	public String getLowerCase() { 
		return strField.toUpperCase();
	}
	/**
	 * return striped strField in lower case, never null,
	 * with first char to upper case,
	 * with every word capitalized if eachWord is true
	 */
	public String getCapitalized(boolean eachWord) { 
		if (eachWord) {
			return capitalize(strField);
		}
		return toSentence(strField);
	}
	/**
	 * return the integer value, onWrong if none
	 */
	public int getOrDefault(int onWrong) {
        return getOrDefault(strField, onWrong);
	}
	/**
	 * return the boolean value, onWrong if none
	 */
	public boolean getOrDefault(boolean onWrong) {
		return getOrDefault(strField, onWrong);
	}
	/**
	 * return the value, onWrong if blank
	 */
	public String getOrDefault(String onWrong) {
		return getOrDefault(strField, onWrong);
	}
    // ==================================================
    // Public Other Methods
    //
	/**
	 * Convert null strField to Empty
	 * Return current object to allow chaining
	 */
	public StrField strip() {
		if (strField == null) { 
			strField = "";
		}
		strField = strField.strip(); 
		return this;
	}
	/**
	 * Convert null strField to Empty
	 * Return current object to allow chaining
	 */
	public StrField neverNull() {
		strField = neverNull(strField);
		return this;
	}
	/**
	 * Strip and never null
	 * Return current object to allow chaining
	 */
	public StrField clean() {
		strField = clean(strField);
		return this;
    }
	/**
	 * true if Empty or null
	 */
	public boolean isEmpty() { 
		return strField.isEmpty();
	}
	/**
	 * true if Blank, Empty or null
	 */
	public boolean isBlank() {
	    return strField.isBlank();
    }
	/**
	 * check for the presence of "[0-9.]+"
	 */
	public boolean isNumeric() {
        return isNumeric(strField);
    }
	/**
	 * check if value as Key == string
	 */
	public boolean keyTest(String string) {
		return getAsKey().equalsIgnoreCase(string);
	}
	/**
	 * check if is valid member of List
	 */
	public boolean isMemberOf(Set<String> set) {
		return set.contains(getAsKey());
	}
	/**
	 * check if is valid member of List
	 */
	public boolean isMemberOf(List<String> list) {
		return list.contains(getAsKey());
	}
	/**
	 * check if is member of RANDOM_LIST
	 */
	public boolean isRandom() {
		return getAsKey().contains(RANDOM_ID);
	}
	/**
	 * Extract the parameters following RANDOM_ID as String
	 */
	public String extractRandomParametersString() {
		return getAsKey().replace(RANDOM_ID, "").strip();
	}
	/**
	 * Extract the parameters following RANDOM_ID as List
	 */
	public String[] extractRandomParameters() {
		return extractRandomParametersString().split(RANDOM_PARAMETERS_SEPARATOR);
	}
	/**
	 * Extract the parameters following RANDOM_ID as List
	 * And return as integer Value
	 */
	public int[] extractOrDefaultMinMaxRandomParameters(int min, int max) {
		int userMin = min;
		int userMax = max;
		String[] param = extractRandomParameters();
		if (param.length > 0) {
			userMin = StrField.getOrDefault(param[0], min);
			if (param.length > 1) {
				userMax = StrField.getOrDefault(param[1], max);
			}
		}
		return new int[] {userMin, userMax} ;
	}

	// ==================================================
    // Public Static Methods
    //
	/**
	 * true if Empty or null
	 */
	public static boolean isEmpty(String string) {
	    return (string == null || string.isEmpty());
    }
	/**
	 * true if Blank, Empty or null
	 */
	public static boolean isBlank(String string) {
	    return (string == null || string.isBlank());
    }
	/**
	 * Strip and never null
	 */
	public static String clean(String string) {
		return neverNull(string).strip(); 
    }
	/**
	 * convert null Strings to Empty Strings
	 */
	public static String neverNull(String string) {
		if (string == null) { 
			return "";
		}
		return string; 
    }
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 */
	public static String toSentence(String string) {
		String s = clean(string);
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	 /**
	 * return Upper Case of stripped string, never null
	 */
	public static String toKey(String string) {
		return clean(string).toUpperCase();
	}
	/**
	 * Strip and return every word capitalized, never null
	 */
	public static String capitalize(String string) {
		String result = "";
		String cs = clean(string);
		if (cs.length() > 0) {
			String[] elements = cs.toLowerCase().split("((?<= )|(?<=_)|(?<=-))");
			for(String s : elements) {
				result += s.substring(0, 1).toUpperCase();
				result += s.substring(1);
			}
		}
		return result;
	}
	/**
	 * return the capitalized last element of the string (after "_")
	 */
	public static String suggestedOptionToLabel (String option) {
		return toSentence(option.substring(option.lastIndexOf("_") + 1));
	}
	/**
	 * check for the presence of "[0-9.]+"
	 */
	public static boolean isNumeric(String string) {
        return clean(string).matches("[0-9.]+");
    }
	/**
	 * return the integer value of string, onWrong if none
	 */
	public static int getOrDefault(String string, int onWrong){
		if (isNumeric(string)) {
			return Integer.valueOf(clean(string));
		}
        return onWrong;
	}
	/**
	 * return the boolean value of string, onWrong if none
	 */
	public static boolean getOrDefault(String string, boolean onWrong) {
    	if (YES_LIST.contains(clean(string))) {
    		return true;
    	}
    	if (NO_LIST.contains(clean(string))) {
    		return false;
    	}
        return onWrong;
	}
	/**
	 * return the value of string, onWrong if blank
	 */
	public static String getOrDefault(String string, String onWrong) {
    	if (isBlank(string)) {
    		return onWrong;
    	}
        return string;
	}
	/**
	 * return the boolean value as string.
	 *  true  = "YES"
	 *  false = "NO"
	 */
	public static String toYesNoString(boolean b) { 
		return b ? "YES" : "NO";
	}
	/**
	 * check if string is member of RANDOM_LIST
	 */
	public static boolean isRandomValue(String string) {
		return new StrField(string).isRandom();
	}
}
