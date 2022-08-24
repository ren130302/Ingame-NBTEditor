package com.mcf.davidee.nbtedit.packets;

import java.util.function.Supplier;

import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTHelper;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;
import com.mcf.davidee.nbtedit.screen.EditNBTTreeScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent.Context;

public class TileNBTPacket {
	/**
	 * The block of the tileEntity.
	 */
	private BlockPos pos;
	/**
	 * The nbt data of the tileEntity.
	 */
	private CompoundTag tag;

	public TileNBTPacket(BlockPos pos, CompoundTag tag) {
		this.pos = pos;
		this.tag = tag;
	}

	public static void encode(TileNBTPacket msg, FriendlyByteBuf buf) {
		buf.writeBlockPos(msg.pos);
		buf.writeNbt(msg.tag);

	}

	public static TileNBTPacket decode(FriendlyByteBuf buf) {
		final BlockPos pos = buf.readBlockPos();
		final CompoundTag tag = buf.readNbt();
		return new TileNBTPacket(pos, tag);
	}

	public static void handle(TileNBTPacket msg, Supplier<Context> sup) {
		final Context context = sup.get();

		context.setPacketHandled(true);
		context.enqueueWork(() -> {
			final ServerPlayer player = context.getSender();

			NBTHelper.assertSender(player);
			NBTHelper.assertPermission(player);

			final BlockEntity blockEntity = player.getLevel().getBlockEntity(msg.pos);
			final NBTTarget target = NBTTarget.of(msg.pos, msg.tag);

			NBTHelper.assertExsistTarget(target, blockEntity);

			if (context.getDirection().getOriginationSide() == LogicalSide.SERVER) {
				final ServerLevel level = player.getLevel();

				NBTHelper.readFromNBT(msg.tag, target, blockEntity, (t, u) -> u.load(t));

				blockEntity.setChanged();
				level.getChunk(msg.pos).markPosForPostprocessing(msg.pos);

				NBTEditMessage.saved(msg.tag);

			} else {
				Minecraft.getInstance().setScreen(new EditNBTTreeScreen(target));
			}
		});
	}
}
