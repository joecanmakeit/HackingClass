package mymod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mymod.dimension.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="mymod", name="My Mod", version="1.0.0")
public class MyMod {
	public static int varCount = 6;
	public static int armorCount = 5;
	public static String modid = "mymod";
	public static HashMap<String, Integer> armorMap = new HashMap<String, Integer>();

	// AUTHOR NAME: 

	@Instance(value = "1")
	public static MyMod instance;
	@SidedProxy(clientSide="mymod.client.ClientProxy", serverSide="mymod.CommonProxy")
	public static CommonProxy proxy;
	
	//  TEXTURE NAMES
	//  newItemTexture		TO MAKE A NEW TEXTURE,
	//  newBlockTexture		HIGHLIGHT ONE OF THE TEXTURE NAMES TO THE RIGHT,
	//  newArmorTexture		THEN PRESS CTRL + SHIFT + R

	// MATERIALS
	public static ToolMaterial makersTool = addToolMaterial(2, 500, 7.0F, 2.0F, 23);
	public static ArmorMaterial makersArmor = addArmorMaterial(18, new int[] {3, 7, 6, 3}, 23);
	
	// NEW ITEMS
	public static MyItem makersIngot = new MyItem(CreativeTabs.tabMaterials);
	public static MyItem makersMineral = new MyItem(CreativeTabs.tabMaterials);
	
	// NEW BLOCKS
	public static MyBlock makersOre = new MyBlock(CreativeTabs.tabBlock, Material.rock);
	public static MyBlock makersBlock = new MyBlock(CreativeTabs.tabBlock, Material.iron);
	
	// NEW TOOLS
	public static MyPickaxe makersPickaxe = new MyPickaxe(makersTool);
	public static MyShovel makersShovel = new MyShovel(makersTool);
	public static MyAxe makersAxe = new MyAxe(makersTool);
	public static MySword makersSword = new MySword(makersTool);
	public static MyHoe makersHoe = new MyHoe(makersTool);
	
	// NEW ARMOR
	public static MyHelmet makersHelmet = new MyHelmet(makersArmor);
	public static MyChestplate makersChestplate = new MyChestplate(makersArmor);
	public static MyLeggings makersLeggings = new MyLeggings(makersArmor);
	public static MyBoots makersBoots = new MyBoots(makersArmor);
	
	// NEW THROWABLE
	public static MyThrowableItem makersThrowable = new MyThrowableItem(CreativeTabs.tabMisc);
	
	// BIOMES AND DIMENSIONS
	public static int biomeID = 50;
	public static MyTeleporterItem tpHome = new MyTeleporterItem(0);
	public static int makersWorldID = 2;
	public static MyTeleporterItem tpMakers = new MyTeleporterItem(makersWorldID);
	public static MakersBiome theMakersBiome = new MakersBiome(biomeID++, Blocks.stone);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// BLOCK QUALITIES
		makersBlock.setHarvestLevel("pickaxe", 1);
		makersBlock.setHardness(5.0F);
		makersBlock.setResistance(50.0F);
		makersBlock.addBlockDropped(makersBlock);

		makersOre.setHarvestLevel("pickaxe", 1);
		makersOre.setHardness(3.0F);
		makersOre.setResistance(15.0F);
		makersOre.addItemDropped(makersMineral,2,4);
		
		// POTION EFFECTS FOR FOOD
		
		// BUILD DIMENSIONS
		buildDimension(makersWorldID, MakersWorld.class);
		
		// REGISTRATION
		registerEverything();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
				
		// CRAFTING AND SMELTING RECIPES
		MyRecipes.setSymbol("b", makersBlock);
		MyRecipes.setSymbol("o", makersOre);
		MyRecipes.setSymbol("g", Items.gunpowder);
		MyRecipes.setSymbol("t", makersThrowable);
		MyRecipes.setSymbol("i", makersIngot);
		
		MyRecipes.addShapedRecipe("b", 1, "iii", "iii", "iii");
		MyRecipes.addShapedRecipe("t", 4, " i ", "igi", " i ");
		MyRecipes.addShapelessRecipe("i", 9, "b");
		MyRecipes.addSmeltingRecipe("i", 1, "o", 1.0F);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	public static ToolMaterial addToolMaterial(int harvest_level, int durability, float efficiency, float damage, int enchantability) {
		return EnumHelper.addToolMaterial(MyMod.class.getDeclaredFields()[MyMod.varCount++].getName(),harvest_level,durability,efficiency,damage,enchantability);
	}

	public static ArmorMaterial addArmorMaterial(int durability, int[] damage_reductions, int enchantability) {
		armorMap.put(MyMod.class.getDeclaredFields()[MyMod.varCount].getName(), MyMod.armorCount++);
		return EnumHelper.addArmorMaterial(MyMod.class.getDeclaredFields()[MyMod.varCount++].getName(),durability,damage_reductions,enchantability);
	}
	
	public static String getNextName() {
		Field f;
		while (true) {
			f = MyMod.class.getDeclaredFields()[MyMod.varCount++];
			Class c = f.getType();
			if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
					c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
					c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
					c == MyShovel.class || c == MySword.class || c == MyThrowableItem.class || 
					c == MyTeleporterItem.class || c == MyBlock.class) break;
		}
		return f.getName();
	}
	
	private void registerEverything() {
		MinecraftForge.EVENT_BUS.register(new MyForgeEvents());
		FMLCommonHandler.instance().bus().register(new MyWorldEvents());
		int entityID = 0;
		EntityRegistry.registerModEntity(MyThrowableEntity.class, "MyThrowableEntity", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 1, true);
		GameRegistry.registerWorldGenerator(new MyGenerator(), 1000);
		try {
			for (Field f : this.getClass().getDeclaredFields()) {
				Class c = f.getType();
				if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
						c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
						c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
						c == MyShovel.class || c == MySword.class || c == MyThrowableItem.class || 
						c == MyTeleporterItem.class) {
					Item item = (Item)f.get(this);
					GameRegistry.registerItem(item, item.getUnlocalizedName());
				}
				if (c == MyBlock.class) {
					Block block = (Block)f.get(this);
					GameRegistry.registerBlock(block, block.getUnlocalizedName());
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void buildDimension(int dimID, Class<? extends WorldProvider> dimClass) {
		DimensionManager.registerProviderType(dimID, dimClass, false);
		DimensionManager.registerDimension(dimID, dimID);
	}
}
