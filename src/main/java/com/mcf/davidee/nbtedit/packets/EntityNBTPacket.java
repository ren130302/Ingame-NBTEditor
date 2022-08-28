package com.mcf.davidee.nbtedit.packets;

import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTHelper;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;
import com.mcf.davidee.nbtedit.screen.EditNBTTreeScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent.Context;

public class EntityNBTPacket {
	/**
	 * The id of the entity being edited.
	 */
	protected final int entityID;
	/**
	 * The nbt data of the entity.
	 */
	protected final CompoundTag tag;

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

		context.setPacketHandled(true);
		context.enqueueWork(() -> {
			final Minecraft minecraft = Minecraft.getInstance();
			final Player player = Objects.nonNull(context.getSender()) ? context.getSender() : minecraft.player;
			final Level level = player.getLevel();
			final Entity entity = level.getEntity(msg.entityID);
			final NBTTarget target = NBTTarget.of(entity, msg.tag);

			NBTHelper.assertExsistTarget(target, entity);

			if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
				NBTHelper.readFromNBT(msg.tag, target, entity, (t, u) -> u.deserializeNBT(t));

				if (entity instanceof ServerPlayer) {
					ServerPlayer targetPlayer = (ServerPlayer) entity;
					targetPlayer.connection.send(new ClientboundSetHealthPacket(targetPlayer.getHealth(),
							targetPlayer.getFoodData().getFoodLevel(),
							targetPlayer.getFoodData().getSaturationLevel()));
					targetPlayer.connection.send(new ClientboundSetExperiencePacket(targetPlayer.experienceProgress,
							targetPlayer.totalExperience, targetPlayer.experienceLevel));
					targetPlayer.connection.send(new ClientboundPlayerAbilitiesPacket(targetPlayer.getAbilities()));
					targetPlayer.connection.send(new ClientboundPlayerPositionPacket(targetPlayer.getX(),
							targetPlayer.getY(), targetPlayer.getZ(), targetPlayer.getYRot(), targetPlayer.getXRot(),
							Collections.emptySet(), targetPlayer.getId(), false));
				}

				NBTEditMessage.saved(msg.tag);
			} else {
				Minecraft.getInstance().setScreen(new EditNBTTreeScreen(target));
			}
		});
	}
}
