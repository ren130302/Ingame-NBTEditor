package com.ruby.meshi.enchant;

import com.ren130302.bamboo.enchantment.BMEnchantmentBase;
import com.ren130302.bamboo.enchantment.BMEnchantments;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class FlameThrow extends BMEnchantmentBase {
	public FlameThrow() {
		super(Rarity.RARE, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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