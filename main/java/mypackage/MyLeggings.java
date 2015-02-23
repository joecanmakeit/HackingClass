package mypackage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class MyLeggings extends ItemArmor {

	String name;
	String material;

	//Constructor
	//Accepts three arguments:
	//material - an ArmorMaterial object which is the material the armor item is made of.
	//tier - an integer which is the 'tier' or level of the armor:
		//0 - cloth (leather)
		//1 - chain
		//2 - iron
		//3 - gold
		//4 - diamond
		//5+ - user-made armor
	public MyLeggings(ArmorMaterial material) {
		super(material, MyMod.armorMap.get(material.toString()), 2);
		this.material = material.toString();
		this.name = MyMod.getNextName();
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setTextureName("myassets:" + this.name);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "myassets:textures/models/armor/" + this.material + "_layer_2.png";
	}

}