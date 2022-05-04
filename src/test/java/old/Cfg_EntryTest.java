
/**
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

package test.java.old;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.config.Cfg_Entry;

class Cfg_EntryTest {

	@Test
	void template() {
		assertEquals(true, true
				, "should have been: true");
	}
	@Test
	void clone_None() {
		Cfg_Entry cfgOriginal;
		Cfg_Entry cfgClone;
		cfgOriginal = new Cfg_Entry("Source", true);
		assertEquals("Source", cfgOriginal.get()
				, "should have been: «Source»");
		assertEquals(true, cfgOriginal.isCaseSensitive()
				, "should have been: «true»");
		cfgClone = cfgOriginal.clone();
		assertEquals("Source", cfgClone.get()
				, "should have been: «Source»");
		assertEquals(true, cfgClone.isCaseSensitive()
				, "should have been: «true»");
		cfgClone.set("xxx", false);
		assertEquals("xxx", cfgClone.get()
				, "should have been: «xxx»");
		assertEquals(false, cfgClone.isCaseSensitive()
				, "should have been: «false»");
		assertEquals("Source", cfgOriginal.get()
				, "should have been: «Source»");
		assertEquals(true, cfgOriginal.isCaseSensitive()
				, "should have been: «true»");
	}	
	@Test
	void setCaseSensitivity_Boolean() {
		Cfg_Entry cfgEntry;
		cfgEntry = new Cfg_Entry("Source", true);
		assertEquals(true, cfgEntry.isCaseSensitive()
				, "should have been: «true»");
		cfgEntry.setCaseSensitive(false);
		assertEquals(false, cfgEntry.isCaseSensitive()
				, "should have been: «false»");		
		cfgEntry.setCaseSensitive(true);
		assertEquals(true, cfgEntry.isCaseSensitive()
				, "should have been: «true»");
	}
	@Test
	void isCaseSensitive_None() {
		assertEquals(true, new Cfg_Entry("Source", true).isCaseSensitive()
				, "should have been: «true»");
		assertEquals(false, new Cfg_Entry("  source   ", false).isCaseSensitive()
				, "should have been: «false»");		
	}
	@Test
	void get_None() {
		assertEquals("Source", new Cfg_Entry("Source").get()
				, "should have been: «Source»");
		assertEquals("source", new Cfg_Entry("  source   ").get()
				, "should have been: «source»");		
	}
	@Test
	void toComment_None() {
		assertEquals("# Source", new Cfg_Entry("Source").toComment()
				, "should have been: «# Source»");
		assertEquals("# source", new Cfg_Entry("  source   ").toComment()
				, "should have been: «# source»");		
	}
	@Test
	void toString_None() {
		assertEquals("Source", new Cfg_Entry("Source").toString()
				, "should have been: «Source»");
		assertEquals("source", new Cfg_Entry("  source   ").toString()
				, "should have been: «source»");		
	}
	@Test
	void toPrint_None() {
		assertEquals("Source", new Cfg_Entry("Source").toPrint()
				, "should have been: «Source»");
		assertEquals("source", new Cfg_Entry("  source   ").toPrint()
				, "should have been: «source»");		
	}
	@Test
	void toTest_None() {
		assertEquals("Source", new Cfg_Entry("Source", true).toTest()
				, "should have been: «Source»");
		assertEquals("source", new Cfg_Entry("  source   ", true).toTest()
				, "should have been: «source»");		
		assertEquals("SOURCE", new Cfg_Entry("Source", false).toTest()
				, "should have been: «SOURCE»");
		assertEquals("SOURCE", new Cfg_Entry("  source   ", false).toTest()
				, "should have been: «SOURCE»");		
	}
	@Test
	void toKey_None() {
		assertEquals("SOURCE", new Cfg_Entry("Source").toKey()
				, "should have been: «SOURCE»");
		assertEquals("SOURCE", new Cfg_Entry("  source   ").toKey()
				, "should have been: «SOURCE»");		
	}
	@Test
	void toCapitalized_Boolean() {
		assertEquals("Very option source", new Cfg_Entry("VERY OPTION SOURCE").toCapitalized(true)
				, "should have been: «Very option source»");
		assertEquals("Very Option Source", new Cfg_Entry("VERY OPTION SOURCE").toCapitalized(false)
				, "should have been: «Very Option Source»");		
	}
	@Test
	void toCapitalized_String() {
		assertEquals("Source", new Cfg_Entry("Source").toCapitalized()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("source").toCapitalized()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("SOURCE").toCapitalized()
				, "should have been «Source»");
		assertEquals("Option Source", new Cfg_Entry("OPTION SOURCE").toCapitalized()
				, "should have been: «Option Source»");
		assertEquals("Very Option Source", new Cfg_Entry("VERY OPTION SOURCE").toCapitalized()
				, "should have been: «Very Option Source»");
		assertEquals("Option  Source", new Cfg_Entry("OPTION  SOURCE").toCapitalized()
				, "should have been: «Option  Source»");
		assertEquals("Source", new Cfg_Entry(" SOURCE").toCapitalized()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("  SOURCE  ").toCapitalized()
				, "should have been «Source»");
		assertEquals("", new Cfg_Entry("    ").toCapitalized()
				, "should have been «»");
	}
	@Test
	void toSentence_String() {
		assertEquals("Source", new Cfg_Entry("Source").toSentence()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("source").toSentence()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("SOURCE").toSentence()
				, "should have been «Source»");
		assertEquals("Option source", new Cfg_Entry("OPTION SOURCE").toSentence()
				, "should have been: «Option source»");
		assertEquals("Very option source", new Cfg_Entry("VERY OPTION SOURCE").toSentence()
				, "should have been: «Very option source»");
		assertEquals("Option  source", new Cfg_Entry("OPTION  SOURCE").toSentence()
				, "should have been: «Option  source»");
		assertEquals("Source", new Cfg_Entry(" SOURCE").toSentence()
				, "should have been «Source»");
		assertEquals("Source", new Cfg_Entry("  SOURCE  ").toSentence()
				, "should have been «Source»");
		assertEquals("", new Cfg_Entry("    ").toSentence()
				, "should have been «»");
	}
	@Test
	void getOrDefault_String() {
		assertEquals("", new Cfg_Entry(" ").getOrDefault("")
				, "should have been: «»");
		assertEquals("x", new Cfg_Entry(" ").getOrDefault("x")
				, "should have been: «x»");
		assertEquals("x", new Cfg_Entry("").getOrDefault("x")
				, "should have been: «x»");
		assertEquals("abc", new Cfg_Entry("abc").getOrDefault("x")
				, "should have been: «abc»");
		assertEquals("xxx", new Cfg_Entry(" xxx ").getOrDefault("x")
				, "should have been: «xxx»");
		assertEquals((String)null, new Cfg_Entry(" ").getOrDefault((String)null)
				, "should have been: «null»");
	}	
	@Test
	void isEmpty_None() {
		assertEquals(true, new Cfg_Entry("").isEmpty()
				, "should have been: «true»");
		assertEquals(true, new Cfg_Entry("  ").isEmpty()
				, "should have been: «false»"); // Cleaned by the constructor
		assertEquals(false, new Cfg_Entry(" x ").isEmpty()
				, "should have been: «false»");
	}
	@Test
	void isBlank_None() {
		assertEquals(true, new Cfg_Entry("").isBlank()
				, "should have been: «true»");
		assertEquals(true, new Cfg_Entry("  ").isBlank()
				, "should have been: «true»");
		assertEquals(false, new Cfg_Entry(" x ").isBlank()
				, "should have been: «false»");
	}
	@Test
	void equals_String() {
		assertEquals(true, new Cfg_Entry("abc", false).equals("abc")
					, "should have been: «true»");
		assertEquals(true, new Cfg_Entry("abc", true).equals("abc")
					, "should have been: «true»");
		assertEquals(true, new Cfg_Entry("ABC", false).equals("abc")
					, "should have been: «true»");
		assertEquals(false, new Cfg_Entry("ABC", true).equals("abc")
					, "should have been: «false»");
		assertEquals(false,  new Cfg_Entry("ABC", false).equals((String)null)
					, "should have been: «false»");
	}	
	@Test
	void equals_CfgEntry() {
		assertEquals(true,  new Cfg_Entry("abc", false)
					.equals(new Cfg_Entry("abc", false))
					, "should have been: «true»");
		assertEquals(true,  new Cfg_Entry("abc", true)
					.equals(new Cfg_Entry("abc", true))
					, "should have been: «true»");
		assertEquals(true,  new Cfg_Entry("ABC", false)
					.equals(new Cfg_Entry("abc", false))
					, "should have been: «true»");
		assertEquals(true,  new Cfg_Entry("ABC", true)
					.equals(new Cfg_Entry("abc", false))
					, "should have been: «true»");
		assertEquals(true,  new Cfg_Entry("ABC", false)
					.equals(new Cfg_Entry("abc", true))
					, "should have been: «true»");
		assertEquals(false, new Cfg_Entry("ABC", true)
					.equals(new Cfg_Entry("abc", true))
					, "should have been: «false»");
		assertEquals(false, new Cfg_Entry("ABC", true)
					.equals((Cfg_Entry)null)
					, "should have been: «false»");
	}	
	@Test
	void contains_String() {
		assertEquals(true, new Cfg_Entry("ABCD", false).contains("abc")
				, "should have been: «true»");
		assertEquals(false, new Cfg_Entry("AB", false).contains("abc")
				, "should have been: «false»");
		assertEquals(false, new Cfg_Entry("ABCD", true).contains("abc")
				, "should have been: «false»");
		assertEquals(true, new Cfg_Entry("abcd", true).contains("abc")
				, "should have been: «true»");
		assertEquals(true, new Cfg_Entry("abc", true).contains((String)null)
				, "should have been: «true»");
	}
	@Test
	void contains_CfgEntry() {
		Cfg_Entry cfgEntry;
		Cfg_Entry container;
		cfgEntry  = new Cfg_Entry("abc", false);
		container = new Cfg_Entry("ABCD", false);
		assertEquals(true, container.contains(cfgEntry)
				, "should have been: «true»");
		container.setCaseSensitive(true);
		assertEquals(true, container.contains(cfgEntry)
				, "should have been: «true»");
		cfgEntry.setCaseSensitive(true);
		assertEquals(false, container.contains(cfgEntry)
				, "should have been: «false»");
		container.setCaseSensitive(false);
		assertEquals(true, container.contains(cfgEntry)
				, "should have been: «true»");
		cfgEntry= null;
		assertEquals(true, container.contains(cfgEntry)
				, "should have been: «true»");
	}	
	@Test
	void isContained_String() {
		Cfg_Entry cfgEntry;
		cfgEntry  = new Cfg_Entry("abc", false);
		assertEquals(true, cfgEntry.isContained("ABCD")
				, "should have been: «true»");
		assertEquals(false, cfgEntry.isContained("AB")
				, "should have been: «false»");
		cfgEntry.setCaseSensitive(true);
		assertEquals(false, cfgEntry.isContained("ABCD")
				, "should have been: «false»");
		assertEquals(true, cfgEntry.isContained("abcd")
				, "should have been: «true»");
		assertEquals(false, cfgEntry.isContained((String)null)
				, "should have been: «false»");
	}
	@Test
	void isContained_CfgEntry() {
		Cfg_Entry cfgEntry;
		Cfg_Entry container;
		cfgEntry  = new Cfg_Entry("abc", false);
		container = new Cfg_Entry("ABCD", false);
		assertEquals(true, cfgEntry.isContained(container)
				, "should have been: «true»");
		container.setCaseSensitive(true);
		assertEquals(true, cfgEntry.isContained(container)
				, "should have been: «true»");
		cfgEntry.setCaseSensitive(true);
		assertEquals(false, cfgEntry.isContained(container)
				, "should have been: «false»");
		container.setCaseSensitive(false);
		assertEquals(true, cfgEntry.isContained(container)
				, "should have been: «true»");
		container= null;
		assertEquals(false, cfgEntry.isContained(container)
				, "should have been: «false»");
	}
	@Test
	void testForNumeric_None() {
		Cfg_Entry cfgEntry;
		cfgEntry = new Cfg_Entry("123");
		assertEquals(true, cfgEntry.testForNumeric()
				, "should have been: «true»");
		cfgEntry = new Cfg_Entry("Test ");
		assertEquals(false, cfgEntry.testForNumeric()
				, "should have been: «false»");
		cfgEntry = new Cfg_Entry(" 0155.3 ");
		assertEquals(true, cfgEntry.testForNumeric()
				, "should have been: «true»");
	}
	@Test
	void removeComment_None() {
		Cfg_Entry cfgEntry;
		cfgEntry = new Cfg_Entry("Test # Comment");
		assertEquals("Test # Comment", cfgEntry.get()
				, "should have been: «Test # Comment»");
		assertEquals("Test", cfgEntry.removeComment().get()
				, "should have been: «Test»");
		assertEquals("Test", cfgEntry.get()
				, "should have been: «Test»");
		cfgEntry = new Cfg_Entry("Test ");
		assertEquals("Test", cfgEntry.removeComment().get()
				, "should have been: «Test»");
		cfgEntry = new Cfg_Entry("# Comment");
		assertEquals("", cfgEntry.removeComment().get()
				, "should have been: «»");
	}
}
