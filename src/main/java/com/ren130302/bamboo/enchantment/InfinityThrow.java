package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;

public class InfinityThrow extends BMEnchantmentBase {
    protected InfinityThrow() {
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

    @Override
    public boolean checkCompatibility(Enchantment ench) {
	return (ench instanceof MendingEnchantment || ench instanceof EconomyBracelet || ench instanceof ReturnThrow)
		? false
		: super.checkCompatibility(ench);
    }
}