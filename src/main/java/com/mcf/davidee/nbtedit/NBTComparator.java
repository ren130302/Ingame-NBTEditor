package com.mcf.davidee.nbtedit;

import java.util.Comparator;

import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.nbt.Node;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class NBTComparator implements Comparator<Node<NamedNBT>> {

	@Override
	public int compare(Node<NamedNBT> o1, Node<NamedNBT> o2) {
		final Tag n1 = o1.object().tag();
		final Tag n2 = o2.object().tag();
		final String s1 = o1.object().name();
		final String s2 = o2.object().name();
		final boolean lc1 = n1 instanceof CompoundTag || n1 instanceof ListTag;
		final boolean lc2 = n2 instanceof CompoundTag || n2 instanceof ListTag;
		final int dif = n1.getId() - n2.getId();
		final int ret = (dif == 0) ? s1.compareTo(s2) : dif;

		return lc1 ? (lc2 ? ret : 1) : (lc2 ? -1 : ret);
	}
}
