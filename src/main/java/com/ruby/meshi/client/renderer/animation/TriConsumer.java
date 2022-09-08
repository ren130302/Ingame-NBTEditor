 package com.ruby.meshi.client.renderer.animation;
 
 import java.util.Objects;
 
 @FunctionalInterface
 public interface TriConsumer<T, U, V> {
    void accept(T var1, U var2, V var3);
 
    default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
       Objects.requireNonNull(after);
 
       return (l, r, v) -> {
          this.accept(l, r, v);
          after.accept(l, r, v);
       };
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 4 ms
	
	Decompiled with FernFlower.
*/