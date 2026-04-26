package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTrampolineDodgeball;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorTrampolineDodgeball.class */
public class EntityOperatorTrampolineDodgeball extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;

    public EntityOperatorTrampolineDodgeball(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        this.spawnPositions = ContestCoordinates.dodgeballSpawnPositions();
        for (EntityControllerTrampolineDodgeball entity : this.field_70170_p.func_72872_a(EntityControllerTrampolineDodgeball.class, getArenaAABB())) {
            entity.func_70106_y();
        }
        EntityControllerTrampolineDodgeball gamehologram = new EntityControllerTrampolineDodgeball(this.field_70170_p);
        BlockPos pos = ContestCoordinates.dodgeballControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        int curSpawn = 0;
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : this.contenders) {
            if (this.contenders.indexOf(pl_id) > 10) {
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
                new ItemStack(ItemInit.championDodgeball, 1);
                pl.func_191521_c(new ItemStack(ItemInit.championDodgeball, 1));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, eliminate the other players, and dodge their shots to win!"));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        gamehologram.setSpawnPositions(this.spawnPositions);
        gamehologram.initGame();
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.dodgeballArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.dodgeballLobbyAABB();
    }
}
