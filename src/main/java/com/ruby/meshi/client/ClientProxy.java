 package com.ruby.meshi.client;
 
 import java.util.stream.Stream;

import com.ruby.meshi.block.Indlight;
import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.block.tileentity.BambooPotTileEntity;
import com.ruby.meshi.block.tileentity.CardboardTileEntity;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
import com.ruby.meshi.block.tileentity.HearthTileEntity;
import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
import com.ruby.meshi.block.tileentity.SlideDoorTileEntity;
import com.ruby.meshi.block.tileentity.WallShelfTileEntity;
import com.ruby.meshi.client.renderer.BambooPotRender;
import com.ruby.meshi.client.renderer.CardboardRender;
import com.ruby.meshi.client.renderer.CollectorPressurePlateItemRender;
import com.ruby.meshi.client.renderer.HearthRender;
import com.ruby.meshi.client.renderer.MillstoneRender;
import com.ruby.meshi.client.renderer.MiniatureRender;
import com.ruby.meshi.client.renderer.ScarecrowRender;
import com.ruby.meshi.client.renderer.ShurikenRender;
import com.ruby.meshi.client.renderer.SlideDoorRender;
import com.ruby.meshi.client.renderer.WallShelfItemRender;
import com.ruby.meshi.entity.ScarecrowEntity;
import com.ruby.meshi.entity.ShurikenEntity;

import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.MOD,
    value = {Dist.CLIENT}
 )
 public class ClientProxy {
    @SubscribeEvent
    public static void colorEvent(Block e) {
       e.getBlockColors().func_186722_a((s, r, p, l) -> {
          return ((Indlight)s.func_177230_c()).getColorCode();      }, SakuraBlocks.INDLIGHT);
    }
 
    @SubscribeEvent
    public static void colorEvent(Item e) {
       Stream.of(SakuraBlocks.INDLIGHT).forEach((l) -> {         e.getItemColors().func_199877_a((stack, i) -> {            return ((Indlight)l).getColorCode();         }, new IItemProvider[]{l});      });
    }
 
 
    public static void renderRegister() {
       ClientRegistry.bindTileEntitySpecialRenderer(WallShelfTileEntity.class, new WallShelfItemRender());
       ClientRegistry.bindTileEntitySpecialRenderer(CollectorPressurePlateTileEntity.class, new CollectorPressurePlateItemRender());
       ClientRegistry.bindTileEntitySpecialRenderer(BambooPotTileEntity.class, new BambooPotRender());
       ClientRegistry.bindTileEntitySpecialRenderer(MillstoneTileEntity.class, new MillstoneRender());
       ClientRegistry.bindTileEntitySpecialRenderer(HearthTileEntity.class, new HearthRender());
       ClientRegistry.bindTileEntitySpecialRenderer(CardboardTileEntity.class, new CardboardRender());
       ClientRegistry.bindTileEntitySpecialRenderer(SlideDoorTileEntity.class, new SlideDoorRender());
       ClientRegistry.bindTileEntitySpecialRenderer(MiniatureTileEntity.class, new MiniatureRender());
       RenderingRegistry.registerEntityRenderingHandler(ShurikenEntity.class, ShurikenRender::new);
       RenderingRegistry.registerEntityRenderingHandler(ScarecrowEntity.class, ScarecrowRender::new);
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/