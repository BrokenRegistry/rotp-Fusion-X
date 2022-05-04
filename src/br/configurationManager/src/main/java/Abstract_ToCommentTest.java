package br.configurationManager.src.main.java;

import static br.configurationManager.src.main.java.Abstract_ToComment.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Abstract_ToCommentTest {

	@Test
	void toComment_Object() {
		assertEquals("# abc", toComment("abc")
				, "should have been «# abc»");
		assertEquals("# ", toComment("")
				, "should have been «# »");
		assertEquals("", toComment((String)null)
				, "should have been «»");
	}

	@Test
	void toComment_Object_Boolean() {
		assertEquals("# abc", toComment("abc", true)
				, "should have been «# abc»");
		assertEquals("# ", toComment("", true)
				, "should have been «# »");
		assertEquals("", toComment("", false)
				, "should have been «»");
		assertEquals((String)null, toComment("", (Boolean)null)
				, "should have been «null»");
	}

	@Test
	void isComment_Object() {
		assertEquals(false, isComment("abc")
				, "should have been «false»");
		assertEquals(false, isComment("")
				, "should have been «false»");
		assertEquals(false, isComment((Boolean)null)
				, "should have been «false»");
		assertEquals(true, isComment("# abc")
				, "should have been «true»");
		assertEquals(true, isComment("#abc")
				, "should have been «true»");
		assertEquals(true, isComment("#abc")
				, "should have been «true»");
		assertEquals(true, isComment("#")
				, "should have been «true»");
		assertEquals(false, isComment("xyz    # abc")
				, "should have been «false»");
	}

	@Test
	void containsComment_Object() {
		assertEquals(false, containsComment("abc")
				, "should have been «false»");
		assertEquals(false, containsComment("")
				, "should have been «false»");
		assertEquals(false, containsComment((Boolean)null)
				, "should have been «false»");
		assertEquals(true, containsComment("# abc")
				, "should have been «true»");
		assertEquals(true, containsComment("#abc")
				, "should have been «true»");
		assertEquals(true, containsComment("#abc")
				, "should have been «true»");
		assertEquals(true, containsComment("#")
				, "should have been «true»");
		assertEquals(true, containsComment("xyz    # abc")
				, "should have been «true»");
	}

	@Test
	void removeComment_Object() {
		assertEquals("", removeComment("# abc")
				, "should have been «»");
		assertEquals("xyz", removeComment("xyz    # abc")
				, "should have been «xyz»");
	}

	@Test
	void extractComment_Object() {
		assertEquals("", extractComment("# ")
				, "should have been «»");
		assertEquals("abc", extractComment("# abc")
				, "should have been «abc»");
		assertEquals("abc", extractComment("xyz    # abc")
				, "should have been «abc»");
		assertEquals((String)null, extractComment((String)null)
				, "should have been «null»");
	}

	@Test
	void splitComment_Object() {
		assertEquals("xyz", splitComment("xyz    # abc")[0]
				, "should have been «xyz»");
		assertEquals("abc", splitComment("xyz    # abc")[1]
				, "should have been «abc»");
		assertEquals("", splitComment("    # abc")[0]
				, "should have been «xyz»");
		assertEquals("abc", splitComment("    # abc")[1]
				, "should have been «abc»");
		assertEquals("xyz", splitComment("xyz    #")[0]
				, "should have been «xyz»");
		assertEquals("", splitComment("xyz    #")[1]
				, "should have been «»");
		assertEquals("xyz", splitComment("xyz ")[0]
				, "should have been «xyz»");
		assertEquals("", splitComment("xyz ")[1]
				, "should have been «null»");
		assertEquals("", splitComment("# abc")[0]
				, "should have been «»");
		assertEquals("abc", splitComment("# abc")[1]
				, "should have been «abc»");
		assertEquals("", splitComment("")[0]
				, "should have been «»");
		assertEquals("", splitComment("")[1]
				, "should have been «null»");
	}

	@Test
	void trawSplitComment_Object() {
		assertEquals("xyz    ", rawSplitComment("xyz    # abc")[0]
				, "should have been «xyz»");
		assertEquals(" abc", rawSplitComment("xyz    # abc")[1]
				, "should have been « abc»");
		assertEquals("    ", rawSplitComment("    # abc")[0]
				, "should have been «xyz»");
		assertEquals(" abc", rawSplitComment("    # abc")[1]
				, "should have been « abc»");
		assertEquals("xyz    ", rawSplitComment("xyz    #")[0]
				, "should have been «xyz»");
		assertEquals("", rawSplitComment("xyz    #")[1]
				, "should have been «»");
		assertEquals("xyz ", rawSplitComment("xyz ")[0]
				, "should have been «xyz»");
		assertEquals((String)null, rawSplitComment("xyz ")[1]
				, "should have been «null»");
		assertEquals("", rawSplitComment("# abc")[0]
				, "should have been «»");
		assertEquals(" abc", rawSplitComment("# abc")[1]
				, "should have been « abc»");
		assertEquals("", rawSplitComment("")[0]
				, "should have been «»");
		assertEquals((String)null, rawSplitComment("")[1]
				, "should have been «null»");
	}
}
