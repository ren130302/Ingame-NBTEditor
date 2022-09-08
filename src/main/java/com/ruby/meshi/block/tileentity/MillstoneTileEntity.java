 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.block.Millstone;
 import com.ruby.meshi.common.inventory.MillstoneContainer;
 import com.ruby.meshi.crafting.GrindRecipe;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Iterator;
 import java.util.function.Function;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IRecipeHelperPopulator;
 import net.minecraft.inventory.ISidedInventory;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.particles.ItemParticleData;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.tileentity.ITickableTileEntity;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.IntArray;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;
 import net.minecraftforge.common.capabilities.Capability;
 import net.minecraftforge.common.util.LazyOptional;
 import net.minecraftforge.common.util.NonNullSupplier;
 import net.minecraftforge.items.CapabilityItemHandler;
 import net.minecraftforge.items.IItemHandler;
 import net.minecraftforge.items.wrapper.SidedInvWrapper;
 
 
 
 public class MillstoneTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory, IRecipeHelperPopulator {
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{0};
    public static final int INPUT = 0;
    public static final int OUT_MAIN = 1;
    public static final int OUT_SUB = 2;
    public static final int INV_SIZE = 3;
    private NonNullList<ItemStack> contents;
    private int roll;
    private int progress;
    public MillstoneTileEntity() {
       super(HiganTileEntityType.MILLSTONE);
       this.contents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       this.progress = -1;
    }
 
 
    public void func_73660_a() {
       int magnification = 1;
       if (!this.func_145831_w().field_72995_K) {
          if (this.progress <= 0) {
             if (this.canGrind()) {
                this.progress = this.getGrindTime();
                this.func_145831_w().func_175656_a(this.field_174879_c, (BlockState)this.func_195044_w().func_206870_a(Millstone.ENABLE, true));
                this.func_70296_d();
             }
          } else {
             this.progress -= magnification;
             if (this.progress <= 0) {
                this.grindItem();
                this.reset();
                this.func_70296_d();
 
             }
          }
       } else if ((Boolean)this.func_195044_w().func_177229_b(Millstone.ENABLE)) {
          this.roll = this.roll++ < 360 ? this.roll : 0;
          this.itemCrackParticle(this.func_70301_a(0));
 
       } else {
          this.roll = this.roll != 0 && this.roll++ < 360 ? this.roll : 0;
 
       }
 
    }
 
    private boolean canGrind() {
       if (this.isSlotEmpty(0)) {
          return false;
       } else {
          GrindRecipe gr = this.getRecipe();
 
          if (gr == null) {
             return false;
          } else {
             int inputreq = gr.getRequestCount();
             ItemStack output = gr.getResult();
             ItemStack bonus = gr.getBonus();
 
             if (this.func_70301_a(0).func_190916_E() < inputreq) {
                return false;
 
             } else {
                boolean isMainInsert = this.hasInsert(1, output);
                boolean isBonusInsert = this.hasInsert(2, bonus);
 
 
                if (!isMainInsert) {
                   return false;
 
 
 
                } else if (isMainInsert && isBonusInsert) {
                   return true;
 
 
                } else {
                   return bonus.func_190926_b();
                }
             }
          }
       }
    }
 
 
    private boolean hasInsert(int slot, ItemStack stack) {
       if (this.isSlotEmpty(slot)) {
          return true;
 
       } else if (stack != null && !stack.func_190926_b() && this.func_70301_a(slot).func_77969_a(stack)) {      } else {
          return false;
 
          int outResult = this.func_70301_a(slot).func_190916_E() + stack.func_190916_E();
          return outResult <= this.func_70297_j_() && outResult <= stack.func_77976_d();
       }
    }
    private void grindItem() {
       if (!this.isSlotEmpty(0)) {
          GrindRecipe gr = this.getRecipe();
          if (gr != null) {
 
             ItemStack output = gr.getResult();
             ItemStack bonus = this.func_145831_w().field_73012_v.nextFloat() <= gr.getBonusWeight() ? gr.getBonus() : ItemStack.field_190927_a;
 
             this.insertInventorySlotContents(1, output);
             this.insertInventorySlotContents(2, bonus);
 
             this.func_70298_a(0, gr.getRequestCount());
          }      }
 
    }
 
    public GrindRecipe getRecipe() {
       return (GrindRecipe)this.func_145831_w().func_199532_z().func_215371_a(GrindRecipe.TYPE, this, this.func_145831_w()).orElse((Object)null);
    }
 
    public int getGrindTime() {
       return (Integer)this.func_145831_w().func_199532_z().func_215371_a(GrindRecipe.TYPE, this, this.func_145831_w()).map(GrindRecipe::getGrindingTime).orElse(200);
    }
 
    private boolean isSlotEmpty(int slot) {
       return ((ItemStack)this.contents.get(slot)).func_190926_b();
    }
 
    public double getRoll() {
       return (double)this.roll;
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.roll = compound.func_74762_e("roll");
       this.contents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       this.readData(compound);
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
       compound.func_74768_a("roll", this.roll);
       return this.writeData(compound);
    }
 
    public void readData(CompoundNBT compound) {
       ItemStackHelper.func_191283_b(compound, this.contents);
       this.progress = compound.func_74762_e("progress");
    }
 
    public CompoundNBT writeData(CompoundNBT compound) {
       ItemStackHelper.func_191282_a(compound, this.contents);
       compound.func_74768_a("progress", this.progress);
       return compound;
    }
 
    private void itemCrackParticle(ItemStack nowgi) {
       if (!nowgi.func_190926_b()) {
          float pitch = 0.0F;
          float yaw = this.func_145831_w().field_73012_v.nextFloat() * 20.0F - 1.0F;
 
          for(int j = 0; j < 1; ++j) {
             Vec3d vec3d = new Vec3d(((double)this.func_145831_w().field_73012_v.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
             vec3d = vec3d.func_178789_a(-pitch * 3.1415927F / 180.0F);
             vec3d = vec3d.func_178785_b(-yaw * 3.1415927F);
             double d0 = (double)(-this.func_145831_w().field_73012_v.nextFloat()) * 0.6D - 0.3D;
             Vec3d vec3d1 = new Vec3d(((double)this.func_145831_w().field_73012_v.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
             vec3d1 = vec3d1.func_178789_a(-pitch * 3.1415927F / 180.0F);
             vec3d1 = vec3d1.func_178785_b(-yaw * 3.1415927F);
             vec3d1 = vec3d1.func_72441_c((double)((float)this.func_174877_v().func_177958_n() + 0.5F), (double)((float)this.func_174877_v().func_177956_o() + 1.0F), (double)((float)this.func_174877_v().func_177952_p() + 0.5F));
 
             this.func_145831_w().func_195594_a(new ItemParticleData(ParticleTypes.field_197591_B, nowgi), vec3d1.func_82615_a(), vec3d1.func_82617_b(), vec3d1.func_82616_c(), vec3d.func_82615_a(), vec3d.func_82617_b() - 0.15D, vec3d.func_82616_c());
          }      }
 
    }
 
    public int getProgress() {
       return this.progress;
    }
 
 
    public int func_70302_i_() {
       return 3;
    }
 
 
    public boolean func_191420_l() {
       Iterator var1 = this.contents.iterator();         }         itemstack = (ItemStack)var1.next();
       } while(itemstack.func_190926_b());
       return false;
       ItemStack itemstack;
       do {
          if (!var1.hasNext()) {
             return true;
    }
 
 
    public ItemStack func_70301_a(int index) {
       return (ItemStack)this.contents.get(index);
    }
 
 
    public ItemStack func_70298_a(int index, int count) {
       ItemStack itemstack = ItemStackHelper.func_188382_a(this.contents, index, count);
       if (!itemstack.func_190926_b()) {
          this.func_70296_d();
       }
 
       if (index == 0 && !this.canGrind()) {
 
          this.reset();
 
       }
 
       return itemstack;
    }
 
 
    public ItemStack func_70304_b(int index) {
       return ItemStackHelper.func_188383_a(this.contents, index);
    }
 
 
    public void func_70299_a(int index, ItemStack stack) {
       if (index == 0 && !this.func_70301_a(index).func_77969_a(stack)) {
 
          this.reset();
       }
 
       this.contents.set(index, stack);
       if (stack.func_190916_E() > this.func_70297_j_()) {
          stack.func_190920_e(this.func_70297_j_());
       }
 
       this.func_70296_d();
    }
 
    private void reset() {
       this.progress = -1;
       this.func_145831_w().func_175656_a(this.field_174879_c, (BlockState)this.func_195044_w().func_206870_a(Millstone.ENABLE, false));
    }
 
    public void insertInventorySlotContents(int index, ItemStack insertStack) {
       if (insertStack != null && !insertStack.func_190926_b()) {         }      }
    }
 
          if (this.isSlotEmpty(index)) {
             this.func_70299_a(index, insertStack);
          } else if (this.func_70301_a(index).func_77969_a(insertStack)) {
             ItemStack stack = (ItemStack)this.contents.get(index);
             stack.func_190920_e(stack.func_190916_E() + insertStack.func_190916_E());
             if (stack.func_190916_E() > this.func_70297_j_()) {
                stack.func_190920_e(this.func_70297_j_());
             }
             this.func_70296_d();
 
 
 
 
    public boolean func_70300_a(PlayerEntity player) {
       if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
          return false;
       } else {
          return player.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
       }
    }
 
 
    public void func_174888_l() {
       this.contents.clear();
    }
 
 
    public ITextComponent func_213907_g() {
       return new TranslationTextComponent(String.join(".", "container", "meshi", this.func_195044_w().func_177230_c().getRegistryName().func_110623_a()), new Object[0]);
    }
 
 
    protected Container func_213906_a(int id, PlayerInventory player) {
       IntArray intArray = new IntArray(2);
       intArray.func_221477_a(0, this.progress);
       intArray.func_221477_a(1, this.getGrindTime());
       return new MillstoneContainer(id, player, this, intArray);
    }
 
 
    public void handleUpdateTag(CompoundNBT tag) {
       super.handleUpdateTag(tag);
       this.readData(tag);
    }
 
 
    public CompoundNBT func_189517_E_() {
       CompoundNBT tag = super.func_189517_E_();
       this.writeData(tag);
       return tag;
    }
 
 
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       this.readData(pkt.func_148857_g());
    }
 
 
    public SUpdateTileEntityPacket func_189518_D_() {
       CompoundNBT var1 = new CompoundNBT();
       this.writeData(var1);
       return new SUpdateTileEntityPacket(this.func_174877_v(), 5, var1);
    }
 
 
    public boolean func_94041_b(int index, ItemStack stack) {
       return index != 1 && index != 2;
    }
 
 
    public int[] func_180463_a(Direction side) {
       return side == Direction.DOWN ? slots_bottom : (side == Direction.UP ? slots_top : slots_sides);
    }
 
 
    public boolean func_180462_a(int index, ItemStack itemStackIn, Direction direction) {
       return this.func_94041_b(index, itemStackIn);
    }
 
 
    public boolean func_180461_b(int index, ItemStack stack, Direction direction) {
       return direction != Direction.DOWN || index != 0;
    }
    private LazyOptional<IItemHandler> handlerTop = LazyOptional.of(() -> {
       return new SidedInvWrapper(this, Direction.UP);   private LazyOptional<IItemHandler> handlerBottom = LazyOptional.of(() -> {   });
       return new SidedInvWrapper(this, Direction.DOWN);   private LazyOptional<IItemHandler> handlerSide = LazyOptional.of(() -> {   });
       return new SidedInvWrapper(this, Direction.WEST);
    });
 
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
       if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
          if (facing == Direction.DOWN) {
             return this.handlerBottom.cast();         } else {
             return facing == Direction.UP ? this.handlerTop.cast() : this.handlerSide.cast();
 
 
 
          }
       } else {
          return super.getCapability(capability, facing);
       }
    }
 
    public void func_145843_s() {
       super.func_145843_s();
       this.handlerBottom.invalidate();
       this.handlerSide.invalidate();
       this.handlerTop.invalidate();
    }
 
 
    public void func_194018_a(RecipeItemHelper helper) {
       Iterator var2 = this.contents.iterator();      while(var2.hasNext()) {         ItemStack itemstack = (ItemStack)var2.next();
          helper.func_194112_a(itemstack);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 66 ms
	
	Decompiled with FernFlower.
*/