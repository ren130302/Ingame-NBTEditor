package com.mcf.davidee.nbtedit.packets;

import com.mcf.davidee.nbtedit.NBTEdit;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
	private static final String PROTOCOL_VERSION = "1.0";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(NBTEdit.MODID, "main_channel"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	private PacketHandler() {
		int index = 0;
		CHANNEL.registerMessage(index++, TileRequestPacket.class, TileRequestPacket::encode, TileRequestPacket::decode,
				TileRequestPacket::handle);
		CHANNEL.registerMessage(index++, TileNBTPacket.class, TileNBTPacket::encode, TileNBTPacket::decode,
				TileNBTPacket::handle);
		CHANNEL.registerMessage(index++, TileNBTPacket.class, TileNBTPacket::encode, TileNBTPacket::decode,
				TileNBTPacket::handle);
		CHANNEL.registerMessage(index++, EntityRequestPacket.class, EntityRequestPacket::encode,
				EntityRequestPacket::decode, EntityRequestPacket::handle);
		CHANNEL.registerMessage(index++, EntityNBTPacket.class, EntityNBTPacket::encode, EntityNBTPacket::decode,
				EntityNBTPacket::handle);
		CHANNEL.registerMessage(index++, EntityNBTPacket.class, EntityNBTPacket::encode, EntityNBTPacket::decode,
				EntityNBTPacket::handle);
		CHANNEL.registerMessage(index++, MouseOverPacket.class, MouseOverPacket::encode, MouseOverPacket::decode,
				MouseOverPacket::handle);
	}

	public static <MSG> void sendToAll(MSG msg) {
		CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player) {
		CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
	}

	public static <MSG> void sendToDimension(MSG msg, ResourceKey<Level> level) {
		CHANNEL.send(PacketDistributor.DIMENSION.with(() -> level), msg);
	}

	public static <MSG> void sendToNear(MSG msg, TargetPoint point) {
		CHANNEL.send(PacketDistributor.NEAR.with(() -> point), msg);
	}

	public static <MSG> void sendToServer(MSG msg) {
		CHANNEL.send(PacketDistributor.SERVER.noArg(), msg);
	}
}
