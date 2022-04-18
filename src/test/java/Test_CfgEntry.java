package test.java;

import java.util.List;

import br.config.Cfg_Entry;
import test.java.TestUtil.Abstract_TestList;
import test.java.TestUtil.Abstract_Test;
import test.java.TestUtil.Show;
import test.java.TestUtil.Show.TestLevel;

public class Test_CfgEntry extends Abstract_TestList{

	@Override public String testName()     { return "CfgEntry"; }
	@Override public String listName()     { return "String Methods"; }
	@Override public TestLevel testLevel() { return TestLevel.CLASS; }
	@Override public TestLevel listLevel() { return TestLevel.LIST; }

	@Override protected List<Abstract_Test> getTestList() {
		testList.add(new contains());
		testList.add(new isContained());
		return testList;
	}
	@Override protected List<Abstract_TestList> getSuiteList() { 
		return suiteList;
	}
	
	// ==============================================================
	// Nested Test Class
	//
	class contains extends Abstract_Test{
		@Override protected String testName() { return "Contains"; }

		@Override protected Boolean startTest() {
			Boolean pass = true;
			Cfg_Entry entry1 = new Cfg_Entry("ABC", true);
			Cfg_Entry entry2 = new Cfg_Entry("abcd", true);
			if (entry2.contains(entry1)) {
				showTestError("entry2 does not contains entry1");
				pass = false;
			}
			entry2.setCaseSensitive(false);
			if (!entry2.contains(entry1)) {
				showTestError("entry2 does contains entry1");
				pass = false;
			}
			return pass;
		}
	}
	// ==============================================================
	class isContained extends Abstract_Test{
		@Override protected String testName() { return "Is Contained"; }

		@Override protected Boolean startTest() {
			Boolean pass = true;
			Cfg_Entry entry1 = new Cfg_Entry("ABC", true);
			Cfg_Entry entry2 = new Cfg_Entry("abcd", true);
			if (entry1.isContained(entry2)) {
				showTestError("entry1 is not contained in entry2");
				pass = false;
			}
			entry2.setCaseSensitive(false);
			if (!entry1.isContained(entry2)) {
				showTestError("entry1 is contained in entry2");
				pass = false;
			}
			return pass;
		}	
	}
}
