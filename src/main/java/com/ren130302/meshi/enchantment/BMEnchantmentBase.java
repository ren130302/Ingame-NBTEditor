package com.ren130302.meshi.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
	}

	Component component = Component.translatable(this.getDescriptionId() + "_ex").withStyle(this.curseColor());

	if (this.getMaxLevel() < level) {
	    component.plainCopy().append("★");
	}

	return component;
    }

    protected final ChatFormatting curseColor() {
	return this.isCurse() ? ChatFormatting.RED : ChatFormatting.GRAY;
    }

}
