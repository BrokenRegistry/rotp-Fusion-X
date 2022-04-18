
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

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FDBigInteger;

/**
 * Numeric Conversion without the need to throws and catch errors
 * not the type then null.
 */	
public class Numeric {
	private Double d;
	private Long l;
	
	// ==================================================
    // Constructors
    //
	public Numeric() {}
	/**
	 * Create new Numeric from String
	 */
	public Numeric(String  value) { set(value); }
	/**
	 * Create new Numeric from Double
	 */
	public Numeric(Double  value) { set(value); }
	/**
	 * Create new Numeric from Float
	 */
	public Numeric(Float   value) { set(value); }
	/**
	 * Create new Numeric from Long
	 */
	public Numeric(Long    value) { set(value); }
	/**
	 * Create new Numeric from Integer
	 */
	public Numeric(Integer value) { set(value); }
	/**
	 * Create new Numeric from Short
	 */
	public Numeric(Short   value) { set(value); }
	/**
	 * Create new Numeric from Byte
	 */
	public Numeric(Byte    value) { set(value); }
    // ==================================================
    // Getters
    //
	public Boolean isNumeric() { 
		return isDouble(); 
	}
	public Boolean isDouble() {
		return d != null;
	}
	public Boolean isFloat() {
		return toFloat() != null;
	}
	public Boolean isLong() { 
		return l != null; 
	}
	public Boolean isInteger() { 
		return toInteger() != null; 
	}
	public Boolean isShort() {
		return toShort() != null; 
	}
	public Boolean isByte() { 
		return toByte() != null;
	}
	public Double  toDouble()  { 
		return d; 
	}
	public Float toFloat() {
		if (d <= Float.MAX_VALUE && d >= -Float.MAX_VALUE) {
			return d.floatValue();
		}
		return null;
	}
	public Long toLong() { 
		return l; 
		}
	public Integer toInteger() { 
		if (l <= Integer.MAX_VALUE && l >= Integer.MIN_VALUE) {
			return l.intValue();
		}
		return null;
	}
	public Short toShort() {
		if (l <= Short.MAX_VALUE && l >= Short.MIN_VALUE) {
			return l.shortValue();
		}
		return null;
	}
	public Byte toByte() { 
		if (l <= Byte.MAX_VALUE && l >= Byte.MIN_VALUE) {
			return l.byteValue();
		} 
		return null;
	}
	// ==================================================
    // Setters
    //
	private void set(String source) {
		d = toUsableDouble(source);
		l = toLong(source);
	}
	private void set(Double value) {
		d = toUsableDouble(value);
		l = (d != null 
				&& d <= Long.MAX_VALUE 
				&& d >= Long.MIN_VALUE) ? Math.round(d) : null;
	}
	private void set(Float value) {
		set(Double.valueOf(value));
	}
	private void set(Long value) {
		l = value;
		set(Double.valueOf(value));
	}
	private void set(Integer value) {
		set(Long.valueOf(value));
	}
	private void set(Short value) {
		set(Long.valueOf(value));
	}
	private void set(Byte value) {
		set(Long.valueOf(value));
	}
	// ==================================================
    // Tools
    //
	public Numeric validateMin(Numeric min) {
		if (min == null || !min.isDouble() || !isDouble()) {
			return this;
		}
		if ( min.toDouble() > toDouble() 
				|| ( isLong() && min.isLong() && min.toLong() > toLong()) ) {
			set(min.toDouble());
		}
		return this;
	}
	public Numeric validateMax(Numeric max) {
		if (max == null || !max.isDouble() || !isDouble()) {
			return this;
		}
		if ( max.toDouble() < toDouble() 
				|| ( isLong() && max.isLong() && max.toLong() < toLong()) ) {
			set(max.toDouble());
		}
		return this;
	}
	public  Numeric validateMinMax(Numeric min, Numeric max) {
		return validateMin(min).validateMax(max);
	}
	
	public static Numeric max(Numeric n1, Numeric n2) {
		if (n1 == null || n1.toDouble() == null) {
			return n2;
		}
		if (n2 == null || n2.toDouble() == null) {
			return n1;
		}
		if (n1.toDouble() > n2.toDouble()) {
			return n1;
		}
		return n2;
	}
	public static Numeric min(Numeric n1, Numeric n2) {
		if (n1 == null || n1.toDouble() == null) {
			return n2;
		}
		if (n2 == null || n2.toDouble() == null) {
			return n1;
		}
		if (n1.toDouble() > n2.toDouble()) {
			return n2;
		}
		return n1;
	}

	public static Double toUsableDouble(Double value) {
		return (value != null 
				&& value != Double.NaN 
				&& value != Double.NEGATIVE_INFINITY
				&& value != Double.POSITIVE_INFINITY) ? value : null;
	}
	// ==================================================
    // Random Generation Methods
    //
	private Long minMaxLongDiff(Long min, Long max) {
		 // Test diff is still a Long
		if (BigInteger.valueOf(max)
				.subtract(BigInteger.valueOf(min))
				.add(BigInteger.valueOf(1))
				.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) == 1) {
			return null;
		}
		return max - min + 1;
	}
  	private Numeric nextRandom(Numeric min, Numeric max) {
		Double diff = max.toDouble() - min.toDouble();
		if (diff < 0) {
			return nextRandom(max, min);
		}
		Double rnd = ThreadLocalRandom.current().nextDouble();
		d = min.toDouble() + diff * rnd;
		Long ldiff = minMaxLongDiff(min.toLong(), max.toLong());
		l = ldiff == null ? null : Math.round(ldiff * rnd);	
		return this;
	}
	/**
	 * Return random Numeric value between min (inclusive)
	 * and max (inclusive for whole numbers but exclusive 
	 * for floating point numbers)
	 * the resulting may then not match perfectly
	 */
	public static Numeric nextRandomNumeric(Numeric min, Numeric max) {
		Double diff = max.toDouble() - min.toDouble();
		if (diff < 0) {
			return nextRandomNumeric(max, min);
		}
		return new Numeric().nextRandom(min, max);
	}
	public static Numeric nextRandomNumeric(Integer min, Integer max) {
		if (max >= min) {
			return  new Numeric().nextRandom(new Numeric(min), new Numeric(max));
		}
		return  new Numeric().nextRandom(new Numeric(max), new Numeric(min));
	}
	/**
	 * Test if String is usable Numeric Value: Not NAN nor Infinity
	 */
	public static boolean isUsableNumeric(String string) {
		Double dbl = toDouble(string);
		return dbl != null 
				&& dbl != Double.NaN 
				&& dbl != Double.NEGATIVE_INFINITY
				&& dbl != Double.POSITIVE_INFINITY;
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
		            	return negative ? result : -result; // the beginning was OK!
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
	            } 
	            else if(c == 'I') { // Check for Infinity strings
	                if((len-i)==INFINITY_LENGTH && in.indexOf(INFINITY_REP,i)==i) {
	                    return isNegative? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	                }
	                // something went wrong, throw exception
	                return null;
	            } 
	            else if (c == '0')  { // check for hexadecimal floating-point number
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
	                }
	                else if (c == '.') {
	                    if (decSeen) {
	                        // already saw one ., this is the 2nd.
	                    	return null;
	                    }
	                    decPt = i;
	                    if (signSeen) {
	                        decPt -= 1;
	                    }
	                    decSeen = true;
	                }
	                else {
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
	                }
	                else if (c == '0') {
	                    digits[nDigits++] = c;
	                    nTrailZero++;
	                }
	                else if (c == '.') {
	                    if (decSeen) {
	                        // already saw one ., this is the 2nd.
	                    	return null;
	                    }
	                    decPt = i;
	                    if (signSeen) {
	                        decPt -= 1;
	                    }
	                    decSeen = true;
	                } 
	                else {
	                    break digitLoop;
	                }
	                i++;
	            }
	            nDigits -=nTrailZero;
	            //
	            // At this point, we've scanned all the digits and decimal
	            // point we're going to see. Trim off leading and trailing
	            // zeros, which will just confuse us later, and adjust
	            // our initial decimal exponent accordingly.
	            // To review:
	            // we have seen i total characters.
	            // nLeadZero of them were zeros before any other digits.
	            // nTrailZero of them were zeros after any other digits.
	            // if ( decSeen ), then a . was seen after decPt characters
	            // ( including leading zeros which have been discarded )
	            // nDigits characters were neither lead nor trailing
	            // zeros, nor point
	            //
	            //
	            // special hack: if we saw no non-zero digits, then the
	            // answer is zero!
	            // Unfortunately, we feel honor-bound to keep parsing!
	            //
	            boolean isZero = (nDigits == 0);
	            if ( isZero &&  nLeadZero == 0 ){
	                // we saw NO DIGITS AT ALL,
	                // not even a crummy 0!
	                // this is not allowed.
	            	return null; // go throw exception
	            }
	            //
	            // Our initial exponent is decPt, adjusted by the number of
	            // discarded zeros. Or, if there was no decPt,
	            // then its just nDigits adjusted by discarded trailing zeros.
	            //
	            if ( decSeen ){
	                decExp = decPt - nLeadZero;
	            } 
	            else {
	                decExp = nDigits + nTrailZero;
	            }
	            //
	            // Look for 'e' or 'E' and an optionally signed integer.
	            //
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
	                    // There is still a chance that the exponent will be safe to
	                    // use: if it would eventually decrease due to a negative
	                    // decExp, and that number is below the limit.  We check for
	                    // that here.
	                    if (!expOverflow && (expSign == 1 && decExp < 0)
	                            && (expVal + decExp) < expLimit) {
	                        // Cannot overflow: adding a positive and negative number.
	                        decExp += expVal;
	                    } 
	                    else {
	                        //
	                        // The intent here is to end up with
	                        // infinity or zero, as appropriate.
	                        // The reason for yielding such a small decExponent,
	                        // rather than something intuitive such as
	                        // expSign*Integer.MAX_VALUE, is that this value
	                        // is subject to further manipulation in
	                        // doubleValue() and floatValue(), and I don't want
	                        // it to be able to cause overflow there!
	                        // (The only way we can get into trouble here is for
	                        // really outrageous nDigits+nTrailZero, such as 2
	                        // billion.)
	                        //
	                        decExp = expSign * expLimit;
	                    }
	                } 
	                else {
	                    // this should not overflow, since we tested
	                    // for expVal > (MAX+N), where N >= abs(decExp)
	                    decExp = decExp + expSign*expVal;
	                }
	                // if we saw something not a digit ( or end of string )
	                // after the [Ee][+-], without seeing any digits at all
	                // this is certainly an error. If we saw some digits,
	                // but then some trailing garbage, that might be ok.
	                // so we just fall through in that case.
	                // HUMBUG
	                if ( i == expAt ) {
	                    break parseNumber; // certainly bad
	                }
	            }
	            //
	            // We parsed everything we could.
	            // If there are leftovers, then this is not good input!
	            //
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
	        //
	        // convert the lead kDigits to a long integer.
	        //
	        // (special performance hack: start to do it using int)
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
	        //
	        // lValue now contains a long integer with the value of
	        // the first kDigits digits of the number.
	        // dValue contains the (double) of the same.
	        //
	
	        if (nDigits <= MAX_DECIMAL_DIGITS) {
	            //
	            // possibly an easy case.
	            // We know that the digits can be represented
	            // exactly. And if the exponent isn't too outrageous,
	            // the whole thing can be done with one operation,
	            // thus one rounding error.
	            // Note that all our constructors trim all leading and
	            // trailing zeros, so simple values (including zero)
	            // will always end up here
	            //
	            if (exp == 0 || dValue == 0.0) {
	                return (isNegative) ? -dValue : dValue; // small floating integer
	            }
	            else if (exp >= 0) {
	                if (exp <= MAX_SMALL_TEN) {
	                    //
	                    // Can get the answer with one operation,
	                    // thus one roundoff.
	                    //
	                    double rValue = dValue * SMALL_10_POW[exp];
	                    return (isNegative) ? -rValue : rValue;
	                }
	                int slop = MAX_DECIMAL_DIGITS - kDigits;
	                if (exp <= MAX_SMALL_TEN + slop) {
	                    //
	                    // We can multiply dValue by 10^(slop)
	                    // and it is still "small" and exact.
	                    // Then we can multiply by 10^(exp-slop)
	                    // with one rounding.
	                    //
	                    dValue *= SMALL_10_POW[slop];
	                    double rValue = dValue * SMALL_10_POW[exp - slop];
	                    return (isNegative) ? -rValue : rValue;
	                }
	                //
	                // Else we have a hard case with a positive exp.
	                //
	            } else {
	                if (exp >= -MAX_SMALL_TEN) {
	                    //
	                    // Can get the answer in one division.
	                    //
	                    double rValue = dValue / SMALL_10_POW[-exp];
	                    return (isNegative) ? -rValue : rValue;
	                }
	                //
	                // Else we have a hard case with a negative exp.
	                //
	            }
	        }
	
	        //
	        // Harder cases:
	        // The sum of digits plus exponent is greater than
	        // what we think we can do with one error.
	        //
	        // Start by approximating the right answer by,
	        // naively, scaling by powers of 10.
	        //
	        if (exp > 0) {
	            if (decExponent > MAX_DECIMAL_EXPONENT + 1) {
	                //
	                // Lets face it. This is going to be
	                // Infinity. Cut to the chase.
	                //
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
	                //
	                // The reason for the weird exp > 1 condition
	                // in the above loop was so that the last multiply
	                // would get unrolled. We handle it here.
	                // It could overflow.
	                //
	                double t = dValue * BIG_10_POW[j];
	                if (Double.isInfinite(t)) {
	                    //
	                    // It did overflow.
	                    // Look more closely at the result.
	                    // If the exponent is just one too large,
	                    // then use the maximum finite as our estimate
	                    // value. Else call the result infinity
	                    // and punt it.
	                    // ( I presume this could happen because
	                    // rounding forces the result here to be
	                    // an ULP or two larger than
	                    // Double.MAX_VALUE ).
	                    //
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
	                //
	                // Lets face it. This is going to be
	                // zero. Cut to the chase.
	                //
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
	                //
	                // The reason for the weird exp > 1 condition
	                // in the above loop was so that the last multiply
	                // would get unrolled. We handle it here.
	                // It could underflow.
	                //
	                double t = dValue * TINY_10_POW[j];
	                if (t == 0.0) {
	                    //
	                    // It did underflow.
	                    // Look more closely at the result.
	                    // If the exponent is just one too small,
	                    // then use the minimum finite as our estimate
	                    // value. Else call the result 0.0
	                    // and punt it.
	                    // ( I presume this could happen because
	                    // rounding forces the result here to be
	                    // an ULP or two less than
	                    // Double.MIN_VALUE ).
	                    //
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
	
	        //
	        // dValue is now approximately the result.
	        // The hard part is adjusting it, by comparison
	        // with FDBigInteger arithmetic.
	        // Formulate the EXACT big-number result as
	        // bigD0 * 10^exp
	        //
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
	
	            //
	            // Scale bigD, bigB appropriately for
	            // big-integer operations.
	            // Naively, we multiply by powers of ten
	            // and powers of two. What we actually do
	            // is keep track of the powers of 5 and
	            // powers of 2 we would use, then factor out
	            // common divisors before doing the work.
	            //
	            int B2 = B5; // powers of 2 in bigB
	            int D2 = D5; // powers of 2 in bigD
	            int Ulp2;   // powers of 2 in halfUlp.
	            if (bigIntExp >= 0) {
	                B2 += bigIntExp;
	            } else {
	                D2 -= bigIntExp;
	            }
	            Ulp2 = B2;
	            // shift bigB and bigD left by a number s. t.
	            // halfUlp is still an integer.
	            int hulpbias;
	            if (binexp <= -DoubleConsts.EXP_BIAS) {
	                // This is going to be a denormalized number
	                // (if not actually zero).
	                // half an ULP is at 2^-(DoubleConsts.EXP_BIAS+EXP_SHIFT+1)
	                hulpbias = binexp + lowOrderZeros + DoubleConsts.EXP_BIAS;
	            } else {
	                hulpbias = 1 + lowOrderZeros;
	            }
	            B2 += hulpbias;
	            D2 += hulpbias;
	            // if there are common factors of 2, we might just as well
	            // factor them out, as they add nothing useful.
	            int common2 = Math.min(B2, Math.min(D2, Ulp2));
	            B2 -= common2;
	            D2 -= common2;
	            Ulp2 -= common2;
	            // do multiplications by powers of 5 and 2
	            FDBigInteger bigB = FDBigInteger.valueOfMulPow52(bigBbits, B5, B2);
	            if (bigD == null || prevD2 != D2) {
	                bigD = bigD0.leftShift(D2);
	                prevD2 = D2;
	            }
	            //
	            // to recap:
	            // bigB is the scaled-big-int version of our floating-point
	            // candidate.
	            // bigD is the scaled-big-int version of the exact value
	            // as we understand it.
	            // halfUlp is 1/2 an ulp of bigB, except for special cases
	            // of exact powers of 2
	            //
	            // the plan is to compare bigB with bigD, and if the difference
	            // is less than halfUlp, then we're satisfied. Otherwise,
	            // use the ratio of difference to halfUlp to calculate a fudge
	            // factor to add to the floating value, then go 'round again.
	            //
	            FDBigInteger diff;
	            int cmpResult;
	            boolean overvalue;
	            if ((cmpResult = bigB.cmp(bigD)) > 0) {
	                overvalue = true; // our candidate is too big.
	                diff = bigB.leftInplaceSub(bigD); // bigB is not user further - reuse
	                if ((bigIntNBits == 1) && (bigIntExp > -DoubleConsts.EXP_BIAS + 1)) {
	                    // candidate is a normalized exact power of 2 and
	                    // is too big (larger than Double.MIN_NORMAL). We will be subtracting.
	                    // For our purposes, ulp is the ulp of the
	                    // next smaller range.
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
	                // the candidate is exactly right!
	                // this happens with surprising frequency
	                break correctionLoop;
	            }
	            cmpResult = diff.cmpPow52(B5, Ulp2);
	            if ((cmpResult) < 0) {
	                // difference is small.
	                // this is close enough
	                break correctionLoop;
	            } else if (cmpResult == 0) {
	                // difference is exactly half an ULP
	                // round to some other value maybe, then finish
	                if ((ieeeBits & 1) != 0) { // half ties to even
	                    ieeeBits += overvalue ? -1 : 1; // nextDown or nextUp
	                }
	                break correctionLoop;
	            } else {
	                // difference is non-trivial.
	                // could scale addend by ratio of difference to
	                // halfUlp here, if we bothered to compute that difference.
	                // Most of the time ( I hope ) it is about 1 anyway.
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
	    // Copy of JAVA FloatingDecimal internal method
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
	                //
	                // We must isolate the sign, significand, and exponent
	                // fields.  The sign value is straightforward.  Since
	                // floating-point numbers are stored with a normalized
	                // representation, the significand and exponent are
	                // interrelated.
	                //
	                // After extracting the sign, we normalized the
	                // significand as a hexadecimal value, calculating an
	                // exponent adjust for any shifts made during
	                // normalization.  If the significand is zero, the
	                // exponent doesn't need to be examined since the output
	                // will be zero.
	                //
	                // Next the exponent in the input string is extracted.
	                // Afterwards, the significand is normalized as a *binary*
	                // value and the input value's normalized exponent can be
	                // computed.  The significand bits are copied into a
	                // double significand; if the string has more logical bits
	                // than can fit in a double, the extra bits affect the
	                // round and sticky bits which are used to round the final
	                // value.
	                //
	                //  Extract significand sign
	                String group1 = m.group(1);
	                boolean isNegative = ((group1 != null) && group1.equals("-"));
	
	                //  Extract Significand magnitude
	                //
	                // Based on the form of the significand, calculate how the
	                // binary exponent needs to be adjusted to create a
	                // normalized//hexadecimal* floating-point number; that
	                // is, a number where there is one nonzero hex digit to
	                // the left of the (hexa)decimal point.  Since we are
	                // adjusting a binary, not hexadecimal exponent, the
	                // exponent is adjusted by a multiple of 4.
	                //
	                // There are a number of significand scenarios to consider;
	                // letters are used in indicate nonzero digits:
	                //
	                // 1. 000xxxx       =>      x.xxx   normalized
	                //    increase exponent by (number of x's - 1)*4
	                //
	                // 2. 000xxx.yyyy =>        x.xxyyyy        normalized
	                //    increase exponent by (number of x's - 1)*4
	                //
	                // 3. .000yyy  =>   y.yy    normalized
	                //    decrease exponent by (number of zeros + 1)*4
	                //
	                // 4. 000.00000yyy => y.yy normalized
	                //    decrease exponent by (number of zeros to right of point + 1)*4
	                //
	                // If the significand is exactly zero, return a properly
	                // signed zero.
	                //
	
	                String significandString;
	                int signifLength;
	                int exponentAdjust;
	                {
	                    int leftDigits = 0; // number of meaningful digits to
	                    // left of "decimal" point
	                    // (leading zeros stripped)
	                    int rightDigits = 0; // number of digits to right of
	                    // "decimal" point; leading zeros
	                    // must always be accounted for
	                    //
	                    // The significand is made up of either
	                    //
	                    // 1. group 4 entirely (integer portion only)
	                    //
	                    // OR
	                    //
	                    // 2. the fractional portion from group 7 plus any
	                    // (optional) integer portions from group 6.
	                    //
	                    String group4;
	                    if ((group4 = m.group(4)) != null) {  // Integer-only significand
	                        // Leading zeros never matter on the integer portion
	                        significandString = stripLeadingZeros(group4);
	                        leftDigits = significandString.length();
	                    } else {
	                        // Group 6 is the optional integer; leading zeros
	                        // never matter on the integer portion
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
	
	                    //
	                    // Adjust exponent as described above
	                    //
	                    if (leftDigits >= 1) {  // Cases 1 and 2
	                        exponentAdjust = 4 * (leftDigits - 1);
	                    } else {                // Cases 3 and 4
	                        exponentAdjust = -4 * (rightDigits - signifLength + 1);
	                    }
	
	                    // If the significand is zero, the exponent doesn't
	                    // matter; return a properly signed zero.
	
	                    if (signifLength == 0) { // Only zeros in input
	                        return isNegative ? -0.0 : 0.0;
	                    }
	                }
	
	                //  Extract Exponent
	                //
	                // Use an int to read in the exponent value; this should
	                // provide more than sufficient range for non-contrived
	                // inputs.  If reading the exponent in as an int does
	                // overflow, examine the sign of the exponent and
	                // significand to determine what to do.
	                //
	                String group8 = m.group(8);
	                boolean positiveExponent = (group8 == null) || group8.equals("+");
	                long unsignedRawExponent;
	                try {
	                    unsignedRawExponent = Integer.parseInt(m.group(9));
	                }
	                catch (NumberFormatException e) {
	                    // At this point, we know the exponent is
	                    // syntactically well-formed as a sequence of
	                    // digits.  Therefore, if an NumberFormatException
	                    // is thrown, it must be due to overflowing int's
	                    // range.  Also, at this point, we have already
	                    // checked for a zero significand.  Thus the signs
	                    // of the exponent and significand determine the
	                    // final result:
	                    //
	                    //                      significand
	                    //                      +               -
	                    // exponent     +       +infinity       -infinity
	                    //              -       +0.0            -0.0
	                    return isNegative ?
	                              (positiveExponent ? Double.NEGATIVE_INFINITY : -0.0)
	                            : (positiveExponent ? Double.POSITIVE_INFINITY : 0.0);
	
	                }
	
	                long rawExponent =
	                        (positiveExponent ? 1L : -1L) * // exponent sign
	                                unsignedRawExponent;            // exponent magnitude
	
	                // Calculate partially adjusted exponent
	                long exponent = rawExponent + exponentAdjust;
	
	                // Starting copying non-zero bits into proper position in
	                // a long; copy explicit bit too; this will be masked
	                // later for normal values.
	
	                boolean round = false;
	                boolean sticky = false;
	                int nextShift;
	                long significand = 0L;
	                // First iteration is different, since we only copy
	                // from the leading significand bit; one more exponent
	                // adjust will be needed...
	
	                // IMPORTANT: make leadingDigit a long to avoid
	                // surprising shift semantics!
	                Long leadingDigit = getHexDigit(significandString, 0);
	                if (leadingDigit == null) {
	                	return null;
	                }
	
	                //
	                // Left shift the leading digit (53 - (bit position of
	                // leading 1 in digit)); this sets the top bit of the
	                // significand to 1.  The nextShift value is adjusted
	                // to take into account the number of bit positions of
	                // the leadingDigit actually used.  Finally, the
	                // exponent is adjusted to normalize the significand
	                // as a binary value, not just a hex value.
	                //
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
	                    throw new AssertionError("Result from digit conversion too large!");
	                }
	                // The preceding if-else could be replaced by a single
	                // code block based on the high-order bit set in
	                // leadingDigit.  Given leadingOnePosition,
	
	                // significand |= leadingDigit << (SIGNIFICAND_WIDTH - leadingOnePosition);
	                // nextShift = 52 - (3 + leadingOnePosition);
	                // exponent += (leadingOnePosition-1);
	
	                //
	                // Now the exponent variable is equal to the normalized
	                // binary exponent.  Code below will make representation
	                // adjustments if the exponent is incremented after
	                // rounding (includes overflows to infinity) or if the
	                // result is subnormal.
	                //
	
	                // Copy digit into significand until the significand can't
	                // hold another full hex digit or there are no more input
	                // hex digits.
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
	
	                // After the above loop, the bulk of the string is copied.
	                // Now, we must copy any partial hex digits into the
	                // significand AND compute the round bit and start computing
	                // sticky bit.
	
	                if (i < signifLength) { // at least one hex input digit exists
	                    Long currentDigit = getHexDigit(significandString, i);
	                    if (currentDigit == null) {
	                    	return null;
	                    }
	
	                    // from nextShift, figure out how many bits need
	                    // to be copied, if any
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
	
	                    // Round is set; sticky might be set.
	
	                    // For the sticky bit, it suffices to check the
	                    // current digit and test for any nonzero digits in
	                    // the remaining unprocessed input.
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
	                // else all of string was seen, round and sticky are
	                // correct as false.
	
	                // Check for overflow and update exponent accordingly.
	                if (exponent > Double.MAX_EXPONENT) {         // Infinite result
	                    // overflow to properly signed infinity
	                    return isNegative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
	                } 
	                else {  // Finite return value
	                    if (exponent <= Double.MAX_EXPONENT && // (Usually) normal result
	                            exponent >= Double.MIN_EXPONENT) {
	
	                        // The result returned in this block cannot be a
	                        // zero or subnormal; however after the
	                        // significand is adjusted from rounding, we could
	                        // still overflow in infinity.
	
	                        // AND exponent bits into significand; if the
	                        // significand is incremented and overflows from
	                        // rounding, this combination will update the
	                        // exponent correctly, even in the case of
	                        // Double.MAX_VALUE overflowing to infinity.
	
	                        significand = ((( exponent +
	                                (long) DoubleConsts.EXP_BIAS) <<
	                                (DoubleConsts.SIGNIFICAND_WIDTH - 1))
	                                & DoubleConsts.EXP_BIT_MASK) |
	                                (DoubleConsts.SIGNIF_BIT_MASK & significand);
	
	                    } 
	                    else {  // Subnormal or zero
	                        // (exponent < Double.MIN_EXPONENT)
	
	                        if (exponent < (DoubleConsts.MIN_SUB_EXPONENT - 1)) {
	                            // No way to round back to nonzero value
	                            // regardless of significand if the exponent is
	                            // less than -1075.
	                            return isNegative ? -0.0 : 0.0;
	                        } 
	                        else { //  -1075 <= exponent <= MIN_EXPONENT -1 = -1023
	                            //
	                            // Find bit position to round to; recompute
	                            // round and sticky bits, and shift
	                            // significand right appropriately.
	                            //
	
	                            sticky = sticky || round;
	                            round = false;
	
	                            // Number of bits of significand to preserve is
	                            // exponent - abs_min_exp +1
	                            // check:
	                            // -1075 +1074 + 1 = 0
	                            // -1023 +1074 + 1 = 52
	
	                            int bitsDiscarded = 53 -
	                                    ((int) exponent - DoubleConsts.MIN_SUB_EXPONENT + 1);
	                            assert bitsDiscarded >= 1 && bitsDiscarded <= 53;
	
	                            // What to do here:
	                            // First, isolate the new round bit
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
	
	                    // The significand variable now contains the currently
	                    // appropriate exponent bits too.
	
	                    //
	                    // Determine if significand should be incremented;
	                    // making this determination depends on the least
	                    // significant bit and the round and sticky bits.
	                    //
	                    // Round to nearest even rounding table, adapted from
	                    // table 4.7 in "Computer Arithmetic" by IsraelKoren.
	                    // The digit to the left of the "decimal" point is the
	                    // least significant bit, the digits to the right of
	                    // the point are the round and sticky bits
	                    //
	                    // Number       Round(x)
	                    // x0.00        x0.
	                    // x0.01        x0.
	                    // x0.10        x0.
	                    // x0.11        x1. = x0. +1
	                    // x1.00        x1.
	                    // x1.01        x1.
	                    // x1.10        x1. + 1
	                    // x1.11        x1. + 1
	                    //
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
