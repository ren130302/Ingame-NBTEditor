 package com.ruby.meshi.util.packet;
 
 import java.util.function.Supplier;

import com.ruby.meshi.util.CapabilityExtendInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
 
 
 public class NBTSyncHandler {
    public final CompoundNBT nbt;
 
    public NBTSyncHandler(INBT nbt) {
       this.nbt = compNBT(nbt);
    }
 
    public static CompoundNBT compNBT(INBT nbt) {
       CompoundNBT tmp = new CompoundNBT();
       tmp.func_218657_a("sync", nbt);
       return tmp;
    }
 
    public static INBT decompNBT(CompoundNBT nbt) {
       return nbt.func_74781_a("sync");
    }
 
    public NBTSyncHandler(CompoundNBT nbt) {
       this.nbt = nbt;
    }
 
    public static void encode(NBTSyncHandler msg, PacketBuffer buf) {
       buf.func_150786_a(msg.nbt);
    }
 
    public static NBTSyncHandler decode(PacketBuffer buf) {
       return new NBTSyncHandler(buf.func_150793_b());
    }
 
    public CompoundNBT getNBT() {
       return this.nbt;
    }
 
    public static void handle(NBTSyncHandler message, Supplier<Context> ctx) {
       ((Context)ctx.get()).enqueueWork(() -> {
          CapabilityExtendInventory.EXTEND_INVENTORY.readNBT(Minecraft.func_71410_x().field_71439_g.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY).orElse(CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance()), (Direction)null, decompNBT(message.nbt));
       });
       ((Context)ctx.get()).setPacketHandled(true);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 14 ms
	
	Decompiled with FernFlower.
*/