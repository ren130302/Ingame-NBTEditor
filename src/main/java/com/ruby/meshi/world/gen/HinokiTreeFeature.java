 package com.ruby.meshi.world.gen;
 
 import com.mojang.datafixers.Dynamic;
 import com.ruby.meshi.block.SakuraBlocks;
 import java.util.Random;
 import java.util.Set;
 import java.util.function.Consumer;
 import java.util.function.Function;
 import java.util.function.Predicate;
 import net.minecraft.block.BlockState;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MutableBoundingBox;
 import net.minecraft.world.gen.IWorldGenerationReader;
 import net.minecraft.world.gen.feature.AbstractTreeFeature;
 import net.minecraft.world.gen.feature.NoFeatureConfig;
 public class HinokiTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private final BlockState log;
    private final BlockState leave;
 
       this.log = SakuraBlocks.HINOKI_LOG.func_176223_P();
       this.leave = SakuraBlocks.HINOKI_LEAVE.func_176223_P();
 
    public HinokiTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_, boolean doBlockNofityOnPlace) {
       super(p_i49920_1_, doBlockNofityOnPlace);
    }
 
 
    protected boolean func_208519_a(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
       int log_minheight = 6;
       int log_height = log_minheight + rand.nextInt(3);
       int leave_base = 2;
       int leave_top = 5;
       if (isSoil(worldIn, position.func_177977_b(), this.getSapling()) && position.func_177956_o() < worldIn.getMaxHeight() - log_height - 1) {
          int i;
          for(i = leave_base; i <= log_height + leave_top; ++i) {
             int posY = i - leave_base;
             int size = 3;
             if (posY == 0) {
                size = 1;
             } else if (posY == 1) {
                size = 2;
             } else if (posY >= log_height + leave_top - 3) {
                size = 0;
             } else if (posY >= log_height + leave_top - 5) {
                size = 1;
             } else if (posY >= log_height + leave_top - 7) {
                size = 2;
             }
             this.horizonLeaveBox(worldIn, position.func_177981_b(i), size, (p) -> {
                this.func_202278_a(worldIn, p, this.leave);            });         }
 
          for(i = 0; i < log_height; ++i) {
             if (func_214572_g(worldIn, position.func_177981_b(i))) {
                this.func_208520_a(changedBlocks, worldIn, position.func_177981_b(i), this.log, p_208519_5_);
             }         }
 
          return true;
       } else {
          return false;
       }
    }
    private void horizonLeaveBox(IWorldGenerationReader worldIn, BlockPos pos, int size, Consumer<BlockPos> cons) {
       if (size == 0) {
          cons.accept(pos);
       } else {
          int limit = 2;
          BlockPos.func_218281_b(pos.func_177982_a(-size, 0, -size), pos.func_177982_a(size, 0, size)).filter((p) -> {
             return func_214572_g(worldIn, p) && Math.abs(pos.func_218139_n(p)) <= size;         }).filter((p) -> {            return Math.max(Math.abs(pos.func_177958_n() - p.func_177958_n()), Math.abs(pos.func_177952_p() - p.func_177952_p())) <= limit;
          }).forEach(cons);
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 25 ms
	
	Decompiled with FernFlower.
*/