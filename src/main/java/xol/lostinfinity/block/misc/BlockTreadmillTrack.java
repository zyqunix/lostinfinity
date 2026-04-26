package xol.lostinfinity.block.misc;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
public class BlockTreadmillTrack extends BlockBasic {
    public BlockTreadmillTrack(String name) {
        super(name);
    }
    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.field_72995_K) {
            entityIn.field_70159_w -= 0.03999999910593033d;
            entityIn.field_70133_I = true;
        }
    }
}
