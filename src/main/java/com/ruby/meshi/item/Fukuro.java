 package com.ruby.meshi.item;
 
 import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ruby.meshi.common.ItemCraftedEventHandler;
import com.ruby.meshi.common.ItemCraftedEventHandler.CraftedEventWrapper;
import com.ruby.meshi.item.Fukuro.DummyContext;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.core.NonNullList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 
 
 
 
 
 
 
 
 public class Fukuro extends Item {
    public static final String STORAGE = "storage";
    public static final String COUNT = "excount";
    public static final int STORAGE_SIZE = 1024;
 
    public Fukuro(Properties properties) {
       super(properties);
       ItemCraftedEventHandler.register(this.getCraftEvent());
    }
 
    Consumer<CraftedEventWrapper> getCraftEvent() {
       return (event) -> {
          if (event.crafting.func_77973_b() == HiganItems.FUKURO) {
             Optional<ItemStack> stack = event.getFindFirst((s) -> {               return s.func_77973_b() == HiganItems.FUKURO;            });
             if (stack.isPresent()) {
                ItemStack storageStack = getStorageItem((ItemStack)stack.get());
                if (!storageStack.func_190926_b()) {
                   event.player.func_71019_a(storageStack, false);
                }            }
          }
 
       };
    }
 
 
    public ActionResultType func_195939_a(ItemUseContext context) {
       ItemStack stack = context.func_195996_i();
       ItemStack storageStack = getStorageItem(stack);
       if (!storageStack.func_190926_b() && storageStack.func_77973_b() instanceof BlockItem && storageStack.func_196084_a(new DummyContext(context, storageStack)) == ActionResultType.SUCCESS) {
 
 
          setStorageItem(stack, storageStack);
          return ActionResultType.SUCCESS;
 
 
       } else {
          return super.func_195939_a(context);
       }
    }
 
    public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
       ItemStack stack = playerIn.func_184586_b(handIn);
       ItemStack storageStack = getStorageItem(stack);
 
       if (!worldIn.field_72995_K) {
          if (!storageStack.func_190926_b()) {
             NonNullList<ItemStack> inv = playerIn.field_71071_by.field_70462_a;
             storageStack.func_190920_e(this.searchInventory(inv, storageStack, storageStack.func_190916_E()));
             setStorageItem(stack, storageStack);
             return new ActionResult(ActionResultType.SUCCESS, stack);
          }
 
          playerIn.func_184598_c(handIn);
          playerIn.func_213829_a(new Provider(stack));
          playerIn.func_71029_a(Stats.field_199092_j.func_199076_b(Stats.field_188062_ab));
 
       }
 
       return super.func_77659_a(worldIn, playerIn, handIn);
    }
 
    int searchInventory(NonNullList<ItemStack> list, ItemStack target, int now) {
       int count = now;
       for(int i = 0; i < list.size(); ++i) {
          ItemStack stack = (ItemStack)list.get(i);
          if (ItemStack.func_179545_c(stack, target)) {
             int stackCount = stack.func_190916_E();
             if (count + stackCount >= 1024) {            }
             count += stackCount;
             list.set(i, ItemStack.field_190927_a);
 
                stack.func_190920_e(stack.func_190916_E() - (1024 - count));
                count = 1024;
                break;
          }
       }
 
       return count;
    }
 
 
    public ITextComponent func_200295_i(ItemStack stack) {
       ItemStack strageStack = getStorageItem(stack);
       return strageStack.func_190926_b() ? super.func_200295_i(strageStack) : (new TranslationTextComponent(this.func_77667_c(stack), new Object[0])).func_150258_a(" (" + (new TranslationTextComponent(strageStack.func_77977_a(), new Object[0])).func_150254_d() + ")");
    }
 
 
 
 
 
    public boolean showDurabilityBar(ItemStack stack) {
       return !getStorageItem(stack).func_190926_b();
    }
 
 
    public double getDurabilityForDisplay(ItemStack stack) {
       return 1.0D - (double)getStorageItem(stack).func_190916_E() / 1024.0D;
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
       tooltip.add((new TranslationTextComponent("Count", new Object[0])).func_150258_a(" ").func_150258_a(getStorageItem(stack).func_190916_E() + "/" + 1024));
    }
 
 
    public int func_77626_a(ItemStack stack) {
       return 32000;
    }
 
    public static ItemStack getStrageSplitStack(ItemStack stack, int amount) {
       ItemStack storagedStack = getStorageItem(stack);
       ItemStack ret = amount >= 0 && !storagedStack.func_190926_b() ? storagedStack.func_77979_a(amount) : ItemStack.field_190927_a;
       setStorageItem(stack, storagedStack);
       return ret;
    }
 
    public static ItemStack getStorageItem(ItemStack stack) {
       CompoundNBT nbt = stack.func_190925_c("storage");
       ItemStack storageStack = ItemStack.func_199557_a(nbt);
       storageStack.func_190920_e(nbt.func_74762_e("excount"));
       return storageStack;
    }
 
    public static void setStorageItem(ItemStack parent, ItemStack child) {
       CompoundNBT nbt = parent.func_190925_c("storage");
       nbt.func_74768_a("excount", child.func_190916_E());
       child.func_77955_b(nbt);
    }
 
    public static ItemStack removeStorage(ItemStack stack) {
       ItemStack storageStack = getStorageItem(stack);
       setStorageItem(stack, ItemStack.field_190927_a);
       return storageStack;
    }
 
    public static boolean isItemValid(ItemStack stack) {
       Item item = stack.func_77973_b();
       return item instanceof BlockItem;
    }
 
 
 
 
 
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
       return slotChanged || !ItemStack.func_179545_c(oldStack, newStack);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 29 ms
	
	Decompiled with FernFlower.
*/