package com.ren130302.bamboo.enchantment;

import java.util.function.Supplier;

import com.ren130302.bamboo.BambooMod;
import com.ruby.meshi.enchant.AssassinThrow;
import com.ruby.meshi.enchant.CriticalThrow;
import com.ruby.meshi.enchant.EconomyBracelet;
import com.ruby.meshi.enchant.FlameThrow;
import com.ruby.meshi.enchant.HunterThrow;
import com.ruby.meshi.enchant.InfinityThrow;
import com.ruby.meshi.enchant.MultiThrow;
import com.ruby.meshi.enchant.Pickpocket;
import com.ruby.meshi.enchant.PoisonThrow;
import com.ruby.meshi.enchant.PowerThrow;
import com.ruby.meshi.enchant.QuickThrow;
import com.ruby.meshi.enchant.ReturnThrow;
import com.ruby.meshi.enchant.RogueThrow;
import com.ruby.meshi.enchant.SnipeThrow;
import com.ruby.meshi.enchant.UnbreakingBracelet;
import com.ruby.meshi.item.NinjaBracelet;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMEnchantments {

	private static final DeferredRegister<Enchantment> DEF_REG = BambooMod.REGISTER
			.create(ForgeRegistries.ENCHANTMENTS);

	public static RegistryObject<Enchantment> QUICK_THROW;
	public static RegistryObject<Enchantment> POWER_THROW;
	public static RegistryObject<Enchantment> CRITICAL_THROW;
	public static RegistryObject<Enchantment> POISON_THROW;
	public static RegistryObject<Enchantment> SNIPE_THROW;
	public static RegistryObject<Enchantment> ECONOMY_BRACELET;
	public static RegistryObject<Enchantment> UNBREAKING_BRACELET;
	public static RegistryObject<Enchantment> PICKPOCKET;
	public static RegistryObject<Enchantment> FLAME_THROW;
	public static RegistryObject<Enchantment> HUNTER_THROW;
	public static RegistryObject<Enchantment> ROGUE_THROW;
	public static RegistryObject<Enchantment> MULTI_THROW;
	public static RegistryObject<Enchantment> INFINITY_THROW;
	public static RegistryObject<Enchantment> ASSASSIN_THROW;
	public static RegistryObject<Enchantment> RETURN_THROW;

	public static void init() {
		QUICK_THROW = register("quick_throw", QuickThrow::new);
		POWER_THROW = register("power_throw", PowerThrow::new);
		CRITICAL_THROW = register("critical_throw", CriticalThrow::new);
		POISON_THROW = register("poison_throw", PoisonThrow::new);
		SNIPE_THROW = register("snipe_throw", SnipeThrow::new);

		ECONOMY_BRACELET = register("economy_bracelet", EconomyBracelet::new);
		UNBREAKING_BRACELET = register("unbreaking_bracelet", UnbreakingBracelet::new);
		PICKPOCKET = register("pickpocket", Pickpocket::new);

		FLAME_THROW = register("flame_throw", FlameThrow::new);
		HUNTER_THROW = register("hunter_throw", HunterThrow::new);
		ROGUE_THROW = register("rogue_throw", RogueThrow::new);
		MULTI_THROW = register("multi_throw", MultiThrow::new);
		INFINITY_THROW = register("infinity_throw", InfinityThrow::new);

		ASSASSIN_THROW = register("assassin_throw", AssassinThrow::new);
		RETURN_THROW = register("return_throw", ReturnThrow::new);
	}

	private static RegistryObject<Enchantment> register(String name, Supplier<Enchantment> enc) {
		return DEF_REG.register(name, enc);
	}

	public class Category {
		public static final EnchantmentCategory BRACELET = EnchantmentCategory.create("bracelet",
				item -> (item instanceof NinjaBracelet));
	}
}
