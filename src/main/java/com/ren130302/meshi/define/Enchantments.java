package com.ren130302.meshi.define;

import java.util.stream.Stream;

import com.ren130302.lib.RegisterUtils;
import com.ren130302.meshi.BambooMod;
import com.ren130302.meshi.enchantment.AssassinThrow;
import com.ren130302.meshi.enchantment.CriticalThrow;
import com.ren130302.meshi.enchantment.EconomyBracelet;
import com.ren130302.meshi.enchantment.FlameThrow;
import com.ren130302.meshi.enchantment.HunterThrow;
import com.ren130302.meshi.enchantment.InfinityThrow;
import com.ren130302.meshi.enchantment.MultiThrow;
import com.ren130302.meshi.enchantment.Pickpocket;
import com.ren130302.meshi.enchantment.PoisonThrow;
import com.ren130302.meshi.enchantment.PowerThrow;
import com.ren130302.meshi.enchantment.QuickThrow;
import com.ren130302.meshi.enchantment.ReturnThrow;
import com.ren130302.meshi.enchantment.RogueThrow;
import com.ren130302.meshi.enchantment.SnipeThrow;
import com.ren130302.meshi.enchantment.UnbreakingBracelet;
import com.ren130302.meshi.item.NinjaBracelet;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public enum Enchantments {
    /* COMMON */
    QUICK_THROW(new QuickThrow(Rarity.COMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    POWER_THROW(new PowerThrow(Rarity.COMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    CRITICAL_THROW(new CriticalThrow(Rarity.COMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    POISON_THROW(new PoisonThrow(Rarity.COMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    SNIPE_THROW(new SnipeThrow(Rarity.COMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    /* UNCOMMON */
    ECONOMY_BRACELET(
	    new EconomyBracelet(Rarity.UNCOMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    UNBREAKING_BRACELET(
	    new UnbreakingBracelet(Rarity.UNCOMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    PICKPOCKET(new Pickpocket(Rarity.UNCOMMON, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    /* RARE */
    FLAME_THROW(new FlameThrow(Rarity.RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    HUNTER_THROW(new HunterThrow(Rarity.RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    ROGUE_THROW(new RogueThrow(Rarity.RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    MULTI_THROW(new MultiThrow(Rarity.RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    INFINITY_THROW(new InfinityThrow(Rarity.RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    /* VERYRARE */
    ASSASSIN_THROW(
	    new AssassinThrow(Rarity.VERY_RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND })),
    RETURN_THROW(new ReturnThrow(Rarity.VERY_RARE, Category.BRACELET, new EquipmentSlot[] { EquipmentSlot.MAINHAND }));

    static {
	DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
		BambooMod.MODID);

	Stream.of(values()).forEach(value -> RegisterUtils.define(ENCHANTMENTS, value, () -> value.get()));

	ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private final Enchantment enchantment;

    private Enchantments(Enchantment enchantment) {
	this.enchantment = enchantment;
    }

    public final Enchantment get() {
	return this.enchantment;
    }

    public class Category {
	public static final EnchantmentCategory BRACELET = EnchantmentCategory.create("bracelet",
		item -> (item instanceof NinjaBracelet));
    }
}