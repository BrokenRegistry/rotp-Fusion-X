
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

package br.config;
/**
 * Contains the interface and the tools helping to manage it.
 */
public interface I_Comment {
	String COMMENT_KEY = "#";
	String COMMENT_PRT = COMMENT_KEY + I_Prt.SPACER;

	//===============================================================
	// Abstract Methods
	//
	/**
     * Format the element as Comment
	 * @return the {@code String} formated element
	 */
	String toComment();
	//===============================================================
	// Static Methods
	//
	 /**
     * Format the {@code Object} as Comment
	 * @return the {@code String} formated element
	 * <br> null if null
	 */
 	 static String toComment(Object object) {
 		 if (object == null) {
 			 return null;
 		 }
 		 String element = object.toString();
		 return element == null ?
				null : COMMENT_PRT + element;
	}
     /**
 	 * Check if the {@code Object} is a comment
 	 * @return true if the trimmed {@code Object} Start with a COMMENT_KEY
 	 */
	public static boolean isComment(Object object) {
 		if (object == null) {
 			return false;
 		}
 		String element = object.toString();
 	    return element == null ?
				false : element.trim().startsWith(COMMENT_KEY);
 	}
    /**
 	 * Check if the {@code Object} contains a comment
 	 * @return true if the {@code Object} contains at least one COMMENT_KEY
 	 */
 	public static boolean containsComment(Object object) {
 		if (object == null) {
			return false;
		}
		String element = object.toString();
	    return element == null ?
				false : element.contains(COMMENT_KEY);
 	}
	/**
	 * Remove the comment from the {@code Object}
	 * @return a trimmed {@code String} without the comment element
	 */
	public static String removeComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
 		return element == null ?
				null : (" " + element).split(CommentLine.COMMENT_KEY, 2)[0].trim();
	}
	/**
	 * Remove the beginning of the {@code Object} and the COMMENT_KEY.
	 * @return a trimmed {@code String} with only the comment element
	 */
	public static String extractComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		if (containsComment(element)) {
			return element.trim().split(CommentLine.COMMENT_KEY, 2)[1].trim();
		}
		// No comment!
		return "";
	}
	/**
	 * Convert the {@code Object} as String, trim it and split it
	 * @return Return a {@code String Array} with both part
	 * <br> {@code String[0]} The part from the left, trimmed
	 * <br> {@code String[1]} The part from the right (not trimmed)
	 */
	public static String[] splitComment(Object object) {
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		if (isComment(element)) {
			return new String[] {"", element.trim().split(CommentLine.COMMENT_KEY, 2)[1].trim()};
		}
		if (containsComment(element)) {
			String[] s = (" " + element).split(CommentLine.COMMENT_KEY, 2);
			s[0] = s[0].trim();
			s[1] = Cfg_Util.removeFirstSpace(s[1]);
			return s;
		}
		// No comment!
		return new String[] {element.trim(), ""};
	}
	/**
	 * Convert the {@code Object} as String
	 * and split the comment
	 * @return Return a {@code String Array} with both part
	 * <br> null for null {@code Object}, null {@code String}
	 * <br> {@code String[0]} The part from the left
	 * <br> {@code String[1]} The part from the right, null for no COMMENT_KEY
	 */
	public static String[] rawSplitComment(Object object) {
		// Null management
		if (object == null) {
 			return null;
 		}
 		String element = object.toString();
		if (element == null) {
			return null;
		}
		// Non Comment management
		return !containsComment(element) ?
			new String[] {element, null} 
			: element.split(CommentLine.COMMENT_KEY, 2);
	}

}
