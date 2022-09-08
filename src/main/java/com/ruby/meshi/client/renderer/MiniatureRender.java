 package com.ruby.meshi.client.renderer;
 
 import java.nio.ByteBuffer;
import java.util.Random;
import java.util.function.BiConsumer;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
import com.ruby.meshi.client.renderer.MiniatureRender.BB;
import com.ruby.meshi.client.renderer.MiniatureRender.BakeWraper;

import net.minecraft.block.BlockRenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MiniatureRender extends TileEntityRenderer<MiniatureTileEntity> {
    protected static BlockRendererDispatcher blockRenderer;
    Random rand = new Random();
    BB wrapBB = new BB(this);
 
    public void render(MiniatureTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
       if (blockRenderer == null) {
          blockRenderer = Minecraft.func_71410_x().func_175602_ab();
       }
       Tessellator tessellator = Tessellator.func_178181_a();
       this.wrapBB.buffer = tessellator.func_178180_c();
       this.wrapBB.offsetSize = 1.0F / (float)te.getSize();
       this.func_147499_a(AtlasTexture.field_110575_b);
       RenderHelper.func_74518_a();
       if (Minecraft.func_71379_u()) {
          GlStateManager.shadeModel(7425);
       } else {
          GlStateManager.shadeModel(7424);
       }
 
       GlStateManager.enableCull();
 
       GL11.glPushMatrix();
       GlStateManager.disableBlend();
       this.wrapBB.func_181668_a(7, DefaultVertexFormats.field_176600_a);
 
       if (te.renderSolidCache != null) {
          te.renderSolidCache.rewind();
          this.wrapBB.func_178993_a(te.renderSolidState);
          this.wrapBB.putBulkData(te.renderSolidCache);
       } else {
          this.renderMiniatureBlocks(te, this.wrapBB, true, (b, s) -> {
             te.renderSolidCache = b;
             te.renderSolidState = s;
          });
       }
 
       GL11.glTranslated(x, y, z);
 
       this.wrapBB.func_178969_c(0.0D, 0.0D, 0.0D);
       tessellator.func_78381_a();
       GL11.glPopMatrix();
 
 
       GL11.glPushMatrix();
       GlStateManager.enableBlend();
       GlStateManager.blendFunc(770, 771);
       this.wrapBB.func_181668_a(7, DefaultVertexFormats.field_176600_a);
 
       if (te.renderCache != null) {
          te.renderCache.rewind();
          this.wrapBB.func_178993_a(te.renderState);
          this.wrapBB.putBulkData(te.renderCache);
       } else {
          this.renderMiniatureBlocks(te, this.wrapBB, false, (b, s) -> {
             te.renderCache = b;
             te.renderState = s;
          });
       }
 
       GL11.glTranslated(x, y, z);
 
 
       this.wrapBB.func_181674_a((float)(-x), (float)(-y), (float)(-z));
       this.wrapBB.func_178969_c(0.0D, 0.0D, 0.0D);
       tessellator.func_78381_a();
       GL11.glPopMatrix();
 
       RenderHelper.func_74519_b();
    }
 
 
    public void renderMiniatureBlocks(MiniatureTileEntity te, BufferBuilder buffer, boolean isSolid, BiConsumer<ByteBuffer, State> container) {
       BlockPos worldPos = te.func_174877_v();
       IEnviromentBlockReader innerWorld = te.getInnerWorld();
       MutableBlockPos mpos = new MutableBlockPos();
 
 
       for(int i = 0; i < te.getSize(); ++i) {
          for(int j = 0; j < te.getSize(); ++j) {
             for(int k = 0; k < te.getSize(); ++k) {
                mpos.func_181079_c(i, j, k);
                BlockState innerState = te.getInnerState(i, j, k);
                if (innerState != MiniatureTileEntity.EMPTY) {
                   this.wrapBB.innerPos = mpos;
                   IBakedModel model = blockRenderer.func_175023_a().func_178125_b(innerState);
                   if (te.isNoTexResize()) {
                      model = new BakeWraper(this, (IBakedModel)model, mpos.func_185334_h(), te.getSize());
                   }
                   float offsetSize = 1.0F / (float)te.getSize();
                   this.wrapBB.func_178969_c((double)((float)mpos.func_177958_n() * offsetSize), (double)((float)mpos.func_177956_o() * offsetSize), (double)((float)mpos.func_177952_p() * offsetSize));
                   if (isSolid) {
                      if (innerState.func_185901_i() != BlockRenderType.INVISIBLE && this.hasPriority(innerState.func_177230_c().func_180664_k())) {
                         blockRenderer.func_175019_b().renderModel(innerWorld, (IBakedModel)model, innerState, mpos, this.wrapBB, true, this.rand, 42L, ((IBakedModel)model).getModelData(innerWorld, worldPos, innerState, (IModelData)null));
                      }
                   } else {
                      if (innerState.func_185901_i() != BlockRenderType.INVISIBLE && !this.hasPriority(innerState.func_177230_c().func_180664_k())) {
                         blockRenderer.func_175019_b().renderModel(innerWorld, (IBakedModel)model, innerState, mpos, this.wrapBB, true, this.rand, 42L, ((IBakedModel)model).getModelData(innerWorld, worldPos, innerState, (IModelData)null));
                      }
                      if (!innerState.func_204520_s().func_206888_e()) {
                         blockRenderer.func_215331_a(mpos, innerWorld, this.wrapBB, innerState.func_204520_s());
                      }                  }
                }
             }
          }
       }
 
       this.wrapBB.func_178966_f().flip();
       container.accept(ByteBuffer.allocate(this.wrapBB.func_178966_f().limit()).put(this.wrapBB.func_178966_f()), this.wrapBB.func_181672_a());
       this.wrapBB.func_178966_f().rewind();
 
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    boolean hasPriority(BlockRenderLayer layer) {
       return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 80 ms
	
	Decompiled with FernFlower.
*/