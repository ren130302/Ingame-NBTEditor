 package com.ruby.meshi.client.renderer.animation;import javax.annotation.Nullable;

import com.ruby.meshi.client.renderer.animation.EntityModelAnimation.RenderPart;
 public class ViewHand implements EntityModelAnimation {   float prevMoveAmount;   float moveAmount;
    float prevRotateYaw;
    float rotateYaw;
    float prevRotatePitch;
    float rotatePitch;
    int timer;
    int maxTimer;
    final Direction direction;
    EntityModelAnimation parent;
    @Nullable
    RenderPart part;
 
    public ViewHand(Direction direction) {
       this.timer = 0;
 
 
 
       this.part = null;
 
 
 
       this.direction = direction;
       this.maxTimer = 100 + rand.nextInt(100);
       this.moveAmount = 0.3F;
    }
 
    public ViewHand(Direction direction, RenderPart part) {
       this(direction);
       this.part = part;
    }
 
 
    public boolean shouldRenderPart(RenderPart part) {
       if (this.part != null) {
          return this.part == part;
       } else {
          return part == RenderPart.LEFT_HAND || part == RenderPart.RIGHT_HAND;
       }
    }
 
    public void animationTick() {
       this.prevMoveAmount = this.moveAmount;
       this.prevRotateYaw = this.rotateYaw;
       this.prevRotatePitch = this.rotatePitch;
 
 
 
 
 
 
 
       this.handMove();
 
 
    }
 
    private void handMove() {
    }
 
    public ViewHand setParent(EntityModelAnimation parent) {
       this.parent = parent;
       this.maxTimer = parent.getMaxTimer();
       return this;
    }
 
 
    public void translatef(TriConsumer<Float, Float, Float> position, float partialTicks) {
       position.accept(this.getDiractionOffsetVal(this.lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Axis.X), 0.0F, this.getDiractionOffsetVal(this.lerp(partialTicks, this.prevMoveAmount, this.moveAmount), this.direction, Axis.Z));
 
 
    }
 
 
    public boolean isFinished() {
       return this.timer > this.maxTimer;
    }
 
 
    public int getMaxTimer() {
       return this.maxTimer;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/