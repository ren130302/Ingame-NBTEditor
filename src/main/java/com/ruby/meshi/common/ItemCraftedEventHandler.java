 package com.ruby.meshi.common;
 
 import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.ruby.meshi.common.ItemCraftedEventHandler.CraftedEventWrapper;
import com.ruby.meshi.item.HiganItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemTier;
import net.minecraft.world.World;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 
 
 
 
 
 @EventBusSubscriber(
    bus = Bus.FORGE
 )
 public class ItemCraftedEventHandler {
    private static final List<Consumer<CraftedEventWrapper>> list = Lists.newArrayList();
 
    static {
       register(vanillaToolEnchant());
    }
 
    @SubscribeEvent
    public static void craftRelease(ItemCraftedEvent event) {
       PlayerEntity player = event.getPlayer();
       if (player != null) {
          World world = event.getPlayer().field_70170_p;
          if (world != null && !world.field_72995_K) {
             list.forEach((c) -> {
                c.accept(new CraftedEventWrapper(event));            });         }      }
 
    }
 
    public static void register(Consumer<CraftedEventWrapper> listener) {
       list.add(listener);
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    private static Consumer<CraftedEventWrapper> vanillaToolEnchant() {
       return (event) -> {
          Item item = event.crafting.func_77973_b();
          if (item.getRegistryName().func_110624_b().equals("minecraft")) {            Predicate<ItemStack> allMaterialFilter = (s) -> {               return false;
             };            Predicate<ItemStack> mapleMaterialFilter = (s) -> {               return false;
             };
             int randomRate = 15;
             Random rand = ThreadLocalRandom.current();
             if (item instanceof TieredItem) {
                if (((TieredItem)item).func_200891_e() == ItemTier.IRON) {                  allMaterialFilter = (s) -> {                     return Items.INGOTS_IRON.func_199685_a_(s.func_77973_b());
                   };                  mapleMaterialFilter = (s) -> {                     return s.func_77973_b() == HiganItems.MAPLE_INGOT;
                   };
                } else if (((TieredItem)item).func_200891_e() == ItemTier.DIAMOND) {                  allMaterialFilter = (s) -> {                     return Items.GEMS_DIAMOND.func_199685_a_(s.func_77973_b());
                   };                  mapleMaterialFilter = (s) -> {                     return s.func_77973_b() == HiganItems.GINKGO_GEM;
                   };
                   randomRate = 30;
                }
             } else if (item instanceof ArmorItem) {
                if (((ArmorItem)item).func_200880_d() == ArmorMaterial.IRON) {                  allMaterialFilter = (s) -> {                     return Items.INGOTS_IRON.func_199685_a_(s.func_77973_b());
                   };                  mapleMaterialFilter = (s) -> {                     return s.func_77973_b() == HiganItems.MAPLE_INGOT;
                   };
                } else if (((ArmorItem)item).func_200880_d() == ArmorMaterial.DIAMOND) {                  allMaterialFilter = (s) -> {                     return Items.GEMS_DIAMOND.func_199685_a_(s.func_77973_b());
                   };                  mapleMaterialFilter = (s) -> {                     return s.func_77973_b() == HiganItems.GINKGO_GEM;
                   };
                   randomRate = 20;
                }            }
 
             int mcount = event.getFindCount(mapleMaterialFilter);
             if (mcount > 0) {
                int acount = event.getFindCount(allMaterialFilter);
                EnchantmentHelper.func_77504_a(rand, event.crafting, 5 + rand.nextInt((int)((float)mcount / (float)acount * (float)randomRate)), randomRate > 15);
                ((ServerPlayerEntity)event.player).func_71113_k();
             }         }
 
       };
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 24 ms
	
	Decompiled with FernFlower.
*/