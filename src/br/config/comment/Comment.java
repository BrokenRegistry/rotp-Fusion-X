
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

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private List<CommentLine> commentList;

    // --------------------------------------------------------------
    // Constructors
    //
    public Comment(String newComment) {
    	set(newComment);
    }
    public Comment(List<String> newCommentList) {
        set(newCommentList);
    }
    // --------------------------------------------------------------
    // Getters and Setters
    //
    void set(String newComment) {
    	commentList = new ArrayList<CommentLine>();
        add(newComment);
    }
    void set(List<String> newCommentList) {
    	commentList = new ArrayList<CommentLine>();
    	for (String comment : newCommentList) {
    		add(comment);
    	}
    }
    // --------------------------------------------------------------
    // Public Methods
    //
    public boolean isEmpty() {
        return commentList == null || commentList.isEmpty();
    }
    public String toPrint() {
        if (isEmpty()) { 
        	return new CommentLine("").toString();
        }
        List<String> list = new ArrayList<String>();
        for (CommentLine comment : commentList) {
            list.add(comment.toString());
        }
        return String.join(System.lineSeparator(), list);
    }
    // --------------------------------------------------------------
    // Other private Methods
    //
    private void add(String newComment) {
        commentList.add(new CommentLine(newComment));
    }
    // --------------------------------------------------------------
    // Public Static Methods
    //
	public static boolean isComment(String line) {
	    return CommentLine.isComment(line);
    }
}
