package xol.lostinfinity.block.basic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/ITargetable.class */
public interface ITargetable {
    void targetedResult(World world, EntityPlayer entityPlayer, BlockPos blockPos);
}
