
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

package test.java.br.config.base;

import java.util.List;

import br.config.Cfg_Entry;
import test.java.TestUtil.Abstract_TestList;
import test.java.TestUtil.Abstract_Test;
import test.java.TestUtil.Show.TestLevel;

public class Test_CfgEntry extends Abstract_TestList{

	@Override public String testName()     { return "Cfg Entry"; }
	@Override public String listName()     { return "String Methods"; }
	@Override public TestLevel testLevel() { return TestLevel.CLASS; }
	@Override public TestLevel listLevel() { return TestLevel.LIST; }

	@Override protected void setTestList() {
		testList.add(new contains());
		testList.add(new isContained());
	}
	@Override protected void setSuiteList() { 
	}
	
	// ==============================================================
	// Nested Test Class
	//
	class contains extends Abstract_Test{
		@Override protected String testName() { return "Contains"; }

		@Override protected void startTest() {
			Boolean pass = true;
			Cfg_Entry entry1 = new Cfg_Entry("ABC", true);
			Cfg_Entry entry2 = new Cfg_Entry("abcd", true);
			if (entry2.contains(entry1)) {
				showTestError("entry2 does not contains entry1");
			}
			entry2.setCaseSensitive(false);
			if (!entry2.contains(entry1)) {
				showTestError("entry2 does contains entry1");
			}
		}
	}
	// ==============================================================
	class isContained extends Abstract_Test{
		@Override protected String testName() { return "Is Contained"; }

		@Override protected void startTest() {
			Cfg_Entry entry1 = new Cfg_Entry("ABC", true);
			Cfg_Entry entry2 = new Cfg_Entry("abcd", true);
			if (entry1.isContained(entry2)) {
				showTestError("entry1 is not contained in entry2");
			}
			entry2.setCaseSensitive(false);
			if (!entry1.isContained(entry2)) {
				showTestError("entry1 is contained in entry2");
			}
		}	
	}
}
