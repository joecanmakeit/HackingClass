package mymod.client;

import java.lang.reflect.Field;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import mymod.CommonProxy;
import mymod.MyMod;
import mymod.MyThrowableEntity;
import mymod.MyThrowableItem;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

	@SuppressWarnings("unchecked")
	@Override
	public void registerRenderers() {
		Field[] fields = MyMod.class.getDeclaredFields();
		for (int i=0; i<fields.length; i++) {
			if(fields[i].getType() == ArmorMaterial.class) {
				RenderingRegistry.addNewArmourRendererPrefix(fields[i].getName());
			}
			if(fields[i].getType() == MyThrowableItem.class) {
				try {
					MyThrowableItem item = (MyThrowableItem)fields[i].get(MyMod.instance);
					RenderingRegistry.registerEntityRenderingHandler(item.entityClass, new RenderSnowball(item));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
