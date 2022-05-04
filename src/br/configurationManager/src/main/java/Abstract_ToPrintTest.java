package br.configurationManager.src.main.java;

import static br.configurationManager.src.main.java.Abstract_ToPrint.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.configurationManager.src.main.java.Abstract_ToPrint.PrintFormat;

class Abstract_ToPrintTest {

	@Test
	void toPrint_Object() {
		assertEquals("abc", toPrint("abc")
				, "should have been «abc»");
		assertEquals("", toPrint((String)null)
				, "should have been «»");
	}

	@Test
	void toPrint_Object_PrintFormat() {
		String str = "testing THE class";
		assertEquals("", toPrint((String)null, (PrintFormat)null)
				, "should have been «»");
		assertEquals("testing THE class", toPrint(str, (PrintFormat)null)
				, "should have been «testing THE class»");
		assertEquals("testing THE class", toPrint(str, PrintFormat.HOLD)
				, "should have been «testing THE class»");
		assertEquals("TESTING THE CLASS", toPrint(str, PrintFormat.UPPER_CASE)
				, "should have been «TESTING THE CLASS»");
		assertEquals("testing the class", toPrint(str, PrintFormat.LOWER_CASE)
				, "should have been «testing the class»");
		assertEquals("Testing The Class", toPrint(str, PrintFormat.CAPITALIZE)
				, "should have been «Testing The Class»");
		assertEquals("Testing the class", toPrint(str, PrintFormat.SENTENCE)
				, "should have been «Testing the class»");
	}

}
