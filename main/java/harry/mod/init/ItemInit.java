package harry.mod.init;

import java.util.ArrayList;
import java.util.List;

import harry.mod.objects.ItemBase;
import harry.mod.objects.armour.ArmourBase;
import harry.mod.objects.items.ItemStructure;
import harry.mod.objects.tools.MultiToolBase;
import harry.mod.objects.tools.ToolAxeBase;
import harry.mod.objects.tools.ToolHoeBase;
import harry.mod.objects.tools.ToolPickaxeBase;
import harry.mod.objects.tools.ToolShovelBase;
import harry.mod.objects.tools.ToolSwordBase;
import harry.mod.objects.tools.staffs.StaffFlame;
import harry.mod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial COPPER_TOOL = EnumHelper.addToolMaterial("copper_tool", 2, 250, 6.0F, 2.0F, 14);
	public static final ArmorMaterial COPPER_ARMOUR = EnumHelper.addArmorMaterial("copper_armour", Reference.MODID + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	
	public static final ToolMaterial STAFFS = EnumHelper.addToolMaterial("staffs", 0, 250, 0.0F, 2.5F, 0);
	
	//Items
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");
	
	public static final Item STRUCTURE_HOUSE = new ItemStructure("structure_house");
	
	//Tools
	public static final Item COPPER_AXE = new ToolAxeBase("copper_axe", COPPER_TOOL);
	public static final Item COPPER_HOE = new ToolHoeBase("copper_hoe", COPPER_TOOL);
	public static final Item COPPER_PICKAXE = new ToolPickaxeBase("copper_pickaxe", COPPER_TOOL);
	public static final Item COPPER_SHOVEL = new ToolShovelBase("copper_shovel", COPPER_TOOL);
	public static final Item COPPER_SWORD = new ToolSwordBase("copper_sword", COPPER_TOOL);
	public static final Item COPPER_MULTITOOL = new MultiToolBase("copper_multitool", COPPER_TOOL);

	public static final Item FLAME_STAFF = new StaffFlame("staff_flame", STAFFS);
	
	//Armour
	public static final Item COPPER_HELMET = new ArmourBase("copper_helmet", COPPER_ARMOUR, 1, EntityEquipmentSlot.HEAD);
	public static final Item COPPER_CHESTPLATE = new ArmourBase("copper_chestplate", COPPER_ARMOUR, 1, EntityEquipmentSlot.CHEST);
	public static final Item COPPER_LEGGINGS = new ArmourBase("copper_leggings", COPPER_ARMOUR, 2, EntityEquipmentSlot.LEGS);
	public static final Item COPPER_BOOTS = new ArmourBase("copper_boots", COPPER_ARMOUR, 1, EntityEquipmentSlot.FEET);
}
