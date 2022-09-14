package com.ren130302.meshi.define;

import com.ren130302.meshi.BambooMod;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public enum Items {
    NINJA_BRACELET(new Item(new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get())));

    static {
	DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BambooMod.MODID);

	for (Items value : values()) {
	    ITEMS.register(value.name().toLowerCase(), () -> value.get());
	}

	ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private final Item item;

    private Items(Item item) {
	this.item = item;
    }

    public final Item get() {
	return this.item;
    }
}
