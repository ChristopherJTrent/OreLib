package com.arkevorkhat.orelib;

import com.arkevorkhat.orelib.strategies.OreGenerationStrategyBase;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;


public class OreRegistry
{
	public static List<oreRegistryEntry> registry = new ArrayList<>();
	
	public static synchronized void RegisterOre(Block block,
	                                            OreGenerationStrategyBase generatorOptions)
	{
		for (Biome b : ForgeRegistries.BIOMES.getValues()){
				b.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					  generatorOptions.getConfiguredFeature(block));
		}
	}
}
