package com.ren130302.lib;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ModUtils {
    private final String modid;

    public ModUtils(String modid) {
	this.modid = modid;
    }

    public String modid() {
	return this.modid;
    }

    /**
     * <pre>
     * create(ForgeRegistries.Block);
     * </pre>
     *
     * @param type {@link ForgeRegistries}
     * @return
     */
    public <B> DeferredRegister<B> createDeferredRegister(IForgeRegistry<B> type) {
	return DeferredRegister.create(type, this.modid);
    }
}
