package com.ren130302.meshi.define;

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.block.Andon;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public enum Blocks {
    TEST(() -> new Block(BlockBehaviour.Properties.of(Material.CACTUS))),
    ANDON(() -> new Andon(BlockBehaviour.Properties.of(Material.CACTUS)));

    static {
	Stream.of(values()).forEach(value -> {
	    final RegistryObject<Block> block = RegisterUtils.define(Defination.BLOCKS, value, value.block());
	    final RegistryObject<Item> item = RegisterUtils.define(Defination.ITEMS, value,
		    () -> new BlockItem(block.get(), value.prop()));
	});
    }

    private final Supplier<Block> block;
    private final Supplier<BlockItem> blockItem;
    private final Properties prop;

    private Blocks(Supplier<Block> block) {
	this(block, new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));
    }

    private Blocks(Supplier<Block> block, Properties prop) {
	this.block = block;
	this.blockItem = () -> new BlockItem(block.get(), prop);
	this.prop = prop;
    }

    public final Supplier<Block> block() {
	return this.block;
    }

    public final Supplier<BlockItem> blockItem() {
	return this.blockItem;
    }

    public final Properties prop() {
	return this.prop;
    }
}
