package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Line_BooleanTest {

	// ========== Constructors ==========
	@Test
	void Line_String_None() {
		assertEquals((Boolean)null, new Line_Boolean().getValue()
				, "should have been «null»");
	}
	
	@Test
	void Line_String_String() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.getValue()
				, "should have been «true»");
		assertEquals("comment", new Line_Boolean("key : YES # comment")
				.getComment()
				, "should have been «comment»");
		assertEquals("key", new Line_Boolean("key : YES # comment")
				.getKey()
				, "should have been «key»");
	}

	// ========== Setters ==========
	@Test
	void setValue_Boolean() {
		Line_Boolean line = new Line_Boolean();
		assertEquals(false, line.newLine("key : YES # comment")
				.setValue(false).getValue()
				, "should have been «newValue»");
		assertEquals(false, line.getValue()
				, "should have been «false»");
	}
	
	@Test
	void newLine_String() {
		assertEquals((Boolean)null, new Line_Boolean()
				.newLine("").getValue()
				, "should have been «null»");
		assertEquals((Boolean)null, new Line_Boolean()
				.newLine((String)null).getValue()
				, "should have been «»");
		assertEquals(true, new Line_Boolean()
				.newLine("key : YES # comment").getValue()
				, "should have been «true»");
		assertEquals("", new Line_Boolean()
				.newLine("key : YES").getComment()
				, "should have been «»");
	}

	@Test
	void setKey_String() {
		Line_Boolean line = new Line_Boolean();
		assertEquals("newKey", line.newLine("key : YES # comment")
				.setKey("newKey").getKey()
				, "should have been «newKey»");
		assertEquals("newKey", line.getKey()
				, "should have been «newKey»");
	}

	@Test
	void setValue_String() {
		Line_Boolean line = new Line_Boolean();
		assertEquals(false, line.newLine("key : YES # comment")
				.setValue(false).getValue()
				, "should have been «newValue»");
		assertEquals(false, line.getValue()
				, "should have been «false»");
	}

	@Test
	void setComment_String() {
		Line_Boolean line = new Line_Boolean();
		assertEquals("newComment", line.newLine("key : YES # comment")
				.setComment("newComment").getComment()
				, "should have been «newComment»");
		assertEquals("newComment", line.getComment()
				, "should have been «newComment»");
	}

	// ========== Getters ==========
	@Test
	void getKey_None() {
		assertEquals("key", new Line_Boolean("key : YES # comment")
				.getKey()
				, "should have been «key»");
	}
	
	@Test
	void getValue_None() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.getValue()
				, "should have been true");
	}

	@Test
	void toBoolean_None() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.toBoolean()
				, "should have been true");
	}

	@Test
	void getValue_Boolean() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.getValue(false)
				, "should have been true");
		assertEquals(true, new Line_Boolean().getValue(true)
				, "should have been true");
	}

	@Test
	void getOrDefault_Boolean() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.getOrDefault(false)
				, "should have been true");
		assertEquals(true, new Line_Boolean().getOrDefault(true)
				, "should have been true");
	}

	@Test
	void getComment_None() {
		assertEquals("comment", new Line_Boolean("key : YES # comment")
				.getComment()
				, "should have been «comment»");
	}
	
	@Test
	void getKeyAsEntry_None() {
		assertEquals("key", new Line_Boolean("key : YES # comment")
				.getKeyAsEntry().getValue()
				, "should have been «key»");
	}
	
	@Test
	void getValueAsEntry_None() {
		assertEquals(true, new Line_Boolean("key : YES # comment")
				.getValueAsEntry().getValue()
				, "should have been true");
	}
}
