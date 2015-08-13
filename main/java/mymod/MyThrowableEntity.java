package mymod;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class MyThrowableEntity extends EntityThrowable {

	Random random = new Random();
	
	public MyThrowableEntity(World par1World) {
		super(par1World);
	}
	
	public MyThrowableEntity(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		this.setDead();
	}

}
