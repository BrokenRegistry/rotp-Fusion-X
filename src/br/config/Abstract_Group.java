
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class Abstract_Group <GuiObject, SaveObject> {
    // ------------------------------------------------------------------------
	// Variables Properties
    //
    LinkedHashMap<String, Abstract_Setting<GuiObject, SaveObject>> keySettingMap 
    			= new LinkedHashMap<String, Abstract_Setting<GuiObject, SaveObject>>();
    // ------------------------------------------------------------------------
    // Constructor
    //
    protected Abstract_Group(GuiObject guiObject) {
        initSettingList(guiObject);
    }
    // ========================================================================
	// Abstract Methods
	//
    protected abstract void initSettingList(GuiObject guiObject);

    // ========================================================================
	// Public and packages Methods
	//
    public void actionGetGuiValue(GuiObject guiObject) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.setGuiOption(guiObject);
        }
    }
    public void actionGetGameValue(GuiObject guiObject) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.setGameOption(guiObject);
        }
    }
    public void actionGuiToFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionUiToFile(userSettingKey);
        }
    }
    public void actionGameToFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionGameToFile(userSettingKey);
        }
    }
    public void actionInitialToFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionInitialToFile(userSettingKey);
        }
    }
    public void actionGuiUpdateFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionUiUpdateFile(userSettingKey);
        }
    }
    public void actionGameUpdateFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionGameUpdateFile(userSettingKey);
        }
    }
    public void actionInitialUpdateFile(String userSettingKey) {
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.actionInitialUpdateFile(userSettingKey);
        }
    }
    public LinkedHashSet<String> keyList() {
        return new LinkedHashSet<String>(keySettingMap.keySet());
     }
    public Abstract_Setting<GuiObject, SaveObject> getSetting(String key) {
        return keySettingMap.get(key);
    }
    public String toPrint(List<String> userSettingKeys, boolean cleanUserKeys) {
        String out = "";
        for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
        	if (cleanUserKeys) {
        		out += setting.toPrint(userSettingKeys);
        	}
        	else {
        		List<String> keySet = new ArrayList<String>(userSettingKeys);
        		keySet.addAll(setting.getLabelList());
        		out += setting.toPrint(keySet);
        	}
        }
        return out;
    }
    // ========================================================================
	// Initialization Methods
	//
    protected void addSetting(Abstract_Setting<GuiObject, SaveObject> setting) {
        keySettingMap.put(setting.settingID().toTest(), setting);
    }
    protected void resetAllUserSettings() {
        for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.resetUserSettings();
        }
    }
    public void overrideGuiParameters (GuiObject guiObject, List<String> settingKeys) {
    	// Loop thru settings
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
    		setting.overrideGuiParameters(guiObject, settingKeys);
        }
    }
    public void changeGameFileParameters (SaveObject saveObject, List<String> settingKeys) {
    	// Loop thru settings
    	for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
    		setting.changeGameFileParameters(saveObject, settingKeys);
        }
    }
    public void setGuiParametersToInitial(GuiObject guiObject) {
        for (Abstract_Setting<GuiObject, SaveObject> setting : keySettingMap.values() ) {
            setting.putInitialToGUI(guiObject);
        }
    }
}
