package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantments;

public class UnbreakingBracelet extends BMEnchantmentBase {
    protected UnbreakingBracelet() {
	super(Rarity.UNCOMMON);
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
    public boolean isAllowedOnBooks() {
	return false;
    }

    @Override
    public Component getFullname(int level) {
	return Enchantments.UNBREAKING.getFullname(level);
    }
}
