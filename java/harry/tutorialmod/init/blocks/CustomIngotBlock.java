package harry.tutorialmod.init.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CustomIngotBlock extends Block
{
	public CustomIngotBlock(String name, float hardness, float resistance, int harvestLevel) 
	{
		super(Material.IRON);
		
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("pickaxe", harvestLevel);
	}

}
