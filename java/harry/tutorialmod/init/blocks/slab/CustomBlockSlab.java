package harry.tutorialmod.init.blocks.slab;

import java.util.Random;

import harry.tutorialmod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class CustomBlockSlab extends BlockSlab
{
	public CustomBlockSlab(String name, float hardness, float resistance)
	{
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		
		this.fullBlock = this.isDouble();
		IBlockState state = this.blockState.getBaseState();
		
		if(!this.isDouble())
		{
			state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		
		setDefaultState(state);
		this.useNeighborBrightness = true;
	}
	
	@Override
	public String getUnlocalizedName(int meta) 
	{
		return this.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty() 
	{
		return HALF;
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return EnumBlockHalf.BOTTOM;
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		if(!this.isDouble())
		{
			return this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
		}
		return this.getDefaultState();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		if(this.isDouble())
		{
			return 0;
		}
		
		return ((EnumBlockHalf)state.getValue(HALF)).ordinal() + 1;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(BlockInit.tutorial_slab_half);
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {HALF});
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}

}