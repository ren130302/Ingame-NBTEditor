package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantment;

public class ReturnThrow extends BMEnchantmentBase {
    protected ReturnThrow() {
	super(Rarity.VERY_RARE);
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
