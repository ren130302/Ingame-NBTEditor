 package com.ruby.meshi.client.renderer;
 
 import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.tileentity.WallShelfTileEntity;
 import com.ruby.meshi.client.renderer.WallShelfItemRender.1;
 import java.util.function.Consumer;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.texture.AtlasTexture;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.util.Direction;
 
 
 
 public class WallShelfItemRender extends SimpleItemRender<WallShelfTileEntity> {
    private final Minecraft mc = Minecraft.func_71410_x();
 
    public void render(WallShelfTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
       this.mc.func_175598_ae().field_78724_e.func_110577_a(AtlasTexture.field_110575_b);
 
       GlStateManager.enableLighting();
       if (entity.isDouble()) {
          this.renderDouble(entity, x, y, z, partialTicks, destroyStage);
       }
       GlStateManager.enableLighting();
 
    }
 
    private void renderDouble(WallShelfTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
       Direction dir = (Direction)entity.func_195044_w().func_177229_b(BlockStateProperties.field_208157_J);
 
 
       int rotateYaw = this.getRotation(dir);      double renderX;      double renderZ;
       if (entity.hasItem((byte)0)) {
          renderX = this.getRightOffsetX(dir) + x;
          renderZ = this.getRightOffsetZ(dir) + z;
          this.renderItem(entity.func_70301_a(0), renderX, y, renderZ, rotateYaw);
       }
       if (entity.hasItem((byte)1)) {
          renderX = this.getLeftOffsetX(dir) + x;
          renderZ = this.getLeftOffsetZ(dir) + z;
          this.renderItem(entity.func_70301_a(1), renderX, y, renderZ, rotateYaw);
       }
 
    }
 
 
    private void renderSingle(WallShelfTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
    }
 
    private void renderItem(ItemStack stack, double x, double y, double z, int rotateYaw) {      this.renderItem(stack, (s) -> {
       }, 0.5F);
          GlStateManager.translated(x + 0.5D, y + (this.mc.func_175599_af().func_175050_a(s) ? 0.5D : 0.6D), z + 0.5D);      }, (s) -> {
          GlStateManager.rotatef((float)(180 - rotateYaw), 0.0F, 1.0F, 0.0F);
 
    }
 
 
 
    public int getRotation(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
       case 2:
          return 90;
       default:
          return 0;
       }
    }
 
    public double getRightOffsetX(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
       case 3:
          return 0.25D;
       case 2:
       case 4:
          return -0.25D;
       default:
          return 0.0D;
       }
    }
 
    public double getRightOffsetZ(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
       case 4:
          return 0.25D;
       case 2:
       case 3:
          return -0.25D;
       default:
          return 0.0D;
       }
    }
 
    public double getLeftOffsetX(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
       case 4:
          return 0.25D;
       case 2:
       case 3:
          return -0.25D;
       default:
          return 0.0D;
       }
    }
 
    public double getLeftOffsetZ(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 2:
       case 4:
          return 0.25D;
       case 1:
       case 3:
          return -0.25D;
       default:
          return 0.0D;
       }
    }
 
    public double getSingleOffsetX(Direction dir) {
       return 0.0D;
    }
 
    public double geSingleOffsetZ(Direction dir) {
       return 0.0D;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 18 ms
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/