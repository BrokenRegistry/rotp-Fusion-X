
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

public class Group_Governor extends AbstractGroup <IGameOptions, GameSession> {
	
	// TODO ALL

    Group_Governor(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new GovernorOnByDefault(gO));
        addSetting(new AutospendOnByDefault(gO));
        addSetting(new DefaultMaxBases(gO));
        addSetting(new DivertExcessToResearch(gO));
    }

    // ------------------------------------------------------------------------
    // GOVERNOR ON BY DEFAULT
    //
    public static class GovernorOnByDefault extends AbstractSetting <IGameOptions, GameSession> {

    	GovernorOnByDefault(IGameOptions gO) { super(
            "GOVERNOR ON BY DEFAULT",
            gO.galaxySizeOptions(),
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
    // AUTOSPEND ON BY DEFAULT
    //
    public static class AutospendOnByDefault extends AbstractSetting <IGameOptions, GameSession> {

    	AutospendOnByDefault(IGameOptions gO) { super(
            "AUTOSPEND ON BY DEFAULT",
            gO.galaxySizeOptions(),
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
    // DEFAULT MAX BASES
    //
    public static class DefaultMaxBases extends AbstractSetting <IGameOptions, GameSession> {

    	DefaultMaxBases(IGameOptions gO) { super(
            "DEFAULT MAX BASES",
            gO.gameDifficultyOptions(),
            gO.selectedGameDifficulty());
        }
        @Override
        public String getFromGame (IGameOptions gO) {
        	return gO.selectedGameDifficulty();
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

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
        public void initComments() {}
    }
    // ------------------------------------------------------------------------
    // DIVERT EXCESS TO RESEARCH
    //
    public static class DivertExcessToResearch extends AbstractSetting <IGameOptions, GameSession> {

    	DivertExcessToResearch(IGameOptions gO) { super(
            "DIVERT EXCESS TO RESEARCH",
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
}
