
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

import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FDBigInteger;

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
	 * @return the Byte value, defaultValue if none
	 */
	public static Byte getOrDefault(String source, Byte defaultValue) {
		if (isNumeric(source)) {
			return Byte.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Short value, defaultValue if none
	 */
	public static Short getOrDefault(String source, Short defaultValue) {
		if (isNumeric(source)) {
			return Short.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Integer value, defaultValue if none
	 */
	public static Integer getOrDefault(String source, Integer defaultValue) {
		if (isNumeric(source) && isInteger(source)) {
			return Integer.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Long value, defaultValue if none
	 */
	public static Long getOrDefault(String source, Long defaultValue) {
		if (isNumeric(source)) {
			return Long.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Float value, defaultValue if none
	 */
	public static Float getOrDefault(String source, Float defaultValue) {
		if (isNumeric(source)) {
			return Float.valueOf(source);
		}
        return defaultValue;
	}
	/**
	 * @return the Double value, defaultValue if none
	 */
	public static Double getOrDefault(String source, Double defaultValue) {
		if (isNumeric(source)) {
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
	public static boolean isBoolean(String source) {
        return BOOLEAN_LIST.contains(clean(source));
    }
	public static boolean isValidInteger(String source, int min, int max) {
		int val = getOrDefault(source, min - 1);
		return (val >= min && val <= max);
	}
	public static boolean isInteger(String source) {
		if (!isBlank(source)) {
		double d =  Double.valueOf(source);
		return d <= Integer.MAX_VALUE && d >= Integer.MIN_VALUE; 
		}
		return false;
	}
	// ==================================================
    // Other Methods
    //
	public static String removeFirstSpace (String str) {
		if (str == null ) {
			return null;
		}
		return (str.charAt(0) == ' ') ? 
		     str.substring(1) : str;
	}
	/**
	 * convert objects to String
	 * and null objects to Empty Strings
	 */
	public static String neverNull(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString(); 
    }
	/**
	 * convert objects to String and strip them
	 * and null objects to Empty Strings
	 */
	public static String clean(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString().strip(); 
    }
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 */
	public static String toSentence(String source) {
		String result = "";
		String value = neverNull(source).toLowerCase();
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
	 * return Upper Case of stripped string, never null
	 */
	public static String toKey(String source) {
		return clean(source).toUpperCase();
	}
	/**
	 * Strip and return every word capitalized, never null
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
	 * return the capitalized last element of the string (after "_")
	 */
	public static String suggestedOptionToLabel (String option) {
		return toSentence(option.substring(option.lastIndexOf("_") + 1));
	}
	/**
	 * return the capitalized last element of the string (after "_")
	 */
	public static Cfg_Entry suggestedOptionToLabel (Cfg_Entry option) {
		return new Cfg_Entry(suggestedOptionToLabel (option).toString());
	}
	/**
	 * return the value of string, defaultValue if blank
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
	 * Return a random Boolean
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
		if (isUsableDouble(min) && isUsableDouble(max)) {
			if (max == min) {
				return min;
			}
			return max < min 
				? ThreadLocalRandom.current().nextDouble(max, min)
				: ThreadLocalRandom.current().nextDouble(min, max);
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
		if (max == min) {
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
	 * @return the Boolean value, null if none
	 */
	public static Boolean toBoolean(String source) {
		if (source != null) {
	    	if (YES_LIST.contains(toKey(source))) {
	    		return true;
	    	}
	    	if (NO_LIST.contains(toKey(source))) {
	    		return false;
	    	}
		}
        return null;
	}
	/**
	 * @param   value   the {@code Boolean} to be converted
	 * @return the boolean value as {@code string}.
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
	 * Test if String is usable Numeric Value: Not NAN nor Infinity
	 */
	public static boolean isUsableNumeric(String string) {
		return isUsableDouble(toDouble(string));
    }
	/**
	 * Test if String is Numeric Value
	 */
	public static boolean isNumeric(String string) {
		if (string == null) {
			return false;
		}
		return toDouble(string) != null;
    }
	/**
	 * Check {@code Double} for null NaN or Infinity
	 * @param value the {@code Double} to be checked
     * @return the {@code Boolean} result
	 */
	public static Boolean isUsableDouble(Double value) {
		return (value != null 
				&& value != Double.NaN 
				&& value != Double.NEGATIVE_INFINITY
				&& value != Double.POSITIVE_INFINITY);
	}
	/**
	 * Convert NaN and Infinity are converted to Null
     * @param value the {@code Double} to be checked and adjusted.
     * @return the {@code Double} true numeric or null
	 */
	public static Double toUsableDouble(Double value) {
		return isUsableDouble(value) ? value : null;
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a {@code null} value
	 * NaN and Infinity are converted to Null
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @return the {@code Double} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Double} was found
	 */
	public static Double toUsableDouble(String string) {
		return toUsableDouble(toDouble(string));
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a {@code null} value
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @return the {@code Double} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Double} was found
	 */
	public static Double toDouble(String string) {
		if (string == null) {
			return null;
		}
		return StringToNumeric.parseDoubleOrNull(string.trim());
	}
	/**
	 * Convert String to Double without throwing errors
	 * they are replaced by returning a default value
     * @param string the {@code String} containing the {@code Double} representation to be parsed.
     * @param onWrong the {@code Double} containing return on error value.
     * @return the {@code Double} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Double} was found
	 */
	public static Double toDoubleOrDefault(String string, Double onWrong) {
		if (string == null) {
			return onWrong;
		}
		Double dbl = StringToNumeric.parseDoubleOrNull(string.trim());
		return dbl == null ? onWrong : dbl;
	}
	/**
	 * Convert String to Long without throwing errors
	 * they are replaced by returning a {@code null} value
     * @param string the {@code String} containing the {@code Long} representation to be parsed.
     * @return the {@code Long} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Long} was found
	 */
	public static Long toLong(String string) {
		if (string == null) {
			return null;
		}
		return StringToNumeric.parseLongOrNull(string.trim(), 10);
	}
	/**
	 * Convert String to Long without throwing errors
	 * they are replaced by returning a default value
     * @param string the {@code String} containing the {@code Long} representation to be parsed.
     * @param onWrong the {@code Long} containing return on error value.
     * @return the {@code Long} represented by the string argument in
     *         the specified radix, or {@code null} if no {@code Long} was found
	 */
	public static Long toLongOrDefault(String string, Long onWrong) {
		if (string == null) {
			return onWrong;
		}
		Long lg = StringToNumeric.parseLongOrNull(string.trim(), 10);
		return lg == null ? onWrong : lg;
	}

	// ========================================================================
	// Copy of Original JAVA String to numeric
	// Conversion code.
	// Modified to remove all Error throwing
	// Replaced by returning null
	//
	private static class StringToNumeric {
		
		/*
		 * Copy of Java {@code long} Class parseLong, but without throwing errors
		 * they are replaced by returning a {@code null} value
	     * @param      s       the {@code String} containing the
	     *                     {@code Long} representation to be parsed.
	     * @param      radix   the radix to be used while parsing {@code s}.
	     * @param      onWrong the value to return on error
	     * @return     the {@code Long} represented by the string argument in
	     *             the specified radix, or {@code null} if no {@code Long}
	     *             was found
		 */
		private static Long parseLongOrNull(String s, int radix) {
			if (s == null 
		    		  || radix < Character.MIN_RADIX 
		    		  || radix > Character.MAX_RADIX) {
				return null;
		    }
		    boolean negative = false;
		    int i = 0, len = s.length();
		    long limit = -Long.MAX_VALUE;
		    if (len > 0) {
		    	char firstChar = s.charAt(0);
		        if (firstChar < '0') { // Possible leading "+" or "-"
		        	if (firstChar == '-') {
		        		negative = true;
		                limit = Long.MIN_VALUE;
		            }
		            else if (firstChar != '+') {
		            	return null;
		            }
		            if (len == 1) { // Cannot have lone "+" or "-"
		            	return null;
		            }
		            i++;
		        }
		        long multmin = limit / radix;
		        long result = 0;
		        while (i < len) {
		        	// Accumulating negatively avoids surprises near MAX_VALUE
		            int digit = Character.digit(s.charAt(i++),radix);
		            if (digit < 0 ) {
		            	return null;
		            }
		            else if (result < multmin) {
		            	return null;
		            }
		            result *= radix;
		            if (result < limit + digit) {
		            	return null;
		            }
		            result -= digit;
		        }
		        return negative ? result : -result;
		    } 
		    else {
		    	return null;
		    }
		}
	
	    /**
	     * All the positive powers of 10 that can be
	     * represented exactly in double/float.
	     */
	    private static final double[] SMALL_10_POW = {
	        1.0e0,
	        1.0e1, 1.0e2, 1.0e3, 1.0e4, 1.0e5,
	        1.0e6, 1.0e7, 1.0e8, 1.0e9, 1.0e10,
	        1.0e11, 1.0e12, 1.0e13, 1.0e14, 1.0e15,
	        1.0e16, 1.0e17, 1.0e18, 1.0e19, 1.0e20,
	        1.0e21, 1.0e22
	    };
	    private static final double[] BIG_10_POW = {
	            1e16, 1e32, 1e64, 1e128, 1e256
	    };
	    private static final double[] TINY_10_POW = {
	            1e-16, 1e-32, 1e-64, 1e-128, 1e-256
	    };
	
	    private static final String INFINITY_REP = "Infinity";
	    private static final int INFINITY_LENGTH = INFINITY_REP.length();
	    private static final String NAN_REP      = "NaN";
	    private static final int NAN_LENGTH      = NAN_REP.length();
	    private static final int EXP_SHIFT       = DoubleConsts.SIGNIFICAND_WIDTH - 1;
	    private static final long FRACT_HOB      = ( 1L<<EXP_SHIFT ); // assumed High-Order bit
	    private static final int MAX_DECIMAL_DIGITS   = 15;
	    private static final int MAX_DECIMAL_EXPONENT = 308;
	    private static final int MIN_DECIMAL_EXPONENT = -324;
	    private static final int BIG_DECIMAL_EXPONENT = 324; // i.e. abs(MIN_DECIMAL_EXPONENT)
	    private static final int MAX_NDIGITS          = 1100;
	    private static final int INT_DECIMAL_DIGITS   = 9;
	    private static final int MAX_SMALL_TEN        = SMALL_10_POW.length-1;
	
	   
	    private static Double parseDoubleOrNull(String in) {
	        boolean isNegative = false;
	        boolean signSeen   = false;
	        int     decExp;
	        char    c;
	
	    parseNumber:
	        try{
	            in = in.trim();
	            int len = in.length();
	            if ( len == 0 ) {
	            	return null;
	            }
	            int i = 0;
	            switch (in.charAt(i)){
	            case '-':
	                isNegative = true;
	                //FALLTHROUGH
	            case '+':
	                i++;
	                signSeen = true;
	            }
	            c = in.charAt(i);
	            if(c == 'N') { // Check for NaN
	                if((len-i)==NAN_LENGTH && in.indexOf(NAN_REP,i)==i) {
	                    return Double.NaN;
	                }
	                // something went wrong, throw exception
	                return null;
	            } else if(c == 'I') { // Check for Infinity strings
	                if((len-i)==INFINITY_LENGTH && in.indexOf(INFINITY_REP,i)==i) {
	                    return isNegative? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	                }
	                // something went wrong, throw exception
	                return null;
	            } else if (c == '0')  { // check for hexadecimal floating-point number
	                if (len > i+1 ) {
	                    char ch = in.charAt(i+1);
	                    if (ch == 'x' || ch == 'X' ) { // possible hex string
	                    	return parseHexStringOrNull(in);
	                    }
	                }
	            }  // look for and process decimal floating-point string
	
	            char[] digits = new char[ len ];
	            boolean decSeen = false;
	            int nDigits = 0;
	            int decPt = 0;
	            int nLeadZero = 0;
	            int nTrailZero = 0;
	
	        skipLeadingZerosLoop:
	            while (i < len) {
	                c = in.charAt(i);
	                if (c == '0') {
	                    nLeadZero++;
	                } else if (c == '.') {
	                    if (decSeen) {
	                        // already saw one ., this is the 2nd.
	                    	return null;
	                    }
	                    decPt = i;
	                    if (signSeen) {
	                        decPt -= 1;
	                    }
	                    decSeen = true;
	                } else {
	                    break skipLeadingZerosLoop;
	                }
	                i++;
	            }
	        digitLoop:
	            while (i < len) {
	                c = in.charAt(i);
	                if (c >= '1' && c <= '9') {
	                    digits[nDigits++] = c;
	                    nTrailZero = 0;
	                } else if (c == '0') {
	                    digits[nDigits++] = c;
	                    nTrailZero++;
	                } else if (c == '.') {
	                    if (decSeen) {
	                        // already saw one ., this is the 2nd.
	                    	return null;
	                    }
	                    decPt = i;
	                    if (signSeen) {
	                        decPt -= 1;
	                    }
	                    decSeen = true;
	                }  else {
	                    break digitLoop;
	                }
	                i++;
	            }
	            nDigits -=nTrailZero;
	            boolean isZero = (nDigits == 0);
	            if ( isZero &&  nLeadZero == 0 ){
	            	return null; // go throw exception
	            }
	            if ( decSeen ){
	                decExp = decPt - nLeadZero;
	            } else {
	                decExp = nDigits + nTrailZero;
	            }
	            // Look for 'e' or 'E' and an optionally signed integer.
	            if ( (i < len) &&  (((c = in.charAt(i) )=='e') || (c == 'E') ) ){
	                int expSign = 1;
	                int expVal  = 0;
	                int reallyBig = Integer.MAX_VALUE / 10;
	                boolean expOverflow = false;
	                switch( in.charAt(++i) ){
	                case '-':
	                    expSign = -1;
	                    //FALLTHROUGH
	                case '+':
	                    i++;
	                }
	                int expAt = i;
	            expLoop:
	                while ( i < len  ){
	                    if ( expVal >= reallyBig ){
	                        // the next character will cause integer
	                        // overflow.
	                        expOverflow = true;
	                    }
	                    c = in.charAt(i++);
	                    if(c>='0' && c<='9') {
	                        expVal = expVal*10 + ( (int)c - (int)'0' );
	                    } else {
	                        i--;           // back up.
	                        break expLoop; // stop parsing exponent.
	                    }
	                }
	                int expLimit = BIG_DECIMAL_EXPONENT + nDigits + nTrailZero;
	                if (expOverflow || (expVal > expLimit)) {
	                    if (!expOverflow && (expSign == 1 && decExp < 0)
	                            && (expVal + decExp) < expLimit) {
	                        // Cannot overflow: adding a positive and negative number.
	                        decExp += expVal;
	                    } else {
	                        decExp = expSign * expLimit;
	                    }
	                } 
	                else {
	                    decExp = decExp + expSign*expVal;
	                }
	                if ( i == expAt ) {
	                    break parseNumber; // certainly bad
	                }
	            }
	            if ( i < len &&
	                ((i != len - 1) ||
	                (in.charAt(i) != 'f' &&
	                 in.charAt(i) != 'F' &&
	                 in.charAt(i) != 'd' &&
	                 in.charAt(i) != 'D'))) {
	                return null; // go throw exception
	            }
	            if(isZero) {
	                return isNegative ? -0.0 : 0.0;
	            }
	            return doubleValue (isNegative, decExp, digits, nDigits);
	        } catch ( StringIndexOutOfBoundsException e ){ }
	        return null;
	    }
	    
	    /**
	     * Copy of JAVA CODE
	     * Takes a FloatingDecimal, which we presumably just scanned in,
	     * and finds out what its value is, as a double.
	     *
	     * AS A SIDE EFFECT, SET roundDir TO INDICATE PREFERRED
	     * ROUNDING DIRECTION in case the result is really destined
	     * for a single-precision float.
	     */
	    private static Double doubleValue(
	    		boolean isNegative, 
	    		int     decExponent, 
	    		char    digits[], 
	    		int     nDigits) {
	        int kDigits = Math.min(nDigits, MAX_DECIMAL_DIGITS + 1);
	        // convert the lead kDigits to a long integer.
	        int iValue = (int) digits[0] - (int) '0';
	        int iDigits = Math.min(kDigits, INT_DECIMAL_DIGITS);
	        for (int i = 1; i < iDigits; i++) {
	            iValue = iValue * 10 + (int) digits[i] - (int) '0';
	        }
	        long lValue = (long) iValue;
	        for (int i = iDigits; i < kDigits; i++) {
	            lValue = lValue * 10L + (long) ((int) digits[i] - (int) '0');
	        }
	        double dValue = (double) lValue;
	        int exp = decExponent - kDigits;
	        if (nDigits <= MAX_DECIMAL_DIGITS) {
	            if (exp == 0 || dValue == 0.0) {
	                return (isNegative) ? -dValue : dValue; // small floating integer
	            } else if (exp >= 0) {
	                if (exp <= MAX_SMALL_TEN) {
	                    double rValue = dValue * SMALL_10_POW[exp];
	                    return (isNegative) ? -rValue : rValue;
	                }
	                int slop = MAX_DECIMAL_DIGITS - kDigits;
	                if (exp <= MAX_SMALL_TEN + slop) {
	                    dValue *= SMALL_10_POW[slop];
	                    double rValue = dValue * SMALL_10_POW[exp - slop];
	                    return (isNegative) ? -rValue : rValue;
	                }
	                // Else we have a hard case with a positive exp.
	            } else {
	                if (exp >= -MAX_SMALL_TEN) {
                    double rValue = dValue / SMALL_10_POW[-exp];
	                    return (isNegative) ? -rValue : rValue;
	                }
	            }
	        }
	        if (exp > 0) {
	            if (decExponent > MAX_DECIMAL_EXPONENT + 1) {
	                return (isNegative) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	            }
	            if ((exp & 15) != 0) {
	                dValue *= SMALL_10_POW[exp & 15];
	            }
	            if ((exp >>= 4) != 0) {
	                int j;
	                for (j = 0; exp > 1; j++, exp >>= 1) {
	                    if ((exp & 1) != 0) {
	                        dValue *= BIG_10_POW[j];
	                    }
	                }
	                double t = dValue * BIG_10_POW[j];
	                if (Double.isInfinite(t)) {
	                    t = dValue / 2.0;
	                    t *= BIG_10_POW[j];
	                    if (Double.isInfinite(t)) {
	                        return (isNegative) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	                    }
	                    t = Double.MAX_VALUE;
	                }
	                dValue = t;
	            }
	        } else if (exp < 0) {
	            exp = -exp;
	            if (decExponent < MIN_DECIMAL_EXPONENT - 1) {
	                return (isNegative) ? -0.0 : 0.0;
	            }
	            if ((exp & 15) != 0) {
	                dValue /= SMALL_10_POW[exp & 15];
	            }
	            if ((exp >>= 4) != 0) {
	                int j;
	                for (j = 0; exp > 1; j++, exp >>= 1) {
	                    if ((exp & 1) != 0) {
	                        dValue *= TINY_10_POW[j];
	                    }
	                }
	                double t = dValue * TINY_10_POW[j];
	                if (t == 0.0) {
	                    t = dValue * 2.0;
	                    t *= TINY_10_POW[j];
	                    if (t == 0.0) {
	                        return (isNegative) ? -0.0 : 0.0;
	                    }
	                    t = Double.MIN_VALUE;
	                }
	                dValue = t;
	            }
	        }
	        if (nDigits > MAX_NDIGITS) {
	            nDigits = MAX_NDIGITS + 1;
	            digits[MAX_NDIGITS] = '1';
	        }
	        FDBigInteger bigD0 = new FDBigInteger(lValue, digits, kDigits, nDigits);
	        exp = decExponent - nDigits;
	
	        long ieeeBits = Double.doubleToRawLongBits(dValue); // IEEE-754 bits of double candidate
	        final int B5 = Math.max(0, -exp); // powers of 5 in bigB, value is not modified inside correctionLoop
	        final int D5 = Math.max(0, exp); // powers of 5 in bigD, value is not modified inside correctionLoop
	        bigD0 = bigD0.multByPow52(D5, 0);
	        bigD0.makeImmutable();   // prevent bigD0 modification inside correctionLoop
	        FDBigInteger bigD = null;
	        int prevD2 = 0;
	
	        correctionLoop:
	        while (true) {
	            // here ieeeBits can't be NaN, Infinity or zero
	            int binexp = (int) (ieeeBits >>> EXP_SHIFT);
	            long bigBbits = ieeeBits & DoubleConsts.SIGNIF_BIT_MASK;
	            if (binexp > 0) {
	                bigBbits |= FRACT_HOB;
	            } else { // Normalize denormalized numbers.
	                assert bigBbits != 0L : bigBbits; // doubleToBigInt(0.0)
	                int leadingZeros = Long.numberOfLeadingZeros(bigBbits);
	                int shift = leadingZeros - (63 - EXP_SHIFT);
	                bigBbits <<= shift;
	                binexp = 1 - shift;
	            }
	            binexp -= DoubleConsts.EXP_BIAS;
	            int lowOrderZeros = Long.numberOfTrailingZeros(bigBbits);
	            bigBbits >>>= lowOrderZeros;
	            final int bigIntExp = binexp - EXP_SHIFT + lowOrderZeros;
	            final int bigIntNBits = EXP_SHIFT + 1 - lowOrderZeros;
	            int B2 = B5; // powers of 2 in bigB
	            int D2 = D5; // powers of 2 in bigD
	            int Ulp2;   // powers of 2 in halfUlp.
	            if (bigIntExp >= 0) {
	                B2 += bigIntExp;
	            } else {
	                D2 -= bigIntExp;
	            }
	            Ulp2 = B2;
	            int hulpbias;
	            if (binexp <= -DoubleConsts.EXP_BIAS) {
	                hulpbias = binexp + lowOrderZeros + DoubleConsts.EXP_BIAS;
	            } else {
	                hulpbias = 1 + lowOrderZeros;
	            }
	            B2 += hulpbias;
	            D2 += hulpbias;
	            int common2 = Math.min(B2, Math.min(D2, Ulp2));
	            B2 -= common2;
	            D2 -= common2;
	            Ulp2 -= common2;
	            FDBigInteger bigB = FDBigInteger.valueOfMulPow52(bigBbits, B5, B2);
	            if (bigD == null || prevD2 != D2) {
	                bigD = bigD0.leftShift(D2);
	                prevD2 = D2;
	            }
	            FDBigInteger diff;
	            int cmpResult;
	            boolean overvalue;
	            if ((cmpResult = bigB.cmp(bigD)) > 0) {
	                overvalue = true; // our candidate is too big.
	                diff = bigB.leftInplaceSub(bigD); // bigB is not user further - reuse
	                if ((bigIntNBits == 1) && (bigIntExp > -DoubleConsts.EXP_BIAS + 1)) {
	                    Ulp2 -= 1;
	                    if (Ulp2 < 0) {
	                        // rats. Cannot de-scale ulp this far.
	                        // must scale diff in other direction.
	                        Ulp2 = 0;
	                        diff = diff.leftShift(1);
	                    }
	                }
	            } else if (cmpResult < 0) {
	                overvalue = false; // our candidate is too small.
	                diff = bigD.rightInplaceSub(bigB); // bigB is not user further - reuse
	            } else {
	                break correctionLoop;
	            }
	            cmpResult = diff.cmpPow52(B5, Ulp2);
	            if ((cmpResult) < 0) {
	                break correctionLoop;
	            } else if (cmpResult == 0) {
	                if ((ieeeBits & 1) != 0) { // half ties to even
	                    ieeeBits += overvalue ? -1 : 1; // nextDown or nextUp
	                }
	                break correctionLoop;
	            } else {
	                ieeeBits += overvalue ? -1 : 1; // nextDown or nextUp
	                if (ieeeBits == 0 || ieeeBits == DoubleConsts.EXP_BIT_MASK) { // 0.0 or Double.POSITIVE_INFINITY
	                    break correctionLoop; // oops. Fell off end of range.
	                }
	                continue; // try again.
	            }
	        }
	        if (isNegative) {
	            ieeeBits |= DoubleConsts.SIGN_BIT_MASK;
	        }
	        return Double.longBitsToDouble(ieeeBits);
	    }
	    private static class HexFloatPattern {
	        /**
	         * Grammar is compatible with hexadecimal floating-point constants
	         * described in section 6.4.4.2 of the C99 specification.
	         */
	        private static final Pattern VALUE = Pattern.compile(
	                   //1           234                   56                7                   8      9
	                    "([-+])?0[xX](((\\p{XDigit}+)\\.?)|((\\p{XDigit}*)\\.(\\p{XDigit}+)))[pP]([-+])?(\\p{Digit}+)[fFdD]?"
	                    );
	    }
	    /**
	     * Copy of JAVA FloatingDecimal internal method
	     * Returns <code>s</code> with any leading zeros removed.
	     */
	    private static String stripLeadingZeros(String s) {
	        if(!s.isEmpty() && s.charAt(0)=='0') {
	            for(int i=1; i<s.length(); i++) {
	                if(s.charAt(i)!='0') {
	                    return s.substring(i);
	                }
	            }
	            return "";
	        }
	        return s;
	    }
	    /**
	     * Copy of JAVA FloatingDecimal internal method
	     * Extracts a hexadecimal digit from position <code>position</code>
	     * of string <code>s</code>.
	     */
	    private static Long getHexDigit(String s, int position) {
	        long value = Character.digit(s.charAt(position), 16);
	        if (value <= -1 || value >= 16) {
	            return null;
	        }
	        return value;
	    }
	    /**
	     * Converts string s to a suitable floating decimal; uses the
	     * double constructor and sets the roundDir variable appropriately
	     * in case the value is later converted to a float.
	     *
	     * @param s The <code>String</code> to parse.
	     */
	   private static Double parseHexStringOrNull(String s) {
            // Verify string is a member of the hexadecimal floating-point
            // string language.
            Matcher m = HexFloatPattern.VALUE.matcher(s);
            boolean validInput = m.matches();
            if (!validInput) {
                // Input does not match pattern
                throw new NumberFormatException("For input string: \"" + s + "\"");
            } else { // validInput
                //  Extract significand sign
                String group1 = m.group(1);
                boolean isNegative = ((group1 != null) && group1.equals("-"));
                //  Extract Significand magnitude
                String significandString;
                int signifLength;
                int exponentAdjust;
                {
                    int leftDigits = 0; // number of meaningful digits to
                    int rightDigits = 0; // number of digits to right of
                    String group4;
                    if ((group4 = m.group(4)) != null) {  // Integer-only significand
                        // Leading zeros never matter on the integer portion
                        significandString = stripLeadingZeros(group4);
                        leftDigits = significandString.length();
                    } else {
                        String group6 = stripLeadingZeros(m.group(6));
                        leftDigits = group6.length();
                        // fraction
                        String group7 = m.group(7);
                        rightDigits = group7.length();	
                        // Turn "integer.fraction" into "integer"+"fraction"
                        significandString =
                                ((group6 == null) ? "" : group6) + // is the null
                                        // check necessary?
                                        group7;
                    }
                    significandString = stripLeadingZeros(significandString);
                    signifLength = significandString.length();
                    // Adjust exponent
                    if (leftDigits >= 1) {  // Cases 1 and 2
                        exponentAdjust = 4 * (leftDigits - 1);
                    } else {                // Cases 3 and 4
                        exponentAdjust = -4 * (rightDigits - signifLength + 1);
                    }
                    if (signifLength == 0) { // Only zeros in input
                        return isNegative ? -0.0 : 0.0;
                    }
                }	
                //  Extract Exponent
                String group8 = m.group(8);
                boolean positiveExponent = (group8 == null) || group8.equals("+");
                long unsignedRawExponent;
                try {
                    unsignedRawExponent = Integer.parseInt(m.group(9));
                }
                catch (NumberFormatException e) {
                    return isNegative ?
                              (positiveExponent ? Double.NEGATIVE_INFINITY : -0.0)
                            : (positiveExponent ? Double.POSITIVE_INFINITY : 0.0);
                }
                long rawExponent =
                        (positiveExponent ? 1L : -1L) * // exponent sign
                                unsignedRawExponent;            // exponent magnitude
                // Calculate partially adjusted exponent
                long exponent = rawExponent + exponentAdjust;
                boolean round = false;
                boolean sticky = false;
                int nextShift;
                long significand = 0L;
                Long leadingDigit = getHexDigit(significandString, 0);
                if (leadingDigit == null) {
                	return null;
                }
                if (leadingDigit == 1) {
                    significand |= leadingDigit << 52;
                    nextShift = 52 - 4;
                    // exponent += 0
                } else if (leadingDigit <= 3) { // [2, 3]
                    significand |= leadingDigit << 51;
                    nextShift = 52 - 5;
                    exponent += 1;
                } else if (leadingDigit <= 7) { // [4, 7]
                    significand |= leadingDigit << 50;
                    nextShift = 52 - 6;
                    exponent += 2;
                } else if (leadingDigit <= 15) { // [8, f]
                    significand |= leadingDigit << 49;
                    nextShift = 52 - 7;
                    exponent += 3;
                } else {
                    return null;
                }
                int i = 0;
                for (i = 1;
                     i < signifLength && nextShift >= 0;
                     i++) {
                    Long currentDigit = getHexDigit(significandString, i);
                    if (currentDigit == null) {
                    	return null;
                    }
                    significand |= (currentDigit << nextShift);
                    nextShift -= 4;
                }
                if (i < signifLength) { // at least one hex input digit exists
                    Long currentDigit = getHexDigit(significandString, i);
                    if (currentDigit == null) {
                    	return null;
                    }
                    switch (nextShift) { // must be negative
                        case -1:
                            // three bits need to be copied in; can
                            // set round bit
                            significand |= ((currentDigit & 0xEL) >> 1);
                            round = (currentDigit & 0x1L) != 0L;
                            break;
                        case -2:
                            // two bits need to be copied in; can
                            // set round and start sticky
                            significand |= ((currentDigit & 0xCL) >> 2);
                            round = (currentDigit & 0x2L) != 0L;
                            sticky = (currentDigit & 0x1L) != 0;
                            break;
                        case -3:
                            // one bit needs to be copied in
                            significand |= ((currentDigit & 0x8L) >> 3);
                            // Now set round and start sticky, if possible
                            round = (currentDigit & 0x4L) != 0L;
                            sticky = (currentDigit & 0x3L) != 0;
                            break;
                        case -4:
                            // all bits copied into significand; set
                            // round and start sticky
                            round = ((currentDigit & 0x8L) != 0);  // is top bit set?
                            // nonzeros in three low order bits?
                            sticky = (currentDigit & 0x7L) != 0;
                            break;
                        default:
                            return null;
                            // break;
                    }
                    i++;
                    while (i < signifLength && !sticky) {
                        currentDigit = getHexDigit(significandString, i);
                        if (currentDigit == null) {
                        	return null;
                        }
                        sticky = sticky || (currentDigit != 0);
                        i++;
                    }
                }
                // Check for overflow and update exponent accordingly.
                if (exponent > Double.MAX_EXPONENT) {         // Infinite result
                    // overflow to properly signed infinity
                    return isNegative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                } else {  // Finite return value
                    if (exponent <= Double.MAX_EXPONENT && // (Usually) normal result
                            exponent >= Double.MIN_EXPONENT) {
                        significand = ((( exponent +
                                (long) DoubleConsts.EXP_BIAS) <<
                                (DoubleConsts.SIGNIFICAND_WIDTH - 1))
                                & DoubleConsts.EXP_BIT_MASK) |
                                (DoubleConsts.SIGNIF_BIT_MASK & significand);
                    } else {  // Subnormal or zero
                        // (exponent < Double.MIN_EXPONENT)

                        if (exponent < (DoubleConsts.MIN_SUB_EXPONENT - 1)) {
                            return isNegative ? -0.0 : 0.0;
                        } else { //  -1075 <= exponent <= MIN_EXPONENT -1 = -1023
                            sticky = sticky || round;
                            round = false;
                            int bitsDiscarded = 53 -
                                    ((int) exponent - DoubleConsts.MIN_SUB_EXPONENT + 1);
                            assert bitsDiscarded >= 1 && bitsDiscarded <= 53;
                            round = (significand & (1L << (bitsDiscarded - 1))) != 0L;
                            if (bitsDiscarded > 1) {
                                // create mask to update sticky bits; low
                                // order bitsDiscarded bits should be 1
                                long mask = ~((~0L) << (bitsDiscarded - 1));
                                sticky = sticky || ((significand & mask) != 0L);
                            }
                            // Now, discard the bits
                            significand = significand >> bitsDiscarded;
                            significand = ((((long) (Double.MIN_EXPONENT - 1) + // subnorm exp.
                                    (long) DoubleConsts.EXP_BIAS) <<
                                    (DoubleConsts.SIGNIFICAND_WIDTH - 1))
                                    & DoubleConsts.EXP_BIT_MASK) |
                                    (DoubleConsts.SIGNIF_BIT_MASK & significand);
                        }
                    }
                    boolean leastZero = ((significand & 1L) == 0L);
                    if ((leastZero && round && sticky) ||
                            ((!leastZero) && round)) {
                        significand++;
                    }
                    double value = isNegative ?
                            Double.longBitsToDouble(significand | DoubleConsts.SIGN_BIT_MASK) :
                            Double.longBitsToDouble(significand );
                    return value;
                }
            }
	    }
	}
}
