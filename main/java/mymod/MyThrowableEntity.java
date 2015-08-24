package mymod;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public abstract class MyThrowableEntity extends EntityThrowable {
	
	public MyThrowableEntity(World a, EntityLivingBase b) {
		super(a, b);
	}
	
	public MyThrowableEntity(World a) {
		super(a);
	}

	protected abstract void onImpact(MovingObjectPosition var1);

}
