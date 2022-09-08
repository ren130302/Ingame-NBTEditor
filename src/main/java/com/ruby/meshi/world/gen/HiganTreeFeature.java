 package com.ruby.meshi.world.gen;
 
 import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.serialization.Dynamic;

import net.minecraft.core.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
 
 
 
 
 public class HiganTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private final SaplingBlock sapling;
 
    public HiganTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config, SaplingBlock sapling) {
       super(config, false);
       this.sapling = sapling;
    }
 
 
    protected boolean func_208519_a(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox p_208519_5_) {
       return this.generateSapling((IWorld)worldIn, rand, pos);
    }
 
    private boolean generateSapling(IWorld world, Random rand, BlockPos pos) {
       if (world.func_175623_d(pos)) {
          BlockState block = world.func_180495_p(pos.func_177977_b());
          if (block.func_177230_c() == Blocks.field_196658_i || block.func_177230_c() == Blocks.field_150346_d) {
             this.sapling.func_176478_d(world, pos, (BlockState)this.sapling.func_176223_P().func_177231_a(SaplingBlock.field_176479_b), rand);
             return true;
          }      }
 
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/