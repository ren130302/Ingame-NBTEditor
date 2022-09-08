 package com.ruby.meshi.client.gui.recipebook;
 
 import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruby.meshi.block.tileentity.HearthTileEntity;
import com.ruby.meshi.common.inventory.HearthContainer;
import com.ruby.meshi.crafting.GrindRecipe;
import com.ruby.meshi.crafting.HearthRecipe;
import com.ruby.meshi.crafting.HearthShapelessRecipe;
import com.ruby.meshi.item.HiganItems;
import com.ruby.meshi.util.EnumHelper;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.item.ItemStack;
 public class HiganRecipeBookCategories {
    public static final RecipeBookCategories GRIND;
    public static final RecipeBookCategories HEARTH;
    public static final RecipeBookCategories HEARTH_SEARCH;
 
    static {
       GRIND = create("grind", new ItemStack(HiganItems.RAW_RICE));
       HEARTH = create("hearth", new ItemStack(HiganItems.STRAW));
       HEARTH_SEARCH = create("hearth_search", new ItemStack(Items.field_151111_aL));
    }
    private static RecipeBookCategories create(String name, ItemStack... icons) {
       return (RecipeBookCategories)EnumHelper.addEnum(RecipeBookCategories.class, name, RecipeBookCategories.values().length, new Class[]{ItemStack[].class}, new Object[]{icons});
    }
 
 
 
 
 
 
 
 
 
 
    public static boolean isHiganRecipe(IRecipe<?> recipe) {
       return recipe instanceof GrindRecipe || recipe instanceof HearthRecipe || recipe instanceof HearthShapelessRecipe;
    }
 
 
 
 
 
 
 
    public static RecipeBookCategories getCategorie(IRecipe<?> recipe) {
       return recipe instanceof GrindRecipe ? GRIND : HEARTH;
    }
 
 
 
 
 
 
 
 
 
 
 
 
    public static void postRebuildTable(Map<RecipeBookCategories, List<RecipeList>> map, List<RecipeList> list) {
       list.removeIf((rl) -> {         return rl.func_192711_b().stream().anyMatch((r) -> {            return HearthTileEntity.hasEnchantRecipe(r.func_199560_c());         });      });
       ((List)map.get(RecipeBookCategories.SEARCH)).removeIf((rl) -> {         return rl.func_192711_b().stream().anyMatch(HiganRecipeBookCategories::isHiganRecipe);      });
       map.put(HEARTH_SEARCH, ((List)HearthContainer.CATEGORIES.get()).stream().filter((c) -> {
          return c != HEARTH_SEARCH;      }).flatMap((c) -> {
          return ((List)map.get(c)).stream();
       }).collect(Collectors.toList()));      Comparator<RecipeList> sorter = (a, b) -> {         return getResultName(a).compareTo(getResultName(b));
       };
 
       ((List)map.get(HEARTH)).sort(sorter);
       ((List)map.get(HEARTH_SEARCH)).sort(sorter);
       ((List)map.get(GRIND)).sort(sorter);
 
    }
 
    private static String getResultName(RecipeList list) {
       return ((ItemStack)list.func_192711_b().stream().findFirst().map((r) -> {
          return r.func_77571_b();
       }).orElse(ItemStack.field_190927_a)).func_200301_q().getString();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 75 ms
	
	Decompiled with FernFlower.
*/