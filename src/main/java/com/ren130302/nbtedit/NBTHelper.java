package com.ren130302.nbtedit;

import java.util.Objects;
import java.util.function.BiConsumer;

import com.ren130302.nbtedit.nbt.NBTTarget;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class NBTHelper {
	public static void assertSender(Player player) {
		try {
			if (Objects.isNull(player)) {
				throw NBTEditException.executeCommandsOnServeSide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void assertPermission(Player player) {
		try {
			final boolean hasPermission = CommandNBTEdit.checkPermission(player);

			if (!hasPermission) {
				throw NBTEditException.playerHasNotPermission();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> void assertExsistTarget(NBTTarget target, T t) {
		try {
			if (Objects.isNull(t)) {
				throw NBTEditException.notExistsTarget(target);
			}
		} catch (NBTEditException e) {
			e.printStackTrace();
		}
	}

	public static <T> void readFromNBT(CompoundTag tag, NBTTarget target, T t, BiConsumer<CompoundTag, T> consumer) {
		try {
			try {
				consumer.accept(tag, t);
			} catch (Exception e) {
				throw NBTEditException.invalidNBTFormat(target);
			}
		} catch (NBTEditException e) {
			e.printStackTrace();
		}
	}

	public static void assertEditOtherPlayers(ServerPlayer player, Entity entity) {
		try {
			if (entity instanceof Player && entity != player && !NBTEditConfig.editOtherPlayers) {
				final Player other = (Player) entity;
				throw NBTEditException.useOtherPlayer(player, other);
			}
		} catch (NBTEditException e) {
			e.printStackTrace();
		}

	}

	public static void hitResultIsNone() {
		try {
			throw NBTEditException.hitResultIsNone();
		} catch (NBTEditException e) {
			e.printStackTrace();
		}
	}

	public static void assertTag(NBTTarget target) {
		try {
			if (!target.hasTag()) {
				throw NBTEditException.targetHasNoTags(target);
			}
		} catch (NBTEditException e) {
			e.printStackTrace();
		}
	}
}
