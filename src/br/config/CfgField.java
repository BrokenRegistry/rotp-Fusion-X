
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
import java.util.concurrent.ThreadLocalRandom;


/**
 * The field content will never be null
 * and will be striped
 */
public class CfgField extends UserField{
	
	public static final List<String> YES_LIST     = List.of("YES", "TRUE");
	public static final List<String> NO_LIST      = List.of("NO", "FALSE");
	public static final List<String> BOOLEAN_LIST = List.of("YES", "NO", "TRUE", "FALSE");
	public static final List<String> ENABLE_LOAD_LIST  = 
			List.of("LOAD", "BOTH", "FILE TO UI", "FILE TO GAME");
	public static final List<String> ENABLE_WRITE_LIST = 
			List.of("SAVE", "BOTH", "UI TO FILE", "GAME TO FILE", "INITIAL TO FILE",
					"UI UPDATE FILE", "GAME UPDATE FILE", "INITIAL UPDATE FILE");
	public static final List<String> ENABLE_VALID_LIST = 
			List.of("BOTH", "SAVE", "LOAD", "NO", "FILE TO UI", "FILE TO GAME", 
					"UI TO FILE", "GAME TO FILE", "INITIAL TO FILE",
					"UI UPDATE FILE", "GAME UPDATE FILE", "INITIAL UPDATE FILE");
	public static final Boolean BOOLEAN_DEFAULT_VALUE = false;
	public static final String  STRING_DEFAULT_VALUE  = "";
	public static final char    CHAR_DEFAULT_VALUE    = ' ';
	public static final Byte    BYTE_DEFAULT_VALUE    = 0;
	public static final Short   SHORT_DEFAULT_VALUE   = 0;
	public static final Integer INTEGER_DEFAULT_VALUE = 0;
	public static final Long    LONG_DEFAULT_VALUE    = 0L;
	public static final Float   FLOAT_DEFAULT_VALUE   = 0.0F;
	public static final Double  DOUBLE_DEFAULT_VALUE  = 0.0D;
	public static final String  PARAMETERS_SEPARATOR  = ",";
	public static final String  RANDOM_ID             = "RANDOM";
	
    // ==================================================
    // Constructors
    //
	public CfgField() {}
	public CfgField(Object newField) {
        set(newField);
    }
    // ==================================================
    // Setters
    //
	/**
	 * Convert and set a new String value
	 */
	public void set(Object newValue) {
		value = clean(newValue);
	}
	/**
	 * Never null and stripped / converted
	 */
	public void set(Boolean newValue) {
		value = toYesNoString(newValue); 
	}
    // ==================================================
    // Getters simple
    //
	/**
	 * return the Byte value, BYTE_DEFAULT_VALUE if none
	 */
	public Byte toByte() {
        return getOrDefault(BYTE_DEFAULT_VALUE);
	}
	/**
	 * return the Short value, SHORT_DEFAULT_VALUE if none
	 */
	public Short toShort() {
        return getOrDefault(SHORT_DEFAULT_VALUE);
	}
	/**
	 * return the Integer value, INTEGER_DEFAULT_VALUE if none
	 */
	public Integer toInteger() {
        return getOrDefault(INTEGER_DEFAULT_VALUE);
	}
	/**
	 * return the Long value, LONG_DEFAULT_VALUE if none
	 */
	public Long toLong() {
        return getOrDefault(LONG_DEFAULT_VALUE);
	}
	/**
	 * return the Float value, FLOAT_DEFAULT_VALUE if none
	 */
	public Float toFloat() {
        return getOrDefault(FLOAT_DEFAULT_VALUE);
	}
	/**
	 * return the Double value, DOUBLE_DEFAULT_VALUE if none
	 */
	public Double toDouble() {
        return getOrDefault(DOUBLE_DEFAULT_VALUE);
	}
	/**
	 * return the boolean value, BOOLEAN_DEFAULT_VALUE if none
	 */
	public boolean toBoolean() {
        return getOrDefault(BOOLEAN_DEFAULT_VALUE);
	}
    // ==================================================
    // Getters with default values
    //
	/**
	 * return the Byte value, defaultValue if none
	 */
	public Byte getOrDefault(Byte defaultValue) {
		if (isNumeric()) {
			return Byte.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the Short value, defaultValue if none
	 */
	public Short getOrDefault(Short defaultValue) {
		if (isNumeric()) {
			return Short.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the Integer value, defaultValue if none
	 */
	public Integer getOrDefault(Integer defaultValue) {
		if (isNumeric()) {
			return Integer.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the Long value, defaultValue if none
	 */
	public Long getOrDefault(Long defaultValue) {
		if (isNumeric()) {
			return Long.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the Float value, defaultValue if none
	 */
	public Float getOrDefault(Float defaultValue) {
		if (isNumeric()) {
			return Float.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the Double value, defaultValue if none
	 */
	public Double getOrDefault(Double defaultValue) {
		if (isNumeric()) {
			return Double.valueOf(value);
		}
        return defaultValue;
	}
	/**
	 * return the boolean value, defaultValue if none
	 */
	public boolean getOrDefault(boolean defaultValue) {
    	if (YES_LIST.contains(value.toUpperCase())) {
    		return true;
    	}
    	if (NO_LIST.contains(value.toUpperCase())) {
    		return false;
    	}
        return defaultValue;
	}
    // ==================================================
    // Tests Methods
    //
	/**
	 * check if is member of BOOLEAN_LIST
	 */
	public boolean isBoolean() {
        return isMemberOf(BOOLEAN_LIST);
    }
	/**
	 * check for the presence of "[0-9.]+"
	 */
	public boolean isNumeric() {
        return value.matches("[0-9.]+");
    }
	public boolean isValid(int min, int max) {
		int val = getOrDefault(min - 1);
		return (val >= min && val <= max);
	}
   // ==================================================
    // Very specific Tests Methods
    //
	/**
	 * check if it contains of RANDOM_ID
	 */
	public boolean isRandom() {
		return toKey().contains(RANDOM_ID);
	}
	/**
	 * check if is member of ENABLE_LOAD_LIST
	 */
	public boolean isLoadEnabled() {
		return isMemberOf(ENABLE_LOAD_LIST);
	}
	/**
	 * check if is member of ENABLE_WRITE_LIST
	 */
	public boolean isWriteEnabled() {
		return isMemberOf(ENABLE_WRITE_LIST);
	}
	/**
	 * check if is member of ENABLE_VALID_LIST
	 */
	public boolean isValidEnable() {
		return isMemberOf(ENABLE_VALID_LIST);
	}
	// ==================================================
    // Public Other Methods
    //
	/**
	 * Extract the parameters following head as String
	 */
	public String extractParametersString(String head) {
		return toKey().replace(head, "").strip();
	}
	/**
	 * Extract the parameters following head as String
	 */
	public String[] extractParameters(String head) {
		return extractParametersString(head).split(PARAMETERS_SEPARATOR);
	}
	/**
	 * Extract the parameters following head as Array
	 * And return as integer Array Value
	 */
	public int[] extractOrDefaultMinMaxParameters(String head, int min, int max) {
		int userMin = min;
		int userMax = max;
		String[] param = extractParameters(head);
		if (param.length > 0) {
			userMin = CfgField.getOrDefault(param[0], min);
			if (param.length > 1) {
				userMax = CfgField.getOrDefault(param[1], max);
			}
		}
		return new int[] {userMin, userMax} ;
	}
	/**
	 * Extract the parameters following RANDOM_ID as List
	 * And return as integer Array Value
	 */
	public int[] extractOrDefaultMinMaxRandomParameters(int min, int max) {
		return extractOrDefaultMinMaxParameters(RANDOM_ID, min, max);
	}

	// ==================================================
    // Public Static Methods
    //
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 */
	public static String toSentence(String string) {
		return new CfgField(string).toSentence();
	}
	 /**
	 * return Upper Case of stripped string, never null
	 */
	public static String toKey(String string) {
		return new CfgField(string).toKey();
	}
	/**
	 * Strip and return every word capitalized, never null
	 */
	public static String capitalize(String string) {
		return new CfgField(string).toCapitalized();
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
        return new CfgField(string).isNumeric();
    }
	/**
	 * return the integer value of string, defaultValue if none
	 */
	public static int getOrDefault(String string, int defaultValue) {
		return new CfgField(string).getOrDefault(defaultValue);
	}
	/**
	 * return the boolean value of string, defaultValue if none
	 */
	public static boolean getOrDefault(String string, boolean defaultValue) {
		return new CfgField(string).getOrDefault(defaultValue);
	}
	/**
	 * return the value of string, defaultValue if blank
	 */
	public static String getOrDefault(String string, String defaultValue) {
    	if (isBlank(string)) {
    		return defaultValue;
    	}
        return string;
	}
	/**
	 * return the boolean value as string.
	 *  true  = "YES"
	 *  false = "NO"
	 */
	public static String toYesNoString(Boolean b) {
		if (b == null) {
			b = BOOLEAN_DEFAULT_VALUE;
		}
		if (b) {
			return "YES";
		}
		return "NO";
	}
	/**
	 * check if string is member of RANDOM_LIST
	 */
	public static boolean isRandomValue(String string) {
		return new CfgField(string).isRandom();
	}  
	/**
	 * Return random Integer value between min and max, (inclusive)
	 */
	public static int getIntegerRandom(int min, int max) {
		int diff = max - min;
		if (diff == 0) {
			return min;
		}
		if (diff < 0) {
			return getIntegerRandom(max, min);
		}
		return ThreadLocalRandom.current().nextInt(diff + 1) + min; // +1 because last value is exclusive!!!
	}
	/**
	 * Return random Integer value between bound[0] and bound[1], (inclusive)
	 */
	public static Integer getIntegerRandom(int[] bound) {
		return getIntegerRandom(bound[0], bound[1]);
	}
	public static boolean getBooleanRandom() {
		return ThreadLocalRandom.current().nextBoolean();
	}
}
