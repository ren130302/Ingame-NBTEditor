 package com.ruby.meshi.util;
 
 import com.ruby.meshi.util.CapabilityExtendInventory.1;
 import com.ruby.meshi.util.CapabilityExtendInventory.ExtendInventory;
 import java.util.concurrent.Callable;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.nbt.INBT;
 import net.minecraft.util.Direction;
 import net.minecraftforge.common.capabilities.Capability;
 import net.minecraftforge.common.capabilities.CapabilityInject;
 import net.minecraftforge.common.capabilities.CapabilityManager;
 import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
 import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 
 
 
 
 
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class CapabilityExtendInventory {
    @CapabilityInject(ExtendInventory.class)
    public static Capability<ExtendInventory> EXTEND_INVENTORY = null;
 
    public void register() {
       CapabilityManager.INSTANCE.register(ExtendInventory.class, new 1(this), () -> {
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
          return new ExtendInventory();      });
    }
 
    @SubscribeEvent
    public static void playerClone(Clone event) {
       if (event.isWasDeath()) {
          INBT nbt = EXTEND_INVENTORY.writeNBT(getInventory(event.getOriginal()), (Direction)null);
          EXTEND_INVENTORY.readNBT(getInventory(event.getPlayer()), (Direction)null, nbt);
       }
    }
 
    @SubscribeEvent
    public static void palyerJoin(PlayerLoggedInEvent event) {
       if (!event.getEntity().func_130014_f_().field_72995_K && event.getEntity() instanceof PlayerEntity) {
 
          NetworkHandler.sendExtendInvCap((ServerPlayerEntity)event.getEntity());
       }
 
    }
 
    public static ExtendInventory getInventory(PlayerEntity player) {
       return (ExtendInventory)player.getCapability(EXTEND_INVENTORY).orElse(EXTEND_INVENTORY.getDefaultInstance());
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 12 ms
	
	Decompiled with FernFlower.
*/