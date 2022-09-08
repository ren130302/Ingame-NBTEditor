 package com.ruby.meshi.client.renderer.animation;
 
 import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
 
 
 
 
 public class AnimationSet {
    public static List<EntityModelAnimation> createSwingHead(Direction direction, boolean hand) {
       List<EntityModelAnimation> list = Lists.newArrayList();
       EntityModelAnimation head = new SwingHead(direction);
       list.add(head);
       if (hand) {
          list.add((new ViewHand(direction)).setParent(head));
       }
       return list;
    }
 
    public static List<EntityModelAnimation> createWatchHead(Direction direction, BlockPos pos, Entity entity, boolean hand) {
       List<EntityModelAnimation> list = Lists.newArrayList();
       EntityModelAnimation head = (new WatchEntity(direction, pos)).setTarget(entity);
       list.add(head);
       if (hand) {
          list.add((new ViewHand(direction)).setParent(head));
       }
       return list;
    }
 
    public static List<EntityModelAnimation> createTail(Direction direction) {
       List<EntityModelAnimation> list = Lists.newArrayList();
       list.add(new SwingTail(direction));
       return list;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/