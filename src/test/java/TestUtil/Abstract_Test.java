
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

import test.java.TestUtil.Show.*;

public abstract class Abstract_Test{
	
	private final InfoLevel START  = InfoLevel.Start;
	private final InfoLevel RESULT = InfoLevel.Result;
	private final InfoLevel ERROR  = InfoLevel.Error;
	private final InfoLevel FINAL  = InfoLevel.Final;
	
	private TestLevel testLevel = TestLevel.TEST;
	private Boolean   pass;
	private String    testfullName;
	
	protected abstract void startTest();
	protected abstract String testName();
	
	protected Boolean startLocalTest(String parentName) {
		testfullName = parentName + " # " + testName();
		pass = true;
		Show.message(START, testLevel, testName());
		try {
			startTest();
		} 
		catch(Exception e) { 
			showTestError(e); 
			pass = false;
		}
		Show.message(RESULT, testLevel, testfullName, pass);
		return pass;
	}
	protected void showTestError (Object err) {
		Show.message (ERROR, testLevel, testfullName, err.toString());
		pass = false;
	}
	// Not abstract function!
	// this level is most always the default one
	// Do the change if needed
	protected void setTestLevel (TestLevel level) {
		testLevel = level;
	}
	public Boolean showFinalMessage (TestLevel level) {
		if (testLevel.compareTo(level) <= 0) {
			Show.message(FINAL, testLevel, testName(), pass);
		}
		return pass;
	}
	
}
