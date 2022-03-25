
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

import br.config.StrField;

public class CommentLine {
    private static final String KEY     = "#";
    private static final String SPACER  = " ";
    private static final String KEY_PRT = KEY + SPACER;
    private StrField comment = new StrField();

    // ------------------------------------------------------------------------
    // Constructors
    //
    CommentLine(String newComment) {
        set(newComment);
    }
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
    void set(String newComment) {
        comment.set(newComment);
    }
    // ------------------------------------------------------------------------
    // Other package Methods
    //
    boolean isEmpty() {
        return comment.isEmpty();
    }
    public String toString() {
        return KEY_PRT + comment.toString();
    }
    // ------------------------------------------------------------------------
    // Static Methods
    //
	static boolean isComment(String line) {
	    return StrField.clean(line).startsWith(KEY);
    }
}
