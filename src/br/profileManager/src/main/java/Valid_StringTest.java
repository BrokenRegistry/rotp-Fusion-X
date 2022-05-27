package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.profileManager.src.main.java.Valid_String.Line_String;

class Valid_StringTest {

	@Test
	void Line_String_None() {
		assertEquals("", new Line_String().getValue()
				, "should have been «»");
	}
	
	@Test
	void Line_String_String() {
		assertEquals("value", new Line_String("key : value # comment").getValue()
				, "should have been «value»");
		assertEquals("comment", new Line_String("key : value # comment").getComment()
				, "should have been «comment»");
		assertEquals("key", new Line_String("key : value # comment").getName()
				, "should have been «key»");
	}
	
	@Test
	void newLine_String() {
		assertEquals("", new Line_String().newLine("").getValue()
				, "should have been «»");
		assertEquals("", new Line_String().newLine((String)null).getValue()
				, "should have been «»");
		assertEquals("value", new Line_String()
				.newLine("key : value # comment").getValue()
				, "should have been «value»");
		assertEquals("", new Line_String()
				.newLine("key : value").getComment()
				, "should have been «»");
	}
	
	@Test
	void setName_String() {
		Line_String line = new Line_String();
		assertEquals("newKey", line.newLine("key : value # comment")
				.setName("newKey").getName()
				, "should have been «newKey»");
		assertEquals("newKey", line.getName()
				, "should have been «newKey»");
	}

	@Test
	void setValue_String() {
		Line_String line = new Line_String();
		assertEquals("newValue", line.newLine("key : value # comment")
				.setValue("newValue").getValue()
				, "should have been «newValue»");
		assertEquals("newValue", line.getValue()
				, "should have been «newValue»");
		
	}
	
	@Test
	void setComment_String() {
		Line_String line = new Line_String();
		assertEquals("newComment", line.newLine("key : value # comment")
				.setComment("newComment").getComment()
				, "should have been «newComment»");
		assertEquals("newComment", line.getComment()
				, "should have been «newComment»");
	}
	
	@Test
	void getName_None() {
		assertEquals("key", new Line_String()
				.newLine("key : value # comment").getName()
				, "should have been «key»");
	}
	
	@Test
	void getValue_None() {
		assertEquals("value", new Line_String()
				.newLine("key : value # comment").getValue()
				, "should have been «value»");
	}

	@Test
	void getComment_None() {
		assertEquals("comment", new Line_String()
				.newLine("key : value # comment").getComment()
				, "should have been «comment»");
	}

	@Test
	void getValueAsEntry_None() {
		assertEquals("value", new Line_String()
				.newLine("key : value # comment")
				.getValueAsEntry().getValue()
				, "should have been «value»");
	}
}