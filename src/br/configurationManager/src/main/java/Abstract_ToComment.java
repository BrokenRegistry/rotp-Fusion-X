
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

package br.configurationManager.src.main.java;

/**
 * Contains the interface and the tools for toComment Methods
 */
abstract class Abstract_ToComment {
	
	/**
	 * The {@code String} to be recognized as Comment starter
	 */
	static final String COMMENT_KEY = "#";

	/**
	 * The {@code String} to be added after the key for esthetic purpose
	 */
	static final String COMMENT_SPACER = " ";

	/**
	 * The final {@code String} to be added to the toString
	 */
	static final String COMMENT_PRT = COMMENT_KEY + COMMENT_SPACER;

	/**
	 * End of line comment position
	 */
	static final Integer END_COMMENT_POSITION = 30;

	//===============================================================
	// Abstract Methods
	//
	/**
     * Format the element as String
	 * @return the {@code String} formated element
	 */
	@Override
	public abstract String toString();

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
     * Format the object.toString() as Comment
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
		return COMMENT_PRT + element;
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
  				return COMMENT_PRT;
  			}
  			if (onEmpty == false) {
  				return "";
  			}
   		}
 		return COMMENT_PRT + element;
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
 	    return element == null ?
				false : element.strip().startsWith(COMMENT_KEY);
 	}

	/**
 	 * Check if the object.toString() contains a comment
     * @param object the {@code Object} to be analyzed
 	 * @return true if the {@code Object} contains at least one COMMENT_KEY
 	 */
 	static boolean containsComment(Object object) {
 		if (object == null) {
			return false;
		}
		String element = object.toString();
	    return element == null ?
				false : element.contains(COMMENT_KEY);
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
 		return element == null ?
				null : (" " + element).split(COMMENT_KEY, 2)[0].strip();
	}

	/**
	 * Remove the beginning of the {@code Object} and the COMMENT_KEY.
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
			return element.strip().split(COMMENT_KEY, 2)[1].strip();
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
			return new String[] { "", CMutil.removeFirstSpace(
					element.strip().split(COMMENT_KEY, 2)[1]) };
		}
		if (containsComment(element)) {
			String[] s = (" " + element).split(COMMENT_KEY, 2);
			s[0] = s[0].strip();
			s[1] = CMutil.removeFirstSpace(s[1]);
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
	 * <br> {@code String[1]} The part from the right, null for no COMMENT_KEY
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
		return !containsComment(element) ?
			new String[] {element, null} 
			: element.split(COMMENT_KEY, 2);
	}
}
