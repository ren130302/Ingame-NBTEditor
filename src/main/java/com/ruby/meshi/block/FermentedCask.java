 package com.ruby.meshi.block;
 
 import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.TempCategory;
 
 public class FermentedCask extends Block {
    public FermentedCask(Properties properties) {
       super(properties);
    }
 
 
    public void getType(World worldIn, BlockPos pos) {
       TempCategory temp = worldIn.func_180494_b(pos).func_150561_m();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 4 ms
	
	Decompiled with FernFlower.
*/