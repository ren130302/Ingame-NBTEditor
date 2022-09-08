 package com.ruby.meshi.block.tileentity;
 
 import java.nio.ByteBuffer;

import com.ruby.meshi.block.Miniature;
import com.ruby.meshi.block.tileentity.MiniatureTileEntity.MiniWorld;
import com.ruby.meshi.init.HiganTileEntityType;

import net.minecraft.core.BlockPos;
import net.minecraft.fluid.IFluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class MiniatureTileEntity extends TileEntity {
    public static final BlockState EMPTY;
    public static final String STATE_NBT = "state";
    public VoxelShape shapeCache;
    public ByteBuffer renderSolidCache;
    public State renderSolidState;
    public ByteBuffer renderCache;
    public State renderState;
    static final String NO_TEX_RESIZE = "ntrs";
    static final String SIZE = "size";
    private int innerSize = 8;
    private boolean isNoTexResize = false;
    private final MiniWorld inner = new MiniWorld(this);
 
    static {
       EMPTY = Blocks.field_150350_a.func_176223_P();
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    public MiniatureTileEntity() {
       super(HiganTileEntityType.MINIATUE);
 
    }
 
    public void setSize(int size) {
       this.innerSize = size;
       this.inner.setSize(size);
    }
 
    public int getSize() {
       return this.innerSize;
    }
 
    public boolean setInnerState(BlockPos pos, BlockState state) {
       boolean isSet = this.inner.func_175656_a(pos, state);
       if (isSet) {
          this.func_145831_w().func_175656_a(this.func_174877_v(), (BlockState)this.func_195044_w().func_206870_a(Miniature.ENABLED, !this.isInnerEmpty()));
          this.func_70296_d();
       }
       return isSet;
    }
 
    public boolean isInnerEmpty() {
       return this.inner.isEmpty();
    }
 
 
 
 
    public BlockState getInnerState(BlockPos pos) {
       return this.inner.func_180495_p(pos);
    }
 
 
 
 
    public IFluidState getInnerFluidState(BlockPos pos) {
       return this.inner.func_204610_c(pos);
    }
 
    public World getInnerWorld() {
       return this.inner;
    }
 
 
 
 
    public BlockState getInnerState(int x, int y, int z) {
       return this.inner.getInnerBlockState(x, y, z);
    }
 
 
    public void func_70296_d() {
       super.func_70296_d();
       this.removeCache();
    }
 
    public void removeCache() {
       this.shapeCache = null;
       this.renderSolidCache = null;
       this.renderCache = null;
    }
 
 
    public void func_145839_a(CompoundNBT compound) {
       super.func_145839_a(compound);
       this.readData(compound);
    }
 
 
    public CompoundNBT func_189515_b(CompoundNBT compound) {
       CompoundNBT nbt = super.func_189515_b(compound);
       this.writeData(nbt);
       return nbt;
    }
 
 
 
    public void readData(CompoundNBT nbt) {
       if (nbt.func_74764_b("ntrs")) {
          this.isNoTexResize = nbt.func_74767_n("ntrs");
 
       }
 
       if (nbt.func_74764_b("size")) {
          this.innerSize = nbt.func_74762_e("size");
          this.inner.setSize(this.innerSize);
       }
       this.inner.loadState(nbt);
       this.removeCache();
    }
 
    public CompoundNBT writeData(CompoundNBT nbt) {
       this.inner.saveState(nbt);
       nbt.func_74757_a("ntrs", this.isNoTexResize);
       nbt.func_74768_a("size", this.innerSize);
       return nbt;
    }
 
 
    public void handleUpdateTag(CompoundNBT tag) {
       super.handleUpdateTag(tag);
       this.readData(tag);
    }
 
 
    public CompoundNBT func_189517_E_() {
       CompoundNBT tag = super.func_189517_E_();
       this.writeData(tag);
       return tag;
    }
 
 
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
       this.readData(pkt.func_148857_g());
    }
 
 
    public SUpdateTileEntityPacket func_189518_D_() {
       CompoundNBT var1 = new CompoundNBT();
       this.writeData(var1);
       return new SUpdateTileEntityPacket(this.func_174877_v(), 5, var1);
    }
 
 
    public void func_145834_a(World worldIn) {
       super.func_145834_a(worldIn);
       MiniWorld.access$002(this.inner, worldIn.func_201670_d());
    }
 
 
    public void func_189667_a(Rotation rotationIn) {
       if (rotationIn == Rotation.CLOCKWISE_90) {
          this.inner.rotateInnerState(false);
       } else if (rotationIn == Rotation.COUNTERCLOCKWISE_90) {
          this.inner.rotateInnerState(true);
       }
    }
 
    public boolean isInInnerRange(int x, int y, int z) {
       return this.inner.hasBlockRange(x, y, z);
    }
 
 
    public AxisAlignedBB getRenderBoundingBox() {
       return this.shapeCache != null && !this.shapeCache.func_197766_b() ? this.shapeCache.func_197752_a().func_186670_a(this.field_174879_c) : super.getRenderBoundingBox();
    }
 
    public boolean isNoTexResize() {
       return this.isNoTexResize;
    }
 
    public void setNoTexResize(boolean isNTRS) {
       this.isNoTexResize = isNTRS;
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 20 ms
	
	Decompiled with FernFlower.
*/