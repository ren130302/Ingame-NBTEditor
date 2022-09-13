package com.ren130302.bamboo.enchantment;

import com.ren130302.bamboo.BambooMod;
import com.ren130302.bamboo.item.NinjaBracelet;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMEnchantments {

    /*
     *
     * UnbreakingBracelet
     */
    private static final DeferredRegister<Enchantment> DEF_REG = BambooMod.REGISTER
	    .create(ForgeRegistries.ENCHANTMENTS);
    /* COMMON */
    public static RegistryObject<Enchantment> QUICK_THROW = DEF_REG.register("quick_throw", QuickThrow::new);
    public static RegistryObject<Enchantment> POWER_THROW = DEF_REG.register("power_throw", PowerThrow::new);
    public static RegistryObject<Enchantment> CRITICAL_THROW = DEF_REG.register("critical_throw", CriticalThrow::new);
    public static RegistryObject<Enchantment> POISON_THROW = DEF_REG.register("poison_throw", PoisonThrow::new);
    public static RegistryObject<Enchantment> SNIPE_THROW = DEF_REG.register("snipe_throw", SnipeThrow::new);
    /* UNCOMMON */
    public static RegistryObject<Enchantment> ECONOMY_BRACELET = DEF_REG.register("economy_bracelet",
	    EconomyBracelet::new);
    public static RegistryObject<Enchantment> UNBREAKING_BRACELET = DEF_REG.register("unbreaking_bracelet",
	    UnbreakingBracelet::new);
    public static RegistryObject<Enchantment> PICKPOCKET = DEF_REG.register("pickpocket", Pickpocket::new);
    /* RARE */
    public static RegistryObject<Enchantment> FLAME_THROW = DEF_REG.register("flame_throw", FlameThrow::new);
    public static RegistryObject<Enchantment> HUNTER_THROW = DEF_REG.register("hunter_throw", HunterThrow::new);
    public static RegistryObject<Enchantment> ROGUE_THROW = DEF_REG.register("rogue_throw", RogueThrow::new);
    public static RegistryObject<Enchantment> MULTI_THROW = DEF_REG.register("multi_throw", MultiThrow::new);
    public static RegistryObject<Enchantment> INFINITY_THROW = DEF_REG.register("infinity_throw", InfinityThrow::new);
    /* VERYRARE */
    public static RegistryObject<Enchantment> ASSASSIN_THROW = DEF_REG.register("assassin_throw", AssassinThrow::new);
    public static RegistryObject<Enchantment> RETURN_THROW = DEF_REG.register("return_throw", ReturnThrow::new);

    public class Category {
	public static final EnchantmentCategory BRACELET = EnchantmentCategory.create("bracelet",
		item -> (item instanceof NinjaBracelet));
    }
}
