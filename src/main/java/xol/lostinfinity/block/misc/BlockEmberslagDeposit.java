package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockEmberslagDeposit extends BlockBasic {
    public BlockEmberslagDeposit(String name) {
        super(name);
        func_149675_a(true);
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.func_175656_a(pos.func_177984_a(), BlockInit.emberslag.func_176223_P());
    }
}
