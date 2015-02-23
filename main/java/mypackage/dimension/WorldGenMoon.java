package mypackage.dimension;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMoon extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r,
			int x, int y, int z) {
		int rad = r.nextInt(6) + 2;
		for (int i = -rad; i < rad; i++) {
			for (int j = -rad; j < rad; j++) {
				for (int k = -rad; k < rad; k++) {
					if (Math.sqrt(i*i + j*j + k*k) < rad) {
						w.setBlock(x+i, y+j, z+k, Blocks.gold_block, 0, 2);
					}
				}
			}
		}
		return true;
	}

}
