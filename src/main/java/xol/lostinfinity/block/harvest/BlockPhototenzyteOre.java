package xol.lostinfinity.block.harvest;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/harvest/BlockPhototenzyteOre.class */
public class BlockPhototenzyteOre extends BlockBasicBoolState implements ISpecialHarvest {
    public BlockPhototenzyteOre(String name) {
        super(name);
        func_149675_a(true);
    }

    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }

    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.phototenzyte;
    }

    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }

    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
    }

    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return func_176201_c(world.func_180495_p(pos)) == 1;
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(pos.func_177982_a(-12, -12, -12), pos.func_177982_a(12, 12, 12));
            for (BlockPos nearpos : nearblocks) {
                if ((world.func_180495_p(nearpos).func_177230_c() instanceof BlockPhototenzyteOre) && world.field_73012_v.nextBoolean()) {
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
}
