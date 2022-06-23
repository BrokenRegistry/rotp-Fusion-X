package mod.br.AddOns;

import static mod.br.AddOns.StarsOptions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StarsOptionsTest {

	private List<Float> same  = List.of( 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f );
	private List<Float> two   = List.of( 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f );
	private List<Float> old   = List.of( -.50f, -.15f, -.1f, -.05f, -.05f, -.15f );
	private float[] youngPcts = { .20f, .40f, .55f, .85f, .95f, 1.0f };
	private float[] oldPcts   = { .50f, .65f, .75f, .80f, .85f, 1.0f };

	
	@Test void modifyStarProbability_Same() {
		probabilityModifier(STARS_KEY).selectedModifierList(same);;
		assertTrue(same.containsAll(probabilityModifier(STARS_KEY).selectedModifierList()));
		assertEquals (Arrays.toString(youngPcts), 
				Arrays.toString(probabilityModifier(STARS_KEY).modifyProbability(youngPcts))
				, "should have retrieved the Array");
	}
	
	@Test void modifyStarProbability_Two() {
		probabilityModifier(STARS_KEY).selectedModifierList(two);
		assertEquals (Arrays.toString(youngPcts), 
				Arrays.toString(probabilityModifier(STARS_KEY).modifyProbability(youngPcts))
				, "should have retrieved the Array");
	}

	@Test void modifyStarProbability_Change() {
		probabilityModifier(STARS_KEY).selectedModifierList(old);
		assertEquals (Arrays.toString(oldPcts), 
				Arrays.toString(probabilityModifier(STARS_KEY).modifyProbability(youngPcts))
				, "should have retrieved the Array");
	}


}
