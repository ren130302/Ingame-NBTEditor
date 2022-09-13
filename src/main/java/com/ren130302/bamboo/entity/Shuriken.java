package com.ren130302.bamboo.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class Shuriken extends Entity {

    public Shuriken(EntityType<?> entityType, Level level) {
	super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
	// TODO 自動生成されたメソッド・スタブ

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
	// TODO 自動生成されたメソッド・スタブ

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
	// TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public Packet<?> getAddEntityPacket() {
	// TODO 自動生成されたメソッド・スタブ
	return null;
    }

}
