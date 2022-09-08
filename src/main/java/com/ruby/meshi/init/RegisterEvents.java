 package com.ruby.meshi.init;
 
 import java.util.Map;
import java.util.stream.Stream;

import com.ruby.meshi.block.CustomItemBlock;
import com.ruby.meshi.block.HideCreateTab;
import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.client.CreativeTab;
import com.ruby.meshi.core.MeshiMod;
import com.ruby.meshi.crafting.HiganRecipeSerializer;
import com.ruby.meshi.enchant.HiganEnchant;
import com.ruby.meshi.entity.HiganEntityType;
import com.ruby.meshi.fluid.MeshiFluids;
import com.ruby.meshi.item.HiganItems;
import com.ruby.meshi.world.biome.HiganBiomes;
import com.ruby.meshi.world.gen.HiganGenerater;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;
 @EventBusSubscriber(
    bus = Bus.MOD
 )
 public class RegisterEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(Register<Block> reg) {
       reg.getRegistry().registerAll(getBlocks());
    }
 
 
    @SubscribeEvent
    public static void onItemsRegistry(Register<Item> reg) {
       reg.getRegistry().registerAll(getBlockItems());
       reg.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganItems.class).toArray((x$0) -> {         return new Item[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onEntityRegistry(Register<EntityType<?>> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganEntityType.class).toArray((x$0) -> {         return new EntityType[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onEntityTypeRegistry(Register<TileEntityType<?>> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganTileEntityType.class).toArray((x$0) -> {         return new TileEntityType[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onContinerTypeRegistry(Register<ContainerType<?>> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganContainerType.class).toArray((x$0) -> {         return new ContainerType[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onWorldGenRegistry(Register<Feature<?>> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganGenerater.class).toArray((x$0) -> {         return new Feature[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onRecipeSerialize(Register<IRecipeSerializer<?>> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganRecipeSerializer.class).toArray((x$0) -> {         return new IRecipeSerializer[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onBiomeRegistry(Register<Biome> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganBiomes.class).toArray((x$0) -> {         return new Biome[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onEnchantRegistry(Register<Enchantment> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(HiganEnchant.class).toArray((x$0) -> {         return new Enchantment[x$0];      }));
    }
 
    @SubscribeEvent
    public static void onFluidRegistry(Register<Fluid> event) {
       event.getRegistry().registerAll((IForgeRegistryEntry[])getFields(MeshiFluids.class).toArray((x$0) -> {         return new Fluid[x$0];      }));
    }
 
 
 
 
 
 
 
    private static Block[] getBlocks() {
       return (Block[])getFields(SakuraBlocks.class).toArray((x$0) -> {
          return new Block[x$0];
       });
    }   private static Item[] getBlockItems() {
       return (Item[])Stream.of(getBlocks()).map((b) -> {
 
          Properties prop = new Properties();
          Object item;
          if (b instanceof CustomItemBlock) {
             item = ((CustomItemBlock)b).getBlockItem(b, prop);
          } else {
             if (b.getClass().getAnnotation(HideCreateTab.class) == null) {
                prop = prop.func_200916_a(CreativeTab.ITEM_GROUP);
             }
             item = new BlockItem(b, prop);
          }
          return item != CustomItemBlock.EMPTY ? (Item)((Item)item).setRegistryName(b.getRegistryName()) : CustomItemBlock.EMPTY;
       }).filter((i) -> {
          return i != CustomItemBlock.EMPTY;
       }).toArray((x$0) -> {
          return new Item[x$0];
       });
    }
 
 
 
 
 
 
    public static <T> Stream<T> getFields(Class<?> c) {
       return Stream.of(c.getFields()).flatMap((f) -> {
 
          try {
             Object obj = f.get((Object)null);
             return obj.getClass().isArray() ? Stream.of((Object[])((Object[])obj)) : Stream.of(obj);
 
 
 
 
 
 
          } catch (Exception var2) {
 
             MeshiMod.warnlog("Field get Exception: " + f.toString());
             return null;
          }
       });
    }
    public static Item[] getBlockToItem(Block... block) {      Stream var10000 = Stream.of(block);      Map var10001 = Item.field_179220_a;
       Item.field_179220_a.getClass();
       return (Item[])var10000.map(var10001::get).toArray((x$0) -> {
          return new Item[x$0];
       });
    }   public static Block[] getItemToBlock(Item... item) {
       return (Block[])Stream.of(item).map(Block::func_149634_a).toArray((x$0) -> {
          return new Block[x$0];
       });
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 29 ms
	
	Decompiled with FernFlower.
*/