package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;

public class MultiThrow extends BMEnchantmentBase {
    protected MultiThrow() {
	super(Rarity.RARE);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return (enchantmentLevel == 1) ? 30 : ((enchantmentLevel == 2) ? 70 : 90);
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return (enchantmentLevel == 1) ? 69 : ((enchantmentLevel == 2) ? 85 : 100);
    }

    @Override
    public int getMaxLevel() {
	return 3;
    }

    @Override
    public Component getFullname(int level) {
	return Component.translatable(this.getDescriptionId() + "_" + level).withStyle(this.curseColor());
    }

    @Override
    public boolean isAllowedOnBooks() {
	return false;
    }
}
