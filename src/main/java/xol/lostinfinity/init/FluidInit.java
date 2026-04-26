package xol.lostinfinity.init;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicFluid;
import xol.lostinfinity.fluid.FluidLiquid;
import xol.lostinfinity.util.Reference;
public class FluidInit {
    public static final List<Fluid> FLUIDS = new ArrayList();
    public static Fluid concentratedAcid = registerLiquid("concentrated_acid");
    public static void registerFluids(Side side) {
        FLUIDS.forEach(it -> {
            FluidRegistry.registerFluid(it);
            FluidRegistry.addBucketForFluid(it);
        });
        if (side.isClient()) {
            BlockInit.BLOCKS.stream().filter(it2 -> {
                return it2 instanceof BlockBasicFluid;
            }).forEach(it3 -> {
                registerCustomFluid((BlockBasicFluid) it3);
            });
        }
    }
    private static Fluid registerLiquid(String regName) {
        return new FluidLiquid(regName, new ResourceLocation(Reference.MODID, "blocks/" + regName + "_still"), new ResourceLocation(Reference.MODID, "blocks/" + regName + "_flow"));
    }
    @SideOnly(Side.CLIENT)
    public static <T extends BlockBasicFluid> void registerCustomFluid(final T t) {
        ModelLoader.setCustomMeshDefinition(Item.func_150898_a(t), new ItemMeshDefinition() { // from class: xol.lostinfinity.init.FluidInit.1
            public ModelResourceLocation func_178113_a(ItemStack stack) {
                return new ModelResourceLocation(t.getRegistryName(), "fluid");
            }
        });
        ModelLoader.setCustomStateMapper(t, new StateMapperBase() { // from class: xol.lostinfinity.init.FluidInit.2
            protected ModelResourceLocation func_178132_a(IBlockState state) {
                return new ModelResourceLocation(t.getRegistryName(), "fluid");
            }
        });
    }
}
