package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class UnbreakingBracelet extends Enchantment {
	public UnbreakingBracelet() {
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

	public boolean checkCompatibility() {
		return false;
	}

	@Override
	public Component getFullname(int level) {
		return Enchantments.UNBREAKING.getFullname(level);
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