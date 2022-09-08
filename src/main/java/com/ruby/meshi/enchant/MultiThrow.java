package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class MultiThrow extends Enchantment {
	public MultiThrow() {
		super(Rarity.RARE, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel == 1 ? 30 : (enchantmentLevel == 2 ? 70 : 90);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return enchantmentLevel == 1 ? 69 : (enchantmentLevel == 2 ? 85 : 100);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	public ITextComponent func_200305_d(int level) {
		ITextComponent itextcomponent = new TranslationTextComponent(this.func_77320_a() + "_" + level, new Object[0]);
		if (this.func_190936_d()) {
			itextcomponent.func_211708_a(TextFormatting.RED);
		} else {
			itextcomponent.func_211708_a(TextFormatting.GRAY);
		}

		return itextcomponent;
	}

	public boolean checkCompatibility() {
		return false;
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