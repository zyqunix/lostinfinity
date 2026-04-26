package xol.lostinfinity.block.activator;

import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBattleSnakes;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockSnakeGuide.class */
public class BlockSnakeGuide extends BlockBasicRotational {
    public BlockSnakeGuide(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            AxisAlignedBB arena = ContestCoordinates.battleSnakesArenaAABB();
            EntityControllerBattleSnakes controller = null;
            Iterator it = worldIn.func_72872_a(EntityControllerBattleSnakes.class, arena).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EntityControllerBattleSnakes entity = (EntityControllerBattleSnakes) it.next();
                if (!entity.field_70128_L) {
                    controller = entity;
                    break;
                }
            }
            if (controller != null) {
                if (state.func_177230_c().equals(BlockInit.snakeGuideLeft)) {
                    controller.turnSnake(pos, false);
                    return true;
                }
                if (state.func_177230_c().equals(BlockInit.snakeGuideRight)) {
                    controller.turnSnake(pos, true);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
