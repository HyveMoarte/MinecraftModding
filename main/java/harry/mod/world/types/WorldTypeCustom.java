package harry.mod.world.types;

import harry.mod.init.BiomeInit;
import harry.mod.world.types.layer.GenLayerCustom;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;

public class WorldTypeCustom extends WorldType
{
	public WorldTypeCustom() 
	{
		super("Custom");
	}
	
	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, ChunkGeneratorSettings chunkSettings) 
	{
		return new GenLayerCustom(worldSeed, parentLayer, this, chunkSettings);
	}
}
