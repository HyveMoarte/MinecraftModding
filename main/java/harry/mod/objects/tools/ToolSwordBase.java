package harry.mod.objects.tools;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.objects.ItemBase;
import harry.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSwordBase extends ItemSword implements IHasModel 
{
	protected String name; 
	
	public ToolSwordBase(String name, ToolMaterial material) 
	{
		super(material);
		
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ToolSwordBase setCreativeTab(CreativeTabs tab) 
	{
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
