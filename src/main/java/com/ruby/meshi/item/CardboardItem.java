 package com.ruby.meshi.item;
 
 import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
 
 public class CardboardItem extends BlockItem {
    public CardboardItem(Block block, Properties properties) {
       super(block, properties);
    }
 
 
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
       return EquipmentSlotType.HEAD == armorType;
    }
 
 
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
       return EquipmentSlotType.HEAD;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/