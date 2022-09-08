 package com.ruby.meshi.common.inventory;
 
 import java.util.List;

import org.antlr.runtime.misc.IntArray;

import com.google.common.collect.Lists;
import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
import com.ruby.meshi.client.gui.recipebook.HiganRecipeBookCategories;
import com.ruby.meshi.common.inventory.slot.NonInsertSlot;
import com.ruby.meshi.crafting.ServerRecipePlacerGrind;
import com.ruby.meshi.init.HiganContainerType;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
 
 
 
 
 public class MillstoneContainer extends RecipeBookContainer<IInventory> {
    private IInventory inventory;
    private IntArray tracker;
    private MillstoneTileEntity tile;
    private World world;
 
    public MillstoneContainer(int windowId, PlayerInventory playerInventory) {
       this(HiganContainerType.MILLSTONE, windowId, playerInventory, new Inventory(3));
    }
 
    public MillstoneContainer(int windowId, PlayerInventory playerInventory, MillstoneTileEntity inventory, IntArray progress) {
       this(HiganContainerType.MILLSTONE, windowId, playerInventory, inventory);
       this.tile = inventory;
    }
 
    public MillstoneContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory) {
       super(type, id);
       this.inventory = inventory;
       inventory.func_174889_b(playerInventory.field_70458_d);
       this.tracker = new IntArray(2);
       this.func_216961_a(this.tracker);
       this.world = playerInventory.field_70458_d.field_70170_p;
       func_216959_a(this.tracker, 2);
 
       this.func_75146_a(new Slot(inventory, 0, 80, 9));
       this.func_75146_a(new NonInsertSlot(inventory, 1, 58, 57));
       this.func_75146_a(new NonInsertSlot(inventory, 2, 102, 57));
 
       int i;
       for(i = 0; i < 3; ++i) {
          for(int j = 0; j < 9; ++j) {
             this.func_75146_a(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
          }
       }
 
       for(i = 0; i < 9; ++i) {
          this.func_75146_a(new Slot(playerInventory, i, 8 + i * 18, 142));
       }
    }
 
 
    public void func_75134_a(PlayerEntity playerIn) {
       super.func_75134_a(playerIn);
       this.inventory.func_174886_c(playerIn);
    }
 
 
    public boolean func_75145_c(PlayerEntity playerIn) {
       return this.inventory.func_70300_a(playerIn);
    }
 
 
    public void func_75142_b() {
       super.func_75142_b();
       if (this.tile != null) {
          this.tracker.func_221477_a(0, this.tile.getProgress());
          this.tracker.func_221477_a(1, this.tile.getGrindTime());
       }
    }
 
    public int getProgress() {
       return this.tracker.func_221476_a(0);
    }
 
    public int getGrindtime() {
       return this.tracker.func_221476_a(1);
    }
 
 
    public ItemStack func_82846_b(PlayerEntity par1EntityPlayer, int par2) {
       ItemStack itemstack = ItemStack.field_190927_a;
       Slot slot = (Slot)this.field_75151_b.get(par2);
 
       if (slot != null && slot.func_75216_d()) {
          ItemStack itemstack1 = slot.func_75211_c();
          itemstack = itemstack1.func_77946_l();
 
          if (par2 != 1 && par2 != 2) {            }         } else {
             if (!this.func_75135_a(itemstack1, 3, 39, true)) {
                return ItemStack.field_190927_a;
             }
 
             slot.func_75220_a(itemstack1, itemstack);
             if (par2 != 0) {
                if (par2 >= 3 && par2 < 30) {
                   if (!this.func_75135_a(itemstack1, 30, 39, false)) {
                      return ItemStack.field_190927_a;
                   }
                } else if (par2 >= 30 && par2 < 39 && !this.func_75135_a(itemstack1, 3, 30, false)) {
                   return ItemStack.field_190927_a;
                }
             } else if (!this.func_75135_a(itemstack1, 3, 39, false)) {
                return ItemStack.field_190927_a;
          }
 
          if (itemstack1.func_190916_E() == 0) {
             slot.func_75215_d(ItemStack.field_190927_a);
          } else {
             slot.func_75218_e();
          }
 
          if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
             return ItemStack.field_190927_a;
          }
 
          slot.func_190901_a(par1EntityPlayer, itemstack1);
       }
 
       return itemstack;
    }
 
 
    public void func_201771_a(RecipeItemHelper helper) {
       if (this.inventory instanceof IRecipeHelperPopulator) {
          ((IRecipeHelperPopulator)this.inventory).func_194018_a(helper);
       }
    }
 
 
    public void func_201768_e() {
       this.inventory.func_174888_l();
    }
 
 
    public void func_217056_a(boolean p_217056_1_, IRecipe<?> recipe, ServerPlayerEntity player) {
       (new ServerRecipePlacerGrind(this, recipe)).func_194327_a(player, recipe, p_217056_1_);
    }
 
 
    public boolean func_201769_a(IRecipe<? super IInventory> recipeIn) {
       return recipeIn.func_77569_a(this.inventory, this.world);
    }
 
 
    public int func_201767_f() {
       return 1;
    }
 
 
    public int func_201770_g() {
       return 1;
    }
 
 
    public int func_201772_h() {
       return 1;
    }
 
 
    public int func_203721_h() {
       return 3;
    }
 
 
    public List<RecipeBookCategories> getRecipeBookCategories() {
       return Lists.newArrayList(new RecipeBookCategories[]{HiganRecipeBookCategories.GRIND});
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 33 ms
	
	Decompiled with FernFlower.
*/