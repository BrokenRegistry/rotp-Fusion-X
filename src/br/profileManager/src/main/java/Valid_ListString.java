//
///*
// * Licensed under the GNU General Public License, Version 3 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.gnu.org/licenses/gpl-3.0.html
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package br.profileManager.src.main.java;
//
//import java.util.List;
//import static br.profileManager.src.main.java.WriteUtil.History.*;
//
///**
// *
// */
//public class Valid_ListString extends 
//				Abstract_ValidListData<String, List<String>>{
//
//    // ==================================================
//    // Constructors and initializers
//    //
//
//	// ==================================================
//    // Overriders
//    //	
//	/**
//	 * Process non Random user entry
//	 * @return {@code Code View List<String>} Validated Value
//	 */
//	@Override List<String> entryValidation(String userEntry) {
//		userEntry = PMutil.clean(userEntry);
//		// First Check for blank values
//		if (userEntry.isBlank()) {
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		// Then Check if value is valid
//		for (String element : splitUserView(userEntry)) {
//			
//		}
//		Integer value = PMutil.toInteger(userEntry);
//		if (value == null) {
//			if (hasList() && isValidUserEntry(userEntry)) {
//				return getCodeView(userEntry);
//			}
//			if (getValidationCriteria().isBlankAllowed()) {
//				return null;
//			}
//			return getHistoryCodeView(Default);
//		}
//		return PMutil.validateLimits(value, getLimits(0), getLimits(1));
//
////		
////		// Then Check for a validation list
////		if (!hasList()) {
////			// No list = entry accepted
////			return userEntry;
////		}
////		// Then check validity
////		if (isValidUserEntry(userEntry)) {
////			return toCodeView(userEntry);
////		}
////		return getHistoryUserView(Default);
////
////		// TODO Auto-generated method stub
////		return null;
//	}
//
//    //
//	// ===== No Random Allowed Yet =====
//	//
////	/**
////	 * Process Random with parameters
////	 * @param parameters {@code String[]} the extra parameters
////	 * @return {@code List<String>} OutputString
////	 */
////	@Override String randomWithLimit(String[] parameters) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	/**
////	 * Process Random without parameters
////	 * @return {@code String} OutputString
////	 */
////	@Override String randomWithoutParameters() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//	/**
//	 * Conversion from code view to user view
//	 * <br> For already validated Data
//	 * @param codeView {@code Code View} the value to convert
//	 * @return {@code String} the user view
//	 */
//	@Override String toUserView(List<String> codeView) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * Conversion from user view to code view.
//	 * <br> For already validated Data
//	 * @param codeView {@code String} the value to convert
//	 * @return {@code List<String>} the code view
//	 */
//	@Override List<String> toCodeView(String userView) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
