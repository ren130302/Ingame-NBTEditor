package com.ren130302.meshi.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kitsunebi extends Block {
    public static final VoxelShape SHAPE = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);

    public Kitsunebi(Block.Properties properties) {
	super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext context) {
	return context.isHoldingItem(this.asItem()) ? SHAPE : Shapes.empty();
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
//	(Minecraft.getInstance()).particleEngine.add(new KitsunebiParticle(worldIn, (pos.getX() + 0.5F),
//	    (pos.getY() + 0.5F), (pos.getZ() + 0.5F), this.asItem()));
    }
}
