 package com.ruby.meshi.fluid;
 
 import net.minecraft.world.level.material.FlowingFluid;
 
 
 
 public class MeshiFluids {
    public static final FlowingFluid FLOWING_HOT_SPING = (FlowingFluid)register("flowing_hot_spring", new Flowing());
    public static final FlowingFluid HOT_SPING = (FlowingFluid)register("hot_spring", new Source());
 
    private static <T extends Fluid> T register(String name, T fluid) {
       return (Fluid)fluid.setRegistryName("meshi", name);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/