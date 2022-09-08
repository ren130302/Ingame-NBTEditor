 package com.ruby.meshi.world.biome;
 
 import java.util.Random;

import com.ruby.meshi.block.SakuraBlocks;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
 
 public abstract class ExpelledBaseBiome extends Biome {
    protected ExpelledBaseBiome(Builder biomeBuilder) {
       super(biomeBuilder);
 
       DefaultBiomeFeatures.func_222326_g(this);
       DefaultBiomeFeatures.func_222288_h(this);
       this.addModOres(this);
    }
 
    public void addModOres(Biome biomeIn) {
       biomeIn.func_203611_a(Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, SakuraBlocks.MAPLE_ORE.func_176223_P(), 8), Placement.field_215028_n, new CountRangeConfig(20, 0, 0, 64)));
       biomeIn.func_203611_a(Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, SakuraBlocks.GINKGO_ORE.func_176223_P(), 4), Placement.field_215028_n, new CountRangeConfig(1, 0, 0, 32)));
       biomeIn.func_203611_a(Decoration.UNDERGROUND_ORES, Biome.func_222280_a(Feature.field_202290_aj, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, SakuraBlocks.SAKURA_ORE.func_176223_P(), 1), Placement.field_215028_n, new CountRangeConfig(1, 0, 0, 32)));
    }
 
 
    public void func_206854_a(Random random, IChunk chunkIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
       super.func_206854_a(random, chunkIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);
 
    }
 
 
    protected void func_201866_a(EntityClassification type, SpawnListEntry spawnListEntry) {
    }
 
 
    public float func_76741_f() {
       return 0.0F;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/