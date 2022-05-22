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
			"#  " + NL +
			"# --------- Races Game Options ---------" + NL +
			"#  " + NL +
			"¦ PARAMETER         : PLAYER RACE" + NL +
			"# OPTIONS           : [Human, Alkari, Silicoid, Mrrshan, Klackon, " + 
			                       "Meklar, Psilon, Darlok, Sakkra, Bulrathi]" + NL +
			"# Initial/GUI/Game  : Human / Human / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String colorPrt = 
			"¦ PARAMETER         : PLAYER COLOR" + NL +
			"# OPTIONS           : [Red, Green, Yellow, Blue, Orange, Purple, " + 
			                       "Aqua, Fuchsia, Brown, White, Lime, Grey, " +
			                       "Plum, Light Blue, Mint, Olive]" + NL +
			"# Initial/GUI/Game  : Blue / Blue / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"# ---- Available for changes in game saves" + NL + 
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

	@Test void Group_Race_ClientClasses() {
		Group_Race group;
		group = new Group_Race(cct);
		String out = group.toString(List.of("Profile 1", "Random"));
		String shouldBe = racePrt + colorPrt;
		assertEquals(shouldBe, out, "should have been equals");
		// System.out.println(out);
	}

	@Test void profileList_None() {
		Group_Race group;
		group = new Group_Race(cct);
		assertEquals("[PLAYER RACE, PLAYER COLOR]",
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
