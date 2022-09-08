 package com.ruby.meshi.block;
 
 import java.util.Optional;
import java.util.function.Consumer;

import com.ruby.meshi.block.tileentity.CardboardTileEntity;
import com.ruby.meshi.client.CreativeTab;
import com.ruby.meshi.item.CardboardItem;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.CatLieOnBedGoal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
 
 
 public class Cardboard extends PlayerFacingBlock implements CustomItemBlock {
    public static final VoxelShape AABB = Block.func_208617_a(3.0D, 0.0D, 3.0D, 13.0D, 7.0D, 13.0D);
 
    public Cardboard(Properties properties) {
       super(properties);
    }
 
 
    public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
       if (!worldIn.func_175623_d(pos.func_177984_a())) {
          return false;
 
       } else if (worldIn.field_72995_K) {
          return true;
       } else {
          TileEntity tile = worldIn.func_175625_s(pos);
          if (tile != null && tile instanceof CardboardTileEntity) {
             CardboardTileEntity cardTile = (CardboardTileEntity)tile;
             if (cardTile.hasCatNBT()) {
                cardTile.createCatFromNBT();
                tile.func_145831_w().func_184138_a(pos, state, state, 3);
                cardTile.isOccupied = false;
 
             } else if (!cardTile.isOccupied) {
                Optional<Entity> entity = this.searchCat(worldIn, pos);
                if (entity.isPresent()) {
                   CatEntity cat = (CatEntity)entity.get();
                   cat.field_70714_bg.func_75776_a(0, (new Cardboard.MoveCardboard(cat, 1.1D, 8, pos.func_177984_a())).setEndExecute((g) -> {
                      cardTile.compoundCat(g.field_220727_g);
                      tile.func_145831_w().func_184138_a(pos, state, state, 3);
                   }));
                   cardTile.isOccupied = true;
                }            }
          }
 
          return true;
       }
    }
 
    public Optional<Entity> searchCat(World world, BlockPos pos) {
       return world.func_175674_a((Entity)null, (new AxisAlignedBB(pos)).func_186662_g(10.0D), (e) -> {
          return e instanceof CatEntity;
       }).stream().findFirst();
    }
    public boolean hasTileEntity(BlockState state) {
       return true;
    }
 
 
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
       return new CardboardTileEntity();
    }
 
 
    public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       return AABB;
    }
 
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT;
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
 
 
    public Item getBlockItem(Block block, net.minecraft.item.Item.Properties prop) {
       return new CardboardItem(block, prop.func_200916_a(CreativeTab.ITEM_GROUP));
    }
 
 
    public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
       if (state.func_177230_c() != newState.func_177230_c()) {
          TileEntity tileentity = worldIn.func_175625_s(pos);
          if (tileentity instanceof CardboardTileEntity) {
             ((CardboardTileEntity)tileentity).createCatFromNBT();
          }
 
          super.func_196243_a(state, worldIn, pos, newState, isMoving);
       }
    }
 
 
    private static class MoveCardboard extends CatLieOnBedGoal {
       CatEntity field_220727_g;
       Consumer<Cardboard.MoveCardboard> cons = (a) -> {
       };
 
       public MoveCardboard(CatEntity catIn, double speed, int length, BlockPos pos) {
          super(catIn, speed, length);
          this.field_220727_g = catIn;
          this.field_179494_b = pos;
       }
 
       public Cardboard.MoveCardboard setEndExecute(Consumer<Cardboard.MoveCardboard> cons) {
          this.cons = cons;
          return this;
       }
 
 
       public boolean func_75250_a() {
          return super.func_75250_a();
       }
 
 
       public boolean func_75253_b() {
          if (this.func_203110_f() < 1.5D) {
             this.cons.accept(this);
             return false;
          } else {
             return super.func_75253_b();
          }
       }
 
       protected boolean func_179488_a(IWorldReader worldIn, BlockPos pos) {
          return worldIn.func_180495_p(pos).func_177230_c() == SakuraBlocks.CARDBOARD;
       }
 
 
       protected boolean func_179489_g() {
          return true;
       }
 
 
       public double func_203110_f() {
          return this.field_179494_b.func_177977_b().func_177951_i(this.field_220727_g.func_180425_c());
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 44 ms
	
	Decompiled with FernFlower.
*/