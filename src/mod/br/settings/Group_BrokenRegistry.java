
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
import br.config.AbstractGroup;
import br.config.CfgField;
import br.config.Comment;
import mod.br.alteration.GalaxySpacing;
import mod.br.alteration.StarsOptions;
import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

public class Group_BrokenRegistry extends AbstractGroup <IGameOptions, GameSession> {
	
    Group_BrokenRegistry(IGameOptions gO) {
       super(gO);
    }
    @Override
    protected void initSettingList(IGameOptions gO) {
        addSetting(new MaximizeEmpiresSpacing(gO));
        addSetting(new PreferedStarsPerEmpire(gO));
        addSetting(new MinStarsPerEmpire(gO));
        addSetting(new NoPlanetPctMultiplier(gO));
    }

    // ========================================================================
    // MAXIMIZE EMPIRES SPACING
    //
    public static class MaximizeEmpiresSpacing extends AbstractSetting <IGameOptions, GameSession> {
     
    	MaximizeEmpiresSpacing(IGameOptions gO) { super(
            "MAXIMIZE EMPIRES SPACING",
            GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return CfgField.toYesNoString(GalaxySpacing.isMaximizeEmpiresSpacing());
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return CfgField.toYesNoString(GalaxySpacing.isMaximizeEmpiresSpacing());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setMaximizeEmpiresSpacing(getBooleanOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setMaximizeEmpiresSpacing(initialValue().getOrDefault(GalaxySpacing.DEFAULT_MAXIMIZE_EMPIRES_SPACING));
        }
        @Override
        public void initComments() {
            headComments(new Comment(List.of(
                " ",
                "------------- Extended Options -------------",
                " ")));
        }
    }
    // ========================================================================
    // PREF STARS PER EMPIRE
    //
    public static class PreferedStarsPerEmpire extends AbstractSetting <IGameOptions, GameSession> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 16;
        private static final int MAX_R = 24;
    	
    	PreferedStarsPerEmpire(IGameOptions gO) { super(
            "PREF STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getPreferedStarsPerEmpire());
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getPreferedStarsPerEmpire());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setPreferedStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setPreferedStarsPerEmpire(initialValue().getOrDefault(GalaxySpacing.DEFAULT_PREFERED_STARS_PER_EMPIRE));
        }
        @Override
        public void initComments() {}
    }
    // ========================================================================
    // MIN STARS PER EMPIRE
    //
    public static class MinStarsPerEmpire extends AbstractSetting <IGameOptions, GameSession> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 4;
        private static final int MAX_R = 16;
        
    	MinStarsPerEmpire(IGameOptions gO) { super(
            "MIN STARS PER EMPIRE",
            MIN, MAX, MIN_R, MAX_R,
            GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getMinStarsPerEmpire());
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(GalaxySpacing.getMinStarsPerEmpire());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	GalaxySpacing.setMinStarsPerEmpire(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	GalaxySpacing.setMinStarsPerEmpire(initialValue().getOrDefault(GalaxySpacing.DEFAULT_MIN_STARS_PER_EMPIRE));
        }
        @Override
        public void initComments() {}

    }
    // ========================================================================
    // NO PLANET PCT MULT
    //
    public static class NoPlanetPctMultiplier extends AbstractSetting <IGameOptions, GameSession> {

        private static final int MIN   = 0;
        private static final int MAX   = 1000000;
        private static final int MIN_R = 0;
        private static final int MAX_R = 200;
        
    	NoPlanetPctMultiplier(IGameOptions gO) { super(
            "NO PLANET PCT MULT",
            MIN, MAX, MIN_R, MAX_R,
            StarsOptions.NO_PLANET_PCT_MULTIPLIER);
        }
        // ------------------------------------------------
        // Overrider
        //
        @Override
        public String getFromGame (IGameOptions gO) {
        	return String.valueOf(StarsOptions.getNoPlanetPctMultiplier());
        }
        @Override
        public void putToGame(GameSession gO, String userOption) {

        }    
    	@Override
        public String getFromUI (IGameOptions gO) {
        	return String.valueOf(StarsOptions.getNoPlanetPctMultiplier());
        }
        @Override
        public void putToGUI(IGameOptions gO, String userOption) {
        	StarsOptions.setNoPlanetPctMultiplier(getIntegerOption(userOption));
        }
        @Override
        public void putInitialToGUI(IGameOptions gO) {
        	StarsOptions.setNoPlanetPctMultiplier(initialValue().getOrDefault(StarsOptions.NO_PLANET_PCT_MULTIPLIER));
        }
        @Override
        public void initComments() {}
    }
}
