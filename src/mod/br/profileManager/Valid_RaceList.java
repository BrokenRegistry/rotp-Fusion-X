
/*
 * Licensed under the GNU General License, Version 3 (the "License");
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

package mod.br.profileManager;

import static br.profileManager.src.main.java.PMconfig.parametersSeparator;
import static br.profileManager.src.main.java.PMconfig.randomId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.profileManager.src.main.java.AbstractT;
import br.profileManager.src.main.java.PMutil;
import br.profileManager.src.main.java.Validation;
import mod.br.Races.RaceFilter;
import rotp.model.game.IGameOptions;

/**
 * For the validation of the configurations Action
 */
//class Valid_LocalEnable extends Valid_ProfileAction {
class Valid_RaceList extends Validation<String> {

	private List<String> startingList;	 // The original List
	private List<String> allRaceOptions; // The full Scrambled list
	private List<String> optionList;	 // The remaining List
	private String[] selectedOpponents;
	private int index;
	private int maxArray;
	private boolean neverNull;


	// ==================================================
	// Constructors and initializers
	//
	Valid_RaceList(AbstractT<String> initialValue, List<String> options, boolean neverNull) {
		super(initialValue, options);
		this.neverNull = neverNull;
	}
	// ========== Getters and Setters ==========
	//
	String[] selectedOpponents() {
		return selectedOpponents;
	}
	String[] selectedOpponents(List<String> userEntry, int maxOpponentType) {
		if (maxOpponentType == 0) {
			maxOpponentType = IGameOptions.MAX_OPPONENT_TYPE;
		}
		initOptions(maxOpponentType);

		String randomGui  = randomSource(RaceFilter.selectedGuiRaceFilter());
		String randomGame = randomSource(RaceFilter.selectedGameRaceFilter());
		int iMax = selectedOpponents.length;
		int iMaxList = userEntry.size()-1;
		int iList;

		String entry = "";
		// loop thru the list
		for (index=0; index<iMax; index++) {
			iList = Math.min(index, iMaxList);
			entry = userEntry.get(iList).strip().toUpperCase();
			switch(entry) {
			case "NULL":
				setNull();
				break;
			case "GUI":
				super.elementAnalysis(randomGui);
				break;
			case "GAME":
				super.elementAnalysis(randomGame);
				break;
			case "":
				validBlankEntry();
				break;
			default:
				super.elementAnalysis(entry);
			}
		}
		return selectedOpponents;
	}
	// ========== Overriders ==========
	//
	@Override protected AbstractT<String> randomWithInListLimit(String[] parameters) {
		int[] limits = validateLimits(parameters);
		List<String> subList = startingList.subList(limits[0], limits[1]);
		if (buildOptionList(subList)) {
			int id = PMutil.getRandom(0, optionList.size());
			return validEntry(optionList.get(id));
		}
		// End of possibilities
		return setNull();
	}
	@Override protected AbstractT<String> randomWithOptions(String[] parameters) {
		if (buildOptionList(Arrays.asList(parameters))) {
			int id = PMutil.getRandom(0, optionList.size());
			return validEntry(optionList.get(id));
		}
		// End of possibilities
		return setNull();
	}
	@Override protected AbstractT<String> randomWithoutParameters() {
		if (buildOptionList()) {
			int id = PMutil.getRandom(0, optionList.size());
			return validEntry(optionList.get(id));
		}
		// End of possibilities
		return setNull();
	}
	@Override protected AbstractT<String> entryAnalysis(String userEntry) {
		initOptions(IGameOptions.MAX_OPPONENT_TYPE);
		return super.entryAnalysis(userEntry);		
	}
	@Override protected AbstractT<String> elementAnalysis(String userEntry) {
		index++;
		// Never change the user entry!
		return super.elementAnalysis(userEntry).setUserViewOnly(userEntry);
	}
	@Override protected AbstractT<String> entryValidation(String userEntry) {
		userEntry = PMutil.clean(userEntry);
		// First Check for blank values
		if (userEntry.isBlank()) {
			return validBlankEntry();
		}
		// Then Check check if part of the list 
		if (isValidUserView(userEntry)) {
			return validEntry(toValue(userEntry).getCodeView());
		} 
		// Bad entry, then either blank or null
		return validBlankEntry();
	}
	// ========== Other Methods ==========
	// 
	private String randomSource(List<String> raceFilter) {
		// Add random ID
		String source = randomId() + " "
				+ String.join(parametersSeparator(), raceFilter);
		return source;
	}
	private boolean buildOptionList(List<String> userList) {
		optionList = userList.stream()
				.filter(allRaceOptions::contains)
				.collect(Collectors.toList());
		return optionList.size() > 0;
	}
	
	private boolean buildOptionList() {
		optionList = allRaceOptions.stream()
				.distinct()
				.collect(Collectors.toList());
		return optionList.size() > 0;
	}
	
	private AbstractT<String> validEntry(String race) {
		if (isValidRace(race)) {
			allRaceOptions.remove(race);
			return setRace(race);
		}
		return setNull();
	}
	
	private AbstractT<String> validBlankEntry() {
		if (maxArray > index) {
			String selectedRace = selectedOpponents[index];
			if (isValidRace(selectedRace)) {
				allRaceOptions.remove(selectedRace);
				return setBlank();
			}
			// to much of this race selected
			return setNull();
		}
		// no race selected OK for blank value
		return setBlank();
	}
	
	private AbstractT<String> setRace(String race) {
		if (maxArray > index) {
			selectedOpponents[index] = race;
		}
		return newValue(race);
	}

	private AbstractT<String> setBlank() {
		return newValue(getBlankCodeView());
	}

	private AbstractT<String> setNull() {
		if (neverNull && allRaceOptions.size() > 0) {
			String race = allRaceOptions.remove(0);
			return setRace(race);
		}
		setRace(null);
		return newValue("Null");
	}

	private boolean isValidRace(String race) {
		return allRaceOptions.contains(race);
	}

	private IGameOptions newOptions() {
		return ClientClasses.getNewOptions();
	}
	
	private void initOptions(int maxOpponentType) {
		// init raceList
		index = 0;
		// init allRaceOptions
		allRaceOptions = new ArrayList<String>();
		startingList = newOptions().startingRaceOptions();
		List<String> optionList = newOptions().startingRaceOptions();
		for (int i=0; i<maxOpponentType; i++) {
			Collections.shuffle(optionList);
			allRaceOptions.addAll(optionList);
		}
		// remove the player from the list
		allRaceOptions.remove(newOptions().selectedPlayerRace());
		
		// load already set opponents
        selectedOpponents = newOptions().selectedOpponentRaces().clone();
		maxArray = selectedOpponents.length;
	}
}
