package com.ren130302.bamboo;

import com.ren130302.lib.RegisterUtils;

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

@Mod(BambooMod.MODID)
@Mod.EventBusSubscriber(modid = BambooMod.MODID)
public class BambooMod {

    public static final String MODID = "bambooMod";

    // public static final PacketDispatcher PIPELINE = new PacketDispatcher();

    public static final RegisterUtils REGISTER = new RegisterUtils(MODID);

    public static BMConfig CONFIG;

    public BambooMod() {
	IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
	modBusEvent.addListener(BambooMod::modConfig);
	modBusEvent.addListener(BambooMod::onCommonSetup);
	final ModLoadingContext modLoadingContext = ModLoadingContext.get();
	modLoadingContext.registerConfig(ModConfig.Type.COMMON, BMConfig.Holder.SPEC, "bamboo.toml");
	MinecraftForge.EVENT_BUS.register(this);
	new BMDefination(modBusEvent);
    }

    public static void onCommonSetup(final FMLCommonSetupEvent event) {
	// PIPELINE.init();
    }

    @SubscribeEvent
    public static void onServerStarting(final ServerStartingEvent event) {

    }

    public static void modConfig(final ModConfigEvent event) {
	final ModConfig config = event.getConfig();
	BMConfig.init(config);
    }
}