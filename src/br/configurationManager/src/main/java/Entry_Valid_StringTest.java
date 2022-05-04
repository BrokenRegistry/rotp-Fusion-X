package br.configurationManager.src.main.java;

import static br.configurationManager.src.main.java.CMutil.getDefaultCaseSensitivity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Entry_Valid_StringTest {

	Valid_ConfigAction vd = new Valid_ConfigAction();
	Entry_Valid_String entry  = new Entry_Valid_String(vd);
	Entry_Valid_String origin = new Entry_Valid_String(vd);
	
	@Test
	void getValue_None() {
		assertEquals("abc", entry.set("abc").getValue()
				, "should have been «abc»");
	}

	@Test
	void Entry_Valid_String_VD() {
		assertEquals("xyz", new Entry_Valid_String(vd).set("abc").set("xyz").getValue()
				, "should have been «xyz»");
	}
	
	@Test
	void Entry_Valid_String_VD_String() {
		assertEquals("abc", new Entry_Valid_String(vd, "abc").getValue()
				, "should have been «abc»");
	}

	@Test
	void getOrDefault_VD_String() {
		assertEquals("abc", new Entry_Valid_String(vd, "abc").getOrDefault("xxx")
				, "should have been «abc»");
		assertEquals("xxx", new Entry_Valid_String(vd, "").getOrDefault("xxx")
				, "should have been «xxx»");
		assertEquals("xxx", new Entry_Valid_String(vd).getOrDefault("xxx")
				, "should have been «xxx»");
	}
	
	@Test
	void getOrDefault_Entry_Vali_String() {
		origin.set("xyz");
		assertEquals("xyz", new Entry_Valid_String(vd).getOrDefault(origin)
				, "should have been «xyz»");
	}
	
	@Test
	void reset_None() {
		assertEquals("", new Entry_Valid_String(vd, "abc").reset().getValue()
				, "should have been «»");
	}

	@Test
	void set_String() {
		assertEquals("abc", new Entry_Valid_String(vd).set("abc").getValue()
				, "should have been «abc»");
	}

	@Test
	void clone_None() {
		assertEquals("xyz", entry.set("abc").clone().set("xyz").getValue()
				, "should have been «xyz»");
		assertEquals("abc", entry.getValue()
				, "should have been «abc»");
	}

	@Test
	void copy_None() {
		assertEquals("xyz", entry.set("abc").copy().set("xyz").getValue()
				, "should have been «xyz»");
		assertEquals("xyz", entry.getValue()
				, "should have been «xyz»");
	}

	@Test
	void toCapitalized_Boolean() {
		assertEquals("Abc xxx", new Entry_Valid_String(vd, "abc xxx").toCapitalized(true)
				, "should have been «Abc xxx»");
		assertEquals("Abc Xxx", new Entry_Valid_String(vd, "abc xxx").toCapitalized(false)
				, "should have been «Abc Xxx»");
	}
	
	@Test
	void toCapitalized_None() {
		assertEquals("Abc Xxx", new Entry_Valid_String(vd, "abc xxx").toCapitalized()
				, "should have been «Abc Xxx»");
	}
	
	@Test
	void toSentence_None() {
		assertEquals("Abc xxx", new Entry_Valid_String(vd, "abc xxx").toSentence()
				, "should have been «Abc xxx»");
	}
	
	@Test
	void testForNumeric_None() {
		assertEquals(false, entry.set("xyz").testForNumeric()
				, "should have been «false»");
		assertEquals(true, entry.set("88").testForNumeric()
				, "should have been «true»");
		assertEquals(true, entry.set("-88.8").testForNumeric()
				, "should have been «true»");
	}
	
	@Test
	void removeComment_None() {
		assertEquals("", entry.set("# abc").removeComment().getValue()
				, "should have been «»");
		assertEquals("xyz", entry.set("xyz    # abc").removeComment().getValue()
				, "should have been «xyz»");
	}
	
	@Test
	void toComment_None() {
		assertEquals("# abc", entry.set("abc").toComment()
				, "should have been «# abc»");
		assertEquals("# ", entry.set("").toComment()
				, "should have been «# »");
	}
	
	@Test
	void toComment_Boolean() {
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
	void toPrint_None() {
		assertEquals("abc", entry.set("abc").toPrint()
				, "should have been «abc»");
	}
	
	@Test
	void toString_None() {
		assertEquals("abc", entry.set("abc").toString()
				, "should have been «abc»");
	}
}
