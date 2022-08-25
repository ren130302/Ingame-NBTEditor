package com.mcf.davidee.nbtedit;

import com.mcf.davidee.nbtedit.nbt.NBTTarget;

import net.minecraft.world.entity.player.Player;

class NBTEditException extends Exception {

	public NBTEditException(String s) {
		NBTEditMessage.failed(s);
	}

	public static NBTEditException executeCommandsOnServeSide() {
		return new NBTEditException("Execute commands on the serve side");
	}

	public static NBTEditException hitResultIsNone() {
		return new NBTEditException("No tile or entity selected");
	}

	public static NBTEditException invalidNBTFormat(NBTTarget target) {
		return new NBTEditException("Invalid NBT format for " + target);
	}

	public static NBTEditException updateSelectTarget(String s, NBTTarget target) {
		return new NBTEditException(s + target);
	}

	public static NBTEditException notExistsTarget(NBTTarget target) {
		return new NBTEditException("There is no " + target);
	}

	public static NBTEditException playerHasNotPermission() {
		return new NBTEditException("You have not permission");
	}

	public static NBTEditException useOtherPlayer(Player player, Player other) {
		return new NBTEditException(player.getName() + " tried to use NBTEdit on another player, "
				+ other.getName().getString());
	}

	public static NBTEditException targetHasNoTags(NBTTarget target) {
		return new NBTEditException("NBTTarget has no tags");
	}
}
