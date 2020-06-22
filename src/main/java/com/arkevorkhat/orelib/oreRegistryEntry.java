package com.arkevorkhat.orelib;


import com.arkevorkhat.orelib.Strategies.OreGenerationStrategyBase;
import net.minecraft.block.Block;

public class oreRegistryEntry{
	Block block;
	OreGenerationStrategyBase oreGenerationStrategy;
	public oreRegistryEntry(Block b, OreGenerationStrategyBase s){
		block = b;
		oreGenerationStrategy = s;
	}
	public void setBlock(Block b){ block = b;}
	public void setOreGenerationStrategy(OreGenerationStrategyBase s) {oreGenerationStrategy = s;}
	public Block getBlock(){return block;}
	public OreGenerationStrategyBase getOreGenerationStrategy(){return oreGenerationStrategy;}
}