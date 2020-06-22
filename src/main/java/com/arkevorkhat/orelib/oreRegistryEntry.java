package com.arkevorkhat.orelib;


import com.arkevorkhat.orelib.strategies.OreGenerationStrategyBase;

import net.minecraft.block.Block;

public class oreRegistryEntry
{
	final Block block;
	final OreGenerationStrategyBase oreGenerationStrategy;
	
	public oreRegistryEntry(Block b, OreGenerationStrategyBase s)
	{
		block = b;
		oreGenerationStrategy = s;
	}
	
	public Block getBlock(){return block;}
	public OreGenerationStrategyBase getOreGenerationStrategy(){return oreGenerationStrategy;}
}