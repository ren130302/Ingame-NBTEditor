 package com.ruby.meshi.block.tileentity;
 
 import java.util.WeakHashMap;
import java.util.function.Predicate;

import com.ruby.meshi.init.HiganTileEntityType;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.monster.IMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class ManekiNekoTileEntity extends TileEntity {
    static final WeakHashMap<TileEntity, Predicate<BlockPos>> map = new WeakHashMap();
    int range = 4;
 
    public ManekiNekoTileEntity() {
       super(HiganTileEntityType.MANEKINEKO);
    }
 
    public static boolean isEntityDeny(BlockPos pos) {
       return map.values().stream().anyMatch((p) -> {
          return p.test(pos);
       });
    }
    public void onLoad() {
       super.onLoad();
       map.put(this, (pos) -> {         return Math.abs(this.func_174877_v().func_177958_n() - pos.func_177958_n() >> 4) <= this.range && Math.abs(this.func_174877_v().func_177952_p() - pos.func_177952_p() >> 4) <= this.range;      });
    }
 
 
    public void onChunkUnloaded() {
       super.onChunkUnloaded();
       map.remove(this);
    }
 
 
    public void func_145843_s() {
       super.func_145843_s();
       map.remove(this);
    }
 
    @SubscribeEvent
    public static void entitySpawn(CheckSpawn event) {
       if (!event.isSpawner() && event.getEntity() instanceof IMob) {
 
          BlockPos entityPos = event.getEntity().func_180425_c();
          if (isEntityDeny(entityPos)) {
             event.setResult(Result.DENY);
          }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 12 ms
	
	Decompiled with FernFlower.
*/