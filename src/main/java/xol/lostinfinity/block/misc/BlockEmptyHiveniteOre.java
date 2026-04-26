package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockEmptyHiveniteOre extends BlockBasic {
    public BlockEmptyHiveniteOre(String name) {
        super(name);
        func_149675_a(true);
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            world.func_175656_a(pos, BlockInit.hivenite.func_176223_P());
        }
    }
}
