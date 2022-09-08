 package com.ruby.meshi.block;
 
 import java.util.function.Supplier;

import com.ruby.meshi.fluid.HotSpring.SpringColor;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.IProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FlowingFluid;
 @HideCreateTab
 public class HotSpring extends FlowingFluidBlock {
    public static final EnumProperty<SpringColor> COLOR;
 
    static {
       COLOR = com.ruby.meshi.fluid.HotSpring.COLOR;
    }
    public HotSpring(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
       super(supplier, p_i48368_1_);
       this.func_180632_j((BlockState)this.func_176223_P().func_206870_a(COLOR, SpringColor.DEFAULT));
    }
 
 
    protected void func_206840_a(Builder<Block, BlockState> builder) {
       super.func_206840_a(builder);
       builder.func_206894_a(new IProperty[]{COLOR});
    }
 
 
    public IFluidState func_204507_t(BlockState state) {
       return (IFluidState)super.func_204507_t(state).func_206870_a(COLOR, state.func_177229_b(COLOR));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 9 ms
	
	Decompiled with FernFlower.
*/