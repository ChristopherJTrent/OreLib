package com.arkevorkhat.orelib.strategies;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public abstract class OreGenerationStrategyBase
{
	public abstract List<Biome.Category> getBiomeCategories();
	
	public abstract boolean biomesAreWhitelisted();
	
	public abstract ConfiguredFeature<?, ?> getConfiguredFeature(Block block);
}
