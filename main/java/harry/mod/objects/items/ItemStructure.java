package harry.mod.objects.items;

import java.util.Random;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.util.IHasModel;
import harry.mod.world.structures.StructureCastle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemStructure extends Item implements IHasModel
{
	protected String name;
	
	private Random rand;
	private final StructureCastle castle = new StructureCastle();
	
	public ItemStructure(String name) 
	{	
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		
		this.setMaxStackSize(1);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public ItemStructure setCreativeTab(CreativeTabs tab) 
	{
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	 @Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	 {
		 int x = pos.getX(), y = pos.getY(), z= pos.getZ();
		 
		 int side = facing.getIndex();
		 if(side == 0) y--;
		 if(side == 1) y++;
		 if(side == 2) z--;
		 if(side == 3) z++;
		 if(side == 4) x--;
		 if(side == 5) x++;	 
		 if(!player.canPlayerEdit(new BlockPos(x, y, z), facing, player.getHeldItem(hand))) return EnumActionResult.FAIL;
		 
		 IBlockState state = world.getBlockState(new BlockPos(x, y, z));
		 if(state == Blocks.AIR.getDefaultState())
		 {
			 world.playSound(player, new BlockPos(x, y, z), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
			 castle.generate(world, rand, new BlockPos(x, y, z));
		 }
		 
		 player.getHeldItem(hand).damageItem(1, player);
		 return EnumActionResult.SUCCESS;
	 }
	
	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		this.loadStructure(playerIn.getPosition().add(0, 1, 0), worldIn, "Castle");
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	public void loadStructure(BlockPos pos, World worldIn, String name)
	{
		if (!worldIn.isRemote) 
		{
			WorldServer worldserver = (WorldServer) worldIn;
			MinecraftServer minecraftserver = worldIn.getMinecraftServer();
			TemplateManager templatemanager = worldserver.getStructureTemplateManager();
			ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
			Template template = templatemanager.getTemplate(minecraftserver, loc); 
			
			if (template != null) 
			{   
				PlacementSettings settings = new PlacementSettings().setChunk(new ChunkPos(pos)).setRotation(Rotation.NONE).setIgnoreEntities(false).setMirror(Mirror.NONE);			
				template.addBlocksToWorld(worldIn, pos, settings);
			}
		}
	}
	*/
}
