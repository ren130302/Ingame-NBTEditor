 package com.ruby.meshi.client;
 
 import com.ruby.meshi.client.CreativeTab.1;
 import com.ruby.meshi.client.CreativeTab.2;
 import com.ruby.meshi.enchant.HiganEnchantType;
 import net.minecraft.enchantment.EnchantmentType;
 import net.minecraft.item.ItemGroup;
 
 
 
 
 
 public class CreativeTab {
    public static final ItemGroup ITEM_GROUP;
    public static final ItemGroup DECO_GROUP;
 
    static {
       ITEM_GROUP = (new 1("meshi.base")).func_111229_a(new EnchantmentType[]{HiganEnchantType.BRACELET});
 
 
 
 
 
 
       DECO_GROUP = new 2("meshi.deco");
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 4 ms
	
	Decompiled with FernFlower.
*/