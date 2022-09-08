 package com.ruby.meshi.client.renderer;
 
 import java.util.function.Consumer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;
 
 
 
 public abstract class SimpleItemRender<T extends TileEntity> extends TileEntityRenderer<T> {
    private static final Minecraft mc = Minecraft.func_71410_x();
 
    public void renderItem(ItemStack stack, Consumer<ItemStack> transleter, Consumer<ItemStack> rotater, float scale) {
       if (!stack.func_190926_b()) {
          GlStateManager.pushMatrix();
          transleter.accept(stack);
          rotater.accept(stack);
          GlStateManager.scalef(scale, scale, scale);
          mc.func_175599_af().func_181564_a(stack, TransformType.FIXED);
          GlStateManager.popMatrix();
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/