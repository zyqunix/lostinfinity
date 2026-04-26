package xol.lostinfinity.block.misc;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasic;
public class BlockCelestialBody extends BlockBasic {
    public BlockCelestialBody(String name) {
        super(name, Material.field_151592_s);
        func_149672_a(SoundType.field_185853_f);
        func_149715_a(1.0f);
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    public boolean func_149686_d(IBlockState state) {
        return false;
    }
    public boolean func_149662_c(IBlockState state) {
        return false;
    }
}
