
/**
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

package br.profileManager.src.main.java;

import static br.profileManager.src.main.java.PMutil.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author BrokenRegistry
 *
 */
class PMutilTest {
	void template() {
		assertEquals(true, true
				, "should have been: «true»");
	}
    // ==================================================
    // Generic Converters with default values
    //
	@Test
	void getOrDefault_Byte() {
		assertEquals((byte)-88, getOrDefault("-88", (byte)88)
				, "This String should have been read correctly");
		assertEquals((byte)88, getOrDefault("XXX", (byte)88)
				, "The default value should have been returned");
		assertEquals((byte)12, getOrDefault("12.3456789", (byte)88)
				, "The rounded value should have been returned");
		assertEquals((byte)88, getOrDefault("123456e122", (byte)88)
				, "The default value should have been returned");
	}

	@Test
	void getOrDefault_Short() {
		assertEquals((short)88, getOrDefault("123456789", (short)88)
				, "The default value should have been returned");
		assertEquals((short)88, getOrDefault("XXX", (short)88)
				, "The default value should have been returned");
		assertEquals((short)12346, getOrDefault("12345.6789", (short)88)
				, "The rounded value should have been returned");
		assertEquals((short)88, getOrDefault("123456e122", (short)88)
				, "The default value should have been returned");
	}

	@Test
	void getOrDefault_Integer() {
		assertEquals(123456789, getOrDefault("123456789", 88)
				, "This String should have been read correctly");
		assertEquals(88, getOrDefault("XXX", 88)
				, "The default value should have been returned");
		assertEquals(123457, getOrDefault("123456.789", 88)
				, "The rounded value should have been returned");
		assertEquals(88, getOrDefault("123456e122", 88)
				, "The default value should have been returned");
	}

	@Test
	void getOrDefault_Long() {
		// pure Long reading
		assertEquals(123456789L, getOrDefault("123456789", 88L)
				, "This String should have been read correctly");
		assertEquals(88L, getOrDefault("XXX", 88L)
				, "The default value should have been returned");
		// thru Double reading
		assertEquals(123457, getOrDefault("123456.789", 88L)
				, "The rounded value should have been returned");
		assertEquals(88L, getOrDefault("123456e122", 88L)
				, "The default value should have been returned");
	}

	@Test
	void getOrDefault_Double() {
		assertEquals(123456789.0, getOrDefault("123456789", 88.8)
				, "This String should have been read correctly");
		assertEquals(88.8, getOrDefault("2XXX", 88.8)
				, "The default value should have been returned");
		assertEquals(Double.NaN, getOrDefault("NaN", 88.8)
				, "This String should have been read correctly");
	}	

	@Test
	void getOrDefault_Boolean() {
		assertEquals(true, getOrDefault("yes", false)
				, "should have been «true»");
		assertEquals(false, getOrDefault("no", true)
				, "should have been «false»");
		assertEquals(true, getOrDefault("True", false)
				, "should have been «true»");
		assertEquals(false, getOrDefault("False", true)
				, "should have been «false»");
		assertEquals(true, getOrDefault("x", true)
				, "should have been «true»");
		assertEquals(false, getOrDefault("x", false)
				, "should have been «false»");
		assertEquals((Boolean)null, getOrDefault("x", (Boolean)null)
				, "should have been «null»");
		assertEquals(true, getOrDefault((String)null, true)
				, "should have been «true»");
		assertEquals(false, getOrDefault((String)null, false)
				, "should have been «false»");
	}

	@Test
	void getOrDefault_String() {
		assertEquals("Source", getOrDefault("Source", "Good")
				, "should have been «Source»");
		assertEquals("Good", getOrDefault("", "Good")
				, "should have been «Good»");
		assertEquals("Good", getOrDefault("  ", "Good")
				, "should have been «Source»");
		assertEquals("Good", getOrDefault((String)null, "Good")
				, "should have been «Source»");
	}

	// ==================================================
    // Some String Methods
    //
	@Test
	void addLinebreaks_String() {
		String NL = System.lineSeparator();
		String src = "  34 67 90 23456 8901234";
		String out =  "  34 67 90" + NL
					+ " 23456 " + NL
					+ "8901234";
		assertEquals(out, addLinebreaks(src, " ", 10, true)
				, "should have been: 3 lines");		
		src = "  34 67 90 23456 8901234  ";
		out = "  34 67 90" + NL
			+ " 23456 " + NL
			+ "8901234  ";
		assertEquals(out, addLinebreaks(src, " ", 10, true)
				, "should have been: 3 lines");		
		src = "  34 67 90 23456 8901234";
		out =  "34 67 90" + NL
					+ "23456" + NL
					+ "8901234";
		assertEquals(out, addLinebreaks(src, " ", 10, false)
				, "should have been: 3 lines");		
		src = "1234 67 90 23456 8901234  ";
		out = "1234 67 90" + NL
			+ "23456" + NL
			+ "8901234";
		assertEquals(out, addLinebreaks(src, " ", 10, false)
				, "should have been: 3 lines");		
	}
	
	@Test
	void removeFirstSpace_String() {
		assertEquals("Source", removeFirstSpace("Source")
				, "should have been: \"Source\"");
		assertEquals("Source", removeFirstSpace(" Source")
				, "should have been: \"Source\"");
		assertEquals(" Source", removeFirstSpace("  Source")
				, "should have been: \" Source\"");
		assertEquals("", removeFirstSpace("")
				, "should have been: \"\"");
		assertEquals(null, removeFirstSpace(null)
				, "should have been: «null»");
	}
	
	@Test
	void getLastChar_String() {
		assertEquals("e", getLastChar("Source")
				, "should have been: \"e\"");
		assertEquals(" ", getLastChar("Source ")
				, "should have been: \" \"");
		assertEquals(" ", getLastChar(" ")
				, "should have been: \" \"");
		assertEquals(null, getLastChar("")
				, "should have been: «null»");
		assertEquals(null, getLastChar(null)
				, "should have been: «null»");
	}
	
	@Test
	void neverNull_Object() {
		assertEquals("Source", neverNull("Source")
				, "should have been: «Source»");
		assertEquals("  source   ", neverNull("  source   ")
				, "should have been: «  source   »");		
		assertEquals("", neverNull((String)null)
				, "should have been: «»");		
		assertEquals("127", neverNull(Byte.MAX_VALUE)
				, "should have been: 127");		
	}

	@Test
	void clean_Object() {
		assertEquals("Source", clean("Source")
				, "should have been: «Source»");
		assertEquals("source", clean("  source   ")
				, "should have been: «source»");		
		assertEquals("", clean((String)null)
				, "should have been: «»");		
		assertEquals("127", clean(Byte.MAX_VALUE)
				, "should have been: «127»");		
	}

	@Test
	void toKey_String() {
		assertEquals("SOURCE", toKey("Source")
				, "should have been: «SOURCE»");
		assertEquals("SOURCE", toKey("  source   ")
				, "should have been: «SOURCE»");		
		assertEquals("", toKey((String)null)
				, "should have been: «»");		
	}

	@Test
	void toSentence_String() {
		assertEquals("Source", toSentence("Source")
				, "should have been «Source»");
		assertEquals("Source", toSentence("source")
				, "should have been «Source»");
		assertEquals("Source", toSentence("SOURCE")
				, "should have been «Source»");
		assertEquals("Option source", toSentence("OPTION SOURCE")
				, "should have been: «Option source»");
		assertEquals("Very option source", toSentence("VERY OPTION SOURCE")
				, "should have been: «Very option source»");
		assertEquals("Option  source", toSentence("OPTION  SOURCE")
				, "should have been: «Option  source»");
		assertEquals("Source", toSentence(" SOURCE")
				, "should have been «Source»");
		assertEquals("Source", toSentence("  SOURCE  ")
				, "should have been «Source»");
		assertEquals("", toSentence("    ")
				, "should have been «»");
		assertEquals("", toSentence((String)null)
				, "should have been «»");
	}

	@Test
	void capitalize_String() {
		assertEquals("Source", capitalize("Source")
				, "should have been «Source»");
		assertEquals("Source", capitalize("source")
				, "should have been «Source»");
		assertEquals("Source", capitalize("SOURCE")
				, "should have been «Source»");
		assertEquals("Option Source", capitalize("OPTION SOURCE")
				, "should have been: «Option Source»");
		assertEquals("Very Option Source", capitalize("VERY OPTION SOURCE")
				, "should have been: «Very Option Source»");
		assertEquals("Option  Source", capitalize("OPTION  SOURCE")
				, "should have been: «Option  Source»");
		assertEquals("Source", capitalize(" SOURCE")
				, "should have been «Source»");
		assertEquals("Source", capitalize("  SOURCE  ")
				, "should have been «Source»");
		assertEquals("", capitalize("    ")
				, "should have been «»");
		assertEquals("", capitalize((String)null)
				, "should have been «»");
	}

	@Test
	void capitalize_Boolean() {
		assertEquals("Very option source", capitalize("VERY OPTION SOURCE", true)
				, "should have been: «Very option source»");
		assertEquals("Very Option Source", capitalize("VERY OPTION SOURCE", false)
				, "should have been: «Very Option Source»");		
	}

	@Test
	void suggestedOptionToLabel_String() {
		assertEquals("Source", suggestedUserViewFromCodeView("Source")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("source")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("SOURCE")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("OPTION_SOURCE")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("VERY_OPTION_SOURCE")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("OPTION__SOURCE")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("_SOURCE")
				, "should have been «Source»");
		assertEquals("Source", suggestedUserViewFromCodeView("__SOURCE")
				, "should have been «Source»");
	}

	// ==================================================
    // Math Methods
    //
	@Test
	void probDensityToCumul_FloatArray() {
	   	float[] cumSum  = new float[] {0.0f, 0.1f, 0.5f, 0.8f, 1.0f, 1.0f};
	   	float[] density = new float[] {0.0f, 0.1f, 0.4f, 0.3f, 0.2f, 0.0f};    
	   	assertArrayEquals(cumSum, probDensityToCumul(density)
				, "should have been «{0f, 10f, 50f, 80f, 100f, 100f}»");
	}
	
	@Test
	void probCumulToDensity_FloatArray() {
	   	float[] cumSum  = new float[] {0f, 10f, 50f, 80f, 100f, 100f};
	   	float[] density = new float[] {0,  10f, 40f, 30f,  20f,   0f};    
	   	assertArrayEquals(density, probCumulToDensity(cumSum)
				, "should have been «{0,  10f, 40f, 30f,  20f,   0f}»");
	}
	
	@Test
	void testValidateLimitsInteger() {
		assertEquals(8, validateLimits(8, -88, 88)
				, "should have been «8»");
		assertEquals(88, validateLimits(888, -88, 88)
				, "should have been «88»");
		assertEquals(-88, validateLimits(-888, -88, 88)
				, "should have been «-88»");
		assertEquals(-888, validateLimits(-888, (Integer)null, 88)
				, "should have been «-888»");
		assertEquals(88, validateLimits(888, (Integer)null, 88)
				, "should have been «88»");
		assertEquals(888, validateLimits(888, (Integer)null, (Integer)null)
				, "should have been «888»");
		assertEquals(-88, validateLimits(-888, -88, (Integer)null)
				, "should have been «-88»");
		assertEquals(888, validateLimits(888, -88, (Integer)null)
				, "should have been «888»");
		assertEquals(0, validateLimits((Integer)null, -88, 88)
				, "should have been «0»");
		assertEquals(88, validateLimits((Integer)null, (Integer)null, 88)
				, "should have been «88»");
		assertEquals(-88, validateLimits((Integer)null, -88, (Integer)null)
				, "should have been «-88»");
	}

	@Test
	void testMinIntegerInteger() {
		assertEquals(-88,  min(-88, 88)
				, "should have been «-88»");
		assertEquals(-88,  min(88, -88)
				, "should have been «-88»");
		assertEquals(88,  min(88, (Integer)null)
				, "should have been «88»");
		assertEquals(-88,  min((Integer)null, -88)
				, "should have been «-88»");
		assertEquals((Integer)null,  min((Integer)null, (Integer)null)
				, "should have been «null»");
	}

	@Test
	void testMaxIntegerInteger() {
		assertEquals(88,  max(-88, 88)
				, "should have been «88»");
		assertEquals(88,  max(88, -88)
				, "should have been «88»");
		assertEquals(88,  max(88, (Integer)null)
				, "should have been «88»");
		assertEquals(-88,  max((Integer)null, -88)
				, "should have been «-88»");
		assertEquals((Integer)null,  max((Integer)null, (Integer)null)
				, "should have been «null»");
	}

	@Test
	void testValidateLimitsDouble() {
		assertEquals(8.0, validateLimits(8.0, -88.8, 88.8)
				, "should have been «8»");
		assertEquals(88.8, validateLimits(888.8, -88.8, 88.8)
				, "should have been «88.8»");
		assertEquals(-88.8, validateLimits(-888.8, -88.8, 88.8)
				, "should have been «-88.8»");
		assertEquals(-888.8, validateLimits(-888.8, (Double)null, 88.8)
				, "should have been «-888.8»");
		assertEquals(88.8, validateLimits(888.8, (Double)null, 88.8)
				, "should have been «88.8»");
		assertEquals(888.8, validateLimits(888.8, (Double)null, (Double)null)
				, "should have been «888.8»");
		assertEquals(-88.8, validateLimits(-888.8, -88.8, (Double)null)
				, "should have been «-88.8»");
		assertEquals(888.8, validateLimits(888.8, -88.8, (Double)null)
				, "should have been «888.8»");
		assertEquals(0, validateLimits((Double)null, -88.8, 88.8)
				, "should have been «0»");
		assertEquals(88.8, validateLimits((Double)null, (Double)null, 88.8)
				, "should have been «88.8»");
		assertEquals(-88.8, validateLimits((Double)null, -88.8, (Double)null)
				, "should have been «-88.8»");
	}

	@Test
	void testMinDoubleDouble() {
		assertEquals(-88.8,  min(-88.8, 88.8)
				, "should have been «-88.8»");
		assertEquals(-88.8,  min(88.8, -88.8)
				, "should have been «-88.8»");
		assertEquals(88.8,  min(88.8, (Double)null)
				, "should have been «88.8»");
		assertEquals(-88.8,  min((Double)null, -88.8)
				, "should have been «-88.8»");
		assertEquals((Double)null,  min((Double)null, (Double)null)
				, "should have been «null»");
	}

	@Test
	void testMaxDoubleDouble() {
		assertEquals(88.8,  max(-88.8, 88.8)
				, "should have been «88.8»");
		assertEquals(88.8,  max(88.8, -88.8)
				, "should have been «88.8»");
		assertEquals(88.8,  max(88.8, (Double)null)
				, "should have been «88.8»");
		assertEquals(-88.8,  max((Double)null, -88.8)
				, "should have been «-88.8»");
		assertEquals((Double)null,  max((Double)null, (Double)null)
				, "should have been «null»");
	}

	// ==============================================================
    //Random Generation Methods
    //
	@Test
	void getBooleanRandom_Boolean() {
		// to validate no errors are thrown
		assertTrue(getBooleanRandom() || true, "should have been true!");
	}

	@Test
	void nextRandomDouble_Double() {
		assertEquals(88.8,  nextRandomDouble(88.8, 88.8)
				, "should have been «88.8»");
		assertEquals(true, Math.abs(nextRandomDouble(-88.8, 88.8)) <= 88.8
				, "should have been inside the margin");
		assertEquals(true, Math.abs(nextRandomDouble(88.8, -88.8)) <= 88.8
				, "should have been able to manage min max inversion");
	}

	@Test
	void nextRandomLong_Long() {
		assertEquals(88L,  nextRandomLong(88L, 88L)
				, "should have been «88»");
		assertEquals(true, Math.abs(nextRandomLong(-88L, 88L)) <= 88L
				, "should have been inside the margin");
		assertEquals(true, Math.abs(nextRandomLong(88L, -88L)) <= 88L
				, "should have been able to manage min max inversion");
	}

	// ==============================================================
    // Test Methods
    //
	@Test
	void genericTest_String2_boolean2() {
		assertEquals(true, genericTest("xxx", "XXX", false, true)
				, "should have been: «true»");
		assertEquals(false, genericTest("xxx", "XXX", true, true)
				, "should have been: «false»");
		assertEquals(true, genericTest("xxxx", "XXX", false, false)
				, "should have been: «true»");
		assertEquals(false, genericTest("xxxx", "XXX", true, false)
				, "should have been: «false»");
	}
	
	@Test
	void anyContainsIgnoreCase_List() {
		List<String> list;
		list = null;
		assertEquals(true, anyContainsIgnoreCase(list, (String)null)
				, "should have been: «true»");
		assertEquals(false, anyContainsIgnoreCase(list, "")
				, "should have been: «false»");
		assertEquals(false, anyContainsIgnoreCase(list, "abc")
				, "should have been: «false»");
		list = List.of("");
		assertEquals(true, anyContainsIgnoreCase(list, (String)null)
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, "")
				, "should have been: «true»");
		assertEquals(false, anyContainsIgnoreCase(list, "abc")
				, "should have been: «false»");
		list = List.of("ABC", " xyz", " ");
		assertEquals(true, anyContainsIgnoreCase(list, (String)null)
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, "")
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, "abc")
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, " XYZ")
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, " ")
				, "should have been: «true»");
		assertEquals(true, anyContainsIgnoreCase(list, "XYZ")
				, "should have been: «true»");
		assertEquals(false, anyContainsIgnoreCase(list, " abc")
				, "should have been: «false»");
	}	

	@Test
	void containsIgnoreCase_List() {
		List<String> list;
		list = null;
		assertEquals(false, containsIgnoreCase(list, (String)null)
				, "should have been: «false»");
		assertEquals(false, containsIgnoreCase(list, "")
				, "should have been: «false»");
		assertEquals(false, containsIgnoreCase(list, "abc")
				, "should have been: «false»");
		list = List.of("");
		assertEquals(false, containsIgnoreCase(list, (String)null)
				, "should have been: «false»");
		assertEquals(true, containsIgnoreCase(list, "")
				, "should have been: «true»");
		assertEquals(false, containsIgnoreCase(list, "abc")
				, "should have been: «false»");
		list = List.of("ABC", " xyz", " ");
		assertEquals(false, containsIgnoreCase(list, (String)null)
				, "should have been: «false»");
		assertEquals(false, containsIgnoreCase(list, "")
				, "should have been: «false»");
		assertEquals(true, containsIgnoreCase(list, "abc")
				, "should have been: «true»");
		assertEquals(true, containsIgnoreCase(list, " XYZ")
				, "should have been: «true»");
		assertEquals(true, containsIgnoreCase(list, " ")
				, "should have been: «true»");
		assertEquals(false, containsIgnoreCase(list, "XYZ")
				, "should have been: «false»");
		assertEquals(false, containsIgnoreCase(list, " abc")
				, "should have been: «false»");
	}	

	@Test
	void containedIgnoreCase_String() {
		assertEquals(true, isContainedIgnoreCase("", "")
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase((String)null, (String)null)
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase("", (String)null)
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase((String)null, "")
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase("abcde", "ABC")
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase("ABC", "abcde")
				, "should have been: «true»");
		assertEquals(true, isContainedIgnoreCase("abc", "ABC")
				, "should have been: «true»");
		assertEquals(false, isContainedIgnoreCase("abcde", " ABC")
				, "should have been: «false»");
	}

	@Test
	void containsIgnoreCase_String() {
		assertEquals(true, containsIgnoreCase("", "")
				, "should have been: «true»");
		assertEquals(true, containsIgnoreCase((String)null, (String)null)
				, "should have been: «true»");
		assertEquals(true, containsIgnoreCase("", (String)null)
				, "should have been: «true»");
		assertEquals(false, containsIgnoreCase((String)null, "")
				, "should have been: «false»");
		assertEquals(true, containsIgnoreCase("abcde", "ABC")
				, "should have been: «true»");
		assertEquals(false, containsIgnoreCase("ABC", "abcde")
				, "should have been: «false»");
		assertEquals(true, containsIgnoreCase("abc", "ABC")
				, "should have been: «true»");
		assertEquals(false, containsIgnoreCase("abcde", " ABC")
				, "should have been: «false»");
	}

	@Test
	void isEmpty_String() {
		assertEquals(true, isEmpty("")
				, "should have been: «true»");
		assertEquals(false, isEmpty("  ")
				, "should have been: «false»");
		assertEquals(true, isEmpty((String)null)
				, "should have been: «true»");
		assertEquals(false, isEmpty("_")
				, "should have been: «false»");
	}

	@Test
	void isBlank_String() {
		assertEquals(true, isBlank("")
				, "should have been: «true»");
		assertEquals(true, isBlank("  ")
				, "should have been: «true»");
		assertEquals(true, isBlank((String)null)
				, "should have been: «true»");
		assertEquals(false, isBlank("_")
				, "should have been: «false»");
	}

	@Test
	void testForBoolean_String() {
		assertEquals(true, testForBoolean("yes")
				, "should have been: «true»");
		assertEquals(true, testForBoolean(" True ")
				, "should have been: «true»");
		assertEquals(true, testForBoolean("NO")
				, "should have been: «true»");
		assertEquals(true, testForBoolean("false")
				, "should have been: «true»");
		assertEquals(false, testForBoolean("")
				, "should have been: «false»");
		assertEquals(false, testForBoolean((String)null)
				, "should have been: «false»");
	}

	@Test
	void testForIntegerBoundaries_String() {
		assertEquals(true, testIntegerBoundaries("88", 44, 100)
				, "should have been: «true»");
		assertEquals(false, testIntegerBoundaries("88", 44, 66)
				, "should have been: «false»");
		assertEquals(false, testIntegerBoundaries("88", 100, 200)
				, "should have been: «false»");
		assertEquals(false, testIntegerBoundaries("x", 100, 200)
				, "should have been: «false»");
	}

	@Test
	void testForInteger_String() {
		assertEquals(true, testForInteger("88")
				, "should have been: «true»");
		assertEquals(true, testForInteger("88.8")
				, "should have been: «true»");
		assertEquals(false, testForInteger("x")
				, "should have been: «false»");
		assertEquals(false, testForInteger(Long.toString(Long.MAX_VALUE / 2))
				, "should have been: «false»");
		assertEquals(false, testForInteger("1e21")
				, "should have been: «false»");
	}

	@Test
	void testForNumeric_String() {
		assertEquals(false, testForNumeric("xx")
				, "not a Numeric");
		assertEquals(true,  testForNumeric("NaN")
				, "NaN is a perfectly valid Numeric");
		assertEquals(true,  testForNumeric("88.8")
				, "This Double is a perfectly valid Numeric");
	}	

	@Test
	void testForFiniteNumeric_String() {
		assertEquals(false, testForFiniteNumeric("xx")
				, "not a Numeric");
		assertEquals(false, testForFiniteNumeric("NaN")
				, "NaN is not finite Numeric");
		assertEquals(true,  testForFiniteNumeric("88.8")
				, "This Double is a perfectly valid Numeric");
	}	

	@Test
	void isFiniteDouble_Double() {
		assertEquals(false, isFiniteDouble(Double.NEGATIVE_INFINITY)
				, "not an «Finite« Double");
		assertEquals(false, isFiniteDouble(Double.NaN)
				, "not an «Finite» Double");
		assertEquals(true,  isFiniteDouble(88.8)
				, "This Double is Finite!");
	}

	// ==============================================================
    // String Conversion Tools
    //
	@Test
	void toBoolean_String() {
		assertEquals((Boolean)null, toBoolean("ss")
				, "should have been null»");
		assertEquals(true,  toBoolean("true")
				, "should have been «true»");
		assertEquals(true,  toBoolean("Yes")
				, "should have been «true»");
		assertEquals(false, toBoolean("FAlse")
				, "should have been «false»");
		assertEquals(false, toBoolean("NO")
				, "should have been «false»");
	}	

	@Test
	void toYesNoString_Boolean() {
		assertEquals("", toYesNoString((Boolean)null)
				, "should have been \"\"»");
		assertEquals("YES",  toYesNoString(true)
				, "should have been «null»");
		assertEquals("NO",   toYesNoString(false)
				, "should have been «null»");
	}	

	@Test
	void toFiniteDouble_Double() {
		Long longNull = null;
		assertEquals(longNull, toFiniteDouble(Double.NEGATIVE_INFINITY)
				, "The default value should have been returned");
		assertEquals(88.8, toFiniteDouble(88.8)
				, "This String should have been read correctly");
	}	

	@Test
	void toUsableDouble_String() {
		Double doubleNull = null;
		assertEquals(doubleNull, toFiniteDouble("NaN")
				, "«null» should have been returned");
		assertEquals(doubleNull, toFiniteDouble("inf")
				, "«null» should have been returned");
	}	

	@Test
	void toDouble_String() {
		Double doubleNull = null;
		assertEquals(123456789.0, toDouble("123456789")
				, "This String should have been read correctly");
		assertEquals(doubleNull, toDouble("2XXX")
				, "«null» should have been returned");
		assertEquals(doubleNull, toDouble("10H")
				, "«null» should have been returned");
	}

	@Test
	void toDoubleOrDefault_String() {
		assertEquals(123456789.0, toDoubleOrDefault("123456789", 88.8)
				, "This String should have been read correctly");
		assertEquals(88.8, toDoubleOrDefault("2XXX", 88.8)
				, "The default value should have been returned");
		assertEquals(Double.NaN, toDoubleOrDefault("NaN", 88.8)
				, "This String should have been read correctly");
	}

	@Test
	void toFloat_String() {
		Float floatNull = null;
		assertEquals(123456789.0f, toFloat("123456789")
				, "This String should have been read correctly");
		assertEquals(floatNull, toFloat("1e122")
				, "«null» should have been returned");
		assertEquals(floatNull, toFloat("2XXX")
				, "«null» should have been returned");
		assertEquals(floatNull, toFloat("10H")
				, "«null» should have been returned");
	}

	@Test
	void toFloatOrDefault_String() {
		assertEquals(123456789.0f, toFloatOrDefault("123456789", 88.8f)
				, "This String should have been read correctly");
		assertEquals(88.8f, toFloatOrDefault("-1e122", 88.8f)
				, "The default value should have been returned");
		assertEquals(88.8f, toFloatOrDefault("2XXX", 88.8f)
				, "The default value should have been returned");
		assertEquals(88.8f, toFloatOrDefault("NaN", 88.8f)
				, "The default value should have been returned «NaN» not managed!");
	}

	@Test
	void toLong_Double() {
		Long longNull = null;
		assertEquals(123456789L, toLong(123456789.1, 88L)
				, "This String should have been converted correctly");
		assertEquals(88L, toLong(Double.NaN, 88L)
				, "The default value should have been returned");
		assertEquals(88L, toLong(Double.MAX_VALUE, 88L)
				, "The default value should have been returned");
		assertEquals(longNull, toLong(Double.POSITIVE_INFINITY, longNull)
				, "The default value should have been returned");
	}

	@Test
	void toLong_String() {
		// pure Long reading
		Long longNull = null;
		assertEquals(123456789L, toLong("123456789")
				, "This String should have been read correctly");
		assertEquals(longNull, toLong("XXX")
				, "The default value should have been returned");
		// thru Double reading
		assertEquals(123457, toLong("123456.789")
				, "The rounded value should have been returned");
		assertEquals(longNull, toLong("123456e122")
				, "The default value should have been returned");
	}

	@Test
	void toLongOrDefault_String() {
		// pure Long reading
		assertEquals(123456789L, toLongOrDefault("123456789", 88L)
				, "This String should have been read correctly");
		assertEquals(88L, toLongOrDefault("XXX", 88L)
				, "The default value should have been returned");
		// thru Double reading
		assertEquals(123457, toLongOrDefault("123456.789", 88L)
				, "The rounded value should have been returned");
		assertEquals(88L, toLongOrDefault("123456e122", 88L)
				, "The default value should have been returned");
	}

	@Test
	void toInteger_String() {
		Integer integerNull = null;
		assertEquals(123456789, toInteger("123456789")
				, "This String should have been read correctly");
		assertEquals(integerNull, toInteger("XXX")
				, "The default value should have been returned");
		assertEquals(123457, toInteger("123456.789")
				, "The rounded value should have been returned");
		assertEquals(integerNull, toInteger("123456e122")
				, "The default value should have been returned");
	}

	@Test
	void toIntegerOrDefault_String() {
		assertEquals(123456789, toIntegerOrDefault("123456789", 88)
				, "This String should have been read correctly");
		assertEquals(88, toIntegerOrDefault("XXX", 88)
				, "The default value should have been returned");
		assertEquals(123457, toIntegerOrDefault("123456.789", 88)
				, "The rounded value should have been returned");
		assertEquals(88, toIntegerOrDefault("123456e122", 88)
				, "The default value should have been returned");
	}

	@Test
	void toShort_String() {
		assertEquals((short)1234, toShort("1234")
				, "This String should have been read correctly");
		assertEquals((Short)null, toShort("XXX")
				, "The default value should have been returned");
		assertEquals((short)1235, toShort("1234.56789")
				, "The rounded value should have been returned");
		assertEquals((Short)null, toShort("123456e122")
				, "The default value should have been returned");
	}

	@Test
	void toShortOrDefault_String() {
		assertEquals((short)88, toShortOrDefault("123456789", (short)88)
				, "The default value should have been returned");
		assertEquals((short)88, toShortOrDefault("XXX", (short)88)
				, "The default value should have been returned");
		assertEquals((short)12346, toShortOrDefault("12345.6789", (short)88)
				, "The rounded value should have been returned");
		assertEquals((short)88, toShortOrDefault("123456e122", (short)88)
				, "The default value should have been returned");
	}

	@Test
	void toByte_String() {
		assertEquals((Byte)null, toByte("123456789")
				, "The default value should have been returned");
		assertEquals((Byte)null, toByte("XXX")
				, "The default value should have been returned");
		assertEquals((byte)123, toByte("123.456789")
				, "The rounded value should have been returned");
		assertEquals((Byte)null, toByte("123456e122")
				, "The default value should have been returned");
	}

	@Test
	void toByteOrDefault_String() {
		assertEquals((byte)-88, toByteOrDefault("-88", (byte)88)
				, "This String should have been read correctly");
		assertEquals((byte)88, toByteOrDefault("XXX", (byte)88)
				, "The default value should have been returned");
		assertEquals((byte)12, toByteOrDefault("12.3456789", (byte)88)
				, "The rounded value should have been returned");
		assertEquals((byte)88, toByteOrDefault("123456e122", (byte)88)
				, "The default value should have been returned");
	}
}
