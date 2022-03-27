
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

import br.config.CfgField;

public class Comment {
    private List<CommentLine> commentList;

    // --------------------------------------------------------------
    // Constructors
    //
    public Comment() {
    	set("");
    }
    public Comment(String newComment) {
    	set(newComment);
    }
    public Comment(CfgField newComment) {
    	set(newComment);
    }
    public Comment(List<?> newCommentList) {
        set(newCommentList);
    }
    // --------------------------------------------------------------
    // Getters and Setters
    //
    /**
  	 * Reset comment with a new String
  	 * Return current object to allow chaining
  	 */
    Comment set(String newComment) {
    	commentList = new ArrayList<CommentLine>();
        add(newComment);
        return this;
    }
    /**
  	 * Reset comment with a new CfgField
  	 * Return current object to allow chaining
  	 */
    Comment set(CfgField newComment) {
    	commentList = new ArrayList<CommentLine>();
        add(newComment);
        return this;
    }
    /**
  	 * Reset comment with a new List of any object
  	 * that must have .toString() method
  	 * Return current object to allow chaining
  	 */
    Comment set(List<?> newCommentList) {
    	commentList = new ArrayList<CommentLine>();
    	for (Object comment : newCommentList) {
    		add(comment.toString());
    	}
        return this;
    }
     /**
  	 * Reset comment with a new List of String
  	 * Return current object to allow chaining
  	 */
//    Comment set(List<CfgField> newCommentList) {
//    	commentList = new ArrayList<CommentLine>();
//    	for (String comment : newCommentList) {
//    		add(comment);
//    	}
//        return this;
//    }
   // --------------------------------------------------------------
    // Public Methods
    //
    public boolean isEmpty() {
        return commentList == null || commentList.isEmpty();
    }
    /**
	 * Return a String ready to be printed
	 */
    public String toPrint() {
        if (isEmpty()) { 
        	return new CommentLine("").toPrint();
        }
        List<String> list = new ArrayList<String>();
        for (CommentLine comment : commentList) {
            list.add(comment.toPrint());
        }
        return String.join(System.lineSeparator(), list);
    }
    // --------------------------------------------------------------
    // Other private Methods
    //
    /**
 	 * Add new String to the list
 	 * Return current object to allow chaining
 	 */
     private Comment add(String newComment) {
         commentList.add(new CommentLine(newComment));
         return this;
     }
     /**
 	 * Add new CfgField to the list
 	 * Return current object to allow chaining
 	 */
     private Comment add(CfgField newComment) {
         commentList.add(new CommentLine(newComment));
         return this;
     }
     // ------------------------------------------------------------------------
     // Overrider
     //
     public String toString() {
         return toPrint();
     }
    // --------------------------------------------------------------
    // Public Static Methods
    //
    /**
	 * Check if the string is a comment
	 */
	public static boolean isComment(String line) {
	    return CommentLine.isComment(line);
    }
	/**
	 * Check if the CfgField is a comment
	 */
	public static boolean isComment(CfgField line) {
		return CommentLine.isComment(line);
    }
}
