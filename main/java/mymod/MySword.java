package mymod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class MySword extends ItemSword {

	String name;

	public MySword(ToolMaterial material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setTextureName(MyMod.modid + ":" + this.name);
	}
}
