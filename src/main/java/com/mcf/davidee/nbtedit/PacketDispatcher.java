package com.mcf.davidee.nbtedit;

import com.mcf.davidee.nbtedit.packets.EntityNBTPacket;
import com.mcf.davidee.nbtedit.packets.EntityRequestPacket;
import com.mcf.davidee.nbtedit.packets.MouseOverPacket;
import com.mcf.davidee.nbtedit.packets.TileNBTPacket;
import com.mcf.davidee.nbtedit.packets.TileRequestPacket;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketDispatcher {
	public static final String PROTOCOL_VERSION = "1.0";
	public final SimpleChannel channel;

	public PacketDispatcher() {
		this.channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(NBTEdit.MODID, "main_channel"),
				() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	}

	public void init() {
		System.out.println("registerMessage");
		int index = 0;
		this.channel.registerMessage(index++, TileRequestPacket.class, TileRequestPacket::encode,
				TileRequestPacket::decode, TileRequestPacket::handle);
		this.channel.registerMessage(index++, TileNBTPacket.class, TileNBTPacket::encode, TileNBTPacket::decode,
				TileNBTPacket::handle);
		this.channel.registerMessage(index++, TileNBTPacket.class, TileNBTPacket::encode, TileNBTPacket::decode,
				TileNBTPacket::handle);
		this.channel.registerMessage(index++, EntityRequestPacket.class, EntityRequestPacket::encode,
				EntityRequestPacket::decode, EntityRequestPacket::handle);
		this.channel.registerMessage(index++, EntityNBTPacket.class, EntityNBTPacket::encode, EntityNBTPacket::decode,
				EntityNBTPacket::handle);
		this.channel.registerMessage(index++, EntityNBTPacket.class, EntityNBTPacket::encode, EntityNBTPacket::decode,
				EntityNBTPacket::handle);
		this.channel.registerMessage(index++, MouseOverPacket.class, MouseOverPacket::encode, MouseOverPacket::decode,
				MouseOverPacket::handle);
	}

	public <MSG> void sendToAll(MSG msg) {
		this.channel.send(PacketDistributor.ALL.noArg(), msg);
	}

	public <MSG> void sendToPlayer(MSG msg, ServerPlayer player) {
		this.channel.send(PacketDistributor.PLAYER.with(() -> player), msg);
	}

	public <MSG> void sendToDimension(MSG msg, ResourceKey<Level> level) {
		this.channel.send(PacketDistributor.DIMENSION.with(() -> level), msg);
	}

	public <MSG> void sendToNear(MSG msg, TargetPoint point) {
		this.channel.send(PacketDistributor.NEAR.with(() -> point), msg);
	}

	public <MSG> void sendToServer(MSG msg) {
		//this.channel.send(PacketDistributor.SERVER.noArg(), msg);
		this.channel.sendToServer(msg);
	}
}
