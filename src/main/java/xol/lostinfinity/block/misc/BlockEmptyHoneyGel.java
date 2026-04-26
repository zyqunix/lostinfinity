package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicNoCollide;
import xol.lostinfinity.init.BlockInit;
public class BlockEmptyHoneyGel extends BlockBasicNoCollide {
    public BlockEmptyHoneyGel(String name) {
        super(name);
        func_149675_a(true);
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            boolean foundEntity = false;
            for (EntityLivingBase entityLivingBase : world.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(pos))) {
                foundEntity = true;
            }
            if (!foundEntity) {
                world.func_175656_a(pos, BlockInit.honeyGel.func_176223_P());
            }
        }
    }
}
