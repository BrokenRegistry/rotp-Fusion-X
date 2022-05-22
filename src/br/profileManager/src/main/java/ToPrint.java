
/*
 * Licensed under the GNU General License, Version 3 (the "License");
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
 * Give tools and a common Interface for toPrint methods
 */
public class ToPrint extends ToComment {
	/**
	 * The way this String should be printed
	 */
	protected static enum PrintFormat {
	/**
	 * Print as is
	 */
	HOLD,
	/**
	 * Print everything in upper case 
	 */
	UPPER_CASE,
	/**
	 * Print everything in lower case 
	 */
	LOWER_CASE,
	/**
	 * Print the first letter of the every word in upper case,
	 * in lower case otherwise
	 */
	CAPITALIZE,
	/**
	 * Print the first letter of the first word in upper case,
	 * Print lower case otherwise
	 */
	SENTENCE
	};

	/**
	 * Label - Value Separator Position
	 */
	static final Integer LINE_SPLIT_POSITION = 20;

	/**
	 * Format to element, ready to be Printed
	 * @return the {@code String} formated element, never null
	 */
	String toPrint() {
		return toPrint(toString());
	}

	/**
	 * Format to element, ready to be Printed
     * @param format the {@code PrintFormat} to be applied
	 * @return the {@code String} formated element, never null
	 */
	String toPrint(PrintFormat format) {
		return toPrint(toString(), format);
	}
	
    /**
     * Format the {@code Object} to be Printed
     * @param object the {@code Object} to be formated
	 * @return the {@code String} formated element, never null
	 */
	static String toPrint(Object object) {
 		if (object == null) {
 			return "";
 		}
 		String element = object.toString();
 		if (element == null) {
 			return "";
 		}
		return element;
	}

	/**
     * Format the {@code Object} to be Printed
     * @param object the {@code Object} to be formated
     * @param format the {@code PrintFormat} to be applied
	 * @return the {@code String} formated element, never null
	 */
	static String toPrint(Object object, PrintFormat format) {
 		if (object == null) {
 			return "";
 		}
 		String element = object.toString();
 		if (element == null) {
 			return "";
 		}
 		if (format == null) {
 			return element;
 		}
 		switch (format) {
			case CAPITALIZE:
				return PMutil.capitalize(element);
			case HOLD:
				return element;
			case LOWER_CASE:
				return element.toLowerCase();
			case SENTENCE:
				return PMutil.toSentence(element);
			case UPPER_CASE:
				return element.toUpperCase();
		default:
			return element;
 		}
	}
}
