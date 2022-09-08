 package com.ruby.meshi.block.decorate;
 
 import com.ruby.meshi.block.CustomItemBlock;
import com.ruby.meshi.client.CreativeTab;

import net.minecraft.block.StairsBlock;
import net.minecraft.world.level.block.state.BlockState;
 
 
 
 public class DecorateStairs extends StairsBlock implements CustomItemBlock {
    public DecorateStairs(BlockState p_i48321_1_, Properties p_i48321_2_) {
       super(p_i48321_1_, p_i48321_2_);
    }
 
 
    public net.minecraft.item.Item.Properties getProperty(net.minecraft.item.Item.Properties prop) {
       return prop.func_200916_a(CreativeTab.DECO_GROUP);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 4 ms
	
	Decompiled with FernFlower.
*/