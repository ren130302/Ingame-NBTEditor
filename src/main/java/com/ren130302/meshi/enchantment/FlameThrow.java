package com.ren130302.meshi.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FlameThrow extends BMEnchantmentBase {
    public FlameThrow(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return 20;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return 50;
    }

    @Override
    public int getMaxLevel() {
	return 1;
    }

    @Override
    public Component getFullname(int level) {
	return Enchantments.FLAMING_ARROWS.getFullname(level);
    }
}
