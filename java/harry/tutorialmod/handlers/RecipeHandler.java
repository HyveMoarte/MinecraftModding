package harry.tutorialmod.handlers;

import harry.tutorialmod.init.ArmourInit;
import harry.tutorialmod.init.BlockInit;
import harry.tutorialmod.init.ItemInit;
import harry.tutorialmod.init.ToolInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler 
{
	public static void registerCrafting()
	{
		GameRegistry.addShapedRecipe(new ResourceLocation("tm:tutorial_ore"), new ResourceLocation("tm:tutorial_blocks"), new ItemStack(BlockInit.tutorial_ore), new Object[]{"SSS","SIS","SSS",'S', Blocks.STONE, 'I', ItemInit.tutorial_ingot});
		registerArmourCrafting(ArmourInit.tutorial_helmet, ArmourInit.tutorial_chestplate, ArmourInit.tutorial_leggings, ArmourInit.tutorial_boots, ItemInit.tutorial_ingot);
		registerToolCrafting(ToolInit.tutorial_axe, ToolInit.tutorial_hoe, ToolInit.tutorial_pickaxe, ToolInit.tutorial_shovel, ToolInit.tutorial_sword, ToolInit.tutorial_multitool, ItemInit.tutorial_ingot);
		GameRegistry.addShapelessRecipe(new ResourceLocation("tm:tutorial_ingot"), new ResourceLocation("tm:tutorial_items"), new ItemStack(ItemInit.tutorial_ingot, 9), new Ingredient[]{Ingredient.fromItem(Item.getItemFromBlock(BlockInit.tutorial_block))});
	}
	
	public static void registerSmelting()
	{
		GameRegistry.addSmelting(BlockInit.tutorial_ore, new ItemStack(ItemInit.tutorial_ingot), 10);
	}
	
	private static void registerToolCrafting(Item axe, Item hoe, Item pickaxe, Item shovel, Item sword, Item multitool, Item ingot)
	{
		GameRegistry.addShapedRecipe(new ResourceLocation("" + axe.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(axe), new Object[]{"II ","IS "," S ",'S', Items.STICK, 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + hoe.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(hoe), new Object[]{"II "," S "," S ",'S', Items.STICK, 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + pickaxe.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(pickaxe), new Object[]{"III"," S "," S ",'S', Items.STICK, 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + shovel.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(shovel), new Object[]{" I "," S "," S ",'S', Items.STICK, 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + sword.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(sword), new Object[]{" I "," I "," S ",'S', Items.STICK, 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + multitool.getRegistryName()), new ResourceLocation("tm:tutorial_tools"), new ItemStack(multitool), new Object[]{"SPA","BHB","III", 'S', shovel, 'P', pickaxe, 'A', axe, 'H', ToolInit.tutorial_hoe, 'B', BlockInit.tutorial_block, 'I', ingot});
	}
	
	private static void registerArmourCrafting(Item helmet, Item chestplate, Item leggings, Item boots, Item ingot)
	{
		GameRegistry.addShapedRecipe(new ResourceLocation("" + helmet.getRegistryName()), new ResourceLocation("tm:tutorial_armour"), new ItemStack(helmet), new Object[]{"III","I I","   ", 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + chestplate.getRegistryName()), new ResourceLocation("tm:tutorial_armour"), new ItemStack(chestplate), new Object[]{"I I","III","III", 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + leggings.getRegistryName()), new ResourceLocation("tm:tutorial_armour"), new ItemStack(leggings), new Object[]{"III","I I","I I", 'I', ingot});
		GameRegistry.addShapedRecipe(new ResourceLocation("" + boots.getRegistryName()), new ResourceLocation("tm:tutorial_armour"), new ItemStack(boots), new Object[]{"I I","I I", "   ", 'I', ingot});
	}
}
