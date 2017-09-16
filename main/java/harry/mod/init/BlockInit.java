package harry.mod.init;

import java.util.ArrayList;
import java.util.List;

import harry.mod.objects.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON);
	public static final Block COPPER_ORE = new BlockBase("copper_ore", Material.ROCK);
}
