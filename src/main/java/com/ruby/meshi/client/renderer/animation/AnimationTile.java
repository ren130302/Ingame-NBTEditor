// Warning: No line numbers available in class file
package com.ruby.meshi.client.renderer.animation;

import java.util.List;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface AnimationTile {
   @OnlyIn(Dist.CLIENT)
   List<EntityModelAnimation> getAnimations();
}

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 2 ms
	
	Decompiled with FernFlower.
*/