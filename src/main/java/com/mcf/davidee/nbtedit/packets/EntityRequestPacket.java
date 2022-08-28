package com.mcf.davidee.nbtedit.packets;

import java.util.function.Supplier;

import com.mcf.davidee.nbtedit.NBTEdit;
import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTHelper;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent.Context;

public class EntityRequestPacket {
	/**
	 * The id of the entity being requested.
	 */
	private int entityID;

	public EntityRequestPacket(int entityID) {
		this.entityID = entityID;
	}

	public static void encode(EntityRequestPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.entityID);
	}

	public static EntityRequestPacket decode(FriendlyByteBuf buf) {
		final int entityID = buf.readInt();
		return new EntityRequestPacket(entityID);
	}

	public static void handle(EntityRequestPacket msg, Supplier<Context> sup) {
		final Context context = sup.get();

		context.setPacketHandled(true);
		context.enqueueWork(() -> {
			final ServerPlayer player = context.getSender();

			NBTHelper.assertSender(player);
			NBTHelper.assertPermission(player);

			final ServerLevel level = player.getLevel();
			final Entity entity = level.getEntity(msg.entityID);
			final NBTTarget target = NBTTarget.of(entity);

			NBTEditMessage.nbtRequest(target);
			NBTHelper.assertExsistTarget(target, entity);
			NBTHelper.assertEditOtherPlayers(player, entity);

			final CompoundTag tag = entity.serializeNBT();
			NBTEdit.PIPELINE.sendToPlayer(new EntityNBTPacket(msg.entityID, tag), player);
		});
	}
}
