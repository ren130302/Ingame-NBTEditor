package com.ren130302.nbtedit;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import com.ren130302.nbtedit.nbt.NamedNBT;
import com.ren130302.nbtedit.nbt.Node;

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

	public static void setValidValue(Node<NamedNBT> node, String value) throws NumberFormatException {
		NamedNBT named = node.object();
		Tag base = named.tag();
		byte tagId = base.getId();
		Tag parsed = null;
		switch (tagId) {
		case 1:
			parsed = ByteTag.valueOf(ParseHelper.parseByte(value));
			break;
		case 2:
			parsed = ShortTag.valueOf(ParseHelper.parseShort(value));
			break;
		case 3:
			parsed = IntTag.valueOf(ParseHelper.parseInt(value));
			break;
		case 4:
			parsed = LongTag.valueOf(ParseHelper.parseLong(value));
			break;
		case 5:
			parsed = FloatTag.valueOf(ParseHelper.parseFloat(value));
			break;
		case 6:
			parsed = DoubleTag.valueOf(ParseHelper.parseDouble(value));
			break;
		case 7:
			parsed = new ByteArrayTag(ParseHelper.parseByteArray(value));
			break;
		case 8:
			parsed = StringTag.valueOf(value);
			break;
		case 11:
			parsed = new IntArrayTag(ParseHelper.parseIntArray(value));
			break;
		case 12:
			parsed = new LongArrayTag(ParseHelper.parseLongArray(value));
			break;
		default:
			break;
		}
		System.out.println(named);
		if (Objects.nonNull(parsed)) {
			named.tag(parsed);
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

	public static byte parseByte(final String s) {
		return parse(s, Byte::parseByte, () -> "Not a valid byte");
	}

	public static short parseShort(final String s) {
		return parse(s, Short::parseShort, () -> "Not a valid short");
	}

	public static int parseInt(final String s) {
		return parse(s, Integer::parseInt, () -> "Not a valid int");
	}

	public static long parseLong(final String s) {
		return parse(s, Long::parseLong, () -> "Not a valid long");
	}

	public static float parseFloat(final String s) {
		return parse(s, Float::parseFloat, () -> "Not a valid float");
	}

	public static double parseDouble(final String s) {
		return parse(s, Double::parseDouble, () -> "Not a valid double");
	}

	public static ArrayList<Byte> parseByteArray(final String s) {
		return parseArray(s, ParseHelper::parseByte, () -> "Not a valid byte array");
	}

	public static ArrayList<Integer> parseIntArray(final String s) {
		return parseArray(s, ParseHelper::parseInt, () -> "Not a valid int array");
	}

	public static ArrayList<Long> parseLongArray(final String s) {
		return parseArray(s, ParseHelper::parseLong, () -> "Not a valid long array");
	}

	private static <T> T parse(final String value, final Function<String, T> func, final Supplier<String> msg)
			throws NumberFormatException {
		try {
			return func.apply(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(msg.get());
		}
	}

	private static <T> ArrayList<T> parseArray(final String s, final Function<String, T> func,
			final Supplier<String> msg) {
		try {
			String[] input = s.split(" ");
			ArrayList<T> arr = new ArrayList<T>(input.length);
			for (int i = 0; i < input.length; ++i) {
				arr.add(func.apply(s));
			}
			return arr;
		} catch (NumberFormatException e) {
			throw new NumberFormatException(msg.get());
		}
	}
}
