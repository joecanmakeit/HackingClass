package mypackage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;

public class MyHoe extends ItemHoe {
	
	String name;
	
	public MyHoe(ToolMaterial material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("myassets:" + this.name);
	}

}
