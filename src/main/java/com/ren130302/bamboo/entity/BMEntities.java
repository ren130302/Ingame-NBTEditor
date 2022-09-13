package com.ren130302.bamboo.entity;

import com.ren130302.bamboo.BambooMod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMEntities {
    private static final DeferredRegister<EntityType<?>> DEF_REG = BambooMod.REGISTER
	    .create(ForgeRegistries.ENTITY_TYPES);

    public static final RegistryObject<EntityType<Shuriken>> SHURIKEN = register("shuriken_entity", EntityType.Builder
	    .of(Shuriken::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).sized(0.25F, 0.25F));
    public static final RegistryObject<EntityType<Scarecrow>> SCARECROW = register("scarecrow_entity",
	    EntityType.Builder.<Scarecrow>of(Scarecrow::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
		    .sized(0.5F, 1.975F));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name,
	    EntityType.Builder<T> entityType) {
	return DEF_REG.register(name, () -> entityType.build(name));
    }
}
