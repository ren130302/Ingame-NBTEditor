package com.ren130302.meshi.define;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.BambooMod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final RegistryObject<Item> SAKURA_GEM = register("sakura_gem", baseProp());
    public static final RegistryObject<Item> GINKGO_GEM = register("ginkgo_gem", baseProp());
    public static final RegistryObject<Item> MAPLE_INGOT = register("maple_ingot", baseProp());
    public static final RegistryObject<Item> PADDY_FIELD_HOE = register("paddy_field_hoe", baseProp().stacksTo(1));
    public static final RegistryObject<Item> RICE_SEED = register("rice_seed", baseProp());
    // public static final RegistryObject<Item> STRAW = register("straw",
    // baseProp());
    public static final RegistryObject<Item> BIOME_LENZ = register("biome_lenz", baseProp().stacksTo(1));
    public static final RegistryObject<Item> ITEM_MAGNET = register("item_magnet", baseProp().stacksTo(1));
    public static final RegistryObject<Item> RAW_RICE = register("rawrice", baseProp());
    public static final RegistryObject<Item> KATANA = register("katana", baseProp());
    public static final RegistryObject<Item> SHURIKEN_STONE = register("shuriken_stone", baseProp().stacksTo(64));
    public static final RegistryObject<Item> SHURIKEN_IRON = register("shuriken_iron", baseProp().stacksTo(64));
    public static final RegistryObject<Item> SHURIKEN_DIAMOND = register("shuriken_diamond", baseProp().stacksTo(64));
    public static final RegistryObject<Item> NINJA_BRACELET = register("ninja_bracelet", baseProp());
    public static final RegistryObject<Item> SCARECROW = register("scarecrow", baseProp());
    public static final RegistryObject<Item> FUKURO = register("fukuro", baseProp().stacksTo(1));
    public static final RegistryObject<Item> HOT_SPRING_BUCKET = register("hot_spring_bucket", baseProp().stacksTo(1));
    public static final RegistryObject<Item> CLIMBING_CLAW = register("climbing_claw", baseProp().stacksTo(1));
    public static final RegistryObject<Item> WATER_WALKER = register("water_warker", baseProp().stacksTo(1));
    public static final RegistryObject<Item> AGEDASHI = register("agedashi", food(4));
    public static final RegistryObject<Item> DANGO_ANKO = register("dango_anko", food(3));
    public static final RegistryObject<Item> DANGO_KINAKO = register("dango_kinako", food(3));
    public static final RegistryObject<Item> DANGO_MITARASHI = register("dango_mitarashi", food(3));
    public static final RegistryObject<Item> DANGO_SANSYOKU = register("dango_sansyoku", food(3));
    public static final RegistryObject<Item> DANGO_ZUNDA = register("dango_zunda", food(3));
    public static final RegistryObject<Item> KUSHI_BUTA = register("kushi_buta", food(4));
    public static final RegistryObject<Item> KUSHI_GYU = register("kushi_gyu", food(4));
    public static final RegistryObject<Item> KUSHI_TORI = register("kushi_tori", food(4));
    public static final RegistryObject<Item> MESHI = register("meshi", food(4));
    public static final RegistryObject<Item> MESHI_AZUKI = register("meshi_azuki", food(6));
    public static final RegistryObject<Item> MESHI_BUTA = register("meshi_buta", food(8));
    public static final RegistryObject<Item> MESHI_EGG = register("meshi_egg", food(6));
    public static final RegistryObject<Item> MESHI_GYU = register("meshi_gyu", food(8));
    public static final RegistryObject<Item> MESHI_KATSU = register("meshi_katsu", food(9));
    public static final RegistryObject<Item> meshi_kinoko = register("meshi_kinoko", food(8));
    public static final RegistryObject<Item> MESHI_NATTO = register("meshi_natto", food(6));
    public static final RegistryObject<Item> MESHI_NATTO_EGG = register("meshi_natto_egg", food(7));
    public static final RegistryObject<Item> MESHI_OYAKO = register("meshi_oyako", food(8));
    public static final RegistryObject<Item> MOCHI = register("mochi", food(3));
    public static final RegistryObject<Item> MOCHI_COOKED = register("mochi_cooked", food(4));
    public static final RegistryObject<Item> MOCHI_SAKURA = register("mochi_sakura", food(4));
    public static final RegistryObject<Item> NATTO = register("natto", food(3));
    public static final RegistryObject<Item> NOODLE_RAMEN = register("noodle_ramen", food(8));
    public static final RegistryObject<Item> NOODLE_UDON = register("noodle_udon", food(8));
    public static final RegistryObject<Item> OHAGI = register("ohagi", food(4));
    public static final RegistryObject<Item> ONIGIRI = register("onigiri", food(4));
    public static final RegistryObject<Item> PIZZA = register("pizza", food(6));
    public static final RegistryObject<Item> SHERBET_BERRY = register("sherbet_berry", food(4));
    public static final RegistryObject<Item> SHERBET_BERRY_MILK = register("sherbet_berry_milk", food(5));
    public static final RegistryObject<Item> TOFU = register("tofu", food(3));

    private static Properties baseProp() {
	return new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get());
    }

    private static FoodProperties food(int hunger) {
	FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(hunger)
		.saturationMod((hunger < 5) ? (hunger * 0.5F) : (hunger * 0.75F));
	if (hunger < 4) {
	    builder = builder.fast();
	}
	return builder.build();
    }

    private static RegistryObject<Item> register(String name, FoodProperties foodProperties) {
	return RegisterUtils.item(BambooMod.ITEMS, name, new Item.Properties().food(foodProperties));
    }

    private static RegistryObject<Item> register(String name, Item.Properties itemProp) {
	return RegisterUtils.item(BambooMod.ITEMS, name, itemProp);
    }

    public static void init() {
    }
}
