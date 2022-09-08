 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Iterator;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.tileentity.LockableLootTileEntity;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 
 
 
 public class WallShelfTileEntity extends LockableLootTileEntity {
    public static final byte RIGHT = 0;
    public static final byte LEFT = 1;
    public static final byte CENTER = 0;
    private NonNullList<ItemStack> chestContents;
    private boolean isDouble;
    private static final byte TABLE_SIZE = 2;
 
    public WallShelfTileEntity() {
       super(HiganTileEntityType.WALL_SHELF);
       this.chestContents = NonNullList.func_191197_a(2, ItemStack.field_190927_a);
       this.isDouble = true;
    }
 
 
    public void func_70296_d() {
       super.func_70296_d();
    }
 
 
    public ItemStack func_70304_b(int index) {
       return super.func_70304_b(index);
    }
 
 
    public void func_70299_a(int index, ItemStack stack) {
       super.func_70299_a(index, stack);
    }
 
 
    public int func_70302_i_() {
       return this.func_190576_q().size();
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
 
 
    protected NonNullList<ItemStack> func_190576_q() {
       return this.chestContents;
    }
 
 
    protected void func_199721_a(NonNullList<ItemStack> itemsIn) {
       this.chestContents = NonNullList.func_191197_a(2, ItemStack.field_190927_a);
 
       for(int i = 0; i < itemsIn.size(); ++i) {
          if (i < this.chestContents.size()) {
             this.func_190576_q().set(i, itemsIn.get(i));
          }      }
 
    }
 
 
    protected ITextComponent func_213907_g() {
       return new TranslationTextComponent(String.join(".", "container", "meshi", this.func_195044_w().func_177230_c().getRegistryName().func_110623_a()), new Object[0]);
    }
 
 
    protected Container func_213906_a(int p_213906_1_, PlayerInventory p_213906_2_) {
       return null;
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.readAddtionalData(compound);
    }
 
    void readAddtionalData(CompoundNBT compound) {
       this.chestContents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       if (!this.func_184283_b(compound)) {
          ItemStackHelper.func_191283_b(compound, this.chestContents);
       }
       if (compound.func_74764_b("isDouble")) {
          this.isDouble = compound.func_74767_n("isDouble");
       }
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
       this.writeAdditionalData(compound);
       return compound;
    }
 
    CompoundNBT writeAdditionalData(CompoundNBT compound) {
       if (!this.func_184282_c(compound)) {
          ItemStackHelper.func_191282_a(compound, this.chestContents);
       }
       compound.func_74757_a("isDouble", this.isDouble);
       return compound;
    }
 
    public void setSingleMode() {
       this.isDouble = false;
    }
 
    public boolean isDouble() {
       return this.isDouble;
    }
 
    public boolean hasItem(byte side) {
       return !((ItemStack)this.chestContents.get(side)).func_190926_b();
    }
 
 
 
    public void handleUpdateTag(CompoundNBT tag) {
       super.handleUpdateTag(tag);
       this.readAddtionalData(tag);
    }
 
 
    public CompoundNBT func_189517_E_() {
       CompoundNBT tag = super.func_189517_E_();
       this.writeAdditionalData(tag);
       return tag;
    }
 
 
 
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       this.readAddtionalData(pkt.func_148857_g());
    }
 
 
    public SUpdateTileEntityPacket func_189518_D_() {
       CompoundNBT var1 = new CompoundNBT();
       this.writeAdditionalData(var1);
       return new SUpdateTileEntityPacket(this.func_174877_v(), 5, var1);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 24 ms
	
	Decompiled with FernFlower.
*/