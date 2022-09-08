 package com.ruby.meshi.item;import java.util.List;

import com.google.common.collect.Lists;
import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.client.CreativeTab;
import com.ruby.meshi.fluid.MeshiFluids;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.ItemTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
 public class HiganItems {
    public static final Item SAKURA_GEM = register("sakura_gem", new Item(baseProp()));
    public static final Item GINKGO_GEM = register("ginkgo_gem", new Item(baseProp()));
    public static final Item MAPLE_INGOT = register("maple_ingot", new Item(baseProp()));
    public static final Item PADDY_FIELD_HOE;
    public static final Item RICE_SEED;
    public static final Item STRAW;
    public static final Item BIOME_LENZ;
    public static final Item ITEM_MAGNET;
    public static final Item RAW_RICE;
    public static final Item KATANA;
    public static final Item SHURIKEN_STONE;
    public static final Item SHURIKEN_IRON;
    public static final Item SHURIKEN_DIAMOND;
    public static final Item NINJA_BRACELET;
    public static final Item SCARECROW;
    public static final Item FUKURO;
    public static final Item HOT_SPRING_BUCKET;
    public static final Item CLIMBING_CLAW;
    public static final Item WATER_WALKER;
    public static final Item[] FOODS;
 
    static {
       PADDY_FIELD_HOE = register("paddy_field_hoe", new PaddyFieldHoe(ItemTier.DIAMOND, 0.0F, baseProp().func_200917_a(1)));
       RICE_SEED = register("rice_seed", new BlockNamedItem(SakuraBlocks.RICE_PLANT, baseProp()));
       STRAW = register("straw", new Item(baseProp()));
       BIOME_LENZ = register("biome_lenz", new BiomeLenz(baseProp().func_200917_a(1)));
       ITEM_MAGNET = register("item_magnet", new ItemMagnet(baseProp().func_200917_a(1)));
       RAW_RICE = register("rawrice", new Item(baseProp()));
       KATANA = register("katana", new Katana(baseProp()));
       SHURIKEN_STONE = register("shuriken_stone", new Shuriken(ItemTier.STONE, baseProp().func_200917_a(64)));
       SHURIKEN_IRON = register("shuriken_iron", new Shuriken(ItemTier.IRON, baseProp().func_200917_a(64)));
       SHURIKEN_DIAMOND = register("shuriken_diamond", new Shuriken(ItemTier.DIAMOND, baseProp().func_200917_a(64)));
       NINJA_BRACELET = register("ninja_bracelet", new NinjaBracelet(baseProp().func_200918_c(384)));
       SCARECROW = register("scarecrow", new ScarecrowItem(new Properties()));
       FUKURO = register("fukuro", new Fukuro(baseProp().func_200917_a(1)));
       HOT_SPRING_BUCKET = register("hot_spring_bucket", new BucketItem(() -> {         return MeshiFluids.HOT_SPING;      }, baseProp().func_200919_a(Items.field_151133_ar).func_200917_a(1)));
       CLIMBING_CLAW = register("climbing_claw", new ClimbingClaw(baseProp().func_200917_a(1)));
       WATER_WALKER = register("water_warker", new WaterWalker(baseProp().func_200917_a(1)));
 
 
       FOODS = createFoods();
    }
 
 
    private static Item register(String key, Item item) {
       return (Item)item.setRegistryName(new ResourceLocation("meshi", key));
    }
 
    private static Properties baseProp() {
       return (new Properties()).func_200916_a(CreativeTab.ITEM_GROUP);
    }
 
    private static Item[] createFoods() {
       List<Item> items = Lists.newArrayList();
 
 
 
       items.add(createFood("agedashi", food(4).func_221453_d()));
       items.add(createFood("dango_anko", food(3).func_221453_d()));
       items.add(createFood("dango_kinako", food(3).func_221453_d()));
       items.add(createFood("dango_mitarashi", food(3).func_221453_d()));
       items.add(createFood("dango_sansyoku", food(3).func_221453_d()));
       items.add(createFood("dango_zunda", food(3).func_221453_d()));
       items.add(createFood("kushi_buta", food(4).func_221453_d()));
       items.add(createFood("kushi_gyu", food(4).func_221453_d()));
       items.add(createFood("kushi_tori", food(4).func_221453_d()));
       items.add(createFood("meshi", food(4).func_221453_d()));
       items.add(createFood("meshi_azuki", food(6).func_221453_d()));
       items.add(createFood("meshi_buta", food(8).func_221453_d()));
       items.add(createFood("meshi_egg", food(6).func_221453_d()));
       items.add(createFood("meshi_gyu", food(8).func_221453_d()));
       items.add(createFood("meshi_katsu", food(9).func_221453_d()));
       items.add(createFood("meshi_kinoko", food(8).func_221453_d()));
       items.add(createFood("meshi_natto", food(6).func_221453_d()));
       items.add(createFood("meshi_natto_egg", food(7).func_221453_d()));
       items.add(createFood("meshi_oyako", food(8).func_221453_d()));
       items.add(createFood("mochi", food(3).func_221453_d()));
       items.add(createFood("mochi_cooked", food(4).func_221453_d()));
       items.add(createFood("mochi_sakura", food(4).func_221453_d()));
       items.add(createFood("natto", food(3).func_221453_d()));
       items.add(createFood("noodle_ramen", food(8).func_221453_d()));
       items.add(createFood("noodle_udon", food(8).func_221453_d()));
       items.add(createFood("ohagi", food(4).func_221453_d()));
       items.add(createFood("onigiri", food(4).func_221453_d()));
       items.add(createFood("pizza", food(6).func_221453_d()));
       items.add(createFood("sherbet_berry", food(4).func_221453_d()));
       items.add(createFood("sherbet_berry_milk", food(5).func_221453_d()));
       items.add(createFood("tofu", food(3).func_221453_d()));
 
       return (Item[])items.stream().toArray((x$0) -> {
          return new Item[x$0];
       });
    }   private static Builder food(int hunger) {
       Builder builder = (Builder)(new Builder()).func_221456_a(hunger).func_221454_a(hunger < 5 ? (float)hunger * 0.5F : (float)hunger * 0.75F);
       if (hunger < 4) {
          builder = (Builder)builder.func_221457_c();
       }
       return builder;
    }
 
    private static Item createFood(String name, Food food) {
       return register(name, new Meshi(baseProp().func_221540_a(food)));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 17 ms
	
	Decompiled with FernFlower.
*/