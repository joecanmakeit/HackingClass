package mypackage.dimension;

import mypackage.MyMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldTwo extends MyWorldBase {

	@Override
	protected int customID() {
		return MyMod.worldTwoID;
	}

	@Override
	protected BiomeGenBase[] customBiomes() {
		BiomeGenBase[] biomes = {
				MyMod.sparce,
				BiomeGenBase.ocean
		};
		return biomes;
	}
	
	@Override
	protected int customBiomeSize() {
		return 3;
	}

	@Override
	protected String customSkyColor() {
		return "#CCDDCC";
	}

	@Override
	protected String customFogColor() {
		return "#003300";
	}

	@Override
	protected String customSunriseSunsetColor() {
		return "#00FF33";
	}

	@Override
	protected float customDaySpeed() {
		return 0.5F;
	}

	@Override
	protected String customCloudColor() {
		return "#00BBFF";
	}

	@Override
	protected Block getBaseBlock() {
		return Blocks.diamond_block;
	}

}
