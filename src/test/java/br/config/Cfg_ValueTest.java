package test.java.br.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.config.Cfg_Entry;
import br.config.Cfg_Value;
import br.config.Cfg_Value.DataType;
import static br.config.Cfg_Value.*;

class Cfg_ValueTest {

	void template() {
		assertEquals(true, true
				, "should have been: «true» «false» «»");
	}
	@Test
	void testSetString() {
		Cfg_Value value = new Cfg_Value();
		assertEquals("xxx", value.set("xxx").get()
				, "should have been: «xxx»");
	}
	@Test
	void testSetStringBoolean() {
		Cfg_Value value = new Cfg_Value();
		assertEquals("abc", value.set("abc", true).get()
				, "should have been: «abc»");	
		assertEquals(true, value.isCaseSensitive()
				, "should have been: «true»");	
		assertEquals("zzz", value.set("zzz", false).get()
				, "should have been: «zzz»");	
		assertEquals(false, value.isCaseSensitive()
				, "should have been: «false»");	
	}
	@Test
	void testSetCfg_Entry() {
		Cfg_Value value = new Cfg_Value();
		assertEquals("entry", value.set(new Cfg_Entry("entry", true)).get()
				, "should have been: «entry»");	
		assertEquals(true, value.isCaseSensitive()
				, "should have been: «true»");	
		assertEquals("cfg", value.set(new Cfg_Entry("cfg", false)).get()
				, "should have been: «cfg»");	
		assertEquals(false, value.isCaseSensitive()
				, "should have been: «false»");	
	}
	@Test
	void testSetCfg_EntryBoolean() {
		Cfg_Value value = new Cfg_Value();
		assertEquals(true, value.set(true).toBoolean()
				, "should have been: «true»");	
		assertEquals(false, value.set(false).toBoolean()
				, "should have been: «false»");	
	}
	@Test
	void testIsEmpty() {
		Cfg_Value value = new Cfg_Value();
		assertEquals(true, value.isEmpty()
				, "should have been: «true»");	
		assertEquals(false, value.set("not empty").isEmpty()
				, "should have been: «false»");	
		assertEquals(true, value.set("").isEmpty()
				, "should have been: «true»");	
		assertEquals(false, value.set(0.0).isEmpty()
				, "should have been: «false»");	
		assertEquals(false, value.set(88).isEmpty()
				, "should have been: «false»");	
		assertEquals(false, value.set(true).isEmpty()
				, "should have been: «false»");	
		assertEquals(true, value.set("").isEmpty()
				, "should have been: «true»");	
	}
	@Test
	void testTestForNumeric() {
		Cfg_Value value = new Cfg_Value();
		assertEquals(false, value.testForNumeric()
				, "should have been: «false»");	
		assertEquals(true, value.set(88).testForNumeric()
				, "should have been: «true»");	
		assertEquals(false, value.set("text").testForNumeric()
				, "should have been: «false»");	
		assertEquals(true, value.set(88.8).testForNumeric()
				, "should have been: «true»");	
		assertEquals(false, value.set(true).testForNumeric()
				, "should have been: «false»");	
		assertEquals(true, value.set("88.8").testForNumeric()
				, "should have been: «true»");	
	}
	// ===== Constructors =====
	@Test
	void testCfg_Value() {
		assertEquals("", new Cfg_Value().get()
				, "should have been: «»");	
		assertEquals((Boolean)null, new Cfg_Value().getCaseSensitive()
				, "should have been: «null»");	
	}
	@Test
	void testCfg_ValueStringDataType() {
		assertEquals("abc", new Cfg_Value("abc", DataType.STRING).get()
				, "should have been: «abc»");	
		assertEquals(DataType.STRING, new Cfg_Value("abc", DataType.STRING).getDataType()
				, "should have been: «STRING»");	
	}
	@Test
	void testCfg_ValueDoubleDataType() {
		assertEquals(88.8, new Cfg_Value(88.8, DataType.STRING).toDouble()
				, "should have been: «88.8»");	
		assertEquals("88.8", new Cfg_Value(88.8, DataType.STRING).toString()
				, "should have been: «88.8»");	
		assertEquals("88.8", new Cfg_Value(88.8, DataType.STRING).toPrint()
				, "should have been: «88.8»");	
		assertEquals("88.8", new Cfg_Value(88.8, DataType.STRING).get()
				, "should have been: «88.8»");	
		assertEquals(89L, new Cfg_Value(88.8, DataType.STRING).toLong()
				, "should have been: «89»");	
		assertEquals((Boolean)null, new Cfg_Value(88.8, DataType.STRING).toBoolean()
				, "should have been: «null»");	
		assertEquals(DataType.BOOLEAN, new Cfg_Value(88.8, DataType.BOOLEAN).getDataType()
				, "should have been: «BOOLEAN»");	
	}
	@Test
	void testCfg_ValueFloatDataType() {
		assertEquals((float)88.8, new Cfg_Value((float)88.8, DataType.STRING).toFloat()
				, "should have been: «88.8»");	
	}
	@Test
	void testCfg_ValueLongDataType() {
		assertEquals(89.0, new Cfg_Value(89L, DataType.STRING).toDouble()
				, "should have been: «89»");	
	}
	@Test
	void testCfg_ValueIntDataType() {
		assertEquals(89.0, new Cfg_Value(89, DataType.STRING).toDouble()
				, "should have been: «89.0»");	
	}
	@Test
	void testCfg_ValueShortDataType() {
		assertEquals(88.0, new Cfg_Value((short)88, DataType.STRING).toDouble()
				, "should have been: «88.0»");	
	}
	@Test
	void testCfg_ValueByteDataType() {
		assertEquals(88.0, new Cfg_Value((byte)88, DataType.STRING).toDouble()
				, "should have been: «88.0»");	
	}
	@Test
	void testCfg_ValueBooleanDataType() {
		assertEquals(true, new Cfg_Value(true, DataType.STRING).toBoolean()
				, "should have been: «true»");	
		assertEquals((Double)null, new Cfg_Value(true, DataType.STRING).toDouble()
				, "should have been: «null»");	
		assertEquals("YES", new Cfg_Value(true, DataType.STRING).get()
				, "should have been: «YES»");	
	}
	@Test
	void testCfg_ValueString() {
		assertEquals("Xyz", new Cfg_Value("Xyz", DataType.STRING).get()
				, "should have been: «Xyz»");	
	}
	@Test
	void testCfg_ValueDouble() {
		assertEquals(88.8, new Cfg_Value(88.8).toDouble()
				, "should have been: «88.8»");	
	}
	@Test
	void testCfg_ValueFloat() {
		assertEquals((float)88.8, new Cfg_Value((float)88.8).toFloat()
				, "should have been: «88.8»");	
	}
	@Test
	void testCfg_ValueLong() {
		assertEquals(89L, new Cfg_Value(89L).toLong()
				, "should have been: «89»");	
	}
	@Test
	void testCfg_ValueInt() {
		assertEquals(88, new Cfg_Value(88).toInteger()
				, "should have been: «88»");	
	}
	@Test
	void testCfg_ValueShort() {
		assertEquals((short)88, new Cfg_Value((short)88).toShort()
				, "should have been: «88»");	
	}
	@Test
	void testCfg_ValueByte() {
		assertEquals((byte)88, new Cfg_Value((byte)88).toByte()
				, "should have been: «88»");	
	}
	@Test
	void testCfg_ValueBoolean() {
		assertEquals(true, new Cfg_Value(true).toBoolean()
				, "should have been: «true»");	
		assertEquals(false, new Cfg_Value(false).toBoolean()
				, "should have been: «false»");	
	}
	// ===== Test Methods =====
	@Test
	void testIsWhole() {
		assertEquals(false, new Cfg_Value(true).isWhole()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.WHOLE).isWhole()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isWhole()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isWhole()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isWhole()
				, "should have been: «true»");	
	}
	@Test
	void testIsDouble() {
		assertEquals(false, new Cfg_Value(true).isDouble()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.FLOATING).isDouble()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isDouble()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isDouble()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isDouble()
				, "should have been: «true»");	
	}
	@Test
	void testIsFloat() {
		assertEquals(false, new Cfg_Value(true).isFloat()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.FLOATING).isFloat()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isFloat()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isFloat()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isFloat()
				, "should have been: «true»");	
	}
	@Test
	void testIsLong() {
		assertEquals(false, new Cfg_Value(true).isLong()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.WHOLE).isLong()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isLong()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isLong()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isLong()
				, "should have been: «true»");	
	}
	@Test
	void testIsInteger() {
		assertEquals(false, new Cfg_Value(true).isInteger()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.WHOLE).isInteger()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isInteger()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isInteger()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isInteger()
				, "should have been: «true»");	
	}
	@Test
	void testIsShort() {
		assertEquals(false, new Cfg_Value(true).isShort()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.WHOLE).isShort()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isShort()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isShort()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isShort()
				, "should have been: «true»");	
	}
	@Test
	void testIsByte() {
		assertEquals(false, new Cfg_Value(true).isByte()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(true, DataType.WHOLE).isByte()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value(88).isByte()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(88.8).isByte()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("88.8").isByte()
				, "should have been: «true»");	
	}
	@Test
	void testIsBoolean() {
		assertEquals(true, new Cfg_Value(true).isBoolean()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(true, DataType.WHOLE).isBoolean()
				, "should have been: «true»");	
		assertEquals(false, new Cfg_Value(88).isBoolean()
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value(88.8, DataType.BOOLEAN).isBoolean()
				, "should have been: «false»");	
		assertEquals(true, new Cfg_Value("NO").isBoolean()
				, "should have been: «true»");	
	}
	// ===== Getter Methods =====
	@Test
	void testClone() {
		Cfg_Value value = new Cfg_Value("abc", DataType.WHOLE);
		Cfg_Value clone = value.clone();
		assertEquals("abc", clone.get()
				, "should have been: «abc»");	
		assertEquals(DataType.WHOLE, clone.getDataType()
				, "should have been: «WHOLE»");	
		value = new Cfg_Value("xyz", DataType.BOOLEAN);
		assertEquals("abc", clone.get()
				, "should have been: «abc»");	
		assertEquals(DataType.WHOLE, clone.getDataType()
				, "should have been: «WHOLE»");	
	}
	@Test
	void testGetCfg_Value() { // get a copy
		Cfg_Value value = new Cfg_Value("abc", DataType.WHOLE);
		Cfg_Value clone = value.getCfg_Value();
		assertEquals("abc", clone.get()
				, "should have been: «abc»");	
		assertEquals(DataType.WHOLE, clone.getDataType()
				, "should have been: «WHOLE»");	
		value = new Cfg_Value("xyz", DataType.BOOLEAN);
		assertEquals("abc", clone.get()
				, "should have been: «abc»");	
		assertEquals(DataType.WHOLE, clone.getDataType()
				, "should have been: «WHOLE»");	
	}
	@Test
	void testGet() {
		assertEquals("", new Cfg_Value().get()
				, "should have been: «»");	
		assertEquals("abc", new Cfg_Value("abc").get()
				, "should have been: «abc»");	
		assertEquals("YES", new Cfg_Value(true).get()
				, "should have been: «YES»");	
		assertEquals("88.9", new Cfg_Value(88.9).get()
				, "should have been: «88.9»");	
		assertEquals("88", new Cfg_Value(88L).get()
				, "should have been: «88.9»");	
		assertEquals("89", new Cfg_Value(88.9, DataType.WHOLE).get()
				, "should have been: «89»");	
		assertEquals("88.0", new Cfg_Value(88L, DataType.FLOATING).get()
				, "should have been: «88.0»");	
	}
	@Test
	void testToPrint() {
		assertEquals("", new Cfg_Value().toPrint()
				, "should have been: «»");	
		assertEquals("abc", new Cfg_Value("abc").toPrint()
				, "should have been: «abc»");	
		assertEquals("YES", new Cfg_Value(true).toPrint()
				, "should have been: «YES»");	
		assertEquals("88.9", new Cfg_Value(88.9).toPrint()
				, "should have been: «88.9»");	
		assertEquals("88", new Cfg_Value(88L).toPrint()
				, "should have been: «88.9»");	
		assertEquals("89", new Cfg_Value(88.9, DataType.WHOLE).toPrint()
				, "should have been: «89»");	
		assertEquals("88.0", new Cfg_Value(88L, DataType.FLOATING).toPrint()
				, "should have been: «88.0»");	
	}
	@Test
	void testToString() {
		assertEquals("", new Cfg_Value().toString()
				, "should have been: «»");	
		assertEquals("abc", new Cfg_Value("abc").toString()
				, "should have been: «abc»");	
		assertEquals("YES", new Cfg_Value(true).toString()
				, "should have been: «YES»");	
		assertEquals("88.9", new Cfg_Value(88.9).toString()
				, "should have been: «88.9»");	
		assertEquals("88", new Cfg_Value(88L).toString()
				, "should have been: «88.9»");	
		assertEquals("89", new Cfg_Value(88.9, DataType.WHOLE).toString()
				, "should have been: «89»");	
		assertEquals("88.0", new Cfg_Value(88L, DataType.FLOATING).toString()
				, "should have been: «88.0»");	
		assertEquals("", new Cfg_Value((Double)null).toString()
				, "should have been: «88.9»");	
	}
	@Test
	void testToStringString() {
		assertEquals("Empty", new Cfg_Value().toString("Empty")
				, "should have been: «Empty»");	
		assertEquals("abc", new Cfg_Value("abc").toString("Empty")
				, "should have been: «abc»");	
		assertEquals("YES", new Cfg_Value(true).toString("Empty")
				, "should have been: «YES»");	
		assertEquals("88.9", new Cfg_Value(88.9).toString("Empty")
				, "should have been: «88.9»");	
		assertEquals("88", new Cfg_Value(88L).toString("Empty")
				, "should have been: «88.9»");	
		assertEquals("89", new Cfg_Value(88.9, DataType.WHOLE).toString("Empty")
				, "should have been: «89»");	
		assertEquals("88.0", new Cfg_Value(88L, DataType.FLOATING).toString("Empty")
				, "should have been: «88»");	
		assertEquals("Empty", new Cfg_Value((Double)null).toString("Empty")
				, "should have been: «88.9»");	
	}
	@Test
	void testToDouble() {
		assertEquals((Double)null, new Cfg_Value().toDouble()
				, "should have been: «null»");	
		assertEquals((Double)null, new Cfg_Value(true).toDouble()
				, "should have been: «null»");	
		assertEquals((Double)null, new Cfg_Value("Test").toDouble()
				, "should have been: «null»");	
		assertEquals(88.8, new Cfg_Value("88.8").toDouble()
				, "should have been: «88.8»");	
		assertEquals(88.8, new Cfg_Value(88.8).toDouble()
				, "should have been: «88.8»");	
		assertEquals(88, new Cfg_Value(88L).toDouble()
				, "should have been: «88»");	
	}
	@Test
	void testToDoubleDouble() {
		assertEquals(888.888, new Cfg_Value().toDouble(888.888)
				, "should have been: «888.888»");	
		assertEquals(888.888, new Cfg_Value(true).toDouble(888.888)
				, "should have been: «888.888»");	
		assertEquals(888.888, new Cfg_Value("Test").toDouble(888.888)
				, "should have been: «888.888»");	
		assertEquals(88.8, new Cfg_Value("88.8").toDouble(888.888)
				, "should have been: «88.8»");	
		assertEquals(88.8, new Cfg_Value(88.8).toDouble(888.888)
				, "should have been: «88.8»");	
		assertEquals(88, new Cfg_Value(88L).toDouble(888.888)
				, "should have been: «88»");	
	}
	@Test
	void testToFloat() {
		assertEquals((Float)null, new Cfg_Value().toFloat()
				, "should have been: «null»");	
		assertEquals((Float)null, new Cfg_Value(true).toFloat()
				, "should have been: «null»");	
		assertEquals((Float)null, new Cfg_Value("Test").toFloat()
				, "should have been: «null»");	
		assertEquals((float)88.8, new Cfg_Value("88.8").toFloat()
				, "should have been: «88.8»");	
		assertEquals((float)88.8, new Cfg_Value(88.8).toFloat()
				, "should have been: «88.8»");	
		assertEquals((float)88, new Cfg_Value(88L).toFloat()
				, "should have been: «88»");
	}
	@Test
	void testToFloatFloat() {
		assertEquals((float)888.888, new Cfg_Value().toFloat((float)888.888)
				, "should have been: «888.888»");	
		assertEquals((float)888.888, new Cfg_Value(true).toFloat((float)888.888)
				, "should have been: «888.888»");	
		assertEquals((float)888.888, new Cfg_Value("Test").toFloat((float)888.888)
				, "should have been: «888.888»");	
		assertEquals((float)88.8, new Cfg_Value("88.8").toFloat((float)888.888)
				, "should have been: «88.8»");	
		assertEquals((float)88.8, new Cfg_Value(88.8).toFloat((float)888.888)
				, "should have been: «88.8»");	
		assertEquals((float)88, new Cfg_Value(88L).toFloat((float)888.888)
				, "should have been: «88»");
	}
	@Test
	void testToLong() {
		assertEquals((Long)null, new Cfg_Value().toLong()
				, "should have been: «null»");	
		assertEquals((Long)null, new Cfg_Value(true).toLong()
				, "should have been: «null»");	
		assertEquals((Long)null, new Cfg_Value("Test").toLong()
				, "should have been: «null»");	
		assertEquals(89L, new Cfg_Value("89").toLong()
				, "should have been: «88.8»");	
		assertEquals(89L, new Cfg_Value(88.8).toLong()
				, "should have been: «89»");	
		assertEquals(88L, new Cfg_Value(88L).toLong()
				, "should have been: «88»");
	}
	@Test
	void testToLongLong() {
		assertEquals(888L, new Cfg_Value().toLong(888L)
				, "should have been: «888»");	
		assertEquals(888L, new Cfg_Value(true).toLong(888L)
				, "should have been: «888»");	
		assertEquals(888L, new Cfg_Value("Test").toLong(888L)
				, "should have been: «888»");	
		assertEquals(89L, new Cfg_Value("88.8").toLong(888L)
				, "should have been: «89»");	
		assertEquals(89L, new Cfg_Value(88.8).toLong(888L)
				, "should have been: «89»");	
		assertEquals(88L, new Cfg_Value(88L).toLong(888L)
				, "should have been: «88»");
	}
	@Test
	void testToInteger() {
		assertEquals((Integer)null, new Cfg_Value().toInteger()
				, "should have been: «null»");	
		assertEquals((Integer)null, new Cfg_Value(true).toInteger()
				, "should have been: «null»");	
		assertEquals((Integer)null, new Cfg_Value("Test").toInteger()
				, "should have been: «null»");	
		assertEquals(89, new Cfg_Value("88.8").toInteger()
				, "should have been: «89»");	
		assertEquals(89, new Cfg_Value(88.8).toInteger()
				, "should have been: «89»");	
		assertEquals(88, new Cfg_Value(88).toInteger()
				, "should have been: «88»");
	}
	@Test
	void testToIntegerInteger() {
		assertEquals(888, new Cfg_Value().toInteger(888)
				, "should have been: «888»");	
		assertEquals(888, new Cfg_Value(true).toInteger(888)
				, "should have been: «888»");	
		assertEquals(888, new Cfg_Value("Test").toInteger(888)
				, "should have been: «888»");	
		assertEquals(89, new Cfg_Value("88.8").toInteger(888)
				, "should have been: «89»");	
		assertEquals(89, new Cfg_Value(88.8).toInteger(888)
				, "should have been: «89»");	
		assertEquals(88, new Cfg_Value(88L).toInteger(888)
				, "should have been: «88»");
	}
	@Test
	void testToShort() {
		assertEquals((Short)null, new Cfg_Value().toShort()
				, "should have been: «null»");	
		assertEquals((Short)null, new Cfg_Value(true).toShort()
				, "should have been: «null»");	
		assertEquals((Short)null, new Cfg_Value("Test").toShort()
				, "should have been: «null»");	
		assertEquals((short)89, new Cfg_Value("88.8").toShort()
				, "should have been: «89»");	
		assertEquals((short)89, new Cfg_Value(88.8).toShort()
				, "should have been: «89»");	
		assertEquals((short)88, new Cfg_Value((short)88).toShort()
				, "should have been: «88»");
	}
	@Test
	void testToShortShort() {
		assertEquals((short)888, new Cfg_Value().toShort((short)888)
				, "should have been: «888»");	
		assertEquals((short)888, new Cfg_Value(true).toShort((short)888)
				, "should have been: «888»");	
		assertEquals((short)888, new Cfg_Value("Test").toShort((short)888)
				, "should have been: «888»");	
		assertEquals((short)89, new Cfg_Value("88.8").toShort((short)888)
				, "should have been: «89»");	
		assertEquals((short)89, new Cfg_Value(88.8).toShort((short)888)
				, "should have been: «89»");	
		assertEquals((short)88, new Cfg_Value(88L).toShort((short)888)
				, "should have been: «88»");
	}
	@Test
	void testToByte() {
		assertEquals((Byte)null, new Cfg_Value().toByte()
				, "should have been: «null»");	
		assertEquals((Byte)null, new Cfg_Value(true).toByte()
				, "should have been: «null»");	
		assertEquals((Byte)null, new Cfg_Value("Test").toByte()
				, "should have been: «null»");	
		assertEquals((byte)89, new Cfg_Value("88.8").toByte()
				, "should have been: «89»");	
		assertEquals((byte)89, new Cfg_Value(88.8).toByte()
				, "should have been: «89»");	
		assertEquals((byte)88, new Cfg_Value((byte)88).toByte()
				, "should have been: «88»");
	}
	@Test
	void testToByteByte() {
		assertEquals((byte)8, new Cfg_Value().toByte((byte)8)
				, "should have been: «8»");	
		assertEquals((byte)8, new Cfg_Value(true).toByte((byte)8)
				, "should have been: «8»");	
		assertEquals((byte)8, new Cfg_Value("Test").toByte((byte)8)
				, "should have been: «8»");	
		assertEquals((byte)89, new Cfg_Value("88.8").toByte((byte)8)
				, "should have been: «89»");	
		assertEquals((byte)89, new Cfg_Value(88.8).toByte((byte)8)
				, "should have been: «89»");	
		assertEquals((byte)88, new Cfg_Value(88L).toByte((byte)8)
				, "should have been: «88»");
	}
	@Test
	void testToBoolean() {
		assertEquals((Boolean)null, new Cfg_Value().toBoolean()
				, "should have been: «null»");	
		assertEquals(true, new Cfg_Value(true).toBoolean()
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("true").toBoolean()
				, "should have been: «true»");	
		assertEquals(false, new Cfg_Value("no").toBoolean()
				, "should have been: «false»");	
		assertEquals((Boolean)null, new Cfg_Value("xxx").toBoolean()
				, "should have been: «null»");	
		assertEquals((Boolean)null, new Cfg_Value(88).toBoolean()
				, "should have been: «null»");	
	}
	@Test
	void testToBooleanBoolean() {
		assertEquals(true, new Cfg_Value().toBoolean(true)
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value(true).toBoolean(false)
				, "should have been: «true»");	
		assertEquals(true, new Cfg_Value("true").toBoolean(false)
				, "should have been: «true»");	
		assertEquals(false, new Cfg_Value("no").toBoolean(true)
				, "should have been: «false»");	
		assertEquals(false, new Cfg_Value("xxx").toBoolean(false)
				, "should have been: «false»");	
		assertEquals((Boolean)null, new Cfg_Value(88).toBoolean((Boolean)null)
				, "should have been: «null»");	
	}
	@Test
	void testGetDataType() {
		assertEquals(DataType.UNKNOWN, new Cfg_Value().getDataType()
				, "should have been: «UNKNOWN»");	
		assertEquals(DataType.WHOLE, new Cfg_Value("", DataType.WHOLE).getDataType()
				, "should have been: «WHOLE»");	
	}
	// ===== Getter Methods =====
	@Test
	void testNextRandom() {
		Cfg_Value min = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value max = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(DataType.WHOLE, nextRandom(min, max).getDataType()
				, "should have been: «WHOLE»");	
		assertEquals(true, 88 >= Math.abs(nextRandom(min, max).toInteger())
				, "should have been: «true»");
		assertEquals(true, 88 >= Math.abs(nextRandom(max, min).toInteger())
				, "should have been: «true»");
		min = new Cfg_Value (-88, DataType.FLOATING);
		assertEquals(DataType.WHOLE, nextRandom(min, max).getDataType()
				, "should have been: «WHOLE»");	
		max = new Cfg_Value (88, DataType.FLOATING);
		assertEquals(DataType.FLOATING, nextRandom(min, max).getDataType()
				, "should have been: «FLOATING»");	
		assertEquals(true, 88 >= Math.abs(nextRandom(min, max).toInteger())
				, "should have been: «true»");
		assertEquals(true, 88.0 >= Math.abs(nextRandom(min, max).toDouble())
				, "should have been: «true»");
		min = null;
		assertEquals((Double)null, nextRandom(min, max).toDouble()
				, "should have been: «null»");
	}
	@Test
	void testValidateMin() {
		Cfg_Value val2 = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value val1 = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(-88, val2.validateMin(val1).toInteger()
				, "should have been: «-88»");
		assertEquals(DataType.WHOLE, val2.validateMin(val1).getDataType()
				, "should have been: «WHOLE»");
		assertEquals(-88, val1.validateMin(val2).toInteger()
				, "should have been: «-88»");
		assertEquals(-88, val1.toInteger()
				, "should have been: «-88»");
		val2 = new Cfg_Value (-88, DataType.WHOLE);
		val1 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(-88, val2.validateMin(val1).toDouble()
				, "should have been: «-88»");
		assertEquals(DataType.FLOATING, val1.validateMin(val2).getDataType()
				, "should have been: «FLOATING»");
		val2 = new Cfg_Value (-88.8, DataType.FLOATING);
		val1 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(-88.8, val2.validateMin(val1).toDouble()
				, "should have been: «-88.8»");
		assertEquals(DataType.FLOATING, val1.validateMin(val2).getDataType()
				, "should have been: «FLOATING»");
		val2 = null;
		assertEquals(-88.8, val1.validateMin(val2).toDouble()
				, "should have been: «-88.8»");
		val2 = new Cfg_Value ((Double)null, DataType.FLOATING);
		assertEquals(-88.8, val2.validateMin(val1).toDouble()
				, "should have been: «-88.8»");
	}
	@Test
	void testValidateMax() {
		Cfg_Value val1 = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value val2 = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(88, val2.validateMax(val1).toInteger()
				, "should have been: «88»");
		assertEquals(DataType.WHOLE, val2.validateMax(val1).getDataType()
				, "should have been: «WHOLE»");
		assertEquals(88, val1.validateMax(val2).toInteger()
				, "should have been: «88»");
		assertEquals(88, val1.toInteger()
				, "should have been: «88»");
		val1 = new Cfg_Value (-88, DataType.WHOLE);
		val2 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(88.8, val2.validateMax(val1).toDouble()
				, "should have been: «88.8»");
		assertEquals(DataType.WHOLE, val1.validateMax(val2).getDataType()
				, "should have been: «WHOLE»");
		val1 = new Cfg_Value (-88.8, DataType.FLOATING);
		val2 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(88.8, val2.validateMax(val1).toDouble()
				, "should have been: «88.8»");
		assertEquals(DataType.FLOATING, val1.validateMax(val2).getDataType()
				, "should have been: «FLOATING»");
		val2 = null;
		assertEquals(88.8, val1.validateMax(val2).toDouble()
				, "should have been: «88.8»");
		val1 = new Cfg_Value (-88.8, DataType.FLOATING);
		assertEquals(-88.8, val1.validateMax(val2).toDouble()
				, "should have been: «-88.8»");
		val2 = new Cfg_Value ((Double)null, DataType.FLOATING);
		assertEquals(-88.8, val2.validateMax(val1).toDouble()
				, "should have been: «-88.8»");
	}
	@Test
	void testValidateMinMax() {
		Cfg_Value min = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value max = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(0, new Cfg_Value (0, DataType.WHOLE)
				.validateMinMax(min, max).toInteger()
				, "should have been: «0»");
		assertEquals(88, new Cfg_Value (100, DataType.WHOLE)
				.validateMinMax(min, max).toInteger()
				, "should have been: «88»");
		assertEquals(-88, new Cfg_Value (-100, DataType.WHOLE)
				.validateMinMax(min, max).toInteger()
				, "should have been: «-88»");
		assertEquals(DataType.FLOATING, new Cfg_Value (-100, DataType.FLOATING)
				.validateMinMax(min, max).getDataType()
				, "should have been: «FLOATING»");
		assertEquals(8.88, new Cfg_Value (8.88, DataType.FLOATING)
				.validateMinMax(min, max).toDouble()
				, "should have been: «8.88»");
		min = new Cfg_Value (-88.8, DataType.FLOATING);
		max = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(89, new Cfg_Value (100, DataType.WHOLE)
				.validateMinMax(min, max).toInteger()
				, "should have been: «89»");
		assertEquals(89.0, new Cfg_Value (100, DataType.WHOLE)
				.validateMinMax(min, max).toDouble()
				, "should have been: «89.0»");
		assertEquals(88.8, new Cfg_Value (100, DataType.FLOATING)
				.validateMinMax(min, max).toDouble()
				, "should have been: «88.8»");
	}
	@Test
	void testMax() {
		Cfg_Value val1 = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value val2 = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(88.0, max(val1, val2).toDouble()
				, "should have been: «88»");
		assertEquals(88.0, max(val2, val1).toDouble()
				, "should have been: «88»");
		assertEquals(DataType.WHOLE, max(val2, val1).getDataType()
				, "should have been: «WHOLE»");
		val1 = new Cfg_Value (-88.8, DataType.WHOLE);
		val2 = new Cfg_Value (88.8, DataType.WHOLE);
		assertEquals(88.8, max(val1, val2).toDouble()
				, "should have been: «88.8»");
		assertEquals(DataType.WHOLE, max(val2, val1).getDataType()
				, "should have been: «WHOLE»");
		val1 = new Cfg_Value (-88.8, DataType.FLOATING);
		val2 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(88.8, max(val1, val2).toDouble()
				, "should have been: «88.8»");
		assertEquals(88.8, max(val2, val1).toDouble()
				, "should have been: «88.8»");
		assertEquals(DataType.FLOATING, max(val2, val1).getDataType()
				, "should have been: «FLOATING»");
	}
	@Test
	void testMin() {
		Cfg_Value val1 = new Cfg_Value (-88, DataType.WHOLE);
		Cfg_Value val2 = new Cfg_Value (88, DataType.WHOLE);
		assertEquals(-88.0, min(val1, val2).toDouble()
				, "should have been: «-88»");
		assertEquals(-88.0, min(val2, val1).toDouble()
				, "should have been: «-88»");
		assertEquals(DataType.WHOLE, min(val2, val1).getDataType()
				, "should have been: «WHOLE»");
		val1 = new Cfg_Value (-88.8, DataType.WHOLE);
		val2 = new Cfg_Value (88.8, DataType.WHOLE);
		assertEquals(-88.8, min(val1, val2).toDouble()
				, "should have been: «-88.8»");
		assertEquals(DataType.WHOLE, min(val2, val1).getDataType()
				, "should have been: «WHOLE»");
		val1 = new Cfg_Value (-88.8, DataType.FLOATING);
		val2 = new Cfg_Value (88.8, DataType.FLOATING);
		assertEquals(-88.8, min(val1, val2).toDouble()
				, "should have been: «-88.8»");
		assertEquals(-88.8, min(val2, val1).toDouble()
				, "should have been: «-88.8»");
		assertEquals(DataType.FLOATING, min(val2, val1).getDataType()
				, "should have been: «FLOATING»");
	}
	@Test
	void testSetDataType() {
		assertEquals(DataType.UNKNOWN, new Cfg_Value().getDataType()
				, "should have been: «UNKNOWN»");	
		assertEquals(DataType.WHOLE, new Cfg_Value("", DataType.WHOLE).getDataType()
				, "should have been: «WHOLE»");	
	}
	@Test
	void testSetDouble() {
		assertEquals(88.8, new Cfg_Value("xxx").set(88.8).toDouble()
				, "should have been: «88.8»");	
	}
	@Test
	void testSetFloat() {
		assertEquals((float)88.8, new Cfg_Value("4").set((float)88.8).toFloat()
				, "should have been: «88.8»");	
	}
	@Test
	void testSetLong() {
		assertEquals((long)88, new Cfg_Value(true).set((long)88).toLong()
				, "should have been: «88»");	
	}
	@Test
	void testSetInteger() {
		assertEquals(88, new Cfg_Value("yes").set(88).toInteger()
				, "should have been: «88»");	
	}
	@Test
	void testSetShort() {
		assertEquals((short)88, new Cfg_Value(888.888).set((short)88).toShort()
				, "should have been: «88»");	
	}
	@Test
	void testSetByte() {
		assertEquals((byte)88, new Cfg_Value("xxx").set((byte)88).toByte()
				, "should have been: «88»");	
	}
	@Test
	void testSetBoolean() {
		assertEquals(true, new Cfg_Value("xxx").set(true).toBoolean()
				, "should have been: «true»");	
	}
}
