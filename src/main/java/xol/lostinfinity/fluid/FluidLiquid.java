package xol.lostinfinity.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import xol.lostinfinity.init.FluidInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/fluid/FluidLiquid.class */
public class FluidLiquid extends Fluid {
    public FluidLiquid(String fluidName, ResourceLocation still, ResourceLocation flowing) {
        super(fluidName, still, flowing);
        FluidInit.FLUIDS.add(this);
    }
}
