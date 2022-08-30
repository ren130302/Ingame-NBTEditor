package com.ren130302.nbtedit.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class NamedNBT implements Comparable<NamedNBT> {

	private String name;
	private Tag tag;

	private NamedNBT(String name, Tag tag) {
		this.name = name;
		this.tag = tag;
	}

	public static NamedNBT of(Tag tag) {
		return of("", tag);
	}

	public static NamedNBT of(String name, Tag tag) {
		return new NamedNBT(name, tag);
	}

	public String name() {
		return this.name;
	}

	public NamedNBT name(String name) {
		this.name = name;
		return this;
	}

	public Tag tag() {
		return this.tag;
	}

	public NamedNBT tag(Tag tag) {
		this.tag = tag;
		return this;
	}

	public NamedNBT copy() {
		return new NamedNBT(this.name, this.tag.copy());
	}

	@Override
	public int compareTo(NamedNBT that) {
		Tag n1 = this.tag();
		Tag n2 = that.tag();
		String s1 = this.name();
		String s2 = that.name();
		boolean lc1 = n1 instanceof CompoundTag || n1 instanceof ListTag;
		boolean lc2 = n2 instanceof CompoundTag || n2 instanceof ListTag;
		if (lc1) {
			if (lc2) {
				int dif = n1.getId() - n2.getId();
				return (dif == 0) ? s1.compareTo(s2) : dif;
			} else {
				return 1;
			}
		}
		if (lc2) {
			return -1;
		}
		int dif = n1.getId() - n2.getId();
		return (dif == 0) ? s1.compareTo(s2) : dif;
	}

	@Override
	public String toString() {
		return "NamedNBT[key=" + this.name + ",  value=" + this.tag + "]";
	}
}