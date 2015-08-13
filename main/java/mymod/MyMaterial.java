package mymod;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MyMaterial extends Material {

	public MyMaterial(MapColor color, boolean needsTool, boolean canBurn) {
		super(color);
		if (needsTool) this.setRequiresTool();
		if (canBurn) this.setBurning();
	}

	

}
