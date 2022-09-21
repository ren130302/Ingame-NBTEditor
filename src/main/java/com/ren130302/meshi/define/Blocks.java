package com.ren130302.meshi.define;

import java.util.function.Supplier;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.BambooMod;
import com.ren130302.meshi.block.Andon;
import com.ren130302.meshi.block.BambooPot;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public class Blocks {
    public static final RegistryObject<Block> SAKURA_LOG = register("sakura_log",
	    () -> log(MaterialColor.WOOD, MaterialColor.PODZOL));
    public static final RegistryObject<Block> SAKURA_LEAVE = register("sakura_leave",
	    Block.Properties.of(Material.LEAVES));
    public static final RegistryObject<Block> SAKURA_SAPLING = register("sakura_sapling",
	    Block.Properties.of(Material.GLASS));
    public static final RegistryObject<Block> SAKURA_PLANKS = register("sakura_planks",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SAKURA_SLAB = register("sakura_slab", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MAPLE_LOG = register("maple_log", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MAPLE_LEAVE = register("maple_leave", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MAPLE_SAPLING = register("maple_sapling",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GINKGO_LOG = register("ginkgo_log", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GINKGO_LEAVE = register("ginkgo_leave", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GINKGO_SAPLING = register("ginkgo_sapling",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HINOKI_LOG = register("hinoki_log", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HINOKI_LEAVE = register("hinoki_leave", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HINOKI_SAPLING = register("hinoki_sapling",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BAMBOO_PANE = register("bamboo_pane", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BAMBOO_PANE2 = register("bamboo_pane2", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BAMBOO_PANE3 = register("bamboo_pane3", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> RANMA = register("ranma", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BLIND = register("blind", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> NOREN_BLUE = register("noren_blue", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> NOREN_PURPLE = register("noren_purple", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> KOTATSU = register("kotatsu", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> CARDBOARD = register("cardboard", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BRICK_ORANGE = register("brick_orange", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BRICK_WHITE = register("brick_white", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BRICK_BROWN = register("brick_brown", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SHOJI = register("shoji", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SHOJI_YOKOGUMI = register("shoji_yokogumi",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SHOJI_TATEGUMI = register("shoji_tategumi",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SHOJI_YUKIMI = register("shoji_yukimi", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HUSUMA = register("husuma", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GLASS_DOOR = register("glassdoor", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GLASS_DOOR_GRID = register("glassdoor_grid",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> INDLIGHT = register("indlight", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SPRING_SPAWNER = register("spring_spawner",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HOT_SPRING = register("hot_spring", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> WALL_SHELF = register("wall_shelf", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> COLLECTOR_PLATE = register("collector_pressure_plate",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> DELIVERY_PLATE = register("delivery_pressure_plate",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> BAMBOO_POT = register("bamboo_pot",
	    () -> new BambooPot(Properties.of(Material.AMETHYST)));
    public static final RegistryObject<Block> KITSUNEBI = register("kitsunebi", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MILLSTONE = register("millstone", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> ANDON = register("andon",
	    () -> new Andon(Properties.of(Material.AMETHYST)));
    public static final RegistryObject<Block> PADDY_FIELD = register("paddy_field", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> RICE_PLANT = register("rice_plant", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HEARTH = register("hearth", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MAPLE_ORE = register("maple_ore", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> GINKGO_ORE = register("ginkgo_ore", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> SAKURA_ORE = register("sakura_ore", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> TATAMI = register("tatami", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> TATAMI_SLAB = register("tatami_slab", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> TATAMI_TAN = register("tatami_tan", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> TATAMI_TAN_SLAB = register("tatami_tan_slab",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MINIATURE = register("miniature", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> KITSUNEBI_PRESSURE = register("kitsunebi_pressure",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> MANEKINEKO = register("manekineko", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> HUTON = register("huton", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> JPCHEST_SINGLE = register("jpchest_single",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> KAWARA_BLOCKS = register("kawara", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> STRAW_BLOCKS = register("straw", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> CHECKERED_OAK_BLOCKS = register("checkered_oak",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> CHECKERED_BIRCH_BLOCKS = register("checkered_birch",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> CHECKERED_PINE_BLOCKS = register("checkered_pine",
	    Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> PLASTER_BLCOKS = register("plaster", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> NAMAKO_BLOCKS = register("namako", Properties.of(Material.AMETHYST));
    public static final RegistryObject<Block> THATCHED_BLOCKS = register("thatched", Properties.of(Material.AMETHYST));

    private static RegistryObject<Block> register(String name, Supplier<Block> block) {
	return RegisterUtils.blockAndItem(BambooMod.BLOCKS, BambooMod.ITEMS, name, block,
		new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));
    }

    private static RegistryObject<Block> register(String name, Block.Properties blockProp) {
	return RegisterUtils.blockAndItem(BambooMod.BLOCKS, BambooMod.ITEMS, name, blockProp,
		new Item.Properties().tab(CreativeTabs.ITEM_GROUP.get()));
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

    public static void init() {
    }
}
