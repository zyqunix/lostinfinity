package xol.lostinfinity.block.basic;
import javax.annotation.Nullable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class BlockBasicNoCollide extends BlockBasic {
    public BlockBasicNoCollide(String name) {
        super(name, Material.field_151592_s);
        func_149672_a(SoundType.field_185853_f);
    }
    public BlockBasicNoCollide(String name, Material material) {
        super(name, material);
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
    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }
}
