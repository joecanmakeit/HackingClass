package mymod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class MakersThrowableEntity extends MyThrowableEntity {

	public MakersThrowableEntity(World a, EntityLivingBase b) {
		super(a, b);
	}
	
	public MakersThrowableEntity(World a) {
		super(a);
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		this.worldObj.setBlock(var1.blockX, var1.blockY, var1.blockZ, MyMod.makersBlock);
		this.setDead();

	}

}
