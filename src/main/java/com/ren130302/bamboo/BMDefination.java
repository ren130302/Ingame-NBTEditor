package com.ren130302.bamboo;

import com.ren130302.bamboo.block.Andon;
import com.ren130302.bamboo.enchantment.AssassinThrow;
import com.ren130302.bamboo.enchantment.CriticalThrow;
import com.ren130302.bamboo.enchantment.EconomyBracelet;
import com.ren130302.bamboo.enchantment.FlameThrow;
import com.ren130302.bamboo.enchantment.HunterThrow;
import com.ren130302.bamboo.enchantment.InfinityThrow;
import com.ren130302.bamboo.enchantment.MultiThrow;
import com.ren130302.bamboo.enchantment.Pickpocket;
import com.ren130302.bamboo.enchantment.PoisonThrow;
import com.ren130302.bamboo.enchantment.PowerThrow;
import com.ren130302.bamboo.enchantment.QuickThrow;
import com.ren130302.bamboo.enchantment.ReturnThrow;
import com.ren130302.bamboo.enchantment.RogueThrow;
import com.ren130302.bamboo.enchantment.SnipeThrow;
import com.ren130302.bamboo.enchantment.UnbreakingBracelet;
import com.ren130302.bamboo.item.NinjaBracelet;
import com.ren130302.lib.RegisterUtils.DefineObject;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMDefination {

    private static final DeferredRegister<Enchantment> ENCHANTMENTS = BambooMod.REGISTER
	    .create(ForgeRegistries.ENCHANTMENTS);

    public BMDefination(IEventBus bus) {
	ENCHANTMENTS.register(bus);
    }

    public static class Biomes {

    }

    public static class Blocks {
	private static final DeferredRegister<Block> DEF_REG = BambooMod.REGISTER.create(ForgeRegistries.BLOCKS);

	public static final RegistryObject<Block> ANDON = register("andon", new Andon());

	private static RegistryObject<Block> register(String name, Block block) {
	    return DEF_REG.register(name, () -> block);
	}
    }

//    public static class Enchantments {
//	public static final RegistryObject<Enchantment> QUICK_THROW = register("quick_throw", new QuickThrow());
//	public static final RegistryObject<Enchantment> POWER_THROW = register("power_throw", new PowerThrow());
//	public static final RegistryObject<Enchantment> CRITICAL_THROW = register("critical_throw",
//		new CriticalThrow());
//	public static final RegistryObject<Enchantment> POISON_THROW = register("poison_throw", new PoisonThrow());
//	public static final RegistryObject<Enchantment> SNIPE_THROW = register("snipe_throw", new SnipeThrow());
//	/* UNCOMMON */
//	public static final RegistryObject<Enchantment> ECONOMY_BRACELET = register("economy_bracelet",
//		new EconomyBracelet());
//	public static final RegistryObject<Enchantment> UNBREAKING_BRACELET = register("unbreaking_bracelet",
//		new UnbreakingBracelet());
//	public static final RegistryObject<Enchantment> PICKPOCKET = register("pickpocket", new Pickpocket());
//	/* RARE */
//	public static final RegistryObject<Enchantment> FLAME_THROW = register("flame_throw", new FlameThrow());
//	public static final RegistryObject<Enchantment> HUNTER_THROW = register("hunter_throw", new HunterThrow());
//	public static final RegistryObject<Enchantment> ROGUE_THROW = register("rogue_throw", new RogueThrow());
//	public static final RegistryObject<Enchantment> MULTI_THROW = register("multi_throw", new MultiThrow());
//	public static final RegistryObject<Enchantment> INFINITY_THROW = register("infinity_throw",
//		new InfinityThrow());
//	/* VERYRARE */
//	public static final RegistryObject<Enchantment> ASSASSIN_THROW = register("assassin_throw",
//		new AssassinThrow());
//	public static final RegistryObject<Enchantment> RETURN_THROW = register("return_throw", new ReturnThrow());
//
//	private static final DeferredRegister<Enchantment> DEF_REG = BambooMod.REGISTER
//		.create(ForgeRegistries.ENCHANTMENTS);
//
//	private final RegistryObject<Enchantment> registryObject;
//
//	private Enchantments(Enchantment enchantment) {
//	    this.registryObject = this.DEF_REG.register(this.name().toLowerCase(), () -> enchantment);
//	}
//
//	public class Category {
//	    public static final EnchantmentCategory BRACELET = EnchantmentCategory.create("bracelet",
//		    item -> (item instanceof NinjaBracelet));
//	}
//
//	private static RegistryObject<Enchantment> register(String name, Enchantment enchantment) {
//	    return DEF_REG.register(name, () -> enchantment);
//	}
//    }

    public static enum Enchantments {
	QUICK_THROW(new QuickThrow()), POWER_THROW(new PowerThrow()), CRITICAL_THROW(new CriticalThrow()),
	POISON_THROW(new PoisonThrow()), SNIPE_THROW(new SnipeThrow()),
	/* UNCOMMON */
	ECONOMY_BRACELET(new EconomyBracelet()), UNBREAKING_BRACELET(new UnbreakingBracelet()),
	PICKPOCKET(new Pickpocket()),
	/* RARE */
	FLAME_THROW(new FlameThrow()), HUNTER_THROW(new HunterThrow()), ROGUE_THROW(new RogueThrow()),
	MULTI_THROW(new MultiThrow()), INFINITY_THROW(new InfinityThrow()),
	/* VERYRARE */
	ASSASSIN_THROW(new AssassinThrow()), RETURN_THROW(new ReturnThrow());

	private final DefineObject<Enchantment> defineObject;

	private Enchantments(Enchantment enchantment) {
	    this.defineObject = DefineObject.define(ENCHANTMENTS, this, enchantment);
	}

	public final RegistryObject<Enchantment> get() {
	    return this.defineObject.get();
	}

	public class Category {
	    public static final EnchantmentCategory BRACELET = EnchantmentCategory.create("bracelet",
		    item -> (item instanceof NinjaBracelet));
	}
    }

}
