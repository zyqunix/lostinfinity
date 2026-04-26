package xol.lostinfinity.block.misc;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockLucientOre.class */
public class BlockLucientOre extends BlockBasicBoolState {
    public BlockLucientOre(String name) {
        super(name);
        func_149675_a(true);
        func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(ACTIVE, true));
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            int meta = func_176201_c(state);
            if (meta == 0) {
                worldIn.func_175656_a(pos, func_176203_a(1));
            }
        }
    }
}
