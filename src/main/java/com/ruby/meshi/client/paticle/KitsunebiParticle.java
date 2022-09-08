 package com.ruby.meshi.client.paticle;
 
 import com.mojang.blaze3d.vertex.BufferBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
 
 public class KitsunebiParticle extends SpriteTexturedParticle {
    private float yRotate = 0.0F;
 
    public KitsunebiParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, IItemProvider renderItem) {
       super(world, xCoordIn, yCoordIn, zCoordIn);
       this.func_217567_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_199934_a(renderItem));
       this.field_70545_g = 0.0F;
       this.field_70547_e = 60;
       this.field_190017_n = false;
    }
 
    public KitsunebiParticle setHorizontal() {
       this.yRotate = 90.0F;
       return this;
    }
 
 
    public void func_189213_a() {
       if (this.field_187122_b != null && this.field_187122_b.func_175623_d(new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h))) {
          this.func_187112_i();
       }
       super.func_189213_a();
    }
 
 
    public void func_180434_a(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
       rotationZ = this.yRotate != 0.0F ? MathHelper.func_76134_b(this.yRotate * 0.017453292F) : rotationZ;
       super.func_180434_a(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
 
 
    public IParticleRenderType func_217558_b() {
       return IParticleRenderType.field_217601_a;
    }
 
 
    public float func_217561_b(float p_217561_1_) {
       return 0.5F;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 16 ms
	
	Decompiled with FernFlower.
*/