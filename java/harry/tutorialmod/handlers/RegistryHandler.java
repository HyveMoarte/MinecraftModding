package harry.tutorialmod.handlers;

import harry.tutorialmod.init.ArmourInit;
import harry.tutorialmod.init.BlockInit;
import harry.tutorialmod.init.ItemInit;
import harry.tutorialmod.init.ToolInit;
import harry.tutorialmod.init.blocks.CustomOre;
import harry.tutorialmod.world.gen.TutorialOreGen;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHandler 
{
	public static void Client()
	{	
		RecipeHandler.registerCrafting();
		RecipeHandler.registerSmelting();
	}
	
	public static void Common()
	{
		ItemInit.init();
		ItemInit.register();
		
		BlockInit.init();
		BlockInit.register();
		BlockInit.registerRenders();
		
		ToolInit.init();
		ToolInit.register();
		
		ArmourInit.init();
		ArmourInit.register();
		
		TutorialOreGen.register();
		
		TileEntityHandler.register();
		GUIHandler.register();
	}
}
