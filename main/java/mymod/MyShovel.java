package mymod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class MyShovel extends ItemSpade {
	
	String name;
	
	public MyShovel(ToolMaterial material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(MyMod.modid + ":" + this.name);
	}

}
