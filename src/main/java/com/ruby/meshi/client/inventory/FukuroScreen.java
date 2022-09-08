 package com.ruby.meshi.client.inventory;
 
 import com.mojang.blaze3d.platform.GlStateManager;
import com.ruby.meshi.common.inventory.FukuroContainer;

import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
 
 
 
 
 public class FukuroScreen extends ContainerScreen<FukuroContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("meshi", "textures/guis/fukuro_gui.png");
 
    public FukuroScreen(FukuroContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
       super(screenContainer, inv, titleIn);
    }
 
 
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
       GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.minecraft.func_110434_K().func_110577_a(TEXTURE);
       int k = this.field_147003_i;
       int l = this.field_147009_r;
       this.blit(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
 
 
    protected void func_146979_b(int mouseX, int mouseY) {
       this.font.func_211126_b(this.title.func_150254_d(), 8.0F, 6.0F, 4210752);
       this.font.func_211126_b(this.field_213127_e.func_145748_c_().func_150254_d(), 8.0F, (float)(this.field_147000_g - 96 + 2), 4210752);
    }
 
 
    public void render(int mouseX, int mouseY, float partialTicks) {
       this.renderBackground();
       super.render(mouseX, mouseY, partialTicks);
       this.func_191948_b(mouseX, mouseY);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/