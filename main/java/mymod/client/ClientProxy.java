package mymod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.MinecraftForgeClient;
import mymod.CommonProxy;
import mymod.MyMod;
import mymod.MyThrowableEntity;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		for (int i=0; i<MyMod.class.getDeclaredFields().length; i++) {
			if(MyMod.class.getDeclaredFields()[i].getType() == ArmorMaterial.class) {
				RenderingRegistry.addNewArmourRendererPrefix(MyMod.class.getDeclaredFields()[i].getName());
			}
		}
		RenderingRegistry.registerEntityRenderingHandler(MyThrowableEntity.class, new RenderSnowball(MyMod.makersThrowable));
	}
}
