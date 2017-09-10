package harry.tutorialmod.init.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;

public class CustomShovel extends ItemSpade 
{
	public CustomShovel(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
}
