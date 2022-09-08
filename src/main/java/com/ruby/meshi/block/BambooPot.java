package com.ruby.meshi.block;

import com.ruby.meshi.block.tileentity.BambooPotTileEntity;

import net.minecraft.block.BlockRenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BambooPot extends Block {
	public static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	public static final BooleanProperty ATTACHED;

	static {
		ATTACHED = BlockStateProperties.ATTACHED;
	}

	public BambooPot(Properties properties) {
		super(properties);
		this.func_180632_j((BlockState) this.func_176223_P().func_206870_a(ATTACHED, false));
	}

	public BlockState func_196258_a(BlockItemUseContext context) {
		return super.func_196258_a(context);
	}

	protected void func_206840_a(Builder<Block, BlockState> builder) {
		super.func_206840_a(builder);
		builder.func_206894_a(new IProperty[] { ATTACHED });
	}

	public BlockRenderType func_149645_b(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public BlockRenderLayer func_180664_k() {
		return BlockRenderLayer.CUTOUT;
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	public boolean func_220060_c(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	public boolean func_220081_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	public boolean hasTileEntity(BlockState state) {
		return (Boolean) state.func_177229_b(ATTACHED);
	}

	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new BambooPotTileEntity();
	}
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 15 ms
 *
 * Decompiled with FernFlower.
 */