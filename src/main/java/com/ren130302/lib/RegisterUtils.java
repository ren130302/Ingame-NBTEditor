package com.ren130302.lib;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterUtils {

    public static <T> RegistryObject<T> register(DeferredRegister<T> defReg, String name, Supplier<T> sup) {
	return defReg.register(name, sup);
    }

    public static <T> RegistryObject<T> register(DeferredRegister<T> defReg, String name, T sup) {
	return defReg.register(name, () -> sup);
    }

    /* block */

    public static RegistryObject<Block> blockAndItem(DeferredRegister<Block> defreg_blocks,
	    DeferredRegister<Item> defreg_items, String name, Block.Properties blockProp, Item.Properties itemProp) {
	return blockAndItem(defreg_blocks, defreg_items, name, Block::new, blockProp, itemProp);
    }

    public static RegistryObject<Block> blockAndItem(DeferredRegister<Block> defreg_blocks,
	    DeferredRegister<Item> defreg_items, String name, Function<Block.Properties, Block> block,
	    Block.Properties blockProp, Item.Properties itemProp) {
	final RegistryObject<Block> blockObj = block(defreg_blocks, name, block, blockProp);
	item(defreg_items, name, () -> new BlockItem(null, itemProp) {
	    @Override
	    public Block getBlock() {
		return blockObj.get();
	    }
	});
	return blockObj;
    }

    public static RegistryObject<Block> block(DeferredRegister<Block> defregItems, String name, Block.Properties prop) {
	return block(defregItems, name, Block::new, prop);
    }

    public static RegistryObject<Block> block(DeferredRegister<Block> defregItems, String name,
	    Function<Block.Properties, Block> item, Block.Properties prop) {
	return block(defregItems, name, () -> item.apply(prop));
    }

    public static RegistryObject<Block> block(DeferredRegister<Block> defregItems, String name, Supplier<Block> item) {
	return register(defregItems, name, item);
    }

    /* item */

    public static RegistryObject<Item> item(DeferredRegister<Item> defregItems, String name, Item.Properties prop) {
	return item(defregItems, name, Item::new, prop);
    }

    public static RegistryObject<Item> item(DeferredRegister<Item> defregItems, String name,
	    Function<Item.Properties, Item> item, Item.Properties prop) {
	return item(defregItems, name, () -> item.apply(prop));
    }

    public static RegistryObject<Item> item(DeferredRegister<Item> defregItems, String name, Supplier<Item> item) {
	return register(defregItems, name, item);
    }
}