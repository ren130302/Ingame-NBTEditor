 package com.ruby.meshi.crafting;
 
 import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
 
 
 
 public class HiganRecipeSerializer {
    public static final IRecipeSerializer<?> GRIND = register("grind", new Serializer());
    public static final IRecipeSerializer<?> HEARTH = register("hearth", new com.ruby.meshi.crafting.HearthRecipe.Serializer());
    public static final IRecipeSerializer<?> HEARTH_SHAPELESS = register("hearth_shapeless", new com.ruby.meshi.crafting.HearthShapelessRecipe.Serializer());
 
    private static <T extends IRecipe<?>> IRecipeSerializer<?> register(String key, IRecipeSerializer<T> serializer) {
       return (IRecipeSerializer)serializer.setRegistryName(new ResourceLocation("meshi", key));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/