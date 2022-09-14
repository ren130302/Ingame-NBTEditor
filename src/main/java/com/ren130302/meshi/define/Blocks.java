package com.ren130302.meshi.define;

import java.util.stream.Stream;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.BambooMod;
import com.ren130302.meshi.block.Andon;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public enum Blocks {

    ANDON(new Andon(BlockBehaviour.Properties.of(Material.CACTUS)),
	    new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));

    static {
	final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BambooMod.MODID);

	Stream.of(values()).forEach(value -> RegisterUtils.define(BLOCKS, value, () -> value.get()));

	BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private final Block block;

    private Blocks(Block block, Properties tab) {
	this.block = block;
    }

    public final Block get() {
	return this.block;
    }
}
