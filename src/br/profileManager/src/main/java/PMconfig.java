
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

package br.profileManager.src.main.java;

/**
 * @author BrokenRegistry
 * Global parameters, open for configuration
 */
public class PMconfig {

	private static String commentKey        = ";";
	private static String commentSpacer     = " ";
	private static String keyValueSeparator = ":";
    private static String valueSpacer       = " ";
	private static String separatorSymbol   = "=";
	private static String separatorSpacer   = " ";
	private static String parameterKey      = "¦ Parameter";
	private static String historyKey        = "¦ History";
	private static String optionsKey        = "Options";
	private static String optionsSubKey	    = "  \" \" ";
	private static String historyNameValueSeparator = " : ";
	private static String historyElementsSeparator  = " / ";
	private static String parametersSeparator  = ",";
	private static String listSeparator        = "/";
	private static String randomId             = "RANDOM";
	
	private static String availableForChange = "---- Available for changes in game saves";
	private static String dynamicParameter   = "---- Follow the GUI, not stored in game";
    
	private static int lineSplitPosition  = 16;
	private static int commentEndPosition = 30;
	private static int maxLineLength      = 80;

	/**
	 * @return The {@code String} to be used as Comment starter
	 */
	public static String commentKey() {
		return commentKey;
	}
	/**
	 * @param newValue the new {@code String} to be used as Comment starter
	 */
	public static void commentKey(String newValue) {
		commentKey = newValue;
	}
	/**
	 * @return The {@code String} to be used as Comment Spacer
	 */
	public static String commentSpacer() {
		return commentSpacer;
	}
	/**
	 * @param newValue the new {@code String} to be used as Comment Spacer
	 */
	public static void commentSpacer(String newValue) {
		commentSpacer = newValue;
	}
	/**
	 * @return The {@code String} to be used as Key - Value Separator
	 */
	public static String keyValueSeparator() {
		return keyValueSeparator;
	}
	/**
	 * @param newValue the new {@code String} to be used as Key - Value Separator
	 */
	public static void keyValueSeparator(String newValue) {
		keyValueSeparator = newValue;
	}
	/**
	 * @return The {@code String} to be used as Value Spacer
	 */
	public static String valueSpacer() {
		return valueSpacer;
	}
	/**
	 * @param newValue the new {@code String} to be used as Value Spacer
	 */
	public static void valueSpacer(String newValue) {
		valueSpacer = newValue;
	}
	/**
	 * @return The {@code String} to be used as Option Definition Symbol
	 */
	public static String separatorSymbol() {
		return separatorSymbol;
	}
	/**
	 * @param newValue the new {@code String} to be used as Option Definition Symbol
	 */
	public static void separatorSymbol(String newValue) {
		separatorSymbol = newValue;
	}
	/**
	 * @return The {@code String} to be used as Definition Spacer
	 */
	public static String separatorSpacer() {
		return separatorSpacer;
	}
	/**
	 * @param newValue the new {@code String} to be used as Definition Spacer
	 */
	public static void separatorSpacer(String newValue) {
		separatorSpacer = newValue;
	}
	/**
	 * @return The {@code String} to be used as Parameter Key
	 */
	public static String parameterKey() {
		return parameterKey;
	}
	/**
	 * @param newValue the new {@code String} to be used as Parameter Key
	 */
	public static void parameterKey(String newValue) {
		parameterKey = newValue;
	}
	/**
	 * @return The {@code String} to be used as history Key
	 */
	public static String historyKey() {
		return historyKey;
	}
	/**
	 * @param newValue the new {@code String} to be used as History Key
	 */
	public static void historyKey(String newValue) {
		historyKey = newValue;
	}
	/**
	 * @return The {@code String} to be used as Options Key
	 */
	public static String optionsKey() {
		return optionsKey;
	}
	/**
	 * @param newValue the new {@code String} to be used as Options Key
	 */
	public static void optionsKey(String newValue) {
		optionsKey = newValue;
	}
	/**
	 * @return The {@code String} to be used as Options subKey
	 * (to start new line when long lines have been cut)
	 */
	public static String optionsSubKey() {
		return optionsSubKey;
	}
	/**
	 * @param newValue the new {@code String} to be used as Options subKey
	 * (to start new line when long lines have been cut)
	 */
	public static void optionsSubKey(String newValue) {
		optionsSubKey = newValue;
	}
	/**
	 * @return The {@code String} to be used as 
	 * History Name - Value Separator (Definition)
	 */
	public static String historyNameValueSeparator() {
		return historyNameValueSeparator;
	}
	/**
	 * @param newValue the new {@code String} to be used as 
	 * History Name - Value Separator (Definition)
	 */
	public static void historyNameValueSeparator(String newValue) {
		historyNameValueSeparator = newValue;
	}
	/**
	 * @return The {@code String} to be used as 
	 * History elements Separator
	 */
	public static String historyElementsSeparator() {
		return historyElementsSeparator;
	}
	/**
	 * @param newValue the new {@code String} to be used as 
	 * History elements Separator
	 */
	public static void historyElementsSeparator(String newValue) {
		historyElementsSeparator = newValue;
	}
	/**
	 * @return The {@code String} to be used as
	 * elements separator for random parameters list
	 */
	public static String parametersSeparator() {
		return parametersSeparator;
	}
	/**
	 * @param newValue the new {@code String} to be used as
	 * elements separator for random parameters list
	 */
	public static void parametersSeparator(String newValue) {
		parametersSeparator = newValue;
	}
	/**
	 * @return The {@code String} to be used as 
	 * elements separator for parameters list
	 */
	public static String listSeparator() {
		return listSeparator;
	}
	/**
	 * @param newValue the new {@code String} to be used as
	 * elements separator for parameters list
	 */
	public static void listSeparator(String newValue) {
		listSeparator = newValue;
	}
	/**
	 * @return The {@code String} to be used as Identifier for Random parameters
	 */
	public static String randomId() {
		return randomId;
	}
	/**
	 * @param newValue the new {@code String} to be used as Identifier for Random parameters
	 */
	public static void randomId(String newValue) {
		randomId = newValue;
	}
	/**
	 * @return The {@code String} to be used to inform that this
	 * parameter is available for in Game setting
	 */

	public static String availableForChange() {
		return availableForChange;
	}
	/**
	 * @param newValue the new {@code String} to be used to inform that this
	 * parameter is available for in Game setting
	 */
	public static void availableForChange(String newValue) {
		availableForChange = newValue;
	}
	/**
	 * @return The {@code String} to be used to inform that this
	 * parameter change dynamically with GUI (Not stored in game file)
	 */
	public static String dynamicParameter() {
		return dynamicParameter;
	}
	/**
	 * @param newValue the new {@code String} to be used to inform that this
	 * parameter change dynamically with GUI (Not stored in game file)
	 */
	public static void dynamicParameter(String newValue) {
		dynamicParameter = newValue;
	}

	/**
	 * @return Label - Value Separator Position
	 */
	protected static int lineSplitPosition() {
		return lineSplitPosition;
	}
	/**
	 * @param newValue the new {@code int} Label - Value Separator Position
	 */
	public static void lineSplitPosition(int newValue) {
		lineSplitPosition = newValue;
	}
	/**
	 * @return End o line comment position
	 */
	protected static int commentEndPosition() {
		return commentEndPosition;
	}
	/**
	 * @param newValue the new {@code int} End o line comment position
	 */
	public static void commentEndPosition(int newValue) {
		commentEndPosition = newValue;
	}
	/**
	 * @return Maximum Line Length before splitting
	 */
	protected static int maxLineLength() {
		return maxLineLength;
	}
	/**
	 * @param newValue the new {@code int} Maximum Line Length before splitting
	 */
	public static void maxLineLength(int newValue) {
		maxLineLength = newValue;
	}

}
