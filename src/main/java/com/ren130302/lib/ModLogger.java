package com.ren130302.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class ModLogger {
  private final Logger logger;

  public static Logger logger(Class<?> clazz) {
    return new ModLogger(clazz).logger;
  }

  private ModLogger(Class<?> clazz) {
    this.logger = (Logger) LogManager.getLogger(clazz);
    final PatternLayout layout =
        PatternLayout.newBuilder().withPattern("[%d{MM-dd HH:mm:ss}] [%level]: %msg%n").build();
    final FileAppender appender =
        FileAppender.newBuilder().withFileName("logs/" + this.logger.getName() + ".log")
            .setName(this.logger.getName() + "File Appender").setLayout(layout)
            .setIgnoreExceptions(false).build();
    appender.start();
    this.logger.addAppender(appender);
    this.logger.setAdditive(false); // Sets our logger to not show up in console.
    this.logger.setLevel(Level.ALL);
  }

  public static void msg(String msg, ChatFormatting color) {
    final Minecraft minecraft = Minecraft.getInstance();
    final Style style = Style.EMPTY.withColor(color);
    minecraft.player.sendSystemMessage(Component.literal(msg).setStyle(style));
  }
}
