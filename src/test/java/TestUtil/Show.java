package test.java.TestUtil;

import java.util.EnumMap;
import java.util.Map;

public class Show {

	public static enum TestLevel 
	{SILENT, FINAL, FOLDER, PACKAGE, CLASS, NESTED, LIST, METHOD, TEST, ALL}
	public static enum InfoLevel
	{Error, Warning, Final, Result, Start, Comment, Info}	
	public static final TestLevel DEFAULT_TEST_LEVEL_SHOWN = TestLevel.ALL;
	public static Map<InfoLevel, TestLevel> infoMap = initInfoMapDefault ();
	public static Boolean finalResult = true;
	
	public static void initFinalResult() {
		finalResult = true;
	}
	public static void updateFinalResult(Boolean result) {
		finalResult &= result == null? true : result;
	}
	public static void showFinalResult() {
		System.out.println("========================================");
		System.out.println( formatInfoLevel("Result")
							+ formatTestLevel("FINAL")
							+ statusToString(finalResult));
	}
	public static void showFinalResult(String testName) {
		System.out.println("========================================");
		System.out.println( formatInfoLevel("Result")
							+ formatTestLevel("FINAL")
							+ testName + " = "
							+ statusToString(finalResult));
	}

	/**
	 * Display {@code InfoLevel} message with {@code TestLevel} privilege 
	 * @param infoLevel the {@code InfoLevel}
	 * @param testLevel the current {@code TestLevel}
	 * @param message   the message to display
	 */
	public static void 
	message	(InfoLevel infoLevel, TestLevel testLevel, String message) {
		message = message == null ? "" : message;
		TestLevel verbose = infoMap.getOrDefault(infoLevel, DEFAULT_TEST_LEVEL_SHOWN);
		if (verbose.compareTo(testLevel) >= 0) {
			
			System.out.println( formatInfoLevel(infoLevel) 
								+ formatTestLevel(testLevel)
								+ message);
		}
	}
	/**
	 * Display {@code InfoLevel} message with {@code TestLevel} privilege 
	 * @param infoLevel the {@code InfoLevel}
	 * @param testLevel the current {@code TestLevel}
	 * @param status    the status to display
	 */
	public static void 
	message	(InfoLevel infoLevel, TestLevel testLevel, Boolean status) {
		TestLevel verbose = infoMap.getOrDefault(infoLevel, DEFAULT_TEST_LEVEL_SHOWN);
		if (verbose.compareTo(testLevel) >= 0) {
			System.out.println( formatInfoLevel(infoLevel) 
								+ formatTestLevel(testLevel)
								+ statusToString(status));
		}
	}
	/**
	 * Display {@code InfoLevel} message with {@code TestLevel} privilege 
	 * @param infoLevel the {@code InfoLevel}
	 * @param testLevel the current {@code TestLevel}
	 * @param testName  the current test
	 * @param status    the status to display
	 */
	public static void 
	message	(InfoLevel infoLevel, TestLevel testLevel, String testName, Boolean status) {
		TestLevel verbose = infoMap.getOrDefault(infoLevel, DEFAULT_TEST_LEVEL_SHOWN);
		if (verbose.compareTo(testLevel) >= 0) {
			System.out.println( formatInfoLevel(infoLevel) 
								+ formatTestLevel(testLevel)
								+ testName + " = "
								+ statusToString(status));
		}
	}
	/**
	 * Display {@code InfoLevel} 2 messages with {@code TestLevel} privilege 
	 * @param infoLevel the {@code InfoLevel}
	 * @param testLevel the current {@code TestLevel}
	 * @param message1  the first message to display
	 * @param message2  the second message to display
	 */
	public static void 
	message	(InfoLevel infoLevel, TestLevel testLevel, String message1, String message2) {
		message1 = message1 == null ? "" : message1;
		message2 = message2 == null ? "" : message2;
		TestLevel verbose = infoMap.getOrDefault(infoLevel, DEFAULT_TEST_LEVEL_SHOWN);
		if (verbose.compareTo(testLevel) >= 0) {
			System.out.println( formatInfoLevel(infoLevel)  
								+ formatTestLevel(testLevel)
								+ message1);
			System.out.println( formatInfoLevel(infoLevel) 
								+ "Error = "
								+ message2);
		}
	}
	// ========== Initialization methods ==========
	public static Map<InfoLevel, TestLevel> initInfoMapDefault () {
		Map<InfoLevel, TestLevel> newMap = new EnumMap<>(InfoLevel.class);
		newMap.put(InfoLevel.Error,   TestLevel.ALL);
		newMap.put(InfoLevel.Warning, TestLevel.ALL);
		newMap.put(InfoLevel.Final,   TestLevel.FINAL);
		newMap.put(InfoLevel.Result,  TestLevel.METHOD);
		newMap.put(InfoLevel.Start,   TestLevel.CLASS);
		newMap.put(InfoLevel.Comment, TestLevel.ALL);
		newMap.put(InfoLevel.Info,    TestLevel.ALL);
		return newMap;
	}
	public static Map<InfoLevel, TestLevel> initInfoMapQuiet () {
		Map<InfoLevel, TestLevel> newMap = new EnumMap<>(InfoLevel.class);
		newMap.put(InfoLevel.Error,   TestLevel.ALL);
		newMap.put(InfoLevel.Warning, TestLevel.ALL);
		newMap.put(InfoLevel.Final,   TestLevel.FINAL);
		newMap.put(InfoLevel.Result,  TestLevel.CLASS);
		newMap.put(InfoLevel.Start,   TestLevel.PACKAGE);
		newMap.put(InfoLevel.Comment, TestLevel.SILENT);
		newMap.put(InfoLevel.Info,    TestLevel.SILENT);
		return newMap;
	}
	public static Map<InfoLevel, TestLevel> initInfoMapAll () {
		return initInfoMapUniform (TestLevel.ALL);
	}
	public static Map<InfoLevel, TestLevel> initInfoMapUniform (TestLevel testLevel) {
		Map<InfoLevel, TestLevel> newMap = new EnumMap<>(InfoLevel.class);
		for (InfoLevel infoLevel : InfoLevel.values()) {
			newMap.put(infoLevel, testLevel);
		}
		return newMap;
	}
	// ========== Private methods ==========
	private static String statusToString (Boolean status) {
		if (status == null) {
			return "Not Executed";
		}
		return status ? "Pass" : "Fail";
	}
	private static String formatInfoLevel(Object infoLevel) {
		return String.format("%-10s", "[" + infoLevel.toString() + "]");
	}
	private static String formatTestLevel(Object testLevel) {
		return String.format("%-9s", testLevel.toString() + ":");
	}
	// Forbidden constructor
	private Show() {}
}
