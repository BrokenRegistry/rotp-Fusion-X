package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import br.profileManager.src.main.java.Valid_LocalEnable.Line_LocalEnable;

class Valid_LocalEnableTest {
	
	@Test
	void L_LocalEnable_None() {
		assertEquals(Valid_LocalEnable.PARAMETER_NAME, 
				new Line_LocalEnable().getName()
				, "should have been \"¦ LOCAL ENABLE\"");
		assertEquals("All", 
				new Line_LocalEnable().getValue().getUserView()
				, "should have been \"All\"");
	}

	@Test
	void L_LocalEnable_String() {
		assertEquals(Valid_LocalEnable.PARAMETER_NAME, 
				new Line_LocalEnable("xxx").getName()
				, "should have been \"xxx\"");
	}

	@Test
	void isLoadEnabled_None() {
		assertEquals(true, 
				new Line_LocalEnable().isLoadEnabled()
				, "should have been «true»");
		Line_LocalEnable testLE = new Line_LocalEnable();
		testLE.setValue("NONE");
		assertEquals(false, 
				testLE.isLoadEnabled()
				, "should have been «false»");
	}

	@Test
	void isWriteEnabled_None() {
		assertEquals(true, 
				new Line_LocalEnable().isWriteEnabled()
				, "should have been «true»");
		Line_LocalEnable testLE = new Line_LocalEnable();
		testLE.setValue("NONE");
		assertEquals(false, 
				testLE.isWriteEnabled()
				, "should have been «false»");
	}

//	@Test
//	void isLocal_None() {
//		assertEquals(true, 
//				new Line_LocalEnable().isLocal()
//				, "should have been «true»");
//	}

	@Test
	void isBlankValue_None() {
		assertEquals(false, 
				new Line_LocalEnable().isBlankValue()
				, "should have been «false»");
		Line_LocalEnable testLE = new Line_LocalEnable();
		testLE.setValue("");
		assertEquals(true, 
				testLE.isBlankValue()
				, "should have been «true»");
	}

	@Test
	void toString_None() {
		assertEquals("¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]", 
				new Line_LocalEnable().toString()
				, "should have been \"¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\"");
	}
}
