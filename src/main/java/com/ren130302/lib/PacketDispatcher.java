package com.ren130302.lib;

import java.util.function.Predicate;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketDispatcher {
	private final SimpleChannel channel;

	private PacketDispatcher(String name, String path, String protocolVersion, Predicate<String> clientAcceptedVersions,
			Predicate<String> serverAcceptedVersions) {
		this.channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(name, path), () -> protocolVersion,
				clientAcceptedVersions, serverAcceptedVersions);
	}

	public static PacketDispatcher newSimpleChannel(String name, String path, String protocolVersion,
			Predicate<String> clientAcceptedVersions, Predicate<String> serverAcceptedVersions) {
		return new PacketDispatcher(name, path, protocolVersion, clientAcceptedVersions, serverAcceptedVersions);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends SimplePacket> void registerMessage(int index, T messageType) {
		this.registerMessage(index, messageType.getClass(), messageType);
	}

	public <T> void registerMessage(int index, Class<T> clazz, SimplePacket<T> messageType) {
		this.channel.registerMessage(index, clazz, messageType::encode, messageType::decode, messageType::handle);
	}
}
