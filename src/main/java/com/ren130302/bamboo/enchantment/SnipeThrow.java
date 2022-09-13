package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;

public class SnipeThrow extends BMEnchantmentBase {
    public SnipeThrow() {
	super(Rarity.COMMON);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return enchantmentLevel * 20 + 10;
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
	return 4;
    }

    @Override
    public Component getFullname(int level) {
	return Component.translatable(this.getDescriptionId()).withStyle(this.curseColor());
    }
}
