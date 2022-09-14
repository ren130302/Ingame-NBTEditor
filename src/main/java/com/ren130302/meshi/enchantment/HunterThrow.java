package com.ren130302.meshi.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HunterThrow extends BMEnchantmentBase {
    public HunterThrow(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return 5 + (enchantmentLevel - 1) * 18;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return super.getMinCost(enchantmentLevel) + 17;
    }

    @Override
    public int getMaxLevel() {
	return 6;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
	return (ench instanceof PowerThrow || ench instanceof QuickThrow || ench instanceof AssassinThrow) ? false
		: super.checkCompatibility(ench);
    }
}
