
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

package test.java;

import test.java.TestUtil.Abstract_TestList;
import test.java.TestUtil.Show;
import test.java.TestUtil.Show.InfoLevel;
import test.java.TestUtil.Show.TestLevel;

public class Main_Test{
	// ====================================================
	// Test List Declaration
	//
	private static Abstract_TestList cfgEntry;

	public static void main(String[] args) {
		// Init Verbose
		Show.infoMap.put(InfoLevel.Error,   TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Warning, TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Final,   TestLevel.FINAL);
		Show.infoMap.put(InfoLevel.Result,  TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Start,   TestLevel.CLASS);
		Show.infoMap.put(InfoLevel.Comment, TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Info,    TestLevel.ALL);
		
		cfgEntry = new Test_CfgEntry();
		cfgEntry.startAllTests();
//		cfgUtil  = TestCfgUtil(sCfgUtil);
//		comment  = TestComment(sComment); // Comment & CommentLine
//		numeric  = TestNumeric(sNumeric);
//		cfgStringValidation = TestCfgStringValidation(sCfgStringValidation);
//		cfgEntryValidation  = TestCfgEntryValidation (sCfgEntryValidation);
//		cfgLineValidation   = TestCfgLineValidation  (sCfgEntryValidation);

		// Show Final Result
		System.out.println();
		cfgEntry.showFinalMessage();
		Show.showFinalResult("Main Test");
	}
}
