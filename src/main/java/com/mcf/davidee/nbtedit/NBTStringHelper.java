package com.mcf.davidee.nbtedit;

import com.google.common.base.Strings;
import com.mcf.davidee.nbtedit.nbt.NamedNBT;

import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.EndTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

public class NBTStringHelper {

	public static final char SECTION_SIGN = '\u00A7';

	public static final String SEPARATOR_SIGN = System.getProperty("line.separator");

	public static String getNBTName(NamedNBT namedNBT) {
		String name = namedNBT.name();
		Tag obj = namedNBT.tag();

		String s = toString(obj);
		return Strings.isNullOrEmpty(name) ? "" + s : name + ": " + s;
	}

	public static String getNBTNameSpecial(NamedNBT namedNBT) {
		String name = namedNBT.name();
		Tag obj = namedNBT.tag();

		String s = toString(obj);
		return Strings.isNullOrEmpty(name) ? "" + s : name + ": " + s + SECTION_SIGN + 'r';
	}

	public static Tag newTag(byte type) {
		switch (type) {
		case 0:
			return EndTag.INSTANCE;
		case 1:
			return ByteTag.valueOf((byte) 0);
		case 2:
			return ShortTag.valueOf((short) 0);
		case 3:
			return IntTag.valueOf(0);
		case 4:
			return LongTag.valueOf(0l);
		case 5:
			return FloatTag.valueOf(0f);
		case 6:
			return DoubleTag.valueOf(0d);
		case 7:
			return new ByteArrayTag(new byte[0]);
		case 8:
			return StringTag.valueOf("");
		case 9:
			return new ListTag();
		case 10:
			return new CompoundTag();
		case 11:
			return new IntArrayTag(new int[0]);
		case 12:
			return new LongArrayTag(new long[0]);
		}
		return null;
	}

	public static String toString(Tag base) {
		switch (base.getId()) {
		case 1:
			return "" + ((ByteTag) base).getAsByte();
		case 2:
			return "" + ((ShortTag) base).getAsShort();
		case 3:
			return "" + ((IntTag) base).getAsInt();
		case 4:
			return "" + ((LongTag) base).getAsLong();
		case 5:
			return "" + ((FloatTag) base).getAsFloat();
		case 6:
			return "" + ((DoubleTag) base).getAsDouble();
		case 7:
			return base.toString();
		case 8:
			return ((StringTag) base).getAsString();
		case 9:
			return "(TagList)";
		case 10:
			return "(TagCompound)";
		case 11:
			return base.toString();
		case 12:
			return base.toString();
		case 13:
			return base.toString();
		default:
			return "?";
		}
		//		switch (base.getId()) {
		//		case 1:
		//			return "" + ((ByteTag) base).getAsByte();
		//		case 2:
		//			return "" + ((ShortTag) base).getAsShort();
		//		case 3:
		//			return "" + ((IntTag) base).getAsInt();
		//		case 4:
		//			return "" + ((LongTag) base).getAsLong();
		//		case 5:
		//			return "" + ((FloatTag) base).getAsFloat();
		//		case 6:
		//			return "" + ((DoubleTag) base).getAsDouble();
		//		case 7:
		//			return base.toString();
		//		case 8:
		//			return ((StringTag) base).getAsString();
		//		case 9:
		//			return "(TagList)";
		//		case 10:
		//			return "(TagCompound)";
		//		case 11:
		//			return base.toString();
		//		case 12:
		//			return base.toString();
		//		case 13:
		//			return base.toString();
		//		default:
		//			return "?";
		//		}
	}

	public static String getValue(Tag base) {
		switch (base.getId()) {
		case 7:
			String b = "";
			for (byte a : ((ByteArrayTag) base).getAsByteArray()) {
				b += a + " ";
			}
			return b;
		case 9:
			return "TagList";
		case 10:
			return "TagCompound";
		case 11:
			String i = "";
			for (int a : ((IntArrayTag) base).getAsIntArray()) {
				i += a + " ";
			}
			return i;
		case 12:
			String l = "";
			for (long a : ((LongArrayTag) base).getAsLongArray()) {
				l += a + " ";
			}
			return l;
		default:
			return NBTStringHelper.toString(base);
		}
	}
}
