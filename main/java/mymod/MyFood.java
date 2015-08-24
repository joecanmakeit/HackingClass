package mymod;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class MyFood extends ItemFood {
	
	String name;
	
	public ArrayList<PotionData> potionEffects = new ArrayList<PotionData>();
	
	public class PotionData {
	    private int id;
	    private int dur;
	    private int amp;
	    private float prob;
	    
	    public PotionData(int id, int dur, int amp, float prob) {
	    	this.id = id;
	    	this.dur = dur;
	    	this.amp = amp;
	    	this.prob = prob;
	    }
	}
	
	public MyFood(int hunger, float saturation, boolean dogfood) {
		super(hunger, saturation, dogfood);
		//this.name = MyMod.class.getDeclaredFields()[MyMod.varCount++].getName();
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(MyMod.modid + ":" + this.name);
	}
	
	public void addPotionEffect(Potion potionEffect, int duration, int amplifier, float probability) {
		this.potionEffects.add(new PotionData(potionEffect.id, duration, amplifier, probability));
	}
	
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
        	for (PotionData p : potionEffects) {
        		if (world.rand.nextFloat() < p.prob){ 
        			player.addPotionEffect(new PotionEffect(p.id, p.dur * 20, p.amp));
        		}
        	}
        }
    }
}
