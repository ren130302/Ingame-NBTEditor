package com.mcf.davidee.nbtedit.packets;

import java.util.function.Supplier;

import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTHelper;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent.Context;

public class TileRequestPacket {

	private BlockPos pos;

	public TileRequestPacket() {
	}

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
		final ServerPlayer player = context.getSender();

		context.enqueueWork(() -> {
			final ServerLevel level = player.getLevel();
			final BlockEntity blockEntity = level.getBlockEntity(msg.pos);
			final NBTTarget target = NBTTarget.of(msg.pos);

			NBTEditMessage.nbtRequest(target);
			NBTHelper.assertExsistTarget(target, blockEntity);

			CompoundTag tag = blockEntity.saveWithFullMetadata();
			PacketHandler.sendToPlayer(new TileNBTPacket(msg.pos, tag), player);
		});
	}
}
