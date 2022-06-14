
package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleTest {

	@Test void ValidationElement_String() {
		assertEquals("XXX", new Options<String>("XXX")
				.getCodeView()
				, "should have been «XXX»");
	}

}
