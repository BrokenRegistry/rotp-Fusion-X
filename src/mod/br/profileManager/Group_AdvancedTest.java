package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mod.br.profileManager.Group_Advanced.*;



class Group_AdvancedTest {
	private ClientClassesTest cct = new ClientClassesTest();

	
	private String galaxyAgePrt = 
			";  \r\n"
			+ "; ----------- Advanced Game Options -----------\r\n"
			+ ";  \r\n"
			+ "¦ Parameter     : GALAXY AGE\r\n"
			+ "; Options       : [Young, Normal, Old]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String starDensityPrt = 
			"¦ Parameter     : STAR DENSITY\r\n"
			+ "; Options       : [Lowest, Lower, Low, Normal, High, Higher, Highest]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String nebulaePrt = 
			"¦ Parameter     : NEBULAE\r\n"
			+ "; Options       : [None, Rare, Uncommon, Normal, Common, Frequent]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String planetQualityPrt = 
			"¦ Parameter     : PLANET QUALITY\r\n"
			+ "; Options       : [Normal, Larger, Richer]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String terraformingPrt = 
			"¦ Parameter     : TERRAFORMING\r\n"
			+ "; Options       : [Normal, Reduced, None]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";
	
	private String randomEventsPrt = 
			"¦ Parameter     : RANDOM EVENTS\r\n"
			+ "; Options       : [On, Monsters, Off]\r\n"
			+ "¦ History       : Current : Monsters ¦ Last :  ¦ Initial : Monsters\r\n"
			+ "¦ History       : Default : On ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "; ---- Available for changes in game saves\r\n"
			+ "\r\n";

	private String AIHostilityPrt = 
			"¦ Parameter     : AI HOSTILITY\r\n"
			+ "; Options       : [Lowest, Lower, Low, Normal, High, Higher, Highest]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String councilPrt = 
			"¦ Parameter     : COUNCIL\r\n"
			+ "; Options       : [Immediate, Rebels, None]\r\n"
			+ "¦ History       : Current : Rebels ¦ Last :  ¦ Initial : Rebels\r\n"
			+ "¦ History       : Default : Rebels ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "; ---- Available for changes in game saves\r\n"
			+ "\r\n";

	private String randomizeAIPrt = 
			"¦ Parameter     : RANDOMIZE AI\r\n"
			+ "; Options       : [None, Personality, Ability, Both]\r\n"
			+ "¦ History       : Current : None ¦ Last :  ¦ Initial : None ¦ Default : None\r\n"
			+ "¦ History       : Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";

	private String autoPlayPrt = 
			"¦ Parameter     : AUTOPLAY\r\n"
			+ "; Options       : [Off, Base, Xilmi, Cruel, Random]\r\n"
			+ "¦ History       : Current : Off ¦ Last :  ¦ Initial : Off ¦ Default : Off\r\n"
			+ "¦ History       : Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";
	
	private String researchPrt = 
			"¦ Parameter     : RESEARCH\r\n"
			+ "; Options       : [Normal, Slow, Slower, Slowest, Fast]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";
	
	private String warpSpeedPrt = 
			"¦ Parameter     : WARP SPEED\r\n"
			+ "; Options       : [Normal, Fast]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";
	
	private String fuelRangePrt = 
			"¦ Parameter     : FUEL RANGE\r\n"
			+ "; Options       : [Normal]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "\r\n";
	
	private String techTradingPrt = 
			"¦ Parameter     : TECH TRADING\r\n"
			+ "; Options       : [Yes, Allies, No]\r\n"
			+ "¦ History       : Current : Yes ¦ Last :  ¦ Initial : Yes ¦ Default : Yes\r\n"
			+ "¦ History       : Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "; ---- Available for changes in game saves\r\n"
			+ "\r\n";
	
	private String colonizingPrt = 
			"¦ Parameter     : COLONIZING\r\n"
			+ "; Options       : [Normal, Restricted]\r\n"
			+ "¦ History       : Current : Normal ¦ Last :  ¦ Initial : Normal\r\n"
			+ "¦ History       : Default : Normal ¦ Game : \r\n"
			+ "¦ LOCAL ENABLE  : All         ; [No, All, Save, Load]\r\n"
			+ "Profile 1       : \r\n"
			+ "Random          : \r\n"
			+ "; ---- Available for changes in game saves\r\n"
			+ "\r\n";
	
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
