package com.arkevorkhat.orelib.Strategies;

import com.sun.istack.internal.NotNull;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Arkevorkhat
 */
public class BiasedCountGenerationStrategy extends OreGenerationStrategyBase {
	int size;
	int count;
	int lowerBound;
	int upperBias;
	int maximumHeight;
	boolean whitelist;
	List<Biome.Category> Biomes;
	public BiasedCountGenerationStrategy(){
		size = 10;
		count = 5;
		lowerBound = 1;
		upperBias = 1;
		maximumHeight = 128;
		Biomes = new ArrayList<>();
		Biomes.addAll(Arrays.asList(Biome.Category.THEEND, Biome.Category.NETHER));
		whitelist = false;
	}
	public BiasedCountGenerationStrategy(int size,
	                                     int count,
	                                     int lowerBound,
	                                     int upperBias,
	                                     int maximumHeight
	) {
		this.size = size;
		this.count = count;
		this.lowerBound = lowerBound;
		this.upperBias = upperBias;
		this.maximumHeight = maximumHeight;
		Biomes = new ArrayList<>();
		Biomes.addAll(Arrays.asList(Biome.Category.THEEND, Biome.Category.NETHER));
		whitelist = false;
	}
	public BiasedCountGenerationStrategy(
		  int size,
		  int count,
		  int lowerBound,
		  int upperBias,
		  int maximumHeight,
		  boolean whiteList,
		  Biome.Category ... Biomes
	) {
		this.size = size;
		this.count = count;
		this.lowerBound = lowerBound;
		this.upperBias = upperBias;
		this.maximumHeight = maximumHeight;
		this.Biomes = new ArrayList<>();
		this.Biomes.addAll(Arrays.asList(Biomes));
		this.whitelist = whiteList;
	}
	
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public List<Biome.Category> getBiomeCategories() {
		if(Objects.isNull(Biomes)){
			throw new IllegalStateException();
		} else {
			return Biomes;
		}
	}
	
	@Override
	public boolean biomesAreWhitelisted() {
		return whitelist;
	}
	
	@Override
	public OreFeatureConfig getOreConfig(Block block) {
		return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.getDefaultState(), size);
	}
	
	@Override
	public CountRangeConfig getGeneratorConfig() {
		return new CountRangeConfig(
			  count,
			  lowerBound,
			  upperBias,
			  maximumHeight
		);
	}
	
	@Override
	public ConfiguredFeature<?, ?> getConfiguredFeature(Block block) {
		return Feature.ORE.withConfiguration(
			  this.getOreConfig(block))
			  .withPlacement(Placement.COUNT_BIASED_RANGE.configure(
					this.getGeneratorConfig()));
	}
}
