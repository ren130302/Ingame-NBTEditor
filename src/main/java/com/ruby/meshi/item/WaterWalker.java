 package com.ruby.meshi.item;
 
 import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
 
 public class WaterWalker extends Item implements Accessory {
    public WaterWalker(Properties properties) {
       super(properties);
    }
 
 
    public void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
       if (!player.func_70093_af()) {
          BlockState underState = world.func_180495_p(new BlockPos(player.field_70165_t, Math.ceil(player.field_70163_u + player.func_213322_ci().field_72448_b) - 0.0625D, player.field_70161_v));
          if (world.func_175623_d(new BlockPos(player.field_70165_t, Math.floor(player.field_70163_u), player.field_70161_v)) && underState.func_204520_s().func_206884_a(FluidTags.field_206959_a)) {
             player.func_213293_j(player.func_213322_ci().field_72450_a, 0.0D, player.func_213322_ci().field_72449_c);
             player.field_70122_E = true;
          }      }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/