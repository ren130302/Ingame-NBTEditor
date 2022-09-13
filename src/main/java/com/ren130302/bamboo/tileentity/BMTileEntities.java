package com.ren130302.bamboo.tileentity;

import com.ren130302.bamboo.BambooMod;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMTileEntities {
    private static final DeferredRegister<BlockEntityType<?>> DEF_REG = BambooMod.REGISTER
	    .create(ForgeRegistries.BLOCK_ENTITY_TYPES);
    public static final RegistryObject<BlockEntityType<?>> a = DEF_REG.register("slidedoor", null);
}
