 package com.ruby.meshi.item;
 
 import java.util.List;

import javax.annotation.Nullable;

import com.ruby.meshi.entity.ShurikenEntity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 
 
 
 
 public class Shuriken extends Item {
    private final IItemTier tier;
 
    public Shuriken(IItemTier tierIn, Properties builder) {
       super(builder);
       this.tier = tierIn;
    }
 
    public IItemTier getTier() {
       return this.tier;
    }
 
 
    public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
       ItemStack itemstack = playerIn.func_184586_b(handIn);
       if (!playerIn.field_71075_bZ.field_75098_d) {
          itemstack.func_190918_g(1);
       }
 
       worldIn.func_184148_a((PlayerEntity)null, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundEvents.field_187797_fA, SoundCategory.PLAYERS, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
       if (!worldIn.field_72995_K) {
          ShurikenEntity entity = new ShurikenEntity(playerIn, worldIn);
          entity.setItemStack(itemstack);
          entity.func_184547_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0F, 0.8F, 5.0F);
          worldIn.func_217376_c(entity);
          playerIn.func_184811_cZ().func_185145_a(this, 25);
       }
 
       playerIn.func_71029_a(Stats.field_75929_E.func_199076_b(this));
       return new ActionResult(ActionResultType.SUCCESS, itemstack);
    }
 
 
    public boolean func_82789_a(ItemStack toRepair, ItemStack repair) {
       return false;
    }
 
    public float getAttackDamage(ItemStack itemStack) {
       return this.tier.func_200929_c();
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
       tooltip.add((new TranslationTextComponent("attribute.modifier.plus." + Operation.ADDITION.func_220371_a(), new Object[]{ItemStack.field_111284_a.format((double)this.getAttackDamage(stack)), new TranslationTextComponent("tooltip.meshi.throwing_damage", new Object[0])})).func_211708_a(TextFormatting.DARK_GREEN));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 12 ms
	
	Decompiled with FernFlower.
*/