package harry.tutorialmod.init;

import harry.tutorialmod.Reference;
import harry.tutorialmod.TutorialMod;
import harry.tutorialmod.init.armour.CustomArmour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ArmourInit 
{
	public static final ArmorMaterial tutorial_armour = EnumHelper.addArmorMaterial("tutorial_armour", Reference.MODID + ":tutorial", 25, new int[]{3, 5, 7, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.5F);
	
	public static Item tutorial_helmet, tutorial_chestplate, tutorial_leggings, tutorial_boots;
	
	public static void init()
	{
		tutorial_helmet = new CustomArmour("tutorial_helmet", tutorial_armour, 1, EntityEquipmentSlot.HEAD);
		tutorial_chestplate = new CustomArmour("tutorial_chestplate", tutorial_armour, 1, EntityEquipmentSlot.CHEST);
		tutorial_leggings = new CustomArmour("tutorial_leggings", tutorial_armour, 2, EntityEquipmentSlot.LEGS);
		tutorial_boots = new CustomArmour("tutorial_boots", tutorial_armour, 1, EntityEquipmentSlot.FEET);
	}
	
	public static void register()
	{
		registerItem(tutorial_helmet);
		registerItem(tutorial_chestplate);
		registerItem(tutorial_leggings);
		registerItem(tutorial_boots);
	}
	
	public static void registerItem(Item item)
	{
		ForgeRegistries.ITEMS.register(item);
		item.setCreativeTab(TutorialMod.tutorialtab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
