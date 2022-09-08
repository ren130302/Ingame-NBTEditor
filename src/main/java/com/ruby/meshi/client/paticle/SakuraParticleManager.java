 package com.ruby.meshi.client.paticle;
 
 import java.util.Random;

import com.ruby.meshi.block.ExtraParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.FORGE,
    value = {Dist.CLIENT}
 )
 public class SakuraParticleManager {
    @SubscribeEvent
    public static void tick(PlayerTickEvent e) {
       if (e.side == LogicalSide.CLIENT && e.phase == Phase.START) {
 
 
          PlayerEntity player = e.player;
          World world = player.func_130014_f_();
          MutableBlockPos mutableblockpos = new MutableBlockPos();
 
          int posX = MathHelper.func_76128_c(player.field_70165_t);
          int posY = MathHelper.func_76128_c(player.field_70163_u);
          int posZ = MathHelper.func_76128_c(player.field_70161_v);
          for(int j = 0; j < 500; ++j) {
             animateTick(world, posX, posY, posZ, 32, 32, world.field_73012_v, mutableblockpos);
             animateTick(world, posX, posY, posZ, 64, 32, world.field_73012_v, mutableblockpos);
          }
       }
 
    }
 
 
    private static void animateTick(World world, int x, int y, int z, int xzOffset, int yOffset, Random rand, MutableBlockPos pos) {
       int i = x + rand.nextInt(xzOffset) - rand.nextInt(xzOffset);
       int j = y + rand.nextInt(yOffset) - rand.nextInt(yOffset);
       int k = z + rand.nextInt(xzOffset) - rand.nextInt(xzOffset);
       pos.func_181079_c(i, j, k);
       BlockState blockstate = world.func_180495_p(pos);
       if (blockstate.func_177230_c() instanceof ExtraParticle) {
          ((ExtraParticle)((ExtraParticle)blockstate.func_177230_c())).paticleTick(blockstate, world, pos, rand);
       }
    }
 
    @OnlyIn(Dist.CLIENT)
    public static void addEffect(Particle p) {
       if (p != null) {
          Minecraft.func_71410_x().field_71452_i.func_78873_a(p);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 25 ms
	
	Decompiled with FernFlower.
*/