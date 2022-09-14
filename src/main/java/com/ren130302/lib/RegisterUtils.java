package com.ren130302.lib;

import java.util.function.Supplier;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class RegisterUtils {

    private final String modid;

    public RegisterUtils(String modid) {
	this.modid = modid;
    }

    public static RegisterUtils DEF_REG(String modid) {
	return new RegisterUtils(modid);
    }

    /**
     * <pre>
     * create(ForgeRegistries.Block);
     * </pre>
     *
     * @param type {@link ForgeRegistries}
     * @return
     */
    public <B> DeferredRegister<B> create(IForgeRegistry<B> type) {
	return DeferredRegister.create(type, this.modid);
    }

    public String modId() {
	return this.modid;
    }

    public static <T> RegistryObject<T> define(DeferredRegister<T> deferredRegister, String name, Supplier<T> target) {
	return deferredRegister.register(name, target);
    }

    public static <T> RegistryObject<T> define(DeferredRegister<T> deferredRegister, String name, T target) {
	return deferredRegister.register(name, () -> target);
    }

    public static <T> RegistryObject<T> define(DeferredRegister<T> deferredRegister, Enum _enum, Supplier<T> target) {
	return deferredRegister.register(_enum.name().toLowerCase(), target);
    }

    public static <T> RegistryObject<T> define(DeferredRegister<T> deferredRegister, Enum _enum, T target) {
	return deferredRegister.register(_enum.name().toLowerCase(), () -> target);
    }

}