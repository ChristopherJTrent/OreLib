package com.arkevorkhat.orelib;

import com.arkevorkhat.orelib.Strategies.OreGenerationStrategyBase;
import net.minecraft.block.Block;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;


public class OreRegistry {
	public static List<oreRegistryEntry> registry = new ArrayList<>();
	
	public static synchronized void RegisterOre(Block block, OreGenerationStrategyBase generatorOptions) {
		registry.add(new oreRegistryEntry(block, generatorOptions));
	}
	
	public static void finalizeRegistration() {
		for (oreRegistryEntry e : registry) {
			OreGenerationStrategyBase generatorOptions = e.getOreGenerationStrategy();
			Block block = e.getBlock();
			ForgeRegistries.BIOMES.getValues().stream().filter(biome -> (generatorOptions
				  .getBiomeCategories()
				  .contains(
						biome.getCategory()))
				  == generatorOptions
				  .biomesAreWhitelisted())
				  .forEach(biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, generatorOptions.getConfiguredFeature(block)));
		}
	}
}
