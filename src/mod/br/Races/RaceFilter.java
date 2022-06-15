
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *	 https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mod.br.Races;

import java.util.List;

/**
 * @author BrokenRegistry
 * Control the race allowed when randomly selected
 */
public class RaceFilter {

	private static List<String> defaultRaceList;
	private static List<String> selectedRaceList;
	private static List<String> selectedGuiRaceList;

	/**
	 * @return the raceList
	 */
	public static List<String> selectedRaceList() {
		if (selectedRaceList == null 
				|| selectedRaceList.toString().isBlank()) {
			return defaultRaceList;
		}
		return selectedRaceList;
	}
	/**
	 * @param newRaceList the raceList to set
	 */
	public static void selectedRaceList(List<String> newRaceList) {
		selectedRaceList = newRaceList;
	}
	/**
	 * @return the raceList
	 */
	public static List<String> selectedGuiRaceList() {
		if (selectedGuiRaceList == null 
				|| selectedGuiRaceList.toString().isBlank()) {
			return defaultRaceList;
		}
		return selectedGuiRaceList;
	}
	/**
	 * @param newRaceList the raceList to set
	 */
	public static void selectedGuiRaceList(List<String> newRaceList) {
		selectedGuiRaceList = newRaceList;
	}
	/**
	 * @return the raceList
	 */
	public static List<String> defaultRaceList() {
		return defaultRaceList;
	}
	/**
	 * @param newRaceList the raceList to set
	 */
	public static void defaultRaceList(List<String> newRaceList) {
		defaultRaceList = newRaceList;
	}

}
