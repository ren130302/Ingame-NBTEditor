 package com.ruby.meshi.item;
 
 import java.util.Iterator;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
 public class ItemMagnet extends Item implements Accessory {
    public ItemMagnet(Properties properties) {
       super(properties);
    }
 
 
    public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
       if (!world.field_72995_K && (world.func_72912_H().func_82573_f() & 7L) == 0L && entity instanceof PlayerEntity) {
          Iterator var6 = world.func_175674_a(entity, entity.func_174813_aQ().func_72314_b(5.0D, 2.0D, 5.0D), (ex) -> {            return ex instanceof ItemEntity || ex instanceof ThrowableEntity || ex instanceof AbstractArrowEntity || ex instanceof ExperienceOrbEntity;         }).iterator();
          while(var6.hasNext()) {            Entity e = (Entity)var6.next();
             if (e.func_70089_S()) {
                e.func_70100_b_((PlayerEntity)entity);
             }
          }
       }
 
    }
 
    public void playerPostTick(World world, PlayerEntity player, ItemStack stack) {
       if (!world.field_72995_K && (world.func_72912_H().func_82573_f() & 7L) == 0L) {         Iterator var4 = world.func_175674_a(player, player.func_174813_aQ().func_72314_b(5.0D, 2.0D, 5.0D), (ex) -> {            return ex instanceof ItemEntity || ex instanceof ThrowableEntity || ex instanceof AbstractArrowEntity || ex instanceof ExperienceOrbEntity;         }).iterator();         while(var4.hasNext()) {
             Entity e = (Entity)var4.next();
             if (e.func_70089_S()) {
                e.func_70100_b_(player);
             }         }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 15 ms
	
	Decompiled with FernFlower.
*/