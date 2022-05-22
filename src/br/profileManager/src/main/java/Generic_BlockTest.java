package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Generic_BlockTest {

	private Generic_Block<String, Valid_ProfileAction> block;
	private Generic_Line<String, Abstract_ValidData<String>> line;

	
	Generic_Block<String, Valid_ProfileAction> newBlock() {
		return new Generic_Block<String, Valid_ProfileAction>(
				new Valid_ProfileAction());
	}
	private String NL = System.lineSeparator();
	private String line1 = "key1 : gui";
	private String out1  = "key1                : GUI";
	private String line2 = "key2 : xxx";
	private String out2  = "key2                : SAVE";
	private String line3 = "key3 : Game  #  bla bla bla";
	private String out3  = "key3                : GAME    # bla bla bla";
	
	// ========== Constructors ==========
	@Test
	void Generic_Block_ValidationData() {
		block = newBlock();
		assertEquals("", block.toString()
				, "should have been «\"\"»");
		assertEquals("", newBlock().toString()
				, "should have been «\"\"»");
	}

	// ========== Setters ==========
	@Test
	void add_String() {
		block = newBlock();
		block.add(line1);
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : GUI\"»");
		block = newBlock();
		block.add(line2);
		assertEquals(out2, block.toString()
				, "should have been «\"key2                : SAVE\"»");
		block = newBlock();
		block.add(line3);
		assertEquals(out3, block.toString()
				, "should have been «\"key3                : GAME    # bla bla bla\"»");
		block.add(line2);
		block.add(line1);
		assertEquals(out3 + NL + out2 + NL + out1, block.toString()
				, "should have been three lines");
	}

	@Test
	void add_StringString() {
		block = newBlock();
		block.add("key1", "gui");
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : GUI\"»");
		block = newBlock();
		block.add("key2 ", "xxx");
		assertEquals(out2, block.toString()
				, "should have been «\"key2                : SAVE\"»");
		block.add(line1);
		assertEquals(out2 + NL + out1, block.toString()
				, "should have been two lines");
	}

	@Test
	void add_StringStringString() {
		block = newBlock();
		block.add("key1", "gui", null);
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : GUI\"»");
		block = newBlock();
		block.add("key2 ", "xxx", "");
		assertEquals(out2, block.toString()
				, "should have been «\"key2                : SAVE\"»");
		block = newBlock();
		block.add("key3 ", " Game", "bla bla bla");
		assertEquals(out3, block.toString()
				, "should have been «\"key3                : GAME    # bla bla bla\"»");
		block.add(line2);
		block.add(line1);
		assertEquals(out3 + NL + out2 + NL + out1, block.toString()
				, "should have been three lines");
	}

	@Test
	void add_Line() {
		block = newBlock();
		block.add(line1);
		line = block.getLine("key1");
		block = newBlock();
		block.add(line2);
		block.add(line);
		assertEquals(out2 + NL + out1, block.toString()
				, "should have been two lines");
	}

	@Test
	void addMissing_String() {
		block = newBlock();
		block.add(line1);
		block.addMissing("key2");
		out2 ="key2                : SAVE";
		assertEquals(out1 + NL + out2, block.toString()
				, "should have been two lines");	
	}

	@Test
	void addMissing_ListOfString() {
		block = newBlock();
		block.add(line1);
		List<String> keyList = List.of("key2", "key3");
		block.addMissing(keyList);
		out2 ="key2                : SAVE";
		out3 ="key3                : SAVE";
		assertEquals(out1 + NL + out2 + NL + out3, block.toString()
				, "should have been three lines");	
	}

	// ========== Testers ==========
	@Test
	void isValid_String() {
		block = newBlock();
		block.add(line1);
		assertEquals(true, block.isValid("key1")
				, "should have been «true»");	
		assertEquals(false, block.isValid("key2")
				, "should have been «false»");	
		line = block.getLine("key1");
		line.setValue("xxx");
		block = newBlock();
		block.add(line);
		assertEquals(false, block.isValid("key1")
				, "should have been «false»");	
		out1 ="key1                : xxx";
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : xxx\"»");	
		line.setValue("");
		block = newBlock();
		block.add(line);
		assertEquals(true, block.isValid("key1")
				, "should have been «true»");	
		out1 ="key1                : ";
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : \"»");	
	}

	@Test
	void isBlankValue_String() {
		block = newBlock();
		block.add(line1);
		assertEquals(false, block.isBlankValue("key1")
				, "should have been «false»");	
		assertEquals(null, block.isBlankValue("key2")
				, "should have been «null»");
		line = block.getLine("key1");
		line.setValue("");
		block = newBlock();
		block.add(line);
		assertEquals(true, block.isBlankValue("key1")
				, "should have been «true»");	
		out1 ="key1                : ";
		assertEquals(out1, block.toString()
				, "should have been «\"key1                : \"»");			
	}

	// ========== Getters ==========
	@Test
	void getValue_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("GUI", block.getValue("key1")
				, "should have been «\"GUI\"»");	
		assertEquals("SAVE", block.getValue("key2")
				, "should have been «\"SAVE\"»");	
		assertEquals("GAME", block.getValue("key3")
				, "should have been «\"GAME\"»");	
		assertEquals(null, block.getValue("key4")
				, "should have been «null»");	
	}

	@Test
	void getValue_StringValueClass() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("GUI", block.getValue("key1", "zzz")
				, "should have been «\"GUI\"»");	
		assertEquals("SAVE", block.getValue("key2", "zzz")
				, "should have been «\"SAVE\"»");	
		assertEquals("GAME", block.getValue("key3", "zzz")
				, "should have been «\"GAME\"»");	
		assertEquals("zzz", block.getValue("key4", "zzz")
				, "should have been «\"zzz\"»");	
	}

	@Test
	void getLine_String() {
		block = newBlock();
		block.add(line1);
		line = block.getLine("key1");
		assertEquals("GUI", line.getValue()
				, "should have been «\"GUI\"»");	
	}

	@Test
	void toString_None() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals(out1 + NL + out2 + NL + out3, block.toString()
				, "should have been three lines");	
	}

	@Test
	void toString_ListOfString() {
		List<String> keyList = List.of("key1", "key3");
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals(out1 + NL + out3, block.toString(keyList)
				, "should have been two lines");	
		keyList = List.of("key3", "key1");
		assertEquals(out3 + NL + out1, block.toString(keyList)
				, "should have been two lines");
	}

	@Test
	void getProfileList_None() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key1, key2, key3]", block.getProfileList().toString()
				, "should have been «\"[key1, key2, key3]\"»");	
	}

	@Test
	void getProfileListForValueEqualsFilter_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key1]",
				block.getProfileListForValueEqualsFilter("GUI").toString()
				, "should have been «\"[key1]\"»");	
		assertEquals("[]",
				block.getProfileListForValueEqualsFilter("PULL").toString()
				, "should have been «\"[]\"»");	
	}

	@Test
	void getProfileListForValueContainsFilter_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key1, key3]",
				block.getProfileListForValueContainsFilter("G").toString()
				, "should have been «\"[key1, key3]\"»");	
		assertEquals("[key2, key3]",
				block.getProfileListForValueContainsFilter("A").toString()
				, "should have been «\"[key2, key3]\"»");	
	}

	@Test
	void getProfileListForValueInFilter_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key1, key2]",
				block.getProfileListForValueInFilter("GUI SAVE").toString()
				, "should have been «\"[key1, key2]\"»");	
		assertEquals("[key2, key3]",
				block.getProfileListForValueInFilter("SAVE GAME").toString()
				, "should have been «\"[key2, key3]\"»");	
	}

	@Test
	void getProfileListForCategory_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key1, key3]",
				block.getProfileListForCategory(Valid_ProfileAction.LOAD_ENABLED).toString()
				, "should have been «\"[key1, key3]\"»");	
		assertEquals("[key2]",
				block.getProfileListForCategory(Valid_ProfileAction.WRITE_ENABLED).toString()
				, "should have been «\"[key2]\"»");	
		assertEquals("[]",
				block.getProfileListForCategory(Valid_ProfileAction.SPECIAL_ENABLED).toString()
				, "should have been «\"[]\"»");	
	}

}
