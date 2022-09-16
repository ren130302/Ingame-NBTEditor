package com.ren130302.meshi;

import com.ren130302.lib.ModUtils;
import com.ren130302.meshi.define.Blocks;
import com.ren130302.meshi.define.Enchantments;
import com.ren130302.meshi.define.Items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(BambooMod.MODID)
@Mod.EventBusSubscriber(modid = BambooMod.MODID)
public class BambooMod {

    public static final String MODID = "meshi";

    // public static final PacketDispatcher PIPELINE = new PacketDispatcher();
    public static BMConfig CONFIG;

    public static final ModUtils UTILS = new ModUtils(MODID);

    public static final DeferredRegister<Item> ITEMS = BambooMod.UTILS.createDeferredRegister(ForgeRegistries.ITEMS);
    public static final DeferredRegister<Block> BLOCKS = BambooMod.UTILS.createDeferredRegister(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = BambooMod.UTILS
	    .createDeferredRegister(ForgeRegistries.ENCHANTMENTS);

    public BambooMod() {
	IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
	modBusEvent.addListener(BambooMod::modConfig);
	modBusEvent.addListener(BambooMod::onCommonSetup);
	final ModLoadingContext modLoadingContext = ModLoadingContext.get();
	modLoadingContext.registerConfig(ModConfig.Type.COMMON, BMConfig.Holder.SPEC);
	MinecraftForge.EVENT_BUS.register(this);
	Blocks.init();
	Items.init();
	Enchantments.init();
	BLOCKS.register(modBusEvent);
	ITEMS.register(modBusEvent);
	ENCHANTMENTS.register(modBusEvent);
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