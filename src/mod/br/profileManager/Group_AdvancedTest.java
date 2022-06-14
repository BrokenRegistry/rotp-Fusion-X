package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mod.br.profileManager.Group_Advanced.*;



class Group_AdvancedTest {
	private ClientClassesTest cct = new ClientClassesTest();

	private String NL = System.lineSeparator();
	
	private String galaxyAgePrt = 
			";  " + NL +
			"; ----------- Advanced Game Options -----------" + NL +
			";  " + NL +
			"¦ Parameter     : GALAXY AGE" + NL +
			"; Options       : [Young, Normal, Old]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String starDensityPrt = 
			"¦ Parameter     : STAR DENSITY" + NL +
			"; Options       : [Lowest, Lower, Low, Normal, High, Higher, Highest]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String nebulaePrt = 
			"¦ Parameter     : NEBULAE" + NL +
			"; Options       : [None, Rare, Uncommon, Normal, Common, Frequent]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String planetQualityPrt = 
			"¦ Parameter     : PLANET QUALITY" + NL +
			"; Options       : [Normal, Larger, Richer]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String terraformingPrt = 
			"¦ Parameter     : TERRAFORMING" + NL +
			"; Options       : [Normal, Reduced, None]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"" + NL;
	
	private String randomEventsPrt = 
			"¦ Parameter     : RANDOM EVENTS" + NL +
			"; Options       : [On, Monsters, Off]" + NL +
			"¦ History       : Current : Monsters / Last :  / Initial : Monsters / Default : On / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"; ---- Available for changes in game saves" + NL +
			"" + NL;

	private String AIHostilityPrt = 
			"¦ Parameter     : AI HOSTILITY" + NL +
			"; Options       : [Lowest, Lower, Low, Normal, High, Higher, Highest]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String councilPrt = 
			"¦ Parameter     : COUNCIL" + NL +
			"; Options       : [Immediate, Rebels, None]" + NL +
			"¦ History       : Current : Rebels / Last :  / Initial : Rebels / Default : Rebels / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"; ---- Available for changes in game saves" + NL +
			"" + NL;

	private String randomizeAIPrt = 
			"¦ Parameter     : RANDOMIZE AI" + NL +
			"; Options       : [None, Personality, Ability, Both]" + NL +
			"¦ History       : Current : None / Last :  / Initial : None / Default : None / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String autoPlayPrt = 
			"¦ Parameter     : AUTOPLAY" + NL +
			"; Options       : [Off, Base, Xilmi, Cruel, Random]" + NL +
			"¦ History       : Current : Off / Last :  / Initial : Off / Default : Off / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"" + NL;
	
	private String researchPrt = 
			"¦ Parameter     : RESEARCH" + NL +
			"; Options       : [Normal, Slow, Slower, Slowest, Fast]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"" + NL;
	
	private String warpSpeedPrt = 
			"¦ Parameter     : WARP SPEED" + NL +
			"; Options       : [Normal, Fast]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"" + NL;
	
	private String fuelRangePrt = 
			"¦ Parameter     : FUEL RANGE" + NL +
			"; Options       : [Normal]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"" + NL;
	
	private String techTradingPrt = 
			"¦ Parameter     : TECH TRADING" + NL +
			"; Options       : [Yes, Allies, No]" + NL +
			"¦ History       : Current : Yes / Last :  / Initial : Yes / Default : Yes / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"; ---- Available for changes in game saves" + NL +
			"" + NL;
	
	private String colonizingPrt = 
			"¦ Parameter     : COLONIZING" + NL +
			"; Options       : [Normal, Restricted]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Normal / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
			"; ---- Available for changes in game saves" + NL +
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
