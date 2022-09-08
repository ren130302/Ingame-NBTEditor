 package com.ruby.meshi.block;
 
 import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
 public class PlayerFachingSlab extends SlabBlock {
    public static final DirectionProperty HORIZONTAL_FACING;
 
    static {
       HORIZONTAL_FACING = BlockStateProperties.field_208157_J;
    }
    public PlayerFachingSlab(Properties properties) {
       super(properties);
       this.func_180632_j((BlockState)this.func_176223_P().func_206870_a(HORIZONTAL_FACING, Direction.NORTH));
    }
 
 
 
    public BlockState func_196258_a(BlockItemUseContext context) {
       return (BlockState)super.func_196258_a(context).func_206870_a(HORIZONTAL_FACING, context.func_195992_f().func_176734_d());
    }
 
 
    protected void func_206840_a(Builder<Block, BlockState> builder) {
       super.func_206840_a(builder);
       builder.func_206894_a(new IProperty[]{HORIZONTAL_FACING});
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 5 ms
	
	Decompiled with FernFlower.
*/