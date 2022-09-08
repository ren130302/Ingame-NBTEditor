 package com.ruby.meshi.block.tileentity;
 
 import com.ruby.meshi.block.SlideDoor;
import com.ruby.meshi.init.HiganTileEntityType;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
 
 
 public class SlideDoorTileEntity extends TileEntity implements ITickableTileEntity {
    public float posX;
    public float posZ;
    public float nowPosX;
    public float nowPosZ;
 
    public SlideDoorTileEntity() {
       super(HiganTileEntityType.SLIDEDOOR);
    }
 
 
    public void func_73660_a() {
       this.nowPosX = this.posX;
       this.nowPosZ = this.posZ;
       Direction facing = ((Direction)this.func_195044_w().func_177229_b(SlideDoor.field_176520_a)).func_176735_f();
       if (this.func_195044_w().func_177229_b(SlideDoor.field_176521_M) == DoorHingeSide.RIGHT) {
          facing = facing.func_176734_d();
       }
       boolean isOpen = (Boolean)this.func_195044_w().func_177229_b(SlideDoor.field_176519_b);
       if (isOpen) {
          this.posX = (float)facing.func_82601_c();
          this.posZ = (float)facing.func_82599_e();
          if (this.nowPosX != this.posX && this.nowPosZ != this.posZ && !this.func_145831_w().field_72995_K) {
 
             this.func_145831_w().func_180501_a(this.field_174879_c, (BlockState)this.func_195044_w().func_206870_a(SlideDoor.MOVED, true), 2);
 
          }
       } else {
          this.posX = 0.0F;
          this.posZ = 0.0F;
          if (this.nowPosX == 0.0F && this.nowPosZ == 0.0F) {
             if (!this.func_145831_w().field_72995_K) {
                this.func_145831_w().func_180501_a(this.func_174877_v(), (BlockState)this.func_195044_w().func_206870_a(SlideDoor.MOVED, false), 2);
             }
             this.func_145831_w().func_175713_t(this.func_174877_v());
          }      }
 
    }
 
 
    public boolean hasFastRenderer() {
       return true;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 8 ms
	
	Decompiled with FernFlower.
*/