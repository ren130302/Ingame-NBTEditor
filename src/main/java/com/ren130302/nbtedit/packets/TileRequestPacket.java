package com.ren130302.nbtedit.packets;

import java.util.function.Supplier;

import com.ren130302.nbtedit.NBTEdit;
import com.ren130302.nbtedit.NBTEditMessage;
import com.ren130302.nbtedit.NBTHelper;
import com.ren130302.nbtedit.nbt.NBTTarget;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent.Context;

public class TileRequestPacket {

	private final BlockPos pos;

	public TileRequestPacket(BlockPos pos) {
		this.pos = pos;
	}

	public static void encode(TileRequestPacket msg, FriendlyByteBuf buf) {
		buf.writeBlockPos(msg.pos);
	}

	public static TileRequestPacket decode(FriendlyByteBuf buf) {
		final BlockPos blockPos = buf.readBlockPos();
		return new TileRequestPacket(blockPos);
	}

	public static void handle(TileRequestPacket msg, Supplier<Context> sup) {
		final Context context = sup.get();

		context.setPacketHandled(true);
		context.enqueueWork(() -> {
			final ServerPlayer player = context.getSender();

			NBTHelper.assertSender(player);
			NBTHelper.assertPermission(player);

			final ServerLevel level = player.getLevel();
			final BlockEntity blockEntity = level.getBlockEntity(msg.pos);
			final NBTTarget target = NBTTarget.of(msg.pos);

			NBTEditMessage.nbtRequest(target);
			NBTHelper.assertExsistTarget(target, blockEntity);

			final CompoundTag tag = blockEntity.serializeNBT();
			NBTEdit.PIPELINE.sendToPlayer(new TileNBTPacket(msg.pos, tag), player);
		});
	}
}
