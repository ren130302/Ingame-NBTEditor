 package com.ruby.meshi.util;
 
 import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import sun.misc.Unsafe;
import sun.reflect.ConstructorAccessor;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class EnumHelper {
    public static <T extends Enum<?>> T addEnum(Class<T> target, String name, int ordinal, Class<?>[] paramTypes, Object[] param) {
       Enum result = null;
       try {
          Method m = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
          m.setAccessible(true);
          Constructor<T> cnst = target.getDeclaredConstructor((Class[])Stream.concat(Stream.of(String.class, Integer.TYPE), Arrays.stream(paramTypes)).toArray((x$0) -> {            return new Class[x$0];
          }));
          ConstructorAccessor ca = (ConstructorAccessor)m.invoke(cnst);
 
          Object[] args = Stream.concat(Stream.of(name, ordinal), Arrays.stream(param)).toArray();
          result = (Enum)ca.newInstance(args);
 
          addValueToEnum(result);
       } catch (Exception var10) {
          var10.printStackTrace();
       }
 
       return result;
    }
 
 
 
    private static <T extends Enum<?>> void addValueToEnum(T newValue) throws Exception {
       Field f;
       try {
          f = newValue.getClass().getDeclaredField("$VALUES");
       } catch (NoSuchFieldException var6) {
 
          f = newValue.getClass().getDeclaredField("ENUM$VALUES");
       }
       Field uf = Unsafe.class.getDeclaredField("theUnsafe");
       f.setAccessible(true);
       uf.setAccessible(true);
       T[] values = (Enum[])((Enum[])f.get((Object)null));
       T[] newValues = (Enum[])Arrays.copyOf(values, values.length + 1);
       newValues[values.length] = newValue;
 
       Unsafe unsafe = (Unsafe)uf.get((Object)null);
       unsafe.putObjectVolatile(unsafe.staticFieldBase(f), unsafe.staticFieldOffset(f), newValues);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 11 ms
	
	Decompiled with FernFlower.
*/