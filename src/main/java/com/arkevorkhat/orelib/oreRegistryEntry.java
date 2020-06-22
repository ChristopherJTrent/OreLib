package com.arkevorkhat.orelib;


import com.arkevorkhat.orelib.Strategies.OreGenerationStrategyBase;

import net.minecraft.block.Block;

public class oreRegistryEntry
{
	Block block;
	OreGenerationStrategyBase oreGenerationStrategy;
	
	public oreRegistryEntry(Block b, OreGenerationStrategyBase s)
	{
		block = b;
		oreGenerationStrategy = s;
	}
	
	public Block getBlock(){return block;}
	
	public void setBlock(Block b){ block = b;}
	
	public OreGenerationStrategyBase getOreGenerationStrategy(){return oreGenerationStrategy;}
	
	public void setOreGenerationStrategy(OreGenerationStrategyBase s){oreGenerationStrategy = s;}
}