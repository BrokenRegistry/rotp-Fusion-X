package test.java.TestUtil;

import test.java.TestUtil.Show.InfoLevel;
import test.java.TestUtil.Show.TestLevel;

public abstract class Abstract_Test{
	
	private final InfoLevel START  = InfoLevel.Start;
	private final InfoLevel RESULT = InfoLevel.Result;
	private final InfoLevel ERROR  = InfoLevel.Error;
	
	private Show.TestLevel testLevel = Show.TestLevel.TEST;
	
	protected abstract Boolean startTest();
	protected abstract String testName();
	
	Boolean startLocalTest() {
		Boolean pass = true;
		Show.message(START, testLevel, testName());
		try {
			pass &= startTest();
		} 
		catch(Exception e) { 
			showTestError(e); 
			pass = false;
		}
		Show.message(RESULT, testLevel, testName(), pass);
		return pass;
	}

	protected void showTestError (Object err) {
		Show.message (ERROR, testLevel, testName(), err.toString());
	}
	protected void setTestLevel (TestLevel level) {
		testLevel = level;
	}
}
