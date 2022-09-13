package com.ren130302.bamboo.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantments;

public class FlameThrow extends BMEnchantmentBase {
    public FlameThrow() {
	super(Rarity.RARE);
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
