
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

package br.config.comment;

import br.config.CfgField;

public class CommentLine {
    public static final String KEY     = "#";
    private static final String SPACER  = " ";
    private static final String KEY_PRT = KEY + SPACER;
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
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
    /**
	 * set a new String value
	 * Return current object to allow chaining
	 */
    CommentLine set(String newComment) {
        comment = CfgField.neverNull(newComment);
        return this;
    }
    /**
	 * set a new CfgField value
	 * Return current object to allow chaining
	 */
    CommentLine set(CfgField newComment) {
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
    /**
	 * Return a String ready to be printed
	 */
    public String toPrint() {
        return KEY_PRT + comment;
    }
    // ------------------------------------------------------------------------
    // Overrider
    //
    public String toString() {
        return toPrint();
    }    
    // ------------------------------------------------------------------------
    // Package Static Methods
    //
    /**
	 * Check if the string is a comment
	 */
	static boolean isComment(String line) {
	    return CfgField.clean(line).startsWith(KEY);
    }
	/**
	 * Check if the CfgField is a comment
	 */
	static boolean isComment(CfgField line) {
	    return line.toKey().startsWith(KEY);
    }
}
