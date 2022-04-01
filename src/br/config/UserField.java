
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

import java.util.List;
import java.util.Set;

import br.config.comment.CommentLine;

/**
 * The field content will never be null
 * and will be striped
 */
public abstract class UserField{
	
	String value = "";
	
    // ==================================================
    // Constructors
    //
	public UserField() {}
    // ==================================================
    // Setters
    //
	/**
	 * Set a new String value
	 */
	public void set(String newValue) {
		value = clean(newValue);
	}
    // ==================================================
    // Getters simple
    //
	/**
	 * return value as String
	 */
	public String get() { 
		return value;
	}
	/**
	 * return value as String
	 */
	public String toString() { 
		return value;
	}
	/**
	 * return value as String, ready to be printed
	 */
	public String toPrint() { 
		return value;
	}
	/**
	 * return Upper Case String of value
	 */
	public String toKey() { 
		return value.toUpperCase();
	}
	/**
	 * return value in lower case, with first char to upper case,
	 * with every word capitalized if eachWord is true
	 */
	public String toCapitalized() { 
		String result = "";
		if (value.length() > 0) {
			String[] elements = value.toLowerCase().split("((?<= )|(?<=_)|(?<=-)|(?<=\\[))");
//			String[] elements = value.toLowerCase().split("((?<= )|(?<=_)|(?<=-)|(?<=[))");
			for(String s : elements) {
				result += s.substring(0, 1).toUpperCase();
				result += s.substring(1);
			}
		}
		return result;
	}
	/**
	 * Strip and return in lower case with first char to upper case, never null
	 */
	public String toSentence() { 
		return value.substring(0, 1).toUpperCase() 
				+ value.substring(1).toLowerCase();
	}

    // ==================================================
    // Getters with default values
    //
	/**
	 * return the value, defaultValue if blank
	 */
	public String getOrDefault(String defaultValue) {
	   	if (value.isBlank()) {
    		return defaultValue;
    	}
        return value;
	}
    // ==================================================
    // Tests Methods
    //
	/**
	 * true if null
	 */
	public boolean isNull() {
	    return (value == null);
    }
	/**
	 * true if Empty or null
	 */
	public boolean isEmpty() {
	    return (value == null || value.isEmpty());
    }
	/**
	 * true if Blank, Empty or null
	 */
	public boolean isBlank() {
	    return (value == null || value.isBlank());
    }
	/**
	 * check if value as Key == string
	 */
	public boolean keyTest(String string) {
		return toKey().equalsIgnoreCase(string);
	}
	/**
	 * check if is valid member of Set
	 */
	public boolean isMemberOf(Set<String> set) {
		return set.contains(toKey());
	}
	/**
	 * check if is valid member of List
	 */
	public boolean isMemberOf(List<String> list) {
		return list.contains(toKey());
	}
	// ==================================================
    // Other Methods
    //
	/**
	 * replace null values to empty String
	 */
	public void neverNull() {
		if (value == null) {
			value = "";
		}
	}
	/**
	 * strip the value or
	 * replace null values to empty String
	 */
	public void clean() {
		neverNull();
		value = value.strip();
	}
	/**
	 * Remove the comments and clean
	 */
	public void removeComments() {
		set((" " + value).split(CommentLine.KEY, 2)[0]);
	}
	// ==================================================
    // Public Static Methods
    //
	/**
	 * true if Empty or null
	 */
	public static boolean isEmpty(String string) {
	    return (string == null || string.isEmpty());
    }
	/**
	 * true if Blank, Empty or null
	 */
	public static boolean isBlank(String string) {
	    return (string == null || string.isBlank());
    }
	/**
	 * convert objects to String
	 * and null objects to Empty Strings
	 */
	public static String neverNull(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString(); 
    }
	/**
	 * convert objects to String and strip them
	 * and null objects to Empty Strings
	 */
	public static String clean(Object obj) {
		if (obj == null) { 
			return "";
		}
		return obj.toString().strip(); 
    }
}
