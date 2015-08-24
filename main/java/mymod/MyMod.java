package mymod;

import java.lang.reflect.Field;
import java.util.HashMap;

import mymod.dimension.MakersBiome;
import mymod.dimension.MakersWorld;
import mymod.dimension.MyTeleporterItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.WorldProvider;
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

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
@Mod(modid="mymod", name="My Mod", version="1.0.0")
public class MyMod {
	public static ArrayList<Class> throwableClasses = new ArrayList<Class>();
	public static int varCount = 7;
	public static int armorCount = 5;
	public static int biomeID = 50;
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
	public static MyBlock makersOre = new MyBlock(CreativeTabs.tabBlock, MapColor.blueColor, true, false);
	public static MyBlock makersBlock = new MyBlock(CreativeTabs.tabBlock, MapColor.blueColor, true, false);

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
	public static MyThrowableItem makersThrowable = new MyThrowableItem(CreativeTabs.tabMisc, MakersThrowableEntity.class);
	
	// BIOMES AND DIMENSIONS
	public static int makersWorldID = 2;
	public static MyTeleporterItem tpHome = new MyTeleporterItem(0);
	public static MyTeleporterItem tpMakers = new MyTeleporterItem(makersWorldID);
	public static MakersBiome theMakersBiome = new MakersBiome(biomeID++, Blocks.stone);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// BLOCK PROPERTIES
		makersBlock.setHardness(5.0F);
		makersBlock.setHarvestLevel("pickaxe", 1);
		makersBlock.setResistance(50.0F);
		makersBlock.setStepSound(Block.soundTypeMetal);
		makersBlock.addBlockDropped(makersBlock);

		makersOre.setHardness(3.0F);
		makersOre.setHarvestLevel("pickaxe", 1);
		makersOre.setResistance(15.0F);
		makersOre.setStepSound(Block.soundTypeStone);
		makersOre.addItemDropped(makersMineral,2,4);
		
		// POTION EFFECTS FOR FOOD
		
		// BUILD DIMENSIONS
		buildDimension(makersWorldID, MakersWorld.class);
		
		// FINAL REGISTRATION
		registerEverything();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
				
		// CRAFTING AND SMELTING RECIPES
		MyRecipes.setSymbol("i", makersIngot);
		MyRecipes.setSymbol("m", makersMineral);
		MyRecipes.setSymbol("g", Items.gunpowder);
		MyRecipes.setSymbol("b", makersBlock);
		MyRecipes.setSymbol("s", Items.stick);
		
		MyRecipes.addShapedRecipe(makersPickaxe, 1, "iii", " s ", " s ");
		MyRecipes.addShapedRecipe(makersShovel, 1, "i", "s", "s");
		MyRecipes.addShapedRecipe(makersSword, 1, "i", "i", "s");
		MyRecipes.addShapedRecipe(makersAxe, 1, "ii", "is", " s");
		MyRecipes.addShapedRecipe(makersHoe, 1, "ii", " s", " s");
		MyRecipes.addShapedRecipe(makersHelmet, 1, "iii", "i i");
		MyRecipes.addShapedRecipe(makersChestplate, 1, "i i", "iii", "iii");
		MyRecipes.addShapedRecipe(makersLeggings, 1, "iii", "i i", "i i");
		MyRecipes.addShapedRecipe(makersBoots, 1, "i i", "i i");
		MyRecipes.addShapedRecipe(makersThrowable, 4, " i ", "igi", " i ");
		MyRecipes.addShapedRecipe(makersBlock, 1, "iii", "iii", "iii");
		MyRecipes.addShapelessRecipe(makersIngot, 9, "b");
		MyRecipes.addSmeltingRecipe(makersIngot, 1, "m", 1.0F);
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
			@SuppressWarnings("rawtypes")
			Class c = f.getType();
			if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
					c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
					c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
					c == MyShovel.class || c == MySword.class || c == MyThrowableItem.class || 
					c == MyTeleporterItem.class || c == MyBlock.class) break;
		}
		return f.getName();
	}
	
	public void registerEntity(Class c) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(c, c.getSimpleName(), id);
		EntityRegistry.registerModEntity(c, c.getSimpleName(), id, this, 80, 1, true);
	}
	
	private void registerEverything() {
		MinecraftForge.EVENT_BUS.register(new MyForgeEvents());
		FMLCommonHandler.instance().bus().register(new MyWorldEvents());
		for (Class c : throwableClasses) {
			registerEntity(c);
		}
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
