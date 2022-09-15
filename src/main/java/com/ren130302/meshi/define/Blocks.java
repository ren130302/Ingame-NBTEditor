package com.ren130302.meshi.define;

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.ren130302.lib.RegisterUtils;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public enum Blocks {
    SAKURA_LOG(() -> log(MaterialColor.PODZOL, MaterialColor.PODZOL)), SAKURA_LEAVE(() -> leaves(SoundType.GRASS)),
//    SAKURA_SAPLING(new SakuraSapling(new SakuraTree(SAKURA_LOG, SAKURA_LEAVE),
//	    Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F)
//		    .sound(SoundType.PLANT))),
//    SAKURA_PLANKS(() -> new Block(createWoodPropety())), SAKURA_SLAB(new PlayerFachingSlab(createWoodPropety())),
//    MAPLE_LOG(new HiganLogBlock(Material.WOOD,
//	    Block.Properties.create(Material.WOOD, Material.OBSIDIAN).hardnessAndResistance(2.0F)
//		    .sound(SoundType.WOOD))),
//    MAPLE_LEAVE((new SakuraLeave(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly()
//	    .sound(SoundType.PLANT).lightValue(10))).setPetalType(SakuraLeave.SakuraType.RED)),
//    MAPLE_SAPLING("maple_sapling",
//	    new SakuraSapling(new SakuraTree(MAPLE_LOG, MAPLE_LEAVE),
//		    Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
//			    .hardnessAndResistance(0.0F).sound(SoundType.PLANT))),
//    GINKGO_LOG("ginkgo_log",
//	    new HiganLogBlock(MaterialColor.WOOD,
//		    Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F)
//			    .sound(SoundType.WOOD))),
//    GINKGO_LEAVE("ginkgo_leave",
//	    (new SakuraLeave(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly()
//		    .sound(SoundType.PLANT).lightValue(10))).setPetalType(SakuraLeave.SakuraType.YELLOW)),
//    GINKGO_SAPLING("ginkgo_sapling",
//	    new SakuraSapling(new SakuraTree(GINKGO_LOG, GINKGO_LEAVE),
//		    Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
//			    .hardnessAndResistance(0.0F).sound(SoundType.PLANT))),
//    HINOKI_LOG("hinoki_log",
//	    new HiganLogBlock(MaterialColor.WOOD,
//		    Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F)
//			    .sound(SoundType.WOOD))),
//    HINOKI_LEAVE("hinoki_leave",
//	    new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly()
//		    .sound(SoundType.PLANT))),
//    HINOKI_SAPLING("hinoki_sapling",
//	    new SakuraSapling(new HinokiTree(),
//		    Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
//			    .hardnessAndResistance(0.0F).sound(SoundType.PLANT))),
//    BAMBOO_PANE("bamboo_pane", new PaneBlock(createWoodPropety())),
//    BAMBOO_PANE2("bamboo_pane2", new PaneBlock(createWoodPropety())),
//    BAMBOO_PANE3("bamboo_pane3", new PaneBlock(createWoodPropety())),
//    RANMA("ranma", new PaneBlock(createWoodPropety())), BLIND("blind", new PaneBlock(createWoodPropety())),
//    NOREN_BLUE("noren_blue", new PaneBlock(createMiscPropety().doesNotBlockMovement())),
//    NOREN_PURPLE("noren_purple", new PaneBlock(createMiscPropety().doesNotBlockMovement())),
//    KOTATSU("kotatsu", new Kotatsu(createMiscPropety())), CARDBOARD("cardboard", new Cardboard(createMiscPropety())),
//    BRICK_ORANGE("brick_orange", new Block(createRockPropety())),
//    BRICK_WHITE("brick_white", new Block(createRockPropety())),
//    BRICK_BROWN("brick_brown", new Block(createRockPropety())), SHOJI("shoji", new SlideDoor(createMiscPropety())),
//    SHOJI_YOKOGUMI("shoji_yokogumi", new SlideDoor(createMiscPropety())),
//    SHOJI_TATEGUMI("shoji_tategumi", new SlideDoor(createMiscPropety())),
//    SHOJI_YUKIMI("shoji_yukimi", new SlideDoor.LayerTranslucent(createMiscPropety())),
//    HUSUMA("husuma", new SlideDoor(createMiscPropety())),
//    GLASS_DOOR("glassdoor", new SlideDoor.LayerTranslucent(createMiscPropety())),
//    GLASS_DOOR_GRID("glassdoor_grid", new SlideDoor.LayerTranslucent(createMiscPropety())), INDLIGHT=
//    createBlockDyePattern("indlight", Indlight.class,
//	    Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F)
//		    .sound(SoundType.WOOD).lightValue(15),
//	    DyeColor.values()),
//    SPRING_SPAWNER("spring_spawner", new SpringSpawner(createRockPropety().hardnessAndResistance(2.5F))),
//    HOT_SPRING("hot_spring",
//	    new HotSpring(() -> MeshiFluids.HOT_SPING,
//		    Block.Properties
//			    .create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())),
//    WALL_SHELF("wall_shelf", new WallShelf(createWoodPropety(1.5F))),
//    COLLECTOR_PLATE("collector_pressure_plate",
//	    new CollectorPressurePlate(createWoodPropety(1.5F).doesNotBlockMovement(), 1, 2)),
//    DELIVERY_PLATE("delivery_pressure_plate",
//	    new DeliveryPressurePlate(createWoodPropety(1.5F).doesNotBlockMovement(), 1, 2)),
//    BAMBOO_POT("bamboo_pot", new BambooPot(createWoodPropety())),
//    KITSUNEBI("kitsunebi",
//	    new Kitsunebi(Block.Properties.create(Material.MISCELLANEOUS).lightValue(15).doesNotBlockMovement())),
//    MILLSTONE("millstone", new Millstone(createRockPropety(2.5F))),
//    ANDON("andon", new Andon(createWoodPropety(0.5F).lightValue(15))),
//    PADDY_FIELD("paddy_field", new PaddyField(createDirtPropety(0.6F).tickRandomly())),
//    RICE_PLANT("rice_plant", new RicePlant(createPlantPropety())),
//    HEARTH("hearth", new Hearth(createRockPropety().lightValue(10))),
//    MAPLE_ORE("maple_ore", new HiganOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))),
//    GINKGO_ORE("ginkgo_ore", new HiganOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))),
//    SAKURA_ORE("sakura_ore", new HiganOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))),
//    TATAMI("tatami", new PlayerFacingBlock(createMiscPropety().hardnessAndResistance(0.5F))),
//    TATAMI_SLAB("tatami_slab", new PlayerFachingSlab(createMiscPropety().hardnessAndResistance(0.5F))),
//    TATAMI_TAN("tatami_tan", new PlayerFacingBlock(createMiscPropety().hardnessAndResistance(0.5F))),
//    TATAMI_TAN_SLAB("tatami_tan_slab", new PlayerFachingSlab(createMiscPropety().hardnessAndResistance(0.5F))),
//    MINIATURE("miniature", new Miniature(createMiscPropety())),
//    KITSUNEBI_PRESSURE("kitsunebi_pressure",
//	    new KitsunebiPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, createMiscPropety())),
//    MANEKINEKO("manekineko", new ManekiNeko(createMiscPropety())), HUTON("huton", new Huton(createMiscPropety())),
//    JPCHEST_SINGLE("jpchest_single", new JPChest(createWoodPropety())), KAWARA_BLOCKS=Builder.create("kawara",
//    createMiscPropety()).build(true), STRAW_BLOCKS=Builder.create("straw",
//    createMiscPropety()).addStandardCube("straw_block").build(true),
//    CHECKERED_OAK_BLOCKS=Builder.create("checkered_oak", createWoodPropety()).build(true),
//    CHECKERED_BIRCH_BLOCKS=Builder.create("checkered_birch", createWoodPropety()).build(true),
//    CHECKERED_PINE_BLOCKS=Builder.create("checkered_pine", createWoodPropety()).build(true),
//    PLASTER_BLCOKS=Builder.create("plaster", createMiscPropety()).build(true), NAMAKO_BLOCKS=Builder.create("namako",
//    createMiscPropety()).build(true),
    // THATCHED_BLOCKS=Builder.create("thatched", createMiscPropety()).build(true),
    ;

    static {
	Stream.of(values()).forEach(value -> {
	    RegisterUtils.define(Defination.BLOCKS, value, value.block());
	    RegisterUtils.define(Defination.ITEMS, value, value.blockItem());
	});
    }

    private final Supplier<Block> block;
    private final Supplier<Item> blockItem;

    private Blocks(Supplier<Block> block) {
	this(block, new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));
    }

    private Blocks(Supplier<Block> block, Item.Properties prop) {
	this.block = block;
	this.blockItem = () -> new BlockItem(block.get(), prop);
    }

    public final Supplier<Block> block() {
	return this.block;
    }

    public final Supplier<Item> blockItem() {
	return this.blockItem;
    }

    private static RotatedPillarBlock log(MaterialColor p_50789_, MaterialColor p_50790_) {
	return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (p_152624_) -> {
	    return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_50789_ : p_50790_;
	}).strength(2.0F).sound(SoundType.WOOD));
    }

    private static LeavesBlock leaves(SoundType p_152615_) {
	return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks()
		.sound(p_152615_).noOcclusion());
    }
}
