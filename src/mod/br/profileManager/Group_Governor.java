
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

package mod.br.profileManager;

import java.util.List;

import br.profileManager.src.main.java.Abstract_Group;
import br.profileManager.src.main.java.Abstract_Parameter;
import br.profileManager.src.main.java.Valid_Boolean;
import br.profileManager.src.main.java.Valid_Integer;
import br.profileManager.src.main.java.ValidationCriteria;


class Group_Governor extends Abstract_Group <ClientClasses> {
	
	// TODO ALL

	Group_Governor(ClientClasses go) {
	   super(go);
	}
	@Override
	protected void initSettingList(ClientClasses go) {
		addParameter(new GovernorOnByDefault(go));
		addParameter(new AutospendOnByDefault(go));
		addParameter(new DefaultMaxBases(go));
		addParameter(new DivertExcessToResearch(go));
	}

	// ==============================================================
	// GOVERNOR ON BY DEFAULT
	//
	public static class GovernorOnByDefault extends 
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		GovernorOnByDefault(ClientClasses go) { 
			super("GOVERNOR ON BY DEFAULT", new Valid_Boolean());
		}
		
		@Override public Boolean getFromGame (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}
		
		@Override public void putToGame(ClientClasses go, Boolean userOption) {

		}	

		@Override public Boolean getFromUI (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGUI(ClientClasses go, Boolean userOption) {
		}
		
		@Override public void initComments() {
			setHeadComments(
				" " + NL +
				"------------- Gouvernor Options -------------" + NL +
				" ");
		}
	}
	// ==============================================================
	// AUTOSPEND ON BY DEFAULT
	//
	public static class AutospendOnByDefault extends
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		AutospendOnByDefault(ClientClasses go) {
			super("AUTOSPEND ON BY DEFAULT", new Valid_Boolean());
		}

		@Override public Boolean getFromGame (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGame(ClientClasses go, Boolean userOption) {

		}	

		@Override public Boolean getFromUI (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGUI(ClientClasses go, Boolean userOption) {
		}

		@Override public void initComments() {}
	}
	// ==============================================================
	// DEFAULT MAX BASES
	//
	static class Valid_DefaultMaxBases extends Valid_Integer {

		// ========== Constructors and initializer ==========
		//
		Valid_DefaultMaxBases() {
			super();
			init();
		}
		
		Valid_DefaultMaxBases(List<Integer> options) {
			super(options);
			init();
		}
		
		private void initCriteria() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false));
		}
		
		private void init() {
			initCriteria();
		}
	}
	
	// ========== Parameter Section ==========
	//
	public static class DefaultMaxBases extends 
			Abstract_Parameter <Integer, Valid_DefaultMaxBases, ClientClasses> {

		DefaultMaxBases(ClientClasses go) { 
			super("DEFAULT MAX BASES", new Valid_DefaultMaxBases());
		}

		@Override public Integer getFromGame (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGame(ClientClasses go, Integer userOption) {

		}	

		@Override public Integer getFromUI (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGUI(ClientClasses go, Integer userOption) {
		}

		@Override public void initComments() {}
	}
	// ==============================================================
	// DIVERT EXCESS TO RESEARCH
	//
	public static class DivertExcessToResearch extends
			Abstract_Parameter <Boolean, Valid_Boolean, ClientClasses> {

		DivertExcessToResearch(ClientClasses go) {
			super("DIVERT EXCESS TO RESEARCH", new Valid_Boolean());
		}

		@Override public Boolean getFromGame (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGame(ClientClasses go, Boolean userOption) {

		}	

		@Override public Boolean getFromUI (ClientClasses go) {
			return null;
//			return go.getGuiObject().selectedGalaxyShape();
		}

		@Override public void putToGUI(ClientClasses go, Boolean userOption) {
		}

		@Override public void initComments() {}
	}
}
