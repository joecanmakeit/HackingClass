package mymod;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MyRecipes {
	
	private static HashMap<String, ItemStack> symbols = new HashMap<String, ItemStack>();
	
	public static void setSymbol(String symbol, Object... meaning) {
		if (meaning.length < 1) return;
		ItemStack is;
		int meta = meaning.length > 1 ? (Integer)meaning[1] : 0;
		if (meaning[0] instanceof Item) is = new ItemStack((Item)meaning[0], 1, meta);
		else if (meaning[0] instanceof Block) is = new ItemStack((Block)meaning[0], 1, meta);
		else {
			System.out.println("[MYRECIPE]: Object " + meaning[0].getClass().toString() + " is not Block or Item!");
			return;
		}
		symbols.put(symbol, is);
	}
	
	public static void addShapedRecipe(Object result, int quantity, String... pattern) {
		ArrayList<Object> params = new ArrayList<Object>();

		// Result
		ItemStack output = objToItemStack(result, quantity);
		if (output == null) return;
		
		// Pattern
		for (String s : pattern) {
			params.add(s);
		}
		
		// Ingredient Mapping
		ArrayList<Character> keys = new ArrayList<Character>();
		for (String s : pattern) {
			for (char c : s.toCharArray()) {
				if (c != ' ' && !keys.contains(c)) keys.add(c);
			}
		}
		for (char c : keys) {
			params.add(c);
			if (!symbols.containsKey(""+c)) {
				System.out.println("[MYRECIPE]: Symbol '" + c + "' not set!");
				return;
			}
			params.add(symbols.get(""+c));
		}
		GameRegistry.addRecipe(output, params.toArray());
		
	}
	
	public static void addShapelessRecipe(Object result, int quantity, Object... ingredients) {
		if (ingredients.length < 1) return;
		ItemStack output = objToItemStack(result, quantity);
		if (output == null) return;
		ArrayList<ItemStack> params = new ArrayList<ItemStack>();
		for (Object o : ingredients) {
			ItemStack ing = objToItemStack(o, 1);
			if (ing == null) return;
			params.add(ing);
		}
		GameRegistry.addShapelessRecipe(output, params.toArray());
	}
	
	public static void addSmeltingRecipe(Object result, int quantity, Object input, float xp) {
		ItemStack a = objToItemStack(input, 1);
		if (a == null) return;
		ItemStack b = objToItemStack(result, quantity);
		if (b == null) return;
		GameRegistry.addSmelting(a, b, xp);
	}
	
	// Returns an ItemStack with "obj" as the item, and "quantity" as the stacksize.
	// If "obj" is a String, the ItemStack is drawn from the symbol map.
	public static ItemStack objToItemStack(Object obj, int quantity) {
		ItemStack output;
		if (obj instanceof String) {
			String sym = (String)obj;
			if (!symbols.containsKey(sym)) {
				System.out.println("[MYRECIPE]: Symbol '" + sym + "' not set!");
				output = null;
			}
			output = new ItemStack(symbols.get(sym).getItem(), quantity);
		}
		else if (obj instanceof Block) {
			output = new ItemStack((Block)obj, quantity);
		}
		else if (obj instanceof Item) {
			output = new ItemStack((Item)obj, quantity);
		}
		else {
			System.out.println("[MYRECIPE]: Object " + obj.getClass().toString() + " is not Block, Item, or String!");
			output = null;
		}
		return output;
	}

}
