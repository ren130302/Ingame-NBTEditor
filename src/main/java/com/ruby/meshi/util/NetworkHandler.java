 package com.ruby.meshi.util;
 
 import com.ruby.meshi.util.packet.ContainerHandler;
 import com.ruby.meshi.util.packet.NBTSyncHandler;
 import java.util.function.BiConsumer;
 import java.util.function.Function;
 import java.util.function.Predicate;
 import java.util.function.Supplier;
 import net.minecraft.entity.player.ServerPlayerEntity;import net.minecraft.inventory.container.ContainerType;import net.minecraft.nbt.INBT;import net.minecraft.util.Direction;import net.minecraft.util.ResourceLocation;import net.minecraftforge.fml.network.PacketDistributor;import net.minecraftforge.fml.network.NetworkRegistry.ChannelBuilder;import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;import net.minecraftforge.fml.network.simple.SimpleChannel;
 public class NetworkHandler {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel HANDLER;
 
    static {
       ChannelBuilder var10000 = ChannelBuilder.named(new ResourceLocation("meshi", "main_channel"));
       String var10001 = PROTOCOL_VERSION;         return PROTOCOL_VERSION;      }).simpleChannel();      PROTOCOL_VERSION.getClass();
    }
       var10000 = var10000.clientAcceptedVersions(var10001::equals);      var10001 = PROTOCOL_VERSION;      PROTOCOL_VERSION.getClass();
       HANDLER = var10000.serverAcceptedVersions(var10001::equals).networkProtocolVersion(() -> {
 
 
 
    public static void register() {
       int disc = 0;      int var1 = disc + 1;
       HANDLER.registerMessage(disc, ContainerHandler.class, ContainerHandler::encode, ContainerHandler::decode, ContainerHandler::handle);
       HANDLER.registerMessage(var1++, NBTSyncHandler.class, NBTSyncHandler::encode, NBTSyncHandler::decode, NBTSyncHandler::handle);
    }
 
    public static <MSG> void send(PacketTarget target, MSG message) {
       HANDLER.send(target, message);
    }
 
    public static void openServerContainer(ContainerType<?> type) {
       HANDLER.sendToServer(new ContainerHandler(type.getRegistryName()));
    }
 
    public static void sendExtendInvCap(ServerPlayerEntity player) {
       INBT nbt = CapabilityExtendInventory.EXTEND_INVENTORY.writeNBT(player.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY).orElse(CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance()), (Direction)null);
       HANDLER.send(PacketDistributor.PLAYER.with(() -> {         return player;      }), new NBTSyncHandler(nbt));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/