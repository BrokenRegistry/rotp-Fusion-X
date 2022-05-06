package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Generic_ValidTest {

	Valid_ConfigAction vd = new Valid_ConfigAction();
	Entry_Test_Valid_String entry  = new Entry_Test_Valid_String(vd);
	Entry_Test_Valid_String origin = new Entry_Test_Valid_String(vd);

	@Test
	void toString_None() {
		assertEquals("abc", entry.set("abc").toString()
				, "should have been «abc»");
	}
	
	@Test
	void isBlank_None() {
		assertEquals(true, entry.set("").isBlank()
				, "should have been «true»");
	}
	
	@Test
	void entry_Base_None() {
		assertEquals("xyz", new Entry_Test_Valid_String(vd).set("abc").set("xyz").getUserEntry()
				, "should have been «xyz»");
	}
	
	@Test
	void entry_Base_String() {
		assertEquals("abc", new Entry_Test_Valid_String(vd, "abc").getUserEntry()
				, "should have been «abc»");
	}
	
	@Test
	void get_None() {
		assertEquals("abc", entry.set("abc").getUserEntry()
				, "should have been «abc»");
	}
	
	@Test
	void set_String() {
		assertEquals("xyz", new Entry_Test_Valid_String(vd, "xxx").set("abc").set("xyz").getUserEntry()
				, "should have been «xyz»");
	}
	
	@Test
	void reset_None() {
		assertEquals("", entry.set("abc").reset().getUserEntry()
				, "should have been «»");
	}

	@Test
	void copy_None() {
		assertEquals("xyz", entry.set("abc").copy().set("xyz").getUserEntry()
				, "should have been «xyz»");
		assertEquals("xyz", entry.getUserEntry()
				, "should have been «xyz»");
	}

	@Test
	void toCapitalized_Boolean() {
		assertEquals("Xyz", entry.set("xyz").toCapitalized(true)
				, "should have been «Xyz»");
		assertEquals("Xyz abc", entry.set("xyz ABC").toCapitalized(true)
				, "should have been «Xyz abc»");
		assertEquals("Xyz Abc", entry.set("xyz ABC").toCapitalized(false)
				, "should have been «Xyz Abc»");
	}

	@Test
	void toCapitalized_None() {
		assertEquals("Xyz", entry.set("xyz").toCapitalized()
				, "should have been «Xyz»");
		assertEquals("Xyz Abc", entry.set("xyz ABC").toCapitalized()
				, "should have been «Xyz Abc»");
	}
	
	@Test
	void toSentence_None() {
		assertEquals("Xyz", entry.set("xyz").toSentence()
				, "should have been «Xyz»");
		assertEquals("Xyz abc", entry.set("xyz ABC").toSentence()
				, "should have been «Xyz abc»");
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
	void removeComment_String() {
		assertEquals("", entry.set("# abc").removeComment().getUserEntry()
				, "should have been «»");
		assertEquals("xyz", entry.set("xyz    # abc").removeComment().getUserEntry()
				, "should have been «xyz»");
	}
	
	@Test
	void getOrDefault_ValueClass() {
		assertEquals("xyz", entry.set(" ").getOrDefault("xyz")
				, "should have been «xyz»");
		assertEquals("abc", entry.set("abc").getOrDefault("xyz")
				, "should have been «abc»");
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
	
	class Entry_Test_Valid_String extends Generic_Valid<String> {
	    // ==================================================
	    // Constructors
	    //
		/**
		 * create a new empty {@code Entry_String}
		 */
		Entry_Test_Valid_String(Abstract_ValidData<String> validationData) {
			super(validationData);
		}
		/**
		 * create and initialize a new {@code Entry_String}
		 * @param value the {@code String} value
		 */

		Entry_Test_Valid_String(Abstract_ValidData<String> validationData, String value) {
			super(validationData, value);
		}

		// ==================================================
	    // Abstract Methods overriders + extended
	    //
		/**
		 * return value as {@code String}
		 * @return the original value
		 */
		@Override
		public String getValue() { 
			return getUserEntry();
		}
		@Override
		public String toString() {
			return getUserEntry();
		}
	}
}
