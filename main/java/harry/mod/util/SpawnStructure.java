package harry.mod.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class SpawnStructure 
{
	public static void loadStructure(BlockPos pos, World world, String name)
	{
		if(!world.isRemote)
		{
			WorldServer server = (WorldServer)world;
			MinecraftServer mcserver = world.getMinecraftServer();
			
			TemplateManager templateManager = server.getStructureTemplateManager();
			ResourceLocation templateLocation = new ResourceLocation(Reference.MODID, name);
			Template template = templateManager.getTemplate(mcserver, templateLocation);
			
			BlockPos blockpos = template.getSize();
			IBlockState state = world.getBlockState(pos);
			
			if(template != null)
			{
				world.notifyBlockUpdate(pos, state, state, 3);
				PlacementSettings settings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(null).setReplacedBlock(null).setIgnoreStructureBlock(false);
				template.addBlocksToWorld(world, pos, settings);
			}
		}
	}
}
