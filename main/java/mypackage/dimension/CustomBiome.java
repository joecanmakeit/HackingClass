package mypackage.dimension;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class CustomBiome extends MyBiomeBase {

	public CustomBiome(int i, Block base) {
		super(i, base);
		MyBiomeDecorator decorator = (MyBiomeDecorator)this.theBiomeDecorator;
		this.setHeight(new BiomeGenBase.Height(3.0F, 1.0F));
		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.ice;
		this.setTemperatureRainfall(-0.5F, 0.8F);
		this.enableSnow = true;
	}

}
