package mypackage.dimension;

import mypackage.MyMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class CustomWorld extends MyWorldBase {

	@Override
	protected int customID() {
		return MyMod.customWorldID;
	}

	@Override
	protected BiomeGenBase[] customBiomes() {
		BiomeGenBase[] biomes = {
				MyMod.customBiome,
				BiomeGenBase.frozenOcean
		};
		return biomes;
	}
	
	@Override
	protected int customBiomeSize() {
		return 3;
	}

	@Override
	protected String customSkyColor() {
		return "#0B60B5";
	}

	@Override
	protected String customFogColor() {
		return "#00264D";
	}

	@Override
	protected String customSunriseSunsetColor() {
		return "#BAF0FF";
	}

	@Override
	protected float customDaySpeed() {
		return 0.5F;
	}

	@Override
	protected String customCloudColor() {
		return "#BCC1C2";
	}

	@Override
	protected Block getBaseBlock() {
		return Blocks.stone;
	}

}
