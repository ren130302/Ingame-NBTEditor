 package com.ruby.meshi.client.renderer;
 
 import org.lwjgl.opengl.GL11;

import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
import com.ruby.meshi.client.renderer.MillstoneRender.ModelMillStone;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.resources.ResourceLocation;
 
 
 
 
 
 
 public class MillstoneRender extends TileEntityRenderer<MillstoneTileEntity> {
    private static final ModelMillStone model = new ModelMillStone();
    private static final ResourceLocation RESOURCE = new ResourceLocation("meshi", "textures/entitys/millstone.png");
 
    public void render(MillstoneTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
       GL11.glPushMatrix();
       this.func_147499_a(RESOURCE);
       GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
       model.render(entity, partialTicks, 0.0625F);
       GL11.glPopMatrix();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 7 ms
	
	Decompiled with FernFlower.
*/