package harry.tutorialmod.init.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import harry.tutorialmod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomMultitool extends ItemTool
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER, Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, BlockInit.tutorial_block, BlockInit.tutorial_ore, BlockInit.tutorial_ore_end, BlockInit.tutorial_ore_nether);
	private final Item.ToolMaterial material;
	
	public CustomMultitool(String name, ToolMaterial material)
	{
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		this.material = material;
		maxStackSize = 1;
		setMaxDamage(material.getMaxUses());
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){ return true; }
	public float getDamageVsEntity() { return this.material.getDamageVsEntity(); }
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
        {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
            {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
                {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
                    {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
                        {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
                            {
                                Material material = blockIn.getMaterial();

                                if (material == Material.ROCK)
                                {
                                    return true;
                                }
                                else if (material == Material.IRON)
                                {
                                    return true;
                                }
                                else
                                {
                                    return material == Material.ANVIL;
                                }
                            }
                            else
                            {
                                return this.toolMaterial.getHarvestLevel() >= 2;
                            }
                        }
                        else
                        {
                            return this.toolMaterial.getHarvestLevel() >= 1;
                        }
                    }
                    else
                    {
                        return this.toolMaterial.getHarvestLevel() >= 1;
                    }
                }
                else
                {
                    return this.toolMaterial.getHarvestLevel() >= 2;
                }
            }
            else
            {
                return this.toolMaterial.getHarvestLevel() >= 2;
            }
        }
        else
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
    }

	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, player, world, pos);
		
		if(!player.canPlayerEdit(pos.offset(facing), facing, stack)) return EnumActionResult.FAIL;
		
		//Shovel to Grass Path Code
		else if(facing !=EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS)
		{
			IBlockState state1 = Blocks.GRASS_PATH.getDefaultState();
			world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if(!world.isRemote) { world.setBlockState(pos, state1, 11); stack.damageItem(1, player);
		}
			
			return EnumActionResult.SUCCESS;
	}
		
		if(hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		
		//Hoe Code
		
		else if(facing != EnumFacing.DOWN && world.isAirBlock(pos.up()))
		{
			if(block == Blocks.GRASS_PATH || block == Blocks.DIRT)
			{
				this.setBlock(stack, player, world, pos, Blocks.FARMLAND.getDefaultState());
				return EnumActionResult.SUCCESS;
			}
		}
		
		else
			{
			return EnumActionResult.PASS;
			}
		
		return EnumActionResult.PASS;
	}

	private void setBlock(ItemStack stack, EntityPlayer player, World world, BlockPos pos, IBlockState state) 
	{
		world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if(!world.isRemote) { world.setBlockState(pos, state, 11); stack.damageItem(1, player); }
	}
	
	
}








































