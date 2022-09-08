 package com.ruby.meshi.init;
 
 import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
 
 
 
 
 
 
 
 
 
 
 public class SakuraConfig {
    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;
 
    static {
       Pair<Client, ForgeConfigSpec> specPair = (new Builder()).configure(Client::new);
       clientSpec = (ForgeConfigSpec)specPair.getRight();
       CLIENT = (Client)specPair.getLeft();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/