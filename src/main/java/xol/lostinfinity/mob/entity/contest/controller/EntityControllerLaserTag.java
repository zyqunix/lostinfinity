package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerLaserTag.class */
public class EntityControllerLaserTag extends EntityControllerBase {
    private HashMap<UUID, Integer> playerScores;
    private int time;
    private static final int duration = 5000;
    private List<BlockPos> spawnPositions;

    public void initLaserTag(List<BlockPos> spawnPositions) {
        this.spawnPositions.clear();
        this.spawnPositions.addAll(spawnPositions);
        this.time = 0;
        this.playerScores = new HashMap<>();
        for (UUID pl_id : getPlayerList()) {
            this.playerScores.put(pl_id, 0);
        }
    }

    public void hitPlayer(EntityPlayer attacker, EntityPlayer target, int numHits) {
        UUID attacker_id = null;
        if (attacker != null) {
            attacker_id = attacker.func_110124_au();
        }
        int score = 0;
        if (this.playerScores == null) {
            return;
        }
        if (attacker_id != null && this.playerScores.containsKey(attacker_id)) {
            score = this.playerScores.get(attacker_id).intValue() + numHits;
            this.playerScores.replace(attacker_id, Integer.valueOf(score));
            if (this.spawnPositions != null && !this.spawnPositions.isEmpty()) {
                int randSpawn = this.field_70146_Z.nextInt(this.spawnPositions.size());
                BlockPos spawn = this.spawnPositions.get(randSpawn);
                target.func_70634_a(spawn.func_177958_n(), spawn.func_177956_o(), spawn.func_177952_p());
            }
        }
        if (score != 0) {
            messageContenders(TextFmt.Red, String.format("%s SCORE: %d ", attacker.func_70005_c_(), Integer.valueOf(score)));
        }
    }

    public EntityControllerLaserTag(World worldIn) {
        super(worldIn);
        this.playerScores = null;
        this.time = 0;
        this.spawnPositions = new ArrayList();
        func_70105_a(5.0f, 12.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.laserTagArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2 && this.playerScores == null) {
                func_70106_y();
            }
            this.time++;
            if (this.time >= duration) {
                int numToRemove = getPlayerList().size() - 1;
                for (int i = 0; i < numToRemove; i++) {
                    int min = 1000;
                    UUID remove = null;
                    for (UUID pl_id : getPlayerList()) {
                        if (this.playerScores.get(pl_id).intValue() < min) {
                            min = this.playerScores.get(pl_id).intValue();
                            remove = pl_id;
                        }
                    }
                    if (remove != null) {
                        removePlayerByUUID(remove);
                    }
                }
            }
            List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB());
            for (EntityPlayer near_pl : inAABB) {
                if (near_pl.field_71075_bZ.field_75100_b) {
                    removePlayer(near_pl);
                }
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.laserTagControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.laserTagLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.laserZapper) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
        int reward_count = Math.min(10 + (20 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 10 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaMidnight, reward_count));
    }
}
