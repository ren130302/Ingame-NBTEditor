package com.ren130302.nbtedit;

import java.io.File;
import java.util.Comparator;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ren130302.lib.Draw;
import com.ren130302.nbtedit.nbt.NamedNBT;
import com.ren130302.nbtedit.nbt.Node;
import com.ren130302.nbtedit.packets.PacketDispatcher;

import net.minecraft.client.Camera;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(NBTEdit.MODID)
@Mod.EventBusSubscriber(modid = NBTEdit.MODID)
public class NBTEdit {

	public static final String MODID = "nbtedit";

	public static final Comparator<Node<NamedNBT>> SORTER = new NBTComparator();
	public static final PacketDispatcher PIPELINE = new PacketDispatcher();

	public static final File NBT_DAT = new File(FMLPaths.GAMEDIR.get().toFile(), "saves/NBTEdit.dat");
	public static final KeyMapping EDITNBT_KEY = new KeyMapping("NBTEdit Shortcut", InputConstants.UNKNOWN.getValue(),
			"key.categories.misc");

	public static NBTEditConfig CONFIG;

	public NBTEdit() {
		IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
		modBusEvent.addListener(NBTEdit::modConfig);
		modBusEvent.addListener(NBTEdit::registryKeyMapping);
		modBusEvent.addListener(NBTEdit::onCommonSetup);
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		modLoadingContext.registerConfig(ModConfig.Type.COMMON, NBTEditConfig.Holder.SPEC, "nbtedit.toml");
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void onCommonSetup(final FMLCommonSetupEvent event) {
		PIPELINE.init();
	}

	@SubscribeEvent
	public static void onServerStarting(final ServerStartingEvent event) {
		final MinecraftServer server = event.getServer();
		final Commands serverCommandManager = server.getCommands();
		CommandNBTEdit.register(serverCommandManager.getDispatcher());
		NBTEditMessage.serverStart();
	}

	public static void modConfig(final ModConfigEvent event) {
		final ModConfig config = event.getConfig();
		NBTEditConfig.init(config);
	}

	public static void registryKeyMapping(final RegisterKeyMappingsEvent event) {
		event.register(EDITNBT_KEY);
	}

	// RenderLevelLastEvent
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void renderWorldLast(final RenderHighlightEvent event) {
		final Minecraft minecraft = Minecraft.getInstance();
		final LocalPlayer player = minecraft.player;
		final HitResult hitResult = minecraft.hitResult;
		final Camera camera = event.getCamera();
		final float partialTick = event.getPartialTick();
		final PoseStack poseStack = event.getPoseStack();
		final MultiBufferSource source = event.getMultiBufferSource();
		final Level level = player.level;
		final VertexConsumer vertexConsumer = source.getBuffer(RenderType.lines());
		// LevelRenderer
		switch (hitResult.getType()) {
		case BLOCK:
			final BlockHitResult blockHitResult = ((BlockHitResult) hitResult);
			final BlockPos blockPos = blockHitResult.getBlockPos();
			Draw.outline(poseStack, vertexConsumer, level, camera, blockPos, partialTick);
			break;
		case ENTITY:
			final EntityHitResult entityHitResult = ((EntityHitResult) hitResult);
			final Entity entity = entityHitResult.getEntity();
			Draw.outline(poseStack, vertexConsumer, level, camera, entity, partialTick);
			break;
		default:
			return;
		}

		// final Screen curScreen = minecraft.screen;
		// if (curScreen instanceof EditNBTTreeScreen) {
		// EditNBTTreeScreen screen = (EditNBTTreeScreen) curScreen;
		// NBTTarget target = screen.target();
		//
		// if (target.hasEntity()) {
		// Entity entity = target.entity();
		// aabb = entity.getBoundingBox();
		// } else if (target.hasPos()) {
		// Level world = minecraft.player.getLevel();
		// BlockPos pos = target.pos();
		// Block block = world.getBlockState(pos).getBlock();
		// if (block != null) {
		// aabb = new AABB(pos);
		// }
		// }
		// // LevelRenderer
		//
		// try {
		// drawBoundingBox(poseStack, partialTick, aabb);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
	}
}