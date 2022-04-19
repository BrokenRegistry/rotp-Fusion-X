
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

package test.java.br.config;

import test.java.TestUtil.*;
import test.java.TestUtil.Show.TestLevel;
import test.java.br.config.base.*;

public class Test_CfgListLow extends Abstract_TestList{

	@Override public String testName()     { return "Cfg List Low"; }
	@Override public String listName()     { return "String Methods"; }
	@Override public TestLevel testLevel() { return TestLevel.CLASS; }
	@Override public TestLevel listLevel() { return TestLevel.LIST; }

	@Override protected void setTestList() {
		// testList.add(new Test_xxx());
	}
	@Override protected void setSuiteList() { 
//		suiteList.add(new Test_CfgComment());
//		suiteList.add(new Test_CfgUtil());
		suiteList.add(new Test_CfgEntry());
//		suiteList.add(new Test_CfgValue());
//		suiteList.add(new Test_Numeric());
		suiteList.add(new Test_CfgStringValidation());
//		suiteList.add(new Test_CfgEntryValidation());
//		suiteList.add(new Test_CfgValueValidation());
//		suiteList.add(new Test_CfgBlockValidation());
//		suiteList.add(new Test_CfgSetting());
//		suiteList.add(new Test_CfgGroup());
//		suiteList.add(new Test_CfgFile());
	}
	
	// ==============================================================
	// Nested Test Class
	//

}
