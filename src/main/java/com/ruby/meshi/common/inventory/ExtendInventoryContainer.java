 package com.ruby.meshi.common.inventory;
 
 import com.ruby.meshi.common.inventory.ExtendInventoryContainer.1;
 import com.ruby.meshi.init.HiganContainerType;
 import com.ruby.meshi.util.CapabilityExtendInventory;
 import com.ruby.meshi.util.CapabilityExtendInventory.ExtendInventory;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.network.PacketBuffer;
 import net.minecraftforge.common.util.LazyOptional;
 import net.minecraftforge.common.util.NonNullSupplier;
 import net.minecraftforge.fml.network.IContainerFactory;
 
 
 
 
 public class ExtendInventoryContainer extends Container implements IContainerFactory<ExtendInventoryContainer> {
    PlayerEntity player;
 
    public ExtendInventoryContainer(int windowId, PlayerInventory playerInventory) {
       this(HiganContainerType.EXTEND_INVENTORY, windowId, playerInventory);
    }
 
    public ExtendInventoryContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
       super(type, id);
       this.player = playerInventory.field_70458_d;
       LazyOptional<ExtendInventory> opt = this.player.getCapability(CapabilityExtendInventory.EXTEND_INVENTORY);      int j1;
       if (opt.isPresent()) {
          ExtendInventory inv = (ExtendInventory)opt.orElseGet(() -> {            return (ExtendInventory)CapabilityExtendInventory.EXTEND_INVENTORY.getDefaultInstance();         });
          for(j1 = 0; j1 < 4; ++j1) {
 
             this.func_75146_a(new 1(this, inv, j1, 8, 8 + j1 * 18));
 
 
 
 
 
 
 
 
 
 
 
 
 
          }
       }
 
       int i1;
       for(i1 = 0; i1 < 3; ++i1) {
          for(j1 = 0; j1 < 9; ++j1) {
             this.func_75146_a(new Slot(playerInventory, j1 + (i1 + 1) * 9, 8 + j1 * 18, 84 + i1 * 18));
          }
       }
 
       for(i1 = 0; i1 < 9; ++i1) {
          this.func_75146_a(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
       }
 
    }
 
 
    public boolean func_75145_c(PlayerEntity playerIn) {
       return playerIn.func_70089_S();
    }
 
 
    public ExtendInventoryContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
       return new ExtendInventoryContainer(windowId, inv);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 16 ms
	
	Decompiled with FernFlower.
*/