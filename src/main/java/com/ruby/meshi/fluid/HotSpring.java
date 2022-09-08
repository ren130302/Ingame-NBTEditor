package com.ruby.meshi.fluid;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.ruby.meshi.block.SakuraBlocks;
import com.ruby.meshi.block.SpringSpawner;
import com.ruby.meshi.fluid.HotSpring.HotWater;
import com.ruby.meshi.fluid.HotSpring.SpringColor;
import com.ruby.meshi.item.HiganItems;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.fluid.IFluidState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.state.IProperty;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidAttributes.Water;

public abstract class HotSpring extends WaterFluid {
	public static final EnumProperty<SpringColor> COLOR = EnumProperty.func_177709_a("color", SpringColor.class);

	HotSpring() {
		this.func_207183_f((IFluidState) this.func_207188_f().func_206870_a(COLOR, SpringColor.DEFAULT));
	}

	protected void func_207184_a(Builder<Fluid, IFluidState> builder) {
		super.func_207184_a(builder);
		builder.func_206894_a(new IProperty[] { COLOR });
	}

	public boolean isEntityInside(IFluidState state, IWorldReader reader, BlockPos pos, Entity entity, double yToTest,
			Tag<Fluid> tag, boolean testingHead) {
		if (!reader.func_201670_d() && entity instanceof ItemEntity) {

			ItemStack stack = ((ItemEntity) entity).func_92059_d();
			if (stack.func_77973_b() instanceof DyeItem) {
				DyeColor dye = ((DyeItem) stack.func_77973_b()).func_195962_g();
				SpringColor dyeToColor = SpringColor.getColorToDye(dye);
				SpringColor stateColor = (SpringColor) state.func_177229_b(COLOR);
				if (dyeToColor != null && dye != stateColor.dye) {
					IWorld world = reader.func_217349_x(pos).getWorldForge();
					if (world != null) {
						Set<BlockPos> fluidPos = SpringSpawner.getFluidToList(reader, pos, this, 100);
						if (dye == DyeColor.BLACK) {
							dyeToColor = SpringColor.DEFAULT;
						}
						Iterator var15 = fluidPos.iterator();
						while (var15.hasNext()) {
							BlockPos p = (BlockPos) var15.next();
							world.func_180501_a(p, (BlockState) world.func_204610_c(p).func_206883_i()
									.func_206870_a(COLOR, dyeToColor), 3);
						}

						stack.func_190918_g(1);
					}
				}
			} else if (stack.func_77973_b() == Items.field_151123_aH) {
				IWorld world = reader.func_217349_x(pos).getWorldForge();
				if (world != null) {
					Set<BlockPos> fluidPos = SpringSpawner.getFluidToList(reader, pos, this, 100);
					fluidPos.forEach(px -> {
						world.func_180501_a(px, (BlockState) world.func_204610_c(px).func_206883_i()
								.func_206870_a(COLOR, SpringColor.VANILLA), 3);
					});
					stack.func_190918_g(1);
				}
			}
		}

		return super.isEntityInside(state, reader, pos, entity, yToTest, tag, testingHead);
	}

	protected void func_205574_a(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction,
			IFluidState fluidStateIn) {
		super.func_205574_a(worldIn, pos, blockStateIn, direction, (IFluidState) fluidStateIn.func_206870_a(COLOR,
				worldIn.func_180495_p(pos.func_177972_a(direction.func_176734_d())).func_177229_b(COLOR)));
	}

	protected IFluidState func_205576_a(IWorldReader worldIn, BlockPos pos, BlockState blockStateIn) {
		IFluidState state = super.func_205576_a(worldIn, pos, blockStateIn);
		return state.func_206886_c() == this.getFluid() && blockStateIn.func_177230_c() == SakuraBlocks.HOT_SPRING
				? (IFluidState) state.func_206870_a(COLOR, blockStateIn.func_204520_s().func_177229_b(COLOR))
				: state;
	}

	public Fluid func_210197_e() {
		return MeshiFluids.FLOWING_HOT_SPING;
	}

	public Fluid func_210198_f() {
		return MeshiFluids.HOT_SPING;
	}

	public Item func_204524_b() {
		return HiganItems.HOT_SPRING_BUCKET;
	}

	@OnlyIn(Dist.CLIENT)
	public void func_204522_a(World worldIn, BlockPos pos, IFluidState state, Random random) {
		super.func_204522_a(worldIn, pos, state, random);
		double d0;
		double d1;
		if (worldIn.func_175623_d(pos.func_177984_a()) && random.nextFloat() < 0.05F) {
			double d0 = (float) pos.func_177958_n() + random.nextFloat();
			d0 = (float) pos.func_177956_o() + 0.2F + (float) state.func_206882_g() * 0.1F;
			d1 = (float) pos.func_177952_p() + random.nextFloat();
			float color = 0.7F + 0.1F * random.nextFloat();
			Minecraft.func_71410_x().field_71452_i
					.func_199280_a(ParticleTypes.field_197601_L, d0, d0, d1, 0.0D, 0.0D, 0.0D)
					.func_70538_b(color, color, color);
		}
		Biome biome = worldIn.func_180494_b(pos);
		float bubbleRate = BiomeDictionary.getTypes(biome).contains(Type.NETHER) ? 0.2F : 0.02F;

		if (state.func_206889_d() && random.nextFloat() < bubbleRate) {
			d0 = (float) pos.func_177958_n() + random.nextFloat();
			d1 = (float) pos.func_177956_o() + 0.5F * random.nextFloat();
			double d2 = (float) pos.func_177952_p() + random.nextFloat();
			Minecraft.func_71410_x().field_71452_i.func_199280_a(ParticleTypes.field_197612_e, d0, d1, d2, 0.0D, 0.5D,
					0.0D);
			worldIn.func_184134_a(d0, d1, d2, SoundEvents.field_203253_U, SoundCategory.BLOCKS,
					random.nextFloat() * 0.25F + 0.5F, random.nextFloat() + 0.5F, false);
		}
	}

	public BlockState func_204527_a(IFluidState state) {
		return (BlockState) ((BlockState) SakuraBlocks.HOT_SPRING.func_176223_P()
				.func_206870_a(FlowingFluidBlock.field_176367_b, func_207205_e(state))).func_206870_a(COLOR,
						state.func_177229_b(COLOR));
	}

	public boolean func_207187_a(Fluid fluidIn) {
		return fluidIn == MeshiFluids.HOT_SPING || fluidIn == MeshiFluids.FLOWING_HOT_SPING;
	}

	protected boolean func_205579_d() {
		return false;
	}

	protected FluidAttributes createAttributes() {
		return new HotWater(
				Water.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"))
						.overlay(new ResourceLocation("block/water_overlay")).translationKey("block.meshi.hot_spring")
						.color(-12618012),
				this);
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar Total
 * time: 35 ms
 * 
 * Decompiled with FernFlower.
 */