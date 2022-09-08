 package com.ruby.meshi.client.inventory;
 
 import com.mojang.blaze3d.platform.GlStateManager;
import com.ruby.meshi.client.gui.recipebook.GrindRecipeBookGui;
import com.ruby.meshi.common.inventory.MillstoneContainer;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
 
 public class MillstoneScreen extends ContainerScreen<MillstoneContainer> implements IRecipeShownListener {
    private static final ResourceLocation BOOK_BUTTON = new ResourceLocation("textures/gui/recipe_button.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("meshi", "textures/guis/millstone_gui.png");
    public final RecipeBookGui book = new GrindRecipeBookGui();
    private boolean widthTooNarrow;
 
    public MillstoneScreen(MillstoneContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
       super(screenContainer, inv, titleIn);
 
    }
 
 
    public void init() {
       super.init();
       this.widthTooNarrow = this.width < 379;
       this.book.func_201520_a(this.width, this.height, this.minecraft, this.widthTooNarrow, (RecipeBookContainer)this.field_147002_h);
       this.field_147003_i = this.book.func_193011_a(this.widthTooNarrow, this.width, this.field_146999_f);
       this.children.add(this.book);
       this.func_212928_a(this.book);
 
       this.addButton(new ImageButton(this.field_147003_i + 20, this.height / 2 - 49, 20, 18, 0, 0, 19, BOOK_BUTTON, (button) -> {
          this.book.func_201518_a(this.widthTooNarrow);
          this.book.func_191866_a();
          this.field_147003_i = this.book.func_193011_a(this.widthTooNarrow, this.width, this.field_146999_f);
          ((ImageButton)button).func_191746_c(this.field_147003_i + 20, this.height / 2 - 49);
       }));
    }
 
 
    public void render(int mouseX, int mouseY, float partialTicks) {
       this.renderBackground();
       if (this.book.func_191878_b() && this.widthTooNarrow) {
          this.func_146976_a(partialTicks, mouseX, mouseY);
          this.book.render(mouseX, mouseY, partialTicks);
       } else {
          this.book.render(mouseX, mouseY, partialTicks);
          super.render(mouseX, mouseY, partialTicks);
          this.book.func_191864_a(this.field_147003_i, this.field_147009_r, true, partialTicks);
       }
 
       this.func_191948_b(mouseX, mouseY);
       this.book.func_191876_c(this.field_147003_i, this.field_147009_r, mouseX, mouseY);
       this.func_212932_b(this.book);
    }
 
 
    protected void func_146979_b(int mouseX, int mouseY) {
       this.font.func_211126_b(this.title.func_150254_d(), 8.0F, 6.0F, 4210752);
    }
 
 
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
       GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
       this.minecraft.func_110434_K().func_110577_a(TEXTURE);
       int k = this.field_147003_i;
       int l = this.field_147009_r;
       this.blit(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
 
       int progress = ((MillstoneContainer)this.func_212873_a_()).getProgress();
 
       this.blit(k + 80, l + 28, 176, 16 * ((((MillstoneContainer)this.func_212873_a_()).getGrindtime() - progress) / 10 % 4), 16, 16);
 
 
       this.blit(k + 80, l + 46, 192, 6 * this.getRatio((float)(((MillstoneContainer)this.func_212873_a_()).getGrindtime() - progress), (float)((MillstoneContainer)this.func_212873_a_()).getGrindtime()), 16, 6);
    }
 
 
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
       if (this.book.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
          return true;
       } else {
          return this.widthTooNarrow && this.book.func_191878_b() ? true : super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
       }
    }
 
 
    protected void func_184098_a(Slot slotIn, int slotId, int mouseButton, ClickType type) {
       super.func_184098_a(slotIn, slotId, mouseButton, type);
       this.book.func_191874_a(slotIn);
    }
 
 
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
       return this.book.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_) ? false : super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }
 
 
    protected boolean func_195361_a(double p_195361_1_, double p_195361_3_, int p_195361_5_, int p_195361_6_, int p_195361_7_) {
       boolean lvt_8_1_ = p_195361_1_ < (double)p_195361_5_ || p_195361_3_ < (double)p_195361_6_ || p_195361_1_ >= (double)(p_195361_5_ + this.field_146999_f) || p_195361_3_ >= (double)(p_195361_6_ + this.field_147000_g);
       return this.book.func_195604_a(p_195361_1_, p_195361_3_, this.field_147003_i, this.field_147009_r, this.field_146999_f, this.field_147000_g, p_195361_7_) && lvt_8_1_;
    }
 
 
    public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
       return this.book.charTyped(p_charTyped_1_, p_charTyped_2_) ? true : super.charTyped(p_charTyped_1_, p_charTyped_2_);
    }
 
 
    public void func_192043_J_() {
       this.book.func_193948_e();
    }
 
 
    public RecipeBookGui func_194310_f() {
       return this.book;
    }
 
 
    public void removed() {
       this.book.func_191871_c();
       super.removed();
    }
 
    private int getRatio(float min, float max) {
       return max - min > 0.0F ? Math.round(min / max * 3.0F) % 4 : 0;
    }
 
 
    public void tick() {
       super.tick();
       this.book.func_193957_d();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 46 ms
	
	Decompiled with FernFlower.
*/