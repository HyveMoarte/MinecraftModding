package harry.mod.world.structures;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureCastle extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) 
	{
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		
		for(int i = 0; i <= 5; i++)
		{
			for(int j = 0; j <= 5; j++)
			{
				world.setBlockState(new BlockPos(x + i, y + j, z), Blocks.PLANKS.getDefaultState());
				world.setBlockState(new BlockPos(x, y + j, z + i), Blocks.PLANKS.getDefaultState());
				world.setBlockState(new BlockPos(x + 5, y + j, z + i), Blocks.PLANKS.getDefaultState());
				world.setBlockState(new BlockPos(x + i, y + j, z + 5), Blocks.PLANKS.getDefaultState());
				world.setBlockState(new BlockPos(x + i, y, z + j), Blocks.PLANKS.getDefaultState());
				world.setBlockState(new BlockPos(x + i, y + 5, z + j), Blocks.PLANKS.getDefaultState());
			}
		}
		
		world.setBlockState(new BlockPos(x + 4, y + 1, z + 1), Blocks.CRAFTING_TABLE.getDefaultState());
		world.setBlockState(new BlockPos(x + 4, y + 1, z + 2), Blocks.FURNACE.getDefaultState());
		world.setBlockState(new BlockPos(x + 4, y + 1, z + 3), Blocks.CHEST.getDefaultState());
		world.setBlockState(new BlockPos(x + 4, y + 1, z + 4), Blocks.CHEST.getDefaultState());
		
		return true;
	}	
}
