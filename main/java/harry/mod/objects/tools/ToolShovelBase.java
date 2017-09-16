package harry.mod.objects.tools;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.objects.ItemBase;
import harry.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ToolShovelBase extends ItemSpade implements IHasModel
{
	protected String name;
	
	public ToolShovelBase(String name, ToolMaterial material) 
	{
		super(material);
		
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ToolShovelBase setCreativeTab(CreativeTabs tab) 
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
