
/*
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.java.TestUtil;

import java.util.ArrayList;
import java.util.List;

import test.java.TestUtil.Show.*;

public abstract class Abstract_TestList{

	private final InfoLevel START  = InfoLevel.Start;
	private final InfoLevel RESULT = InfoLevel.Result;
	private final InfoLevel FINAL  = InfoLevel.Final;
	private Boolean testResult;
	private String  mainName = "";

	protected List<Abstract_Test>     testList  = new ArrayList<Abstract_Test>();
	protected List<Abstract_TestList> suiteList = new ArrayList<Abstract_TestList>();
	//===============================================================
	// Constructor
	//
	protected Abstract_TestList () {
		setSuiteList();
		setTestList();
	}
	//===============================================================
	// Public Methods
	//
	/**
	 * Display the Final Result on the console
	 * @return the test Result
	 */
	public Boolean showFinalMessage () {
		Show.message(FINAL, testLevel(), testName(), testResult);
		return testResult;
	}	
	/**
	 * Display the Final Result on the console
	 * @param subLevel the number of subLevel to be shown
	 * @return the test Result
	 */
	public Boolean showFinalMessage (int subLevel) {
		if (subLevel > 0) {
			for (Abstract_TestList test : suiteList) {
				test.showFinalMessage(subLevel - 1);
			}	
		}
		Show.message(FINAL, testLevel(), testName(), testResult);
		return testResult;
	}
	/**
	 * Display the Final Result on the console
	 * @param level the level of sublist to be displayed
	 * @return the test Result
	 */
	public Boolean showFinalMessage (TestLevel level) {
		for (Abstract_Test test : testList) {
			test.showFinalMessage(level);
		}
		for (Abstract_TestList test : suiteList) {
			test.showFinalMessage(level);
		}
		if (testLevel().compareTo(level) <= 0) {
			Show.message(FINAL, testLevel(), testName(), testResult);
		}
		return testResult;
	}
	/**
	 * get the final result
	 * @return testResult the {@code Boolean} value
	 */
	public Boolean getFinalTestResult() {
		return testResult;
	}
	/**
	 * Start all test in this list and sub list
	 * Then Display final Results
	 * @param name Test description
	 * @return testResult the {@code Boolean} value
	 */
	public Boolean startMainTest(String name) {
		mainName = name;
		Show.message(START, TestLevel.FINAL, mainName);
		startAllTests();
		System.out.println("==================================================");
		Show.message(FINAL, TestLevel.FINAL, mainName, testResult);		
		return testResult;
	}
	/**
	 * Start all test in this list and sub list
	 * @return testResult the {@code Boolean} value
	 */
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
	protected abstract void setTestList();
	protected abstract void setSuiteList();

	protected Boolean startTestList() {
		Boolean pass = true;
		Show.message(START, listLevel(), listFullName());
		for (Abstract_Test test : testList) {
			pass &= test.startLocalTest(listFullName());
		}
		Show.message(RESULT, listLevel(), listFullName(), pass);
		return pass;
	}	
	protected Boolean startSuiteList() {
		Boolean pass = true;
		for (Abstract_TestList test : suiteList) {
			pass &= test.startAllTests();
		}
		Show.message(RESULT, testLevel(), testName(), pass);
		return pass;
	}
	private String listFullName() {
		return testName() + " # " + listName();
	}
}
