package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Line_DoubleTest {

	// ========== Constructors ==========
	@Test
	void Line_String_None() {
		assertEquals((Double)null, new Line_Double().getValue()
				, "should have been «null»");
	}
	
	@Test
	void Line_String_String() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.getValue()
				, "should have been «88.8»");
		assertEquals("comment", new Line_Double("key : 88.8 # comment")
				.getComment()
				, "should have been «comment»");
		assertEquals("key", new Line_Double("key : 88.8 # comment")
				.getKey()
				, "should have been «key»");
	}

	// ========== Setters ==========
	@Test
	void setValue_Double() {
		Line_Double line = new Line_Double();
		assertEquals(888.888, line.newLine("key : 88.8 # comment")
				.setValue(888.888).getValue()
				, "should have been «newValue»");
		assertEquals(888.888, line.getValue()
				, "should have been «888.888»");
	}
	
	@Test
	void newLine_String() {
		assertEquals((Double)null, new Line_Double()
				.newLine("").getValue()
				, "should have been «null»");
		assertEquals((Double)null, new Line_Double()
				.newLine((String)null).getValue()
				, "should have been «»");
		assertEquals(88.8, new Line_Double()
				.newLine("key : 88.8 # comment").getValue()
				, "should have been «88.8»");
		assertEquals("", new Line_Double()
				.newLine("key : 88.8").getComment()
				, "should have been «»");
	}

	@Test
	void setKey_String() {
		Line_Double line = new Line_Double();
		assertEquals("newKey", line.newLine("key : 88.8 # comment")
				.setKey("newKey").getKey()
				, "should have been «newKey»");
		assertEquals("newKey", line.getKey()
				, "should have been «newKey»");
	}

	@Test
	void setValue_String() {
		Line_Double line = new Line_Double();
		assertEquals(888.888, line.newLine("key : 88.8 # comment")
				.setValue(888.888).getValue()
				, "should have been «newValue»");
		assertEquals(888.888, line.getValue()
				, "should have been «888.888»");
	}

	@Test
	void setComment_String() {
		Line_Double line = new Line_Double();
		assertEquals("newComment", line.newLine("key : 88.8 # comment")
				.setComment("newComment").getComment()
				, "should have been «newComment»");
		assertEquals("newComment", line.getComment()
				, "should have been «newComment»");
	}

	// ========== Getters ==========
	@Test
	void getKey_None() {
		assertEquals("key", new Line_Double("key : 88.8 # comment")
				.getKey()
				, "should have been «key»");
	}
	
	@Test
	void getValue_None() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.getValue()
				, "should have been 88.8");
	}

	@Test
	void toDouble_None() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.toDouble()
				, "should have been 88.8");
	}

	@Test
	void getValue_Double() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.getValue(888.888)
				, "should have been true");
		assertEquals(88.8, new Line_Double().getValue(88.8)
				, "should have been 88.8");
	}

	@Test
	void getOrDefault_Double() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.getOrDefault(888.888)
				, "should have been true");
		assertEquals(88.8, new Line_Double().getOrDefault(88.8)
				, "should have been true");
	}

	@Test
	void getComment_None() {
		assertEquals("comment", new Line_Double("key : 88.8 # comment")
				.getComment()
				, "should have been «comment»");
	}
	
	@Test
	void getKeyAsEntry_None() {
		assertEquals("key", new Line_Double("key : 88.8 # comment")
				.getKeyAsEntry().getValue()
				, "should have been «key»");
	}
	
	@Test
	void getValueAsEntry_None() {
		assertEquals(88.8, new Line_Double("key : 88.8 # comment")
				.getValueAsEntry().getValue()
				, "should have been 88.8");
	}
}
