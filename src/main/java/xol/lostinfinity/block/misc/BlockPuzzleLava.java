package xol.lostinfinity.block.misc;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockPuzzleLava.class */
public class BlockPuzzleLava extends BlockBasicGlass {
    public BlockPuzzleLava(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.field_72995_K && (entityIn instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!player.func_184812_l_() && player.field_70173_aa % 5 == 0 && player.func_110143_aJ() > 0.0f) {
                player.func_70606_j(player.func_110143_aJ() - (player.func_110138_aP() / 6.0f));
            }
        }
    }
}
