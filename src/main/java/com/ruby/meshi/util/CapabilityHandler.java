 package com.ruby.meshi.util;
 
 import com.ruby.meshi.util.CapabilityHandler.CapExtendInvProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 
 
 
 
 
 
 
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class CapabilityHandler {
    public static final ResourceLocation EXTEND_INVENTORY = new ResourceLocation("meshi", "extend_inv");
 
    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
       if (event.getObject() instanceof PlayerEntity) {
          event.addCapability(EXTEND_INVENTORY, new CapExtendInvProvider());
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 7 ms
	
	Decompiled with FernFlower.
*/