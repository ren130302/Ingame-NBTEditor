package com.mcf.davidee.nbtedit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

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

public abstract class NBTTags<T extends Tag> {
    public static final NBTTags<EndTag> END = _value(null, EndTag.INSTANCE);
    public static final NBTTags<ByteTag> BYTE = _value(NBTTags::parseByte);
    public static final NBTTags<ShortTag> SHORT = _value(NBTTags::parseShort);
    public static final NBTTags<IntTag> INT = _value(NBTTags::parseInt);
    public static final NBTTags<LongTag> LONG = _value(NBTTags::parseLong);
    public static final NBTTags<FloatTag> FLOAT = _value(NBTTags::parseFloat);
    public static final NBTTags<DoubleTag> DOUBLE = _value(NBTTags::parseDouble, DoubleTag::valueOf);
    public static final NBTTags<ByteArrayTag> BYTE_ARRAY = _values(NBTTags::parseByteArray, ByteArrayTag::new);
    public static final NBTTags<StringTag> STRING = _value(null, StringTag::valueOf);
    public static final NBTTags<ListTag> LIST = _instance(null, ListTag::new);
    public static final NBTTags<CompoundTag> COUMPOUND = _instance(null, CompoundTag::new);
    public static final NBTTags<IntArrayTag> INT_ARRAY = _values(NBTTags::parseIntArray, IntArrayTag::new);
    public static final NBTTags<LongArrayTag> LONG_ARRAY = _values(NBTTags::parseLongArray, LongArrayTag::new);

    public static <T extends Tag> NBTTags<T> _value(Function<String, ?> parseFunc, Function<?, T> tagFunc) {
	return new NBTTags<T>() {
	};
    }

    public static <T extends Tag> NBTTags<T> _instance(Function<String, ?> parseFunc, Supplier<T> tagFunc) {
	return new NBTTags<T>(func);
    }

    public static <T extends Tag> NBTTags<T> _values(Function<String, ?> parseFunc, Function<List, T> tagFunc) {
	return new NBTTags<T>(func);
    }

    public T parse() {
	return this.parse(null);
    }

    public T parse(@Nullable final String value) {
	return this.func.apply(value);
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
