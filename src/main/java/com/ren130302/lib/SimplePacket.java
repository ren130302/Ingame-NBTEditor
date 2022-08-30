package com.ren130302.lib;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public interface SimplePacket<MSG> {

	public void encode(MSG msg, FriendlyByteBuf buf);

	public MSG decode(FriendlyByteBuf buf);

	public void handle(MSG msg, Supplier<Context> sup);
}
