package com.ren130302.meshi.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Andon extends Block {

    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;
    public static final VoxelShape AABB = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public Andon(Properties prop) {
	super(prop);
	// this.registerDefaultState(this.stateDefinition.any().setValue(BOTTOM,
	// false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
	return AABB;
    }

}
