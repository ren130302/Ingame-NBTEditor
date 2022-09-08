 package com.ruby.meshi.block;import java.util.stream.Stream;

import com.ruby.meshi.block.SakuraLeave.SakuraType;
import com.ruby.meshi.block.SlideDoor.LayerTranslucent;
import com.ruby.meshi.fluid.MeshiFluids;
import com.ruby.meshi.world.gen.HinokiTree;
import com.ruby.meshi.world.gen.SakuraTree;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.material.MaterialColor;
 public class SakuraBlocks {   public static final Block SAKURA_LOG;   public static final Block SAKURA_LEAVE;   public static final Block SAKURA_SAPLING;   public static final Block SAKURA_PLANKS;   public static final Block SAKURA_SLAB;   public static final Block MAPLE_LOG;   public static final Block MAPLE_LEAVE;   public static final Block MAPLE_SAPLING;   public static final Block GINKGO_LOG;   public static final Block GINKGO_LEAVE;   public static final Block GINKGO_SAPLING;   public static final Block HINOKI_LOG;   public static final Block HINOKI_LEAVE;   public static final Block HINOKI_SAPLING;   public static final Block BAMBOO_PANE;   public static final Block BAMBOO_PANE2;   public static final Block BAMBOO_PANE3;   public static final Block RANMA;   public static final Block BLIND;   public static final Block NOREN_BLUE;   public static final Block NOREN_PURPLE;   public static final Block KOTATSU;   public static final Block CARDBOARD;   public static final Block BRICK_ORANGE;   public static final Block BRICK_WHITE;   public static final Block BRICK_BROWN;   public static final Block SHOJI;   public static final Block SHOJI_YOKOGUMI;   public static final Block SHOJI_TATEGUMI;   public static final Block SHOJI_YUKIMI;   public static final Block HUSUMA;   public static final Block GLASS_DOOR;   public static final Block GLASS_DOOR_GRID;   public static final Block[] INDLIGHT;   public static final Block SPRING_SPAWNER;   public static final Block HOT_SPRING;   public static final Block WALL_SHELF;   public static final Block COLLECTOR_PLATE;   public static final Block DELIVERY_PLATE;   public static final Block BAMBOO_POT;   public static final Block KITSUNEBI;   public static final Block MILLSTONE;   public static final Block ANDON;
    public static final Block PADDY_FIELD;
    public static final Block RICE_PLANT;
    public static final Block HEARTH;
    public static final Block MAPLE_ORE;
    public static final Block GINKGO_ORE;
    public static final Block SAKURA_ORE;
    public static final Block TATAMI;
    public static final Block TATAMI_SLAB;
    public static final Block TATAMI_TAN;
    public static final Block TATAMI_TAN_SLAB;
    public static final Block MINIATURE;
    public static final Block KITSUNEBI_PRESSURE;
    public static final Block MANEKINEKO;
    public static final Block HUTON;
    public static final Block JPCHEST_SINGLE;
    public static final Block[] KAWARA_BLOCKS;
    public static final Block[] STRAW_BLOCKS;
    public static final Block[] CHECKERED_OAK_BLOCKS;
    public static final Block[] CHECKERED_BIRCH_BLOCKS;
    public static final Block[] CHECKERED_PINE_BLOCKS;
    public static final Block[] PLASTER_BLCOKS;
    public static final Block[] NAMAKO_BLOCKS;
    public static final Block[] THATCHED_BLOCKS;
 
    static {
       SAKURA_LOG = register("sakura_log", new HiganLogBlock(MaterialColor.field_151663_o, Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
       SAKURA_LEAVE = register("sakura_leave", (new SakuraLeave(Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraType.PINK));
       SAKURA_SAPLING = register("sakura_sapling", new SakuraSapling(new SakuraTree(SAKURA_LOG, SAKURA_LEAVE), Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
       SAKURA_PLANKS = register("sakura_planks", new PlayerFacingBlock(createWoodPropety()));
       SAKURA_SLAB = register("sakura_slab", new PlayerFachingSlab(createWoodPropety()));
       MAPLE_LOG = register("maple_log", new HiganLogBlock(MaterialColor.field_151663_o, Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
       MAPLE_LEAVE = register("maple_leave", (new SakuraLeave(Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraType.RED));
       MAPLE_SAPLING = register("maple_sapling", new SakuraSapling(new SakuraTree(MAPLE_LOG, MAPLE_LEAVE), Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
       GINKGO_LOG = register("ginkgo_log", new HiganLogBlock(MaterialColor.field_151663_o, Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
       GINKGO_LEAVE = register("ginkgo_leave", (new SakuraLeave(Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c).func_200951_a(10))).setPetalType(SakuraType.YELLOW));
       GINKGO_SAPLING = register("ginkgo_sapling", new SakuraSapling(new SakuraTree(GINKGO_LOG, GINKGO_LEAVE), Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
       HINOKI_LOG = register("hinoki_log", new HiganLogBlock(MaterialColor.field_151663_o, Properties.func_200949_a(Material.field_151575_d, MaterialColor.field_151654_J).func_200943_b(2.0F).func_200947_a(SoundType.field_185848_a)));
       HINOKI_LEAVE = register("hinoki_leave", new LeavesBlock(Properties.func_200945_a(Material.field_151584_j).func_200943_b(0.2F).func_200944_c().func_200947_a(SoundType.field_185850_c)));
       HINOKI_SAPLING = register("hinoki_sapling", new SakuraSapling(new HinokiTree(), Properties.func_200945_a(Material.field_151585_k).func_200942_a().func_200944_c().func_200943_b(0.0F).func_200947_a(SoundType.field_185850_c)));
       BAMBOO_PANE = register("bamboo_pane", new PaneBlock(createWoodPropety()));
       BAMBOO_PANE2 = register("bamboo_pane2", new PaneBlock(createWoodPropety()));
       BAMBOO_PANE3 = register("bamboo_pane3", new PaneBlock(createWoodPropety()));
       RANMA = register("ranma", new PaneBlock(createWoodPropety()));
       BLIND = register("blind", new PaneBlock(createWoodPropety()));
       NOREN_BLUE = register("noren_blue", new PaneBlock(createMiscPropety().func_200942_a()));
       NOREN_PURPLE = register("noren_purple", new PaneBlock(createMiscPropety().func_200942_a()));
       KOTATSU = register("kotatsu", new Kotatsu(createMiscPropety()));
       CARDBOARD = register("cardboard", new Cardboard(createMiscPropety()));
       BRICK_ORANGE = register("brick_orange", new Block(createRockPropety()));
       BRICK_WHITE = register("brick_white", new Block(createRockPropety()));
       BRICK_BROWN = register("brick_brown", new Block(createRockPropety()));
 
       SHOJI = register("shoji", new SlideDoor(createMiscPropety()));
       SHOJI_YOKOGUMI = register("shoji_yokogumi", new SlideDoor(createMiscPropety()));
       SHOJI_TATEGUMI = register("shoji_tategumi", new SlideDoor(createMiscPropety()));
       SHOJI_YUKIMI = register("shoji_yukimi", new LayerTranslucent(createMiscPropety()));
       HUSUMA = register("husuma", new SlideDoor(createMiscPropety()));
       GLASS_DOOR = register("glassdoor", new LayerTranslucent(createMiscPropety()));
       GLASS_DOOR_GRID = register("glassdoor_grid", new LayerTranslucent(createMiscPropety()));
 
       INDLIGHT = createBlockDyePattern("indlight", Indlight.class, Properties.func_200945_a(Material.field_151594_q).func_200942_a().func_200943_b(0.5F).func_200947_a(SoundType.field_185848_a).func_200951_a(15), DyeColor.values());
 
       SPRING_SPAWNER = register("spring_spawner", new SpringSpawner(createRockPropety().func_200943_b(2.5F)));
       HOT_SPRING = register("hot_spring", new HotSpring(() -> {         return MeshiFluids.HOT_SPING;      }, Properties.func_200945_a(Material.field_151586_h).func_200942_a().func_200943_b(100.0F).func_222380_e()));
       WALL_SHELF = register("wall_shelf", new WallShelf(createWoodPropety(1.5F)));
       COLLECTOR_PLATE = register("collector_pressure_plate", new CollectorPressurePlate(createWoodPropety(1.5F).func_200942_a(), 1, 2));
       DELIVERY_PLATE = register("delivery_pressure_plate", new DeliveryPressurePlate(createWoodPropety(1.5F).func_200942_a(), 1, 2));
       BAMBOO_POT = register("bamboo_pot", new BambooPot(createWoodPropety()));
       KITSUNEBI = register("kitsunebi", new Kitsunebi(Properties.func_200945_a(Material.field_151594_q).func_200951_a(15).func_200942_a()));
       MILLSTONE = register("millstone", new Millstone(createRockPropety(2.5F)));
       ANDON = register("andon", new Andon(createWoodPropety(0.5F).func_200951_a(15)));
       PADDY_FIELD = register("paddy_field", new PaddyField(createDirtPropety(0.6F).func_200944_c()));
       RICE_PLANT = register("rice_plant", new RicePlant(createPlantPropety()));
       HEARTH = register("hearth", new Hearth(createRockPropety().func_200951_a(10)));
       MAPLE_ORE = register("maple_ore", new HiganOre(Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
       GINKGO_ORE = register("ginkgo_ore", new HiganOre(Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
       SAKURA_ORE = register("sakura_ore", new HiganOre(Properties.func_200945_a(Material.field_151576_e).func_200948_a(3.0F, 3.0F)));
       TATAMI = register("tatami", new PlayerFacingBlock(createMiscPropety().func_200943_b(0.5F)));
       TATAMI_SLAB = register("tatami_slab", new PlayerFachingSlab(createMiscPropety().func_200943_b(0.5F)));
       TATAMI_TAN = register("tatami_tan", new PlayerFacingBlock(createMiscPropety().func_200943_b(0.5F)));
       TATAMI_TAN_SLAB = register("tatami_tan_slab", new PlayerFachingSlab(createMiscPropety().func_200943_b(0.5F)));
       MINIATURE = register("miniature", new Miniature(createMiscPropety()));
       KITSUNEBI_PRESSURE = register("kitsunebi_pressure", new KitsunebiPressurePlateBlock(Sensitivity.EVERYTHING, createMiscPropety()));
       MANEKINEKO = register("manekineko", new ManekiNeko(createMiscPropety()));
       HUTON = register("huton", new Huton(createMiscPropety()));
 
       JPCHEST_SINGLE = register("jpchest_single", new JPChest(createWoodPropety()));
 
 
       KAWARA_BLOCKS = Builder.create("kawara", createMiscPropety()).build(true);
       STRAW_BLOCKS = Builder.create("straw", createMiscPropety()).addStandardCube("straw_block").build(true);
       CHECKERED_OAK_BLOCKS = Builder.create("checkered_oak", createWoodPropety()).build(true);
       CHECKERED_BIRCH_BLOCKS = Builder.create("checkered_birch", createWoodPropety()).build(true);
       CHECKERED_PINE_BLOCKS = Builder.create("checkered_pine", createWoodPropety()).build(true);
       PLASTER_BLCOKS = Builder.create("plaster", createMiscPropety()).build(true);
       NAMAKO_BLOCKS = Builder.create("namako", createMiscPropety()).build(true);
       THATCHED_BLOCKS = Builder.create("thatched", createMiscPropety()).build(true);
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    private static Block register(String name, Block block) {
       return (Block)block.setRegistryName("meshi", name);
    }
 
 
    private static <T extends Block & SimpleColorMultiply<T>> Block[] createBlockDyePattern(String basename, Class<T> block, Properties prop, DyeColor... colors) {
       return (Block[])Stream.of(colors).map((c) -> {
          return (Block)((Block)((SimpleColorMultiply)createBlockIns(block, prop)).setColorCode(c)).setRegistryName("meshi", basename + "_" + c.func_176610_l());
       }).toArray((x$0) -> {
          return new Block[x$0];
       });
    }
    private static <T extends Block> T createBlockIns(Class<T> cls, Properties prop) {      try {
          return (Block)cls.getConstructor(Properties.class).newInstance(prop);
       } catch (Exception var3) {
 
          var3.printStackTrace();
          return null;
       }
    }
 
    private static Properties createMiscPropety() {
       return createMiscPropety(1.0F);
    }
 
    private static Properties createMiscPropety(float hardness) {
       return createPropety(Material.field_151592_s, hardness, SoundType.field_185854_g);
    }
 
    private static Properties createPlantPropety() {
       return createPropety(Material.field_151585_k, 0.0F, SoundType.field_222472_s).func_200942_a().func_200944_c();
    }
 
    private static Properties createDirtPropety(float hardness) {
       return createPropety(Material.field_151578_c, hardness, SoundType.field_185849_b);
    }
 
    private static Properties createRockPropety() {
       return createRockPropety(2.0F);
    }
 
    private static Properties createRockPropety(float hardness) {
       return createPropety(Material.field_151576_e, hardness, SoundType.field_185851_d);
    }
 
    private static Properties createWoodPropety() {
       return createWoodPropety(2.0F);
    }
 
    private static Properties createWoodPropety(float hardness) {
       return createPropety(Material.field_151575_d, hardness, SoundType.field_185848_a);
    }
 
    private static Properties createPropety(Material materialIn, float hardness, SoundType soundTypeIn) {
       return Properties.func_200945_a(materialIn).func_200943_b(hardness).func_200947_a(soundTypeIn);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 60 ms
	
	Decompiled with FernFlower.
*/