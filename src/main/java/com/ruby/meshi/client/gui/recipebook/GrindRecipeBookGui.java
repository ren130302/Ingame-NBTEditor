 package com.ruby.meshi.client.gui.recipebook;
 
 import java.util.List;

import com.ruby.meshi.client.gui.recipebook.GrindRecipeBookGui.WrapIngredient;
import com.ruby.meshi.crafting.GrindRecipe;
import com.ruby.meshi.crafting.GrindRecipeItemHelper;

import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
 
 
 
 
 
 
 
 
 
 
 public class GrindRecipeBookGui extends RecipeBookGui {
    public GrindRecipeBookGui() {
       GrindRecipeItemHelper.hookHelper(RecipeBookGui.class, this);
    }
 
 
 
    public void func_193951_a(IRecipe<?> recipe, List<Slot> slots) {
       if (recipe instanceof GrindRecipe) {
          this.field_191915_z.func_192685_a(recipe);
          this.field_191915_z.func_194187_a(new WrapIngredient(((GrindRecipe)recipe).ingredient, ((GrindRecipe)recipe).getRequestCount()), ((Slot)slots.get(0)).field_75223_e, ((Slot)slots.get(0)).field_75221_f);
          ItemStack outPut = recipe.func_77571_b();
          this.field_191915_z.func_194187_a(new WrapIngredient(Ingredient.func_193369_a(new ItemStack[]{outPut}), outPut.func_190916_E()), ((Slot)slots.get(1)).field_75223_e, ((Slot)slots.get(1)).field_75221_f);
          ItemStack bonus = ((GrindRecipe)recipe).getBonus();
          if (!bonus.func_190926_b()) {
             this.field_191915_z.func_194187_a(new WrapIngredient(Ingredient.func_193369_a(new ItemStack[]{bonus}), bonus.func_190916_E()), ((Slot)slots.get(2)).field_75223_e, ((Slot)slots.get(2)).field_75221_f);
          }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 256 ms
	
	Decompiled with FernFlower.
*/