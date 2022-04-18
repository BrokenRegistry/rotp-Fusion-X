
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

import java.util.List;

import br.config.Cfg_Entry;
import br.config.Cfg_ValidationData;
import br.config.Comment;
import br.config.CommentLine;
import br.config.Numeric;

public class Cfg_Tests {

//	private static final Integer SILENT      = 0;
//	private static final Integer GLOBAL_FAIL = 10;
//	private static final Integer LOCAL_FAIL  = 20;
//	private static final Integer GLOBAL_INFO = 30;
//	private static final Integer LOCAL_INFO  = 40;
//	private static Integer verbose    = LOCAL_FAIL;
//	private static String  globalTest = "";
//	private static String  localTest  = "";
//	private static Boolean globalPass = true;
//	private static Boolean localPass  = true;

	private static Boolean comment = null;
	private static String sComment = "Comment & CommentLine";
	private static Boolean numeric = null;
	private static String sNumeric = "Numeric";
	private static Boolean cfgStringValidation = null;
	private static String sCfgStringValidation = "CfgStringValidation";
	private static Boolean cfgEntryValidation  = null;
	private static String sCfgEntryValidation  = "CfgEntryValidation";
	private static Boolean cfgLineValidation   = null;
	private static String sCfgLineValidation   = "CfgLineValidation";
	private static Boolean cfgEntry = null;
	private static String sCfgEntry = "CfgEntry";
//	private static Boolean cfgUtil  = null;
//	private static String sCfgUtil  = "CfgUtil";

//	public static void main(String[] args) {
//		allTest();
//	}

//	static void allTest() {
//		cfgEntry = TestCfgEntry(sCfgEntry);
////		cfgUtil  = TestCfgUtil(sCfgUtil);
//		comment  = TestComment(sComment); // Comment & CommentLine
//		numeric  = TestNumeric(sNumeric);
//		cfgStringValidation = TestCfgStringValidation(sCfgStringValidation);
////		cfgEntryValidation  = TestCfgEntryValidation (sCfgEntryValidation);
//		cfgLineValidation   = TestCfgLineValidation  (sCfgEntryValidation);
//
//		// Show Final Result
//		System.out.println();
//		System.out.println(sCfgEntry + ": "  + boolToString(cfgEntry));
////		System.out.println(sCfgUtil  + ": "  + boolToString(cfgUtil));
//		System.out.println(sComment + ": " + boolToString(comment));
//		System.out.println(sNumeric + ": " + boolToString(numeric));
//		System.out.println(sCfgStringValidation + ": " + boolToString(cfgStringValidation));
//		System.out.println(sCfgEntryValidation  + ": " + boolToString(cfgEntryValidation));
//		System.out.println(sCfgLineValidation   + ": " + boolToString(cfgLineValidation));
//		System.out.println();
//		System.out.println("==> Final Test status: " + boolToString(globalPass));		
//	}
//	// ==== private Methods
//	private static Cfg_ValidationData getTestList() {
//		Cfg_ValidationData testList = null;
//		testList = new Cfg_ValidationData();
//		testList.addElement("O1", "D1");
//		testList.addElement("O2", "D2");
//		testList.addElement("O3", "D3", "C1");
//		testList.addElement("O4", "D4", "C2");
//		testList.addElement("o5", "O5", "D5", "C1");
//		testList.addElement("o6", "O6", "D6", "C2");
//		return testList;
//	}
//	// ==== Tests
//	public static Boolean TestComment(String description) {
//		showGlobalTestStart (description);
//		Comment comment;
//		newTest("new Empty Comment + isEmpty + toPrint");
//		try {
//			comment = new Comment();
//			if (!comment.isEmpty()) {
//				showTestError("comment should have been empty");
//			}
//			String prt = comment.toPrint();
//			if (!prt.equals("# ")) {
//				showTestError("toPrint should have been # ");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("new String Comment + isEmpty + toPrint");
//		try {
//			comment = new Comment("Test");
//			if (comment.isEmpty()) {
//				showTestError("comment should not have been empty");
//			}
//			String prt = comment.toPrint();
//			if (!prt.equals("# Test")) {
//				showTestError("toPrint should have been # Test");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("new CfgField Comment + isEmpty + toPrint");
//		try {
//			comment = new Comment(new Cfg_Entry("Test"));
//			if (comment.isEmpty()) {
//				showTestError("comment should not have been empty");
//			}
//			String prt = comment.toPrint();
//			if (!prt.equals("# Test")) {
//				showTestError("toPrint should have been # Test");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("new List Comment");
//		try {
//			comment = new Comment(List.of("Test1", "Test2"));
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("is Comment");
//		try {
//			if (Comment.isComment("Test")) {
//				showTestError("Should not have been seen as comment");
//			}
//			if (!Comment.isComment(" # Test")) {
//				showTestError("Should have been seen as comment");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment CfgEntry");
//		try {
//			Cfg_Entry entry = new Cfg_Entry("Label : Value  # Comment  ");
//			CommentLine remark = CommentLine.splitComment(entry);
//			if (!remark.toPrint().equals("# Comment")) {
//				showTestError("Returned comment should have been \"# Comment\"");
//			}
//			if (!entry.toString().equals("Label : Value")) {
//				showTestError("Entry Should have been \\\"Label : Value\\\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment String");
//		try {
//			String entry = "Label : Value  # Comment  ";
//			String[] result = CommentLine.splitComment(entry);
//			if (!result[1].equals("Comment")) {
//				showTestError("Returned comment should have been \"Comment\"");
//			}
//			if (!result[0].equals("Label : Value")) {
//				showTestError("Entry Should have been \\\"Label : Value\\\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment no comment CfgEntry");
//		try {
//			Cfg_Entry entry = new Cfg_Entry("Label : Value   Comment  ");
//			CommentLine remark = CommentLine.splitComment(entry);
//			if (remark != null) {
//				showTestError("Returned comment should have been null");
//			}
//			if (!entry.toString().equals("Label : Value   Comment")) {
//				showTestError("Entry Should have been \"Label : Value   Comment\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment no comment String");
//		try {
//			String entry = "Label : Value   Comment  ";
//			String[] result = CommentLine.splitComment(entry);
//			if (!result[1].equals("")) {
//				showTestError("Returned comment should have been \"\"");
//			}
//			if (!result[0].equals("Label : Value   Comment")) {
//				showTestError("Entry Should have been \"Label : Value   Comment\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment only comment CfgEntry");
//		try {
//			Cfg_Entry entry = new Cfg_Entry("# Label : Value  # Comment  ");
//			CommentLine remark = CommentLine.splitComment(entry);
//			if (!remark.toPrint().equals("# Label : Value  # Comment")) {
//				showTestError("Returned comment should have been \"# Label : Value  # Comment\"");
//			}
//			if (!entry.toString().equals("")) {
//				showTestError("Entry Should have been \"\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Split Comment only comment String");
//		try {
//			String entry = "# Label : Value  # Comment  ";
//			String[] result = CommentLine.splitComment(entry);
//			if (!result[1].equals("Label : Value  # Comment")) {
//				showTestError("Returned comment should have been \"Label : Value  # Comment\"");
//			}
//			if (!result[0].equals("")) {
//				showTestError("Entry Should have been \"\"");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		showGlobalTestResult();
//		return localPass;
//	}
//	public static Boolean TestNumeric(String description) {
//		showGlobalTestStart (description);
//		Numeric value;
//		Double dbl = null;
//		Long   lg  = null;
//		newTest("new random");
//		try {
//			value = Numeric.nextRandomNumeric(0, 10);
//			if (value.toInteger() > 10 || value.toDouble() < 0 ) {
//				showTestError("Value should have been between 0 and 10");
//			}
//			value = Numeric.nextRandomNumeric(new Numeric(0.0), new Numeric(100));
//			if (value.toInteger() > 100 || value.toDouble() < 0 ) {
//				showTestError("Value should have been between 0 and 100");
//			}
//		} catch(Exception e) { showTestError(e);}
//
//		newTest("Overflow");
//		try {
//			value = new Numeric(Long.MAX_VALUE);
//			if (value.toInteger() != null) {
//				showTestError("Integer Value should have been null");
//			}
//			if (value.toLong() != Long.MAX_VALUE) {
//				showTestError("Long Value should have been Long.MAX_VALUE");
//			}
//		} catch(Exception e) { showTestError(e);}
//
//		newTest("To Double No error");
//		try {
//			dbl = Numeric.toDouble(" -01.2e-03 ");
//			if (dbl == null ) {
//				showTestError("result should not be null");
//			}
//			else if (dbl != -0.0012) {
//				showTestError("result should be -0.0012");
//			}
//		} catch(Exception e) { showTestError(e);}
//
//		newTest("To Long No error");
//		try {
//			lg = Numeric.toLong("-125");
//			if (lg == null ) {
//				showTestError("result should not be null");
//			}
//			else if (lg != -125) {
//				showTestError("result should be -125");
//			}
//		} catch(Exception e) { showTestError(e);}
//
//		showGlobalTestResult();
//		return localPass;
//	}
//	public static Boolean TestCfgStringValidation(String description) {
//		showGlobalTestStart (description);
//		Cfg_ValidationData testList = null;
//		Cfg_Entry entry = new Cfg_Entry("");
//		newTest("Create list and add elements");
//		try {
//			testList = getTestList();
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("get length");
//		try {
//			if (testList.lastIndex() != 5) {
//				showTestError("testList lastIndex should be 5");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("get option and category");
//		try {
//			if (!testList.getOption(4).equals("O5")) {
//				showTestError("Option index 4 should have been O5");
//			}
//			entry = new Cfg_Entry("o3");
//			if (!testList.getCategory(entry).equals("C1")) {
//				showTestError("Category for O3 should have been C1");
//			}
//			if (!testList.getOption(entry).equals("O3")) {
//				showTestError("Option for o3 should have been O3");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("get validity and from category");
//		try {
//			entry = new Cfg_Entry("o6");
//			if (!testList.isValid(entry, "C2")) {
//				showTestError("Wrong validity returned, should have been C2");
//			}
//			if (testList.isValid(entry, "C1")) {
//				showTestError("Wrong validity returned, should have been C1");
//			}
//			if (!testList.getOption(entry, "C2").equals("O6")) {
//				showTestError("Wrong option returned, should have been O6");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		showGlobalTestResult();
//		return localPass;
//	}
//
//	public static Boolean TestCfgEntryValidation(String description) {
//		showGlobalTestStart (description);
//		Cfg_ValidationData testListBase   = null;
//		Cfg_ValidationData testListLimits = null;
//		Cfg_ValidationData testListRandom = null;
//		Cfg_ValidationData testListNum    = null;
//		Cfg_ValidationData testListNumRnd = null;
//		Cfg_EntryValidation entryBase   = null;
//		Cfg_EntryValidation entryLimits = null;
//		Cfg_EntryValidation entryRandom = null;
//		Cfg_EntryValidation numericEntry  = null;
//		Cfg_EntryValidation numericRandom = null;
//		Integer min  = 0;
//		Integer max  = 100;
//		Integer rMin = 20;
//		Integer rMax = 50;
//		Numeric minN = null;
//		Numeric maxN = null;
//		Numeric rMinN = null;
//		Numeric rMaxN = null;
//
//		newTest("Create variables (Test Constructors)");
//		try {
//			testListBase   = getTestList();
//			testListLimits = getTestList();
//			testListRandom = getTestList();
//			testListNum    = new Cfg_ValidationData();
//			testListNumRnd = new Cfg_ValidationData();
//			minN = new Numeric(min);
//			maxN = new Numeric(max);
//			rMinN = new Numeric(rMin);
//			rMaxN = new Numeric(rMax);
//			testListLimits.setLimits(minN, maxN);
//			testListRandom.setLimits(minN, maxN);
//			testListNum.setLimits(minN, maxN);
//			testListNumRnd.setLimits(minN, maxN);
//			testListRandom.setDefaultRandomLimits(rMinN, rMaxN);
//			testListNumRnd.setDefaultRandomLimits(rMinN, rMaxN);
//
//			entryBase   = new Cfg_EntryValidation(testListBase);
//			entryLimits = new Cfg_EntryValidation(testListLimits);
//			entryRandom = new Cfg_EntryValidation(testListRandom);
//			numericEntry  = new Cfg_EntryValidation(testListNum);
//			numericRandom = new Cfg_EntryValidation(testListNumRnd);
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Valid Entry");
//		try {
//			entryBase.setEntry(new Cfg_Entry("o5"));
//			if (!entryBase.getStringOption().equals("O5")) {
//				showTestError("entryBase returned wrong String Option, should have been O5");
//			}
//			entryLimits.setEntry(new Cfg_Entry("o4"));
//			if (!entryLimits.getStringOption().equals("O4")) {
//				showTestError("entryLimits returned wrong String Option, should have been O4");
//			}
//			entryRandom.setEntry(new Cfg_Entry("o2"));
//			if (!entryRandom.getStringOption().equals("O2")) {
//				showTestError("entryRandom returned wrong String Option, should have been O2");
//			}
//			numericEntry.setEntry(new Cfg_Entry("5"));
//			if (!numericEntry.getStringOption().equals("5")) {
//				showTestError("numericEntry returned wrong String Option, should have been 5");
//			}
//			if (numericEntry.getNumericOption().toInteger() != 5) {
//				showTestError("numericEntry returned wrong Numeric Option, should have been 5");
//			}
//			numericRandom.setEntry(new Cfg_Entry("7"));
//			if (!numericRandom.getStringOption().equals("7")) {
//				showTestError("numericRandom returned wrong String Option, should have been 7");
//			}
//			if (numericRandom.getNumericOption().toInteger() != 7) {
//				showTestError("numericRandom returned wrong Numeric Option, should have been 7");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Invalid Entry");
//		try {
//			entryBase.setEntry(new Cfg_Entry("o"));
//			if (!entryBase.getStringOption().equals("")) {
//				showTestError("entryBase returned wrong String Option, should have been Empty");
//			}
//			entryLimits.setEntry(new Cfg_Entry(""));
//			if (!entryLimits.getStringOption().equals("")) {
//				showTestError("entryLimits returned wrong String Option, should have been Empty");
//			}
//			entryRandom.setEntry(new Cfg_Entry("2"));
//			if (!entryRandom.getStringOption().equals("")) {
//				showTestError("entryRandom returned wrong String Option, should have been Empty");
//			}
//			numericEntry.setEntry(new Cfg_Entry("200"));
//			if (!numericEntry.getStringOption().equals("100")) {
//				showTestError("numericEntry returned wrong String Option, should have been 100");
//			}
//			if (numericEntry.getNumericOption().toInteger() != 100) {
//				showTestError("numericEntry returned wrong Numeric Option, should have been 100");
//			}
//			numericRandom.setEntry(new Cfg_Entry("500"));
//			if (!numericRandom.getStringOption().equals("100")) {
//				showTestError("numericRandom returned wrong String Option, should have been 100");
//			}
//			if (numericRandom.getNumericOption().toInteger() != 100) {
//				showTestError("numericRandom returned wrong Numeric Option, should have been 100");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry entryBase");
//		try {
//			entryBase.setEntry(new Cfg_Entry("Random"));
//			if (entryBase.getStringOption().equals("")) {
//				showTestError("entryBase returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry entryLimits");
//		try {
//			entryLimits.setEntry(new Cfg_Entry("RANDOM"));
//			if (entryLimits.getStringOption().equals("")) {
//				showTestError("entryLimits returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry entryRandom");
//		try {
//			entryRandom.setEntry(new Cfg_Entry("Random"));
//			if (entryRandom.getStringOption().equals("")) {
//				showTestError("entryRandom returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry numericEntry String");
//		try {
//			numericEntry.setEntry(new Cfg_Entry("Random"));
//			if (numericEntry.getStringOption().equals("")) {
//				showTestError("numericEntry returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry numericEntry Numeric");
//		try {
//			if (numericEntry.getNumericOption().toInteger() == null) {
//				showTestError("numericEntry returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry numericRandom String");
//		try {
//			numericRandom.setEntry(new Cfg_Entry("RANDOM"));
//			if (numericRandom.getStringOption().equals("")) {
//				showTestError("numericRandom returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random Entry numericRandom Numeric");
//		try {
//			if (numericRandom.getNumericOption().toInteger() == null) {
//				showTestError("numericRandom returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits entryBase");
//		try {
//			entryBase.setEntry(new Cfg_Entry("Random 5, 100"));
//			if (entryBase.getStringOption().equals("")) {
//				showTestError("entryBase returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits entryLimits");
//		try {
//			entryLimits.setEntry(new Cfg_Entry("RANDOM 0, 3"));
//			if (entryLimits.getStringOption().equals("")) {
//				showTestError("entryLimits returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits entryRandom");
//		try {
//			entryRandom.setEntry(new Cfg_Entry("Random 2, 2"));
//			if (entryRandom.getStringOption().equals("")) {
//				showTestError("entryRandom returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits numericEntry String");
//		try {
//			numericEntry.setEntry(new Cfg_Entry("Random 10, 50000"));
//			if (numericEntry.getStringOption().equals("")) {
//				showTestError("numericEntry returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits numericEntry Numeric");
//		try {
//			if (numericEntry.getNumericOption().toInteger() == null) {
//				showTestError("numericEntry returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits numericRandom String");
//		try {
//			numericRandom.setEntry(new Cfg_Entry("RANDOM 50, 50"));
//			if (numericRandom.getStringOption().equals("")) {
//				showTestError("numericRandom returned wrong String Option, should not have been Empty");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random with limits numericRandom Numeric");
//		try {
//			if (numericRandom.getNumericOption().toInteger() == null) {
//				showTestError("numericRandom returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list entryBase");
//		try {
//			entryBase.setEntry(new Cfg_Entry("Random o1"));
//			if (!entryBase.getStringOption().equals("O1")) {
//				showTestError("entryBase returned wrong String Option, should have been O1");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list entryLimits");
//		try {
//			entryLimits.setEntry(new Cfg_Entry("RANDOM O1, O2"));
//			String option = entryLimits.getStringOption();
//			if (!(option.equals("O1") || option.equals("O2"))) {
//				showTestError("entryLimits returned wrong String Option, should have been O1 or O2");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list entryRandom");
//		try {
//			entryRandom.setEntry(new Cfg_Entry("Random o2, o5, o3"));
//			String option = entryRandom.getStringOption();
//			if (!(option.equals("O2") || option.equals("O3") || option.equals("O5"))) {
//				showTestError("entryRandom returned wrong String Option, should have been O2, O3 or O5");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list numericEntry String");
//		try {
//			numericEntry.setEntry(new Cfg_Entry("Random 333, 334, 335, 336"));
//			if (!numericEntry.getStringOption().equals("100")) {
//				showTestError("numericEntry returned wrong String Option, should have been 100");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list numericEntry Numeric");
//		try {
//			if (numericEntry.getNumericOption().toInteger() != 100) {
//				showTestError("numericEntry returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list numericRandom String");
//		try {
//			numericRandom.setEntry(new Cfg_Entry("RANDOM 0, 10, 20, 30, 100, 200, 500"));
//			String option = numericRandom.getStringOption();
//			if (option == null) {
//				showTestError("numericRandom returned wrong String Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		newTest("Test Random list numericRandom Numeric");
//		try {
//			Numeric option = numericRandom.getNumericOption();
//			if (option == null) {
//				showTestError("numericRandom returned wrong Numeric Option, should not have been null");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		showGlobalTestResult();
//		return localPass;
//	}
//
//	public static Boolean TestCfgLineValidation(String description) {
//		showGlobalTestStart (description);
//		newTest("Test Random list");
//		try {
//
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
//		// TODO
//		showGlobalTestResult();
//		return localPass;
//	}
//	public static Boolean TestCfgUtil(String description) {
//		showGlobalTestStart (description);
//		// TODO
//		showGlobalTestResult();
//		return localPass;
//	}
//	public static Boolean TestCfgEntry(String description) {
//		showGlobalTestStart (description);
//		Cfg_Entry entry1 = null;
//		Cfg_Entry entry2 = null;
//		newTest("Test isContained and contains");
//		try {
//			entry1 = new Cfg_Entry("ABC", true);
//			entry2 = new Cfg_Entry("abcd", true);
//			if (entry1.isContained(entry2)) {
//				showTestError("entry1 is not contained in entry2");
//			}
//			if (entry2.contains(entry1)) {
//				showTestError("entry2 does not contains entry1");
//			}
//			entry2.setCaseSensitive(false);
//			if (!entry1.isContained(entry2)) {
//				showTestError("entry1 is contained in entry2");
//			}
//			if (!entry2.contains(entry1)) {
//				showTestError("entry2 does contains entry1");
//			}
//		}
//		catch(Exception e) {
//			showTestError(e);
//		}
////		newTest("Test Random list");
////		try {
////
////		}
////		catch(Exception e) {
////			showTestError(e);
////		}
////		newTest("Test Random list");
////		try {
////
////		}
////		catch(Exception e) {
////			showTestError(e);
////		}
//
//		// TODO
//		showGlobalTestResult();
//		return localPass;
//	}
}
