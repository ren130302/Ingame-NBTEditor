 package com.ruby.meshi.block;
 
 import net.minecraft.block.LogBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
 
 public class HiganLogBlock extends LogBlock {
    public HiganLogBlock(MaterialColor color, Properties prop) {
       super(color, prop);
    }
 
 
    public ToolType getHarvestTool(BlockState state) {
       return ToolType.AXE;
    }
 
 
    public int getHarvestLevel(BlockState state) {
       return 0;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/