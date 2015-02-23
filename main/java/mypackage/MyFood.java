package mypackage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class MyFood extends ItemFood {
	
	String name;
	
	public MyFood(int hunger, float saturation, boolean dogfood) {
		super(hunger, saturation, dogfood);
		//this.name = MyMod.class.getDeclaredFields()[MyMod.varCount++].getName();
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName("myassets:" + this.name);
	}
}
