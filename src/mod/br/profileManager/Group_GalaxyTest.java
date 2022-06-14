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
			";  " + NL +
			"; ------------- Galaxy Options -------------" + NL +
			";  " + NL +
			"¦ Parameter     : GALAXY SHAPE" + NL +
			"; Options       : [Rectangle, Ellipse, Spiral, Text, Cluster, Swirlclusters," + NL +
			";   \" \"         : Grid, Spiralarms, Maze, Shuriken, Bullseye, Lorenz, Fractal]" + NL +
			"¦ History       : Current : Rectangle / Last :  / Initial : Rectangle / Default : Rectangle / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String galaxySizePrt = 
			"¦ Parameter     : GALAXY SIZE" + NL +
			"; Options       : [Tiny, Small, Small2, Average, Average2, Large, Large2, Huge," + NL +
			";   \" \"         : Huge2, Massive, Massive2, Massive3, Massive4, Massive5," + NL +
			";   \" \"         : Insane, Ludicrous, Maximum]" + NL +
			"¦ History       : Current : Small / Last :  / Initial : Small / Default : Small / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String difficultyPrt = 
			"¦ Parameter     : DIFFICULTY" + NL +
			"; Options       : [Easiest, Easier, Easy, Normal, Hard, Harder, Hardest, Custom]" + NL +
			"¦ History       : Current : Normal / Last :  / Initial : Normal / Default : Easy / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String opponentAIPrt = 
			"¦ Parameter     : OPPONENT AI" + NL +
			"; Options       : [Base, Xilmi, Cruel, Unfair, Random, Allrandom, Select]" + NL +
			"¦ History       : Current : Cruel / Last :  / Initial : Cruel / Default : Base / Game :" + NL + 
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL +
			"" + NL;

	private String nbOpponentPrt = 
			"¦ Parameter     : NB OPPONENTS" + NL +
			"; Options       : [Min=0, Max=15, Rnd Low=1, Rnd Up=15]" + NL +
			"¦ History       : Current : 4 / Last :  / Initial : 4 / Default : 3 / Game :"  + NL +
			"¦ LOCAL ENABLE  : All         ; [No, All, Write, Load]" + NL +
			"Profile 1       : " + NL +
			"Random          : " + NL+
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
