package harry.mod.objects.tools.staffs;

import harry.mod.objects.tools.ToolSwordBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StaffFlame extends ToolSwordBase
{	
	public StaffFlame(String name, ToolMaterial material) 
	{
		super(name, material);
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) 
	{
		ItemStack item = player.getHeldItem(hand);
		Vec3d aim = player.getLookVec();
		EntityLargeFireball fireball = new EntityLargeFireball(world, player, 1, 1, 1);
		
		fireball.setPosition(player.posX + aim.x * 1.5D, player.posY + aim.y * 1.5D, player.posZ + aim.z * 1.5D);
		fireball.accelerationX = aim.x * 0.1; fireball.accelerationY = aim.y * 0.1; fireball.accelerationZ = aim.z * 0.1;
		world.spawnEntity(fireball);
		
		item.damageItem(1, player);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
}
