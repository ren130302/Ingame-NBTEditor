 package com.ruby.meshi.block;
 
 import com.ruby.meshi.block.tileentity.MillstoneTileEntity;
import com.ruby.meshi.client.CreativeTab;
import com.ruby.meshi.client.renderer.SimpleItemStackRender;

import net.minecraft.block.BlockRenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.IProperty;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
 public class Millstone extends Block implements CustomItemBlock {
    public static final BooleanProperty ENABLE;
 
    static {
       ENABLE = BlockStateProperties.field_208180_g;
    }
    public Millstone(Properties properties) {
       super(properties);
       this.func_180632_j((BlockState)this.func_176223_P().func_206870_a(ENABLE, false));
    }
 
 
    protected void func_206840_a(Builder<Block, BlockState> builder) {
       builder.func_206894_a(new IProperty[]{ENABLE});
    }
 
 
 
    public BlockRenderLayer func_180664_k() {
       return BlockRenderLayer.CUTOUT;
    }
 
 
    public BlockRenderType func_149645_b(BlockState state) {
       return BlockRenderType.INVISIBLE;
    }
 
 
    public boolean func_220051_a(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
       if (worldIn.field_72995_K) {
          return true;
       } else {
          INamedContainerProvider inamedcontainerprovider = this.func_220052_b(state, worldIn, pos);
          if (inamedcontainerprovider != null) {
             player.func_213829_a(inamedcontainerprovider);
             player.func_71029_a(Stats.field_199092_j.func_199076_b(Stats.field_188061_aa));
          }
          return true;
       }
    }
 
 
    public INamedContainerProvider func_220052_b(BlockState state, World world, BlockPos pos) {
       TileEntity tileentity = world.func_175625_s(pos);
       return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider)tileentity : null;
    }
 
 
    public boolean hasTileEntity(BlockState state) {
       return true;
    }
 
 
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
       return new MillstoneTileEntity();
    }
 
 
    public net.minecraft.item.Item.Properties getProperty(net.minecraft.item.Item.Properties prop) {
       return prop.func_200916_a(CreativeTab.ITEM_GROUP).setTEISR(() -> {
          return new SimpleItemStackRender(this);
       });
    }
    public boolean func_220067_a(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
       return false;
    }
 
 
 
    public void func_196243_a(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
       if (state.func_177230_c() != newState.func_177230_c()) {
          TileEntity tileentity = worldIn.func_175625_s(pos);
          if (tileentity instanceof MillstoneTileEntity) {
             InventoryHelper.func_180175_a(worldIn, pos, (MillstoneTileEntity)tileentity);
             worldIn.func_175666_e(pos, this);
          }
 
          super.func_196243_a(state, worldIn, pos, newState, isMoving);
       }
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 34 ms
	
	Decompiled with FernFlower.
*/