package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import static br.profileManager.src.main.java.Valid_ProfileAction.*;

class Generic_BlockTest {

	private Generic_Block<String, Valid_ProfileAction> block;
	private Generic_Line<String, Abstract_ValidData<String>> line;

	
	Generic_Block<String, Valid_ProfileAction> newBlock() {
		return new Generic_Block<String, Valid_ProfileAction>(
				new Valid_ProfileAction());
	}
	private String NL = System.lineSeparator();
	private String line1 = "key1 : " + ACTION_FILE_TO_GUI.toLowerCase();
	private String out1  = "key1            : " + ACTION_FILE_TO_GUI;
	private String line2 = "key2 : xxx";
	private String out2  = "key2            : ";
	private String line3 = "key3 : " + ACTION_FILE_TO_GAME + "  ;  bla bla bla";
	private String out3  = "key3            : Change      ; bla bla bla";
	
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
				, "should have been «\"key1            : \"»");
		block = newBlock();
		block.add(line2);
		assertEquals(out2, block.toString()
				, "should have been «\"key2            : \"»");
		block = newBlock();
		block.add(line3);
		assertEquals(out3, block.toString()
				, "should have been «\"key3            : Change      ;; bla bla bla\"»");
		block.add(line2);
		block.add(line1);
		assertEquals(out3 + NL + out2 + NL + out1, block.toString()
				, "should have been three lines");
	}

	@Test
	void add_StringString() {
		block = newBlock();
		block.add("key1", ACTION_FILE_TO_GUI);
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
		block.add("key1", ACTION_FILE_TO_GUI, null);
		assertEquals(out1, block.toString()
				, "should have been «\"key1            : \"»");
		block = newBlock();
		block.add("key2 ", "xxx", "");
		assertEquals(out2, block.toString()
				, "should have been «\"key2            : \"»");
		block = newBlock();
		block.add("key3 ", " " + ACTION_FILE_TO_GAME, "bla bla bla");
		assertEquals(out3, block.toString()
				, "should have been «\"key3            : GAME    ; bla bla bla\"»");
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
		out2 ="key2            : ";
		assertEquals(out1 + NL + out2, block.toString()
				, "should have been two lines");	
	}

	@Test
	void addMissing_ListOfString() {
		block = newBlock();
		block.add(line1);
		List<String> keyList = List.of("key2", "key3");
		block.addMissing(keyList);
		out2 ="key2            : ";
		out3 ="key3            : ";
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
		out1 ="key1            : xxx";
		assertEquals(out1, block.toString()
				, "should have been «\"key1            : xxx\"»");	
		line.setValue("");
		block = newBlock();
		block.add(line);
		assertEquals(true, block.isValid("key1")
				, "should have been «true»");	
		out1 ="key1            : ";
		assertEquals(out1, block.toString()
				, "should have been «\"key1            : \"»");	
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
		out1 ="key1            : ";
		assertEquals(out1, block.toString()
				, "should have been «\"key1            : \"»");			
	}

	// ========== Getters ==========
	@Test
	void getValue_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("Load", block.getValue("key1")
				, "should have been «\"Load\"»");	
		assertEquals("", block.getValue("key2")
				, "should have been «\"\"»");	
		assertEquals("Change", block.getValue("key3")
				, "should have been «\"Change\"»");	
		assertEquals(null, block.getValue("key4")
				, "should have been «null»");	
	}

	@Test
	void getValue_StringValueClass() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("Load", block.getValue("key1", "zzz")
				, "should have been «\"Load\"»");	
		assertEquals("zzz", block.getValue("key2", "zzz")
				, "should have been «\"zzz\"»");	
		assertEquals("Change", block.getValue("key3", "zzz")
				, "should have been «\"Change\"»");	
		assertEquals("zzz", block.getValue("key4", "zzz")
				, "should have been «\"zzz\"»");	
	}

	@Test
	void getLine_String() {
		block = newBlock();
		block.add(line1);
		line = block.getLine("key1");
		assertEquals("Load", line.getValue()
				, "should have been «\"Load\"»");	
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
				block.getProfileListForValueEqualsFilter(ACTION_FILE_TO_GUI).toString()
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
		assertEquals("[key3]",
				block.getProfileListForValueContainsFilter("G").toString()
				, "should have been «\"[key3]\"»");	
		assertEquals("[key1, key3]",
				block.getProfileListForValueContainsFilter("A").toString()
				, "should have been «\"[key1, key3]\"»");	
	}

	@Test
	void getProfileListForValueInFilter_String() {
		block = newBlock();
		block.add(line1);
		block.add(line2);
		block.add(line3);
		assertEquals("[key2]",
				block.getProfileListForValueInFilter("GUI SAVE").toString()
				, "should have been «\"[key2]\"»");	
		assertEquals("[key2]",
				block.getProfileListForValueInFilter("SAVE GAME").toString()
				, "should have been «\"[key2]\"»");	
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
		assertEquals("[]",
				block.getProfileListForCategory(Valid_ProfileAction.WRITE_ENABLED).toString()
				, "should have been «\"[]\"»");	
		assertEquals("[]",
				block.getProfileListForCategory(Valid_ProfileAction.SPECIAL_ENABLED).toString()
				, "should have been «\"[]\"»");	
	}

}
