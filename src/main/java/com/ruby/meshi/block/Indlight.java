 package com.ruby.meshi.block;
 
 import com.ruby.meshi.block.Indlight.1;
 import com.ruby.meshi.client.CreativeTab;
 import java.util.Arrays;
 import java.util.List;
 import javax.annotation.Nullable;
 import net.minecraft.block.Block;
 import net.minecraft.block.BlockState;
 import net.minecraft.block.DirectionalBlock;
 import net.minecraft.block.Block.Properties;
 import net.minecraft.entity.EntityType;
 import net.minecraft.item.BlockItemUseContext;
 import net.minecraft.item.DyeColor;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.state.BooleanProperty;
 import net.minecraft.state.IProperty;import net.minecraft.state.properties.BlockStateProperties;import net.minecraft.util.BlockRenderLayer;import net.minecraft.util.Direction;import net.minecraft.util.math.BlockPos;import net.minecraft.util.math.shapes.ISelectionContext;import net.minecraft.util.math.shapes.VoxelShape;import net.minecraft.world.IBlockReader;import net.minecraft.world.IWorld;import net.minecraft.world.IWorldReader;import net.minecraft.world.storage.loot.LootContext.Builder;import net.minecraftforge.api.distmarker.Dist;import net.minecraftforge.api.distmarker.OnlyIn;
 public class Indlight extends DirectionalBlock implements SimpleColorMultiply<Indlight>, CustomItemBlock {
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    private int color;
    public static final BooleanProperty CONDITIONAL;
    public static final VoxelShape UP_AABB;
    public static final VoxelShape DOWN_AABB;
    public static final VoxelShape EAST_AABB;
    public static final VoxelShape NORTH_AABB;
    public static final VoxelShape SOUTH_AABB;
    public static final VoxelShape WEST_AABB;
 
    static {
       NORTH = BlockStateProperties.field_208151_D;
       EAST = BlockStateProperties.field_208152_E;
       SOUTH = BlockStateProperties.field_208153_F;
       WEST = BlockStateProperties.field_208154_G;
 
       CONDITIONAL = BlockStateProperties.field_208176_c;
       UP_AABB = Block.func_208617_a(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
       DOWN_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
       EAST_AABB = Block.func_208617_a(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
       NORTH_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
       SOUTH_AABB = Block.func_208617_a(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
       WEST_AABB = Block.func_208617_a(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    }
    public Indlight(Properties properties) {
       super(properties);
       this.func_180632_j((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a(field_176387_N, Direction.NORTH)).func_206870_a(NORTH, false)).func_206870_a(EAST, false)).func_206870_a(WEST, false)).func_206870_a(SOUTH, false)).func_206870_a(CONDITIONAL, false));
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       Direction dir = (Direction)state.func_177229_b(field_176387_N);
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
          return super.func_220053_a(state, worldIn, pos, context);
       }
    }
 
 
    public Indlight setColorCode(DyeColor dye) {
       int[] color_table = new int[]{16777215, 16750592, 13435085, 10092543, 16777012, 10092288, 16750797, 6710886, 10000536, 3447295, 6684877, 203, 3942166, 26164, 13369344, 0};
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
       this.color = color_table[dye.func_196059_a() % color_table.length];
       return this;
    }
 
 
    public List<ItemStack> func_220076_a(BlockState state, Builder builder) {
       List<ItemStack> stack = super.func_220076_a(state, builder);
       return stack != null && !stack.isEmpty() ? super.func_220076_a(state, builder) : Arrays.asList(new ItemStack(Item.func_150898_a(this)));
    }
 
 
 
 
 
    public int getColorCode() {
       return this.color;
    }
 
 
    @Nullable
    public BlockState func_196258_a(BlockItemUseContext context) {
       BlockState blockstate = this.func_176223_P();
       IWorldReader iworldreader = context.func_195991_k();
       BlockPos blockpos = context.func_195995_a();
       Direction[] adirection = context.func_196009_e();
       blockstate = (BlockState)blockstate.func_206870_a(CONDITIONAL, context.func_195998_g());
       Direction[] var6 = adirection;      int var7 = adirection.length;
       for(int var8 = 0; var8 < var7; ++var8) {         Direction direction = var6[var8];
          blockstate = this.setSideState((BlockState)blockstate.func_206870_a(field_176387_N, direction), iworldreader, blockpos);
 
          if (blockstate.func_196955_c(iworldreader, blockpos)) {
             return blockstate;
          }
       }
 
       return blockstate;
    }
 
 
    public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
       return this.setSideState(stateIn, worldIn, currentPos);
    }
 
    private BlockState setSideState(BlockState stateIn, IWorldReader worldIn, BlockPos currentPos) {
       switch(1.$SwitchMap$net$minecraft$util$Direction[((Direction)stateIn.func_177229_b(field_176387_N)).ordinal()]) {
       case 5:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST)));
 
 
 
       case 1:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST)));
 
 
 
       case 3:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)));
 
 
 
       case 4:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.EAST)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.WEST)));
 
 
 
 
       case 2:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)));
 
 
 
       case 6:
          return (BlockState)((BlockState)((BlockState)((BlockState)stateIn.func_206870_a(NORTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.UP)))).func_206870_a(SOUTH, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.DOWN)))).func_206870_a(EAST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.SOUTH)))).func_206870_a(WEST, this.canChain(worldIn, stateIn, currentPos.func_177972_a(Direction.NORTH)));
 
 
 
 
 
 
       default:
          return stateIn;
       }
    }
    private boolean canChain(IWorldReader worldIn, BlockState state, BlockPos targetPos) {
       BlockState targetState = worldIn.func_180495_p(targetPos);
       if (targetState.func_200015_d(worldIn, targetPos)) {
          return (Boolean)state.func_177229_b(CONDITIONAL);
 
       } else if (targetState.func_177230_c() instanceof Indlight && targetState.func_177229_b(field_176387_N) == state.func_177229_b(field_176387_N)) {
          return state.func_177229_b(CONDITIONAL) == targetState.func_177229_b(CONDITIONAL);
 
       } else {
          return false;
       }
    }
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.TRANSLUCENT;
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public float func_220080_a(BlockState state, IBlockReader worldIn, BlockPos pos) {
       return 1.0F;
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
 
 
    public void func_206840_a(net.minecraft.state.StateContainer.Builder<Block, BlockState> builder) {
       builder.func_206894_a(new IProperty[]{field_176387_N, EAST, SOUTH, WEST, NORTH, CONDITIONAL});
    }
 
 
    public net.minecraft.item.Item.Properties getProperty(net.minecraft.item.Item.Properties prop) {
       return prop.func_200916_a(CreativeTab.DECO_GROUP);
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 51 ms
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	ERROR: Unable to simplify switch on enum: 1 not found, available: {}
	
	Decompiled with FernFlower.
*/