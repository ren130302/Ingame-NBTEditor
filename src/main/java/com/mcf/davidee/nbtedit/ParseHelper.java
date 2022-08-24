package com.mcf.davidee.nbtedit;

import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.nbt.Node;

import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

public class ParseHelper {

	public static void setValidValue(Node<NamedNBT> node, String value) {
		NamedNBT named = node.object();
		Tag base = named.tag();

		if (base instanceof ByteTag) {
			named.tag(ByteTag.valueOf(ParseHelper.parseByte(value)));
		}
		if (base instanceof ShortTag) {
			named.tag(ShortTag.valueOf(ParseHelper.parseShort(value)));
		}
		if (base instanceof IntTag) {
			named.tag(IntTag.valueOf(ParseHelper.parseInt(value)));
		}
		if (base instanceof LongTag) {
			named.tag(LongTag.valueOf(ParseHelper.parseLong(value)));
		}
		if (base instanceof FloatTag) {
			named.tag(FloatTag.valueOf(ParseHelper.parseFloat(value)));
		}
		if (base instanceof DoubleTag) {
			named.tag(DoubleTag.valueOf(ParseHelper.parseDouble(value)));
		}
		if (base instanceof ByteArrayTag) {
			named.tag(new ByteArrayTag(ParseHelper.parseByteArray(value)));
		}
		if (base instanceof IntArrayTag) {
			named.tag(new IntArrayTag(ParseHelper.parseIntArray(value)));
		}
		if (base instanceof StringTag) {
			named.tag(StringTag.valueOf(value));
		}
	}

	public static void validValue(String value, byte type) throws NumberFormatException {
		switch (type) {
		case 1:
			ParseHelper.parseByte(value);
			break;
		case 2:
			ParseHelper.parseShort(value);
			break;
		case 3:
			ParseHelper.parseInt(value);
			break;
		case 4:
			ParseHelper.parseLong(value);
			break;
		case 5:
			ParseHelper.parseFloat(value);
			break;
		case 6:
			ParseHelper.parseDouble(value);
			break;
		case 7:
			ParseHelper.parseByteArray(value);
			break;
		case 11:
			ParseHelper.parseIntArray(value);
			break;
		}
	}

	public static byte parseByte(String s) throws NumberFormatException {
		try {
			return Byte.parseByte(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid byte");
		}
	}

	public static short parseShort(String s) throws NumberFormatException {
		try {
			return Short.parseShort(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid short");
		}
	}

	public static int parseInt(String s) throws NumberFormatException {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid int");
		}
	}

	public static long parseLong(String s) throws NumberFormatException {
		try {
			return Long.parseLong(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid long");
		}
	}

	public static float parseFloat(String s) throws NumberFormatException {
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid float");
		}
	}

	public static double parseDouble(String s) throws NumberFormatException {
		try {
			return Double.parseDouble(s);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid double");
		}
	}

	public static byte[] parseByteArray(String s) throws NumberFormatException {
		try {
			String[] input = s.split(" ");
			byte[] arr = new byte[input.length];
			for (int i = 0; i < input.length; ++i) {
				arr[i] = parseByte(input[i]);
			}
			return arr;
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid byte array");
		}
	}

	public static int[] parseIntArray(String s) throws NumberFormatException {
		try {
			String[] input = s.split(" ");
			int[] arr = new int[input.length];
			for (int i = 0; i < input.length; ++i) {
				arr[i] = parseInt(input[i]);
			}
			return arr;
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid int array");
		}
	}
}
