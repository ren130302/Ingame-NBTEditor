package com.ren130302.meshi.define;

import com.ren130302.meshi.BambooMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public enum Blocks {

    ANDON(new Block(BlockBehaviour.Properties.of(Material.CACTUS)),
	    new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));

    static {
	DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BambooMod.MODID);

	for (Blocks value : values()) {
	    BLOCKS.register(value.name().toLowerCase(), () -> value.get());
	}

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
