 package com.ruby.meshi.block;
 
 import net.minecraft.core.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
 
 public class PaneBlock extends net.minecraft.block.PaneBlock {
    public PaneBlock(Properties builder) {
       super(builder);
    }
 
 
    public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       return this.field_196274_w ? super.func_220071_b(state, worldIn, pos, context) : VoxelShapes.func_197880_a();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/