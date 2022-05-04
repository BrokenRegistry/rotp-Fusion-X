package br.configurationManager.src.main.java;

import static br.configurationManager.src.main.java.CMutil.getDefaultCaseSensitivity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Entry_StringTest {

	Entry_String entry  = new Entry_String();
	Entry_String origin = new Entry_String();
	
	@Test
	void testGetValue() {
		assertEquals("abc", entry.set("abc").getValue()
				, "should have been «abc»");
	}
	@Test
	void testEntry_String2() {
		assertEquals("xyz", new Entry_String().set("abc").set("xyz").getValue()
				, "should have been «xyz»");
	}
	@Test
	void testEntry_String2Entry_String2() {
		assertEquals("xyz", origin.set("xyz").setCaseSensitive(true).getValue()
				, "should have been «xyz»");
		assertEquals("xyz", new Entry_String(origin).getValue()
				, "should have been «xyz»");
		assertEquals(true, new Entry_String(origin).getCaseSensitive()
				, "should have been «true»");
	}
	@Test
	void testEntry_String2String() {
		assertEquals("abc", new Entry_String("abc").getValue()
				, "should have been «abc»");
	}
	@Test
	void testEntry_String2Entry_String2Boolean() {
		assertEquals("xyz", origin.set("xyz").setCaseSensitive(true).getValue()
				, "should have been «xyz»");
		assertEquals(false, new Entry_String(origin, false).getCaseSensitive()
				, "should have been «false»");
	}
	@Test
	void testEntry_String2StringBoolean() {
		assertEquals("abc", new Entry_String("abc", true).getValue()
				, "should have been «abc»");
		assertEquals(true, new Entry_String("abc", true).getCaseSensitive()
				, "should have been «true»");
	}
	@Test
	void testGetOrDefaultString() {
		assertEquals("abc", new Entry_String("abc").getOrDefault("xxx")
				, "should have been «abc»");
		assertEquals("xxx", new Entry_String("").getOrDefault("xxx")
				, "should have been «xxx»");
		assertEquals("xxx", new Entry_String().getOrDefault("xxx")
				, "should have been «xxx»");
	}
	@Test
	void testGetOrDefaultEntry_String2() {
		origin.set("xyz");
		assertEquals("xyz", new Entry_String().getOrDefault(origin)
				, "should have been «xyz»");
	}
	@Test
	void testReset() {
		assertEquals("", new Entry_String("abc").reset().getValue()
				, "should have been «»");
		assertEquals((Boolean)null , new Entry_String("abc", true).reset().getCaseSensitive()
				, "should have been «null»");
	}
	@Test
	void testSetString() {
		assertEquals("abc", new Entry_String().set("abc").getValue()
				, "should have been «abc»");
	}
	@Test
	void testClone() {
		assertEquals("xyz", entry.set("abc").clone().set("xyz").getValue()
				, "should have been «xyz»");
		assertEquals("abc", entry.getValue()
				, "should have been «abc»");
	}
	@Test
	void testCopy() {
		assertEquals("xyz", entry.set("abc").copy().set("xyz").getValue()
				, "should have been «xyz»");
		assertEquals("xyz", entry.getValue()
				, "should have been «xyz»");
	}
	@Test
	void testSetStringBoolean() {
		assertEquals("abc", new Entry_String().set("abc", true).getValue()
				, "should have been «abc»");
		assertEquals(true, new Entry_String().set("abc", true).getCaseSensitive()
				, "should have been «true»");
	}
	@Test
	void testSetEntry_String2() {
		assertEquals("xyz", origin.set("xyz").setCaseSensitive(true).getValue()
				, "should have been «xyz»");
		assertEquals("xyz", new Entry_String().set(origin).getValue()
				, "should have been «xyz»");
		assertEquals(true, new Entry_String().set(origin).getCaseSensitive()
				, "should have been «true»");
	}
	@Test
	void testSetEntry_String2Boolean() {
		assertEquals("xyz", origin.set("xyz").setCaseSensitive(true).getValue()
				, "should have been «xyz»");
		assertEquals(false, new Entry_String().set(origin, false).getCaseSensitive()
				, "should have been «false»");
	}
	@Test
	void testSetCaseSensitive() {
		assertEquals(false, new Entry_String().setCaseSensitive(false).getCaseSensitive()
				, "should have been «false»");
		assertEquals(true, new Entry_String().setCaseSensitive(true).getCaseSensitive()
				, "should have been «true»");
	}
	@Test
	void testGetCaseSensitive() {
		assertEquals(false, new Entry_String().setCaseSensitive(false).getCaseSensitive()
				, "should have been «false»");
		assertEquals(true, new Entry_String().setCaseSensitive(true).getCaseSensitive()
				, "should have been «true»");
		assertEquals((Boolean)null, new Entry_String().getCaseSensitive()
				, "should have been «null»");
	}
	@Test
	void testIsCaseSensitive() {
		assertEquals(false, new Entry_String().setCaseSensitive(false).isCaseSensitive()
				, "should have been «false»");
		assertEquals(true, new Entry_String().setCaseSensitive(true).isCaseSensitive()
				, "should have been «true»");
		assertEquals(getDefaultCaseSensitivity(), new Entry_String().isCaseSensitive()
				, "should have been «default value»");
	}
	@Test
	void testToTest() {
		assertEquals("ABC", new Entry_String().set("abc", false).toTest()
				, "should have been «ABC»");
		assertEquals("abc", new Entry_String().set("abc", true).toTest()
				, "should have been «abc»");
	}
	@Test
	void testEqualsString() {
		assertEquals(false, new Entry_String("abc",true).equals("ABC")
				, "should have been «false»");
		assertEquals(true, new Entry_String("abc",false).equals("ABC")
				, "should have been «true»");
	}
	@Test
	void testEqualsEntry_String2() {
		assertEquals(false, new Entry_String("abc",true).equals(new Entry_String("ABC",true))
				, "should have been «false»");
		assertEquals(true, new Entry_String("abc",true).equals(new Entry_String("ABC",false))
				, "should have been «true»");
		assertEquals(true, new Entry_String("abc",false).equals(new Entry_String("ABC",true))
				, "should have been «true»");
	}
	@Test
	void testContainsString() {
		assertEquals(false, new Entry_String("abc",true).contains("BC")
				, "should have been «false»");
		assertEquals(true, new Entry_String("abc",false).contains("BC")
				, "should have been «true»");
		assertEquals(false, new Entry_String("abc",false).contains("AC")
				, "should have been «false»");
	}
	@Test
	void testContainsEntry_String2() {
		assertEquals(false, new Entry_String("abc",true).contains(new Entry_String("BC",true))
				, "should have been «false»");
		assertEquals(true, new Entry_String("abc",true).contains(new Entry_String("BC",false))
				, "should have been «true»");
		assertEquals(true, new Entry_String("abc",false).contains(new Entry_String("BC",true))
				, "should have been «true»");
	}
	@Test
	void testIsContainedString() {
		assertEquals(false, new Entry_String("ab",true).isContained("ABC")
				, "should have been «false»");
		assertEquals(true, new Entry_String("bc",false).isContained("ABC")
				, "should have been «true»");
		assertEquals(false, new Entry_String("ac",false).isContained("ABC")
				, "should have been «false»");
	}
	@Test
	void testIsContainedEntry_String2() {
		assertEquals(false, new Entry_String("ab",true)
				.isContained(new Entry_String("ABC",true))
				, "should have been «false»");
		assertEquals(true, new Entry_String("bc",true)
				.isContained(new Entry_String("ABC",false))
				, "should have been «true»");
		assertEquals(true, new Entry_String("bc",false)
				.isContained(new Entry_String("ABC",true))
				, "should have been «true»");
	}
	@Test
	void testToCapitalizedBoolean() {
		assertEquals("Abc xxx", new Entry_String("abc xxx").toCapitalized(true)
				, "should have been «Abc xxx»");
		assertEquals("Abc Xxx", new Entry_String("abc xxx").toCapitalized(false)
				, "should have been «Abc Xxx»");
	}
	@Test
	void testToCapitalized() {
		assertEquals("Abc Xxx", new Entry_String("abc xxx").toCapitalized()
				, "should have been «Abc Xxx»");
	}
	@Test
	void testToSentence() {
		assertEquals("Abc xxx", new Entry_String("abc xxx").toSentence()
				, "should have been «Abc xxx»");
	}
	@Test
	void testTestForNumeric() {
		assertEquals(false, entry.set("xyz").testForNumeric()
				, "should have been «false»");
		assertEquals(true, entry.set("88").testForNumeric()
				, "should have been «true»");
		assertEquals(true, entry.set("-88.8").testForNumeric()
				, "should have been «true»");
	}
	@Test
	void testRemoveComment() {
		assertEquals("", entry.set("# abc").removeComment().getValue()
				, "should have been «»");
		assertEquals("xyz", entry.set("xyz    # abc").removeComment().getValue()
				, "should have been «xyz»");
	}
	@Test
	void testToComment() {
		assertEquals("# abc", entry.set("abc").toComment()
				, "should have been «# abc»");
		assertEquals("# ", entry.set("").toComment()
				, "should have been «# »");
	}
	@Test
	void testToCommentBoolean() {
		assertEquals("# abc", entry.set("abc").toComment(true)
				, "should have been «# abc»");
		assertEquals("# ", entry.set("").toComment(true)
				, "should have been «# »");
		assertEquals("", entry.set("").toComment(false)
				, "should have been «»");
		assertEquals((String)null, entry.set("").toComment((Boolean)null)
				, "should have been «null»");
	}
	@Test
	void testToPrint() {
		assertEquals("abc", entry.set("abc").toPrint()
				, "should have been «abc»");
	}
	@Test
	void testToString() {
		assertEquals("abc", entry.set("abc").toString()
				, "should have been «abc»");
	}
}
