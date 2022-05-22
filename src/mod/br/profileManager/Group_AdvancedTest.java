package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mod.br.profileManager.Group_Advanced.*;



class Group_AdvancedTest {
	private ClientClassesTest cct = new ClientClassesTest();

	private String NL = System.lineSeparator();
	
	private String galaxyAgePrt = 
			"#  " + NL +
			"# ----------- Advanced Game Options -----------" + NL +
			"#  " + NL +
			"¦ PARAMETER         : GALAXY AGE" + NL +
			"# OPTIONS           : [Young, Normal, Old]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String starDensityPrt = 
			"¦ PARAMETER         : STAR DENSITY" + NL +
			"# OPTIONS           : [Lowest, Lower, Low, Normal, High, Higher, Highest]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String nebulaePrt = 
			"¦ PARAMETER         : NEBULAE" + NL +
			"# OPTIONS           : [None, Rare, Uncommon, Normal, Common, Frequent]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String planetQualityPrt = 
			"¦ PARAMETER         : PLANET QUALITY" + NL +
			"# OPTIONS           : [Normal, Larger, Richer]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String terraformingPrt = 
			"¦ PARAMETER         : TERRAFORMING" + NL +
			"# OPTIONS           : [Normal, Reduced, None]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	private String randomEventsPrt = 
			"¦ PARAMETER         : RANDOM EVENTS" + NL +
			"# OPTIONS           : [On, Monsters, Off]" + NL +
			"# Initial/GUI/Game  : Monsters / Monsters / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"# ---- Available for changes in game saves" + NL +
			"" + NL;

	private String AIHostilityPrt = 
			"¦ PARAMETER         : AI HOSTILITY" + NL +
			"# OPTIONS           : [Lowest, Lower, Low, Normal, High, Higher, Highest]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String councilPrt = 
			"¦ PARAMETER         : COUNCIL" + NL +
			"# OPTIONS           : [Immediate, Rebels, None]" + NL +
			"# Initial/GUI/Game  : Rebels / Rebels / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"# ---- Available for changes in game saves" + NL +
			"" + NL;

	private String randomizeAIPrt = 
			"¦ PARAMETER         : RANDOMIZE AI" + NL +
			"# OPTIONS           : [None, Personality, Ability, Both]" + NL +
			"# Initial/GUI/Game  : None / None / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String autoPlayPrt = 
			"¦ PARAMETER         : AUTOPLAY" + NL +
			"# OPTIONS           : [Off, Base, Xilmi, Cruel, Random]" + NL +
			"# Initial/GUI/Game  : Off / Off / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	private String researchPrt = 
			"¦ PARAMETER         : RESEARCH" + NL +
			"# OPTIONS           : [Normal, Slow, Slower, Slowest, Fast]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	private String warpSpeedPrt = 
			"¦ PARAMETER         : WARP SPEED" + NL +
			"# OPTIONS           : [Normal, Fast]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	private String fuelRangePrt = 
			"¦ PARAMETER         : FUEL RANGE" + NL +
			"# OPTIONS           : [Normal]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	private String techTradingPrt = 
			"¦ PARAMETER         : TECH TRADING" + NL +
			"# OPTIONS           : [Yes, Allies, No]" + NL +
			"# Initial/GUI/Game  : Yes / Yes / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"# ---- Available for changes in game saves" + NL +
			"" + NL;
	
	private String colonizingPrt = 
			"¦ PARAMETER         : COLONIZING" + NL +
			"# OPTIONS           : [Normal, Restricted]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"# ---- Available for changes in game saves" + NL +
			"" + NL;
	
	@Test void GalaxyAge_ClientClasses() {
		GalaxyAge param;
		param = new GalaxyAge(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = galaxyAgePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void StarDensity_ClientClasses() {
		StarDensity param;
		param = new StarDensity(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = starDensityPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Nebulae_ClientClasses() {
		Nebulae param;
		param = new Nebulae(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = nebulaePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void PlanetQuality_ClientClasses() {
		PlanetQuality param;
		param = new PlanetQuality(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = planetQualityPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Terraforming_ClientClasses() {
		Terraforming param;
		param = new Terraforming(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = terraformingPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void RandomEvents_ClientClasses() {
		RandomEvents param;
		param = new RandomEvents(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = randomEventsPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void AIHostility_ClientClasses() {
		AIHostility param;
		param = new AIHostility(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = AIHostilityPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Council_ClientClasses() {
		Council param;
		param = new Council(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = councilPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void RandomizeAI_ClientClasses() {
		RandomizeAI param;
		param = new RandomizeAI(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = randomizeAIPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void AutoPlay_ClientClasses() {
		AutoPlay param;
		param = new AutoPlay(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = autoPlayPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Research_ClientClasses() {
		Research param;
		param = new Research(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = researchPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void WarpSpeed_ClientClasses() {
		WarpSpeed param;
		param = new WarpSpeed(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = warpSpeedPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

		@Test void FuelRange_ClientClasses() {
			FuelRange param;
		param = new FuelRange(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = fuelRangePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void TechTrading_ClientClasses() {
		TechTrading param;
		param = new TechTrading(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = techTradingPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Colonizing_ClientClasses() {
		Colonizing param;
		param = new Colonizing(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = colonizingPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Group_Advanced_ClientClasses() {
		Group_Advanced group;
		group = new Group_Advanced(cct);
		String out = group.toString(List.of("Profile 1", "Random"));
		String shouldBe = galaxyAgePrt + starDensityPrt + nebulaePrt
				+ planetQualityPrt + terraformingPrt + randomEventsPrt
				+ AIHostilityPrt + councilPrt + randomizeAIPrt
				+ autoPlayPrt + researchPrt + warpSpeedPrt
				+ fuelRangePrt + techTradingPrt + colonizingPrt
				;
		assertEquals(shouldBe, out, "should have been equals");
		// System.out.println(out);
	}

	@Test void profileList_None() {
		Group_Advanced group;
		group = new Group_Advanced(cct);
		assertEquals("[GALAXY AGE, STAR DENSITY, NEBULAE, PLANET QUALITY, TERRAFORMING, RANDOM EVENTS, AI HOSTILITY, COUNCIL, RANDOMIZE AI, AUTOPLAY, RESEARCH, WARP SPEED, FUEL RANGE, TECH TRADING, COLONIZING]",
				group.profileList().toString(),
				"should have been ... a long list ...");
	}

	@Test void getParameter_String() {
		String param;
		Group_Advanced group;
		group = new Group_Advanced(cct);
		param = "GALAXY AGE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "STAR DENSITY";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "NEBULAE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "PLANET QUALITY";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "TERRAFORMING";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "RANDOM EVENTS";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "AI HOSTILITY";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "COUNCIL";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "RANDOMIZE AI";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "AUTOPLAY";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "RESEARCH";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "WARP SPEED";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "FUEL RANGE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "TECH TRADING";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "COLONIZING";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
	}

}
