 package com.ruby.meshi.world.biome;
 
 import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.world.gen.HiganGenerater;

import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
 public class ExpelledAutumnBiome extends ExpelledBaseBiome {
    protected ExpelledAutumnBiome() {
       super((new Builder()).func_222351_a(SurfaceBuilder.field_215396_G, SurfaceBuilder.field_215425_v).func_205415_a(RainType.RAIN).func_205419_a(Category.FOREST).func_205421_a(0.75F).func_205420_b(0.25F).func_205414_c(0.6F).func_205417_d(0.4F).func_205412_a(4159204).func_205413_b(329011).func_205418_a((String)null));
       this.addTree();
    }
 
    private void addTree() {
       this.func_203611_a(Decoration.VEGETAL_DECORATION, Biome.func_222280_a(Feature.field_202292_al, new MultipleRandomFeatureConfig(new Feature[]{HiganGenerater.MAPLE_TREE, HiganGenerater.GINKGO_TREE, HiganGenerater.HINOKI_TREE}, new IFeatureConfig[]{IFeatureConfig.field_202429_e, IFeatureConfig.field_202429_e, IFeatureConfig.field_202429_e}, new float[]{0.3F, 0.3F, 0.3F}, Feature.field_202301_A, IFeatureConfig.field_202429_e), Placement.field_215027_m, new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
    }
 
 
    public void addModOres(Biome biomeIn) {
       super.addModOres(biomeIn);
       biomeIn.func_203611_a(Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, SakuraBlocks.GINKGO_ORE.func_176223_P(), 4), Placement.field_215028_n, new CountRangeConfig(1, 0, 0, 16)));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/