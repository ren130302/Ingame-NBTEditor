 package com.ruby.meshi.client.renderer.animation;
 
 import com.ruby.meshi.client.renderer.animation.WatchEntity.1;
 import javax.annotation.Nullable;
 import net.minecraft.entity.Entity;
 import net.minecraft.util.Direction;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 
 
 public class WatchEntity extends SwingHead {
    @Nullable
    Entity entity;
    BlockPos pos;
 
    public WatchEntity(Direction direction, BlockPos pos) {
       super(direction);
       this.pos = pos;
    }
 
    public WatchEntity setTarget(Entity entity) {
       this.entity = entity;
       return this;
    }
 
 
    void headMove() {
       if (this.entity != null && this.entity.func_70089_S()) {      } else {
          super.headMove();      }
    }
 
          this.rotatePitch = this.calcAngle(this.rotatePitch, -this.getLookingPitch() + this.getHeadPitchOffset(), 10.0F);
          this.rotateYaw = this.calcAngle(this.rotateYaw, this.getLookingYaw(), 35.0F);
 
          this.rotatePitch = MathHelper.func_76131_a(this.rotatePitch, -35.0F, 35.0F);
          this.rotateYaw = MathHelper.func_76131_a(this.rotateYaw, -35.0F, 35.0F);
 
 
    float calcAngle(float baseAngle, float amount, float swingSpeed) {
       float f = MathHelper.func_203302_c(baseAngle, amount);
       float f1 = MathHelper.func_76131_a(f, -swingSpeed, swingSpeed);
       return baseAngle + f1;
    }
 
    float getLookingPitch() {
       double d0 = (double)this.pos.func_177958_n() - this.entity.field_70165_t;
       double d1 = (double)this.pos.func_177952_p() - this.entity.field_70161_v;
       return (float)(MathHelper.func_181159_b(d1, d0) * 57.2957763671875D) - 90.0F;
    }
 
    float getLookingYaw() {
       double d0 = (double)this.pos.func_177958_n() - this.entity.field_70165_t;
       double d1 = (double)this.pos.func_177956_o() - (this.entity.field_70163_u + (double)this.entity.func_70047_e());
       double d2 = (double)this.pos.func_177952_p() - this.entity.field_70161_v;
       double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2);
       return (float)(-(MathHelper.func_181159_b(d1, d3) * 57.2957763671875D));
    }
 
    float getHeadPitchOffset() {
       float offset = 0.0F;
       switch(1.$SwitchMap$net$minecraft$util$Direction[this.direction.ordinal()]) {
       case 1:
          offset = 90.0F;
          break;
       case 2:
          offset = 180.0F;
          break;
       case 3:
          offset = 270.0F;
       }
 
       return offset;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 12 ms
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/