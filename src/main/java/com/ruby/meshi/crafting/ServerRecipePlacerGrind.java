 package com.ruby.meshi.crafting;
 
 import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ServerRecipePlacer;
import net.minecraft.world.item.ItemStack;
 
 
 public class ServerRecipePlacerGrind<C extends IInventory> extends ServerRecipePlacer<C> {
    private IRecipe<?> recipe;
 
    public ServerRecipePlacerGrind(RecipeBookContainer<C> p_i50752_1_, IRecipe<?> recipe) {
       super(p_i50752_1_);
 
       GrindRecipeItemHelper.hookHelper(ServerRecipePlacer.class, this);
       this.recipe = recipe;
    }
 
 
 
    public int func_201509_a(boolean placeAll, int maxPossible, boolean recipeMatches) {
       int i = 1;
       if (this.recipe instanceof GrindRecipe) {
          i = ((GrindRecipe)this.recipe).getRequestCount();
       }
       if (placeAll) {
          i = maxPossible;
       } else if (recipeMatches) {
          i = 64;
          for(int j = 0; j < this.field_201515_d.func_201770_g() * this.field_201515_d.func_201772_h() + 1; ++j) {
             if (j != this.field_201515_d.func_201767_f()) {
                ItemStack itemstack = this.field_201515_d.func_75139_a(j).func_75211_c();
                if (!itemstack.func_190926_b() && i > itemstack.func_190916_E()) {
                   i = itemstack.func_190916_E();
                }            }
          }
 
          if (i < 64) {
             ++i;
          }      }
 
       return i;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/