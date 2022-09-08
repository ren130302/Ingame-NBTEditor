 package com.ruby.meshi.block.decorate;
 
 import com.ruby.meshi.block.CustomItemBlock;
import com.ruby.meshi.client.CreativeTab;

import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
 
 
 
 public class DecorateBlock extends Block implements CustomItemBlock {
    public DecorateBlock(Properties properties) {
       super(properties);
    }
 
 
    public net.minecraft.item.Item.Properties getProperty(net.minecraft.item.Item.Properties prop) {
       return prop.func_200916_a(CreativeTab.DECO_GROUP);
    }
 
 
    public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/