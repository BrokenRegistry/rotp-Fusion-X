package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Line_IntegerTest {

	// ========== Constructors ==========
	@Test
	void Line_String_None() {
		assertEquals((Integer)null, new Line_Integer().getValue()
				, "should have been «null»");
	}
	
	@Test
	void Line_String_String() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.getValue()
				, "should have been «88»");
		assertEquals("comment", new Line_Integer("key : 88 # comment")
				.getComment()
				, "should have been «comment»");
		assertEquals("key", new Line_Integer("key : 88 # comment")
				.getKey()
				, "should have been «key»");
	}

	// ========== Setters ==========
	@Test
	void setValue_Integer() {
		Line_Integer line = new Line_Integer();
		assertEquals(888, line.newLine("key : 88 # comment")
				.setValue(888).getValue()
				, "should have been «newValue»");
		assertEquals(888, line.getValue()
				, "should have been «888»");
	}
	
	@Test
	void newLine_String() {
		assertEquals((Integer)null, new Line_Integer()
				.newLine("").getValue()
				, "should have been «null»");
		assertEquals((Integer)null, new Line_Integer()
				.newLine((String)null).getValue()
				, "should have been «»");
		assertEquals(88, new Line_Integer()
				.newLine("key : 88 # comment").getValue()
				, "should have been «88»");
		assertEquals("", new Line_Integer()
				.newLine("key : 88").getComment()
				, "should have been «»");
	}

	@Test
	void setKey_String() {
		Line_Integer line = new Line_Integer();
		assertEquals("newKey", line.newLine("key : 88 # comment")
				.setKey("newKey").getKey()
				, "should have been «newKey»");
		assertEquals("newKey", line.getKey()
				, "should have been «newKey»");
	}

	@Test
	void setValue_String() {
		Line_Integer line = new Line_Integer();
		assertEquals(888, line.newLine("key : 88 # comment")
				.setValue(888).getValue()
				, "should have been «newValue»");
		assertEquals(888, line.getValue()
				, "should have been «888»");
	}

	@Test
	void setComment_String() {
		Line_Integer line = new Line_Integer();
		assertEquals("newComment", line.newLine("key : 88 # comment")
				.setComment("newComment").getComment()
				, "should have been «newComment»");
		assertEquals("newComment", line.getComment()
				, "should have been «newComment»");
	}

	// ========== Getters ==========
	@Test
	void getKey_None() {
		assertEquals("key", new Line_Integer("key : 88 # comment")
				.getKey()
				, "should have been «key»");
	}
	
	@Test
	void getValue_None() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.getValue()
				, "should have been 88");
	}

	@Test
	void toInteger_None() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.toInteger()
				, "should have been 88");
	}

	@Test
	void getValue_Integer() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.getValue(888)
				, "should have been true");
		assertEquals(88, new Line_Integer().getValue(88)
				, "should have been 88");
	}

	@Test
	void getOrDefault_Integer() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.getOrDefault(888)
				, "should have been true");
		assertEquals(88, new Line_Integer().getOrDefault(88)
				, "should have been true");
	}

	@Test
	void getComment_None() {
		assertEquals("comment", new Line_Integer("key : 88 # comment")
				.getComment()
				, "should have been «comment»");
	}
	
	@Test
	void getKeyAsEntry_None() {
		assertEquals("key", new Line_Integer("key : 88 # comment")
				.getKeyAsEntry().getValue()
				, "should have been «key»");
	}
	
	@Test
	void getValueAsEntry_None() {
		assertEquals(88, new Line_Integer("key : 88 # comment")
				.getValueAsEntry().getValue()
				, "should have been 88");
	}
	
	@Test
	void getCommentAsEntry_None() {
		assertEquals("comment", new Line_Integer("key : 88 # comment")
				.getCommentAsEntry().getValue()
				, "should have been «comment»");
	}
}
