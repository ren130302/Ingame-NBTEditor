 package com.ruby.meshi.block;
 
 import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruby.meshi.block.CollectionAndDeliveryBase.InventoryEntry;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
 public class DeliveryPressurePlate extends CollectionAndDeliveryBase {
    public DeliveryPressurePlate(Properties properties, int horizon, int vertical) {
       super(properties, horizon, vertical);
    }
 
 
    public void onPressedSwitch(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
       CollectorPressurePlateTileEntity tile = (CollectorPressurePlateTileEntity)worldIn.func_175625_s(pos);
 
       boolean isForce = (Boolean)state.func_177229_b(FORCE);
       List<Item> collectorFilter = (List)this.getSlotList(tile).stream().filter((stack) -> {
 
          return !stack.func_190926_b();
       }).map(ItemStack::func_77973_b).collect(Collectors.toList());
       if (!isForce || !collectorFilter.isEmpty()) {
          Set<IInventory> inventorySet = this.getSurroundingIInventory(worldIn, pos, this.horizonSize, this.verticalSize);         List playerEntryList;
          if (isForce) {
 
             playerEntryList = this.createInventoryEntrys(playerIn.field_71071_by, playerIn.field_71071_by.field_70462_a, (stack) -> {
 
                return stack.func_190926_b();            }, (id, stack) -> {
                return true;
             });
             if (!playerEntryList.isEmpty()) {
 
                collectorFilter.stream().filter((item) -> {
                   return collectorFilter.stream().filter((i) -> {                     return i == item;                  }).count() > playerIn.field_71071_by.field_70462_a.stream().filter((i) -> {                     return i.func_77973_b() == item;
                   }).count();
                }).forEach((item) -> {
                   InventoryEntry invEntry = (InventoryEntry)inventorySet.stream().flatMap((inv) -> {
 
                      return this.createInventoryEntrys(inv, this.getSlotList(inv), (stack) -> {
                         return stack.func_77973_b() == item;
                      }, (id, stack) -> {                        return inv.func_94041_b(id, stack);
                      }).stream();
                   }).findFirst().orElse((Object)null);
                   if (invEntry != null && !playerEntryList.isEmpty()) {
 
                      this.searchAndInsert(invEntry, (InventoryEntry)playerEntryList.remove(0), true);
                   }
                });
 
 
             }
          }
 
          playerEntryList = this.createInventoryEntrys(playerIn.field_71071_by, playerIn.field_71071_by.field_70462_a, (stack) -> {
             boolean var10000;            label25: {
                if (!stack.func_190926_b()) {                  if (collectorFilter.isEmpty()) {                     if (stack.func_77985_e()) {                        break label25;                     }                  } else if (collectorFilter.contains(stack.func_77973_b())) {                     break label25;                  }               }               var10000 = false;               return var10000;            }            var10000 = true;            return var10000;         }, (id, stack) -> {
             return stack.func_190916_E() < playerIn.field_71071_by.func_70297_j_();
 
          });
          if (!playerEntryList.isEmpty()) {
 
             Set<Item> matchedFilter = (Set)playerEntryList.stream().map(InventoryEntry::getItem).collect(Collectors.toSet());
 
 
 
             List<InventoryEntry> chestEntrySet = (List)inventorySet.stream().flatMap((inv) -> {
 
                return this.createInventoryEntrys(inv, this.getSlotList(inv), (stack) -> {
                   return !stack.func_190926_b() && matchedFilter.contains(stack.func_77973_b());
                }, (id, stack) -> {                  return inv.func_94041_b(id, stack);
                }).stream().sorted();
             }).collect(Collectors.toList());
             if (!chestEntrySet.isEmpty()) {
 
                playerEntryList.forEach((playerEntry) -> {
                   chestEntrySet.stream().filter((e) -> {
                      return playerEntry.isItemEqual(e);
                   }).forEach((chestEntry) -> {
                      if (playerEntry.getStack().func_190916_E() < playerEntry.getInventory().func_70297_j_()) {
                         this.searchAndInsert(chestEntry, playerEntry, false);
                      }
                   });
                });
             }         }
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 43 ms
	
	Decompiled with FernFlower.
*/