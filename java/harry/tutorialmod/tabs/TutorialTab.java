package harry.tutorialmod.tabs;

import harry.tutorialmod.init.BlockInit;
import harry.tutorialmod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TutorialTab extends CreativeTabs
{
	public TutorialTab(String label) { super("tutorialtab"); 
	this.setBackgroundImageName("tutorial.png"); }
	public ItemStack getTabIconItem() { return new ItemStack(ItemInit.tutorial_ingot);}
}
