package xol.lostinfinity.block.misc;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBlaze;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantDimTrader;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEnderman;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantPiglin;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkeleton;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSpider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantStray;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockTrackTrigger.class */
public class BlockTrackTrigger extends BlockBasicLight {
    private int active_num;

    public BlockTrackTrigger(String name, int triggernum) {
        super(name);
        this.active_num = 0;
        this.active_num = triggernum;
        BlockInit.TRIGGER_BLOCKS.add(this);
    }

    public void trigger(World world, BlockPos pos) {
        if (!world.field_72995_K) {
            switch (this.active_num) {
                case 0:
                    EntityDeviantCreeper Creeper = new EntityDeviantCreeper(world);
                    Creeper.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Creeper);
                    break;
                case 1:
                    EntityDeviantSkeleton Skeleton = new EntityDeviantSkeleton(world);
                    Skeleton.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Skeleton);
                    break;
                case 2:
                    EntityDeviantStray Stray = new EntityDeviantStray(world);
                    Stray.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Stray);
                    break;
                case 3:
                    EntityDeviantPiglin Piglin = new EntityDeviantPiglin(world);
                    Piglin.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Piglin);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    EntityDeviantEnderman Enderman = new EntityDeviantEnderman(world);
                    Enderman.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Enderman);
                    break;
                case 5:
                    EntityDeviantSpider Spider = new EntityDeviantSpider(world);
                    Spider.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Spider);
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                    EntityDeviantBlaze Blaze = new EntityDeviantBlaze(world);
                    Blaze.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Blaze);
                    break;
                case 7:
                    EntityDeviantDimTrader Dimtrader = new EntityDeviantDimTrader(world);
                    Dimtrader.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                    world.func_72838_d(Dimtrader);
                    break;
                case 8:
                    if (world.field_73012_v.nextBoolean()) {
                        EntityDeviantBlaze DevBlaze = new EntityDeviantBlaze(world);
                        DevBlaze.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p());
                        DevBlaze.setMutation(2);
                        world.func_72838_d(DevBlaze);
                    } else {
                        EntityItem crystal = new EntityItem(world, pos.func_177958_n(), pos.func_177956_o() + 3, pos.func_177952_p(), new ItemStack(ItemInit.crystallizedAlloy, 2 + world.field_73012_v.nextInt(3)));
                        crystal.field_70159_w = 0.0d;
                        crystal.field_70181_x = 0.0d;
                        crystal.field_70179_y = 0.0d;
                        world.func_72838_d(crystal);
                    }
                    break;
                case 9:
                    EntityItem crystal2 = new EntityItem(world, pos.func_177958_n(), pos.func_177956_o() + 3, pos.func_177952_p(), new ItemStack(ItemInit.masterForgedIngot, 5 + world.field_73012_v.nextInt(5)));
                    crystal2.field_70159_w = 0.0d;
                    crystal2.field_70181_x = 0.0d;
                    crystal2.field_70179_y = 0.0d;
                    world.func_72838_d(crystal2);
                    break;
            }
        }
    }

    public static Block randomTriggerBlock(Random rand) {
        return BlockInit.TRIGGER_BLOCKS.get(rand.nextInt(BlockInit.TRIGGER_BLOCKS.size()));
    }
}
