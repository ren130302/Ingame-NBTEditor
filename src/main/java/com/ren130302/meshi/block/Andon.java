package com.ren130302.meshi.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Andon extends HorizontalDirectionalBlock {

    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public Andon(Properties prop) {
	super(prop);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
	return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
	builder.add(FACING).add(BOTTOM);
    }
}
