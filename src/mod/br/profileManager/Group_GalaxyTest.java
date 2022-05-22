package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mod.br.profileManager.Group_Galaxy.Difficulty;
import mod.br.profileManager.Group_Galaxy.GalaxyShape;
import mod.br.profileManager.Group_Galaxy.GalaxySize;
import mod.br.profileManager.Group_Galaxy.NbOpponent;
import mod.br.profileManager.Group_Galaxy.OpponentAI;

class Group_GalaxyTest {
	private ClientClassesTest cct = new ClientClassesTest();

	private String NL = System.lineSeparator();
	
	private String galaxyShapePrt = 
			"#  " + NL +
			"# ------------- Galaxy Options -------------" + NL +
			"#  " + NL +
			"¦ PARAMETER         : GALAXY SHAPE" + NL +
			"# OPTIONS           : [Rectangle, Ellipse, Spiral, Text, Cluster, Swirlclusters, Grid, Spiralarms, Maze, Shuriken, Bullseye, Lorenz, Fractal]" + NL +
			"# Initial/GUI/Game  : Rectangle / Rectangle / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String galaxySizePrt = 
			"¦ PARAMETER         : GALAXY SIZE" + NL +
			"# OPTIONS           : [Tiny, Small, Small2, Average, Average2, Large, Large2, Huge, Huge2, Massive, Massive2, Massive3, Massive4, Massive5, Insane, Ludicrous, Maximum]" + NL +
			"# Initial/GUI/Game  : Small / Small / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String difficultyPrt = 
			"¦ PARAMETER         : DIFFICULTY" + NL +
			"# OPTIONS           : [Easiest, Easier, Easy, Normal, Hard, Harder, Hardest, Custom]" + NL +
			"# Initial/GUI/Game  : Normal / Normal / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String opponentAIPrt = 
			"¦ PARAMETER         : OPPONENT AI" + NL +
			"# OPTIONS           : [Base, Xilmi, Cruel, Unfair, Random, Allrandom, Select]" + NL +
			"# Initial/GUI/Game  : Cruel / Cruel / " + NL + 
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL +
			"" + NL;

	private String nbOpponentPrt = 
			"¦ PARAMETER         : NB OPPONENTS" + NL +
			"# OPTIONS           : [Min=0, Max=15, Rnd Low=1, Rnd Up=15]" + NL +
			"# Initial/GUI/Game  : 4 / 4 / "  + NL +
			"¦ LOCAL ENABLE      : ALL" + NL +
			"Profile 1           : " + NL +
			"Random              : " + NL+
			"" + NL;
	
	@Test void GalaxyShape_ClientClasses() {
		GalaxyShape param;
		param = new GalaxyShape(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = galaxyShapePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void GalaxySize_ClientClasses() {
		GalaxySize param;
		param = new GalaxySize(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = galaxySizePrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Difficulty_ClientClasses() {
		Difficulty param;
		param = new Difficulty(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = difficultyPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void OpponentAI_ClientClasses() {
		OpponentAI param;
		param = new OpponentAI(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = opponentAIPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void NbOpponent_ClientClasses() {
		NbOpponent param;
		param = new NbOpponent(cct);
		String out = param.toString(List.of("Profile 1", "Random"));
		String shouldBe = nbOpponentPrt;
		assertEquals(shouldBe, out, "should have been equals");
	}

	@Test void Group_Galaxy_ClientClasses() {
		Group_Galaxy group;
		group = new Group_Galaxy(cct);
		String out = group.toString(List.of("Profile 1", "Random"));
		String shouldBe = galaxyShapePrt + galaxySizePrt 
				+ difficultyPrt + opponentAIPrt + nbOpponentPrt;
		assertEquals(shouldBe, out, "should have been equals");
		// System.out.println(out);
	}

	@Test void profileList_None() {
		Group_Galaxy group;
		group = new Group_Galaxy(cct);
		assertEquals("[GALAXY SHAPE, GALAXY SIZE, DIFFICULTY, OPPONENT AI, NB OPPONENTS]",
				group.profileList().toString(),
				"should have been a long list");
	}

	@Test void getParameter_String() {
		String param;
		Group_Galaxy group;
		group = new Group_Galaxy(cct);
		param = "GALAXY SHAPE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "GALAXY SIZE";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "DIFFICULTY";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "OPPONENT AI";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
		param = "NB OPPONENTS";
		assertEquals(param,
				group.getParameter(param).getParameterName(),
				"should have retrieved the parameter name");
	}

}
