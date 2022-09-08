 package com.ruby.meshi.block;
 
 import java.util.Random;

import com.ruby.meshi.block.SakuraLeave.SakuraType;
import com.ruby.meshi.client.paticle.PetalParticle;
import com.ruby.meshi.client.paticle.SakuraParticleManager;
import com.ruby.meshi.client.paticle.WindManager;
import com.ruby.meshi.init.SakuraConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 
 
 
 
 public class SakuraLeave extends LeavesBlock implements ExtraParticle {
    private SakuraType type;
 
    public SakuraLeave(Properties properties) {
       super(properties);
       this.type = SakuraType.GREEN;
    }
 
    public SakuraLeave setPetalType(SakuraType type) {
       this.type = type;
       return this;
    }
 
 
    public void func_196265_a(BlockState state, World worldIn, BlockPos pos, Random random) {
       super.func_196265_a(state, worldIn, pos, random);
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void func_180655_c(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
       super.func_180655_c(stateIn, worldIn, pos, rand);
 
    }
 
 
    @OnlyIn(Dist.CLIENT)
    public void paticleTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
       if ((double)rand.nextFloat() < (WindManager.isStrongWind() ? 0.1D : 0.05D)) {
          if (worldIn.func_175623_d(pos.func_177977_b())) {
             SakuraParticleManager.addEffect((new PetalParticle(worldIn, (double)((float)pos.func_177958_n() + this.getPetalRandomRange(rand)), (double)((float)pos.func_177956_o() - 0.1F), (double)((float)pos.func_177952_p() + this.getPetalRandomRange(rand)))).setPetal(this.type));
          }
          if (WindManager.isStrongWind()) {
             Direction dir = WindManager.getWindDir();
             if (worldIn.func_175623_d(pos.func_177972_a(dir))) {
                SakuraParticleManager.addEffect((new PetalParticle(worldIn, (double)((float)pos.func_177958_n() + this.getPetalRandomRange(rand) + (float)dir.func_82601_c()), (double)((float)pos.func_177956_o() + this.getPetalRandomRange(rand)), (double)((float)pos.func_177952_p() + this.getPetalRandomRange(rand) + (float)dir.func_82599_e()))).setPetal(this.type));
             }         }
       }
 
    }
 
    float getPetalRandomRange(Random rand) {
       return Math.min(rand.nextFloat() + 0.1F, 0.9F);
    }
 
 
    public BlockRenderLayer func_180664_k() {
       return (Boolean)SakuraConfig.CLIENT.sakuraFancyRender.get() ? BlockRenderLayer.CUTOUT_MIPPED : super.func_180664_k();
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 43 ms
	
	Decompiled with FernFlower.
*/