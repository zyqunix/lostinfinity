package xol.lostinfinity.block.basic;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class BlockBasicGlass extends BlockBasic {
    public BlockBasicGlass(String name) {
        super(name, Material.field_151592_s);
        func_149672_a(SoundType.field_185853_f);
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    @SideOnly(Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState state = blockAccess.func_180495_p(pos.func_177972_a(side));
        Block block = state.func_177230_c();
        return block != this;
    }
    public boolean func_149686_d(IBlockState state) {
        return false;
    }
    public boolean func_149662_c(IBlockState state) {
        return false;
    }
}
