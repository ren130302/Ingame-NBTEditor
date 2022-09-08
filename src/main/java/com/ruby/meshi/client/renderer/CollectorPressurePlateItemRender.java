 package com.ruby.meshi.client.renderer;
 
 import com.mojang.blaze3d.platform.GlStateManager;
 import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import java.util.function.Consumer;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.texture.AtlasTexture;
 import net.minecraft.item.ItemStack;import net.minecraft.item.Items;
 public class CollectorPressurePlateItemRender extends SimpleItemRender<CollectorPressurePlateTileEntity> {
    private final Minecraft mc = Minecraft.func_71410_x();
    private static final ItemStack BATTIER;
 
    static {
       BATTIER = new ItemStack(Items.field_221803_eL);
    }
 
 
    public void render(CollectorPressurePlateTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
       this.mc.func_175598_ae().field_78724_e.func_110577_a(AtlasTexture.field_110575_b);
 
 
       ItemStack stack = entity.func_70301_a(0);
       if (stack.func_190926_b() && entity.isForce()) {
          stack = BATTIER;
       }
 
       GlStateManager.enableLighting();      this.renderItem(stack, (s) -> {
       }, 0.5F);
          GlStateManager.translated(x + 0.5D, y + (this.mc.func_175599_af().func_175050_a(s) ? 0.05D : 0.1D), z + 0.5D);      }, (s) -> {
          GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
 
       GlStateManager.enableLighting();
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 9 ms
	
	Decompiled with FernFlower.
*/