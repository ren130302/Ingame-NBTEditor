 package com.ruby.meshi.client.renderer.animation;
 
 import java.util.function.Consumer;

import com.ruby.meshi.client.renderer.animation.EntityModelAnimation.RenderPart;

import net.minecraft.util.math.MathHelper;
 
 public class SwingTail implements EntityModelAnimation {
    float prevMoveAmount;
    float moveAmount;
    float prevRotateYaw;
    float rotateYaw;
    float prevRotatePitch;
    float rotatePitch;
    int timer;
    int maxTimer;
    int moveTime;
    final Direction direction;
    static final float ANGLE_LIMIT = 35.0F;
    float rotateAngleX;
    float rotateAngleY;
    float moveLength;
 
    public SwingTail(Direction direction) {
       this.direction = direction;
       this.maxTimer = 600 + rand.nextInt(600);
       this.moveAmount = this.moveLength = 0.15F;
 
       this.rotateAngleX = 70.0F * rand.nextFloat() - 35.0F;
       this.rotateAngleX += 10.0F * Math.signum(this.rotateAngleX);
       this.rotatePitch = this.rotateAngleX = MathHelper.func_76131_a(this.rotateAngleX, -35.0F, 35.0F);
       this.rotateYaw = this.rotateAngleY = 17.5F * rand.nextFloat() - 8.75F;
    }
 
 
    public boolean shouldRenderPart(RenderPart part) {
       return part == RenderPart.TAIL;
    }
 
 
    public void animationTick() {
       this.prevMoveAmount = this.moveAmount;
       this.prevRotateYaw = this.rotateYaw;
       this.prevRotatePitch = this.rotatePitch;
 
 
 
 
 
 
       this.taildMove();
 
 
    }
 
    private void taildMove() {
       if (Math.abs(this.rotateAngleX - this.rotatePitch) > 0.1F) {
          this.rotatePitch = this.lerp(0.1F, this.rotatePitch, this.rotateAngleX);
          this.rotateYaw = this.lerp(0.1F, this.rotateYaw, this.rotateAngleY);
       } else {
          this.rotateAngleX = -this.rotateAngleX;
       }
 
    }
 
 
    public int getMaxTimer() {
       return this.maxTimer;
    }
 
 
    public boolean isFinished() {
       return this.timer > this.maxTimer;
    }
 
 
    public void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
       position.accept(this.getDiractionOffsetVal(this.lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Axis.X), 0.0F, this.getDiractionOffsetVal(this.lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Axis.Z));
 
 
    }
 
 
    public void rotateY(Consumer<Float> angle, float partialTicks) {
       angle.accept(this.lerp(partialTicks, this.prevRotatePitch, this.rotatePitch));
    }
 
 
    public void rotateX(Consumer<Float> angle, float partialTicks) {
       angle.accept(-this.getDiractionVal(this.lerp(partialTicks, this.prevRotateYaw, this.rotateYaw), this.direction, Axis.Z));
    }
 
 
    public void rotateZ(Consumer<Float> angle, float partialTicks) {
       angle.accept(this.getDiractionOffsetVal(this.lerp(partialTicks, this.prevRotateYaw, this.rotateYaw), this.direction, Axis.X));
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 11 ms
	
	Decompiled with FernFlower.
*/