 package com.ruby.meshi.crafting;
 
 import com.google.common.collect.Lists;
 import com.ruby.meshi.crafting.HearthShapelessRecipe.1;
 import it.unimi.dsi.fastutil.ints.IntList;
 import java.util.List;
 import java.util.function.Predicate;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.registry.Registry;
 import net.minecraft.world.World;
 import net.minecraftforge.common.util.RecipeMatcher;
 
 public class HearthShapelessRecipe implements IRecipe<IInventory>, NonLockRecipe, CookingTimerRecipe {
    public static final IRecipeType<HearthShapelessRecipe> TYPE;
    private final ResourceLocation id;
    private final String group;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;
    private final int cookingTime;
 
    static {
       ResourceLocation key = HiganRecipeSerializer.HEARTH_SHAPELESS.getRegistryName();
       TYPE = (IRecipeType)Registry.func_218322_a(Registry.field_218367_H, key, new 1(key));
 
 
 
 
 
    }
 
 
 
 
 
 
 
 
    public HearthShapelessRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, int time) {
       this.id = idIn;
       this.group = groupIn;
       this.recipeOutput = recipeOutputIn;
       this.recipeItems = recipeItemsIn;
       this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
       this.cookingTime = time;
    }
 
 
    public ResourceLocation func_199560_c() {
       return this.id;
    }
 
 
    public IRecipeSerializer<?> func_199559_b() {
       return HiganRecipeSerializer.HEARTH_SHAPELESS;
    }
 
 
    public String func_193358_e() {
       return this.group;
    }
 
 
    public ItemStack func_77571_b() {
       return this.recipeOutput;
    }
 
 
    public NonNullList<Ingredient> func_192400_c() {
       return this.recipeItems;
    }
 
 
    public boolean func_77569_a(IInventory inv, World worldIn) {
       RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
       List<ItemStack> inputs = Lists.newArrayList();
       int i = 0;
 
       for(int j = 0; j < inv.func_70302_i_(); ++j) {
          ItemStack itemstack = inv.func_70301_a(j);
          if (!itemstack.func_190926_b()) {
             ++i;
             if (this.isSimple) {
                recipeitemhelper.func_221264_a(itemstack, 1);
             } else {
                inputs.add(itemstack);
             }         }      }      boolean var10000;      label43: {         if (i == this.recipeItems.size()) {            if (this.isSimple) {               if (recipeitemhelper.func_194116_a(this, (IntList)null)) {                  break label43;               }            } else if (RecipeMatcher.findMatches(inputs, this.recipeItems) != null) {               break label43;            }         }         var10000 = false;
          return var10000;
       }
 
       var10000 = true;
       return var10000;
    }
 
    public ItemStack func_77572_b(IInventory inv) {
       return this.recipeOutput.func_77946_l();
    }
 
 
    public boolean func_194133_a(int width, int height) {
       return width * height >= this.recipeItems.size();
    }
 
 
    public int getCookTime() {
       return this.cookingTime;
    }
 
 
    public IRecipeType<?> func_222127_g() {
       return TYPE;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 22 ms
	
	Decompiled with FernFlower.
*/