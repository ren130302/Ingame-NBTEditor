package com.mcf.davidee.nbtedit;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.mcf.davidee.nbtedit.nbt.NBTTarget;
import com.mcf.davidee.nbtedit.nbt.NBTTree;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class NBTEditMessage {
	private static final Logger logger = (Logger) LogManager.getLogger(NBTEdit.class);

	public NBTEditMessage() {

		// Set up our file logging.
		PatternLayout layout = PatternLayout.newBuilder().withPattern("[%d{MM-dd HH:mm:ss}] [%level]: %msg%n").build();
		FileAppender appender = FileAppender.newBuilder().withFileName("logs/NBTEdit.log")
				.setName("NBTEdit File Appender").setLayout(layout).setIgnoreExceptions(false).build();
		appender.start();
		logger.addAppender(appender);
		logger.setAdditive(false); // Sets our logger to not show up in console.
		logger.setLevel(Level.ALL);
	}

	public static void failed(String message, @Nonnull NBTTarget target) {
		failed(message + target.toString());
	}

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
		System.err.println(msg);
	}

	public static void trace(String msg) {
		logger.trace(msg);
		System.out.println(msg);
	}

	public static void fine(String msg) {
		message(msg, ChatFormatting.WHITE);

	}

	public static void failed(String msg) {
		message(msg, ChatFormatting.RED);

	}

	private static void message(String msg, ChatFormatting color) {
		Minecraft minecraft = Minecraft.getInstance();
		Style style = Style.EMPTY.withColor(color);
		minecraft.player.sendSystemMessage(Component.literal(msg).setStyle(style));
	}
}
