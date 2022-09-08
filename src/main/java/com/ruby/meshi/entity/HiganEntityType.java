 package com.ruby.meshi.entity;
 
 import net.minecraft.entity.EntityClassification;
import net.minecraft.world.entity.EntityType;
 public class HiganEntityType {
    public static final EntityType<ShurikenEntity> SHURIKEN;
    public static final EntityType<ScarecrowEntity> SCARECROW;
 
    static {
       SHURIKEN = create("shuriken_entity", Builder.func_220319_a(EntityClassification.MISC).setCustomClientFactory(ShurikenEntity::new).setShouldReceiveVelocityUpdates(true).func_220321_a(0.25F, 0.25F));
       SCARECROW = create("scarecrow_entity", Builder.func_220319_a(EntityClassification.MISC).setCustomClientFactory(ScarecrowEntity::new).setShouldReceiveVelocityUpdates(true).func_220321_a(0.5F, 1.975F));
    }
 
    public static <T extends Entity> EntityType<T> create(String name, Builder<T> builder) {
       return (EntityType)builder.func_206830_a(name).setRegistryName("meshi", name);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 6 ms
	
	Decompiled with FernFlower.
*/