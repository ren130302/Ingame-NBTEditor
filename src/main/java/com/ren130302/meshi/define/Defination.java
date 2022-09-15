package com.ren130302.meshi.define;

import com.ren130302.meshi.BambooMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Defination {
    static final DeferredRegister<Item> ITEMS = BambooMod.REGISTER.create(ForgeRegistries.ITEMS);
    static final DeferredRegister<Block> BLOCKS = BambooMod.REGISTER.create(ForgeRegistries.BLOCKS);
    static final DeferredRegister<Enchantment> ENCHANTMENTS = BambooMod.REGISTER.create(ForgeRegistries.ENCHANTMENTS);
    static {
	Blocks.values();
	Items.values();
	Enchantments.values();

	final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
	BLOCKS.register(bus);
	ITEMS.register(bus);
	ENCHANTMENTS.register(bus);
    }
}
