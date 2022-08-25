package com.mcf.davidee.nbtedit;

import java.util.Objects;

import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.nbt.Node;

import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

public class ParseHelper {

	public static void setValidValue(Node<NamedNBT> node, String value) {
		NamedNBT named = node.object();
		Tag base = named.tag();
		Tag parsed = null;
		switch (base.getId()) {
		case 1:
			parsed = ByteTag.valueOf(ParseHelper.parseByte(value));
			break;
		case 2:
			parsed = ShortTag.valueOf(ParseHelper.parseShort(value));
			break;
		case 3:
			named.tag(IntTag.valueOf(ParseHelper.parseInt(value)));
		case 4:
			named.tag(LongTag.valueOf(ParseHelper.parseLong(value)));
		case 5:
			named.tag(FloatTag.valueOf(ParseHelper.parseFloat(value)));
		case 6:
			named.tag(DoubleTag.valueOf(ParseHelper.parseDouble(value)));
		case 7:
			named.tag(new ByteArrayTag(ParseHelper.parseByteArray(value)));
		case 8:
			named.tag(StringTag.valueOf(value));
		case 11:
			named.tag(new IntArrayTag(ParseHelper.parseIntArray(value)));
		case 12:
			named.tag(new LongArrayTag(ParseHelper.parseLongArray(value)));
		}
		if (Objects.isNull(parsed)) {
			named.tag(parsed);
		} else {
			throw new NullPointerException("Could not parse, invalid value ->" + value);
		}
	}

	public static boolean validValue(String value, byte type) throws NumberFormatException {
		boolean isValid = true;
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
		case 12:
			ParseHelper.parseLongArray(value);
			break;
		default:
			isValid = false;
		}
		return isValid;
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

	private static long[] parseLongArray(String s) throws NumberFormatException {
		try {
			String[] input = s.split(" ");
			long[] arr = new long[input.length];
			for (int i = 0; i < input.length; ++i) {
				arr[i] = parseLong(input[i]);
			}
			return arr;
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Not a valid long array");
		}
	}
}
