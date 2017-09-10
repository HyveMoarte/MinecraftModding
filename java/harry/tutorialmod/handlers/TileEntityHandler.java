package harry.tutorialmod.handlers;

import harry.tutorialmod.tileentity.TileEntityMicrowave;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityMicrowave.class, "microwave");
	}
}
