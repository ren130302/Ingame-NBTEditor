// Warning: No line numbers available in class file
package com.ruby.meshi.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ExtraParticle {
   @OnlyIn(Dist.CLIENT)
   void paticleTick(BlockState var1, World var2, BlockPos var3, Random var4);
}

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/