package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static br.profileManager.src.main.java.Lines.*;

class LineStringTest {
	private static PMconfig PM = new PMconfig();
	private static final String keyValueSeparator = PM.getConfig("keyValueSeparator");
	private static final String commentKey        = PM.getConfig("commentKey");

	@Test void Line_String_None() {
		LineString line = new LineString();
		assertEquals(true, line.isBlankValue()
				, "This String should have been true");
	}

	@Test void Line_String_String() {
		LineString line = new LineString("Key " + keyValueSeparator
						+ " Value " + commentKey + "comment");
		assertEquals("Key", line.key()
				, "This String should have been \"Key\"");
		assertEquals("Value", line.value()
				, "This String should have been \"Value\"");
		assertEquals("comment", line.comment()
				, "This String should have been \"comment\"");
	}

	@Test void Line_String_String2() {
		LineString line = new LineString("Key", "Value");
		assertEquals("Key", line.key()
				, "This String should have been \"Key\"");
		assertEquals("Value", line.value()
				, "This String should have been \"Value\"");
	}

	@Test void Line_String_String3() {
		LineString line = new LineString("Key", "Value", "comment");
		assertEquals("Key", line.key()
				, "This String should have been \"Key\"");
		assertEquals("Value", line.value()
				, "This String should have been \"Value\"");
		assertEquals("comment", line.comment()
				, "This String should have been \"comment\"");
	}

	@Test void setName_String() {
		LineString line = new LineString();
		line.key("Key");
		assertEquals("Key", line.key()
				, "This String should have been \"Key\"");
	}

	@Test void setValue_String() {
		LineString line = new LineString();
		line.value("Value");
		assertEquals("Value", line.value()
				, "This String should have been \"Value\"");
	}

	@Test void setComment_String() {
		LineString line = new LineString();
		line.comment("comment");
		assertEquals("comment", line.comment()
				, "This String should have been \"comment\"");
	}

	@Test void getName_None() {
		LineString line = new LineString();
		line.key("Key");
		assertEquals("Key", line.key()
				, "This String should have been \"Key\"");
	}

	@Test void getValue_None() {
		LineString line = new LineString();
		line.value("Value");
		assertEquals("Value", line.value()
				, "This String should have been \"Value\"");
	}

	@Test void toString_None() {
		LineString line = new LineString("Key", "Value", "comment");
		assertEquals("Key             : Value       ; comment", line.toString()
				, "This String should have been \"Key             : Value       ; comment\"");
	}

	@Test void toComment_String() {
		String head = "Options";
		String comment = toComment(head);
		LineString line = new LineString(comment, "");
		String HEAD_OF_OPTIONS = line.toString();
		assertEquals("; Options       : ", HEAD_OF_OPTIONS
				, "This String should have been \"; Options       : \"");
	}

}
