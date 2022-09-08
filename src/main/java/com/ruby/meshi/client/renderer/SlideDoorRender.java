 package com.ruby.meshi.client.renderer;
 
 import java.util.Random;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.ruby.meshi.block.SlideDoor;
import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataManager;
 
 
 
 
 public class SlideDoorRender extends TileEntityRendererFast<SlideDoorTileEntity> {
    protected static BlockRendererDispatcher blockRenderer;
 
    public void renderTileEntityFast(SlideDoorTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer) {
       BlockState state = te.func_195044_w();
       BlockPos pos = te.func_174877_v();
       if (blockRenderer == null) {
          blockRenderer = Minecraft.func_71410_x().func_175602_ab();
       }
       IEnviromentBlockReader world = MinecraftForgeClient.getRegionRenderCache(te.func_145831_w(), pos);
       IBakedModel model = blockRenderer.func_175023_a().func_178125_b(state);
       IModelData data = model.getModelData(world, pos, state, ModelDataManager.getModelData(te.func_145831_w(), pos));
       Direction facing = ((Direction)state.func_177229_b(SlideDoor.field_176520_a)).func_176735_f();
       if (state.func_177229_b(SlideDoor.field_176521_M) == DoorHingeSide.RIGHT) {
          facing = facing.func_176734_d();
       }
       BlockPos renderPos = pos.func_177971_a(facing.func_176730_m());
       double renderX = x - (double)renderPos.func_177958_n() + (double)MathHelper.func_219799_g(partialTicks, te.nowPosX, te.posX);
       double renderY = y - (double)renderPos.func_177956_o();
       double renderZ = z - (double)renderPos.func_177952_p() + (double)MathHelper.func_219799_g(partialTicks, te.nowPosZ, te.posZ);
       Random rand = new Random();
       buffer.func_178969_c(renderX, renderY, renderZ);
       blockRenderer.func_175019_b().renderModel(world, model, state, renderPos, buffer, false, rand, 42L, data);
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/