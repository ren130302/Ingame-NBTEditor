 package com.ruby.meshi.client.inventory;
 
 import com.mojang.blaze3d.platform.GlStateManager;
import com.ruby.meshi.common.inventory.CollectorPressurePlateContainer;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
 
 
 
 
 public class CollectorPressurePlateScreen extends ContainerScreen<CollectorPressurePlateContainer> implements IHasContainer<CollectorPressurePlateContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("meshi", "textures/guis/collerctor_plate_gui.png");
 
    public CollectorPressurePlateScreen(CollectorPressurePlateContainer container, PlayerInventory inventory, ITextComponent title) {
       super(container, inventory, title);
    }
 
 
    public void render(int mouseX, int mouseY, float partialTicks) {
       this.renderBackground();
       super.render(mouseX, mouseY, partialTicks);
       this.func_191948_b(mouseX, mouseY);
    }
 
 
    protected void func_146979_b(int mouseX, int mouseY) {
       this.font.func_211126_b(this.title.func_150254_d(), 8.0F, 6.0F, 4210752);
       this.font.func_211126_b("Filter", 42.0F, 22.0F, 4210752);
       this.font.func_211126_b(this.field_213127_e.func_145748_c_().func_150254_d(), 8.0F, (float)(this.field_147000_g - 96 + 2), 4210752);
    }
 
 
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
       GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.minecraft.func_110434_K().func_110577_a(TEXTURE);
       int k = (this.width - this.field_146999_f) / 2;
       int l = (this.height - this.field_147000_g) / 2;
       this.blit(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 17 ms
	
	Decompiled with FernFlower.
*/