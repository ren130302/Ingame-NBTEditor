 package com.ruby.meshi.client.paticle;
 
 import java.util.Random;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
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
 public class WindManager {
    private static Direction windDir;
    private static int prevWindAngle;
    private static int windAngle;
    private static float prevWindPower;
    private static float windPower;
    private static double motionX;
    private static double motionY;
    private static double motionZ;
    private static int coolTimer;
    private static int chengeTime;
    private static int maxChangeAngle;
    private static int minChangeAngle;
    private static float maxWindPow;
    private static int updateRate;
 
    static {
       windDir = Direction.NORTH;
       windAngle = (int)(360.0D * Math.random());
       maxChangeAngle = 180;
       minChangeAngle = 40;
       maxWindPow = 0.04F;
       updateRate = 100;
 
       chengeTime = 1800 / updateRate;
    }
 
 
    @SubscribeEvent
    public static void tick(PlayerTickEvent e) {
       if (e.side == LogicalSide.CLIENT && e.phase == Phase.START) {
 
 
          if (e.player.field_70170_p.func_82737_E() % (long)updateRate == 0L) {
             Random rand = e.player.field_70170_p.field_73012_v;
             prevWindAngle = windAngle;
             prevWindPower = windPower;
             if (--coolTimer < 0) {
 
                windAngle += getRandomChange(rand, maxChangeAngle) % 360;
                windPower = maxWindPow * rand.nextFloat();
                coolTimer = chengeTime + rand.nextInt(chengeTime);
 
             } else {
                if (rand.nextFloat() < 0.3F) {
                   windAngle += getRandomChange(rand, minChangeAngle) % 360;
                }
 
                windPower += getRandomChange(rand, maxWindPow) * 0.2F;
 
 
 
             }
          }
 
          calcMotion((float)(e.player.field_70170_p.func_82737_E() % (long)updateRate) / (float)updateRate);
       }
 
    }
 
 
    private static int getRandomChange(Random rand, int i) {
       return rand.nextInt(i) - rand.nextInt(i);
    }
 
    private static float getRandomChange(Random rand, float f) {
       return f * rand.nextFloat() - f * rand.nextFloat();
    }
 
    private static void calcMotion(float partialTicks) {
       float pow = MathHelper.func_219799_g(partialTicks, prevWindPower, windPower);
       float angle = MathHelper.func_219799_g(partialTicks, (float)prevWindAngle, (float)windAngle);
       double rad = Math.toRadians((double)angle);
       motionZ = (double)(pow * MathHelper.func_76134_b((float)rad));
       motionX = (double)(pow * -MathHelper.func_76126_a((float)rad));
       windDir = Direction.func_176733_a((double)angle);
    }
 
 
    public static void setWind(int angle, float power) {
       prevWindAngle = windAngle;
       windAngle = angle;
       prevWindPower = windPower;
       windPower = power;
    }
 
    public static boolean isStrongWind() {
       return prevWindPower > 0.025F;
    }
 
    public static float getWindPow() {
       return prevWindPower;
    }
 
    public static Direction getWindDir() {
       return windDir;
    }
 
    public static double getMotionX() {
       return motionX;
    }
 
    public static double getMotionY() {
       return motionY;
    }
 
    public static double getMotionZ() {
       return motionZ;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 21 ms
	
	Decompiled with FernFlower.
*/