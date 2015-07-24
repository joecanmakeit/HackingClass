package mymod;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MyBlock extends Block {
	
	Random random = new Random();
	
	class Drop {
		Item item;
		Block block;
		int min = 1;
		int max = 1;
	}
	
	ArrayList<Drop> drops = new ArrayList<Drop>();
	
	String name;
	Item itemDropped;
	int minQuantity = 1;
	int maxQuantity = 1;
	
	public MyBlock(CreativeTabs tab, Material material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setBlockName(this.name);
		this.setBlockTextureName(MyMod.modid + ":" + this.name);
		this.setCreativeTab(tab);
	}
	
	public MyBlock addItemDropped(Item item) {
		return addItemDropped(item, 1, 1);
	}
	
	public MyBlock addItemDropped(Item item, int quantity) {
		return addItemDropped(item, quantity, quantity);
	}

	public MyBlock addItemDropped(Item item, int minQuantity, int maxQuantity) {
		Drop d = new Drop();
		d.item = item;
		d.min = minQuantity;
		d.max = maxQuantity;
		drops.add(d);
		return this;
	}
	
	public MyBlock addBlockDropped(Block block) {
		return addBlockDropped(block, 1, 1);
	}
	
	public MyBlock addBlockDropped(Block block, int quantity) {
		return addBlockDropped(block, quantity, quantity);
	}

	public MyBlock addBlockDropped(Block block, int minQuantity, int maxQuantity) {
		Drop d = new Drop();
		d.block = block;
		d.min = minQuantity;
		d.max = maxQuantity;
		drops.add(d);
		return this;
	}
	
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		for (Drop d : drops) {
			if (d.item != null) {
				int num = random.nextInt(d.max-d.min+1)+d.min;
				for (int i=0; i<num; i++) {
					result.add(new ItemStack(d.item));
				}
			}
			else {
				int num = random.nextInt(d.max-d.min+1)+d.min;
				for (int i=0; i<num; i++) {
					result.add(new ItemStack(Item.getItemFromBlock(d.block)));
				}
			}
		}
		return result;
	}

}
