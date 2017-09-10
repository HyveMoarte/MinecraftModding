package harry.tutorialmod.init;

import harry.tutorialmod.Reference;
import harry.tutorialmod.TutorialMod;
import harry.tutorialmod.init.items.CustomIngot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemInit 
{
	public static Item tutorial_ingot;
	
	public static void init()
	{
		tutorial_ingot = new CustomIngot("tutorial_ingot");
	}
	
	public static void register()
	{
		registerItem(tutorial_ingot);
	}
	
	public static void registerItem(Item item)
	{
		ForgeRegistries.ITEMS.register(item);
		item.setCreativeTab(TutorialMod.tutorialtab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
