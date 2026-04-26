package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
public class BlockSeaClay extends BlockBasicBoolState {
    public BlockSeaClay(String name) {
        super(name);
        func_149675_a(true);
        func_149711_c(3.0f);
        func_149752_b(5.0f);
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(pos.func_177982_a(-3, -3, -3), pos.func_177982_a(3, 3, 3));
            for (BlockPos nearpos : nearblocks) {
                if ((world.func_180495_p(nearpos).func_177230_c() instanceof BlockSeaClay) && world.field_73012_v.nextBoolean()) {
                    IBlockState nearState = world.func_180495_p(nearpos);
                    int meta = func_176201_c(nearState);
                    if (meta == 0) {
                        world.func_175656_a(nearpos, func_176203_a(1));
                    } else {
                        world.func_175656_a(nearpos, func_176203_a(0));
                    }
                }
            }
        }
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return ItemStack.field_190927_a.func_77973_b();
    }
}
