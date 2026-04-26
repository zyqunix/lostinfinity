package xol.lostinfinity.block.misc;

import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockArchlumioGate.class */
public class BlockArchlumioGate extends BlockBasicGlass {
    public BlockArchlumioGate(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.field_72995_K && (entityIn instanceof EntityLivingBase)) {
            EntityLivingBase killed = (EntityLivingBase) entityIn;
            if (killed.func_110143_aJ() > 0.0f) {
                killed.func_70606_j(0.0f);
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SCANNER, SoundCategory.MASTER, 1.0f, 1.0f);
            }
        }
    }

    public void propogatePass(World worldIn, BlockPos pos, ArrayList<BlockPos> visited) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        if (!visited.contains(pos)) {
            visited.add(pos);
            worldIn.func_175656_a(pos, BlockInit.archlumioGatePass.func_176223_P());
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (i != 0 || j != 0 || k != 0) {
                            BlockPos check = pos.func_177982_a(i, j, k);
                            Block block = worldIn.func_180495_p(check).func_177230_c();
                            if (block.equals(BlockInit.archlumioGate)) {
                                ((BlockArchlumioGate) block).propogatePass(worldIn, check, visited);
                            }
                        }
                    }
                }
            }
        }
    }
}
