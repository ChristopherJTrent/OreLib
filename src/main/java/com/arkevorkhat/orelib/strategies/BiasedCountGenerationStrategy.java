package com.arkevorkhat.orelib.strategies;

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
 @author Arkevorkhat */
public class BiasedCountGenerationStrategy extends OreGenerationStrategyBase
{
	int size;
	int count;
	int lowerBound;
	int upperBias;
	int maximumHeight;
	boolean whitelist;
	List<Biome.Category> Biomes;
	
	/**
	 * Default constructor:
	 * defines a basic ore generation strategy with the following parameters
	 * how many blocks will generate in the vein (size)
	 * the number of veins that will generate per chunk (count)
	 * minimum Y value for the ore to generate (lowerBound)
	 * an undocumented and (to me) poorly understood value (upperBias)
	 * the maximum Y value where the ore will generate (maximumHeight)
	 * a list of Biome.Category objects (Biomes)
	 *      (defaults to blacklisting the End and the Nether.)
	 * a boolean determining whether Biomes will be treated as a whitelist or a blacklist (whitelist)
	 *      (whitelist = false means that Biomes will be treated as a blacklist.)
	 */
	public BiasedCountGenerationStrategy()
	{
		size = 10;
		count = 5;
		lowerBound = 1;
		upperBias = 1;
		maximumHeight = 128;
		Biomes = new ArrayList<>();
		Biomes.addAll(Arrays.asList(Biome.Category.THEEND, Biome.Category.NETHER));
		whitelist = false;
	}
	
	/**
	 * Basic parameterized constructor
	 * @see BiasedCountGenerationStrategy#BiasedCountGenerationStrategy()
	 * @param size number of ore blocks in a vein
	 * @param count number of veins in a chunk
	 * @param lowerBound lowest Y level a vein can generate at
	 * @param upperBias required by Minecraft, undocumented. (safe default value = 1)
	 * @param maximumHeight highest Y level a vein can generate at
	 *
	 */
	public BiasedCountGenerationStrategy(int size,
	                                     int count,
	                                     int lowerBound,
	                                     int upperBias,
	                                     int maximumHeight)
	{
		this.size = size;
		this.count = count;
		this.lowerBound = lowerBound;
		this.upperBias = upperBias;
		this.maximumHeight = maximumHeight;
		Biomes = new ArrayList<>();
		Biomes.addAll(Arrays.asList(Biome.Category.THEEND, Biome.Category.NETHER));
		whitelist = false;
	}
	
	/**
	 * Advanced parameterized constructor
	 * @see BiasedCountGenerationStrategy#BiasedCountGenerationStrategy()
	 * @param size number of ore blocks in a vein
	 * @param count number of veins in a chunk
	 * @param lowerBound lowest Y level a vein can generate at
	 * @param upperBias required by Minecraft, undocumented (safe default value = 1)
	 * @param maximumHeight highest Y level a vein can generate at
	 * @param whiteList whether Biomes is treated as a whitelist (true) or a blacklist (false)
	 * @param Biomes a list (or vararg) of Biome.Category objects, used to create the biome filter
	 */
	public BiasedCountGenerationStrategy(int size,
	                                     int count,
	                                     int lowerBound,
	                                     int upperBias,
	                                     int maximumHeight,
	                                     boolean whiteList,
	                                     Biome.Category... Biomes)
	{
		this.size = size;
		this.count = count;
		this.lowerBound = lowerBound;
		this.upperBias = upperBias;
		this.maximumHeight = maximumHeight;
		this.Biomes = new ArrayList<>();
		this.Biomes.addAll(Arrays.asList(Biomes));
		this.whitelist = whiteList;
	}
	
	/**
	 * @return size (the vein size)
	 * @see BiasedCountGenerationStrategy#BiasedCountGenerationStrategy()
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * @return a list of Biome Categories
	 * @exception IllegalStateException if the biomes list is somehow null.
	 */
	@Override
	public List<Biome.Category> getBiomeCategories()
	{
		if(Objects.isNull(Biomes))
		{
			throw new IllegalStateException();
		}
		else
		{
			return Biomes;
		}
	}
	
	/**
	 * @return whitelist
	 * @see BiasedCountGenerationStrategy#BiasedCountGenerationStrategy() 
	 */
	@Override
	public boolean biomesAreWhitelisted()
	{
		return whitelist;
	}
	
	/**
	 * @param block the block to be registered
	 * @return a configured OreFeatureConfig object with the default FillerBlockType Natural Stone (Overworld)
	 */
	public OreFeatureConfig getOreConfig(Block block)
	{
		return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
		                            block.getDefaultState(),
		                            size);
	}
	
	/**
	 * @return a CountRangeConfig object with the proper values
	 */
	public CountRangeConfig getGeneratorConfig()
	{
		return new CountRangeConfig(count, lowerBound, upperBias, maximumHeight);
	}
	
	@Override
	public ConfiguredFeature<?, ?> getConfiguredFeature(Block block)
	{
		return Feature.ORE.withConfiguration(this.getOreConfig(block))
		                  .withPlacement(Placement.COUNT_BIASED_RANGE.configure(this.getGeneratorConfig()));
	}
}
