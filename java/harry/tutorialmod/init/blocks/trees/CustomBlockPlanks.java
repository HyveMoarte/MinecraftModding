package harry.tutorialmod.init.blocks.trees;

import harry.tutorialmod.util.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CustomBlockPlanks extends Block implements IMetaName
{
	public static final PropertyEnum<CustomBlockPlanks.EnumType> VARIANT = PropertyEnum.<CustomBlockPlanks.EnumType>create("variant", CustomBlockPlanks.EnumType.class);
	
	public CustomBlockPlanks(String name) 
	{
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, CustomBlockPlanks.EnumType.TUTORIAL));
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
		return ((CustomBlockPlanks.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		for(CustomBlockPlanks.EnumType customblockplanks$enumtype : CustomBlockPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(VARIANT, CustomBlockPlanks.EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((CustomBlockPlanks.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	public static enum EnumType implements IStringSerializable
	{
		TUTORIAL(0, "tutorial"),
		ALUMINIUM(1, "aluminium");
		
		private static final CustomBlockPlanks.EnumType[] META_LOOKUP = new CustomBlockPlanks.EnumType[values().length];
		private final int meta;
		private final String name, unlocializedName;
		
		private EnumType(int meta, String name) 
		{
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String unlocializedName) 
		{
			this.meta = meta;
			this.name = name;
			this.unlocializedName = unlocializedName;
		}
		
		@Override
		public String getName() 
		{
			return this.name;
		}
		
		public int getMeta() 
		{
			return this.meta;
		}
		
		public String getUnlocializedName() 
		{
			return this.unlocializedName;
		}
		
		@Override
		public String toString() 
		{
			return this.name;
		}
		
		public static CustomBlockPlanks.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}
		
		static
		{
			for(CustomBlockPlanks.EnumType customblockplanks$enumtype : values())
			{
				META_LOOKUP[customblockplanks$enumtype.getMeta()] = customblockplanks$enumtype;
			}
		}
		
	}

	@Override
	public String getSpecialName(ItemStack stack) 
	{
		return CustomBlockPlanks.EnumType.values()[stack.getItemDamage()].getName();
	}
}
