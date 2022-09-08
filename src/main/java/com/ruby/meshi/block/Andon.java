package com.ruby.meshi.block;

import net.minecraft.core.BlockPos;
import net.minecraft.state.IProperty;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Andon extends PlayerFacingBlock {
	public static final BooleanProperty BOTTOM;
	public static final VoxelShape AABB;

	static {
		BOTTOM = BlockStateProperties.BOTTOM;
		AABB = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
	}

	public Andon(Properties properties) {
		super(properties);
		this.func_180632_j((BlockState) this.func_176223_P().func_206870_a(BOTTOM, false));
	}

	protected void func_206840_a(Builder<Block, BlockState> builder) {
		super.func_206840_a(builder);
		builder.func_206894_a(new IProperty[] { BOTTOM });
	}

	public void func_220082_b(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.func_220082_b(state, worldIn, pos, oldState, isMoving);
		this.onGround(state, worldIn, pos);
	}

	public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
		this.onGround(state, worldIn, pos);
	}

	private void onGround(BlockState state, World worldIn, BlockPos pos) {
		worldIn.func_175656_a(pos,
				(BlockState) state.func_206870_a(BOTTOM, !worldIn.func_175623_d(pos.func_177977_b())));
	}

	public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB;
	}

	public BlockRenderLayer func_180664_k() {
		return BlockRenderLayer.CUTOUT;
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
}

/*
 * DECOMPILATION REPORT
 *
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 26 ms
 *
 * Decompiled with FernFlower.
 */