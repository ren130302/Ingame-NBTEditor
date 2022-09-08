 package com.ruby.meshi.client.renderer;
 
 import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.Cardboard;
 import com.ruby.meshi.block.tileentity.CardboardTileEntity;
 import com.ruby.meshi.client.renderer.CardboardRender.1;
 import com.ruby.meshi.client.renderer.CardboardRender.WrapCatModel;
 import com.ruby.meshi.client.renderer.animation.EntityModelAnimation;
 import com.ruby.meshi.client.renderer.animation.TriConsumer;
 import com.ruby.meshi.client.renderer.animation.EntityModelAnimation.RenderPart;
 import java.util.function.Consumer;
 import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
 import net.minecraft.entity.passive.CatEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.Direction.Axis;
 public class CardboardRender extends TileEntityRenderer<CardboardTileEntity> {
    private static final WrapCatModel CAT_RENDER = new WrapCatModel(0.0F);
    private static final float MODEL_SCALE = 0.8F;
 
    public void render(CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
       if (tileEntityIn.hasCatNBT()) {      }
    }
 
          float scale = 0.0625F;
          Direction direction = (Direction)tileEntityIn.func_195044_w().func_177229_b(Cardboard.field_185512_D);
          this.func_147499_a(this.getCatTexture(tileEntityIn));
          tileEntityIn.getAnimations().forEach((a) -> {
             this.renderHead(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
             this.renderFrontLeg(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
             this.renderTail(a, tileEntityIn, x, y, z, partialTicks, 0.0625F, direction);
          });
 
 
    private void renderTail(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
       if (animation.shouldRenderPart(RenderPart.TAIL)) {
          GlStateManager.pushMatrix();
          animation.translatef((i, j, k) -> {
             GlStateManager.translatef((float)x + 0.5F + i + this.getDirOffset(direction, 0.1F, Axis.X), (float)y + 0.11F + j + this.getDirOffset(direction, 0.2F, Axis.Y), (float)z + 0.5F + k + this.getDirOffset(direction, 0.1F, Axis.Z));
 
          }, partialTicks);
          animation.scalef((i, j, k) -> {            GlStateManager.scalef(0.8F + i, 0.8F + j, 0.8F + k);         }, partialTicks);
          animation.rotateZ((a) -> {            GlStateManager.rotatef(a, 0.0F, 0.0F, 1.0F);         }, partialTicks);
          animation.rotateY((a) -> {            GlStateManager.rotatef((float)this.getPartRotate(direction) + a, 0.0F, 1.0F, 0.0F);         }, partialTicks);
          animation.rotateX((a) -> {            GlStateManager.rotatef(a, 1.0F, 0.0F, 0.0F);         }, partialTicks);
          GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
          CAT_RENDER.renderTail(scale);
          GlStateManager.popMatrix();
       }
    }
 
    private void renderHead(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
       if (animation.shouldRenderPart(RenderPart.HEAD)) {
          GlStateManager.pushMatrix();
          animation.translatef((i, j, k) -> {
             GlStateManager.translatef((float)x + 0.5F + i + this.getDirOffset(direction, 0.2F, Axis.X), (float)y + 0.15F + j + this.getDirOffset(direction, 0.2F, Axis.Y), (float)z + 0.5F + k + this.getDirOffset(direction, 0.2F, Axis.Z));
 
          }, partialTicks);
          animation.scalef((i, j, k) -> {            GlStateManager.scalef(0.8F + i, 0.8F + j, 0.8F + k);         }, partialTicks);
          animation.rotateZ((a) -> {            GlStateManager.rotatef(a, 0.0F, 0.0F, 1.0F);         }, partialTicks);
          animation.rotateY((a) -> {            GlStateManager.rotatef((float)this.getPartRotate(direction) + a, 0.0F, 1.0F, 0.0F);         }, partialTicks);
          animation.rotateX((a) -> {            GlStateManager.rotatef(a, 1.0F, 0.0F, 0.0F);         }, partialTicks);
          GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
          CAT_RENDER.renderHead(scale);
          GlStateManager.popMatrix();
       }
    }
 
    private void renderFrontLeg(EntityModelAnimation animation, CardboardTileEntity tileEntityIn, double x, double y, double z, float partialTicks, float scale, Direction direction) {
       if (animation.shouldRenderPart(RenderPart.LEFT_HAND)) {
          GlStateManager.pushMatrix();
          animation.translatef((i, j, k) -> {
             GlStateManager.translatef((float)x + i + 0.5F + this.getDirOffset(direction, -0.15F, Axis.X) + this.getDirOffset(direction, 0.15F, Axis.Z), (float)y + 0.11F + j + this.getDirOffset(direction, 0.2F, Axis.Y), (float)z + 0.5F + k + this.getDirOffset(direction, 0.15F, Axis.X) + this.getDirOffset(direction, -0.15F, Axis.Z));
 
          }, partialTicks);
          animation.scalef((i, j, k) -> {            GlStateManager.scalef(0.8F + i, 0.8F + j, 0.8F + k);         }, partialTicks);
          animation.rotateZ((a) -> {            GlStateManager.rotatef(a, 0.0F, 0.0F, 1.0F);         }, partialTicks);
          animation.rotateY((a) -> {            GlStateManager.rotatef((float)this.getPartRotate(direction) + a, 0.0F, 1.0F, 0.0F);         }, partialTicks);
          animation.rotateX((a) -> {            GlStateManager.rotatef(a, 1.0F, 0.0F, 0.0F);         }, partialTicks);
          GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
          CAT_RENDER.renderFrontLeftLeg(scale);
          GlStateManager.popMatrix();
       }
       if (animation.shouldRenderPart(RenderPart.RIGHT_HAND)) {
          GlStateManager.pushMatrix();
          animation.translatef((i, j, k) -> {
             GlStateManager.translatef((float)x + i + 0.5F + this.getDirOffset(direction, -0.15F, Axis.X) + this.getDirOffset(direction, -0.15F, Axis.Z), (float)y + 0.11F + j + this.getDirOffset(direction, 0.2F, Axis.Y), (float)z + 0.5F + k + this.getDirOffset(direction, -0.15F, Axis.X) + this.getDirOffset(direction, -0.15F, Axis.Z));
 
          }, partialTicks);
          animation.scalef((i, j, k) -> {            GlStateManager.scalef(0.8F + i, 0.8F + j, 0.8F + k);         }, partialTicks);
          animation.rotateZ((a) -> {            GlStateManager.rotatef(a, 0.0F, 0.0F, 1.0F);         }, partialTicks);
          animation.rotateY((a) -> {            GlStateManager.rotatef((float)this.getPartRotate(direction) + a, 0.0F, 1.0F, 0.0F);         }, partialTicks);
          animation.rotateX((a) -> {            GlStateManager.rotatef(a, 1.0F, 0.0F, 0.0F);         }, partialTicks);
          GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
          CAT_RENDER.renderFrontRightLeg(scale);
          GlStateManager.popMatrix();
       }
    }
 
    private float getDirOffset(Direction dir, float value, Axis axis) {
       float offset = 0.0F;
       if (dir.func_176740_k() == axis) {
          offset = value * (float)dir.func_176743_c().func_179524_a();
       }
       return offset;
    }
 
    private int getPartRotate(Direction dir) {
       int headRotate = 0;
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
          headRotate = 90;
          break;
       case 2:
          headRotate = 180;
       case 3:
       default:
          break;
       case 4:
          headRotate = 270;
       }
 
       return headRotate;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    private ResourceLocation getCatTexture(CardboardTileEntity tile) {
       return (ResourceLocation)CatEntity.field_213425_bD.get(tile != null ? tile.getCatType() : 0);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 46 ms
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/