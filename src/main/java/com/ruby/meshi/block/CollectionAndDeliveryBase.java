package com.ruby.meshi.block;

import com.ruby.meshi.block.CollectionAndDeliveryBase.InventoryEntry;
import com.ruby.meshi.block.tileentity.CollectorPressurePlateTileEntity;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.level.block.BasePressurePlateBlock;

public abstract class CollectionAndDeliveryBase extends BasePressurePlateBlock {
	public static final BooleanProperty FORCE;
	final int horizonSize;
	final int verticalSize;

	static {
		FORCE = BlockStateProperties.field_208180_g;
	}

	public CollectionAndDeliveryBase(Properties properties, int horizon, int vertical) {
		super(Sensitivity.EVERYTHING, properties);
		this.horizonSize = horizon;
		this.verticalSize = vertical;
		this.func_180632_j((BlockState) this.func_176223_P().func_206870_a(FORCE, false));
	}

	public BlockState func_196258_a(BlockItemUseContext context) {
		return (BlockState) super.func_196258_a(context).func_206870_a(FORCE, context.func_195998_g());
	}

	protected void func_206840_a(Builder<Block, BlockState> builder) {
		super.func_206840_a(builder);
		builder.func_206894_a(new IProperty[] { FORCE });
	}

	public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.field_72995_K) {
			return true;
		} else {
			INamedContainerProvider inamedcontainerprovider = this.func_220052_b(state, worldIn, pos);
			if (inamedcontainerprovider != null) {
				player.func_213829_a(inamedcontainerprovider);
				player.func_71029_a(Stats.field_199092_j.func_199076_b(Stats.field_188063_ac));
			}

			return true;
		}
	}

	public INamedContainerProvider func_220052_b(BlockState state, World world, BlockPos pos) {
		TileEntity tileentity = world.func_175625_s(pos);
		return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
	}

	public boolean func_189539_a(BlockState state, World worldIn, BlockPos pos, int id, int param) {
       super.func_189539_a(state, worldIn, pos, id, param);
       TileEntity tileentity = worldIn.func_175625_s(pos);
       return tileentity == null ? false : tileentity.func_145842_c(id, param);
    }

	return world.func_180495_p(p).func_177230_c()instanceof ChestBlock?ChestBlock.func_220105_a(world.func_180495_p(p),world,p,false):world.func_175625_s(p);

	}).filter((e)->{return e instanceof IInventory&&!(e instanceof CollectorPressurePlateTileEntity);});IInventory.class.getClass();

	public Set<IInventory> getSurroundingIInventory(World world, BlockPos pos, int horizonSize, int verticalSize) {
       return (Set)var10000.map(IInventory.class::cast).collect(Collectors.toSet());
       Stream var10000 = BlockPos.func_218281_b(pos.func_177982_a(-horizonSize, 0, -horizonSize), pos.func_177982_a(horizonSize, verticalSize, horizonSize)).map(p -> {
    }

	public List<ItemStack> getSlotList(IInventory inv) {
       IntStream var10000 = IntStream.range(0, inv.func_70302_i_());
       inv.getClass();
       return (List)var10000.mapToObj(inv::func_70301_a).collect(Collectors.toList());
    }

	public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
       if (!worldIn.field_72995_K && entityIn instanceof PlayerEntity) {
          int i = this.func_176576_e(state);
          if (i == 0) {
             this.onPressedSwitch(state, worldIn, pos, (PlayerEntity)entityIn);
          }      }

       super.func_196262_a(state, worldIn, pos, entityIn);
    }

	public List<InventoryEntry> createInventoryEntrys(IInventory targetInventory, List<ItemStack> targetList, Predicate<ItemStack> stackTester, BiPredicate<Integer, ItemStack> slotTester) {
       com.ruby.meshi.block.CollectionAndDeliveryBase.InventoryEntry.Builder entryBuilder = InventoryEntry.build(targetInventory);
       return (List)IntStream.range(0, targetList.size()).mapToObj(slotId -> {

          ItemStack stack = (ItemStack)targetList.get(slotId);
          return stackTester.test(stack) && slotTester.test(slotId, stack) ? entryBuilder.create(slotId) : null;
       }).filter(e -> (e != null)).collect(Collectors.toList());
    }

	public void searchAndInsert(InventoryEntry fromEntry, InventoryEntry toEntry, boolean isEmptyInsert) {
       ItemStack fromStack = fromEntry.getStack();
       int slot = toEntry.getSlot();

       for(int i = 0; i < toEntry.getInventory().func_70302_i_(); ++i) {
          if (toEntry.insertStack(fromStack, slot)) {

             fromEntry.getInventory().func_70296_d();
             toEntry.getInventory().func_70296_d();
          }
          if (fromStack.func_190926_b()) {
             fromEntry.setStack(ItemStack.field_190927_a);
             break;
          }
          int nextSlot = toEntry.getNextStackableSlot(fromStack, slot);
          if (nextSlot >= 0) {
             slot = nextSlot;
          } else {
             if (!isEmptyInsert) {
                break;            }
             nextSlot = toEntry.getNextEmptySlot(slot);
             if (nextSlot < 0) {               break;            }
             slot = nextSlot;








          }
       }

    }

	public boolean hasTileEntity(BlockState state) {
       return true;
    }

	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
       return new CollectorPressurePlateTileEntity();
    }
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 102 ms
 * 
 * Decompiled with FernFlower.
 */