 package com.ruby.meshi.init;
 
 import java.util.function.Supplier;
import java.util.stream.Stream;

import com.mojang.datafixers.types.Type;
import com.ruby.meshi.block.JPChest;
import com.ruby.meshi.block.ManekiNeko;
import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.block.SlideDoor;
import com.ruby.meshi.block.tileentity.BambooPotTileEntity;
import com.ruby.meshi.block.tileentity.CardboardTileEntity;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
import com.ruby.meshi.block.tileentity.ContainerTileEntity;
import com.ruby.meshi.block.tileentity.HearthTileEntity;
import com.ruby.meshi.block.tileentity.ManekiNekoTileEntity;
import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;
import com.ruby.meshi.block.tileentity.WallShelfTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
 public class HiganTileEntityType {
    public static final TileEntityType<?> WALL_SHELF;
    public static final TileEntityType<?> COLLECTOR_PLATE;
    public static final TileEntityType<?> BAMBOO_POT;
    public static final TileEntityType<?> MILLSTONE;
    public static final TileEntityType<?> HEARTH;
    public static final TileEntityType<?> CARDBOARD;
    public static final TileEntityType<?> SLIDEDOOR;
    public static final TileEntityType<?> MINIATUE;
    public static final TileEntityType<?> JPCHEST;
    public static final TileEntityType<?> MANEKINEKO;
 
    static {
       WALL_SHELF = create("wall_shelf", WallShelfTileEntity::new, SakuraBlocks.WALL_SHELF);
       COLLECTOR_PLATE = create("collector_pressure_plate", CollectorPressurePlateTileEntity::new, SakuraBlocks.COLLECTOR_PLATE, SakuraBlocks.DELIVERY_PLATE);
       BAMBOO_POT = create("bamboo_pot", BambooPotTileEntity::new, SakuraBlocks.BAMBOO_POT);
       MILLSTONE = create("millstone", MillstoneTileEntity::new, SakuraBlocks.MILLSTONE);
       HEARTH = create("hearth", HearthTileEntity::new, SakuraBlocks.HEARTH);
       CARDBOARD = create("cardboard", CardboardTileEntity::new, SakuraBlocks.CARDBOARD);
       SLIDEDOOR = create("slidedoor", SlideDoorTileEntity::new, getIsInstanceBlocks(SlideDoor.class));
       MINIATUE = create("miniature", MiniatureTileEntity::new, SakuraBlocks.MINIATURE);
       JPCHEST = create("jpchest", ContainerTileEntity::new, getIsInstanceBlocks(JPChest.class));
       MANEKINEKO = create("manekineko", ManekiNekoTileEntity::new, getIsInstanceBlocks(ManekiNeko.class));
    }
    private static TileEntityType<? extends TileEntity> create(String key, Supplier<? extends TileEntity> tile, Block... block) {
       return (TileEntityType)Builder.func_223042_a(tile, block).func_206865_a((Type)null).setRegistryName("meshi", key);
    }
 
    private static Block[] getIsInstanceBlocks(Class<?> clazz) {
       Stream var10000 = RegisterEvents.getFields(SakuraBlocks.class);
       clazz.getClass();
       return (Block[])var10000.filter(clazz::isInstance).toArray((x$0) -> {
          return new Block[x$0];
       });
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 9 ms
	
	Decompiled with FernFlower.
*/