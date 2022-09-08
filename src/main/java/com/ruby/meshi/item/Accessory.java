 package com.ruby.meshi.item;
 
 import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
 
 
 public interface Accessory {
    default void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 9 ms
	
	Decompiled with FernFlower.
*/