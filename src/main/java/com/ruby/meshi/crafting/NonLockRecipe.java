 package com.ruby.meshi.crafting;
 
 import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
 
 
 public interface NonLockRecipe {
    default boolean isUnlocked(IRecipe<? extends IInventory> recipe) {
       return true;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 2 ms
	
	Decompiled with FernFlower.
*/