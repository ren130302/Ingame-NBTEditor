package com.ren130302.meshi.block;

import java.util.Optional;
import java.util.function.Consumer;

import com.ruby.meshi.block.tileentity.CardboardTileEntity;
import com.ruby.meshi.client.CreativeTab;
import com.ruby.meshi.item.CardboardItem;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.CatLieOnBedGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Cardboard extends HorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 7.0D, 13.0D);

    public Cardboard(Block.Properties properties) {
	super(properties);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
	    BlockRayTraceResult hit) {
	if (!worldIn.isAirBlock(pos.up())) {
	    return false;
	}
	if (worldIn.isRemote) {
	    return true;
	}
	TileEntity tile = worldIn.getTileEntity(pos);
	if (tile != null && tile instanceof CardboardTileEntity) {
	    CardboardTileEntity cardTile = (CardboardTileEntity) tile;
	    if (cardTile.hasCatNBT()) {
		cardTile.createCatFromNBT();
		tile.getWorld().notifyBlockUpdate(pos, state, state, 3);
		cardTile.isOccupied = false;
	    } else if (!cardTile.isOccupied) {
		Optional<Entity> entity = this.searchCat(worldIn, pos);
		if (entity.isPresent()) {
		    CatEntity cat = (CatEntity) entity.get();
		    cat.goalSelector.addGoal(0, (new MoveCardboard(cat, 1.1D, 8, pos.up())).setEndExecute(g -> {
			cardTile.compoundCat(g.cat);
			tile.getWorld().notifyBlockUpdate(pos, state, state, 3);
		    }));
		    cardTile.isOccupied = true;
		}
	    }
	}
	return true;
    }

    public Optional<Entity> searchCat(World world, BlockPos pos) {
	return world.getEntitiesInAABBexcluding(null, (new AxisAlignedBB(pos)).grow(10.0D), e -> e instanceof CatEntity)
		.stream().findFirst();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
	return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	return new CardboardTileEntity();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	return SHAPE;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	return true;
    }

    @Override
    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return false;
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return false;
    }

    @Override
    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	return false;
    }

    @Override
    public Item getBlockItem(Block block, Item.Properties prop) {
	return new CardboardItem(block, prop.group(CreativeTab.ITEM_GROUP));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
	if (state.getBlock() != newState.getBlock()) {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	    if (tileentity instanceof CardboardTileEntity) {
		((CardboardTileEntity) tileentity).createCatFromNBT();
	    }
	    super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
    }

    private static class MoveCardboard extends CatLieOnBedGoal {
	CatEntity cat;
	Consumer<MoveCardboard> cons = a -> {
	};

	public MoveCardboard(CatEntity catIn, double speed, int length, BlockPos pos) {
	    super(catIn, speed, length);
	    this.cat = catIn;
	    this.destinationBlock = pos;
	}

	public MoveCardboard setEndExecute(Consumer<MoveCardboard> cons) {
	    this.cons = cons;
	    return this;
	}

	@Override
	public boolean shouldExecute() {
	    return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting() {
	    if (this.getTargetDistanceSq() < 1.5D) {
		this.cons.accept(this);
		return false;
	    }
	    return super.shouldContinueExecuting();
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	    return (worldIn.getBlockState(pos).getBlock() == SakuraBlocks.CARDBOARD);
	}

	@Override
	protected boolean searchForDestination() {
	    return true;
	}

	@Override
	public double getTargetDistanceSq() {
	    return this.destinationBlock.down().distanceSq(this.cat.getPosition());
	}
    }
}
