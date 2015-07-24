package mymod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MyItem extends Item {
	
	String name;
	
	public MyItem(CreativeTabs tab) {
		super();
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(tab);
		this.setTextureName(MyMod.modid + ":" + this.name);
	}

}