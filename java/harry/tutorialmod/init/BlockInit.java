package harry.tutorialmod.init;

import harry.tutorialmod.Reference;
import harry.tutorialmod.TutorialMod;
import harry.tutorialmod.init.blocks.CustomBlockMicrowave;
import harry.tutorialmod.init.blocks.CustomBlockStairs;
import harry.tutorialmod.init.blocks.CustomIngotBlock;
import harry.tutorialmod.init.blocks.CustomOre;
import harry.tutorialmod.init.blocks.activators.CustomBlockButton;
import harry.tutorialmod.init.blocks.activators.CustomBlockPressurePlate;
import harry.tutorialmod.init.blocks.fence.CustomBlockFence;
import harry.tutorialmod.init.blocks.fence.CustomBlockFenceGate;
import harry.tutorialmod.init.blocks.item.ItemBlockVariants;
import harry.tutorialmod.init.blocks.slab.CustomBlockDoubleSlab;
import harry.tutorialmod.init.blocks.slab.CustomBlockHalfSlab;
import harry.tutorialmod.init.blocks.trees.CustomBlockLeaves;
import harry.tutorialmod.init.blocks.trees.CustomBlockLog;
import harry.tutorialmod.init.blocks.trees.CustomBlockPlanks;
import harry.tutorialmod.init.blocks.trees.CustomBlockSapling;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockInit 
{
	public static Block tutorial_ore, tutorial_ore_nether, tutorial_ore_end;
	public static Block tutorial_block;
	public static Block tutorial_stairs;
	public static CustomBlockHalfSlab tutorial_slab_half;
	public static CustomBlockDoubleSlab tutorial_slab_double;
	public static CustomBlockFence tutorial_fence;
	public static CustomBlockFenceGate tutorial_fence_gate;
	public static CustomBlockPressurePlate tutorial_pressure_plate;
	public static CustomBlockButton tutorial_button;
	
	public static Block planks, leaves, log, sapling;
	
	public static Block microwave_idle, microwave_active;
	
	public static void init()
	{
		tutorial_ore = new CustomOre("tutorial_ore", 2.0F, 4.0F, 2);
		tutorial_ore_nether = new CustomOre("tutorial_ore_nether", 2.0F, 4.0F, 2);
		tutorial_ore_end = new CustomOre("tutorial_ore_end", 2.0F, 4.0F, 2);
		tutorial_block = new CustomIngotBlock("tutorial_block", 2.5F, 4.5F, 2);
		tutorial_stairs = new CustomBlockStairs("tutorial_stairs", tutorial_block.getDefaultState());
		tutorial_slab_half = new CustomBlockHalfSlab("tutorial_slab_half", 2.5F, 4.5F);
		tutorial_slab_double = new CustomBlockDoubleSlab("tutorial_slab_double", 2.5F, 4.5F);
		tutorial_fence = new CustomBlockFence("tutorial_fence", 2.5F, 4.5F);
		tutorial_fence_gate = new CustomBlockFenceGate("tutorial_fence_gate", 2.5F, 4.5F);
		tutorial_pressure_plate = new CustomBlockPressurePlate("tutorial_pressure_plate", 2.5F, 4.5F);
		tutorial_button = new CustomBlockButton("tutorial_button", 2.5F, 4.5F);
		microwave_active = new CustomBlockMicrowave("microwave_active", 2.5F, 4.5F, true);
		microwave_idle = new CustomBlockMicrowave("microwave_idle", 2.5F, 4.5F, false);
		
		planks = new CustomBlockPlanks("planks");
		log = new CustomBlockLog("log");
		leaves = new CustomBlockLeaves("leaves");
		sapling = new CustomBlockSapling("sapling");
	}
	
	public static void register()
	{
		registerBlock(tutorial_ore);
		registerBlock(tutorial_ore_nether);
		registerBlock(tutorial_ore_end);
		registerBlock(tutorial_block);
		registerBlock(tutorial_stairs);
		registerBlock(tutorial_slab_half, new ItemSlab(tutorial_slab_half, tutorial_slab_half, tutorial_slab_double));
		ForgeRegistries.BLOCKS.register(tutorial_slab_double);
		registerBlock(tutorial_fence);
		registerBlock(tutorial_fence_gate);
		registerBlock(tutorial_button);
		registerBlock(tutorial_pressure_plate);
		
		registerBlock(microwave_idle);
		ForgeRegistries.BLOCKS.register(microwave_active);
		
		registerBlockWithVariants(planks, new ItemBlockVariants(planks));
		registerBlockWithVariants(log, new ItemBlockVariants(log));
		registerBlockWithVariants(leaves, new ItemBlockVariants(leaves));
		registerBlockWithVariants(sapling, new ItemBlockVariants(sapling));
	}
	
	public static void registerRenders()
	{
		for(int i = 0; i < CustomBlockPlanks.EnumType.values().length; i++)
		{
			registerRender(planks, i, "planks_" + CustomBlockPlanks.EnumType.values()[i].getName());
			registerRender(log, i, "log_" + CustomBlockPlanks.EnumType.values()[i].getName());
			registerRender(leaves, i, "leaves_" + CustomBlockPlanks.EnumType.values()[i].getName());
			registerRender(sapling, i, "sapling_" + CustomBlockPlanks.EnumType.values()[i].getName());
		}
	}
	
	public static void registerBlock(Block block)
	{
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(TutorialMod.tutorialtab);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	public static void registerBlock(Block block, ItemBlock itemblock)
	{
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(TutorialMod.tutorialtab);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	public static void registerBlockWithVariants(Block block, ItemBlock itemblock)
	{
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(TutorialMod.tutorialtab);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
	}
	
	public static void registerRender(Block block, int meta, String filename)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), "inventory"));
	}
}
