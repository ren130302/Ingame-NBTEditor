 package com.ruby.meshi.common;
 
 import com.google.common.collect.UnmodifiableIterator;

import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class Remapper {
    @SubscribeEvent
    public static void remapBlocks(MissingMappings<Block> event) {
       UnmodifiableIterator var1 = event.getMappings().iterator();      while(var1.hasNext()) {         Mapping<Block> miss = (Mapping)var1.next();
          if (event.getName().func_110624_b().equals("meshi")) {
             miss.remap(Blocks.field_150350_a);
          }      }
 
    }
 
    @SubscribeEvent
    public static void remapItems(MissingMappings<Item> event) {
       UnmodifiableIterator var1 = event.getMappings().iterator();      while(var1.hasNext()) {         Mapping<Item> miss = (Mapping)var1.next();
          if (event.getName().func_110624_b().equals("meshi")) {
             miss.remap(Items.field_190931_a);
          }      }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/