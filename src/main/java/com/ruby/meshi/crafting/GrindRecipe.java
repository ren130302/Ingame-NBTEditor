 package com.ruby.meshi.crafting;
 
 import com.ruby.meshi.crafting.GrindRecipe.1;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeSerializer;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.Ingredient;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.registry.Registry;import net.minecraft.world.World;
 public class GrindRecipe implements IRecipe<IInventory>, NonLockRecipe {
    public static final IRecipeType<GrindRecipe> TYPE;
    public final ResourceLocation id;
    public final String group;
    public final Ingredient ingredient;
    public final int reqcount;
    public final ItemStack result;
    public final ItemStack bonus;
    public final float bonuswegiht;
    public final int grindingTime;
 
    static {
       ResourceLocation key = HiganRecipeSerializer.GRIND.getRegistryName();
       TYPE = (IRecipeType)Registry.func_218322_a(Registry.field_218367_H, key, new 1(key));
 
 
 
 
 
    }
 
 
 
 
 
 
 
 
 
 
    public GrindRecipe(ResourceLocation recipeId, String group, Ingredient ingredient, int reqcount, ItemStack result, ItemStack bonus, float weight, int time) {
       this.id = recipeId;
       this.group = group;
       this.ingredient = ingredient;
       this.reqcount = reqcount;
       this.result = result;
       this.bonus = bonus;
       this.bonuswegiht = weight;
       this.grindingTime = time;
    }
 
 
    public boolean func_77569_a(IInventory inv, World worldIn) {
       return this.ingredient.test(inv.func_70301_a(0));
    }
 
 
    public ItemStack func_77572_b(IInventory inv) {
       return this.result.func_77946_l();
    }
 
 
    public boolean func_194133_a(int width, int height) {
       return width == 1 && height == 1;
    }
 
 
    public ItemStack func_77571_b() {
       return this.result.func_77946_l();
    }
 
 
    public ResourceLocation func_199560_c() {
       return this.id;
    }
 
 
    public IRecipeSerializer<?> func_199559_b() {
       return HiganRecipeSerializer.GRIND;
    }
 
 
    public IRecipeType<?> func_222127_g() {
       return TYPE;
    }
 
 
    public String func_193358_e() {
       return this.group;
    }
 
 
    public NonNullList<Ingredient> func_192400_c() {
       return NonNullList.func_193580_a(Ingredient.field_193370_a, new Ingredient[]{this.ingredient});
    }
 
    public int getRequestCount() {
       return this.reqcount;
    }
 
    public int getGrindingTime() {
       return this.grindingTime;
    }
 
    public ItemStack getResult() {
       return this.result.func_77946_l();
    }
 
    public ItemStack getBonus() {
       return this.bonus.func_77946_l();
    }
 
    public float getBonusWeight() {
       return this.bonuswegiht;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    public String toString() {
       return this.result.toString() + this.id.toString();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 20 ms
	
	Decompiled with FernFlower.
*/