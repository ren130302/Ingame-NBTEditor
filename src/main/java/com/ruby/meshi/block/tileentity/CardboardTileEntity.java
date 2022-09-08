 package com.ruby.meshi.block.tileentity;
 
 import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.ruby.meshi.block.Cardboard;
import com.ruby.meshi.client.renderer.animation.AnimationSet;
import com.ruby.meshi.client.renderer.animation.AnimationTile;
import com.ruby.meshi.client.renderer.animation.EntityModelAnimation;
import com.ruby.meshi.init.HiganTileEntityType;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 @OnlyIn(
    value = Dist.CLIENT,
    _interface = AnimationTile.class
 )
 public class CardboardTileEntity extends TileEntity implements ITickableTileEntity, AnimationTile {
    private CompoundNBT nbt;
    private static final String CAT = "cat";
    private static final String CAT_DATA = "cat_data";
    @OnlyIn(Dist.CLIENT)
    private List<EntityModelAnimation> animation;
    public boolean isOccupied = false;
 
    public CardboardTileEntity() {
       super(HiganTileEntityType.CARDBOARD);
    }
 
 
    public void func_73660_a() {
       if (this.func_145831_w().field_72995_K) {
          this.animateTick();
       }
    }
 
 
    public void animateTick() {
       if (this.getAnimations().isEmpty()) {
          int animeNo = this.field_145850_b.func_201674_k().nextInt(3);
          if (animeNo == 0) {
             this.getAnimations().addAll(AnimationSet.createSwingHead(this.getDirection(), true));
          } else if (animeNo == 1) {
             this.getAnimations().addAll(AnimationSet.createWatchHead(this.getDirection(), this.func_174877_v(), this.searchEntity((e) -> {
                return e instanceof LivingEntity;            }), true));         } else {
             this.getAnimations().addAll(AnimationSet.createTail(this.getDirection()));
 
          }
       } else if (!this.hasCatNBT()) {
          this.getAnimations().clear();
 
       }
 
       this.getAnimations().forEach(EntityModelAnimation::animationTick);
    }
 
    public Entity searchEntity(Predicate<Entity> tester) {
       return (Entity)this.field_145850_b.func_175674_a((Entity)null, (new AxisAlignedBB(this.field_174879_c)).func_186662_g(10.0D), tester).stream().findAny().orElse((Object)null);
    }
 
    private Direction getDirection() {
       return (Direction)this.func_195044_w().func_177229_b(Cardboard.field_185512_D);
    }
 
    public void compoundCat(CatEntity cat) {
       this.getNBT().func_218657_a("cat_data", cat.func_189511_e(new CompoundNBT()));
       cat.func_70106_y();
    }
 
    public void createCatFromNBT() {
       if (this.hasCatNBT()) {
          CatEntity cat = (CatEntity)EntityType.field_220360_g.func_200721_a(this.func_145831_w());
          cat.func_70020_e(this.getCatNBT());
          this.func_145831_w().func_217376_c(cat);
          this.nbt = null;
       }
    }
 
    public boolean hasCatNBT() {
       return this.nbt != null && !this.nbt.isEmpty();
    }
 
    public int getCatType() {
       return this.nbt != null && !this.nbt.isEmpty() ? this.getCatNBT().func_74762_e("CatType") : 0;
    }
 
 
 
 
    @Nullable
    public CompoundNBT getCatNBT() {
       return this.nbt != null && !this.nbt.isEmpty() ? this.nbt.func_74775_l("cat_data") : null;
    }
 
 
 
 
    private CompoundNBT getNBT() {
       if (this.nbt == null) {
          this.nbt = new CompoundNBT();
       }
       return this.nbt;
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.readData(compound);
       this.isOccupied = this.hasCatNBT();
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       super.func_189515_b(compound);
       return this.writeData(compound);
    }
 
    public void readData(CompoundNBT compound) {
       this.nbt = compound.func_74775_l("cat");
    }
 
    public CompoundNBT writeData(CompoundNBT compound) {
       if (this.nbt != null && !this.nbt.isEmpty()) {
          compound.func_218657_a("cat", this.nbt);
       }
       return compound;
    }
 
 
    public void handleUpdateTag(CompoundNBT tag) {
       super.handleUpdateTag(tag);
       this.readData(tag);
    }
 
 
    public CompoundNBT func_189517_E_() {
       CompoundNBT tag = super.func_189517_E_();
       this.writeData(tag);
       return tag;
    }
 
 
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       this.readData(pkt.func_148857_g());
    }
 
 
    public SUpdateTileEntityPacket func_189518_D_() {
       CompoundNBT var1 = new CompoundNBT();
       this.writeData(var1);
       return new SUpdateTileEntityPacket(this.func_174877_v(), 5, var1);
    }
 
 
    public List<EntityModelAnimation> getAnimations() {
       if (this.animation == null) {
          this.animation = Lists.newArrayList();
       }
       return this.animation;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 29 ms
	
	Decompiled with FernFlower.
*/