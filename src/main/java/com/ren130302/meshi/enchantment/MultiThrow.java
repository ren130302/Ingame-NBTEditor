package com.ren130302.meshi.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MultiThrow extends BMEnchantmentBase {
    public MultiThrow(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
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
