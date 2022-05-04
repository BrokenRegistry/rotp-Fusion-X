package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Entry_IntegerTest {

	Entry_Integer entry  = new Entry_Integer();
	Entry_Integer origin = new Entry_Integer();
	
	@Test
	void testToString() {
		assertEquals("88", new Entry_Integer(88).toString()
				, "should have been «88»");	
		assertEquals("88", new Entry_Integer("88").toString()
				, "should have been «88»");
		assertEquals("89", new Entry_Integer("88.8").toString()
				, "should have been «89»");
		assertEquals("", new Entry_Integer().toString()
				, "should have been «»");
		assertEquals("", new Entry_Integer("").toString()
				, "should have been «null»");
		assertEquals("", new Entry_Integer("xxx").toString()
				, "should have been «»");
	}
	@Test
	void testIsBlank() {
		assertEquals(false, new Entry_Integer(88).isBlank()
				, "should have been «false»");	
		assertEquals(false, new Entry_Integer("88").isBlank()
				, "should have been «false»");
		assertEquals(false, new Entry_Integer("88.8").isBlank()
				, "should have been «false»");
		assertEquals(true, new Entry_Integer().isBlank()
				, "should have been «true»");
		assertEquals(true, new Entry_Integer("").isBlank()
				, "should have been «true»");
		assertEquals(true, new Entry_Integer("xxx").isBlank()
				, "should have been «true»");
	}
	@Test
	void testEntry_Integer() {
		assertEquals((Integer)null, new Entry_Integer().getValue()
				, "should have been «null»");	
	}
	@Test
	void testEntry_IntegerString() {
		assertEquals(88, new Entry_Integer("88").getValue()
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").getValue()
				, "should have been «89»");
		assertEquals((Integer)null, new Entry_Integer("").getValue()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testEntry_IntegerInteger() {
		assertEquals(88, new Entry_Integer(88).getValue()
				, "should have been «88»");	
		assertEquals((Integer)null, new Entry_Integer((Integer)null).getValue()
				, "should have been «null»");	
	}
	@Test
	void testSetInteger() {
		assertEquals((Integer)null, new Entry_Integer(88)
				.set((Integer)null).getValue()
				, "should have been «null»");	
		assertEquals(88, new Entry_Integer().set(88).getValue()
				, "should have been «88»");	
	}
	@Test
	void testSetEntry_Integer() {
		assertEquals((Integer)null, new Entry_Integer(88)
				.set(new Entry_Integer((Integer)null)).getValue()
				, "should have been «null»");	
		assertEquals(88, new Entry_Integer().set(new Entry_Integer(88)).getValue()
				, "should have been «88»");	
	}
	@Test
	void testSetString() {
		assertEquals(88, new Entry_Integer().set("88").getValue()
				, "should have been «88»");
		assertEquals(89, new Entry_Integer().set("88.8").getValue()
				, "should have been «89»");
		assertEquals((Integer)null, new Entry_Integer().set("").getValue()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer().set("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testGetValue() {
		assertEquals(88, new Entry_Integer(88).getValue()
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").getValue()
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").getValue()
				, "should have been «89»");
		assertEquals((Integer)null, new Entry_Integer("").getValue()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer("xxx").getValue()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer().getValue()
				, "should have been «null»");
	}
	@Test
	void testReset() {
		assertEquals((Integer)null, new Entry_Integer(88).reset().getValue()
				, "should have been «null»");
		assertEquals("", new Entry_Integer("abc").reset().getUserEntry()
				, "should have been «»");
		assertEquals("abc", new Entry_Integer("abc").getUserEntry()
				, "should have been «abc»");
	}
	@Test
	void testClone() {
		assertEquals(888, entry.set(88).clone().set(888).getValue()
				, "should have been «888»");
		assertEquals(88, entry.getValue()
				, "should have been «88»");
	}
	@Test
	void testCopy() {
		assertEquals(888, entry.set(88).copy().set(888).getValue()
				, "should have been «888»");
		assertEquals(888, entry.getValue()
				, "should have been «888»");
	}
	@Test
	void testIsInteger() {
		assertEquals(true, new Entry_Integer(88).isInteger()
				, "should have been «true»");	
		assertEquals(true, new Entry_Integer("88").isInteger()
				, "should have been «true»");
		assertEquals(true, new Entry_Integer("88.8").isInteger()
				, "should have been «true»");
		assertEquals(false, new Entry_Integer().isInteger()
				, "should have been «false»");
		assertEquals(false, new Entry_Integer("").isInteger()
				, "should have been «false»");
		assertEquals(false, new Entry_Integer("xxx").isInteger()
				, "should have been «false»");
	}
	@Test
	void testToInteger() {
		assertEquals(88, new Entry_Integer(88).toInteger()
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").toInteger()
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").toInteger()
				, "should have been «89»");
		assertEquals((Integer)null, new Entry_Integer("").toInteger()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer("xxx").toInteger()
				, "should have been «null»");
		assertEquals((Integer)null, new Entry_Integer().toInteger()
				, "should have been «null»");
	}
	@Test
	void testToIntegerInteger() {
		assertEquals(88, new Entry_Integer(88).toInteger(888)
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").toInteger(888)
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").toInteger(888)
				, "should have been «89»");
		assertEquals(888, new Entry_Integer("").toInteger(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer("xxx").toInteger(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer().toInteger(888)
				, "should have been «888»");
	}
	@Test
	void testValidateMinInteger() {
		assertEquals(88, entry.set(88).validateMin(-88).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals(88, entry.set(-88).validateMin(88).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals(88, entry.set(88).validateMin((Integer)null).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals(88, entry.set("").validateMin(88).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals((Integer)null, entry.set("").validateMin((Integer)null).getValue()
				, "should have been: «null»");
		assertEquals((Integer)null, entry.getValue()
				, "should have been: «null»");
	}
	@Test
	void testValidateMaxInteger() {
		assertEquals(-88, entry.set(88).validateMax(-88).getValue()
				, "should have been: «-88»");
		assertEquals(-88, entry.getValue()
				, "should have been: «-88»");
		assertEquals(-88, entry.set(-88).validateMax(88).getValue()
				, "should have been: «-88»");
		assertEquals(-88, entry.getValue()
				, "should have been: «-88»");
		assertEquals(88, entry.set(88).validateMax((Integer)null).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals(88, entry.set("").validateMax(88).getValue()
				, "should have been: «88»");
		assertEquals(88, entry.getValue()
				, "should have been: «88»");
		assertEquals((Integer)null, entry.set("").validateMax((Integer)null).getValue()
				, "should have been: «null»");
		assertEquals((Integer)null, entry.getValue()
				, "should have been: «null»");
	}
	@Test
	void testValidateMinMax() {
		assertEquals(8, entry.set(8)
				.validateMinMax(-88, 88).getValue()
				, "should have been «8»");
		assertEquals(88, entry.set(888)
				.validateMinMax(-88, 88).getValue()
				, "should have been «88»");
		assertEquals(-88, entry.set(-888)
				.validateMinMax(-88, 88).getValue()
				, "should have been «-88»");
		assertEquals(-888, entry.set(-888)
				.validateMinMax((Integer)null, 88).getValue()
				, "should have been «-888»");
		assertEquals(88, entry.set(888)
				.validateMinMax((Integer)null, 88).getValue()
				, "should have been «88»");
		assertEquals(888, entry.set(888)
				.validateMinMax((Integer)null, (Integer)null).getValue()
				, "should have been «888»");
		assertEquals(-88, entry.set(-888)
				.validateMinMax(-88, (Integer)null).getValue()
				, "should have been «-88»");
		assertEquals(888, entry.set(888)
				.validateMinMax(-88, (Integer)null).getValue()
				, "should have been «888»");
		assertEquals(0, entry.set((Integer)null)
				.validateMinMax(-88, 88).getValue()
				, "should have been «0»");
		assertEquals(88, entry.set((Integer)null)
				.validateMinMax((Integer)null, 88).getValue()
				, "should have been «88»");
		assertEquals(-88, entry.set((Integer)null)
				.validateMinMax(-88, (Integer)null).getValue()
				, "should have been «-88»");
	}
	@Test
	void testGetValueInteger() {
		assertEquals(88, new Entry_Integer(88).getValue(888)
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").getValue(888)
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").getValue(888)
				, "should have been «89»");
		assertEquals(888, new Entry_Integer("").getValue(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer("xxx").getValue(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer().getValue(888)
				, "should have been «888»");
	}
	@Test
	void testGetOrDefaultValueClass() {
		assertEquals(88, new Entry_Integer(88).getOrDefault(888)
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").getOrDefault(888)
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").getOrDefault(888)
				, "should have been «89»");
		assertEquals(888, new Entry_Integer("").getOrDefault(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer("xxx").getOrDefault(888)
				, "should have been «888»");
		assertEquals(888, new Entry_Integer().getOrDefault(888)
				, "should have been «888»");
	}
	@Test
	void testGetOrDefaultEntry_BaseOfValueClass() {
		assertEquals(88, new Entry_Integer(88).getOrDefault(new Entry_Integer(888))
				, "should have been «88»");	
		assertEquals(88, new Entry_Integer("88").getOrDefault(new Entry_Integer(888))
				, "should have been «88»");
		assertEquals(89, new Entry_Integer("88.8").getOrDefault(new Entry_Integer(888))
				, "should have been «89»");
		assertEquals(888, new Entry_Integer("").getOrDefault(new Entry_Integer(888))
				, "should have been «888»");
		assertEquals(888, new Entry_Integer("xxx").getOrDefault(new Entry_Integer(888))
				, "should have been «888»");
		assertEquals(888, new Entry_Integer().getOrDefault(new Entry_Integer(888))
				, "should have been «888»");
	}
	@Test
	void testToComment() {
		assertEquals("# 88", new Entry_Integer(88).toComment()
				, "should have been «# 88»");	
		assertEquals("# 88", new Entry_Integer("88").toComment()
				, "should have been «# 88»");
		assertEquals("# 89", new Entry_Integer("88.8").toComment()
				, "should have been «# 89»");
		assertEquals("# ", new Entry_Integer().toComment()
				, "should have been «# »");
		assertEquals("# ", new Entry_Integer("").toComment()
				, "should have been «# null»");
		assertEquals("# ", new Entry_Integer("xxx").toComment()
				, "should have been «# null»");
	}
	@Test
	void testToCommentBoolean() {
		assertEquals("# 88", new Entry_Integer(88).toComment(false)
				, "should have been «88»");	
		assertEquals("# 88", new Entry_Integer("88").toComment(true)
				, "should have been «88»");
		assertEquals("# 89", new Entry_Integer("88.8").toComment(false)
				, "should have been «89»");
		assertEquals("", new Entry_Integer().toComment(false)
				, "should have been «»");
		assertEquals("", new Entry_Integer("").toComment(false)
				, "should have been «null»");
		assertEquals(null, new Entry_Integer("xxx").toComment((Boolean)null)
				, "should have been «null»");
		assertEquals("# ", new Entry_Integer("xxx").toComment(true)
				, "should have been «null»");
	}
	@Test
	void testToPrint() {
		assertEquals("88", new Entry_Integer(88).toPrint()
				, "should have been «88»");	
		assertEquals("88", new Entry_Integer("88").toPrint()
				, "should have been «88»");
		assertEquals("89", new Entry_Integer("88.8").toPrint()
				, "should have been «89»");
		assertEquals("", new Entry_Integer().toPrint()
				, "should have been «»");
		assertEquals("", new Entry_Integer("").toPrint()
				, "should have been «null»");
		assertEquals("", new Entry_Integer("xxx").toPrint()
				, "should have been «»");
	}
}
