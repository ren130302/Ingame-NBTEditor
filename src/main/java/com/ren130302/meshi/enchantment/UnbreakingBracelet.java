package com.ren130302.meshi.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class UnbreakingBracelet extends BMEnchantmentBase {
    public UnbreakingBracelet(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
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
    public boolean isAllowedOnBooks() {
	return false;
    }

    @Override
    public Component getFullname(int level) {
	return Enchantments.UNBREAKING.getFullname(level);
    }
}
