package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRowToggleButton.class */
public class BlockRowToggleButton extends BlockBasic {
    public BlockRowToggleButton(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Vec3i dir;
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K && (dir = findTileDir(worldIn, pos)) != null) {
            BlockPos blockPosFunc_177971_a = pos.func_177971_a(dir);
            while (true) {
                BlockPos nextPos = blockPosFunc_177971_a;
                if (worldIn.func_180495_p(nextPos).func_177230_c().equals(BlockInit.rowToggleTile)) {
                    int meta = ((BlockBasicBoolState) BlockInit.rowToggleTile).func_176201_c(worldIn.func_180495_p(nextPos));
                    if (meta == 0) {
                        worldIn.func_175656_a(nextPos, BlockInit.rowToggleTile.func_176203_a(1));
                    } else {
                        worldIn.func_175656_a(nextPos, BlockInit.rowToggleTile.func_176203_a(0));
                    }
                    blockPosFunc_177971_a = nextPos.func_177971_a(dir);
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 0));
        dirs.add(new Vec3i(-1, 0, 0));
        dirs.add(new Vec3i(0, 0, 1));
        dirs.add(new Vec3i(0, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.rowToggleTile)) {
                return dir;
            }
        }
        return null;
    }
}
