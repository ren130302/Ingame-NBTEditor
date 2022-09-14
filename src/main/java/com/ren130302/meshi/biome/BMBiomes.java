package com.ren130302.meshi.biome;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BMBiomes {
    private static DeferredRegister<Biome> DEF_REG;
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> HINOKI = FeatureUtils.register("hinoki",
	    Feature.TREE, createOak().build());

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block p_195147_, Block p_195148_,
	    int p_195149_, int p_195150_, int p_195151_, int p_195152_) {
	return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(p_195147_),
		new StraightTrunkPlacer(p_195149_, p_195150_, p_195151_), BlockStateProvider.simple(p_195148_),
		new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3),
		new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createOak() {
	return createStraightBlobTree(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2).ignoreVines();
    }

    public static final RegistryObject<Biome> _HINOKI = DEF_REG.register("hinoki", () -> null);
}
