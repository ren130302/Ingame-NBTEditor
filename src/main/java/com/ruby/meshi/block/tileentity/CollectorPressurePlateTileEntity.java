 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.block.CollectionAndDeliveryBase;
 import com.ruby.meshi.common.inventory.CollectorPressurePlateContainer;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Iterator;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 
 
 
 public class CollectorPressurePlateTileEntity extends LockableTileEntity {
    public static final byte INV_SIZE = 10;
    private NonNullList<ItemStack> chestContents;
 
    public CollectorPressurePlateTileEntity() {
       super(HiganTileEntityType.COLLECTOR_PLATE);
       this.chestContents = NonNullList.func_191197_a(10, ItemStack.field_190927_a);
    }
 
 
    public boolean isForce() {
       return (Boolean)this.func_195044_w().func_177229_b(CollectionAndDeliveryBase.FORCE);
    }
 
 
    public void func_70296_d() {
       super.func_70296_d();
       this.func_145831_w().func_184138_a(this.func_174877_v(), this.func_195044_w(), this.func_195044_w(), 3);
    }
 
 
    public boolean func_94041_b(int index, ItemStack stack) {
       ItemStack copyStack = stack.func_77946_l();
       copyStack.func_190920_e(1);
       this.chestContents.set(index, copyStack);
       this.func_70296_d();
       return false;
    }
 
 
    public int func_70302_i_() {
       return 10;
    }
 
 
    public boolean func_191420_l() {
       Iterator var1 = this.chestContents.iterator();         }         itemstack = (ItemStack)var1.next();
       } while(itemstack.func_190926_b());
       return false;
       ItemStack itemstack;
       do {
          if (!var1.hasNext()) {
             return true;
    }
 
 
    public ItemStack func_70301_a(int index) {
       return (ItemStack)this.chestContents.get(index);
    }
 
 
    public ItemStack func_70298_a(int index, int count) {
       return this.func_70304_b(index);
    }
 
 
    public ItemStack func_70304_b(int index) {
       ItemStack stack = ItemStackHelper.func_188383_a(this.chestContents, index);
       if (!stack.func_190926_b()) {
          this.func_70296_d();
       }
       return ItemStack.field_190927_a;
    }
 
 
    public void func_70299_a(int index, ItemStack stack) {
       this.chestContents.set(index, stack.func_77946_l());
       this.func_70296_d();
    }
 
 
    public boolean func_70300_a(PlayerEntity player) {
       if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
          return false;
       } else {
          return player.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
       }
    }
 
 
    public void func_174888_l() {
       this.chestContents.clear();
    }
 
 
    protected ITextComponent func_213907_g() {
       return new TranslationTextComponent(String.join(".", "container", "meshi", this.func_195044_w().func_177230_c().getRegistryName().func_110623_a()), new Object[0]);
    }
 
 
    protected Container func_213906_a(int windowId, PlayerInventory inventory) {
       return new CollectorPressurePlateContainer(windowId, inventory, this);
    }
 
    public ItemStack getDisplayItem() {
       return (ItemStack)this.chestContents.get(0);
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.readContents(compound);
    }
 
    private void readContents(CompoundNBT compound) {
       this.chestContents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       ItemStackHelper.func_191283_b(compound, this.chestContents);
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
       this.writeContents(compound);
       return compound;
    }
 
    private CompoundNBT writeContents(CompoundNBT compound) {
       ItemStackHelper.func_191282_a(compound, this.chestContents);
       return compound;
    }
 
 
 
 
    public void handleUpdateTag(CompoundNBT tag) {
       super.handleUpdateTag(tag);
       this.readContents(tag);
    }
 
 
    public CompoundNBT func_189517_E_() {
       CompoundNBT tag = super.func_189517_E_();
       this.writeContents(tag);
       return tag;
    }
 
 
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       this.readContents(pkt.func_148857_g());
    }
 
 
    public SUpdateTileEntityPacket func_189518_D_() {
       CompoundNBT var1 = new CompoundNBT();
       this.writeContents(var1);
       return new SUpdateTileEntityPacket(this.func_174877_v(), 0, var1);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 33 ms
	
	Decompiled with FernFlower.
*/