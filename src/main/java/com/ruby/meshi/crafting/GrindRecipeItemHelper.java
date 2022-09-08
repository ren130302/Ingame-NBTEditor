 package com.ruby.meshi.crafting;
 
 import java.lang.reflect.Field;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
 
 
 
 
 
 public class GrindRecipeItemHelper extends RecipeItemHelper {
    public boolean func_194116_a(IRecipe<?> recipe, IntList packedItemList) {
       return recipe instanceof GrindRecipe ? this.func_194118_a(recipe, packedItemList, ((GrindRecipe)recipe).getRequestCount()) : super.func_194116_a(recipe, packedItemList);
    }
 
 
    public static void hookHelper(Class<?> target, Object instance) {
       Field[] var2 = target.getDeclaredFields();
       int var3 = var2.length;
 
       for(int var4 = 0; var4 < var3; ++var4) {         Field field = var2[var4];
          if (field.getType() == RecipeItemHelper.class) {
             field.setAccessible(true);
             try {
                field.set(instance, new GrindRecipeItemHelper());
             } catch (Exception var7) {
                var7.printStackTrace();
             }         }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 31 ms
	
	Decompiled with FernFlower.
*/