package com.ruby.meshi.world.biome;

import java.util.function.Supplier;

import com.ren130302.bamboo.BambooMod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HiganBiomes {
	private static final DeferredRegister<Biome> DEF_REG = BambooMod.REGISTER.create(ForgeRegistries.BIOMES);

	public static ResourceKey<Biome> EX_PLAINS_BIOME = register("expelledplains", ExpelledPlainsBiome::new);
	public static ResourceKey<Biome> EX_AUTUMN_BIOME = register("expelledautumn", ExpelledAutumnBiome::new);
	public static ResourceKey<Biome> EX_SPRING_BIOME = register("expelledspring", ExpelledSpringBiome::new);

	private static RegistryObject<Biome> register(String key, Supplier<Biome> biome) {
		return DEF_REG.register(key, biome);
	}

	public static void addBiomes() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(EX_PLAINS_BIOME, 15));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(EX_AUTUMN_BIOME, 10));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(EX_SPRING_BIOME, 5));
		// BiomeManager.addSpawnBiome(EX_PLAINS_BIOME);
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 5 ms
 *
 * Decompiled with FernFlower.
 */