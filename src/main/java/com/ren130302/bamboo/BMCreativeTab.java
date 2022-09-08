package com.ren130302.bamboo;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class BMCreativeTab {

//	public static final DeferredRegister<Enchantment> DEF_REG = BambooMod.REGISTER
//			.create(ForgeRegistries.TAENCHANTMENTS);

	public static CreativeModeTab ITEM_GROUP;
	public static CreativeModeTab DECO_GROUP;

	public static void init() {
		ITEM_GROUP = new CreativeModeTab("meshi.base") {
			@Override
			public ItemStack makeIcon() {
				return null;
			}
		};

		DECO_GROUP = new CreativeModeTab("meshi.deco") {
			@Override
			public ItemStack makeIcon() {
				return null;
			}
		};
	}
}
