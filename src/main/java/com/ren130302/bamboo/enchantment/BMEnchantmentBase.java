package com.ren130302.bamboo.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class BMEnchantmentBase extends Enchantment {

    protected BMEnchantmentBase(Rarity rarity) {
	super(rarity, BMEnchantments.Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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
