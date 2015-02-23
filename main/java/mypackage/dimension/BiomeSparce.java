package mypackage.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenIcePath;

public class BiomeSparce extends MyBiomeGenBase {

	public BiomeSparce(int p_i1971_1_, Block block) {
		super(p_i1971_1_, block);
		this.setBiomeName("Custom Biome");
		this.setHeight(BiomeGenBase.height_HighPlateaus);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.baseBlock = block;
		this.setTemperatureRainfall(-0.5F, 0.5F);
		this.setEnableSnow();
	}
	
	public BiomeSparce(int i) {
		this(i, Blocks.stone);
	}
	
	public void decorate(World w, Random r, int x, int z)
    {
        super.decorate(w, r, x, z);

        if (r.nextInt(16) == 0)
        {
            int k = x + r.nextInt(16) + 8;
            int h = 110 + r.nextInt(80);
            int l = z + r.nextInt(16) + 8;
            WorldGenMoon moon = new WorldGenMoon();
            moon.generate(w, r, k, h, l);
        }
    }

}
