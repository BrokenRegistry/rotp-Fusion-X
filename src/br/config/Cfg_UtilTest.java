/**
 * 
 */
package br.config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static br.config.Cfg_Util.*;

/**
 * @author BrokenRegistry
 *
 */
class Cfg_UtilTest {

	@Test
	void testForIntegerBoundariesString() {
		assertEquals(true, testIntegerBoundaries("88", 44, 100)
				, "should have been: true");
		assertEquals(false, testIntegerBoundaries("88", 44, 66)
				, "should have been: false");
		assertEquals(false, testIntegerBoundaries("88", 100, 200)
				, "should have been: false");
		assertEquals(false, testIntegerBoundaries("x", 100, 200)
				, "should have been: false");
	}
	@Test
	void testForIntegerString() {
		assertEquals(true, testForInteger("88")
				, "should have been: true");
		assertEquals(true, testForInteger("88.8")
				, "should have been: true");
		assertEquals(false, testForInteger("x")
				, "should have been: false");
		assertEquals(false, testForInteger(Long.toString(Long.MAX_VALUE / 2))
				, "should have been: false");
		assertEquals(false, testForInteger("1e21")
				, "should have been: false");
	}
	@Test
	void neverNullObject() {
		assertEquals("Source", neverNull("Source")
				, "should have been: Source");
		assertEquals("  source   ", neverNull("  source   ")
				, "should have been: \"  source   \"");		
		assertEquals("", neverNull((String)null)
				, "should have been: \"\"");		
		assertEquals("127", neverNull(Byte.MAX_VALUE)
				, "should have been: 127");		
		assertEquals("Source", neverNull(new Cfg_Entry("  Source  "))
				, "should have been: \"Source\""); // Cfg_Entry clean the string
	}
	@Test
	void cleanObject() {
		assertEquals("Source", clean("Source")
				, "should have been: Source");
		assertEquals("source", clean("  source   ")
				, "should have been: source");		
		assertEquals("", clean((String)null)
				, "should have been: \"\"");		
		assertEquals("127", clean(Byte.MAX_VALUE)
				, "should have been: 127");		
		assertEquals("Source", clean(new Cfg_Entry("  Source  "))
				, "should have been: Source");		
	}
	@Test
	void toKeyString() {
		assertEquals("SOURCE", toKey("Source")
				, "should have been: SOURCE");
		assertEquals("SOURCE", toKey("  source   ")
				, "should have been: SOURCE");		
		assertEquals("", toKey((String)null)
				, "should have been: \"\"");		
	}
	@Test
	void toSentenceString() {
		assertEquals("Source", toSentence("Source")
				, "should have been Source");
		assertEquals("Source", toSentence("source")
				, "should have been Source");
		assertEquals("Source", toSentence("SOURCE")
				, "should have been Source");
		assertEquals("Option source", toSentence("OPTION SOURCE")
				, "should have been: Option source");
		assertEquals("Very option source", toSentence("VERY OPTION SOURCE")
				, "should have been: Very option source");
		assertEquals("Option  source", toSentence("OPTION  SOURCE")
				, "should have been: Option  source");
		assertEquals("Source", toSentence(" SOURCE")
				, "should have been Source");
		assertEquals("Source", toSentence("  SOURCE  ")
				, "should have been Source");
		assertEquals("", toSentence("    ")
				, "should have been \"\"");
		assertEquals("", toSentence((String)null)
				, "should have been \"\"");
	}
	@Test
	void capitalizeString() {
		assertEquals("Source", capitalize("Source")
				, "should have been Source");
		assertEquals("Source", capitalize("source")
				, "should have been Source");
		assertEquals("Source", capitalize("SOURCE")
				, "should have been Source");
		assertEquals("Option Source", capitalize("OPTION SOURCE")
				, "should have been: Option Source");
		assertEquals("Very Option Source", capitalize("VERY OPTION SOURCE")
				, "should have been: Very Option Source");
		assertEquals("Option  Source", capitalize("OPTION  SOURCE")
				, "should have been: Option  Source");
		assertEquals("Source", capitalize(" SOURCE")
				, "should have been Source");
		assertEquals("Source", capitalize("  SOURCE  ")
				, "should have been Source");
		assertEquals("", capitalize("    ")
				, "should have been \"\"");
		assertEquals("", capitalize((String)null)
				, "should have been \"\"");
	}
	@Test
	void capitalizeBoolean() {
		assertEquals("Very option source", capitalize("VERY OPTION SOURCE", true)
				, "should have been: Very option source");
		assertEquals("Very Option Source", capitalize("VERY OPTION SOURCE", false)
				, "should have been: Very Option Source");		
	}
	@Test
	void suggestedOptionToLabelString() {
		assertEquals("Source", suggestedOptionToLabel("Source")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("source")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("SOURCE")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("OPTION_SOURCE")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("VERY_OPTION_SOURCE")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("OPTION__SOURCE")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("_SOURCE")
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel("__SOURCE")
				, "should have been Source");
	}
	@Test
	void suggestedOptionToLabelCfg_Entry() {
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("Source").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("source").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("SOURCE").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("OPTION_SOURCE").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("VERY_OPTION_SOURCE").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("OPTION__SOURCE").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("_SOURCE").get())
				, "should have been Source");
		assertEquals("Source", suggestedOptionToLabel(new Cfg_Entry("__SOURCE").get())
				, "should have been Source");
	}
	@Test
	void getOrDefaultString() {
		assertEquals("Source", getOrDefault("Source", "Good")
				, "should have been Source");
		assertEquals("Good", getOrDefault("", "Good")
				, "should have been Good");
		assertEquals("Good", getOrDefault("  ", "Good")
				, "should have been Source");
		assertEquals("Good", getOrDefault((String)null, "Good")
				, "should have been Source");
	}
	@Test
	void getBooleanRandomBoolean() {
		// to validate no errors are thrown
		assertTrue(getBooleanRandom() || true, "should have been true!");
	}
	@Test
	void nextRandomDoubleDouble() {
		assertEquals(88.8,  nextRandomDouble(88.8, 88.8)
				, "should have been 88L");
		assertEquals(true, Math.abs(nextRandomDouble(-88.8, 88.8)) <= 88.8
				, "should have been inside the margin");
		assertEquals(true, Math.abs(nextRandomDouble(88.8, -88.8)) <= 88.8
				, "should have been able to manage min max inversion");
	}
	@Test
	void nextRandomLongLong() {
		assertEquals(88L,  nextRandomLong(88L, 88L)
				, "should have been 88L");
		assertEquals(true, Math.abs(nextRandomLong(-88L, 88L)) <= 88L
				, "should have been inside the margin");
		assertEquals(true, Math.abs(nextRandomLong(88L, -88L)) <= 88L
				, "should have been able to manage min max inversion");
	}
	// ==============================================================
    // String Conversion Tools
    //
	@Test
	void toBooleanString() {
		assertEquals((Boolean)null, toBoolean("ss")
				, "should have been null");
		assertEquals(true,  toBoolean("true")
				, "should have been true");
		assertEquals(true,  toBoolean("Yes")
				, "should have been true");
		assertEquals(false, toBoolean("FAlse")
				, "should have been false");
		assertEquals(false, toBoolean("NO")
				, "should have been false");
	}	
	@Test
	void toYesNoStringBoolean() {
		assertEquals("null", toYesNoString((Boolean)null)
				, "should have been \"null\"");
		assertEquals("YES",  toYesNoString(true)
				, "should have been \"null\"");
		assertEquals("NO",   toYesNoString(false)
				, "should have been \"null\"");
	}	
	@Test
	void isNumericString() {
		assertEquals(false, testForNumeric("xx")
				, "not a Numeric");
		assertEquals(true,  testForNumeric("NaN")
				, "NaN is a perfectly valid Numeric");
		assertEquals(true,  testForNumeric("88.8")
				, "This Double is a perfectly valid Numeric");
	}	
	@Test
	void isFiniteNumericString() {
		assertEquals(false, testForFiniteNumeric("xx")
				, "not a Numeric");
		assertEquals(false, testForFiniteNumeric("NaN")
				, "NaN is not finite Numeric");
		assertEquals(true,  testForFiniteNumeric("88.8")
				, "This Double is a perfectly valid Numeric");
	}	
	@Test
	void isFiniteDoubleDouble() {
		assertEquals(false, isFiniteDouble(Double.NEGATIVE_INFINITY)
				, "not an \"Finite\" Double");
		assertEquals(false, isFiniteDouble(Double.NaN)
				, "not an \"Finite\" Double");
		assertEquals(true,  isFiniteDouble(88.8)
				, "This Double is Finite!");
	}	
	@Test
	void toFiniteDoubleDouble() {
		Long longNull = null;
		assertEquals(longNull, toFiniteDouble(Double.NEGATIVE_INFINITY)
				, "The default value should have been returned");
		assertEquals(88.8, toFiniteDouble(88.8)
				, "This String should have been read correctly");
	}	
	@Test
	void toUsableDoubleString() {
		Double doubleNull = null;
		assertEquals(doubleNull, toFiniteDouble("NaN")
				, "null should have been returned");
		assertEquals(doubleNull, toFiniteDouble("inf")
				, "null should have been returned");
	}	
	@Test
	void toDoubleString() {
		Double doubleNull = null;
		assertEquals(123456789.0, toDouble("123456789")
				, "This String should have been read correctly");
		assertEquals(doubleNull, toDouble("2XXX")
				, "null should have been returned");
		assertEquals(doubleNull, toDouble("10H")
				, "null should have been returned");
	}
	@Test
	void toDoubleOrDefaultString() {
		assertEquals(123456789.0, toDoubleOrDefault("123456789", 88.8)
				, "This String should have been read correctly");
		assertEquals(88.8, toDoubleOrDefault("2XXX", 88.8)
				, "The default value should have been returned");
		assertEquals(Double.NaN, toDoubleOrDefault("NaN", 88.8)
				, "This String should have been read correctly");
	}
	@Test
	void toLongDouble() {
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
	void toLongString() {
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
	void toLongOrDefaultString() {
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
	void toIntegerString() {
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
	void toIntegerOrDefaultString() {
		assertEquals(123456789, toIntegerOrDefault("123456789", 88)
				, "This String should have been read correctly");
		assertEquals(88, toIntegerOrDefault("XXX", 88)
				, "The default value should have been returned");
		assertEquals(123457, toIntegerOrDefault("123456.789", 88)
				, "The rounded value should have been returned");
		assertEquals(88, toIntegerOrDefault("123456e122", 88)
				, "The default value should have been returned");
	}
}
