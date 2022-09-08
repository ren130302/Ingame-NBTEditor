 package com.ruby.meshi.item;
 
 import com.ruby.meshi.item.KatanaDrop.KatanaDropListener;
 import java.util.UUID;
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.function.Consumer;
 import net.minecraft.client.renderer.Quaternion;
 import net.minecraft.client.renderer.Vector3f;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.IProjectile;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.monster.BlazeEntity;
 import net.minecraft.entity.monster.CreeperEntity;
 import net.minecraft.entity.monster.SkeletonEntity;
 import net.minecraft.entity.monster.SlimeEntity;
 import net.minecraft.entity.monster.SpiderEntity;
 import net.minecraft.entity.monster.ZombieEntity;
 import net.minecraft.entity.passive.AnimalEntity;
 import net.minecraft.entity.passive.CatEntity;
 import net.minecraft.entity.passive.GolemEntity;
 import net.minecraft.entity.passive.OcelotEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.player.ServerPlayerEntity;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.IItemPropertyGetter;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemTier;
 import net.minecraft.item.SwordItem;
 import net.minecraft.item.UseAction;
 import net.minecraft.item.Item.Properties;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.particles.ParticleTypes;
 import net.minecraft.util.ActionResult;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.DamageSource;
 import net.minecraft.util.Hand;
 import net.minecraft.util.ResourceLocation;
 import net.minecraft.util.math.MathHelper;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.world.World;
 import net.minecraft.world.server.ServerWorld;
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.event.ForgeEventFactory;
 import net.minecraftforge.event.entity.living.LivingAttackEvent;
 import net.minecraftforge.event.entity.player.CriticalHitEvent;
 import net.minecraftforge.eventbus.api.Event.Result;
 
 public class Katana extends SwordItem implements KatanaDropListener {
    public static final ThreadLocalRandom random = ThreadLocalRandom.current();
    public static final int ACTIVE_TIME = 20;
    public static final String PLAYER_SOURCE = "player";
 
    public Katana(Properties builder) {
       super(ItemTier.IRON, 3, -2.0F, builder);
       this.func_185043_a(new ResourceLocation("blocking"), (stack, world, entity) -> {
          return entity != null && entity.func_184587_cr() && entity.func_184607_cu() == stack ? 1.0F : 0.0F;
 
       });
       this.eventBusInit();
    }
 
    public void eventBusInit() {
       MinecraftForge.EVENT_BUS.addListener(this::onReflect);
       MinecraftForge.EVENT_BUS.addListener(this::onAttack);
    }
 
 
    public ItemStack func_77654_b(ItemStack stack, World worldIn, LivingEntity entityLiving) {
       if (entityLiving instanceof PlayerEntity) {
          ((PlayerEntity)entityLiving).func_184811_cZ().func_185145_a(this, 20);
       }
       return super.func_77654_b(stack, worldIn, entityLiving);
    }
 
 
    public void func_77615_a(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
       if (entityLiving instanceof PlayerEntity) {
          ((PlayerEntity)entityLiving).func_184811_cZ().func_185145_a(this, 20 + timeLeft);
       }
    }
 
 
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
       super.onUsingTick(stack, player, count);
       World world = player.func_130014_f_();
       if (world.field_72995_K && this.isReflect(count)) {
 
 
          Vec3d eyeVec = new Vec3d(random.nextDouble() - 0.375D, -0.15D, 0.6D);
          eyeVec = eyeVec.func_178789_a(-player.field_70125_A * 0.017453292F);
          eyeVec = eyeVec.func_178785_b(-player.field_70177_z * 0.017453292F);
          eyeVec = eyeVec.func_72441_c(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
 
          double randX = random.nextGaussian() * 0.02D;
          double randY = random.nextGaussian() * 0.02D;
          double randZ = random.nextGaussian() * 0.02D;
          Vec3d vec = player.func_213303_ch().func_72441_c(1.0D, 0.0D, 1.0D);
          vec = Vec3d.func_189984_a(player.func_189653_aC());
 
          world.func_195594_a(ParticleTypes.field_197614_g, eyeVec.field_72450_a, eyeVec.field_72448_b, eyeVec.field_72449_c, randX, randY, randZ);
       }
 
    }
 
 
    private boolean isReflect(int count) {
       return count <= 18 && count > 10;
    }
 
 
    public UseAction func_77661_b(ItemStack stack) {
       return UseAction.BLOCK;
    }
 
 
    public int func_77626_a(ItemStack stack) {
       return 20;
    }
 
 
    public ActionResult<ItemStack> func_77659_a(World worldIn, PlayerEntity playerIn, Hand handIn) {
       if (!worldIn.field_72995_K && playerIn instanceof ServerPlayerEntity) {
 
          ItemStack offStack = playerIn.func_184586_b(Hand.OFF_HAND);
          if (offStack.func_190926_b() || this.isKatana(offStack) || offStack.func_77973_b() instanceof BlockItem) {
             Hand activateHand = handIn;
             if (offStack.func_77973_b() == this) {
 
                activateHand = Hand.OFF_HAND;
             }
             playerIn.func_184598_c(activateHand);
             return new ActionResult(ActionResultType.SUCCESS, playerIn.func_184586_b(handIn));
          }
       }
 
       return new ActionResult(ActionResultType.FAIL, playerIn.func_184586_b(handIn));
    }
 
    public boolean isKatana(ItemStack stack) {
       return stack.func_77973_b() instanceof Katana;
    }
 
 
 
    public int getMaxDamage(ItemStack stack) {
       return super.getMaxDamage(stack) / 2;
    }
 
    public void onReflect(LivingAttackEvent event) {
       DamageSource source = event.getSource();
       LivingEntity entity = event.getEntityLiving();
       if (source != null && entity != null) {
          World world = entity.field_70170_p;
          if (!world.field_72995_K && this.isKatana(entity.func_184607_cu())) {
 
             Entity sourceEntity = source.func_76364_f();
             if (sourceEntity != null) {
                if (this.isReflect(entity.func_184605_cv())) {
                   boolean isReflected = false;
                   if (sourceEntity instanceof IProjectile && source.func_76352_a()) {
 
 
                      Entity reflectEntity = sourceEntity.func_200600_R().func_200721_a(world);
                      UUID uuid = reflectEntity.func_110124_au();
                      CompoundNBT nbt = sourceEntity.func_189511_e(new CompoundNBT());
 
                      nbt.func_186854_a("UUID", uuid);
                      reflectEntity.func_70020_e(nbt);
                      sourceEntity.func_70106_y();
 
                      Vec3d vec3d1 = entity.func_213286_i(1.0F);
                      Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), 0.0F, true);
                      Vec3d vec3d = entity.func_70676_i(1.0F);
                      Vector3f vector3f = new Vector3f(vec3d);
                      vector3f.func_214905_a(quaternion);
                      ((IProjectile)reflectEntity).func_70186_c((double)vector3f.func_195899_a(), (double)vector3f.func_195900_b(), (double)vector3f.func_195902_c(), 1.6F, 0.0F);
 
                      world.func_217376_c(reflectEntity);
                      isReflected = true;
 
                   } else if (sourceEntity instanceof LivingEntity) {
 
                      DamageSource reflectDmg = DamageSource.func_76358_a(entity);
                      float targetHealth = ((LivingEntity)sourceEntity).func_110143_aJ();
                      sourceEntity.func_70097_a(reflectDmg, event.getAmount() * 2.0F);
                      if (entity instanceof PlayerEntity) {
                         float attackedTargetHealth = targetHealth - ((LivingEntity)sourceEntity).func_110143_aJ();
                         int pCount = (int)((double)attackedTargetHealth * 0.5D);
                         ((ServerWorld)world).func_195598_a(ParticleTypes.field_197615_h, sourceEntity.field_70165_t, sourceEntity.field_70163_u + (double)(sourceEntity.func_213302_cg() * 0.5F), sourceEntity.field_70161_v, pCount, 0.1D, 0.0D, 0.1D, 0.2D);
                      }
 
                      isReflected = true;
                   }
                   if (isReflected && entity instanceof PlayerEntity) {
 
                      double d0 = (double)(-MathHelper.func_76126_a(entity.field_70177_z * 0.017453292F));
                      double d1 = (double)MathHelper.func_76134_b(entity.field_70177_z * 0.017453292F);
                      ((ServerWorld)world).func_195598_a(ParticleTypes.field_197603_N, entity.field_70165_t + d0, entity.field_70163_u + (double)entity.func_213302_cg() * 0.75D, entity.field_70161_v + d1, 0, d0, 0.0D, d1, 0.0D);
                   }
 
                   event.setCanceled(isReflected);
 
 
                } else if (entity instanceof PlayerEntity) {
                   entity.func_184607_cu().func_222118_a((MathHelper.func_76141_d(event.getAmount()) + 1) * 2, entity, (p) -> {
                      p.func_213334_d(entity.func_184600_cs());
                      ForgeEventFactory.onPlayerDestroyItem((PlayerEntity)entity, entity.func_184607_cu(), entity.func_184600_cs());
                   });
 
                }
             }
          }
       }
 
    }
 
    public void onAttack(CriticalHitEvent event) {
       PlayerEntity player = event.getPlayer();
 
       if (this.isTwoHanded(player)) {
 
 
          event.setDamageModifier(this.getCritRate(event.getTarget()));
          event.setResult(Result.ALLOW);
       }
    }
 
    public float getCritRate(Entity entity) {
       float mod = 1.5F;
 
 
       if (!(entity instanceof SlimeEntity) && !(entity instanceof CatEntity) && !(entity instanceof OcelotEntity)) {         }      } else {
          mod = 0.0F;
          if (!(entity instanceof ZombieEntity) && !(entity instanceof CreeperEntity) && !(entity instanceof SpiderEntity)) {            }         } else {
             mod = 2.0F;
             if (entity instanceof AnimalEntity) {
                mod = 4.0F;
             } else if (entity instanceof SkeletonEntity) {
                mod = 1.0F;
             } else if (entity instanceof BlazeEntity) {
                mod = 0.7F;
             } else if (entity instanceof GolemEntity) {
                mod = 0.1F;
       }
       return mod;
    }
 
    public boolean isTwoHanded(LivingEntity entity) {
       return this.isKatana(entity.func_184614_ca()) && entity.func_184592_cb().func_190926_b();
    }
 
 
    public boolean canKatanaDrop(LivingEntity source, LivingEntity target, ItemStack stack, Hand hand) {
       return this.isTwoHanded(source);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 50 ms
	
	Decompiled with FernFlower.
*/