 package com.ruby.meshi.block;
 
 import java.util.List;
import java.util.Random;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;
 
 public class HiganOre extends OreBlock {
    public HiganOre(Properties properties) {
       super(properties);
    }
 
 
    public ToolType getHarvestTool(BlockState state) {
       return ToolType.PICKAXE;
    }
 
 
    public int getHarvestLevel(BlockState state) {
       if (this == SakuraBlocks.SAKURA_ORE) {
          return 3;
       } else {
          return this == SakuraBlocks.GINKGO_ORE ? 2 : 1;
       }
    }
 
 
 
 
    protected int func_220281_a(Random rand) {
       if (this == SakuraBlocks.SAKURA_ORE) {
          return MathHelper.func_76136_a(rand, 6, 10);
       } else {
          return this == SakuraBlocks.GINKGO_ORE ? MathHelper.func_76136_a(rand, 3, 7) : 0;
       }
    }
 
 
 
 
    public List<ItemStack> func_220076_a(BlockState state, Builder builder) {
       List<ItemStack> list = super.func_220076_a(state, builder);
       list.forEach((stack) -> {         stack.func_190920_e(Math.max(1, stack.func_190916_E() / 2));      });
       return list;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 18 ms
	
	Decompiled with FernFlower.
*/