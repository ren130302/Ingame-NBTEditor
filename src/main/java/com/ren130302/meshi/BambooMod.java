package com.ren130302.meshi;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.define.Defination;

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

    public static final String MODID = "meshi";

    // public static final PacketDispatcher PIPELINE = new PacketDispatcher();
    public static BMConfig CONFIG;
    public static final RegisterUtils REGISTER = new RegisterUtils(MODID);

    public BambooMod() {
	IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
	modBusEvent.addListener(BambooMod::modConfig);
	modBusEvent.addListener(BambooMod::onCommonSetup);
	final ModLoadingContext modLoadingContext = ModLoadingContext.get();
	modLoadingContext.registerConfig(ModConfig.Type.COMMON, BMConfig.Holder.SPEC);
	MinecraftForge.EVENT_BUS.register(this);
	new Defination();
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