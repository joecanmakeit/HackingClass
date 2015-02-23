package mypackage.dimension;

import cpw.mods.fml.common.FMLCommonHandler;
import mypackage.MyMod;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class MyTeleporterItem extends Item {
	
	int dimension = 0;
	String name;
	
	public MyTeleporterItem(int id) {
		this.name = MyMod.getNextName();
		this.dimension = id;
		this.setUnlocalizedName(this.name);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("myassets:" + this.name);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			EntityPlayerMP mplayer = (EntityPlayerMP)par3EntityPlayer;
			if (mplayer.dimension != this.dimension) {
				FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par3EntityPlayer, this.dimension, new MyTeleporter(MinecraftServer.getServer().worldServerForDimension(this.dimension)));
			}
		}
		return par1ItemStack;
	}

}
