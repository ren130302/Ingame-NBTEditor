 package com.ruby.meshi.block;
 
 import java.util.Random;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Plane;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
 public class PaddyField extends FarmlandBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED;
 
    static {
       WATERLOGGED = BlockStateProperties.field_208198_y;
    }
    protected PaddyField(Properties builder) {
       super(builder);
       this.func_180632_j((BlockState)this.func_176223_P().func_206870_a(WATERLOGGED, false));
    }
 
 
    public void func_196267_b(BlockState state, World worldIn, BlockPos pos, Random random) {
       super.func_196267_b(state, worldIn, pos, random);
 
 
       int moisture = (Integer)state.func_177229_b(field_176531_a);
       boolean waterlogged = (Boolean)state.func_177229_b(WATERLOGGED);
       BlockPos upperPos = pos.func_177984_a();
       BlockState upperState = worldIn.func_180495_p(upperPos);
       if (moisture == 7) {
          if (!waterlogged) {
             if (upperState.func_177230_c() instanceof IPlantable) {
                PlantType type = ((IPlantable)upperState.func_177230_c()).getPlantType(worldIn, pos.func_177972_a(Direction.UP));
                if (type == PlantType.Crop && upperState.func_177230_c() != SakuraBlocks.RICE_PLANT && random.nextFloat() < 0.75F) {
 
                   upperState.func_196940_a(worldIn, upperPos, random);
 
 
                }
             }
          } else if (upperState.func_177230_c() == SakuraBlocks.RICE_PLANT && random.nextFloat() < 0.75F) {
 
             upperState.func_196940_a(worldIn, upperPos, random);
 
          }
       }
 
    }
 
 
    private void randomDestory(World worldIn, BlockPos pos, Random rand) {
       if (rand.nextFloat() < 0.01F) {
          worldIn.func_175655_b(pos, false);
       }
    }
 
 
    public IFluidState func_204507_t(BlockState state) {
       return (Boolean)state.func_177229_b(WATERLOGGED) ? Fluids.field_204546_a.func_207204_a(false) : super.func_204507_t(state);
    }
 
 
    public void func_206840_a(Builder<Block, BlockState> builder) {
       super.func_206840_a(builder);
       builder.func_206894_a(new IProperty[]{WATERLOGGED});
    }
 
 
    public void func_220082_b(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
       super.func_220082_b(state, worldIn, pos, oldState, isMoving);
       if (oldState.func_177230_c() != this) {
          worldIn.func_175656_a(pos, this.setHorizontalWater(state, worldIn, pos));
       }
    }
 
 
    public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
       if ((Boolean)stateIn.func_177229_b(WATERLOGGED)) {
          worldIn.func_205219_F_().func_205360_a(currentPos, Fluids.field_204546_a, Fluids.field_204546_a.func_205569_a(worldIn));
       }
 
       return super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
 
 
    public BlockState func_196258_a(BlockItemUseContext context) {
       return this.setHorizontalWater(super.func_196258_a(context), context.func_195991_k(), context.func_195995_a());
    }
 
    public BlockState setHorizontalWater(BlockState state, World worldIn, BlockPos pos) {
       Fluid fluid = worldIn.func_204610_c(pos).func_206886_c();
       if (fluid == Fluids.field_204541_a || fluid == Fluids.field_207212_b) {
          Stream var10000 = Streams.stream(Plane.HORIZONTAL.iterator()).filter((d) -> {
             return worldIn.func_180495_p(pos.func_177972_a(d)).func_177230_c() == this;         }).map((d) -> {            return worldIn.func_204610_c(pos.func_177972_a(d)).func_206886_c();         });         FlowingFluid var10001 = Fluids.field_204546_a;
          Fluids.field_204546_a.getClass();
          fluid = (Fluid)var10000.filter(var10001::equals).findAny().orElse(Fluids.field_204541_a);
       }
 
       return (BlockState)((BlockState)state.func_206870_a(WATERLOGGED, fluid == Fluids.field_204546_a)).func_206870_a(field_176531_a, fluid == Fluids.field_204546_a ? 7 : 0);
    }
 
 
    public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
       if (!(Boolean)worldIn.func_180495_p(pos).func_177229_b(WATERLOGGED)) {
          super.func_180658_a(worldIn, pos, entityIn, fallDistance);
       }
    }
 
 
    public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
       super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
       if (!worldIn.field_72995_K) {
          BlockState fromState = worldIn.func_180495_p(fromPos);
          if (fromState.func_177230_c() == this && this.isHorizontalPos(pos, fromPos) && state.func_177229_b(WATERLOGGED) != fromState.func_177229_b(WATERLOGGED)) {
 
             worldIn.func_175656_a(pos, (BlockState)state.func_206870_a(WATERLOGGED, fromState.func_177229_b(WATERLOGGED)));
          }
       }
 
    }
 
    private boolean isHorizontalPos(BlockPos pos, BlockPos fromPos) {
       return pos.func_177956_o() == fromPos.func_177956_o() ? Streams.stream(Plane.HORIZONTAL.iterator()).anyMatch((d) -> {
 
 
          return pos.func_177972_a(d).equals(fromPos);
       }) : false;
    }
 
 
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
       PlantType type = plantable.getPlantType(world, pos.func_177972_a(facing));
       return type == PlantType.Crop;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 45 ms
	
	Decompiled with FernFlower.
*/