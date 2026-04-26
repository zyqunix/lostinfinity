package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.activate.ItemComplexLostMap;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRowIncrementGame.class */
public class BlockRowIncrementGame extends BlockBasic {
    public BlockRowIncrementGame(String name) {
        super(name);
    }

    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.acidbloodSolution) && stack.func_190916_E() >= 10;
    }

    private boolean validMap(ItemStack stack) {
        if ((stack.func_77973_b() instanceof ItemComplexLostMap) && stack.func_77942_o() && stack.func_77978_p().func_74762_e("MapProgress") == 2) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Incorrect condition in loop: B:16:0x0094 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean func_180639_a(net.minecraft.world.World r12, net.minecraft.util.math.BlockPos r13, net.minecraft.block.state.IBlockState r14, net.minecraft.entity.player.EntityPlayer r15, net.minecraft.util.EnumHand r16, net.minecraft.util.EnumFacing r17, float r18, float r19, float r20) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.block.activator.BlockRowIncrementGame.func_180639_a(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand, net.minecraft.util.EnumFacing, float, float, float):boolean");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0045 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void reset(net.minecraft.world.World r12, net.minecraft.util.math.BlockPos r13, net.minecraft.block.state.IBlockState r14, net.minecraft.entity.player.EntityPlayer r15, net.minecraft.util.EnumHand r16, net.minecraft.util.EnumFacing r17, float r18, float r19, float r20) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.block.activator.BlockRowIncrementGame.reset(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand, net.minecraft.util.EnumFacing, float, float, float):void");
    }

    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(1, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.rowIncrementTile)) {
                return dir;
            }
        }
        return null;
    }
}
