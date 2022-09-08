package com.ruby.meshi.world.gen;

import java.util.Random;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HinokiTree extends AbstractTreeGrower {
	protected AbstractTreeFeature<NoFeatureConfig> func_196936_b(Random random) {
		return new HinokiTreeFeature(NoFeatureConfig::func_214639_a, true);
	}

	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource source, boolean p_222911_) {
		return null;
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 4 ms
 * 
 * Decompiled with FernFlower.
 */