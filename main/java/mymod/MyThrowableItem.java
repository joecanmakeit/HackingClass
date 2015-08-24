package mymod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class MyThrowableItem extends MyItem {
	
	public Class entityClass;
	
	public MyThrowableItem(CreativeTabs tab, Class entity) {
		super(tab);
		this.entityClass = entity;
		if (!MyMod.throwableClasses.contains(entity)) {
			MyMod.throwableClasses.add(entity);
		}
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
        	Constructor<? extends MyThrowableEntity>[] ctors = entityClass.getDeclaredConstructors();
        	Constructor<? extends MyThrowableEntity> ctor = null;
        	for (int i = 0; i < ctors.length; i++) {
        		ctor = ctors[i];
        		if (ctor.getParameterTypes().length == 2) break;
        	}
        	try {
				par2World.spawnEntityInWorld(ctor.newInstance(par2World, par3EntityPlayer));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
            //par2World.spawnEntityInWorld(new MyThrowableEntity(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
    }
	
}
