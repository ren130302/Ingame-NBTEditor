package com.ren130302.meshi.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EconomyBracelet extends BMEnchantmentBase {
    public EconomyBracelet(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return 5 + (enchantmentLevel - 1) * 40;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return super.getMinCost(enchantmentLevel) + (this.getMaxLevel() - enchantmentLevel + 1) * 15;
    }

    @Override
    public int getMaxLevel() {
	return 3;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
	return (ench instanceof InfinityThrow) ? false : super.checkCompatibility(ench);
    }
}
