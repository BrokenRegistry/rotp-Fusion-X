package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Entry_DoubleTest {

	Entry_Double entry  = new Entry_Double();
	Entry_Double origin = new Entry_Double();
	
	@Test
	void testToString() {
		assertEquals("88.8", new Entry_Double(88.8).toString()
				, "should have been «88.8»");	
		assertEquals("88.8", new Entry_Double("88.8").toString()
				, "should have been «88.8»");
		assertEquals("88.8", new Entry_Double("88.8").toString()
				, "should have been «88.8»");
		assertEquals("", new Entry_Double().toString()
				, "should have been «»");
		assertEquals("", new Entry_Double("").toString()
				, "should have been «null»");
		assertEquals("", new Entry_Double("xxx").toString()
				, "should have been «»");
	}
	@Test
	void testIsBlank() {
		assertEquals(false, new Entry_Double(88.8).isBlank()
				, "should have been «false»");	
		assertEquals(false, new Entry_Double("88.8").isBlank()
				, "should have been «false»");
		assertEquals(false, new Entry_Double("88.8").isBlank()
				, "should have been «false»");
		assertEquals(true, new Entry_Double().isBlank()
				, "should have been «true»");
		assertEquals(true, new Entry_Double("").isBlank()
				, "should have been «true»");
		assertEquals(true, new Entry_Double("xxx").isBlank()
				, "should have been «true»");
	}
	@Test
	void testEntry_Double() {
		assertEquals((Double)null, new Entry_Double().getValue()
				, "should have been «null»");	
	}
	@Test
	void testEntry_DoubleString() {
		assertEquals(88.8, new Entry_Double("88.8").getValue()
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").getValue()
				, "should have been «88.8»");
		assertEquals((Double)null, new Entry_Double("").getValue()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testEntry_DoubleDouble() {
		assertEquals(88.8, new Entry_Double(88.8).getValue()
				, "should have been «88.8»");	
		assertEquals((Double)null, new Entry_Double((Double)null).getValue()
				, "should have been «null»");	
	}
	@Test
	void testSetDouble() {
		assertEquals((Double)null, new Entry_Double(88.8)
				.set((Double)null).getValue()
				, "should have been «null»");	
		assertEquals(88.8, new Entry_Double().set(88.8).getValue()
				, "should have been «88.8»");	
	}
	@Test
	void testSetEntry_Double() {
		assertEquals((Double)null, new Entry_Double(88.8)
				.set(new Entry_Double((Double)null)).getValue()
				, "should have been «null»");	
		assertEquals(88.8, new Entry_Double().set(new Entry_Double(88.8)).getValue()
				, "should have been «88.8»");	
	}
	@Test
	void testSetString() {
		assertEquals(88.8, new Entry_Double().set("88.8").getValue()
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double().set("88.8").getValue()
				, "should have been «88.8»");
		assertEquals((Double)null, new Entry_Double().set("").getValue()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double().set("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testGetValue() {
		assertEquals(88.8, new Entry_Double(88.8).getValue()
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").getValue()
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").getValue()
				, "should have been «88.8»");
		assertEquals((Double)null, new Entry_Double("").getValue()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double("xxx").getValue()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double().getValue()
				, "should have been «null»");
	}
	@Test
	void testReset() {
		assertEquals((Double)null, new Entry_Double(88.8).reset().getValue()
				, "should have been «null»");
		assertEquals("", new Entry_Double("abc").reset().getUserEntry()
				, "should have been «»");
		assertEquals("abc", new Entry_Double("abc").getUserEntry()
				, "should have been «abc»");
	}
	@Test
	void testClone() {
		assertEquals(888.8, entry.set(88.8).clone().set(888.8).getValue()
				, "should have been «888.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been «88.8»");
	}
	@Test
	void testCopy() {
		assertEquals(888.8, entry.set(88.8).copy().set(888.8).getValue()
				, "should have been «888.8»");
		assertEquals(888.8, entry.getValue()
				, "should have been «888.8»");
	}
	@Test
	void testIsDouble() {
		assertEquals(true, new Entry_Double(88.8).isDouble()
				, "should have been «true»");	
		assertEquals(true, new Entry_Double("88.8").isDouble()
				, "should have been «true»");
		assertEquals(true, new Entry_Double("88.8").isDouble()
				, "should have been «true»");
		assertEquals(false, new Entry_Double().isDouble()
				, "should have been «false»");
		assertEquals(false, new Entry_Double("").isDouble()
				, "should have been «false»");
		assertEquals(false, new Entry_Double("xxx").isDouble()
				, "should have been «false»");
	}
	@Test
	void testToDouble() {
		assertEquals(88.8, new Entry_Double(88.8).toDouble()
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").toDouble()
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").toDouble()
				, "should have been «88.8»");
		assertEquals((Double)null, new Entry_Double("").toDouble()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double("xxx").toDouble()
				, "should have been «null»");
		assertEquals((Double)null, new Entry_Double().toDouble()
				, "should have been «null»");
	}
	@Test
	void testToDoubleDouble() {
		assertEquals(88.8, new Entry_Double(88.8).toDouble(888.8)
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").toDouble(888.8)
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").toDouble(888.8)
				, "should have been «88.8»");
		assertEquals(888.8, new Entry_Double("").toDouble(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double("xxx").toDouble(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double().toDouble(888.8)
				, "should have been «888.8»");
	}
	@Test
	void testValidateMinDouble() {
		assertEquals(88.8, entry.set(88.8).validateMin(-88.8).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.set(-88.8).validateMin(88.8).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.set(88.8).validateMin((Double)null).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.set("").validateMin(88.8).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals((Double)null, entry.set("").validateMin((Double)null).getValue()
				, "should have been: «null»");
		assertEquals((Double)null, entry.getValue()
				, "should have been: «null»");
	}
	@Test
	void testValidateMaxDouble() {
		assertEquals(-88.8, entry.set(88.8).validateMax(-88.8).getValue()
				, "should have been: «-88.8»");
		assertEquals(-88.8, entry.getValue()
				, "should have been: «-88.8»");
		assertEquals(-88.8, entry.set(-88.8).validateMax(88.8).getValue()
				, "should have been: «-88.8»");
		assertEquals(-88.8, entry.getValue()
				, "should have been: «-88.8»");
		assertEquals(88.8, entry.set(88.8).validateMax((Double)null).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.set("").validateMax(88.8).getValue()
				, "should have been: «88.8»");
		assertEquals(88.8, entry.getValue()
				, "should have been: «88.8»");
		assertEquals((Double)null, entry.set("").validateMax((Double)null).getValue()
				, "should have been: «null»");
		assertEquals((Double)null, entry.getValue()
				, "should have been: «null»");
	}
	@Test
	void testValidateMinMax() {
		assertEquals(8.8, entry.set(8.8)
				.validateMinMax(-88.8, 88.8).getValue()
				, "should have been «8»");
		assertEquals(88.8, entry.set(888.8)
				.validateMinMax(-88.8, 88.8).getValue()
				, "should have been «88.8»");
		assertEquals(-88.8, entry.set(-888.8)
				.validateMinMax(-88.8, 88.8).getValue()
				, "should have been «-88.8»");
		assertEquals(-888.8, entry.set(-888.8)
				.validateMinMax((Double)null, 88.8).getValue()
				, "should have been «-888.8»");
		assertEquals(88.8, entry.set(888.8)
				.validateMinMax((Double)null, 88.8).getValue()
				, "should have been «88.8»");
		assertEquals(888.8, entry.set(888.8)
				.validateMinMax((Double)null, (Double)null).getValue()
				, "should have been «888.8»");
		assertEquals(-88.8, entry.set(-888.8)
				.validateMinMax(-88.8, (Double)null).getValue()
				, "should have been «-88.8»");
		assertEquals(888.8, entry.set(888.8)
				.validateMinMax(-88.8, (Double)null).getValue()
				, "should have been «888.8»");
		assertEquals(0, entry.set((Double)null)
				.validateMinMax(-88.8, 88.8).getValue()
				, "should have been «0»");
		assertEquals(88.8, entry.set((Double)null)
				.validateMinMax((Double)null, 88.8).getValue()
				, "should have been «88.8»");
		assertEquals(-88.8, entry.set((Double)null)
				.validateMinMax(-88.8, (Double)null).getValue()
				, "should have been «-88.8»");
	}
	@Test
	void testGetValueDouble() {
		assertEquals(88.8, new Entry_Double(88.8).getValue(888.8)
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").getValue(888.8)
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").getValue(888.8)
				, "should have been «88.8»");
		assertEquals(888.8, new Entry_Double("").getValue(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double("xxx").getValue(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double().getValue(888.8)
				, "should have been «888.8»");
	}
	@Test
	void testGetOrDefaultValueClass() {
		assertEquals(88.8, new Entry_Double(88.8).getOrDefault(888.8)
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").getOrDefault(888.8)
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").getOrDefault(888.8)
				, "should have been «88.8»");
		assertEquals(888.8, new Entry_Double("").getOrDefault(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double("xxx").getOrDefault(888.8)
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double().getOrDefault(888.8)
				, "should have been «888.8»");
	}
	@Test
	void testGetOrDefaultEntry_BaseOfValueClass() {
		assertEquals(88.8, new Entry_Double(88.8).getOrDefault(new Entry_Double(888.8))
				, "should have been «88.8»");	
		assertEquals(88.8, new Entry_Double("88.8").getOrDefault(new Entry_Double(888.8))
				, "should have been «88.8»");
		assertEquals(88.8, new Entry_Double("88.8").getOrDefault(new Entry_Double(888.8))
				, "should have been «88.8»");
		assertEquals(888.8, new Entry_Double("").getOrDefault(new Entry_Double(888.8))
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double("xxx").getOrDefault(new Entry_Double(888.8))
				, "should have been «888.8»");
		assertEquals(888.8, new Entry_Double().getOrDefault(new Entry_Double(888.8))
				, "should have been «888.8»");
	}
	@Test
	void testToComment() {
		assertEquals("# 88.8", new Entry_Double(88.8).toComment()
				, "should have been «# 88.8»");	
		assertEquals("# 88.8", new Entry_Double("88.8").toComment()
				, "should have been «# 88.8»");
		assertEquals("# 88.8", new Entry_Double("88.8").toComment()
				, "should have been «# 88.8»");
		assertEquals("# ", new Entry_Double().toComment()
				, "should have been «# »");
		assertEquals("# ", new Entry_Double("").toComment()
				, "should have been «# null»");
		assertEquals("# ", new Entry_Double("xxx").toComment()
				, "should have been «# null»");
	}
	@Test
	void testToCommentBoolean() {
		assertEquals("# 88.8", new Entry_Double(88.8).toComment(false)
				, "should have been «88.8»");	
		assertEquals("# 88.8", new Entry_Double("88.8").toComment(true)
				, "should have been «88.8»");
		assertEquals("# 88.8", new Entry_Double("88.8").toComment(false)
				, "should have been «88.8»");
		assertEquals("", new Entry_Double().toComment(false)
				, "should have been «»");
		assertEquals("", new Entry_Double("").toComment(false)
				, "should have been «null»");
		assertEquals(null, new Entry_Double("xxx").toComment((Boolean)null)
				, "should have been «null»");
		assertEquals("# ", new Entry_Double("xxx").toComment(true)
				, "should have been «null»");
	}
	@Test
	void testToPrint() {
		assertEquals("88.8", new Entry_Double(88.8).toPrint()
				, "should have been «88.8»");	
		assertEquals("88.8", new Entry_Double("88.8").toPrint()
				, "should have been «88.8»");
		assertEquals("88.8", new Entry_Double("88.8").toPrint()
				, "should have been «88.8»");
		assertEquals("", new Entry_Double().toPrint()
				, "should have been «»");
		assertEquals("", new Entry_Double("").toPrint()
				, "should have been «null»");
		assertEquals("", new Entry_Double("xxx").toPrint()
				, "should have been «»");
	}
}
