package test.java.TestUtil;

import java.util.ArrayList;
import java.util.List;

import test.java.TestUtil.Show.InfoLevel;

public abstract class Abstract_TestList{

	private final InfoLevel START  = InfoLevel.Start;
	private final InfoLevel RESULT = InfoLevel.Result;
	private final InfoLevel FINAL  = InfoLevel.Final;
	private Boolean testResult = null;

	protected List<Abstract_Test>     testList  = new ArrayList<Abstract_Test>();
	protected List<Abstract_TestList> suiteList = new ArrayList<Abstract_TestList>();

	//===============================================================
	// Public Methods
	//
	public Boolean showFinalMessage () {
		Show.message(FINAL, testLevel(), testName(), testResult);
		return testResult;
	}
	public Boolean getFinalTestResult() {
		return testResult;
	}
	public Boolean startAllTests() {
		Show.message(START, testLevel(), testName());
		testResult =  startTestList();
		testResult &= startSuiteList();
		return testResult;
	}	
	public abstract String testName();
	public abstract String listName();
	public abstract Show.TestLevel testLevel(); // also SuiteLevel
	//===============================================================
	// Protected Methods
	//
	protected abstract Show.TestLevel listLevel();
	protected abstract List<Abstract_Test>  getTestList();
	protected abstract List<Abstract_TestList> getSuiteList();

	protected Boolean startTestList() {
		Boolean pass = true;
		Show.message(START, listLevel(), listName());
		for (Abstract_Test test : getTestList()) {
			pass &= test.startLocalTest();
		}
		Show.message(RESULT, listLevel(), listName(), pass);
		return pass;
	}	
	protected Boolean startSuiteList() {
		Boolean pass = true;
		for (Abstract_TestList test : getSuiteList()) {
			pass &= test.startAllTests();
		}
		Show.message(RESULT, testLevel(), testName(), pass);
		return pass;
	}
}
