package mymod;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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
		float prob = 1.0F;
	}
	
	ArrayList<Drop> drops = new ArrayList<Drop>();
	
	String name;
	Item itemDropped;
	int minQuantity = 1;
	int maxQuantity = 1;
	
	public MyBlock(CreativeTabs tab, MapColor color, boolean needsTool, boolean canBurn) {
		super(new MyMaterial(color, needsTool, canBurn));
		this.name = MyMod.getNextName();
		this.setBlockName(this.name);
		this.setBlockTextureName(MyMod.modid + ":" + this.name);
		this.setCreativeTab(tab);
	}
	
	public MyBlock(CreativeTabs tab, Material material) {
		super(material);
		this.name = MyMod.getNextName();
		this.setBlockName(this.name);
		this.setBlockTextureName(MyMod.modid + ":" + this.name);
		this.setCreativeTab(tab);
	}
	
	public MyBlock addObjectDropped(Object o) {
		return addObjectDropped(o, 1, 1, 1.0F);
	}
	public MyBlock addObjectDropped(Object o, float chance) {
		return addObjectDropped(o, 1, 1, chance);
	}

	public MyBlock addObjectDropped(Object o, int quantity) {
		return addObjectDropped(o, quantity, quantity, 1.0F);
	}
	public MyBlock addObjectDropped(Object o, int quantity, float chance) {
		return addObjectDropped(o, quantity, quantity, chance);
	}

	
	public MyBlock addObjectDropped(Object o, int minQuantity, int maxQuantity) {
		return addObjectDropped(o, minQuantity, maxQuantity, 1.0F);
	}
	public MyBlock addObjectDropped(Object o, int minQuantity, int maxQuantity, float chance) {
		if (o instanceof Block) {
			return addBlockDropped((Block)o, minQuantity, maxQuantity, chance);
		}
		else if (o instanceof Item) {
			return addItemDropped((Item)o, minQuantity, maxQuantity, chance);
		}
		return this;
	}
	
	public MyBlock addItemDropped(Item item) {
		return addItemDropped(item, 1, 1, 1.0F);
	}
	public MyBlock addItemDropped(Item item, float chance) {
		return addItemDropped(item, 1, 1, chance);
	}
	
	public MyBlock addItemDropped(Item item, int quantity) {
		return addItemDropped(item, quantity, quantity, 1.0F);
	}
	public MyBlock addItemDropped(Item item, int quantity, float chance) {
		return addItemDropped(item, quantity, quantity, chance);
	}
	
	public MyBlock addItemDropped(Item item, int minQuantity, int maxQuantity) {
		return addItemDropped(item, minQuantity, maxQuantity, 1.0F);
	}

	public MyBlock addItemDropped(Item item, int minQuantity, int maxQuantity, float chance) {
		Drop d = new Drop();
		d.item = item;
		d.min = minQuantity;
		d.max = maxQuantity;
		d.prob = chance;
		drops.add(d);
		return this;
	}
	
	public MyBlock addBlockDropped(Block block) {
		return addBlockDropped(block, 1, 1, 1.0F);
	}
	public MyBlock addBlockDropped(Block block, float chance) {
		return addBlockDropped(block, 1, 1, chance);
	}
	
	public MyBlock addBlockDropped(Block block, int quantity) {
		return addBlockDropped(block, quantity, quantity, 1.0F);
	}
	public MyBlock addBlockDropped(Block block, int quantity, float chance) {
		return addBlockDropped(block, quantity, quantity, chance);
	}
	
	public MyBlock addBlockDropped(Block block, int minQuantity, int maxQuantity) {
		return addBlockDropped(block, minQuantity, maxQuantity, 1.0F);
	}

	public MyBlock addBlockDropped(Block block, int minQuantity, int maxQuantity, float chance) {
		Drop d = new Drop();
		d.block = block;
		d.min = minQuantity;
		d.max = maxQuantity;
		d.prob = chance;
		drops.add(d);
		return this;
	}
	
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		for (Drop d : drops) {
			if (world.rand.nextFloat() < d.prob) {
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
		}
		return result;
	}
	
}
