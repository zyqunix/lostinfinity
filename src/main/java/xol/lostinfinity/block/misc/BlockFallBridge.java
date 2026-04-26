package xol.lostinfinity.block.misc;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicNoCollide;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockFallBridge.class */
public class BlockFallBridge extends BlockBasicNoCollide {
    public BlockFallBridge(String name) {
        super(name);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState state = blockAccess.func_180495_p(pos.func_177972_a(side));
        Block block = state.func_177230_c();
        return block != this;
    }

    @Override // xol.lostinfinity.block.basic.BlockBasicNoCollide
    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    @Override // xol.lostinfinity.block.basic.BlockBasicNoCollide
    public boolean func_149662_c(IBlockState state) {
        return false;
    }
}
