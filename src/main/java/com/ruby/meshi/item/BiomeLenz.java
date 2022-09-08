 package com.ruby.meshi.item;
 
 import com.ruby.meshi.block.tileentity.ManekiNekoTileEntity;
 import com.ruby.meshi.item.BiomeLenz.1;
 import com.ruby.meshi.item.BiomeLenz.DPSBukket;
 import net.minecraft.client.Minecraft;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity;
 import net.minecraft.entity.projectile.ThrowableEntity;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemUseContext;
 import net.minecraft.item.Item.Properties;
 import net.minecraft.util.ActionResult;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.RayTraceContext.FluidMode;
 import net.minecraft.world.World;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.client.event.InputEvent.MouseInputEvent;
 import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
 import net.minecraftforge.event.TickEvent.ClientTickEvent;
 import net.minecraftforge.event.TickEvent.Phase;
 import net.minecraftforge.event.entity.living.LivingDamageEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.FORGE,
    value = {Dist.CLIENT}
 )
 public class BiomeLenz extends Item implements Accessory {
    private static Vec3d vec;
    private static DPSBukket damageBukket = new DPSBukket((1)null);
 
    public BiomeLenz(Properties properties) {
       super(properties);
    }
 
 
    public ActionResultType func_195939_a(ItemUseContext context) {
       return super.func_195939_a(context);
    }
 
 
    public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
       return super.func_77659_a(worldIn, playerIn, handIn);
    }
 
 
    public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
       super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
    }
 
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void renderText(Text event) {
       PlayerEntity player = Minecraft.func_71410_x().field_71439_g;
       if (player != null) {
          World world = player.field_70170_p;
          if (0 < player.field_71071_by.func_213901_a(HiganItems.BIOME_LENZ)) {
             int hour = (int)((world.func_72912_H().func_76073_f() + 6000L) % 24000L / 1000L);
             int minute = (int)((world.func_72912_H().func_76073_f() + 6000L) % 600L / 10L);
             event.getLeft().add("Time: " + String.format("%02d:%02d", hour, minute) + (world.func_72912_H().func_76059_o() ? " Rain" : "") + (world.func_72912_H().func_76061_m() ? " Thunder" : ""));
             event.getLeft().add("NPos: " + getFormatedPos(player.func_180425_c()) + "(" + Direction.func_176733_a((double)player.func_189653_aC().field_189983_j) + ")");
             event.getLeft().add("SPos: " + getFormatedPos(player.getBedLocation(player.field_71093_bK)));
             if (vec != null) {
                Vec3d subVec = roundDown(func_219968_a(world, player, FluidMode.NONE).func_216347_e()).func_178788_d(vec);
                event.getLeft().add("RCPos: " + getFormatedPos(new BlockPos(subVec)));
             }
             event.getLeft().add("Biome: " + player.field_70170_p.func_180494_b(player.func_180425_c()).func_205403_k().func_150254_d());
             event.getLeft().add("DPM: " + String.format("%.1f", damageBukket.getDPM()) + "(" + String.format("%.1f", damageBukket.getDPS()) + ")");
             if (ManekiNekoTileEntity.isEntityDeny(player.func_180425_c())) {
                event.getLeft().add("No mob spawn area");
             }         }
       }
 
    }
 
    private static Vec3d roundDown(Vec3d vec) {
       return new Vec3d((double)((int)vec.func_82615_a()), (double)((int)vec.func_82617_b()), (double)((int)vec.func_82616_c()));
    }
 
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void rightClick(MouseInputEvent event) {
       if (event.getButton() == 1) {
          PlayerEntity player = Minecraft.func_71410_x().field_71439_g;
          if (player != null) {
             World world = player.field_70170_p;
             if (0 < player.field_71071_by.func_213901_a(HiganItems.BIOME_LENZ)) {
                vec = roundDown(func_219968_a(world, player, FluidMode.NONE).func_216347_e());
             }         }
       }
 
    }
 
    private static String getFormatedPos(BlockPos pos) {
       return pos == null ? "" : pos.func_177958_n() + ", " + pos.func_177956_o() + ", " + pos.func_177952_p();
    }
 
 
 
 
 
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
       Entity attacker = event.getSource().func_76364_f();
       if (attacker instanceof AbstractArrowEntity) {
          attacker = ((AbstractArrowEntity)attacker).func_212360_k();
       }
       if (attacker instanceof ThrowableEntity) {
          attacker = ((ThrowableEntity)attacker).func_85052_h();
       }
       if (attacker instanceof PlayerEntity && ((Entity)attacker).func_110124_au() == Minecraft.func_71410_x().field_71439_g.func_110124_au()) {
 
          damageBukket.sum((double)event.getAmount());
       }
 
    }
 
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onTick(ClientTickEvent event) {
       if (event.phase == Phase.END) {
          World world = Minecraft.func_71410_x().field_71441_e;
          if (world != null && world.func_72912_H().func_82573_f() % 20L == 0L) {
             damageBukket.nextBukket();
          }      }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 20 ms
	
	Decompiled with FernFlower.
*/