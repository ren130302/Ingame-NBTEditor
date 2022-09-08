 package com.ruby.meshi.item;
 
 import com.ruby.meshi.block.SakuraBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
 public class PaddyFieldHoe extends HoeItem {
    public PaddyFieldHoe(IItemTier tier, float attackSpeedIn, Properties builder) {
       super(tier, attackSpeedIn, builder);
    }
 
 
    public ActionResultType func_195939_a(ItemUseContext context) {
       World world = context.func_195991_k();
       BlockPos blockpos = context.func_195995_a();
       int hook = ForgeEventFactory.onHoeUse(context);
       if (hook != 0) {
          return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;      } else {
          if (context.func_196000_l() != Direction.DOWN && world.func_175623_d(blockpos.func_177984_a())) {
             BlockState blockstate = (BlockState)field_195973_b.get(world.func_180495_p(blockpos).func_177230_c());
             if (blockstate != null) {
                PlayerEntity playerentity = context.func_195999_j();
                world.func_184133_a(playerentity, blockpos, SoundEvents.field_187693_cj, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.field_72995_K) {
                   world.func_175656_a(blockpos, SakuraBlocks.PADDY_FIELD.func_176223_P());
                   if (playerentity != null) {
                      context.func_195996_i().func_222118_a(1, playerentity, (p_220043_1_) -> {
                         p_220043_1_.func_213334_d(context.func_221531_n());
                      });
                   }
                }
 
                return ActionResultType.SUCCESS;
             }
          }
 
          return ActionResultType.PASS;
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 12 ms
	
	Decompiled with FernFlower.
*/