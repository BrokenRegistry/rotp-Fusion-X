package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationElementTest {

	// ========== Constructors ==========
	@Test
	void ValidationElement_String() {
		assertEquals("XXX", new ValidationElement("XXX")
				.getCodeView()
				, "should have been «XXX»");
	}

	@Test
	void ValidationElement_String2() {
		assertEquals("Bla bla", new ValidationElement("XXX", "Bla bla")
				.getDescription()
				, "should have been «Bla bla»");
	}

	@Test
	void ValidationElement_String3() {
		assertEquals("Test", 
				new ValidationElement("XXX", "Bla bla", "Test")
				.getCategory()
				, "should have been «Test»");
	}

	@Test
	void ValidationElement_String4() {
		assertEquals("yyy", 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.getUserView()
				, "should have been «yyy»");
	}

	// ========== Getters ==========
	@Test
	void toString_None() {
		assertEquals("yyy               = Bla bla", 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.toString()
				, "should have been «yyy               = Bla bla»");
	}

	@Test
	void toPrint_None() {
		assertEquals("# yyy               = Bla bla", 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.toPrint()
				, "should have been «# yyy               = Bla bla»");
	}

	// ========== Testers ==========
	@Test
	void isValidCodeView_String_VC() {
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidCodeView("XXX", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidCodeView("xxx", new ValidationCriteria())
				, "should have been «false»");
	}

	@Test
	void isValidUserEntry_String_VC() {
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yyy", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("Yyy", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yy", new ValidationCriteria())
				, "should have been «false»");
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("Yy",
						new ValidationCriteria().userViewEquals(false))
				, "should have been «true»");
	}

	@Test
	void isValidUserEntry_String2_VC() {
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yyy", "Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yy", "Test", new ValidationCriteria())
				, "should have been «false»");
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yyy", "te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidUserEntry("yyy", "ttt", new ValidationCriteria())
				, "should have been «false»");
	}

	@Test
	void isValidCategory_String_VC() {
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidCategory("Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidCategory("te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isValidCategory("ttt", new ValidationCriteria())
				, "should have been «false»");
	}

	@Test
	void isMember_String_VC() {
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isMember("Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isMember("te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new ValidationElement("yyy", "XXX", "Bla bla", "Test")
				.isMember("ttt", new ValidationCriteria())
				, "should have been «false»");
	}
}
