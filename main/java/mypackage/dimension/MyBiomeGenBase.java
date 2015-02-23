package mypackage.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class MyBiomeGenBase extends BiomeGenBase {

	public Block baseBlock = Blocks.stone;
	
	public MyBiomeGenBase(int p_i1971_1_) {
		this(p_i1971_1_, Blocks.stone);
	}
	
	public MyBiomeGenBase(int i, Block base) {
		super(i);
		baseBlock = base;
		this.theBiomeDecorator = new MyBiomeDecorator(baseBlock);
	}
	
	public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) 
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(var7 / 3.0D + 3.0D + var2.nextDouble() * 0.25D);
        int i1 = var5 & 15;
        int j1 = var6 & 15;
        int k1 = var3.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + var2.nextInt(5))
            {
                var3[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = var3[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == baseBlock)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = baseBlock;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(var5, l1, var6) < 0.15F)
                                {
                                    block = Blocks.ice;
                                    b0 = 0;
                                }
                                else
                                {
                                    block = Blocks.water;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                var3[i2] = block;
                                var4[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = baseBlock;
                                var3[i2] = Blocks.gravel;
                            }
                            else
                            {
                                var3[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            var3[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = var2.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }

}
