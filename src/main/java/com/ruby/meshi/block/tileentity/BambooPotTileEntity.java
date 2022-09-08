 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.init.HiganTileEntityType;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
 
 
 
 public class BambooPotTileEntity extends TileEntity {
    public BambooPotTileEntity(TileEntityType<?> tileEntityTypeIn) {
       super(tileEntityTypeIn);
    }
 
    public BambooPotTileEntity() {
       this(HiganTileEntityType.BAMBOO_POT);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 4 ms
	
	Decompiled with FernFlower.
*/