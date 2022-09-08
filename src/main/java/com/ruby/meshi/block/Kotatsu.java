 package com.ruby.meshi.block;
 
 import net.minecraft.block.FourWayBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.level.block.state.BlockState;
 public class Kotatsu extends FourWayBlock {
    protected Kotatsu(Properties properties) {
       super(5.0F, 5.0F, 10.0F, 10.0F, 10.0F, properties);
       this.func_180632_j((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a(field_196409_a, false)).func_206870_a(field_196411_b, false)).func_206870_a(field_196413_c, false)).func_206870_a(field_196414_y, false)).func_206870_a(field_204514_u, false));
 
    }
 
 
    public BlockState func_196258_a(BlockItemUseContext context) {
       IBlockReader iblockreader = context.func_195991_k();
       BlockPos blockpos = context.func_195995_a();
       IFluidState ifluidstate = context.func_195991_k().func_204610_c(context.func_195995_a());
       BlockPos blockpos1 = blockpos.func_177978_c();
       BlockPos blockpos2 = blockpos.func_177968_d();
       BlockPos blockpos3 = blockpos.func_177976_e();
       BlockPos blockpos4 = blockpos.func_177974_f();
       BlockState blockstate = iblockreader.func_180495_p(blockpos1);
       BlockState blockstate1 = iblockreader.func_180495_p(blockpos2);
       BlockState blockstate2 = iblockreader.func_180495_p(blockpos3);
       BlockState blockstate3 = iblockreader.func_180495_p(blockpos4);
       return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.func_176223_P().func_206870_a(field_196409_a, this.canAttachTo(blockstate, blockstate.func_224755_d(iblockreader, blockpos1, Direction.SOUTH)))).func_206870_a(field_196413_c, this.canAttachTo(blockstate1, blockstate1.func_224755_d(iblockreader, blockpos2, Direction.NORTH)))).func_206870_a(field_196414_y, this.canAttachTo(blockstate2, blockstate2.func_224755_d(iblockreader, blockpos3, Direction.EAST)))).func_206870_a(field_196411_b, this.canAttachTo(blockstate3, blockstate3.func_224755_d(iblockreader, blockpos4, Direction.WEST)))).func_206870_a(field_204514_u, ifluidstate.func_206886_c() == Fluids.field_204546_a);
    }
 
 
    public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
       return facing.func_176740_k().func_176722_c() ? (BlockState)stateIn.func_206870_a((IProperty)field_196415_z.get(facing), this.canAttachTo(facingState, facingState.func_224755_d(worldIn, facingPos, facing.func_176734_d()))) : super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
 
    public boolean canAttachTo(BlockState p_220112_1_, boolean p_220112_2_) {
       Block block = p_220112_1_.func_177230_c();
       return !func_220073_a(block) && p_220112_2_ || block instanceof Kotatsu;
    }
 
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT_MIPPED;
    }
 
 
    protected void func_206840_a(Builder<Block, BlockState> builder) {
       super.func_206840_a(builder);
       builder.func_206894_a(new IProperty[]{field_196409_a, field_196411_b, field_196414_y, field_196413_c, field_204514_u});
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 20 ms
	
	Decompiled with FernFlower.
*/