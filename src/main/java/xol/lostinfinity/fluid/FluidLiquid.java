package xol.lostinfinity.fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import xol.lostinfinity.init.FluidInit;
public class FluidLiquid extends Fluid {
    public FluidLiquid(String fluidName, ResourceLocation still, ResourceLocation flowing) {
        super(fluidName, still, flowing);
        FluidInit.FLUIDS.add(this);
    }
}
