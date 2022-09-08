 package com.ruby.meshi.common.inventory.slot;
 
 import net.minecraft.inventory.IInventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
 
 
 public class NonInsertSlot extends Slot {
    public NonInsertSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
       super(inventoryIn, index, xPosition, yPosition);
    }
 
 
    public boolean func_75214_a(ItemStack stack) {
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/