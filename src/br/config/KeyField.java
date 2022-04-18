
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
 * The field content will never be null
 * and will be striped
 */
public class KeyField extends UserField{
	
	public static final String STRING_DEFAULT_VALUE  = "";
    public static final String LABEL_OF_SECTION_KEY   = "¦ SETTING";
	public static final String LABEL_OF_ENABLE_SECTION_KEY = "¦ LOCAL ENABLE";
		
    // ==================================================
    // Constructors
    //
	public KeyField() {}
	public KeyField(String newField) {
        set(newField);
    }
   // ==================================================
    // Very specific Tests Methods
    //
	/**
	 * check if it contains of LABEL_OF_SECTION_KEY
	 */
 	public boolean isSectionKey() {
		return toTest().contains(LABEL_OF_SECTION_KEY);
	}
	/**
	 * check if it contains of LABEL_OF_ENABLE_SECTION_KEY
	 */
 	public boolean isLocalEnableKey() {
		return toTest().contains(LABEL_OF_ENABLE_SECTION_KEY);
	}

}
