package rotp.mod.br.alteration;

import mod.br.alteration.GalaxySpacing;

public class GalaxyOptions {
	
	public static void initSpacing(int maxStars, int numOpps, float sysBuffer) {
		GalaxySpacing.initSpacing(maxStars, numOpps, sysBuffer);
	}
     public static float getMinEmpireBuffer() {
         return GalaxySpacing.getMinEmpireBuffer();
    }
	public static float getMaxMinEmpireBuffer() {
         return GalaxySpacing.getMaxMinEmpireBuffer();
    }
	public static float getMinOrionBuffer() {
		return GalaxySpacing.getMinOrionBuffer();
    }	
}
