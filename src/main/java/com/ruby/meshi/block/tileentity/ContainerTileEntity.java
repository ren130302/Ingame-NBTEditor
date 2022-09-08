 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.block.tileentity.ContainerTileEntity.MeshiContainer;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Iterator;
 import java.util.function.Supplier;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 import net.minecraftforge.common.capabilities.Capability;
 import net.minecraftforge.common.util.LazyOptional;
 import net.minecraftforge.common.util.NonNullSupplier;
 import net.minecraftforge.items.CapabilityItemHandler;
 import net.minecraftforge.items.IItemHandlerModifiable;
 import net.minecraftforge.items.wrapper.InvWrapper;
 
 public class ContainerTileEntity extends LockableTileEntity {
    NonNullList<ItemStack> contents;
    Supplier<MeshiContainer> container = () -> {
       return (MeshiContainer)this.func_195044_w().func_177230_c();
    };
 
    public ContainerTileEntity() {
       super(HiganTileEntityType.JPCHEST);
    }
 
    public ContainerTileEntity setContainerBlock(MeshiContainer container) {
       this.container = () -> {         return container;      };
       this.contents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       return this;
    }
 
 
    public boolean func_191420_l() {
       Iterator var1 = this.contents.iterator();         }         itemstack = (ItemStack)var1.next();
       } while(itemstack.func_190926_b());      ItemStack itemstack;
       return false;
       do {
          if (!var1.hasNext()) {
             return true;
    }
 
 
    public ItemStack func_70301_a(int index) {
       return (ItemStack)this.contents.get(index);
    }
 
 
    public void func_70299_a(int index, ItemStack stack) {
       this.contents.set(index, stack);
       if (stack.func_190916_E() > this.func_70297_j_()) {
          stack.func_190920_e(this.func_70297_j_());
       }
       this.func_70296_d();
    }
 
 
    public ItemStack func_70304_b(int index) {
       return ItemStackHelper.func_188383_a(this.contents, index);
    }
 
 
    public void func_174888_l() {
       this.contents.clear();
    }
 
 
    public boolean func_70300_a(PlayerEntity player) {
       if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
          return false;
       } else {
          return player.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
       }
    }
 
 
    public ItemStack func_70298_a(int index, int count) {
       ItemStack itemstack = ItemStackHelper.func_188382_a(this.contents, index, count);
       if (!itemstack.func_190926_b()) {
          this.func_70296_d();
       }
       return itemstack;
    }
 
 
    public ITextComponent func_213907_g() {
       return new TranslationTextComponent("container.chest", new Object[0]);
    }
 
 
    public int func_70302_i_() {
       return ((MeshiContainer)this.container.get()).getSize();
    }
 
 
    protected Container func_213906_a(int id, PlayerInventory player) {
       return ((MeshiContainer)this.container.get()).createMenu(id, player, this);
    }
 
 
    public void func_174889_b(PlayerEntity player) {
       if (!player.func_175149_v()) {
          this.openOrClose(player);
       }
    }
 
 
    public void func_174886_c(PlayerEntity player) {
       if (!player.func_175149_v()) {
          this.openOrClose(player);
       }
    }
 
    void openOrClose(PlayerEntity player) {
       if (this.field_145850_b.func_175625_s(this.func_174877_v()) instanceof ContainerTileEntity) {
          this.field_145850_b.func_195593_d(this.field_174879_c, this.func_195044_w().func_177230_c());
       }
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.contents = NonNullList.func_191197_a(compound.func_74762_e("invsize"), ItemStack.field_190927_a);
       ItemStackHelper.func_191283_b(compound, this.contents);
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
       compound.func_74768_a("invsize", this.func_70302_i_());
       ItemStackHelper.func_191282_a(compound, this.contents);
       return compound;
    }
 
 
 
 
 
 
 
 
 
    public void func_145836_u() {
       super.func_145836_u();
       if (this.chestHandler != null) {
          this.chestHandler.invalidate();
          this.chestHandler = null;
       }
    }
 
 
    public void func_145843_s() {
       super.func_145843_s();
       if (this.chestHandler != null) {
          this.chestHandler.invalidate();
       }
    }
 
 
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
       if (!this.field_145846_f && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
          if (this.chestHandler == null) {
             this.chestHandler = LazyOptional.of(() -> {
                return new InvWrapper(this);            });         }
          return this.chestHandler.cast();
       } else {
          return super.getCapability(cap, side);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 26 ms
	
	Decompiled with FernFlower.
*/