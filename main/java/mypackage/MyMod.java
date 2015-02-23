package mypackage;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mypackage.dimension.*;
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

@Mod(modid="MyModID", name="My Mod", version="1.0.0")
public class MyMod {
	public static int varCount = 5;
	public static int armorCount = 5;
	public static HashMap<String, Integer> armorMap = new HashMap<String, Integer>();

	// AUTHOR NAME: 

	@Instance(value = "1")
	public static MyMod instance;
	@SidedProxy(clientSide="mypackage.client.ClientProxy", serverSide="mypackage.CommonProxy")
	public static CommonProxy proxy;

	// MATERIALS
	
	// NEW ITEMS
	public static MyItem makersIngot = new MyItem(CreativeTabs.tabMaterials);
	public static MyItem makersMineral = new MyItem(CreativeTabs.tabMaterials);
	
	// NEW BLOCKS
	public static MyBlock makersOre = new MyBlock(CreativeTabs.tabBlock, Material.rock);
	public static MyBlock makersBlock = new MyBlock(CreativeTabs.tabBlock, Material.iron);
	
	// NEW ARMOR
	
	// NEW THROWABLE
	public static MyThrowableItem makersThrowable = new MyThrowableItem(CreativeTabs.tabMisc);
	
	// BIOMES AND DIMENSIONS
	public static int worldTwoID = 6;
	public static MyTeleporterItem tpHome = new MyTeleporterItem(0);
	public static MyTeleporterItem tpTwo = new MyTeleporterItem(worldTwoID);
	public static final MyBiomeGenBase sparce = new BiomeSparce(50, Blocks.diamond_block);
	
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
		buildDimension(worldTwoID, WorldTwo.class);
		
		// REGISTRATIONgmail
		registerEverything();
		
		// BIOME
		BiomeDictionary.registerBiomeType(sparce, BiomeDictionary.Type.PLAINS);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		GameRegistry.registerWorldGenerator(new MyGenerator(), 1000);
		
		// ITEM STACKS
		ItemStack oneMakersMineral = new ItemStack(makersMineral);
		ItemStack oneMakersIngot = new ItemStack(makersIngot);
		ItemStack nineMakersIngot = new ItemStack(makersIngot, 9);
		ItemStack oneMakersOre = new ItemStack(makersOre);
		ItemStack oneMakersBlock = new ItemStack(makersBlock);
		ItemStack fourMakersThrowable = new ItemStack(makersThrowable, 4);
		ItemStack oneGunpowder = new ItemStack(Items.gunpowder);
		
		// NEW RECIPES
		GameRegistry.addRecipe(oneMakersBlock, "iii", "iii", "iii", 'i', oneMakersIngot);
		GameRegistry.addRecipe(fourMakersThrowable, " a ", "aba", " a ", 'a', oneMakersIngot, 'b', oneGunpowder);
		GameRegistry.addShapelessRecipe(nineMakersIngot, oneMakersBlock);
		GameRegistry.addSmelting(oneMakersOre, oneMakersIngot, 1.0F);
		
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
			if (f.getType() != int.class && f.getType() != String.class) break;
		}
		return f.getName();
	}
	
	private void registerEverything() {
		MinecraftForge.EVENT_BUS.register(new MyForgeEvents());
		FMLCommonHandler.instance().bus().register(new MyWorldEvents());
		int entityID = 0;
		EntityRegistry.registerModEntity(MyThrowableEntity.class, "MyThrowableEntity", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 1, true);
		try {
			for (Field f : this.getClass().getDeclaredFields()) {
				Class c = f.getType();
				if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
						c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
						c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
						c == MySpade.class || c == MySword.class || c == MyThrowableItem.class || 
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
