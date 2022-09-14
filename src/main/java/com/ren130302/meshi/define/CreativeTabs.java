package com.ren130302.meshi.define;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public enum CreativeTabs {

    ITEM_GROUP("meshi.item", null), DECO_GROUP("meshi.deco", null);

    private final CreativeModeTab creativeTab;

    private CreativeTabs(String label, ItemLike icon) {
	this.creativeTab = new CreativeModeTab(label) {
	    @Override
	    public ItemStack makeIcon() {
		return new ItemStack(icon);
	    }
	};
    }

    public CreativeModeTab get() {
	return this.creativeTab;
    }
}