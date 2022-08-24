package com.mcf.davidee.nbtedit.nbt;

import java.util.Objects;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public class NBTTarget {
	private Entity entity;
	private BlockPos pos;
	private final String affix;
	private final CompoundTag tag;

	private NBTTarget(Entity entity, CompoundTag tag) {
		this.entity = entity;
		this.tag = tag;
		this.affix = "Entity ID #" + entity.getId();
	}

	private NBTTarget(BlockPos pos, CompoundTag tag) {
		this.pos = pos;
		this.tag = tag;
		this.affix = "TileEntity at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
	}

	public static NBTTarget of(Entity entity) {
		return of(entity, null);
	}

	public static NBTTarget of(BlockPos pos) {
		return of(pos, null);
	}

	public static NBTTarget of(Entity entity, CompoundTag tag) {
		return new NBTTarget(entity, tag);
	}

	public static NBTTarget of(BlockPos pos, CompoundTag tag) {
		return new NBTTarget(pos, tag);
	}

	public boolean hasEntity() {
		return Objects.nonNull(this.entity);
	}

	public boolean hasPos() {
		return Objects.nonNull(this.pos);
	}

	@Override
	public String toString() {
		return this.affix;
	}

	public Entity entity() {
		return this.entity;
	}

	public BlockPos pos() {
		return this.pos;
	}

	public boolean hasTag() {
		return Objects.nonNull(this.tag);
	}

	public CompoundTag tag() {
		return this.tag;
	}
}
