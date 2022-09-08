 package com.ruby.meshi.block;
 
 import net.minecraft.block.BlockRenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
 public class Huton extends BedBlock {
    public static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
 
    public Huton(Properties properties) {
       super(DyeColor.WHITE, properties);
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       return SHAPE;
    }
 
 
    public boolean hasTileEntity(BlockState state) {
       return false;
    }
 
 
    public BlockRenderType func_149645_b(BlockState state) {
       return BlockRenderType.MODEL;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 11 ms
	
	Decompiled with FernFlower.
*/