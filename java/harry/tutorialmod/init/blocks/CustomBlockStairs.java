package harry.tutorialmod.init.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class CustomBlockStairs extends BlockStairs 
{
	public CustomBlockStairs(String name, IBlockState modelState) 
	{
		super(modelState);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.useNeighborBrightness = true;
	}

}
