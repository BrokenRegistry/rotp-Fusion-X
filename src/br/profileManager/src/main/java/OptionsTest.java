package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OptionsTest {

	// ========== Constructors ==========
	@Test
	void ValidationElement_String() {
		assertEquals("XXX", new Options<String>("XXX")
				.getCodeView()
				, "should have been «XXX»");
	}

	@Test
	void ValidationElement_String2() {
		assertEquals("", new Options<String>("XXX", "Bla bla")
				.getDescription()
				, "should have been «\"\"»");
	}

	@Test
	void ValidationElement_String3() {
		assertEquals("Test", 
				new Options<String>("XXX", "Bla bla", "Test")
				.getCategory()
				, "should have been «Test»");
	}

	@Test
	void ValidationElement_String4() {
		assertEquals("yyy", 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.getUserView()
				, "should have been «yyy»");
	}

	// ========== Getters ==========	
	@Test
	void toString_None() {
		assertEquals("yyy           = Bla bla", 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.toString()
				, "should have been «yyy           = Bla bla»");
	}

	// ========== Testers ==========
	@Test
	void isValidCodeView_String_VC() {
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidCodeView("XXX", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidCodeView("xxx", new ValidationCriteria())
				, "should have been «true»");
	}
	
	@Test
	void isValidUserEntry_String_VC() {
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yyy", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("Yyy", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yy", new ValidationCriteria())
				, "should have been «false»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("Yyy, zzz",
						new ValidationCriteria().userViewEquals(false))
				, "should have been «true»");
	}
	
	@Test
	void isValidUserEntry_String2_VC() {
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yyy", "Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yy", "Test", new ValidationCriteria())
				, "should have been «false»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yyy", "te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidUserView("yyy", "ttt", new ValidationCriteria())
				, "should have been «false»");
	}
	
	@Test
	void isValidCategory_String_VC() {
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidCategory("Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidCategory("te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isValidCategory("ttt", new ValidationCriteria())
				, "should have been «false»");
	}

	@Test
	void isMember_String_VC() {
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isMember("Test", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(true, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isMember("te", new ValidationCriteria())
				, "should have been «true»");
		assertEquals(false, 
				new Options<String>("XXX", "yyy", "Bla bla", "Test")
				.isMember("ttt", new ValidationCriteria())
				, "should have been «false»");
	}
}
