
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

public interface I_Prt {
	String SPACER = " ";
	
	String toPrint();
	
    /**
     * Format the {@code Object} to be Printed
	 * @return the {@code String} formated element, never null
	 */
	 static String toPrint(Object object) {
 		 if (object == null) {
 			 return "";
 		 }
 		 String element = object.toString();
		 return element == null ?
				"" : element;
	}
	 
//	default String toPrint(String s) {
//		return s;
//	}
}
