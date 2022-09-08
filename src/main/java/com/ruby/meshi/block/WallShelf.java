 package com.ruby.meshi.block;
 
 import com.ruby.meshi.block.WallShelf.1;
 import com.ruby.meshi.block.tileentity.WallShelfTileEntity;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockRenderType;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.ContainerBlock;
 import net.minecraft.block.Block.Properties;
 import net.minecraft.entity.EntityType;
 import net.minecraft.entity.LivingEntity;
 import net.minecraft.entity.player.PlayerEntity;
 import net.minecraft.inventory.IInventory;
 import net.minecraft.inventory.InventoryHelper;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.DirectionProperty;
 import net.minecraft.state.IProperty;
 import net.minecraft.state.StateContainer.Builder;
 import net.minecraft.state.properties.BlockStateProperties;
 import net.minecraft.tileentity.TileEntity;
 import net.minecraft.util.BlockRenderLayer;
 import net.minecraft.util.Direction;
 import net.minecraft.util.Hand;
 import net.minecraft.util.math.BlockPos;
 import net.minecraft.util.math.BlockRayTraceResult;
 import net.minecraft.util.math.shapes.ISelectionContext;
 import net.minecraft.util.math.shapes.VoxelShape;
 import net.minecraft.world.IBlockReader;
 import net.minecraft.world.World;import net.minecraftforge.api.distmarker.Dist;import net.minecraftforge.api.distmarker.OnlyIn;
 public class WallShelf extends ContainerBlock {
    public static final VoxelShape AABB_EAST = Block.func_208617_a(8.0D, 5.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    public static final VoxelShape AABB_WEST = Block.func_208617_a(0.0D, 5.0D, 0.0D, 8.0D, 6.0D, 16.0D);
    public static final VoxelShape AABB_NORTH = Block.func_208617_a(0.0D, 5.0D, 0.0D, 16.0D, 6.0D, 8.0D);
    public static final VoxelShape AABB_SOUTH = Block.func_208617_a(0.0D, 5.0D, 8.0D, 16.0D, 6.0D, 16.0D);
    public static final DirectionProperty HORIZONTAL_FACING;
 
    static {
       HORIZONTAL_FACING = BlockStateProperties.field_208157_J;
    }
    public WallShelf(Properties properties) {
       super(properties);
       this.func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a(HORIZONTAL_FACING, Direction.NORTH));
    }
 
 
    public BlockState func_196258_a(BlockItemUseContext context) {
       return (BlockState)this.func_176223_P().func_206870_a(HORIZONTAL_FACING, context.func_195992_f());
    }
 
 
    protected void func_206840_a(Builder<Block, BlockState> builder) {
       builder.func_206894_a(new IProperty[]{HORIZONTAL_FACING});
    }
 
 
    public void func_180633_a(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
       super.func_180633_a(worldIn, pos, state, placer, stack);
    }
 
 
 
    public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
       if (!worldIn.field_72995_K) {
          Direction dir = (Direction)state.func_177229_b(HORIZONTAL_FACING);
          WallShelfTileEntity tile = (WallShelfTileEntity)worldIn.func_175625_s(pos);
          double hitX = Math.abs((double)pos.func_177958_n() - hit.func_216347_e().func_82615_a());
          double hitZ = Math.abs((double)pos.func_177952_p() - hit.func_216347_e().func_82616_c());
          boolean isUpdated = false;
          if (hitX < 0.5D) {
             if (hitZ < 0.5D) {
 
                if (dir == Direction.NORTH) {
                   isUpdated = this.useTileContiner(tile, (byte)1, worldIn, pos, player, handIn);
                } else {
                   isUpdated = this.useTileContiner(tile, (byte)0, worldIn, pos, player, handIn);
 
 
                }
             } else if (dir == Direction.SOUTH) {
                isUpdated = this.useTileContiner(tile, (byte)0, worldIn, pos, player, handIn);
             } else {
                isUpdated = this.useTileContiner(tile, (byte)1, worldIn, pos, player, handIn);
 
 
             }
          } else if (hitZ < 0.5D) {
 
             if (dir == Direction.NORTH) {
                isUpdated = this.useTileContiner(tile, (byte)0, worldIn, pos, player, handIn);
             } else {
                isUpdated = this.useTileContiner(tile, (byte)1, worldIn, pos, player, handIn);
 
 
             }
          } else if (dir == Direction.SOUTH) {
             isUpdated = this.useTileContiner(tile, (byte)1, worldIn, pos, player, handIn);
          } else {
             isUpdated = this.useTileContiner(tile, (byte)0, worldIn, pos, player, handIn);
 
          }
 
          if (isUpdated) {
             worldIn.func_184138_a(pos, state, state, 2);
             worldIn.func_195592_c(pos, this);
          }      }
 
       return true;
    }
 
    private boolean useTileContiner(WallShelfTileEntity tile, byte side, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn) {
       boolean isCreative = player.func_184812_l_();      ItemStack stack;
       if (tile.hasItem(side)) {
          stack = tile.func_70304_b(side);
          if (!isCreative) {
             InventoryHelper.func_180173_a(worldIn, (double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), stack);
          }
          return true;
       } else {
          stack = player.func_184586_b(handIn);
          if (!stack.func_190926_b()) {
             if (isCreative) {
                ItemStack copy = stack.func_77946_l();
                copy.func_190920_e(1);
                tile.func_70299_a(side, copy);
             } else {
                tile.func_70299_a(side, stack.func_77979_a(1));
             }
             return true;
 
          } else {
             return false;
          }
       }
    }
    public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
       if (state.func_177230_c() != newState.func_177230_c()) {
          TileEntity tileentity = worldIn.func_175625_s(pos);
          if (tileentity instanceof IInventory) {
             InventoryHelper.func_180175_a(worldIn, pos, (IInventory)tileentity);
             worldIn.func_175666_e(pos, this);
          }
 
          super.func_196243_a(state, worldIn, pos, newState, isMoving);
       }
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       Direction dir = (Direction)state.func_177229_b(HORIZONTAL_FACING);
 
       switch(1.$SwitchMap$net$minecraft$util$Direction[dir.ordinal()]) {
       case 1:
          return AABB_EAST;
       case 2:
          return AABB_WEST;
       case 3:
          return AABB_SOUTH;
       case 4:
          return AABB_NORTH;
       default:
          return super.func_220053_a(state, worldIn, pos, context);
       }
    }
 
 
    public BlockRenderType func_149645_b(BlockState state) {
       return BlockRenderType.MODEL;
    }
 
 
    public TileEntity func_196283_a_(IBlockReader worldIn) {
       return new WallShelfTileEntity();
    }
 
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT;
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
       return adjacentBlockState.func_177230_c() == this ? true : super.func_200122_a(state, adjacentBlockState, side);
    }
 
 
    public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
       return true;
    }
 
 
    public boolean func_220060_c(BlockState state, IBlockReader worldIn, BlockPos pos) {
       return false;
    }
 
 
    public boolean func_220081_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
       return false;
    }
 
 
    public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
       return false;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 31 ms
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/