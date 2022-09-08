 package com.ruby.meshi.client.paticle;
 
 import com.ruby.meshi.block.SakuraLeave.SakuraType;
 import com.ruby.meshi.client.paticle.PetalParticle.ParticleRenderType;
 import net.minecraft.client.particle.IParticleRenderType;
 import net.minecraft.client.particle.Particle;
 import net.minecraft.client.renderer.ActiveRenderInfo;
 import net.minecraft.client.renderer.BufferBuilder;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.IFluidState;
 import net.minecraft.tags.Tag;
 import net.minecraft.util.math.AxisAlignedBB;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.World;
 public class PetalParticle extends Particle {
    static final float PI180 = 0.017453292F;
    float scale;
    private float rotateMotion;
    private SakuraType petalType;
    private float moveResistance;
    private boolean onWater;
    float pitch;
    float prevPitch;
    float yaw;
    float prevYaw;
    static final IParticleRenderType PETAL = new ParticleRenderType();
 
       this.scale = 0.1F;
 
 
 
 
 
 
 
 
 
 
    public PetalParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
       this(worldIn, posXIn, posYIn, posZIn, 0.0D, 0.0D, 0.0D);
    }
 
    public PetalParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
       super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
       this.func_187115_a(0.1F, 0.1F);
       this.field_187129_i = 0.0D;
       this.field_187130_j = (double)(-this.field_187136_p.nextFloat() * 0.05F);
       this.field_187131_k = 0.0D;
       this.field_70547_e = Math.max((int)(600.0D / (Math.random() * 0.8D + 0.6D)), 1);
       this.pitch = 90.0F * (this.field_187136_p.nextFloat() - 0.5F) * 0.5F + 90.0F;
       this.prevPitch = this.pitch;
       this.yaw = 360.0F * this.field_187136_p.nextFloat();
       this.rotateMotion = 20.0F * (this.field_187136_p.nextFloat() - 0.5F);
       this.petalType = SakuraType.GREEN;
       float c = 1.0F - this.field_187136_p.nextFloat() * 0.3F;
       this.func_70538_b(1.0F, c, c);
 
       this.field_70545_g = 1.0F - (this.field_187136_p.nextFloat() - 0.2F) * 0.6F;
       this.moveResistance = 1.0F - this.field_187136_p.nextFloat() * 0.6F;
 
       this.func_189213_a();
    }
 
 
 
 
 
 
 
 
    public PetalParticle setPetal(SakuraType type) {
       this.petalType = type;
       return this;
    }
 
 
    public void func_189213_a() {
       this.field_187123_c = this.field_187126_f;
       this.field_187124_d = this.field_187127_g;
       this.field_187125_e = this.field_187128_h;
       this.prevYaw = this.yaw;
       if (this.field_70546_d++ >= this.field_70547_e) {
          this.func_187112_i();
       } else {
          if (!this.onWater && this.isInWater()) {
             this.onWater = true;
             this.rotateMotion = 0.0F;
          }
          this.field_187130_j -= 0.004D * (double)this.petalType.MASS * (double)this.field_70545_g;
 
          this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
 
          this.field_187129_i *= 0.9599999785423279D * (double)this.moveResistance;
          this.field_187130_j *= 0.9599999785423279D;
          this.field_187131_k *= 0.9599999785423279D * (double)this.moveResistance;
          this.yaw += this.rotateMotion;
          if (this.field_187124_d == this.field_187127_g) {
             this.field_187132_l = true;
             this.rotateMotion = 0.0F;
             ++this.field_70546_d;
          }
          if (this.field_187132_l) {
             this.field_187129_i *= 0.8999999761581421D - (double)(this.petalType.MASS * 0.2F);
             this.field_187131_k *= 0.8999999761581421D - (double)(this.petalType.MASS * 0.2F);
 
          } else if (this.onWater) {
             this.waterMove();
          } else {
             this.windMove();
          }
       }
 
    }
 
    private boolean isInWater() {
       if (this.field_187122_b.func_72953_d(this.func_187116_l())) {
          BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
          IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
 
          VoxelShape box = ifluidstate.func_215676_d(this.field_187122_b, blockpos);
          if (!box.func_197766_b()) {
             AxisAlignedBB aabb = ifluidstate.func_215676_d(this.field_187122_b, blockpos).func_197752_a().func_186670_a(blockpos).func_72317_d(0.0D, 0.1D, 0.0D);
             return this.func_187116_l().func_72326_a(aabb);
          }      }
 
       return false;
    }
 
 
    private void waterMove() {
       BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
       IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
       VoxelShape box = ifluidstate.func_215676_d(this.field_187122_b, blockpos);
       if (!box.func_197766_b()) {
          AxisAlignedBB aabb = ifluidstate.func_215676_d(this.field_187122_b, blockpos).func_197752_a().func_186670_a(blockpos).func_72317_d(0.0D, 0.1D, 0.0D);
          if (this.func_187116_l().func_72326_a(aabb)) {
             Vec3d vec = ifluidstate.func_215673_c(this.field_187122_b, blockpos).func_186678_a(0.1D);
             this.field_187129_i = vec.field_72450_a;
             this.field_187127_g = aabb.field_72337_e;
             this.field_187131_k = vec.field_72449_c;
          }      }
 
    }
 
    public boolean isInFluid(Tag<Fluid> p_213290_1_) {
       BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
       if (!this.field_187122_b.func_217354_b(blockpos.func_177958_n() >> 4, blockpos.func_177952_p() >> 4)) {
          return false;
       } else {
          this.field_187122_b.func_72953_d(this.func_187116_l());
          IFluidState ifluidstate = this.field_187122_b.func_204610_c(blockpos);
          return ifluidstate.getFluidState().func_206886_c().isAABBInsideLiquid(ifluidstate, this.field_187122_b, blockpos, this.func_187116_l());
       }
    }
 
    void windMove() {
       this.field_187129_i += WindManager.getMotionX();
       this.field_187130_j += WindManager.getMotionY();
       this.field_187131_k += WindManager.getMotionZ();
    }
 
    public int getTextuerID() {
       return this.petalType.ID;
    }
 
 
    public IParticleRenderType func_217558_b() {
       return PETAL;
    }
 
 
    public void func_180434_a(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
       float partialPitch = (float)MathHelper.func_219803_d((double)partialTicks, (double)this.prevPitch, (double)this.pitch);
       float partialYaw = (float)MathHelper.func_219803_d((double)partialTicks, (double)this.prevYaw, (double)this.yaw);
       rotationX = MathHelper.func_76134_b(partialYaw * 0.017453292F);
       rotationYZ = MathHelper.func_76126_a(partialYaw * 0.017453292F);
       rotationXY = -rotationYZ * MathHelper.func_76126_a(partialPitch * 0.017453292F);
       rotationXZ = rotationX * MathHelper.func_76126_a(partialPitch * 0.017453292F);
       rotationZ = MathHelper.func_76134_b(partialPitch * 0.017453292F);
       float minU = 0.15625F * (float)this.getTextuerID();
       float maxU = minU + 0.15625F;
       float minV = 0.3125F * (float)(this.getTextuerID() / 6);
       float maxV = minV + 0.3125F;
       float x = (float)(MathHelper.func_219803_d((double)partialTicks, this.field_187123_c, this.field_187126_f) - field_70556_an);
       float y = (float)(MathHelper.func_219803_d((double)partialTicks, this.field_187124_d, this.field_187127_g) - field_70554_ao);
       float z = (float)(MathHelper.func_219803_d((double)partialTicks, this.field_187125_e, this.field_187128_h) - field_70555_ap);
       Vec3d[] avec3d = new Vec3d[]{new Vec3d((double)(-rotationX * this.scale - rotationXY * this.scale), (double)(-rotationZ * this.scale), (double)(-rotationYZ * this.scale - rotationXZ * this.scale)), new Vec3d((double)(-rotationX * this.scale + rotationXY * this.scale), (double)(rotationZ * this.scale), (double)(-rotationYZ * this.scale + rotationXZ * this.scale)), new Vec3d((double)(rotationX * this.scale + rotationXY * this.scale), (double)(rotationZ * this.scale), (double)(rotationYZ * this.scale + rotationXZ * this.scale)), new Vec3d((double)(rotationX * this.scale - rotationXY * this.scale), (double)(-rotationZ * this.scale), (double)(rotationYZ * this.scale - rotationXZ * this.scale))};
 
       int i = this.func_189214_a(partialTicks);
       int j = i >> 16 & '￿';
       int k = i & '￿';
       buffer.func_181662_b((double)x + avec3d[0].field_72450_a, (double)y + avec3d[0].field_72448_b, (double)z + avec3d[0].field_72449_c).func_187315_a((double)maxU, (double)maxV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
       buffer.func_181662_b((double)x + avec3d[1].field_72450_a, (double)y + avec3d[1].field_72448_b, (double)z + avec3d[1].field_72449_c).func_187315_a((double)maxU, (double)minV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
       buffer.func_181662_b((double)x + avec3d[2].field_72450_a, (double)y + avec3d[2].field_72448_b, (double)z + avec3d[2].field_72449_c).func_187315_a((double)minU, (double)minV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
       buffer.func_181662_b((double)x + avec3d[3].field_72450_a, (double)y + avec3d[3].field_72448_b, (double)z + avec3d[3].field_72449_c).func_187315_a((double)minU, (double)maxV).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 66 ms
	
	Decompiled with FernFlower.
*/