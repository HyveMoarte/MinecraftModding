package harry.mod.init;

import java.util.ArrayList;
import java.util.List;

import harry.mod.objects.blocks.BlockBase;
import harry.mod.objects.blocks.BlockLeaf;
import harry.mod.objects.blocks.BlockLogs;
import harry.mod.objects.blocks.BlockPlank;
import harry.mod.objects.blocks.BlockSaplings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON);
	
	public static final Block PLANKS = new BlockPlank("planks");
	public static final Block LOG = new BlockLogs("log");
	public static final Block LEAVES = new BlockLeaf("leaves");
	public static final Block SAPLING = new BlockSaplings("sapling");
}
