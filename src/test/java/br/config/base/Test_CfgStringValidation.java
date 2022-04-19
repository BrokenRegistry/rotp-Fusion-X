package test.java.br.config.base;

import java.util.List;

import br.config.Cfg_Entry;
import br.config.Cfg_ValidationData;
import test.java.TestUtil.*;
import test.java.TestUtil.Show.TestLevel;

public class Test_CfgStringValidation extends Abstract_TestList{
	
	@Override public String testName()     { return "Test Cfg String Validation"; }
	@Override public String listName()     { return "A few test"; }
	@Override public TestLevel testLevel() { return TestLevel.CLASS; }
	@Override public TestLevel listLevel() { return TestLevel.LIST; }

	@Override protected void setTestList() {
		testList.add(new createListAndAddElements());
		testList.add(new getOptionAndCategory());
		testList.add(new getValidityAndFromCategory());
	}
	@Override protected void setSuiteList() { 
	}
	
	// ==============================================================
	// private Methods
	private static Cfg_ValidationData newTestList() {
		Cfg_ValidationData testList = null;
		testList = new Cfg_ValidationData();
		testList.addElement("O1", "D1");
		testList.addElement("O2", "D2");
		testList.addElement("O3", "D3", "C1");
		testList.addElement("O4", "D4", "C2");
		testList.addElement("o5", "O5", "D5", "C1");
		testList.addElement("o6", "O6", "D6", "C2");
		return testList;
	}
	// ==============================================================
	// Nested Test Class
	//
	class createListAndAddElements extends Abstract_Test {
		@Override protected String testName() { return "Create list and add elements"; }
		@Override protected void startTest() {
			Cfg_ValidationData testList = null;
			Cfg_Entry entry = new Cfg_Entry("");
			testList = newTestList();
		}
	}
	class getOptionAndCategory extends Abstract_Test {
		@Override protected String testName() { return "get option and category"; }
		@Override protected void startTest() {
			Cfg_ValidationData testList = null;
			Cfg_Entry entry = new Cfg_Entry("");
			testList = newTestList();
			if (!testList.getOption(4).equals("O5")) {
				showTestError("Option index 4 should have been O5");
			}
			entry = new Cfg_Entry("o3");
			if (!testList.getCategory(entry).equals("C1")) {
				showTestError("Category for O3 should have been C1");
			}
			if (!testList.getOption(entry).equals("O3")) {
				showTestError("Option for o3 should have been O3");
			}
		}
	}
	class getValidityAndFromCategory extends Abstract_Test {
		@Override protected String testName() { return "get validity and from category"; }
		@Override protected void startTest() {
			Cfg_ValidationData testList = null;
			Cfg_Entry entry = new Cfg_Entry("");
			testList = newTestList();
			entry = new Cfg_Entry("o6");
			if (!testList.isValid(entry, "C2")) {
				showTestError("Wrong validity returned, should have been C2");
			}
			if (testList.isValid(entry, "C1")) {
				showTestError("Wrong validity returned, should have been C1");
			}
			if (!testList.getOption(entry, "C2").equals("O6")) {
				showTestError("Wrong option returned, should have been O6");
			}
		}
	}
}
