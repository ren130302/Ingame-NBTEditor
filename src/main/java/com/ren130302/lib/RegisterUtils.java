package com.ren130302.lib;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

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
	 * need(ForgeRegistries.Block);
	 * </pre>
	 * 
	 * @param type {@link ForgeRegistries}
	 * @return
	 */
	public DeferredRegister<?> need(IForgeRegistry<?> type) {
		return DeferredRegister.create(type, this.modid);
	}

	public String modId() {
		return this.modid;
	}
}
