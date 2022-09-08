 package com.ruby.meshi.client.renderer;
 
 import org.lwjgl.opengl.GL11;

import com.ruby.meshi.block.Hearth;
import com.ruby.meshi.block.Hearth.HearthStateType;
import com.ruby.meshi.block.tileentity.HearthTileEntity;
import com.ruby.meshi.client.renderer.HearthRender.ModelHearth;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.level.block.state.BlockState;
 
 
 
 
 
 
 public class HearthRender extends TileEntityRenderer<HearthTileEntity> {
    private static final ModelHearth model = new ModelHearth();
    private static final ResourceLocation RESOURCE = new ResourceLocation("meshi", "textures/entitys/hearth.png");
 
    public void render(HearthTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
       GL11.glPushMatrix();
       this.func_147499_a(RESOURCE);
       GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
       model.renderWood();
 
       if (tileEntityIn.func_145831_w() != null && tileEntityIn.func_195044_w() != null) {
          BlockState state = tileEntityIn.func_195044_w();
          GL11.glRotatef((float)(90 * ((Direction)state.func_177229_b(Hearth.field_185512_D)).func_176736_b()), 0.0F, 1.0F, 0.0F);
          HearthStateType type = (HearthStateType)state.func_177229_b(Hearth.TYPE);
          if (type == HearthStateType.MEAT) {
             model.renderRods();
             model.renderMeat(MathHelper.func_219799_g(partialTicks, tileEntityIn.now_roll, tileEntityIn.next_roll));
          }
          if (type == HearthStateType.FISH) {
             model.renderFish();
          }
          if (type == HearthStateType.OTHER) {
             model.renderRods();
             model.renderPot();
          }
       }
 
       GL11.glPopMatrix();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/