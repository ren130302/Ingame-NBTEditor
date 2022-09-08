 package com.ruby.meshi.item;
 
 import java.util.List;
import java.util.Random;

import com.ruby.meshi.entity.ScarecrowEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Rotations;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemUseContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
 
 
 public class ScarecrowItem extends Item {
    public ScarecrowItem(Properties properties) {
       super(properties);
    }
 
 
    public ActionResultType func_195939_a(ItemUseContext context) {
       Direction direction = context.func_196000_l();
       if (direction == Direction.DOWN) {
          return ActionResultType.FAIL;
       } else {
          World world = context.func_195991_k();
          BlockItemUseContext blockitemusecontext = new BlockItemUseContext(context);
          BlockPos blockpos = blockitemusecontext.func_195995_a();
          BlockPos blockpos1 = blockpos.func_177984_a();
          if (blockitemusecontext.func_196011_b() && world.func_180495_p(blockpos1).func_196953_a(blockitemusecontext)) {
             double d0 = (double)blockpos.func_177958_n();
             double d1 = (double)blockpos.func_177956_o();
             double d2 = (double)blockpos.func_177952_p();
             List<Entity> list = world.func_72839_b((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
             if (!list.isEmpty()) {
                return ActionResultType.FAIL;
             } else {
                ItemStack itemstack = context.func_195996_i();
                if (!world.field_72995_K) {
                   world.func_217377_a(blockpos, false);
                   world.func_217377_a(blockpos1, false);
                   ScarecrowEntity scarecrowEntity = new ScarecrowEntity(world, d0 + 0.5D, d1, d2 + 0.5D);
                   float f = (float)MathHelper.func_76141_d((MathHelper.func_76142_g(context.func_195990_h() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                   scarecrowEntity.func_70012_b(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
                   this.applyRandomRotations(scarecrowEntity, world.field_73012_v);
                   EntityType.func_208048_a(world, context.func_195999_j(), scarecrowEntity, itemstack.func_77978_p());
                   world.func_217376_c(scarecrowEntity);
                   world.func_184148_a((PlayerEntity)null, scarecrowEntity.field_70165_t, scarecrowEntity.field_70163_u, scarecrowEntity.field_70161_v, SoundEvents.field_187710_m, SoundCategory.BLOCKS, 0.75F, 0.8F);
                }
 
                itemstack.func_190918_g(1);
                return ActionResultType.SUCCESS;
             }
          } else {
             return ActionResultType.FAIL;
          }
       }
    }
 
    private void applyRandomRotations(ScarecrowEntity armorStand, Random rand) {
       Rotations rotations = armorStand.func_175418_s();
       float f = rand.nextFloat() * 5.0F;
       float f1 = rand.nextFloat() * 20.0F - 10.0F;
       Rotations rotations1 = new Rotations(rotations.func_179415_b() + f, rotations.func_179416_c() + f1, rotations.func_179413_d());
       armorStand.func_175415_a(rotations1);
       rotations = armorStand.func_175408_t();
       f = rand.nextFloat() * 10.0F - 5.0F;
       rotations1 = new Rotations(rotations.func_179415_b(), rotations.func_179416_c() + f, rotations.func_179413_d());
       armorStand.func_175424_b(rotations1);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 14 ms
	
	Decompiled with FernFlower.
*/