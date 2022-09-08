 package com.ruby.meshi.block;
 
 import net.minecraft.world.item.BlockItem;
 public interface CustomItemBlock {
    Item EMPTY = Items.field_190931_a;
 
    default Properties getProperty(Properties prop) {
       return prop;
    }
 
    default Item getBlockItem(Block block, Properties prop) {
       return new BlockItem(block, this.getProperty(prop));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/