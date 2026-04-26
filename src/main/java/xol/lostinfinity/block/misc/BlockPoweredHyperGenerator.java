package xol.lostinfinity.block.misc;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.mob.entity.misc.EntityTornIndividual;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockPoweredHyperGenerator.class */
public class BlockPoweredHyperGenerator extends BlockBasic {
    public BlockPoweredHyperGenerator(String name) {
        super(name);
        func_149715_a(1.0f);
        func_149675_a(true);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, BlockInit.hyperGeneratorUnpowered.func_176223_P());
            EntityTornIndividual torn = new EntityTornIndividual(worldIn);
            torn.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p());
            worldIn.func_72838_d(torn);
        }
    }
}
