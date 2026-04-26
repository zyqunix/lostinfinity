package xol.lostinfinity.block.misc;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
public class BlockContestEliminator extends BlockBasicGlass {
    public BlockContestEliminator(String name) {
        super(name);
    }
    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.field_72995_K) {
            entityIn.func_70634_a(entityIn.field_70165_t, entityIn.field_70163_u + 20.0d, entityIn.field_70161_v);
        }
    }
}
