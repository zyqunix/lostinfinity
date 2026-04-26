package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRingTile.class */
public class BlockRingTile extends BlockBasicBoolState {
    public BlockRingTile(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            ArrayList<BlockPos> adjblocks = new ArrayList<>();
            adjblocks.add(pos.func_177982_a(1, 0, 0));
            adjblocks.add(pos.func_177982_a(1, 0, 1));
            adjblocks.add(pos.func_177982_a(1, 0, -1));
            adjblocks.add(pos.func_177982_a(0, 0, 1));
            adjblocks.add(pos.func_177982_a(0, 0, -1));
            adjblocks.add(pos.func_177982_a(-1, 0, 1));
            adjblocks.add(pos.func_177982_a(-1, 0, 0));
            adjblocks.add(pos.func_177982_a(-1, 0, -1));
            for (BlockPos side : adjblocks) {
                if (worldIn.func_180495_p(side).equals(BlockInit.ringTile.func_176203_a(1))) {
                    worldIn.func_175656_a(side, BlockInit.ringTile.func_176203_a(0));
                } else if (worldIn.func_180495_p(side).equals(BlockInit.ringTile.func_176203_a(0))) {
                    worldIn.func_175656_a(side, BlockInit.ringTile.func_176203_a(1));
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
