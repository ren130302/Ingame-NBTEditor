package com.ren130302.bamboo.block;

import com.ren130302.bamboo.BambooMod;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMBlocks {
    private static final DeferredRegister<Block> DEF_REG = BambooMod.REGISTER.create(ForgeRegistries.BLOCKS);

    public static final RegistryObject<Block> ANDON = DEF_REG.register("andon", Andon::new);

}
