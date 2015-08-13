package mymod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class MyHoe extends ItemHoe {
	
	String name;
	
	public MyHoe(ToolMaterial material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(MyMod.modid + ":" + this.name);
	}

}
