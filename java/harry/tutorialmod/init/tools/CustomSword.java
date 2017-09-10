package harry.tutorialmod.init.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;

public class CustomSword extends ItemSword 
{
	public CustomSword(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
}
