package xol.lostinfinity.block.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.basic.ITargetable;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTargets;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockTarget.class */
public class BlockTarget extends BlockBasicBoolState implements ITargetable {
    public BlockTarget(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    @Override // xol.lostinfinity.block.basic.ITargetable
    public void targetedResult(World worldIn, EntityPlayer playerIn, BlockPos pos) {
        for (EntityControllerTargets controller : worldIn.func_72872_a(EntityControllerTargets.class, ContestCoordinates.targetsArenaAABB())) {
            controller.scoreTarget(pos);
        }
    }

    public IBlockState getActiveState() {
        return func_176223_P().func_177226_a(ACTIVE, true);
    }

    public IBlockState getInactiveState() {
        return func_176223_P().func_177226_a(ACTIVE, false);
    }
}
