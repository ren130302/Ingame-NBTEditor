 package com.ruby.meshi.item;
 
 import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
 
 public class ClimbingClaw extends Item implements Accessory {
    public ClimbingClaw(Properties properties) {
       super(properties);
    }
 
 
    public void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
       if (player.field_70123_F) {
          player.func_213293_j(player.func_213322_ci().field_72450_a, 0.2D, player.func_213322_ci().field_72449_c);
       } else if (this.isCollideBlock(player)) {
          player.field_70143_R = 0.0F;
          float f = 0.15F;
          double d0 = MathHelper.func_151237_a(player.func_213322_ci().func_82615_a(), (double)(-f), (double)f);
          double d1 = MathHelper.func_151237_a(player.func_213322_ci().func_82616_c(), (double)(-f), (double)f);
          double d2 = Math.max(player.func_213322_ci().func_82617_b(), (double)(-f));
          if (d2 < 0.0D && player.func_70093_af()) {
             d2 = 0.0D;
          }
 
          player.func_213317_d(new Vec3d(d0, d2, d1));
       }
    }
 
    boolean isCollideBlock(PlayerEntity player) {
       World world = player.func_130014_f_();
       AxisAlignedBB bb = player.func_174813_aQ().func_72314_b(0.1D, 0.0D, 0.1D);
       int mX = MathHelper.func_76128_c(bb.field_72340_a);
       int mY = MathHelper.func_76128_c(bb.field_72338_b);
       int mZ = MathHelper.func_76128_c(bb.field_72339_c);
       for(int y2 = mY; (double)y2 < bb.field_72337_e; ++y2) {
          for(int x2 = mX; (double)x2 < bb.field_72336_d; ++x2) {
             for(int z2 = mZ; (double)z2 < bb.field_72334_f; ++z2) {
                BlockPos tmp = new BlockPos(x2, y2, z2);
                if (!world.func_175623_d(tmp) && world.func_204610_c(tmp).func_206888_e()) {
                   return true;
                }            }
          }
       }
 
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 21 ms
	
	Decompiled with FernFlower.
*/