package com.ren130302.nbtedit.packets;

import java.util.function.Supplier;

import com.ren130302.nbtedit.NBTEdit;
import com.ren130302.nbtedit.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent.Context;

public class MouseOverPacket {

	public static void encode(MouseOverPacket msg, FriendlyByteBuf buf) {
	}

	public static MouseOverPacket decode(FriendlyByteBuf buf) {
		return new MouseOverPacket();
	}

	public static void handle(MouseOverPacket msg, Supplier<Context> sup) {
		final Context context = sup.get();

		context.setPacketHandled(true);
		context.enqueueWork(() -> {
			final Minecraft minecraft = Minecraft.getInstance();
			final HitResult hitResult = minecraft.hitResult;

			switch (hitResult.getType()) {
			case BLOCK:
				final BlockHitResult blockHitResult = ((BlockHitResult) hitResult);
				NBTEdit.PIPELINE.sendToServer(new TileRequestPacket(blockHitResult.getBlockPos()));
				break;
			case ENTITY:
				final EntityHitResult entityHitResult = ((EntityHitResult) hitResult);
				NBTEdit.PIPELINE.sendToServer(new EntityRequestPacket(entityHitResult.getEntity().getId()));
				break;
			default:
				NBTHelper.hitResultIsNone();
				break;
			}
		});
	}
}
