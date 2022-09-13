package com.ren130302.bamboo.item;

import com.ren130302.bamboo.BambooMod;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMItems {
    private static final DeferredRegister<Item> DEF_REG = BambooMod.REGISTER.create(ForgeRegistries.ITEMS);

    public static final RegistryObject<Item> NINJA_BRACELET = DEF_REG.register("ninja_bracelet", NinjaBracelet::new);
}