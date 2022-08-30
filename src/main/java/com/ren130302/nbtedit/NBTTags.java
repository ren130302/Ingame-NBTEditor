package com.ren130302.nbtedit;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

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

public class NBTTags {

	public static NBTTag<EndTag> END = (new NBTTag<EndTag>() {
		@Override
		public EndTag instance() {
			return EndTag.INSTANCE;
		}
	});

	public static NumNBTTag<ByteTag, Byte> BYTE = (new NumNBTTag<ByteTag, Byte>() {
		@Override
		public ByteTag instance() {
			return this.valueOf((byte) 0);
		}

		@Override
		public ByteTag valueOf(Byte value) {
			return ByteTag.valueOf(value);
		}

		@Override
		public Byte parse(String value) {
			return ParseHelper.parseByte(value);
		}

	});

	public static NumNBTTag<ShortTag, Short> SHORT = (new NumNBTTag<ShortTag, Short>() {
		@Override
		public ShortTag instance() {
			return this.valueOf((short) 0);
		}

		@Override
		public ShortTag valueOf(Short value) {
			return ShortTag.valueOf(value);
		}

		@Override
		public Short parse(String value) {
			return ParseHelper.parseShort(value);
		}
	});

	public static NumNBTTag<IntTag, Integer> INT = (new NumNBTTag<IntTag, Integer>() {

		@Override
		public IntTag instance() {
			return this.valueOf(0);
		}

		@Override
		public IntTag valueOf(Integer value) {
			return IntTag.valueOf(value);
		}

		@Override
		public Integer parse(String value) {
			return ParseHelper.parseInt(value);
		}

	});

	public static NumNBTTag<LongTag, Long> LONG = (new NumNBTTag<LongTag, Long>() {

		@Override
		public LongTag instance() {
			return this.valueOf((long) 0);
		}

		@Override
		public LongTag valueOf(Long value) {
			return LongTag.valueOf(value);
		}

		@Override
		public Long parse(String value) {
			return ParseHelper.parseLong(value);
		}
	});

	public static NumNBTTag<FloatTag, Float> FLOAT = (new NumNBTTag<FloatTag, Float>() {

		@Override
		public FloatTag instance() {
			return this.valueOf((float) 0);
		}

		@Override
		public FloatTag valueOf(Float value) {
			return FloatTag.valueOf(value);
		}

		@Override
		public Float parse(String value) {
			return ParseHelper.parseFloat(value);
		}
	});

	public static NumNBTTag<DoubleTag, Double> DOUBLE = (new NumNBTTag<DoubleTag, Double>() {

		@Override
		public DoubleTag instance() {
			return this.valueOf((double) 0);
		}

		@Override
		public DoubleTag valueOf(Double value) {
			return DoubleTag.valueOf(value);
		}

		@Override
		public Double parse(String value) {
			return ParseHelper.parseDouble(value);
		}
	});

	public static NumNBTTag<ByteArrayTag, List<Byte>> BYTE_ARRAY = (new NumNBTTag<ByteArrayTag, List<Byte>>() {

		@Override
		public ByteArrayTag instance() {
			return this.valueOf(Lists.newArrayList());
		}

		@Override
		public ByteArrayTag valueOf(List<Byte> value) {
			return new ByteArrayTag(value);
		}

		@Override
		public List<Byte> parse(String value) {
			return ParseHelper.parseByteArray(value);
		}
	});

	public static NumNBTTag<StringTag, String> STRING = (new NumNBTTag<StringTag, String>() {

		@Override
		public StringTag instance() {
			return this.valueOf("");
		}

		@Override
		public StringTag valueOf(String value) {
			return StringTag.valueOf(value);
		}

		@Override
		public String parse(String value) {
			return value;
		}
	});

	public static NBTTag<ListTag> LIST = (new NBTTag<ListTag>() {

		@Override
		public ListTag instance() {
			return new ListTag();
		}
	});

	public static NBTTag<CompoundTag> COUMPOUND = (new NBTTag<CompoundTag>() {

		@Override
		public CompoundTag instance() {
			return new CompoundTag();
		}
	});

	public static NumNBTTag<IntArrayTag, List<Integer>> INT_ARRAY = (new NumNBTTag<IntArrayTag, List<Integer>>() {

		@Override
		public IntArrayTag instance() {
			return this.valueOf(Lists.newArrayList());
		}

		@Override
		public IntArrayTag valueOf(List<Integer> value) {
			return new IntArrayTag(value);
		}

		@Override
		public List<Integer> parse(String value) {
			return ParseHelper.parseIntArray(value);
		}
	});

	public static NumNBTTag<LongArrayTag, List<Long>> LONG_ARRAY = (new NumNBTTag<LongArrayTag, List<Long>>() {

		@Override
		public LongArrayTag instance() {
			return this.valueOf(Lists.newArrayList());
		}

		@Override
		public LongArrayTag valueOf(List<Long> value) {
			return new LongArrayTag(value);
		}

		@Override
		public List<Long> parse(String value) {
			return ParseHelper.parseLongArray(value);
		}
	});

	private interface Instance<T extends Tag> {
		T instance();
	}

	public interface NBTTag<T extends Tag> extends Instance<T> {

	}

	public interface NumNBTTag<T extends Tag, V> extends NBTTag<T> {
		T valueOf(V value);

		V parse(final String value);

		default T parseTag(final String value) {
			return this.valueOf(this.parse(value));
		}
	}

}
