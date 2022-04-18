
/*
 * Copyright 2015-2020 Ray Fowler
 * 
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

package mod.br.settings;

import java.util.List;

import br.config.AbstractSetting;
import br.config.Comment;
import br.config.AbstractGroup;
import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

public class Group_Galaxy extends AbstractGroup <IGameOptions, GameSession> {

    Group_Galaxy(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new GalaxyShape(gO));
        addSetting(new GalaxySize(gO));
        addSetting(new Difficulty(gO));
        addSetting(new OpponentAI(gO));
        addSetting(new NbOpponent(gO));    
    }

    // ------------------------------------------------------------------------
    // GALAXY SHAPE
    //
    public static class GalaxyShape extends AbstractSetting <IGameOptions, GameSession> {

        GalaxyShape(IGameOptions gO) { super(
            "GALAXY SHAPE",
            gO.galaxyShapeOptions(),
            gO.selectedGalaxySize());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedGalaxyShape();
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedGalaxyShape();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedGalaxyShape(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedGalaxyShape(initialOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Galaxy Options -------------",
                " ")));
        }
    }
    // ------------------------------------------------------------------------
    // GALAXY SIZE
    //
    public static class GalaxySize extends AbstractSetting <IGameOptions, GameSession> {

    	GalaxySize(IGameOptions gO) { super(
            "GALAXY SIZE",
            gO.galaxySizeOptions(),
            gO.selectedGalaxySize());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedGalaxySize();
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedGalaxySize();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedGalaxySize(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedGalaxySize(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // DIFFICULTY
    //
    public static class Difficulty extends AbstractSetting <IGameOptions, GameSession> {

    	Difficulty(IGameOptions gO) { super(
            "DIFFICULTY",
            gO.gameDifficultyOptions(),
            gO.selectedGameDifficulty());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedGameDifficulty();
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {
//        	gO.options().selectedGameDifficulty(userOption);
        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedGameDifficulty();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedGameDifficulty(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedGameDifficulty(initialOption());
        }
        @Override
        public void initComments() {
//                	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        	}
    }
    // ------------------------------------------------------------------------
    // OPPONENT AI
    //
    public static class OpponentAI extends AbstractSetting <IGameOptions, GameSession> {

    	OpponentAI(IGameOptions gO) { super(
            "OPPONENT AI",
            gO.opponentAIOptions(),
            gO.selectedOpponentAIOption());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedOpponentAIOption();
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return gO.selectedOpponentAIOption();
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
            gO.selectedOpponentAIOption(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedOpponentAIOption(initialOption());
        }
        @Override
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // NB OPPONENTS
    //
    public static class NbOpponent extends AbstractSetting <IGameOptions, GameSession> {

    	NbOpponent(IGameOptions gO) { super(
            "NB OPPONENTS",
            0, gO.maximumOpponentsOptions(), 1, gO.maximumOpponentsOptions(),
            gO.selectedNumberOpponents());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return Integer.toString(gO.selectedNumberOpponents());
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return Integer.toString(gO.selectedNumberOpponents());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	// the limits may have changed from previous settings
            int min = 0;
            int max = gO.maximumOpponentsOptions();
            setSettingOptions(min, max, 1, max);
            gO.selectedNumberOpponents(
                Math.min(max, getIntegerOption(userOption)));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
            gO.selectedNumberOpponents(initialValue().toInteger());
        }
        @Override
        public void initComments() {}
    }
}
