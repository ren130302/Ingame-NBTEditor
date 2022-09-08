 package com.ruby.meshi.common.inventory;
 
 import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
 import com.ruby.meshi.init.HiganContainerType;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.Inventory;
 import net.minecraft.inventory.container.ClickType;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.inventory.container.ContainerType;
 import net.minecraft.inventory.container.Slot;
 import net.minecraft.item.ItemStack;
 
 
 
 public class CollectorPressurePlateContainer extends Container {
    private IInventory inventory;
 
    public CollectorPressurePlateContainer(int windowId, PlayerInventory playerInventory) {
       this(HiganContainerType.COLLECTOR_PLATE, windowId, playerInventory, new Inventory(10));
    }
 
    public CollectorPressurePlateContainer(int windowId, PlayerInventory playerInventory, CollectorPressurePlateTileEntity inventory) {
       this(HiganContainerType.COLLECTOR_PLATE, windowId, playerInventory, inventory);
    }
 
    protected CollectorPressurePlateContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory) {
       super(type, id);
       this.inventory = inventory;
 
       inventory.func_174889_b(playerInventory.field_70458_d);
 
 
       int j;
       for(j = 0; j < 2; ++j) {
          for(int chestCol = 0; chestCol < 5; ++chestCol) {
             this.func_75146_a(new Slot(inventory, chestCol + j * 5, 44 + chestCol * 18, 33 + j * 18));
          }      }
 
       int i;
       for(i = 0; i < 3; ++i) {
          for(j = 0; j < 9; ++j) {
             this.func_75146_a(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
          }
       }
 
       for(i = 0; i < 9; ++i) {
          this.func_75146_a(new Slot(playerInventory, i, 8 + i * 18, 142));
       }
    }
 
 
    public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
       if (clickTypeIn != ClickType.QUICK_MOVE && clickTypeIn != ClickType.QUICK_CRAFT) {         }      } else {
          return ItemStack.field_190927_a;
 
          if (0 <= slotId && slotId < 10) {
             PlayerInventory playerinventory = player.field_71071_by;
             ItemStack stack = playerinventory.func_70445_o();
             if (dragType == 0 && !stack.func_190926_b()) {
 
                ItemStack copy = stack.func_77946_l();
                copy.func_190920_e(1);
                this.func_75139_a(slotId).func_75215_d(copy);
             }
 
             if (dragType == 1) {
                this.func_75139_a(slotId).func_75215_d(ItemStack.field_190927_a);
             }
             return ItemStack.field_190927_a;
          } else {
             return super.func_184996_a(slotId, dragType, clickTypeIn, player);
       }
    }
 
 
    public ItemStack func_82846_b(PlayerEntity playerIn, int index) {
       ItemStack itemstack = ItemStack.field_190927_a;
       Slot slot = (Slot)this.field_75151_b.get(index);
 
       if (slot != null && slot.func_75216_d()) {
          ItemStack itemstack1 = slot.func_75211_c();
          itemstack = itemstack1.func_77946_l();
 
          if (index < 10) {
             if (!this.func_75135_a(itemstack1, 10, this.field_75151_b.size(), true)) {
                return ItemStack.field_190927_a;
             }
          } else if (!this.func_75135_a(itemstack1, 0, 10, false)) {
             return ItemStack.field_190927_a;
          }
 
          if (itemstack1.func_190926_b()) {
             slot.func_75215_d(ItemStack.field_190927_a);
          } else {
             slot.func_75218_e();
          }
       }
 
       return itemstack;
    }
 
 
    public void func_75134_a(PlayerEntity playerIn) {
       super.func_75134_a(playerIn);
       this.inventory.func_174886_c(playerIn);
    }
 
 
    public boolean func_75145_c(PlayerEntity playerIn) {
       return this.inventory.func_70300_a(playerIn);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 28 ms
	
	Decompiled with FernFlower.
*/