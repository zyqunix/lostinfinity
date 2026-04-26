package xol.lostinfinity.block.basic;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.Event;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicFluid.class */
public class BlockBasicFluid extends BlockFluidClassic {
    public BlockBasicFluid(String name, Fluid fluid, Material material) {
        super(fluid, material);
        func_149663_c(name);
        setRegistryName(name);
    }

    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public void handleFill(FillBucketEvent event, IBlockState bs) {
        if (bs.func_177230_c().equals(this)) {
            event.getWorld().func_175656_a(event.getTarget().func_178782_a(), Blocks.field_150350_a.func_176223_P());
            event.setResult(Event.Result.ALLOW);
        } else {
            event.setResult(Event.Result.DENY);
        }
    }
}
