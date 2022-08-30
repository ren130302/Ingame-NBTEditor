package com.ren130302.nbtedit;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.core.Logger;

import com.ren130302.lib.ModLogger;
import com.ren130302.nbtedit.nbt.NBTTarget;
import com.ren130302.nbtedit.nbt.NBTTree;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class NBTEditMessage {
	private static final Logger logger = ModLogger.logger(NBTEdit.class);

	public static void nbtRequest(@Nonnull NBTTarget target) {
		trace("NBTRequest - " + target.toString());
	}

	public static void saved(CompoundTag tag) {
		trace("Save Succsess - Your changes have been saved");
		tag(tag);
		fine("Your changes have been saved");
	}

	public static void tag(CompoundTag tag) {
		trace("Compound Tag - Output tag detail ");
		trace(NBTTree.of(tag).toString());
	}

	public static void serverStart() {
		trace("Server Starting -- Added \"/nbtedit\" command");
	}

	public static void warn(String msg) {
		logger.warn(msg);
	}

	public static void trace(String msg) {
		logger.trace(msg);
	}

	public static void fine(String msg) {
		message(msg, ChatFormatting.WHITE);
	}

	public static void failed(String msg) {
		message(msg, ChatFormatting.RED);
	}

	private static void message(String msg, ChatFormatting color) {
		final Minecraft minecraft = Minecraft.getInstance();
		final Style style = Style.EMPTY.withColor(color);
		minecraft.player.sendSystemMessage(Component.literal(msg).setStyle(style));
	}
}
