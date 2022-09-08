package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantment;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class Pickpocket extends Enchantment {
	public Pickpocket() {
		super(Rarity.UNCOMMON, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 20 - 10;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 10;
	}

	public boolean checkCompatibility() {
		return false;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	public ITextComponent func_200305_d(int level) {
		ITextComponent itextcomponent = new TranslationTextComponent(this.func_77320_a(), new Object[0]);
		if (this.func_190936_d()) {
			itextcomponent.func_211708_a(TextFormatting.RED);
		} else {
			itextcomponent.func_211708_a(TextFormatting.GRAY);
		}

		return itextcomponent;
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