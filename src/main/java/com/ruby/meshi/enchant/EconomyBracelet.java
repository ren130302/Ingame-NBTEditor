package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantmentBase;
import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class EconomyBracelet extends BMEnchantmentBase {
	public EconomyBracelet() {
		super(Rarity.UNCOMMON, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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
	public boolean checkCompatibility(Enchantment ench) {
		return ench instanceof InfinityThrow ? false : super.checkCompatibility(ench);
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 7 ms
 *
 * Decompiled with FernFlower.
 */