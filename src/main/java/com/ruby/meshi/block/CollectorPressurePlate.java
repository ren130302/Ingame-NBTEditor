 package com.ruby.meshi.block;
 
 import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruby.meshi.block.CollectionAndDeliveryBase.InventoryEntry;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
 public class CollectorPressurePlate extends CollectionAndDeliveryBase {
    public CollectorPressurePlate(Properties properties, int horizon, int vertical) {
       super(properties, horizon, vertical);
    }
 
 
    public void onPressedSwitch(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
       CollectorPressurePlateTileEntity tile = (CollectorPressurePlateTileEntity)worldIn.func_175625_s(pos);
       boolean isForce = (Boolean)state.func_177229_b(FORCE);
       Set<Item> collectorFilter = (Set)this.getSlotList(tile).stream().filter((stack) -> {
 
          return !stack.func_190926_b();
       }).map(ItemStack::func_77973_b).collect(Collectors.toSet());
 
       if (!isForce || !collectorFilter.isEmpty()) {
 
 
          List<InventoryEntry> playerEntryList = this.createInventoryEntrys(playerIn.field_71071_by, playerIn.field_71071_by.field_70462_a, (stack) -> {
             boolean var10000;            label25: {
                if (!stack.func_190926_b()) {                  if (collectorFilter.isEmpty()) {                     if (stack.func_77985_e()) {                        break label25;                     }                  } else if (collectorFilter.contains(stack.func_77973_b())) {                     break label25;                  }               }               var10000 = false;               return var10000;            }            var10000 = true;            return var10000;         }, (id, stack) -> {
             return !PlayerInventory.func_184435_e(id);
          });
          if (!playerEntryList.isEmpty()) {
 
             Set<Item> matchedFilter = (Set)playerEntryList.stream().map(InventoryEntry::getItem).collect(Collectors.toSet());
 
 
 
             Set<InventoryEntry> chestEntrySet = (Set)this.getSurroundingIInventory(worldIn, pos, this.horizonSize, this.verticalSize).stream().flatMap((inv) -> {
 
 
                return this.createInventoryEntrys(inv, this.getSlotList(inv), (stack) -> {
                   return isForce || !stack.func_190926_b() && matchedFilter.contains(stack.func_77973_b());
                }, (id, stack) -> {
                   return inv.func_94041_b(id, stack);
                }).stream().sorted().distinct();
             }).collect(Collectors.toSet());
             if (!chestEntrySet.isEmpty()) {
 
                playerEntryList.forEach((playerEntry) -> {
                   chestEntrySet.stream().filter((e) -> {
                      return isForce || playerEntry.isItemEqual(e);
                   }).forEach((chestEntry) -> {
                      if (!playerEntry.getStack().func_190926_b()) {
                         this.searchAndInsert(playerEntry, chestEntry, true);
                      }
                   });
                });
             }
          }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 31 ms
	
	Decompiled with FernFlower.
*/