package xol.lostinfinity.block.misc;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicBoolState;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockGalaxyGate.class */
public class BlockGalaxyGate extends BlockBasicBoolState {
    public BlockGalaxyGate(String name) {
        super(name);
        func_149715_a(1.0f);
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

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        if (blockState.equals(func_176203_a(1))) {
            return field_185506_k;
        }
        return super.func_180646_a(blockState, worldIn, pos);
    }
}
