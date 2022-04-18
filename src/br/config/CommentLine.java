
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

public class CommentLine implements I_Prt, I_Comment{
    private String comment = "";

    // ------------------------------------------------------------------------
    // Constructors
    //
    public CommentLine(String newComment) {
        set(newComment);
    }
    CommentLine(CfgField newComment) {
        set(newComment);
    }
    CommentLine(Cfg_Entry newComment) {
        set(newComment);
    }
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
    /**
	 * set a new String value
	 * @return current object to allow chaining
	 */
    CommentLine set(String newComment) {
        comment = CfgField.neverNull(newComment);
        return this;
    }
    /**
	 * set a new CfgField value
	 * @return current object to allow chaining
	 */
    CommentLine set(CfgField newComment) {
        comment = newComment.toString();
        return this;
    }
    /**
	 * set a new Cfg_Entry value
	 * @return current object to allow chaining
	 */
    CommentLine set(Cfg_Entry newComment) {
        comment = newComment.toString();
        return this;
    }
    // ------------------------------------------------------------------------
    // Other Package Methods
    //
    /**
	 * Check if null or empty
	 */
    boolean isEmpty() {
        return comment == null || comment.isEmpty();
    }
   // ------------------------------------------------------------------------
    // Overriders
    //
    /**
     * get only the value as {@code String}
	 * @return the raw {@code String} value
	 */
    @Override
     public String toString() {
        return comment;
    }    
    /**
     * Format the value to be Printed
	 * @return the {@code String} formated element
	 */
    @Override
    public String toPrint() {
        return I_Comment.toComment(CfgField.neverNull(comment));
    }
    /**
     * Format the value as Comment
	 * @return the {@code String} formated element
	 */
    @Override
    public String toComment() {
        return I_Comment.toComment(comment);
    }
    // ------------------------------------------------------------------------
    // Package Static Methods
    //
    /**
	 * Check if the string is a comment
	 */
	public static boolean isComment(String line) {
	    return Cfg_Util.clean(line).startsWith(COMMENT_KEY);
    }
	/**
	 * Check if the CfgField is a comment
	 */
	public static boolean isComment(CfgField line) {
	    return line.toTest().startsWith(COMMENT_KEY);
    }
	/**
	 * Check if the CfgEntry is a comment
	 */
	public static boolean isComment(Cfg_Entry line) {
	    return line.toTest().startsWith(COMMENT_KEY);
    }
    /**
	 * Check if the string contains a comment
	 */
	public static boolean hasComment(String line) {
	    return Cfg_Util.clean(line).contains(COMMENT_KEY);
    }
	/**
	 * Check if the CfgField contains a comment
	 */
	public static boolean hasComment(Cfg_Entry line) {
	    return line.toTest().contains(COMMENT_KEY);
    }
	/**
	 * Return a string without the comments
	 */
	public static String removeComment(String entry) {
		return Cfg_Util.clean((" " + entry).split(CommentLine.COMMENT_KEY, 2)[0]);
	}
	/**
	 * Split and Return the comment while cleaning it from CfgEntry
	 */
	public static CommentLine splitComment(Cfg_Entry entry) {
		if (entry == null || entry.isBlank()) {
			return null;
		}
		if (isComment(entry)) {
			String result = Cfg_Util.clean(entry.get().split(CommentLine.COMMENT_KEY, 2)[1]);
			entry.set("");
			return new CommentLine(result);
		}
		if (hasComment(entry)) {
			String[] s = (" " + entry.toString()).split(CommentLine.COMMENT_KEY, 2);
			entry.set(s[0]);
			return new CommentLine(Cfg_Util.clean(s[1]));
		}
		// No comment!
		return null;
	}
	/**
	 * Split and Return both the comment and the content before
	 */
	public static String[] splitComment(String entry) {
		if (entry == null || entry.isBlank()) {
			return new String[] {"", ""};
		}
		if (isComment(entry)) {
			return new String[] {"", Cfg_Util.clean(entry.split(CommentLine.COMMENT_KEY, 2)[1])};
		}
		if (hasComment(entry)) {
			String[] s = (" " + entry.toString()).split(CommentLine.COMMENT_KEY, 2);
			s[0] = Cfg_Util.clean(s[0]);
			s[1] = Cfg_Util.clean(s[1]);
			return s;
		}
		// No comment!
		return new String[] {Cfg_Util.clean(entry), ""};

	}

}
