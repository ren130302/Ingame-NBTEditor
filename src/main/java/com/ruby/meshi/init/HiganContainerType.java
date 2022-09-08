 package com.ruby.meshi.init;
 
 import com.mojang.blaze3d.platform.ScreenManager;
import com.ruby.meshi.client.inventory.CollectorPressurePlateScreen;
import com.ruby.meshi.client.inventory.ExtendInventoryScreen;
import com.ruby.meshi.client.inventory.FukuroScreen;
import com.ruby.meshi.client.inventory.HearthScreen;
import com.ruby.meshi.client.inventory.MillstoneScreen;
import com.ruby.meshi.common.inventory.CollectorPressurePlateContainer;
import com.ruby.meshi.common.inventory.ExtendInventoryContainer;
import com.ruby.meshi.common.inventory.FukuroContainer;
import com.ruby.meshi.common.inventory.HearthContainer;
import com.ruby.meshi.common.inventory.MillstoneContainer;
import com.ruby.meshi.init.HiganContainerType.IFactory;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.Container;
import net.minecraftforge.network.IContainerFactory;
 
 
 
 
 
 
 
 
 
 public class HiganContainerType {
    public static final ContainerType<CollectorPressurePlateContainer> COLLECTOR_PLATE = create("collector_pressure_plate", CollectorPressurePlateContainer::new);
    public static final ContainerType<MillstoneContainer> MILLSTONE = create("millstone", MillstoneContainer::new);
    public static final ContainerType<HearthContainer> HEARTH = create("hearth", HearthContainer::new);
    public static final ContainerType<FukuroContainer> FUKURO = create("fukuro", FukuroContainer::new);
    public static final ContainerType<ExtendInventoryContainer> EXTEND_INVENTORY = create("extend_inventory", ExtendInventoryContainer::new);
 
    private static <T extends Container> ContainerType<T> create(String key, IFactory<T> factory) {
       IContainerFactory<T> fact = (windowId, inv, data) -> {
          return factory.create(windowId, inv);
       };
       return (ContainerType)(new ContainerType(fact)).setRegistryName("meshi", key);
    }
 
 
    public static void registerScreenFactories() {
       ScreenManager.func_216911_a(COLLECTOR_PLATE, CollectorPressurePlateScreen::new);
       ScreenManager.func_216911_a(MILLSTONE, MillstoneScreen::new);
       ScreenManager.func_216911_a(HEARTH, HearthScreen::new);
       ScreenManager.func_216911_a(FUKURO, FukuroScreen::new);
       ScreenManager.func_216911_a(EXTEND_INVENTORY, ExtendInventoryScreen::new);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/