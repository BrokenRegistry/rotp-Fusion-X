
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

public class Group_Race extends AbstractGroup <IGameOptions, GameSession> {

    Group_Race(IGameOptions go) {
       super(go);
    }
    @Override
	protected void initSettingList(IGameOptions go) {
        addSetting(new PlayerRace(go));
        addSetting(new PlayerColor(go));
    }

    // ------------------------------------------------------------------------
    // PLAYER RACE
    //
    public static class PlayerRace extends AbstractSetting <IGameOptions, GameSession> {

        PlayerRace(IGameOptions go) { super(
            "PLAYER RACE",
            go.startingRaceOptions(),
            go.selectedPlayerRace());
        }
        @Override
        public String getFromGame (IGameOptions go) {
        	return go.selectedPlayerRace();
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
//        	gs.options().selectedPlayer().race = userOption; // Direct to avoid reseting opponents
        }        
        @Override
        public String getFromUI (IGameOptions go) {
        	return go.selectedPlayerRace();
        }
        @Override
        public void putToGUI(IGameOptions go, String userOption) {
            go.selectedPlayerRace(userOption);
        }
        @Override
        public void putInitialToGUI(IGameOptions go) {
            go.selectedPlayerRace(initialOption());
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "--------- Races Game Options ---------",
                " ")));
//        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
        }
    }
    // ------------------------------------------------------------------------
    // PLAYER COLOR
    //
    public static class PlayerColor extends AbstractSetting <IGameOptions, GameSession> {
        static final List<String> EMPIRECOLORS  =
        List.of("red", "green", "yellow", "blue", "orange", "purple", "aqua", "fuchsia",
                "brown", "white", "lime", "grey", "plum", "light blue", "mint", "olive");

        PlayerColor(IGameOptions go) { super(
            "PLAYER COLOR",
            EMPIRECOLORS,
            intToColor(go));
        }
        @Override
        public String getFromGame (IGameOptions go) {
        	return intToColor(go);
        }
        @Override
        public void putToGame(GameSession gs, String userOption) {
        	gs.galaxy().empire(0).changeColorId(colorToInt(userOption));
        	gs.options().selectedPlayerColor(colorToInt(userOption));
        }    
        @Override
        public String getFromUI (IGameOptions go) {
        	return intToColor(go);
        }
        @Override
        public void putToGUI(IGameOptions go, String userOption) {
            go.selectedPlayerColor(colorToInt(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions go) {
            go.selectedPlayerColor(colorToInt(initialOption()));
        }
        @Override
        public void initComments() {
        	bottomComments(new Comment(AVAILABLE_FOR_CHANGE));
            	}
        // -----------------------------------------------------------------
        // Private Converter Methods
        //
        private static String intToColor (IGameOptions go) {
        	return EMPIRECOLORS.get(go.selectedPlayerColor());
        }        
        private static int colorToInt (String color) {
        	return EMPIRECOLORS.indexOf(color);
        }
    }
}