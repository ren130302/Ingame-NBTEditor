package com.ren130302.bamboo.enchantment;

import com.ruby.meshi.enchant.CriticalThrow;
import com.ruby.meshi.enchant.HunterThrow;
import com.ruby.meshi.enchant.PoisonThrow;
import com.ruby.meshi.enchant.PowerThrow;
import com.ruby.meshi.enchant.QuickThrow;
import com.ruby.meshi.enchant.RogueThrow;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BMEnchantmentBase extends Enchantment {

	protected BMEnchantmentBase(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
		super(rarity, category, slots);
	}

	@Override
	public Component getFullname(int level) {
		if (level < this.getMaxLevel()) {
			return super.getFullname(level);
		} else {
			final MutableComponent component = Component.translatable(this.getDescriptionId() + "_ex");
			final ChatFormatting color = this.isCurse() ? ChatFormatting.RED : ChatFormatting.GRAY;
			if (this.getMaxLevel() < level) {
				component.append("★");
			}
			return component.withStyle(color);
		}
	}

	protected final boolean isHunter(Enchantment ench) {
		return ench instanceof PowerThrow || ench instanceof QuickThrow || ench instanceof HunterThrow;
	}

	protected final boolean isRogue(Enchantment ench) {
		return ench instanceof CriticalThrow || ench instanceof PoisonThrow || ench instanceof RogueThrow;
	}
}
