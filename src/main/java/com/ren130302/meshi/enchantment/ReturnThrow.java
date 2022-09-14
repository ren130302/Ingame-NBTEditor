package com.ren130302.meshi.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ReturnThrow extends BMEnchantmentBase {
    public ReturnThrow(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return enchantmentLevel * 20 - 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return this.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public boolean isAllowedOnBooks() {
	return false;
    }

    @Override
    public int getMaxLevel() {
	return 5;
    }

    @Override
    public Component getFullname(int level) {
	return Component.translatable(this.getDescriptionId()).withStyle(this.curseColor());
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
	return (ench instanceof InfinityThrow) ? false : super.checkCompatibility(ench);
    }
}
