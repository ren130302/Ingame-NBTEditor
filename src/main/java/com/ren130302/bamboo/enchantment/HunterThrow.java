package com.ren130302.bamboo.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class HunterThrow extends BMEnchantmentBase {
    public HunterThrow() {
	super(Rarity.RARE);
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
