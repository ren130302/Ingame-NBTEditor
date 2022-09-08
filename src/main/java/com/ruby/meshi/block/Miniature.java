 package com.ruby.meshi.block;
 
 import com.ruby.meshi.block.Miniature.1;
 import com.ruby.meshi.block.Miniature.DummyItemUseContext;
 import com.ruby.meshi.block.tileentity.MiniatureTileEntity;
 import java.util.List;
 import java.util.Optional;
 import java.util.function.Predicate;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.CropsBlock;
 import net.minecraft.block.DirectionalBlock;
 import net.minecraft.block.IWaterLoggable;
 import net.minecraft.block.RedstoneTorchBlock;
 import net.minecraft.block.Block.Properties;
 import net.minecraft.block.material.PushReaction;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.fluid.Fluid;
 import net.minecraft.fluid.Fluids;
 import net.minecraft.item.BlockItem;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.BoneMealItem;
 import net.minecraft.item.BucketItem;
 import net.minecraft.item.FlintAndSteelItem;
 import net.minecraft.item.HoeItem;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.Items;
 import net.minecraft.item.ShovelItem;
 import net.minecraft.pathfinding.PathType;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.ActionResultType;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.Rotation;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.RayTraceResult;
 import net.minecraft.util.math.Vec3d;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.util.math.shapes.VoxelShapes;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.IWorld;import net.minecraft.world.World;import net.minecraft.world.storage.loot.LootParameters;import net.minecraft.world.storage.loot.LootContext.Builder;
 public class Miniature extends DirectionalBlock {
    public static final VoxelShape UP_AABB = Block.func_208617_a(0.0D, 15.9D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape DOWN_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);
    public static final VoxelShape EAST_AABB = Block.func_208617_a(15.9D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 0.1D);
    public static final VoxelShape SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 15.9D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 0.1D, 16.0D, 16.0D);
    public VoxelShape[][][] shapes = this.makeShapes(this.getSize());
    public static final BooleanProperty ENABLED;
    public static final String BLOCK_ENTITY_TAG = "BlockEntityTag";
 
    static {
       ENABLED = BlockStateProperties.field_208180_g;
    }
 
    public Miniature(Properties properties) {
       super(properties);
       this.func_180632_j((BlockState)((BlockState)this.func_176223_P().func_206870_a(field_176387_N, Direction.NORTH)).func_206870_a(ENABLED, false));
    }
 
    int getSize() {
       return 8;
    }
 
    private VoxelShape[][][] makeShapes(int size) {
       VoxelShape[][][] shapes = new VoxelShape[size][size][size];
 
       float shapeOffset = 16.0F * (1.0F / (float)size);
       for(int i = 0; i < size; ++i) {
          double minX = (double)((float)i * shapeOffset);
          for(int j = 0; j < size; ++j) {
             double minY = (double)((float)j * shapeOffset);
             for(int k = 0; k < size; ++k) {
                double minZ = (double)((float)k * shapeOffset);
                shapes[i][j][k] = Block.func_208617_a(minX, minY, minZ, minX + (double)shapeOffset, minY + (double)shapeOffset, minZ + (double)shapeOffset);
             }         }
       }
 
       return shapes;
    }
 
 
    public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
       if (!worldIn.field_72995_K) {
          TileEntity te = worldIn.func_175625_s(pos);
          boolean isUpdated = false;
          if (te != null && te instanceof MiniatureTileEntity) {
             MiniatureTileEntity mini = (MiniatureTileEntity)te;
             ItemStack stack = player.func_184586_b(handIn);
             int size = this.getSize() == mini.getSize() ? this.getSize() : mini.getSize();
 
             BlockPos innerTargetPos = this.calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b().func_176734_d(), size);
             BlockPos innerHitPos = this.calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b(), size);
             BlockState innerTargeState;
             BlockState updateState;
             if (!stack.func_190926_b()) {
 
                ItemStack copyStack = stack.func_77946_l();
                innerTargeState = mini.getInnerState(innerTargetPos.func_177958_n(), innerTargetPos.func_177956_o(), innerTargetPos.func_177952_p());
 
                if (!this.onItemUse(worldIn, pos, mini, mini.getInnerWorld(), innerTargetPos, innerTargeState, stack, player, handIn, hit)) {
 
                   if (stack.func_77973_b() instanceof BlockItem) {
                      DummyItemUseContext context = new DummyItemUseContext(this, mini.getInnerWorld(), player, handIn, copyStack, hit);
                      if (innerTargeState.func_204520_s().func_206888_e()) {
                         context.innerPos = innerHitPos;
                      } else {
                         context.innerPos = innerTargetPos;
                      }
                      if (((BlockItem)copyStack.func_77973_b()).func_195942_a(context) == ActionResultType.SUCCESS) {
 
 
 
 
                         if (!(Boolean)state.func_177229_b(ENABLED)) {
                            worldIn.func_175656_a(pos, (BlockState)state.func_206870_a(ENABLED, true));
                         }
                         isUpdated = true;
                      }
                   } else if (stack.func_77973_b() instanceof BucketItem) {
                      Fluid fluid = ((BucketItem)stack.func_77973_b()).getFluid();
                      if (innerTargeState.func_177230_c() instanceof IWaterLoggable) {
                         IWaterLoggable container = (IWaterLoggable)innerTargeState.func_177230_c();
                         if (!fluid.func_207188_f().func_206888_e()) {
                            if (container.func_204510_a(mini.getInnerWorld(), innerTargetPos, innerTargeState, fluid)) {
                               isUpdated = container.func_204509_a(mini.getInnerWorld(), innerTargetPos, innerTargeState, fluid.func_207188_f());
                            } else {
                               BlockState fluidState = ((BucketItem)stack.func_77973_b()).getFluid().func_207188_f().func_206883_i();
                               if (!fluidState.hasTileEntity()) {
                                  isUpdated = mini.setInnerState(innerHitPos, fluidState);
                               }
                            }
                         } else {
                            isUpdated = container.func_204508_a(mini.getInnerWorld(), innerTargetPos, innerTargeState) != Fluids.field_204541_a;
 
                         }
                      } else if (!fluid.func_207188_f().func_206888_e()) {
                         BlockState fluidState = ((BucketItem)stack.func_77973_b()).getFluid().func_207188_f().func_206883_i();
                         if (!fluidState.hasTileEntity()) {
                            isUpdated = mini.setInnerState(innerHitPos, fluidState);
                         }
                      } else {
                         isUpdated = mini.setInnerState(innerTargetPos, MiniatureTileEntity.EMPTY);
 
                      }
                   }
                } else {
                   isUpdated = true;
 
                }
             } else {
                updateState = mini.getInnerState(innerTargetPos.func_177958_n(), innerTargetPos.func_177956_o(), innerTargetPos.func_177952_p());
                if (updateState != MiniatureTileEntity.EMPTY) {
 
 
 
 
                   updateState.func_177230_c().func_176208_a(mini.getInnerWorld(), innerTargetPos, updateState, player);
                   isUpdated = mini.setInnerState(innerTargetPos, MiniatureTileEntity.EMPTY);
 
                   this.validCheck(mini, innerTargetPos, player);
                   worldIn.func_175656_a(pos, (BlockState)state.func_206870_a(ENABLED, !mini.isInnerEmpty()));
 
                }
             }
 
             if (!isUpdated && this.isOuterPos(mini, innerHitPos)) {
 
                BlockPos outerPos = pos.func_177972_a(hit.func_216354_b());
                innerTargeState = worldIn.func_180495_p(outerPos);
                boolean isSuccess = false;
 
                if (innerTargeState.isAir(worldIn, outerPos)) {
                   Optional<ItemStack> searchItem = player.field_71071_by.field_70462_a.stream().filter((s) -> {                     return s.func_77969_a(new ItemStack(this));                  }).findFirst();
                   if (searchItem.isPresent() || player.func_184812_l_()) {
                      innerTargeState = (BlockState)((BlockState)this.func_176223_P().func_206870_a(field_176387_N, hit.func_216354_b().func_176734_d())).func_206870_a(ENABLED, true);
                      isSuccess = worldIn.func_175656_a(outerPos, innerTargeState);
                      if (isSuccess) {
                         if (!player.func_184812_l_()) {
                            ((ItemStack)searchItem.get()).func_190918_g(1);
                         }
                         ((MiniatureTileEntity)worldIn.func_175625_s(outerPos)).setSize(mini.getSize());
                         ((MiniatureTileEntity)worldIn.func_175625_s(outerPos)).setNoTexResize(mini.isNoTexResize());
                      }
                   }
                }
 
                if (isSuccess && innerTargeState.func_177230_c() instanceof Miniature) {
                   return innerTargeState.func_215687_a(worldIn, player, handIn, new BlockRayTraceResult(hit.func_216347_e(), hit.func_216354_b(), outerPos, hit.func_216353_d()));
 
 
                }
             }
 
             if (isUpdated) {
                updateState = worldIn.func_180495_p(pos);
                worldIn.func_184138_a(pos, updateState, updateState, 2);
                worldIn.func_195592_c(pos, this);
 
             }
          }
       }
 
       return true;
    }
 
 
 
 
 
    private boolean isOuterPos(MiniatureTileEntity mini, BlockPos innerHitPos) {
       return !mini.isInInnerRange(innerHitPos.func_177958_n(), innerHitPos.func_177956_o(), innerHitPos.func_177952_p());
    }
 
 
 
 
    @Deprecated
    ItemStack getReturnItem(World outerWorld, BlockPos outerPos, BlockState innerState) {
       ItemStack drop;
       if (innerState.func_177230_c() instanceof CropsBlock) {
          drop = ((CropsBlock)innerState.func_177230_c()).func_185473_a(outerWorld, outerPos, innerState);
       } else {
          drop = new ItemStack(innerState.func_177230_c());
       }
       return drop;
    }
 
 
 
 
 
 
 
 
    boolean onItemUse(World outerWorld, BlockPos outerPos, MiniatureTileEntity mini, World innerWorld, BlockPos innerPos, BlockState innerState, ItemStack stack, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
       if (Items.field_151137_ax == stack.func_77973_b()) {         boolean isPower;         BlockState powerState;
          if (innerState.func_196959_b(RedstoneTorchBlock.field_196528_a)) {
             isPower = (Boolean)innerState.func_177229_b(RedstoneTorchBlock.field_196528_a);
             powerState = (BlockState)innerState.func_206870_a(RedstoneTorchBlock.field_196528_a, !isPower);
             if (!powerState.hasTileEntity()) {
                mini.setInnerState(innerPos, powerState);
                return true;
             }         }
 
          if (innerState.func_196959_b(BlockStateProperties.field_208193_t)) {
             isPower = (Boolean)innerState.func_177229_b(BlockStateProperties.field_208193_t);
             powerState = (BlockState)innerState.func_206870_a(BlockStateProperties.field_208193_t, !isPower);
             if (!powerState.hasTileEntity()) {
                mini.setInnerState(innerPos, powerState);
                return true;
             }         }
       } else {
          if (stack.func_77973_b() == Item.func_150898_a(this)) {
             if (!mini.isInnerEmpty()) {
                ItemStack copy = player.func_184812_l_() ? new ItemStack(this) : stack.func_77979_a(1);
                mini.writeData(copy.func_190925_c("BlockEntityTag"));
                Block.func_180635_a(outerWorld, outerPos, copy);
             }
             return true;         }
          if (Items.field_151055_y == stack.func_77973_b()) {
             mini.func_189667_a(this.getRotation(player.func_174811_aO(), hit));
             return true;         }
          if ((stack.func_77973_b() instanceof HoeItem || stack.func_77973_b() instanceof ShovelItem || stack.func_77973_b() instanceof BoneMealItem || stack.func_77973_b() instanceof FlintAndSteelItem) && !outerWorld.field_72995_K && stack.func_196084_a((new DummyItemUseContext(this, innerWorld, player, handIn, stack.func_77946_l(), hit)).setPos(innerPos)) == ActionResultType.SUCCESS) {
 
 
             return true;
          }
       }
 
       return false;
    }
 
 
 
 
 
 
 
 
    Rotation getRotation(Direction direction, BlockRayTraceResult hit) {
       return Rotation.CLOCKWISE_90;
    }
    void validCheck(MiniatureTileEntity mini, BlockPos pos, PlayerEntity player) {      Direction[] var4 = Direction.values();      int var5 = var4.length;
 
       for(int var6 = 0; var6 < var5; ++var6) {         Direction dir = var4[var6];
          BlockPos offsetPos = pos.func_177971_a(dir.func_176730_m());
          BlockState s = mini.getInnerState(offsetPos.func_177958_n(), offsetPos.func_177956_o(), offsetPos.func_177952_p());
          if (s != MiniatureTileEntity.EMPTY && !s.func_196955_c(mini.getInnerWorld(), offsetPos)) {
             s.func_177230_c().func_176208_a(mini.getInnerWorld(), offsetPos, s, player);
             mini.setInnerState(offsetPos, MiniatureTileEntity.EMPTY);
             this.validCheck(mini, offsetPos, player);
          }      }
 
    }
 
 
    @Deprecated
    public List<ItemStack> func_220076_a(BlockState state, Builder builder) {
       List<ItemStack> list = super.func_220076_a(state, builder);
       ItemStack stack = (ItemStack)list.stream().findFirst().orElse(ItemStack.field_190927_a);
       if (stack != ItemStack.field_190927_a) {
          TileEntity te = (TileEntity)builder.func_216019_b(LootParameters.field_216288_h);
          if (te != null && te instanceof MiniatureTileEntity && !((MiniatureTileEntity)te).isInnerEmpty()) {
 
             ((MiniatureTileEntity)te).writeData(stack.func_190925_c("BlockEntityTag"));
          }
       }
 
       return list;
    }
 
    BlockPos calcHitPos(BlockPos pos, Vec3d hitVec, Direction face, int size) {
       Vec3d offsetVec = this.getOffsetVec3d(hitVec.func_178786_a((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p()), face, size);
       return new BlockPos(offsetVec.func_82615_a() / (double)(1.0F / (float)size), offsetVec.func_82617_b() / (double)(1.0F / (float)size), offsetVec.func_82616_c() / (double)(1.0F / (float)size));
    }
 
    Vec3d getOffsetVec3d(Vec3d vec, Direction face, int size) {
       return vec.func_72441_c((double)((float)face.func_82601_c() * (1.0F / (float)size) * 0.5F), (double)((float)face.func_96559_d() * (1.0F / (float)size) * 0.5F), (double)((float)face.func_82599_e() * (1.0F / (float)size) * 0.5F));
    }
 
 
    public void func_220069_a(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
       super.func_220069_a(state, worldIn, pos, blockIn, fromPos, isMoving);
       TileEntity te = worldIn.func_175625_s(pos);
       if (te != null && te instanceof MiniatureTileEntity) {
          worldIn.func_184138_a(pos, state, state, 2);
       }
    }
 
 
    public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
       BlockState updateState = super.func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
       TileEntity te = worldIn.func_175625_s(currentPos);
       if (te != null && te instanceof MiniatureTileEntity && worldIn.func_201670_d()) {
 
          ((MiniatureTileEntity)te).removeCache();
       }
 
       return updateState;
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       VoxelShape shape = VoxelShapes.func_197880_a();
       if (!(Boolean)state.func_177229_b(ENABLED)) {
          shape = this.getFaceShape((Direction)state.func_177229_b(field_176387_N));
       } else {         Direction[] var6 = Direction.values();         int var7 = var6.length;         int size;
          for(size = 0; size < var7; ++size) {            Direction dir = var6[size];
             BlockState offsetState = worldIn.func_180495_p(pos.func_177972_a(dir));
             if (offsetState.func_177230_c() != this && offsetState.func_196954_c(worldIn, pos.func_177972_a(dir)) == VoxelShapes.func_197868_b()) {
 
                shape = VoxelShapes.func_197872_a(shape, this.getFaceShape(dir));
 
             }
          }
 
          TileEntity te = worldIn.func_175625_s(pos);
          if (te != null && te instanceof MiniatureTileEntity) {
             MiniatureTileEntity mini = (MiniatureTileEntity)te;
             if (mini.shapeCache != null) {
                return mini.shapeCache;
             }
             size = this.getSize() == mini.getSize() ? this.getSize() : mini.getSize();
             VoxelShape[][][] s = this.getSize() == size ? this.shapes : this.makeShapes(size);
             for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                   for(int k = 0; k < size; ++k) {
                      BlockState innerState = mini.getInnerState(i, j, k);
                      if (innerState != MiniatureTileEntity.EMPTY) {
                         shape = VoxelShapes.func_197872_a(shape, s[i][j][k]);
                      }                  }
                }
             }
 
             mini.shapeCache = shape;
          }      }
 
       return shape;
    }
 
 
    VoxelShape getFaceShape(Direction dir) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
          return DOWN_AABB;
       case 2:
          return EAST_AABB;
       case 3:
          return NORTH_AABB;
       case 4:
          return SOUTH_AABB;
       case 5:
          return UP_AABB;
       case 6:
          return WEST_AABB;
       default:
          return VoxelShapes.func_197880_a();
       }
    }
 
    public BlockState func_196258_a(BlockItemUseContext context) {
       BlockState blockstate = this.func_176223_P();
       boolean flg = context.func_195996_i().func_179543_a("BlockEntityTag") != null && context.func_195996_i().func_179543_a("BlockEntityTag").func_74764_b("state");
       return (BlockState)((BlockState)blockstate.func_206870_a(field_176387_N, context.func_196000_l().func_176734_d())).func_206870_a(ENABLED, flg);
    }
 
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT;
    }
 
 
    public boolean hasTileEntity(BlockState state) {
       return true;
    }
 
 
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
       return new MiniatureTileEntity();
    }
 
 
    public BlockRenderType func_149645_b(BlockState state) {
       return (Boolean)state.func_177229_b(ENABLED) ? BlockRenderType.INVISIBLE : super.func_149645_b(state);
    }
 
 
    protected void func_206840_a(net.minecraft.state.StateContainer.Builder<Block, BlockState> builder) {
       super.func_206840_a(builder);
       builder.func_206894_a(new IProperty[]{ENABLED, field_176387_N});
    }
 
 
    public boolean func_196266_a(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
       return false;
    }
 
 
    public PushReaction func_149656_h(BlockState state) {
       return PushReaction.BLOCK;
    }
 
 
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader worldIn, BlockPos pos, PlayerEntity player) {
       TileEntity te = worldIn.func_175625_s(pos);
       if (te != null && te instanceof MiniatureTileEntity && target instanceof BlockRayTraceResult) {
 
          BlockRayTraceResult hit = (BlockRayTraceResult)target;
          int size = this.getSize() == ((MiniatureTileEntity)te).getSize() ? this.getSize() : ((MiniatureTileEntity)te).getSize();
          BlockPos innerTargetPos = this.calcHitPos(pos, hit.func_216347_e(), hit.func_216354_b().func_176734_d(), size);
          return new ItemStack(((MiniatureTileEntity)te).getInnerState(innerTargetPos).func_177230_c());
 
       } else {
          return this.getBlock().func_185473_a(worldIn, pos, state);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 199 ms
	 @deprecated 
	@deprecated ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/