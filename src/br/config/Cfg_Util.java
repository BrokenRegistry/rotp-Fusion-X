
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import jdk.internal.math.DoubleConsts;
//import jdk.internal.math.FDBigInteger;

public class Cfg_Util {
	
	public static final List<String> YES_LIST     = List.of("YES", "TRUE");
	public static final List<String> NO_LIST      = List.of("NO", "FALSE");
	public static final List<String> BOOLEAN_LIST = List.of("YES", "NO", "TRUE", "FALSE");
	public static final String  CAPITALIZE_REGEX  = "((?<= )|(?<=_)|(?<=-)|(?<=/)|(?<=\\[))";
//	public static final String  PARAMETERS_SEPARATOR  = ",";
//	public static final String  RANDOM_ID             = "RANDOM";	
	public static final Boolean BOOLEAN_DEFAULT_VALUE = false;
	public static final String  STRING_DEFAULT_VALUE  = "";
	public static final char    CHAR_DEFAULT_VALUE    = ' ';
	public static final Byte    BYTE_DEFAULT_VALUE    = 0;
	public static final Short   SHORT_DEFAULT_VALUE   = 0;
	public static final Integer INTEGER_DEFAULT_VALUE = 0;
	public static final Long    LONG_DEFAULT_VALUE    = 0L;
	public static final Float   FLOAT_DEFAULT_VALUE   = 0.0F;
	public static final Double  DOUBLE_DEFAULT_VALUE  = 0.0D;	
	public static final Integer LINE_SPLIT_POSITION   = 20; // Label - Value Separator Position	
	public static final Integer END_COMMENT_POSITION  = 30; // End of line comment position
	
	public static final List<String> ENABLE_GAME_CHANGE_LIST  = 
			List.of("FILE TO GAME");
	public static final List<String> ENABLE_LOAD_LIST  = 
			List.of("LOAD", "BOTH", "FILE TO UI");
	public static final List<String> ENABLE_WRITE_LIST = 
			List.of("SAVE", "BOTH", "UI TO FILE", "GAME TO FILE", "INITIAL TO FILE",
					"UI UPDATE FILE", "GAME UPDATE FILE", "INITIAL UPDATE FILE");
	public static final List<String> ENABLE_VALID_LIST = 
			List.of("BOTH", "SAVE", "LOAD", "NO", "FILE TO UI", "FILE TO GAME", 
					"UI TO FILE", "GAME TO FILE", "INITIAL TO FILE",
					"UI UPDATE FILE", "GAME UPDATE FILE", "INITIAL UPDATE FILE");

	private static Boolean defaultCaseSensitivity = false;
	
    // ==================================================
    // Constructors
    //
	protected Cfg_Util() {}
	
    // ==================================================
    // Setters and Getters
    //
	public static void setDefaultCaseSensitivity(Boolean newValue) {
		defaultCaseSensitivity = newValue;
	}
	public static Boolean getDefaultCaseSensitivity() {
		return defaultCaseSensitivity;
	}
    // ==================================================
    // Simple Converters
    //
//	/**
//	 * @return the Byte value, BYTE_DEFAULT_VALUE if none
//	 */
//	public static Byte toByte(String source) {
//        return getOrDefault(source, BYTE_DEFAULT_VALUE);
//	}
//	/**
//	 * @return the Short value, SHORT_DEFAULT_VALUE if none
//	 */
//	public static Short toShort(String source) {
//        return getOrDefault(source, SHORT_DEFAULT_VALUE);
//	}
//	/**
//	 * @return the Integer value, INTEGER_DEFAULT_VALUE if none
//	 */
//	public static Integer toInteger(String source) {
//        return getOrDefault(source, INTEGER_DEFAULT_VALUE);
//	}
//	/**
//	 * @return the Float value, FLOAT_DEFAULT_VALUE if none
//	 */
//	public static Float toFloat(String source) {
//        return getOrDefault(source, FLOAT_DEFAULT_VALUE);
//	}

    // ==================================================
    // Converters with default values
    //
	/**
	 * @param source 
	 * @param defaultValue 
	 * @return the Byte value, defaultValue if none
	 */
	public static Byte getOrDefault(String source, Byte defaultValue) {
		if (testForNumeric(source)) {
			return Byte.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Short value, defaultValue if none
	 */
	public static Short getOrDefault(String source, Short defaultValue) {
		if (testForNumeric(source)) {
			return Short.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Integer value, defaultValue if none
	 */
	public static Integer getOrDefault(String source, Integer defaultValue) {
		if (testForNumeric(source) && testForInteger(source)) {
			return Integer.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Long value, defaultValue if none
	 */
	public static Long getOrDefault(String source, Long defaultValue) {
		if (testForNumeric(source)) {
			return Long.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Float value, defaultValue if none
	 */
	public static Float getOrDefault(String source, Float defaultValue) {
		if (testForNumeric(source)) {
			return Float.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Double value, defaultValue if none
	 */
	public static Double getOrDefault(String source, Double defaultValue) {
		if (testForNumeric(source)) {
			return Double.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the boolean value, defaultValue if none
	 */
	public static boolean getOrDefault(String source, Boolean defaultValue) {
    	if (YES_LIST.contains(toKey(source))) {
    		return true;
    	}
    	if (NO_LIST.contains(toKey(source))) {
    		return false;
    	}
        return defaultValue;
	}
    // ==================================================
    // Tests Methods
    //
	/**
	 * @return true if Empty or null
	 */
	public static boolean isEmpty(String source) {
	    return (source == null || source.isEmpty());
    }
	/**
	 * @return true if Blank, Empty or null
	 */
	public static boolean isBlank(String source) {
	    return (source == null || source.isBlank());
    }
	/**
	 * check if is member of BOOLEAN_LIST
	 */
	public static boolean testForBoolean(String source) {
        return BOOLEAN_LIST.contains(clean(source));
    }
	/**
	 * Validate if {@code String} could be converted or rounded
	 * to {@code Integer} then validate if the result is
	 * inside the boundaries
	 * @param source the {@code String} to analyze
	 * @param min    the lower boundary
	 * @param max    the upper boundary
	 * @return       is Valid
	 */
	public static boolean testIntegerBoundaries(String source, int min, int max) {
		Integer value = toInteger(source);
		return (value != null
				&& value >= min
				&& value <= max);
	}
	/**
	 * Validate if {@code String} could be converted 
	 * or rounded to Integer
	 * @param source the {@code String} to analyze
	 * @return {@code Boolean} may be converted
	 */
	public static boolean testForInteger(String source) {
		return toInteger(source) != null;
	}
	// ==================================================
    // Other Methods
    //
	/**
	 * Remove first Space if one. 
	 * Originally done to restore original comment
	 * after removing the comment key, as one space
	 * was added after the key.
	 * @param string the {@code String} to process
	 * @return the processed  {@code String}
	 */
	public static String removeFirstSpace (String string) {
		if (string == null ) {
			return null;
		}
		return (string.charAt(0) == ' ') ? 
				string.substring(1) : string;
	}
	/**
	 * Convert {@code objects} to  {@code String}
	 * null {@code objects} are replaced by Empty {@code String}
	 * @param obj the {@code objects} to process
	 * @return the processed {@code String}
	 */
	public static String neverNull(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString(); 
    }
	/**
	 * Convert {@code objects} to  {@code String} and strip them.
	 * null {@code objects} are replaced by Empty {@code String}
	 * @param obj the {@code objects} to process
	 * @return the cleaned {@code String}
	 */
	public static String clean(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString().strip(); 
    }
	/**
	 * Strip and convert to upper case
	 * @param source the {@code String} to process
	 * @return Upper Case of stripped string, never null
	 */
	public static String toKey(String source) {
		return clean(source).toUpperCase();
	}
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 * @param source the {@code String} to process
	 * @return the processed {@code String} Sentence
	 */
	public static String toSentence(String source) {
		String result = "";
		String value = clean(source).toLowerCase();
		if (value.length() > 0) {
			String[] elements = value.split("[a-z]", 1);
			for(String s : elements) {
				result += s.substring(0, 1).toUpperCase();
				result += s.substring(1);
			}
		}
		return result.strip();
	}
	/**
	 * Strip and return every word capitalized, never null
	 * @param source the {@code String} to process
	 * @return the processed {@code String}
	 */
	public static String capitalize(String source) {
		String result = "";
		String value = clean(source);
		if (value.length() > 0) {
			String[] elements = value.toLowerCase().split(CAPITALIZE_REGEX);
			for(String s : elements) {
				result += s.substring(0, 1).toUpperCase();
				result += s.substring(1);
			}
		}
		return result;
	}
	/**
	 * Strip and return every word capitalized, never null
	 *  or only first word
	 * @param source the {@code String} to process
	 * @param onlyFirstWord ... every word otherwise
	 * @return the processed {@code String}
	 */
	public static String capitalize(String source, boolean onlyFirstWord) {
			if (onlyFirstWord) {
				return toSentence(source);
			}
			else {
				return capitalize(source);				
			}
	}
	/**
	 * Convert a {@code String} with several "_" to a more user friendly one
	 * @param option the {@code String} Option 
	 * @return  the capitalized last element of the {@code String} (after "_")
	 */
	public static String suggestedOptionToLabel (String option) {
		return toSentence(option.substring(option.lastIndexOf("_") + 1));
	}
	/**
	 * Convert a {@code Cfg_Entry} value with several "_" 
	 * to a more user friendly one
	 * @param option the {@code Cfg_Entry} Option
	 * @return  {@code Cfg_Entry} with value being 
	 *      the capitalized last element of the {@code String} (after "_")
	 */
	public static Cfg_Entry suggestedOptionToLabel (Cfg_Entry option) {
		return new Cfg_Entry(suggestedOptionToLabel (option).toString());
	}
	/**
	 * Check the validity of the source and return a valid one
	 * @param source the {@code String} to validate
	 * @param defaultValue the {@code String} to return 
	 * @return the source or the default value
	 */
	public static String getOrDefault(String source, String defaultValue) {
    	if (isBlank(source)) {
    		return defaultValue;
    	}
        return source;
	}
	// ==================================================
    // Random Generation Methods
    //
	/**
	 * Generate a random {@code Boolean}
	 * @return a random {@code Boolean}
	 */
	public static Boolean getBooleanRandom() {
		return ThreadLocalRandom.current().nextBoolean();
	}		
	/**
	 * Check boundaries to avoid error throwing 
	 * and generate random {@code Double} number
	 * @param min inclusive minimum bound
	 * @param max exclusive maximum bound
	 * @return random {@code Double} value in the specified range
	 * <br> if min = max : return min
	 */
	public static Double nextRandomDouble(Double min, Double max) {
		if (isFiniteDouble(min) && isFiniteDouble(max)) { // also test for null
			if (max.doubleValue() == min.doubleValue()) {
				return min;
			}
			if (max < min) {
				return ThreadLocalRandom.current().nextDouble(max, min);
			}
			return ThreadLocalRandom.current().nextDouble(min, max);
		}
		return null; // what else?
	}
	/**
	 * Check boundaries to avoid error throwing 
	 * and generate random {@code Long} number
	 * @param min inclusive minimum bound
	 * @param max exclusive maximum bound
	 * @return random {@code Long} value in the specified range
	 * <br> if min = max : return min
	 */
	public static Long nextRandomLong(Long min, Long max) {
		if (min == null || max == null) {
			return null; // what else?
		}
		if (max.longValue() == min.longValue()) {
			return min;
		}
		return max < min 
			? ThreadLocalRandom.current().nextLong(max, min)
			: ThreadLocalRandom.current().nextLong(min, max);
	}
	// ==============================================================
    // String Conversion Tools
    //
	/**
	 * Convert {@code String} to {@code Boolean}
	 * @param string 
	 * @return the {@code Boolean} value, {@code null} if none
	 */
	public static Boolean toBoolean(String string) {
		if (string != null) {
	    	if (YES_LIST.contains(toKey(string))) {
	    		return true;
	    	}
	    	if (NO_LIST.contains(toKey(string))) {
	    		return false;
	    	}
		}
        return null;
	}
	/**
	 * Convert a {@code Boolean} to {@code String}
	 * @param   value   the {@code Boolean} to be converted
	 * @return the boolean value as {@code String}.
	 *  <br>   - true  = "YES"
	 *  <br>   - false = "NO"
	 *  <br>   - null  = "null"
	 */
	public static String toYesNoString(Boolean value) {
		if (value == null) {
			return "null";
		}
		return value ? "YES" : "NO";
	}
	/**
	 * Test if String is Numeric Value
	 * @param string the {@code String} to be tested.
     * @return true if {@code Double} compatible value was found
	 */
	public static boolean testForNumeric(String string) {
		return toDouble(string) != null;
    }
	/**
	 * Test if String is usable Numeric Value: Not NAN nor Infinity
	 * @param string the {@code String} to be tested.
	 * @return true if Finite {@code Double} was found
	 */
	public static boolean testForFiniteNumeric(String string) {
		return isFiniteDouble(toDouble(string));
    }
	/**
	 * Check {@code Double} for null NaN or Infinity
	 * @param value the {@code Double} to be checked
     * @return the {@code Boolean} result
	 */
	public static Boolean isFiniteDouble(Double value) {
		return (value != null && Double.isFinite(value));
	}
	/**
	 * Convert NaN and Infinity are converted to Null
     * @param value the {@code Double} to be checked and adjusted.
     * @return the {@code Double} true numeric or null
	 */
	public static Double toFiniteDouble(Double value) {
		return isFiniteDouble(value) ? value : null;
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a {@code null} value
	 * NaN and Infinity are converted to Null
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @return the {@code Double} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Double} was found
	 */
	public static Double toFiniteDouble(String string) {
		return toFiniteDouble(toDouble(string));
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a {@code null} value
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @return the {@code Double} found in the string
     *         or {@code null} if no {@code Double} was found
	 */
	public static Double toDouble(String string) {
		return toDoubleOrDefault(string, (Double) null);
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a default value
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @param onWrong the {@code Double} containing return on error value.
     * @return the {@code Double} found in the string
     *         or the on error value if no {@code Double} was found
	 */
	public static Double toDoubleOrDefault(String string, Double onWrong) {
		if (string == null) {
			return onWrong;
		}
		try {
			return Double.valueOf(string);
		} catch (Exception e) {
			return onWrong;
		}
	}
	/**
	 * Convert (round) {@code Double} to {@code Long} or return a default value 
	 * @param value   the {@code Double} to be converted
	 * @param onWrong the value to return if the conversion fail
	 * @return the converted value or onWrong
	 */
	public static Long toLong(Double value, Long onWrong) {
		if (value != null
				&& value <= Long.MAX_VALUE 
				&& value >= Long.MIN_VALUE) {
			return Math.round(value);
		}
		return onWrong;
	}
	/**
	 * Convert String to {@code Long} without throwing errors
	 * they are replaced by returning a {@code null} value
     * @param string the {@code String} containing the {@code Long} representation to be parsed.
     * @return the {@code Long} found in the string
     *         or {@code null} if no {@code Long} was found
	 */
	public static Long toLong(String string) {
		return toLongOrDefault(string, (Long) null);
	}
	/**
	 * Convert String to {@code Long} without throwing errors
	 * they are replaced by returning a default value
     * @param string the {@code String} containing the 
     *               {@code Long} representation to be parsed.
     * @param onWrong the {@code Long} containing return on error value.
     * @return the {@code Long} found in the string
     *  or the on error value if no {@code Long} was found
	 */
	public static Long toLongOrDefault(String string, Long onWrong) {
		if (string == null) {
			return onWrong;
		}
		// Try standard String to Long
		try {
			return Long.valueOf(string);
		} catch (Exception e) { }
		// Try thru String to Double
		return toLong(toDoubleOrDefault(string, (Double) null), onWrong);
	}
	/**
	 * Convert String to {@code Integer} without throwing errors
	 * they are replaced by returning a {@code null} value
     * @param string the {@code String} containing the 
     *               {@code Integer} representation to be parsed.
     * @return       the {@code Integer} found in the string
     *                or {@code null} if no {@code Integer} was found
	 */
	public static Integer toInteger(String string) {
		return toIntegerOrDefault(string, (Integer) null);
	}
	/**
	 * Convert String to {@code Integer} without throwing errors
	 * they are replaced by returning a default value
     * @param string the {@code String} containing 
     *               the {@code Integer} representation to be parsed.
     * @param onWrong the {@code Integer} containing return on error value.
     * @return the {@code Integer} found in the string
     *  or the on error value if no {@code Integer} was found
	 */
	public static Integer toIntegerOrDefault(String string, Integer onWrong) {
		Long lg = toLong(string);
		if (lg != null 
				&& lg <= Integer.MAX_VALUE 
				&& lg >= Integer.MIN_VALUE) { 
			return lg.intValue();
		}
		return onWrong;
	}
}
