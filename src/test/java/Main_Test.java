
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
import test.java.TestUtil.ConsoleColors;
import test.java.TestUtil.Show;
import test.java.TestUtil.Show.*;
import test.java.br.config.*;
import test.java.br.config.base.*;

public class Main_Test{
	// ====================================================
	// Test List Declaration
	//
	private static Abstract_TestList cfgComment;
	private static Abstract_TestList cfgUtil;
	private static Abstract_TestList cfgEntry;
	private static Abstract_TestList cfgValue;
	private static Abstract_TestList numeric;
	private static Abstract_TestList cfgStringValidation;
	private static Abstract_TestList cfgEntryValidation;
	private static Abstract_TestList cfgValueValidation;
	private static Abstract_TestList cfgLineValidation;
	private static Abstract_TestList cfgBlockValidation;
	private static Abstract_TestList cfgSetting;
	private static Abstract_TestList cfgGroup;
	private static Abstract_TestList cfgFile;
	
	private static Abstract_TestList cfgListLow; 

	static void main(String[] args) {
		// Init Verbose
		Show.infoMap.put(InfoLevel.Error,   TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Warning, TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Final,   TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Result,  TestLevel.TEST);
		Show.infoMap.put(InfoLevel.Start,   TestLevel.CLASS);
		Show.infoMap.put(InfoLevel.Comment, TestLevel.ALL);
		Show.infoMap.put(InfoLevel.Info,    TestLevel.ALL);
		
		cfgListLow = new Test_CfgListLow();
		cfgListLow.startMainTest("==== MAIN TEST =====");
		System.out.println(ConsoleColors.GREEN + "===== color test 1" + ConsoleColors.RESET);	
		System.out.println("✔❌⭕🚫🚫✅🆗🔴🟩🔷===== color test 2");
		System.out.println("===== color test 3");
//		System.out.println("===== showFinalMessage(1)");
//		cfgListLow.showFinalMessage(1);
//		System.out.println();
//		System.out.println("===== showFinalMessage(0)");
//		cfgListLow.showFinalMessage(0);
//		System.out.println("===== showFinalMessage(TestLevel.CLASS)");
//		cfgListLow.showFinalMessage(TestLevel.CLASS);

//		cfgEntry = new Test_CfgEntry();
//		cfgEntry.startAllTests();
//
//		cfgStringValidation = new Test_CfgStringValidation();
//		cfgStringValidation.startAllTests();

		// Show Final Result
//		System.out.println();
//		cfgEntry.showFinalMessage();
//		Show.showFinalResult("Main Test");
		
	}
}
