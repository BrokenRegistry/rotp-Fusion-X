
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

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the interface and the tools for toComment Methods
 */
public class WriteUtil {
	/**
	 * List of memorized Values
	 */
	public enum History {
	    /**
	     * The very default settings, whit new installation 
	     */
	    Default,
	    /**
	     * GUI settings from the last time the program was launched
	     */
	    Last,
	    /**
	     * GUI settings at the current launch
	     */
	    Initial,
	    /**
	     * The current GUI settings
	     */
	    Current,
	    /**
	     * Settings from the last time a game was launched
	     */
	    Game,
	    /**
	     * Bank value! (Type dependent)
	     */
	    Blank
	}
	
	/**
	 * a condensed access to System.lineSeparator()
	 */
	protected static final String NL = System.lineSeparator();

	private static final String COMMENT_KEY = ";";

	private static final String COMMENT_SPACER = " ";

	private static final String COMMENT_PRT = COMMENT_KEY + COMMENT_SPACER;
	
	private static final String EMPTY_COMMENT_LINE = COMMENT_KEY + COMMENT_SPACER + NL;

	private static final int LINE_SPLIT_POSITION = 16;

	private static final int COMMENT_END_POSITION = 30;

	private static final int MAX_LINE_LENGTH = 80;

	/**
	 * The {@code String} to be recognized as Comment starter
	 */
	protected static String commentKey() {
		return COMMENT_KEY;
	}
	
	/**
	 * @return The {@code String} to be added to the toString
	 */
	protected static String commentPrt() {
		return COMMENT_PRT;
	}
	
	/**
	 * @param quantity the number of empty comment lines
	 * @return a batch of empty comment lines
	 */
	protected static String emptyCommentLines(int quantity) {
		return EMPTY_COMMENT_LINE.repeat(quantity);
	}
	
	/**
	 * @return an empty comment lines
	 */
	protected static String emptyCommentLines() {
		return EMPTY_COMMENT_LINE;
	}
	
	/**
	 * @return Label - Value Separator Position
	 */
	protected static int lineSplitPosition() {
		return LINE_SPLIT_POSITION;
	}
	
	/**
	 * @return End of line comment position
	 */
	protected static int commentEndPosition() {
		return COMMENT_END_POSITION;
	}
	
	/**
	 * @return Default maximum line length
	 */
	protected static int maxLineLength() {
		return MAX_LINE_LENGTH;
	}
	
	//===============================================================
	// Methods using the Abstract methods
	//
	/**
     * Format the element as Comment
	 * @return the {@code String} formated element
	 */
	String toComment() {
		return toComment(toString());
	}

	/**
     * Format the element as Comment
     * @param onEmpty {@code Boolean} what to return if the object is <b>null</b> or <i>empty</i>
	 * <ul><ul>
	 * <b> true </b> = Comment Key <br>
	 * <b> false </b> = <i>empty</i> <br>
	 * <b> null </b> = <b>null</b>
	 * </ul> </ul> 
	 * @return the {@code String} formated element
	 */
	String toComment(Boolean onEmpty) {
		return toComment(toString(), onEmpty);
	}

	//===============================================================
	// Static Methods
	//
	/**
     * split the string over several lines, using firstHeader
     * on the first line then otherHeader on the following ones
 	 * @param string the {@code String} to be formated
 	 * @param splitter the {@code String} where the line could be cut
	 * @param firstHeader the Header for the first line
	 * @param otherHeader the Header for the other lines
	 * @return the {@code String} formated object, never null
	 */
	static String multiLines(String string, String splitter,
							String firstHeader, String otherHeader) {
		firstHeader = PMutil.neverNull(firstHeader);
		otherHeader = PMutil.neverNull(otherHeader);
		splitter = PMutil.neverNull(splitter);
		if (splitter.isEmpty()) {
			splitter = " ";
		}
		int slen = splitter.length();
		List<String> lines = new ArrayList<String>();
		String[] elements;
		String line = firstHeader;
		boolean firstLineElement = true;
		
		elements = PMutil.neverNull(string)
						 .replace(NL, splitter + NL + splitter)
						 .split(splitter);
		for (String element : elements) {
			if (element.contains(NL)) {
				lines.add(line);
				line = otherHeader;
				firstLineElement = true;
			} 
			else if (line.length() + slen + element.length() > maxLineLength()) {
				lines.add(line);
				line = otherHeader + element;
				firstLineElement = false;
			} 
			else if (firstLineElement) {
					line += element;
					firstLineElement = false;
			} else {
				line += splitter + element;
			}
		}
		lines.add(line);
		return String.join(NL, lines);
	}

	/**
     * split the string over several lines, using firstHeader
     * on the first line then otherHeader on the following ones,
     * String is only split on " "
 	 * @param string the {@code String} to be formated
	 * @param firstHeader the Header for the first line
	 * @param otherHeader the Header for the other lines
	 * @return the {@code String} formated object, never null
	 */
	static String multiLines(String string, String firstHeader, String otherHeader) {
		return multiLines(string, " ", firstHeader, otherHeader);
	}

	/**
     * Format the object.toString() as Comment
     * Split with {@code System.lineSeparator()} and comment each lines
	 * @param object {@code Object} to be formated
	 * @return the {@code String} formated object, <i>empty</i> if <b>null</b>
	 */
 	static String toComment(Object object) {
 		if (object == null) {
 			return "";
 		}
 		String element = object.toString();
 		if (element == null) {
 			return "";
 		}
 		String[] lines = element.split(System.lineSeparator());
 		String out = "";
 		for (String line : lines) {
			if (out.isBlank()) {
 				out = commentPrt() + line;
 			} else {
 				out += NL + commentPrt() + line;
 			}
 		}
		return out;
	}

	/**
     * Format the object.toString() as Comment,
     * with leading and following empty comment lines.
     * Split with {@code System.lineSeparator()} and comment each lines.
	 * @param object {@code Object} to be formated
	 * @return the {@code String} formated object, <i>empty</i> if <b>null</b> or empty
	 */
 	static String toCommentLine(Object object, int before, int after) {
 		if (object == null 
 				|| object.toString() == null 
 				|| object.toString().isEmpty()) {
 			return "";
 		}
 		return emptyCommentLines(before)
 				+ toComment(object) + NL
 				+ emptyCommentLines(after);
 	}

	/**
     * Format the object.toString() as Comment and terminate by new line.
     * Split with {@code System.lineSeparator()} and comment each lines.
	 * @param object {@code Object} to be formated
	 * @return the {@code String} formated object, <i>empty</i> if <b>null</b> or empty
	 */
 	static String toCommentLine(Object object) {
 		if (object == null 
 				|| object.toString() == null 
 				|| object.toString().isEmpty()) {
 			return "";
 		}
 		return toComment(object) + NL;
 	}

	/**
     * Format the object.toString() as Comment
	 * @param object {@code Object} to be formated
	 * @return the {@code String} formated object, <i>empty</i> if <b>null</b>
	 */
 	static String listToComment(List<String> list) {
 		if (list == null) {
 			return "";
 		}
 		String out = "";
 		for (String element : list) {
 			if (out.isBlank()) {
 				out = toComment(element);
 			} else {
 				out += NL + toComment(element);
 			}
 		}
		return out;
	}

 	/**
     * Format the object.toString() as Comment
 	 * @param object {@code Object} to be formated
	 * @param onEmpty {@code Boolean} what to return if the object is <b>null</b> or <i>empty</i>
	 * <ul><ul>
	 * <b> true </b> = COMMENT KEY <br>
	 * <b> false </b> = <i>empty</i> <br>
	 * <b> null </b> = <b>null</b>
	 * </ul> </ul> 
 	 * @return the {@code String} formated object
 	 */
  	static String toComment(Object object, Boolean onEmpty) {
  		String element = null;
  		if (object != null) {
  			element = object.toString();
  		}
  		if (element == null || element.isEmpty()) {
  			if (onEmpty == null) {
  				return null;
  			}
  			if (onEmpty == true) {
  				return commentPrt();
  			}
  			if (onEmpty == false) {
  				return "";
  			}
   		}
 		return toComment(element);
 	}

  	/**
 	 * Check if the object.toString() is a comment
     * @param object the {@code Object} to be analyzed
 	 * @return true if the stripped {@code Object} Start with a COMMENT KEY
 	 */
	static boolean isComment(Object object) {
 		if (object == null) {
 			return false;
 		}
 		String element = object.toString();
 		if (element == null) {
 			return false;
 		}
 	    return element.strip().startsWith(commentKey());
 	}

	/**
 	 * Check if the object.toString() contains a comment
     * @param object the {@code Object} to be analyzed
 	 * @return true if the {@code Object} contains at least one commentKey()
 	 */
 	static boolean containsComment(Object object) {
 		if (object == null) {
			return false;
		}
		String element = object.toString();
 		if (element == null) {
 			return false;
 		}
	    return element.contains(commentKey());
 	}

 	/**
	 * Remove the comment from the object.toString()
     * @param object the {@code Object} to be formated
 	 * @return a stripped {@code String} without the comment element
	 */
	static String removeComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
 		if (element == null) {
 			return null;
 		}
  		return (" " + element).split(commentKey(), 2)[0].strip();
	}

	/**
	 * Remove the beginning of the {@code Object} and the commentKey().
	 * @param object the {@code Object} to be analyzed
 	 * @return a stripped {@code String} with only the comment element
	 */
	static String extractComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		if (containsComment(element)) {
			return element.strip().split(commentKey(), 2)[1].strip();
		}
		// No comment!
		return "";
	}

	/**
	 * Convert the {@code Object} as String, strip it and split it
     * @param object the {@code Object} to be formated
	 * @return Return a {@code String Array} with both part
	 * <br> {@code String[0]} The part from the left, stripped
	 * <br> {@code String[1]} The part from the right (not stripped)
	 */
	static String[] splitComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		if (isComment(element)) {
			return new String[] { "", PMutil.removeFirstSpace(
					element.strip().split(commentKey(), 2)[1]) };
		}
		if (containsComment(element)) {
			String[] s = (" " + element).split(commentKey(), 2);
			s[0] = s[0].strip();
			s[1] = PMutil.removeFirstSpace(s[1]);
			return s;
		}
		// No comment!
		return new String[] {element.strip(), ""};
	}

	/**
	 * Convert the {@code Object} as String
	 * and split the comment (no stripping)
     * @param object the {@code Object} to be formated
	 * @return Return a {@code String Array} with both part
	 * <br> null for null {@code Object}, null {@code String}
	 * <br> {@code String[0]} The part from the left
	 * <br> {@code String[1]} The part from the right, null for no commentKey()
	 */
	static String[] rawSplitComment(Object object) {
		// Null management
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		// Non Comment management
 		if (!containsComment(element)) {
 			return new String[] {element, null};
 		}
		return element.split(commentKey(), 2);
	}
}
