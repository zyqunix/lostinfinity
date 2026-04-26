package xol.lostinfinity.block.misc;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicPillar;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockTentacleEyePinkEmpty.class */
public class BlockTentacleEyePinkEmpty extends BlockBasicPillar {
    public BlockTentacleEyePinkEmpty(String name) {
        super(name);
        func_149675_a(true);
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            int meta = func_176201_c(world.func_180495_p(pos));
            world.func_175656_a(pos, BlockInit.pinkTentacleEye.func_176203_a(meta));
        }
    }
}
