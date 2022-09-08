 package com.ruby.meshi.paticle;
 
 import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;
 
 
 
 
 
 public class DamageParticle extends SpriteTexturedParticle {
    protected DamageParticle(World p_i50998_1_, double p_i50998_2_, double p_i50998_4_, double p_i50998_6_) {
       super(p_i50998_1_, p_i50998_2_, p_i50998_4_, p_i50998_6_);
    }
 
 
    public IParticleRenderType func_217558_b() {
       return IParticleRenderType.field_217602_b;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 3 ms
	
	Decompiled with FernFlower.
*/