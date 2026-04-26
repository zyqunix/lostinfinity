package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTreadmill;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorTreadmill.class */
public class EntityOperatorTreadmill extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;

    public EntityOperatorTreadmill(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        this.spawnPositions = ContestCoordinates.treadmillSpawnPositions();
        for (EntityControllerTreadmill entity : this.field_70170_p.func_72872_a(EntityControllerTreadmill.class, getArenaAABB())) {
            entity.func_70106_y();
        }
        EntityControllerTreadmill gamehologram = new EntityControllerTreadmill(this.field_70170_p);
        BlockPos pos = ContestCoordinates.treadmillControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        int curSpawn = 0;
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : this.contenders) {
            if (this.contenders.indexOf(pl_id) > 6) {
                toRemove.add(pl_id);
            }
        }
        for (UUID pl_id2 : toRemove) {
            this.contenders.remove(pl_id2);
            this.field_70170_p.func_152378_a(pl_id2).func_145747_a(new TextComponentString(TextFmt.Aqua + "Too many players, please join the next game"));
        }
        Iterator<UUID> it = this.contenders.iterator();
        while (it.hasNext()) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(it.next());
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, avoid the obstacles to survive!"));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.treadmillArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.treadmillLobbyAABB();
    }
}
