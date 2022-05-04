package br.configurationManager.src.main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Entry_BooleanTest {

	Entry_Boolean entry  = new Entry_Boolean();
	Entry_Boolean origin = new Entry_Boolean();
	
	@Test
	void testEntry_Boolean() {
		assertEquals(true, new Entry_Boolean().set(true).getValue()
				, "should have been «true»");
	}
	@Test
	void testEntry_BooleanString() {
		assertEquals(true, new Entry_Boolean("true").getValue()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean("no").getValue()
				, "should have been «false»");
		assertEquals(null, new Entry_Boolean("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testEntry_BooleanBoolean() {
		assertEquals(true, new Entry_Boolean(true).getValue()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(false).getValue()
				, "should have been «false»");
		assertEquals((Boolean)null, new Entry_Boolean((Boolean)null).getValue()
				, "should have been «null»");
	}
	@Test
	void testGetValue() {
		assertEquals(true, new Entry_Boolean(true).getValue()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(false).getValue()
				, "should have been «false»");
		assertEquals((Boolean)null, new Entry_Boolean("abc").getValue()
				, "should have been «null»");
	}
	@Test
	void testReset() {
		assertEquals((Boolean)null, new Entry_Boolean(true).reset().getValue()
				, "should have been «null»");
		assertEquals("", new Entry_Boolean("abc").reset().getUserEntry()
				, "should have been «»");
		assertEquals("abc", new Entry_Boolean("abc").getUserEntry()
				, "should have been «abc»");
	}
	@Test
	void testSetString() {
		assertEquals(true, new Entry_Boolean(false).set("Yes").getValue()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(true).set("No").getValue()
				, "should have been «false»");
		assertEquals((Boolean)null, new Entry_Boolean(false).set("xxx").getValue()
				, "should have been «null»");
	}
	@Test
	void testClone() {
		assertEquals(true, entry.set(false).clone().set(true).getValue()
				, "should have been «true»");
		assertEquals(false, entry.getValue()
				, "should have been «false»");
	}
	@Test
	void testCopy() {
		assertEquals(true, entry.set(false).copy().set(true).getValue()
				, "should have been «true»");
		assertEquals(true, entry.getValue()
				, "should have been «true»");
	}
	@Test
	void testSetBoolean() {
		assertEquals(true, new Entry_Boolean().set(true).getValue()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean().set(false).getValue()
				, "should have been «false»");
		assertEquals((Boolean)null, new Entry_Boolean().set("xxx").getValue()
				, "should have been «null»");
		assertEquals((Boolean)null, new Entry_Boolean().set((Boolean)null).getValue()
				, "should have been «null»");
	}
	@Test
	void testIsBoolean() {
		assertEquals(true, new Entry_Boolean(true).isBoolean()
				, "should have been «true»");
		assertEquals(true, new Entry_Boolean("false").isBoolean()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean("xxx").isBoolean()
				, "should have been «false»");
		assertEquals(false, new Entry_Boolean((Boolean)null).isBoolean()
				, "should have been «false»");
	}
	@Test
	void testToBoolean() {
		assertEquals(true, new Entry_Boolean().set(true).toBoolean()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean().set(false).toBoolean()
				, "should have been «false»");
		assertEquals((Boolean)null, new Entry_Boolean().set("xxx").toBoolean()
				, "should have been «null»");
		assertEquals((Boolean)null, new Entry_Boolean().set((Boolean)null).toBoolean()
				, "should have been «null»");
	}
	@Test
	void testToBooleanBoolean() {
		assertEquals(true, new Entry_Boolean().set(true).toBoolean(false)
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean().set(false).toBoolean(true)
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean().set("xxx").toBoolean(true)
				, "should have been «null»");
		assertEquals(false, new Entry_Boolean().set((Boolean)null).toBoolean(false)
				, "should have been «null»");
	}
	@Test
	void testToString() {
		assertEquals("YES", new Entry_Boolean(true).toString()
				, "should have been «YES»");
		assertEquals("NO", new Entry_Boolean(false).toString()
				, "should have been «NO»");
		assertEquals("null", new Entry_Boolean((Boolean)null).toString()
				, "should have been «null»");
		assertEquals("YES", new Entry_Boolean("true").toString()
				, "should have been «YES»");
		assertEquals("NO", new Entry_Boolean("false").toString()
				, "should have been «NO»");
		assertEquals("null", new Entry_Boolean("abc").toString()
				, "should have been «null»");
		assertEquals("", new Entry_Boolean().toString()
				, "should have been «»");
	}
	@Test
	void testIsBlank() {
		assertEquals(false, new Entry_Boolean(true).isBlank()
				, "should have been «false»");
		assertEquals(false, new Entry_Boolean(false).isBlank()
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean((Boolean)null).isBlank()
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean("true").isBlank()
				, "should have been «false»");
		assertEquals(false, new Entry_Boolean("false").isBlank()
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean("abc").isBlank()
				, "should have been «true»");
		assertEquals(true, new Entry_Boolean().isBlank()
				, "should have been «true»");		
	}
	@Test
	void testGetValueBoolean() {
		assertEquals(true, new Entry_Boolean(true).getValue(false)
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(false).getValue(true)
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean("xxx").getValue(true)
				, "should have been «null»");
		assertEquals(false, new Entry_Boolean((Boolean)null).getValue(false)
				, "should have been «null»");
	}
	@Test
	void testGetOrDefaultEntry_BaseOfValueClass() {
		assertEquals(true, new Entry_Boolean(true).getOrDefault(new Entry_Boolean(false))
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(false).getOrDefault(new Entry_Boolean(true))
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean("xxx").getOrDefault(new Entry_Boolean(true))
				, "should have been «null»");
		assertEquals(false, new Entry_Boolean((Boolean)null).getOrDefault(new Entry_Boolean(false))
				, "should have been «null»");
	}
	@Test
	void testGetOrDefaultValueClass() {
		assertEquals(true, new Entry_Boolean(true).getOrDefault(false)
				, "should have been «true»");
		assertEquals(false, new Entry_Boolean(false).getOrDefault(true)
				, "should have been «false»");
		assertEquals(true, new Entry_Boolean("xxx").getOrDefault(true)
				, "should have been «null»");
		assertEquals(false, new Entry_Boolean((Boolean)null).getOrDefault(false)
				, "should have been «null»");
	}
	@Test
	void testToComment() {
		assertEquals("# YES", new Entry_Boolean(true).toComment()
				, "should have been «# YES»");
		assertEquals("# NO", new Entry_Boolean(false).toComment()
				, "should have been «# NO»");
		assertEquals("# null", new Entry_Boolean((Boolean)null).toComment()
				, "should have been «# null»");
		assertEquals("# YES", new Entry_Boolean("true").toComment()
				, "should have been «# YES»");
		assertEquals("# NO", new Entry_Boolean("false").toComment()
				, "should have been «# NO»");
		assertEquals("# null", new Entry_Boolean("abc").toComment()
				, "should have been «# null»");
		assertEquals("# ", new Entry_Boolean().toComment()
				, "should have been «# »");
	}
	@Test
	void testToCommentBoolean() {
		assertEquals("# YES", new Entry_Boolean(true).toComment(false)
				, "should have been «# YES»");
		assertEquals("# NO", new Entry_Boolean(false).toComment(false)
				, "should have been «# NO»");
		assertEquals("# null", new Entry_Boolean((Boolean)null).toComment(false)
				, "should have been «# null»");
		assertEquals("# YES", new Entry_Boolean("true").toComment(false)
				, "should have been «# YES»");
		assertEquals("# NO", new Entry_Boolean("false").toComment(false)
				, "should have been «# NO»");
		assertEquals("# null", new Entry_Boolean("abc").toComment(false)
				, "should have been «# null»");
		assertEquals("", new Entry_Boolean().toComment(false)
				, "should have been «# »");
		assertEquals("# ", new Entry_Boolean().toComment(true)
				, "should have been «# »");
		assertEquals((String)null, new Entry_Boolean().toComment((Boolean)null)
				, "should have been «# »");
	}
	@Test
	void testToPrint() {
		assertEquals("YES", new Entry_Boolean(true).toPrint()
				, "should have been «YES»");
		assertEquals("NO", new Entry_Boolean(false).toPrint()
				, "should have been «NO»");
		assertEquals("null", new Entry_Boolean((Boolean)null).toPrint()
				, "should have been «null»");
		assertEquals("YES", new Entry_Boolean("true").toPrint()
				, "should have been «YES»");
		assertEquals("NO", new Entry_Boolean("false").toPrint()
				, "should have been «NO»");
		assertEquals("null", new Entry_Boolean("abc").toPrint()
				, "should have been «null»");
		assertEquals("", new Entry_Boolean().toPrint()
				, "should have been «»");
	}
}
