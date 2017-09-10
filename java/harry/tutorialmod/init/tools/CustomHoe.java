package harry.tutorialmod.init.tools;

import net.minecraft.item.ItemHoe;

public class CustomHoe extends ItemHoe 
{
	public CustomHoe(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

}
