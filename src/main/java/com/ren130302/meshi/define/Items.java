package com.ren130302.meshi.define;

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.ren130302.lib.RegisterUtils;

import net.minecraft.world.item.Item;

public enum Items {
    NINJA_BRACELET(() -> new Item(new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get())));

    static {
	Stream.of(values()).forEach(value -> RegisterUtils.define(Defination.ITEMS, value, value.item()));
    }

    private final Supplier<Item> item;

    private Items(Supplier<Item> item) {
	this.item = item;
    }

    public final Supplier<Item> item() {
	return this.item;
    }
}
