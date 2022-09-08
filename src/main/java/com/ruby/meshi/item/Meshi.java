 package com.ruby.meshi.item;
 
 import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 
 
 
 
 public class Meshi extends Item {
    public Meshi(Properties properties) {
       super(properties);
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
       String key = "desc.meshi." + this.getRegistryName().func_110623_a();
       TranslationTextComponent text = new TranslationTextComponent(key, new Object[0]);
       text.func_211708_a(TextFormatting.GOLD);
       if (!text.getString().equals(key)) {
          tooltip.add(text);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/