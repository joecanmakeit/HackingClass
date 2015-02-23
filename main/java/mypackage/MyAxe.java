package mypackage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class MyAxe extends ItemAxe {
	
	String name;

	public MyAxe(ToolMaterial material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("myassets:" + this.name);
	}

}
