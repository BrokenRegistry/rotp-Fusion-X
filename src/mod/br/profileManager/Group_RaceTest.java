package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mod.br.profileManager.Group_Race.PlayerColor;
import mod.br.profileManager.Group_Race.PlayerRace;

class Group_RaceTest {
	private ClientClassesTest cct = new ClientClassesTest();
	private String NL = System.lineSeparator();
	
	private String racePrt = 
			";  " + NL +
			"; --------- Races Game Options ---------" + NL +
			";  " + NL +
			"¦ Parameter     : PLAYER RACE" + NL +
			"; Options       : [Human, Alkari, Silicoid, Mrrshan, Klackon, Meklar, Psilon," + NL +
			";   \" \"         : Darlok, Sakkra, Bulrathi]" + NL +
			"¦ History       : Current : Human / Last :  / Initial : Human / Default : Human / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"; ---- Available for changes in game saves" + NL +
			"" + NL;

	private String colorPrt = 
			"¦ Parameter     : PLAYER COLOR" + NL +
			"; Options       : [Red, Green, Yellow, Blue, Orange, Purple, Aqua, Fuchsia," + NL +
			";   \" \"         : Brown, White, Lime, Grey, Plum, Light Blue, Mint, Olive]" + NL +
			"¦ History       : Current : Blue / Last :  / Initial : Blue / Default : Red / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"; ---- Available for changes in game saves" + NL + 
			"" + NL;
	
	@Test void PlayerRace_ClientClasses() {
		PlayerRace param;
		param = new PlayerRace(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = racePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void PlayerColor_ClientClasses() {
		PlayerColor param;
		param = new PlayerColor(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = colorPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void profileList_None() {
		Group_Race group;
		group = new Group_Race(cct);
		assertEquals("[PLAYER RACE, PLAYER COLOR, PLAYER HOMEWORLD, PLAYER NAME]",
				group.profileList().toString(),
				"should have been \"[PLAYER RACE, PLAYER COLOR]\"");
	}

	@Test void getParameter_String() {
		String param;
		Group_Race group;
		group = new Group_Race(cct);
		param = "PLAYER RACE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "PLAYER COLOR";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
	}

}
