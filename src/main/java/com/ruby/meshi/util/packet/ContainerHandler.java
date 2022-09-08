 package com.ruby.meshi.util.packet;
 
 import java.util.function.Supplier;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
 public class ContainerHandler {
    public final ResourceLocation loc;
 
    public ContainerHandler(ResourceLocation loc) {
       this.loc = loc;
    }
 
    public static void encode(ContainerHandler msg, PacketBuffer buf) {
       buf.func_192572_a(msg.loc);
    }
 
    public static ContainerHandler decode(PacketBuffer buf) {
       return new ContainerHandler(buf.func_192575_l());
    }
 
    public ResourceLocation getLocation() {
       return this.loc;
    }
 
    public static void handle(ContainerHandler message, Supplier<Context> ctx) {
       ((Context)ctx.get()).enqueueWork(() -> {
          ((Context)ctx.get()).getSender().func_213829_a(new SimpleNamedContainerProvider((windowId, inventory, player) -> {
             return getContainerType(message.getLocation()).create(windowId, inventory, (PacketBuffer)null);
          }, new TranslationTextComponent("", new Object[0])));
       });
       ((Context)ctx.get()).setPacketHandled(true);
    }
 
    private static ContainerType<?> getContainerType(ResourceLocation loc) {
       return (ContainerType)ForgeRegistries.CONTAINERS.getEntries().stream().filter((e) -> {
          return ((ResourceLocation)e.getKey()).equals(loc);
       }).findFirst().map((e) -> {
          return (ContainerType)e.getValue();
       }).orElse((Object)null);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 16 ms
	
	Decompiled with FernFlower.
*/