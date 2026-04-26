package xol.lostinfinity.block.misc;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.Event;
import xol.lostinfinity.block.basic.BlockBasicFluid;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.util.damagesource.LostDamageSources;
import xol.lostinfinity.util.data.IMaxAttack;
public class BlockConcentratedAcid extends BlockBasicFluid implements IMaxAttack {
    public BlockConcentratedAcid(String name, Fluid fluid, Material material) {
        super(name, fluid, material);
        BlockInit.BLOCKS.add(this);
    }
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (0 == 0 && (entityIn instanceof EntityLivingBase)) {
            EntityLivingBase e = (EntityLivingBase) entityIn;
            if ((entityIn instanceof EntityPlayer) && ((EntityPlayer) entityIn).func_184812_l_()) {
                return;
            }
            e.func_70097_a(LostDamageSources.CONCENTRATED_ACID.source, e.func_110138_aP() / 3.0f);
        }
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicFluid
    public void handleFill(FillBucketEvent event, IBlockState bs) {
        boolean result = false;
        if (bs.func_177230_c().equals(this)) {
            if (!event.getWorld().func_180494_b(event.getTarget().func_178782_a()).equals(DimensionInit.biomeCelestialArena)) {
                event.getWorld().func_175656_a(event.getTarget().func_178782_a(), Blocks.field_150350_a.func_176223_P());
            }
            event.setFilledBucket(FluidUtil.getFilledBucket(FluidRegistry.getFluidStack(this.fluidName, 1000)));
            event.setResult(Event.Result.ALLOW);
            result = true;
        }
        event.setResult(result ? Event.Result.ALLOW : Event.Result.DENY);
    }
}
