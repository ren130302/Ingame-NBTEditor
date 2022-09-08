 package com.ruby.meshi.entity;
 
 import com.google.common.collect.Sets;
 import com.ruby.meshi.item.HiganItems;
 import com.ruby.meshi.item.Shuriken;
 import java.util.Collection;
 import java.util.Iterator;
 import java.util.Set;
 import java.util.function.Consumer;
 import java.util.function.Predicate;
 import javax.annotation.Nonnull;
 import net.minecraft.block.BlockState;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.IRendersAsItem;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.monster.EndermanEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity;
 import net.minecraft.entity.projectile.AbstractArrowEntity.PickupStatus;
 import net.minecraft.item.IItemTier;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.ItemTier;
 import net.minecraft.nbt.CompoundNBT;
 import net.minecraft.nbt.ListNBT;
 import net.minecraft.network.IPacket;
 import net.minecraft.network.datasync.DataParameter;
 import net.minecraft.network.datasync.DataSerializers;
 import net.minecraft.network.datasync.EntityDataManager;
 import net.minecraft.potion.EffectInstance;import net.minecraft.potion.PotionUtils;import net.minecraft.util.IndirectEntityDamageSource;import net.minecraft.util.SoundCategory;import net.minecraft.util.SoundEvents;import net.minecraft.util.Util;import net.minecraft.util.math.BlockPos;import net.minecraft.util.math.BlockRayTraceResult;import net.minecraft.util.math.EntityRayTraceResult;import net.minecraft.util.math.RayTraceResult;import net.minecraft.util.math.Vec3d;import net.minecraft.util.math.RayTraceResult.Type;import net.minecraft.world.World;import net.minecraftforge.api.distmarker.Dist;import net.minecraftforge.api.distmarker.OnlyIn;import net.minecraftforge.fml.network.NetworkHooks;import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
 @OnlyIn(
    value = Dist.CLIENT,
    _interface = IRendersAsItem.class
 )
 public class ShurikenEntity extends AbstractArrowEntity implements IRendersAsItem {
    private static final DataParameter<ItemStack> ITEMSTACK_DATA;
    private int timer = 0;
    public float xAngle;
    private final Set<EffectInstance> customPotionEffects;
    private int economy;
    private int infinity;
    private static final int STONE = 1;
    private static final int IRON = 2;
    private static final int DIAMOND = 3;
    private boolean isReturn;
    private int multiThrow;
    private float velocity;
    private float inaccuracy;
 
    static {
       ITEMSTACK_DATA = EntityDataManager.func_187226_a(ShurikenEntity.class, DataSerializers.field_187196_f);
    }
 
       this.xAngle = this.field_70146_Z.nextFloat() * 90.0F - 45.0F;
 
       this.customPotionEffects = Sets.newHashSet();
 
 
       this.economy = 0;
 
       this.infinity = 0;
 
 
 
 
       this.isReturn = false;
 
       this.multiThrow = 0;
 
 
 
    public ShurikenEntity(SpawnEntity packet, World worldIn) {
       super(HiganEntityType.SHURIKEN, worldIn);
       this.xAngle = this.field_70146_Z.nextFloat() * 90.0F - 45.0F;      this.customPotionEffects = Sets.newHashSet();      this.economy = 0;      this.infinity = 0;      this.isReturn = false;      this.multiThrow = 0;
    }
 
    public ShurikenEntity(LivingEntity livingEntityIn, World worldIn) {
       super(HiganEntityType.SHURIKEN, livingEntityIn, worldIn);
       this.func_189654_d(true);
    }
 
 
    public void func_184547_a(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
       super.func_184547_a(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
       this.velocity = velocity;
       this.inaccuracy = inaccuracy;
 
       this.field_70163_u -= 0.25D;
       this.func_213317_d(this.func_213322_ci().func_72441_c(0.0D, 0.05D, 0.0D));
    }
 
 
    public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) {
       super.func_70186_c(x, y, z, velocity, inaccuracy);
    }
 
 
 
    protected void func_184549_a(RayTraceResult result) {
       Type resultType = result.func_216346_c();
       if (resultType == Type.ENTITY) {
          this.attackEntity((EntityRayTraceResult)result);
       } else if (resultType == Type.BLOCK) {
          BlockRayTraceResult blockResult = (BlockRayTraceResult)result;
          BlockPos blockPos = blockResult.func_216350_a();
          BlockState state = this.field_70170_p.func_180495_p(blockPos);
          Vec3d vec3d = blockResult.func_216347_e().func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
          this.func_213317_d(vec3d);
          Vec3d vec3d1 = vec3d.func_72432_b().func_186678_a(0.05000000074505806D);
          this.field_70165_t -= vec3d1.field_72450_a;
          this.field_70163_u -= vec3d1.field_72448_b;
          this.field_70161_v -= vec3d1.field_72449_c;
          this.func_184185_a(state.func_215695_r().func_185846_f(), 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
          this.field_70254_i = true;
          this.field_70249_b = 7;
          this.func_70243_d(false);
          this.func_213872_b((byte)0);
          this.func_213865_o(false);
          state.func_215690_a(this.field_70170_p, state, blockResult, this);
       }
 
    }
 
    protected void attackEntity(EntityRayTraceResult result) {
       if (!this.field_70170_p.field_72995_K) {
          Entity target = result.func_216348_a();
          Entity shooter = this.func_212360_k();
 
          if (this.multiThrow > 0) {
             this.multiThrow();
          }
          if (this.multiThrow < 0) {
             target.field_70172_ad = 0;
          }
          float dmg = ((Shuriken)this.func_184550_j().func_77973_b()).getAttackDamage(this.func_184550_j());
          if (this.func_70241_g()) {
             dmg *= 1.5F;
          }
          dmg = (float)((double)dmg + this.func_70242_d());
 
          if (target.func_70097_a(new IndirectEntityDamageSource("shuriken", this, target), dmg)) {
             if (shooter != null && shooter instanceof LivingEntity) {
 
                ((LivingEntity)shooter).func_130011_c(target);
             }
 
             if (this.func_70027_ad() && !(target instanceof EndermanEntity)) {
                target.func_70015_d(5);
             }
             if (target instanceof LivingEntity && !this.customPotionEffects.isEmpty()) {
                Iterator var5 = this.customPotionEffects.iterator();               while(var5.hasNext()) {                  EffectInstance effectinstance1 = (EffectInstance)var5.next();
                   ((LivingEntity)target).func_195064_c(effectinstance1);
                }            }
 
             this.func_70106_y();
          }      }
 
    }
 
 
    protected void func_70088_a() {
       super.func_70088_a();
       this.func_184212_Q().func_187214_a(ITEMSTACK_DATA, ItemStack.field_190927_a);
    }
 
 
 
    public void func_70071_h_() {
       this.multiThrow();
 
       ++this.timer;
 
       float tempPitth = this.field_70125_A;
       float tempYaw = this.field_70177_z;
       super.func_70071_h_();
       this.field_70127_C = this.field_70125_A = tempPitth;
       this.field_70126_B = this.field_70177_z = tempYaw;
 
       if (!this.field_70254_i) {
          this.field_70125_A -= 36.0F;
 
       } else if (!this.field_70170_p.field_72995_K && this.isReturn && this.func_212360_k() instanceof PlayerEntity) {
 
          this.func_70100_b_((PlayerEntity)this.func_212360_k());
 
 
       }
 
       Vec3d motion = this.func_213322_ci();
       this.func_213293_j(motion.field_72450_a, motion.field_72448_b - 0.03D, motion.field_72449_c);
 
    }
 
    private void multiThrow() {
       if (!this.field_70170_p.field_72995_K && this.timer % 3 == 1 && this.multiThrow > 0) {
 
 
          Entity shooter = this.func_212360_k();
          if (shooter instanceof LivingEntity) {
             ItemStack stack = this.pickupInventoryShuriken();
             if (!stack.func_190926_b()) {
                ShurikenEntity entity = new ShurikenEntity((LivingEntity)shooter, this.field_70170_p);
                entity.setItemStack(stack);
                entity.func_184547_a(shooter, shooter.field_70125_A, shooter.field_70177_z, 0.0F, this.velocity, this.inaccuracy);
 
                entity.multiThrow = --this.multiThrow != 0 ? this.multiThrow : -1;
                entity.field_70251_a = this.field_70251_a;
                entity.infinity = this.infinity;
                entity.func_70243_d(this.func_70241_g());
                entity.setEffect(this.getEffect());
                entity.setIsReturn(this.isReturn);
                entity.func_70239_b(this.func_70242_d() * 0.699999988079071D);
                entity.func_70015_d(this.func_223314_ad());
                this.field_70170_p.func_217376_c(entity);
                this.field_70170_p.func_184148_a((PlayerEntity)null, shooter.field_70165_t, shooter.field_70163_u, shooter.field_70161_v, SoundEvents.field_187797_fA, SoundCategory.PLAYERS, 0.5F, 0.4F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
                this.multiThrow = -1;
             } else {
                this.multiThrow = 0;
             }
          } else {
             this.multiThrow = 0;
 
          }
       }
 
    }
 
    @Nonnull
    private ItemStack pickupInventoryShuriken() {
       if (this.infinity > 0) {
 
          return new ItemStack(this.infinity == 3 ? HiganItems.SHURIKEN_DIAMOND : (this.infinity == 2 ? HiganItems.SHURIKEN_IRON : HiganItems.SHURIKEN_STONE));
 
 
       } else if (this.func_212360_k() != null && this.func_212360_k() instanceof PlayerEntity) {
          PlayerEntity player = (PlayerEntity)this.func_212360_k();
          ItemStack itemstack = (ItemStack)player.field_71071_by.field_70462_a.stream().filter((s) -> {            return s.func_77973_b() instanceof Shuriken;         }).findFirst().orElse(ItemStack.field_190927_a);
          if (!player.field_71075_bZ.field_75098_d) {
             if (this.isNoPickup()) {
                itemstack = itemstack.func_77946_l();
                itemstack.func_190920_e(1);
             } else {
                itemstack = itemstack.func_77979_a(1);
             }         }
 
          return itemstack;
       } else {
          return ItemStack.field_190927_a;
       }
    }
 
 
    public boolean func_189652_ae() {
       return true;
    }
 
 
    public void func_70100_b_(PlayerEntity entityIn) {
       if (!this.field_70170_p.field_72995_K && (this.field_70254_i || this.func_203047_q()) && this.field_70249_b <= 0) {
          boolean flag = this.field_70251_a == PickupStatus.ALLOWED || this.field_70251_a == PickupStatus.CREATIVE_ONLY && entityIn.field_71075_bZ.field_75098_d || this.func_203047_q() && this.func_212360_k().func_110124_au() == entityIn.func_110124_au();
 
          if (!entityIn.field_71075_bZ.field_75098_d && !this.isNoPickup()) {
             if (this.field_70251_a == PickupStatus.ALLOWED && !entityIn.field_71071_by.func_70441_a(this.func_184550_j())) {
                flag = false;
             }
          } else {
             flag = true;
          }
          if (flag) {
             entityIn.func_71001_a(this, 1);
             this.func_70106_y();
          } else if (this.isReturn) {
             this.func_70106_y();
          }      }
 
    }
 
 
    public void func_70106_y() {
       super.func_70106_y();
    }
 
 
    public void func_70037_a(CompoundNBT compound) {
       super.func_70037_a(compound);
       ItemStack itemstack = ItemStack.func_199557_a(compound.func_74775_l("Item"));
       this.setItemStack(itemstack);      Iterator var3 = PotionUtils.func_185192_b(compound).iterator();
       while(var3.hasNext()) {         EffectInstance effectinstance = (EffectInstance)var3.next();
          this.addEffect(effectinstance);
       }
       this.infinity = compound.func_74762_e("infinity");
       this.economy = compound.func_74762_e("economy");
    }
 
 
    public void func_213281_b(CompoundNBT compound) {
       super.func_213281_b(compound);
       ItemStack itemstack = this.func_184550_j();
       if (!itemstack.func_190926_b()) {
          compound.func_218657_a("Item", itemstack.func_77955_b(new CompoundNBT()));
       }
       if (!this.customPotionEffects.isEmpty()) {
          ListNBT listnbt = new ListNBT();
 
          Iterator var4 = this.customPotionEffects.iterator();         while(var4.hasNext()) {            EffectInstance effectinstance = (EffectInstance)var4.next();
             listnbt.add(effectinstance.func_82719_a(new CompoundNBT()));
          }
          compound.func_218657_a("CustomPotionEffects", listnbt);
       }
       compound.func_74768_a("infinity", this.infinity);
       compound.func_74768_a("economy", this.economy);
    }
 
 
    public IPacket<?> func_213297_N() {
       return NetworkHooks.getEntitySpawningPacket(this);
    }
 
 
    public ItemStack func_184543_l() {
       ItemStack itemstack = this.func_184550_j();
       return itemstack.func_190926_b() ? new ItemStack(this.func_184550_j().func_77973_b()) : itemstack;
    }
 
    public void setItemStack(ItemStack stack) {
       if (stack.func_77973_b() != this.func_184550_j().func_77973_b() || stack.func_77942_o()) {
          this.func_184212_Q().func_187227_b(ITEMSTACK_DATA, Util.func_200696_a(stack.func_77946_l(), (p_213883_0_) -> {
             p_213883_0_.func_190920_e(1);
          }));
       }
    }
 
 
    protected ItemStack func_184550_j() {
       return (ItemStack)this.func_184212_Q().func_187225_a(ITEMSTACK_DATA);
    }
 
    public void setMultiThrow(int lvl) {
       this.multiThrow = lvl;
    }
 
    public void addEffect(EffectInstance effect) {
       this.customPotionEffects.add(effect);
    }
 
    public void setEffect(Collection<EffectInstance> effects) {
       this.customPotionEffects.clear();
       this.customPotionEffects.addAll(effects);
    }
 
    public Set<EffectInstance> getEffect() {
       return this.customPotionEffects;
    }
 
    public void setIsReturn(boolean isReturn) {
       this.isReturn = isReturn;
    }
 
    public void setInfinity(IItemTier iItemTier) {
       this.infinity = iItemTier == ItemTier.STONE ? 1 : (iItemTier == ItemTier.IRON ? 2 : (iItemTier == ItemTier.DIAMOND ? 3 : 0));
    }
 
    public void setEconomy(int eco) {
       this.economy = eco;
    }
 
    public void setNonPickup() {
       this.field_70251_a = PickupStatus.DISALLOWED;
    }
 
    public boolean isNoPickup() {
       return this.field_70251_a == PickupStatus.DISALLOWED;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 111 ms
	
	Decompiled with FernFlower.
*/