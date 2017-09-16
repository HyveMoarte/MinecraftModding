package harry.mod.objects;

import harry.mod.Main;
import harry.mod.init.ItemInit;
import harry.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
	protected String name; 
	
	public ItemBase(String name) 
	{	
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) 
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
