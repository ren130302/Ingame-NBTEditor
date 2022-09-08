 package com.ruby.meshi.block;
 
 import com.ruby.meshi.item.HiganItems;

import net.minecraft.block.CropsBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
 
 
 public class RicePlant extends CropsBlock implements CustomItemBlock {
    protected RicePlant(Properties builder) {
       super(builder);
    }
 
 
    public IItemProvider func_199772_f() {
       return HiganItems.RICE_SEED;
    }
 
 
    public ItemStack func_185473_a(IBlockReader worldIn, BlockPos pos, BlockState state) {
       return new ItemStack(this.func_199772_f());
    }
 
 
    public Item getBlockItem(Block block, net.minecraft.item.Item.Properties prop) {
       return EMPTY;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/