package com.mcf.davidee.nbtedit.packets;

import java.util.function.Supplier;

import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTHelper;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;
import com.mcf.davidee.nbtedit.screen.EditNBTTreeScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;

public class EntityNBTPacket {
	/**
	 * The id of the entity being edited.
	 */
	private int entityID;
	/**
	 * The nbt data of the entity.
	 */
	private CompoundTag tag;

	public EntityNBTPacket(int entityID, CompoundTag tag) {
		this.entityID = entityID;
		this.tag = tag;
	}

	public static void encode(EntityNBTPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.entityID);
		buf.writeNbt(msg.tag);
	}

	public static EntityNBTPacket decode(FriendlyByteBuf buf) {
		final int entityId = buf.readInt();
		final CompoundTag tag = buf.readNbt();
		return new EntityNBTPacket(entityId, tag);
	}

	public static void handle(EntityNBTPacket msg, Supplier<Context> sup) {
		final Context context = sup.get();
		final ServerPlayer player = context.getSender();

		NBTHelper.assertSender(player);
		NBTHelper.assertPermission(player);

		final Entity entity = player.getLevel().getEntity(msg.entityID);
		final NBTTarget target = NBTTarget.of(entity);

		NBTHelper.assertExsistTarget(target, entity);

		context.enqueueWork(() -> {
			if (context.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				NBTHelper.readFromNBT(msg.tag, target, entity, (t, u) -> u.load(t));

				if (entity instanceof ServerPlayer) {
					ServerPlayer targetPlayer = (ServerPlayer) entity;
					targetPlayer.connection.send(new ClientboundSetHealthPacket(targetPlayer.getHealth(),
							targetPlayer.getFoodData().getFoodLevel(),
							targetPlayer.getFoodData().getSaturationLevel()));
					targetPlayer.connection.send(new ClientboundSetExperiencePacket(targetPlayer.experienceProgress,
							targetPlayer.totalExperience, targetPlayer.experienceLevel));
					// targetPlayer.sendPlayerAbilities();
					// targetPlayer.setPositionAndUpdate(targetPlayer.posX, targetPlayer.posY,
					// targetPlayer.posZ);
				}
				NBTEditMessage.saved(msg.tag);
			} else {
				Minecraft.getInstance().setScreen(new EditNBTTreeScreen(target));
			}
		});
	}
}
