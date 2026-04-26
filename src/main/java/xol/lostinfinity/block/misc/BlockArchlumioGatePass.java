package xol.lostinfinity.block.misc;

import java.util.ArrayList;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockArchlumioGatePass.class */
public class BlockArchlumioGatePass extends BlockBasicGlass {
    public BlockArchlumioGatePass(String name) {
        super(name);
        func_149715_a(1.0f);
        func_149675_a(true);
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        closeGate(worldIn, pos, null);
        super.func_180650_b(worldIn, pos, state, rand);
    }

    public void closeGate(World worldIn, BlockPos pos, ArrayList<BlockPos> visited) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        if (!visited.contains(pos)) {
            visited.add(pos);
            worldIn.func_175656_a(pos, BlockInit.archlumioGate.func_176223_P());
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (i != 0 || j != 0 || k != 0) {
                            BlockPos check = pos.func_177982_a(i, j, k);
                            Block block = worldIn.func_180495_p(check).func_177230_c();
                            if (block.equals(BlockInit.archlumioGatePass)) {
                                ((BlockArchlumioGatePass) block).closeGate(worldIn, check, visited);
                            }
                        }
                    }
                }
            }
        }
    }
}
