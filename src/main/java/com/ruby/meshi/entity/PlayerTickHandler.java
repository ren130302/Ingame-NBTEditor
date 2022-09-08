 package com.ruby.meshi.entity;
 
 import com.ruby.meshi.item.Accessory;
import com.ruby.meshi.util.CapabilityExtendInventory;

import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class PlayerTickHandler {
    @SubscribeEvent
    public static void playerTickPost(PlayerTickEvent event) {
       if (event.phase == Phase.END) {
          CapabilityExtendInventory.getInventory(event.player).accessoryInventory.forEach((s) -> {
             if (s.func_77973_b() instanceof Accessory) {
                ((Accessory)s.func_77973_b()).playerPostTick(event.player.func_130014_f_(), event.player, s);
             }
          });
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 7 ms
	
	Decompiled with FernFlower.
*/