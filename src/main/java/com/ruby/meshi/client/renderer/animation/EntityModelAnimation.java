 package com.ruby.meshi.client.renderer.animation;
 
 import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import com.ruby.meshi.client.renderer.animation.EntityModelAnimation.RenderPart;

import net.minecraft.util.math.MathHelper;
 
 
 
 
 
 
 
 
 
 public interface EntityModelAnimation {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
 
    boolean shouldRenderPart(RenderPart var1);
 
    void animationTick();
 
    int getMaxTimer();
 
    boolean isFinished();
 
    default void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
       position.accept(0.0F, 0.0F, 0.0F);
    }
 
    default void scalef(TriConsumer<Float, Float, Float> scale, float partialTicks) {
       scale.accept(0.0F, 0.0F, 0.0F);
    }
 
    default void rotateX(Consumer<Float> angle, float partialTicks) {
       angle.accept(0.0F);
    }
 
    default void rotateY(Consumer<Float> angle, float partialTicks) {
       angle.accept(0.0F);
    }
 
    default void rotateZ(Consumer<Float> angle, float partialTicks) {
       angle.accept(0.0F);
    }
 
    default float getDiractionVal(float val, Direction direction, Axis axis) {
       float offset = 0.0F;
       if (direction.func_176740_k() == axis) {
          offset = val;
       }
       return offset;
    }
 
    default float getDiractionOffsetVal(float val, Direction direction, Axis axis) {
       float offset = 0.0F;
       if (direction.func_176740_k() == axis) {
          offset = val * (float)direction.func_176743_c().func_179524_a();
       }
       return offset;
    }
 
    default float lerp(float partialTicks, float prev, float now) {
       return MathHelper.func_219799_g(partialTicks, prev, now);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 9 ms
	
	Decompiled with FernFlower.
*/