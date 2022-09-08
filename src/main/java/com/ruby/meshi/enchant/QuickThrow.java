package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantmentBase;
import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class QuickThrow extends BMEnchantmentBase {
	public QuickThrow() {
		super(Rarity.COMMON, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + (enchantmentLevel - 1) * 18;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 17;
	}

	@Override
	public int getMaxLevel() {
		return 6;
	}

	@Override
	public boolean checkCompatibility(Enchantment ench) {
		return !(ench instanceof PowerThrow) && !(ench instanceof HunterThrow) && !(ench instanceof AssassinThrow)
				? super.checkCompatibility(ench)
				: false;
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 6 ms
 *
 * Decompiled with FernFlower.
 */