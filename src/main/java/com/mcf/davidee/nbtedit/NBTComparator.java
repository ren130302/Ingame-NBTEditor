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
		Tag n1 = o1.object().tag();
		Tag n2 = o2.object().tag();
		String s1 = o1.object().name();
		String s2 = o2.object().name();
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

}
