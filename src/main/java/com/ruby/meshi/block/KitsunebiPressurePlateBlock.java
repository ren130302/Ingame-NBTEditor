 package com.ruby.meshi.block;
 
 import java.util.Random;

import com.ruby.meshi.client.paticle.KitsunebiParticle;

import net.minecraft.block.BlockRenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 
 public class KitsunebiPressurePlateBlock extends PressurePlateBlock {
    protected KitsunebiPressurePlateBlock(Sensitivity sensitiv, Properties prop) {
       super(sensitiv, prop);
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
       if (this.isVisible(Minecraft.func_71410_x().field_71439_g)) {
          Minecraft.func_71410_x().field_71452_i.func_78873_a((new KitsunebiParticle(worldIn, (double)((float)pos.func_177958_n() + 0.5F), (double)((float)pos.func_177956_o() + 0.05F), (double)((float)pos.func_177952_p() + 0.5F), this.func_199767_j())).setHorizontal());
       }
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       return this.isVisible(context.getEntity()) ? super.func_220053_a(state, worldIn, pos, context) : VoxelShapes.func_197880_a();
    }
 
    private boolean isVisible(Entity entity) {
       if (entity != null && entity instanceof PlayerEntity) {
          PlayerEntity player = (PlayerEntity)entity;
          return player.func_184614_ca().func_77973_b() == this.func_199767_j() || player.func_184592_cb().func_77973_b() == this.func_199767_j();
       } else {
          return false;
       }
    }
 
    public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
       return true;
    }
 
 
    public BlockRenderType func_149645_b(BlockState state) {
       return BlockRenderType.INVISIBLE;
    }
 
 
    public boolean func_200124_e(BlockState state) {
       return false;
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public float func_220080_a(BlockState state, IBlockReader worldIn, BlockPos pos) {
       return 1.0F;
    }
 
 
    public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 19 ms
	
	Decompiled with FernFlower.
*/