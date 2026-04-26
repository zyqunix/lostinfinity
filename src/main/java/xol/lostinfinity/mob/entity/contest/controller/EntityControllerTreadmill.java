package xol.lostinfinity.mob.entity.contest.controller;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.misc.EntityTreadmillObstacle;
import xol.lostinfinity.mob.entity.contest.misc.EntityTreadmillObstacleJumpable;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerTreadmill extends EntityControllerBase {
    private static final int treadHeight = 45;
    public EntityControllerTreadmill(World worldIn) {
        super(worldIn);
        func_70105_a(5.0f, 12.0f);
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.treadmillArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.treadmillControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            AxisAlignedBB treadmillBB = ContestCoordinates.treadmillGridAABB();
            int maxX = (int) treadmillBB.field_72336_d;
            int minZ = ((int) treadmillBB.field_72339_c) + 1;
            int maxZ = ((int) treadmillBB.field_72334_f) - 1;
            int count = 0;
            if (this.field_70173_aa % 40 == 0) {
                for (int i = minZ; i <= maxZ; i++) {
                    BlockPos obsPos = new BlockPos(maxX, 46, i);
                    if (count < 6 && this.field_70146_Z.nextInt(3) == 0) {
                        count++;
                        if (this.field_70146_Z.nextBoolean()) {
                            EntityTreadmillObstacle obs = new EntityTreadmillObstacle(this.field_70170_p);
                            obs.setVisual(this.field_70146_Z.nextInt(5) + 1);
                            obs.setSpeed(0.2f + (this.field_70146_Z.nextFloat() * 0.1f));
                            obs.func_70634_a(((double) obsPos.func_177958_n()) - 0.5d, obsPos.func_177956_o(), ((double) obsPos.func_177952_p()) + 0.5d);
                            this.field_70170_p.func_72838_d(obs);
                        } else {
                            EntityTreadmillObstacleJumpable obs2 = new EntityTreadmillObstacleJumpable(this.field_70170_p);
                            obs2.setVisual(this.field_70146_Z.nextInt(5) + 1);
                            obs2.setSpeed(0.2f + (this.field_70146_Z.nextFloat() * 0.1f));
                            obs2.func_70634_a(((double) obsPos.func_177958_n()) - 0.5d, obsPos.func_177956_o(), ((double) obsPos.func_177952_p()) + 0.5d);
                            this.field_70170_p.func_72838_d(obs2);
                        }
                    }
                }
            }
            this.field_70170_p.func_72872_a(EntityTreadmillObstacle.class, treadmillBB);
            List<EntityPlayer> arenaPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB());
            List<EntityPlayer> inPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, treadmillBB);
            for (EntityPlayer player : arenaPlayers) {
                if (!inPlayers.contains(player)) {
                    removePlayer(player);
                } else if (player.field_71075_bZ.field_75100_b) {
                    removePlayer(player);
                }
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.treadmillLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = 10 + (20 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 10 : 0);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaMythic, reward_count));
    }
}
