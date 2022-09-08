 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.block.Hearth;
 import com.ruby.meshi.block.Hearth.HearthStateType;
 import com.ruby.meshi.block.tileentity.HearthTileEntity.1;
 import com.ruby.meshi.block.tileentity.HearthTileEntity.FurnaceInventory;
 import com.ruby.meshi.common.inventory.HearthContainer;
 import com.ruby.meshi.crafting.CookingTimerRecipe;
 import com.ruby.meshi.crafting.HearthRecipe;
 import com.ruby.meshi.crafting.HearthShapelessRecipe;
 import com.ruby.meshi.init.HiganTileEntityType;
 import java.util.Arrays;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Optional;
 import java.util.Random;
 import java.util.Map.Entry;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.function.BiFunction;
 import java.util.function.Consumer;
 import java.util.function.Function;
 import java.util.function.IntConsumer;
 import java.util.function.IntFunction;
 import java.util.function.IntPredicate;
 import java.util.function.Predicate;
 import java.util.stream.IntStream;
 import java.util.stream.Stream;
 import net.minecraft.block.BlockState;
 import net.minecraft.enchantment.Enchantment;
 import net.minecraft.enchantment.EnchantmentHelper;
 import net.minecraft.enchantment.Enchantments;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.PlayerInventory;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.IRecipeHelperPopulator;
 import net.minecraft.inventory.ItemStackHelper;
 import net.minecraft.inventory.container.Container;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.TieredItem;
 import net.minecraft.item.crafting.AbstractCookingRecipe;
 import net.minecraft.item.crafting.FurnaceRecipe;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.IRecipeType;
 import net.minecraft.item.crafting.RecipeItemHelper;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.network.NetworkManager;
 import net.minecraft.network.play.server.SUpdateTileEntityPacket;
 import net.minecraft.particles.IParticleData;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.tileentity.ITickableTileEntity;
 import net.minecraft.tileentity.LockableTileEntity;
 import net.minecraft.util.IntArray;
 import net.minecraft.util.NonNullList;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.TranslationTextComponent;import net.minecraft.world.World;import net.minecraft.world.server.ServerWorld;
 public class HearthTileEntity extends LockableTileEntity implements ITickableTileEntity, IRecipeHelperPopulator {
    private NonNullList<ItemStack> contents;
    public static final int INV_SIZE = 10;
    public static final int[] INPUT_SLOT = IntStream.range(1, 10).toArray();
    public static final int OUT_MAIN = 0;
    public static final int ENCHANT_BASE = 2;
    public static final int ENCHANT_SUB = 8;
    private int progress;
    public float now_roll;
    public float next_roll;
    private static final float ROLL_SPEED = 0.017453292F;
    private static FurnaceInventory furnaceInv = new FurnaceInventory((1)null);
    public static final ThreadLocalRandom rand = ThreadLocalRandom.current();
 
    public HearthTileEntity() {
       super(HiganTileEntityType.HEARTH);
       this.contents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       this.next_roll = (float)Math.random();
       this.reset();
    }
 
 
    public void func_73660_a() {
       int magnification = 1;
       if (!this.func_145831_w().field_72995_K) {
          if (this.progress <= 0) {
             if (this.canCooking()) {
                this.progress = this.getCookTime();
                this.func_70296_d();
             }
          } else {
             this.progress -= magnification;
             if (this.progress <= 0) {
                this.cookItem();
                this.reset();
                this.func_70296_d();
             }
          }
       } else {
          this.now_roll = this.next_roll;
          if ((double)this.next_roll >= 6.283185307179586D) {
             this.next_roll = this.now_roll = 0.017453292F;
          }
          this.next_roll += 0.017453292F;
       }
    }
 
    private void cookItem() {
       if (!this.isInputEmpty()) {
          Optional<? extends IRecipe<? extends IInventory>> recipe = this.getRecipe();
          if (recipe.isPresent()) {
             ItemStack output = ((IRecipe)recipe.get()).func_77571_b().func_77946_l();
 
             if (hasEnchantRecipe(((IRecipe)recipe.get()).func_199560_c())) {
                output = this.enchantReBuild((ItemStack)this.contents.get(2), (ItemStack)this.contents.get(8));
 
             }
 
             boolean crit = false;
             if (output.func_190916_E() > 1) {
                crit = rand.nextBoolean();
 
             } else if (output.func_77976_d() > 1) {
                crit = rand.nextFloat() < 0.2F;
             }
 
             IParticleData data = ParticleTypes.field_197613_f;
             if (crit) {
                int count = output.func_190916_E();
                if (count < output.func_77976_d()) {
                   output.func_190920_e(count + 1);
                }
                data = ParticleTypes.field_197632_y;
             }
             double d0 = 0.25D;
             double d1 = rand.nextGaussian() * 0.02D;
             double d2 = 0.25D;
             ((ServerWorld)this.func_145831_w()).func_195598_a(data, (double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.75D, (double)this.field_174879_c.func_177952_p() + 0.5D, 8, d0, d1, d2, 0.0D);
 
             this.insertInventorySlotContents(0, output);
 
             Arrays.stream(INPUT_SLOT).forEach((i) -> {
                this.func_70298_a(i, 1);            });         }      }
 
    }
 
    public static boolean hasEnchantRecipe(ResourceLocation resourceLocation) {
       return resourceLocation.toString().matches("meshi:hearth/enchant_.*");
    }
 
    private void reset() {
       this.progress = -1;
    }
 
    private void setParts(ItemStack stack) {
       this.func_145831_w().func_175656_a(this.field_174879_c, (BlockState)this.func_195044_w().func_206870_a(Hearth.TYPE, HearthStateType.getType(stack)));
    }
 
    private void removeParts() {
       this.func_145831_w().func_175656_a(this.field_174879_c, (BlockState)this.func_195044_w().func_206870_a(Hearth.TYPE, HearthStateType.NONE));
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
 
 
 
    private boolean canCooking() {
       return this.isInputEmpty() ? false : this.isExistenceRacipe(this.getRecipe());
    }
 
 
 
 
 
    private boolean isExistenceRacipe(Optional<? extends IRecipe<?>> optional) {
       if (optional.isPresent()) {
          ItemStack output = ((IRecipe)optional.get()).func_77571_b();
          return this.hasInsert(0, output);
       } else {
          return false;
       }
    }
    public Optional<? extends IRecipe<?>> getRecipe() {
       Optional<? extends IRecipe<?>> recipe = this.getSmeltingRecipe();
       if (this.isExistenceRacipe(recipe)) {
          return recipe;
       } else {
          recipe = this.getRecipe(HearthShapelessRecipe.TYPE, this, this.func_145831_w());
          return this.isExistenceRacipe(recipe) ? recipe : this.getRecipe(HearthRecipe.TYPE, this, this.func_145831_w());
       }
    }
 
 
 
    public int getCookTime() {
       return (Integer)this.getRecipe().filter((r) -> {
          return r instanceof CookingTimerRecipe;
       }).map((r) -> {
          return ((CookingTimerRecipe)r).getCookTime();   private boolean hasInsert(int slot, ItemStack stack) {      }).orElse(this.getSmeltingTime());
       if (this.isSlotEmpty(slot)) {   }
          return true;
 
       } else if (stack != null && !stack.func_190926_b() && this.func_70301_a(slot).func_77969_a(stack)) {      } else {
          return false;
 
          int outResult = this.func_70301_a(slot).func_190916_E() + stack.func_190916_E();
          return outResult <= this.func_70297_j_() && outResult <= stack.func_77976_d();
       }
    }
    private boolean isInputEmpty() {
       return Arrays.stream(INPUT_SLOT).allMatch(this::isSlotEmpty);
    }
 
    private boolean isSlotEmpty(int slot) {
       return ((ItemStack)this.contents.get(slot)).func_190926_b();
    }
 
    public Optional<? extends FurnaceRecipe> getSmeltingRecipe() {
       return this.getRecipe(IRecipeType.field_222150_b, furnaceInv.setDummyStack(this.getInputStream()), this.func_145831_w());
    }
 
    public int getSmeltingTime() {
       return (Integer)this.getSmeltingRecipe().map(AbstractCookingRecipe::func_222137_e).orElse(200) * 4;
    }
 
    public <T extends IRecipe<U>, U extends IInventory> Optional<T> getRecipe(IRecipeType<T> type, U inv, World world) {
       return this.func_145831_w().func_199532_z().func_215371_a(type, inv, world);
    }
 
 
    public int func_70302_i_() {
       return 10;
    }
 
 
    public boolean func_191420_l() {
       return this.contents.stream().allMatch(ItemStack::func_190926_b);
    }
 
 
    public ItemStack func_70301_a(int index) {
       return (ItemStack)this.contents.get(index);
    }
 
 
    public ItemStack func_70298_a(int index, int count) {
       ItemStack itemstack = ItemStackHelper.func_188382_a(this.contents, index, count);
       if (!itemstack.func_190926_b()) {
          this.func_70296_d();
       }
       if (this.isInputSlotNum(index) && !this.canCooking()) {
 
          this.reset();
 
       }
 
       return itemstack;
    }
 
 
    public ItemStack func_70304_b(int index) {
       return ItemStackHelper.func_188383_a(this.contents, index);
    }
 
 
    public void func_70299_a(int index, ItemStack stack) {
       if (this.isInputSlotNum(index) && !this.func_70301_a(index).func_77969_a(stack)) {
 
          this.reset();
       }
 
       this.contents.set(index, stack);
       if (stack.func_190916_E() > this.func_70297_j_()) {
          stack.func_190920_e(this.func_70297_j_());
       }
 
       if (this.canCooking()) {
          this.setParts(((IRecipe)this.getRecipe().get()).func_77571_b());
       } else if (this.func_70301_a(0).func_190926_b() && this.func_195044_w().func_177229_b(Hearth.TYPE) != HearthStateType.NONE) {
 
          this.removeParts();
 
       }
 
       this.func_70296_d();
    }
 
    public boolean isInputSlotNum(int slot) {
       return slot >= INPUT_SLOT[0] && slot <= INPUT_SLOT[INPUT_SLOT.length - 1];
    }
 
 
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
 
 
    protected ITextComponent func_213907_g() {
       return new TranslationTextComponent(String.join(".", "container", "meshi", this.func_195044_w().func_177230_c().getRegistryName().func_110623_a()), new Object[0]);
    }
 
 
    protected Container func_213906_a(int id, PlayerInventory player) {
       IntArray intArray = new IntArray(2);
       intArray.func_221477_a(0, this.progress);
       intArray.func_221477_a(1, this.getCookTime());
       return new HearthContainer(id, player, this, intArray);
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
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.contents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
       this.readData(compound);
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
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
 
    public int getProgress() {
       return this.progress;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    public void func_194018_a(RecipeItemHelper helper) {
       this.getInputStream().forEach(helper::func_195932_a);
    }
 
    private Stream<ItemStack> getInputStream() {
       IntStream var10000 = Arrays.stream(INPUT_SLOT);
       NonNullList var10001 = this.contents;
       this.contents.getClass();
       return var10000.mapToObj(var10001::get);
    }   private ItemStack enchantReBuild(ItemStack base, ItemStack parts) {
       String KEY_ENCHANTMENTS = "Enchantments";
       Random rand = this.func_145831_w().func_201674_k();
       ItemStack stack = base.func_77946_l();
 
       stack.func_196085_b(0);
 
       ItemStack baseCopy = base.func_77946_l();
       ItemStack partsCopy = parts.func_77946_l();
       ListNBT baseEnc = base.func_77986_q();
       ListNBT partsEnc = parts.func_77986_q();
 
 
       int baseTier = this.getEncCount(base);
       int partsTier = this.getEncCount(parts);
 
       partsCopy.func_196082_o().func_218657_a(KEY_ENCHANTMENTS, this.subListNBT(partsEnc, 0, partsTier));
       Map<Enchantment, Integer> baseEncMap = EnchantmentHelper.func_82781_a(baseCopy);
       Map<Enchantment, Integer> partsEncMap = EnchantmentHelper.func_82781_a(partsCopy);
 
       if (partsEnc.isEmpty()) {
          return stack;      } else {
          if (baseEnc.isEmpty()) {
             EnchantmentHelper.func_82782_a(partsEncMap, stack);
 
 
 
 
          } else {
             int materialTier = Math.max(0, Math.min(baseTier, partsTier) - 1);
             Iterator var15 = partsEncMap.entrySet().iterator();            while(var15.hasNext()) {               Entry<Enchantment, Integer> entry = (Entry)var15.next();
                int maxLV = ((Enchantment)entry.getKey()).getMaxLevel();
                baseEncMap.merge(entry.getKey(), entry.getValue(), (b, p) -> {
 
 
 
 
 
 
                   return maxLV > 1 ? Math.min(maxLV + materialTier, this.calcMergeLv(b, p)) : 1;
                });
             }
 
             if (this.isCurse(rand, baseEncMap)) {
                baseEncMap.put(Enchantments.field_190940_C, 1);
             }
 
             EnchantmentHelper.func_82782_a(baseEncMap, stack);
          }
 
          return stack;
       }
    }
    private boolean isCurse(Random rand, Map<Enchantment, Integer> enchantMap) {
       int curse = -5;
       Entry entry;      int maxLV;
       for(Iterator var4 = enchantMap.entrySet().iterator(); var4.hasNext(); curse = (int)((double)curse + Math.pow((double)Math.max(0, (Integer)entry.getValue() - maxLV), 2.0D) * 5.0D)) {         entry = (Entry)var4.next();
          maxLV = ((Enchantment)entry.getKey()).getMaxLevel();
 
       }
 
       if (curse > 0 && rand.nextFloat() <= 0.97F) {      } else {
          return false;
 
          return rand.nextInt(100) < curse;
       }
    }
    private int calcMergeLv(int i1, int i2) {
       return i1 == i2 ? i1 + 1 : (int)Math.ceil((double)(i1 + i2) / 2.0D);
    }
 
 
 
 
    private int getEncCount(ItemStack stack) {
       return stack.func_77973_b() instanceof TieredItem ? Math.max(1, ((TieredItem)stack.func_77973_b()).func_200891_e().func_200925_d()) : 1;
    }
 
 
 
 
    private ListNBT subListNBT(ListNBT org, int from, int to) {
       ListNBT list = new ListNBT();
       to = Math.min(to, org.size());
       for(int i = from; i < to; ++i) {
          list.add(org.func_150305_b(i));
       }
       return list;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 79 ms
	
	Decompiled with FernFlower.
*/