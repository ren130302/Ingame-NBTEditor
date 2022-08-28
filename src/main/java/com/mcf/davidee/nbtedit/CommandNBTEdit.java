package com.mcf.davidee.nbtedit;

import com.mcf.davidee.nbtedit.packets.EntityRequestPacket;
import com.mcf.davidee.nbtedit.packets.MouseOverPacket;
import com.mcf.davidee.nbtedit.packets.TileRequestPacket;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class CommandNBTEdit {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("nbtedit").requires(command -> checkPermission(command.getPlayer()))
				.executes(command -> check(command.getSource()))
				.then(Commands.argument("pos", BlockPosArgument.blockPos()).executes(
						command -> check(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"))))
				.then(Commands.argument("entityId", EntityArgument.entity())
						.executes(command -> check(command.getSource(), EntityArgument.getEntity(command, "entityId"))))
				.then(Commands.literal("me")
						.executes(command -> check(command.getSource(), command.getSource().getPlayer()))));
	}

	public static int check(CommandSourceStack source, Entity entity) throws CommandSyntaxException {
		return check(source, () -> new EntityRequestPacket(entity.getId()));
	}

	public static int check(CommandSourceStack source, BlockPos blockPos) throws CommandSyntaxException {
		return check(source, () -> new TileRequestPacket(blockPos));
	}

	public static int check(CommandSourceStack source) throws CommandSyntaxException {
		return check(source, () -> NBTEdit.PIPELINE.sendToServer(new MouseOverPacket()));
	}

	public static final SimpleCommandExceptionType NO_PERMISSION = new SimpleCommandExceptionType(
			Component.literal("You can not use because have no permission."));

	public static boolean checkPermission(Player player) {
		return NBTEditConfig.opOnly ? player.hasPermissions(Commands.LEVEL_OWNERS) : player.isCreative();
	}

	private static int check(CommandSourceStack source, Runnable runnable) throws CommandSyntaxException {
		try {
			if (checkPermission(source.getPlayer())) {
				System.out.println("permission ok");
				try {
					System.out.println("try run command...");
					// source.getServer().executeIfPossible(runnable);
					runnable.run();
					return source.getPlayer().getId();
				} catch (Exception e) {
					System.out.println("catched exception");
					e.printStackTrace();
					return -1;
				}
			}
			System.out.println("permission error");
			throw NO_PERMISSION.create();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
