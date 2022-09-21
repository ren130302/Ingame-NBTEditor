package com.ren130302.lib;

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

    /* block */

    public static RegistryObject<Block> blockAndItem(DeferredRegister<Block> defreg_blocks,
	    DeferredRegister<Item> defreg_items, String name, Block.Properties blockProp, Item.Properties itemProp) {
	return blockAndItem(defreg_blocks, defreg_items, name, () -> new Block(blockProp), itemProp);
    }

    public static RegistryObject<Block> blockAndItem(DeferredRegister<Block> defreg_blocks,
	    DeferredRegister<Item> defreg_items, String name, Supplier<Block> block, Item.Properties itemProp) {
	final RegistryObject<Block> blockObj = register(defreg_blocks, name, block);
	register(defreg_items, name, () -> new AbstractBlockItem(blockObj, itemProp));
	return blockObj;
    }

    public static RegistryObject<Block> block(DeferredRegister<Block> defregItems, String name, Block.Properties prop) {
	return register(defregItems, name, () -> new Block(prop));
    }

    /* item */

    public static RegistryObject<Item> item(DeferredRegister<Item> defregItems, String name, Item.Properties prop) {
	return register(defregItems, name, () -> new Item(prop));
    }

//    public static RegistryObject<Item> itemblock(DeferredRegister<Item> defregItems, String name, Supplier<Item> item) {
//	return register(defregItems, name, () -> new ItemBlock);
//    }

    public static class AbstractBlockItem extends BlockItem {

	private final RegistryObject<Block> itemblockHolder;

	public AbstractBlockItem(RegistryObject<Block> itemblockHolder, Properties p_40566_) {
	    super(null, p_40566_);
	    this.itemblockHolder = itemblockHolder;
	}

	@Override
	public Block getBlock() {
	    return this.itemblockHolder.get();
	}

    }
}