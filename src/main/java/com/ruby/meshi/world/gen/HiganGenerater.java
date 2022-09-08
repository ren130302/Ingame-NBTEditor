 package com.ruby.meshi.world.gen;
 
 import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.world.biome.ExpelledBaseBiome;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.BiomeDictionary;
 public class HiganGenerater {
    public static final Feature<NoFeatureConfig> SAKURA_TREE;
    public static final Feature<NoFeatureConfig> MAPLE_TREE;
    public static final Feature<NoFeatureConfig> GINKGO_TREE;
    public static final Feature<NoFeatureConfig> HINOKI_TREE;
 
    static {
       SAKURA_TREE = register("sakura_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.SAKURA_SAPLING));
       MAPLE_TREE = register("maple_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.MAPLE_SAPLING));
       GINKGO_TREE = register("ginkgo_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.GINKGO_SAPLING));
       HINOKI_TREE = register("hinoki_tree", new HiganTreeFeature(NoFeatureConfig::func_214639_a, (SaplingBlock)SakuraBlocks.HINOKI_SAPLING));
    }
 
 
    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
       return (Feature)value.setRegistryName("meshi", key);
    }
 
 
 
    public static void addBiomeGen() {
       Set<Biome> forests = getBiomes(Type.FOREST);
       Set<Biome> forestsAndmountains = getBiomes(Type.FOREST, Type.MOUNTAIN);
       addTree(getSelectBiomes(forestsAndmountains, (b) -> {
 
 
          return BiomeDictionary.hasType(b, Type.COLD);
       }), MAPLE_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
       addTree(getSelectBiomes(forestsAndmountains, (b) -> {
 
 
          return BiomeDictionary.hasType(b, Type.COLD);
       }), GINKGO_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
       addTree(getSelectBiomes(forests, (b) -> {
 
 
          return !BiomeDictionary.hasType(b, Type.COLD);
       }), SAKURA_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
       addTree(forestsAndmountains, HINOKI_TREE, IFeatureConfig.field_202429_e, Placement.field_215027_m, new AtSurfaceWithExtraConfig(0, 0.01F, 1));
 
 
 
 
 
    }
 
    private static <F extends IFeatureConfig, D extends IPlacementConfig> void addTree(Set<Biome> biomes, Feature<F> feature, F conf, Placement<D> placer, D placeConf) {
       Iterator var5 = biomes.iterator();      while(var5.hasNext()) {         Biome b = (Biome)var5.next();
          b.func_203611_a(Decoration.VEGETAL_DECORATION, Biome.func_222280_a(feature, conf, placer, placeConf));
       }
    }
 
    private static <D extends IPlacementConfig> void addRandomTree(Set<Biome> biomes, Feature<?>[] features, IFeatureConfig[] iFeatureConfigs, float[] weight, Placement<D> placer, D placeConf) {
       Iterator var6 = biomes.iterator();      while(var6.hasNext()) {         Biome b = (Biome)var6.next();
          b.func_203611_a(Decoration.VEGETAL_DECORATION, Biome.func_222280_a(Feature.field_202292_al, new MultipleRandomFeatureConfig(features, iFeatureConfigs, weight, features[0], IFeatureConfig.field_202429_e), placer, placeConf));
       }
    }
 
    public static Set<Biome> getBiomes(Type... type) {
       Set<Biome> biome = Sets.newHashSet();      Type[] var2 = type;      int var3 = type.length;
       for(int var4 = 0; var4 < var3; ++var4) {         Type t = var2[var4];
          biome.addAll(BiomeDictionary.getBiomes(t));
 
       }
 
       biome.removeIf((b) -> {
          return b instanceof ExpelledBaseBiome;      });
       return biome;
    }
 
    private static Set<Biome> getSelectBiomes(Set<Biome> biomes, Predicate<Biome> tester) {
       return (Set)biomes.stream().filter(tester).collect(Collectors.toSet());
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 27 ms
	
	Decompiled with FernFlower.
*/