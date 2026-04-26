package xol.lostinfinity.block.basic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/ISpecialHarvest.class */
public interface ISpecialHarvest {
    Item getHarvestResult(World world, BlockPos blockPos);

    Item getToolNeeded();

    void worldHarvestEffect(World world, BlockPos blockPos, EntityPlayer entityPlayer);

    void failedHarvest(World world, BlockPos blockPos, EntityPlayer entityPlayer);

    boolean isHarvestable(World world, BlockPos blockPos, EntityPlayer entityPlayer);
}
