package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockUnpoweredHyperMelder extends BlockBasic {
    public BlockUnpoweredHyperMelder(String name) {
        super(name);
        func_149715_a(1.0f);
        func_149675_a(true);
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            int generators = 0;
            for (int addX = -9; addX < 9; addX++) {
                for (int addZ = -9; addZ < 9; addZ++) {
                    BlockPos testPos = new BlockPos(pos.func_177982_a(addX, -1, addZ));
                    if (worldIn.func_180495_p(testPos).func_177230_c() == BlockInit.hyperGeneratorPowered) {
                        generators++;
                    }
                }
            }
            if (generators >= 8) {
                worldIn.func_175656_a(pos, BlockInit.hyperMelderPowered.func_176223_P());
            }
        }
    }
}
