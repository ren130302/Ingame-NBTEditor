package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantmentBase;
import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class AssassinThrow extends BMEnchantmentBase {
	public AssassinThrow() {
		super(Rarity.VERY_RARE, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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
	public boolean checkCompatibility(Enchantment ench) {
		boolean isHunter = ench instanceof PowerThrow || ench instanceof QuickThrow || ench instanceof HunterThrow;
		boolean isRogue = ench instanceof CriticalThrow || ench instanceof PoisonThrow || ench instanceof RogueThrow;
		return !isHunter && !isRogue ? super.checkCompatibility(ench) : false;
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 12 ms
 *
 * Decompiled with FernFlower.
 */