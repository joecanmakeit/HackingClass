package mymod.dimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class MyGenLayer extends GenLayer {

	public MyGenLayer(long seed) {
		super(seed);
	}

	public static GenLayer[] makeTheWorld(long seed) {

		GenLayer biomes = new MyGenLayerBiomes(1L);

		// more GenLayerZoom = bigger biomes
		biomes = new GenLayerZoom(1000L, biomes);
		//biomes = new GenLayerZoom(1001L, biomes);
		//biomes = new GenLayerZoom(1002L, biomes);
		//biomes = new GenLayerZoom(1003L, biomes);
		//biomes = new GenLayerZoom(1004L, biomes);
		//biomes = new GenLayerZoom(1005L, biomes);

		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, genlayervoronoizoom};
	}
	
	@SuppressWarnings("unused")
	public static GenLayer[] makeTheWorld(long seed, BiomeGenBase[] b, int biomeSize) {

		if (biomeSize < 1) biomeSize = 1;
		GenLayer biomes = new MyGenLayerBiomes(1L, b);

		// more GenLayerZoom = bigger biomes
		long l = 1000L;
		for (int i = 0; i < biomeSize; i++) {
			biomes = new GenLayerZoom(1000L, biomes);
			l += 1L;
		}

		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, genlayervoronoizoom};
	}

}
