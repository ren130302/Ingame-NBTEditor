package com.ruby.meshi.block;

import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PlayerFacingBlock extends HorizontalDirectionalBlock {
	public PlayerFacingBlock(Properties properties) {
		super(properties);
		this.func_180632_j((BlockState) ((BlockState) this.field_176227_L.func_177621_b()).func_206870_a(field_185512_D,
				Direction.NORTH));
	}

	public BlockState func_196258_a(BlockItemUseContext context) {
		return (BlockState) this.func_176223_P().func_206870_a(field_185512_D, context.func_195992_f().func_176734_d());
	}

	protected void func_206840_a(Builder<Block, BlockState> builder) {
		builder.func_206894_a(new IProperty[] { field_185512_D });
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 10 ms
 *
 * Decompiled with FernFlower.
 */