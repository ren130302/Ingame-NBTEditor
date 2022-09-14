package com.ren130302.meshi.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AssassinThrow extends BMEnchantmentBase {
    public AssassinThrow(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
	super(rarity, category, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
	return 40 + (enchantmentLevel - 1) * 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
	return super.getMinCost(enchantmentLevel) + 5;
    }

    @Override
    public int getMaxLevel() {
	return 6;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
	boolean isHunter = (ench instanceof PowerThrow || ench instanceof QuickThrow || ench instanceof HunterThrow);
	boolean isRogue = (ench instanceof CriticalThrow || ench instanceof PoisonThrow || ench instanceof RogueThrow);
	return (isHunter || isRogue) ? false : super.checkCompatibility(ench);
    }
}
